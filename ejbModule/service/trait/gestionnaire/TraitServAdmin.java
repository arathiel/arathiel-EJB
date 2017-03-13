package service.trait.gestionnaire;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.trait.TraitFacade;

/**
 * Classe de service d'administration pour la fonctionnalité Trait
 * @author Jonathan Fuentes
 *
 */
@LocalBean
@Singleton
public class TraitServAdmin {
	
	// Attribut de classe
	@EJB
	private TraitFacade daoFacade;

}
