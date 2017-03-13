package entity.bonus;

import clientServeur.bonus.IBonussable;

public abstract class Bonus {
	
	private int valeurBonus;
	private int coutXp;
	

	
	public Bonus creerBonus(IBonussable bonussable, int valeurBonus, int coutXp, boolean acad){
		
		//TODO switch des instanceOf du bonussable	
		
		Bonus bonus = new BonusCarac();
		bonus.setValeurBonus(valeurBonus);
		bonus.setCoutXp(coutXp);
		return bonus;
		
	}



	public int getValeurBonus() {
		return valeurBonus;
	}



	public void setValeurBonus(int valeurBonus) {
		this.valeurBonus = valeurBonus;
	}



	public int getCoutXp() {
		return coutXp;
	}



	public void setCoutXp(int coutXp) {
		this.coutXp = coutXp;
	}
}
