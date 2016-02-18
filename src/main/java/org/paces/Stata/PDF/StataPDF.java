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
		String filenm = args[0];
		// Area or guess
		if (!args[1].isEmpty()) {
			List<Float> rect = parseFloats(Arrays.asList(args[1].split(",")));
		} else {
			List<Float> rect = null;
		}

		if (!args[2].isEmpty()) {
			List<Float> columns = parseFloats(Arrays.asList(args[2].split(",")));
		} else {
			List<Float> columns = null;
		}
		if (!args[3].isEmpty()) {
			List<Integer> pages = parsePages(Arrays.asList(args[3].split(",")));
		} else {
			List<Integer> pages = null;
		}
		String extract = args[4];
		String password = args[5];
		String useLines = args[6];



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
