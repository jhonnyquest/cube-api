package ve.jmunoz.cube.services;

import org.springframework.stereotype.Service;

import ve.jmunoz.cube.models.OBCoordinate;
import ve.jmunoz.cube.models.OBCubeRequest;
import ve.jmunoz.cube.models.QueryResponse;
import ve.jmunoz.cube.models.OBqueryRequest;
import ve.jmunoz.cube.models.SimpleResponse;
import ve.jmunoz.cube.models.UpdateResponse;

@Service
public interface CubeManager {

	public SimpleResponse test(String message);

	public SimpleResponse createCubeRequest(OBCubeRequest newCube, String token);

	public UpdateResponse updateCubeRequest(OBCoordinate coordinate, String token);

	public QueryResponse queryCubeRequest(OBqueryRequest queryRequest, String token);

	public SimpleResponse deleteCubeRequest(String token);

	public SimpleResponse setTestCasesRequest(int testCases, String token);
}
