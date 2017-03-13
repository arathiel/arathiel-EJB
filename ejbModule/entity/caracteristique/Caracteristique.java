package entity.caracteristique;

import javax.persistence.Entity;
import javax.persistence.Id;

import clientServeur.bonus.IBonussable;

@Entity
public class Caracteristique implements IBonussable{
	
	@Id
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
	

}
