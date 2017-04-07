package dao.race_bonus_carac.param;

public enum Requetes {
	
	TOUTES_RACES ("select r from Race r order by r.nom asc"),
	RACE_PAR_NOM ("select r from Race r where lower(trim(r.nom)) = ?1"),
	DELETE_ARME_RACE ("delete from armerace where idRace = ?1"),
	DELETE_BONUS_RACE ("delete from bonus_race where idrace = ?1"),
	TOUS_BONUS ("select b from Bonus b order by b.id asc"),
	TOUTES_CARAC ("select c from Caracteristique c order by c.idCarac asc"),
	TOUTES_COMP ("select c from Competence c order by c.nom asc");
	
	
	String msg;
	
	Requetes(String msg){
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
