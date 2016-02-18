package org.paces.Stata.PDF;

import com.stata.sfi.Data;
import technology.tabula.RectangularTextContainer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Header {

	/**
	 * Member containing valid Stata variable names
	 */
	private List<String> varnames;

	private List<String> rawVarnames;

	/**
	 * Class Constructor.  This class should only be passed the first row of
	 * the table object.
	 * @param row The first row of the table object
	 */
	public Header(List<RectangularTextContainer> row) {
		validNames(row);
	}

	/**
	 * Method used to parse table headers into valid variable names
	 * @param row The first row of the table object
	 * @return A list of valid Stata variable names
	 */
	private void validNames(List<RectangularTextContainer> row) {
		this.varnames.addAll(row.stream().map(tc -> Data.makeVarName(tc.getText(), false)).collect(Collectors.toList()));
		this.rawVarnames.addAll(row.stream().map(tc -> tc.getText()).collect(Collectors.toList()));
	}

	/**
	 * Returns the list of valid Stata variable names
	 * @return A List of valid Stata Variable Names
	 */
	public List<String> getVarNames() {
		return this.varnames;
	}

	public List<String> getRawVarnames() {
		return this.rawVarnames;
	}

}
