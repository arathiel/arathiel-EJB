package dao.passionMagie.magie.consultation;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.passionMagie.exception.DaoErrorMessage;
import dao.passionMagie.exception.DaoException;
import dao.util.Parameter;
import dao.util.passionMagie.UtilDAO;
import entity.magie.MDPFondamental;
import entity.magie.MDPNormal;

/**
 * Classe gérant les actions de 
 * 	- Recherche
 * 	- Liste
 * des concepts de Magie (mot de pouvoir) dans la base de données
 * @author Afpa
 *
 */
@LocalBean
@Singleton
public class MagieDaoConsultation {

	@PersistenceContext(unitName = Parameter.UNITNAME_JUNONARATHIEL)
	EntityManager em;
	
	private static final Logger LOGGER = LogManager.getLogger();

//TODO mettre en place les méthodes et les exceptions et les requêtes
	
	
	/**
	 * Méthode permettant de récupérer un mot de pouvoir fondamental de la base de données par référence
	 * @param refMDPvoirFond
	 * @return MDPFondamental
	 */
	public MDPFondamental getMDPFondamental(int refMDPvoirFond) throws DaoException {
			MDPFondamental mot = null;
			
			mot = em.find(MDPFondamental.class, refMDPvoirFond);
			
			if(mot == null){
			LOGGER.warn("Levée d'exception : le mot de pouvoir n'est pas en base");
			throw new DaoException(DaoErrorMessage.ERR_INEXISTANT);
		}
		return mot;
		}
		
	
	/**
	 * Méthode permettant de récupérer un mot de pouvoir fondamental de la base de données par nom
	 * @param nom
	 * @return MDPFondamental
	 * @throws DaoException 
	 */

	public MDPFondamental getMDPFondamental(String nom) throws DaoException {
		MDPFondamental mot = null;
		nom=UtilDAO.modifNom(nom);
		
		try{
			mot= (MDPFondamental) em.createNamedQuery("Recherche mot de pouvoir fond par nom").setParameter(1, nom).getSingleResult();
		}catch(NoResultException e){
			LOGGER.warn("Levée d'exception : le mot de pouvoir n'est pas en base");
			throw new DaoException(DaoErrorMessage.ERR_INEXISTANT);
		}
		return mot;
	}

	public MDPNormal getMDPNormal(int refMDPvoirNorm) throws DaoException {
		MDPNormal mot = null;
		
		try{
			mot = (MDPNormal)em.find(MDPNormal.class, refMDPvoirNorm);
		}catch(NoResultException e){
			LOGGER.warn("Levée d'exception : le mot de pouvoir n'est pas en base");
			throw new DaoException(DaoErrorMessage.ERR_INEXISTANT);
		}
		return mot;
	}

	public MDPNormal getMDPNormal(String nom) throws DaoException {
		MDPNormal mot = null;
		nom=UtilDAO.modifNom(nom);
		
		try{
			mot = (MDPNormal) em.createNamedQuery("Recherche mot de pouvoir normal par nom").setParameter(1, nom).getSingleResult();
		}catch(NoResultException e){
			LOGGER.warn("Levée d'exception : le mot de pouvoir n'est pas en base");
			throw new DaoException(DaoErrorMessage.ERR_INEXISTANT);
		}
		return mot;
	}

	public List<MDPNormal> getMDPNormalTrieNom() {
		List<MDPNormal> motsNorm = new ArrayList<>();
		for (Object p : em.createNamedQuery("Lister mots normaux par nom").getResultList()) {
			if (p instanceof MDPNormal) {
				LOGGER.warn("p est : {} ", () -> p);
				motsNorm.add((MDPNormal) p);
			}
		}
		return motsNorm;
	}

	public List<MDPNormal> getMDPNormalTrieRef() {
		List<MDPNormal> motsNorm = new ArrayList<>();
		for (Object p : em.createNamedQuery("Lister mots normaux par ref").getResultList()) {
			if (p instanceof MDPNormal) {
				LOGGER.warn("p est : {} ", () -> p);
				motsNorm.add((MDPNormal) p);
			}
		}
		return motsNorm;
	}

	public List<MDPFondamental> getMDPFondamentalTrieNom() {
		List<MDPFondamental> motsFond = new ArrayList<>();
		for (Object p : em.createNamedQuery("Lister mots fondamentaux par nom").getResultList()) {
			if (p instanceof MDPFondamental) {
				LOGGER.warn("p est : {} ", () -> p);
				motsFond.add((MDPFondamental) p);
			}
		}
		return motsFond;
	}

	public List<MDPFondamental> getMDPFondamentalTrieRef() {
		List<MDPFondamental> motsFond = new ArrayList<>();
		for (Object p : em.createNamedQuery("Lister mots fondamentaux par ref").getResultList()) {
			if (p instanceof MDPFondamental) {
				LOGGER.warn("p est : {} ", () -> p);
				motsFond.add((MDPFondamental) p);
			}
		}
		return motsFond;
	}


}
