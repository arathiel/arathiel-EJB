package service.carriere.categorie.catalogue;

import java.util.ArrayList;

import javax.ejb.*;

import clientServeur.carriere.userException.UserExceptionCarriere;
import dao.FacadeDAO;
import dao.carriere.exception.DaoExceptionCarriere;
import entity.carriere.CategorieCarriere;
import service.ressources.carriere.Erreur;


/**
 * Classe transmettant des informations a la DAO et recevant des DAOExceptions qu'elle modifie en UserExceptions
 * @author BlackSwan
 *
 */
@Singleton
@LocalBean
public class CategorieServCatalogue 
{
	@EJB
	FacadeDAO daoFacade;

	/**
	 * Methode permattant d'avoir la liste des categories
	 * @return ArrayList<Categorie>
	 */
	public ArrayList<CategorieCarriere> lstCategories() 
	{
		return daoFacade.lstCategorieCarrieres();
	}

	/**
	 * Methode permettant la recherche par id
	 * @param idCategorieCarriere
	 * @return categorie
	 * @throws UserExceptionCarriere
	 */
	public CategorieCarriere recherchCategorieParId(int idCategorieCarriere) throws UserExceptionCarriere
	{
		CategorieCarriere categorie = null;
		try 
		{
			categorie = daoFacade.recherchCategorieParId(idCategorieCarriere);
		} 
		catch (DaoExceptionCarriere e)
		{
			if(e.getMessage().equals(Erreur.CAT_INEXISTANT.getMsg())) 
			{ throw new UserExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());}
		}
		return categorie;
	}

	/**
	 * Methode permettant la recherche par nom
	 * @param idCategorieCarriere
	 * @return categorie
	 * @throws UserExceptionCarriere
	 */
	public CategorieCarriere recherchCategorieParNom(String nom) throws UserExceptionCarriere
	{
		CategorieCarriere categorie = null;
		try
		{
			categorie = daoFacade.recherchCategorieParNom(nom);
		}
		catch (DaoExceptionCarriere e)
		{
			if(e.getMessage().equals(Erreur.CAT_INEXISTANT.getMsg())) 
			{ throw new UserExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());}
		}
		return categorie;
	}

}
