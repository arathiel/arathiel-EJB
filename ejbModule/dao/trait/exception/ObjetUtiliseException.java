package dao.trait.exception;

import dao.trait.ressources.Erreur;

public class ObjetUtiliseException extends DaoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjetUtiliseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObjetUtiliseException(Erreur type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public ObjetUtiliseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ObjetUtiliseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ObjetUtiliseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ObjetUtiliseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}


}
