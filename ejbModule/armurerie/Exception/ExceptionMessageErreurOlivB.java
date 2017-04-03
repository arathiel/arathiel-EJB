package armurerie.Exception;

public enum ExceptionMessageErreurOlivB {
	
	DOUBLON_ARME ("L'arme existe déjà"),
	ARME_NULL ("L'arme est vide"),
	ARME_INEXISTANTE ("L'arme recherchée n'existe pas"),
	NO_LISTE_ARME ("Liste d'armes inexistante"),
	NOM_ARME_INEXISTANT ("Le nom de l'arme est obligatoire"),
	NO_LISTE_JOUEUR ("Il n'existe aucun jouuer"),
	NO_JOUEUR ("Le joueur n'existe pas"),
	DOUBLON_ARMEJOUEUR ("Le joueur possède déjà cette arme"),
	ARMEJOUR_NULL ("L'arme est vide"),
	ENCOM_OBLIG ("L'encombrement est obligatoirement supérieur à 0"),
	PRIX_OBLIG ("le prix de l'arme est obligatoirement supérieur à 1"),
	RACE_INEXISTANTE ("Aucune race n'est associée à l'arme"),
	NO_LISTE_RACE ("Liste de race inéxistante"),
	NO_RACE ("La race n'existe pas");
	
	public String message;

	private ExceptionMessageErreurOlivB(String message) {
		this.setMessage(message);
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
