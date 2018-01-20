package ve.jmunoz.cube.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Component;

import ve.jmunoz.cube.exceptions.ExceptionHandler;
import ve.jmunoz.cube.models.OBContext;
import ve.jmunoz.cube.models.OBCoordinate;
import ve.jmunoz.cube.models.OBCubeRequest;
import ve.jmunoz.cube.models.QueryResponse;
import ve.jmunoz.cube.models.OBqueryRequest;
import ve.jmunoz.cube.models.SimpleResponse;
import ve.jmunoz.cube.models.UpdateResponse;
import ve.jmunoz.cube.services.CubeManager;
import ve.jmunoz.cube.utils.PersistHelper;
import ve.jmunoz.cube.utils.TransformationHelper;


@Component
public class CubeManagerImpl implements CubeManager{
	
	private static final String GET_CONTENT_EXCEPTION_MESSAGE = "There was an exception while trying to get user data. ";
	private static final String WRITE_CONTENT_EXCEPTION_MESSAGE = "There was an exception while trying to store user data. ";
	private static final String QUERY_SUCCESS_MESSAGE = "Cube summation calculation successful. ";
	private static final String WRITE_CONTENT_SUCCESS_MESSAGE = "Data successfully stored. ";
	private static final String OUT_RANGE_COMMANDS_MESSAGE = "Commands out of range. ";
	private static final String OUT_RANGE_DIMENSION_MESSAGE = "Matrix dimension out of range. ";
	private static final String OUT_RANGE_TEST_CASES_MESSAGE = "Test cases out of range. ";
	private static final String OUT_RANGE_VALUE_MESSAGE = "Value out of range. ";
	private static final String OUT_RANGE_COORDINATE_MESSAGE = "Coordinate out of range";
	private static final String NO_MORE_TEST_CASES_MESSAGE = "There are no more test cases";

	public SimpleResponse test(String message) {
		SimpleResponse response = new SimpleResponse();
		response.setSuccess(true);
		response.setMessage("message from API: " + message);
		return response;
	}
	
	public SimpleResponse setTestCasesRequest(int testCases, String token){
		/*
		 * - Initialize main response variable and persist test cases
		 */
		SimpleResponse response = new SimpleResponse();
		OBContext context = new OBContext();
		OBCubeRequest request = new  OBCubeRequest();
		request.setTestCases(testCases);
		context.setInitData(request);
		try {
			PersistHelper.writeContent(
					token, 
					TransformationHelper.getJsonOfObject(context).getBytes()
			);
			response.setSuccess(true);
			response.setMessage(WRITE_CONTENT_SUCCESS_MESSAGE);
		} catch (IOException e) {
			throw new ExceptionHandler(WRITE_CONTENT_EXCEPTION_MESSAGE);
		}
		
		return response;
	}
	
	public SimpleResponse createCubeRequest(OBCubeRequest newCube, String token){
		/*
		 * - Initialize main response variable and test cases
		 */
		SimpleResponse response = new SimpleResponse();
		OBContext currentContext = getContext(token);
		newCube.setTestCases(currentContext.getInitData().getTestCases());
		
		try {
		/*
		 * - Validate if cube data are not restricted by constraints then create a new context
		 *   based on user given data. 
		 */
			SimpleResponse cubeValidated = validateCube(newCube);
			if(cubeValidated.isSuccess()){
				
		/*
		 *  - Create a new context and persist it.
		 */
				PersistHelper.createContext(token);
				float[][][] cube = new float
						[newCube.getDimension()]
						[newCube.getDimension()]
						[newCube.getDimension()];
				OBContext context = new OBContext();
				context.setInitData(newCube);
				context.setRemainingTestCases(newCube.getTestCases());
				context.setRemainingCommands(newCube.getCommands());
				context.setCube(cube);
				PersistHelper.writeContent(
						token, 
						TransformationHelper.getJsonOfObject(context).getBytes()
				);
		
		/*
		 * - Prepare and return successful response
		 */
				response.setSuccess(true);
				response.setMessage(WRITE_CONTENT_SUCCESS_MESSAGE);
				return response;
			}else
				return cubeValidated;
		} catch (IOException e) {
			throw new ExceptionHandler(WRITE_CONTENT_EXCEPTION_MESSAGE);
		}
	}
	
	public UpdateResponse updateCubeRequest(OBCoordinate coordinate, String token){
		
		/**
		 * - Initialize main variables: response to return operation response and context
		 *   to get stored context data.
		 */
		UpdateResponse response = new UpdateResponse();
		OBContext context = getContext(token);
		
		/**
		 * - Initialize operation variable cube to store cube matrix data
		 */
		float[][][] cube = new float
				[context.getInitData().getDimension()]
				[context.getInitData().getDimension()]
				[context.getInitData().getDimension()];
		
		/*
		 * - Set successful response to false
		 */
		response.setSuccess(false);
		
		/*
		 * - If there are no more commands then reinitialize commands and reduce test cases
		 *   it means that there are a new test case, note that cube is a new cube. Else then
		 *   cube is populated with stored cube data. 
		 */
		if(context.getRemainingCommands() == 0){
			context.setRemainingCommands(context.getInitData().getCommands());
			context.setRemainingTestCases(context.getRemainingTestCases() - 1);
		}else
			cube = context.getCube(); 
		
		/*
		 * - If there is more test cases remaining then store new value into given coordinates,
		 *   Else then close current cube summation process.  
		 */
		if(context.getRemainingTestCases() > 0){
			
		/*
		 * - If all coordinates are in dimension range and value is between allowed range 
		 *   then proceed to store the value in given coordinate, 
		 *   else return out of range coordinate message.
		 */
			if (validateCoordinateValue(coordinate, context.getInitData().getDimension()).isSuccess()){
				
		/*
		 * - Set cube value into given coordinates.
		 */
				cube[coordinate.getX() - 1][coordinate.getY() - 1][coordinate.getZ() - 1] = coordinate.getValue();
				context.setCube(cube);
		
		/*
		 * - Take of from the cube commands remaining the current command. It means subtract 1 to 
		 *   current command remaining
		 */
				context.setRemainingCommands(context.getRemainingCommands() - 1);
			
		/*
		 * Store context data updated with new cube data and command and/or test cases updated
		 */
				try {
					PersistHelper.writeContent(
							token, 
							TransformationHelper.getJsonOfObject(context).getBytes()
					);
				} catch (IOException e) {
					throw new ExceptionHandler(WRITE_CONTENT_EXCEPTION_MESSAGE);
				}
		
		/*
		 * - Prepare successful response
		 */
				response.setSuccess(true);
				response.setMessage(WRITE_CONTENT_SUCCESS_MESSAGE);
				response.setRemainingCommands(context.getRemainingCommands());
				response.setRemainingTestCases(context.getRemainingTestCases());
			}else
				response.setMessage(OUT_RANGE_COORDINATE_MESSAGE);
			
		}else
			response.setMessage(NO_MORE_TEST_CASES_MESSAGE);
		
		return response;
	}
	
	public QueryResponse queryCubeRequest(OBqueryRequest queryRequest, String token){
		/**
		 * - Initialize main variables: response to return operation response and context
		 *   to get stored context data.
		 */
		QueryResponse response = new QueryResponse();
		OBContext context = getContext(token);
		float[][][] cube = context.getCube();
		
		/*
		 * - If there are no more commands then reinitialize commands and reduce test cases
		 *   it means that there are a new test case. 
		 */
		if(context.getRemainingCommands() == 0){
			context.setRemainingCommands(context.getInitData().getCommands());
			context.setRemainingTestCases(context.getRemainingTestCases() - 1);
		}
		
		/*
		 * - If there is more test cases remaining then store new value into given coordinates,
		 *   Else then close current cube summation process.  
		 */
		if(context.getRemainingTestCases() > 0 && context.getRemainingCommands() > 0){
		
			if(context.getRemainingCommands() > 0){
		/*
		 * - Calculate cube summation between pair of coordinates given.
		 */
				float sum = 0;
				int i, j, k;
				for(i = queryRequest.getX1()-1 ; i <= queryRequest.getX2()-1 ; i++){
					for(j = queryRequest.getY1()-1 ; j <= queryRequest.getY2()-1 ; j++){
						for(k = queryRequest.getZ1()-1 ; k <= queryRequest.getZ2()-1 ; k++){
							sum = sum + cube[i][j][k];
						}
					}
				}
				
		/*
		 * - Take of from the cube commands remaining the current command. It means subtract 1 to 
		 *   current command remaining
		 */
				context.setRemainingCommands(context.getRemainingCommands() - 1);
			
		/*
		 * Store context data updated with new cube data and command and/or test cases updated
		 */
				try {
					PersistHelper.writeContent(
							token, 
							TransformationHelper.getJsonOfObject(context).getBytes()
					);
				} catch (IOException e) {
					throw new ExceptionHandler(WRITE_CONTENT_EXCEPTION_MESSAGE);
				}
				
		/*
		 * - Prepare and return query response 
		 */
				response.setSuccess(true);
				response.setMessage(QUERY_SUCCESS_MESSAGE);
				response.setResult(sum);
				response.setRemainingCommands(context.getRemainingCommands());
				response.setRemainingTestCases(context.getRemainingTestCases());
			}
		}else
			response.setMessage(NO_MORE_TEST_CASES_MESSAGE);
		
		return response;
	}
	
	public SimpleResponse deleteCubeRequest(String token){
		SimpleResponse response = new SimpleResponse();
		response.setSuccess(true);
		response.setMessage("message from API: Cube deleted successfully");
		return response;
	}

	private SimpleResponse validateCube(OBCubeRequest cube){
		
		/*
		 * - Populate message to return initial cube data validation response. 
		 */
		String message = "";
		message += (1 <= cube.getCommands() && cube.getCommands() <= 1000) 
				? "" : OUT_RANGE_COMMANDS_MESSAGE;
		message += (1 <= cube.getDimension() && cube.getDimension() <= 100) 
				? "" : OUT_RANGE_DIMENSION_MESSAGE;
		message += (1 <= cube.getTestCases() && cube.getTestCases() <= 50) 
				? "" : OUT_RANGE_TEST_CASES_MESSAGE;
		
		/*
		 * - Prepare and return initial cube data validation response
		 */
		SimpleResponse response = new SimpleResponse();
		boolean success = (message.equals("")) ? true : false;
		response.setSuccess(success);
		response.setMessage(message);
		return response;
	}
	
	private SimpleResponse validateCoordinateValue(OBCoordinate coordinate, int dimension ){
		
		/*
		 * - Populate message to return coordinate value validation response. Note that coordinates 
		 *   are given starting in 1 but arrays manage ranges starting in 0. 
		 */
		String message = "";
		message += ( Math.pow(-10,9) <= coordinate.getValue() && coordinate.getValue() <= Math.pow(10,9)) 
				? "" : OUT_RANGE_VALUE_MESSAGE;
		message += (coordinate.getX()  > dimension || coordinate.getY() > dimension || coordinate.getZ() > dimension
				|| coordinate.getX() <= 0 || coordinate.getY() <= 0 || coordinate.getX() <= 0) 
				? OUT_RANGE_COORDINATE_MESSAGE : "";
		
		/*
		 * - Prepare and return initial cube data validation response
		 */
		SimpleResponse response = new SimpleResponse();
		boolean success = (message.equals("")) ? true : false;
		response.setSuccess(success);
		response.setMessage(message);
		return response;
	}
	
	private OBContext getContext(String token){
		
		/*
		 * - Retrieve from persistence helper the current cube summation context
		 */
		try {
			String content = PersistHelper.getContent(token);
			OBContext context = new OBContext();
			context = TransformationHelper.getObjectOfJson(content);
			return context;
		} catch (IOException e) {
			throw new ExceptionHandler(GET_CONTENT_EXCEPTION_MESSAGE);
		}
	}
}
