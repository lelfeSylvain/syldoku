package tuto_swing.Chapitre4.Etape2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Additionneur extends JFrame implements ActionListener{
	private JPanel container = null;//Déclaration de l’objet JPanel	
	private FlowLayout layout = null ;//Déclaration de notre layout

	//Composants de la calculatrice
	private JButton buttonCalcul = null ;
	private JLabel labelOperateur = null ;
	private JLabel labelResultat = null;
	private JTextField fieldNombre1 = null;
	private JTextField fieldNombre2 = null;
	
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
		
		fieldNombre1 = new JTextField();
		fieldNombre1.setPreferredSize(new Dimension(75,25));
		container.add(fieldNombre1);
		
		labelOperateur = new JLabel("+");
		labelOperateur.setPreferredSize(new Dimension(7,25));
		container.add(labelOperateur);
			
		fieldNombre2 = new JTextField();
		fieldNombre2.setPreferredSize(new Dimension(75,25));
		container.add(fieldNombre2);

		buttonCalcul = new JButton("Calculer");
		buttonCalcul.setPreferredSize(new Dimension(125,25));
		buttonCalcul.addActionListener(this) ;
		container.add(buttonCalcul);
			
		labelResultat = new JLabel("Résultat = ");
		labelResultat.setPreferredSize(new Dimension(180,25));
		container.add(labelResultat);
		
		return container ;
	}

	public void actionPerformed(ActionEvent e){
		
	}
}