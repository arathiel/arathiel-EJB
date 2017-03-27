package service;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import clientServeur.IFacadeService;
import clientServeur.exception.UserException;
import dao.trait.exception.IdNullException;
import dao.trait.exception.LibelleNullException;
import dao.trait.exception.LibelleVideException;
import dao.trait.exception.ObjetInexistantException;
import dao.trait.ressources.Erreur;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
import service.competence.FacadeServiceCompetence;
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
	
}
