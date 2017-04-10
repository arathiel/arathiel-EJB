package clientServeur;

import java.util.ArrayList;
import java.util.List;

import clientServeur.exception.ExceptionService;
import clientServeur.exception.UserException;
import clientServeur.race_bonus_carac.userException.UserExceptionRBC;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import entity.armurerie.Arme;
import entity.armurerie.ArmeJoueur;
import entity.armurerie.Joueur;
//import entity.magie.MDPFondamental;
//import entity.magie.MDPNormal;
//import entity.passion.Passion;
import entity.competence.Competence;
import entity.magie.MDPFondamental;
import entity.magie.MDPNormal;
import entity.passion.Passion;
import entity.race_bonus_carac.bonus.Bonus;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.race_bonus_carac.race.Race;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
import service.armurerie.exception.ServiceOlivBException;
import technic.trait.Comportements;
import technic.trait.Traits;

/**
 * Interface pour service.FacadeService.class
 * 
 * Contient la liste de toutes les méthodes accessibles depuis le serveur web.
 * 
 * @author Groupe
 * @version 2017/03/13
 *
 */

public interface IFacadeService {

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
	public void ajouterTrait			(Trait trait) 		throws UserException;
	
	/**
	 * Modifie un trait
	 * @param trait
	 * @throws UserException
	 */
	public void modiferTrait			(Trait trait) 		throws UserException;
	
	/**
	 * Supprime un trait
	 * @param id
	 * @throws UserException
	 */
	public void supprimerTrait			(int id) 			throws UserException;
	
	/**
	 * Supprime tous les traits
	 * @throws UserException
	 */
	public void reinitialiserTrait		() 					throws UserException;
	
	
	//Consultation
	
	/**
	 * Retourne un trait via son ID
	 * @param id
	 * @return
	 * @throws UserException
	 */
	public Trait  consulterTraitById	(int id) 			throws UserException;
	
	/**
	 * Retourne un trait via son libellé
	 * @param libelle
	 * @return
	 * @throws UserException
	 */
	public Trait  consulterTraitByLib	(String libelle) 	throws UserException;
	
	/**
	 * Retourne la liste complete de tous les traits
	 * @return
	 */
	public Traits consulterListTrait	();
	
	/**
	 * Retourne la liste des trait d'après libellé
	 * @return
	 */
	public Traits consulterListTraitByLib(String libelle);
	
	
	/* ========================================== */ 
	/*  			COMPORTEMENT				  */
	/* ========================================== */
	
	// Administration
	
	/**
	 * Ajoute un comportement
	 * @param comportement
	 * @throws UserException
	 */
	public void ajouterComp					(Comportement comportement) throws UserException;
	
	/**
	 * Modifie un comportement
	 * @param comportement
	 * @throws UserException
	 */
	public void modifierComp				(Comportement comportement) throws UserException;
	
	/**
	 * Supprime un comportement
	 * @param id
	 * @throws UserException
	 */
	public void supprimerComp				(int id) 					throws UserException;
	
	/**
	 * Supprime tous les comportements
	 * @throws UserException
	 */
	public void reinitialiserComp 			() 							throws UserException;
	
	
	//Consultation
	
	/**
	 * Retourne un comportement via son ID
	 * @param id
	 * @return
	 * @throws UserException
	 */
	public Comportement  consulterCompById	(int id) 					throws UserException;
	
	/**
	 * Retourne un comportement via son libellé
	 * @param libelle
	 * @return
	 * @throws UserException
	 */
	public Comportement  consulterCompByLib	(String libelle) 			throws UserException;
	
	/**
	 * Retourne la liste complète des comportements
	 * @return
	 */
	public Comportements consulterListComp();
	
	/**
	 * Retourne la liste complète des CompCaracteristique
	 * @return
	 */
	public Comportements consulterListCompCar();
	
	/**
	 * Retourne la liste complète des CompRoleplay
	 * @return
	 */
	public Comportements consulterListCompRP();
	
	
	//--------------------------------------------------------------------------------------------  Francois
	
	/* ========================================== */ 
	/*  				RACE					  */
	/* ========================================== */
	public void enregistrerRace (Race race) throws UserExceptionRBC;
	public void supprimerRace(Race race) throws UserExceptionRBC;
	public void modifierRace(Race race) throws UserExceptionRBC;
	public ArrayList<Race> listeToutesRaces();
	public ArrayList<Race> listeRacesJouables();
	public Race RechRaceParNom(String nom) throws UserExceptionRBC;
	public Race RechRaceParId(int id) throws UserExceptionRBC;
	
	
	/* ========================================== */ 
	/*  			CARACTERISTIQUE				  */
	/* ========================================== */
	public void insertBonus (Bonus bonus) throws UserExceptionRBC;
	public ArrayList<Bonus> listeTousBonus();
	public void deleteBonus(Bonus bonus) throws UserExceptionRBC;
	public Caracteristique rechCaracParId(String id);
		
	
	/* ========================================== */ 
	/*  				BONUS					  */
	/* ========================================== */
	public void insertCarac (Caracteristique carac);
	public void deleteCarac(Caracteristique carac);	
	public ArrayList<Caracteristique> listeCarac();
	
	public ArrayList<Competence> listeToutesComp();
	public Competence rechCompParId(int id);
	
	
	
	
	

	//-------------------------------------------------------------------------------------------- Anaïs
	
	/* ========================================== */ 
	/*  				Passion					  */
	/* ========================================== */

	// ajout
	public void addPassion(Passion passion) throws ExceptionService, UserExceptionRBC;

	// modification
	public void updatePassion(Passion passion) throws ExceptionService, UserExceptionRBC;

	// suppression
	public void delPassions();

	public void delPassion(int refPassion) throws ExceptionService;

	public void delPassion(String nom) throws ExceptionService;

	// liste
	public List<Passion> getPassionsTrieNom();

	public List<Passion> getPassionsByLettres(String lettres);
	
	public List<Passion> getPassionsTrieRef();
	
	public List<Race> getRaceLibre();

	// recherche
	public Passion getPassion(int refPassion) throws ExceptionService;

	public Passion getPassion(String nom) throws ExceptionService;


	

	/* ========================================== */ 
	/*  				Magie					  */
	/* ========================================== */

	//Ajout
	public void addMDPFond(MDPFondamental mDPvoirfond) throws ExceptionService;

	public void addMDPNorm(MDPNormal mDPvoirNorm) throws ExceptionService;
	
	//Modification
	public void updateMDPFond(MDPFondamental mDPvoirfond) throws ExceptionService;

	public void updateMDPNorm(MDPNormal mDPvoirNorm) throws ExceptionService;
	
	//Suppression
	public void delMDPFonds();

	public void delMDPFond(int refMDPvoirFond) throws ExceptionService;

	public void delMDPFond(MDPFondamental mDPvoirfond) throws ExceptionService;
	
	public void delMDPFond(String nom) throws ExceptionService;

	public void delMDPNorms();

	public void delMDPNorm(int refMDPvoirNorm) throws ExceptionService;

	public void delMDPNorm(MDPNormal mDPvoirNorm) throws ExceptionService;

	public void delMDPNorm(String nom) throws ExceptionService;
	
	//Liste
	
	public List<MDPNormal> getMDPNormalTrieNom();

	public List<MDPNormal> getMDPNormalTrieRef();

	public List<MDPFondamental> getMDPFondamentalTrieNom();

	public List<MDPFondamental> getMDPFondamentalTrieRef();

	//Recherche

	public MDPFondamental getMDPFondamental(int refMDPvoirFond) throws ExceptionService;

	public MDPFondamental getMDPFondamental(String nom) throws ExceptionService;

	public MDPNormal getMDPNormal(int refMDPvoirNorm) throws ExceptionService;

	public MDPNormal getMDPNormal(String nom) throws ExceptionService;
	
	
	//------------------------------------------------------------------------------OlivB
//	/* ========================================== */ 
//	/*  				Armurerie					  */
//	/* ========================================== */	
	//Création
	public void createArme(IArme arme, List<String> raceArme) throws ServiceOlivBException;
	
	public void createArmeJoueur(ArmeJoueur armeJoueurDto, int joueurId, int armeId, String etat) throws ServiceOlivBException;

	//Modification
	public void modifArme(IArme arme, List<String> raceArme) throws ServiceOlivBException;
	
	//Suprpession
	public void supprArme(IArme arme) throws ServiceOlivBException;
	
	//Recherche
	public Arme getArme(String nom);
	public Arme rechArme(int idArme);
	
	//Listes
	public List<Arme> listerArmesRace() throws ServiceOlivBException;
	
	public List<Joueur> listerJoueurs() throws ServiceOlivBException;
	
	public List<Race> listerRaces() throws ServiceOlivBException;
	
	public List<Arme> listerArmes() throws ServiceOlivBException;
	
	
	


}// Fin classe