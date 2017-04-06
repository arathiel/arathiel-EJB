package dao.armurerie.inventaire;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import dao.armurerie.exception.DaoOlivBException;
import dao.armurerie.param.ArmurerieParam;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import dao.race_bonus_carac.race.consultation.RaceDaoConsultation;
import dao.util.Parameter;
import entity.armurerie.Arme;
import entity.armurerie.Joueur;
import entity.race_bonus_carac.race.Race;
import util.armurerie.ExceptionMessageErreurOlivB;

@Singleton
@LocalBean
public class DaoInventaire {

	
	@EJB
	RaceDaoConsultation raceDaoConsultation;
	
	@PersistenceContext(unitName=Parameter.UNITNAME_JUNONARATHIEL)
	EntityManager em;


	private List<Arme> 		armes;
	private List<Race> 		races;
	private List<Joueur> 	joueurs;
	private Joueur			joueur;
	private Arme			arme;

	public List<Arme> selectArmesWhereRace() throws DaoOlivBException {
		armes = new ArrayList<Arme>();
		try {
			for (Object obj : em.createQuery(ArmurerieParam.SELECT_ARME.getRequete()).getResultList()){
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
			for (Object obj : em.createQuery(ArmurerieParam.SELECT_ARME.getRequete()).getResultList()){
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
			for (Object obj :  em.createQuery(ArmurerieParam.SELECT_RACE.getRequete()).getResultList()) {
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
		Arme armeTransition = (Arme) em.createQuery(ArmurerieParam.SELECT_ARME_NOM.getRequete()).setParameter(1, nom).getSingleResult();
		return armeTransition;
	}

	public List<Race> findRacesAssociees(List<String> raceArme) throws DaoOlivBException, DaoExceptionRBC {
		if (raceArme.isEmpty()) throw new DaoOlivBException(ExceptionMessageErreurOlivB.RACE_INEXISTANTE);
		races = new ArrayList<Race>();
		for (String value : raceArme) {
			try {
				Race race = raceDaoConsultation.rechRaceParNom(value);
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
			for (Object obj :  em.createQuery(ArmurerieParam.SELECT_JOUEUR.getRequete()).getResultList()) {
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
