package service.passion;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import clientServeur.exception.ExceptionService;
import dao.race_bonus_carac.exception.DaoExceptionRBC;
import entity.passion.Passion;
import entity.race_bonus_carac.race.Race;
import service.passion.consultation.ServicePassionConsultation;
import service.passion.gestion.ServicePassionGestion;

@LocalBean
@Singleton
public class FacadePassion {

//	private static final Logger LOGGER = LogManager.getLogger();
	
	@EJB
	ServicePassionGestion passionGest;
	
	@EJB
	ServicePassionConsultation passionConsult;
	
	public void addPassion(Passion passion) throws ExceptionService, DaoExceptionRBC {

		passionGest.addPassion(passion);
	}

	public void updatePassion(Passion passion) throws ExceptionService, DaoExceptionRBC {
	
		passionGest.updatePassion(passion);
	}

	public void delPassions() {

		passionGest.delPassions();
	}

	public void delPassion(int refPassion) throws ExceptionService {

		passionGest.delPassion(refPassion);
		
	}

	public void delPassion(String passion) throws ExceptionService {
	
		passionGest.delPassion(passion);
	}

	public List<Passion> getPassionsTrieNom() {
	
		return passionConsult.getPassionsTrieNom();
	}

	public List<Passion> getPassionsTrieRef() {
	
		return passionConsult.getPassionsTrieRef();
	}

	public Passion getPassion(int refPassion) throws ExceptionService {
	
		return passionConsult.getPassion(refPassion);
	}

	public Passion getPassion(String nom) throws ExceptionService {

		return passionConsult.getPassion(nom);
	}

	public List<Race> getRaceLibre() {
		// TODO Auto-generated method stub
		return passionConsult.getRaceLibre();
	}

	public List<Passion> getPassionsByLettres(String lettres) {

		return passionConsult.getPassionsByLettres(lettres);
	}

	
	

}
