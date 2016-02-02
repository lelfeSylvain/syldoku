package tuto_swing.Chapitre4.Etape1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Additionneur extends JFrame implements ActionListener{
	private JPanel container = null;//Déclaration de l’objet JPanel	
	private FlowLayout layout = null ;//Déclaration de notre layout
	
	public Additionneur(){
		super();

		build();
	}

	public static void main(String[] args){
		Additionneur gui = new Additionneur();
		gui.setVisible(true);
	}
	
	private void build(){
		this.setTitle("Additionneur"); //On donne un titre à l’application
		this.setSize(200,150); //On donne une taille à notre fenêtre
		this.setLocationRelativeTo(null); //On centre la fenêtre sur l’écran
		this.setResizable(false) ; //On interdit la redimensionnement de l’écran
		this.setContentPane(getContainer()) ;//On lui dit de mettre le panel en fond
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l’application de se fermer lors du clic sur la croix
	}

	private JPanel getContainer(){
		layout = new FlowLayout() ; //Instanciation du layout
		layout.setAlignment(FlowLayout.LEFT) ;
		
		container = new JPanel() ; //On crée notre objet
		container.setLayout(layout);
		
		return container ;
	}

	public void actionPerformed(ActionEvent e){
		
	}
}