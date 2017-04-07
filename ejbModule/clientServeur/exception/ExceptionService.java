package clientServeur.exception;


/**
 * Service Exception pour les utilisateurs et la couche Service
 * @author Anaïs
 *
 */
public class ExceptionService extends Exception {
	
	private static final long serialVersionUID = 1L;
	private int code;


	public ExceptionService() {
		super();
	}
	
	public ExceptionService(int code) {
		super();
		this.code = code;
	}

	public ExceptionService(String message, int code) {
		super(message);
		this.code = code;
	}

	public ExceptionService(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}

	public ExceptionService(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return super.getMessage();
	}
}
