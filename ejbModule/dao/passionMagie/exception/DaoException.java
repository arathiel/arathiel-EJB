package dao.passionMagie.exception;

/**
 * Exception utilisateur
 * Utilise dans DaoDocuments
 *
 */
public class DaoException extends Exception  {

	private static final long serialVersionUID = 1L;
	
	// la classe mere possède le message
	// code d'erreur
	private int code;

	public DaoException(int code) {
		super();
		this.code = code;
	}

	public DaoException(String message, int code) {
		super(message);
		this.code = code;
	}
	
	public DaoException(DaoErrorMessage daoErrorMessage) {
		super(daoErrorMessage.getMessage());
		this.code = daoErrorMessage.getCode();
	}

	public DaoException(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}

	public DaoException(String message, Throwable cause, int code) {
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
