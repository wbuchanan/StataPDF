package org.paces.Stata.PDF.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Header {

	private List<String> varnames = new ArrayList<>();
	private Integer colPosition;
	private Integer rowPosition = 0;

	public Header(Integer colidx, String header) {
		this.colPosition = colidx;
		this.varnames.add(validHeader(header));
	}

	private static String validHeader(String headerValue) {
		Pattern validName = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]+");
		Pattern numflip = Pattern.compile("^([0-9])+([a-zA-Z])+");
		String cleanname = headerValue;
		if (!validName.matcher(headerValue).find()) {
			cleanname = headerValue.replace("\\W", "");
			if (numflip.matcher(cleanname).find()) {
				cleanname = numflip.matcher(cleanname).group(2) +
						numflip.matcher(cleanname).group(1);
			}
		}
		return cleanname;
	}


}
