package dao.race_bonus_carac.race;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.race_bonus_carac.exception.DaoExceptionRBCMsg;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import dao.race_bonus_carac.race.consultation.RaceDaoConsultation;
import dao.race_bonus_carac.race.gestion.RaceDaoGestion;
import entity.race_bonus_carac.race.Race;


/**
 *  Classe de contact de la couche persistence.
 *  
 *  ** ATTENTION **
 *  
 *  Cette classe est codée en remote pour tester la couche DAO,
 *  Elle devra être recodée en LocalBean quand la couche service sera implémentée 
 *  
 *  
 *  
 * @author Francois Georgel
 *
 */


@LocalBean
@Singleton
public class FacadeDaoRace {

	
	@EJB
	RaceDaoGestion rdgestion;
	
	@EJB 
	RaceDaoConsultation rdconsult;
	
	


	public void insertRace(Race race) throws DaoExceptionRBC {		
		rdgestion.insertRace(race);
	}


	public void deleteRace(Race race) throws DaoExceptionRBC {
		rdgestion.deleteRace(race);
	} 

	public void updateRace(Race race) throws DaoExceptionRBC {
		rdgestion.updateRace(race);
	}


	public ArrayList<Race> listeToutesRaces() {
		ArrayList<Race> listeRaces = new ArrayList<Race>();
		
		for (Race raceHib : rdconsult.listeToutesRaces()) {
			Race race = raceHib.dto();
			listeRaces.add(race);
		}	
		
		return listeRaces;
	}


	public ArrayList<Race> listeRacesJouables() {
		return rdconsult.listeRacesJouables();
	}


	public Race RechRaceParNom(String nom) throws DaoExceptionRBC {
		Race raceHib;
		Race race = null;
		
		try {
			raceHib = rdconsult.RechRaceParNom(nom);
			race = raceHib.dto();
			
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.RACE_NO_EXIST.getMsg())) {
				System.out.println("Remontée de l'exception race non existante dans raceDaoFacade/rechParNom");
				throw new DaoExceptionRBC(DaoExceptionRBCMsg.RACE_NO_EXIST);
			}
		}
		
		return race;
	}


	public Race RechRaceParId(int id) throws DaoExceptionRBC {
		Race raceHib;
		Race race = null; 
		
		try {
			raceHib = rdconsult.RechRaceParId(id);
			race = raceHib.dto();
			
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.RACE_NO_EXIST.getMsg())) {
				System.out.println("Remontée de l'exception race non existante dans raceDaoFacade/rechParId");
				throw new DaoExceptionRBC(DaoExceptionRBCMsg.RACE_NO_EXIST);
			}
		}
		return race;
	}	
	
	
}
