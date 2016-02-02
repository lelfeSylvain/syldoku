package org.sylves.syldoku.utilitaires;

import java.io.File;
import javax.swing.filechooser.FileFilter;


public class FilterBiblioSudoku extends FileFilter {

	public FilterBiblioSudoku() {
		super();
	}

	@Override
	public boolean accept(File f) {
		boolean accepted = false;
		if (f.isDirectory()) {
            accepted=true;
        }

        String extension = Convert.getExtension(f);
        if (extension != null) {
            if (extension.equals(Convert.biblioSDK))
                    accepted= true;

        }

        return accepted;
	}

	@Override
	public String getDescription() {
		return "Les bibliothèques de grilles de Sudoku";
	}

}
