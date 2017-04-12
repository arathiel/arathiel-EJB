package dao.carriere.categorieCarriere.gestion;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.*;
import javax.persistence.*;

import dao.carriere.carriere.gestion.CarriereDaoGestion;
import dao.carriere.exception.DoublonExceptionCarriere;
import dao.carriere.exception.InexistantExceptionCarriere;
import dao.carriere.matiere.gestion.MatiereDaoGestion;
import dao.util.carriere.UtilBdD;
import entity.carriere.CategorieCarriere;
import entity.carriere.matiere.Matiere;
import util.carriere.ReqSQLGestion;



@LocalBean
@Singleton
public class CategorieDaoGestion 
{
	@PersistenceContext(unitName=UtilBdD.PERSISTANCE_UNITNAME)
	EntityManager em;

	@EJB
	private CarriereDaoGestion daoCarriere;

	@EJB
	private MatiereDaoGestion daoMatiere;

	/**
	 * Methode d'ajout d'une categorie
	 * @param catCarriere
	 * @return newCat
	 * @throws InexistantExceptionCarriere 
	 * @throws DoublonExceptionCarriere 
	 * @throws IdException 
	 */
	public CategorieCarriere addCategorie(CategorieCarriere catCarriere) throws InexistantExceptionCarriere, DoublonExceptionCarriere
	{
		System.out.println("*** CategorieDaoGestion - addCategorie catCarriere : " + catCarriere);
		try
		{
			if(catCarriere !=null)
			{
				System.out.println("*** CarriereDaoGestion - debut du try ***" + catCarriere);
				if(catCarriere.getIdCategorie() == 0)
				{
					// on rtecupere la liste de matiere de la categorie
					List<Matiere> matieres = catCarriere.getMatieres();
					if(matieres != null)
					{
						ArrayList<Matiere> newListe = new ArrayList<Matiere>();
						
						System.out.println("*** CarriereDaoGestion - methode categorieCarriere ***" + matieres);
						for (Matiere matiere : matieres) { 

							Matiere newMat = daoMatiere.getMatiereParNom(matiere.getNomMatiere());
							if(newMat == null)
							{
								newMat = daoMatiere.addMatiere(matiere);

								System.out.println("*** Categorie creee ***" + matiere);
							}
							newListe.add(newMat);	
						}
						catCarriere.setMatieres(newListe);
					}
				}
				System.out.println("Categorie : " + catCarriere);
				em.persist(catCarriere);
				em.flush();
				System.out.println("Categorie apres flush : " + catCarriere);
			}	
		}
		catch (PersistenceException e) 
		{
			Throwable t = e.getCause();
			while ((t!=null) && (t instanceof SQLIntegrityConstraintViolationException))
			{
				t = t.getCause();
			}
			if (t instanceof SQLIntegrityConstraintViolationException)
			{
				throw new DoublonExceptionCarriere();
			}
			e.printStackTrace();
		}

		CategorieCarriere newCat =  getCategorieParNom(catCarriere.getNom());
		System.out.println("recuperation de l'objet : " + newCat);
		System.out.println("***Persist catCarriere *** : " + catCarriere);

		return newCat;
	}

	/**
	 * Methode de recuperation par le nom
	 * @param nom
	 * @return cat
	 * @throws InexistantExceptionCarriere
	 */
	public CategorieCarriere getCategorieParNom(String nom) throws InexistantExceptionCarriere 
	{
		CategorieCarriere cat = null;

		Query query = em.createQuery(ReqSQLGestion.CATEGORIECARRIERE_PAR_NOM);
		query.setParameter(1,nom).getSingleResult();

		try 
		{ 
			cat = (CategorieCarriere) query.getResultList();
		} 
		catch (PersistenceException e)
		{	
			if (e.getClass().equals(NoResultException.class))
			{
				System.out.println("*** CategorieDaoGestion - getCategorieParNom(nom) - throw : *** " + e.getClass());
				throw new InexistantExceptionCarriere();
			}
		} 
		return cat;
	}


	/**
	 * Methode de modification par le nom
	 * @param catCarriere
	 * @return
	 * @throws DoublonExceptionCarriere
	 * @throws InexistantExceptionCarriere
	 */
	public CategorieCarriere updateCategorie(CategorieCarriere catCarriere) throws DoublonExceptionCarriere, InexistantExceptionCarriere
	{
		//		Carriere carriere 			= null;
		Matiere matiere				= null;
		try
		{
			if(catCarriere !=null)
			{
				CategorieCarriere newCat = getCategorieParNom(catCarriere.getNom());
				System.out.println("*** CarriereDaoGestion - debut du try ***" + catCarriere);

				matiere = (Matiere) catCarriere.getMatieres();
				if(matiere != null)
				{
					matiere = daoMatiere.updateMatiere(matiere);
				//	newCat.setMatieres(matiere);
				}
				catCarriere.setIdCategorie(newCat.getIdCategorie());
			}
			else catCarriere = null;
			System.out.println("*** Persist method dans dao *** : " + catCarriere);
			em.merge(catCarriere);
			System.out.println("*** Persist method avant Flush  Categorie *** : " + catCarriere);
			em.flush();

		}
		catch (PersistenceException e) 
		{
			Throwable t = e.getCause();
			while ((t!=null) && (t instanceof SQLIntegrityConstraintViolationException))
			{
				t = t.getCause();
			}
			if (t instanceof SQLIntegrityConstraintViolationException)
			{
				throw new DoublonExceptionCarriere();
			}
			e.printStackTrace();
		}
		return catCarriere;
	}

	/**
	 * Methode de suppression d'une categorie
	 * @param catCarriere
	 * @throws InexistantExceptionCarriere
	 */
	public void delCategorie(CategorieCarriere catCarriere) throws InexistantExceptionCarriere 
	{
		if(catCarriere != null)
		{
			delCategorieParNom(catCarriere.getNom());
		}
	}

	/**
	 * Methode de suppression d'une categorie par le nom
	 * @param nom
	 * @throws InexistantExceptionCarriere
	 */
	public void delCategorieParNom(String nom) throws InexistantExceptionCarriere 
	{
		CategorieCarriere catCarriere = null;
		try
		{
			catCarriere = getCategorieParNom(nom);
			em.remove(catCarriere);
			em.flush();
		}
		catch (InexistantExceptionCarriere e) 
		{
			throw e;
		}
		catch (PersistenceException e) 
		{
			Throwable t = e.getCause();
			while ((t!=null) && !(t instanceof NoResultException))
			{
				throw new InexistantExceptionCarriere();
			}
			if (t instanceof SQLIntegrityConstraintViolationException)
			{
				t = t.getCause();
			}
		}		
	}

	/**
	 * Methode permettant la suppression de la table categorie
	 */
	public void removeCategorieCarriereNative() 
	{
		try
		{
			em.createNativeQuery(ReqSQLGestion.REMOVE_NATIVE_CATEGORIECARRIERE).executeUpdate();
		}
		catch (Exception e) 
		{
			System.err.println("*** Erreur CarriereDaoGestion : removeCarriereNative ***");
			e.printStackTrace();
		}
	}
}
