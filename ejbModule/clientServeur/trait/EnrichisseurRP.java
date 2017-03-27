package clientServeur.trait;

import clientServeur.exception.UserException;
import entity.trait.Description;
import entity.trait.Trait;
import technic.trait.Comportements;

/**
 * Service de fabrique pour les traits
 * @author Jonathan Fuentes
 *
 */
public interface EnrichisseurRP {
	
	/**
	 * Retourne un trait complet
	 * @param libelle
	 * @param visiPublic
	 * @param dispoCrea
	 * @param malus
	 * @param listComp
	 * @param description
	 * @return
	 * @throws UserException 
	 */
	public Trait creerTrait (String libelle, boolean visiPublic, boolean dispoCrea, boolean malus,
			Comportements listComp, Description description) throws UserException;
	
	/**
	 * Retourne un trait sans comportement
	 * @param libelle
	 * @param visiPublic
	 * @param dispoCrea
	 * @param malus
	 * @param description
	 * @return
	 */
	public Trait creerTrait (String libelle, boolean visiPublic, boolean dispoCrea, boolean malus, 
			Description description);
	
	/**
	 * Retourne un trait sans description
	 * @param libelle
	 * @param visiPublic
	 * @param dispoCrea
	 * @param malus
	 * @param listComp
	 * @return
	 */
	public Trait creerTrait (String libelle, boolean visiPublic, boolean dispoCrea, boolean malus,
			Comportements listComp);
	
	/**
	 * Retourne un trait sans comportement ni description
	 * @param libelle
	 * @param visiPublic
	 * @param dispoCrea
	 * @param malus
	 * @return
	 */
	public Trait creerTrait (String libelle, boolean visiPublic, boolean dispoCrea, boolean malus);
}
