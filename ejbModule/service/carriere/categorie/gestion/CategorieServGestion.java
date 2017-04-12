package service.carriere.categorie.gestion;

import javax.ejb.*;

import clientServeur.carriere.userException.UserExceptionCarriere;
import dao.FacadeDAO;
import dao.carriere.exception.DaoExceptionCarriere;
import entity.carriere.CategorieCarriere;
import service.ressources.carriere.Erreur;



@Singleton
@LocalBean
public class CategorieServGestion 
{
	@EJB
	FacadeDAO daoFacade;

	/**
	 * Methode permettant le transfert de demande d'ajout a la couche DAO
	 * @param categorie
	 * @throws UserExceptionCarriere
	 */
	public void ajouterCategorie(CategorieCarriere categorie) throws UserExceptionCarriere
	{
		isValide(categorie);
		try
		{
			daoFacade.ajouterCategorieCarriere(categorie);
		}
		catch (DaoExceptionCarriere e) 
		{
			if(e.getMessage().equals(Erreur.CAT_DOUBLON.getMsg()))
				{ throw new UserExceptionCarriere(Erreur.CAT_DOUBLON.action(), Erreur.CAT_DOUBLON.getCode());}
			if(e.getMessage().equals(Erreur.CAT_NOM_OBLIGATOIRE.getMsg()))
				{ throw new UserExceptionCarriere(Erreur.CAT_NOM_OBLIGATOIRE.action(), Erreur.CAT_NOM_OBLIGATOIRE.getCode());}
		}
		
	}

	/**
	 * Methode permettant le transfert de demande de modification a la couche DAO
	 * @param categorie
	 * @throws UserExceptionCarriere
	 */
	public void modifiereCategorie(CategorieCarriere categorie) throws UserExceptionCarriere
	{
		isValide(categorie);
		try
		{
			daoFacade.ajouterCategorieCarriere(categorie);
		}
		catch (DaoExceptionCarriere e) 
		{
			if(e.getMessage().equals(Erreur.CAT_DOUBLON.getMsg()))
						{ throw new UserExceptionCarriere(Erreur.CAT_DOUBLON.action(), Erreur.CAT_DOUBLON.getCode());}
			if(e.getMessage().equals(Erreur.CAT_NOM_OBLIGATOIRE.getMsg()))
						{ throw new UserExceptionCarriere(Erreur.CAT_NOM_OBLIGATOIRE.action(), Erreur.CAT_NOM_OBLIGATOIRE.getCode());}
		}
	}

	/**
	 * Methode permettant le transfert de demande de suppression a la couche DAO
	 * @param categorie
	 * @throws UserExceptionCarriere
	 */
	public void delCategorie(CategorieCarriere categorie) throws UserExceptionCarriere
	{
		isValide(categorie);
		try
		{
			daoFacade.supprimerCategorieCarriere(categorie);
		}
		catch (DaoExceptionCarriere e) 
		{
			if(e.getMessage().equals(Erreur.CAT_INEXISTANT.getMsg()))
								{ throw new UserExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());}
			if(e.getMessage().equals(Erreur.CAT_NOM_OBLIGATOIRE.getMsg()))
								{ throw new UserExceptionCarriere(Erreur.CAT_NOM_OBLIGATOIRE.action(), Erreur.CAT_NOM_OBLIGATOIRE.getCode());}
		}		
	}

	/**
	 * Methode permettant le transfert de demande de suppresion a la couche DAO
	 * @param nom
	 * @throws UserExceptionCarriere
	 */
	public void delCategorieParNom(String nom) throws UserExceptionCarriere
	{
		try
		{
			daoFacade.supprimerCarriereParParNom(nom);
		}
		catch (DaoExceptionCarriere e) 
		{
			if(e.getMessage().equals(Erreur.CAT_INEXISTANT.getMsg()))
												{ throw new UserExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());}
			if(e.getMessage().equals(Erreur.CAT_NOM_OBLIGATOIRE.getMsg()))
												{ throw new UserExceptionCarriere(Erreur.CAT_NOM_OBLIGATOIRE.action(), Erreur.CAT_NOM_OBLIGATOIRE.getCode());}
		}
	}
	
	/**
	 * Methode permettant la validation du nom de categorie
	 * @param categorie
	 * @throws UserExceptionCarriere
	 */
	private void isValide(CategorieCarriere categorie) throws UserExceptionCarriere
	{
		categorie.getNom().trim().toLowerCase();
		if(categorie.getNom().isEmpty())
		{
				throw new UserExceptionCarriere(Erreur.MAT_NOM_OBLIGATOIRE.action(), Erreur.MAT_NOM_OBLIGATOIRE.getCode());
		}
	}
}
