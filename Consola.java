package Retos.RetoDia9;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*Creamos una Consola con 3 instruccione: ls, cd y pwd.
 * Comprobamos en main que el directorio existe y pedimos al usuario que introduzca
 * un comando  cada vez para mostrar ficheros y carpetas del direcorio actual (ls),
 * accder a un subdirectorio dentro del directorio actual (cd nombre_subdirectorio)
 * o imprima el nombre del directorio actual (pwd). 
 * Para salir de la consola, escribe 'exit' por pantalla
 * Ampliamos con comandos cd .. para ir al directorio raiz y ls -l para imprimir los archivos
 * mostrando el tamano y la fecha de creacion en formato lista*/

public class Consola {

	public static void main(String [] args) {
		
		Scanner entrada=new Scanner(System.in);
		String comando="";

		File file=(new File("C:\\Users\\Jose\\IdeaProjects\\Project_JavaBasico\\src"));
		
		System.out.println("Bienvenido!! Usted esta ahora mismo dentro de la consola de OpenBootcamp ");
		System.out.println("Puede elegir entre las siguientes opciones: ");
		System.out.println(" ls: Muestra todas los ficheros y las carpetas del directorio actual.");
		System.out.println(" ls -l: Muestra los archivos con su tamaño y fecha de creación");
		System.out.println(" cd 'nombre_directorio' : Accede al directorio especificado");
		System.out.println(" cd '..' : Accede al directorio raíz");
		System.out.println(" pwd: Imprime el directorio activo");
		System.out.println(" Escriba 'exit' si desea salir de la consola");
		
		comando=entrada.nextLine();
		String[] comandoDividido=comando.split("\\s+");
			
		while(!comandoDividido[0].equals("exit")) {
			
		
			switch(comandoDividido[0])
			{
				
				case "ls":
					
					if(file.isDirectory()) {
						if(comandoDividido.length==1)
							Comandos.ejecutarLS(file);
						else {
							try {
								if(comandoDividido[1].equals("-l"))
									Comandos.ejecutarLS_L(file);
							}catch (IOException e) {
								System.out.println("Error al leer el fichero"+e.getMessage());
							}
						}
							
					}
					else
						System.out.println("Error. El archivo actual no es un directorio.");
					
					break;
				
				case "cd":
					
					if(comandoDividido[1]=="..")
						file=Comandos.ejecutarCD(file, comandoDividido[1]);
					else {
						if(file.isDirectory()) 
							file=Comandos.ejecutarCD(file,comandoDividido[1]);
					
						else
							System.out.println("Error. El archivo actual no es un directorio.");
					}
					
					break;
				
				case "pwd":
				
					Comandos.ejecutarPWD(file);
					break;
				
				case "exit":
					break;
					
			}
			
			comando=entrada.nextLine();
			comandoDividido=comando.split("\\s+");
		}
		entrada.close();
		System.out.println("Muchas gracias por usar nuestra consola. Que tenga un buen día");
	
	}
		
}
