package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import clientServeur.exception.UserException;
import dao.carriere.FacadeDaoCarriere;
import dao.carriere.exception.*;
import dao.carriere.exception.InexistantExceptionCarriere;
import dao.competence.FacadeDaoCompetence;
import dao.passionMagie.exception.DaoException;
import dao.passionMagie.magie.FacadeDaoMagie;
import dao.passionMagie.passion.FacadeDaoPassion;
import dao.race_bonus_carac.bonus.DaoBonus;
import dao.race_bonus_carac.carac.DaoCarac;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import dao.race_bonus_carac.race.FacadeDaoRace;
import dao.trait.FacadeTraitDao;
import dao.trait.exception.DoublonException;
import dao.trait.exception.IdNullException;
import dao.trait.exception.LibelleNullException;
import dao.trait.exception.LibelleVideException;
import dao.trait.exception.ObjetInexistantException;
import dao.trait.exception.ObjetNullException;
import dao.trait.exception.ObjetUtiliseException;
import entity.carriere.Carriere;
import entity.carriere.CategorieCarriere;
import entity.carriere.matiere.Matiere;
import entity.carriere.util.Carrieres;
import entity.carriere.util.CategorieCarrieres;
import entity.carriere.util.Matieres;
import entity.competence.Competence;
import entity.magie.MDPFondamental;
import entity.magie.MDPNormal;
import entity.passion.Passion;
import entity.race_bonus_carac.bonus.Bonus;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.race_bonus_carac.race.Race;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
import service.ressources.carriere.Erreur;
import technic.trait.Comportements;
import technic.trait.Traits;

/**
 * Cette classe est la facade de la couche DAO.
 * 
 * Liste et aiguillage des méthodes interragissant avec l'unité de persistance.
 * Elle est instanciée par le serveur d'application à l'appel des différentes classes du package service.
 * Une fois créé, le même objet est utilisé.
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


	//--------------------------------------- Francois
	@EJB
	private FacadeDaoRace	fDaoRace;

	@EJB
	private DaoBonus dBonus;

	@EJB
	private DaoCarac dCarac;

	@EJB
	private FacadeDaoCompetence dComp;


	//--------------------------------------- Anaïs
	@EJB
	private FacadeDaoMagie facDaoMagie;

	@EJB
	private FacadeDaoPassion facDaoPassion;

	//--------------------------------------- Ismaël

	@EJB
	FacadeDaoCarriere fDaoGestion;


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
	 * Retourne un trait via son libellé
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
	 * Retourne la liste complète de trait de la BDD
	 * @return
	 */
	public Traits consulterListTrait() {
		return daoTrait.consulterListTrait();
	}

	/**
	 * Retourne la liste des trait de la BDD d'après
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
	 * Retourne un comportement via son libellé
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
	 * Retourne la liste complète des comportements
	 * 
	 * @return
	 */
	public Comportements consulterListComp() {
		return daoTrait.consulterListComp();
	}

	/**
	 * Retourne la liste complète des CompCaracteristique
	 * 
	 * @return
	 */
	public Comportements consulterListCompCar() {
		return daoTrait.consulterListCompCar();
	}

	/**
	 * Retourne la liste complète des CompRoleplay
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
	 * Retourne une Caractéristique via le nom (Aucun contrôle)
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
	 * Retourne une Caractéristique via le nom (Aucun contrôle)
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
	 * Retourne la liste complète des caractéristiques de la BDD
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


	public Race rechRaceParNom(String nom) throws DaoExceptionRBC {
		return fDaoRace.rechRaceParNom(nom);
	}


	public Race rechRaceParId(int id) throws DaoExceptionRBC {
		return fDaoRace.rechRaceParId(id);
	}	


	//--------------------------------------------------------------------------------- Anaïs

	/* ========================================== */ 
	/*  				PASSION					  */
	/* ========================================== */

	public void addPassion(Passion passion) throws DaoException, DaoExceptionRBC {

		facDaoPassion.addPassion(passion);
	}

	public void addMDPFond(MDPFondamental mDPvoirFond) throws DaoException {

		facDaoMagie.addMDPFond(mDPvoirFond);

	}

	public void addMDPNorm(MDPNormal mDPvoirNorm) throws DaoException {

		facDaoMagie.addMDPNorm(mDPvoirNorm);

	}


	public void updatePassion(Passion passion) throws DaoException, DaoExceptionRBC {

		facDaoPassion.updatePassion(passion);

	}

	public void updateMDPFond(MDPFondamental mDPvoirfond) throws DaoException {

		facDaoMagie.updateMDPFond(mDPvoirfond);

	}

	public void updateMDPNorm(MDPNormal mDPvoirNorm) throws DaoException {

		facDaoMagie.updateMDPNorm(mDPvoirNorm);

	}


	public void delPassions() {

		facDaoPassion.delPassions();
	}

	public void delPassion(int refPassion) throws DaoException {

		facDaoPassion.delPassion(refPassion);
	}

	public void delPassion(String passion) throws DaoException {

		facDaoPassion.delPassion(passion);

	}

	public void delMDPFonds() {
		// TODO FAIRE METHODE!
		facDaoMagie.delMDPFonds();
	}

	public void delMDPFond(int refMDPvoirFond) throws DaoException {

		facDaoMagie.delMDPFond(refMDPvoirFond);
	}

	public void delMDPFond(MDPFondamental mDPvoirfond) throws DaoException {

		facDaoMagie.delMDPFond(mDPvoirfond);

	}

	public void delMDPFond(String nom) throws DaoException {

		facDaoMagie.delMDPFond(nom);

	}

	public void delMDPNorms() {
		facDaoMagie.delMDPNorms();
	}

	public void delMDPNorm(int refMDPvoirNorm) throws DaoException {

		facDaoMagie.delMDPNorm(refMDPvoirNorm);

	}

	public void delMDPNorm(MDPNormal mDPvoirNorm) throws DaoException {

		facDaoMagie.delMDPNorm(mDPvoirNorm);

	}

	public void delMDPNorm(String nom) throws DaoException {
		facDaoMagie.delMDPNorm(nom);

	}


	public List<Passion> getPassionsTrieNom() {

		return facDaoPassion.getPassionsTrieNom();
	}

	public List<Passion> getPassionsTrieRef() {

		return facDaoPassion.getPassionsTrieRef();
	}

	public List<Passion> getPassionsByLettres(String lettres) {
		return facDaoPassion.getPassionsByLettres(lettres);
	}	

	public List<MDPNormal> getMDPNormalTrieNom() {

		return facDaoMagie.getMDPNormalTrieNom();
	}

	public List<MDPNormal> getMDPNormalTrieRef() {

		return facDaoMagie.getMDPNormalTrieRef();
	}

	public List<MDPFondamental> getMDPFondamentalTrieNom() {

		return facDaoMagie.getMDPFondamentalTrieNom();
	}

	public List<MDPFondamental> getMDPFondamentalTrieRef() {

		return facDaoMagie.getMDPFondamentalTrieRef();
	}

	public Passion getPassion(int refPassion) throws DaoException {

		return facDaoPassion.getPassion(refPassion);
	}

	public Passion getPassion(String nom) throws DaoException {

		return facDaoPassion.getPassion(nom);
	}

	public MDPFondamental getMDPFondamental(int refMDPvoirFond) throws DaoException {

		return facDaoMagie.getMDPFondamental(refMDPvoirFond);

	}

	public MDPFondamental getMDPFondamental(String nom) throws DaoException {

		return facDaoMagie.getMDPFondamental(nom);
	}

	public MDPNormal getMDPNormal(int refMDPvoirNorm) throws DaoException {

		return facDaoMagie.getMDPNormal(refMDPvoirNorm);
	}

	public MDPNormal getMDPNormal(String nom) throws DaoException {

		return facDaoMagie.getMDPNormal(nom);
	}

	public List<Race> getRaceLibre() {

		return facDaoPassion.getRaceLibre();
	}


	// --------------------------------------------------------------------------- Ismaël


	/* ================================================= */
	/* 		Methode pour la gestion des carrieres		 */
	/* ================================================= */

	/**
	 * Methode d'ajout  d'une carriere
	 * @param carriere
	 * @return carriere
	 * @throws DoublonException 
	 * @throws InexistantExceptionCarriere 
	 */
	public Carriere ajouterCarriere(Carriere carriere) throws DaoExceptionCarriere
	{

		fDaoGestion.ajouterCarriere(carriere);

		return carriere;
	}

	/**
	 * Methode de modification
	 * @param carriere
	 * @return carriere
	 * @throws UserException 
	 * @throws InexistantExceptionCarriere 
	 * @throws DoublonException 
	 */
	public Carriere modifierCarriere(Carriere carriere) throws DaoExceptionCarriere
	{
		fDaoGestion.modifierCarriere(carriere);

		return carriere;
	}

	/**
	 * Methode de suppression d'une carriere
	 * @param carriere
	 * @throws InexistantExceptionCarriere 
	 */
	public void supprimerCarriere(Carriere carriere) throws DaoExceptionCarriere
	{	
			fDaoGestion.supprimerCarriere(carriere);
	}

	/**
	 * Methode de supperession d'une carriere par l'id
	 * @param id
	 * @throws InexistantExceptionCarriere 
	 */
	public void supprimerCarriereParId(int id) throws DaoExceptionCarriere
	{
			fDaoGestion.supprimerCarriereParId(id);
	}

	/**
	 *  Methode de suppression d'une carriere par nom
	 *  @param nom
	 * @throws InexistantExceptionCarriere 
	 */
	public void supprimerCarriereParParNom(String nom) throws DaoExceptionCarriere
	{
			fDaoGestion.supprimerCarriereParParNom(nom);
	}


	/**
	 * Methode d'affichage de la liste des carrieres
	 * @return lstCarrieres
	 */
	public Carrieres lstCarrieres() 
	{
		return fDaoGestion.lstCarrieres();
	}

	/***
	 * Methode de recherche d'une carriere par nom
	 * @param nom
	 * @return Carriere
	 */
	public Carriere recherCarParNom(String nom) throws DaoExceptionCarriere
	{
		return fDaoGestion.recherCarParNom(nom);
	}

	/**
	 * Methode de recherche d'une carriere par id
	 * @param idCarriere
	 * @return Carriere
	 */
	public Carriere recherCarParId(int idCarriere) throws DaoExceptionCarriere
	{
		return fDaoGestion.recherCarParId(idCarriere);
	}


	/* ================================================= */
	/* 		Methode pour la gestion des categories		 */
	/* ================================================= */	

	/**
	 * Methode d'ajout d'une categorie
	 * @param catCarriere
	 * @return cc
	 * @throws InexistantExceptionCarriere 
	 * @throws DoublonException 
	 */
	public CategorieCarriere ajouterCategorieCarriere(CategorieCarriere catCarriere) throws DaoExceptionCarriere
	{
		if(catCarriere != null)
		{		
			System.out.println("Entree hibernate : " + catCarriere);
			try 
			{
				fDaoGestion.ajouterCategorieCarriere(catCarriere);
			} 
			catch (InexistantExceptionCarriere e) 
			{
				throw new DaoExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());
			}
			catch (DoublonExceptionCarriere  e) 
			{
				throw new DaoExceptionCarriere(Erreur.CAT_DOUBLON.action(), Erreur.CAT_DOUBLON.getCode());
			}
			System.out.println("Dans hibernate : " + catCarriere);
			catCarriere = catCarriere.getDtoNoMatiere();
			System.out.println("Retour hibernate : " + catCarriere);
		}
		return catCarriere;

	}

	/**
	 *  Methode de modification d'une categorie
	 *  @param catCarriere
	 *  @return cc
	 * @throws InexistantExceptionCarriere 
	 * @throws DoublonException 
	 */
	public CategorieCarriere modifierCategorieCarriere(CategorieCarriere catCarriere) throws DaoExceptionCarriere
	{
		if(catCarriere != null)
		{
			try 
			{
				fDaoGestion.modifierCategorieCarriere(catCarriere);
				catCarriere = catCarriere.getDtoNoMatiere();
			} 
			catch (DoublonExceptionCarriere  e) 
			{
				throw new DaoExceptionCarriere(Erreur.CAT_DOUBLON.action(), Erreur.CAT_DOUBLON.getCode());
			} 
			catch (InexistantExceptionCarriere e)
			{
				throw new DaoExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());
			}

		}
		return catCarriere;
	}

	/**
	 *  Methode de suppression d'une categorie
	 *  @param catCarriere
	 * @throws InexistantExceptionCarriere 
	 */
	public void supprimerCategorieCarriere(CategorieCarriere catCarriere) throws DaoExceptionCarriere
	{
		try 
		{
			fDaoGestion.supprimerCategorieCarriere(catCarriere);
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());
		}
	}

	/**
	 *  Methode de suppression d'une categorie par nom
	 *  @param nom
	 * @throws InexistantExceptionCarriere 
	 */
	public void supprimerCategorieCarriereParNom(String nom) throws DaoExceptionCarriere
	{
		try 
		{
			fDaoGestion.supprimerCarriereParParNom(nom);
		}
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());
		}
	}

	/**
	 *  Methode de recuperation d'une liste de categorie
	 *  @return lstCategories
	 */
	public CategorieCarrieres lstCategorieCarrieres() 
	{
		CategorieCarrieres lstCategories = new CategorieCarrieres();
		for(CategorieCarriere catCarriere : fDaoGestion.lstCategorieCarrieres())
		{
			CategorieCarriere categorie = catCarriere.getDto();
			lstCategories.add(categorie);
		}
		return lstCategories;
	}

	/**
	 * Methode de recherche par Id d'une categorie
	 * @param idCategorieCarriere
	 * @return CategorieCarriere
	 */
	public CategorieCarriere recherchCategorieParId(int idCategorieCarriere) throws DaoExceptionCarriere
	{
		CategorieCarriere catCarriere = null ;
		try
		{
			catCarriere =  fDaoGestion.recherchCategorieParId(idCategorieCarriere);
			if(catCarriere != null) catCarriere = catCarriere.getDto();
		}
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());
		}


		return catCarriere;
	}

	/**
	 * Methode de recherche par Nom d'une categorie
	 * @param nom
	 * @return CategorieCarriere
	 */
	public CategorieCarriere recherchCategorieParNom(String nom) throws DaoExceptionCarriere
	{
		CategorieCarriere catCarriere = null;
		try
		{
			catCarriere = fDaoGestion.recherchCategorieParNom(nom);
		}
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.CAT_INEXISTANT.action(), Erreur.CAT_INEXISTANT.getCode());
		}


		if(catCarriere != null) catCarriere = catCarriere.getDto();

		return catCarriere;
	}


	/* ================================================= */
	/* 		Methode pour la gestion des matieres		 */
	/* ================================================= */

	/**
	 * Methode d'ajout d'une matiere
	 * @param matiere
	 * @return matiere
	 */
	public Matiere ajouterMatiere(Matiere matiere) throws DaoExceptionCarriere
	{
		if(matiere != null)
		{
			try
			{
				fDaoGestion.ajouterMatiere(matiere);
				matiere = matiere.getDto();	
			}
			catch (DoublonExceptionCarriere e) 
			{
				throw new DaoExceptionCarriere(Erreur.MAT_DOUBLON.action(), Erreur.MAT_DOUBLON.getCode());
			} 
			catch (InexistantExceptionCarriere e)
			{
				throw new DaoExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());
			}	
		}
		return matiere;
	}

	/**
	 * Methode de modification d'une matiere 
	 * @param matiere
	 * @return matiere
	 */
	public Matiere modifierMatiere(Matiere matiere) throws DaoExceptionCarriere
	{
		if(matiere != null)
		{
			fDaoGestion.modifierMatiere(matiere);
			matiere = matiere.getDto();
		}
		return matiere;
	}

	/**
	 * Methode de suppression d'une matiere 
	 * @param matiere
	 */
	public void supprimerMatiere(Matiere matiere) throws DaoExceptionCarriere
	{
		try 
		{
			fDaoGestion.supprimerMatiere(matiere);
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());
		}
	}

	/**
	 * Methode de suppression d'une matiere par le nom
	 * @param nomMatiere
	 */
	public void supprimerMatiereParNom(String nomMatiere) throws DaoExceptionCarriere
	{
		try 
		{
			fDaoGestion.supprimerCarriereParParNom(nomMatiere);
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());
		}	
	}

	/***
	 * Methode de recuperation d'une liste de matiere
	 * @return lstMatieres
	 */
	public Matieres lstMatieres()
	{
		Matieres lstMatieres = new Matieres();
		for(Matiere mat : fDaoGestion.lstMatieres())
		{
			Matiere matiere = mat.getDto();
			lstMatieres.add(matiere);
		}
		return lstMatieres;
	}

	/**
	 * Methode de recherche par Nom d'une Matiere
	 * @return Matiere
	 * @throws InexistantExceptionCarriere 
	 */
	public Matiere recherchMatiereParNom(String nomMatiere) throws DaoExceptionCarriere
	{
		Matiere matiere = null;
		try
		{
			matiere = fDaoGestion.recherchMatiereParNom(nomMatiere);
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());
		}
		if(matiere != null) matiere = matiere.getDto();

		return matiere;
	}

	/**
	 * Methode de recherche par Id d'une Matiere
	 * @return Matiere
	 * @throws InexistantExceptionCarriere 
	 */
	public Matiere recherchMatierParID(int id) throws DaoExceptionCarriere
	{
		Matiere matiere;
		try {
			matiere = fDaoGestion.recherchMatierParID(id);
			if( matiere != null) matiere = matiere.getDto();
		} 
		catch (InexistantExceptionCarriere e) 
		{
			throw new DaoExceptionCarriere(Erreur.MAT_INEXISTANT.action(), Erreur.MAT_INEXISTANT.getCode());
		}
		return matiere;
	}

	public void removeCarriereNative() 
	{
		fDaoGestion.removeCarriereNative();

	}

	public void removeCategorieCarriereNative() 
	{
		fDaoGestion.removeCategorieCarriereNative();
	}

	public void removeMatiereNative() 
	{
		fDaoGestion.removeMatiereNative();
	}






}
