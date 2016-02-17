package org.paces.Stata.PDF.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class RowIDs {

	private List<String> rowids = new ArrayList<>();
	private Integer colPosition = 0;
	private Integer rowPosition;

	public RowIDs(Integer rowidx, String id) {
		this.rowPosition = rowidx;
		rowids.add(id);
	}


}
