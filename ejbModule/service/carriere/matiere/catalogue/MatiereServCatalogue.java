package service.carriere.matiere.catalogue;

import java.util.ArrayList;

import javax.ejb.*;

import clientServeur.carriere.userException.UserExceptionCarriere;
import dao.FacadeDAO;
import dao.carriere.exception.DaoExceptionCarriere;
import entity.carriere.matiere.Matiere;
import entity.carriere.util.Matieres;
import service.ressources.carriere.Erreur;


@LocalBean
@Singleton
public class MatiereServCatalogue
{
	@EJB
	FacadeDAO daoMatiere;
	
	public ArrayList<Matiere> lstMatieres() 
	{
		Matieres lst;
		lst = daoMatiere.lstMatieres();
		return lst;
//		Query query = em.createQuery(ReqSQLGestion.ALL_CARRIERE_PAR_ID);
//		return recupObjet(query, Matiere.class);
	}

	public Matiere recherchMatiereParNom(String nomMatiere) throws UserExceptionCarriere
	{
		Matiere matiere = null;
		try
		{
			matiere = daoMatiere.recherchMatiereParNom(nomMatiere);
		}
		catch (DaoExceptionCarriere e) 
		{
			if(e.getMessage().equals(Erreur.MAT_INEXISTANT.getCode()));
			{ throw new UserExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());}
		}
		//Query query = em.createQuery(ReqSQLGestion.MATIERE_PAR_NOM);
		//return (Matiere) recupObjet(query, Matiere.class);
		
		return matiere;
	}

	public Matiere recherchMatiereParId(int id) throws UserExceptionCarriere
	{
		Matiere matiere = null;
		try
		{
			matiere = daoMatiere.recherchMatierParID(id);	
		}
		catch (DaoExceptionCarriere e)
		{
			if(e.getMessage().equals(Erreur.MAT_INEXISTANT.getCode()));
			{ throw new UserExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());}
		}
		return matiere;
	}

	
}
