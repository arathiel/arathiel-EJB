package entity.trait.comportement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe de persistance des comportements associés à un ou plusieurs traits
 * @author Jonathan Fuentes
 *
 */
@Entity
public abstract class Comportement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comp_id", length = 5)
	private int id;
}
