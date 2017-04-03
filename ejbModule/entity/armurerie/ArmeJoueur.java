package entity.armurerie;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import armurerie.util.Etat;

@Entity
@Table(name="armeJoueur")
public class ArmeJoueur implements Serializable{
	
	@Embeddable
	public static class IdArmeJoueur implements Serializable {
		
		private static final long serialVersionUID = 1L;
		@Column(name="idArme", nullable = false)
		private int idArme;
		
		@Column(name="idJoueur", nullable = false)
		private int idJoueur;
		
		@Column(name="etat", nullable = false)
		private Etat etat;

		public IdArmeJoueur() {
			super();
		}

		public IdArmeJoueur(int idArme, int idJoueur, Etat etat) {
			super();
			this.idArme = idArme;
			this.idJoueur = idJoueur;
			this.etat = etat;
		}

		public int getIdArme() {
			return idArme;
		}

		public void setIdArme(int idArme) {
			this.idArme = idArme;
		}

		public int getIdJoueur() {
			return idJoueur;
		}

		public void setIdJoueur(int idJoueur) {
			this.idJoueur = idJoueur;
		}

		public Etat getEtat() {
			return etat;
		}

		public void setEtat(Etat etat) {
			this.etat = etat;
		}
				
	}
//Suite de la classe ArmeJoueur
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private IdArmeJoueur idArmeJoueur = new IdArmeJoueur();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idArme", insertable = false, updatable = false)
	private Arme arme;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idJoueur", insertable = false, updatable = false)
	private Joueur joueur;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10, insertable = false, updatable = false, nullable = false)
	private Etat etat;

	public ArmeJoueur() {
		super();
	}

	public ArmeJoueur(Arme arme, Joueur joueur, Etat etat) {
		getIdArmeJoueur().setIdArme(arme.getIdArme());
		getIdArmeJoueur().setIdJoueur(joueur.getIdJoueur());
		getIdArmeJoueur().setEtat(etat);
		
		this.setArme(arme);
		this.setJoueur(joueur);
		this.setEtat(etat);
		
		arme.getArmeJoueur().add(this);
		joueur.getArmeJoueur().add(this);
	}

	public Arme getArme() {
		return arme;
	}

	public void setArme(Arme arme) {
		this.arme = arme;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public IdArmeJoueur getIdArmeJoueur() {
		return idArmeJoueur;
	}

	public void setIdArmeJoueur(IdArmeJoueur idArmeJoueur) {
		this.idArmeJoueur = idArmeJoueur;
	}

}
