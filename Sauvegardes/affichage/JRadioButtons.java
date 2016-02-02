package org.sylves.affichage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JRadioButtons extends JFrame {


	   JLabel jLabel1 = new JLabel("Choisissez une couleur");

	   JPanel jPanel1 = new JPanel();
	   JButton btnQuit = new JButton("Quitter");

	   JRadioButton jRadioButton1 = new JRadioButton("Bleu");
	   JRadioButton jRadioButton2 = new JRadioButton("Vert");
	   JRadioButton jRadioButton3 = new JRadioButton("Jaune");
	   JRadioButton jRadioButton4 = new JRadioButton("Orange");

	   ButtonGroup buttonGroup1 = new ButtonGroup();

	   public static void main(String[] args)
	   {
	    JRadioButtons radioButtons1 = new JRadioButtons();
	    radioButtons1.setVisible(true);
	   }

	   public JRadioButtons()
	   {
	    this.setSize(300,150);
	    jLabel1.setBounds(new Rectangle(3, 4, 290, 20));
	    this.getContentPane().setLayout(null);
	    jRadioButton1.setSelected(true);
	    jRadioButton1.setBounds(new Rectangle(5, 20, 100, 20));
	    jRadioButton1.addItemListener(new java.awt.event.ItemListener()
	     {
	      public void itemStateChanged(ItemEvent e)
	       {
	        radioButtons_itemStateChanged(e);
	       }
	     });
	     jRadioButton2.setBounds(new Rectangle(5, 40, 100, 20));
	     jRadioButton2.addItemListener(new java.awt.event.ItemListener()
	     {
	      public void itemStateChanged(ItemEvent e)
	       {
	        radioButtons_itemStateChanged(e);
	       }
	     });
	     jRadioButton3.setBounds(new Rectangle(5, 60, 100, 20));
	     jRadioButton3.addItemListener(new java.awt.event.ItemListener()
	      {
	       public void itemStateChanged(ItemEvent e)
	       {
	        radioButtons_itemStateChanged(e);
	       }
	      });
	     jRadioButton4.setBounds(new Rectangle(5, 80, 100, 20));
	     jRadioButton4.addItemListener(new java.awt.event.ItemListener()
	      {
	       public void itemStateChanged(ItemEvent e)
	       {
	        radioButtons_itemStateChanged(e);
	       }
	      });
	     jPanel1.setBackground(Color.blue);
	     jPanel1.setBounds(new Rectangle(105, 20, 150, 100));
	     btnQuit.setBounds(new Rectangle(5, 100, 100, 20));
	     btnQuit.addActionListener(new java.awt.event.ActionListener()
	      {
	       public void actionPerformed(ActionEvent e)
	       {
	        setVisible(false); dispose();
	       }
	      });
	     this.getContentPane().add(jLabel1, null);
	     this.getContentPane().add(jRadioButton1, null);
	     this.getContentPane().add(jRadioButton2, null);
	     this.getContentPane().add(jRadioButton3, null);
	     this.getContentPane().add(jRadioButton4, null);
	     this.getContentPane().add(jPanel1, null);
	     this.getContentPane().add(btnQuit, null);

	     this.buttonGroup1.add(this.jRadioButton1);
	     this.buttonGroup1.add(this.jRadioButton2);
	     this.buttonGroup1.add(this.jRadioButton3);
	     this.buttonGroup1.add(this.jRadioButton4);

	     this.addWindowListener(new WindowAdapter()
	      {
	       public void windowClosing(WindowEvent evt)
	       {
	        dispose();
	       }
	       public void windowClosed(WindowEvent evt)
	       {
	        System.exit(0);
	       }
	      });
	   }

	   void radioButtons_itemStateChanged(ItemEvent e)
	   {
	    Object source = e.getSource();
	    if (source == jRadioButton1) this.jPanel1.setBackground(Color.blue);
	    if (source == jRadioButton2) this.jPanel1.setBackground(Color.green);
	    if (source == jRadioButton3) this.jPanel1.setBackground(Color.yellow);
	    if (source == jRadioButton4) this.jPanel1.setBackground(new Color(255, 153, 51));
	   }

}
