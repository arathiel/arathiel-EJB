package dao;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import clientServeur.exception.UserException;
import dao.competence.FacadeDaoCompetence;
import dao.magie.FacadeDaoMagie;
import dao.passion.FacadeDaoPassion;
import dao.trait.FacadeTraitDao;
import dao.trait.exception.DoublonException;
import dao.trait.exception.IdNullException;
import dao.trait.exception.LibelleNullException;
import dao.trait.exception.LibelleVideException;
import dao.trait.exception.ObjetInexistantException;
import dao.trait.exception.ObjetNullException;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
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
	@EJB
	private FacadeDaoMagie 			facDaoMagie;
	@EJB
	private FacadeDaoPassion 		facDaoPassion;
	
	
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
	 */
	public void supprimerTrait(int id) throws ObjetNullException, IdNullException {
		daoTrait.supprimerTrait(id);
	}
	
	/**
	 * Vide la table de trait de la BDD
	 * 
	 * @throws UserException
	 */
	public void reinitialiserTrait() {
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
	 */
	public Comportement consulterCompByLib(String libelle) throws IdNullException, LibelleVideException  {
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
	
}
