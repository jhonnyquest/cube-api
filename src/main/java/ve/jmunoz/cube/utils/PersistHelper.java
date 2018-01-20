package ve.jmunoz.cube.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersistHelper {
	
	private static final String BASE_PATH = "/tmp/cube/";

	public static boolean createContext(String token) throws IOException {
		File file = new File(BASE_PATH + token + ".json");
		file.getParentFile().mkdirs(); 
		file.createNewFile();
		return true;
	}

	public static String getContent(String token) throws IOException {
		Path pathFile = FileSystems.getDefault().getPath(BASE_PATH + token + ".json");
		byte[] resp = Files.readAllBytes(pathFile);
		return (resp != null) ? new String(resp) : "";
	}
	
	public static boolean writeContent(String token, byte[] content) throws IOException {
		Path pathFile = FileSystems.getDefault().getPath(BASE_PATH + token + ".json");
		Files.write(pathFile, content);
		return true;
	}
	
	public static boolean deleteFile(String token, byte[] content) throws IOException {
		Path pathFile = FileSystems.getDefault().getPath(BASE_PATH + token + ".json");
		Files.delete(pathFile);
		return true;
	}

	public static void main(String args[]) {
		
	}
}
