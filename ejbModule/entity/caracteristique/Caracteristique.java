package entity.caracteristique;

import javax.persistence.Entity;
import javax.persistence.Id;

import clientServeur.bonus.IBonussable;

/** 
 * Classe 
 * 
 * N.B. cette classe qui doit pourvoir �tre modifiable par la suite dans l'appli web n'est pour le moment pas
 * �ditable. 
 * La table, cr�e au moment du lancement du serveur sera compl�t�e "� la main"
 * 
 * 
 * @author Fran�ois Georgel
 *
 */

@Entity
public class Caracteristique implements IBonussable{
	
	@Id
	private String nomCarac;
	private String idCarac;
	private String aspect;
	private String qualite;
	
	
	public String getIdCarac() {
		return idCarac;
	}
	public void setIdCarac(String idCarac) {
		this.idCarac = idCarac;
	}
	public String getAspect() {
		return aspect;
	}
	public void setAspect(String aspect) {
		this.aspect = aspect;
	}
	public String getQualite() {
		return qualite;
	}
	public void setQualite(String qualite) {
		this.qualite = qualite;
	}
	
	public String getNomCarac() {
		return nomCarac;
	}
	public void setNomCarac(String nomCarac) {
		this.nomCarac = nomCarac;
	}
}
