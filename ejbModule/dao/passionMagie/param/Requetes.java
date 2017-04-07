package dao.passionMagie.param;

public enum Requetes {
	
	
	DELETE_MAGIE_PASSION ("delete from passionmagie where id_mdpfondamental = ?1"),
	DELETE_ALL_MAGIE_PASSION ("delete from passionmagie"),
	DELETE_MAGIE_PASSION_IDPASSION("delete from passionmagie where idpassion = ?1"),
	ADD_MAGIE_PASSION("insert into passionmagie values(?1 , ?2)"),
	GET_RACE_NONAFFECTEE("select * from race where race.id not in(select race.id from race, passion where race.id = passion.IDRACE)"),
	PASSION_LIKE_NOM("select * from passion p where p.nom like ?1 order by p.nom asc");
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
