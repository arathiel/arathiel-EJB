package entity.bonus;

/**
 * Classe qui servira de fabrique aux différents bonus
 * 
 * 
 * @author Afpa
 *
 */

public abstract class Bonus {
	
	private String idBonus;
	private int valeurBonus;
	private int coutXp;
	

	public Bonus(){		
	}
	
	public String getIdBonus() {
		return idBonus;
	}

	public void setIdBonus(String idBonus) {
		this.idBonus = idBonus;
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
	
	public void calculerCoutXp() {
		this.coutXp = 0;
	}
}
