package ve.jmunoz.cube.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <h1>Class PersistHelper</h1><br>
 * Persistence helper helps and abstract cube manager to manage persistence of cube summation data
 * 
 * @author jmunoz
 * @since 2018-01-21
 * @version 0.0.1
 */
public class PersistHelper {
	
	private static final Logger LOGGER = LogManager.getLogger(PersistHelper.class);
	
	/**
	 * <h2>Create a new user's context - createContext</h2> Create a new file to store user's 
	 * cube summation context data
	 * 
	 * @param String token: User token identifier
	 * @return boolean: Result of file context creation 
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	public static boolean createContext(String token) throws IOException {
		LOGGER.info("Starting to create new cube context");
		File file = new File(AppConfig.STORAGE_BASE_PATH + token + ".json");
		file.getParentFile().mkdirs(); 
		file.createNewFile();
		LOGGER.info("Create new cube context finished");
		return true;
	}

	/**
	 * <h2>Get user's context content- getContent</h2> Gets whole user's context content as string 
	 * 
	 * @param String token:User token identifier
	 * @return String: whole context string data 
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	public static String getContent(String token) throws IOException {
		LOGGER.info("Starting to get content from cube context");
		Path pathFile = FileSystems.getDefault().getPath(
				AppConfig.STORAGE_BASE_PATH + token + ".json");
		byte[] resp = Files.readAllBytes(pathFile);
		LOGGER.info("Get cube context finished");
		return (resp != null) ? new String(resp) : "";
	}
	
	/**
	 * <h2>Write user's context content- writeContent</h2> Store into a file the whole user's context
	 * information. 
	 * 
	 * @param String token: User token identifier
	 * @param String content: 
	 * @return String: whole context string data 
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	public static boolean writeContent(String token, byte[] content) throws IOException {
		LOGGER.info("Starting to write content to cube context");
		Path pathFile = FileSystems.getDefault().getPath(
				AppConfig.STORAGE_BASE_PATH + token + ".json");
		Files.write(pathFile, content);
		LOGGER.info("Write cube context finished");
		return true;
	}
	
	/**
	 * <h2>Delete user's context content- deleteContent</h2> Delete the file the user's context
	 * information file. 
	 * 
	 * @param String token: User token identifier
	 * @param String content: 
	 * @return String: whole context string data 
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	public static boolean deleteContent(String token) throws IOException {
		LOGGER.info("Starting to delete cube context");
		Path pathFile = FileSystems.getDefault().getPath(
				AppConfig.STORAGE_BASE_PATH + token + ".json");
		Files.delete(pathFile);
		LOGGER.info("Delete cube context finished");
		return true;
	}
	
	public static void main(String args[]) {
		
	}
}
