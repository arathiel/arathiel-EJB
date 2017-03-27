package dao.trait.ressources;

/**
 * Catalogue de messages d'erreur de UserException
 * 
 * Pour bien faire il aurait fallut un fichier similaire pour la couche DAO et un pour la couche la Service
 * afin d'envoyer des messages avec les erreurs et faciliter de travail de maintenance.
 * 
 * @author Jonathan Fuentes
 *
 */
public enum Erreur {

	/* ========================================== */ 
	/*  				Type					  */
	/* ========================================== */
	
	TRAIT	("Erreur Trait"),
	COMP	("Erreur Comportement"),
	CAR		("Erreur Caractéristique"),
	
	/* ========================================== */ 
	/*  			Messages TRAIT				  */
	/* ========================================== */
	
	TR_NULL			("Le trait passé à la méthode est null"),
	TR_DOUBLON 		("Trait déjà existant"),
	TR_INEXISTANT	("Trait inexistant"),
	TR_IDNULL		("ID du trait est null"),
	TR_LIBNULL		("Le libellé du trait est null"),
	TR_LIBVIDE		("Le libellé du trait est vide"),

	
	
	/* ========================================== */ 
	/*  		Messages COMPORTEMENT			  */
	/* ========================================== */
	
	COMP_NULL		("Le comportement passé à la méthode est null"),
	COMP_DOUBLON 	("Comportement déjà existant"),
	COMP_INEXISTANT	("Comportement inexistant"),
	COMP_IDNULL		("ID du comportement est null"),
	COMP_LIBNULL	("Le libellé du comportement est null"),
	COMP_LIBVIDE	("Le libellé du comportement est vide"),
	
	
	/* ========================================== */ 
	/*  		Messages CARACTERISTIQUE		  */
	/* ========================================== */
	
	CAR_INEXISTANT	("Comportement inexistant"),
	CAR_IDNULL		("ID du comportement est null"),
	CAR_LIBNULL	("Le libellé du comportement est null"),
	CAR_LIBVIDE	("Le libellé du comportement est vide");
	
	//Attribut
	private String  message;
	
	/**
	 * Constructeur avec un message d'erreur
	 * @param message
	 */
	Erreur(String message) {
		this.message = message;
	}
	


	//Getters & Setters
	/**
	 * Retourne le message
	 * @return
	 */
	public String getMessage() {
		return message;
	}
}
