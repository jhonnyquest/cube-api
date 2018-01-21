package ve.jmunoz.cube.services;

import org.springframework.stereotype.Service;

import ve.jmunoz.cube.models.OBCoordinate;
import ve.jmunoz.cube.models.CubeRequest;
import ve.jmunoz.cube.models.QueryResponse;
import ve.jmunoz.cube.models.QueryRequest;
import ve.jmunoz.cube.models.SimpleResponse;
import ve.jmunoz.cube.models.UpdateResponse;

/**
 * <h1>Interface CubeManager</h1><br>
 * Interface from cube manager:<br>
 * 
 * @author jmunoz
 * @since 2018-01-21
 * @version 0.0.1
 */
@Service
public interface CubeManager {

	public SimpleResponse test(String message);

	public SimpleResponse createCubeRequest(CubeRequest newCube, String token);

	public UpdateResponse updateCubeRequest(OBCoordinate coordinate, String token);

	public QueryResponse queryCubeRequest(QueryRequest queryRequest, String token);

	public SimpleResponse deleteCubeRequest(String token);

	public SimpleResponse setTestCasesRequest(int testCases, String token);
}
