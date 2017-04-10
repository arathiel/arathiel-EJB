package dao.race_bonus_carac.bonus;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.race_bonus_carac.exception.DaoExceptionRBC;
import dao.race_bonus_carac.exception.DaoExceptionRBCMsg;
import dao.race_bonus_carac.param.Requetes;
import dao.util.Parameter;
import entity.race_bonus_carac.bonus.Bonus;
import entity.race_bonus_carac.bonus.BonusCarac;

/**
 * Classe qui va gérer la dao de l'entity {@link Bonus}
 * 
 * 
 * @author Francois Georgel
 *
 */
@LocalBean
@Singleton
public class DaoBonus{
	
	@PersistenceContext(unitName=Parameter.UNITNAME_JUNONARATHIEL)
	EntityManager em;
	
	/**
	 * Insertion des bonus
	 * 
	 * @param bonus
	 * @throws DaoExceptionRBC
	 */
	public void insertBonus(Bonus bonus) throws DaoExceptionRBC {
		try {	
			em.persist(bonus);
			em.flush();
			
		} catch (Exception e) {
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException){
				throw new DaoExceptionRBC(DaoExceptionRBCMsg.DOUBLON_BONUS);
			} else {
				throw new DaoExceptionRBC(DaoExceptionRBCMsg.PB_INSERT_BONUS);
			}
		}						
	}

	/**
	 * Methode de recupération de tous les bonus persistés
	 * 
	 * @return ArrayList<Bonus>
	 */
	public ArrayList<Bonus> listeTousBonus(){
		ArrayList<Bonus> liste = new ArrayList<Bonus>();
		
		for (Object r : em.createQuery(Requetes.TOUS_BONUS.getMsg()).getResultList()) {
			if (r instanceof Bonus){
				liste.add((Bonus) r);
			}
		}
		return liste;		
	}

	/**
	 * Methode de suppression d'un bonus
	 * Appelée lors de la suppression d'un {@link IPackable}
	 * 
	 * @param bonus
	 * @throws DaoExceptionRBC
	 */
	public void deleteBonus(Bonus bonus) throws DaoExceptionRBC{
		Bonus bonusHib = null;
		
		if(bonus != null) {
			bonusHib = em.find(Bonus.class, bonus.getIdBonus());
		} else {
			throw new DaoExceptionRBC(DaoExceptionRBCMsg.BONUS_NO_EXISTS);
		}
		
		if (bonusHib != null) {
			em.remove(bonusHib);
		} else {
			throw new DaoExceptionRBC(DaoExceptionRBCMsg.BONUS_NO_EXISTS);
		}
	}
	
	
	/**
	 * Methode qui verifie si un bonus est présent dans la base.
	 * Si le bonus n'existe pas, il est inséré
	 * 
	 * Cette méthode n'est appelée que lors de la création d'un {@link IPackable}
	 * 
	 * @param bonus
	 * @throws DaoExceptionRBC
	 */
	public void verifBonusPresent(Bonus bonus) throws DaoExceptionRBC{
		verifBonus(bonus);
		Bonus bonusHib;
		
		bonusHib = em.find(Bonus.class, bonus.getIdBonus());
		
		if (bonusHib == null){
			insertBonus(bonus);
		}
	}
	
	
	
	/**
	 * Methode qui verifie la validité d'un bonus 
	 * (pour le moment verifie uniquement si un bonus de type {@link Caracteristique} a une valeur non nulle)
	 * 
	 * @param bonus
	 * @throws DaoExceptionRBC
	 */
	public void verifBonus(Bonus bonus) throws DaoExceptionRBC {
		
		if (bonus==null) {
			throw new DaoExceptionRBC(DaoExceptionRBCMsg.BONUS_NULL);
		}
		if (bonus instanceof BonusCarac && bonus.getValeurBonus() == 0) {
			throw new DaoExceptionRBC(DaoExceptionRBCMsg.ERR_VAL_BONUS);
		}	
	}
}
