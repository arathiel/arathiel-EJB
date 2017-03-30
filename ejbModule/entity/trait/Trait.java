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
	@GeneratedValue(strategy = GenerationType.AUTO, generator="seq_trait")
	@SequenceGenerator(name="seq_trait", sequenceName="trait_sequence")
	@Column(name = "tr_id", length = 5)
	private int				id;
	
	@Column(name = "tr_lib", length = 25, nullable = false)
	private	String			libelle;
	
	@Column(name = "tr_pub", nullable = false)
	private boolean 		visiPublic;
	
	@Column(name = "tr_crea", nullable = false)
	private boolean			dispoCrea;
	
	@Column(name = "tr_malus", nullable = false)
	private boolean			malus;
	
	// Création de la table de jointure *..*
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER) 	
	@JoinTable(	name				= "trait_comp",
				joinColumns			= @JoinColumn(name = "id_trait"),
				inverseJoinColumns	= @JoinColumn(name = "id_comp"))			
	private Collection<Comportement> listComp = new Comportements();
	
	// Pas util de créer une table pour la description 1..1
	@Embedded 																
	@Column(name = "tr_desc", nullable = true)
	private Description 	description;
	
	// Constructeurs
	
	
	/**
	 * Constructeur par défaut pour fonctionnement de la persistance
	 */
	public Trait () {
	}
	
	/**
	 * Constructeur complet avec Id pour manipulation en sorti de BDD
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
	 * Constructeur complet de persistance complet sans ID (Id auto généré par Hibernate)
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
	 * Constructeur de persistance sans comportements (optionnel à la création) et sans ID (Id auto généré par Hibernate)
	 * @param libelle
	 * @param visiPublic
	 * @param dispoCrea
	 * @param malus
	 * @param description
	 */
	public Trait(String libelle, boolean visiPublic, boolean dispoCrea, boolean malus, Description description) {
		super();
		this.libelle = libelle;
		this.visiPublic = visiPublic;
		this.dispoCrea = dispoCrea;
		this.malus = malus;
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
		this.libelle = libelle;
		this.visiPublic = visiPublic;
		this.dispoCrea = dispoCrea;
		this.malus = malus;
		this.listComp = listComp;
	}
	
	/**
	 * Constructeur de persistance sans description ni comportements (optionnel à la création) et sans ID (Id auto généré par Hibernate)
	 * @param libelle
	 * @param visiPublic
	 * @param dispoCrea
	 * @param malus
	 */
	public Trait(String libelle, boolean visiPublic, boolean dispoCrea, boolean malus) {
		super();
		this.libelle = libelle;
		this.visiPublic = visiPublic;
		this.dispoCrea = dispoCrea;
		this.malus = malus;
	}
	
	/**
	 * Permet d'enrichir la liste de comportement du trait
	 * @param comportement
	 */
	public void addComp(Comportement comportement) {
		this.listComp.add(comportement);
	}
	
	public Trait getDto () {
		Trait traitDto = new Trait(this.id, this.libelle, this.visiPublic, this.dispoCrea, this.malus, this.listComp, this.description);

		// on ajoute les comportements du persistantBag dans le nouveau docDto
		if (this.getListComp() != null) {
			Comportements listeDto = new Comportements();
			for (Comportement c : this.getListComp()) {
				listeDto.add(c);
			}
			traitDto.setListComp(listeDto);
		}
		return traitDto;
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

	/**
	 * Retourne booléen informant si le trait à une visibilité public (par tous)
	 * @return
	 */
	public boolean isVisiPublic() {
		return visiPublic;
	}

	/**
	 * Modifie la visibilité du trait
	 * @param visiPublic
	 */
	public void setVisiPublic(boolean visiPublic) {
		this.visiPublic = visiPublic;
	}

	/**
	 * Retourne booléen informant si le trait est disponible à la création
	 * @return
	 */
	public boolean isDispoCrea() {
		return dispoCrea;
	}

	/**
	 * Modifie la disponibilité du trait à la création
	 * @param dispoCrea
	 */
	public void setDispoCrea(boolean dispoCrea) {
		this.dispoCrea = dispoCrea;
	}

	/**
	 * Retourne un booléen informant si le trait est un malus ou bonus
	 * @return
	 */
	public boolean isMalus() {
		return malus;
	}

	/**
	 * Modifie la nature du trait
	 * @param malus
	 */
	public void setMalus(boolean malus) {
		this.malus = malus;
	}

	/**
	 * Retourne la liste des comportements du trait
	 * @return
	 */
	public Collection<Comportement> getListComp() {
		return listComp;
	}

	/**
	 * Modifie la liste de comportement du trait
	 * @param listComp
	 */
	public void setListComp(Collection<Comportement> listComp) {
		this.listComp = listComp;
	}

	/**
	 * Retourne la description du trait
	 * @return
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * Modifie la description du trait
	 * @param description
	 */
	public void setDescription(Description description) {
		this.description = description;
	}

	
	@Override
	public String toString() {
		return "id : " 								+ id 
				+ ", libelle = " 					+ libelle
				+ ", Visibilité publique = " 		+ visiPublic 
				+ ", Disponible à la création = " 	+ dispoCrea
				+ ", Maluse = " + malus 
				+ (listComp    != null ? ", Comportements = " + listComp : "")
				+ (description != null ? ", description   = " + description.getContenu() : "");
	}
	
}// Fin classe
