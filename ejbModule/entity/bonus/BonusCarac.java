package entity.bonus;

import entity.caracteristique.Caracteristique;

/**
 * Classe fabriquée par la Classe {@link}FabriqueBonus
 * 
 * 
 * @author François Georgel
 *
 */



public class BonusCarac extends Bonus{
	
	
	private Caracteristique caracAssociee;
	
	public BonusCarac(){		
	}
	
	public BonusCarac(String id, Caracteristique carac, int valeur){
		this.setIdBonus(id);
		this.caracAssociee = carac;
		this.setValeurBonus(valeur);
	}
	
	public Caracteristique getCaracAssociee() {
		return caracAssociee;
	}

	public void setCaracAssociee(Caracteristique caracAssociee) {
		this.caracAssociee = caracAssociee;
	}

	@Override
	public void calculerCoutXp() {
		int cout = this.getValeurBonus()*2;
		this.setCoutXp(cout);
	}
	
}
