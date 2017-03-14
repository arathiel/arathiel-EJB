package dao.passion.gestion;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entity.passion.Passion;

/**
 * Classe permettant l'accès à la base de données pour les méthodes de gestion des passions
 * Elle comprend les méthodes Créer - Modifier - Supprimer
 * 
 * @author Anaïs
 *
 */
@LocalBean
@Singleton
public class PassionDaoGestion {

	@PersistenceContext()
	EntityManager em;
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	/**
	 * Méthode permettant d'insérer une passion
	 * 
	 * @author Anaïs
	 * @param passion
	 */
	public void addPassion(Passion passion){
		//TODO
	}
	

	
}
