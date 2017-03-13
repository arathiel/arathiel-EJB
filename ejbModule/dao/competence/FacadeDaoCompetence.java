package dao.competence;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.competence.consultation.CompetenceConsultationDAO;
import dao.competence.gestionnaire.CompetenceGestionnaireDAO;

/**
 * Cette classe liste toutes les m�thodes interragissant avec l'unit� de persistance pour le module Comp�tence.
 * 
 * Elle redirige vers les classes de gestion ou de consultation de la couche DAO du module Comp�tence.
 * Elle est instanci�e par le serveur d'application � l'appel de dao.FacadeDAO.class
 * Une fois cr��, le m�me objet est utilis�.
 * 
 * @author Claire
 * @version 20170313
 *
 */
@LocalBean
@Singleton
public class FacadeDaoCompetence {
	
	@EJB
	private CompetenceConsultationDAO cd;
	@EJB
	private CompetenceGestionnaireDAO gd;

}
