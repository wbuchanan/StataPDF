package org.paces.Stata.PDF;

import java.io.File;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Insheet {
	private File pdf;

	public Insheet(String[] args) {
		this.pdf = new File(args[0]);

	}

}
