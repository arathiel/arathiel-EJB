package clientServeur.race_bonus_carac.interfaces;

import java.util.ArrayList;

import clientServeur.race_bonus_carac.userException.UserExceptionRBC;
import entity.race_bonus_carac.bonus.Bonus;
import entity.race_bonus_carac.caracteristique.Caracteristique;
import entity.race_bonus_carac.race.Race;

/**
 * Interface Générale pour les trois concepts Races/Bonus/Caractéristiques
 * 
 * Contient les méthodes de redirection vers les Services de chaque Entités
 * 
 * 
 * @author Francois Georgel
 *
 */
public interface IFacadeServiceRBC {
	
	//Méthodes concernant les Races
	public void enregistrerRace (Race race) throws UserExceptionRBC;
	public void supprimerRace(Race race) throws UserExceptionRBC;
	public void modifierRace(Race race) throws UserExceptionRBC;
	public ArrayList<Race> listeToutesRaces();
	public ArrayList<Race> listeRacesJouables();
	public Race RechRaceParNom(String nom) throws UserExceptionRBC;
	public Race RechRaceParId(int id) throws UserExceptionRBC;
	
	//Methodes concernant les Bonus
	public void insertBonus (Bonus bonus) throws UserExceptionRBC;
	public ArrayList<Bonus> listeTousBonus();
	public void deleteBonus(Bonus bonus) throws UserExceptionRBC;

	
	//Methodes concerant les Caracteristiques
	public void insertCarac (Caracteristique carac);
	public void deleteCarac(Caracteristique carac);	
	public ArrayList<Caracteristique> listeCarac();

}
