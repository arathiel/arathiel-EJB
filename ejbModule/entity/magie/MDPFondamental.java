package entity.magie;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe représentant le concept de mot de pouvoir fondamental
 * Elle hérite de la classe MotDePouvoir
 * Elle ne possède pas de propriétés supplémentaires
 * RG:  un mot de pouvoir fondamental est relié à plusieurs passions
 * Une passion est reliée à plusieurs mots de pouvoir fondamentaux
 * 
 * @author Anaïs
 *
 */
@Entity
@Table(name= Parametres.TBL_MDP_FOND)
@AttributeOverride(name="id_MotDePouvoir", column=@Column(name="id_MDPvoir_Fond"))
public class MDPFondamental extends MotDePouvoir{

	private static final long serialVersionUID = 1L;
		
	public MDPFondamental() {
		super();
	}

	/**
	 * @param id
	 * @param libelle
	 */
	public MDPFondamental(int id, String libelle) {
		super(id, libelle);
	}

	/**
	 * @param libelle
	 */
	public MDPFondamental(String libelle) {
		super(libelle);
	}

	@Override
	public String toString() {
		return "MDPFondamental [id=" + getId() + ", nom="
				+ getLibelle() + "]";
	}



	
}
