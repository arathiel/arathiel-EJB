package service.carriere.carriere.gestion;

import javax.ejb.*;

import clientServeur.carriere.userException.UserExceptionCarriere;
import dao.FacadeDAO;
import dao.carriere.exception.DaoExceptionCarriere;
import entity.carriere.Carriere;
import service.ressources.carriere.Erreur;


@LocalBean
@Singleton
public class CarriereServGestion 
{
	@EJB
	FacadeDAO daoCarriere;

	/**
	 * Passage de la demande d'ajout a la couche DAO
	 * @param carriere
	 * @throws UserExceptionCarriere
	 */
	public void ajouterCarriere(Carriere carriere) throws UserExceptionCarriere
	{
		isValide(carriere);
		
		try
		{
			daoCarriere.ajouterCarriere(carriere);
		}
		catch (DaoExceptionCarriere e) 
		{
			if(e.getMessage().equals(Erreur.CAR_DOUBLON.getMsg()))	{ throw new UserExceptionCarriere(Erreur.CAR_DOUBLON);}
			if(e.getMessage().equals(Erreur.CAR_NOM_OBLIGATOIRE.getMsg()))	{ throw new UserExceptionCarriere(Erreur.CAR_NOM_OBLIGATOIRE);}
		}
	}

	/**
	 * Passage de la demande de modification a la couche DAO
	 * @param carriere
	 * @throws UserExceptionCarriere
	 */
	public void modifierCarriere(Carriere carriere) throws UserExceptionCarriere
	{
		isValide(carriere);
			try 
			{
				daoCarriere.modifierCarriere(carriere);
			} 
			catch (DaoExceptionCarriere e) 
			{
				if(e.getMessage().equals(Erreur.CAR_NOM_OBLIGATOIRE.getMsg()))	{ throw new UserExceptionCarriere(Erreur.CAR_NOM_OBLIGATOIRE);}
				if(e.getMessage().equals(Erreur.CAR_NULL.getMsg()))	{ throw new UserExceptionCarriere(Erreur.CAR_NULL);}
			}
	}

	/**
	 * Passage de la demande de suppression a la couche DAO
	 * @param carriere
	 * @throws UserExceptionCarriere
	 * @throws DaoExceptionCarriere 
	 */
	public void delCarriere(Carriere carriere) throws UserExceptionCarriere
	{
		isValide(carriere);
		try 
		{
			daoCarriere.supprimerCarriere(carriere);
		} 
		catch (DaoExceptionCarriere e) 
		{
			throw new UserExceptionCarriere(Erreur.CAR_INEXISTANT.action(), Erreur.CAR_INEXISTANT.getCode());
		}
		
	}

	/**
	 * Passage de la demande de suppression par Id a la couche DAO
	 * @param id
	 * @throws UserExceptionCarriere
	 * @throws DaoExceptionCarriere 
	 */
	public void delCarriereParId(int id) throws UserExceptionCarriere
	{
		try 
		{
			daoCarriere.supprimerCarriereParId(id);
		} 
		catch (DaoExceptionCarriere e) 
		{
			throw new UserExceptionCarriere(Erreur.CAR_INEXISTANT.action(), Erreur.CAR_INEXISTANT.getCode());
		}
		
	}

	/**
	 * Passage de la demande de suppression par nom a la couche DAO
	 * @param nom
	 * @throws UserExceptionCarriere
	 */
	public void delCarriereParNom(String nom) throws UserExceptionCarriere
	{
		try 
		{
			daoCarriere.supprimerCarriereParParNom(nom);
		} 
		catch (DaoExceptionCarriere e) 
		{
			throw new UserExceptionCarriere(Erreur.CAR_INEXISTANT.action(), Erreur.CAR_INEXISTANT.getCode());
		}
	}

	/**
	 * Methode permettant la formalisation du nom de la carriere
	 * @param carriere
	 * @throws UserExceptionCarriere
	 */
	private void isValide(Carriere carriere) throws UserExceptionCarriere
	{
		carriere.getNom().trim().toLowerCase();
	}
}
