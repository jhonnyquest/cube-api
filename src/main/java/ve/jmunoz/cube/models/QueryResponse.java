package ve.jmunoz.cube.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <h1>Class QueryResponse</h1><br>
 * Support object model to manage cube query response <br>
 * 
 * @author jmunoz
 * @since 2018-01-21
 * @version 0.0.1
 */
public class QueryResponse {
	
	private boolean success;
	private double result;
	private String message;
	private int remainingCommands;
	private int remainingTestCases;
	
	public QueryResponse(){
		super();
	}

	@JsonProperty("success")
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@JsonProperty("result")
	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@JsonProperty("remaining_commands")
	public int getRemainingCommands() {
		return remainingCommands;
	}

	public void setRemainingCommands(int remainingCommands) {
		this.remainingCommands = remainingCommands;
	}

	@JsonProperty("remaining_testCases")
	public int getRemainingTestCases() {
		return remainingTestCases;
	}

	public void setRemainingTestCases(int remainingTestCases) {
		this.remainingTestCases = remainingTestCases;
	}

	@Override
	public String toString() {
		return "QueryResponse [success=" + success + ", result=" + result + ", message=" + message
				+ ", remainingCommands=" + remainingCommands + ", remainingTestCases=" + remainingTestCases + "]";
	}
}
