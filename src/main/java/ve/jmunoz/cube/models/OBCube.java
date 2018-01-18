package ve.jmunoz.cube.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OBCube {
	private Integer testCases;
	private Integer dimension;
	private Integer commands;

	public OBCube() {
		super();
	}

	@JsonProperty("testCases")
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
		return "OBCube [testCases=" + testCases + ", dimension=" + dimension + ", commands=" + commands + "]";
	}
}