package dao.trait;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.trait.consultation.TraitDaoConsult;
import dao.trait.gestionnaire.TraitDaoAdmin;

/**
 * Classe controllant les flux DAO pour la fonctionnalité Trait
 * @author Jonathan Fuentes
 * @version 1
 *
 */
@LocalBean
@Singleton
public class TraitFacade {
	
	// Attributs de classe
	@EJB
	private TraitDaoAdmin 	daoAdmin;
	@EJB
	private TraitDaoConsult daoConsult;
	
	

}
