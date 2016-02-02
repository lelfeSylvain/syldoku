package org.sylves.syldoku.suNoyau;
/**
 * permet de localiser les coordonn�es d'une case dans la grille
 * @author Sylvain Parise
 *
 */
public class Coordonnee {
	/**
	 * @uml.property  name="i"
	 */
	private int i;
	/**
	 * @uml.property  name="j"
	 */
	private int j;
	/*m�thodes*/
	/**
	 * @return  Renvoie i.
	 * @uml.property  name="i"
	 */
	public int getI() {
		return i;
	}
	/**
	 * @param i  i � d�finir.
	 * @uml.property  name="i"
	 */
	public void setI(int i) {
		this.i = i;
	}
	/**
	 * @return  Renvoie j.
	 * @uml.property  name="j"
	 */
	public int getJ() {
		return j;
	}
	/**
	 * @param j  j � d�finir.
	 * @uml.property  name="j"
	 */
	public void setJ(int j) {
		this.j = j;
	}
	/*constructeur*/
	public Coordonnee(int i, int j) {
		this.i=i;
		this.j=j;
	}
	public Coordonnee(Coordonnee c) {
		this.i=c.i;
		this.j=c.j;
	}
	public Coordonnee() {
		this.i=0;
		this.j=0;
	}
	/** surcharge pour d�boggueur
	 *
	 */
	public String toString() {
		return "("+String.valueOf(this.i)+","+String.valueOf(this.j)+")";
	}
}
