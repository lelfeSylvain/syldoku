package tuto_swing.Chapitre7;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculateurAvecMenu extends JFrame implements ActionListener{
	private JPanel container = null;//Déclaration de l’objet JPanel	
	private FlowLayout layout = null ;//Déclaration de notre layout

	//Composants de la calculatrice
	private JButton buttonCalcul = null ;
	private JLabel labelResultat = null;
	private JTextField fieldNombre1 = null;
	private JTextField fieldNombre2 = null;
	private JComboBox listOperateurs = null ;
	private String[] operateurs = new String[]{"+","/","*","-"} ;

	//Composants du menu
	private JMenuBar menuBar = null ; ;
	private JMenu menu1 = null ;
	private JMenuItem calculer = null ;
	private JMenuItem quitter = null ;
	private JMenu menu2 = null ;
	private JMenuItem aPropos = null ;
	
	public CalculateurAvecMenu(){
		super();

		build();
	}

	public static void main(String[] args){
		CalculateurAvecMenu gui = new CalculateurAvecMenu();
		gui.setVisible(true);
	}
	
	private void build(){
		menuBar = new JMenuBar() ;

		menu1 = new JMenu("Calculatrice") ;

		calculer = new JMenuItem("Calculer") ;
		calculer.addActionListener(this) ;
		menu1.add(calculer) ;

		quitter = new JMenuItem("Quitter") ;
		quitter.addActionListener(this) ;
		menu1.add(quitter) ;

		menuBar.add(menu1) ;

		menu2 = new JMenu("?") ;

		aPropos = new JMenuItem("A propos") ;
		aPropos.addActionListener(this) ;
		menu2.add(aPropos) ;

		menuBar.add(menu2) ;
		this.setJMenuBar(menuBar) ;

		this.setTitle("Additionneur"); //On donne un titre à l’application
		this.setSize(275,200); //On donne une taille à notre fenêtre
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
		fieldNombre1.setText("0");
		fieldNombre1.setPreferredSize(new Dimension(75,25));
		container.add(fieldNombre1);
		
		listOperateurs = new JComboBox(operateurs);//On crée la liste en lui donnant un tableau d'opérateurs
		listOperateurs.setPreferredSize(new Dimension(100,25));//On lui donne une taille
		container.add(listOperateurs); //on l'ajoute à la fenêtre
			
		fieldNombre2 = new JTextField();
		fieldNombre2.setPreferredSize(new Dimension(75,25));
		fieldNombre2.setText("0");
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
		if(e.getSource() == buttonCalcul || e.getSource() == calculer){
			String nombre1String = fieldNombre1.getText();//On récupère la valeur dans le premier champ
			double nombre1 = Double.parseDouble(nombre1String);//On convertit cette valeur en un nombre

			String nombre2String = fieldNombre2.getText();//On récupère la valeur dans le deuxième champ
			double nombre2 = Double.parseDouble(nombre2String);//On convertit cette valeur en un nombre

			Object operateur = listOperateurs.getSelectedItem();
			listOperateurs.getSelectedIndex();
			
			double resultat = 0;
			
			if(operateur.equals("+")){
				resultat = nombre1 + nombre2;
			}
			else if (operateur.equals("-")){
				resultat = nombre1 - nombre2;
			}
			else if (operateur.equals("*")){
				resultat = nombre1 * nombre2; 
			}
			else if (operateur.equals("/")){
				resultat = nombre1 / nombre2; 
			}
			
			labelResultat.setText("Résultat = " +  resultat);
		}else if(e.getSource() == quitter){
			System.exit(0);
		}else if(e.getSource() == aPropos){
			JOptionPane.showMessageDialog(null,"Ce programme a été développé par XXX");
		}
	}
}