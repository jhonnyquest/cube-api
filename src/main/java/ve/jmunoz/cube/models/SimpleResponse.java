package ve.jmunoz.cube.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <h1>Class SimpleResponse</h1><br>
 * Support object model to manage all simple responses <br>
 * 
 * @author jmunoz
 * @since 2018-01-21
 * @version 0.0.1
 */
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
		return "SimpleResponse [success=" + success + ", "
				+ "message=" + message + "]";
	}
}
