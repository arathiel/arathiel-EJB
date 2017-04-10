package dao.passionMagie.passion.gestion;

import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dao.FacadeDAO;
import dao.passionMagie.exception.DaoErrorMessage;
import dao.passionMagie.exception.DaoException;
import dao.passionMagie.param.Requetes;
import dao.passionMagie.passion.consultation.PassionDaoConsultation;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import dao.util.Parameter;
import dao.util.passionMagie.UtilDAO;
import entity.magie.MDPFondamental;
import entity.passion.Passion;
import entity.race_bonus_carac.race.Race;

/**
 * Classe permettant l'acc�s � la base de donn�es pour les m�thodes de gestion
 * des passions Elle comprend les m�thodes Cr�er - Modifier - Supprimer
 * 
 * @author Ana�s
 *
 */
@LocalBean
@Singleton
public class PassionDaoGestion {

	@PersistenceContext(unitName = Parameter.UNITNAME_JUNONARATHIEL)
	EntityManager em;

	@EJB
	PassionDaoConsultation daoConsult;

	@EJB
	FacadeDAO daoFacade;

	// TODO mettre en place les exceptions

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * M�thode permettant d'ins�rer une passion
	 * 
	 * @author Ana�s
	 * @param passion
	 * @throws DaoException
	 * @throws DaoExceptionRBC 
	 */

	public void addPassion(Passion passion) throws DaoException, DaoExceptionRBC {

		/* Liste des mots de pouvoir de la passion � ins�rer*/
		Collection<MDPFondamental> liste = passion.getMagies(); 
		
		Passion pDAO = null; // Passion r�cup�r�e de la base de donn�es
		MDPFondamental motDAO; // Mot de pouvoir r�cup�r� de la base de donn�es
		String nomMot; // Nom d'un mot de pouvoir donn� de la passion
		Race race = null;
		Race raceHib = null;
		
		// Avant tout on fait un trim et un tolowerCase sur le nom
		passion.setNom(UtilDAO.modifNom(passion.getNom()));
		// Si le nom de la passion est null alors on l�ve une exception
		if (passion.getNom() == null) {
			throw new DaoException(DaoErrorMessage.ERR_NOM_NULL.getCode());
		}
		if (passion.getRace() != null) {
			race= passion.getRace();
			try {
				LOGGER.info("Race de la passion : " + race.getNom());
				//Race raceHib = (Race) em.createNamedQuery("Recherche race par nom").setParameter(1, race.getNom()).getSingleResult();
				raceHib = daoFacade.RechRaceParNom(race.getNom());
				passion.setRace(raceHib);
				LOGGER.info(passion);
			} catch (DaoExceptionRBC e) {
				
				daoFacade.insertRace(raceHib);
				//em.persist(race);
				}
			}
		try {
			// On v�rifie si la passion est en base
			pDAO = daoConsult.getPassion(passion.getNom());
		} catch (DaoException e) {
			// Si on catch une exception DAO et que le code est 1, c'est que la
			// passion n'est pas en base
			// LOGGER.info("La passion n'est pas en base");
			if (e.getCode() == 1) {
				// On cr�e une nouvelle liste de mots de pouvoir avec les mots
				// de pouvoir de la base de donn�es
				Collection<MDPFondamental> magiesB = new ArrayList<MDPFondamental>();

				for (MDPFondamental mot : liste) {
					nomMot = UtilDAO.modifNom(mot.getLibelle());

					try {
						motDAO = daoFacade.getMDPFondamental(nomMot);
						// Si pas d'exception, le mot est d�j� en base, donc on
						// le r�cup�re et on le met dans la nouvelle liste
						magiesB.add(motDAO);
					} catch (DaoException e1) {
						// Si exception avec code =1, le mot de pouvoir n'est
						// pas en base
						if (e1.getCode() == 1) {
							// Donc on l'insert en modifiant d'abord son nom
							// LOGGER.info("Le mot de pouvoir n'est pas encore
							// en base... On l'ins�re");
							mot.setLibelle(nomMot);
							daoFacade.addMDPFond(mot);
							// Puis on r�cup�re le mot en base afin de l'ajouter
							// � la liste
							mot = daoFacade.getMDPFondamental(nomMot);
							magiesB.add(mot);
						}
					}
				}
				// On modifie la liste de mots de pouvoir de la passion �
				// ins�rer
				passion.setMagies(magiesB);
			}
			try {
				em.persist(passion);
				LOGGER.info("La passion est ins�r�e");
			} catch (PersistenceException eP) {
				Throwable th = eP.getCause();
				LOGGER.info("exception: " + th.getMessage());
			}
		}
		if (pDAO != null) {
			LOGGER.warn("La passion existe d�j�!");
			throw new DaoException(DaoErrorMessage.ERR_DOUBLON);
		}
	}

	/**
	 * M�thode permettant de modifier une passion
	 * 
	 * @param passion
	 * @throws DaoExceptionRBC 
	 * @throws DaoException
	 */
	public void updatePassion(Passion passion) throws DaoExceptionRBC, DaoException {

		Passion passDAO = null; 				// Passion r�cup�r�e de la base de donn�es
		MDPFondamental motDAO = null; 			// Mot de pouvoir r�cup�r� de la base de1 donn�es
		Race raceHib = null; 					// Race r�cup�r�e de la base de donn�es
		String nomMot; 							// Nom d'un mot de pouvoir donn� de la passion

		passion.setNom(UtilDAO.modifNom(passion.getNom()));
		// Il faut r�cup�rer d'abord la passion de la base de donn�es avant de la modifier
		try {
			passDAO = daoConsult.getPassion(passion.getNom());
			// On r�cup�re l'id de la passion d�j� en base et on la set � sa modification
			passion.setId(passDAO.getId());
			
			Race race= passion.getRace();
			LOGGER.info("La race est : " + race);
			
			if (passion.getRace() != null) {
				race= passion.getRace();
				try {
					LOGGER.info("Race de la passion : " + race.getNom());
					//Race raceHib = (Race) em.createNamedQuery("Recherche race par nom").setParameter(1, race.getNom()).getSingleResult();
					raceHib = daoFacade.RechRaceParNom(race.getNom());
					passion.setRace(raceHib);
				} catch ( /*NoResultException |*/ DaoExceptionRBC e) {
					daoFacade.insertRace(raceHib);
					//em.persist(race);
					}
				}
			
			
			
//			if (race != null || race.getNom() != null || race.getId() != 0) {
//				try {
//					LOGGER.info("La race est : " + race);
//					raceHib = (Race) em.createNamedQuery("Recherche race par nom").setParameter(1, race.getNom()).getSingleResult();
//					passion.setRace(raceHib);
//				} catch (NoResultException e) {
//					em.persist(race);
//					}
//				}
			
			/* On supprime toutes les associations correspondant � cette passion
			 Sinon au moment de la modification de la passion on risque
			 d'avoir des associations qu'on ne veut plus */
			int i = em.createNativeQuery(Requetes.DELETE_MAGIE_PASSION_IDPASSION.getMsg())
					.setParameter(1, passion.getId()).executeUpdate();

			LOGGER.info("requete modification passion magie" + i);

			// On r�cup�re tous les mots de pouvoir de la passion � modifier
			for (MDPFondamental mot : passion.getMagies()) {
				nomMot = UtilDAO.modifNom(mot.getLibelle());
				// LOGGER.info(nom);

				try {
					// et on les recherches dans la base.
					motDAO = daoFacade.getMDPFondamental(nomMot);
					LOGGER.info(motDAO);
					// Si un mot n'existe pas une exception DAO est lev�e
				} catch (DaoException e) {
					// On ins�re alors ce nouveau mot en base
					daoFacade.addMDPFond(mot);
					// Et on le r�cup�re (l'id est auto g�n�r� par la base, si
					// on ne le faisait pas on aurait eu une exception
					// "Violation Constraint")
					// Puisque le pg aurait essay� de l'ins�rer quand m�me et le
					// nom du mot de pouvoir est unique
					motDAO = daoFacade.getMDPFondamental(nomMot);
				}

				// Une fois le mot r�cup�r� on insert l'association entre la
				// passion et le mot de pouvoir
				int n = em.createNativeQuery(Requetes.ADD_MAGIE_PASSION.getMsg()).setParameter(1, passion.getId())
						.setParameter(2, motDAO.getId()).executeUpdate();
				LOGGER.info("requete nouvelle association: " + n);
			}


			LOGGER.info("Nouvelle passion � update : " + passion);
		} catch (DaoException e) {
			LOGGER.info(e.getMessage());
		}

		try {
			// Puis on essaie de modifier la passion et la race (on a g�r� plus
			// haut l'association magie passion)
			em.createNamedQuery("Modifier la passion").setParameter(1, passion.getNom())
					.setParameter(2, passion.getDescription()).setParameter(3, passion.getRace())
					.setParameter(4, passion.getId()).executeUpdate();

		} catch (Exception e) {
			LOGGER.info(e.getCause().toString());

		}
	}

	/**
	 * M�thode permettant de supprimer une passion de la base de donn�es �
	 * partir d'une r�f�rence
	 * 
	 * @param refPassion
	 * @throws DaoException
	 */
	public void delPassion(int refPassion) throws DaoException {
		Passion passion;
		// On commence par rechercher l'objet en base correspondant � cette
		// r�f�rence
		try {
			passion = daoConsult.getPassion(refPassion);
			// Puis on le supprime
			em.remove(passion);
		} catch (DaoException e) {
			throw new DaoException(DaoErrorMessage.ERR_INEXISTANT.getCode());
		}

	}

	/**
	 * M�thode permettant de supprimer une passion de la base de donn�es �
	 * partir d'un nom de passion
	 * 
	 * @param nomPassion
	 * @throws DaoException
	 */
	public void delPassion(String nomPassion) throws DaoException {
		Passion passion;
		nomPassion = UtilDAO.modifNom(nomPassion);
		try {
			passion = daoConsult.getPassion(nomPassion);
			//Avant de supprimer une passion il faut supprimer l'association avec la magie.. Table PassionMagie
			
			int i = em.createNativeQuery(Requetes.DELETE_MAGIE_PASSION_IDPASSION.getMsg()).setParameter(1, passion.getId()).executeUpdate();
			LOGGER.info(" r�sultat update = " + i );	
			em.createNamedQuery("Supprimer une passion par nom").setParameter(1, nomPassion).executeUpdate();

		} catch (DaoException e) {
			throw new DaoException(DaoErrorMessage.ERR_INEXISTANT.getCode());
		}
	}

	/**
	 * M�thode pour supprimer l'ensemble des passions contenues dans la base de
	 * donn�es
	 */
	public void delPassions() {
		int i = em.createNamedQuery("Effacer toutes les passions").executeUpdate();
		LOGGER.warn("Resultat requete = " + i);
		// Aucune exception n'est remont�e!
	}

	public Race RechRaceParNom(String nom) throws DaoException {
		Race raceHib = null;
		
		if (nom!=null) {
			nom = nom.trim().toLowerCase();	
		
			try {
				raceHib = (Race) em.createQuery("Recherche race par nom").setParameter(1, nom).getSingleResult();
				LOGGER.info(raceHib);
			} catch(Exception e) {
				if (e instanceof javax.persistence.NoResultException) {
					throw new DaoException(DaoErrorMessage.ERR_NOM_NULL);
				}
			}
		}	
		return raceHib;
	}
}
