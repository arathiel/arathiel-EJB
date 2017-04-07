package dao.passionMagie.passion;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dao.passionMagie.exception.DaoException;
import dao.passionMagie.passion.consultation.PassionDaoConsultation;
import dao.passionMagie.passion.gestion.PassionDaoGestion;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import entity.passion.Passion;
import entity.race_bonus_carac.race.Race;

@LocalBean
@Singleton
public class FacadeDaoPassion {

	@EJB
	private PassionDaoGestion passionDaoGestion;

	@EJB
	private PassionDaoConsultation passionDaoConsult;

	
	public void addPassion(Passion passion) throws DaoException, DaoExceptionRBC {
	
		passionDaoGestion.addPassion(passion);
	}

	public void updatePassion(Passion passion) throws DaoException {
		
		passionDaoGestion.updatePassion(passion);
	}

	public void delPassion(int refPassion) throws DaoException {

		passionDaoGestion.delPassion(refPassion);

	}

	public void delPassions() {
		passionDaoGestion.delPassions();
		
	}
	
	public void delPassion(String passion) throws DaoException {

		passionDaoGestion.delPassion(passion);

	}

	public List<Passion> getPassionsTrieNom() {

		return passionDaoConsult.getPassionsTrieNom();
	}

	public List<Passion> getPassionsTrieRef() {

		return passionDaoConsult.getPassionsTrieRef();
	}

	public Passion getPassion(int refPassion) throws DaoException {

		return passionDaoConsult.getPassion(refPassion);
	}

	public Passion getPassion(String nom) throws DaoException {

		return passionDaoConsult.getPassion(nom);
	}

	public List<Race> getRaceLibre() {
		// TODO Auto-generated method stub
		return passionDaoConsult.getRaceLibre();
	}

	public List<Passion> getPassionsByLettres(String lettres) {
		// TODO Auto-generated method stub
		return passionDaoConsult.getPassionsByLettres(lettres);
	}

	

}
