package obsolete;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FrmSudoku {
	private	JFrame frmSudoku;
	private	JTextArea txtCase;
	private	JPanel pnlSudoku;
	//private	JScrollPane sclSudoku;

	public FrmSudoku(int hauteur, int largeur,int x, int y, String titre) {
		//int i=0,j=0; // indice
		//   fen�tre
		frmSudoku = new JFrame(titre);
			   //   taille par d�faut
		frmSudoku.setSize(150, 150);
			   //	 zone de texte
		txtCase = new JTextArea();
			//	 les ascenceurs sont le container de txtCase
		//sclSudoku = new JScrollPane(txtCase);

			//	 super container
		pnlSudoku = new JPanel();
		   //    permet d'ajouter les enfants les uns � la suite des autres
		pnlSudoku.setLayout(new FlowLayout());
		       //    ajoute les case au panel
		//for (i=0;i<9;i++)
			//for(j=0;j<9;j++){
				//txtCase[j][i].setSize(15,15);//dimension de la case
				//txtCase[j][i].setLocation(15*j,15*i);//localisation de la case
		txtCase.setSize(15,15);//dimension de la case
		txtCase.setLocation(15,15);//localisation de la case
				pnlSudoku.add(txtCase);
			//}
		       //    ajoute mon panel au centre de ma fen�tre
		frmSudoku.getContentPane().add(pnlSudoku, BorderLayout.CENTER);
		       //    sort si l'on clique sur fermeture
		frmSudoku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		       //    interdit d'�crire dedans car ne donne pas le focus
		//txtSudoku.setFocusable(false);
		       //     r�organise la fen�tre en dimension
		frmSudoku.pack();
		       //    ajuste la position dans l'�cran
		frmSudoku.setLocation(x,y);
		       //        affiche la fen�tre
		frmSudoku.setVisible(true);
		   }
}
