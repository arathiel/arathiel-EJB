package entity.magie;

import javax.persistence.Entity;

/**
 * Classe entity représentant la notion de mot de pouvoir fondamental
 * Elle hérite de la classe abstraite MotDePouvoir
 * 
 * RG: Un mot de pouvoir fondamental est relié à une ou plusieurs passions
 * 	   Un mot de pouvoir fondamental possède 4 mots de pouvoir normaux
 * @author Afpa
 *
 */

@Entity
public class MotDPFondamental extends MotDePouvoir {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
