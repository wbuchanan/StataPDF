package org.paces.Stata.PDF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class DataTypes {

	List<Boolean> numericTypes;

	public DataTypes(String types) {
		this.numericTypes = parseTypes(types);
	}

	private List<Boolean> parseTypes(String typeString) {
		List<Boolean> types = new ArrayList<>();
		for(String i : Arrays.asList(typeString.split(","))) {
			switch(i) {
				case "dbl":
					types.add(true);
					break;
				default:
					types.add(false);
					break;
			}
		}
		return types;
	}

	public List<Boolean> getDataTypes() {
		return this.numericTypes;
	}

}
