package entity.carriere.matiere;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


import javax.persistence.*;

import entity.carriere.CategorieCarriere;
import dao.util.carriere.UtilBdD;

/**
 * Classe de la fonctionnalite Matiere
 * @author Ismael
 * 
 */
@Entity
@Table(name=UtilBdD.ENTITY_MATIERE)
public class Matiere implements Serializable
{
	@Version
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="refMatiere")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_id_matiere")
	@SequenceGenerator(name="seq_id_matiere", sequenceName="ID_MATIERE_SEQ", initialValue=1, allocationSize=50)
	private int id;

	@Column(name="nomMatiere", length=150, nullable=false, unique=true)
	private String nomMatiere;

	@Column(name="origine", length=150, nullable=true)
	private String origine;

	/**
	 * RG:
	 * La matiere n'etant pas le propriétaire de la relation categorie - matiere
	 * si on supprime une categorie de la collection, alors on doit gérer la suppresion de l'association
	 * 
	 */
	@ManyToMany(mappedBy= "matieres", fetch=FetchType.LAZY)
	private Collection<CategorieCarriere> categoriesCarrieres = new ArrayList<CategorieCarriere>();

	/**
	 * Methode permettant de supprimer l'association
	 * 
	 */
	@PreRemove
	private void removeMatiereToCategorie()
	{
		if (categoriesCarrieres != null)							//Verification de l'existance de la categorie
		{
			for(CategorieCarriere categorie : categoriesCarrieres)	//Iteration dans la liste de categorie
			{
				categorie.getMatieres().remove(this);				//Suppression de la matiere dans la categorie
			}
		}
	}

	/**
	 * Methode permettant de persister une matiere
	 * et donc creer l'association
	 */
	@PrePersist
	private void addMatiereInCategorie()
	{
		if (categoriesCarrieres != null)								//Verification de l'existance de la categorie
		{
			for(CategorieCarriere categorie : categoriesCarrieres)		//Iteration dans la categorie
			{
				categorie.addMatiere(this);								//Ajout de la matiere dans la categorie et donc creation de l'association
			}
		}
	}

	/**
	 * Constructeur par defaut
	 */
	public Matiere()
	{
		super();
		categoriesCarrieres = new ArrayList<CategorieCarriere>();
	}

	/**
	 * Constructeur avec parametres sans id car auto genere
	 * @param nomMatiere
	 * @param origine
	 */
	public Matiere(String nomMatiere, String origine) 
	{
		super();
		this.nomMatiere = nomMatiere;
		this.origine = origine;
		categoriesCarrieres = new ArrayList<CategorieCarriere>();
	}

	/**
	 * Constructeur avec tous parametres pour la dto
	 * @param id
	 * @param nomMatiere
	 * @param origine
	 */
	public Matiere(int id, String nomMatiere, String origine) 
	{
		super();
		this.id = id;
		this.nomMatiere = nomMatiere;
		this.origine = origine;
		categoriesCarrieres = new ArrayList<CategorieCarriere>();
	}

	//GETTER
	public int getId() 
	{
		return id;
	}
	public String getNomMatiere() 
	{
		return nomMatiere;
	}
	public String getOrigine()
	{
		return origine;
	}
	public Collection<CategorieCarriere> getCategoriesCarrieres() 
	{
		return categoriesCarrieres;
	}

	//SETTER
	public void setId(int id)
	{
		this.id = id;
	}
	public void setNomMatiere(String nomMatiere) 
	{
		this.nomMatiere = nomMatiere;
	}
	public void setOrigine(String origine) 
	{
		this.origine = origine;
	}
	public void setCategoriesCarrieres(Collection<CategorieCarriere> cat) 
	{
		this.categoriesCarrieres = cat;
	}

	/**
	 * Methode d'affichage, on essaie d'eviter un stack overFlow
	 * par un affichage preciser sur la categorie correspondante
	 * a la matiere
	 * @return result
	 */
	@Override
	public String toString() 
	{
		String result = "";
		result += "Matiere [Identifiant : " + getId() + ", Libelle : " 
				+ getNomMatiere() + ", Origine : " + getOrigine() + " ";
		if(categoriesCarrieres != null)
		{
			result += " Categorie [";
			for(CategorieCarriere categorie : categoriesCarrieres)
			{
				result = result + categorie.getIdCategorie() + " " + categorie.getNom() + ",";
			}
		}
		result +="]";
		return result;
	}
	
	/**
	 * Methode permettant l'ajout d'une categorie correspondant a une matiere
	 * @param categorieCarriere
	 */
	public void add(CategorieCarriere categorieCarriere) 
	{
		if(categorieCarriere != null)										//Verification si matiere differente de null, on rentre dans la boucle
		{
			if (categoriesCarrieres == null)								//Si pas de liste de categorie, on cree une nouvelle liste
				categoriesCarrieres = new ArrayList<CategorieCarriere>();	
			if (!categoriesCarrieres.contains(categorieCarriere))			//Si la liste ne contient pas la categorie, on entre dans la boucle
			{
				categoriesCarrieres.add(categorieCarriere);					//On ajoute la categorie saisie a la liste
				categorieCarriere.addMatiere(this);							//On ajout la categorie a la matiere
			}
		}
	}

	/**
	 * Methode de reconstruction de l'objet
	 * par hibernate
	 * @return matiereDto
	 */
	public Matiere getDto() 
	{
		Matiere matiereDto = new Matiere(this.getId(), this.getNomMatiere(), this.getOrigine());	//On reconstruit les attributs de la matiere
		
		if (this.getCategoriesCarrieres() != null)												//Ajout des categories en verifiant 
		{																						//que la categorie n'est pas null
			ArrayList<CategorieCarriere> lstDto = new ArrayList<CategorieCarriere>();			//Creation d'une liste de carriere
			for (CategorieCarriere categorie : this.getCategoriesCarrieres())					//Iteration des carrieres
			{
				CategorieCarriere catDto = categorie.getDtoNoMatiere();
				lstDto.add(catDto);										//Recuperation par la methode dto de categorie des categories 																					//et ajout des categorie reconstruites a la liste 
			}
			matiereDto.setCategoriesCarrieres(lstDto);											//Ajout de la liste a la matiere
		}
		return matiereDto;																		//on recupere la matiere reconstruite
	}
	
	/**
	 * Methode de reconstruction de l'objet
	 * par hibernate sans matiere
	 * @return matiereDto
	 */
	public Matiere getDtoNoCategorie()
	{
		Matiere matiereDto = new Matiere(this.getId(), this.getNomMatiere(), this.getOrigine());	//On reconstruit les attributs de la matiere
		
		matiereDto.setCategoriesCarrieres(new ArrayList<CategorieCarriere>());						//On ajout quand meme une liste vide pour eviter une erreur
		
		return matiereDto;
	}

}
