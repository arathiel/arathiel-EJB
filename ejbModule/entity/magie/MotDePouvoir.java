package entity.magie;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public abstract class MotDePouvoir implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_MotDePouvoir", length = 5)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seqMotDePouvoir")
	@SequenceGenerator(name = "seqMotDePouvoir", sequenceName = "motDePouvoir_SEQ", initialValue = 1, allocationSize = 1)
	private int id;

	@Column(name = "nom", nullable = false, unique = true)
	private String libelle;

	/**
	 * Constructeur par défaut
	 * 
	 */
	public MotDePouvoir() {
		super();
	}

	/**
	 * Constructeur avec l'ensemble des paramètres
	 * 
	 * @param id
	 * @param libelle
	 */
	public MotDePouvoir(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	/**
	 * Constructeur avec seulement le nom du mot de pouvoir, l'id est géré par
	 * la table
	 * 
	 * @param id
	 * @param libelle
	 */
	public MotDePouvoir(String libelle) {
		super();
		this.libelle = libelle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "MotDePouvoir [id=" + id + ", libelle=" + libelle + "]";
	}

}
