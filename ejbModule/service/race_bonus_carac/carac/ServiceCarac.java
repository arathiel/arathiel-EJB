package service.race_bonus_carac.carac;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.FacadeDAO;
import entity.race_bonus_carac.caracteristique.Caracteristique;


/**
 * Classe d'accès a la facade DAO pour les caracteristiques
 * 
 * 
 * @author Francois Georgel
 *
 */
@LocalBean
@Singleton
public class ServiceCarac {
	
	
	@EJB
	FacadeDAO fDao;
	

	public void ajouterCarac(Caracteristique carac) {
		fDao.insertCarac();
		
	}

	public void supprimerCarac(Caracteristique carac) {
		fDao.deleteCarac();
		
	}

	public ArrayList<Caracteristique> listeCarac() {
	
		return fDao.listeCarac();
	}

}
