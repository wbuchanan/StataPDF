package org.paces.Stata.PDF;

import com.stata.sfi.Data;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class ToStata {

	private List<List<String>> data;
	private Header varnames;
	private DataTypes type;
	private List<Integer> varindices;

	public ToStata(Table table, DataTypes dattype) {
		this.varnames = getHeader(table);
		this.data = parseRows(table, this.varnames.getRawVarnames());
		Data.setObsTotal(this.data.size());
		this.type = dattype;
		this.varindices = makeStataVars();
	}

	private Header getHeader(Table tab) {
		return new Header(tab.getRows().get(0));
	}

	private List<List<String>> parseRows(Table table, List<String> variableNames) {
		List<List<String>> rowData = new ArrayList<>();
		for (List<RectangularTextContainer> row : table.getRows()) {
			if (!row.equals(variableNames)) {
				List<String> cells = new ArrayList<>(row.size());
				for (RectangularTextContainer tc: row) {
					String cellString = tc.getText().trim();
					if (cellString.matches("[^\\p{Alpha}]+")) {
						cells.add(cellString.replaceAll("[\\p{Punct}&&[^\\.]]", ""));
					} else {
						cells.add(cellString.replaceAll("[\n\t]", " "));
					}
				}
				rowData.add(cells);
			}
		}
		return rowData;
	}

	private List<Integer> makeStataVars() {
		List<String> varnm = this.varnames.getVarNames();
		List<Boolean> varTypes = this.type.getDataTypes();
		List<Integer> varidx = new ArrayList<>();
		for(int i = 0; i < varTypes.size(); i++) {
			if (varTypes.get(i)) {
				Data.addVarDouble(varnm.get(i));
				varidx.add(Data.getVarIndex(varnm.get(i)));
			} else {
				Data.addVarStrL(varnm.get(i));
				varidx.add(Data.getVarIndex(varnm.get(i)));
			}
		}
		return varidx;
	}

	public void loadData() {
		for(int obs = 0; obs < this.data.size(); obs++) {
			List<String> row = this.data.get(obs);
			for (int var = 0; var < row.size(); var++) {
				if (this.type.getDataTypes().get(var)) {
					Data.storeNum(this.varindices.get(var), (long) obs + 1,
							Double.valueOf(row.get(var)));
				} else {
					Data.storeStr(this.varindices.get(var), (long) obs + 1, row.get(var));
				}
			}
		}
	}

}
