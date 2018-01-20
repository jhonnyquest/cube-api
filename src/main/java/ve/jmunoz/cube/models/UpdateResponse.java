package ve.jmunoz.cube.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateResponse {
	
	private boolean success;
	private String message;
	private int remainingCommands;
	private int remainingTestCases;
	
	@JsonProperty("success")
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
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
	
	@JsonProperty("remaining_test_cases")
	public int getRemainingTestCases() {
		return remainingTestCases;
	}
	
	public void setRemainingTestCases(int remainingTestCases) {
		this.remainingTestCases = remainingTestCases;
	}

	@Override
	public String toString() {
		return "UpdateResponse [success=" + success + ", message=" + message + ", remainingCommands="
				+ remainingCommands + ", remainingTestCases=" + remainingTestCases + "]";
	}
}
