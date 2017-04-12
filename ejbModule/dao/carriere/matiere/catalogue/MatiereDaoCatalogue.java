package dao.carriere.matiere.catalogue;

import javax.ejb.*;
import javax.persistence.*;

import dao.carriere.exception.InexistantExceptionCarriere;
import dao.util.carriere.UtilBdD;
import entity.carriere.matiere.Matiere;
import entity.carriere.util.Matieres;
import util.carriere.ReqSQLGestion;


@LocalBean
@Singleton
public class MatiereDaoCatalogue 
{
	@PersistenceContext(unitName=UtilBdD.PERSISTANCE_UNITNAME)
	EntityManager em;

	/**
	 * 
	 * @return matiere
	 */
	public Matieres lstMatieres() 
	{
		Matieres matiere = new Matieres();
		for(Object o: em.createQuery(ReqSQLGestion.GET_MATIERE).getResultList())
		{
			if(o instanceof Matiere) 
				matiere.add((Matiere) o);
		}
		return matiere;
	}

	/**
	 * Methode permettant le retour d'une matiere par le nom
	 * @param nomMatiere
	 * @return matiere
	 * @throws InexistantExceptionCarriere
	 */
	public Matiere getMatiereParNom(String nomMatiere) throws InexistantExceptionCarriere
	{
		Matiere matiere = null;
		
		Query query = em.createQuery(ReqSQLGestion.MATIERE_PAR_NOM);
		query.setParameter(1, nomMatiere);
		matiere = (Matiere) query.getSingleResult();
		
		return matiere;
	} 
	

	/**
	 * Methode permettant le retour d'une matiere par l'id
	 * @param id
	 * @return matiere
	 * @throws InexistantExceptionCarriere
	 */
	public Matiere getMatiere(int id) throws InexistantExceptionCarriere
	{
		Matiere matiere = null;
		matiere = em.find(Matiere.class, id);
		return matiere;
	}

}
