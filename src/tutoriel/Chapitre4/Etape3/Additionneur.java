package tuto_swing.Chapitre4.Etape3;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Additionneur extends JFrame implements ActionListener{
	private JPanel container = null;//D�claration de l�objet JPanel	
	private FlowLayout layout = null ;//D�claration de notre layout

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
		this.setTitle("Additionneur"); //On donne un titre � l�application
		this.setSize(200,150); //On donne une taille � notre fen�tre
		this.setLocationRelativeTo(null); //On centre la fen�tre sur l��cran
		this.setResizable(false) ; //On interdit la redimensionnement de l��cran
		this.setContentPane(getContainer()) ;//On lui dit de mettre le panel en fond
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l�application de se fermer lors du clic sur la croix
	}

	private JPanel getContainer(){
		layout = new FlowLayout() ; //Instanciation du layout
		layout.setAlignment(FlowLayout.LEFT) ;
		
		container = new JPanel() ; //On cr�e notre objet
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
			
		labelResultat = new JLabel("R�sultat = ");
		labelResultat.setPreferredSize(new Dimension(180,25));
		container.add(labelResultat);
		
		return container ;
	}

	public void actionPerformed(ActionEvent e){
		String nombre1String = fieldNombre1.getText();//On r�cup�re la valeur dans le premier champ
		int nombre1 = Integer.parseInt(nombre1String);//On convertit cette valeur en un nombre

		String nombre2String = fieldNombre2.getText();//On r�cup�re la valeur dans le deuxi�me champ
		int nombre2 = Integer.parseInt(nombre2String);//On convertit cette valeur en un nombre

		int resultat = nombre1 + nombre2;
		labelResultat.setText("R�sultat = " + resultat);
	}
}