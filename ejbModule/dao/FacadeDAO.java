package dao;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import clientServeur.exception.UserException;
import dao.competence.FacadeDaoCompetence;
import dao.race_bonus_carac.bonus.DaoBonus;
import dao.race_bonus_carac.carac.DaoCarac;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import dao.race_bonus_carac.race.FacadeDaoRace;
//import dao.magie.FacadeDaoMagie;
//import dao.passion.FacadeDaoPassion;
import dao.trait.FacadeTraitDao;
import dao.trait.exception.DoublonException;
import dao.trait.exception.IdNullException;
import dao.trait.exception.LibelleNullException;
import dao.trait.exception.LibelleVideException;
import dao.trait.exception.ObjetInexistantException;
import dao.trait.exception.ObjetNullException;
import dao.trait.exception.ObjetUtiliseException;
import entity.competence.Competence;
import entity.race_bonus_carac.bonus.Bonus;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.race_bonus_carac.race.Race;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
import technic.trait.Comportements;
import technic.trait.Traits;

/**
 * Cette classe est la facade de la couche DAO.
 * 
 * Liste et aiguillage des m�thodes interragissant avec l'unit� de persistance.
 * Elle est instanci�e par le serveur d'application � l'appel des diff�rentes classes du package service.
 * Une fois cr��, le m�me objet est utilis�.
 * 
 * @author Groupe
 * @version 20170313
 *
 */
@LocalBean
@Singleton
public class FacadeDAO {

	// Attributs de classe
	
	//--------------------------------------- Jonathan
	@EJB
	private FacadeTraitDao			daoTrait;
	
	//--------------------------------------- ???
	@EJB
	private FacadeDaoCompetence 	daoComp;
//	@EJB
//	private FacadeDaoMagie 			facDaoMagie;
//	@EJB
//	private FacadeDaoPassion 		facDaoPassion;
	
	//--------------------------------------- Francois
	@EJB
	private FacadeDaoRace	fDaoRace;
	
	@EJB
	private DaoBonus dBonus;
	
	@EJB
	private DaoCarac dCarac;
	
	@EJB
	private FacadeDaoCompetence dComp;
	
	
	/* ========================================== */ 
	/*  				TRAIT					  */
	/* ========================================== */
	
	// Administration
	
	/**
	 * Persiste un trait
	 * 
	 * @param trait
	 * @throws UserException
	 * @throws LibelleVideException
	 * @throws LibelleNullException
	 * @throws DoublonException
	 * @throws ObjetNullException
	 */
	public void ajouterTrait(Trait trait) throws LibelleVideException, LibelleNullException, DoublonException, ObjetNullException {
		daoTrait.ajouterTrait(trait);	
	}
	
	/**
	 * Modifie un trait de la BDD
	 * 
	 * @param trait
	 * @throws UserException
	 * @throws ObjetInexistantException
	 * @throws IdNullException
	 * @throws LibelleNullException
	 * @throws ObjetNullException
	 * @throws LibelleVideException
	 */
	public void modiferTrait(Trait trait) throws ObjetInexistantException, IdNullException, LibelleNullException, ObjetNullException, LibelleVideException {
		daoTrait.modiferTrait(trait);
	}
	
	/**
	 * Supprime un trait via son ID
	 * 
	 * @param id
	 * @throws UserException
	 * @throws ObjetNullException
	 * @throws IdNullException
	 * @throws ObjetUtiliseException 
	 */
	public void supprimerTrait(int id) throws ObjetNullException, IdNullException, ObjetUtiliseException {
		daoTrait.supprimerTrait(id);
	}
	
	/**
	 * Vide la table de trait de la BDD
	 * @throws ObjetUtiliseException 
	 * 
	 * @throws UserException
	 */
	public void reinitialiserTrait() throws ObjetUtiliseException {
		daoTrait.reinitialiserTrait();
	}
	
	// Consultation
	
	/**
	 * Retourne un trait via son ID
	 * 
	 * @param id
	 * @return
	 * @throws UserException
	 * @throws IdNullException
	 * @throws ObjetInexistantException
	 */
	public Trait consulterTraitById(int id) throws IdNullException, ObjetInexistantException {
		return daoTrait.consulterTraitById(id);
	}
	
	/**
	 * Retourne un trait via son libell�
	 * 
	 * @param libelle
	 * @return
	 * @throws UserException
	 * @throws ObjetInexistantException
	 * @throws LibelleNullException
	 */
	public Trait consulterTraitByLib(String libelle) throws ObjetInexistantException, LibelleNullException {
		return daoTrait.consulterTraitByLib(libelle);
	}
	
	/**
	 * Retourne la liste compl�te de trait de la BDD
	 * @return
	 */
	public Traits consulterListTrait() {
		return daoTrait.consulterListTrait();
	}
	
	/**
	 * Retourne la liste des trait de la BDD d'apr�s
	 * @return
	 */
	public Traits consulterListTraitByLib(String libelle) {
		return daoTrait.consulterListTraitByLib(libelle);
	}
	
	/* ========================================== */ 
	/*  			COMPORTEMENT				  */
	/* ========================================== */
	
	// Administration
	
	/**
	 * Persiste un comportement dans la BDD
	 * 
	 * @param comportement
	 * @throws UserException
	 * @throws DoublonException
	 * @throws LibelleVideException
	 * @throws LibelleNullException
	 * @throws ObjetNullException
	 */
	public void ajouterComp(Comportement comportement) throws DoublonException, LibelleVideException, LibelleNullException, ObjetNullException {
		daoTrait.ajouterComp(comportement);
	}
	
	/**
	 * Modifie un comportement de la BDD
	 * 
	 * @param comportement
	 * @throws UserException
	 * @throws IdNullException
	 * @throws ObjetInexistantException
	 */
	public void modifierComp(Comportement comportement) throws IdNullException, ObjetInexistantException {
		daoTrait.modifierComp(comportement);
	}
	
	/**
	 * Supprime un comportement de la BDD via son ID
	 * 
	 * @param id
	 * @throws UserException
	 * @throws ObjetInexistantException
	 * @throws IdNullException
	 */
	public void supprimerComp(int id) throws ObjetInexistantException, IdNullException {
		daoTrait.supprimerComp(id);
	}
	
	/**
	 * Vide tous les comportements de la BDD
	 * @throws UserException
	 * @throws ObjetInexistantException 
	 */
	public void reinitialiserComp() throws ObjetInexistantException {
		daoTrait.reinitialiserComp();
	}
	
	// Consultation
	/**
	 * Retourne un comportement via son ID
	 * 
	 * @param id
	 * @return
	 * @throws IdNullException 
	 * @throws ObjetInexistantException 
	 */
	public Comportement consulterCompById(int id) throws IdNullException, ObjetInexistantException  {
		return daoTrait.consulterCompById(id);
	}
	
	/**
	 * Retourne un comportement via son libell�
	 * 
	 * @param libelle
	 * @return
	 * @throws IdNullException 
	 * @throws LibelleVideException 
	 * @throws ObjetInexistantException 
	 * @throws LibelleNullException 
	 */
	public Comportement consulterCompByLib(String libelle) throws LibelleVideException, LibelleNullException, ObjetInexistantException {
		return daoTrait.consulterCompByLib(libelle);
	}
	
	/**
	 * Retourne la liste compl�te des comportements
	 * 
	 * @return
	 */
	public Comportements consulterListComp() {
		return daoTrait.consulterListComp();
	}
	
	/**
	 * Retourne la liste compl�te des CompCaracteristique
	 * 
	 * @return
	 */
	public Comportements consulterListCompCar() {
		return daoTrait.consulterListCompCar();
	}
	
	/**
	 * Retourne la liste compl�te des CompRoleplay
	 * 
	 * @return
	 */
	public Comportements consulterListCompRP() {
		return daoTrait.consulterListCompRP();
	}
	
	
	/* ========================================== */ 
	/*  			CARACTERISTIQUE				  */
	/* ========================================== */
	
	/**
	 * Retourne une Caract�ristique via le nom (Aucun contr�le)
	 * @param nomCarac
	 * @return
	 * @author Jonathan
	 * @throws IdNullException 
	 * @throws ObjetInexistantException 
	 */
	public Caracteristique getCarByLib(int id) throws IdNullException, ObjetInexistantException {
		return daoTrait.getCarByLib(id);
	}
	
	/**
	 * Retourne une Caract�ristique via le nom (Aucun contr�le)
	 * @param nomCarac
	 * @return
	 * @author Jonathan
	 * @throws ObjetInexistantException 
	 * @throws LibelleVideException 
	 * @throws LibelleNullException 
	 */
	public Caracteristique getCarByLib(String nomCarac) throws ObjetInexistantException, LibelleVideException, LibelleNullException {
		return daoTrait.getCarByLib(nomCarac);
	}
	
	/**
	 * Retourne la liste compl�te des caract�ristiques de la BDD
	 * @return
	 */
	public ArrayList<Caracteristique> getAllCar() {
		return daoTrait.getAllCar();
	}

	
	//--------------------------------------------------------------------------------- Francois
	
	/* ========================================== */ 
	/*  				BONUS					  */
	/* ========================================== */
	
	public void insertBonus(Bonus bonus) throws DaoExceptionRBC {
		dBonus.insertBonus(bonus);
	}

	public void deleteBonus(Bonus bonus) throws DaoExceptionRBC {
		dBonus.deleteBonus(bonus);
		
	}

	public ArrayList<Bonus> listeTousBonus() {
		return dBonus.listeTousBonus();
	}

	public ArrayList<Competence> listeToutesComp(){
		return dComp.listeToutesComp();
	}
	
	public Competence rechCompParId(int id){
		return dComp.rechCompParId(id);
	}
	
	/* ========================================== */ 
	/*  			CARACTERISTIQUE				  */
	/* ========================================== */
	public void insertCarac(Caracteristique carac) {
		dCarac.insertCarac(carac);
	}

	public void deleteCarac(Caracteristique carac) {
		dCarac.deleteCarac(carac);		
	}

	public ArrayList<Caracteristique> listeCarac() {		
		return dCarac.listeCarac();
	}
	
	public Caracteristique rechCaracParId(String id) {
		return dCarac.rechCaracParId(id);
	}
	
	/* ========================================== */ 
	/*  				RACE					  */
	/* ========================================== */
	public void insertRace(Race race) throws DaoExceptionRBC {		
		fDaoRace.insertRace(race);
	}


	public void deleteRace(Race race) throws DaoExceptionRBC {
		fDaoRace.deleteRace(race);
	} 

	public void updateRace(Race race) throws DaoExceptionRBC {
		fDaoRace.updateRace(race);
	}


	public ArrayList<Race> listeToutesRaces() {
		return fDaoRace.listeToutesRaces();
	}


	public ArrayList<Race> listeRacesJouables() {
		return fDaoRace.listeRacesJouables();
	}


	public Race RechRaceParNom(String nom) throws DaoExceptionRBC {
		return fDaoRace.RechRaceParNom(nom);
	}


	public Race RechRaceParId(int id) throws DaoExceptionRBC {
		return fDaoRace.RechRaceParId(id);
	}	
}
