package dao.race_bonus_carac.race.gestion;

import java.util.ArrayList;
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
		
		//On verifie ensuite s'il n'est pas déjà présent dans la base avec une casse différente
		ArrayList<Race> liste = rDaoConsult.listeToutesRaces();
		for (Race r: liste) {											
			if ((race.getNom()).equalsIgnoreCase(r.getNom())) {
				throw new DaoExceptionRBC(DaoExceptionRBCMsg.DOUBLON_NOM_RACE);
			}
		}
		
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
			raceHib = rDaoConsult.rechRaceParId(race.getId());
		}
		
		if (raceHib != null) {
			//parcours de la table armerace pour y supprimer toutes les réferences à cette race
			//cette table crée par OlivierB pour gérer le ManyToMany de l'arme sur les races rend la suppression d'une race impossible 
			//si celle -ci est liée à une arme. Il faudra améliorer cette fonctionalité dans l'avenir mais pour le moment, les lignes sont supprimées à la main
			try{
				em.createNativeQuery(Requetes.DELETE_ARME_RACE.getMsg()).setParameter(1, raceHib.getId()).executeUpdate();
			}	catch (Exception e){
				throw new DaoExceptionRBC(DaoExceptionRBCMsg.PB_DELETE_RACE);
			}
			
			//On fait la même chose avec la colonne qui reference cette race dans la table passion
			try{
				em.createNativeQuery(Requetes.DELETE_PASSION_RACE.getMsg()).setParameter(1, raceHib.getId()).executeUpdate();
			}	catch (Exception e){
				throw new DaoExceptionRBC(DaoExceptionRBCMsg.PB_DELETE_RACE);
			}
			
			//puis on supprime la race selectionnée	
			try {
				em.remove(raceHib);
				em.flush();
			} catch (Exception e) {
				throw new DaoExceptionRBC(DaoExceptionRBCMsg.PB_DELETE_RACE);
			}
		
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
		//On cherche si la race à modifier existe bien dans la base
		@SuppressWarnings("unused")
		Race raceHib;
		try {
			raceHib = rDaoConsult.rechRaceParId(race.getId());
		} catch (DaoExceptionRBC e) {
			if (e.getMessage().equals(DaoExceptionRBCMsg.RACE_NO_EXIST.getMsg())) {throw new DaoExceptionRBC(DaoExceptionRBCMsg.RACE_NO_EXIST);}
			else {throw new DaoExceptionRBC(DaoExceptionRBCMsg.PB_UPDATE_RACE);}
		}
			
		
		//On cherche si le nom de la race à modifier est correct
		raceNomValide(race);
		
		//On verifie ensuite s'il n'est pas déjà présent dans la base (le nom peut être changé, c'est l'id qui sert de référence)
		//Si le nom est déjà présent on verifie qu'il correspond bien à l'Id de la race qu'on veut changer
		ArrayList<Race> liste = rDaoConsult.listeToutesRaces();
		for (Race r: liste) {											
			if ((race.getNom()).equalsIgnoreCase(r.getNom())) {
				if(race.getId()!=r.getId()) {
					throw new DaoExceptionRBC(DaoExceptionRBCMsg.DOUBLON_NOM_RACE);
				}	
			}
		}
		
		//Puis on insère les nouveaux bonus (en passant par une verification)
		for (Bonus b : race.getListeBonus()){
			try {
				daoBonus.verifBonusPresent(b);
			} catch (DaoExceptionRBC e) {
				if (e.getMessage().equals(DaoExceptionRBCMsg.BONUS_NULL.getMsg())) 		{ throw new DaoExceptionRBC(DaoExceptionRBCMsg.BONUS_NULL);}
				if (e.getMessage().equals(DaoExceptionRBCMsg.ERR_VAL_BONUS.getMsg())) 	{ throw new DaoExceptionRBC(DaoExceptionRBCMsg.ERR_VAL_BONUS);}
				else { throw new DaoExceptionRBC(DaoExceptionRBCMsg.PB_INSERT_BONUS);}
			}
		}	
		
		try {
			em.merge(race);
			em.flush();		
		} catch (Exception e) {
			throw new DaoExceptionRBC(DaoExceptionRBCMsg.PB_INSERT_RACE);
		}
		
	}
		
	//Methode qui va vérifier la validité du nom d'une race (ne contient pas de signes non autorisés et n'est pas null) 
	public void raceNomValide(Race race) throws DaoExceptionRBC {
	
		boolean b = Pattern.matches("[[a-z] ' \\- () éèêëâàäïîôûüç]*", race.getNom().trim().toLowerCase());
		
		if ((race.getNom().trim()).isEmpty() || race.getNom().trim() == null || b==false || race.getNom().length() > 40){
			throw new DaoExceptionRBC(DaoExceptionRBCMsg.RACE_NOM_INVALIDE);
		}
	}
	
}
