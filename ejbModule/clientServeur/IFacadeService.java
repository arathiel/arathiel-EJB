package clientServeur;

import clientServeur.exception.UserException;
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
	public Comportements consulterListComp	();


	//-------------------------------------------------------------------------------------------- SUIVANT
	
	
	
}// Fin classe