package service.race_bonus_carac.bonus;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import clientServeur.race_bonus_carac.interfaces.IFabriqueBonus;
import entity.competence.Competence;
import entity.race_bonus_carac.bonus.BonusCarac;
import entity.race_bonus_carac.bonus.BonusCompetence;
import entity.race_bonus_carac.bonus.BonusTrait;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.trait.Trait;


/**
 * Classe qui va être en charge de creer les différents bonus en fonction des paramètres reçus
 * 
 * Cette classe se charge egalement de creer l'id de chaque bonus
 * 
 * 
 * @author François Georgel
 *
 */

@Stateless
@Remote(IFabriqueBonus.class)
public class FabriqueBonus implements IFabriqueBonus{

	@Override
	public BonusCarac creerBonus(Caracteristique carac, int valeur) {
		BonusCarac bonus = new BonusCarac(carac, valeur);
		bonus.setIdBonus("Car"+carac.getIdCarac()+valeur);
		return bonus;
	}

	@Override
	public BonusCompetence creerBonus(Competence comp, int valeur, boolean acad) {
		BonusCompetence bonus = new BonusCompetence(comp, valeur, acad);
		bonus.setIdBonus("Comp"+String.valueOf(comp.getId())+valeur+String.valueOf(acad));
		return bonus;
	}

	@Override
	public BonusTrait creerBonus(Trait trait, int valeur) {
		BonusTrait bonus = new BonusTrait(trait, valeur);
		bonus.setIdBonus("Trait"+String.valueOf(trait.getId())+valeur);
		return bonus;
	}

}
