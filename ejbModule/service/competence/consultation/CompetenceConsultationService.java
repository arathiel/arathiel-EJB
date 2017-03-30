package service.competence.consultation;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.FacadeDAO;
import entity.competence.Competence;

/**
 * Cette classe g�re toute les demandes de consultation du module Comp�tence.
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
public class CompetenceConsultationService {

	@EJB
	private FacadeDAO dao;
	
	public ArrayList<Competence> listeToutesComp(){
		return dao.listeToutesComp();
	}
	
}
