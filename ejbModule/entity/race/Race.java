package entity.race;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

import clientServeur.bonus.IPackable;
import entity.bonus.Bonus;
import parametres.race.Param;


@Entity
public class Race implements IPackable {
	
	@Id
	private int id;
	private String nom;
	private boolean dispo;
	private int coutXp = Param.COUT_XP_RACE;
	private ArrayList<Bonus> listeBonus;
	
	
	public Race(){
	}
	
	public Race (int id, String nom, boolean dispo){
		this.id  	= id;
		this.nom 	= nom;
		this.dispo 	= dispo;
	}
	
	

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public void setDispo(boolean dispo) {
		this.dispo = dispo;		
	}

	@Override
	public void setCoutXp(int coutXp) {
		this.coutXp = coutXp;		
	}

	@Override
	public void setListeBonus(ArrayList<Bonus> listeBonus) {
		
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public boolean getDispo() {
		return dispo;
	}

	@Override
	public int getCoutXp() {
		return coutXp;
	}

	@Override
	public ArrayList<Bonus> getListeBonus() {
		return listeBonus;
	}

	@Override
	public void calculRenduXp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterBonus(Bonus bonus) {
		this.listeBonus.add(bonus);		
	}

	@Override
	public void enleverBonus(Bonus bonus) {
		this.listeBonus.remove(bonus);		
	}
	
}
