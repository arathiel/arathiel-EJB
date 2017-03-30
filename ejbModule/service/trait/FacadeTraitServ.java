package service.trait;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import clientServeur.exception.UserException;
import dao.trait.exception.DoublonException;
import dao.trait.exception.IdNullException;
import dao.trait.exception.LibelleNullException;
import dao.trait.exception.LibelleVideException;
import dao.trait.exception.ObjetInexistantException;
import dao.trait.exception.ObjetNullException;
import dao.trait.ressources.Erreur;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
import service.trait.consultation.TraitServConsult;
import service.trait.gestionnaire.TraitServAdmin;
import technic.trait.Comportements;
import technic.trait.Traits;

/**
 * Classe permettant d'aiguiller les flux de service pour la fonctionnalité Trait
 * 
 * Par manque de temps, je n'ai pas refait les test pour les paramètres envoyés.
 * Néanmoins, j'ai déjà fait les test dans la couche DAO. Avec plus de temps j'aurai blindé d'avantage mon code
 * dans la couche service.
 * 
 * @author Jonathan Fuentes
 *
 */
@LocalBean
@Singleton
public class FacadeTraitServ {

	// Attributs de classe
	@EJB
	private TraitServAdmin		servAdmin;
	@EJB
	private TraitServConsult 	servConsult;
	
	private Trait  				traitOut;
	private Comportement		compOut;
	private Caracteristique		carOut;
	
	
	/* ========================================== */ 
	/*  				TRAIT					  */
	/* ========================================== */
	
	// Administration
	
	/**
	 * Persiste un trait dans DAO
	 * @throws UserException 
	 */
	public void ajouterTrait(Trait trait) throws UserException  {
		
		try {
			
			servAdmin.ajouterTrait(trait);
			
		} catch (LibelleVideException | LibelleNullException | DoublonException | ObjetNullException e) {
			//Personalisation des messages d'erreur Utilisateur
			if (e instanceof LibelleVideException) {
				if (e.getType() == Erreur.TRAIT) 	throw new UserException(Erreur.TR_LIBVIDE.getMessage());
				if (e.getType() == Erreur.COMP)  	throw new UserException(Erreur.COMP_LIBVIDE.getMessage());
			}
			
			if (e instanceof LibelleNullException) {
				if (e.getType() == Erreur.TRAIT) 	throw new UserException(Erreur.TR_LIBNULL.getMessage());
				if (e.getType() == Erreur.COMP)  	throw new UserException(Erreur.COMP_LIBNULL.getMessage());
			}
			
			if (e instanceof DoublonException) 		throw new UserException(Erreur.TR_DOUBLON.getMessage());
			if (e instanceof ObjetNullException) 	throw new UserException(Erreur.TR_NULL.getMessage());
		}
	}

	/**
	 * Modifie un trait de la DAO
	 * @throws UserException 
	 */
	public void modiferTrait(Trait trait) throws UserException {
			
		try {
			
			servAdmin.modiferTrait(trait);
		
		} catch (ObjetInexistantException | IdNullException | LibelleNullException | ObjetNullException
				| LibelleVideException e) {

			//Personalisation des messages d'erreur Utilisateur
			if (e instanceof LibelleVideException) {
				if (e.getType() == Erreur.TRAIT) 		throw new UserException(Erreur.TR_LIBVIDE.getMessage());
				if (e.getType() == Erreur.COMP)  		throw new UserException(Erreur.COMP_LIBVIDE.getMessage());
			}
			
			if (e instanceof LibelleNullException) {
				if (e.getType() == Erreur.TRAIT) 		throw new UserException(Erreur.TR_LIBNULL.getMessage());
				if (e.getType() == Erreur.COMP)  		throw new UserException(Erreur.COMP_LIBNULL.getMessage());
			}
			
			if (e instanceof ObjetInexistantException) 	throw new UserException(Erreur.TR_INEXISTANT.getMessage());
			if (e instanceof ObjetNullException) 		throw new UserException(Erreur.TR_NULL.getMessage());
			if (e instanceof IdNullException)			throw new UserException(Erreur.TR_IDNULL.getMessage());
		}

		

	}

	/**
	 * Supprime un trait de la DAO
	 * @throws UserException 
	 */
	public void supprimerTrait(int id) throws UserException {
		
		try {
			
			servAdmin.supprimerTrait(id);
			
		} catch (ObjetNullException | IdNullException e) {
			//Personalisation des messages d'erreur Utilisateur
			if (e instanceof ObjetNullException) 		throw new UserException(Erreur.TR_NULL.getMessage());
			if (e instanceof IdNullException)			throw new UserException(Erreur.TR_IDNULL.getMessage());
		}
	}
	
	/**
	 * Vide la table de Trait
	 * @throws UserException 
	 */
	public void reinitialiserTrait() {
		servAdmin.reinitialiserTrait();
	}

	
	//Consultation
	
	/**
	 * Retourne un trait via son id
	 * @throws UserException 
	 */
	public Trait consulterTraitById(int id) throws UserException {
		//Réinitialisation
		traitOut = null;
		
		//Recherche du trait
		try {
			
			traitOut = servConsult.consulterTraitById(id);
		
		} catch (IdNullException | ObjetInexistantException e) {
			if (e instanceof IdNullException)			throw new UserException(Erreur.TR_IDNULL.getMessage());
			if (e instanceof ObjetInexistantException) 	throw new UserException(Erreur.TR_INEXISTANT.getMessage());
		}
 
		return traitOut;
	}

	/**
	 * Retourne un trait via son libellé
	 * @throws UserException 
	 */
	public Trait consulterTraitByLib(String libelle) throws UserException {	
		//Réinitialisation
		traitOut = null;
		
		//Recherche du trait
		try {
			
			traitOut = servConsult.consulterTraitByLib(libelle);
		
		} catch (ObjetInexistantException | LibelleNullException e) {
			if (e instanceof ObjetInexistantException) 	throw new UserException(Erreur.TR_INEXISTANT.getMessage());
			if (e.getType() == Erreur.TRAIT) 			throw new UserException(Erreur.TR_LIBNULL.getMessage());
		}

		return traitOut;
	}
	
	/**
	 * Retourne la liste des traits
	 */
	public Traits consulterListTrait() {
		return servConsult.consulterListTrait();
	}
	
	/**
	 * Retourne la liste des trait d'après libellé
	 * @return
	 */
	public Traits consulterListTraitByLib(String libelle) {
		return servConsult.consulterListTraitByLib(libelle);
	}

	
	/* ========================================== */ 
	/*  			COMPORTEMENT				  */
	/* ========================================== */
	
	public void ajouterComp(Comportement comportement) throws UserException {
		try {
			servAdmin.ajouterComp(comportement);
		} catch (DoublonException | LibelleVideException | LibelleNullException | ObjetNullException e) {
			if (e instanceof DoublonException) 		throw new UserException(Erreur.COMP_DOUBLON.getMessage());
			if (e instanceof LibelleNullException)	throw new UserException(Erreur.COMP_LIBNULL.getMessage());
			if (e instanceof ObjetNullException)	throw new UserException(Erreur.COMP_NULL.getMessage());
		}
	}

	public void modifierComp(Comportement comportement) throws UserException {
		try {
			servAdmin.modifierComp(comportement);
		} catch (IdNullException | ObjetInexistantException e) {
			if (e instanceof ObjetInexistantException) 	throw new UserException(Erreur.COMP_INEXISTANT.getMessage());
			if (e instanceof IdNullException)			throw new UserException(Erreur.COMP_IDNULL.getMessage());
		}
	}

	public void supprimerComp(int id) throws UserException {
		try {
			servAdmin.supprimerComp(id);
		} catch (ObjetInexistantException | IdNullException e) {
			if (e instanceof ObjetInexistantException) 	throw new UserException(Erreur.COMP_INEXISTANT.getMessage());
			if (e instanceof IdNullException)			throw new UserException(Erreur.COMP_IDNULL.getMessage());
		}
	}
	
	public void reinitialiserComp() throws UserException {
		try {
			servAdmin.reinitialiserComp();
		} catch (ObjetInexistantException e) {
			if (e instanceof ObjetInexistantException) 	throw new UserException(Erreur.COMP_INEXISTANT.getMessage());
		}
	}

	public Comportement consulterCompById(int id) throws UserException {
		compOut = null;
		
		try {
			compOut = servConsult.consulterCompById(id);
		} catch (IdNullException | ObjetInexistantException e) {
			if (e instanceof IdNullException)			throw new UserException(Erreur.COMP_IDNULL.getMessage());
			if (e instanceof ObjetInexistantException)	throw new UserException(Erreur.COMP_INEXISTANT.getMessage());
		}
		
		return compOut;
	}

	public Comportement consulterCompByLib(String libelle) throws UserException {
		compOut = null;
		
		try {
			compOut = servConsult.consulterCompByLib(libelle);
		} catch (IdNullException | LibelleVideException e) {
			if (e instanceof IdNullException)		throw new UserException(Erreur.COMP_LIBNULL.getMessage());
			if (e instanceof LibelleVideException)	throw new UserException(Erreur.COMP_LIBVIDE.getMessage());
		}
		return compOut;
	}

	public Comportements consulterListComp() {
		return servConsult.consulterListComp();
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
		carOut = null;
				
		try {
			carOut = servConsult.getCarByLib(id);
		} catch (IdNullException | ObjetInexistantException e) {
			if (e instanceof IdNullException)			throw new UserException(Erreur.CAR_IDNULL.getMessage());
			if (e instanceof ObjetInexistantException)	throw new UserException(Erreur.CAR_INEXISTANT.getMessage());
		}
				
		return carOut = null
;
	}
	
	/**
	 * Retourne une Caractéristique via le nom (Aucun contrôle)
	 * @param nomCarac
	 * @return
	 * @author Jonathan
	 * @throws UserException 
	 */
	public Caracteristique getCarByLib(String nomCarac) throws UserException {
		carOut = null;
		
		try {
			carOut = servConsult.getCarByLib(nomCarac);
		} catch (ObjetInexistantException | LibelleVideException | LibelleNullException e) {
			if (e instanceof LibelleVideException)		throw new UserException(Erreur.CAR_LIBVIDE.getMessage());
			if (e instanceof LibelleNullException)		throw new UserException(Erreur.CAR_LIBNULL.getMessage());
			if (e instanceof ObjetInexistantException)	throw new UserException(Erreur.CAR_INEXISTANT.getMessage());
		}
				
		return carOut;
	}
	
	/**
	 * Retourne la liste complète des caractéristiques de la BDD
	 * @return
	 */
	public ArrayList<Caracteristique> getAllCar() {
		return servConsult.getAllCar();
	}
}
