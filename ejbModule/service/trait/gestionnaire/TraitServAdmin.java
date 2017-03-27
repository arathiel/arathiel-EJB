package service.trait.gestionnaire;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.FacadeDAO;
import dao.trait.exception.DoublonException;
import dao.trait.exception.IdNullException;
import dao.trait.exception.LibelleNullException;
import dao.trait.exception.LibelleVideException;
import dao.trait.exception.ObjetInexistantException;
import dao.trait.exception.ObjetNullException;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;


/**
 * Classe de service d'administration pour la fonctionnalité Trait
 * @author Jonathan Fuentes
 *
 */
@LocalBean
@Singleton
public class TraitServAdmin {
	
	// Attribut de classe
	@EJB
	private FacadeDAO daoFacade;

	
	/* ========================================== */ 
	/*  				TRAIT					  */
	/* ========================================== */
	
	/**
	 * Persiste un trait dans DAO
	 * @throws ObjetNullException 
	 * @throws DoublonException 
	 * @throws TraitNullException 
	 * @throws LibelleNullException 
	 * @throws IdNullException 
	 * @throws DoublonTraitException 
	 * @throws LibelleVideException 
	 */
	public void ajouterTrait(Trait trait) throws LibelleVideException, LibelleNullException, DoublonException, ObjetNullException {
		daoFacade.ajouterTrait(trait);
	}

	/**
	 * Modifie un trait de la DAO
	 * @throws ObjetNullException 
	 * @throws ObjetInexistantException 
	 * @throws LibelleNullException 
	 * @throws TraitNullException 
	 * @throws LibelleVideException 
	 * @throws IdNullException 
	 * @throws AucunTraitException 
	 * @throws DoublonTraitException 
	 */
	public void modiferTrait(Trait trait) throws ObjetInexistantException, IdNullException, LibelleNullException, ObjetNullException, LibelleVideException {
		daoFacade.modiferTrait(trait);
	}

	/**
	 * Supprime un trait de la DAO
	 * @throws ObjetNullException 
	 * @throws IdNullException 
	 * @throws AucunTraitException 
	 */
	public void supprimerTrait(int id) throws ObjetNullException, IdNullException {
		daoFacade.supprimerTrait(id);

	}
	
	/**
	 * Vide la table de Trait
	 * @throws AucunTraitException 
	 */
	public void reinitialiserTrait() {
		daoFacade.reinitialiserTrait();
	}
	
	/* ========================================== */ 
	/*  			COMPORTEMENT				  */
	/* ========================================== */
	
	/**
	 * Persiste un trait dans la BDD
	 * @param comportement
	 * @throws ObjetNullException 
	 * @throws LibelleNullException 
	 * @throws LibelleVideException 
	 * @throws DoublonException
	 * @throws IdNullException
	 */
	public void ajouterComp(Comportement comportement) throws DoublonException, LibelleVideException, LibelleNullException, ObjetNullException {
		daoFacade.ajouterComp(comportement);
	}

	/**
	 * Met à jour un trait de la BDD
	 * @param comportement
	 * @throws IdNullException
	 * @throws ObjetInexistantException
	 */
	public void modifierComp(Comportement comportement) throws IdNullException, ObjetInexistantException {
		daoFacade.modifierComp(comportement);
	}

	/**
	 * Supprime un trait de la BDD
	 * @param id
	 * @throws ObjetInexistantException
	 * @throws IdNullException
	 */
	public void supprimerComp(int id) throws ObjetInexistantException, IdNullException {
		daoFacade.supprimerComp(id);

	}
	
	/**
	 * Vide la table de trait
	 * @throws ObjetInexistantException
	 */
	public void reinitialiserComp() throws ObjetInexistantException {
		daoFacade.reinitialiserComp();
	}
}
