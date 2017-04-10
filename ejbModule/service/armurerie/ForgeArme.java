package service.armurerie;


import javax.ejb.Remote;
import javax.ejb.Stateless;

import clientServeur.IArme;
import clientServeur.IForge;
import entity.armurerie.Arme;


/**
 * Fabrique d'arme pour la création d'une arme
 * @author OlivB
 *
 */
@Stateless
@Remote(IForge.class)
public class ForgeArme implements IForge{

	public IArme creerArme(){
		return new Arme();
	}
	public IArme creerArme(String nom, int encombrement, int prix, String monnaie) {
		return new Arme(nom, encombrement, prix, monnaie);
	}
	@Override
	public IArme creerArme(int idArme, String nom, int encombrement,  int prix, String monnaie) {
		return new Arme(idArme, nom, encombrement, prix, monnaie);
	}

}
