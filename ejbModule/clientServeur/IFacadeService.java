package clientServeur;

import java.util.ArrayList;
import java.util.List;

import armurerie.Exception.ServiceOlivBException;
import clientServeur.exception.UserException;
import clientServeur.race_bonus_carac.userException.UserExceptionRBC;
import entity.armurerie.Arme;
import entity.armurerie.ArmeJoueur;
import entity.armurerie.Joueur;
//import entity.magie.MDPFondamental;
//import entity.magie.MDPNormal;
//import entity.passion.Passion;
import entity.competence.Competence;
import entity.race_bonus_carac.bonus.Bonus;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.race_bonus_carac.race.Race;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
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
//
//			public void addPassion(Passion passion) throws ServiceException;
//			
//			public void updatePassion(Passion passion) throws ServiceException;
//
//			public void delPassions();
//
//			public void delPassion(int refPassion) throws ServiceException;
//
//			public void delPassion(String nom) throws ServiceException;
//
//			public List<Passion> getPassionsTrieNom();
//
//			public List<Passion> getPassionsTrieRef();
//			
//			public Passion getPassion(int refPassion) throws ServiceException;
//
//			public Passion getPassion(String nom) throws ServiceException;
//	/* ========================================== */ 
//	/*  				Magie					  */
//	/* ========================================== */
//
//		public void addMDPFond(MDPFondamental mDPvoirfond) throws ServiceException;
//
//		public void addMDPNorm(MDPNormal mDPvoirNorm) throws ServiceException;
//
//
//		// modification
//		
//		public void updateMDPFond(MDPFondamental mDPvoirfond) throws ServiceException;
//
//		public void updateMDPNorm(MDPNormal mDPvoirNorm) throws ServiceException;
//
//
//		// suppression
//		
//		public void delMDPFonds();
//
//		public void delMDPFond(int refMDPvoirFond) throws ServiceException;
//
//		public void delMDPFond(MDPFondamental mDPvoirfond) throws ServiceException;
//		
//		public void delMDPFond(String nom) throws ServiceException;
//
//		public void delMDPNorms();
//
//		public void delMDPNorm(int refMDPvoirNorm) throws ServiceException;
//
//		public void delMDPNorm(MDPNormal mDPvoirNorm) throws ServiceException;
//
//		public void delMDPNorm(String nom) throws ServiceException;
//		
//
//		// liste
//				
//		public List<MDPNormal> getMDPNormalTrieNom();
//
//		public List<MDPNormal> getMDPNormalTrieRef();
//
//		public List<MDPFondamental> getMDPFondamentalTrieNom();
//
//		public List<MDPFondamental> getMDPFondamentalTrieRef();
//
//		// recherche
//
//		public MDPFondamental getMDPFondamental(int refMDPvoirFond) throws ServiceException;
//
//		public MDPFondamental getMDPFondamental(String nom) throws ServiceException;
//
//		public MDPNormal getMDPNormal(int refMDPvoirNorm) throws ServiceException;
//
//		public MDPNormal getMDPNormal(String nom) throws ServiceException;

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
	
	//Listes
	public List<Arme> listerArmesRace() throws ServiceOlivBException;
	
	public List<Joueur> listerJoueurs() throws ServiceOlivBException;
	
	public List<Race> listerRaces() throws ServiceOlivBException;
	
	public List<Arme> listerArmes() throws ServiceOlivBException;
	
	


}// Fin classe