package dao.passionMagie.magie;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.passionMagie.exception.DaoException;
import dao.passionMagie.magie.consultation.MagieDaoConsultation;
import dao.passionMagie.magie.gestion.MagieDaoGestion;
import entity.magie.MDPFondamental;
import entity.magie.MDPNormal;

/**
 * Classe repr�sentant la fa�ade d'entr�e dans la couche DAO concernant la
 * notion de magie Elle est utilis�e pour les concepts de Mot De Pouvoir
 * 
 * 
 * @author Ana�s
 *
 */
@LocalBean
@Singleton
public class FacadeDaoMagie {

	// TODO ajouter les m�thodes manquantes et les throws exception
	@EJB
	private MagieDaoGestion magieGestion;
	@EJB
	private MagieDaoConsultation magieConsult;


	/**************************************
	 * M�thode pour ajout * 
	 * @throws DaoException *
	 **************************************/

	public void addMDPFond(MDPFondamental mDPvoirFond) throws DaoException {

		magieGestion.addMDPFond(mDPvoirFond);
	}

	public void addMDPNorm(MDPNormal mDPvoirNorm) throws DaoException {

		magieGestion.addMDPNorm(mDPvoirNorm);
	}

	/**************************************
	 * M�thode pour modification *
	 **************************************/
	public void updateMDPFond(
			MDPFondamental mDPvoirFond) throws DaoException  {

		magieGestion.updateMDPFond(mDPvoirFond);
	}

	public void updateMDPNorm(MDPNormal mDPvoirNorm) throws DaoException {

		magieGestion.updateMDPNorm(mDPvoirNorm);
	}

	/**************************************
	 * M�thode pour suppression 
	 * @throws DaoException *
	 **************************************/

	public void delMDPFonds() {
		magieGestion.delMDPFonds();
		
	}
	

	public void delMDPNorms() {
		magieGestion.delMDPNorms();
		
	}
	
	public void delMDPFond(int refMDPvoirFond) throws DaoException {

		magieGestion.delMDPFond(refMDPvoirFond);

	}

	public void delMDPFond(MDPFondamental mDPvoirfond) throws DaoException {

		magieGestion.delMDPFond(mDPvoirfond);

	}

	public void delMDPFond(String nom) throws DaoException {
		magieGestion.delMDPFond(nom);
		
	}
	
	public void delMDPNorm(int refMDPvoirNorm) throws DaoException {

		magieGestion.delMDPNorm(refMDPvoirNorm);

	}

	public void delMDPNorm(MDPNormal mDPvoirNorm) throws DaoException {

		magieGestion.delMDPNorm(mDPvoirNorm);

	}
	
	public void delMDPNorm(String nom) throws DaoException {
		magieGestion.delMDPNorm(nom);
		
	}

	/**************************************
	 * M�thode pour lister *
	 **************************************/
	public List<MDPNormal> getMDPNormalTrieNom() {

		return magieConsult.getMDPNormalTrieNom();
	}

	public List<MDPNormal> getMDPNormalTrieRef() {
		// TODO Auto-generated method stub
		return magieConsult.getMDPNormalTrieRef();
	}

	public List<MDPFondamental> getMDPFondamentalTrieNom() {
		// TODO Auto-generated method stub
		return magieConsult.getMDPFondamentalTrieNom();
	}

	public List<MDPFondamental> getMDPFondamentalTrieRef() {
		// TODO Auto-generated method stub
		return magieConsult.getMDPFondamentalTrieRef();
	}

	/**************************************
	 * M�thode pour rechercher *
	 * 
	 * @throws DaoException
	 * *
	 **************************************/
	public MDPFondamental getMDPFondamental(int refMDPvoirFond) throws DaoException {

		return magieConsult.getMDPFondamental(refMDPvoirFond);
	}

	public MDPFondamental getMDPFondamental(String nom) throws DaoException {

		return magieConsult.getMDPFondamental(nom);
	}

	public MDPNormal getMDPNormal(int refMDPvoirNorm) throws DaoException {

		return magieConsult.getMDPNormal(refMDPvoirNorm);
	}

	public MDPNormal getMDPNormal(String nom) throws DaoException {

		return magieConsult.getMDPNormal(nom);
	}




}
