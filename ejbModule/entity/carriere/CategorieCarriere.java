package entity.carriere;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import entity.carriere.matiere.Matiere;
import entity.carriere.util.Matieres;
import dao.util.carriere.UtilBdD;

/**
 * Classe de la fonctionnalite Categorie des carrieres
 * @author Ismael
 * 
 */
@Entity
@Table(name=UtilBdD.ENTITY_CATEGORIE_CARRIERE)
public class CategorieCarriere implements Serializable
{
	@Version
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idCAtegorie")
	@GeneratedValue(strategy = GenerationType.AUTO, generator="seq_CategorieC")
	@SequenceGenerator(name = "seq_CategorieC", sequenceName ="CategorieC_SEG", initialValue=1, allocationSize= 5 )
	private int idCategorie;

	@Column(name="nomCategorie", length=50, nullable=false, unique=true)
	private String nom;

	/***
	 * Relation Categorie(Many) vers Matiere(Many)
	 * RG:
	 * la persistance d'une categorie entraine celle de ses matieres utilisees
	 * Si CategorieCarriere supprimer, la matiere ne l'ai pas aussi
	 * La modification d'une matiere se fait par elle meme et non par le biais de
	 * la categorieCarriere
	 * 
	 */
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="Arathiel_MatCat", joinColumns=@JoinColumn(name="idCategorieC"),
	inverseJoinColumns=@JoinColumn(name="idMatiere"))
	private List<Matiere> matieres = new ArrayList<Matiere>();
	
	
	/**
	 * Constructeur par defaut
	 */
	public CategorieCarriere() 
	{
		super();
		matieres = new ArrayList<Matiere>();
	}
	
	/**
	 * Constructeur avec parametres sans id car autogenere
	 * @param nom
	 * @param matiere
	 */
	public CategorieCarriere(String nom) 
	{
		super();
		this.nom = nom;
		matieres = new ArrayList<Matiere>();
	}

	/**
	 * Constructeur avec parametres pour la dto
	 * @param idCategorie
	 * @param nom
	 * @param matiere
	 */
	public CategorieCarriere(int idCategorie, String nom) 
	{
		super();
		this.idCategorie = idCategorie;
		this.nom = nom;
		matieres = new ArrayList<Matiere>();
	}

	//GETTER
	public int getIdCategorie() 
	{
		return idCategorie;
	}
	public String getNom() 
	{
		return nom;
	}
	public List<Matiere> getMatieres() 
	{
		return matieres;
	}

	//SETTER
	public void setMatieres(List<Matiere> matieres) 
	{
		this.matieres = matieres;
	}
	public void setIdCategorie(int idCategorie) 
	{
		this.idCategorie = idCategorie;
	}
	public void setNom(String nom) 
	{
		this.nom = nom;
	}
	
	public void setMatieres(Matieres matieres)
	{
		//TODO A REVOIR
		this.matieres = matieres;
	}

	/**
	 * Methode d'affichage, on essaie d'eviter un stack overFlow
	 * par un affichage preciser sur la matiere correspondante
	 * a la categorie
	 * @return result
	 */
	@Override
	public String toString()
	{
		String result = "";
		result += "CategorieCarriere [getIdCategorie()=" + getIdCategorie() + ", getNom()=" + getNom() + "]";
		if (matieres != null)
		{
			for (Matiere matiere : matieres)
			{
				result = result + matiere.getNomMatiere() + " " + matiere.getOrigine() + ",";
			}
		}
		result += "]";	
		return result;
	}

	/**
	 * Methode permettant l'ajout d'une matiere correspondant a une categorie
	 * @param matiere
	 */
	public void addMatiere(Matiere matiere) 
	{
		if(matiere != null)								//Verification si matiere differente de null, on rentre dans la boucle
		{
			if(matieres == null)						//Si pas de liste de matiere, on cree une nouvelle liste
			matieres = new ArrayList<Matiere>();		
			
			if (!matieres.contains(matiere))		//Si la liste ne contient pas la matiere, on entre dans la boucle
			{
				matieres.add(matiere);					//On ajoute la matiere saisie a la liste
				matiere.add(this);						//On ajout la matiere a la categorie
			}		
		}
	}
	
	

	/**
	 * Methode de reconstruction de l'objet
	 * par hibernate avec matiere
	 * @returnccDto
	 */
	public CategorieCarriere getDto()
	{
		CategorieCarriere ccDto = new CategorieCarriere(this.getIdCategorie(), this.getNom());	//On reconstruit les attributs de la categorie
		
		if (this.getMatieres() != null)							//Ajout des matieres en verifiant que la matiere n'est pas null
		{														
			ArrayList<Matiere> lstDto = new ArrayList<>();		//Creation d'une liste de matiere
			for( Matiere matiere : this.getMatieres())			//Iteration des matieres
			{
				Matiere matiereDto = matiere.getDtoNoCategorie();			//Recuperation par la methode dto de la matiere des matieres
				lstDto.add(matiereDto);							//Ajout des matieres reconstruites a la liste
			}
			ccDto.setMatieres(lstDto);							//Ajout de la liste a la categorie de carriere
		}
		return ccDto;											//on recupere la categorie reconstruite
	}
	
	/**
	 * Methode de reconstruction de l'objet
	 * par hibernate sans matiere
	 * @returnccDto
	 */
	public CategorieCarriere getDtoNoMatiere()
	{
		CategorieCarriere ccDto = new CategorieCarriere(this.getIdCategorie(), this.getNom());		//On reconstruit les attributs de la categorie
		
		ccDto.setMatieres(new ArrayList<Matiere>());			//On ajout quand meme une liste vide pour eviter une erreur
		
		return ccDto;
	}

	public void add(CategorieCarriere catDto) 
	{
		// TODO Auto-generated method stub
		
	}

	

	
}
