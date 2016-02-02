package org.sylves.syldoku.suNoyau;

public interface InterfaceGrille {

	//private int nbIndice;// TODO inutile car on le recalcule � chaque fois.
	/** nombre de cases sur chaque cot� de la matrice*/
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

	/** renvoie vrai si le tableau est incomplet et qu'il reste des possibilit�s
	 * non exploit�es. */
	public abstract boolean estPossible();

	/** Initialise une case du Sudoku (i,j) avec la valeur val - met � jour les tableaux des possibles de chaque case suivante */
	public abstract void setVal(int i, int j, int val);
	/** Initialise une case du Sudoku (C) avec la valeur val - met � jour les tableaux des possibles de chaque case suivante */
	public abstract void setVal(Coordonnee c, int val);
	/** Renvoie la r�ference de la case c */
	public abstract SuCase getCase(Coordonnee c);
	/** Renvoie la r�ference de la case (i,j) */
	public abstract SuCase getCase(int i, int j);

	/** initialise une grille avec une grille
	 *
	 * @param grille : La grille initiale
	 */
	public abstract void setGrille(InterfaceGrille grille);

	/** initialise une grille avec la chaine d�crivant la grille
	 *
	 * @param grille : description de la grille sous forme Texte
	 */
	public abstract void setGrille(String grille);
	/** Renvoie l'it�ration de la grille */
	public abstract int getIteration();
	/** d�fini l'it�ration de la grille */
	public abstract void setIteration(int i);



	/**
	 *
	 * @return faux si la grille est impossible � r�soudre !
	 */
	public abstract boolean estPossibleAResoudre();

}