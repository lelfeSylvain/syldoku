package org.sylves.syldoku.suNoyau;

/**
 * représente un grille de Sudoku
 * @author  Sylvain Parise
 */
public class Grille implements InterfaceGrille {
	/**
	 * Le tableau représentant le Sudoku
	 * @uml.property  name="mat"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	private SuCase Mat [][];
	/** permet d'évaluer la difficulté d'une grille en attribuant un poids à chaque opération de résolution réussie.*/
	private int iteration;
	/** Constructeur : Construit une grille vierge */
	public Grille() {
		int i,j;
		Mat = new SuCase[BORNE ][ BORNE];
		/* initialise les suivants */
		for (i=0; i<BORNE;i++)
			for (j=0;j<BORNE;j++) {
				Mat[i][j] = new SuCase();
				/* gère les suivants des blocs */
				if (i%3==2)  {
					if (j%3==2)  {
						Mat[i][j].setSuivant(SuCase.BLOC,i-2,j-2);
						}
					else {
						Mat[i][j].setSuivant(SuCase.BLOC,i-2,j+1);
					}
				}
				else {
					Mat[i][j].setSuivant(SuCase.BLOC,i+1,j);
					}
				/* suivant sur la même ligne */
				Mat[i][j].setSuivant(SuCase.HORI,(i+1)%BORNE,j);
				/* suivant sur la même colonne */
				Mat[i][j].setSuivant(SuCase.VERT,i,(j+1)%BORNE);
		}
		/* permet de compter le nombre d'itération de la recherche*/
		iteration=0;
		//nbIndice=0;
	}
	/** Constructeur : Construit une grille à partir d'une autre grille passée en paramètre */
	public Grille(InterfaceGrille grille) {
		this();
		setGrille(grille);
	}
	/** Constructeur : Construit une grille à partir d'une autre grille passée en paramètre sous forme d'une chaine de caractères */

	public Grille(String grille) {
		this();
		setGrille(grille);

	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#reinitGrille()
	 */
	public void reinitGrille() {
		int i,j;
		/* initialise les suivants */
		for (i=0; i<BORNE;i++)
			for (j=0;j<BORNE;j++) {
				Mat[i][j].initCase();//initialise la case à zéro
			}
		iteration=0;
	}
	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#estComplet()
	 */
	public boolean estComplet() {
		int i,j;
		boolean incomplet =false;// on considère qu'il est complet

		for (i=0; i<BORNE;i++)
			for (j=0;j<BORNE;j++) {// sauf si on trouve une case vide
				incomplet = incomplet || (Mat[i][j].getVal()==0);

			}
		return !incomplet;
	}
	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#getNombreIndice()
	 */
	public int getNombreIndice(){
		int i,j,nb;
		nb=0;
		for (i=0; i<BORNE;i++)
			for (j=0;j<BORNE;j++) {
				if (Mat[i][j].getVal()!=0)
					nb++;
			}
		return nb;
	}
	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#estPossible()
	 */
	public boolean estPossible() {
		int i,j;
		boolean trouve =false;// on cherche un possible
		boolean possible = true; // et on suppose que c'est possible

		for (i=0; i<BORNE && possible;i++)
			for (j=0;j<BORNE && possible;j++)
				if(Mat[i][j].getVal()==0) {//il existe une case vide
					trouve=true;
					if (Mat[i][j].getPossible()==0) //mais elle est impossible à compléter
							possible= false;
				}
		return possible && trouve; //si on trouve une case vide et possible !
	}


	/* Accesseurs pour une case */
	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#setVal(int, int, int)
	 */
	public void setVal(int i, int j,int val) {
		int k,m;
		Coordonnee c;
		Mat[i][j].setVal(val);

		for (k=0; k<3;k++) {//on parcourt les suivants HORI, VERT et BLOC
			c= Mat[i][j].getSuivant( k);
			for (m=0;m<BORNE;m++) {//on parcours les 9 suivants pour indiquer les impossibles.
				getCase(c).setPossible(false,Mat[i][j].getVal());
				c=getCase(c).getSuivant(k);
			}

		}
	}
	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#setVal(org.sylves.syldoku.suNoyau.Coordonnee, int)
	 */
	public void setVal(Coordonnee c,int val) {
		setVal(c.getI(),c.getJ(),val);
	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#getCase(org.sylves.syldoku.suNoyau.Coordonnee)
	 */
	public SuCase getCase(Coordonnee c) {
		return Mat[c.getI()][c.getJ()];
	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#getCase(int, int)
	 */
	public SuCase getCase(int i, int j) {
		return Mat[i][j];
	}
	public String toString() {
		int i, j;
		String retour ="";

		for (j=0;j<BORNE;j++)
			for (i=0; i<BORNE;i++) {
				retour+=String.valueOf( Mat[i][j].getVal());
			}
		retour+="\n";
		return retour;
	}
	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#setGrille(org.sylves.syldoku.suNoyau.InterfaceGrille)
	 */
	public void setGrille(InterfaceGrille grille) {
		setGrille(grille.toString());
		iteration=grille.getIteration();
		//nbIndice= getNombreIndice();
	}


	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#setGrille(java.lang.String)
	 */
	public void setGrille(String grille) {
		int i,j,indice ;

		int valeur ;
		reinitGrille();

		indice=0;
		for(i=0;i<BORNE;i++)
			for(j=0;j<BORNE;j++){
				// C'est un chiffre j'ai pas trouvé mieux pour le convertir...
				valeur=Integer.valueOf(grille.charAt(indice++)).intValue()-48;
				if (valeur!=0){
					this.setVal(j,i,valeur);
					//nbIndice++;
				}
			}

	}

	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#getIteration()
	 */
	public int getIteration() {
		return iteration;
	}
	/* (non-Javadoc)
	 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#setIteration(int)
	 */
	public void setIteration(int i) {
		 iteration=i;
	}

/* (non-Javadoc)
 * @see org.sylves.syldoku.suNoyau.InterfaceGrille#estPossibleAResoudre()
 */
	public boolean estPossibleAResoudre() {
//		 il est impossible de résoudre une grille de moins de 16 indices.
		boolean impossible = (getNombreIndice()<17);
		if (impossible) return false;
		int i,j, k,n;
		Coordonnee c;
		int chiffre_utilise [];
		chiffre_utilise= new int[Case.BORNEPOSSIBLE+1];
		for (k=0; k<3;k++) //on parcourt les suivants HORI, VERT et BLOC
			for(j=0;j<3;j++)// on parcourt 9 cases bien choisies sur la grille
				for(i=0;i<3;i++) {
					c= Mat[i*3+j][j*3+i].getSuivant( k);
					for (n=0;n<=Case.BORNEPOSSIBLE;n++) //initialise le tableau pour compter
						chiffre_utilise[n]=0;
					for (n=0;n<BORNE;n++) {
						chiffre_utilise[getCase(c).getVal()]++ ;
						//on parcours les 9 suivants pour recenser les chiffres utilisés.
						c=getCase(c).getSuivant(k);
					}
					for (n=1;n<=Case.BORNEPOSSIBLE;n++){
						impossible = impossible || chiffre_utilise[n]>1;
					}
				}
		return !impossible;
	}

	/**
	 * renvoie vrai si les obj et this sont identiques
	 * utilise la Jakarta Common Lang (http://jakarta.apache.org/commons/lang/)
	 * d'après adiGuba http://java.developpez.com/faq/java/?page=divers#DIVERS_hashCode
	 */
	public boolean equals( Object obj){
		//d'après http://java.developpez.com/faq/java/?page=divers#DIVERS_equals
		// Vérification de l'égalité des références
		if (obj==this) {
			return true;
		}

		// Vérification du type du paramètre
		if (obj instanceof Grille) {

			// Vérification des valeurs des attributs
			InterfaceGrille other = (InterfaceGrille) obj;

			return equals(other);
		}
		return false;
	}

	/** renvoie Vrai si la grille g est egale à la grille de l'instance*/
	public boolean equals(InterfaceGrille g) {
		boolean egal=true;// on considère que les 2 grilles sont égales a priori
		int i,j;
		for (i=0; i<BORNE;i++)
			for (j=0;j<BORNE;j++) {
				egal= egal && (Mat[i][j].getVal() == g.getCase(i,j).getVal());
			}

		return egal;
	}


}


