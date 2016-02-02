package tuto_swing.Chapitre1.Etape1;

import javax.swing.*;

public class SimpleFenetre extends JFrame{
	
	public SimpleFenetre(){
		super();
		
		build();//On initialise notre fenêtre
	}
	
	private void build(){
		this.setTitle("Ma première application"); //On donne un titre à l’application
		this.setSize(320,240); //On donne une taille à notre fenêtre
		this.setLocationRelativeTo(null); //On centre la fenêtre sur l’écran
		this.setResizable(false) ; //On interdit la redimensionnement de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l’application de se fermer lors du clic sur la croix
	}
}