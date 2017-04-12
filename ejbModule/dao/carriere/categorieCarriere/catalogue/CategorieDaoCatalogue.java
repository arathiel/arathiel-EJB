package dao.carriere.categorieCarriere.catalogue;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.carriere.exception.InexistantExceptionCarriere;
import dao.util.carriere.UtilBdD;
import entity.carriere.CategorieCarriere;
import entity.carriere.util.CategorieCarrieres;
import util.carriere.ReqSQLGestion;


/**
 * Classe permettant la gestion du catalogue lie aux categories
 * @author BlackSwan
 *
 */
@LocalBean
@Singleton
public class CategorieDaoCatalogue 
{
	@PersistenceContext(unitName=UtilBdD.PERSISTANCE_UNITNAME)
	EntityManager em;

	/**
	 * Methode de recuperation d'une categorie par l'id
	 * @param idCategorieCarriere
	 * @return categorie
	 * @throws InexistantExceptionCarriere
	 */
	public CategorieCarriere getCategorie(int idCategorieCarriere) throws InexistantExceptionCarriere 
	{
		CategorieCarriere categorie = em.find(CategorieCarriere.class, idCategorieCarriere);
		return categorie;
	}

	/**
	 * Methode de recuperation d'une categorie par le nom
	 * @param nom
	 * @return categorie
	 * @throws InexistantExceptionCarriere
	 */
	public CategorieCarriere getCategorieParNom(String nom) throws InexistantExceptionCarriere
	{
		Query categorie = em.createQuery(ReqSQLGestion.CARRIERE_PAR_NOM);
		return (CategorieCarriere) recupObjet(categorie, CategorieCarriere.class);
	} 
	
	/**
	 * Methode paermettant de lister les categories
	 * @return catCarriere
	 */
	public CategorieCarrieres lstCategories() 
	{
		CategorieCarrieres catCarriere = new CategorieCarrieres();
		for(Object o: em.createQuery(ReqSQLGestion.GET_CATEGORIECARRIERE).getResultList())
		{
			if(o instanceof CategorieCarriere) 
				catCarriere.add((CategorieCarriere) o);
		}
		return catCarriere;
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
