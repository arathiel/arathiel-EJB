package entity.trait;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import technic.trait.Comportements;

/**
 * Classe de persistance de la fonctionnalité Trait
 * @author Jonathan Fuentes
 *
 */
@Entity
public class Trait {
	
	// Attributs de classe
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tr_id", length = 5)
	private int				id;
	
	@Column(name = "tr_lib", length = 50, nullable = false)
	private	String			libelle;
	
	@Column(name = "tr_pub", nullable = false)
	private boolean 		visiPublic;
	
	@Column(name = "tr_crea", nullable = false)
	private boolean			dispoCrea;
	
	@Column(name = "tr_malus", nullable = false)
	private boolean			malus;
	
	@Column(name = "tr_comp")
	private Comportements	listComp;
	
	@Embedded
	@Column(name = "tr_desc")
	private Description		description;

}
