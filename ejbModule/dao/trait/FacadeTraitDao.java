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
import dao.trait.exception.ObjetUtiliseException;
import dao.trait.gestionnaire.Admin;
import entity.race_bonus_carac.caracteristique.Caracteristique;
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
	private Admin 						daoAdmin;	
	@EJB
	private Consult 					daoConsult;
	
	private Trait  						traitOut;
	private Traits 						listeTraitOut;	
	private Comportement				compOut;
	private Comportements				listCompOut;
	private Caracteristique 			carOut;
	private ArrayList<Caracteristique>	listCarOut;

	
	/* ========================================== */ 
	/*  				TRAIT					  */
	/* ========================================== */
	
	// Administration
	
	/**
	 * Persiste un trait dans DAO
	 * @throws ObjetNullException 
	 * @throws DoublonException  
	 * @throws LibelleNullException 
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
	 * @throws LibelleVideException 
	 * @throws IdNullException 
	 */
	public void modiferTrait(Trait trait) throws ObjetInexistantException, IdNullException, LibelleNullException, ObjetNullException, LibelleVideException {
		daoAdmin.updateTrait(trait);
	}

	/**
	 * Supprime un trait de la DAO
	 * @throws ObjetNullException 
	 * @throws IdNullException 
	 * @throws ObjetUtiliseException 
	 */
	public void supprimerTrait(int id) throws ObjetNullException, IdNullException, ObjetUtiliseException {
		daoAdmin.deleteTrait(id);

	}
	
	/**
	 * Vide la table de Trait
	 * @throws ObjetUtiliseException 
	 * @throws AucunTraitException 
	 */
	public void reinitialiserTrait() throws ObjetUtiliseException {
		daoAdmin.deleteAllTrait();
	}

	//Consultation
	/**
	 * Retourne un trait via son id
	 * @throws ObjetInexistantException 
	 * @throws IdNullException 
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
	
	/**
	 * Retourne la liste des traits d'après le libellé
	 */
	public Traits consulterListTraitByLib(String libelle) {
		listeTraitOut = new Traits();
		
		for (Trait t : daoConsult.getAllTraitByLib(libelle)) {   
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
		compOut = null;
		
		compOut = daoConsult.getCompById(id);
		
		return compOut;
	}

	/**
	 * Retourne un comportement via son libellé
	 * @param libelle
	 * @return
	 * @throws IdNullException
	 * @throws LibelleVideException 
	 * @throws LibelleNullException 
	 * @throws ObjetInexistantException 
	 */
	public Comportement consulterCompByLib(String libelle) throws LibelleVideException, LibelleNullException, ObjetInexistantException {
		compOut = null;

		compOut = daoConsult.getCompByLib(libelle);

		
		return compOut;
	}

	/**
	 * Retourne la liste complète des compotements de la BDD
	 * @return
	 */
	public Comportements consulterListComp() {
		listCompOut = new Comportements();
		
		listCompOut = daoConsult.getAllComp();
		
		return listCompOut;
	}
	
	/**
	 * Retourne la liste complète des CompCaracteristique de la BDD
	 * @return
	 */
	public Comportements consulterListCompCar() {
		listCompOut = new Comportements();
		
		listCompOut = daoConsult.getAllCompCar();
		
		return listCompOut;
	}
	
	/**
	 * Retourne la liste complète des CompRoleplay de la BDD
	 * @return
	 */
	public Comportements consulterListCompRP() {
		listCompOut = new Comportements();
		
		listCompOut = daoConsult.getAllComRP();
		
		return listCompOut;
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
		carOut = null;
		
		carOut = daoConsult.getCarByLib(id);
		
		return carOut;
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
		carOut = null;
		
		carOut = daoConsult.getCarByLib(nomCarac);
		
		return carOut;
	}
	
	/**
	 * Retourne la liste complète des caractéristiques de la BDD
	 * @return
	 */
	public ArrayList<Caracteristique> getAllCar() {
		listCarOut = new ArrayList<Caracteristique>();
		
		listCarOut = daoConsult.getAllCar();
		
		return listCarOut;
	}

}// Fin classe
