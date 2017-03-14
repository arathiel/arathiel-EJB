package entity.bonus;


import entity.trait.Trait;
import parametres.ParamBonus;


/**
 * Classe fabriquée par la Classe {@link}FabriqueBonus
 * 
 * 
 * @author François Georgel
 *
 */

public class BonusTrait extends Bonus{

	public Trait traitAssocie;

	public BonusTrait (String id, Trait trait, int valeur) {
		this.setIdBonus(id);
		this.traitAssocie = trait;
		this.setValeurBonus(valeur);
	}

	
	@Override
	public void calculerCoutXp() {
		if (this.getValeurBonus() == 0) {
			this.setCoutXp(ParamBonus.COUT_XP_TRAIT_VALEUR_0);
		}			
		else if (this.getValeurBonus() <= 4) {
			this.setCoutXp(ParamBonus.COUT_XP_TRAIT_VALEUR_3);
		}
		else if (this.getValeurBonus() >4) {
			this.setCoutXp(ParamBonus.COUT_XP_TRAIT_VALEUR_5);
		}
	}
	

}
