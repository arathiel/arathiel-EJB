package service.carriere.carriere.catalogue;

import java.util.ArrayList;

import javax.ejb.*;

import clientServeur.carriere.userException.UserExceptionCarriere;
import dao.FacadeDAO;
import dao.carriere.exception.DaoExceptionCarriere;
import entity.carriere.Carriere;
import entity.carriere.util.Carrieres;
import service.ressources.carriere.Erreur;


/**
 * Classe transmettant des informations a la DAO et recevant des DAOExceptions qu'elle modifie en UserExceptions
 * @author BlackSwan
 *
 */
@LocalBean
@Singleton
public class CarriereServCatalogue 
{
	@EJB
	FacadeDAO daoCarriere;

	/**
	 * Methode permattant d'avoir la liste des carrieres
	 * @return ArrayList<Categorie>
	 */
	public ArrayList<Carriere> lstCarrieres() 
	{
		Carrieres lst;
		lst = daoCarriere.lstCarrieres();
		return lst;
	}

	/**
	 * Methode permettant la recherche par nom
	 * @param nom
	 * @return carriere
	 * @throws UserExceptionCarriere
	 */
	public Carriere recherCarParNom(String nom) throws UserExceptionCarriere
	{
		Carriere carriere = null;
		try
		{
			carriere = daoCarriere.recherCarParNom(nom);
		}
		catch (DaoExceptionCarriere e) 
		{
			if(e.getMessage().equals(Erreur.CAR_INEXISTANT.getMsg())) 
			{ throw new UserExceptionCarriere(Erreur.CAR_INEXISTANT.action(), Erreur.CAR_INEXISTANT.getCode());}
		}
		return carriere;
	}

	/**
	 * Methode permettant la recherche par id
	 * @param idCarriere
	 * @return carriere
	 * @throws UserExceptionCarriere
	 */
	public Carriere recherCarParId(int idCarriere) throws UserExceptionCarriere
	{
		Carriere carriere = null;
		try
		{
			carriere = daoCarriere.recherCarParId(idCarriere);
		}
		catch (DaoExceptionCarriere e)
		{
			if(e.getMessage().equals(Erreur.CAR_INEXISTANT.getMsg())) 
			{ throw new UserExceptionCarriere(Erreur.CAR_INEXISTANT.action(), Erreur.CAR_INEXISTANT.getCode());}
		}
		return carriere;
	}

}
