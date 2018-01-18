package ve.jmunoz.cube.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ve.jmunoz.cube.models.OBCube;
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
	public String createCube(@RequestBody OBCube newCube){
		return TransformationHelper.getJsonOfObject(cubeManager.createCubeRequest(newCube));
	}
}

