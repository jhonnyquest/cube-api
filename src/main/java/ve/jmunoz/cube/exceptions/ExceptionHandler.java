package ve.jmunoz.cube.exceptions;

public class ExceptionHandler extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionHandler() {
		super();
	}

	public ExceptionHandler(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExceptionHandler(String message, Throwable cause) {
		super(message, cause);
	}

	public ExceptionHandler(String message) {
		super(message);
	}

	public ExceptionHandler(Throwable cause) {
		super(cause);
	}

}
