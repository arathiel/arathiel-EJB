/* -------------Armurerie OlivB -----------------------------------*/

/* insert table Arme*/
Insert into ARME (IDARME,ENCOMB,MONNAIE,NOM,PRIX) values ('47','2','Cuivre','Epee','1');
Insert into ARME (IDARME,ENCOMB,MONNAIE,NOM,PRIX) values ('48','4','Cuivre','Arc','2');
Insert into ARME (IDARME,ENCOMB,MONNAIE,NOM,PRIX) values ('49','3','Argent','Dague','10');
Insert into ARME (IDARME,ENCOMB,MONNAIE,NOM,PRIX) values ('50','2','Or','Massue','15');

/*insert table joueur*/
Insert into JOUEUR (IDJOUEUR,NOMJOUEUR) values ('1','BestPlayer');
Insert into JOUEUR (IDJOUEUR,NOMJOUEUR) values ('2','MiddlePlayer');
Insert into JOUEUR (IDJOUEUR,NOMJOUEUR) values ('3','BadPlayer');

/*insert table armerace*/
Insert into ARMERACE (IDARME,IDRACE) values ('46','2');
Insert into ARMERACE (IDARME,IDRACE) values ('47','1');
Insert into ARMERACE (IDARME,IDRACE) values ('47','3');

/*insert table armejoueur*/
Insert into ARMEJOUEUR (ETAT,IDARME,IDJOUEUR) values ('casse','47','3');
Insert into ARMEJOUEUR (ETAT,IDARME,IDJOUEUR) values ('neuf','47','1');
Insert into ARMEJOUEUR (ETAT,IDARME,IDJOUEUR) values ('neuf','47','2');
Insert into ARMEJOUEUR (ETAT,IDARME,IDJOUEUR) values ('usage','47','2');

/* -------------Race_Bonus_Caracteristique Francois Georgel-----------*/
--------------------------------------------------------
--  DDL for Table CARACTERISTIQUE
--------------------------------------------------------
Insert into CARACTERISTIQUE (IDCARAC,ASPECT,NOMCARAC,QUALITE) values ('FO','PHYSIQUE','Force','ENERGIE');
Insert into CARACTERISTIQUE (IDCARAC,ASPECT,NOMCARAC,QUALITE) values ('AG','PHYSIQUE','Agilité','ADAPTATION');
Insert into CARACTERISTIQUE (IDCARAC,ASPECT,NOMCARAC,QUALITE) values ('CO','PHYSIQUE','Constitution','RESISTANCE');
Insert into CARACTERISTIQUE (IDCARAC,ASPECT,NOMCARAC,QUALITE) values ('VO','MENTAL','Volonté','ENERGIE');
Insert into CARACTERISTIQUE (IDCARAC,ASPECT,NOMCARAC,QUALITE) values ('IG','MENTAL','Intelligence','ADAPTATION');
Insert into CARACTERISTIQUE (IDCARAC,ASPECT,NOMCARAC,QUALITE) values ('PE','MENTAL','Perception','RESISTANCE');
Insert into CARACTERISTIQUE (IDCARAC,ASPECT,NOMCARAC,QUALITE) values ('IF','SPIRITUEL','Influence','ENERGIE');
Insert into CARACTERISTIQUE (IDCARAC,ASPECT,NOMCARAC,QUALITE) values ('IT','SPIRITUEL','Intuition','ADAPTATION');
Insert into CARACTERISTIQUE (IDCARAC,ASPECT,NOMCARAC,QUALITE) values ('EQ','SPIRITUEL','Equilibre','RESISTANCE');

--------------------------------------------------------
--  DDL for Table COMPETENCE
--------------------------------------------------------
Insert into COMPETENCE (ID,NOM) values ('2','Peinture');
Insert into COMPETENCE (ID,NOM) values ('3','Herboristerie');
Insert into COMPETENCE (ID,NOM) values ('1','Athlétisme');

--------------------------------------------------------
--  DDL for Table RACE
--------------------------------------------------------
Insert into RACE (ID,COUTXP,DISPO,NOM_RACE) values ('1','32','1','Orc');
Insert into RACE (ID,COUTXP,DISPO,NOM_RACE) values ('2','32','1','Elfe');
Insert into RACE (ID,COUTXP,DISPO,NOM_RACE) values ('3','32','0','Smillys');

--------------------------------------------------------
--  DDL for Table BONUS
--------------------------------------------------------
Insert into BONUS (DTYPE,IDBONUS,COUTXP,VALEURBONUS,ACADEMIQUE,IDCOMP,IDTRAIT,IDCARAC) values ('BonusCarac','CarIG1','2','1',null,null,null,'IG');
Insert into BONUS (DTYPE,IDBONUS,COUTXP,VALEURBONUS,ACADEMIQUE,IDCOMP,IDTRAIT,IDCARAC) values ('BonusCarac','CarCO1','2','1',null,null,null,'CO');
Insert into BONUS (DTYPE,IDBONUS,COUTXP,VALEURBONUS,ACADEMIQUE,IDCOMP,IDTRAIT,IDCARAC) values ('BonusCompetence','Comp11false','2','1','0','1',null,null);
Insert into BONUS (DTYPE,IDBONUS,COUTXP,VALEURBONUS,ACADEMIQUE,IDCOMP,IDTRAIT,IDCARAC) values ('BonusCarac','CarAG2','4','2',null,null,null,'AG');
Insert into BONUS (DTYPE,IDBONUS,COUTXP,VALEURBONUS,ACADEMIQUE,IDCOMP,IDTRAIT,IDCARAC) values ('BonusCarac','CarFO3','6','3',null,null,null,'FO');
Insert into BONUS (DTYPE,IDBONUS,COUTXP,VALEURBONUS,ACADEMIQUE,IDCOMP,IDTRAIT,IDCARAC) values ('BonusCompetence','Comp11true','10','1','1','1',null,null);
Insert into BONUS (DTYPE,IDBONUS,COUTXP,VALEURBONUS,ACADEMIQUE,IDCOMP,IDTRAIT,IDCARAC) values ('BonusCarac','CarAG5','10','5',null,null,null,'AG');

--------------------------------------------------------
--  DDL for Table BONUS_RACE
--------------------------------------------------------
Insert into BONUS_RACE (IDRACE,IDBONUS) values ('2','CarAG5');
Insert into BONUS_RACE (IDRACE,IDBONUS) values ('3','CarAG2');
Insert into BONUS_RACE (IDRACE,IDBONUS) values ('3','CarIG1');
Insert into BONUS_RACE (IDRACE,IDBONUS) values ('1','CarFO3');
Insert into BONUS_RACE (IDRACE,IDBONUS) values ('1','CarCO1');
Insert into BONUS_RACE (IDRACE,IDBONUS) values ('1','Comp11true');

/* -------------Suivant-----------*/

commit;
