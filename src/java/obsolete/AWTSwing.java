package obsolete;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class AWTSwing {
        private JComboBox combo; // Composant Swing
        private Button bouton; // Composant AWT
        
        public AWTSwing () {
                JFrame frame = new JFrame ("Peut-on melanger Awt & Swing ?");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.setBounds (300, 300, 200, 300);
                
                String [] a = {"oui", "non", "peut etre"};
                combo = new JComboBox (a);
                frame.getContentPane ().add (combo, BorderLayout.NORTH);
                bouton = new Button ("Coucou");
                
                frame.getContentPane ().add (bouton, BorderLayout.CENTER);
                
                frame.setVisible (true);
        } 
        
        public static void main (String argv []) {
                new AWTSwing ();
        } 
}