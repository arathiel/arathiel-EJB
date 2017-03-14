package entity.trait.comportement;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import entity.caracteristique.Caracteristique;

/**
 * Classe permettant la gestion de comportement influençant les caractéristiques
 * @author Jonathan Fuentes
 *
 */
@Entity
@AttributeOverride(name="id", 	 column=@Column(name="compCar_id"	))
@Table(name = "comp_car")
public class CompCaracteristique extends Technique implements Serializable{

	// Attribut de classe
	/**
	 * 
	 */
	@Version
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "carac_FK", nullable = true)
	private Caracteristique caracteristique;
		
	/**
	 * Constructeur avec Id pour manipulation en sorti de BDD
	 * @param id
	 * @param effet
	 */
	public CompCaracteristique(int id, String effet) {
		super(id, effet);
	}
	
	/**
	 * Constructeur sans ID pour premier ajout dans la BDD (Id auto généré par Hibernate)
	 * @param effet
	 */
	public CompCaracteristique(String effet) {
		super(effet);
	}
	
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

	/**
	 * Affiche CompCaracterique
	 */
	@Override
	public String toString() {
		return "CompCaracteristique [caracteristique=" + caracteristique + "]";
	}
		
}// Fin classe


