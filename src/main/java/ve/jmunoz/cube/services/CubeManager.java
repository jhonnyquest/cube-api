package ve.jmunoz.cube.services;

import org.springframework.stereotype.Service;

import ve.jmunoz.cube.models.OBCube;
import ve.jmunoz.cube.models.SimpleResponse;

@Service
public interface CubeManager {

	public SimpleResponse test(String message);

	public SimpleResponse createCubeRequest(OBCube newCube);
}
