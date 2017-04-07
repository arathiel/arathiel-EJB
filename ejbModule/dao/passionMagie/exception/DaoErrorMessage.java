package dao.passionMagie.exception;

public enum DaoErrorMessage {
	
	//TODO modifer erreur pour les passsions et magie
	//TODO faire une énumération pour les services exceptions
	ERR_NULL			( 0,"L'objet est null"),
	ERR_NOM_NULL		(3, "Le nom de l'objet est null"),
	ERR_INEXISTANT		( 1,"Cet objet est inconnu"),	
	ERR_DOUBLON			(2,"L'objet est déjà en base")
	;	
	
	private int 	code;
	private String 	message;
	
	private DaoErrorMessage(int code, String message) {
		this.code    = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return code + "-" + message;
	}
}
