package ve.jmunoz.cube.service.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import ve.jmunoz.cube.exceptions.ExceptionHandler;
import ve.jmunoz.cube.models.OBContext;
import ve.jmunoz.cube.models.OBCoordinate;
import ve.jmunoz.cube.models.CubeRequest;
import ve.jmunoz.cube.models.QueryResponse;
import ve.jmunoz.cube.models.QueryRequest;
import ve.jmunoz.cube.models.SimpleResponse;
import ve.jmunoz.cube.models.UpdateResponse;
import ve.jmunoz.cube.services.CubeManager;
import ve.jmunoz.cube.utils.AppConfig;
import ve.jmunoz.cube.utils.PersistHelper;
import ve.jmunoz.cube.utils.TransformationHelper;

/**
 * <h1>Class CubeManagerImpl</h1><br>
 * Implements all public operations related with cube summation management that include:<br>
 * <ul>
 * <li>setTestCasesRequest</li>
 * <li>createCubeRequest</li>
 * <li>updateCubeRequest</li>
 * <li>queryCubeRequest</li>
 * </ul>
 * This class also include private methods that supports the main operations such as:
 * <ul>
 * <li>validateCube</li>
 * <li>validateCoordinateValue</li>
 * <li>getContext</li>
 * <li>deleteCubeRequest</li>
 * <ul>
 * @author jmunoz
 * @since 2018-01-18
 * @version 0.0.1
 */

@Component
public class CubeManagerImpl implements CubeManager{
	
	AppConfig config;
	private static final Logger LOGGER = LogManager.getLogger(CubeManagerImpl.class);
	
	public CubeManagerImpl(){
		
	}
	/**
	 * <h2>Test application dispatcher - test</h2> Process test responses
	 * 
	 * @param String message:test message
	 * @return SimpleResponse: Simple result response
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	public SimpleResponse test(String message) {
		LOGGER.info("Starting to process test");
		SimpleResponse response = new SimpleResponse();
		response.setSuccess(true);
		response.setMessage("message from API: " + message);
		LOGGER.info("Test processing finished");
		return response;
	}
	
	/**
	 * <h2>Set new cube's test cases - setTestCasesRequest</h2> Method that process the cube 
	 * test cases.
	 * 
	 * 
	 * @param int testCases: Number of test cases
	 * @param String token: User token identifier
	 * @return SimpleResponse: Simple result response
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	public SimpleResponse setTestCasesRequest(int testCases, String token){
		LOGGER.info("Starting to set cube test cases");
		/**
		 * - Initialize main response variable and persist test cases
		 */
		SimpleResponse response = new SimpleResponse();
		OBContext context = new OBContext();
		CubeRequest request = new  CubeRequest();
		request.setTestCases(testCases);
		context.setInitData(request);
		try {
		/**
		 * - Validate if cube data are not restricted by constraints then create a new context
		 *   based on user given data. 
		 */
			SimpleResponse testCasesValidated = validateTestCases(testCases);
			if(testCasesValidated.isSuccess()){
				PersistHelper.writeContent(
						token, 
						TransformationHelper.getJsonOfObject(context).getBytes()
				);
				response.setSuccess(true);
				response.setMessage(AppConfig.WRITE_CONTENT_SUCCESS_MESSAGE);
			}else
				return testCasesValidated;
		} catch (IOException e) {
			throw new ExceptionHandler(AppConfig.WRITE_CONTENT_EXCEPTION_MESSAGE);
		}
		LOGGER.info("Stting test cases finished");
		return response;
	}
	
	/**
	 * <h2>Create new cube - createCubeRequest</h2> Method that process the cube 
	 * test cases.
	 * 
	 * 
	 * @param CubeRequest newCube: New cube basic meta data to be created
	 * @param String token: User token identifier
	 * @return SimpleResponse: Simple result response
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	public SimpleResponse createCubeRequest(CubeRequest newCube, String token){
		LOGGER.info("Starting to create new cube");
		/**
		 * - Initialize main response variable and test cases
		 */
		SimpleResponse response = new SimpleResponse();
		OBContext currentContext = getContext(token);
		newCube.setTestCases(currentContext.getInitData().getTestCases());
		
		try {
			
		/**
		 * - Validate if cube data are not restricted by constraints then create a new context
		 *   based on user given data. 
		 */
			SimpleResponse cubeValidated = validateCube(newCube);
			if(cubeValidated.isSuccess()){
				
		/**
		 *  - Create a new context and persist it.
		 */
				PersistHelper.createContext(token);
				double[][][] cube = new double
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
		
		/**
		 * - Prepare and return successful response
		 */
				response.setSuccess(true);
				response.setMessage(AppConfig.WRITE_CONTENT_SUCCESS_MESSAGE);
				LOGGER.info("Cube creation finished");
				return response;
			}else
				return cubeValidated;
		} catch (IOException e) {
			throw new ExceptionHandler(AppConfig.WRITE_CONTENT_EXCEPTION_MESSAGE);
		}
	}
	
	/**
	 * <h2>Update cube data - createCubeRequest</h2> Method that process cube data update 
	 * for a single value into a given coordinate.
	 * 
	 * 
	 * @param CubeRequest newCube: New cube basic meta data to be created
	 * @param String token: User token identifier
	 * @return SimpleResponse: Simple result response
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	public UpdateResponse updateCubeRequest(OBCoordinate coordinate, String token){
		LOGGER.info("Starting to update cube value");
		/**
		 * - Initialize main variables: response to return operation response and context
		 *   to get stored context data.
		 */
		UpdateResponse response = new UpdateResponse();
		OBContext context = getContext(token);
		
		/**
		 * - Initialize operation variable cube to store cube matrix data
		 */
		double[][][] cube = new double
				[context.getInitData().getDimension()]
				[context.getInitData().getDimension()]
				[context.getInitData().getDimension()];
		
		/**
		 * - Set successful response to false
		 */
		response.setSuccess(false);
		
		/**
		 * - If there are no more commands then reinitialize commands and reduce test cases
		 *   it means that there are a new test case, note that cube is a new cube. Else then
		 *   cube is populated with stored cube data. 
		 */
		if(context.getRemainingCommands() == 0){
			context.setRemainingCommands(context.getInitData().getCommands());
			context.setRemainingTestCases(context.getRemainingTestCases() - 1);
		}else
			cube = context.getCube(); 
		
		/**
		 * - If there is more test cases remaining then store new value into given coordinates,
		 *   Else then close current cube summation process.  
		 */
		if(context.getRemainingTestCases() > 0){
			
		/**
		 * - If all coordinates are in dimension range and value is between allowed range 
		 *   then proceed to store the value in given coordinate, 
		 *   else return out of range coordinate message.
		 */
			if (validateCoordinateValue(
					coordinate, 
					context.getInitData().getDimension()).isSuccess()){
				
		/**
		 * - Set cube value into given coordinates.
		 */
				cube[coordinate.getX() - 1][coordinate.getY() - 1][coordinate.getZ() - 1] 
						= coordinate.getValue();
				context.setCube(cube);
		
		/**
		 * - Take of from the cube commands remaining the current command. It means subtract 1 to 
		 *   current command remaining
		 */
				context.setRemainingCommands(context.getRemainingCommands() - 1);
			
		/**
		 * Store context data updated with new cube data and command and/or test cases updated
		 */
				try {
					PersistHelper.writeContent(
							token, 
							TransformationHelper.getJsonOfObject(context).getBytes()
					);
				} catch (IOException e) {
					throw new ExceptionHandler(AppConfig.WRITE_CONTENT_EXCEPTION_MESSAGE);
				}
		
		/**
		 * - Prepare successful response
		 */
				response.setSuccess(true);
				response.setMessage(AppConfig.WRITE_CONTENT_SUCCESS_MESSAGE);
				response.setRemainingCommands(context.getRemainingCommands());
				response.setRemainingTestCases(context.getRemainingTestCases());
			}else
				response.setMessage(AppConfig.OUT_RANGE_COORDINATE_MESSAGE);
			
		}else
			response.setMessage(AppConfig.NO_MORE_TEST_CASES_MESSAGE);
		LOGGER.info("Update cube value finished");
		return response;
	}
	
	/**
	 * <h2>Query cube cube - queryCubeRequest</h2> Method that process cube data summation 
	 * for a paired given coordinates set.
	 * 
	 * 
	 * @param QueryRequest queryRequest: Query request for cube summation
	 * @param String token: User token identifier
	 * @return QueryResponse: Query result Response
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	public QueryResponse queryCubeRequest(QueryRequest queryRequest, String token){
		LOGGER.info("Starting to set cube test cases");
		/**
		 * - Initialize main variables: response to return operation response and context
		 *   to get stored context data.
		 */
		QueryResponse response = new QueryResponse();
		OBContext context = getContext(token);
		double[][][] cube = context.getCube();
		
		/**
		 * - If there are no more commands then reinitialize commands and reduce test cases
		 *   it means that there are a new test case. 
		 */
		if(context.getRemainingCommands() == 0){
			context.setRemainingCommands(context.getInitData().getCommands());
			context.setRemainingTestCases(context.getRemainingTestCases() - 1);
		}
		
		/**
		 * - If there is more test cases remaining then store new value into given coordinates,
		 *   Else then close current cube summation process.  
		 */
		if(context.getRemainingTestCases() > 0 && context.getRemainingCommands() > 0){
		
			if(context.getRemainingCommands() > 0){
				
		/**
		 * - Calculate cube summation between pair of coordinates given.
		 */
				double sum = 0;
				int i, j, k;
				for(i = queryRequest.getX1()-1 ; i <= queryRequest.getX2()-1 ; i++){
					for(j = queryRequest.getY1()-1 ; j <= queryRequest.getY2()-1 ; j++){
						for(k = queryRequest.getZ1()-1 ; k <= queryRequest.getZ2()-1 ; k++){
							sum = sum + cube[i][j][k];
						}
					}
				}
				
		/**
		 * - Take of from the cube commands remaining the current command. It means subtract 1 to 
		 *   current command remaining
		 */
				context.setRemainingCommands(context.getRemainingCommands() - 1);
			
		/**
		 * - Store context data updated with new cube data and command and/or test cases updated
		 */
				try {
					PersistHelper.writeContent(
							token, 
							TransformationHelper.getJsonOfObject(context).getBytes()
					);
				} catch (IOException e) {
					throw new ExceptionHandler(AppConfig.WRITE_CONTENT_EXCEPTION_MESSAGE);
				}
				
		/**
		 * - Prepare and return query response 
		 */
				response.setSuccess(true);
				response.setMessage(AppConfig.QUERY_SUCCESS_MESSAGE);
				response.setResult(sum);
				response.setRemainingCommands(context.getRemainingCommands());
				response.setRemainingTestCases(context.getRemainingTestCases());
			}
		}else
			response.setMessage(AppConfig.NO_MORE_TEST_CASES_MESSAGE);
		LOGGER.info("Query cube summation finished");
		return response;
	}

	/**
	 * <h2>Validate cube  - validateCube</h2> Method that validate cube creation meta data.
	 * 
	 * 
	 * @param CubeRequest cube: Query request for cube summation
	 * @return SimpleResponse: Validation result Response
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	private SimpleResponse validateCube(CubeRequest cube){
		LOGGER.info("Starting to validate cube");
		/**
		 * - Populate message to return initial cube data validation response. 
		 */
		String message = "";
		message += (
				AppConfig.MIN_COMMANDS_VALUE <= cube.getCommands() 
				&& cube.getCommands() <= AppConfig.MAX_COMMANDS_VALUE) 
				? "" : AppConfig.OUT_RANGE_COMMANDS_MESSAGE;
		message += (
				AppConfig.MIN_DIMENSION_VALUE <= cube.getDimension() 
				&& cube.getDimension() <= AppConfig.MAX_DIMENSION_VALUE) 
				? "" : AppConfig.OUT_RANGE_DIMENSION_MESSAGE;
		
		/**
		 * - Prepare and return initial cube data validation response
		 */
		SimpleResponse response = new SimpleResponse();
		boolean success = (message.equals("")) ? true : false;
		response.setSuccess(success);
		response.setMessage(message);
		LOGGER.info("Validate cube metadata finished");
		return response;
	}
	
	/**
	 * <h2>Validate test cases  - validateCube</h2> Method that validate cube creation meta data.
	 * 
	 * 
	 * @param CubeRequest cube: Query request for cube summation
	 * @return SimpleResponse: Validation result Response
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	private SimpleResponse validateTestCases(int testCases){
		LOGGER.info("Starting to validate test cases");
		/**
		 * - Populate message to return test cases validation response. 
		 */
		String message = "";
		message += (
				AppConfig.MIN_TEST_CASES_VALUE <= testCases 
				&& testCases <= AppConfig.MAX_TEST_CASES_VALUE) 
				? "" : AppConfig.OUT_RANGE_TEST_CASES_MESSAGE;
		
		/**
		 * - Prepare and return test cases validation response
		 */
		SimpleResponse response = new SimpleResponse();
		boolean success = (message.equals("")) ? true : false;
		response.setSuccess(success);
		response.setMessage(message);
		LOGGER.info("Validate test cases finished");
		return response;
	}
	
	/**
	 * <h2>Validate coordinate value - validateCoordinateValue</h2> Method that validate cube coordinate.
	 * 
	 * 
	 * @param OBCoordinate coordinate: Cube coordinates
	 * @return SimpleResponse: Validation result Response
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	private SimpleResponse validateCoordinateValue(OBCoordinate coordinate, int dimension ){
		LOGGER.info("Starting to validate coordinates and value");
		/**
		 * - Populate message to return coordinate value validation response. Note that coordinates 
		 *   are given starting in 1 but arrays manage ranges starting in 0. 
		 */
		String message = "";
		message += ( 
				AppConfig.MIN_CUBE_VALUE <= coordinate.getValue() 
				&& coordinate.getValue() <= AppConfig.MAX_CUBE_VALUE) 
				? "" : AppConfig.OUT_RANGE_VALUE_MESSAGE;
		message += (
				coordinate.getX()  > dimension || coordinate.getY() > dimension || coordinate.getZ() > dimension
				|| coordinate.getX() <= 0 || coordinate.getY() <= 0 || coordinate.getX() <= 0) 
				? AppConfig.OUT_RANGE_COORDINATE_MESSAGE : "";
		
		/**
		 * - Prepare and return initial cube data validation response
		 */
		SimpleResponse response = new SimpleResponse();
		boolean success = (message.equals("")) ? true : false;
		response.setSuccess(success);
		response.setMessage(message);
		LOGGER.info("Validate coordinates finished");
		return response;
	}
	
	/**
	 * <h2>Get user context - getContext</h2> Method that validate cube coordinate.
	 * 
	 * 
	 * @param String token: User context information
	 * @return OBContext: user whole cube context
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	private OBContext getContext(String token){
		LOGGER.info("Starting to retrieve cube context information");
		/**
		 * - Retrieve from persistence helper the current cube summation context.
		 */
		try {
			String content = PersistHelper.getContent(token);
			OBContext context = new OBContext();
			context = TransformationHelper.getObjectOfJson(content);
			LOGGER.info("Get context finished");
			return context;
		} catch (IOException e) {
			throw new ExceptionHandler(AppConfig.GET_CONTENT_EXCEPTION_MESSAGE);
		}
	}
	
	/**
	 * <h2>Delete current user context - deleteCubeRequest</h2> Method that delete stored data from
	 * user's current cube.
	 * 
	 * 
	 * @param String token: User context information
	 * @return SimpleResponse: Validation result Response
	 * @since 2018-01-21
	 * @author jmunoz
	 */
	public SimpleResponse deleteCubeRequest(String token){
		LOGGER.info("Starting to delete cube");
		/**
		 * - Call persistence helper to delete the current cube summation context.
		 */
		try {
			SimpleResponse response = new SimpleResponse();
			PersistHelper.deleteContent(token);
			response.setSuccess(true);
			response.setMessage(AppConfig.DELETE_SUCCESS_MESSAGE);
			LOGGER.info("Delete cube finished");
			return response;
		} catch (IOException e) {
			throw new ExceptionHandler(AppConfig.DELETE_EXCEPTION_MESSAGE);
		}
	}
}
