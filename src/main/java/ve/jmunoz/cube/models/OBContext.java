package ve.jmunoz.cube.models;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <h1>Class OBContext</h1><br>
 * Object business model to manage user cube context<br>
 * 
 * @author jmunoz
 * @since 2018-01-21
 * @version 0.0.1
 */
public class OBContext {
	
	private CubeRequest initData;
	private double[][][] cube;
	private int remainingTestCases;
	private int remainingCommands;
	
	public OBContext(){
		super();
	}

	@JsonProperty("init_data")
	public CubeRequest getInitData() {
		return initData;
	}

	public void setInitData(CubeRequest initData) {
		this.initData = initData;
	}

	@JsonProperty("cube")
	public double[][][] getCube() {
		return cube;
	}

	public void setCube(double[][][] cube) {
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
