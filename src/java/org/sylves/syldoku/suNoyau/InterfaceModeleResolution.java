package org.sylves.syldoku.suNoyau;

import org.sylves.syldoku.affichage.InterfaceObservateurResolution;

/**  Interface définissant la partie sujet (observable) dans le pattern Observateur
 * nécessaire pour le pattern MVC : c'est la partie sujet du pattern Observateur
 * */
public interface InterfaceModeleResolution {
	/**
	 * Ajoute un observateur à la collection
	 * */
	public void enregistrerObservateur(InterfaceObservateurResolution o);
	/**
	 * Supprime l'observateur o de la collection
	 * @param o est un observateur du sujet qui appartient à la collection
	 * */
	public void supprimerObservateur(InterfaceObservateurResolution o);
	/**
	 * Fait appel au composant inclus qui permet de resoudre la grille de SUdoku
	 * saisie et mémorisée dans le sujet
	 * La grille résultat est affectée dans le sujet
	 * */
	public void resoudre();
	/**
	 * Retourne la grille résolue
	 * @return la grille résolue mémorisée dans le sujet
	 * */
	public GrilleResolue getGrille();
	/**
	 * Renvoie la profondeur de recherche maximum imposée
	 * Cette valeur est importante dans le cas de la recherche de Sudoku
	 * incomplet renvoyant plusieurs résultats.
	 * @return la profondeur est un int
	 *  */
	public int getProfondeurLimite();
	/**
	 * Modifie la profondeur de recherche maximum imposée
	 * Cette valeur est importante dans le cas de la recherche de Sudoku
	 * incomplet renvoyant plusieurs résultats.
	 * @param  profondeurLimite est un int. Attention les recherches étant exponentielles
	 * plus cette valeur est grande plus la recherche sera longue
	 * */
	public void setProfondeurLimite(int profondeurLimite);
	/**
	 * Implémente la grille à rechercher
	 * @param grille est une grille sous la forme d'une chaine de caractère de 81 chiffres sans séparation
	 * */
	public void setGrille(InterfaceGrille grille);
	/***
	 *
	 * @return vrai si la grille est possible à résoudre (si elle n'a
	 * pas de doublon pour le moment.
	 *
	 */
	public boolean estPossibleAResoudre();
}
