package org.sylves.syldoku.affichage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.sylves.syldoku.utilitaires.FilterNameBiblioSudoku;

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events
import java.io.File;
import java.util.Vector;


@SuppressWarnings("serial")
public class JFrmSelectBiblio extends JFrame
                             implements ActionListener {

    private JButton btnOk,btnCancel;
    private JPanel JPbtn;
	private JScrollPane areaScrollPane;
	private JList myList;
	private Vector <String> data;
	private FrmGrilleSudokuAvecMenu monAppli;

	public JFrmSelectBiblio(FrmGrilleSudokuAvecMenu monAppli) {
		super("Selectionner une bibliothèque");
		this.monAppli = monAppli;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(153, 250));
        JPanel myPanelWindows = new JPanel();
        myPanelWindows.setLayout(new BorderLayout());


		//construction de la liste
		FilterNameBiblioSudoku monFiltre= new FilterNameBiblioSudoku();
		File rep = new File(monAppli.getPathSsRep());
		data = new Vector<String>();
		for(String i : rep.list(monFiltre)) {
			data.add(i);
		}
		myList = new JList(data);
		myList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				btnOk.setEnabled(true);
			}

		});
		//... avec un barre de défilement
		areaScrollPane = new JScrollPane();
		//areaScrollPane.setPreferredSize(new Dimension(500, 250));
		areaScrollPane.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("Les bibliothèques disponibles"),
								BorderFactory.createEmptyBorder(2,2,2,2)),
								areaScrollPane.getBorder()));
		areaScrollPane.getViewport().add(myList);
		// on crée le panneau de bouton

		JPbtn=new JPanel(new FlowLayout());
		btnCancel=new JButton("Annuler");
		btnCancel.addActionListener(this);
		JPbtn.add(btnCancel);
		btnOk=new JButton("OK");
		btnOk.setEnabled(false);
		btnOk.addActionListener(this);
		JPbtn.add(btnOk);
		//Put everything together.
		JPanel leftPane = new JPanel(new BorderLayout());
		/*leftPane.add(textControlsPane,
                     BorderLayout.PAGE_START);*/
		leftPane.add(areaScrollPane,BorderLayout.PAGE_START);
		leftPane.add(JPbtn,BorderLayout.PAGE_END);

		myPanelWindows.add(leftPane, BorderLayout.LINE_START);
		//myPanelWindows.add(rightPane, BorderLayout.LINE_END);*/
        //Add content to the window.
        add(myPanelWindows);

        //Display the window.
		pack();
        setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		boolean retour =false;
		boolean canceled = false;
		if (e.getSource()==btnCancel) {
			retour=true;canceled = true;
		} else if (e.getSource()==btnOk) {
			retour=true;
		} else {//autres actions non gérées
		}
		if (retour)
			if (!canceled)
				if (!myList.isSelectionEmpty()){
					monAppli.retourSelectBiblio((String) myList.getSelectedValue());
					dispose();
				}
				else ;//il ne se passe rien : il faut au moins une sélection
			else{// si clic sur annuler
				monAppli.retourSelectBiblio("");
				dispose();
			}
	}
}

