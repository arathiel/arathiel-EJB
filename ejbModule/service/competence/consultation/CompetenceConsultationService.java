package service.competence.consultation;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.FacadeDAO;
import entity.competence.Competence;

/**
 * Cette classe gère toute les demandes de consultation du module Compétence.
 * 
 * Elle interroge dao.FacadeDAO.class pour toutes requêtes à la base de données.
 * Elle est instanciée par le serveur d'application à l'appel de service.FacadeServiceCompetence.class
 * Une fois créé, le même objet est utilisé.
 * 
 * @author Claire
 * @version 20170313
 *
 */
@LocalBean
@Singleton
public class CompetenceConsultationService {

	@EJB
	private FacadeDAO dao;
	
	public ArrayList<Competence> listeToutesComp(){
		return dao.listeToutesComp();
	}
	
}
