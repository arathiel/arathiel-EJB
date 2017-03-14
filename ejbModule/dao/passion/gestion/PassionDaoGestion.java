package dao.passion.gestion;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entity.passion.Passion;

/**
 * Classe permettant l'acc�s � la base de donn�es pour les m�thodes de gestion des passions
 * Elle comprend les m�thodes Cr�er - Modifier - Supprimer
 * 
 * @author Ana�s
 *
 */
@LocalBean
@Singleton
public class PassionDaoGestion {

	@PersistenceContext()
	EntityManager em;
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	/**
	 * M�thode permettant d'ins�rer une passion
	 * 
	 * @author Ana�s
	 * @param passion
	 */
	public void addPassion(Passion passion){
		//TODO
	}
	

	
}
