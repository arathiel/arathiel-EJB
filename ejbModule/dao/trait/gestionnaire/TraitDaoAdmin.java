package dao.trait.gestionnaire;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Classe permmetant de faire les requêtes d'adminitration d'un Trait
 * @author Jonathan Fuentes
 *
 */
@LocalBean
@Singleton
public class TraitDaoAdmin {

	
	// Attribut de classe
	@PersistenceContext()
	private EntityManager em;




}// Fin classe
