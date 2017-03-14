package entity.trait;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Version;

/**
 * Classe de persistance de Description associée à Trait
 * @author Jonathan Fuentes
 *
 */
@Embeddable
public class Description implements Serializable{

	// Attributs de classe
	@Version
	private static final long serialVersionUID = 1L;
	
	@Column(name = "tr_descr", length = 250)
	private String 	contenu;

	
	/**
	 * Constructeur
	 * @param id
	 * @param contenu
	 */
	public Description(String contenu) {
		this.contenu 	= contenu;
	}


	/**
	 * Retourne le contenu de la description
	 * @return
	 */
	public String getContenu() {
		return contenu;
	}

	/**
	 * Modifie le contenu de la Description
	 * @param contenu
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	/**
	 * Affiche Description
	 */
	@Override
	public String toString() {
		return "Description [contenu=" + contenu + "]";
	}
	
}// Fin classe
