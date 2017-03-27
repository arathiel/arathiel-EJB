package dao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

import clientServeur.IFacadeService;
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
	 */
	public Comportement consulterCompById(int id) throws IdNullException  {
		return daoTrait.consulterCompById(id);
	}
	
	/**
	 * Retourne un comportement via son libell�
	 * 
	 * @param libelle
	 * @return
	 * @throws IdNullException 
	 */
	public Comportement consulterCompByLib(String libelle) throws IdNullException  {
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
	
	
}
