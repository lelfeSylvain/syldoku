package org.sylves.syldoku.utilitaires;

import java.io.File;
import java.io.FilenameFilter;


public class FilterNameBiblioSudoku implements FilenameFilter {

	public FilterNameBiblioSudoku() {
		super();
	}


	public boolean accept(File dir, String name) {
		boolean accepted = false;
		/*if (dir.isDirectory()) {
            accepted=true;
        }*/

        String extension = Convert.getExtension(name);
        if (extension != null) {
            if (extension.equals(Convert.biblioSDK))
                    accepted= true;

        }

        return accepted;
	}




}
