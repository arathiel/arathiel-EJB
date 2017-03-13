package clientServeur.bonus;

import java.util.ArrayList;

import entity.bonus.Bonus;

public interface IPackable {
	
	public void setId(int id);
	public void setNom(String nom);
	public void setDispo(boolean dispo);
	public void setCoutXp(int coutXp);
	public void setListeBonus(ArrayList<Bonus> listeBonus);
	
	public int getId();
	public String getNom();
	public boolean getDispo();
	public int getCoutXp();
	public ArrayList<Bonus> getListeBonus();
	
	public void calculRenduXp();
	public void ajouterBonus(Bonus bonus);
	public void enleverBonus(Bonus bonus);
	

}
