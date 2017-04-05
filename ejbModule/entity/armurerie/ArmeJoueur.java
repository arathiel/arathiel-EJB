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

import util.armurerie.Etat;
/**
 * Classe ArmeJoueur représentatnt la table d'association entre Arme et Joueur avec comme attribut Etat de l'arme.
 * Mappé en ManyToOne avec Arme et ManyToOne avec Joueur
 * @author OlivB
 *
 */

@Entity
@Table(name="armeJoueur")
public class ArmeJoueur implements Serializable{

	//Création d'un Id pour cette table composé de idArme, idJoueur et Etat. 1 seul etat par arme du joueur
	@Embeddable
	public static class IdArmeJoueur implements Serializable {

		private static final long serialVersionUID = 1L;
		@Column(name="idArme", nullable = false)
		private int idArme;

		@Column(name="idJoueur", nullable = false)
		private int idJoueur;
		
		//String etat afin de typer la colonne en Varchar2 (sinon number si private Etat etat)
		@Column(name="etat", nullable = false)
		private String etat;

		public IdArmeJoueur() {
			super();
		}

		public IdArmeJoueur(int idArme, int idJoueur, String etat) {
			super();
			this.idArme = idArme;
			this.idJoueur = idJoueur;
			this.etat = etat;
		}

		@Override
		public String toString() {
			return "IdArmeJoueur [idArme=" + idArme + ", idJoueur=" + idJoueur + ", etat=" + etat + "]";
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

		public String getEtat() {
			return etat;
		}

		public void setEtat(String etat) {
			this.etat = etat;
		}

	}
	//Suite de la classe ArmeJoueur
	private static final long serialVersionUID = 1L;

	//instanciation de la Primary Key composite
	@EmbeddedId
	private IdArmeJoueur idArmeJoueur = new IdArmeJoueur();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idArme", insertable = false, updatable = false)
	private Arme arme;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idJoueur", insertable = false, updatable = false)
	private Joueur joueur;

	//@Enumerated car Etat est un enum et non une classe
	@Enumerated(EnumType.STRING)
	@Column(length = 10, insertable = false, updatable = false, nullable = false)
	private Etat etat;

	public ArmeJoueur() {

		super();
	}

	public ArmeJoueur(Arme arme, Joueur joueur, Etat etat) {
		getIdArmeJoueur().setIdArme(arme.getIdArme());
		getIdArmeJoueur().setIdJoueur(joueur.getIdJoueur());
		getIdArmeJoueur().setEtat(Etat.getString(etat));

		this.setArme(arme);
		this.setJoueur(joueur);
		this.setEtat(etat);

		arme.getArmeJoueur().add(this);
		joueur.getArmeJoueur().add(this);
	}

	public ArmeJoueur(IdArmeJoueur idArmeJoueur, Arme arme, Joueur joueur, Etat etat) {
		super();
		this.idArmeJoueur = idArmeJoueur;
		this.arme = arme;
		this.joueur = joueur;
		this.etat = etat;
	}

	public Arme getArme() {
		return arme;
	}

	//Pour ajouter une arme dans l'instance de ArmeJoueur, nous devons créer idArmeJoueur si celui-ci est null
	public void setArme(Arme arme) {
		this.arme = arme;
		if (arme!= null) {
			if (idArmeJoueur==null) idArmeJoueur = new IdArmeJoueur();
			this.idArmeJoueur.setIdArme(arme.getIdArme());
		}

	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
		if (joueur!=null) {
			if (idArmeJoueur==null) idArmeJoueur = new IdArmeJoueur();
			this.idArmeJoueur.setIdJoueur(joueur.getIdJoueur());
		}

	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
		if (idArmeJoueur==null) idArmeJoueur = new IdArmeJoueur();
		this.idArmeJoueur.setEtat(Etat.getString(etat));
	}

	public IdArmeJoueur getIdArmeJoueur() {
		return idArmeJoueur;
	}

	public void setIdArmeJoueur(IdArmeJoueur idArmeJoueur) {
		this.idArmeJoueur = idArmeJoueur;
	}

	@Override
	public String toString() {
		return "ArmeJoueur [idArmeJoueur=" + idArmeJoueur + ", arme=" + arme + ", joueur=" + joueur + ", etat=" + etat
				+ "]";
	}

}
