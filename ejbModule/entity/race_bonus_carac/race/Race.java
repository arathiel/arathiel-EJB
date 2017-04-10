package entity.race_bonus_carac.race;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import clientServeur.race_bonus_carac.interfaces.IPackable;
import entity.armurerie.Arme;
import entity.race_bonus_carac.bonus.Bonus;
import parametres.race_bonus_carac.bonus.ParamIPackable;

/**
 * Entity qui reflète les races du jeu
 * 
 * @author francois Georgel
 *
 */
@Entity
public class Race implements IPackable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_id_race")
	@SequenceGenerator(name="seq_id_race", sequenceName="ID_RACE_SEQ", initialValue=1, allocationSize=1)
	private int id;
	
	@Column(name="nom_race", unique=true, nullable=false,  length=40)
	private String nom;
	
	@Column(nullable = false)
	private boolean dispo = false;
	
	@Column(nullable = false)
	private int coutXp = ParamIPackable.COUT_XP_RACE;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable (name="bonus_race",
				joinColumns = @JoinColumn(name = "idRace"),
				inverseJoinColumns = @JoinColumn(name= "idBonus"))
	private Collection<Bonus> listeBonus = new ArrayList<Bonus>(); 


	
	//Constructeurs
	public Race(){
	}
	
	
	public Race (int id, String nom, boolean dispo) {
		this.id = id;
		this.nom = nom;
		this.dispo = dispo;
	}
	
	
	public Race (String nom, boolean dispo){
		this.nom 	= nom;
		this.dispo 	= dispo;
	}
	
	
	//Assesseurs
	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public void setDispo(boolean dispo) {
		this.dispo = dispo;		
	}

	@Override
	public void setCoutXp(int coutXp) {
		this.coutXp = coutXp;		
	}

	@Override
	public void setListeBonus(Collection<Bonus> listeBonus) {
		this.listeBonus = listeBonus;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public boolean isDispo() {
		return dispo;
	}

	@Override
	public int getCoutXp() {
		return coutXp;
	}

	@Override
	public Collection<Bonus> getListeBonus() {
		return this.listeBonus;
	}

	/**Methode de calcul du montant d'xp rendu par cette race en fonction de la liste de ses bonus
	 * 
	 * @return renduXp valeur des point d'xp rendus
	 */
	@Override
	public int calculRenduXp() {
		int renduXp = this.getCoutXp();
		
		for (Bonus b: this.getListeBonus()){
			renduXp = renduXp - b.getCoutXp();
		}
		return renduXp;
	}

	@Override
	public void ajouterBonus(Bonus bonus) {
		this.listeBonus.add(bonus);		
	}

	@Override
	public void enleverBonus(Bonus bonus) {
		this.listeBonus.remove(bonus);		
	}
	
	@Override
	public String toString() {
		return ("Id: "+this.getId()+" Nom: "+this.getNom()+" Dispo: "+this.isDispo()+" Nb Bonus: "+this.getListeBonus().size());
	}
	
/**
 * Methode qui reconstruit un objet Race avec une arrayList de bonus
 * à partir d'un objet Race hibernate
 * 
 * @return raceDto : objet Race reconstruit
 */
	public Race dto() {
		
		Race raceDto = new Race(this.getId(), this.getNom(), this.isDispo());
	
		//On ajoute les bonus à la raceDto
		if (this.getListeBonus() != null) {
			ArrayList<Bonus> listeDto = new ArrayList<Bonus>();
			for (Bonus b : this.getListeBonus()) {
				Bonus bonus = b.dto();
				listeDto.add(bonus);
			}
			raceDto.setListeBonus(listeDto);
		}
		return raceDto;
	}

}
