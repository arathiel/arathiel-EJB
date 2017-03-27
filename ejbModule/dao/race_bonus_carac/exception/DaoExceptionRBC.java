package dao.race_bonus_carac.exception;

/**
 * Classe de d�finition des Exceptions lev�es dans la couche DAO
 * du package race_bonus_carac
 * 
 * 
 * @author Fran�ois Georgel
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
