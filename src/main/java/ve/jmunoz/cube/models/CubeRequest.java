package ve.jmunoz.cube.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <h1>Class CubeRequest</h1><br>
 * Support object model to manage basic data cube request <br>
 * 
 * @author jmunoz
 * @since 2018-01-21
 * @version 0.0.1
 */
public class CubeRequest {
	private Integer testCases;
	private Integer dimension;
	private Integer commands;

	public CubeRequest() {
		super();
	}

	@JsonProperty("test_cases")
	public Integer getTestCases() {
		return testCases;
	}

	public void setTestCases(Integer testCases) {
		this.testCases = testCases;
	}

	@JsonProperty("dimension")
	public Integer getDimension() {
		return dimension;
	}

	public void setDimension(Integer dimension) {
		this.dimension = dimension;
	}

	@JsonProperty("commands")
	public Integer getCommands() {
		return commands;
	}

	public void setCommands(Integer commands) {
		this.commands = commands;
	}

	@Override
	public String toString() {
		return "OBCube [testCases=" + testCases + ", "
				+ "dimension=" + dimension + ", "
				+ "commands=" + commands + "]";
	}
}