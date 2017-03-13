package service.competence.gestionnaire;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.FacadeDAO;

/**
 * Cette classe g�re toute les demandes de gestion du module Comp�tence.
 * 
 * Elle interroge dao.FacadeDAO.class pour toutes requ�tes � la base de donn�es.
 * Elle est instanci�e par le serveur d'application � l'appel de service.FacadeServiceCompetence.class
 * Une fois cr��, le m�me objet est utilis�.
 * 
 * @author Claire
 * @version 20170313
 *
 */
@LocalBean
@Singleton
public class CompetenceGestionnaireService {

	@EJB
	private FacadeDAO dao;
	
}
