package service.passion.consultation;

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
import entity.passion.Passion;
import entity.race_bonus_carac.race.Race;
import service.ressources.passionMagie.Erreur;


@LocalBean
@Singleton
public class ServicePassionConsultation {

	private static final Logger LOGGER = LogManager.getLogger();
	
	@EJB
	FacadeDAO daoFacade;

	public List<Passion> getPassionsTrieNom() {
		// TODO faire les controles et ajouter exception

		return daoFacade.getPassionsTrieNom();
	}

	public List<Passion> getPassionsTrieRef() {
		// TODO Faire les controles et remonter exception
	
		return daoFacade.getPassionsTrieRef();
	}

	public Passion getPassion(int refPassion) throws ExceptionService {
		// TODO Faire les controles et remonter exception
		Passion passion;
		
		if (refPassion <= 0) {
			throw new ExceptionService(Erreur.PASSION_ID_INCORRECT.message(), Erreur.PASSION_ID_INCORRECT.getCode());
		}
		try{
			passion = daoFacade.getPassion(refPassion);
		}catch(DaoException e){
			LOGGER.info(e.getMessage() + e.getCode());
			throw new ExceptionService();
		}
		return passion;
	}

	public Passion getPassion(String nom) throws ExceptionService {
		// TODO Faire controle et remonter exception
		if (nom == null) {
			throw new ExceptionService(Erreur.MDPFOND_NULL.message(), Erreur.MDPFOND_NULL.getCode());
		}
		nom = UtilDAO.modifNom(nom);
		try {
			return daoFacade.getPassion(nom);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ExceptionService();
		}
			}

	public List<Race> getRaceLibre() {
		return daoFacade.getRaceLibre();
	}

	public List<Passion> getPassionsByLettres(String lettres) {

		return daoFacade.getPassionsByLettres(lettres);
	}
	
}
