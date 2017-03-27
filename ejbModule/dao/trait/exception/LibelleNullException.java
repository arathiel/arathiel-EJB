package dao.trait.exception;

import dao.trait.ressources.Erreur;

public class LibelleNullException extends DaoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LibelleNullException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LibelleNullException(Erreur type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public LibelleNullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public LibelleNullException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public LibelleNullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public LibelleNullException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
