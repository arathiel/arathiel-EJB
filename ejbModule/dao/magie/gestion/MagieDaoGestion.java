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
	 * M�thode permettant d'ins�rer un mot de pouvoir fondamental
	 * 
	 * @author Ana�s
	 * @param motdePvoir
	 * 
	 */
	public void addMDPFond(MotDePouvoir motdePvoir){
		LOGGER.info("Dans m�thode addMDPFond");
		//TODO
	}
	
	/**
	 * M�thode permettant d'ins�rer un mot de pouvoir normal
	 * 
	 * @author Ana�s
	 * @param motdePvoir
	 * 
	 */
	public void addMDPNorm(MotDePouvoir motdePvoir){
		//TODO
	}
	
	/**
	 * M�thode permettant de modifier un mot de pouvoir fondamental
	 * @author Ana�s
	 * @param motDePvoir
	 */
	public void updateMDPFond(MotDePouvoir motDePvoir){
		//TODO
	}
	
	/**
	 * M�thode permettant de modifier un mot de pouvoir normal
	 * @param motDePvoir
	 */
	public void updateMDPNorm(MotDePouvoir motDePvoir){
		//TODO
	}
	

}
