package entity.carriere;

import java.io.Serializable;

import javax.persistence.*;

import dao.util.carriere.UtilBdD;

/**
 * Classe de la fonctionnalite Carriere
 * @author Ismael
 * 
 */
@Entity
@Table(name=UtilBdD.ENTITY_CARRIERE)
public class Carriere implements Serializable
{
	@Version
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="refCarriere")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_id_carriere")
	@SequenceGenerator(name="seq_id_carriere", sequenceName="ID_CARRIERE_SEQ", initialValue=1, allocationSize=5)
	private int id;

	@Column(name="nomCarriere", length=150, nullable=false, unique=true)
	private String nom;

	@Column(name="Disponibilite", nullable=false)
	private boolean dispo;

	@Embedded 
	@Column(name="description", nullable=true)
	private DescripCarriere descriptif;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCategorieC", nullable= true)
	private CategorieCarriere categorieCarriere;

	/**
	 * Constructeur par defaut
	 */
	public Carriere() 
	{
		super();
	}

	/**
	 * Constructeur avec parametres
	 * @param id
	 * @param nom
	 * @param dispo
	 */
	public Carriere(int id, String nom, boolean dispo)
	{
		super();
		this.id = id;
		this.nom = nom;
		this.dispo = dispo;
	}
	/**
	 * Constructeur avec parametres
	 * @param nom
	 * @param dispo
	 */
	public Carriere(String nom, boolean dispo)
	{
		super();
		this.nom = nom;
		this.dispo = dispo;
	}


	/**
	 * Constructeur avec parametres pour la dto
	 * @param id
	 * @param nom
	 * @param dispo
	 * @param description
	 * @param categorieCarriere
	 */

	public Carriere(int id, String nom, boolean dispo, DescripCarriere descriptif, CategorieCarriere categorieCarriere) 
	{
		super();
		this.id = id;
		this.nom = nom;
		this.dispo = dispo;
		this.descriptif = descriptif;
		this.categorieCarriere = categorieCarriere;
	}

	/**
	 * Constructeur avec parametres 
	 * @param nom
	 * @param dispo
	 * @param description
	 * @param categorieCarriere
	 */
	public Carriere(String nom, boolean dispo, DescripCarriere descriptif, CategorieCarriere categorieCarriere)
	{
		super();
		this.nom = nom;
		this.dispo = dispo;
		this.descriptif = descriptif;
		this.categorieCarriere = categorieCarriere;
	}

	/**
	 * Constructeur avec des parametres
	 * @param id
	 * @param nom
	 * @param dispo
	 * @param description
	 */
	public Carriere(int id, String nom, boolean dispo, DescripCarriere descriptif) 
	{
		super();
		this.id = id;
		this.nom = nom;
		this.dispo = dispo;
		this.descriptif = descriptif;
	}

	/**
	 * Constructeur avec des parametres
	 * @param id
	 * @param nom
	 * @param dispo
	 * @param description
	 */
	public Carriere(int id, String nom, boolean dispo, CategorieCarriere categorieCarriere) 
	{
		super();
		this.id = id;
		this.nom = nom;
		this.dispo = dispo;
		this.categorieCarriere = categorieCarriere;
	}

	//GETTER
	public int getId() 
	{
		return id;
	}

	public String getNom() 
	{
		return nom;
	}

	public boolean isDispo() 
	{
		return dispo;
	}

	public DescripCarriere getDescription() 
	{
		return descriptif;
	}

	public CategorieCarriere getCategorieCarriere() 
	{
		return categorieCarriere;
	}

	//SETTER
	public void setId(int id) 
	{
		this.id = id;
	}

	public void setNom(String nom) 
	{
		this.nom = nom;
	}

	public void setDispo(boolean dispo) 
	{
		this.dispo = dispo;
	}

	public void setDescription(DescripCarriere descriptif) 
	{
		this.descriptif = descriptif;
	}

	public void setCategorieCarriere(CategorieCarriere categorieCarriere)
	{
		this.categorieCarriere = categorieCarriere;
	}

	/**
	 * 
	 */
	@Override
	public String toString() 
	{
		return "Carriere [id=" + id + ", nom=" + nom + ", dispo=" + dispo + ", description=" + descriptif
				+ ", categorieCarriere=" + categorieCarriere.getNom() + "]";
	}
	
	
	/**
	 * Methode de construction d'une carriere avec quelques parametres
	 * @return carriereDto
	 */
	public Carriere dto() 
	{
		Carriere carriereDto = new Carriere(this.getId(), this.getNom(), this.isDispo(), this.getDescription(),this.getCategorieCarriere());

		return carriereDto;
	}
}
