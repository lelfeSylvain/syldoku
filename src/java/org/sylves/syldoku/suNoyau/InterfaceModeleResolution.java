package org.sylves.syldoku.suNoyau;

import org.sylves.syldoku.affichage.InterfaceObservateurResolution;

/**  Interface d�finissant la partie sujet (observable) dans le pattern Observateur
 * n�cessaire pour le pattern MVC : c'est la partie sujet du pattern Observateur
 * */
public interface InterfaceModeleResolution {
	/**
	 * Ajoute un observateur � la collection
	 * */
	public void enregistrerObservateur(InterfaceObservateurResolution o);
	/**
	 * Supprime l'observateur o de la collection
	 * @param o est un observateur du sujet qui appartient � la collection
	 * */
	public void supprimerObservateur(InterfaceObservateurResolution o);
	/**
	 * Fait appel au composant inclus qui permet de resoudre la grille de SUdoku
	 * saisie et m�moris�e dans le sujet
	 * La grille r�sultat est affect�e dans le sujet
	 * */
	public void resoudre();
	/**
	 * Retourne la grille r�solue
	 * @return la grille r�solue m�moris�e dans le sujet
	 * */
	public GrilleResolue getGrille();
	/**
	 * Renvoie la profondeur de recherche maximum impos�e
	 * Cette valeur est importante dans le cas de la recherche de Sudoku
	 * incomplet renvoyant plusieurs r�sultats.
	 * @return la profondeur est un int
	 *  */
	public int getProfondeurLimite();
	/**
	 * Modifie la profondeur de recherche maximum impos�e
	 * Cette valeur est importante dans le cas de la recherche de Sudoku
	 * incomplet renvoyant plusieurs r�sultats.
	 * @param  profondeurLimite est un int. Attention les recherches �tant exponentielles
	 * plus cette valeur est grande plus la recherche sera longue
	 * */
	public void setProfondeurLimite(int profondeurLimite);
	/**
	 * Impl�mente la grille � rechercher
	 * @param grille est une grille sous la forme d'une chaine de caract�re de 81 chiffres sans s�paration
	 * */
	public void setGrille(InterfaceGrille grille);
	/***
	 *
	 * @return vrai si la grille est possible � r�soudre (si elle n'a
	 * pas de doublon pour le moment.
	 *
	 */
	public boolean estPossibleAResoudre();
}
