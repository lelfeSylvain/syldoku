package org.sylves.affichage;
//bibliothèques pour le GUI

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import org.sylves.syldoku.controleur.InterfaceControleurSyldoku;
import org.sylves.syldoku.suNoyau.Convert;
import org.sylves.syldoku.suNoyau.InterfaceModeleResolution;
/**
 *	Fenêtre principale de l'application Syldoku
 */
public class FrmGrilleSudokuAvecMenu extends GrilleSudoku implements InterfaceObservateurResolution{


    private static final long serialVersionUID = 1L;
    //paramètres de la fenêtre
    protected int frmLargeur = 715;
    protected int frmHauteur = 400;
    /** Sujet de l'observateur : le modèle du MVC */
    @SuppressWarnings("unused")
    private InterfaceModeleResolution modele;
    /** Controleur du MVC */
    private InterfaceControleurSyldoku controleur;
    /** permet d'enregistrer les liens avec les fenêtres solutions */
    private ArrayList<GrilleSudoku>  colSolutionAffichee;
    private boolean isNamed;
	private String nomGrille;


    /*****************************
     * Constructeur : a besoin des références au controleur et au modèle
     *
     */

    public FrmGrilleSudokuAvecMenu(InterfaceModeleResolution modele, InterfaceControleurSyldoku controleur){
        super();
        //enregistre les liens avec le modèle et le controleur
        this.modele=modele;
        modele.enregistrerObservateur(this);
        this.controleur=controleur;
        //permet d'enregistrer les liens avec les fenêtres solutions
        colSolutionAffichee= new ArrayList<GrilleSudoku>();

        dimensionnerLaFenetre(JFrame.EXIT_ON_CLOSE,true,frmLargeur,frmHauteur);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l’application de se fermer lors du clic sur la croix
        this.setVisible(true);//la fenêtre apparaît

    }

    protected void dimensionnerLaFenetre() {
        //this.setSize(frmLargeur,frmHauteur); //On donne une taille à notre fenêtre
        //this.setLocation(0,0); //On place la fenêtre sur l’écran en haut à gauche
    }

    protected void colorerGrille(String grille) {
        container.setCouleur(grille, Color.BLACK);
    }
    protected void ajouterTitreContainer() {
        TitledBorder monborder = (TitledBorder) container.getBorder();
        monborder.setTitle("Grille problème");
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
     * Ajoute le menu principal dans la fenêtre principale
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
        //JOptionPane.showMessageDialog(null,"En êtes-vous sur ?");
        System.exit(0);
    }
    /*********************
     * Appel au controleur qui va contacter le modèle pour la résolution de la grille de Sudoku
     * actuellement affiché à l'écran
     */
    public void calculer() {
        container.setEnabled(false);
        controleur.setGrille(Convert.sans2avec(container.getGrille()));
        //JOptionPane.showMessageDialog(null,container.getGrille());
        compADroite.topChrono(true);
        controleur.resoudreGrille(compADroite.getProfondeur());
        compADroite.topChrono(false);
    }


    /**
     * Remet à zéro la grille
     * */
    public void raz() {
        container.setGrille("000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        container.setEnabled(true);
        compADroite.actualiserIndice();

    }


    /***************************
     * Cette méthode met à jour la vue en fonction des modifs du modèle
     */
    public void actualiser() {
        // mise à jour du panneau stat
        compADroite.actualiserPanneau(modele.getGrille().getLesSolutions().size(),false);


    }
    /** renvoi l'index de la grille présente dans la collection col
     * Attention ne compare que les String ni les autres champs, ni les références
     * @param g une grille a recherchée
     * @param col : la collection
     * @return l'index dans la collection si la grille est déjà présente dans la collection, -1 sinon
     * */
    private int grilleDejaLa(GrilleSudoku g,ArrayList<GrilleSudoku> col){
        int trouve =-1;
        for(GrilleSudoku g2 : col) {
            if ( g.container.getGrille().equals(g2.container.getGrille()))
                trouve=col.indexOf(g2);
        }
        return trouve;
    }
    /** Affiche la grille solution sélectionnée
     * @param l'index de la sélection dans la combo box*/
    public void afficherSolution(int selectedIndex) {
        FrmGrilleSudoku solution = new FrmGrilleSudoku(Convert.avec2sans(modele.getGrille().getSolution(selectedIndex)), compADroite.combo.getItemAt(selectedIndex).toString(),this);
        int index=grilleDejaLa(solution, colSolutionAffichee);
        if (index==-1) {
            solution.container.setCouleur(container.getGrille(), Color.gray);
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
    }

    public void editer() {
        container.setEnabled(true);

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
            if (colSolutionAffichee.size()<20) // pour éviter d'en avoir trop à afficher - c'est moche mais parfois il faut faire efficace :-)
                for(int i =0;i< compADroite.combo.getItemCount() && i<20;i++) {
                    afficherSolution(i);
            }
    }


    public void reduire() {
        for(GrilleSudoku i : colSolutionAffichee) {
            i.setExtendedState(JFrame.ICONIFIED);
        }
    }

    public void fermer() {
        while(colSolutionAffichee.size()>0) {
            colSolutionAffichee.get(0).dispose();
            colSolutionAffichee.remove(0);
        }
    }

    public void dispose(FrmGrilleSudoku sudoku) {
        colSolutionAffichee.remove(sudoku);

    }

    public void biblio() {
        // TODO Auto-generated method stub

    }

    public void sauversous() {
        String monFichierChoisi =getCheminFichier(false);
        if (monFichierChoisi!="") {
        	isNamed=true;
        	nomGrille=monFichierChoisi;
        	sauverGrille();
        }
    }

    public void sauver() {
        if (!isNamed)
            sauversous();
        else{
        	sauverGrille();
        }

    }

    private void sauverGrille()  {
    	GestionFichier sgf = new GestionFichier(GestionFichier.ECRIRE,nomGrille);
		sgf.ecrire(container.getGrille());
		sgf.fermer();

	}
    /***************************
     * Ouvre une boîte de dialogue pour l'ouverture d'une grille sauvegardée
     */
    public void ouvrir() {
        // procédure muette en attendant de gérer les E/S
    	nomGrille =getCheminFichier(true);
    	isNamed=true;
        GestionFichier sgf = new GestionFichier(GestionFichier.LIRE,nomGrille);
        String maGrille = sgf.lire();
        sgf.fermer();
        //String maGrille=("000000209300800000000000400730060000000029000500000000069400000000500010000008000");
        container.setEnabled(true);
        container.setGrille(maGrille);
        compADroite.actualiserPanneau(0,true);
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

        // on affiche la boîte de dialogue
        SudokuFilter filter = new SudokuFilter();
        fc.setFileFilter(filter);
        int returnVal;
        if (isOpen)
            returnVal= fc.showOpenDialog(this);
        else
            returnVal=fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {// l'utilisateur a validé
            monChemin = fc.getSelectedFile().getAbsolutePath();
        }
        else {//l'utilisateur a annulé l'action

        }

        //File toto = new File();
        return monChemin;
    }



}