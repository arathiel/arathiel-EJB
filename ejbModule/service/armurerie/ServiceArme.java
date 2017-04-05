package service.armurerie;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import armurerie.Exception.ExceptionMessageErreurOlivB;
import armurerie.Exception.ServiceOlivBException;
import clientServeur.IArme;
import dao.armurerie.facade.DaoFacade;
import entity.armurerie.Arme;
import entity.armurerie.ArmeJoueur;
import entity.armurerie.Joueur;
import entity.race_bonus_carac.race.Race;
import util.armurerie.Etat;

/**
 * Classe de Service entre la partie présentation et la partie Serveur. Cette
 * classe sert uniquement pour l'Arme
 * 
 * @author OlivB
 */
@Stateless
@LocalBean
public class ServiceArme {

	@EJB
	DaoFacade daoFacade;
	
	private Etat etatEtat;

	public void createArme(IArme arme, List<String> raceArme) throws ServiceOlivBException   {
		if (arme == null) throw new ServiceOlivBException(ExceptionMessageErreurOlivB.ARME_NULL);
		if (raceArme.isEmpty()) throw new ServiceOlivBException(ExceptionMessageErreurOlivB.RACE_INEXISTANTE);
		try {
			daoFacade.insert(arme, raceArme);
		}
		catch (Exception e) {
			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.DOUBLON_ARME);
		}

	}
	
	public void createArmeJoueur(ArmeJoueur armeJoueurDto, int joueurId, int armeId, String etat) throws ServiceOlivBException {
		if (armeJoueurDto == null) throw new ServiceOlivBException(ExceptionMessageErreurOlivB.ARMEJOUR_NULL);
		etatEtat = Etat.getEnum(etat);
		try {
			daoFacade.persistArmeJoueur(armeJoueurDto, joueurId, armeId, etatEtat);
		}
		catch (Exception e) {
			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.DOUBLON_ARMEJOUEUR);
		}
	}


	public List<Race> listerRaces() throws ServiceOlivBException {
		try {
			return daoFacade.selectRaces();
		} 
		catch (Exception e) {
			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.NO_LISTE_RACE);
		}
	}

	public List<Arme> listerArmes() throws ServiceOlivBException {
		try {
			return daoFacade.selectArmes();
		} 
		catch (Exception e) {
			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.NO_LISTE_ARME);
			
		}
	}

	public void modifArme(IArme arme, List<String> raceArme) throws ServiceOlivBException {
		if (arme == null) throw new ServiceOlivBException(ExceptionMessageErreurOlivB.ARME_NULL);
		if (raceArme.isEmpty()) throw new ServiceOlivBException(ExceptionMessageErreurOlivB.RACE_INEXISTANTE);
		try {
			daoFacade.update(arme, raceArme);
		} 
		catch (Exception e) {
			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.ARME_INEXISTANTE);
		}

	}

	public void supprArme(IArme arme) throws ServiceOlivBException {
		if (arme == null) throw new ServiceOlivBException(ExceptionMessageErreurOlivB.ARME_NULL);
		try {
			daoFacade.delete(arme);
		} catch (Exception e) {
			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.ARME_INEXISTANTE);

		}
		
	}

	public Arme getArme(String nom) {
		return daoFacade.rechArme(nom);
	}

	public List<Arme> listerArmesRace() throws ServiceOlivBException {
		try {
			return daoFacade.selectArmesWhereRace();
		} 
		catch (Exception e) {
			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.NO_LISTE_ARME);
		}
	}

	public List<Joueur> listerJoueurs() throws ServiceOlivBException {
		try {
			return daoFacade.selectJoueurs();
		}
		catch (Exception e) {
			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.NO_LISTE_JOUEUR);
		}
	}


}
