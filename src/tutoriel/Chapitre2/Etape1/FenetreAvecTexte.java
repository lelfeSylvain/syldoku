package tuto_swing.Chapitre2.Etape1;

import java.awt.FlowLayout;
import javax.swing.*;

public class FenetreAvecTexte extends JFrame{
	private JPanel container = null;//D�claration de l�objet JPanel	
	private FlowLayout layout = null ;//D�claration de notre layout
	
	public FenetreAvecTexte(){
		super();
		
		build();//On initialise notre fen�tre
	}
	
	private void build(){
		this.setContentPane(getContainer());
		this.setTitle("Ma premi�re application"); //On donne un titre � l�application
		this.setSize(320,240); //On donne une taille � notre fen�tre
		this.setLocationRelativeTo(null); //On centre la fen�tre sur l��cran
		this.setResizable(false) ; //On interdit la redimensionnement de la fen�tre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l�application de se fermer lors du clic sur la croix	
	}

	public static void main(String[] args){
		FenetreAvecTexte gui = new FenetreAvecTexte(); //On cr�e une nouvelle instance de notre fen�tre
		gui.setVisible(true);
	}

	private JPanel getContainer(){
		layout = new FlowLayout(); //Instanciation du layout
		layout.setAlignment(FlowLayout.CENTER);//On centre les composants
		
		container = new JPanel() ; //On cr�e notre objet
		container.setLayout(layout); //On applique le layout

		return container ;
	}
}
