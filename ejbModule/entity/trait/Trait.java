package entity.trait;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import entity.trait.comportement.Comportement;
import technic.trait.Comportements;

/**
 * Classe de persistance de la fonctionnalité Trait
 * @author Jonathan Fuentes
 *
 */
@Entity
public class Trait implements Serializable{
	
	/**
	 * 
	 */
	@Version
	private static final long serialVersionUID = 1L;

	// Attributs de classe
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name="seqTrait", sequenceName="trait_SEQ")
	@Column(name = "tr_id", length = 5)
	private int				id;
	
	@Column(name = "tr_lib", length = 50, nullable = false)
	private	String			libelle;
	
	@Column(name = "tr_pub", nullable = false)
	private boolean 		visiPublic;
	
	@Column(name = "tr_crea", nullable = false)
	private boolean			dispoCrea;
	
	@Column(name = "tr_malus", nullable = false)
	private boolean			malus;
	
	@ManyToMany(cascade= {CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(	name				= "trait_comp",
				joinColumns			= @JoinColumn(name = "id_trait"),
				inverseJoinColumns	= @JoinColumn(name = "id_comp"))			
	private Collection<Comportement> listComp = new Comportements();
	
	@Embedded
	@Column(name = "tr_desc", nullable = true)
	private Description 	description;
	
	/**
	 * Constructeur avec Id pour manipulation en sorti de BDD
	 * @param id
	 * @param libelle
	 * @param visiPublic
	 * @param dispoCrea
	 * @param malus
	 * @param listComp
	 * @param description
	 */
	public Trait(int id, String libelle, boolean visiPublic, boolean dispoCrea, boolean malus,
			Collection<Comportement> listComp, Description description) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.visiPublic = visiPublic;
		this.dispoCrea = dispoCrea;
		this.malus = malus;
		this.listComp = listComp;
		this.description = description;
	}

	/**
	 * Constructeur de persistance complet sans ID (Id auto généré par Hibernate)
	 * @param libelle
	 * @param visiPublic
	 * @param dispoCrea
	 * @param malus
	 * @param listComp
	 * @param description
	 */
	public Trait(String libelle, boolean visiPublic, boolean dispoCrea, boolean malus,
			Collection<Comportement> listComp, Description description) {
		super();
		this.libelle = libelle;
		this.visiPublic = visiPublic;
		this.dispoCrea = dispoCrea;
		this.malus = malus;
		this.listComp = listComp;
		this.description = description;
	}

	/**
	 * Constructeur de persistance sans description (optionnel à la création) et sans ID (Id auto généré par Hibernate)
	 * @param libelle
	 * @param visiPublic
	 * @param dispoCrea
	 * @param malus
	 * @param listComp
	 */
	public Trait(String libelle, boolean visiPublic, boolean dispoCrea, boolean malus,
			Collection<Comportement> listComp) {
		super();
		this.libelle = libelle;
		this.visiPublic = visiPublic;
		this.dispoCrea = dispoCrea;
		this.malus = malus;
		this.listComp = listComp;
	}
	
	
	

}// Fin classe
