package entity.trait;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe de persistance de Description associée à Trait
 * @author Jonathan Fuentes
 *
 */
@Embeddable
public class Description {

	// Attributs de classe
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "descr_id", length = 5)
	private int 	id;
	
	@Column(name = "descr_cont", length = 250)
	private String 	contenu;

	
	/**
	 * Constructeur
	 * @param id
	 * @param contenu
	 */
	public Description(int id, String contenu) {
		this.id 		= id;
		this.contenu 	= contenu;
	}

	/**
	 * Retourne l'Id de Description
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Modifie l'Id de Description
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
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
		return "Description [id=" + id + ", contenu=" + contenu + "]";
	}
	
	
}
