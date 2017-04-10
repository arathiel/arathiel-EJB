package service.passion.gestion;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import clientServeur.exception.ExceptionService;
import clientServeur.race_bonus_carac.userException.UserExceptionRBC;
import clientServeur.race_bonus_carac.userException.UserExceptionRBCMsg;
import dao.FacadeDAO;
import dao.passionMagie.exception.DaoException;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import dao.race_bonus_carac.exception.DaoExceptionRBCMsg;
import dao.util.passionMagie.UtilDAO;
import entity.magie.MDPFondamental;
import entity.passion.Passion;
import service.ressources.passionMagie.Erreur;


@LocalBean
@Singleton
public class ServicePassionGestion {

	private static final Logger LOGGER = LogManager.getLogger();

	@EJB
	FacadeDAO daoFacade;

	/**
	 * Méthode permettant d'insérer une passion en base de données
	 * 
	 * RG: une passion a obligatoirement deux mots de pouvoir fondamentaux
	 * associés
	 * 
	 * @param passion
	 * @throws ExceptionService
	 * @throws UserExceptionRBC 
	 * 
	 */
	// ==> On doit pouvoir ajouter les mots de pouvoir à ce moment
	public void addPassion(Passion passion) throws ExceptionService, UserExceptionRBC {

		// On n'envoie pas en base si la passion est nulle
		if (passion == null)  {
			LOGGER.warn("Levée d'une exception");
			throw new ExceptionService(Erreur.PASSION_NULL.message(), Erreur.PASSION_NULL.getCode());
		}else if (passion.getNom() == null){
			LOGGER.warn("Levée d'une exception");
			throw new ExceptionService(Erreur.PASSION_NULL.message(), Erreur.PASSION_NULL.getCode());
		}
				passion.setNom(UtilDAO.modifNom(passion.getNom()));
			// RG : Une passion doit avoir deux mots de pouvoir associés
			Collection<MDPFondamental> liste = passion.getMagies();
			LOGGER.info("liste : " + liste.toString());
			if (liste == null || liste.isEmpty()) {
				throw new ExceptionService(Erreur.PASSION_MAGIE_OBLIGATOIRE.message(),
						Erreur.PASSION_MAGIE_OBLIGATOIRE.getCode());
			} else {
				//On modifie le nom de la passion de sorte à envoyer en base un nom de passion trim et lower case
				passion.setNom(passion.getNom().trim().toLowerCase());
				try {
					daoFacade.addPassion(passion);
				} catch (DaoException e) {
					LOGGER.info(e.getCode());
					if (e.getCode() == 2) {
						LOGGER.info("exception" +e.getMessage());
						throw new ExceptionService(Erreur.PASSION_DOUBLON.message(), Erreur.PASSION_DOUBLON.getCode());
					}
					if (e.getCode() == 3) {
						throw new ExceptionService(Erreur.PASSION_NOM_OBLIGATOIRE.message(),
								Erreur.PASSION_NOM_OBLIGATOIRE.getCode());
					}
				} catch (DaoExceptionRBC e) {
					if (e.getMessage().equals(DaoExceptionRBCMsg.RACE_NOM_INVALIDE.getMsg()))	{ throw new UserExceptionRBC(UserExceptionRBCMsg.RACE_NOM_INVALIDE);}
					if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_ID_RACE.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_ID_RACE);}
					if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_NOM_RACE.getMsg()))	{ throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_NOM_RACE);}
					if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_BONUS.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_BONUS);}
					if (e.getMessage().equals(DaoExceptionRBCMsg.ERR_VAL_BONUS.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.ERR_VAL_BONUS);}
					else {throw new UserExceptionRBC(UserExceptionRBCMsg.PB_INSERT_RACE);}
				}
			}

	}

	public void updatePassion(Passion passion) throws ExceptionService, UserExceptionRBC {
		// TODO faire les controles avant d'envoyer en DAO
		
		if (passion == null || passion.getNom() == null) {
			LOGGER.warn("Levée d'une exception dans service");
			throw new ExceptionService(Erreur.PASSION_NULL.message(), Erreur.PASSION_NULL.getCode());
		}
		passion.setNom(UtilDAO.modifNom(passion.getNom()));
		try {
			daoFacade.updatePassion(passion);
		} catch (DaoException e) {
			throw new ExceptionService();
	} catch (DaoExceptionRBC e) {
		if (e.getMessage().equals(DaoExceptionRBCMsg.RACE_NOM_INVALIDE.getMsg()))	{ throw new UserExceptionRBC(UserExceptionRBCMsg.RACE_NOM_INVALIDE);}
		if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_ID_RACE.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_ID_RACE);}
		if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_NOM_RACE.getMsg()))	{ throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_NOM_RACE);}
		if (e.getMessage().equals(DaoExceptionRBCMsg.DOUBLON_BONUS.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.DOUBLON_BONUS);}
		if (e.getMessage().equals(DaoExceptionRBCMsg.ERR_VAL_BONUS.getMsg()))		{ throw new UserExceptionRBC(UserExceptionRBCMsg.ERR_VAL_BONUS);}
		else {throw new UserExceptionRBC(UserExceptionRBCMsg.PB_INSERT_RACE);}
	}
	}

	public void delPassions() {

		daoFacade.delPassions();

	}

	public void delPassion(int refPassion) throws ExceptionService {
		// TODO Faire les controles
		if (refPassion <= 0) {
			throw new ExceptionService(Erreur.PASSION_ID_INCORRECT.message(), Erreur.PASSION_ID_INCORRECT.getCode());
		}
		try {
			daoFacade.delPassion(refPassion);
		} catch (DaoException e) {
			if (e.getCode() == 1) { // Code pour passion inexistant en base
				throw new ExceptionService(Erreur.PASSION_INEXISTANT.message(), Erreur.PASSION_INEXISTANT.getCode());
			}
			throw new ExceptionService();
		}
	}

	public void delPassion(String passion) throws ExceptionService {
		// TODO Faire les controles
		
		if (passion == null) {
			LOGGER.warn("Levée d'une exception");
			throw new ExceptionService(Erreur.PASSION_NULL.message(), Erreur.PASSION_NULL.getCode());
		}
		passion = UtilDAO.modifNom(passion);
		try {
			daoFacade.delPassion(passion);
		} catch (DaoException e) {
			throw new ExceptionService();
		}
	}
}
