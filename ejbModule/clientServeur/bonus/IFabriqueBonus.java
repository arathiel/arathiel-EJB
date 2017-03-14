package clientServeur.bonus;

import entity.bonus.BonusCarac;
import entity.bonus.BonusCompetence;
import entity.bonus.BonusTrait;
import entity.caracteristique.Caracteristique;
import entity.competence.Competence;
import entity.trait.Trait;


/**
 * Classe Fabrique de Bonus
 * 
 * 
 * @author Francois Georgel
 *
 */


public interface IFabriqueBonus {
	
	public BonusCarac creerBonus(String id, Caracteristique carac, int valeur);
	public BonusCompetence creerBonus(String id, Competence comp, int valeur, boolean acad);
	public BonusTrait creerBonus(String id, Trait trait, int valeur);

}
