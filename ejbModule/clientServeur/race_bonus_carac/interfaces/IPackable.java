package clientServeur.race_bonus_carac.interfaces;

import java.util.Collection;

import entity.race_bonus_carac.bonus.Bonus;

public interface IPackable {
	
	public void setId(int id);
	public void setNom(String nom);
	public void setDispo(boolean dispo);
	public void setCoutXp(int coutXp);
	public void setListeBonus(Collection<Bonus> listeBonus);
	
	public int getId();
	public String getNom();
	public boolean isDispo();
	public int getCoutXp();
	public Collection<Bonus> getListeBonus();
	
	public int calculRenduXp();
	public void ajouterBonus(Bonus bonus);
	public void enleverBonus(Bonus bonus);
	

}
