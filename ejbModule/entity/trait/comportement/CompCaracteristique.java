package entity.trait.comportement;

import javax.persistence.Entity;

import entity.caracteristique.Caracteristique;

/**
 * Classe permettant la gestion de comportement influen�ant les caract�ristiques
 * @author Jonathan Fuentes
 *
 */
@Entity
public class CompCaracteristique extends Technique {

	// Attribut de classe
	private Caracteristique caracteristique;


	/**
	 * Constructeur avec Id pour manipulation en sorti de BDD
	 * @param id
	 * @param effet
	 */
	public CompCaracteristique(int id, String effet) {
		super(id, effet);
		// TODO Auto-generated constructor stub
	}
	
	
	// Getters & Setters

	/**
	 * Retourne la caract�ristique associ�e au comportement
	 * @return
	 */
	public Caracteristique getCaracteristique() {
		return caracteristique;
	}

	/**
	 * Modifie la caract�ristique associ�e au comportement
	 * @param caracteristique
	 */
	public void setCaracteristique(Caracteristique caracteristique) {
		this.caracteristique = caracteristique;
	}
	
	
	
	
}// Fin classe


