package entity.trait;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

import entity.trait.comportement.Comportement;
import technic.trait.Comportements;

/**
 * Classe de persistance de la fonctionnalité Trait
 * @author Jonathan Fuentes
 *
 */
@Entity
public class Trait implements Serializable{
	
	/**
	 * 
	 */
	@Version
	private static final long serialVersionUID = 1L;

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
	
	@ManyToMany(cascade= {CascadeType.PERSIST})
	@JoinTable(	name				= "trait_comp",
				joinColumns			= @JoinColumn(name = "id_trait"),
				inverseJoinColumns	= @JoinColumn(name = "id_comp"))			
	private Collection<Comportement> listComp = new Comportements();
	
	@Embedded
	@Column(name = "tr_desc", nullable = true)
	private Description 	description;

}// Fin classe
