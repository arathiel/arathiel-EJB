package dao.competence.consultation;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Cette classe gère toute les demandes de consultation du module Compétence à l'unité de persistance.
 * 
 * Elle travaille avec un EntityManager.
 * Elle est instanciée par le serveur d'application à l'appel de dao.FacadeDaoCompetence.class
 * Une fois créé, le même objet est utilisé.
 * 
 * @author Claire
 * @version 20170313
 *
 */
@LocalBean
@Singleton
public class CompetenceConsultationDAO {

}
