package entity.armurerie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import clientServeur.IArme;
import entity.race_bonus_carac.race.Race;

@Entity
@Table (name="arme")
public class Arme implements IArme, Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="idArme")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_ARME")
	@SequenceGenerator(name = "SEQUENCE_ARME", sequenceName = "SEQUENCE_ARME", allocationSize = 1, initialValue = 1)
	private int idArme;
	
	@Column(name="nom", length=20, unique=true, nullable=false)
	private String nom;

	@Column(name="encomb", length=2, nullable=false)
	private int encombrement;
	
	@Column(name="prix", length=3, nullable=false)
	private int prix;
	
	@Column(name="monnaie", length=10, nullable=false)
	private String monnaie;
	
	@ManyToMany(cascade= {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinTable(	name="armerace", 
				joinColumns = @JoinColumn(name="idArme"),
				inverseJoinColumns = @JoinColumn(name="idRace"))
	private Collection<Race> races = new ArrayList<Race>();
	
	@OneToMany(mappedBy = "arme", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private Collection<ArmeJoueur> armeJoueur = new ArrayList<ArmeJoueur>();
	

	
//Constructeurs de la classe	
	public Arme() {
		super();
	}
	
	public Arme(String nom) {
		super();
		this.nom = nom;
	}
	
	public Arme(String nom, int encombrement, int prix, String monnaie) {
		super();
		this.nom = nom;
		this.encombrement = encombrement;
		this.prix = prix;
		this.monnaie = monnaie;
	}

	public Arme(int idArme, String nom, int encombrement, int prix, String monnaie) {
		super();
		this.idArme = idArme;
		this.nom = nom;
		this.encombrement = encombrement;
		this.prix = prix;
		this.monnaie = monnaie;
	}

	//Getters et Setters
	@Override
	public String getNom() {
		return nom;
	}
	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public int getEncombrement() {
		return encombrement;
	}
	@Override
	public void setEncombrement(int encombrement) {
		this.encombrement = encombrement;
	}
	@Override
	public int getPrix() {
		return prix;
	}
	@Override
	public void setPrix(int prix) {
		this.prix = prix;
	}
	@Override
	public String getMonnaie() {
		return monnaie;
	}
	@Override
	public void setMonnaie(String monnaie) {
		this.monnaie = monnaie;
	}
	public int getIdArme() {
		return idArme;
	}
	
	public void setIdArme(int idArme) {
		this.idArme = idArme;
	}

	public Collection<Race> getRaces() {
		return races;
	}

	public void setRaces(Collection<Race> races) {
		this.races = races;
	}

	public Collection<ArmeJoueur> getArmeJoueur() {
		return armeJoueur;
	}

	public void setArmeJoueur(Collection<ArmeJoueur> armeJoueur) {
		this.armeJoueur = armeJoueur;
	}

	public Arme getPersistBag() {
		Arme armePersistBag = new Arme(this.getIdArme(), this.getNom(), this.getEncombrement(), this.getPrix(), this.getMonnaie());
		System.out.println(armePersistBag.getNom());
		if (this.getRaces() != null) {
			List<Race> listeRaceArme = new ArrayList<Race>();
			for (Race race : this.getRaces()) {
				Race racePersistBag = race.dto();
				listeRaceArme.add(racePersistBag);
			}
			armePersistBag.setRaces(listeRaceArme);
		}
		return armePersistBag;
	}	



}
