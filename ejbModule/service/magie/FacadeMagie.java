package service.magie;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import clientServeur.exception.ExceptionService;
import entity.magie.MDPFondamental;
import entity.magie.MDPNormal;
import service.magie.consultation.ServiceMagieConsultation;
import service.magie.gestion.ServiceMagieGestion;

@LocalBean
@Singleton
public class FacadeMagie {

	@EJB
	ServiceMagieGestion magieGest;

	@EJB
	ServiceMagieConsultation magieConsult;

	public void addMDPFond(MDPFondamental mDPvoirFond) throws ExceptionService {
	
		magieGest.addMPFond(mDPvoirFond);

	}

	public void addMDPNorm(MDPNormal mDPvoirNorm) throws ExceptionService {

		magieGest.addMDPNorm(mDPvoirNorm);

	}

	public void updateMDPFond(MDPFondamental mDPvoirfond) throws ExceptionService {
		// TODO Auto-generated method stub

		magieGest.updateMDPFond(mDPvoirfond);

	}

	public void updateMDPNorm(MDPNormal mDPvoirNorm) throws ExceptionService {

		magieGest.updateMDPNorm(mDPvoirNorm);

	}

	public void delMDPFonds() {

		magieGest.delMDPFonds();

	}

	public void delMDPFond(int refMDPvoirFond) throws ExceptionService {

		magieGest.delMDPFond(refMDPvoirFond);

	}

	public void delMDPFond(MDPFondamental mDPvoirfond) throws ExceptionService {
		magieGest.delMDPFond(mDPvoirfond);

	}
	
	public void delMDPFond(String nom) throws ExceptionService {
		magieGest.delMDPFond(nom);
		
	}

	public void delMDPNorms() {

		magieGest.delMDPNorms();

	}

	public void delMDPNorm(int refMDPvoirNorm) throws ExceptionService {

		magieGest.delMDPNorm(refMDPvoirNorm);

	}

	public void delMDPNorm(MDPNormal mDPvoirNorm) throws ExceptionService {

		magieGest.delMDPNorm(mDPvoirNorm);
	}
	



	public void delMDPNorm(String nom) throws ExceptionService {
		magieGest.delMDPNorm(nom);
		
	}

	public List<MDPNormal> getMDPNormalTrieNom() {

		return magieConsult.getMDPNormalTrieNom();
	}

	public List<MDPNormal> getMDPNormalTrieRef() {

		return magieConsult.getMDPNormalTrieRef();
	}

	public List<MDPFondamental> getMDPFondamentalTrieNom() {

		return magieConsult.getMDPFondamentalTrieNom();
	}

	public List<MDPFondamental> getMDPFondamentalTrieRef() {

		return magieConsult.getMDPFondamentalTrieRef();
	}

	public MDPFondamental getMDPFondamental(int refMDPvoirFond) throws ExceptionService {

		return magieConsult.getMDPFondamental(refMDPvoirFond);
	}

	public MDPFondamental getMDPFondamental(String nom) throws ExceptionService {

		return magieConsult.getMDPFondamental(nom);
	}

	public MDPNormal getMDPNormal(int refMDPvoirNorm) throws ExceptionService {

		return magieConsult.getMDPNormal(refMDPvoirNorm);
	}

	public MDPNormal getMDPNormal(String nom) throws ExceptionService {

		return magieConsult.getMDPNormal(nom);
	}

}
