package entity.magie;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import dao.util.passionMagie.Parametres;

/**
 * Classe repr�sentant le concept de mot de pouvoir fondamental
 * Elle h�rite de la classe MotDePouvoir
 * Elle ne poss�de pas de propri�t�s suppl�mentaires
 * RG:  un mot de pouvoir fondamental est reli� � plusieurs passions
 * Une passion est reli�e � plusieurs mots de pouvoir fondamentaux
 * 
 * @author Ana�s
 *
 */
@Entity
@Table(name=Parametres.TBL_MDP_FOND )
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
