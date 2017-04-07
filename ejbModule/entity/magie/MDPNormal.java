package entity.magie;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@AttributeOverride(name="id_MotDePouvoir", column=@Column(name="id_MDPvoir_Norm"))
@Table(name ="MDPvoir_Normal")
public class MDPNormal extends MotDePouvoir{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public MDPNormal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param libelle
	 */
	public MDPNormal(int id, String libelle) {
		super(id, libelle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param libelle
	 */
	public MDPNormal(String libelle) {
		super(libelle);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Plusieurs mots de pouvoir normaux peuvent être relié à un même mot de pouvoir fondamental
	 * Si on supprime un mot de pouvoir normal on ne supprime pas un mot de pouvoir fondamental
	 */
	@ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_MDPvoir_Fond", nullable = false)
	private MDPFondamental motDPvoirFond;

	public MDPFondamental getMotDPvoirFond() {
		return motDPvoirFond;
	}

	public void setMotDPvoirFond(MDPFondamental motDPvoirFond) {
		this.motDPvoirFond = motDPvoirFond;
	}

	@Override
	public String toString() {
		return "MDPNormal [ id=" + getId() + ", nom="
				+ getLibelle() + "]" + motDPvoirFond.toString() + "]";
	}



	
	
	
}
