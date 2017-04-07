package service.magie.gestion;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import clientServeur.exception.ExceptionService;
import dao.FacadeDAO;
import dao.passionMagie.exception.DaoException;
import dao.util.passionMagie.UtilDAO;
import entity.magie.MDPFondamental;
import entity.magie.MDPNormal;
import service.ressources.passionMagie.Erreur;

@LocalBean
@Singleton
public class ServiceMagieGestion {

	@EJB
	FacadeDAO daoFacade;

	private static final Logger LOGGER = LogManager.getLogger();

	public void addMPFond(MDPFondamental mDPvoirFond) throws ExceptionService {
		// TODO faire les controles avant d'envoyer sur DAO!!
		if (mDPvoirFond.equals(null) || mDPvoirFond.getLibelle().equals(null)) {
			LOGGER.warn("Levée d'exception, le mot de pouvoir est null");
			throw new ExceptionService(Erreur.MDPFOND_NULL.message(), Erreur.MDPFOND_NULL.getCode());
		}
		mDPvoirFond.setLibelle(UtilDAO.modifNom(mDPvoirFond.getLibelle()));
		try {
			daoFacade.addMDPFond(mDPvoirFond);
		} catch (DaoException e) {
			if (e.getCode() == 2) {
				LOGGER.warn("Levée exception doublon");
				throw new ExceptionService(Erreur.MDPFOND_DOUBLON.message(), Erreur.MDPFOND_DOUBLON.getCode());
			}
		}
	}

	/**
	 * Méthode permettant d'ajouter un mot de pouvoir normal
	 * 
	 * RG: un mot de pouvoir normal est obligatoirement relié à un mot de
	 * pouvoir fondamental
	 * 
	 * @param mDPvoirNorm
	 * @throws ExceptionService
	 */
	public void addMDPNorm(MDPNormal mDPvoirNorm) throws ExceptionService {

		// TODO Faire les controles
		MDPFondamental mot = mDPvoirNorm.getMotDPvoirFond(); // Vérifier si le
		// mot normal
		// est bien
		// associé à un
		// mot fond
		if (mot == null) {
			LOGGER.warn("Attention, levée d'exception");
			throw new ExceptionService(Erreur.MDPNORM_MDPFOND_NAFFECTE.message(),
					Erreur.MDPNORM_MDPFOND_NAFFECTE.getCode());
			// Il faut obligatoirement le relié à un mot de pouvoir fondamental!
		}
		if (mDPvoirNorm.equals(null) || mDPvoirNorm.getLibelle().equals(null)) {
			LOGGER.warn("Levée d'exception, le mot de pouvoir est null");
			throw new ExceptionService(Erreur.MDPNORM_NULL.message(), Erreur.MDPNORM_NULL.getCode());
		}
		try {
			mDPvoirNorm.setLibelle(UtilDAO.modifNom(mDPvoirNorm.getLibelle()));
			daoFacade.addMDPNorm(mDPvoirNorm);
		} catch (DaoException e) {
			LOGGER.warn("Exception : " + e.getCode() + e.getMessage());
			if (e.getCode() == 2) {
				LOGGER.info("Levée exception doublon");
				throw new ExceptionService(Erreur.MDPNORM_DOUBLON.message(), Erreur.MDPNORM_DOUBLON.getCode());
			}
		}
	}

	public void updateMDPFond(MDPFondamental mDPvoirfond) throws ExceptionService {
		if (mDPvoirfond.equals(null) || mDPvoirfond.getLibelle().equals(null)) {
			LOGGER.warn("Levée d'exception, le mot de pouvoir est null");
			throw new ExceptionService(Erreur.MDPFOND_NULL.message(), Erreur.MDPFOND_NULL.getCode());
		}
		mDPvoirfond.setLibelle(UtilDAO.modifNom(mDPvoirfond.getLibelle()));
		try {
			daoFacade.updateMDPFond(mDPvoirfond);
		} catch (DaoException e) {
			throw new ExceptionService(e.getCode());
		}

	}

	public void updateMDPNorm(MDPNormal mDPvoirNorm) throws ExceptionService {
		// TODO Faire controle
		if (mDPvoirNorm.equals(null) || mDPvoirNorm.getLibelle().equals(null)) {
			LOGGER.warn("Levée d'exception, le mot de pouvoir est null");
			throw new ExceptionService(Erreur.MDPNORM_NULL.message(), Erreur.MDPNORM_NULL.getCode());
		}
		mDPvoirNorm.setLibelle(UtilDAO.modifNom(mDPvoirNorm.getLibelle()));
		try {
			daoFacade.updateMDPNorm(mDPvoirNorm);
		} catch (DaoException e) {
			throw new ExceptionService();
		}
	}

	public void delMDPFonds() {
		// TODO Faire controle

		daoFacade.delMDPFonds();
	}

	public void delMDPFond(int refMDPvoirFond) throws ExceptionService {
		// TODO Faire les controles
		if (refMDPvoirFond <= 0) {
			throw new ExceptionService(Erreur.MDPFOND_ID_INCORRECT.message(), Erreur.MDPFOND_ID_INCORRECT.getCode());
		}
		try {
			daoFacade.delMDPFond(refMDPvoirFond);
		} catch (DaoException e) {
			throw new ExceptionService(Erreur.MDPFOND_INEXISTANT.message(), Erreur.MDPFOND_INEXISTANT.getCode());
		}

	}

	public void delMDPFond(MDPFondamental mDPvoirfond) throws ExceptionService {
		if (mDPvoirfond.equals(null) || mDPvoirfond.getLibelle().equals(null)) {
			LOGGER.warn("Levée d'exception, le mot de pouvoir est null");
			throw new ExceptionService(Erreur.MDPFOND_NULL.message(), Erreur.MDPFOND_NULL.getCode());
		}

		try {
			daoFacade.delMDPFond(mDPvoirfond);
		} catch (DaoException e) {
			throw new ExceptionService(Erreur.MDPFOND_INEXISTANT.message(), Erreur.MDPFOND_INEXISTANT.getCode());
		}

	}

	public void delMDPFond(String nom) throws ExceptionService {

		if (nom == null) {
			throw new ExceptionService(Erreur.MDPFOND_NULL.message(), Erreur.MDPFOND_NULL.getCode());
		}
		nom = UtilDAO.modifNom(nom);
		try {
			daoFacade.delMDPFond(nom);
		} catch (DaoException e) {
			throw new ExceptionService(Erreur.MDPNORM_INEXISTANT.message(), Erreur.MDPNORM_INEXISTANT.getCode());
		}
	}

	public void delMDPNorms() {

		daoFacade.delMDPNorms();

	}

	public void delMDPNorm(int refMDPvoirNorm) throws ExceptionService {
		if (refMDPvoirNorm <= 0) {
			throw new ExceptionService(Erreur.MDPNORM_ID_INCORRECT.message(), Erreur.MDPNORM_ID_INCORRECT.getCode());
		}
		try {
			daoFacade.delMDPNorm(refMDPvoirNorm);
		} catch (DaoException e) {
			throw new ExceptionService(Erreur.MDPNORM_INEXISTANT.message(), Erreur.MDPNORM_INEXISTANT.getCode());
		}
	}

	public void delMDPNorm(MDPNormal mDPvoirNorm) throws ExceptionService {
		if (mDPvoirNorm.equals(null) || mDPvoirNorm.getLibelle().equals(null)) {
			LOGGER.warn("Levée d'exception, le mot de pouvoir est null");
			throw new ExceptionService(Erreur.MDPNORM_NULL.message(), Erreur.MDPNORM_NULL.getCode());
		}
		try {
			daoFacade.delMDPNorm(mDPvoirNorm);
		} catch (DaoException e) {
			throw new ExceptionService(Erreur.MDPNORM_INEXISTANT.message(), Erreur.MDPNORM_INEXISTANT.getCode());
		}

	}

	public void delMDPNorm(String nom) throws ExceptionService {

		if (nom == null) {
			throw new ExceptionService(Erreur.MDPNORM_NULL.message(), Erreur.MDPNORM_NULL.getCode());
		}
		nom = UtilDAO.modifNom(nom);
		try {
			daoFacade.delMDPNorm(nom);
		} catch (DaoException e) {
			throw new ExceptionService(Erreur.MDPNORM_INEXISTANT.message(), Erreur.MDPNORM_INEXISTANT.getCode());
		}
	}

}
