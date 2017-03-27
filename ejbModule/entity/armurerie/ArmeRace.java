package entity.armurerie;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import entity.race_bonus_carac.race.Race;

/**
 * Cette classe contient la jointure entre Arme et Race.
 * Dans la BDD, cette classe permettra à rechercher les races selon les armes et inversement
 * @author OlivB
 *
 */
@Entity
public class ArmeRace implements Serializable{
// creation d'une class static IdArmeRace pour la création de la Primary Key à partir de l'Id de Arme et Race
	@Embeddable
	public static class IdArmeRace implements Serializable {

		private static final long serialVersionUID = 1L;
		
		@Column(name = "idArme", length=5, nullable = false)
		private int idArme;
		@Column(name = "idRace", length=5, nullable = false)
		private int idRace;
		
//Constructeurs de la classe IdArmeRace		
		public IdArmeRace() {
			super();
		}
		
		public IdArmeRace(int idArme, int idRace) {
			super();
			this.idArme = idArme;
			this.idRace = idRace;
		}
		
//getters et setters de la classe IdArmeRace
		public int getIdArme() {
			return idArme;
		}
		public void setIdArme(int idArme) {
			this.idArme = idArme;
		}
		public int getIdRace() {
			return idRace;
		}
		public void setIdRace(int idRace) {
			this.idRace = idRace;
		}

		
	}


	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private IdArmeRace idArmeRace = new IdArmeRace();
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idArme", insertable = false, updatable = false)
	private Arme arme;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idRace", insertable = false, updatable = false)
	private Race race;

// constructeurs de la classe ArmeRace
	public ArmeRace() {
		super();
	}
	

	public ArmeRace(Arme arme, Race race) {
		super();
		getIdArmeRace().setIdArme(arme.getIdArme());
		getIdArmeRace().setIdRace(race.getId());

		this.setArme(arme);
		this.setRace(race);
		
		arme.getRaces().add(this);
		race.getArmes().add(this);

	}

// getters et setters d ela classe ArmeRace
	public IdArmeRace getIdArmeRace() {
		return idArmeRace;
	}


	public void setIdArmeRace(IdArmeRace idArmeRace) {
		this.idArmeRace = idArmeRace;
	}


	public Arme getArme() {
		return arme;
	}


	public void setArme(Arme arme) {
		this.arme = arme;
	}


	public Race getRace() {
		return race;
	}


	public void setRace(Race race) {
		this.race = race;
	}

}
