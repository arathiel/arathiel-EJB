package clientServeur.race_bonus_carac.interfaces;

import java.util.Collection;

import entity.race_bonus_carac.bonus.Bonus;


/**
 * Interface que doivent implémenter les pack qui peuvent contenir des bonus
 * 
 *Pour le moment sont concernés les pack Race (françois) et Carriere(Ismaël)
 *
 *Ces packs doivent definir un nom, un id, un cout en point d'experience, 
 *une liste de bonus(avec des methodes pour ajouter/retirer des bonus) et un booléen renseignant leur disponibilité pour les joueurs
 *
 *Il doivent également implementer une méthode permettant de calculer combine d'XP ils rendent au joueur qui les choisi
 *
 * @author Francois Georgel
 *
 */
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
