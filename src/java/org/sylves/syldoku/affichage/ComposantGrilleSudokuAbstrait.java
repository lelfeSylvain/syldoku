package org.sylves.syldoku.affichage;


/** Interface d�finissant un composant d'un pattern D�corateur...
 * probl�me d'h�ritage multiple en JAVA donc je ne sais pas si je garde cela */
public interface ComposantGrilleSudokuAbstrait  {

	/** Retourne la possibilit� de modifier la grille de Sudoku
	 * @return vrai si la grille de sudoku est modifiable, faux sinon
	 */
	public boolean isSudokuEnabled() ;


	/** Permet d'autoriser/interdir la modification de la grille de Sudoku
	 * @param enabled est a vrai si on veut autoriser la modification de la grille de sudoku
	 */
	public void setSudokuEnabled(boolean enabled) ;

}