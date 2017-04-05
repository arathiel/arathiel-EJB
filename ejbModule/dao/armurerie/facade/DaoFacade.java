package dao.armurerie.facade;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import clientServeur.IArme;
import dao.armurerie.exception.DaoOlivBException;
import dao.armurerie.gestion.DaoGestion;
import dao.armurerie.inventaire.DaoInventaire;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import entity.armurerie.Arme;
import entity.armurerie.ArmeJoueur;
import entity.armurerie.Joueur;
import entity.race_bonus_carac.race.Race;
import util.armurerie.Etat;

/**
 * Facade entre la couche service et la couche DAO
 * Redirection vers les Bean de gestion ou d'inventaire 
 * @author OlivB
 *
 */
@Singleton
@LocalBean
public class DaoFacade {

	@EJB
	DaoGestion daoGestion;
	@EJB
	DaoInventaire daoInventaire;

	private List<Arme> armes;

	public void insert(IArme arme, List<String> raceArme) throws DaoOlivBException, DaoExceptionRBC {
		daoGestion.insert(arme, raceArme );

	}

	public void persistArmeJoueur(ArmeJoueur armeJoueurDto, int joueurId, int armeId, Etat etatEtat) {
		daoGestion.persistArmeJoueur(armeJoueurDto, joueurId, armeId, etatEtat);
		
	}
	public List<Race> selectRaces() throws DaoOlivBException {
		return daoInventaire.selectRaces();
	}

	public List<Arme> selectArmes() throws DaoOlivBException {
		armes = new ArrayList<Arme>();
		for (Arme arme : daoInventaire.selectArmes()) {
			armes.add(arme.getPersistBag());
		}
		return armes;
	}

	public void update(IArme arme, List<String> raceArme) throws DaoOlivBException, DaoExceptionRBC {
		daoGestion.update(arme, raceArme);
	}

	public void delete(IArme arme) throws DaoOlivBException {
		daoGestion.delete(arme);

	}

	public Arme rechArme(String nom) {
		Arme armeTransit = daoInventaire.rechArme(nom);
		armeTransit.getPersistBag();
		return armeTransit;
	}

	public List<Arme> selectArmesWhereRace() throws DaoOlivBException {
		armes = new ArrayList<Arme>();
		for (Arme arme :  daoInventaire.selectArmesWhereRace()) {
			armes.add(arme.getPersistBag());
		}
		return armes;
	}

	public List<Joueur> selectJoueurs() throws DaoOlivBException {
		return daoInventaire.selectJoueurs();
	}


}
