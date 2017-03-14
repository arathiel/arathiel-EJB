package entity.bonus;

import entity.competence.Competence;

/**
 * Classe fabriqu�e par la Classe {@link}FabriqueBonus
 * 
 * 
 * @author Fran�ois Georgel
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
