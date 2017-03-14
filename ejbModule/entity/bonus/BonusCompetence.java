package entity.bonus;

import entity.competence.Competence;

/**
 * Classe fabriquée par la Classe {@link}FabriqueBonus
 * 
 * 
 * @author François Georgel
 *
 */

public class BonusCompetence extends Bonus{

	private Competence compAssociee;
	private boolean academique;
	
	public BonusCompetence(){
		
	}
	
	public BonusCompetence(String id, Competence comp, int valeur, boolean acad){
		this.setIdBonus(id);
		this.compAssociee = comp;
		this.academique = acad;
	}

	public boolean isAcademique() {
		return academique;
	}

	public void setAcademique(boolean academique) {
		this.academique = academique;
	}
	
	@Override
	public void calculerCoutXp() {
		
	}
	
}
