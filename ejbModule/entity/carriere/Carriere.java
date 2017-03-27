package entity.carriere;

import java.util.Collection;

import javax.persistence.*;

import clientServeur.race_bonus_carac.interfaces.IPackable;
import entity.race_bonus_carac.bonus.Bonus;
import parametres.bonus.ParamIPackable;

@Entity
public class Carriere implements IPackable
{
	@Id
	@Column(name="refCarriere")
	private int id;
	
	@Column(name="nomCarriere", length=150, nullable=false)
	private String nom;
	
	@Column(name="Disponibilité")
	private boolean dispo;
	
	@Column(name="CoutXP")
	private int coutXp = ParamIPackable.COUT_XP_CARRIERE;
	
	

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNom(String nom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDispo(boolean dispo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCoutXp(int coutXp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListeBonus(Collection<Bonus> listeBonus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getDispo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCoutXp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Bonus> getListeBonus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculRenduXp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void ajouterBonus(Bonus bonus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enleverBonus(Bonus bonus) {
		// TODO Auto-generated method stub
		
	}
	
	

	

}
