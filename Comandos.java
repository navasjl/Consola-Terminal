package Retos.RetoDia9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;


public class Comandos {

	

	public static void ejecutarLS(File dir) {
		
		String[] listFicheros = dir.list();
		String[] listCarpetas= new String[listFicheros.length];
		int contCarpetas=0;
		
		System.out.println("Ficheros:");
		System.out.println(" ");
		for(int i=0;i<listFicheros.length;i++) {
			
			//si no tiene punto o si el punto es el ultimo caracter(no tiene extension), es una carpeta
			if(listFicheros[i].indexOf('.')==-1 || listFicheros[i].indexOf('.')==listFicheros[i].length()-1)
			{
				listCarpetas[contCarpetas]=listFicheros[i];
				contCarpetas++;
			}
			else 
				System.out.println(listFicheros[i]);
					
		}
		
		System.out.println(" ");
		System.out.println("Carpetas:");
		System.out.println(" ");
		for(int i=0;i<contCarpetas;i++)
			System.out.println(listCarpetas[i]);
			
		
	}
	public static void ejecutarLS_L(File dir) throws IOException {
		
		File[] listFicheros = dir.listFiles();
		for(int i=0;i<listFicheros.length;i++) {
			System.out.format("%-50s%-5d bytes      	Fecha de creación: %-50s\n",
					listFicheros[i].getName(),Files.size(listFicheros[i].toPath()),
						fechaCreacion(listFicheros[i]));	
		}
		
	}
	private static String fechaCreacion(File file) throws IOException {
		
		BasicFileAttributes attrs=Files.readAttributes(file.toPath(),BasicFileAttributes.class);
		FileTime creationTime=attrs.creationTime();
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");
		String fechaFormateada=dateFormat.format(creationTime.toMillis());
		
		return fechaFormateada;
	}
	public static File ejecutarCD(File dir,String subdir) {
		
		String[] listFicheros = dir.list();
		boolean existe=false;//comprobar que subdir existe en el directorio dir
		
		if(!subdir.equals(".."))
		{
			for(int i=0;i<listFicheros.length && !existe;i++) {
			
				if(listFicheros[i].equals(subdir))
					existe=true;
			}
		
			if(!existe) {
				System.out.println("No se puede ejecutar CD si el subdirectorio no se encuentra en el directorio actual");
				return new File(dir.toString());
				//Si devuelvo null, en la siguiente iteracion da excepcion NullPointerException
				//de esta forma tengo el directorio actual para la siguiente iteracion
			}
			else {
				File fileResult=new File(dir.toString()+"\\"+subdir);
				return fileResult;
			}
		}
		else {
			return dir.getParentFile();
		}
			
	}
	public static void ejecutarPWD(File file) {
		
		System.out.println("El directorio activo es :"+file.toString());
	}
}
