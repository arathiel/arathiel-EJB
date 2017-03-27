package dao.trait.gestionnaire;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.trait.consultation.Consult;
import dao.trait.exception.DoublonException;
import dao.trait.exception.IdNullException;
import dao.trait.exception.LibelleNullException;
import dao.trait.exception.LibelleVideException;
import dao.trait.exception.ObjetInexistantException;
import dao.trait.exception.ObjetNullException;
import dao.trait.ressources.Erreur;
import dao.util.Parameter;
import entity.caracteristique.Caracteristique;
import entity.trait.Trait;
import entity.trait.comportement.CompCaracteristique;
import entity.trait.comportement.CompRoleplay;
import entity.trait.comportement.Comportement;
import technic.trait.Comportements;
import technic.trait.Traits;

/**
 * Classe permmetant de faire les requêtes d'administration de Trait
 * 
 * 
 * @author Jonathan Fuentes
 *
 */
@LocalBean
@Singleton
public class Admin {
	
	// Attribut de classe pour la persistance
	@PersistenceContext(unitName = Parameter.UNITNAME_JUNONARATHIEL)
	private EntityManager em;
	@EJB 
	private Consult daoConsult;
	
	// Attributs de classe pour la manipulation d'objet
	private Traits			listTrait;
	private Trait  			traitHib;
	private Comportements 	listeComp;
	private Comportement	compHib;
	private Caracteristique	carHib;
	private	Caracteristique carIn;

	
	/* ========================================== */ 
	/*  				TRAIT					  */
	/* ========================================== */
	
	/**
	 * RG : A la création, un Trait doit absolument avoir un libellé
	 * 
	 * Persiste un trait dans la BDD
	 * @param trait
	 * @throws AucunTraitException 
	 * @throws DoublonTraitException 
	 * @throws IdNullException 
	 * @throws LibelleVideException 
	 * @throws LibelleNullException 
	 * @throws DoublonException 
	 * @throws ObjetNullException 
	 * @throws TraitNullException 
	 */
	public void addTrait(Trait trait) throws LibelleVideException, LibelleNullException, DoublonException, ObjetNullException {
		// Réinitialisation des variables
		traitHib  = null;
		compHib	  = null;
		carHib	  = null;
		listeComp = null;

		// CONTROLES DU TRAIT 
		if (trait != null) {
			
			// Vérification de la nullité du libellé puis de son contenu
			if (trait.getLibelle() != null) {
				if (trait.getLibelle().isEmpty() != true) { 
					
					// Vérification de l'existance du trait via Libellé

					try {
						traitHib = daoConsult.getTraitByLib(trait.getLibelle());
					} catch (ObjetInexistantException e1) {
						System.out.println(e1 + " " + e1.getType());
					}

					// Vérification de l'existence des comportements
					if (traitHib == null) {

						//CONTROLES DE LA LISTE DE COMPORTEMENT
						listeComp = (Comportements) trait.getListComp();

						//Vérification si null
						if(listeComp != null) {
							
							//On boucle pour récupérer les comportements
							for (int i = 0; i < listeComp.size(); i++) {
								
								// CONTROLES DES COMPORTEMENTS DE LA LISTE
								Comportement comp = listeComp.get(i);
				
								// s'il n'est pas null et que le libellé est OK on vérifie son existence dans la BDD
								if(comp != null) {
									if (comp.getLibelle() != null) {
										if(comp.getLibelle().isEmpty() != true) {
									
											try {
												compHib = daoConsult.getCompByLib(comp.getLibelle());
											} 
											catch (ObjetInexistantException | IdNullException e) {
												System.out.println(e + " " + e.getType());
											}
				
											// On met le proxy comportement dans la liste s'il est trouvé dans la BDD afin de ne pas les dupliquer
											if (compHib != null)  listeComp.set(i, compHib);
						
											// On met le proxy de la caractéristique (existe toujours) dans le comportement si celui-ci est un nouveau CompCaracteristique
											else {
												if (comp instanceof CompCaracteristique) {
													carIn 	= ((CompCaracteristique) comp).getCaracteristique();
											
													//On vérifie que le comportement possède un e caractéristique avant de chercher en base
													if(carIn != null) {
														try {
															carHib	= daoConsult.getCarByLib(carIn.getNomCarac());
														} catch (ObjetInexistantException e) {
															System.out.println(e + "" + e.getMessage());
														}
														((CompCaracteristique) comp).setCaracteristique(carHib);
													}
												}
											}
											
											// On remet à null compHib, sinon il ajoute plusieurs fois le même nouveau comportement
									
											compHib = null;
										
										}//Fin if(comp.getLibelle().isEmpty() != true)
										else {
											throw new LibelleVideException(Erreur.COMP);
										}
									
									}//Fin if (comp.getLibelle() != null)
									else {
										throw new LibelleNullException(Erreur.COMP);
									}
								
								}//Fin if(comp != null)
								
								//si un comportement de la liste est null, on le supprime
								else {
									listeComp.remove(i);
								}						
							}// Fin boucle for
					
						}//Fin if (listeComp != null)

						// Finalement on persite le trait
						em.persist(trait);
						em.flush();
					
					}// Fin if(traitHib == null)
				
					// On propage l'exception si le libellé existe déjà (Doublon)
					else {
						throw new DoublonException(Erreur.TRAIT);
					}
				}// Fin if (trait.getLibelle().isEmpty() != true)
				
				else {
					throw new LibelleVideException(Erreur.TRAIT);
				} 
			
			}//if (trait.getLibelle() != null) {
			
			// On propage l'exception si libellé null
			else {
				throw new LibelleNullException(Erreur.TRAIT);
			}
		
		}// Fin if (trait != null)
		
		else {
			throw new ObjetNullException(Erreur.TRAIT);
		}
		
	}// Fin addTrait()

	/**
	 * Modifie un trait de la BDD
	 * @param trait
	 * @throws ObjetInexistantException 
	 * @throws DoublonTraitException 
	 * @throws AucunTraitException 
	 * @throws IdNullException 
	 * @throws LibelleVideException 
	 * @throws TraitNullException 
	 * @throws LibelleNullException 
	 * @throws ObjetNullException 
	 */
	public void updateTrait(Trait trait) throws ObjetInexistantException, IdNullException, LibelleNullException, ObjetNullException, LibelleVideException {
		// Réinitialisation des variables
		traitHib = null;
		compHib	 = null;

		// Vérification de la nullité de l'objet reçu 
		if (trait != null) {

			//On vérifie le libellé (Obligatoire)
			if (trait.getLibelle() != null) {

				//On vérifie le libellé (Obligatoire)
				if (trait.getLibelle().isEmpty() != true) {

					//On vérifie l'ID (oiur la recherche)
					if (trait.getId() != 0) {

						// Vérification de l'existence du trait
						try {
							traitHib = daoConsult.getTraitById(trait.getId());
						} catch (IdNullException e2) {
							System.out.println(e2 + " " + e2.getType());
						}
						
						if (traitHib != null) {

							// Vérification de l'éxistance des comportements
							listeComp = (Comportements) trait.getListComp();

							//Vérification de la nullité de la liste
							if(listeComp != null) {
								for (int i = 0; i < listeComp.size(); i++) {

									// On récupère la liste envoyée en paramètre via le trait
									Comportement comp = listeComp.get(i);
									
									// s'il n'est pas null et que le libellé est OK on vérifie son existence dans la BDD
									if(comp != null) {
										if (comp.getLibelle() != null) {
											if(comp.getLibelle().isEmpty() != true) {
										
												// On cherche si le comportement existe déjà
												try {
													compHib = daoConsult.getCompByLib(comp.getLibelle());
												} catch (ObjetInexistantException | IdNullException e2) {
													System.out.println(e2 + " " + e2.getType());
												}

												// On met le proxy comportement dans la liste s'il est trouvé dans la BDD afin de ne pas les dupliquer
												if (compHib != null)  listeComp.set(i, compHib);

												// On met le proxy de la caractéristique (existe toujours) dans le comportement si celui-ci est un nouveau CompCaracteristique
												else {
										
													try {
														this.updateComp(comp);
													} 
													catch (IdNullException | ObjetInexistantException e1) {
														System.out.println(e1 + " " + e1.getType());
													}

													try {
														compHib = daoConsult.getCompByLib(comp.getLibelle());
													} 
													catch (ObjetInexistantException | IdNullException e) {
														System.out.println(e + " " + e.getType());
													}

													listeComp.set(i, compHib);
												}

												// On remet à null compHib, sinon il ajoute plusieurs fois le même nouveau comportement
												compHib = null;
									
											}//Fin if(comp.getLibelle().isEmpty() != true)
											else {
												throw new LibelleVideException(Erreur.COMP);
											}
										
										}//Fin if (comp.getLibelle() != null)
										else {
											throw new LibelleNullException(Erreur.COMP);
										}
												
									}//Fin if(comp != null)
									
									//si un comportement de la liste est null, on le supprime
									else {
										listeComp.remove(i);
									}
									
								}// Fin boucle for

							}//Fin if (listeComp != null)

							// Finalement on update le trait
							em.merge(trait);
							em.flush();

						}// Fin if(traitHib == null)

						// On propage l'exception si le trait n'existe pas
						else {
							throw new ObjetInexistantException(Erreur.TRAIT);
						}
					}// Fin if (trait.getId() != 0)

					else {
						throw new IdNullException(Erreur.TRAIT);
					}
				
				}//Fin (trait.getLibelle().isEmpty != true)
				
				else {
					throw new LibelleVideException(Erreur.TRAIT);
				} 
				
			}//Fin (trait.getLibelle() != null)
			
			else {
				throw new LibelleNullException(Erreur.TRAIT);
			}

		}// Fin if (trait != null)

		else {
			throw new ObjetNullException(Erreur.TRAIT);
		}

	}// Fin updateTrait(Trait trait)

	/**
	 * Supprime un trait de la BDD
	 * @param id
	 * @throws ObjetNullException 
	 * @throws AucunTraitException 
	 * @throws IdNullException 
	 */
	public void deleteTrait(int id) throws ObjetNullException, IdNullException {
		// Réinitialisation des variables
		traitHib = null;
		
		if(id != 0) {
			traitHib = em.find(Trait.class, id);
			
			if (traitHib != null){
				em.remove(traitHib);
				em.flush();
			}
			else {
				throw new ObjetNullException(Erreur.TRAIT);
			}
		}
		else {
			throw new IdNullException(Erreur.TRAIT);
		}
	}
	
	/**
	 * Vide la table de trait
	 * @throws AucunTraitException 
	 */
	public void deleteAllTrait() {
	
		listTrait = daoConsult.getAllTrait();
		
		if (listTrait.isEmpty() != true) {
			for (Trait trait : listTrait) {

				try {
					this.deleteTrait(trait.getId());
				} catch (ObjetNullException | IdNullException e) {
					System.out.println(e + " " + e.getType());
				}

			}
		}
	}
	
	
	/* ========================================== */ 
	/*  			COMPORTEMENT				  */
	/* ========================================== */

	/**
	 * Persiste un comportement dans la BDD
	 * @param comportement
	 * @throws DoublonException 
	 * @throws LibelleVideException 
	 * @throws LibelleNullException 
	 * @throws ObjetNullException 
	 * @throws IdNullException 
	 */
	public void addComp(Comportement comportement) throws DoublonException, LibelleVideException, LibelleNullException, ObjetNullException {
		// Réinitialisation des variables
		compHib = null;
		carHib  = null;
		
		// Vérification de la nullité de l'objet reçu 
		if (comportement != null) {
			
			if (comportement.getLibelle() != null) {
				if (comportement.getLibelle().isEmpty() != true) {
					try {
						compHib = daoConsult.getCompByLib(comportement.getLibelle());
					} catch (ObjetInexistantException | IdNullException e) {
						System.out.println(e + " " + e.getType());
					}
	
					// Vérification de l'existance du comportement dans la BDD
					if(compHib == null) {
				
						// Gestion si classe CompCaracteristique
						if (comportement instanceof CompCaracteristique) {
							carIn 	= ((CompCaracteristique) comportement).getCaracteristique();
							
							if (carIn != null) {
								try {
									carHib	= daoConsult.getCarByLib(carIn.getNomCarac());
								} catch (ObjetInexistantException e) {
									System.out.println(e + "" + e.getMessage());
								}
								((CompCaracteristique) comportement).setCaracteristique(carHib);
							}
							
							em.persist(comportement);
							em.flush();
						}
				
						// Gestion si classe CompR
						else if (comportement instanceof CompRoleplay) em.persist(comportement);				
					
					}//Fin if(compHib == null)
			
					else {
						throw new DoublonException(Erreur.COMP);
					}
					
				}//Fin if (comportement.getLibelle().isEmpty() != true)
				else {
					throw new LibelleVideException(Erreur.COMP);
				}
			}//Fin if (comportement.getLibelle() != null)
			else {
				throw new LibelleNullException(Erreur.COMP);
			}	
		}// Fin if (comportement != null)
		else {
			throw new ObjetNullException(Erreur.COMP);
		}
	}

	/**
	 * Modifie un comportement de la BDD
	 * @param comportement
	 * @throws IdNullException 
	 * @throws ObjetInexistantException 
	 */
	public void updateComp(Comportement comportement) throws IdNullException, ObjetInexistantException {
		// Réinitialisation des variables
		compHib = null;
		carHib  = null;
		
		// Vérification de la valeur de l'objet envoyé en paramètre
		if (comportement != null) {
			
			// Vérification de l'existance du comportement dans la BDD
			compHib = daoConsult.getCompById(comportement.getId());
			if(compHib != null) {
				
				// Gestion si classe CompCaracteristique
				if (comportement instanceof CompCaracteristique) {
					carIn 	= ((CompCaracteristique) comportement).getCaracteristique();
					
					if (carIn != null) {
						try {
							carHib	= daoConsult.getCarByLib(carIn.getNomCarac());
						} catch (LibelleVideException | LibelleNullException e) {
							System.out.println(e + "" + e.getMessage());
						}
						((CompCaracteristique) comportement).setCaracteristique(carHib);
					}
					
					em.merge(comportement);
					em.flush();
				}
				
				// Gestion si classe CompR
				else if (comportement instanceof CompRoleplay) em.merge(comportement);
			}
			//Si le comportement n'existe pas, on le persiste
			else {
				throw new ObjetInexistantException(Erreur.COMP);
			}
		}
		else {
			throw new IdNullException(Erreur.COMP);
		}
	}

	
	/**
	 * Supprime un comportement de la BDD
	 * @param id
	 * @throws ObjetInexistantException 
	 * @throws IdNullException 
	 */
	public void deletecomp(int id) throws ObjetInexistantException, IdNullException {
		//Réinitialisation
		compHib = null;
		
		// Vérification de la valeur de l'id envoyé en paramètre
		if (id != 0) {
			// Vérification de l'ID envoyé
			compHib = em.find(Comportement.class, id);
			
			// Vérification de l'existance d'un comportement assosié un ID
			if (compHib != null) {
				em.remove(compHib);
				em.flush();
			}
			else {
				throw new ObjetInexistantException(Erreur.COMP);
			}
		}
		else {
			throw new IdNullException(Erreur.COMP);
		}
	}
	
	
	/**
	 * Vide la table de comportement
	 * @throws ObjetInexistantException 
	 */
	public void deleteAllComp() {
		listeComp = daoConsult.getAllComp();
		
		if (listeComp.isEmpty() != true) {
			em.createNamedQuery("deleteAllComp").executeUpdate();
			em.flush();
		}
	}
}// Fin classe