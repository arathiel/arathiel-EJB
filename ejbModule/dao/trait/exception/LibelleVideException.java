package dao.trait.exception;

import dao.trait.ressources.Erreur;

public class LibelleVideException extends DaoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LibelleVideException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LibelleVideException(Erreur type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public LibelleVideException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public LibelleVideException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public LibelleVideException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public LibelleVideException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
