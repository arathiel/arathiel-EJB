package entity.trait.comportement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/**
 * Classe de persistance des comportements associés à un ou plusieurs traits
 * @author Jonathan Fuentes
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Comportement implements Serializable{

	// Attribut de classe un simple ID. Les classes filles possèderont un attribut supplémentaire les définissant
	/**
	 * 
	 */
	@Version
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name="seqComp", sequenceName="comp_SEQ")
	@Column(name = "comp_id", length = 5)
	protected int id;
	
	@Column(name = "comp_lib", length = 25)
	protected String libelle;
	
	//Constructeurs
	
	/**
	 * Constructeur par défaut
	 */
	public Comportement() {
	}
	
	/**
	 * Constructeur sans ID pour premier ajout dans la BDD (Id auto généré par Hibernate)
	 * @param libelle
	 * @param effet
	 */
	public Comportement(String libelle) {
		this.libelle	= libelle;
	}
	
	/**
	 * Constructeur avec Id pour manipulation en sorti de BDD
	 * @param id
	 * @param libelle
	 * @param effet
	 */
	public Comportement(int id, String libelle) {
		this.id 		= id;
		this.libelle	= libelle;
	}

	// Getters & Setters
	/**
	 * Retourne l'ID
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retourne le libellé
	 * @return
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Modifie le libellé
	 * @param libelle
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return id + " : " + libelle;
	}

}// Fin classe
