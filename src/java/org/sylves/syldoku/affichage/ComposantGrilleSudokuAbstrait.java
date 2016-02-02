package org.sylves.syldoku.affichage;


/** Interface définissant un composant d'un pattern Décorateur...
 * problème d'héritage multiple en JAVA donc je ne sais pas si je garde cela */
public interface ComposantGrilleSudokuAbstrait  {

	/** Retourne la possibilité de modifier la grille de Sudoku
	 * @return vrai si la grille de sudoku est modifiable, faux sinon
	 */
	public boolean isSudokuEnabled() ;


	/** Permet d'autoriser/interdir la modification de la grille de Sudoku
	 * @param enabled est a vrai si on veut autoriser la modification de la grille de sudoku
	 */
	public void setSudokuEnabled(boolean enabled) ;

}