package org.sylves.affichage;
//biblioth�ques pour le GUI

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
    private boolean isNamed;
	private String nomGrille;


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
        //permet d'enregistrer les liens avec les fen�tres solutions
        colSolutionAffichee= new ArrayList<GrilleSudoku>();

        dimensionnerLaFenetre(JFrame.EXIT_ON_CLOSE,true,frmLargeur,frmHauteur);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l�application de se fermer lors du clic sur la croix
        this.setVisible(true);//la fen�tre appara�t

    }

    protected void dimensionnerLaFenetre() {
        //this.setSize(frmLargeur,frmHauteur); //On donne une taille � notre fen�tre
        //this.setLocation(0,0); //On place la fen�tre sur l��cran en haut � gauche
    }

    protected void colorerGrille(String grille) {
        container.setCouleur(grille, Color.BLACK);
    }
    protected void ajouterTitreContainer() {
        TitledBorder monborder = (TitledBorder) container.getBorder();
        monborder.setTitle("Grille probl�me");
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
        //JOptionPane.showMessageDialog(null,"En �tes-vous sur ?");
        System.exit(0);
    }
    /*********************
     * Appel au controleur qui va contacter le mod�le pour la r�solution de la grille de Sudoku
     * actuellement affich� � l'�cran
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
     * Remet � z�ro la grille
     * */
    public void raz() {
        container.setGrille("000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        container.setEnabled(true);
        compADroite.actualiserIndice();

    }


    /***************************
     * Cette m�thode met � jour la vue en fonction des modifs du mod�le
     */
    public void actualiser() {
        // mise � jour du panneau stat
        compADroite.actualiserPanneau(modele.getGrille().getLesSolutions().size(),false);


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
            if ( g.container.getGrille().equals(g2.container.getGrille()))
                trouve=col.indexOf(g2);
        }
        return trouve;
    }
    /** Affiche la grille solution s�lectionn�e
     * @param l'index de la s�lection dans la combo box*/
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
     * Ouvre une bo�te de dialogue pour l'ouverture d'une grille sauvegard�e
     */
    public void ouvrir() {
        // proc�dure muette en attendant de g�rer les E/S
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

        // on affiche la bo�te de dialogue
        SudokuFilter filter = new SudokuFilter();
        fc.setFileFilter(filter);
        int returnVal;
        if (isOpen)
            returnVal= fc.showOpenDialog(this);
        else
            returnVal=fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {// l'utilisateur a valid�
            monChemin = fc.getSelectedFile().getAbsolutePath();
        }
        else {//l'utilisateur a annul� l'action

        }

        //File toto = new File();
        return monChemin;
    }



}