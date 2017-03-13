package entity.trait;

import javax.persistence.Entity;

import technic.trait.Comportements;

/**
 * Classe de persistance de la fonctionnalité Trait
 * @author Jonathan Fuentes
 *
 */
@Entity
public class Trait {
	
	// Attributs de classe
	private int				id;
	private	String			libelle;
	private boolean 		visiPublic;
	private boolean			dispoCrea;
	private boolean			malus;
	private Comportements	listComp; 

}
