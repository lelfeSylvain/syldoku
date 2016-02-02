package org.sylves.syldoku.suNoyau;

import java.util.ArrayList;

public class TabGrille {
	private ArrayList <Grille> Tab;
	
	public TabGrille () {
		Tab =new ArrayList <Grille> ();
		
	}
	
	public TabGrille(Grille grille) {
		this();
		Tab.add(grille);
	}
	
	public void addGrille(InterfaceGrille g) {
		boolean trouve=false;
		for (InterfaceGrille valeur:Tab) {
			trouve = trouve || (valeur.toString().equals(g.toString()));				
		}
		if (!trouve) 
			Tab.add(new Grille(g));
	}
	
	public InterfaceGrille getGrille() {
		if (Tab.size()==0 )
			return null;
		else 
			return Tab.get(0);
	}
		
	public InterfaceGrille getGrille(int i) {
		return Tab.get(i);
	}
	
	public int size() {
		return Tab.size();
	}

	public ArrayList <Grille> getTab() {
		
		return Tab;
	}
}
