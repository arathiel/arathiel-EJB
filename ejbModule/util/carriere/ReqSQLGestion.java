package util.carriere;

public class ReqSQLGestion 
{
	//-------------------------------- RECHERCHE -----------------------------------//
	
		public static final String CARRIERE_PAR_NOM			 = "select c from Carriere c where c.nom = ? ";
		public static final String CARRIERE_PAR_ID		 	 = "select c from Carriere c where c.id = ? ";
		public static final String MATIERE_PAR_NOM 			 = "select m from Matiere m where m.nomMatiere = ?";
		public static final String MATIERE_PAR_ID 			 = "select m from Matiere m where m.id = ?";
		public static final String CATEGORIECARRIERE_PAR_NOM = "select c from CategorieCarriere c where c.nom = ? ";
		
		
		//-------------------------------- LISTER -------------------------------------//
		
		public static final String ALL_CARRIERE_PAR_ID		= 
				"select c from Carriere c left outer join fetch c.description left outer join fetch c.idcategorie order by c.id asc";
		
		public static final String ALL_CARRIERE_PAR_NOM		=
				"select c from Carriere c left outer join fetch c.description left outer join fetch c.idcategorie order by c.nom asc";
		
		public static final String ALL_CATEGORIE_PAR_ID		=
				"select cc from CategorieCarriere cc left outer join fetch cc.matiere left outer join fetch cc.carriere order by cc.idCategorie";
		public static final String ALL_CATEGORIE_PAR_NOM	=
				"select cc from CategorieCarriere cc left outer join fetch cc.matiere left outer join fetch cc.carriere order by cc.nom";
		
		public static final String ALL_MATIERE_PAR_ID		=
				"select m from Matiere m left outer join fetch m.categorieCarriere order by m.id ";
		public static final String ALL_MATIERE_PAR_NOM		=
				"select m from Matiere m left outer join fetch m.categorieCarriere order by m.nom ";
				
		public static final String GET_CARRIERE = "select c from Carriere c order by c.nom";
		public static final String GET_CATEGORIECARRIERE = "select cat from categorieCarriere cat order by cat.nom";
		public static final String GET_MATIERE = "select m from Matiere m order by m.nomMatiere";
		
		
		//----------------------------- SUPPRESSION -------------------------------//
		
		public static final String REMOVE_NATIVE_CARRIERE = "delete from " + UtilBdD.ENTITY_CARRIERE;
		public static final String REMOVE_NATIVE_CATEGORIECARRIERE = "delete from " + UtilBdD.ENTITY_CATEGORIE_CARRIERE;
		public static final String REMOVE_NATIVE_MATIERE = "delete from " + UtilBdD.ENTITY_MATIERE;
		
		
}
