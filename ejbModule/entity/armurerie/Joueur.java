package entity.armurerie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="joueur")
public class Joueur implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private int idJoueur;
	
	@Column (length = 20, nullable=false)
	private String nomJoueur;
	
	@OneToMany(mappedBy = "joueur", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private Collection<ArmeJoueur> armeJoueur = new ArrayList<ArmeJoueur>();
	
	public Joueur() {
		super();
	}

	public Joueur(int idJoueur, String nomJoueur) {
		super();
		this.idJoueur = idJoueur;
		this.nomJoueur = nomJoueur;
	}


	public int getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public Collection<ArmeJoueur> getArmeJoueur() {
		return armeJoueur;
	}

	public void setArmeJoueur(Collection<ArmeJoueur> armeJoueur) {
		this.armeJoueur = armeJoueur;
	}

	@Override
	public String toString() {
		return "Joueur [idJoueur=" + idJoueur + ", nomJoueur=" + nomJoueur + "]";
	}

}
