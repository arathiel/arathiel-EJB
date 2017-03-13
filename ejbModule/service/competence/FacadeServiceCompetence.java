package service.competence;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import service.competence.consultation.CompetenceConsultationService;
import service.competence.gestionnaire.CompetenceGestionnaireService;

/**
 * Cette classe liste toutes les m�thodes disponibles pour le module Comp�tence.
 * 
 * Elle redirige vers les classes de gestion ou de consultation du module Comp�tence.
 * Elle est instanci�e par le serveur d'application � l'appel de service.FacadeService.class
 * Une fois cr��, le m�me objet est utilis�.
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
