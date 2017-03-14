package dao.trait.gestionnaire;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.trait.Trait;

/**
 * Classe permmetant de faire les requêtes DAO d'adminitration d'un Trait
 * @author Jonathan Fuentes
 *
 */
@LocalBean
@Singleton
public class TraitDaoAdmin {

	
	// Attribut de classe
	@PersistenceContext()
	private EntityManager em;

	
	/**
	 * Permet de persister un trait dans la BDD
	 * @param trait
	 */
	public void add(Trait trait) {
	}


}// Fin classe
