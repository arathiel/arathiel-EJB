package service.ressources.carriere;

public enum Erreur 
{
	CAR_DOUBLON(1),
	CAR_INEXISTANT(2),
	CAR_NOM_OBLIGATOIRE(3),
	CAR_AFFECTEE(3),
	CAR_NULL(4),

	CAT_DOUBLON(10),
	CAT_INEXISTANT(11),
	CAT_NOM_OBLIGATOIRE(12),
	CAT_NULL(13),

	MAT_DOUBLON(20),
	MAT_INEXISTANT(21),
	MAT_NOM_OBLIGATOIRE(22),
	MAT_NULL(23),

	DIVERS(99);

	private int code;
	private String msg;

	private Erreur(int code) 
	{
		this.code = code;
	}

	public int getCode() 
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public String getMsg() 
	{
		return msg;
	}
	
	
	public String action()
	{
		switch(this)
		{
		case CAR_DOUBLON : 				return "la carriere existe deja";
		case CAR_INEXISTANT : 			return "le carriere n'existe pas";
		case CAR_NOM_OBLIGATOIRE : 		return "le nom de la carriere doit etre renseigne";
		case CAR_AFFECTEE : 			return "la carriere est déja affecte a une categorie";
		case CAR_NULL :					return "carriere null";

		case CAT_DOUBLON :				return "la categorie existe deja";
		case CAT_INEXISTANT :			return "la categorie n'existe pas";
		case CAT_NOM_OBLIGATOIRE : 		return "le nom de la categorie doit etre renseigne";
		case CAT_NULL :					return "categorie null";

		case MAT_DOUBLON :				return "la matiere existe deja";
		case MAT_INEXISTANT :			return "la matiere n'existe pas";
		case MAT_NOM_OBLIGATOIRE :		return "le nom de la matiere doit etre renseigne";
		case MAT_NULL :					return "matiere null";

		case DIVERS :					return "Erreur non referencee";
		default :						return "Oups";		
		}
	}
}
