package org.sylves.Controleur;

import org.sylves.affichage.*;

import org.sylves.syldoku.suNoyau.InterfaceModeleResolution;

public class ControleurSyldoku implements InterfaceControleurSyldoku {
	InterfaceModeleResolution modele;
	InterfaceObservateurResolution vue,vue2;

	public ControleurSyldoku(InterfaceModeleResolution modele){
		this.modele=modele;
		vue = new FrmGrilleSudokuAvecMenu(modele,this);
		//vue2 = new FenetreStatistique(modele,this);
	}

	public void resoudreGrille(int profondeur) {
		modele.setProfondeurLimite(profondeur);
		modele.resoudre();

	}

	public void setGrille(String grille) {
		modele.setGrille(grille);

	}

}
