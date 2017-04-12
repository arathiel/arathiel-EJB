package service.carriere.matiere.gestion;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import clientServeur.carriere.userException.UserExceptionCarriere;
import dao.FacadeDAO;
import dao.carriere.exception.DaoExceptionCarriere;
import entity.carriere.matiere.Matiere;
import service.ressources.carriere.Erreur;


@LocalBean
@Singleton
public class MatiereServGestion 
{
	
	@EJB
	FacadeDAO daoMatiere;

	public void ajouterMatiere(Matiere matiere) throws UserExceptionCarriere
	{
		isValide(matiere);
		
		try
		{
			daoMatiere.ajouterMatiere(matiere);
		}
		catch (DaoExceptionCarriere e) 
		{
			if(e.getMessage().equals(Erreur.MAT_DOUBLON.getMsg()))	{throw new UserExceptionCarriere(Erreur.MAT_DOUBLON.action(), Erreur.MAT_DOUBLON.getCode());}
			if(e.getMessage().equals(Erreur.MAT_NOM_OBLIGATOIRE.getMsg()))	{throw new UserExceptionCarriere(Erreur.MAT_NOM_OBLIGATOIRE.action(), Erreur.MAT_NOM_OBLIGATOIRE.getCode());}
		}
	}

	/**
	 * Passage de la demande de modification a la couche DAO
	 * @param matiere
	 * @throws UserExceptionCarriere
	 */
	public void modifierMatiere(Matiere matiere) throws UserExceptionCarriere
	{
		isValide(matiere);
		try
		{
			daoMatiere.modifierMatiere(matiere);
		}
		catch (DaoExceptionCarriere e) 
		{
			if(e.getMessage().equals(Erreur.MAT_NOM_OBLIGATOIRE.getMsg()))	{ throw new UserExceptionCarriere(Erreur.MAT_NOM_OBLIGATOIRE.action(), Erreur.MAT_NOM_OBLIGATOIRE.getCode());}
			if(e.getMessage().equals(Erreur.MAT_NULL.getMsg())){ throw new UserExceptionCarriere(Erreur.MAT_NULL.action(), Erreur.MAT_NULL.getCode());}

		}
	}

	/**
	 * Passage de la demande de suppression a la couche DAO
	 * @param matiere
	 * @throws UserExceptionCarriere
	 * @throws DaoExceptionCarriere
	 */
	public void delMatiere(Matiere matiere) throws UserExceptionCarriere
	{
		isValide(matiere);
		try
		{
			daoMatiere.supprimerMatiere(matiere);
		}
		catch (DaoExceptionCarriere e) 
		{
			throw new UserExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());
		}
	}

	/**
	 * Passage de la demande de suppression par nom a la couche DAO
	 * @param nomMatiere
	 * @throws UserExceptionCarriere
	 */
	public void delMatiereParNom(String nomMatiere) throws UserExceptionCarriere
	{
		try
		{
			daoMatiere.supprimerMatiereParNom(nomMatiere);
		}
		catch (DaoExceptionCarriere e) 
		{
			if(e.getMessage().equals(Erreur.MAT_INEXISTANT.getMsg()))
					{ throw new UserExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());}
			if(e.getMessage().equals(Erreur.MAT_NOM_OBLIGATOIRE.getMsg()))
					{ throw new UserExceptionCarriere(Erreur.MAT_NOM_OBLIGATOIRE.action(), Erreur.MAT_NOM_OBLIGATOIRE.getCode());}
			
		}
	}
	
	/**
	 * Methode permettant la formalisation du nom de la Matiere
	 * @param carriere
	 * @throws UserExceptionCarriere
	 */
	private void isValide(Matiere matiere) throws UserExceptionCarriere
	{
		matiere.getNomMatiere().trim().toLowerCase();
		if(matiere.getNomMatiere().isEmpty())
		{
			throw new UserExceptionCarriere(Erreur.MAT_NOM_OBLIGATOIRE.action(), Erreur.MAT_NOM_OBLIGATOIRE.getCode());
		}
	}

}
