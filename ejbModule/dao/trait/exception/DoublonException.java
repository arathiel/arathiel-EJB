package dao.trait.exception;

import dao.trait.ressources.Erreur;

public class DoublonException extends DaoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DoublonException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoublonException(Erreur type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public DoublonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DoublonException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DoublonException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DoublonException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}


}
