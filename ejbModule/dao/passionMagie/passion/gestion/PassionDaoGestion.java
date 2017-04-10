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
 * Classe permettant l'accès à la base de données pour les méthodes de gestion
 * des passions Elle comprend les méthodes Créer - Modifier - Supprimer
 * 
 * @author Anaïs
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
	 * Méthode permettant d'insérer une passion
	 * 
	 * @author Anaïs
	 * @param passion
	 * @throws DaoException
	 * @throws DaoExceptionRBC 
	 */

	public void addPassion(Passion passion) throws DaoException, DaoExceptionRBC {

		/* Liste des mots de pouvoir de la passion à insérer*/
		Collection<MDPFondamental> liste = passion.getMagies(); 
		
		Passion pDAO = null; // Passion récupérée de la base de données
		MDPFondamental motDAO; // Mot de pouvoir récupéré de la base de données
		String nomMot; // Nom d'un mot de pouvoir donné de la passion
		Race race = null;
		Race raceHib = null;
		
		// Avant tout on fait un trim et un tolowerCase sur le nom
		passion.setNom(UtilDAO.modifNom(passion.getNom()));
		// Si le nom de la passion est null alors on lève une exception
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
			// On vérifie si la passion est en base
			pDAO = daoConsult.getPassion(passion.getNom());
		} catch (DaoException e) {
			// Si on catch une exception DAO et que le code est 1, c'est que la
			// passion n'est pas en base
			// LOGGER.info("La passion n'est pas en base");
			if (e.getCode() == 1) {
				// On crée une nouvelle liste de mots de pouvoir avec les mots
				// de pouvoir de la base de données
				Collection<MDPFondamental> magiesB = new ArrayList<MDPFondamental>();

				for (MDPFondamental mot : liste) {
					nomMot = UtilDAO.modifNom(mot.getLibelle());

					try {
						motDAO = daoFacade.getMDPFondamental(nomMot);
						// Si pas d'exception, le mot est déjà en base, donc on
						// le récupère et on le met dans la nouvelle liste
						magiesB.add(motDAO);
					} catch (DaoException e1) {
						// Si exception avec code =1, le mot de pouvoir n'est
						// pas en base
						if (e1.getCode() == 1) {
							// Donc on l'insert en modifiant d'abord son nom
							// LOGGER.info("Le mot de pouvoir n'est pas encore
							// en base... On l'insère");
							mot.setLibelle(nomMot);
							daoFacade.addMDPFond(mot);
							// Puis on récupère le mot en base afin de l'ajouter
							// à la liste
							mot = daoFacade.getMDPFondamental(nomMot);
							magiesB.add(mot);
						}
					}
				}
				// On modifie la liste de mots de pouvoir de la passion à
				// insérer
				passion.setMagies(magiesB);
			}
			try {
				em.persist(passion);
				LOGGER.info("La passion est insérée");
			} catch (PersistenceException eP) {
				Throwable th = eP.getCause();
				LOGGER.info("exception: " + th.getMessage());
			}
		}
		if (pDAO != null) {
			LOGGER.warn("La passion existe déjà!");
			throw new DaoException(DaoErrorMessage.ERR_DOUBLON);
		}
	}

	/**
	 * Méthode permettant de modifier une passion
	 * 
	 * @param passion
	 * @throws DaoExceptionRBC 
	 * @throws DaoException
	 */
	public void updatePassion(Passion passion) throws DaoExceptionRBC, DaoException {

		Passion passDAO = null; 				// Passion récupérée de la base de données
		MDPFondamental motDAO = null; 			// Mot de pouvoir récupéré de la base de1 données
		Race raceHib = null; 					// Race récupérée de la base de données
		String nomMot; 							// Nom d'un mot de pouvoir donné de la passion

		passion.setNom(UtilDAO.modifNom(passion.getNom()));
		// Il faut récupérer d'abord la passion de la base de données avant de la modifier
		try {
			passDAO = daoConsult.getPassion(passion.getNom());
			// On récupère l'id de la passion déjà en base et on la set à sa modification
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
			
			/* On supprime toutes les associations correspondant à cette passion
			 Sinon au moment de la modification de la passion on risque
			 d'avoir des associations qu'on ne veut plus */
			int i = em.createNativeQuery(Requetes.DELETE_MAGIE_PASSION_IDPASSION.getMsg())
					.setParameter(1, passion.getId()).executeUpdate();

			LOGGER.info("requete modification passion magie" + i);

			// On récupère tous les mots de pouvoir de la passion à modifier
			for (MDPFondamental mot : passion.getMagies()) {
				nomMot = UtilDAO.modifNom(mot.getLibelle());
				// LOGGER.info(nom);

				try {
					// et on les recherches dans la base.
					motDAO = daoFacade.getMDPFondamental(nomMot);
					LOGGER.info(motDAO);
					// Si un mot n'existe pas une exception DAO est levée
				} catch (DaoException e) {
					// On insère alors ce nouveau mot en base
					daoFacade.addMDPFond(mot);
					// Et on le récupère (l'id est auto généré par la base, si
					// on ne le faisait pas on aurait eu une exception
					// "Violation Constraint")
					// Puisque le pg aurait essayé de l'insérer quand même et le
					// nom du mot de pouvoir est unique
					motDAO = daoFacade.getMDPFondamental(nomMot);
				}

				// Une fois le mot récupéré on insert l'association entre la
				// passion et le mot de pouvoir
				int n = em.createNativeQuery(Requetes.ADD_MAGIE_PASSION.getMsg()).setParameter(1, passion.getId())
						.setParameter(2, motDAO.getId()).executeUpdate();
				LOGGER.info("requete nouvelle association: " + n);
			}


			LOGGER.info("Nouvelle passion à update : " + passion);
		} catch (DaoException e) {
			LOGGER.info(e.getMessage());
		}

		try {
			// Puis on essaie de modifier la passion et la race (on a géré plus
			// haut l'association magie passion)
			em.createNamedQuery("Modifier la passion").setParameter(1, passion.getNom())
					.setParameter(2, passion.getDescription()).setParameter(3, passion.getRace())
					.setParameter(4, passion.getId()).executeUpdate();

		} catch (Exception e) {
			LOGGER.info(e.getCause().toString());

		}
	}

	/**
	 * Méthode permettant de supprimer une passion de la base de données à
	 * partir d'une référence
	 * 
	 * @param refPassion
	 * @throws DaoException
	 */
	public void delPassion(int refPassion) throws DaoException {
		Passion passion;
		// On commence par rechercher l'objet en base correspondant à cette
		// référence
		try {
			passion = daoConsult.getPassion(refPassion);
			// Puis on le supprime
			em.remove(passion);
		} catch (DaoException e) {
			throw new DaoException(DaoErrorMessage.ERR_INEXISTANT.getCode());
		}

	}

	/**
	 * Méthode permettant de supprimer une passion de la base de données à
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
			LOGGER.info(" résultat update = " + i );	
			em.createNamedQuery("Supprimer une passion par nom").setParameter(1, nomPassion).executeUpdate();

		} catch (DaoException e) {
			throw new DaoException(DaoErrorMessage.ERR_INEXISTANT.getCode());
		}
	}

	/**
	 * Méthode pour supprimer l'ensemble des passions contenues dans la base de
	 * données
	 */
	public void delPassions() {
		int i = em.createNamedQuery("Effacer toutes les passions").executeUpdate();
		LOGGER.warn("Resultat requete = " + i);
		// Aucune exception n'est remontée!
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
