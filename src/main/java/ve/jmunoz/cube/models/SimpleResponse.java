package ve.jmunoz.cube.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleResponse {
	
	private boolean success;
	private String message;
	
	public SimpleResponse() {
		super();
	}

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

	@Override
	public String toString() {
		return "SimpleResponse [success=" + success + ", message=" + message + "]";
	}
}
