package ve.jmunoz.cube.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ve.jmunoz.cube.exceptions.ExceptionHandler;
import ve.jmunoz.cube.models.OBContext;

public class TransformationHelper {
	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
	private static final String EXCEPTION_MESSAGE = "An Exception has occur while transforming ";

	public static String getJsonOfObject(Object source) {
		try {
			return OBJECTMAPPER.writeValueAsString(source);
		} catch (JsonProcessingException e) {
			throw new ExceptionHandler(EXCEPTION_MESSAGE + source.toString());
		}
	}
	
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
