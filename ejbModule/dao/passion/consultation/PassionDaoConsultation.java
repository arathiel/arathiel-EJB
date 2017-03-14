package dao.passion.consultation;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@LocalBean
@Singleton
public class PassionDaoConsultation {

	@PersistenceContext()
	EntityManager em;
	
	private static final Logger LOGGER = LogManager.getLogger();
	
}
