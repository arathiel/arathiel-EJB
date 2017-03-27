package dao.race_bonus_carac.exception;


/**
 * Enumeration des messages associés aux esceptions de la couche DAO
 * du package race_bonus_carac
 * 
 * 
 * @author Francois Georgel
 *
 */
public enum DaoExceptionRBCMsg {
	
	RACE_NO_EXIST ("race inexistante"),
	
	RACE_NOM_INVALIDE ("Le nom de cette race est invalide"),
	
	PB_UPDATE_RACE ("Probleme de modification de race"),
	PB_INSERT_RACE ("Probleme d'insert de race"),
	PB_DELETE_RACE ("Probleme de delete de race"),
	
	DOUBLON_NOM_RACE ("Ce nom de race existe deja"),
	DOUBLON_ID_RACE ("Cet Id de race est deja utilise"),
	
	INSERT_RACE_OK ("Race correctement inseree"),
	DELETE_RACE_OK ("Race correctement delete"),
	UPDATE_RACE_OK ("Race correctement update"),
	
	
	BONUS_NO_EXISTS ("Bonus inconnu"),
	DOUBLON_BONUS ("Ce bonus existe déjà"),
	ERR_VAL_BONUS ("Erreur de valeur du bonus"),
	BONUS_NULL ("Bonus null"),
	
	PB_INSERT_BONUS ("Probleme d'insert de bonus"),
	PB_DELETE_BONUS ("Probleme de delete de bonus");
	
	
	
	private String msg;

	private DaoExceptionRBCMsg(String msg){
		this.setMsg(msg);
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
