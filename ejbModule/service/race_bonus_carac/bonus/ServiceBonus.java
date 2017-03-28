package service.race_bonus_carac.bonus;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.FacadeDAO;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import entity.race_bonus_carac.bonus.Bonus;


/**
 * Classe d'accès a la facade DAO pour les bonus
 * 
 * 
 * @author Francois Georgel
 *
 */
@LocalBean
@Singleton
public class ServiceBonus {
	
	
	@EJB
	FacadeDAO fDao;

	public void ajouterBonus(Bonus bonus) throws DaoExceptionRBC{
		fDao.insertBonus(bonus);
	}
		
	public ArrayList<Bonus> listeTousBonus() {
		return fDao.listeTousBonus();
	}	

	public void supprimerBonus(Bonus bonus) throws DaoExceptionRBC{
		fDao.deleteBonus(bonus);
	}

	

}
