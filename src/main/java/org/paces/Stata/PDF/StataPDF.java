package org.paces.Stata.PDF;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class StataPDF {



	private static final String[] emptyOption = { "" };
	private static final List<String> eo = Arrays.asList(emptyOption);

	public static int intable(String[] args) {
		InTable insheet = new InTable(args);
		ToStata loader = new ToStata(insheet.getTables());
		loader.loadData();
		return 0;
	}

	private static List<Float> parseFloats(List<String> arg) {
		List<Float> fvals = new ArrayList<>();
		if (!eo.equals(arg)) {
			for(String i : arg) {
				if (i.matches("^-?\\d+\\.?\\d*$")) fvals.add(Float.parseFloat(i));
			}
		}
		return fvals;
	}

	private static List<Integer> parsePages(List<String> arg) {
		List<Integer> pages = new ArrayList<>();
		for(String i : arg) {
			if (i.contains("-")) {
				String[] stend = i.split("-");
				for(Integer x = Integer.parseInt(stend[0]); x < Integer.parseInt(stend[1]); x++) {
					pages.add(x);
				}
			} else {
				pages.add(Integer.parseInt(i));
			}
		}
		return pages;
	}


}
