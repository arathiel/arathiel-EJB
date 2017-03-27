package entity.race;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import clientServeur.bonus.IPackable;
import entity.armurerie.ArmeRace;
import entity.bonus.Bonus;
import parametres.bonus.ParamIPackable;


@Entity
public class Race implements IPackable {
	
	@Id
	private int id;
	private String nom;
	private boolean dispo;
	private int coutXp = ParamIPackable.COUT_XP_RACE;
	private ArrayList<Bonus> listeBonus;
//--------------------------------OlivB, creation ArrayList<ArmeRace> pour table d'association------------------	
	@OneToMany(mappedBy="race", cascade= {CascadeType.ALL}, fetch=FetchType.LAZY)
	private Collection <ArmeRace> armes = new ArrayList<ArmeRace>();
	//getter et setter de la collection
	public Collection<ArmeRace> getArmes() {
		return armes;
	}
	public void setArmes(Collection<ArmeRace> armes) {
		this.armes = armes;
	}
	//--------------------------------fin OlivB---------------------------------------------------------------------	
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
