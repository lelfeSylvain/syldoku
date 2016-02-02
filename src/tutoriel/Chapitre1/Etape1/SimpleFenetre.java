package tuto_swing.Chapitre1.Etape1;

import javax.swing.*;

public class SimpleFenetre extends JFrame{
	
	public SimpleFenetre(){
		super();
		
		build();//On initialise notre fen�tre
	}
	
	private void build(){
		this.setTitle("Ma premi�re application"); //On donne un titre � l�application
		this.setSize(320,240); //On donne une taille � notre fen�tre
		this.setLocationRelativeTo(null); //On centre la fen�tre sur l��cran
		this.setResizable(false) ; //On interdit la redimensionnement de la fen�tre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l�application de se fermer lors du clic sur la croix
	}
}