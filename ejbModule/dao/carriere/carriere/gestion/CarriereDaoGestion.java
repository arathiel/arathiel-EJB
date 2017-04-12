package dao.carriere.carriere.gestion;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.ejb.*;
import javax.persistence.*;

import dao.carriere.categorieCarriere.gestion.CategorieDaoGestion;
import dao.carriere.exception.DaoExceptionCarriere;
import dao.carriere.exception.InexistantExceptionCarriere;
import dao.util.carriere.UtilBdD;
import entity.carriere.Carriere;
import entity.carriere.CategorieCarriere;
import service.ressources.carriere.Erreur;
import util.carriere.ReqSQLGestion;



/**
 * Methode de gestion d'une carriere par la BDD
 * @param carriere
 * @author Ismael
 *
 */
@LocalBean
@Singleton
public class CarriereDaoGestion 
{
	@PersistenceContext(unitName=UtilBdD.PERSISTANCE_UNITNAME )
	private EntityManager em;

	@EJB
	private CategorieDaoGestion daoCategorie;

//	@EJB
//	private DescriptionDaoGestion desc;


	/**
	 * Methode d'insertion d'une carriere
	 * @param carriere
	 * @throws DoublonExceptionCarriere 
	 * @throws InexistantExceptionCarriere 
	 * @throws IdException 
	 */
	public Carriere addCarriere(Carriere carriere) throws DaoExceptionCarriere
	{
		System.out.println("*** CarriereDaoGestion - addCarriere carriere : " + carriere);
		try
		{
			if(carriere !=null)
			{
				System.out.println("*** CarriereDaoGestion - debut du try ***" + carriere);
				if (carriere.getId() == 0)
				{
					System.out.println("*** CarriereDaoGestion - methode categorieCarriereId ***" + carriere);
					CategorieCarriere cat = carriere.getCategorieCarriere();
					if(cat != null)
					{
						System.out.println("*** CarriereDaoGestion - methode categorieCarriere ***" + cat);

						CategorieCarriere newCat = daoCategorie.getCategorieParNom(cat.getNom());
						
						if (newCat == null) 
						{
							System.out.println("*** CarriereDaoGestion - methode categorieCarriere ***" + newCat);
							
								newCat = daoCategorie.addCategorie(cat);					// Creation de la categorie si null
								
							System.out.println("*** Categorie creee ***" + cat);			// si cat n'existe pas, il faut la creer 	
						}	
						//setId(newCar.getId());
						carriere.setCategorieCarriere(newCat);
					}
				}
				System.out.println("*** Persist method ***");
				em.persist(carriere);
				em.flush();
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
				throw new DaoExceptionCarriere(Erreur.CAR_DOUBLON.action(), Erreur.CAR_DOUBLON.getCode());
			}
			e.printStackTrace();
		}
		System.out.println("Carriere creee : " + carriere);
		Carriere newCarriere;
		try 
		{
			newCarriere = getCarriereParNom(carriere.getNom());
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAR_INEXISTANT.action(), Erreur.CAR_INEXISTANT.getCode());
		}
		System.out.println("recuperation de l'objet : " + newCarriere);
		return newCarriere;
	}


	/**
	 * Method de mise a jour d'une carriere
	 * @param carriere
	 * @throws DoublonExceptionCarriere 
	 * @throws InexistantExceptionCarriere 
	 */
	public Carriere updateCarriere(Carriere carriere) throws DaoExceptionCarriere
	{
		System.out.println("*** CarriereDaoGestion - updateCarriere : " + carriere);
		CategorieCarriere categorie = null;
		//		DescripCarriere description = null;
		try
		{
			if(carriere != null)
			{
				Carriere newCar = null;
				
				newCar = getCarriereParNom(carriere.getNom());
					
				System.out.println("*** MatiereDaoGestion - debut du try ***" + newCar);

				categorie = carriere.getCategorieCarriere();
				if (categorie !=null)
				{
					categorie = daoCategorie.updateCategorie(categorie);
				
					newCar.setCategorieCarriere(categorie);			
				}
				carriere.setId(newCar.getId());
			}
			else carriere=null;
			System.out.println("*** Persist method ***");
			em.merge(carriere);
			System.out.println("*** Persist method avant Flush *** : " + carriere);
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
				
				throw new DaoExceptionCarriere(Erreur.CAR_DOUBLON.action(), Erreur.CAR_DOUBLON.getCode());
			}
			e.printStackTrace();
		}
		return carriere;
	}

	/**
	 * Methode de suppression
	 * @param carriere
	 * @throws InexistantExceptionCarriere 
	 */
	public void delCarriere(Carriere carriere) throws InexistantExceptionCarriere 
	{
		if(carriere !=null)
		{
			delCarriereParNom(carriere.getNom());
		}
	}

	/**
	 * Methode de suppression d'un carriere par l'id
	 * @param id
	 * @throws InexistantExceptionCarriere 
	 */
	public void delCarriereParId(int id) throws InexistantExceptionCarriere 
	{
		Carriere car = null;
		try
		{
			car = em.find(Carriere.class, id);
			em.remove(car);
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
		}		
	}

	/**
	 * Methode de suppression d'un carriere par le nom
	 * @param nom
	 * @throws InexistantExceptionCarriere
	 */
	public void delCarriereParNom(String nom) throws InexistantExceptionCarriere 
	{
		Carriere carriere = null;
		try
		{
			carriere = getCarriereParNom(nom);
			em.remove(carriere);
			em.flush();
		}
		catch (InexistantExceptionCarriere e) 
		{
			throw e;
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
		}		
	}

	/**
	 * Methode de recuperation par le nom
	 * @param nom
	 * @return car
	 * @throws InexistantExceptionCarriere
	 */
	public Carriere getCarriereParNom(String nomCarriere) throws InexistantExceptionCarriere
	{
		Carriere car = null;

		Query query = em.createQuery(ReqSQLGestion.CARRIERE_PAR_NOM);
		query.setParameter(1, nomCarriere);
		try
		{
			car = (Carriere) query.getSingleResult();
		}
		catch (PersistenceException e)
		{	
			if (e.getClass().equals(NoResultException.class))
			{
				System.out.println("*** CategorieDaoGestion - getCategorieParNom(nom) - throw : *** " + e.getClass());
				throw new InexistantExceptionCarriere();
			}
		} 
		return car;		
	}

	/**
	 * Methode permettant de retrouver une carriere par son Id
	 * @param id
	 * @return carriere
	 */
	public Carriere getCarriereParId(int id)
	{
		Carriere carriere = null;
		if(id !=0)
		{
			carriere = em.find(Carriere.class, id);
		}
		return carriere;
	}

	/**
	 * Methode permettant la reinitialisation
	 */
	public void removeCarriereNative() 
	{
		try
		{
			em.createNativeQuery(ReqSQLGestion.REMOVE_NATIVE_CARRIERE).executeUpdate();
		}
		catch (Exception e) 
		{
			System.err.println("*** Erreur CarriereDaoGestion : removeCarriereNative ***");
			e.printStackTrace();
		}
	}
}
