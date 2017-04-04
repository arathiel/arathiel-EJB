package dao.race_bonus_carac.race.gestion;

import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.race_bonus_carac.exception.DaoExceptionRBCMsg;
import dao.race_bonus_carac.param.Requetes;
import dao.race_bonus_carac.bonus.DaoBonus;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import dao.race_bonus_carac.race.consultation.RaceDaoConsultation;
import dao.util.Parameter;
import entity.race_bonus_carac.bonus.Bonus;
import entity.race_bonus_carac.race.Race;

/**
 * Cette classe gère les requêtes de persistence pour:
 * 
 * 	- 	La création
 * 	-	La suppression
 *  -	La modification 
 *  
 *  		des races du jeu
 * 
 * @author Francois Georgel
 *
 */

@LocalBean
@Singleton
public class RaceDaoGestion {
	
	@EJB
	RaceDaoConsultation rDaoConsult;
	
	@EJB
	DaoBonus daoBonus;
	
	
	@PersistenceContext(unitName=Parameter.UNITNAME_JUNONARATHIEL)
	EntityManager em;
	
	/**
	 * Insertion d'une race dans la base
	 * 
	 * @param race
	 * @throws DaoExceptionRBC
	 */
	public void insertRace (Race race) throws DaoExceptionRBC {
		//On teste d'abord si le nom communiqué est valide
		raceNomValide(race);
		
		System.out.println("insertrace"+race.toString());
		
		//On teste ensuite si parmis les bonus certains n'existent pas. On les insère d'abord.
		for (Bonus b : race.getListeBonus()){
			daoBonus.verifBonusPresent(b);
		}	
		
		
		try { 
			em.persist(race);
			em.flush();
		} catch(Exception e) {
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				throw new DaoExceptionRBC(DaoExceptionRBCMsg.DOUBLON_NOM_RACE);
			} else {
				throw new DaoExceptionRBC(DaoExceptionRBCMsg.PB_INSERT_RACE);
			}
		}		
	}
	
	
	/**
	 * Suppression d'une Race dans la base
	 * 
	 * @param race
	 * @throws DaoExceptionRBC
	 */
	public void deleteRace(Race race) throws DaoExceptionRBC {	
		Race raceHib = null;
		
		if (race != null) {
			raceHib = rDaoConsult.RechRaceParId(race.getId());
		}
		
		if (raceHib != null) {
			//parcours de la table bonus_race pour y supprimer toutes les réferences à cette race
			//le cascadeType.remove posée sur la liste de bonus de la race ne fonctionne pas car il cherche également à supprimer le bonus (qui peut être lié à d'autres races)
			em.createNativeQuery(Requetes.DELETE_BONUS_RACE.getMsg()).setParameter(1, race.getId());
			
			//puis on supprime la race selectionnée		
			em.remove(raceHib);
			em.flush();
			
		} else {	
			throw new DaoExceptionRBC(DaoExceptionRBCMsg.RACE_NO_EXIST);	
		}	
	
	}
	
	/**
	 * Mise à jour d'une race dans la base
	 * 
	 * 
	 * @param race
	 * @throws DaoExceptionRBC
	 */
	public void updateRace(Race race) throws DaoExceptionRBC{
		//On cherche si le nom de la race à modifier est correct
		raceNomValide(race);
		
		//On cherche si la race à modifier existe bien dans la base
		@SuppressWarnings("unused")
		Race raceHib;
		try {
			raceHib = rDaoConsult.RechRaceParId(race.getId());
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.RACE_NO_EXIST.getMsg())) { throw new DaoExceptionRBC(e.getMessage());}
			else {throw new DaoExceptionRBC(DaoExceptionRBCMsg.PB_UPDATE_RACE);}
		}
			
		//On retire auparavant les bonus correspondant à la race avant le merge
		em.createNativeQuery(Requetes.DELETE_BONUS_RACE.getMsg()).setParameter(1, race.getId());
		
		//Puis on insère les nouveaux (en passant par une verification)
		for (Bonus b : race.getListeBonus()){
			try {
				daoBonus.verifBonusPresent(b);
			} catch (DaoExceptionRBC e) {
				if (e.getMessage().equals(DaoExceptionRBCMsg.BONUS_NULL.getMsg())) 		{ throw new DaoExceptionRBC(DaoExceptionRBCMsg.BONUS_NULL);}
				if (e.getMessage().equals(DaoExceptionRBCMsg.ERR_VAL_BONUS.getMsg())) 	{ throw new DaoExceptionRBC(DaoExceptionRBCMsg.ERR_VAL_BONUS);}
				else { throw new DaoExceptionRBC(DaoExceptionRBCMsg.PB_INSERT_BONUS);}
			}
		}	
		
		em.merge(race);
		em.flush();		
	}
		
	//Methode qui va vérifier la validité du nom d'une race (ne contient pas de signes non autorisés et n'est pas null) 
	public void raceNomValide(Race race) throws DaoExceptionRBC {
	
		boolean b = Pattern.matches("[[a-z] ' \\- () éèêëâàäïîôûüç]*", race.getNom().trim().toLowerCase());
		
		if (race.getNom().isEmpty() || race.getNom() == null || b==false || race.getNom().length() > 40){
			throw new DaoExceptionRBC(DaoExceptionRBCMsg.RACE_NOM_INVALIDE);
		}	
	}
	
}
