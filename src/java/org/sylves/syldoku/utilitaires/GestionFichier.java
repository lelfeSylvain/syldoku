package org.sylves.syldoku.utilitaires;


import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

import javax.swing.JOptionPane;


public class GestionFichier {
FileWriter monFichier;
FileReader maLecture;
private int typeAcces;
private int dernierCarLu;

final static public int ECRIRE = 0;
final static public int ECRIREAPRES = 1;
final static public int LIRE = 2;

public  GestionFichier(int ecrire, String fichier) {
	super();
	typeAcces = ecrire;
	try {
	if (typeAcces==ECRIRE)
		monFichier= new FileWriter(fichier);
	else if (typeAcces==ECRIREAPRES)
		monFichier= new FileWriter(fichier,true);
	else
		maLecture = new FileReader(fichier);
	}
	catch ( java.io.IOException e ) {
		e.printStackTrace();
    }
}

public void ecrire(String maChaine) {
	if ((typeAcces==ECRIRE) || (typeAcces==ECRIREAPRES))
		try {
			monFichier.write(maChaine+ (char)13+ (char)(10));
		}
		catch ( java.io.IOException e ) {
		e.printStackTrace();
		}
}

public String lire() {
	String maChaine = "";
	if (typeAcces==LIRE) {
		boolean EOS =false;
		try{
			while (maLecture.ready() && !EOS){
				dernierCarLu=maLecture.read();
				EOS =  finDeLigne();
				if (!EOS)
					maChaine += (char) dernierCarLu;
			}
		}
		catch ( java.io.IOException e ) {
			e.printStackTrace();
	    }
	}
	return maChaine;
}


private boolean finDeLigne()  {
	boolean retour=false;
	if (dernierCarLu==13){
		try {
			if (maLecture.ready()) {
				dernierCarLu=maLecture.read();
				retour=(dernierCarLu==10);
			}
		}
		catch ( java.io.IOException e ) {
			e.printStackTrace();
		}
	}
	else
		retour= (dernierCarLu=='\n');
	return retour;
}

public boolean estPresent(String grille){
	boolean trouve = false;
	String lu;
	try {
		if (typeAcces==LIRE) {
			while (maLecture.ready() && !trouve){
				lu=lire();
				trouve=(lu.equals(grille));
			}
		}

	}
	catch ( java.io.IOException e ) {
		e.printStackTrace();
	}
	return trouve;
}

public void fermer() {
	try {
		if ((typeAcces==ECRIRE) || (typeAcces==ECRIREAPRES))
			monFichier.close();
		else
			maLecture.close();
	}
	catch ( java.io.IOException e ) {
		e.printStackTrace();
	}
}

	public Vector<String> getContenu() {
		Vector<String> retour = new Vector<String>();
		long i = 0;
		boolean fin = false;
		int j = 0;
		try {
			if (typeAcces == LIRE) {
				while (!fin) {
					while (maLecture.ready() && (i) < 10000) {
						retour.addElement(lire());
						i++;
					}
					j += i;
					i = 0;
					fin =  !maLecture.ready();
					if (!fin) {
						fin = (JOptionPane.showConfirmDialog(null, "déjà " + j
								+ " grilles lues\nVoulez-vous continuer ?",
								"Confirmation", JOptionPane.YES_NO_OPTION) != JOptionPane.OK_OPTION);
					}
				}
				if (j>1)
					JOptionPane.showMessageDialog(null, "" + j + " grilles lues !");
				else
					JOptionPane.showMessageDialog(null, "" + j + " grille lue !");
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return retour;
	}

}