package dao.carriere.carriere.catalogue;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.*;
import javax.persistence.*;

import dao.carriere.exception.InexistantExceptionCarriere;
import dao.util.carriere.UtilBdD;
import entity.carriere.Carriere;
import util.carriere.ReqSQLGestion;


@LocalBean
@Singleton
public class CarriereDaoCatalogue 
{
	@PersistenceContext(unitName=UtilBdD.PERSISTANCE_UNITNAME)
	EntityManager em; 

	/**
	 * Methode de recuperation d'une liste de matieres
	 * @return List
	 */
	public List<Carriere> lstCarrieres() 
	{
		Query query = em.createQuery(ReqSQLGestion.GET_CARRIERE);	
		return recupObjet(query, Carriere.class);
//		Carrieres carriere = new Carrieres();
//		for(Object o: em.createQuery(ReqSQLGestion.GET_CARRIERE).getResultList())
//		{
//			if(o instanceof Carriere)
//				carriere.add((Carriere)o);
//		}
//		return carriere;		
	}

	/**
	 * Methode permettant le retour d'une carriere par le nom
	 * @param nom
	 * @return carriere
	 * @throws InexistantExceptionCarriere
	 */
	public Carriere getCarriere(String nom) throws InexistantExceptionCarriere
	{
		Query query = em.createQuery(ReqSQLGestion.CARRIERE_PAR_NOM);	
	
		return (Carriere) recupObjet(query, Carriere.class);
	}

	/**
	 * Methode permettant le retour d'une carriere par l'id
	 * @param idCarriere
	 * @return carriere
	 * @throws InexistantExceptionCarriere
	 */
	public Carriere getCarriere(int idCarriere) throws InexistantExceptionCarriere
	{
		Carriere carriere = em.find(Carriere.class, idCarriere);
		return carriere;
	}

	/**
	 * Methode permettant de creer un object recuperant les attributs de la classe recherchee
	 * @param query
	 * @param classe
	 * @return lst
	 */
	@SuppressWarnings("unchecked")
	private <T> List<T> recupObjet(Query query, Class<T> classe)
	{
		List<T> lst = new ArrayList<T>();
		for(Object o : query.getResultList())
			try
		{
				lst.add((T)o);
		}
		catch (ClassCastException e) 
		{
			
		}
		return lst;
	}
}
