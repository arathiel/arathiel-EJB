package dao.competence;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.competence.consultation.CompetenceConsultationDAO;
import dao.competence.gestionnaire.CompetenceGestionnaireDAO;

/**
 * Cette classe liste toutes les méthodes interragissant avec l'unité de persistance pour le module Compétence.
 * 
 * Elle redirige vers les classes de gestion ou de consultation de la couche DAO du module Compétence.
 * Elle est instanciée par le serveur d'application à l'appel de dao.FacadeDAO.class
 * Une fois créé, le même objet est utilisé.
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
