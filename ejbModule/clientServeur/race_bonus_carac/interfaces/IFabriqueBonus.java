package clientServeur.race_bonus_carac.interfaces;

import entity.competence.Competence;
import entity.race_bonus_carac.bonus.BonusCarac;
import entity.race_bonus_carac.bonus.BonusCompetence;
import entity.race_bonus_carac.bonus.BonusTrait;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.trait.Trait;


/**
 * Classe Fabrique de Bonus
 * 
 * 
 * @author Francois Georgel
 *
 */


public interface IFabriqueBonus {
	
	public BonusCarac creerBonus(Caracteristique carac, int valeur);
	public BonusCompetence creerBonus(Competence comp, int valeur, boolean acad);
	public BonusTrait creerBonus(Trait trait, int valeur);

}
