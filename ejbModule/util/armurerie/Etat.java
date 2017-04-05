package util.armurerie;

public enum Etat {
	
	NEUF ("neuf"),
	USAGE ("usage"),
	CASSE ("casse");
	
	private String etat;

	private Etat(String etat) {
		this.etat = etat;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	public static Etat getEnum(String etat) {

	    switch (etat) {
	        case "neuf":
	            return NEUF;
	        case "usage":
	            return USAGE;
	        case "casse":
	            return CASSE;
	        default:
	            return null;
	     }
	   }
	
	public static String getString(Etat etat) {
		String etatEtat = " ";
		switch (etat) {
		case NEUF:
			etatEtat = "neuf";
		break;
		case USAGE:
			etatEtat = "usage";
		break;
		case CASSE:
			etatEtat = "casse";
			
		}
		return etatEtat;
	}

	
}
