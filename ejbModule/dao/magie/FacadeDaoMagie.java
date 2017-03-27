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
 * Classe repr�sentant la fa�ade d'entr�e dans la couche DAO concernant la
 * notion de magie Elle est utilis�e pour les concepts de Mot De Pouvoir
 * 
 * 
 * @author Ana�s
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
		LOGGER.info("Dans m�thode addMDPFond");
		magieGestion.addMDPFond(motDePvoir);			
	}

	
	public void updateMDPFond(MotDePouvoir motDePvoir) /*throws DaoException*/ {
		magieGestion.updateMDPFond(motDePvoir);
	}
	

}
