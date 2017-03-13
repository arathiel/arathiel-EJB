package dao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.competence.FacadeDaoCompetence;

/**
 * Cette classe est la facade de la couche DAO.
 * 
 * Liste et aiguillage des m�thodes interragissant avec l'unit� de persistance.
 * Elle est instanci�e par le serveur d'application � l'appel des diff�rentes classes du package service.
 * Une fois cr��, le m�me objet est utilis�.
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
