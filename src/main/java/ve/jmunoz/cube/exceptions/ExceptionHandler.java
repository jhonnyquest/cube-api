package ve.jmunoz.cube.exceptions;

/**
 * <h1>Class ExceptionHandler</h1><br>
 * The exception handler add a custom management to any exception generated by API application<br>
 * 
 * @author jmunoz
 * @since 2018-01-21
 * @version 0.0.1
 */
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
