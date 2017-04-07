package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import clientServeur.IArme;
import clientServeur.IFacadeService;
import clientServeur.exception.ExceptionService;
import clientServeur.exception.UserException;
import clientServeur.race_bonus_carac.userException.UserExceptionRBC;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import entity.armurerie.Arme;
import entity.armurerie.ArmeJoueur;
import entity.armurerie.Joueur;
import entity.competence.Competence;
import entity.magie.MDPFondamental;
import entity.magie.MDPNormal;
import entity.passion.Passion;
import entity.race_bonus_carac.bonus.Bonus;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.race_bonus_carac.race.Race;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
import service.armurerie.ServiceArme;
import service.armurerie.exception.ServiceOlivBException;
import service.competence.FacadeServiceCompetence;
import service.magie.FacadeMagie;
import service.passion.FacadePassion;
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
	
	//----------------------------------------Anaïs
	@EJB
	private FacadeMagie facMagie;

	@EJB
	private FacadePassion facPassion;

	
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

	
	/**
	 * Cherche une Caracteristique par son id
	 * 
	 * @return une caracteristique
	 */
	public Caracteristique rechCaracParId(String id) {
		return servRBC.rechCaracParId(id);
	}
	
	//-------------------------------------------------------------------------------------------- OlivB
	
	/* ========================================== */ 
	/*  			ARME				  		  */
	/* ========================================== */
	
	//creation d'une arme avec sa liste de Races (Race obligatoire car multiplicité 1,*)
	@Override
	public void createArme(IArme arme, List<String> raceArme) throws ServiceOlivBException {
		serviceArme.createArme(arme, raceArme);
		
	}

	//association d'un joueur avec une arme et un état de l'arme
	@Override
	public void createArmeJoueur(ArmeJoueur armeJoueurDto, int joueurId, int armeId, String etat) throws ServiceOlivBException {
		serviceArme.createArmeJoueur(armeJoueurDto, joueurId, armeId, etat);
		
	}
	//modification de l'arme
	@Override
	public void modifArme(IArme arme, List<String> raceArme) throws ServiceOlivBException {
		serviceArme.modifArme(arme, raceArme);
		
	}
	//suppression d'une arme
	@Override
	public void supprArme(IArme arme) throws ServiceOlivBException {
		serviceArme.supprArme(arme);
		
	}
	//obtenir une arme par son nom
	@Override
	public Arme getArme(String nom) {
		return serviceArme.getArme(nom);
	}
	//lister les races pour l'affichage du formulaire de création
	@Override
	public List<Race> listerRaces() throws ServiceOlivBException {
		return serviceArme.listerRaces();
	}
	//lister les armes pour l'affichage de la liste des armes
	@Override
	public List<Arme> listerArmes() throws ServiceOlivBException {
		return serviceArme.listerArmes();
	}

	//lister les armes selon la race
	@Override
	public List<Arme> listerArmesRace() throws ServiceOlivBException {
		return serviceArme.listerArmesRace();
	}
	//lister les joueurs pour l'affichage
	@Override
	public List<Joueur> listerJoueurs() throws ServiceOlivBException {
		return serviceArme.listerJoueurs();
	}

	
	//-------------------------------------------------------------------------------------------- Anaïs
	
			/* ========================================== */ 
			/*  				PASSION					  */
			/* ========================================== */


	// Gestion Passion
	@Override
	public void addPassion(Passion passion) throws ExceptionService, UserExceptionRBC {
		facPassion.addPassion(passion);
	}

	@Override
	public void updatePassion(Passion passion) throws ExceptionService, UserExceptionRBC {
		facPassion.updatePassion(passion);
	}

	@Override
	public void delPassions() {
		facPassion.delPassions();
	}

	@Override
	public void delPassion(int refPassion) throws ExceptionService {
		facPassion.delPassion(refPassion);
	}

	@Override
	public void delPassion(String passion) throws ExceptionService {
		facPassion.delPassion(passion);
	}

	// Consultation passions

	@Override
	public List<Passion> getPassionsTrieNom() {
		return facPassion.getPassionsTrieNom();
	}

	@Override
	public List<Passion> getPassionsTrieRef() {
		return facPassion.getPassionsTrieRef();
	}

	@Override
	public Passion getPassion(int refPassion) throws ExceptionService {
		return facPassion.getPassion(refPassion);
	}

	@Override
	public Passion getPassion(String nom) throws ExceptionService {
		return facPassion.getPassion(nom);
	}

	@Override
	public List<Passion> getPassionsByLettres(String lettres) {
		
		return facPassion.getPassionsByLettres(lettres);
	}
		
	@Override
	public List<Race> getRaceLibre() {
		return facPassion.getRaceLibre();
	}
	
	/* ========================================== */ 
	/*  				MAGIE					  */
	/* ========================================== */

	// Gestion Mot de pouvoir fondamental

	@Override
	public void addMDPFond(MDPFondamental mDPvoirFond) throws ExceptionService {
		facMagie.addMDPFond(mDPvoirFond);
	}

	@Override
	public void updateMDPFond(MDPFondamental mDPvoirfond) throws ExceptionService {
		facMagie.updateMDPFond(mDPvoirfond);
	}

	@Override
	public void delMDPFonds() {
		facMagie.delMDPFonds();
	}

	@Override
	public void delMDPFond(int refMDPvoirFond) throws ExceptionService {
		facMagie.delMDPFond(refMDPvoirFond);
	}

	@Override
	public void delMDPFond(MDPFondamental mDPvoirfond) throws ExceptionService {
		facMagie.delMDPFond(mDPvoirfond);
	}


	@Override
	public void delMDPFond(String nom) throws ExceptionService {
		facMagie.delMDPFond(nom);
		
	}
	
	// Gestion mot de pouvoir Normal

	@Override
	public void addMDPNorm(MDPNormal mDPvoirNorm) throws ExceptionService {
		facMagie.addMDPNorm(mDPvoirNorm);
	}

	@Override
	public void updateMDPNorm(MDPNormal mDPvoirNorm) throws ExceptionService {
		facMagie.updateMDPNorm(mDPvoirNorm);
	}

	@Override
	public void delMDPNorms() {
		facMagie.delMDPNorms();
	}

	@Override
	public void delMDPNorm(int refMDPvoirNorm) throws ExceptionService {
		facMagie.delMDPNorm(refMDPvoirNorm);
	}

	@Override
	public void delMDPNorm(MDPNormal mDPvoirNorm) throws ExceptionService {
		facMagie.delMDPNorm(mDPvoirNorm);
	}

	@Override
	public void delMDPNorm(String nom) throws ExceptionService {
		facMagie.delMDPNorm(nom);
	}

	// Lister les concepts magie
	@Override
	public List<MDPNormal> getMDPNormalTrieNom() {

		return facMagie.getMDPNormalTrieNom();
	}

	@Override
	public List<MDPNormal> getMDPNormalTrieRef() {

		return facMagie.getMDPNormalTrieRef();
	}

	@Override
	public List<MDPFondamental> getMDPFondamentalTrieNom() {

		return facMagie.getMDPFondamentalTrieNom();
	}

	@Override
	public List<MDPFondamental> getMDPFondamentalTrieRef() {

		return facMagie.getMDPFondamentalTrieRef();
	}

	@Override
	public MDPFondamental getMDPFondamental(int refMDPvoirFond) throws ExceptionService {

		return facMagie.getMDPFondamental(refMDPvoirFond);
	}

	@Override
	public MDPFondamental getMDPFondamental(String nom) throws ExceptionService {

			return facMagie.getMDPFondamental(nom);
	}

	@Override
	public MDPNormal getMDPNormal(int refMDPvoirNorm) throws ExceptionService {

		return facMagie.getMDPNormal(refMDPvoirNorm);
	}

	@Override
	public MDPNormal getMDPNormal(String nom) throws ExceptionService {

		return facMagie.getMDPNormal(nom);
	}

	
}
