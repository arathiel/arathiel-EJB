package service.race_bonus_carac.race.gestion;

import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import clientServeur.race_bonus_carac.userException.UserExceptionRBC;
import clientServeur.race_bonus_carac.userException.UserExceptionRBCMsg;
import dao.race_bonus_carac.exception.DaoExceptionRBCMsg;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import dao.race_bonus_carac.race.FacadeDaoRace;
import entity.race_bonus_carac.race.Race;

/**
 * Bean Service de Gestion des races
 * 
 * Se charge de transmettre les informations de création/suppression/modification des Races à la couche DAO
 * Se charge de receptionner les Exceptions de la couche DAO et de les transformer en UserException
 * 
 * @author Francois Georgel
 *
 */
@LocalBean
@Singleton
public class RaceServiceGestion {
	
	
	@EJB
	FacadeDaoRace fDaoRace;

	
	/**
	 * Classe de la couche Service 
	 * transmet une race à la couche DAO pour insertion
	 * 
	 * @param race
	 * @throws UserExceptionRBC
	 */
	public void enregistrerRace (Race race) throws UserExceptionRBC{
		//Avant l'envoi à la couche DAO on teste si la race est valide (tous les champs renseignés)
		testRaceValide(race);
		
		try {
			fDaoRace.insertRace(race);
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.RACE_NOM_INVALIDE.getMsg()))	{ throw new UserExceptionRBC(UserExceptionRBCMsg.RACE_NOM_INVALIDE);}
			if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_ID_RACE.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_ID_RACE);}
			if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_NOM_RACE.getMsg()))	{ throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_NOM_RACE);}
			if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_BONUS.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_BONUS);}
			if (e.getMessage().equals(DaoExceptionRBCMsg.ERR_VAL_BONUS.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.ERR_VAL_BONUS);}
			else {throw new UserExceptionRBC(UserExceptionRBCMsg.PB_INSERT_RACE);}
		}
	}
	
	/**
	 * Classe de la couche Service 
	 * transmet une race à la couche DAO pour suppression
	 * 
	 * @param race
	 * @throws UserExceptionRBC
	 */
	public void supprimerRace(Race race) throws UserExceptionRBC{	
		try {
			fDaoRace.deleteRace(race);
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.RACE_NO_EXIST.getMsg())) 	{ throw new UserExceptionRBC(UserExceptionRBCMsg.RACE_NO_EXIST);}
			if (e.getMessage().equals(DaoExceptionRBCMsg.PB_DELETE_RACE.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.PB_DELETE_RACE);}
			if (e.getMessage().equals(DaoExceptionRBCMsg.BONUS_NO_EXISTS.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.BONUS_NO_EXISTS);}
			if (e.getMessage().equals(DaoExceptionRBCMsg.PB_DELETE_BONUS.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.PB_DELETE_BONUS);}
			else {throw new UserExceptionRBC(UserExceptionRBCMsg.PB_DELETE_RACE);}
		}
	}

	
	/**
	 * Classe de la couche Service 
	 * transmet une race à la couche DAO pour modification
	 * 
	 * @param race
	 * @throws UserExceptionRBC
	 */
	public void modifierRace(Race race) throws UserExceptionRBC{
		try {
			fDaoRace.updateRace(race);
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.RACE_NO_EXIST))				{ throw new UserExceptionRBC(UserExceptionRBCMsg.RACE_NO_EXIST);}
			if (e.getMessage().equals(DaoExceptionRBCMsg.RACE_NOM_INVALIDE.getMsg()))	{ throw new UserExceptionRBC(UserExceptionRBCMsg.RACE_NOM_INVALIDE);}
			if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_ID_RACE.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_ID_RACE);}
			if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_NOM_RACE.getMsg()))	{ throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_NOM_RACE);}
			if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_BONUS.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_BONUS);}
			if (e.getMessage().equals(DaoExceptionRBCMsg.ERR_VAL_BONUS.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.ERR_VAL_BONUS);}
			else {throw new UserExceptionRBC(UserExceptionRBCMsg.PB_UPDATE_RACE);}
		}
	}
	
	/**
	 * Méthode de vérification de la validité du nom d'une race
	 * 
	 * 
	 * @param race
	 * @throws UserExceptionRBC
	 */	
	private void testRaceValide(Race race) throws UserExceptionRBC {
		boolean b = Pattern.matches("[[a-z] ' \\- () éèêëâàäïîôûüç]*", race.getNom().trim().toLowerCase());
		
		if (race.getNom().isEmpty() || race.getNom() == null || b==false || race.getNom().length() > 40){
			throw new UserExceptionRBC(UserExceptionRBCMsg.RACE_NOM_INVALIDE);
		}	
		
	}
	
}
