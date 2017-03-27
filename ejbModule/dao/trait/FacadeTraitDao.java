package dao.trait;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.trait.consultation.Consult;
import dao.trait.exception.DoublonException;
import dao.trait.exception.IdNullException;
import dao.trait.exception.LibelleNullException;
import dao.trait.exception.LibelleVideException;
import dao.trait.exception.ObjetInexistantException;
import dao.trait.exception.ObjetNullException;
import dao.trait.gestionnaire.Admin;
import entity.caracteristique.Caracteristique;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
import technic.trait.Comportements;
import technic.trait.Traits;


/**
 * Classe controllant les flux DAO pour la fonctionnalité Trait
 * @author Jonathan Fuentes
 *
 */
@LocalBean
@Singleton
public class FacadeTraitDao {
	
	// Attributs de classe
	@EJB
	private Admin 			daoAdmin;	
	@EJB
	private Consult 		daoConsult;
	
	private Trait  			traitOut;
	private Traits 			listeTraitOut;	
	private Comportement	compOut;

	
	/* ========================================== */ 
	/*  				TRAIT					  */
	/* ========================================== */
	
	// Administration
	
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
		daoAdmin.addTrait(trait);
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
		daoAdmin.updateTrait(trait);
	}

	/**
	 * Supprime un trait de la DAO
	 * @throws ObjetNullException 
	 * @throws IdNullException 
	 * @throws AucunTraitException 
	 */
	public void supprimerTrait(int id) throws ObjetNullException, IdNullException {
		daoAdmin.deleteTrait(id);

	}
	
	/**
	 * Vide la table de Trait
	 * @throws AucunTraitException 
	 */
	public void reinitialiserTrait() {
		daoAdmin.deleteAllTrait();
	}

	//Consultation
	/**
	 * Retourne un trait via son id
	 * @throws ObjetInexistantException 
	 * @throws IdNullException 
	 * @throws AucunTraitException 
	 */
	public Trait consulterTraitById(int id) throws IdNullException, ObjetInexistantException {
		//Réinitialisation
		traitOut = null;
		
		//Recherche du trait
		traitOut = daoConsult.getTraitById(id);
		
		//Si le trait n'est pas null, on le convertit en objet prêt pour la couche service
		if (traitOut != null) traitOut = traitOut.getDto();
		else {
			traitOut = null;
		};
		return traitOut;
	}

	/**
	 * Retourne un trait via son libellé
	 * @throws ObjetInexistantException 
	 * @throws LibelleNullException 
	 * @throws IdNullException 
	 * @throws AucunTraitException 
	 */
	public Trait consulterTraitByLib(String libelle) throws ObjetInexistantException, LibelleNullException {	
		//Réinitialisation
		traitOut = null;
		
		//Recherche du trait
		traitOut = daoConsult.getTraitByLib(libelle);
	
		//Si non null, on le convertir pour la couche service
		if (traitOut != null) traitOut = traitOut.getDto();

		return traitOut;
	}
	
	/**
	 * Retourne la liste des traits
	 */
	public Traits consulterListTrait() {
		listeTraitOut = new Traits();
		
		for (Trait t : daoConsult.getAllTrait()) {   
			listeTraitOut.add(t.getDto());
		}
		
		return listeTraitOut;
	}

	
	/* ========================================== */ 
	/*  			COMPORTEMENT				  */
	/* ========================================== */
	
	//Administration
	
	/**
	 * Persiste un comporement
	 * @param comportement
	 * @throws ObjetNullException 
	 * @throws LibelleNullException 
	 * @throws LibelleVideException 
	 * @throws DoublonException
	 * @throws IdNullException
	 */
	public void ajouterComp(Comportement comportement) throws DoublonException, LibelleVideException, LibelleNullException, ObjetNullException {
		daoAdmin.addComp(comportement);
	}

	/**
	 * Met à jour un comportement
	 * @param comportement
	 * @throws IdNullException
	 * @throws ObjetInexistantException
	 */
	public void modifierComp(Comportement comportement) throws IdNullException, ObjetInexistantException {
		daoAdmin.updateComp(comportement);
	}

	/**
	 * Supprime un comportement de la BDD
	 * @param id
	 * @throws ObjetInexistantException
	 * @throws IdNullException
	 */
	public void supprimerComp(int id) throws ObjetInexistantException, IdNullException {
		daoAdmin.deletecomp(id);
	}
	
	/**
	 * Vide les tables de comportement
	 * @throws ObjetInexistantException
	 */
	public void reinitialiserComp() throws ObjetInexistantException {
		daoAdmin.deleteAllComp();
	}

	//Consultation
	/**
	 * Retourne un comportement via son ID
	 * @param id
	 * @return
	 * @throws IdNullException
	 * @throws ObjetInexistantException 
	 */
	public Comportement consulterCompById(int id) throws IdNullException, ObjetInexistantException {
		return daoConsult.getCompById(id);
	}

	/**
	 * Retourne un comportement via son libellé
	 * @param libelle
	 * @return
	 * @throws IdNullException
	 * @throws LibelleVideException 
	 */
	public Comportement consulterCompByLib(String libelle) throws IdNullException, LibelleVideException {
		try {
			compOut = daoConsult.getCompByLib(libelle);
		} catch (ObjetInexistantException e) {
		}
		return compOut;
	}

	/**
	 * Retourne la liste complète des compotements de la BDD
	 * @return
	 */
	public Comportements consulterListComp() {
		return daoConsult.getAllComp();
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
		return daoConsult.getCarByLib(id);
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
		return daoConsult.getCarByLib(nomCarac);
	}
	
	/**
	 * Retourne la liste complète des caractéristiques de la BDD
	 * @return
	 */
	public ArrayList<Caracteristique> getAllCar() {
		return daoConsult.getAllCar();
	}

}// Fin classe
