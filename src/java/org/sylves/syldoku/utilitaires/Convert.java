package org.sylves.syldoku.utilitaires;

import java.awt.GridBagConstraints;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.sylves.syldoku.suNoyau.Grille;
import org.sylves.syldoku.suNoyau.InterfaceGrille;


public abstract class Convert {

	 public final static String txt = "txt";
	 public final static String sdk = "sdk";
	 public static final String biblioSDK = "bsk";
	 public static final String nomFich = "sudoku";

	/**
	 * Renvoi l'extension d'un fichier.
	 */
	public static String getExtension(File f) {
		return getExtension(f.getName());
	}
	public static String getExtension(String s) {
		String ext = "";

		int i = s.lastIndexOf('.');

		if (i > 0 &&  i < s.length() - 1) {
			ext = s.substring(i+1).toLowerCase();
		}
		return ext;
	}


	/**
	 * transforme une durée en milli seconde en une chaîne String retournée, qui
	 * renvoie cette durée en Jour heure minute seconde
	 *
	 * @param la durée en milliseconde
	 * @return la chaîne représentant cette durée en jour/heure/minute/seconde
	 *         et millième
	 */
	public static String milli2String(long top) {
		String temps = "";
		long sec = top / 1000;
		long milli = (top - (sec) * 1000);
		long min = sec / 60;
		long heure = min / 60;
		long jour = heure / 24;
		sec -= min * 60;
		min -= heure * 60;
		heure -= jour * 24;
		if (jour > 0)
			temps = jour + " j " + heure + " h " + min + " m ";
		else if (heure > 0)
			temps = heure + " h " + min + " m ";
		else if (min > 0)
			temps += min + " m ";
		temps += sec + " s " + String.format("%03d", milli);
		return temps;
	}

	public static InterfaceGrille changeGrille(InterfaceGrille g) {
		char [] tabstr = new char [10];
		char [] tabsubst = new char [10];
		int indicetab=1, j,mem=-1;
		char derniercar='1';
		String grilleTransformee="";
		char c;
		boolean trouve;
		String maGrille = g.toString();
		tabstr[0]=maGrille.charAt(0);
		if (tabstr[0]=='0')
			tabsubst[0]='0';//'.'
		else
			tabsubst[0]=derniercar++;
		grilleTransformee += tabsubst[0];
		for (int i=1; i<81 ;i++){
			c=maGrille.charAt(i);
			trouve = false;
			j=0;
			while (j<indicetab){
				if (c==tabstr[j]) {
					trouve = true;
					mem=j;
				}
				j++;
			}
			if (trouve)
				grilleTransformee += tabsubst[mem];
			else {
				tabstr[indicetab]=c;
				if (c=='0')
					tabsubst[indicetab]='0';//'.'
				else
					tabsubst[indicetab]=derniercar++;
				grilleTransformee += tabsubst[indicetab++];

			}
		}
		return new Grille(grilleTransformee);
	}

	public static void chercheDoublon(String chemin){
		GestionFichier toto = new GestionFichier(GestionFichier.LIRE,chemin+"sudoku17.bsk");
		Vector <String> monVecteur = toto.getContenu();
		toto.fermer();
		ArrayList<String> maColl =  new ArrayList<String>();
		String maNlleGrille;
		toto = new GestionFichier(GestionFichier.ECRIRE,chemin+"test17.bsk");
		for (String maGrille : monVecteur) {
			maNlleGrille=changeGrille(new Grille(maGrille)).toString();
			if (!maColl.contains(maNlleGrille))
				maColl.add(maNlleGrille);
			else
				JOptionPane.showMessageDialog(null, maGrille+"\nDéjà vu\n"+changeGrille(new Grille(maGrille)).toString());
			toto.ecrire(maNlleGrille);
		}
		toto.fermer();
	}

public static InterfaceGrille tourneGrille(InterfaceGrille g){
	String reverse ="";
	int i=8,j;
	String grille = g.toString();
	for (j=0; j<81;j++) {
		reverse += grille.charAt(i);
		i += 9;
		if (i>81)
			i -= 82;
	}
	return new Grille(reverse);
}
public static InterfaceGrille symetrieGrille(InterfaceGrille g) {
	String reverse ="";
	int i=8,j,k=0;
	String grille = g.toString();
	for (j=0; j<81;j++) {
		reverse += grille.charAt(i+k);
		i--;
		if (i<0){
			i = 8;
			k +=9;
		}

	}
	return new Grille(reverse);
}
/**
 * permet de construire une contrainte pour une layout de type GridBagLayout
 *
 * @param gbc : le GridBagConstraint
 * @param gx : position en abcisse de la case dans la grille
 * @param gy : position en ordonnée de la case dans la grille
 * @param gw : largeur proportionelle
 * @param gh : hauteur proportionelle
 * @param wx :
 * @param wy :
 * @param anchor : position de départ (LINESTART etc : voir constantes de GridBagConstraint)
 * @param fill : Remplissage dans la case de l'objet (VERTICAL, HORIZONTAL, BOTH)
 * */
public static void  construire_contrainte(GridBagConstraints gbc,int gx,int gy, int gw,int gh, double wx,double wy, int fill, int anchor){
	gbc.gridx=gx;
	gbc.gridy=gy;
	gbc.gridwidth=gw;
	gbc.gridheight=gh;
	gbc.weightx=wx;
	gbc.weighty=wy;
	gbc.anchor=anchor;
	gbc.fill=fill;
}
}

