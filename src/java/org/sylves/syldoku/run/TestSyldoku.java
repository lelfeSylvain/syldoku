package org.sylves.syldoku.run;

//import java.awt.Frame;

import org.sylves.syldoku.controleur.ControleurSyldoku;
import org.sylves.syldoku.controleur.InterfaceControleurSyldoku;
import org.sylves.syldoku.suNoyau.InterfaceModeleResolution;
import org.sylves.syldoku.suNoyau.ModeleResolution;

public class TestSyldoku {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Frame myFrame = new Frame();
		//SplashWindow mySplash = new SplashWindow("mypicture.jpg",myFrame,10000);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	InterfaceModeleResolution modele = new ModeleResolution();
        		@SuppressWarnings("unused")
				InterfaceControleurSyldoku controleur = new ControleurSyldoku(modele);
            }
        });



	}

}
