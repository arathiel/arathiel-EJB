package service.trait;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import service.trait.consultation.TraitServConsult;
import service.trait.gestionnaire.TraitServAdmin;

/**
 * Classe permettant d'aiguiller les flux de service pour la fonctionnalité Trait
 * @author Jonathan Fuentes
 *
 */
@LocalBean
@Singleton
public class TraitFacade {

	// Attributs de classe
	@EJB
	private TraitServAdmin		servAdmin;
	@EJB
	private TraitServConsult 	servConsult;
}
