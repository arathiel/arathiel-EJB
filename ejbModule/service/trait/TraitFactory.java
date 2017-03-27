package service.trait;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import clientServeur.exception.UserException;
import clientServeur.trait.EnrichisseurRP;
import dao.trait.ressources.Erreur;
import entity.trait.Description;
import entity.trait.Trait;
import entity.trait.comportement.Comportement;
import technic.trait.Comportements;

/**
 * Fabrique de trait. On pourrait y mettre divers traitements suppl�mentaire, des contr�les...
 * Par manque de temps, je n'ai pas r�p�t� le cont�le, mais la d�marche serait la m�me que pour la premi�re m�thode.
 * Ainsi, on respecte les r�gle de gestion : Un trait et un comportement doivent avoir un libell� !!
 * 
 * @author Jonathan Fuentes
 *
 */
@Stateless
@Remote(EnrichisseurRP.class)
public class TraitFactory implements EnrichisseurRP{
	
	private Trait 						trait;
	private String 						lib;
	private Comportement				comportement;
	private Comportements				listComportement;

	@Override
	public Trait creerTrait(String libelle, boolean visiPublic, boolean dispoCrea, boolean malus,
			Comportements listComp, Description description) throws UserException {
		
		//Instanciation du trait
		trait 				= null;
		lib					= null;
		comportement		= null;
		listComportement	= new Comportements();
		
			// Contr�le du libell� (Obligatoire)
			if (libelle != null) {
				if (libelle.isEmpty() != true) {
					
					//OK
					this.lib = libelle;
					
					// Contr�le de la liste de comp�tence et des comp�tences (Libell� obligatoire)
					if (listComp != null) {
						if (listComp.isEmpty() != true) {
							for (int i = 0; i < listComp.size(); i++) {
								Comportement comp = listComp.get(i);
								if (comp != null) {
									if (comp.getLibelle() != null) {
										if (comp.getLibelle().isEmpty() != true) {
											
											//OK
											comportement = comp;
											listComportement.add(comportement);
											
										}// Fin (comp.getLibelle().isEmpty() != true)
										else {
											throw new UserException(Erreur.COMP_LIBVIDE.getMessage());
										}
										
									}// Fin (comp.getLibelle() != null)
									else {
										throw new UserException(Erreur.COMP_LIBNULL.getMessage());
									}
									
								}// Fin if (comp != null)
							}// Fin for (Comportement comp : listComp)
						}// Fin if (listComp.isEmpty() != true)
					}// Fin if (listComp != null)
					
					// Finalement on construit le trait pour le renvoyer. On accepte une liste de comportement null
					trait = new Trait(lib, visiPublic, dispoCrea, malus, listComportement, description);
				}// Fin if(libelle.isEmpty() != true)
				else {
					throw new UserException(Erreur.TR_LIBVIDE.getMessage());
				}
			}// Fin if (libelle != null)
			else {
				throw new UserException(Erreur.TR_LIBNULL.getMessage());
			}
		return trait;
	}

	@Override
	public Trait creerTrait(String libelle, boolean visiPublic, boolean dispoCrea, boolean malus,
			Description description) {
		trait = null;
		
		trait = new Trait(libelle, visiPublic, dispoCrea, malus, description);
		
		return trait;
	}

	@Override
	public Trait creerTrait(String libelle, boolean visiPublic, boolean dispoCrea, boolean malus,
			Comportements listComp) {
		trait = null;
		
		trait = new Trait(libelle, visiPublic, dispoCrea, malus, listComp);
		
		return trait;
	}

	@Override
	public Trait creerTrait(String libelle, boolean visiPublic, boolean dispoCrea, boolean malus) {
		trait = null;
		
		trait = new Trait(libelle, visiPublic, dispoCrea, malus);
		
		return trait;
	}

}
