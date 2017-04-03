package clientServeur;

import java.util.Collection;

import entity.race_bonus_carac.race.Race;


/**
 * Interface Arme
 * @author OlivB
 *
 */
public interface IArme {

	
	public String getNom();
	public void setNom(String nom);
	public int getEncombrement();
	public void setEncombrement(int encombrement);
	public int getPrix();
	public void setPrix(int prix);
	public String getMonnaie();
	public void setMonnaie(String monnaie);
	public int getIdArme();
	public Collection<Race> getRaces();
	public void setRaces(Collection<Race> races);

}
