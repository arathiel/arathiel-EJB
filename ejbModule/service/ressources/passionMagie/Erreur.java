package service.ressources.passionMagie;

public enum Erreur {

	PASSION_DOUBLON(1), 
	PASSION_RACE_AFFECTEE(2), 
	PASSION_INEXISTANT(3), 
	PASSION_ID_INCORRECT(4),
	PASSION_NOM_OBLIGATOIRE(5),
	PASSION_MAGIE_OBLIGATOIRE(6),
	PASSION_NULL(7),

	RACE_INEXISTANT(30),
	RACE_DOUBLON(31),

	MDPFOND_INEXISTANT(40), 
	MDPFOND_DOUBLON(41), 
	MDPFOND_ID_INCORRECT(42),
	MDPFOND_NOM_OBLIGATOIRE(43),
	MDPFOND_NULL(44),
	
	MDPNORM_INEXISTANT(50), 
	MDPNORM_DOUBLON(51), 
	MDPNORM_MDPFOND_NAFFECTE(52), 
	MDPNORM_ID_INCORRECT(53),
	MDPNORM_NOM_OBLIGATOIRE(54),
	MDPNORM_NULL(55),

	THE_INEXISTANT(60), THE_DOUBLON(61), THE_ID_INVALID(62),

	DIVERS(999);

	private int code;

	private Erreur(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public String message() {
		switch (this) {
		case PASSION_DOUBLON:			return "La passion existe déjà dans la base de données";
		case PASSION_RACE_AFFECTEE:		return "La race est déjà affectée à une passion";
		case PASSION_INEXISTANT:		return "La passion n'existe pas dans la base de données";
		case PASSION_ID_INCORRECT:		return "L'id de la passion est incorrecte (il doit être supérieur à 0)";
		case PASSION_NOM_OBLIGATOIRE:	return "Le nom de la passion doit être renseigné";
		case PASSION_MAGIE_OBLIGATOIRE:	return "Une passion doit être associée à deux mots de pouvoir fondamentaux";
		case PASSION_NULL:				return "La passion est null";
		case RACE_INEXISTANT:			return "La race n'existe pas dans la base de données";
		case RACE_DOUBLON:        		return "La race existe déjà dans la base de données";

		case MDPFOND_INEXISTANT:		return "Le mot de pouvoir fondamental n'existe pas dans la base de données";
		case MDPFOND_DOUBLON:			return "Le mot de pouvoir fondamental existe déjà dans la base de données";
		case MDPFOND_ID_INCORRECT:		return "L'id du mot de pouvoir fondamental est incorrect (il doit être différent de 0)";
		case MDPFOND_NOM_OBLIGATOIRE:	return "Le nom du mot de pouvoir fondamental est obligatoire";
		case MDPFOND_NULL:				return "Le mot de pouvoir à insérer est null";
		
		case MDPNORM_INEXISTANT:		return "Le mot de pouvoir normal n'existe pas dans la base de données";
		case MDPNORM_DOUBLON:			return "Le mot de pouvoir normal existe déjà dans la base de données";
		case MDPNORM_MDPFOND_NAFFECTE:	return "Le mot de pouvoir normal doit être associé à un mot de pouvoir fond";
		case MDPNORM_ID_INCORRECT:		return "L'id du mot de pouvoir normal est incorrect (il doit être différent de 0)";
		case MDPNORM_NOM_OBLIGATOIRE:	return "Le nom du mot de pouvoir normal est obligatoire";
		case MDPNORM_NULL:				return "Le mot de pouvoir à insérer est null";
		
		case DIVERS:        			return "Erreur non referencee";
		
		default:						return "";
		}
	}
}
