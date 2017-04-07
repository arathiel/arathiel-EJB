package dao.passionMagie.passion.consultation;

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
import entity.passion.Passion;
import entity.race_bonus_carac.race.Race;

@LocalBean
@Singleton
public class PassionDaoConsultation {

	@PersistenceContext(unitName = Parameter.UNITNAME_JUNONARATHIEL)
	EntityManager em;

	// TODO mettre en place les excexptions et les requêtes

	private static final Logger LOGGER = LogManager.getLogger();

	public List<Passion> getPassionsTrieNom() {
		List<Passion> passions = new ArrayList<>();
		for (Object p : em.createNamedQuery("Lister passions par nom").getResultList()) {
			if (p instanceof Passion) {
				LOGGER.warn("p est : {} ", () -> p);
				passions.add((Passion) p);
			}
		}
		return passions;
	}

	public List<Passion> getPassionsTrieRef() {
		List<Passion> passions = new ArrayList<>();
		for (Object p : em.createNamedQuery("Lister passions par ref").getResultList()) {
			if (p instanceof Passion) {
				LOGGER.warn("p est : {} ", () -> p);
				passions.add((Passion) p);
			}
		}
		return passions;
	}

	public Passion getPassion(int refPassion) throws DaoException {
		
		Passion passion = null;

		Object o = em.find(Passion.class, refPassion);
		
		if (o == null) {
			LOGGER.info("Passion est null");
			throw new DaoException(DaoErrorMessage.ERR_INEXISTANT);
		}else{
			if(o instanceof Passion){
				passion = UtilDAO.getDto((Passion) o);
			}
		}
		return passion;
	}

	public Passion getPassion(String nom) throws DaoException {
		Passion passion = null;
		UtilDAO.modifNom(nom);
		try {
			Object o = em.createNamedQuery("Recherche passion par nom").setParameter(1, nom).getSingleResult();
			if(o instanceof Passion){
				passion = UtilDAO.getDto((Passion) o);
			}
		} catch (NoResultException e) {
			throw new DaoException(DaoErrorMessage.ERR_INEXISTANT);
		}
		LOGGER.info("Retour : " + passion);
		return passion;
	}
	
	public List<Race> getRaceLibre(){
		LOGGER.info("Dans liste race");
		List<Race> races = new ArrayList<>();
		for (Object p : em.createNamedQuery("Lister les races non affectées à une passion").getResultList()) {
			LOGGER.info(p);
			if (p instanceof Race) {
				LOGGER.warn("p est : {} ", () -> p);
				races.add((Race) p);
			}
		}
		return races;

		
	}

	public List<Passion> getPassionsByLettres(String lettres) {
		List<Passion> passions = new ArrayList<>();
		lettres = (lettres + "%");
		LOGGER.info(lettres);
		for (Object o : em.createNamedQuery("Lister les passions par lettres")
				  .setParameter("lettres", lettres)
				  .getResultList()) {   
			if (o instanceof Passion){
				LOGGER.info(o.toString());
				passions.add((Passion)o);
			}
}
		
		return passions;
	}
}
