package org.sylves.syldoku.suNoyau;

public interface InterfaceGrille {

	//private int nbIndice;// TODO inutile car on le recalcule à chaque fois.
	/** nombre de cases sur chaque coté de la matrice*/
	public static final int BORNE = 9;

	/** re-initialise la grille des possibles de toutes les cases */
	public abstract void reinitGrille();

	/** renvoie vrai si le tableau est complet */
	public abstract boolean estComplet();

	/**
	 * Retourne le nombre d'indice de la grille
	 * @return le nombre d'indice de la grille
	 */
	public abstract int getNombreIndice();

	/** renvoie vrai si le tableau est incomplet et qu'il reste des possibilités
	 * non exploitées. */
	public abstract boolean estPossible();

	/** Initialise une case du Sudoku (i,j) avec la valeur val - met à jour les tableaux des possibles de chaque case suivante */
	public abstract void setVal(int i, int j, int val);
	/** Initialise une case du Sudoku (C) avec la valeur val - met à jour les tableaux des possibles de chaque case suivante */
	public abstract void setVal(Coordonnee c, int val);
	/** Renvoie la réference de la case c */
	public abstract SuCase getCase(Coordonnee c);
	/** Renvoie la réference de la case (i,j) */
	public abstract SuCase getCase(int i, int j);

	/** initialise une grille avec une grille
	 *
	 * @param grille : La grille initiale
	 */
	public abstract void setGrille(InterfaceGrille grille);

	/** initialise une grille avec la chaine décrivant la grille
	 *
	 * @param grille : description de la grille sous forme Texte
	 */
	public abstract void setGrille(String grille);
	/** Renvoie l'itération de la grille */
	public abstract int getIteration();
	/** défini l'itération de la grille */
	public abstract void setIteration(int i);



	/**
	 *
	 * @return faux si la grille est impossible à résoudre !
	 */
	public abstract boolean estPossibleAResoudre();

}