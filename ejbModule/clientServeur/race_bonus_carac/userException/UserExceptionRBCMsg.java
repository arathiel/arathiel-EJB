package clientServeur.race_bonus_carac.userException;

public enum UserExceptionRBCMsg {
	
	
	
	PB_RECH_RACE ("Un problème inconnu a empéché la recherche de cette race d'aboutir"),
	PB_INSERT_RACE ("L'enregistrement de la race n'a pas pu aboutir pour des raisons inconnues"),
	PB_UPDATE_RACE ("La modification de la race n'a pas pu aboutir pour des raisons inconnues"),
	PB_DELETE_RACE ("La suppression de la race n'a pas pu aboutir pour des raisons inconnues"),
	
	RACE_NO_EXIST ("La race recherchée est inconnue"),
	RACE_NOM_INVALIDE ("Le nom de race contient des caractères non autorisés"),
	
	DOUBLON_NOM_RACE ("Une race avec ce nom existe déjà"),
	DOUBLON_ID_RACE ("Une race avec cet Id existe déjà"),
	
	INSERT_RACE_OK ("La race a été correctement sauvegardée"),
	DELETE_RACE_OK ("La race a été correctement supprimée"),
	UPDATE_RACE_OK ("La race a été correctement mise à jour"),
	
	BONUS_NO_EXISTS ("Le bonus recherché est inconnu"),
	DOUBLON_BONUS ("Ce bonus existe déjà"),
	BONUS_NULL ("Ce bonus est null"),
	ERR_VAL_BONUS ("Ce bonus possède une valeur incorrecte"),
	
	PB_INSERT_BONUS ("Probleme d'insert de bonus"),
	PB_DELETE_BONUS ("Probleme de delete de bonus");
	
	
	private String msg;
	
	private UserExceptionRBCMsg(String msg) {
		this.setMsg(msg);		
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
