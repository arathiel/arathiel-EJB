package dao.race_bonus_carac.carac;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.race_bonus_carac.param.Requetes;
import entity.race_bonus_carac.caracteristique.Caracteristique;

/**
 * Classe qui va gérer la dao de l'entity Caracteristique 
 * 
 * @author Francois Georgel
 *
 */
@LocalBean
@Singleton
public class DaoCarac {

	@PersistenceContext(unitName="A1Persistence")
	EntityManager em;
	

	public void insertCarac(Caracteristique carac) {
		em.persist(carac);
	}
	

	public void deleteCarac(Caracteristique carac) {
		Caracteristique caracHib = em.find(Caracteristique.class, carac.getIdCarac());
		em.remove(caracHib);
	}


	public ArrayList<Caracteristique> listeCarac() {
		ArrayList<Caracteristique> liste = new ArrayList<>();
				
		for (Object c : em.createQuery(Requetes.TOUTES_CARAC.getMsg()).getResultList()) {
			if (c instanceof Caracteristique){
				liste.add((Caracteristique) c);
			}
		}
		return liste;			
	}


}
