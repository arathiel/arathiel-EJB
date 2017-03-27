package dao.race_bonus_carac.exception;

/**
 * Classe de définition des Exceptions levées dans la couche DAO
 * du package race_bonus_carac
 * 
 * 
 * @author François Georgel
 *
 */
public class DaoExceptionRBC extends Exception{

	private static final long serialVersionUID = 1L;
	
	public DaoExceptionRBC(String msg) {
		super(msg);	
	}

	public DaoExceptionRBC(DaoExceptionRBCMsg msg){
		super(msg.getMsg());
	}
}
