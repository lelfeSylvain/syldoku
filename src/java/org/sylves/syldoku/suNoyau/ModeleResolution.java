package org.sylves.syldoku.suNoyau;

import java.util.ArrayList;

import org.sylves.syldoku.affichage.InterfaceObservateurResolution;

public class ModeleResolution implements InterfaceModeleResolution {

	ArrayList<InterfaceObservateurResolution> observateurs = new ArrayList<InterfaceObservateurResolution>();//liste des observateur de ce sujet
	GrilleResolue maGrille;
	boolean initialiser =false;
	int profondeurLimite =2;

	/**
	 * @return the profondeurLimite
	 */
	public int getProfondeurLimite() {
		return profondeurLimite;
	}

	/**
	 * @param profondeurLimite the profondeurLimite to set
	 */
	public void setProfondeurLimite(int profondeurLimite) {
		this.profondeurLimite = profondeurLimite;
	}

	public ModeleResolution () {
		this.maGrille = new GrilleResolue ();
	}

	public void setGrille(InterfaceGrille maGrille) {
		this.maGrille.setGrille(maGrille);
		initialiser =true;
	}

	public GrilleResolue getGrille(){
		return maGrille;
	}
	// gestion des observateurs
	public void enregistrerObservateur(InterfaceObservateurResolution o) {
		observateurs.add(o);

	}

	public void supprimerObservateur(InterfaceObservateurResolution o) {
		int i = observateurs.indexOf(o);
		if (i>=0){
			observateurs.remove(i);
		}
	}

	public void notifierObservateur(){
		for (int i = 0; i<observateurs.size();i++){
			observateurs.get(i).actualiser();
		}
	}

	public void resoudre() {
		if (initialiser) {
			maGrille.setSolution(ResolutionGrille.resoudreParChoix(maGrille,profondeurLimite));
			notifierObservateur();
		}
	}

	public boolean estPossibleAResoudre() {

		return ResolutionGrille.estPossibleAResoudre(maGrille.getGrille());
	}

}
