package org.sylves.syldoku.suNoyau;

/**
 * L'objet SuCase est une Case du Sudoku qui connait ses voisins horizontaux, verticaux et de bloc
 * @author  Sylvain Parise
 */
public class SuCase extends Case {

	/**
	 * @uml.property  name="suivant"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	private Coordonnee suivant [];
	/**
	 * d�fini un groupe de suivant selon un indice de parcours du tableau suivant ici une ligne horizontale
	 */
	public static final int HORI =1;
	/**
	 * d�fini un groupe de suivant selon un indice de parcours du tableau suivant ici une ligne verticale
	 */
	public static final int VERT =2;
	/**
	 * d�fini un groupe de suivant selon un indice de parcours du tableau suivant ici un bloc de 3 x 3
	 */
	public static final int BLOC =0;

	/**Contructeurs*/
	public SuCase(int val) {
		super(val);
		suivant = new Coordonnee[3];
	}

	public SuCase() {
		super();
		suivant = new Coordonnee[3];
		for (int i=0;i<3;i++)
				suivant[i]= new Coordonnee();
	}

	/** renvoie les coordonn�es de la case suivante appartenant au groupe groupe
	 *
	 * @param groupe : le groupe choisi (HORI,BLOC ou VERT)
	 * @return les coordonn�es (Coordonnee) de la case suivante
	 */
	public  Coordonnee getSuivant(int groupe) {
		return suivant[groupe];
	}
	/** affecte un suivant selon un groupe d�sign�
	 *
	 * @param groupe : groupe choisi (HORI, VERT, BLOC)
	 * @param suivant : les coordonn�es de la case suivante
	 */
	public  void setSuivant(int groupe, Coordonnee suivant) {
		this.suivant[groupe].setJ( suivant.getJ());
		this.suivant[groupe].setI( suivant.getI());
	}
	/** affecte un suivant selon un groupe d�sign�
	 *
	 * @param groupe : groupe choisi (HORI, VERT, BLOC)
	 * @param i : l'abcisse de la case suivante
	 * @param j : l'ordonn�e de la case suivante
	 */
	public  void setSuivant(int groupe, int i, int j) {
		this.suivant[groupe].setJ( j);
		this.suivant[groupe].setI( i);
	}
}
