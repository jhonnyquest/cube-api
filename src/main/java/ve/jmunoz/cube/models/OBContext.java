package ve.jmunoz.cube.models;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OBContext {
	
	private OBCubeRequest initData;
	private float[][][] cube;
	private int remainingTestCases;
	private int remainingCommands;
	
	public OBContext(){
		super();
	}

	@JsonProperty("init_data")
	public OBCubeRequest getInitData() {
		return initData;
	}

	public void setInitData(OBCubeRequest initData) {
		this.initData = initData;
	}

	@JsonProperty("cube")
	public float[][][] getCube() {
		return cube;
	}

	public void setCube(float[][][] cube) {
		this.cube = cube;
	}

	@JsonProperty("remaining_test_cases")
	public int getRemainingTestCases() {
		return remainingTestCases;
	}

	public void setRemainingTestCases(int remainingTestCases) {
		this.remainingTestCases = remainingTestCases;
	}

	@JsonProperty("current_commands")
	public int getRemainingCommands() {
		return remainingCommands;
	}

	public void setRemainingCommands(int remainingCommands) {
		this.remainingCommands = remainingCommands;
	}

	@Override
	public String toString() {
		return "OBContext [initData=" + initData + ", cube=" + Arrays.toString(cube) + ", remainingTestCases="
				+ remainingTestCases + ", remainingCommands=" + remainingCommands + "]";
	}
}
