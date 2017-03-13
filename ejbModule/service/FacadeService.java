package service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import clientServeur.IFacadeService;
import service.competence.FacadeServiceCompetence;
import service.trait.TraitFacade;

/**
 * Cette classe implémente clientServeur.IFacadeService.class
 * 
 * Liste et aiguillage de toutes les méthodes disponibles pour le serveur web.
 * 
 * @author Groupe
 * @version 20170313
 *
 */
@Stateless
@Remote(IFacadeService.class)
public class FacadeService implements IFacadeService {

	// Attributs de classe
	@EJB
	private FacadeServiceCompetence servComp;
	@EJB
	private TraitFacade				servTrait;
	
}
