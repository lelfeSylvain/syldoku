package org.sylves.syldoku.run;

/** @author Philippe Duverger
 * http://www.labo-sun.com/pub/user/view/display.htm?id=129*/

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
@SuppressWarnings("serial") class SplashWindow extends JWindow {


    public SplashWindow(String filename, Frame f, int waitTime) {
        super(f);
        //cree un label avec notre image
        JLabel l = new JLabel(new ImageIcon(filename));
        //ajoute le label au panel
        getContentPane().add(l, BorderLayout.CENTER);
        pack();

        //centre le splash screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = l.getPreferredSize();
        setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));

        //rend le splash screen invisible lorsque l'on clique dessus
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                dispose();
            }
        });

        //afin d'acceder à la valeur WaitTime
        final int pause = waitTime;

        //thread pour fermer le splash screen
        final Runnable closerRunner = new Runnable() {
            public void run() {
                setVisible(false);
                dispose();
            }
        };


        Runnable waitRunner = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(pause);
                    //lance le thread qui ferme le splash screen
                    SwingUtilities.invokeAndWait(closerRunner);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        //affiche le splash screen
        setVisible(true);

        //lance le thread qui ferme le splash screen apres un certain temps
        Thread splashThread = new Thread(waitRunner, "SplashThread");
        splashThread.start();
    }
}