package dao.carriere;

import javax.ejb.*;

import dao.carriere.carriere.catalogue.CarriereDaoCatalogue;
import dao.carriere.carriere.gestion.CarriereDaoGestion;
import dao.carriere.categorieCarriere.catalogue.CategorieDaoCatalogue;
import dao.carriere.categorieCarriere.gestion.CategorieDaoGestion;
import dao.carriere.exception.DaoExceptionCarriere;
import dao.carriere.exception.DoublonExceptionCarriere;
import dao.carriere.exception.InexistantExceptionCarriere;
import dao.carriere.matiere.catalogue.MatiereDaoCatalogue;
import dao.carriere.matiere.gestion.MatiereDaoGestion;
import entity.carriere.Carriere;
import entity.carriere.CategorieCarriere;
import entity.carriere.matiere.Matiere;
import entity.carriere.util.Carrieres;
import entity.carriere.util.CategorieCarrieres;
import entity.carriere.util.Matieres;
import service.ressources.carriere.Erreur;



@Singleton
@LocalBean
public class FacadeDaoCarriere 
{
	@EJB
	CarriereDaoGestion carDaoGestion;

	@EJB
	CarriereDaoCatalogue carDaoCatalogue;

	@EJB
	CategorieDaoGestion categorieDaoGestion;

	@EJB
	CategorieDaoCatalogue categorieDaoCatalogue;

	@EJB
	MatiereDaoGestion matiereDaoGestion;

	@EJB
	MatiereDaoCatalogue matiereDaoCatalogue;

	/* ================================================= */
	/* 		Methode pour la gestion des carrieres		 */
	/* ================================================= */

	/**
	 * Methode d'ajout  d'une carriere
	 * @param carriere
	 * @return carriere
	 * @throws DoublonExceptionCarriere 
	 * @throws InexistantExceptionCarriere 
	 */
	public Carriere ajouterCarriere(Carriere carriere) throws DaoExceptionCarriere
	{
		if(carriere != null)
		{
			System.out.println("Entree hibernate : " + carriere);
			carDaoGestion.addCarriere(carriere);
			System.out.println("Dans hibernate : " + carriere);
			carriere = carriere.dto();
			System.out.println("Retour hibernate : " + carriere);
		}
		return carriere;
	}

	/**
	 * Methode de modification
	 * @param carriere
	 * @return carriere
	 * @throws UserException 
	 * @throws InexistantExceptionCarriere 
	 * @throws DoublonExceptionCarriere 
	 */
	public Carriere modifierCarriere(Carriere carriere) throws DaoExceptionCarriere
	{
		if(carriere != null)
		{
			carDaoGestion.updateCarriere(carriere);
			carriere = carriere.dto();
		}
		return carriere;
	}

	/**
	 * Methode de suppression d'une carriere
	 * @param carriere
	 * @throws InexistantExceptionCarriere 
	 */
	public void supprimerCarriere(Carriere carriere) throws DaoExceptionCarriere
	{
		try 
		{
			carDaoGestion.delCarriere(carriere);
		}
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAR_INEXISTANT.action(), Erreur.CAR_INEXISTANT.getCode());
		}
	}

	/**
	 * Methode de supperession d'une carriere par l'id
	 * @param id
	 * @throws InexistantExceptionCarriere 
	 */
	public void supprimerCarriereParId(int id) throws DaoExceptionCarriere
	{
		try 
		{
			carDaoGestion.delCarriereParId(id);
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAR_INEXISTANT.action(), Erreur.CAR_INEXISTANT.getCode());
		}
	}

	/**
	 *  Methode de suppression d'une carriere par nom
	 *  @param nom
	 * @throws InexistantExceptionCarriere 
	 */
	public void supprimerCarriereParParNom(String nom) throws DaoExceptionCarriere
	{
		try 
		{
			carDaoGestion.delCarriereParNom(nom);
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAR_INEXISTANT.action(), Erreur.CAR_INEXISTANT.getCode());
		}
	}


	/**
	 * Methode d'affichage de la liste des carrieres
	 * @return lstCarrieres
	 */
	public Carrieres lstCarrieres() 
	{
		Carrieres  lstCarrieres = new Carrieres();
		for(Carriere car : carDaoCatalogue.lstCarrieres())
		{
			Carriere carriere = car.dto();
			lstCarrieres.add(carriere);
		}

		return lstCarrieres;
	}

	/***
	 * Methode de recherche d'une carriere par nom
	 * @param nom
	 * @return Carriere
	 */
	public Carriere recherCarParNom(String nom) throws DaoExceptionCarriere
	{
		Carriere carriere = null;
		try
		{
			carriere = carDaoCatalogue.getCarriere(nom);
		}
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAR_INEXISTANT.action(), Erreur.CAR_INEXISTANT.getCode());
		}
		
		if(carriere != null) carriere = carriere.dto();

		return carriere;
	}

	/**
	 * Methode de recherche d'une carriere par id
	 * @param idCarriere
	 * @return Carriere
	 */
	public Carriere recherCarParId(int idCarriere) throws DaoExceptionCarriere
	{
		Carriere carriere = null;
		try
		{
			carriere = carDaoCatalogue.getCarriere(idCarriere);
			if(carriere != null) carriere = carriere.dto();
		}
		catch ( InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAR_INEXISTANT.action(), Erreur.CAR_INEXISTANT.getCode());
		}
		return carriere;
	}


	/* ================================================= */
	/* 		Methode pour la gestion des categories		 */
	/* ================================================= */	

	/**
	 * Methode d'ajout d'une categorie
	 * @param catCarriere
	 * @return cc
	 * @throws InexistantExceptionCarriere 
	 * @throws DoublonExceptionCarriere 
	 */
	public CategorieCarriere ajouterCategorieCarriere(CategorieCarriere catCarriere) throws DaoExceptionCarriere
	{
		if(catCarriere != null)
		{		
			System.out.println("Entree hibernate : " + catCarriere);
			try 
			{
				categorieDaoGestion.addCategorie(catCarriere);
			} 
			catch (InexistantExceptionCarriere e) 
			{
				throw new DaoExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());
			}
			catch (DoublonExceptionCarriere e) 
			{
				throw new DaoExceptionCarriere(Erreur.CAT_DOUBLON.action(), Erreur.CAT_DOUBLON.getCode());
			}
			System.out.println("Dans hibernate : " + catCarriere);
			catCarriere = catCarriere.getDtoNoMatiere();
			System.out.println("Retour hibernate : " + catCarriere);
		}
		return catCarriere;

	}

	/**
	 *  Methode de modification d'une categorie
	 *  @param catCarriere
	 *  @return cc
	 * @throws InexistantExceptionCarriere 
	 * @throws DoublonExceptionCarriere 
	 */
	public CategorieCarriere modifierCategorieCarriere(CategorieCarriere catCarriere) throws DaoExceptionCarriere
	{
		if(catCarriere != null)
		{
			try 
			{
				categorieDaoGestion.updateCategorie(catCarriere);
				catCarriere = catCarriere.getDtoNoMatiere();
			} 
			catch (DoublonExceptionCarriere e) 
			{
				throw new DaoExceptionCarriere(Erreur.CAT_DOUBLON.action(), Erreur.CAT_DOUBLON.getCode());
			} 
			catch (InexistantExceptionCarriere e)
			{
				throw new DaoExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());
			}

		}
		return catCarriere;
	}

	/**
	 *  Methode de suppression d'une categorie
	 *  @param catCarriere
	 * @throws InexistantExceptionCarriere 
	 */
	public void supprimerCategorieCarriere(CategorieCarriere catCarriere) throws DaoExceptionCarriere
	{
		try 
		{
			categorieDaoGestion.delCategorie(catCarriere);
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());
		}
	}

	/**
	 *  Methode de suppression d'une categorie par nom
	 *  @param nom
	 * @throws InexistantExceptionCarriere 
	 */
	public void supprimerCategorieCarriereParNom(String nom) throws DaoExceptionCarriere
	{
		try 
		{
			categorieDaoGestion.delCategorieParNom(nom);
		}
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());
		}
	}

	/**
	 *  Methode de recuperation d'une liste de categorie
	 *  @return lstCategories
	 */
	public CategorieCarrieres lstCategorieCarrieres() 
	{
		CategorieCarrieres lstCategories = new CategorieCarrieres();
		for(CategorieCarriere catCarriere : categorieDaoCatalogue.lstCategories())
		{
			CategorieCarriere categorie = catCarriere.getDto();
			lstCategories.add(categorie);
		}
		return lstCategories;
	}

	/**
	 * Methode de recherche par Id d'une categorie
	 * @param idCategorieCarriere
	 * @return CategorieCarriere
	 */
	public CategorieCarriere recherchCategorieParId(int idCategorieCarriere) throws DaoExceptionCarriere
	{
		CategorieCarriere catCarriere = null ;
		try
		{
			catCarriere =  categorieDaoCatalogue.getCategorie(idCategorieCarriere);
			if(catCarriere != null) catCarriere = catCarriere.getDto();
		}
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());
		}
		

		return catCarriere;
	}

	/**
	 * Methode de recherche par Nom d'une categorie
	 * @param nom
	 * @return CategorieCarriere
	 */
	public CategorieCarriere recherchCategorieParNom(String nom) throws DaoExceptionCarriere
	{
		CategorieCarriere catCarriere = null;
		try
		{
			catCarriere = categorieDaoCatalogue.getCategorieParNom(nom);
		}
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());
		}
		
		
		if(catCarriere != null) catCarriere = catCarriere.getDto();

		return catCarriere;
	}


	/* ================================================= */
	/* 		Methode pour la gestion des matieres		 */
	/* ================================================= */

	/**
	 * Methode d'ajout d'une matiere
	 * @param matiere
	 * @return matiere
	 */
	public Matiere ajouterMatiere(Matiere matiere) throws DaoExceptionCarriere
	{
		if(matiere != null)
		{
			try
			{
				matiereDaoGestion.addMatiere(matiere);
				matiere = matiere.getDto();	
			}
			catch (DoublonExceptionCarriere e) 
			{
				throw new DaoExceptionCarriere(Erreur.MAT_DOUBLON.action(), Erreur.MAT_DOUBLON.getCode());
			} 
			catch (InexistantExceptionCarriere e)
			{
				throw new DaoExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());
			}	
		}
		return matiere;
	}

	/**
	 * Methode de modification d'une matiere 
	 * @param matiere
	 * @return matiere
	 */
	public Matiere modifierMatiere(Matiere matiere) throws DaoExceptionCarriere
	{
		if(matiere != null)
		{
			matiereDaoGestion.updateMatiere(matiere);
			matiere = matiere.getDto();
		}
		return matiere;
	}

	/**
	 * Methode de suppression d'une matiere 
	 * @param matiere
	 */
	public void supprimerMatiere(Matiere matiere) throws DaoExceptionCarriere
	{
		try 
		{
			matiereDaoGestion.delMatiere(matiere);
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());
		}
	}

	/**
	 * Methode de suppression d'une matiere par le nom
	 * @param nomMatiere
	 */
	public void supprimerMatiereParNom(String nomMatiere) throws DaoExceptionCarriere
	{
		try 
		{
			matiereDaoGestion.delMatiereParNom(nomMatiere);
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());
		}	
	}

	/***
	 * Methode de recuperation d'une liste de matiere
	 * @return lstMatieres
	 */
	public Matieres lstMatieres()
	{
		Matieres lstMatieres = new Matieres();
		for(Matiere mat : matiereDaoCatalogue.lstMatieres())
		{
			Matiere matiere = mat.getDto();
			lstMatieres.add(matiere);
		}
		return lstMatieres;
	}

	/**
	 * Methode de recherche par Nom d'une Matiere
	 * @return Matiere
	 * @throws InexistantExceptionCarriere 
	 */
	public Matiere recherchMatiereParNom(String nomMatiere) throws DaoExceptionCarriere
	{
		Matiere matiere = null;
		try
		{
			matiere = matiereDaoCatalogue.getMatiereParNom(nomMatiere);
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());
		}
		if(matiere != null) matiere = matiere.getDto();

		return matiere;
	}

	/**
	 * Methode de recherche par Id d'une Matiere
	 * @return Matiere
	 * @throws InexistantExceptionCarriere 
	 */
	public Matiere recherchMatierParID(int id) throws DaoExceptionCarriere
	{
		Matiere matiere;
		try {
			matiere = matiereDaoCatalogue.getMatiere(id);
			if( matiere != null) matiere = matiere.getDto();
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());
		}
		return matiere;
	}

	public void removeCarriereNative() 
	{
		carDaoGestion.removeCarriereNative();

	}

	public void removeCategorieCarriereNative() 
	{
		categorieDaoGestion.removeCategorieCarriereNative();	
	}

	public void removeMatiereNative() 
	{
		matiereDaoGestion.removeMatiereNative();
	}

}
