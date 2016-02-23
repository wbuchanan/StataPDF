package org.paces.Stata.PDF;


import com.stata.sfi.SFIToolkit;
import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.*;
import technology.tabula.detectors.DetectionAlgorithm;
import technology.tabula.detectors.NurminenDetectionAlgorithm;
import technology.tabula.extractors.BasicExtractionAlgorithm;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class InTable {

	private List<Table> data;
	private Integer maxrows;
	private Integer maxcols;

	public InTable(String[] args) {
		this.data = getData(args);

	}

	public List<Table> getTables() {
		return this.data;
	}

	public void loadData() {

	}

	private List<Table> getData(String[] args) {
		List<Table> tables = new ArrayList<>();
		File pdfFileObject = new File(args[0]);
		Rectangle area = null;
		if (!args[1].isEmpty()) {
			List<Float> rect = getFloats(args[1]);
			if (rect.size() == 4) {
				area = new Rectangle(rect.get(0), rect.get(1),
						rect.get(3) - rect.get(1), rect.get(2) - rect.get(0));
			}
		}

		List<Float> vert = null;
		if (!args[2].isEmpty()) vert = getFloats(args[2]);

		List<Integer> pages;
		if (!args[3].isEmpty()) pages = getInts(args[3]);
		else pages = getInts("all");

		String password = args[4];

		// Used to set if the
		String guess = args[5];

		String extractor = args[6];

		Boolean lineReturns = !args[7].isEmpty();

		String dbug = args[8];

		try {

			PDDocument pdfDoc = PDDocument.load(pdfFileObject);

			ObjectExtractor oe = !password.isEmpty() ?
					new ObjectExtractor(pdfDoc, password) :
					new ObjectExtractor(pdfDoc);

			BasicExtractionAlgorithm basicExtractor = new BasicExtractionAlgorithm();
			SpreadsheetExtractionAlgorithm spreadsheetExtractor = new SpreadsheetExtractionAlgorithm();

			PageIterator pageIterator = pages == null ? oe.extract() : oe.extract(pages);
			Page page;
			while (pageIterator.hasNext()) {
				page = pageIterator.next();

				if (area != null) page = page.getArea(area);


				if (extractor == null)
					extractor = spreadsheetExtractor.isTabular(page) ? "spreadsheet" : "basic";

				switch (extractor) {
					case "basic":
						if (!guess.isEmpty()) {
							DetectionAlgorithm detector = new NurminenDetectionAlgorithm();
							List<Rectangle> guesses = detector.detect(page);
							for (Rectangle guessRect : guesses) {
								Page guessed = page.getArea(guessRect);
								tables.addAll(basicExtractor.extract(guessed));
							}
						} else {
							tables.addAll(vert == null ? basicExtractor.extract(page) : basicExtractor.extract(page, vert));
						}
						break;
					case "spreadsheet":
						tables.addAll(spreadsheetExtractor.extract(page));
						break;
					default:
						break;
				}
			}
		} catch (Exception e) {
			SFIToolkit.errorln(SFIToolkit.stackTraceToString(e));
		}
		return tables;
	}


	private List<Float> getFloats(String arg) {
		List<Float> f = new ArrayList<>();
		Scanner scan = new Scanner(arg).useDelimiter(",");
		while (scan.hasNextFloat()) {
			f.add(scan.nextFloat());
		}
		return f;
	}

	private List<Integer> getInts(String arg) {
		if (arg == "all") return null;
		List<Integer> f = new ArrayList<>();
		List<String> s = Arrays.asList(arg.split(","));
		for(String p : s) {
			if (p.matches("\\d++-\\d++")) {
				Integer start = Integer.valueOf(Arrays.asList(p.split("-")).get(0));
				Integer end = Integer.valueOf(Arrays.asList(p.split("-")).get(1));
				for(Integer i = start; i <= end; i++) f.add(i);
			} else {
				f.add(Integer.valueOf(p));
			}
		}
		return f;
	}


}
