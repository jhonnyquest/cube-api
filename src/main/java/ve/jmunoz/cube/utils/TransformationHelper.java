package ve.jmunoz.cube.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ve.jmunoz.cube.exceptions.TransformationException;

public class TransformationHelper {
	private static final ObjectMapper JSONMAPPER = new ObjectMapper();
	private static final String EXCEPTION_MESSAGE = "An Exception has occur while transforming ";
	private static final String ANGULAR_JSONHIJACKING_ESCAPE = ")]}',\n"; 

	public static String getJsonOfObject(Object source) {
		try {
			return JSONMAPPER.writeValueAsString(source);
		} catch (JsonProcessingException e) {
			throw new TransformationException(EXCEPTION_MESSAGE + source.toString() + "\n" + e.getOriginalMessage());
		}
	}

	public static String getJsonOfObjectForAngular(Object source) {
		try {
			String response = JSONMAPPER.writeValueAsString(source);
			return  ANGULAR_JSONHIJACKING_ESCAPE + response;
		} catch (JsonProcessingException e) {
			throw new TransformationException(EXCEPTION_MESSAGE + source.toString() + "\n" + e.getOriginalMessage());
		}
	}

	public static final void main(String[] args) {
	}

}
