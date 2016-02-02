package org.sylves.syldoku.affichage;

import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class JSliProfondeur extends JSlider {
	private Hashtable<Integer, JLabel> numbers;
	private String [] labels ={"1","10","500","1000","10000"};
	public JSliProfondeur() {
		this(JSlider.HORIZONTAL, 1,4, 1);
	}

	public JSliProfondeur(int arg0) {
		this(arg0, 1,4, 1);

	}

	public JSliProfondeur(BoundedRangeModel arg0) {
		this(JSlider.HORIZONTAL, arg0.getMinimum(),arg0.getMaximum(), arg0.getValue());

	}

	public JSliProfondeur(int arg0, int arg1) {
		this(JSlider.HORIZONTAL, arg0,arg1, arg0);

	}

	public JSliProfondeur(int arg0, int arg1, int arg2) {
		this(JSlider.HORIZONTAL,arg0, arg1, arg2);

	}

	public JSliProfondeur(int arg0, int arg1, int arg2, int arg3) {
		super(arg0, arg1, arg2, arg3);

		construire();
	}

	/**
	 *
	 * @return la valeur sélectionnée
	 */
	public int getProfondeur(){

		return Integer.valueOf(numbers.get(getValue()).getText());
	}
	/** procédure appelé par tous les constructeurs pour construire le JSlider */
	private void construire(){

		numbers =  new Hashtable<Integer, JLabel>();
		for (int i=1;i<=getMaximum();i++){
			numbers.put( new Integer(i),new JLabel(labels[i]));
		}
		setMajorTickSpacing (2);
		setPaintLabels (true);
		setSnapToTicks(true);
		setMajorTickSpacing(1);
		setToolTipText("Borne la recherche dans le cas d'une grille ambiguë");
		setLabelTable(  numbers);
		setBorder(new TitledBorder(BorderFactory.createLoweredBevelBorder(), "Nombre de grille maximum "));
	}

}
