package org.sylves.syldoku.controleur;

import org.sylves.syldoku.suNoyau.InterfaceGrille;

public interface InterfaceControleurSyldoku {
	void resoudreGrille(int profondeur);
	void setGrille(InterfaceGrille grille);
	boolean estPossibleAResoudre();
}
