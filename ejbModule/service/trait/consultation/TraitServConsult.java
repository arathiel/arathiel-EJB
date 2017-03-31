package service.trait.consultation;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.FacadeDAO;
import dao.trait.exception.IdNullException;
import dao.trait.exception.LibelleNullException;
import dao.trait.exception.LibelleVideException;
import dao.trait.exception.ObjetInexistantException;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
import technic.trait.Comportements;
import technic.trait.Traits;

/**
 * Classe de service de consultation pour la fontionnalité Trait
 * @author jonathan Fuentes
 *
 */
@LocalBean
@Singleton
public class TraitServConsult {
	
	// Attribut de classe
	@EJB
	private FacadeDAO daoFacade;

	/* ========================================== */ 
	/*  				TRAIT					  */
	/* ========================================== */
	
	/**
	 * Retourne un trait via son id
	 * @throws ObjetInexistantException 
	 * @throws IdNullException 
	 * @throws AucunTraitException 
	 */
	public Trait consulterTraitById(int id) throws IdNullException, ObjetInexistantException  {
		return daoFacade.consulterTraitById(id);
	}

	/**
	 * Retourne un trait via son libellé
	 * @throws ObjetInexistantException 
	 * @throws LibelleNullException 
	 * @throws IdNullException 
	 * @throws AucunTraitException 
	 */
	public Trait consulterTraitByLib(String libelle) throws ObjetInexistantException, LibelleNullException {	
		return daoFacade.consulterTraitByLib(libelle);
	}
	
	/**
	 * Retourne la liste des traits
	 */
	public Traits consulterListTrait() {
		return daoFacade.consulterListTrait();
	}
	
	/**
	 * Retourne la liste des trait d'après libellé
	 * @return
	 */
	public Traits consulterListTraitByLib(String libelle) {
		return daoFacade.consulterListTraitByLib(libelle);
	}
	
	/* ========================================== */ 
	/*  			COMPORTEMENT				  */
	/* ========================================== */
	
	/**
	 * Retourne un comportement via son ID
	 * @param id
	 * @return
	 * @throws IdNullException
	 * @throws ObjetInexistantException 
	 */
	public Comportement consulterCompById(int id) throws IdNullException, ObjetInexistantException {
		return daoFacade.consulterCompById(id);
	}

	/**
	 * Retourne un comportement via son libellé
	 * @param libelle
	 * @return
	 * @throws IdNullException
	 * @throws LibelleVideException 
	 */
	public Comportement consulterCompByLib(String libelle) throws LibelleVideException, LibelleNullException, ObjetInexistantException {
		return daoFacade.consulterCompByLib(libelle);
	}

	/**
	 * Retourne la liste complète des compotements de la BDD
	 * @return
	 */
	public Comportements consulterListComp() {
		return daoFacade.consulterListComp();
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
		return daoFacade.getCarByLib(id);
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
		return daoFacade.getCarByLib(nomCarac);
	}
	
	/**
	 * Retourne la liste complète des caractéristiques de la BDD
	 * @return
	 */
	public ArrayList<Caracteristique> getAllCar() {
		return daoFacade.getAllCar();
	}
	
}
