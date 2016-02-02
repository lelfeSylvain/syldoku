package org.sylves.syldoku.affichage;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


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
	return retour;
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

}