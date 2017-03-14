package entity.trait.comportement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe de persistance des comportements associ�s � un ou plusieurs traits
 * @author Jonathan Fuentes
 *
 */
public abstract class Comportement {

	// Attribut de classe un simple ID. Les classes filles poss�deront un attribut suppl�mentaire les d�finissant
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comp_id", length = 5)
	protected int id;

	@Column(name = "comp_effet", length = 250)
	protected String effet;
	
	
//	/**
//	 * Constructeur sans ID pour premi�r ajout dans la BDD
//	 * @param effet
//	 */
//	public Comportement(String effet) {
//		this.effet	= effet;
//	}
//	
//	/**
//	 * Constructeur avec Id pour manipulation en sorti de BDD
//	 * @param id
//	 * @param effet
//	 */
//	public Comportement(int id, String effet) {
//		this.id 	= id;
//		this.effet	= effet;
//	}


}// Fin classe
