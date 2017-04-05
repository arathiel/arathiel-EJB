package dao.armurerie.param;

public enum ArmurerieParam {
	
	SELECT_ARME ("select a from Arme a order by a.idArme asc"),
	SELECT_ARME_NOM ("select a from Arme a where a.nom = ?1"),
	SELECT_RACE ("select r from Race r"),
	SELECT_RACE_NOM ("select r from Race r where r.nomRace = ?1"),
	SELECT_JOUEUR ("select j from Joueur j order by j.idJoueur asc");
	
	private String requete;
	
	private ArmurerieParam (String requete) {
		this.requete = requete;
	}
	public String getRequete () {
		return requete;
	}
	public void setRequete (String requete) {
		this.requete = requete;
	}
	

}
