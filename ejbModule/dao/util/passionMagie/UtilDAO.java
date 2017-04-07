package dao.util.passionMagie;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.magie.MDPFondamental;
import entity.magie.MotDePouvoir;
import entity.passion.Passion;

public class UtilDAO {

	private static final Logger LOGGER = LogManager.getLogger();
	/**
	 * Méthode permettant de transformer les paramètres nom des objets avant l'envoie en base
	 * @param nom
	 * @return 
	 */
	public static String modifNom(String nom){
		return nom.toLowerCase().trim();
	}
	
	
	public static Passion getDto (Passion passion) {
		LOGGER.info("d est : {} ", ()->passion);
		
		Passion passionDto = new Passion(passion.getId(), passion.getNom(), passion.getDescription());
		passionDto.setRace(passion.getRace());
		
		LOGGER.info("passionDto est : {} ", ()->passionDto);
		
		
// on ajoute les themes du persistantBag dans le nouveau docDto
		if (passion.getMagies() != null) {
			ArrayList<MDPFondamental> listeDto = new ArrayList<MDPFondamental>();
			Collection<MDPFondamental> lis = passion.getMagies();
			LOGGER.info("lis size est : {} ", ()->lis.size());
			for (MDPFondamental mot : lis) {
				LOGGER.warn("Le mot de pouvoir est : {} ", () -> mot);
								//ltheme.getTheme();
				MDPFondamental motDto = mot;			
				if(motDto instanceof MDPFondamental){
					LOGGER.info("objet de type MDPFondamental");
					listeDto.add(motDto);
				}
			}
			passionDto.setMagies(listeDto);
		}else{
				LOGGER.info("la liste est nulle");
			}

		
		return passionDto;
	}
	
}
