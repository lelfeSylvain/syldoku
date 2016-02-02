package org.sylves.affichage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.sylves.Controleur.InterfaceControleurSyldoku;
import org.sylves.syldoku.suNoyau.Convert;
import org.sylves.syldoku.suNoyau.InterfaceModeleResolution;
import org.sylves.syldoku.suNoyau.ResolutionGrille;

public class FenetreStatistique extends JFrame implements InterfaceObservateurResolution, ActionListener {

	// Sujet de l'observateur : le mod�le du MVC
	private InterfaceModeleResolution modele;
	// Controleur du MVC
	private InterfaceControleurSyldoku controleur;

//	 COnstantes pour la mise en page de la grille
	private static final int frmLargeur = 300; // fen�tre
	private static final int frmHauteur = 300;
	private JPanel container = null;//D�claration de l�objet JPanel
	private FlowLayout layout = null ;//D�claration de notre layout
	private JLabel lblEtiquette = null;
	private JLabel lblEtiquette2 = null;
	private JLabel lblEtiquette3 = null;
	private JLabel lblAucuneGrille= null;
	private DefaultComboBoxModel comboModel;
	private JComboBox combo;

	/*****************************
	 * Constructeur
	 *
	 */

	public FenetreStatistique(InterfaceModeleResolution modele, InterfaceControleurSyldoku controleur){
		super();

		this.modele=modele;
		modele.enregistrerObservateur(this);
		this.controleur=controleur;

		build();
	}

	/******************************
	 * Proc�dures de contruction de la fen�tre graphique
	 *
	 */
	private void build(){


		this.setTitle("SylDoku : Statistiques"); //On donne un titre � l�application
		this.setSize(frmLargeur,frmHauteur); //On donne une taille � notre fen�tre
		this.setLocation(0,400);//On centre la fen�tre sur l��cran
		this.setResizable(false) ; //On interdit la redimensionnement de l��cran
		buildContainer();
		this.setContentPane(container) ;//On lui dit de mettre le panel en fond
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l�application de se fermer lors du clic sur la croix

		this.setVisible(true);
	}


	/************************
	 * Construit la fen�tre utilis� par Build()
	 * @return
	 */
	private void buildContainer(){

		Dimension dimBlanc;// label pour la mise en page de la grille

		layout = new FlowLayout() ; //Instanciation du layout
		layout.setAlignment(FlowLayout.LEFT) ;

		container = new JPanel() ; //On cr�e notre objet
		container.setLayout(layout);


		dimBlanc= new Dimension(280,50);
		lblEtiquette = new JLabel("Pas de r�solution faite pour le moment.");
		lblEtiquette.setPreferredSize(dimBlanc);
		container.add(lblEtiquette);
		lblEtiquette = new JLabel("Pas de r�solution faite pour le moment.");
		lblEtiquette.setPreferredSize(dimBlanc);
		container.add(lblEtiquette);

		lblEtiquette2 = new JLabel("Pas de r�solution faite pour le moment.");
		lblEtiquette2.setPreferredSize(dimBlanc);
		container.add(lblEtiquette2);


		lblEtiquette3 = new JLabel("Pas de r�solution faite pour le moment.");
		lblEtiquette3.setPreferredSize(dimBlanc);
		container.add(lblEtiquette3);

		lblAucuneGrille = new JLabel("Pas de solution trouv�e !");
		lblAucuneGrille.setPreferredSize(dimBlanc);
		container.add(lblAucuneGrille);
		lblAucuneGrille.setVisible(false);


//		 combobox
		comboModel = new DefaultComboBoxModel();



		combo = new JComboBox(comboModel);
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((e.getID()==1001)) {//la combobox a chang� de valeur
						// cet �v�nement arrive aussi lorsqu'on vide la liste
						// aussi il faut d�tecter sur quel �l�ment de la liste
						// j'ai cliqu� !
						if (combo.getSelectedIndex()>=0){
							//JOptionPane.showMessageDialog(null,combo.getSelectedIndex());
							GrilleSudoku solution = new GrilleSudoku(Convert.avec2sans(modele.getGrille().getSolution(combo.getSelectedIndex())), combo.getSelectedItem().toString());
							solution.setVisible(true);
						}
				}
				else {//evenement non r�solu
					JOptionPane.showMessageDialog(null,e.getID() + " - " +e.getActionCommand());

				}

			}
		});
		container.add(combo);
		combo.setVisible(false);
		//return container ;
	}

	/**************************
	 *  m�thode pour g�rer les �v�nements dans la fen�tre
	 */

	public void actionPerformed(ActionEvent e){
		// aiguillage �venementiel
		/*int numBouton;
		char numero;
		JButton boutonClique;
		if(e.getSource() == calculer){
			calculer();
		}else if(e.getSource() == quitter){
			conclusion();
		}else if(e.getSource() == aPropos){
			JOptionPane.showMessageDialog(null,"Ce programme a �t� d�velopp� par l'Elfe Sylvain");
		}else if (e.getSource() == ouvrir){
			ouvrir();
		}else {



		}*/
	}

	/*********************
	 * m�thode observateur de MAJ
	 */
	public void actualiser() {
		int nbsol= modele.getGrille().getLesSolutions().size();

		lblEtiquette.setText("R�solution achev�e - nombre de grille : " + nbsol );
		lblEtiquette2.setText("Nombre limite : " + modele.getProfondeurLimite());
		lblEtiquette3.setText("Profondeur Max: " + ResolutionGrille.maxprofondeur );

			comboModel.removeAllElements();

		if (nbsol>0) {
			for (int i=0;i<nbsol;i++){
				comboModel.addElement("solution n�"+String.valueOf(i+1));
			}
			combo.setVisible(true);
			lblAucuneGrille.setVisible(false);
		}
		else {
			combo.setVisible(false);
			lblAucuneGrille.setVisible(true);
		}



	}


}
