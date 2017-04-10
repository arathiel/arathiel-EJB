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

commit;
