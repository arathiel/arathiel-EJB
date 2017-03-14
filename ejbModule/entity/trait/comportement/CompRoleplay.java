package entity.trait.comportement;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Classe de persistence des comportement influençant le Roleplay
 * @author Jonathan Fuentes
 *
 */
@Entity
@AttributeOverride(name = "id", column=@Column(name="compRP_id"))
@Table(name = "comp_RP")
public class CompRoleplay extends Comportement implements Serializable{

	//Attributs
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
	public CompRoleplay(int id, String effet) {
		super(id, effet);
	}
	
	/**
	 * Constructeur sans ID pour premier ajout dans la BDD (Id auto généré par Hibernate)
	 * @param effet
	 */
	public CompRoleplay(String effet) {
		super(effet);
	}
	
}
