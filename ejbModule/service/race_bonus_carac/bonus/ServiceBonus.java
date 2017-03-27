package service.race_bonus_carac.bonus;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.race_bonus_carac.race.FacadeDaoRace;
import entity.race_bonus_carac.bonus.Bonus;

@LocalBean
@Singleton
public class ServiceBonus {
	
	@EJB
	FacadeDaoRace fDaoRace;

	public void ajouterBonus(Bonus bonus){
		
	}
		
	public ArrayList<Bonus> listeTousBonus() {
		return null;
	}

	

	public void supprimerBonus(Bonus bonus){
		
	}

}
