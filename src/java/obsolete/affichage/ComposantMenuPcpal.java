package org.sylves.affichage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;


@SuppressWarnings("serial")
public class ComposantMenuPcpal extends JMenuBar implements ActionListener {
	// Application Parent
	FrmGrilleSudokuAvecMenu monAppli = null;
	//Composants graphique du menu

	private JMenu fichier = null ;
	private JMenuItem nouveau = null;
	private JMenuItem ouvrir = null;
	private JMenuItem sauver = null;
	private JMenuItem sauversous = null;
	private JMenuItem biblio = null;

	private JMenuItem quitter = null ;
//	 ---
	private JMenu edition = null;
	private JMenuItem raz = null;
	private JMenuItem editer = null;
//	 ---
	private JMenu outils = null;
	private JMenuItem calculer = null ;
//	 ---
	private JMenu fenetre = null;
	private JMenuItem toutAff;
	private JMenuItem reorg;
	private JMenuItem reduire;
	private JMenuItem fermer;
	//	 ---
	private JMenu apropos = null ;
	private JMenuItem aPropos = null ;

	private JSeparator pasdemenu;




	/**
	 *
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == calculer){
			monAppli.calculer();
		}else if(e.getSource() == quitter){
			monAppli.conclusion();
		}else if(e.getSource() == aPropos){
			JOptionPane.showMessageDialog(null,"Ce programme a été développé par l'Elfe Sylvain \n Les dimensions de la fenêtre principale sont : "+monAppli.getHeight() + " x " +monAppli.getWidth()+"\n");
		}else if (e.getSource() == ouvrir){
			monAppli.ouvrir();
		}else if (e.getSource() == editer){
			monAppli.editer();
		}else if ((e.getSource() == raz) || (e.getSource()== nouveau)){
			monAppli.raz();
		}else if (e.getSource() == reorg){
			monAppli.reorg();
		}else if (e.getSource() == reduire){
			monAppli.reduire();
		}else if (e.getSource() == fermer){
			monAppli.fermer();
		}else if (e.getSource() == biblio){
			monAppli.biblio();
		}else if (e.getSource() == sauversous){
			monAppli.sauversous();
		}else if (e.getSource() == sauver){
			monAppli.sauver();
		}else if (e.getSource() == toutAff){
			monAppli.toutAfficher();
		}else {// autres événements en dehors de ceux de la grille : ceux-ci sont gérés par actionPerformed de la grille elle même
		}
	}

	/**
	 * Constructeur
	 */
	public ComposantMenuPcpal(FrmGrilleSudokuAvecMenu a) {
		super();
		// lien avec l'appli parent
		monAppli = a;
		fichier = new JMenu("Fichier") ;
		/**** Menu Fichier ****/
		nouveau = new JMenuItem("Nouvelle Grille");
		nouveau.addActionListener(this);
		fichier.add(nouveau);
		ouvrir = new JMenuItem("Ouvrir") ;
		ouvrir.addActionListener(this) ;
		fichier.add(ouvrir) ;
		sauver= new JMenuItem("Enregistrer") ;
		sauver.addActionListener(this) ;
		fichier.add(sauver) ;
		sauversous= new JMenuItem("Enregistrer sous...") ;
		sauversous.addActionListener(this) ;
		fichier.add(sauversous) ;
		biblio= new JMenuItem("Ajouter à une bibliothèque...") ;
		biblio.addActionListener(this) ;
		fichier.add(biblio) ;

		pasdemenu = new JSeparator() ;

		fichier.add(pasdemenu) ;
		quitter = new JMenuItem("Quitter") ;
		quitter.addActionListener(this) ;
		fichier.add(quitter) ;

		add(fichier) ;
		/**** menu Edition ***/
		edition = new JMenu("Edition");
		add(edition);
		raz = new JMenuItem("Tout effacer") ;
		raz.addActionListener(this) ;
		edition.add(raz) ;
		pasdemenu = new JSeparator() ;
		edition.add(pasdemenu);
		editer = new JMenuItem("Editer");
		editer.addActionListener(this);
		edition.add(editer);
		/**** menu Outils ****/
		outils = new JMenu("Outil") ;

		calculer = new JMenuItem("Résoudre") ;
		calculer.addActionListener(this) ;
		outils.add(calculer) ;

		add(outils) ;
		/**** menu Fenetre ****/
		fenetre = new JMenu("Fenêtres") ;

		toutAff= new JMenuItem("Afficher toutes les solutions") ;
		toutAff.addActionListener(this) ;
		fenetre.add(toutAff) ;

		reorg = new JMenuItem("Réorganiser toutes les solutions") ;
		reorg.addActionListener(this) ;
		fenetre.add(reorg) ;

		reduire = new JMenuItem("Réduire toutes les solutions") ;
		reduire.addActionListener(this) ;
		fenetre.add(reduire) ;

		fermer = new JMenuItem("Fermer toutes les solutions") ;
		fermer.addActionListener(this) ;
		fenetre.add(fermer) ;
		add(fenetre) ;
		/**** A propos ****/
		apropos = new JMenu("?") ;

		aPropos = new JMenuItem("A propos de...") ;
		aPropos.addActionListener(this) ;
		apropos.add(aPropos) ;

		add(apropos) ;
	}

}
