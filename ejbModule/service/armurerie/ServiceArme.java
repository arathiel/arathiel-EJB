package service.armurerie;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import armurerie.Exception.ExceptionMessageErreurOlivB;
import armurerie.Exception.ServiceOlivBException;
import clientServeur.IArme;
import entity.armurerie.Arme;
import entity.armurerie.ArmeJoueur;
import entity.armurerie.Joueur;
import entity.race_bonus_carac.race.Race;

/**
 * Classe de Service entre la partie présentation et la partie Serveur. Cette
 * classe sert uniquement pour l'Arme
 * 
 * @author OlivB
 */
@Stateless
@LocalBean
public class ServiceArme {

//	@EJB
//	DaoFacade daoFacade;
//
//	@Override
//	public void createArme(IArme arme, List<String> raceArme) throws ServiceOlivBException   {
//		if (arme == null) throw new ServiceOlivBException(ExceptionMessageErreurOlivB.ARME_NULL);
//		if (raceArme.isEmpty()) throw new ServiceOlivBException(ExceptionMessageErreurOlivB.RACE_INEXISTANTE);
//		try {
//			daoFacade.insert(arme, raceArme);
//		}
//		catch (Exception e) {
//			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.DOUBLON_ARME);
//		}
//
//	}
//	
//	@Override
//	public void createArmeJoueur(ArmeJoueur armeJoueurDto) throws ServiceOlivBException {
//		if (armeJoueurDto == null) throw new ServiceOlivBException(ExceptionMessageErreurOlivB.ARMEJOUR_NULL);
//		try {
//			daoFacade.persistArmeJoueur(armeJoueurDto);
//		}
//		catch (Exception e) {
//			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.DOUBLON_ARMEJOUEUR);
//		}
//	}
//
//
//	@Override
//	public List<Race> listerRaces() throws ServiceOlivBException {
//		try {
//			return daoFacade.selectRaces();
//		} 
//		catch (Exception e) {
//			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.NO_LISTE_RACE);
//		}
//	}
//
//	@Override
//	public List<Arme> listerArmes() throws ServiceOlivBException {
//		try {
//			return daoFacade.selectArmes();
//		} 
//		catch (Exception e) {
//			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.NO_LISTE_ARME);
//			
//		}
//	}
//
//	@Override
//	public void modifArme(IArme arme, List<String> raceArme) throws ServiceOlivBException {
//		if (arme == null) throw new ServiceOlivBException(ExceptionMessageErreurOlivB.ARME_NULL);
//		if (raceArme.isEmpty()) throw new ServiceOlivBException(ExceptionMessageErreurOlivB.RACE_INEXISTANTE);
//		try {
//			daoFacade.update(arme, raceArme);
//		} 
//		catch (Exception e) {
//			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.ARME_INEXISTANTE);
//		}
//
//	}
//
//	@Override
//	public void supprArme(IArme arme) throws ServiceOlivBException {
//		if (arme == null) throw new ServiceOlivBException(ExceptionMessageErreurOlivB.ARME_NULL);
//		try {
//			daoFacade.delete(arme);
//		} catch (Exception e) {
//			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.ARME_INEXISTANTE);
//
//		}
//		
//	}
//
//	@Override
//	public Arme getArme(String nom) {
//		return daoFacade.rechArme(nom);
//	}
//
//	@Override
//	public List<Arme> listerArmesRace() throws ServiceOlivBException {
//		try {
//			return daoFacade.selectArmesWhereRace();
//		} 
//		catch (Exception e) {
//			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.NO_LISTE_ARME);
//		}
//	}
//
//	@Override
//	public List<Joueur> listerJoueurs() throws ServiceOlivBException {
//		try {
//			return daoFacade.selectJoueurs();
//		}
//		catch (Exception e) {
//			throw new ServiceOlivBException(ExceptionMessageErreurOlivB.NO_LISTE_JOUEUR);
//		}
//	}


}
