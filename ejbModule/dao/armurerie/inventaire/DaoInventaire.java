package dao.armurerie.inventaire;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import armurerie.Exception.ExceptionMessageErreurOlivB;
import dao.armurerie.exception.DaoOlivBException;
import entity.armurerie.Arme;
import entity.armurerie.Joueur;
import entity.race_bonus_carac.race.Race;

@Singleton
@LocalBean
public class DaoInventaire {

	@PersistenceContext(unitName="Ahibernate")
	EntityManager em;

	private List<Arme> 		armes;
	private List<Race> 		races;
	private List<Joueur> 	joueurs;
	private Joueur			joueur;
	private Arme			arme;

	public List<Arme> selectArmesWhereRace() throws DaoOlivBException {
		armes = new ArrayList<Arme>();
		try {
			for (Object obj : em.createQuery("select a from Arme a").getResultList()){
				if (obj instanceof Arme) {
					armes.add((Arme) obj);
				}
			}
		}
		catch (PersistenceException e) {
			throw new DaoOlivBException(ExceptionMessageErreurOlivB.ARME_INEXISTANTE);
		}
		return armes;
	}

	public List<Arme> selectArmes() throws DaoOlivBException {
		armes = new ArrayList<Arme>();
		try {
			for (Object obj : em.createQuery("select a from Arme a order by a.idArme asc").getResultList()){
				if (obj instanceof Arme) {
					armes.add((Arme) obj);
				}
			}
		}
		catch (PersistenceException e) {
			throw new DaoOlivBException(ExceptionMessageErreurOlivB.ARME_INEXISTANTE);
		}
		return armes;
	}

	public List<Race> selectRaces() throws DaoOlivBException {
		races = new ArrayList<Race>();
		try {
			for (Object obj :  em.createQuery("select r from Race r").getResultList()) {
				if (obj instanceof Race) {
					races.add((Race) obj);
				}
			}
		}
		catch (PersistenceException e) {
			throw new DaoOlivBException(ExceptionMessageErreurOlivB.NO_RACE);
		}
		return races;
	}

	public Arme rechArme(String nom) {
		Arme armeTransition = (Arme) em.createQuery("select a from Arme a where a.nom = ?1").setParameter(1, nom).getSingleResult();
		return armeTransition;
	}

	public List<Race> findRacesAssociees(List<String> raceArme) throws DaoOlivBException {
		if (raceArme.isEmpty()) throw new DaoOlivBException(ExceptionMessageErreurOlivB.RACE_INEXISTANTE);
		races = new ArrayList<Race>();
		for (String value : raceArme) {
			try {
				Race race = (Race) em.createQuery("select r from Race r where r.nomRace = ?1")
						.setParameter(1,value).getSingleResult();
				races.add(race);
			}
			catch(PersistenceException e) {
				throw new DaoOlivBException(ExceptionMessageErreurOlivB.NO_RACE);
			}
		}
		return races;
	}

	public List<Joueur> selectJoueurs() throws DaoOlivBException {
		joueurs = new ArrayList<Joueur>();
		try {
			for (Object obj :  em.createQuery("select j from Joueur j order by j.idJoueur asc").getResultList()) {
				if (obj instanceof Joueur) {
					joueurs.add((Joueur) obj);
				}
			}
		}
		catch (PersistenceException e) {
			throw new DaoOlivBException(ExceptionMessageErreurOlivB.NO_JOUEUR);
		}
		return joueurs;
	}
	
	public Joueur findJoueur(int joueurId) {
		joueur = em.find(Joueur.class, joueurId);
		System.out.println(joueur);
		return joueur;
	}

	public Arme findArme(int armeId) {
		arme = em.find(Arme.class, armeId);
		System.out.println(arme);
		return arme;
	}
}
