package ve.jmunoz.cube.utils;

import org.springframework.context.annotation.Configuration;

/**
 * <h1>Class Config</h1><br>
 * Configuration module config gets all configuration properties of the application <br>
 * TODO: For production deployments sensible data variables must be configured as system environment variables. 
 * 
 * @author jmunoz
 * @since 2018-01-21
 * @version 0.0.1
 */
@Configuration
public class AppConfig {
	/**
	 * Messaging data variables configuration
	 */
	public static final String GET_CONTENT_EXCEPTION_MESSAGE = "There was an exception while trying to get user data. ";
	public static final String WRITE_CONTENT_EXCEPTION_MESSAGE = "There was an exception while trying to store user data. ";
	public static final String QUERY_SUCCESS_MESSAGE = "Cube summation calculation successful. ";
	public static final String WRITE_CONTENT_SUCCESS_MESSAGE = "Data successfully stored. ";
	public static final String DELETE_SUCCESS_MESSAGE = "Data successful deleted. ";
	public static final String DELETE_EXCEPTION_MESSAGE = "There was an exception while trying to delete user context. ";
	public static final String OUT_RANGE_COMMANDS_MESSAGE = "Commands out of range. ";
	public static final String OUT_RANGE_DIMENSION_MESSAGE = "Matrix dimension out of range. ";
	public static final String OUT_RANGE_TEST_CASES_MESSAGE = "Test cases out of range. ";
	public static final String OUT_RANGE_VALUE_MESSAGE = "Value out of range. ";
	public static final String OUT_RANGE_COORDINATE_MESSAGE = "Coordinate out of range";
	public static final String NO_MORE_TEST_CASES_MESSAGE = "There are no more test cases";
	
	/**
	 * Validation data variables configuration
	 */
	public static final double MIN_CUBE_VALUE = Math.pow(-10,9);
	public static final double MAX_CUBE_VALUE = Math.pow(10,9);
	public static final int MIN_COMMANDS_VALUE = 1;
	public static final int MAX_COMMANDS_VALUE = 1000;
	public static final int MIN_TEST_CASES_VALUE = 1;
	public static final int MAX_TEST_CASES_VALUE = 50;
	public static final int MIN_DIMENSION_VALUE = 1;
	public static final int MAX_DIMENSION_VALUE = 100;
	
	/**
	 * Persistence data variables configuration
	 */
	public static final String STORAGE_BASE_PATH = "/tmp/cube/";
	
	/**
	 * Unit test data variables configuration
	 */
	public static final String TEST_USER_CONTEXT = "kbfss675fasdfbsdf786ffbkdf55sdfgbd";
	public static final int TEST_TEST_CASES = 5;
	public static final int TEST_DIMENSION = 3;
	public static final int TEST_COMMANDS = 10;
	public static final int TEST_COORDINATE_X = 1;
	public static final int TEST_COORDINATE_Y = 2;
	public static final int TEST_COORDINATE_Z = 3;
	public static final double TEST_VALUE = 23;
}
