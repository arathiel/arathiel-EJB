package dao.passionMagie.magie.gestion;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dao.FacadeDAO;
import dao.passionMagie.exception.DaoErrorMessage;
import dao.passionMagie.exception.DaoException;
import dao.passionMagie.magie.consultation.MagieDaoConsultation;
import dao.passionMagie.param.Requetes;
import dao.util.Parameter;
import dao.util.passionMagie.UtilDAO;
import entity.magie.MDPFondamental;
import entity.magie.MDPNormal;
/**
 * Classe g�rant les actions de 
 *  	- Cr�ation
 *  	- Modification
 *  	- Suppression
 *  du concept de Magie (mot de pouvoir) en base de donn�es
 * @author Ana�s
 *
 */
@LocalBean
@Singleton
public class MagieDaoGestion {
	@EJB
	FacadeDAO daoFacade;

	@EJB
	MagieDaoConsultation magieConsult;

	@PersistenceContext(unitName = Parameter.UNITNAME_JUNONARATHIEL)
	EntityManager em;

	// TODO mettre en place les exceptions et les m�thodes
	// TODO cr�er des exceptions propres � la couche DAO

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * M�thode permettant d'ins�rer un mot de pouvoir fondamental
	 * 
	 * @author Ana�s
	 * @param motdePvoir
	 * @throws DaoException
	 * 
	 */
	public void addMDPFond(MDPFondamental mDPvoirFond) throws DaoException {
		// D'abord on v�rifie qu'il n'est pas en base
		String nom = mDPvoirFond.getLibelle();
		MDPFondamental mot = null;
		try {
			mot = magieConsult.getMDPFondamental(nom);
			// S'il n'est pas en base une DAO Exception est lev�e (voir m�thode)
		} catch (DaoException e1) {
			LOGGER.warn(e1.getMessage() + " " + e1.getCause());
			// A ce moment on peut essayer de le persister
			try {
				em.persist(mDPvoirFond);
				LOGGER.info("Le mot de pouvoir fondamental a bien �t� ins�r� en base");
			} catch (PersistenceException e) {
				LOGGER.info(e.getMessage() + " " + e.getCause());
			}
		}
		// Si le mot n'est pas nul, cela signifie qu'il existe dans la base de
		// donn�es...
		if (mot != null) {
			LOGGER.warn("Lev�e d'exception doublon");
			throw new DaoException(DaoErrorMessage.ERR_DOUBLON);
		}
	}

	/**
	 * M�thode permettant d'ins�rer un mot de pouvoir normal
	 * 
	 * @author Ana�s
	 * @param motdePvoir
	 * @throws DaoException
	 * 
	 */
	public void addMDPNorm(MDPNormal mDPvoirNorm) throws DaoException {
		String nom = mDPvoirNorm.getLibelle();
		MDPNormal motN = null;
		try {
			motN = daoFacade.getMDPNormal(nom);
		} catch (DaoException ed) {
			LOGGER.info("Le mot de pouvoir normal n'est pas en base");
			if (ed.getCode() == 1) {
				MDPFondamental motF = mDPvoirNorm.getMotDPvoirFond();
				String nomMotF = motF.getLibelle();
				try {
					MDPFondamental motFDAO; // Pour recherche en base de donn�es
					motFDAO = daoFacade.getMDPFondamental(nomMotF);
					LOGGER.info("Le mot de pouvoir est d�j� en base... ");
					mDPvoirNorm.setMotDPvoirFond(motFDAO);
					LOGGER.info("Le mot de pouvoir normal est maintenant : " + mDPvoirNorm.toString());
				} catch (DaoException e) {
					if (e.getCode() == 1) {
						LOGGER.info("Le mot de pouvoir n'est pas encore en base... On l'ins�re");
						daoFacade.addMDPFond(motF);
					}
				}
			}
			try {
				em.persist(mDPvoirNorm);
				LOGGER.info("Le mot de pouvoir normal est ins�r�");
			} catch (PersistenceException eP) {
				Throwable th = eP.getCause();
				LOGGER.info("exception: " + th.getMessage());
			}
		}
		if (motN != null) {
			LOGGER.warn("Le mot de pouvoir normal existe d�j�!");
			throw new DaoException(DaoErrorMessage.ERR_DOUBLON);
		}

	}

	/**
	 * M�thode permettant de modifier un mot de pouvoir fondamental
	 * 
	 * @author Ana�s
	 * @param motDePvoir
	 * @throws DaoException 
	 */
	public void updateMDPFond(MDPFondamental mDPvoirFond) throws DaoException {
	//TODO V�rifier la m�thode
		MDPFondamental mot ;
		mDPvoirFond.setLibelle(UtilDAO.modifNom(mDPvoirFond.getLibelle()));
		try {
			LOGGER.info(mDPvoirFond.getLibelle());
			mot = magieConsult.getMDPFondamental(mDPvoirFond.getLibelle());
			LOGGER.info("Le mot r�cup�r� est : " + mot);
			mDPvoirFond.setId(mot.getId());
			em.merge(mDPvoirFond);
			LOGGER.info("Le mot de pouvoir fondamental a bien �t� modifi�");
		} catch (PersistenceException e) {
			LOGGER.info(e.getMessage() + " " + e.getCause());
		}

	}

	/**
	 * M�thode permettant de modifier un mot de pouvoir normal
	 * 
	 * @param motDePvoir
	 * @throws DaoException 
	 */
	public void updateMDPNorm(MDPNormal mDPvoirNorm) throws DaoException {
		MDPNormal mot ;
		mDPvoirNorm.setLibelle(UtilDAO.modifNom(mDPvoirNorm.getLibelle()));
		try {
			mot= magieConsult.getMDPNormal(mDPvoirNorm.getLibelle());
			mDPvoirNorm.setId(mot.getId());
			em.merge(mDPvoirNorm);
			LOGGER.info("Le mot de pouvoir normal a bien �t� modifi�");
		} catch (DaoException e) {
			LOGGER.info(e.getMessage() + " " + e.getCause());
		}

	}

	/**
	 * M�thode permettant de supprimer un mot de pouvoir fondamental � partir de son id
	 * @param refMDPvoirFond
	 * @throws DaoException
	 */
	public void delMDPFond(int refMDPvoirFond) throws DaoException {
		MDPFondamental mot = null;
		List<MDPNormal> liste = new ArrayList<>();
		try {
			mot = magieConsult.getMDPFondamental(refMDPvoirFond);
			LOGGER.info("Le mot de pouvoir est : " + mot);
			for (Object o : em.createNamedQuery("Liste mots de pouvoir normaux pour un mot de pouvoir fond")
					.setParameter(1, mot.getId()).getResultList()) {
				LOGGER.info("Dans resultat requete");
				LOGGER.warn("o est : {} ", () -> o);
				if (o instanceof MDPNormal) {
					LOGGER.warn("o est : {} ", () -> o);
					liste.add((MDPNormal) o);
				}
			}
			for (MDPNormal motN : liste) {
				LOGGER.info(motN);
				delMDPNorm(motN);
			}
			int i = em.createNativeQuery(Requetes.DELETE_MAGIE_PASSION.getMsg()).setParameter(1, refMDPvoirFond)
					.executeUpdate();
			LOGGER.info(i);
			em.remove(mot);
		} catch (DaoException e) {
			LOGGER.warn(e.getCause() + " " + e.getMessage());
			throw new DaoException(DaoErrorMessage.ERR_INEXISTANT);
		}
	}

	/**
	 * M�thode permettant de supprimer le mot de pouvoir fondamental pass� en param�tre
	 * @param mDPvoirfond
	 * @throws DaoException
	 */
	public void delMDPFond(MDPFondamental mDPvoirfond) throws DaoException {
		MDPFondamental mot ;
		mDPvoirfond.setLibelle(UtilDAO.modifNom(mDPvoirfond.getLibelle()));
		try {
			mot = magieConsult.getMDPFondamental(mDPvoirfond.getLibelle());
			delMDPFond(mot.getId());
		} catch (DaoException e) {
			LOGGER.warn(e.getCause() + " " + e.getMessage());
			throw new DaoException(e.getCode());
		}
	}

	/**
	 * M�thode permettant de supprimer un mot de pouvoir fondamental � partir de son nom
	 * @param nom
	 * @throws DaoException
	 */
	public void delMDPFond(String nom) throws DaoException {
		MDPFondamental mot ;
		nom = UtilDAO.modifNom(nom);
		try{
			mot = magieConsult.getMDPFondamental(nom);
			delMDPFond(mot.getId());
		}catch(DaoException e){
			throw new DaoException(e.getCode());
		}
		
	}
	
	/**
	 * M�thode permettant de supprimer un mot de pouvoir � partir de son id 
	 * @param refMDPvoirNorm
	 * @throws DaoException
	 */
	public void delMDPNorm(int refMDPvoirNorm) throws DaoException {

		MDPNormal mot;
		try {
			mot = daoFacade.getMDPNormal(refMDPvoirNorm);
			em.remove(mot);
			LOGGER.info("Le mot de pouvoir normal a bien �t� modifi�");
		} catch (DaoException e) {
			LOGGER.warn(e.getCause() + " " + e.getMessage());
		}

	}

	/**
	 * M�thode permettant de supprimer le mot de pouvoir pass� en param�tres
	 * @param mDPvoirNorm
	 * @throws DaoException
	 */
	public void delMDPNorm(MDPNormal mDPvoirNorm) throws DaoException {
		try {
			LOGGER.info("Mot de pouvoir � supprimer " + mDPvoirNorm);
			em.remove(mDPvoirNorm);
		} catch (PersistenceException e) {
			LOGGER.warn(e.getMessage() + " " + e.getCause());
		}
	}
	
/**
 * M�thode permettant de supprimer un mot de pouvoir normal � partir du nom de celui-ci
 * @param nom
 * @throws DaoException
 */
	public void delMDPNorm(String nom) throws DaoException {
		MDPNormal mot;
		nom = UtilDAO.modifNom(nom);
		try{
			mot = magieConsult.getMDPNormal(nom);
			delMDPNorm(mot.getId());
		}catch(DaoException e){
			throw new DaoException(e.getCode());
		}
		
	}

	/**
	 * M�thode permettant de supprimer tous les mots de pouvoir fondamentaux de la base de donn�es
	 */
	public void delMDPFonds() {
		// On commence par supprimer tous les mots normaux :
		// Un mot de pouvoir normal ne peut pas exister sans �tre associ� � un
		// mot de pouvoir fondamental
		delMDPNorms();
		// On supprime tous les mots de pouvoir de l'association avec les
		// passions
		em.createNativeQuery(Requetes.DELETE_ALL_MAGIE_PASSION.getMsg()).executeUpdate();
		int i = em.createNamedQuery("Effacer tous les mots de pouvoir fondamentaux").executeUpdate();
		LOGGER.warn("Resultat requete = " + i);

	}

	/**
	 * M�thode permettant de supprimer l'ensemble des mots de pouvoir de la base de donn�es 
	 * 
	 */
	public void delMDPNorms() {
		int i = em.createNamedQuery("Effacer tous les mots de pouvoir normaux").executeUpdate();
		LOGGER.warn("Resultat requete = " + i);

	}



}
