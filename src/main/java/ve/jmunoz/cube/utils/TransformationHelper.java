package ve.jmunoz.cube.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ve.jmunoz.cube.exceptions.ExceptionHandler;
import ve.jmunoz.cube.models.OBContext;

/**
 * <h1>Class TransformationHelper</h1><br>
 * Transformation helper helps cube manager to manage data transformations.
 * 
 * @author jmunoz
 * @since 2018-01-22
 * @version 0.0.1
 */
public class TransformationHelper {
	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
	private static final 
		String EXCEPTION_MESSAGE = "An Exception has occur while transforming ";

	/**
	 * <h2>Get json string of object - getJsonOfObject</h2> Transform a given object to
	 * a new json string.
	 * 
	 * @param Object source: Object to transform
	 * @return String: Json string corresponding to object given 
	 * @since 2018-01-22
	 * @author jmunoz
	 */
	public static String getJsonOfObject(Object source) {
		try {
			return OBJECTMAPPER.writeValueAsString(source);
		} catch (JsonProcessingException e) {
			throw new ExceptionHandler(EXCEPTION_MESSAGE + source.toString());
		}
	}
	
	/**
	 * <h2>Get object of json string - getObjectOfJson</h2> Transform a given Json
	 * string to a new OBContext.
	 * 
	 * @param String jsonString: String corresponding to object
	 * @return OBContext: User's context object 
	 * @since 2018-01-22
	 * @author jmunoz
	 */
	public static OBContext getObjectOfJson(String jsonString){
		try {
			return OBJECTMAPPER.readValue(jsonString, OBContext.class);
		} catch (Exception e) {
			throw new ExceptionHandler(EXCEPTION_MESSAGE + jsonString);
		} 
	} 

	public static final void main(String[] args) {
	}

}
