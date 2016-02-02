package org.sylves.syldoku.utilitaires;

import java.io.File;
import java.io.FilenameFilter;


public class FilterNameSudoku implements FilenameFilter {

	public FilterNameSudoku() {
		super();
	}

	public boolean accept(File f,String name) {
		boolean accepted = false;
            if (name.matches("sudoku[0-9]+?."+Convert.sdk) )
                    accepted= true;
        return accepted;
	}

}
