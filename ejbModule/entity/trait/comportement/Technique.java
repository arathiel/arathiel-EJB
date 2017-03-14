package entity.trait.comportement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

/**
 * Classe mère des comportements influençant numériquement le jeu
 * @author Jonathan Fuentes
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Technique extends Comportement implements Serializable{

	
	/**
	 * 
	 */
	@Version
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur avec Id pour manipulation en sorti de BDD
	 * @param id
	 * @param effet
	 */
	public Technique(int id, String effet) {
		super(id, effet);
	}
	
	/**
	 * Constructeur sans ID pour premier ajout dans la BDD (Id auto généré par Hibernate)
	 * @param effet
	 */
	public Technique(String effet) {
		super(effet);
	}
		
}
