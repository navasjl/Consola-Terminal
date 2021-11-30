package Retos.RetoDia9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;

public class DateFactory {

	public static String fechaCreacion(File file) throws IOException {
		
		BasicFileAttributes attrs=Files.readAttributes(file.toPath(),BasicFileAttributes.class);
		FileTime creationTime=attrs.creationTime();
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");
		String fechaFormateada=dateFormat.format(creationTime.toMillis());
		
		return fechaFormateada;
	}
}
