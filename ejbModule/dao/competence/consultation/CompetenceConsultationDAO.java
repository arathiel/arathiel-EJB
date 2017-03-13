package dao.competence.consultation;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Cette classe g�re toute les demandes de consultation du module Comp�tence � l'unit� de persistance.
 * 
 * Elle travaille avec un EntityManager.
 * Elle est instanci�e par le serveur d'application � l'appel de dao.FacadeDaoCompetence.class
 * Une fois cr��, le m�me objet est utilis�.
 * 
 * @author Claire
 * @version 20170313
 *
 */
@LocalBean
@Singleton
public class CompetenceConsultationDAO {

}
