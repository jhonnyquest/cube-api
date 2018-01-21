package ve.jmunoz.cube.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ve.jmunoz.cube.models.OBCoordinate;
import ve.jmunoz.cube.models.CubeRequest;
import ve.jmunoz.cube.models.QueryRequest;
import ve.jmunoz.cube.services.CubeManager;
import ve.jmunoz.cube.utils.TransformationHelper;

/**
 * <h1>Class ProxyEndpointController</h1><br>
 * The proxy endpoint controller expose all endpoits implemented by the cube summation API 
 * by using standard Restful request mapping. Note that there is a base path that have API 
 * descriptor and API version, The following are the resources/verbs exposed in the cube 
 * summation API:<br>
 * <ul>
 * <li>/cube (POST)</li>
 * <li>/cube (DELETE)</li>
 * <li>/cube/update (POST)</li>
 * <li>/cube/test_case (POST)</li>
 * <li>/cube/query (POST)</li>
 * </ul>
 * <br>
 * Note that is mandatory to give a valid user token to each header request in order to 
 * manage the user context persistence.
 * 
 * @author jmunoz
 * @since 2018-01-18
 * @version 0.0.1
 */

@RestController
@RequestMapping("/api/0_0_1")
public class ProxyEndpointController {
	
	@Autowired
	private CubeManager cubeManager;
	
	/**
	 * <h2>Test app dispatcher - getTest</h2> Method that call to cube manager to test dispatcher
	 * 
	 * @param String message: Test message
	 * @return String SimpleResponse: Simple result response
	 * @since 2018-01-18
	 * @author jmunoz
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
	public String getTest(@RequestParam String message) {
		return TransformationHelper.getJsonOfObject(cubeManager.test(message));
	}
	
	/**
	 * <h2>Create new cube - createCube</h2> Method that call to cube manager to create new cube
	 * 
	 * @param OBCube newCube: New cube object
	 * @param String token: User token identifier
	 * @return String SimpleResponse: Simple result response
	 * @since 2018-01-18
	 * @author jmunoz
	 */
	@RequestMapping(value = "/cube", method = RequestMethod.POST, produces = "application/json")
	public String createCube(
			@RequestBody CubeRequest newCube, 
			@RequestHeader(required = true, value="user_token") String token){
		return TransformationHelper.getJsonOfObject(cubeManager.createCubeRequest(newCube, token));
	}
	
	/**
	 * <h2>Delete cube - deleteCube</h2> Method that call to cube manager to delete a cube
	 * 
	 * @param String token: User token identifier 
	 * @return String SimpleResponse: Simple result response 
	 * @since 2018-01-18
	 * @author jmunoz
	 */
	@RequestMapping(value = "/cube", method = RequestMethod.DELETE, produces = "application/json")
	public String deleteCube(@RequestHeader(required = true, value="user_token") String token){
		return TransformationHelper.getJsonOfObject(cubeManager.deleteCubeRequest(token));
	}
	
	/**
	 * <h2>Update cube - updateCube</h2> Method that call to cube manager to update cube
	 * data given coordinates and value
	 * 
	 * @param OBCoordinate coordinate: Cube's Coordinates/Value object 
	 * @param String token: User token identifier
	 * @return String SimpleResponse: Simple result response 
	 * @since 2018-01-18
	 * @author jmunoz
	 */
	@RequestMapping(value = "/cube/update", method = RequestMethod.POST, produces = "application/json")
	public String updateCube(
			@RequestBody OBCoordinate coordinate, 
			@RequestHeader(required = true, value="user_token") String token){
		return TransformationHelper.getJsonOfObject(cubeManager.updateCubeRequest(coordinate, token));
	}
	
	/**
	 * <h2>Set test cases - setTestCases</h2> Method that call to cube manager to set cube
	 * test cases.
	 * 
	 * @param ModelMap message: message that contains test cases quantity
	 * @param String token: User token identifier 
	 * @return String SimpleResponse: Simple result response
	 * @since 2018-01-18
	 * @author jmunoz
	 */
	@RequestMapping(value = "/cube/test_cases", method = RequestMethod.POST, produces = "application/json")
	public String setTestCases(
			@RequestBody ModelMap message, 
			@RequestHeader(required = true, value="user_token") String token){
		return TransformationHelper.getJsonOfObject(
				cubeManager.setTestCasesRequest(Integer.parseInt(message.get("test_cases").toString()), token));
	}
	
	/**
	 * <h2>Query cube - queryCube</h2> Method that call to cube manager to query cube
	 * summation data given a set of paired coordinates
	 * 
	 * @param QueryRequest queryRequest: Cube's Coordinates object
	 * @param String token: User token identifier 
	 * @return String SimpleResponse: Simple result response
	 * @since 2018-01-18
	 * @author jmunoz
	 */
	@RequestMapping(value = "/cube/query", method = RequestMethod.POST, produces = "application/json")
	public String queryCube(
			@RequestBody QueryRequest queryRequest, 
			@RequestHeader(required = true, value="user_token") String token){
		return TransformationHelper.getJsonOfObject(cubeManager.queryCubeRequest(queryRequest, token));
	}
}