package dao.magie.gestion;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.magie.MotDePouvoir;

@LocalBean
@Singleton
public class MagieDaoGestion {

	@PersistenceContext()
	EntityManager em;

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Méthode permettant d'insérer un mot de pouvoir fondamental
	 * 
	 * @author Anaïs
	 * @param motdePvoir
	 * 
	 */
	public void addMDPFond(MotDePouvoir motdePvoir){
		LOGGER.info("Dans méthode addMDPFond");
		//TODO
	}
	
	/**
	 * Méthode permettant d'insérer un mot de pouvoir normal
	 * 
	 * @author Anaïs
	 * @param motdePvoir
	 * 
	 */
	public void addMDPNorm(MotDePouvoir motdePvoir){
		//TODO
	}
	
	/**
	 * Méthode permettant de modifier un mot de pouvoir fondamental
	 * @author Anaïs
	 * @param motDePvoir
	 */
	public void updateMDPFond(MotDePouvoir motDePvoir){
		//TODO
	}
	
	/**
	 * Méthode permettant de modifier un mot de pouvoir normal
	 * @param motDePvoir
	 */
	public void updateMDPNorm(MotDePouvoir motDePvoir){
		//TODO
	}
	

}
