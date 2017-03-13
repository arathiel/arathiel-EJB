package entity.trait;

import javax.persistence.Column;
import javax.persistence.Entity;

import entity.trait.comportement.Comportement;

@Entity
public class CompRoleplay extends Comportement {

	@Column(name = "rp_effet", length = 250)
	private String effet;
}
