package org.sylves.affichage;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import org.sylves.syldoku.suNoyau.Convert;


public class SudokuFilter extends FileFilter {

	public SudokuFilter() {
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
            if (extension.equals(Convert.txt) ||
                extension.equals(Convert.sdk) )
                    accepted= true;

        }

        return accepted;
	}

	@Override
	public String getDescription() {
		return "Les grilles de Sudoku";
	}

}
