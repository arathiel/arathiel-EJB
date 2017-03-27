package entity.race_bonus_carac.bonus;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import entity.race_bonus_carac.caracteristique.Caracteristique;
import parametres.race_bonus_carac.bonus.ParamBonus;

/**
 * Classe fabriqu�e par la Classe {@link}FabriqueBonus
 * 
 * 
 * @author Fran�ois Georgel
 *
 */


@Entity
public class BonusCarac extends Bonus{
	
	private static final long serialVersionUID = 1L;

	//jonction de l'instance avec sa caract�ristique associ�e
	//le nullable est set � true pour permettre la persistence dans la table Bonus (classe m�re)
	@ManyToOne
	@JoinColumn(name = "idCarac", nullable = true)
	private Caracteristique caracAssociee;
	
	public BonusCarac(){		
	}
	
	public BonusCarac(Caracteristique carac, int valeur){
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
		int cout = this.getValeurBonus()*ParamBonus.COUT_XP_CARAC_PAR_POINT;
		this.setCoutXp(cout);
	}
	
	public String toString() {
		return "Bonus Carac Id: "+this.getIdBonus()+" Valeur= "+this.getValeurBonus();  
	}
	
}
