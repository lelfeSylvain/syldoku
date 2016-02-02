package tuto_swing.Chapitre2.Etape2;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class FenetreAvecTexte extends JFrame{
	private JPanel container = null;//Déclaration de l’objet JPanel	
	private FlowLayout layout = null ;//Déclaration de notre layout
	private JLabel texte = null;//Déclaration de l'objet JLabel
	
	public FenetreAvecTexte(){
		super();
		
		build();//On initialise notre fenêtre
	}
	
	private void build(){
		this.setContentPane(getContainer());
		this.setTitle("Ma première application"); //On donne un titre à l’application
		this.setSize(320,240); //On donne une taille à notre fenêtre
		this.setLocationRelativeTo(null); //On centre la fenêtre sur l’écran
		this.setResizable(false) ; //On interdit la redimensionnement de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l’application de se fermer lors du clic sur la croix	
	}

	public static void main(String[] args){
		FenetreAvecTexte gui = new FenetreAvecTexte(); //On crée une nouvelle instance de notre fenêtre
		gui.setVisible(true);
	}

	private JPanel getContainer(){
		layout = new FlowLayout(); //Instanciation du layout
		layout.setAlignment(FlowLayout.CENTER);//On centre les composants
		
		container = new JPanel() ; //On crée notre objet
		container.setLayout(layout); //On applique le layout
		
		texte = new JLabel() ;//ON créer notre objet
		texte.setPreferredSize(new Dimension(250,25)) ;//On lui donne une taille
		texte.setText("Bienvenue dans ma première application"); //On lui donne un texte
		
		container.add(texte);

		return container ;
	}
}
