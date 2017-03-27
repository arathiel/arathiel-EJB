package dao.trait.exception;

import dao.trait.ressources.Erreur;

public class ObjetInexistantException extends DaoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjetInexistantException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObjetInexistantException(Erreur type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public ObjetInexistantException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ObjetInexistantException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ObjetInexistantException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ObjetInexistantException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
