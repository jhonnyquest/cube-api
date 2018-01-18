package ve.jmunoz.cube.service.impl;

import org.springframework.stereotype.Component;

import ve.jmunoz.cube.models.OBCube;
import ve.jmunoz.cube.models.SimpleResponse;
import ve.jmunoz.cube.services.CubeManager;


@Component
public class CubeManagerImpl implements CubeManager{
	
	public SimpleResponse test(String message) {
		SimpleResponse response = new SimpleResponse();
		response.setSuccess(true);
		response.setMessage("message from API: " + message);
		return response;
	}
	
	public SimpleResponse createCubeRequest(OBCube newCube){
		SimpleResponse response = new SimpleResponse();
		response.setSuccess(true);
		response.setMessage("message from API: " + newCube.toString());
		return response;
	}
}
