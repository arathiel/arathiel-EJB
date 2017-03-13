package dao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.competence.FacadeDaoCompetence;

/**
 * Cette classe est la facade de la couche DAO.
 * 
 * Liste et aiguillage des méthodes interragissant avec l'unité de persistance.
 * Elle est instanciée par le serveur d'application à l'appel des différentes classes du package service.
 * Une fois créé, le même objet est utilisé.
 * 
 * @author Groupe
 * @version 20170313
 *
 */
@LocalBean
@Singleton
public class FacadeDAO {

	@EJB
	private FacadeDaoCompetence daoComp;
	
}
