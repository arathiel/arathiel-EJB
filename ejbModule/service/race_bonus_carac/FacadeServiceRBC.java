package service.race_bonus_carac;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import clientServeur.race_bonus_carac.interfaces.IFacadeServiceRBC;
import clientServeur.race_bonus_carac.userException.UserExceptionRBC;
import clientServeur.race_bonus_carac.userException.UserExceptionRBCMsg;
import dao.race_bonus_carac.bonus.DaoBonus;
import dao.race_bonus_carac.carac.DaoCarac;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import dao.race_bonus_carac.exception.DaoExceptionRBCMsg;
import entity.race_bonus_carac.bonus.Bonus;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.race_bonus_carac.race.Race;
import service.race_bonus_carac.race.gestion.RaceServiceGestion;
import service.race_bonus_carac.race.lister.RaceServiceConsultation;

/**
 * 
 * 
 * Cette Facade regroupe les acces:
 * 	- à la couche Service Gestion et Consultation pour les Races
 * 	- directement aux couches DAO uniques pour Bonus et Caracteristique
 *  
 *  @author Francois Georgel 
 *  
 */

@LocalBean
@Singleton
public class FacadeServiceRBC implements IFacadeServiceRBC{
	
	@EJB
	RaceServiceGestion	sRaceGestion;
	
	@EJB
	RaceServiceConsultation sRaceConsult;
	
	@EJB
	DaoBonus dBonus;
	
	@EJB
	DaoCarac dCarac;

	
	//Méthodes de gestion des Races
	@Override
	public void enregistrerRace(Race race) throws UserExceptionRBC {
		sRaceGestion.enregistrerRace(race);
	}

	@Override
	public void supprimerRace(Race race) throws UserExceptionRBC {
		sRaceGestion.supprimerRace(race);
	}

	@Override
	public void modifierRace(Race race) throws UserExceptionRBC {
		sRaceGestion.modifierRace(race);
	}

	//Méthodes de consultation des Races
	@Override
	public ArrayList<Race> listeToutesRaces() {
		return sRaceConsult.listeToutesRaces();
	}

	@Override
	public ArrayList<Race> listeRacesJouables() {
		return sRaceConsult.listeRacesJouables();
	}

	@Override
	public Race RechRaceParNom(String nom) throws UserExceptionRBC {
		System.out.println("rechrace par nom facade service Rbc");
		return sRaceConsult.RechRaceParNom(nom);
	}

	@Override
	public Race RechRaceParId(int id) throws UserExceptionRBC {
		return sRaceConsult.RechRaceParId(id);
	}

	//Méthodes de Gestion et Consultation des Bonus
	@Override
	public void insertBonus(Bonus bonus) throws UserExceptionRBC   {
		try {
			dBonus.insertBonus(bonus);
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_BONUS.getMsg())) {throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_BONUS);}
			else {throw new UserExceptionRBC(UserExceptionRBCMsg.PB_INSERT_BONUS);}
		}	
	}

	@Override
	public ArrayList<Bonus> listeTousBonus() {
		return dBonus.listeTousBonus();
	}

	@Override
	public void deleteBonus(Bonus bonus) throws UserExceptionRBC {
		try {
			dBonus.deleteBonus(bonus);
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.BONUS_NO_EXISTS.getMsg())) {throw new UserExceptionRBC(UserExceptionRBCMsg.BONUS_NO_EXISTS);}
			else {throw new UserExceptionRBC(UserExceptionRBCMsg.PB_DELETE_BONUS);}
		}
		
	}


	
	
	//Méthodes de Gestion et Consultation des Caractéristiques
	@Override
	public void insertCarac(Caracteristique carac) {
		dCarac.insertCarac(carac);
	}

	@Override
	public void deleteCarac(Caracteristique carac) {
		dCarac.deleteCarac(carac);
	}

	@Override
	public ArrayList<Caracteristique> listeCarac() {
		return dCarac.listeCarac();
	}   
    
}
