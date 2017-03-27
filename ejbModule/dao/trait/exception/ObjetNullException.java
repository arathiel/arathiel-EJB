package dao.trait.exception;

import dao.trait.ressources.Erreur;

public class ObjetNullException extends DaoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjetNullException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObjetNullException(Erreur type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public ObjetNullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ObjetNullException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ObjetNullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ObjetNullException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
