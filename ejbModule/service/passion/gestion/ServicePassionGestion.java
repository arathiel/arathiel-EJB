package service.passion.gestion;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import clientServeur.exception.ExceptionService;
import dao.FacadeDAO;
import dao.passionMagie.exception.DaoException;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
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
	 * @throws DaoExceptionRBC 
	 */
	// ==> On doit pouvoir ajouter les mots de pouvoir à ce moment
	public void addPassion(Passion passion) throws ExceptionService, DaoExceptionRBC {

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
					if (e.getCode() == 2) {
						throw new ExceptionService(Erreur.PASSION_DOUBLON.message(), Erreur.PASSION_DOUBLON.getCode());
					}
					if (e.getCode() == 3) {
						throw new ExceptionService(Erreur.PASSION_NOM_OBLIGATOIRE.message(),
								Erreur.PASSION_NOM_OBLIGATOIRE.getCode());
					}
				}
			}

	}

	public void updatePassion(Passion passion) throws ExceptionService, DaoExceptionRBC {
		// TODO faire les controles avant d'envoyer en DAO
		
		if (passion == null || passion.getNom() == null) {
			LOGGER.warn("Levée d'une exception dans service");
			throw new ExceptionService(Erreur.PASSION_NULL.message(), Erreur.PASSION_NULL.getCode());
		}
		passion.setNom(UtilDAO.modifNom(passion.getNom()));
		try {
			daoFacade.updatePassion(passion);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ExceptionService();
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
