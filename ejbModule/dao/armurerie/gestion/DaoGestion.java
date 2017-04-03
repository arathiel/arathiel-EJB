package dao.armurerie.gestion;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import armurerie.Exception.ExceptionMessageErreurOlivB;
import clientServeur.IArme;
import dao.armurerie.exception.DaoOlivBException;
import dao.armurerie.inventaire.DaoInventaire;
import entity.armurerie.Arme;
import entity.armurerie.ArmeJoueur;
import entity.race_bonus_carac.race.Race;

/**
 * Couhe DAO : requetes vers le Contexte de Persistance
 * @author Afpa
 *
 */
@Singleton
@LocalBean
public class DaoGestion {

	@EJB
	DaoInventaire daoInventaire;
	
	@PersistenceContext(unitName="Ahib")
	EntityManager em;

	private List<Race> races;
	private IArme armeTransit;

	//Insertion d'une nouvelle arme avec sa liste de Races associées
	public void insert(IArme arme, List<String> raceArme) throws DaoOlivBException {
		if (arme == null) throw new DaoOlivBException(ExceptionMessageErreurOlivB.ARME_NULL);
		if (raceArme.isEmpty()) throw new DaoOlivBException(ExceptionMessageErreurOlivB.RACE_INEXISTANTE);
		races = daoInventaire.findRacesAssociees(raceArme);
		arme.setRaces(races);
		try {
			em.persist(arme);
		}
		catch (PersistenceException e) {
			throw new DaoOlivBException(ExceptionMessageErreurOlivB.DOUBLON_ARME);
		}
	}
	
	public void persistArmeJoueur(ArmeJoueur armeJoueurDto) throws DaoOlivBException {
		if (armeJoueurDto == null) throw new DaoOlivBException(ExceptionMessageErreurOlivB.ARMEJOUR_NULL);
		System.out.println(armeJoueurDto.getArme().getIdArme() + " " +  armeJoueurDto.getEtat() + " " + armeJoueurDto.getJoueur().getIdJoueur());
		
		
	}

	//méthode de modification de l'arme ainsi que sa liste de races
	public void update(IArme arme, List<String> raceArme) throws DaoOlivBException {
		if (arme == null) throw new DaoOlivBException(ExceptionMessageErreurOlivB.ARME_NULL);
		if (raceArme.isEmpty()) throw new DaoOlivBException(ExceptionMessageErreurOlivB.RACE_INEXISTANTE);
		races = daoInventaire.findRacesAssociees(raceArme);
		//		em.createNativeQuery("delete from armerace where idRace = ?1").setParameter(1, armeFind.getIdArme());
		try {
			armeTransit = em.find(Arme.class, arme.getIdArme());
			armeTransit.setNom(arme.getNom());
			armeTransit.setEncombrement(arme.getEncombrement());
			armeTransit.setPrix(arme.getPrix());
			armeTransit.setMonnaie(arme.getMonnaie());
			armeTransit.setRaces(races);
			em.merge(armeTransit);
		}
		catch (PersistenceException e) {
			throw new DaoOlivBException(ExceptionMessageErreurOlivB.ARME_INEXISTANTE);
		}

	}

	public void delete(IArme arme) throws DaoOlivBException {
		if (arme == null) throw new DaoOlivBException(ExceptionMessageErreurOlivB.ARME_NULL);
		try {
		armeTransit = em.find(Arme.class, arme.getIdArme());
		em.remove(armeTransit);
		}
		catch (PersistenceException e) {
			throw new DaoOlivBException(ExceptionMessageErreurOlivB.ARME_INEXISTANTE);
		}

	}



}
