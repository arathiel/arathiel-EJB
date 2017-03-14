package dao.passion;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@LocalBean
@Singleton
public class FacadeDaoPassion {

	@PersistenceContext()
	EntityManager em;
	
	private static final Logger LOGGER = LogManager.getLogger();
	
}
