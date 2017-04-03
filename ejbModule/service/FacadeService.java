package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import armurerie.Exception.ServiceOlivBException;
import clientServeur.IArme;
import clientServeur.IFacadeService;
import clientServeur.exception.UserException;
import clientServeur.race_bonus_carac.userException.UserExceptionRBC;
import entity.armurerie.Arme;
import entity.armurerie.ArmeJoueur;
import entity.armurerie.Joueur;
import entity.competence.Competence;
import entity.race_bonus_carac.bonus.Bonus;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.race_bonus_carac.race.Race;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
import service.armurerie.ServiceArme;
import service.competence.FacadeServiceCompetence;
import service.race_bonus_carac.FacadeServiceRBC;
import service.trait.FacadeTraitServ;
import technic.trait.Comportements;
import technic.trait.Traits;

/**
 * Cette classe implémente clientServeur.IFacadeService.class
 * 
 * Liste et aiguillage de toutes les méthodes disponibles pour le serveur web.
 * 
 * @author Groupe
 * @version 20170313
 *
 */
@Stateless
@Remote(IFacadeService.class)
public class FacadeService implements IFacadeService {

	// Attributs de classe

	//--------------------------------------- Jonathan	
	@EJB
	private FacadeTraitServ			servTrait;
	
	//--------------------------------------- Suivant
	@EJB
	private FacadeServiceCompetence servComp;

	//--------------------------------------- Francois
	@EJB
	private FacadeServiceRBC		servRBC;
	
	//----------------------------------------OlivB
	@EJB
	private ServiceArme 			serviceArme;
	
	
//-------------------------------------------------------------------------------------------- Jonathan
	
		/* ========================================== */ 
		/*  				TRAIT					  */
		/* ========================================== */
	
	//Administration
	

	/**
	 * Ajoute un trait
	 * @param trait
	 * @throws UserException
	 */
	@Override
	public void ajouterTrait(Trait trait) throws UserException {
		servTrait.ajouterTrait(trait);
	}

	/**
	 * Modifie un trait
	 * @param trait
	 * @throws UserException
	 */
	@Override
	public void modiferTrait(Trait trait) throws UserException {
		servTrait.modiferTrait(trait);
	}

	/**
	 * Supprime un trait
	 * @param id
	 * @throws UserException
	 */
	@Override
	public void supprimerTrait(int id) throws UserException {
		servTrait.supprimerTrait(id);
	}

	/**
	 * Supprime tous les traits
	 * @throws UserException
	 */
	@Override
	public void reinitialiserTrait() throws UserException {
		servTrait.reinitialiserTrait();
	}

	
	//Consultation
	
	/**
	 * Retourne un trait via son ID
	 * @param id
	 * @return
	 * @throws UserException
	 */
	@Override
	public Trait consulterTraitById(int id) throws UserException {
		return servTrait.consulterTraitById(id);
	}

	/**
	 * Retourne un trait via son libellé
	 * @param libelle
	 * @return
	 * @throws UserException
	 */
	@Override
	public Trait consulterTraitByLib(String libelle) throws UserException {
		return servTrait.consulterTraitByLib(libelle);
	}

	/**
	 * Retourne la liste complete de tous les traits
	 * @return
	 */
	@Override
	public Traits consulterListTrait() {
		return servTrait.consulterListTrait();
	}

	/**
	 * Retourne la liste des trait d'après libellé
	 * @return
	 */
	@Override
	public Traits consulterListTraitByLib(String libelle) {
		return servTrait.consulterListTraitByLib(libelle);
	}
	
	/* ========================================== */ 
	/*  			COMPORTEMENT				  */
	/* ========================================== */
	
	// Administration

	/**
	 * Ajoute un comportement
	 * @param comportement
	 * @throws UserException
	 */
	@Override
	public void ajouterComp(Comportement comportement) throws UserException {
		servTrait.ajouterComp(comportement);
	}

	/**
	 * Modifie un comportement
	 * @param comportement
	 * @throws UserException
	 */
	@Override
	public void modifierComp(Comportement comportement) throws UserException {
		servTrait.modifierComp(comportement);
	}

	/**
	 * Supprime un comportement
	 * @param id
	 * @throws UserException
	 */
	@Override
	public void supprimerComp(int id) throws UserException {
		servTrait.supprimerComp(id);
	}

	/**
	 * Supprime tous les comportements
	 * @throws UserException
	 */
	@Override
	public void reinitialiserComp() throws UserException {
		servTrait.reinitialiserComp();
	}

	
	//Consultation
	
	/**
	 * Retourne un comportement via son ID
	 * @param id
	 * @return
	 * @throws UserException
	 */
	@Override
	public Comportement consulterCompById(int id) throws UserException {
		return servTrait.consulterCompById(id);
	}

	/**
	 * Retourne un comportement via son libellé
	 * @param libelle
	 * @return
	 * @throws UserException
	 */
	@Override
	public Comportement consulterCompByLib(String libelle) throws UserException {
		return servTrait.consulterCompByLib(libelle);
	}

	/**
	 * Retourne la liste complète des comportements
	 * @return
	 */
	@Override
	public Comportements consulterListComp() {
		return servTrait.consulterListComp();
	}

	/* ========================================== */ 
	/*  			CARACTERISTIQUE				  */
	/* ========================================== */
	
	/**
	 * Retourne une Caractéristique via le nom (Aucun contrôle)
	 * @param nomCarac
	 * @return
	 * @author Jonathan
	 * @throws UserException 
	 */
	public Caracteristique getCarByLib(int id) throws UserException {
		return servTrait.getCarByLib(id);
	}
	
	/**
	 * Retourne une Caractéristique via le nom (Aucun contrôle)
	 * @param nomCarac
	 * @return
	 * @author Jonathan
	 * @throws UserException 
	 */
	public Caracteristique getCarByLib(String nomCarac) throws UserException {
		return servTrait.getCarByLib(nomCarac);
	}
	
	/**
	 * Retourne la liste complète des caractéristiques de la BDD
	 * @return
	 */
	public ArrayList<Caracteristique> getAllCar() {
		return servTrait.getAllCar();
	}

	
	//-------------------------------------------------------------------------------------------- Francois

	/* ========================================== */ 
	/*  				RACE					  */
	/* ========================================== */
	/**
	 * Enregistre une nouvelle Race
	 * 
	 * @param race
	 * @throws UserExceptionRBC
	 */
	@Override
	public void enregistrerRace(Race race) throws UserExceptionRBC {
		servRBC.enregistrerRace(race);	
	}

	/**
	 * Supprime une Race
	 * 
	 * @param race
	 * @throws UserExceptionRBC
	 */
	@Override
	public void supprimerRace(Race race) throws UserExceptionRBC {
		servRBC.supprimerRace(race);
	}

	/**
	 * Modifie une Race
	 * 
	 * @param race
	 * @throws UserExceptionRBC
	 */
	@Override
	public void modifierRace(Race race) throws UserExceptionRBC {
		servRBC.modifierRace(race);	
	}

	/**
	 * Renvoie la liste de toutes les races
	 * 
	 * @return la liste de toutes les races enregistrees
	 */
	@Override
	public ArrayList<Race> listeToutesRaces() {
		return servRBC.listeToutesRaces();
	}

	/**
	 * Renvoie la liste des races accessibles au joueur
	 * 
	 * @return la liste des races accessibles pour un joueur
	 */	
	@Override
	public ArrayList<Race> listeRacesJouables() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Renvoie une race recherchee par son nom
	 * 
	 * @param nom
	 * @return race correspondant au nom choisi en parametre
	 */
	@Override
	public Race RechRaceParNom(String nom) throws UserExceptionRBC {
		return servRBC.RechRaceParNom(nom);
	}

	/**
	 * Renvoie une race recherchee par son identifiant
	 * 
	 * @param id identifiant de type numeraire
	 * @return race correspondant à l'identifiant choisi en parametre
	 */
	@Override
	public Race RechRaceParId(int id) throws UserExceptionRBC {
		return servRBC.RechRaceParId(id);
	}

	
	/* ========================================== */ 
	/*  				BONUS					  */
	/* ========================================== */
	
	/**
	 * Enregistre un nouveau bonus
	 * 
	 * @param bonus
	 * @throws UserExceptionRBC
	 */
	@Override
	public void insertBonus(Bonus bonus) throws UserExceptionRBC {
		servRBC.ajouterBonus(bonus);
	}

	/**
	 * Renvoie la liste de tous les bonus
	 * 
	 * @return la liste des bonus
	 */	
	@Override
	public ArrayList<Bonus> listeTousBonus() {
		return servRBC.listeTousBonus();
	}

	
	/**
	 * Supprime un Bonus
	 * 
	 * @param bonus
	 * @throws UserExceptionRBC
	 */
	@Override
	public void deleteBonus(Bonus bonus) throws UserExceptionRBC {
		servRBC.supprimerBonus(bonus);		
	}

	/**
	 * Recupère la liste des Compétences
	 * Codé rapidement pour les besoins de l'entité Bonus
	 * 
	 *@return liste de toutes les Competences
	 */
	public ArrayList<Competence> listeToutesComp(){
		return servComp.listeToutesComp();
	}
	
	public Competence rechCompParId(int id){
		return servComp.rechCompParId(id);
	}
	
	/* ========================================== */ 
	/*  			CARACTERISTIQUE				  */
	/* ========================================== */
	
	/**
	 * Enregistre une nouvelle Caracteristique
	 * 
	 * @param carac
	 * @throws UserExceptionRBC
	 */
	@Override
	public void insertCarac(Caracteristique carac) {
		servRBC.ajouterCarac(carac);		
	}

	/**
	 * Supprime une Caracteristique
	 * 
	 * @param carac
	 * @throws UserExceptionRBC
	 */
	@Override
	public void deleteCarac(Caracteristique carac) {
		servRBC.supprimerCarac(carac);
	}

	/**
	 * Renvoie la liste de toutes les Caracteristiques de la base
	 * 
	 * @return la liste des caracteristiques
	 */
	@Override
	public ArrayList<Caracteristique> listeCarac() {
		return servRBC.listeCarac();
	}


	
	//-------------------------------------------------------------------------------------------- OlivB
	
	/* ========================================== */ 
	/*  			ARME				  		  */
	/* ========================================== */
	

	public void createArme(IArme arme, List<String> raceArme) throws ServiceOlivBException {
		serviceArme.createArme(arme, raceArme);
		
	}


	public void createArmeJoueur(ArmeJoueur armeJoueurDto) throws ServiceOlivBException {
		serviceArme.createArmeJoueur(armeJoueurDto);
		
	}
	

	public void modifArme(IArme arme, List<String> raceArme) throws ServiceOlivBException {
		serviceArme.modifArme(arme, raceArme);
		
	}
	

	public void supprArme(IArme arme) throws ServiceOlivBException {
		serviceArme.supprArme(arme);
		
	}
	

	public Arme getArme(String nom) {
		return serviceArme.getArme(nom);
	}


	public List<Race> listerRaces() throws ServiceOlivBException {
		return serviceArme.listerRaces();
	}


	public List<Arme> listerArmes() throws ServiceOlivBException {
		return serviceArme.listerArmes();
	}



	public List<Arme> listerArmesRace() throws ServiceOlivBException {
		return serviceArme.listerArmesRace();
	}


	public List<Joueur> listerJoueurs() throws ServiceOlivBException {
		return serviceArme.listerJoueurs();
	}

	
}
