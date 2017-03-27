package dao.trait.consultation;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import dao.trait.exception.IdNullException;
import dao.trait.exception.LibelleNullException;
import dao.trait.exception.ObjetInexistantException;
import dao.trait.ressources.Erreur;
import dao.util.Parameter;
import entity.caracteristique.Caracteristique;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
import technic.trait.Comportements;
import technic.trait.Traits;

/**
 * Classe permmetant de faire les requ�tes de consultation de Trait
 * @author Jonathan Fuentes
 *
 */
@LocalBean
@Singleton
public class Consult {

	//Attribut de classe
	@PersistenceContext(unitName = Parameter.UNITNAME_JUNONARATHIEL)
	private EntityManager em;
	
	private Trait 			traitHib;
	private Comportement	compHib;

	
	/* ========================================== */ 
	/*  				TRAIT					  */
	/* ========================================== */
	
	/**
	 * Retourne le trait correspondant � l'ID renseign�
	 * @param id
	 * @return
	 * @throws IdNullException 
	 * @throws ObjetInexistantException 
	 * @throws AucunTraitException 
	 */
	public Trait getTraitById(int id) throws IdNullException, ObjetInexistantException {
		//R�initialisation
		traitHib = null;
		
		//V�rification de la valeur envoy�e
		if (id != 0) traitHib = em.find(Trait.class, id);
		
		//Si vide, on l�ve une exception
		else {
			throw new IdNullException(Erreur.TRAIT);
		}
		
		//Si ne correspond � aucun trait existant, on l�ve une exception
		if (traitHib == null) throw new ObjetInexistantException(Erreur.TRAIT);
				
		return traitHib;
	}

	/**
	 * Retourne le trait correspondant au libell� renseign�
	 * @param libelle
	 * @return
	 * @throws ObjetInexistantException 
	 * @throws AucunTraitException 
	 * @throws IdNullException 
	 * @throws LibelleNullException 
	 */
	public Trait getTraitByLib(String libelle) throws ObjetInexistantException, LibelleNullException {
		//R�initialisation
		traitHib = null;
		
		if (libelle != null) {
			 
			if (libelle.isEmpty() != true) {
				// Recherche dans la BDD
				try {
					traitHib = (Trait) em.createNamedQuery("getTraitByLib")
								 	 	 .setParameter("lib", libelle)
								 	 	 .getSingleResult();
				}
				catch (PersistenceException e) {
					if (e instanceof javax.persistence.NoResultException) 
						throw new ObjetInexistantException(Erreur.TRAIT);						
				}	
			}
		}
		else {
			throw new LibelleNullException(Erreur.TRAIT);
		}

		return traitHib;
	}
	
	/**
	 * Retourne la liste compl�te des trait ordonn�e par id
	 * @return
	 */
	public Traits getAllTrait() {
		Traits list = new Traits();
		
		for (Object o : em.createNamedQuery("getAllTrait").getResultList()) {   
			if (o instanceof Trait) list.add((Trait)o);
		}
		return list;
	}
	
	
	/* ========================================== */ 
	/*  			COMPORTEMENT				  */
	/* ========================================== */
	
	
	/**
	 * Retourne le comportement correspondant � l'ID renseign�
	 * @param id
	 * @return
	 * @throws IdNullException 
	 */
	public Comportement getCompById(int id) throws IdNullException {
		//R�initialisation
		compHib = null;
		
		if (id != 0) compHib = em.find(Comportement.class, id);
		
		else {
			throw new IdNullException(Erreur.COMP);
		}
		return compHib;
	}

	/**
	 * Retourne le comportement correspondant au libell� renseign�
	 * @param libelle
	 * @return
	 * @throws ObjetInexistantException 
	 * @throws IdNullException 
	 */
	public Comportement getCompByLib(String libelle) throws ObjetInexistantException, IdNullException {
		//R�initialisation
		compHib = null;
		
		if (libelle != null) {
			if(libelle.isEmpty() != true) {
				try {
					compHib = (Comportement) em.createNamedQuery("getCompByLib")
											   .setParameter("lib", libelle)
											   .getSingleResult(); 
				}
				catch (PersistenceException e) {
					if (e instanceof javax.persistence.NoResultException) throw new ObjetInexistantException(Erreur.COMP);						
				}
			}
		}
		else {
			throw new IdNullException(Erreur.COMP);
		}
		
		return compHib;
	}
	
	/**
	 * Retourne la liste compl�te des comportements ordonn�e par id
	 * @return
	 */
	public Comportements getAllComp() {
		Comportements list = new Comportements();
		
		for (Object o : em.createNamedQuery("getAllComp").getResultList()) {   
			if (o instanceof Comportement) list.add((Comportement)o);
		}
		return list;
	}
	
	
	/* ========================================== */ 
	/*  			CARACTERISTIQUE				  */
	/* ========================================== */
	
	// J'ai cod� ces deux m�thodes par besoin, bien que ce ne soit pas ma fonctionnalit�. M�thode sans contr�les et incompl�tes
	
	/**
	 * Retourne une Caract�ristique via le nom (Aucun contr�le)
	 * @param nomCarac
	 * @return
	 * @author Jonathan
	 */
	public Caracteristique getCarByLib(String nomCarac){
		Caracteristique car = null;
		
		if (nomCarac != null) {
			if (nomCarac.isEmpty() != true) {
				car = (Caracteristique) em.createNamedQuery("getCarByLib")
		 		  	  	  				  .setParameter("lib", nomCarac)
		 		  	  	  				  .getSingleResult(); 
			}
		}
			
		return car;
	}
	
	
	/**
	 * Retourne la liste des Caract�ristiques (Aucun contr�le)
	 * @return
	 */
	public ArrayList<Caracteristique> getAllCar() {
		ArrayList<Caracteristique> list = new ArrayList<Caracteristique>();
		
		for (Object o : em.createNamedQuery("getAllCar").getResultList()) {   
			if (o instanceof Caracteristique) list.add((Caracteristique)o);
		}
		return list;
	}
	
}// Fin classe
