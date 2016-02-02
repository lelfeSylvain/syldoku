package org.sylves.syldoku.suNoyau;

import java.util.ArrayList;

public class GrilleResolue implements InterfaceGrille{
	private InterfaceGrille initial;// grille initiale
	//private int nbIndiceInitial; // Nombre de chiffre dans la grille initiale
	private InterfaceGrille maGrille; // Grille en cours de résolution
	private ArrayList <InterfaceGrille> grillesSolution;//Tableau de grilles résolues

	/* Constructeurs */
	// TODO ce constructeur est mauvais car il n'initialise pas la grille... il faut le détruire
	public GrilleResolue() {
		maGrille = new Grille();
		grillesSolution = new ArrayList <InterfaceGrille> ();
		initial = new Grille();
	}

	/** crée la grille, initialise, et lance le calcul des premières déductions*/
	public GrilleResolue(InterfaceGrille temp) {
		grillesSolution = new ArrayList <InterfaceGrille> ();
		setGrille(temp);
	}

	public GrilleResolue(String grille) {
		this( new Grille(grille));
	}

	/* Accesseurs */
	public void setGrille(InterfaceGrille temp) {
		// Vérification du type du paramètre
		if (temp instanceof GrilleResolue) {
			GrilleResolue maG = (GrilleResolue) temp;
			setSolution(maG);
			initial=maG.getInitial();//recopie la grille initiale
			maGrille=maG.getGrille();
		}
		else {//c'est une Grille
			initial = temp;
			maGrille= new Grille(temp);
		}
		maGrille = ResolutionGrille.resoudre(maGrille);// lance le calcul des premières déductions
	}

	public void setGrille(String maG) {
		maGrille.setGrille(new Grille(maG));
	}

	public InterfaceGrille getGrille() {
		return maGrille;
	}

	public void setSolution(Grille MaSol) {
		if (!estSolution(MaSol))
			grillesSolution.add(MaSol);
	}

	public void setSolution(GrilleResolue MesSol) {

		for (InterfaceGrille valeur : MesSol.grillesSolution)
			if (!estSolution(valeur))
				grillesSolution.add(valeur);
	}

	public ArrayList <InterfaceGrille> getLesSolutions() {

		return grillesSolution;
	}

	public InterfaceGrille getInitial() {
		return initial;
	}
/** retourne Vrai si la solution MaSol est déjà dans les solutions connus
 *
 * @param MaSol : solution à rechercher dans l'ensemble des solutions
 * @return : Vrai si la solution MaSol existe déjà
 */
	public boolean estSolution(InterfaceGrille MaSol) {
		boolean trouve =false;
		for (InterfaceGrille valeur:grillesSolution) {
			trouve = trouve || (valeur.equals(MaSol));
		}
		return trouve;
	}

	public InterfaceGrille getSolution(int i) {
		return grillesSolution.get(i);
	}

	public String toString() {
		String retour = new String("{")/*+maGrille.toString() */;
		for (InterfaceGrille valeur: grillesSolution) {
			retour += ",\n"+valeur.toString() ;
		}
		retour+="}";
		return retour;
	}


	public int getNbIndiceInitial() {
		return initial.getNombreIndice();
	}

	public void removeAllSolution(){
		grillesSolution.removeAll(grillesSolution);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	//TODO a modifier
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#estComplet()
	 */
	public boolean estComplet() {
		return maGrille.estComplet();
	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#estPossible()
	 */
	public boolean estPossible() {
		return maGrille.estPossible();
	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#estPossibleAResoudre()
	 */
	public boolean estPossibleAResoudre() {
		return maGrille.estPossibleAResoudre();
	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#getCase(org.sylves.syldoku.suNoyau.Coordonnee)
	 */
	public SuCase getCase(Coordonnee c) {
		return maGrille.getCase(c);
	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#getCase(int, int)
	 */
	public SuCase getCase(int i, int j) {
		return maGrille.getCase(i,j);
	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#getIteration()
	 */
	public int getIteration() {
		return maGrille.getIteration();
	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#getNombreIndice()
	 */
	public int getNombreIndice() {
		return maGrille.getNombreIndice();
	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#reinitGrille()
	 */
	public void reinitGrille() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#setIteration(int)
	 */
	public void setIteration(int i) {
		maGrille.setIteration(i);
	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#setVal(org.sylves.syldoku.suNoyau.Coordonnee, int)
	 */
	public void setVal(Coordonnee c, int val) {
		maGrille.setVal(c, val);
	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#setVal(int, int, int)
	 */
	public void setVal(int i, int j, int val) {
		maGrille.setVal(i, j, val);
	}


}
