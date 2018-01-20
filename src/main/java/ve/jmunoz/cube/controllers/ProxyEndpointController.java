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
import ve.jmunoz.cube.models.OBCubeRequest;
import ve.jmunoz.cube.models.OBqueryRequest;
import ve.jmunoz.cube.services.CubeManager;
import ve.jmunoz.cube.utils.TransformationHelper;


@RestController
@RequestMapping("/api")
public class ProxyEndpointController {
	
	@Autowired
	private CubeManager cubeManager;
	
	/**
	 * <h1>Test app dispatcher - getTest</h1> Method that call to cube manager to test dispatcher
	 * 
	 * @return String JsonObject - SimpleResponse 
	 * @since 2018-01-18
	 * @author jmunoz
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
	public String getTest(@RequestParam String message) {
		return TransformationHelper.getJsonOfObject(cubeManager.test(message));
	}
	
	/**
	 * <h1>Create new cube - createCube</h1> Method that call to cube manager to create new cube
	 * 
	 * @param newCube (OBCube): New cube object
	 * @return String JsonObject - SimpleResponse 
	 * @since 2018-01-18
	 * @author jmunoz
	 */
	@RequestMapping(value = "/cube", method = RequestMethod.POST, produces = "application/json")
	public String createCube(@RequestBody OBCubeRequest newCube, @RequestHeader(required = true, value="user_token") String token){
		return TransformationHelper.getJsonOfObject(cubeManager.createCubeRequest(newCube, token));
	}
	
	/**
	 * <h1>Delete cube - deleteCube</h1> Method that call to cube manager to delete a cube
	 * 
	 * @return String JsonObject - SimpleResponse 
	 * @since 2018-01-18
	 * @author jmunoz
	 */
	@RequestMapping(value = "/cube", method = RequestMethod.DELETE, produces = "application/json")
	public String deleteCube(@RequestHeader(required = true, value="user_token") String token){
		return TransformationHelper.getJsonOfObject(cubeManager.deleteCubeRequest(token));
	}
	
	/**
	 * <h1>Update cube - updateCube</h1> Method that call to cube manager to update cube
	 * data given coordinates and value
	 * 
	 * @param coordinate (OBCoordinate): Cube's Coordinates/Value object 
	 * @return String JsonObject - SimpleResponse 
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
	 * <h1>Set test cases - setTestCases</h1> Method that call to cube manager to set cube
	 * test cases.
	 * 
	 * @param coordinate (OBCoordinate): Cube's Coordinates/Value object 
	 * @return String JsonObject - SimpleResponse 
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
	 * <h1>Query cube - queryCube</h1> Method that call to cube manager to query cube
	 * summation data given a set of paired coordinates
	 * 
	 * @param coordinate (OBCoordinate): Cube's Coordinates/Value object 
	 * @return String JsonObject - SimpleResponse 
	 * @since 2018-01-18
	 * @author jmunoz
	 */
	@RequestMapping(value = "/cube/query", method = RequestMethod.POST, produces = "application/json")
	public String queryCube(@RequestBody OBqueryRequest queryRequest, @RequestHeader(required = true, value="user_token") String token){
		return TransformationHelper.getJsonOfObject(cubeManager.queryCubeRequest(queryRequest, token));
	}
}

