package service.race_bonus_carac.race.lister;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import clientServeur.race_bonus_carac.userException.UserExceptionRBC;
import clientServeur.race_bonus_carac.userException.UserExceptionRBCMsg;
import dao.FacadeDAO;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import dao.race_bonus_carac.exception.DaoExceptionRBCMsg;
import entity.race_bonus_carac.race.Race;


/**
 * *Bean Service de Consultation des Races
 * 
 * Se charge de transmettre les informations de listage et recherche des Races à la couche DAO
 * Se charge de receptionner les Exceptions de la couche DAO et de les transformer en UserException
 * 
 * @author François Georgel
 *
 */
@LocalBean
@Singleton
public class RaceServiceConsultation {

	@EJB
	FacadeDAO fDao;
	
	
	/**
	 * Liste toutes les races créées dans la base
	 * @return ArrayList<Race>
	 */
	public ArrayList<Race> listeToutesRaces() {
		ArrayList<Race> liste = new ArrayList<Race>();
		liste = fDao.listeToutesRaces();
		return liste;		
	}
	
	
	/**
	 * Liste toutes les races disponibles pour les joueurs
	 * 
	 * @return ArrayList<Race>
	 */
	public ArrayList<Race> listeRacesJouables() {
		ArrayList<Race> liste = new ArrayList<Race>();
		liste = fDao.listeRacesJouables();
		return liste;		
	}

	
	/**
	 * Recherche une Race par son nom
	 * 
	 * @param nom
	 * @return Race
	 * @throws UserExceptionRBC
	 */
	public Race RechRaceParNom(String nom) throws UserExceptionRBC  {
		
		Race race = null;
		try {
			race = fDao.RechRaceParNom(nom);
			
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.RACE_NO_EXIST.getMsg())) {
				System.out.println("race no exist dans raceServiceConsult");
				throw new UserExceptionRBC(UserExceptionRBCMsg.RACE_NO_EXIST);
			} else {
				System.out.println("autre erreur dans raceServiceConsult");
				throw new UserExceptionRBC(UserExceptionRBCMsg.PB_RECH_RACE);
			}
		}
		
		return race;
	}
	
	
	/**
	 * Recherche une race par son Id
	 * 
	 * @param id
	 * @return Race
	 * @throws UserExceptionRBC
	 */
	public Race RechRaceParId(int id) throws UserExceptionRBC {
		System.out.println("RaceServiceConsult rechRaceParId");
		
		Race race = null;
		try {
			race = fDao.RechRaceParId(id);
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.RACE_NO_EXIST.getMsg())) {throw new UserExceptionRBC(UserExceptionRBCMsg.RACE_NO_EXIST);}
			else {throw new UserExceptionRBC(UserExceptionRBCMsg.PB_RECH_RACE);}
		}
		
		return race;
	}
}
