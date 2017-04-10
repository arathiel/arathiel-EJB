package entity.race_bonus_carac.bonus;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import service.race_bonus_carac.bonus.FabriqueBonus;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Bonus implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
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

	public Bonus dto() {
		FabriqueBonus fabBonus = new FabriqueBonus();
		Bonus bonus = null;
		if (this instanceof BonusCarac){
			bonus = fabBonus.creerBonus(((BonusCarac)this).getCaracAssociee(), this.getValeurBonus());
		}
		if (this instanceof BonusCompetence){
			bonus = fabBonus.creerBonus(((BonusCompetence)this).getCompAssociee(), this.getValeurBonus(),((BonusCompetence)this).isAcademique());
		}
		if (this instanceof BonusTrait){
			bonus = fabBonus.creerBonus(((BonusTrait)this).getTraitAssocie().getDto(), this.getValeurBonus());
		}
		return bonus;
	}
	
	
}
