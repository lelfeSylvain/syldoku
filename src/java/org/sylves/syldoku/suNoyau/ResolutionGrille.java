package org.sylves.syldoku.suNoyau;

public  class ResolutionGrille {

	public static int limite =0;
	public static int profondeur =0;
	public static int maxprofondeur =0;
	public static int nbgrille =0;

	/** r�solution de la grille par essai successif de tous les possibles restants
	 * possibilit� d'appel r�cursif*/
	static public GrilleResolue resoudreParChoix(GrilleResolue maGrilleAResoudre, int l) {
		limite=l;
		profondeur=0;
		maxprofondeur=0;
		nbgrille=0;
		maGrilleAResoudre.removeAllSolution();

		return resoudreParChoix(maGrilleAResoudre);

	}
	static private GrilleResolue resoudreParChoix(GrilleResolue maGrilleAResoudre) {

		int i,j,k;// indice de tableau ... on doit pouvoir faire mieux
		int memi, memj, maxpossible ; //memoire de l'indice qui convient

		GrilleResolue temp = new GrilleResolue();
		TabGrille AutresSol = new TabGrille();
		/*La grille r�solue MaSolution va m�moriser toutes les solutions a
		* partir de l'hypoth�se maGrilleAResoudre */
		GrilleResolue MaSolution = new GrilleResolue(maGrilleAResoudre);
		//lors de l'initialisation il y a une d�duction qui se fait...
		profondeur++;
		if (profondeur>maxprofondeur)
			maxprofondeur=profondeur;
		if (! MaSolution.getGrille().estComplet()) {//si ma grille n'est pas compl�te...
			if (MaSolution.getGrille().estPossible() ) {//et si il existe encore des possibilit�s
				maxpossible=10;//il ne peut y avoir 10 possibles dans une case !
				memi=0;
				memj=0;
				for(j=0;j<InterfaceGrille.BORNE;j++) {// ...je cherche une case vide ...
					for(i=0;i<InterfaceGrille.BORNE;i++) { //...afin de chercher ...
						if (MaSolution.getGrille().getCase(i,j).getVal()==0) {//...une possibilit�.
							k=MaSolution.getGrille().getCase(i,j).getNbPossible();
							if (k<maxpossible) {
								maxpossible=k;
								memi=i;
								memj=j;
							}
						}
					}
				}
				// a ce stade on a trouv� la case o� il y a le moins de possibilit�.
				for (k=1;k<=InterfaceGrille.BORNE;k++) {// il faut tester toutes les possibilit�s de cette case
					if (MaSolution.getGrille().getCase(memi,memj).getPossible(k)) {// si k est possible
						temp.setGrille(MaSolution);// je r�initialise et impl�mente une nouvelle grille
						temp.getGrille().setVal(memi,memj,k);// et j'ajoute la nouvelle possibilit�
						temp.setGrille(ResolutionGrille.resoudre(temp.getGrille()));
						if (temp.getGrille().estComplet()) {// si c'est une solution je l'ajoute
							MaSolution.setSolution((Grille) temp.getGrille());
							nbgrille++;
						}
						else
							if (temp.getGrille().estPossible())
								AutresSol.addGrille(temp.getGrille());// sinon je m�morise les nouvelles possibilit�s
					}
				}
				// S'il existe d'autres solutions d�tect�es mais non r�solue
				// et que nous n'avons pas atteint la limite du nb de grille solution qu'on s'�tait fix�e
				//if ((AutresSol.size()!=0) && (maGrilleAResoudre.getLesSolutions().size()<=limite))
				if ((AutresSol.size()!=0) && (nbgrille<=limite))
					for(InterfaceGrille valeur:AutresSol.getTab()) {
						// appel r�cursif pour explorer les autres solutions
						MaSolution.setSolution(ResolutionGrille.resoudreParChoix(new GrilleResolue(valeur)));
				}
			}
		}
		else {// ma grille maGrilleAResoudre est compl�te
			MaSolution.setSolution((Grille) MaSolution.getGrille());
			nbgrille++;
		}
		//System.out.println("Profondeur actuelle : "+(profondeur--)+"/"+maxprofondeur );
		//System.out.println("nombre de grille trouv�e :" +nbgrille);
		return MaSolution;
	}


	/** R�solution de la grille par d�duction
	 *
	 * @param g la grille sous forme de String
	 * @return La grille solution partiel � la grille g*/
	public static InterfaceGrille resoudre(InterfaceGrille g) {
		boolean trouve=true;
		InterfaceGrille MaSol = new Grille(g);
		while (trouve) {
			trouve= elimineLesPossibles(MaSol);
			trouve = trouve || elimineLesSingletons(MaSol);
		}
		return(MaSol);
	}


	/** R�solution de la grille par d�duction
	 *
	 * @param g la grille sous forme de String
	 * @return La grille solution partiel � la grille g
	 */
	public static InterfaceGrille resoudre(String g) {

		return (resoudre(new Grille(g)));

	}
	public static boolean estPossibleAResoudre(InterfaceGrille maGrille) {
		return maGrille.estPossibleAResoudre();
	}

	/** on commence la r�solution simple : juste cocher les cases qui ne propose
	 * qu'une seule valeur possible (voir tableau possible de Case
	 *
	 * @return faux si aucune modif n'a �t� faite sur la grille, vrai sinon
	 *
	 *  */
	public static boolean elimineLesPossibles(InterfaceGrille maGrille) {
		int i,j,mem ;
		boolean trouve=false;

		for(j=0;j<InterfaceGrille.BORNE;j++)
			for(i=0;i<InterfaceGrille.BORNE;i++) {
				mem=maGrille.getCase(i, j).getSeulPossible();
				if (mem>0 && mem<10) {
					maGrille.setVal(i,j,mem);
					trouve=true;
				}
			}
		if (trouve)
			maGrille.setIteration(maGrille.getIteration()+1);
		return trouve;
	}

	/** R�solution suite : si dans un bloc/ligne/colonne il y a un num�ro possible qui
	 * est le seul � ne pas avoir �t� utilis�... on l'affecte !
	 *  @return faux si aucune modif n'a �t� faite sur la grille, vrai sinon
	 */
	public static boolean elimineLesSingletons(InterfaceGrille maGrille) {
		int i,j;
		boolean trouve=false;
		int k,m,n;
		Coordonnee c;
		int chiffre_utilise [];
		chiffre_utilise= new int[Case.BORNEPOSSIBLE+1];

		for (k=0; k<3;k++) //on parcourt les suivants HORI, VERT et BLOC
			for(j=0;j<3;j++)// on parcourt 9 cases bien choisies sur la grille
				for(i=0;i<3;i++) {
					c= maGrille.getCase(i*3+j,j*3+i).getSuivant( k);
					for (n=0;n<=Case.BORNEPOSSIBLE;n++) //initialise le tableau pour compter
						chiffre_utilise[n]=0;
					for (m=0;m<InterfaceGrille.BORNE;m++) {
						//on parcourt les 9 suivants pour recenser les chiffres non utilis�s.
						for (n=1;n<=Case.BORNEPOSSIBLE;n++)
							if (maGrille.getCase(c).getPossible(n))
								chiffre_utilise[n]++ ;

						c=maGrille.getCase(c).getSuivant(k);
					}

					// maintenant on cherche ceux qui ont �t� compt� qu'une seule fois
					for (n=1;n<=Case.BORNEPOSSIBLE;n++)
						if (chiffre_utilise[n]==1) {
							trouve =true;
							for (m=0;m<InterfaceGrille.BORNE;m++) {
								//on parcours les 9 suivants pour recenser les chiffres non utilis�s.
								if (maGrille.getCase(c).estPossible(n))
									maGrille.setVal(c,n);
								c=maGrille.getCase(c).getSuivant(k);
							}
						}

				}
		if (trouve)
			maGrille.setIteration(maGrille.getIteration()+1);
		return trouve;
	}


}
