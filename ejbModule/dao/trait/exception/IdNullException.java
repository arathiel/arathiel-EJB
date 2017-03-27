package dao.trait.exception;

import dao.trait.ressources.Erreur;

public class IdNullException extends DaoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IdNullException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IdNullException(Erreur type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public IdNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public IdNullException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IdNullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IdNullException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
