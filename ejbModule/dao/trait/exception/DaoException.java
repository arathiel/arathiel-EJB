package dao.trait.exception;

import dao.trait.ressources.Erreur;

/**
 * Classe Mère de toutes les exceptions liées à la DAO
 * @author Afpa
 *
 */
public abstract class DaoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Attributs de classe
	protected Erreur type;

	public DaoException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DaoException(Erreur type) {
		this.type = type;
	}

	public DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public Erreur getType() {
		return type;
	}

	public void setType(Erreur type) {
		this.type = type;
	}

}
