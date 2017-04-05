package dao.race_bonus_carac.race.consultation;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.race_bonus_carac.exception.DaoExceptionRBCMsg;
import dao.race_bonus_carac.param.Requetes;
import dao.util.Parameter;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import entity.race_bonus_carac.race.Race;

/**
 * Cette classe gère les requêtes à la base de donnée pour:
 * 
 * 	- 	Lister l'ensemble des races du jeu
 *  -	Lister l'ensemble des races du jeu accessible par le joueur
 * 	-	Rechercher une race par son id
 *  -	Rechercher une race par son nom
 *  
 * 
 * @author Francois Georgel
 *
 */


@LocalBean
@Singleton
public class RaceDaoConsultation {
	
	@PersistenceContext(unitName=Parameter.UNITNAME_JUNONARATHIEL)
	EntityManager em;
	
	/**
	 * Liste de toutes les races insérées dans la base
	 * 
	 * 
	 * @return ArrayList<Race>
	 */
	public ArrayList<Race> listeToutesRaces() {
		ArrayList<Race> liste = new ArrayList<Race>();
		
		for (Object r : em.createQuery(Requetes.TOUTES_RACES.getMsg()).getResultList()) {
			if (r instanceof Race){				
					Race race = ((Race) r).dto();
					liste.add(race);
			}
		}
		return liste;		
	}
	
	
	/**
	 * Liste de toutes les races disponibles pour les joueurs
	 * 
	 * @return ArrayList<Race>
	 */
	public ArrayList<Race> listeRacesJouables() {
		//TODO
		return null;		
	}

	
	/**
	 * Recherche d'une Race par nom
	 * 
	 * @param nom
	 * @return Race
	 * @throws DaoExceptionRBC
	 */
	public Race rechRaceParNom(String nom) throws DaoExceptionRBC {
		Race raceHib = null;
		
		if (nom!=null) {
			nom = nom.trim().toLowerCase();	
		
			try {
				raceHib = (Race) em.createQuery(Requetes.RACE_PAR_NOM.getMsg()).setParameter(1, nom).getSingleResult();
				
			} catch(Exception e) {
				if (e instanceof javax.persistence.NoResultException) {
					throw new DaoExceptionRBC(DaoExceptionRBCMsg.RACE_NO_EXIST);
				}
			}
		}	
		return raceHib;
	}
	
	
	/**
	 * Recherche d'une race par son Id
	 * 
	 * @param id
	 * @return Race
	 * @throws DaoExceptionRBC
	 */
	public Race RechRaceParId(int id) throws DaoExceptionRBC {
		Race raceHib = null;
		
		raceHib = (Race) em.find(Race.class, id);	

		if (raceHib == null) {
			throw new DaoExceptionRBC(DaoExceptionRBCMsg.RACE_NO_EXIST);
		}
		
		return raceHib;
	}

}
