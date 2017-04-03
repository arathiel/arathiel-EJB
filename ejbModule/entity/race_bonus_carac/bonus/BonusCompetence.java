package entity.race_bonus_carac.bonus;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import entity.competence.Competence;
import parametres.race_bonus_carac.bonus.ParamBonus;

/**
 * Classe fabriquée par la Classe {@link}FabriqueBonus
 * 
 * 
 * @author François Georgel
 *
 */

@Entity
public class BonusCompetence extends Bonus{

	private static final long serialVersionUID = 1L;

	//jonction de l'instance avec sa compétence associée
	//le nullable est set à true pour permettre la persistence dans la table Bonus (classe mère)
	@ManyToOne
	@JoinColumn(name="idComp", nullable = true)
	private Competence compAssociee;
	private boolean academique;
	
	public BonusCompetence(){
		
	}
	
	public BonusCompetence(Competence comp, int valeur, boolean acad){
		this.compAssociee = comp;
		this.setValeurBonus(valeur);
		this.academique = acad;
		this.calculerCoutXp();
	}

	public Competence getCompAssociee() {
		return compAssociee;
	}

	public void setCompAssociee(Competence compAssociee) {
		this.compAssociee = compAssociee;
	}

	public boolean isAcademique() {
		return academique;
	}

	public void setAcademique(boolean academique) {
		this.academique = academique;
	}
	
	@Override
	public void calculerCoutXp() {
		int cout = this.getValeurBonus()*ParamBonus.COUT_XP_COMP_PAR_POINT;
		if (this.isAcademique()) {
			cout = cout * ParamBonus.MULTIPLICATEUR_COMP_ACAD;
		}
		this.setCoutXp(cout);
	}
	
	public String toString() {
		return "Bonus Comp Id: "+this.getIdBonus()+" Valeur= "+this.getValeurBonus()+"Academique = "+this.isAcademique();  
	}
}
