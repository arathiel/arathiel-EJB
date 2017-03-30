package dao.competence.consultation;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.race_bonus_carac.param.Requetes;
import dao.util.Parameter;
import entity.competence.Competence;

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
	
	@PersistenceContext(unitName=Parameter.UNITNAME_JUNONARATHIEL)
	EntityManager em;
	
	/**
	 * Liste de toutes les competences insérées dans la base
	 * Methode créée rapidement pour les besoins de la fonction Bonus	 
	 * 
	 * @author François Georgel
	 * @return ArrayList<Competence>
	 */
	public ArrayList<Competence> listeToutesComp() {
		ArrayList<Competence> liste = new ArrayList<Competence>();
		
		for (Object c : em.createQuery(Requetes.TOUTES_COMP.getMsg()).getResultList()) {
			if (c instanceof Competence){				
					liste.add((Competence) c);
			}
		}
		return liste;		
	}
}
