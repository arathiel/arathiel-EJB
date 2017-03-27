package entity.armurerie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Arme implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="idArme")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_ARME")
	@SequenceGenerator(name = "SEQUENCE_ARME", sequenceName = "SEQUENCE_ARME", allocationSize = 1, initialValue = 1)
	private int idArme;
	
	@Column(name="nomArme", length=20, nullable=false)
	private String nom;

	@Column(name="encomb", length=2, nullable=false)
	private int encombrement;
	
	@Column(name="prix", length=3, nullable=false)
	private int prix;
	
	@Column(name="monnaie", length=10, nullable=false)
	private String monnaie;
	
	@OneToMany(mappedBy="arme", cascade= {CascadeType.ALL}, fetch=FetchType.LAZY)
	private Collection <ArmeRace> races = new ArrayList<ArmeRace>();
	
//Constructeurs de la classe	
	public Arme() {
		super();
	}
	
	public Arme(String nom, int encombrement, int prix, String monnaie) {
		super();
		this.nom = nom;
		this.encombrement = encombrement;
		this.prix = prix;
		this.monnaie = monnaie;
	}

	//Getters et Setters
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getEncombrement() {
		return encombrement;
	}
	public void setEncombrement(int encombrement) {
		this.encombrement = encombrement;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public String getMonnaie() {
		return monnaie;
	}
	public void setMonnaie(String monnaie) {
		this.monnaie = monnaie;
	}

	public int getIdArme() {
		return idArme;
	}

	public void setIdArme(int idArme) {
		this.idArme = idArme;
	}

	public Collection<ArmeRace> getRaces() {
		return races;
	}

	public void setRaces(Collection<ArmeRace> races) {
		this.races = races;
	}
}
