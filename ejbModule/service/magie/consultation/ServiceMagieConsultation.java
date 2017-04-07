package service.magie.consultation;

import java.util.List;
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
public class ServiceMagieConsultation {

	private static final Logger LOGGER = LogManager.getLogger();

	@EJB
	FacadeDAO daoFacade;

	public List<MDPNormal> getMDPNormalTrieNom() {

		return daoFacade.getMDPNormalTrieNom();
	}

	public List<MDPNormal> getMDPNormalTrieRef() {

		return daoFacade.getMDPNormalTrieRef();
	}

	public List<MDPFondamental> getMDPFondamentalTrieNom() {

		return daoFacade.getMDPFondamentalTrieNom();
	}

	public List<MDPFondamental> getMDPFondamentalTrieRef() {

		return daoFacade.getMDPFondamentalTrieRef();
	}

	public MDPFondamental getMDPFondamental(int refMDPvoirFond) throws ExceptionService {
		if (refMDPvoirFond <= 0) {
			throw new ExceptionService(Erreur.MDPFOND_ID_INCORRECT.message(), Erreur.MDPFOND_ID_INCORRECT.getCode());
		}
		MDPFondamental mot = null;
		try {
			mot = daoFacade.getMDPFondamental(refMDPvoirFond);
		} catch (DaoException e) {
			if (e.getCode() == 1) {
				throw new ExceptionService(Erreur.MDPFOND_INEXISTANT.message(), Erreur.MDPFOND_INEXISTANT.getCode());
			}
		}

		return mot;
	}

	public MDPFondamental getMDPFondamental(String nom) throws ExceptionService {
		// TODO Vérfier méthodes
		MDPFondamental mot = null;

		if (nom == null) {
			// LOGGER.warn("Levée d'une exception");
			throw new ExceptionService(Erreur.MDPFOND_NULL.message(), Erreur.MDPFOND_NULL.getCode());
		}
		nom = UtilDAO.modifNom(nom);
		try {
			mot = daoFacade.getMDPFondamental(nom);
		} catch (DaoException e) {
			LOGGER.warn("Attention, levée d'exception, le mot n'existe pas en base");
			throw new ExceptionService(Erreur.MDPFOND_INEXISTANT.message(), Erreur.MDPFOND_INEXISTANT.getCode());
		}
		return mot;
	}

	public MDPNormal getMDPNormal(int refMDPvoirNorm) throws ExceptionService {
		MDPNormal mot = null;

		if (refMDPvoirNorm <= 0) {
			throw new ExceptionService(Erreur.MDPNORM_ID_INCORRECT.message(), Erreur.MDPNORM_ID_INCORRECT.getCode());
		}
		try {
			mot = daoFacade.getMDPNormal(refMDPvoirNorm);
		} catch (DaoException e) {
			throw new ExceptionService(Erreur.MDPNORM_INEXISTANT.message(), Erreur.MDPFOND_INEXISTANT.getCode());
		}
		return mot;
	}

	public MDPNormal getMDPNormal(String nom) throws ExceptionService {
		MDPNormal mot = null;

		if (nom == null) {
			// LOGGER.warn("Levée d'une exception");
			throw new ExceptionService(Erreur.MDPNORM_NULL.message(), Erreur.MDPNORM_NULL.getCode());
		}
		nom = UtilDAO.modifNom(nom);

		try {
			mot = daoFacade.getMDPNormal(nom);
		} catch (DaoException e) {
			throw new ExceptionService(Erreur.MDPNORM_INEXISTANT.message(), Erreur.MDPFOND_INEXISTANT.getCode());
		}
		return mot;
	}
}
