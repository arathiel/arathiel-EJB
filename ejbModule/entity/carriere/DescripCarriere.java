package entity.carriere;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Classe de description d'une carriere
 * @author Ismaël
 * 
 */

@Embeddable
public class DescripCarriere implements Serializable
{
	@Version
	private static final long serialVersionUID = 1L;

	@Column(name="description", length = 150)
	private String descriptif;
	
	/**
	 * Constructeur par défaut
	 */
	public DescripCarriere() 
	{
		super();
	}

	/**
	 *  Constructeur avec un parametre 
	 *	@param descriptif
	 */
	public DescripCarriere(String descriptif)
	{
		super();
		this.descriptif = descriptif;
	}

	/**
	 * Recuperation du descriptif
	 * @return descriptif
	 */
	public String getDescriptif() 
	{
		return descriptif;
	}

	/**
	 * Modification du descriptif
	 * @param descriptif
	 */
	public void setDescriptif(String descriptif)
	{
		this.descriptif = descriptif;
	}

	/**
	 * Affichage du descriptif
	 */
	@Override
	public String toString() 
	{
		return "[DescripCarriere :" + descriptif + "]";
	}

	public DescripCarriere add(DescripCarriere description) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
