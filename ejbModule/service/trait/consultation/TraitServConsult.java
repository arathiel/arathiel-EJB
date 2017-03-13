package service.trait.consultation;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.trait.TraitFacade;

/**
 * Classe de service de consultation pour la fontionnalité Trait
 * @author jonathan Fuentes
 *
 */
@LocalBean
@Singleton
public class TraitServConsult {
	
	// Attribut de classe
	@EJB
	private TraitFacade daoFacade;

}
