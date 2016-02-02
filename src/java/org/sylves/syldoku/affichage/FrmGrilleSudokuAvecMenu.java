package org.sylves.syldoku.affichage;
//biblioth�ques pour le GUI

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import org.sylves.syldoku.controleur.InterfaceControleurSyldoku;
import org.sylves.syldoku.suNoyau.Grille;
import org.sylves.syldoku.suNoyau.InterfaceModeleResolution;
import org.sylves.syldoku.utilitaires.Convert;
import org.sylves.syldoku.utilitaires.GestionFichier;
import org.sylves.syldoku.utilitaires.FilterSudoku;
import org.sylves.syldoku.utilitaires.FilterNameSudoku;
/**
 *	Fen�tre principale de l'application Syldoku
 */
public class FrmGrilleSudokuAvecMenu extends GrilleSudoku implements InterfaceObservateurResolution{


	private static final long serialVersionUID = 1L;

	//param�tres de la fen�tre
	protected int frmLargeur = 715;
	protected int frmHauteur = 400;
	/** Sujet de l'observateur : le mod�le du MVC */
	@SuppressWarnings("unused")
	private InterfaceModeleResolution modele;
	/** Controleur du MVC */
	private InterfaceControleurSyldoku controleur;
	/** permet d'enregistrer les liens avec les fen�tres solutions */
	private ArrayList<GrilleSudoku>  colSolutionAffichee;
	/** la grille a-t-elle �t� sauvegard�e une fois*/
	private boolean isSaved;
	/** chemin d'acc�s au fichier*/
	private String cheminSaved;
	private static String SsRep ="/Sauvegardes/";
	/** Chemin courant d'ex�cution de l'application */
	private String path=".";


	/**
	 * @return the isSaved
	 */
	public boolean isSaved() {
		return isSaved;
	}

	/**
	 * @param isSaved the isSaved to set
	 */
	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}

	/*****************************
	 * Constructeur : a besoin des r�f�rences au controleur et au mod�le
	 *
	 */

	public FrmGrilleSudokuAvecMenu(InterfaceModeleResolution modele, InterfaceControleurSyldoku controleur){
		super();
		//enregistre les liens avec le mod�le et le controleur
		this.modele=modele;
		modele.enregistrerObservateur(this);
		this.controleur=controleur;
		//R�pertoire de base d'ex�cution
		verifierPresenceRepertoire();
		//permet d'enregistrer les liens avec les fen�tres solutions
		colSolutionAffichee= new ArrayList<GrilleSudoku>();

		dimensionnerLaFenetre(JFrame.EXIT_ON_CLOSE,true,frmLargeur,frmHauteur);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l�application de se fermer lors du clic sur la croix
		this.setVisible(true);//la fen�tre appara�t

	}

	private void verifierPresenceRepertoire() {
		File monRep = new File(path);
		path=monRep.getAbsolutePath();
		File monSsRep = new File(path+SsRep);
		if (!monSsRep.exists()) {
			if (monRep.canWrite()) {
				monSsRep.mkdir();
			}
		}

	}

	protected void dimensionnerLaFenetre() {
		//this.setSize(frmLargeur,frmHauteur); //On donne une taille � notre fen�tre
		//this.setLocation(0,0); //On place la fen�tre sur l��cran en haut � gauche
	}

	/*protected void colorerGrille(String grille) {
		compGrilleVierge.setCouleur(grille, Color.BLACK);
	}*/
	protected void ajouterTitreContainer(String txt) {
		if (compGrilleVierge!=null){
			TitledBorder monborder = (TitledBorder) compGrilleVierge.getBorder();
			monborder.setTitle(txt);
		}
	}

	protected void ajouterComposantEnDessous() {
		compDessous = new ComposantResoudre(this);
		monPanel.add(compDessous);
		layout.setConstraints(compDessous, gbc);
	}

	protected void ajouterComposantADroite(){
		compADroite = new ComposantStat(this);
		monPanel.add(compADroite);
		layout.setConstraints(compADroite,gbc);
	}
	/***************************
	 * Ajoute le menu principal dans la fen�tre principale
	 */
	protected void ajouterMenu() {
		menuBar = new ComposantMenuPcpal(this) ;
		this.setJMenuBar(menuBar) ;
	}
	/*********************
	 *
	 * fermeture de l'application
	 */
	public void conclusion(){
		if (avertissementAvantRAZ()) {
			System.exit(0);
		}
	}
	/*********************
	 * Appel au controleur qui va contacter le mod�le pour la r�solution de la grille de Sudoku
	 * actuellement affich� � l'�cran
	 */
	public void calculer() {
		compGrilleVierge.setEnabled(false);

		controleur.setGrille(compGrilleVierge.getGrille());
		if (controleur.estPossibleAResoudre()) {
//			JOptionPane.showMessageDialog(null,"Grille possible � r�soudre");
//			JOptionPane.showMessageDialog(null,compGrilleVierge.getGrille());
			compADroite.topChrono(true);
			controleur.resoudreGrille(compADroite.getProfondeur());
			//suite grace � un m�canisme d'observateur/observ� : actualiser
		}
		else {
//			 impossible � r�soudre
			JOptionPane.showMessageDialog(null,"Grille impossible � r�soudre");
		}

	}


	/**
	 * Remet � z�ro la grille
	 * */
	public void raz() {
		if (avertissementAvantRAZ()) {
			compGrilleVierge.setGrille(new Grille());
			compGrilleVierge.setEnabled(true);
			compADroite.actualiserIndice();
			isSaved=false;
			cheminSaved="";
			fermerTout();
		}
	}

	/** Permet d'afficher un message d'avertissement avant la fermeture ou la RAZ de la grille en cours*/
	private boolean avertissementAvantRAZ() {
		int retour=JOptionPane.YES_OPTION;
		if (!colSolutionAffichee.isEmpty() || compGrilleVierge.getNbIndices()!=0)
			if (!isSaved)
				retour = JOptionPane.showConfirmDialog(this, "La grille actuelle et ses solutions vont �tre effac�es ! En �tes-vous sur ?", "Avertissement avant suppression", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		return (retour==JOptionPane.YES_OPTION);
	}

	/***************************
	 * Cette m�thode met � jour la vue en fonction des modifs du mod�le
	 */
	public void actualiser() {
		compADroite.topChrono(false);
		// mise � jour du panneau stat
		compADroite.actualiserPanneau(modele.getGrille().getLesSolutions().size(),false);
		if (modele.getGrille().getLesSolutions().size()>0)
			afficherSolution(0);
	}
	/** renvoi l'index de la grille pr�sente dans la collection col
	 * Attention ne compare que les String ni les autres champs, ni les r�f�rences
	 * @param g une grille a recherch�e
	 * @param col : la collection
	 * @return l'index dans la collection si la grille est d�j� pr�sente dans la collection, -1 sinon
	 * */
	private int grilleDejaLa(GrilleSudoku g,ArrayList<GrilleSudoku> col){
		int trouve =-1;
		for(GrilleSudoku g2 : col) {
			if ( g.compGrilleVierge.getGrille().equals(g2.compGrilleVierge.getGrille()))
				trouve=col.indexOf(g2);
		}
		return trouve;
	}
	/** Affiche la grille solution s�lectionn�e
	 * @param l'index de la s�lection dans la combo box*/
	public void afficherSolution(int selectedIndex) {
		GrilleSudoku solution = new FrmGrilleSudoku(modele.getGrille().getSolution(selectedIndex), compADroite.combo.getItemAt(selectedIndex).toString(),this);
		int index=grilleDejaLa(solution, colSolutionAffichee);
		if (index==-1) {
			solution.compGrilleVierge.setCouleur(compGrilleVierge.getGrille(), Color.gray);
			if (colSolutionAffichee.size()==0)
				solution.setLocation(0, frmHauteur);
			else {
				Point p=colSolutionAffichee.get(colSolutionAffichee.size()-1).getLocation();
				p.translate(10, 10);
				solution.setLocation(p);
			}
			solution.setVisible(true);
			colSolutionAffichee.add(solution);
		}
		else {
			colSolutionAffichee.get(index).setVisible(true);
			colSolutionAffichee.get(index).setExtendedState(JFrame.NORMAL);
		}

	}
	/** observateur de changement d'indice */
	public void actualiserIndice() {
		compADroite.actualiserIndice();
		setSaved(false);
	}
	/** on autorise l'�dition de la grille vierge*/
	public void editer() {
		compGrilleVierge.setEnabled(true);
		compDessous.setVerrouiller(false);

	}

	public void reorg() {
		Point p = new Point(0,0);
		for(GrilleSudoku i : colSolutionAffichee) {
			i.setLocation(p);
			p.translate(10,10);
			i.setVisible(true);
			i.setExtendedState(JFrame.NORMAL);
		}
	}

	public void toutAfficher() {
		if (compADroite.combo.isEnabled())
			if (colSolutionAffichee.size()<20) // pour �viter d'en avoir trop � afficher - c'est moche mais parfois il faut faire efficace :-)
				for(int i =0;i< compADroite.combo.getItemCount() && i<20;i++) {
					afficherSolution(i);
				}
	}


	public void reduire() {
		for(GrilleSudoku i : colSolutionAffichee) {
			i.setExtendedState(JFrame.ICONIFIED);
		}
	}

	public void fermerTout() {
		while(colSolutionAffichee.size()>0) {
			colSolutionAffichee.get(0).dispose();
			colSolutionAffichee.remove(0);
		}
	}

	public void dispose(GrilleSudoku sudoku) {
		colSolutionAffichee.remove(sudoku);
	}

	public void sauversous() {

		String monFichierChoisi = getCheminFichier(false);
		if (monFichierChoisi != "") {
			File test = new File(monFichierChoisi);
			if (test.exists())
				if (JOptionPane.showConfirmDialog(this,
						"Attention ! Le fichier (" + monFichierChoisi
						+ ") existe d�j� ! \nVoulez-vous l'�craser ?",
						"Confirmation avant �crasement",
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					//le fichier existe et on l'�crase !
					isSaved = true;
					cheminSaved = monFichierChoisi;
					sauverGrille();
				}
				else; // le fichier existe mais on ne veut pas l'�craser
			else {//le fichier n'existe pas
				isSaved = true;
				cheminSaved = monFichierChoisi;
				sauverGrille();
			}
		}
	}

	public void sauver() {
		if (!isSaved)
			sauversous();
		else{
			sauverGrille();
		}
	}

	private void sauverGrille()  {

		GestionFichier sgf = new GestionFichier(GestionFichier.ECRIRE,cheminSaved);
		sgf.ecrire(compGrilleVierge.getGrille().toString());
		sgf.fermer();
		JOptionPane.showMessageDialog(this,"Grille sauvegard�e \n(" + cheminSaved +")");
	}
	/***************************************************************************
	 * Ouvre une bo�te de dialogue pour l'ouverture d'une grille sauvegard�e
	 */
	public void ouvrir() {
		String test;
		if (avertissementAvantRAZ()) {
			test = getCheminFichier(true);
			if (!test.equals("")) {
				cheminSaved = test;
				isSaved = true;
				GestionFichier sgf = new GestionFichier(GestionFichier.LIRE,
						cheminSaved);
				String maGrille = sgf.lire();
				sgf.fermer();
				compGrilleVierge.setEnabled(true);
				compDessous.setVerrouiller(false);
				compGrilleVierge.setGrille(new Grille(maGrille));
				compADroite.actualiserPanneau(0, true);

			}
		}
	}
	public String getCheminFichier(boolean isOpen){
		String monChemin="";
		JFileChooser fc = new JFileChooser();
		//Uncomment one of the following lines to try a different
		//file selection mode.  The first allows just directories
		//to be selected (and, at least in the Java look and feel,
		//shown).  The second allows both files and directories
		//to be selected.  If you leave these lines commented out,
		//then the default mode (FILES_ONLY) will be used.
		//
		//fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		// on affiche la bo�te de dialogue
		FilterSudoku filter = new FilterSudoku();
		fc.setFileFilter(filter);
		fc.setCurrentDirectory(new File(path+SsRep));


		int returnVal;
		if (isOpen)
			returnVal= fc.showOpenDialog(this);
		else{
			fc.setSelectedFile(proposeNom());
			returnVal=fc.showSaveDialog(this);
		}
		if (returnVal == JFileChooser.APPROVE_OPTION) {// l'utilisateur a valid�
			monChemin = fc.getSelectedFile().getAbsolutePath();
			if (!Convert.getExtension(fc.getSelectedFile()).equalsIgnoreCase(Convert.sdk))
				monChemin += "." + Convert.sdk;
		}
		else {//l'utilisateur a annul� l'action
		}
		return monChemin;
	}

	private File proposeNom() {
		int max=-1;
		int temp;
		File proposition = new File(getPathSsRep());
		for (String i : proposition.list(new FilterNameSudoku())){
			temp=Integer.valueOf(i.substring(6,i.indexOf('.')));
			if (max<temp)
				max=temp;
		}
		return new File(Convert.nomFich+String.format("%02d", max+1));
	}

	public void biblioSauver() {
		String nom = path + SsRep +"bibliosudoku"+compGrilleVierge.getNbIndices() + "."+Convert.biblioSDK;
		File test  = new File(nom);
		boolean grilleEstPresente = false;
		if (test.exists()) {//si le fichier existe...
			GestionFichier verif = new GestionFichier(GestionFichier.LIRE,nom);
			//  ...on v�rifie si la grille y figure d�j�
			grilleEstPresente = verif.estPresent(compGrilleVierge.getGrille().toString());
		}

		if (!grilleEstPresente) {// la grille n'est pas pr�sente ou le fichier n'est pas cr��
			GestionFichier sgf = new GestionFichier(GestionFichier.ECRIREAPRES,nom);
			sgf.ecrire(compGrilleVierge.getGrille().toString());// on ecrit la nouvelle grille
			sgf.fermer();
			JOptionPane.showMessageDialog(this,"Grille ajout�e (" + nom +")");
		}
		else
			JOptionPane.showMessageDialog(this,"Grille existe d�j� dans la biblioth�que \n (" + nom +")");
	}

	public void biblioOuvrir() {
		if (avertissementAvantRAZ()) {
			JFrmSelectBiblio maBiblio = new JFrmSelectBiblio(this);
			maBiblio.setVisible(true);
		}
	}


	public void retourBiblio(String maGrille) {
		if (!maGrille.equals("")) {
			compGrilleVierge.setEnabled(true);
			compGrilleVierge.setGrille(new Grille(maGrille));
			compADroite.actualiserPanneau(0,true);
			cheminSaved ="";
			isSaved=false;
		}
	}

	public void retourSelectBiblio(String bibliochoisie) {
		if(!bibliochoisie.equals("")) {
			JFrmOpenBiblio maBiblio = new JFrmOpenBiblio(getPathSsRep() + bibliochoisie,this);
			maBiblio.setVisible(true);
		}
	}

	public String getPathSsRep() {
		return path+SsRep;
	}

	public void canoniser() {
		if (avertissementAvantRAZ()) {
			cheminSaved = "";
			isSaved = false;
			compGrilleVierge.setEnabled(true);
			compGrilleVierge.setGrille(Convert.changeGrille(compGrilleVierge.getGrille()));
			compADroite.actualiserPanneau(0, true);
		}

	}

	public void tourner() {
		if (avertissementAvantRAZ()) {
			cheminSaved = "";
			isSaved = false;
			compGrilleVierge.setEnabled(true);
			compGrilleVierge.setGrille(Convert.tourneGrille(compGrilleVierge.getGrille()));
			compADroite.actualiserPanneau(0, true);
		}
	}

	public void symetrieH() {
		if (avertissementAvantRAZ()) {
			cheminSaved = "";
			isSaved = false;
			compGrilleVierge.setEnabled(true);
			compGrilleVierge.setGrille(Convert.symetrieGrille(Convert.tourneGrille(Convert.tourneGrille(compGrilleVierge.getGrille()))));
			compADroite.actualiserPanneau(0, true);
		}
	}

	public void symetrieV() {
		if (avertissementAvantRAZ()) {
			cheminSaved = "";
			isSaved = false;
			compGrilleVierge.setEnabled(true);
			compGrilleVierge.setGrille(Convert.symetrieGrille(compGrilleVierge.getGrille()));
			compADroite.actualiserPanneau(0, true);
		}
	}

}