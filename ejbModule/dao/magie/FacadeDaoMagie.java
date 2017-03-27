package dao.magie;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dao.magie.consultation.MagieDaoConsultation;
import dao.magie.gestion.MagieDaoGestion;
import entity.magie.MotDePouvoir;

/**
 * Classe représentant la façade d'entrée dans la couche DAO concernant la
 * notion de magie Elle est utilisée pour les concepts de Mot De Pouvoir
 * 
 * 
 * @author Anaïs
 *
 */
@LocalBean
@Singleton
public class FacadeDaoMagie 
{
	@EJB
	private MagieDaoGestion magieGestion;
	@EJB
	private MagieDaoConsultation magieConsult;

	private static final Logger LOGGER = LogManager.getLogger();
	
	
	
	public void addMDPFond(MotDePouvoir motDePvoir) /*throws DaoException*/ {
		LOGGER.info("Dans méthode addMDPFond");
		magieGestion.addMDPFond(motDePvoir);			
	}

	
	public void updateMDPFond(MotDePouvoir motDePvoir) /*throws DaoException*/ {
		magieGestion.updateMDPFond(motDePvoir);
	}
	

}
