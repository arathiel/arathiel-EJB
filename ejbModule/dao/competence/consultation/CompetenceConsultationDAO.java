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
	
	@PersistenceContext(unitName=Parameter.UNITNAME_JUNONARATHIEL)
	EntityManager em;
	
	/**
	 * Liste de toutes les competences ins�r�es dans la base
	 * Methode cr��e rapidement pour les besoins de la fonction Bonus	 
	 * 
	 * @author Fran�ois Georgel
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
