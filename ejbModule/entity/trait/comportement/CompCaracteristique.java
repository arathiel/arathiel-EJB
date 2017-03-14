package entity.trait.comportement;

import javax.persistence.Entity;

import entity.caracteristique.Caracteristique;

/**
 * Classe permettant la gestion de comportement influençant les caractéristiques
 * @author Jonathan Fuentes
 *
 */
@Entity
public class CompCaracteristique extends Technique {

	// Attribut de classe
	private Caracteristique caracteristique;
	
	
	// Getters & Setters

	/**
	 * Retourne la caractéristique associée au comportement
	 * @return
	 */
	public Caracteristique getCaracteristique() {
		return caracteristique;
	}

	/**
	 * Modifie la caractéristique associée au comportement
	 * @param caracteristique
	 */
	public void setCaracteristique(Caracteristique caracteristique) {
		this.caracteristique = caracteristique;
	}
	
	
	
	
}// Fin classe


