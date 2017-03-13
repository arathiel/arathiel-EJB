package service.competence;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import service.competence.consultation.CompetenceConsultationService;
import service.competence.gestionnaire.CompetenceGestionnaireService;

/**
 * Cette classe liste toutes les méthodes disponibles pour le module Compétence.
 * 
 * Elle redirige vers les classes de gestion ou de consultation du module Compétence.
 * Elle est instanciée par le serveur d'application à l'appel de service.FacadeService.class
 * Une fois créé, le même objet est utilisé.
 * 
 * @author Claire
 * @version 20170313
 *
 */
@LocalBean
@Singleton
public class FacadeServiceCompetence {
	
	@EJB
	private CompetenceConsultationService consult;
	@EJB
	private CompetenceGestionnaireService gestion;

}
