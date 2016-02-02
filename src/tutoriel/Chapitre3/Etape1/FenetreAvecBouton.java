package tuto_swing.Chapitre3.Etape1;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class FenetreAvecBouton extends JFrame{
	private JPanel container = null;//D�claration de l�objet JPanel	
	private FlowLayout layout = null ;//D�claration de notre layout
	private JLabel texte = null;//D�claration de l'objet JLabel
	private JButton bouton = null;//D�claration du bouton
	
	public FenetreAvecBouton(){
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
		FenetreAvecBouton gui = new FenetreAvecBouton(); //On cr�e une nouvelle instance de notre fen�tre
		gui.setVisible(true);
	}

	private JPanel getContainer(){
		layout = new FlowLayout(); //Instanciation du layout
		layout.setAlignment(FlowLayout.CENTER);//On centre les composants
		
		container = new JPanel() ; //On cr�e notre objet
		container.setLayout(layout); //On applique le layout
		
		texte = new JLabel() ;//On cr�er notre objet
		texte.setPreferredSize(new Dimension(250,25)) ;//On lui donne une taille
		texte.setText("Bienvenue dans ma premi�re application"); //On lui donne un texte

		container.add(texte);
		
		bouton = new JButton () ;//Cr�ation du bouton
		bouton.setPreferredSize(new Dimension(125,25)) ;//On lui donne une taille
		bouton.setText("Cliquer ici") ;//On lui donne un texte
		
		container.add(bouton);//On l�ajoute � la fen�tre
		
		return container ;
	}
}