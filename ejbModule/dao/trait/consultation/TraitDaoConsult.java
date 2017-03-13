package dao.trait.consultation;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Classe permmetant de faire les requêtes de consultation d'un Trait
 * @author Jonathan Fuentes
 *
 */
@LocalBean
@Singleton
public class TraitDaoConsult {

	// Attribut de classe
	@PersistenceContext()
	private EntityManager em;
}
