package dao.carriere.matiere.gestion;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

import javax.ejb.*;
import javax.persistence.*;

import dao.carriere.categorieCarriere.gestion.CategorieDaoGestion;
import dao.carriere.exception.*;
import dao.util.carriere.UtilBdD;
import entity.carriere.CategorieCarriere;
import entity.carriere.matiere.Matiere;
import util.carriere.ReqSQLGestion;



@LocalBean
@Singleton
public class MatiereDaoGestion 
{
	@PersistenceContext(unitName=UtilBdD.PERSISTANCE_UNITNAME)
	EntityManager em;

	@EJB
	private CategorieDaoGestion daoCategorie;
	
	@EJB
	private MatiereDaoGestion daoMatiere;
	

	/**
	 * Methode d'insertion d'une matiere
	 * @param matiere
	 * @return matiere
	 * @throws DoublonExceptionCarriere
	 * @throws InexistantExceptionCarriere 
	 * @throws IdException 
	 */
	public Matiere addMatiere(Matiere matiere) throws DoublonExceptionCarriere, InexistantExceptionCarriere
	{
		System.out.println("***MatiereDaoGestion - addMatiere matiere : " + matiere);
		try
		{
			if(matiere != null)
			{
				System.out.println("*** MatiereDaoGestion - debut du try ***" + matiere);
				if (matiere.getId() == 0) 
				{	
					Collection<CategorieCarriere> lstCats = new ArrayList<>();
					if(matiere !=null)
					{
						List<CategorieCarriere> lstCategories;
						if(lstCats instanceof List)
						{
							lstCategories = (List<CategorieCarriere>) matiere.getCategoriesCarrieres();
							if(lstCategories != null)
							{
								ArrayList<CategorieCarriere> newLst = new ArrayList<CategorieCarriere>();
								System.out.println("*** MatiereDaoGestion - methode matiere ***" + lstCategories);
								for (CategorieCarriere cat : lstCategories)
								{
									CategorieCarriere newCat = daoCategorie.getCategorieParNom(cat.getNom());
									if(newCat == null)
									{
										System.out.println("*** MatiereDaoGestion - methode add matiere ***" + newCat);
										newCat = daoCategorie.addCategorie(cat);

										System.out.println("*** Categorie creee ***" + cat);
									}
									newLst.add(newCat);
								}
								matiere.setCategoriesCarrieres(newLst);
							}	
						}
					}
					System.out.println("*** Persist method ***");
					em.persist(matiere);
					em.flush();
				}	
			}
		}
		catch (PersistenceException e) 
		{
			Throwable t = e.getCause();
			while ((t!=null) && !(t instanceof SQLIntegrityConstraintViolationException))
			{
				t = t.getCause();
			}
			if (t instanceof SQLIntegrityConstraintViolationException)
			{
				throw new DoublonExceptionCarriere();
			}
			e.printStackTrace();
		}
		Matiere newMat = getMatiereParNom(matiere.getNomMatiere());
		System.out.println("recuperation de l'objet : " + newMat);
		System.out.println("***Persist matiere *** : " + matiere);

		return newMat;
	}

	/**
	 *  Methode de modification d'une matiere par le nom
	 * @param matiere
	 * @return matiere
	 * @throws DoublonExceptionCarriere
	 * @throws InexistantExceptionCarriere
	 */
	public Matiere updateMatiere(Matiere matiere) throws DoublonExceptionCarriere, InexistantExceptionCarriere
	{
		System.out.println("*** CarriereDaoGestion - updateCarriere : " + matiere);
		Matiere mat = null;
		CategorieCarriere categorie = null;
		try
		{
			if(matiere != null)
			{
				mat = daoMatiere.getMatiereParNom(matiere.getNomMatiere());	
				
				System.out.println("*** MatiereDaoGestion - debut du try ***" + mat);
				categorie= (CategorieCarriere) matiere.getCategoriesCarrieres();
				
				if(categorie != null)
				{
					categorie = daoCategorie.updateCategorie(categorie);
				}
				matiere.setId(mat.getId());
			}
			else matiere = null;
			
			System.out.println("*** Persist method dans dao *** : " + matiere);
			em.merge(matiere);
			System.out.println("*** Persist method avant Flush  Categorie *** : " + matiere);
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
		return matiere;
	}



	/**
	 * Methode permettant de supprimer une matiere
	 * @param matiere
	 * @throws InexistantExceptionCarriere
	 * @throws DoublonExceptionCarriere
	 */
	public void delMatiere(Matiere matiere) throws InexistantExceptionCarriere 
	{
		Matiere mat = null;
		if(matiere != null)
		{
			mat = getMatiereParNom(matiere.getNomMatiere());
			if(mat != null) 
			{
				matiere.setId(mat.getId());
			}
			else throw new InexistantExceptionCarriere();
			try
			{
				em.remove(mat);
				em.flush();
			}
			catch (PersistenceException e) 
			{
				Throwable t = e.getCause();
				while ((t!=null) && (t instanceof NoResultException))
				{
					throw new InexistantExceptionCarriere();
				}
				if (t instanceof SQLIntegrityConstraintViolationException)
				{
					t = t.getCause();
				}
				//e.printStackTrace();
			}		
		}
	}

	/**
	 * Methode permettant de supprimer une matiere par le nom
	 * @param nomMatiere
	 * @throws InexistantExceptionCarriere
	 */
	public void delMatiereParNom(String nomMatiere) throws InexistantExceptionCarriere 
	{
		Matiere matiere = null;
		if(nomMatiere != null)
		{
			matiere = getMatiereParNom(nomMatiere);
			delMatiere(matiere);
		}
	} 

	/**
	 * Methode permettant de retrouver une matiere par son Id
	 * @param id
	 * @return matiere
	 */
	public Matiere getMatiereParId(int id)
	{
		Matiere matiere = null;
		if (id != 0)	
		{
			matiere = (Matiere) em.createQuery(ReqSQLGestion.MATIERE_PAR_ID).setParameter(1, id).getSingleResult();
		}
		return matiere;
	}

	/**
	 * Methode permettant de retrouver une matiere par son Nom
	 * @param nomMatiere
	 * @return matiere
	 * @throws InexistantExceptionCarriere 
	 */
	public Matiere getMatiereParNom(String nomMatiere) throws InexistantExceptionCarriere
	{
		Matiere matiere = null;
		Query query = em.createQuery(ReqSQLGestion.MATIERE_PAR_NOM);
		query.setParameter(1, nomMatiere).getSingleResult();
		try
		{
			matiere = (Matiere) query.getSingleResult();
		}
		catch (PersistenceException e)
		{	
			if (e.getClass().equals(NoResultException.class))
			{
				System.out.println("*** MatiereDaoGestion - getMatiereParNom(nom) - throw : *** " + e.getClass());
				throw new InexistantExceptionCarriere();
			}
		} 
		return matiere;	
	}

	/**
	 * Methode permettant la suppression de la table matiere
	 */
	public void removeMatiereNative() 
	{
		try
		{
			em.createNativeQuery(ReqSQLGestion.REMOVE_NATIVE_MATIERE).executeUpdate();
		}
		catch (Exception e) 
		{
			System.err.println("*** Erreur MatiereDaoGestion : removeMatiereNative ***");
			e.printStackTrace();
		}
	}
}
