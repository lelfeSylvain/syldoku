package org.sylves.syldoku.run;

import org.sylves.syldoku.suNoyau.GrilleResolue;
import org.sylves.syldoku.suNoyau.ResolutionGrille;

public class RunConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GrilleResolue maGrille = new GrilleResolue("000000010400000000020000000000050407008000300001090000300400200050100000000806000");
		ResolutionGrille.resoudre(maGrille.getGrille());

		System.out.println(maGrille.getGrille().toString());
	}

}
