package org.sylves.syldoku.suNoyau;
/**
 * L'objet Case d�crit le fonctionnement d'une case du sudoku
 * @author Sylvain Parise
 */
public class Case {
	/** permet de v�rifier si le chiffre correspondant �
	l'indice du tableau est encore possible (TRUE) ou non
	si l'indice 0 est � faux, tout le tableau est � faux*/
	private boolean  possible[];
	/**valeur de la case si elle est initialis�e
	*lorsque (possible[0]=faux)
	*
	 * @uml.property  name="val"
	 */
	private int val;
	/**Indice max des cases dans le tableau des possibles*/
	public static int BORNEPOSSIBLE =9;

	/** initialise le tableau possible de la case � la m�me valeur : l'argument
	 * drapeau
	 */
	private void setPossible(boolean drapeau) {
		int i;
		for (i=0;i<=BORNEPOSSIBLE;i++) {
			possible[i]=drapeau;
		}
	}
	/** rend toutes les cases du tableau des possibles � faux (il n'y a plus de possibilit�s)*/
	public void setImPossible() {
		setPossible(false);
	}
	/** rend toutes les cases du tableau des possibles � vrai (on ouvre toutes les possibilit�s)*/
	public void setAllPossible() {
		setPossible(true);
	}

	/** renvoie la valeur du possible i
	 *
	 * @param i indice du possible recherch�
	 * @return la possibilit� de l'indice recherch�...
	 */
	public boolean getPossible(int i) {
		return (possible[i]);
	}
	/** renvoie une valeur toujours possible pour la case recherch�e
	 * @return 0 si pas de possible, un chiffre possible sinon*/
	public int getPossible() {
		int i;
		int retour =0;
		for (i=1;i<=BORNEPOSSIBLE;i++)
			if (possible[i])
				retour=i;
		return retour;
	}
	/** retourne le nombre de possible pour la case
	 * @return : nombre de possible pour la case*/
	public int getNbPossible() {
		int nb=0;
		int i;
		for (i=1;i<=BORNEPOSSIBLE;i++)
			if (possible[i])
					nb++;
		return nb;
	}


	/** affecte la valeur drapeau � l'indice chiffre */
	public void setPossible(boolean drapeau, int chiffre) {
			possible[chiffre]=drapeau;
	}
	/** retourne le nombre de possible pour cette case.
	 * @return 0 si la case est d�j� affect� ou
	 * le chiffre possible s'il n'y en a qu'une seule valeur possible ou
	 * 10 + le nombre de possible
	 *
	 */
	public int getSeulPossible() {
		int i,nb=0,mem=0;
		for(i=1;i<=BORNEPOSSIBLE;i++) {
			if (possible[i]) {
				nb++;
				mem=i;
			}
		}
		if (nb>1)
			mem=10+nb;

		return  mem;
	}
	/** Renvoi vrai si k appartient � la liste des possibles de la case
	 *
	 * @param �l�ment dont on cherche � �valuer la pr�sence dans les possibles
	 * @return vrai si k appartient � la liste des possibles
	 */
	public boolean estPossible(int k) {

		return possible[k];
	}
	/**
	 * renvoie la valeur de la case
	 * @uml.property  name="val"
	 */
	public int getVal() {
		return val;
	}
	/**
	 * affecte une valeur � la case  - bloque les possibilit�s de cette case
	 * @uml.property  name="val"
	 */
	public void setVal(int val) {
		this.val = val;
		setImPossible();
	}
	/** contructeur de la case qui contiendra une valeur */
	public Case (int val) {
		possible = new boolean[BORNEPOSSIBLE+1];
		setVal(val);
	}
	/** constructeur de la case sans valeur */
	public Case () {
		possible = new boolean[BORNEPOSSIBLE+1];
		initCase();
	}
	/** (r�)initialise une case � 0 ainsi que le tableau des possibles */
	public void initCase(){
		val=0;
		setAllPossible();
	}
	/** retourne une chaine de caract�re repr�sentant le contenu de la case*/
	public String toString() {
		int i;
		boolean existe=false;
		String retour =String.valueOf(val) ;
		for (i=1;i<=BORNEPOSSIBLE;i++) {
			if (possible[i]){
				if (!existe){
					existe=true;
					retour+="(";
				}
			    retour+=String.valueOf(i);
			}
		}
		if (existe)
			retour+=")";
		retour+="|";
		return retour;
	}
}
