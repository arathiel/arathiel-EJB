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

	
	//Constructeur
	/**
	 * Constructeur par défaut
	 */
	public CompRoleplay() {
	}
	
	/**
	 * Constructeur avec Id pour manipulation en sorti de BDD
	 * @param id
	 * @param libelle
	 * @param effet
	 */
	public CompRoleplay(int id, String libelle) {
		super(id, libelle);
	}
	
	/**
	 * Constructeur sans ID pour premier ajout dans la BDD (Id auto généré par Hibernate)
	 * @param libelle
	 * @param effet
	 */
	public CompRoleplay(String libelle) {
		super(libelle);
	}
	
}
