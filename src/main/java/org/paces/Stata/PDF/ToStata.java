package org.paces.Stata.PDF;

import com.stata.sfi.Data;
import technology.tabula.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class ToStata {

	private Map<Integer, List<String>> data;
	private List<Integer> varindices;
	private Integer nvars;

	public ToStata(List<Table> table) {
		this.data = parseRows(table);
		Data.setObsTotal(this.data.size());
		this.varindices = makeStataVars();
		loadData();
	}

	private Header getHeader(Table tab) {
		return new Header(tab.getRows().get(0));
	}

	private Map<Integer, List<String>> parseRows(List<Table> tabs) {
		Map<Integer, List<String>> rowData = new HashMap<>();
		Integer id = 0;
		Integer vars = 0;
		for(Table table : tabs) {
			for (Integer rows = 0; rows < table.getRows().size(); rows++) {
				id++;
				List<String> record = new ArrayList<>();
				for(Integer cols = 0; cols < table.getCols().size(); cols++) {
					if (table.getCols().size() > vars) vars = table.getCols().size();

					String cellString = table.getCell(rows, cols).getText()
							.trim();
					if (cellString.matches("[^\\p{Alpha}]+")) {
						record.add(cellString.replaceAll("[\\p{Punct}&&[^\\.]]", ""));
					} else {
						record.add(cellString.replaceAll("[\n\t]", " "));
					}
				}
				rowData.put(id, record);
			}
		}
		this.nvars = vars;
		return rowData;
	}

	private List<Integer> makeStataVars() {
		List<Integer> varidx = new ArrayList<>();
		for(Integer i = 0; i < nvars; i++) {
			Data.addVarStrL("var" + String.valueOf(i));
			varidx.add(Data.getVarIndex("var" + String.valueOf(i)));
		}
		return varidx;
	}

	public void loadData() {
		for(int obs = 0; obs < this.data.size(); obs++) {
			List<String> row = this.data.get(obs);
			for (int var = 0; var < row.size(); var++) {
				Data.storeStr(this.varindices.get(var), (long) obs + 1, row.get(var));
			}
		}
	}

}
