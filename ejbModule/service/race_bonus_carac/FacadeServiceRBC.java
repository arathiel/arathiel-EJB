package service.race_bonus_carac;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import clientServeur.race_bonus_carac.userException.UserExceptionRBC;
import clientServeur.race_bonus_carac.userException.UserExceptionRBCMsg;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import dao.race_bonus_carac.exception.DaoExceptionRBCMsg;
import entity.race_bonus_carac.bonus.Bonus;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.race_bonus_carac.race.Race;
import service.race_bonus_carac.bonus.ServiceBonus;
import service.race_bonus_carac.carac.ServiceCarac;
import service.race_bonus_carac.race.gestion.RaceServiceGestion;
import service.race_bonus_carac.race.lister.RaceServiceConsultation;

/**
 * 
 * 
 * Cette Facade regroupe les acces:
 * 	- aux classes Service Gestion et Consultation pour les Races
 * 	- directement aux classes Service uniques pour Bonus et Caracteristique
 *  
 *  @author Francois Georgel 
 *  
 */

@LocalBean
@Singleton
public class FacadeServiceRBC{
	
	@EJB
	RaceServiceGestion	sRaceGestion;
	
	@EJB
	RaceServiceConsultation sRaceConsult;
	
	@EJB
	ServiceBonus sBonus;
	
	@EJB
	ServiceCarac sCarac;

	
	//Méthodes de gestion des Races
	public void enregistrerRace(Race race) throws UserExceptionRBC {
		sRaceGestion.enregistrerRace(race);
	}

	public void supprimerRace(Race race) throws UserExceptionRBC {
		sRaceGestion.supprimerRace(race);
	}

	public void modifierRace(Race race) throws UserExceptionRBC {
		try {
			sRaceGestion.modifierRace(race);
		} catch (UserExceptionRBC e) {
			throw new UserExceptionRBC(e.getMessage());
		}
	}

	//Méthodes de consultation des Races
	public ArrayList<Race> listeToutesRaces() {
		return sRaceConsult.listeToutesRaces();
	}

	public ArrayList<Race> listeRacesJouables() {
		return sRaceConsult.listeRacesJouables();
	}

	public Race rechRaceParNom(String nom) throws UserExceptionRBC {
		return sRaceConsult.rechRaceParNom(nom);
	}

	public Race rechRaceParId(int id) throws UserExceptionRBC {
		return sRaceConsult.rechRaceParId(id);
	}

	//Méthodes de Gestion et Consultation des Bonus
	public void ajouterBonus(Bonus bonus) throws UserExceptionRBC   {
		try {
			sBonus.ajouterBonus(bonus);
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_BONUS.getMsg())) {throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_BONUS);}
			else {throw new UserExceptionRBC(UserExceptionRBCMsg.PB_INSERT_BONUS);}
		}	
	}

	public ArrayList<Bonus> listeTousBonus() {
		return sBonus.listeTousBonus();
	}

	public void supprimerBonus(Bonus bonus) throws UserExceptionRBC {
		try {
			sBonus.supprimerBonus(bonus);
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.BONUS_NO_EXISTS.getMsg())) {throw new UserExceptionRBC(UserExceptionRBCMsg.BONUS_NO_EXISTS);}
			else {throw new UserExceptionRBC(UserExceptionRBCMsg.PB_DELETE_BONUS);}
		}
		
	}


	
	
	//Méthodes de Gestion et Consultation des Caractéristiques
	public void ajouterCarac(Caracteristique carac) {
		sCarac.ajouterCarac(carac);
	}

	public void supprimerCarac(Caracteristique carac) {
		sCarac.supprimerCarac(carac);
	}

	public ArrayList<Caracteristique> listeCarac() {
		return sCarac.listeCarac();
	}   
	
	public Caracteristique rechCaracParId(String id) {
		return sCarac.rechCaracParId(id);
	}
    
}
