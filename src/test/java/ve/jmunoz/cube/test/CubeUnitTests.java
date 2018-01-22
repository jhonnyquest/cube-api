package ve.jmunoz.cube.test;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ve.jmunoz.cube.models.CubeRequest;
import ve.jmunoz.cube.models.OBCoordinate;
import ve.jmunoz.cube.models.QueryRequest;
import ve.jmunoz.cube.models.QueryResponse;
import ve.jmunoz.cube.models.SimpleResponse;
import ve.jmunoz.cube.models.UpdateResponse;
import ve.jmunoz.cube.service.impl.CubeManagerImpl;
import ve.jmunoz.cube.utils.AppConfig;
 
/**
 * <h1>Class CubeUnitTests</h1><br>
 * Unitary test module from cube summation API application. 
 * 
 * @author jmunoz
 * @since 2018-01-21
 * @version 0.0.1
 */
public class CubeUnitTests {
    private static CubeManagerImpl cubeManagerImpl;
 
    @BeforeClass
    public static void initCalculator() {
    	cubeManagerImpl = new CubeManagerImpl();
    }
 
    @Before
    public void beforeEachTest() {
        System.out.println("Init new cube summation unit test cases");
    }
 
    @After
    public void afterEachTest() {
        System.out.println("Finished cube summation unit test cases");
    }
 
    /**
	 * Test method for
	 * {@link ve.jmunoz.cube.services.impl.CubeManagerImpl.testCasesRequest}.
	 */
    @Test
    public void testSetTestCasesRequest() {
    	SimpleResponse result = cubeManagerImpl.setTestCasesRequest(
    			AppConfig.TEST_TEST_CASES, 
    			AppConfig.TEST_USER_CONTEXT);
        assertEquals(true, result.isSuccess());
        assertEquals(AppConfig.WRITE_CONTENT_SUCCESS_MESSAGE, result.getMessage());
    }
    
    /**
	 * Test method for
	 * {@link ve.jmunoz.cube.services.impl.CubeManagerImpl.CreateCubeRequest}.
	 */
    @Test
    public void testCreateCubeRequest() {
    	CubeRequest testCube = new CubeRequest();
    	testCube.setCommands(AppConfig.TEST_COMMANDS);
    	testCube.setDimension(AppConfig.TEST_DIMENSION);
    	testCube.setTestCases(AppConfig.TEST_TEST_CASES);
    	SimpleResponse result = cubeManagerImpl.createCubeRequest(testCube, AppConfig.TEST_USER_CONTEXT);
        assertEquals(true, result.isSuccess());
        assertEquals(AppConfig.WRITE_CONTENT_SUCCESS_MESSAGE, result.getMessage());
    }
    
    /**
	 * Test method for
	 * {@link ve.jmunoz.cube.services.impl.CubeManagerImpl.updateCubeRequest}.
	 */
    @Test
    public void testUpdateCubeRequest() {
    	OBCoordinate testCoordinate = new OBCoordinate();
    	testCoordinate.setX(AppConfig.TEST_COORDINATE_X);
    	testCoordinate.setY(AppConfig.TEST_COORDINATE_Y);
    	testCoordinate.setZ(AppConfig.TEST_COORDINATE_Z);
    	testCoordinate.setValue(AppConfig.TEST_VALUE);
    	UpdateResponse result = cubeManagerImpl.updateCubeRequest(testCoordinate, AppConfig.TEST_USER_CONTEXT);
        assertEquals(true, result.isSuccess());
        assertEquals(AppConfig.WRITE_CONTENT_SUCCESS_MESSAGE, result.getMessage());
        assertEquals(AppConfig.TEST_COMMANDS - 1, result.getRemainingCommands());
        assertEquals(AppConfig.TEST_TEST_CASES, result.getRemainingTestCases());
    }
    
    /**
	 * Test method for
	 * {@link ve.jmunoz.cube.services.impl.CubeManagerImpl.queryCubeRequest}.
	 */
    @Test
    public void testQueryCubeRequest() {
    	QueryRequest testQuery = new QueryRequest();
    	testQuery.setX1(AppConfig.TEST_DIMENSION - 1);
    	testQuery.setX2(AppConfig.TEST_DIMENSION);
    	testQuery.setY1(AppConfig.TEST_DIMENSION - 1);
    	testQuery.setY2(AppConfig.TEST_DIMENSION);
    	testQuery.setZ1(AppConfig.TEST_DIMENSION - 1);
    	testQuery.setZ2(AppConfig.TEST_DIMENSION);
    	
    	QueryResponse result = cubeManagerImpl.queryCubeRequest(testQuery, AppConfig.TEST_USER_CONTEXT);
        
    	assertEquals(true, result.isSuccess());
        assertEquals(AppConfig.QUERY_SUCCESS_MESSAGE, result.getMessage());
        assertEquals(AppConfig.TEST_COMMANDS - 2, result.getRemainingCommands());
        assertEquals(AppConfig.TEST_TEST_CASES, result.getRemainingTestCases());
    }
}