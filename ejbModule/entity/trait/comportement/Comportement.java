package entity.trait.comportement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/**
 * Classe de persistance des comportements associ�s � un ou plusieurs traits
 * @author Jonathan Fuentes
 *
 */
@MappedSuperclass
public abstract class Comportement implements Serializable{

	// Attribut de classe un simple ID. Les classes filles poss�deront un attribut suppl�mentaire les d�finissant
	/**
	 * 
	 */
	@Version
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name="seqComp", sequenceName="comp_SEQ")
	@Column(name = "comp_id", length = 5)
	protected int id;

	@Column(name = "comp_effet", length = 250)
	protected String effet;
	
	
	/**
	 * Constructeur sans ID pour premier ajout dans la BDD (Id auto g�n�r� par Hibernate)
	 * @param effet
	 */
	public Comportement(String effet) {
		this.effet	= effet;
	}
	
	/**
	 * Constructeur avec Id pour manipulation en sorti de BDD
	 * @param id
	 * @param effet
	 */
	public Comportement(int id, String effet) {
		this.id 	= id;
		this.effet	= effet;
	}


}// Fin classe
