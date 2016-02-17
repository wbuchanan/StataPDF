package org.paces.Stata.PDF.Table;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Cell<T> {

	private T contentValue;
	private Integer rowPosition;
	private Integer colPosition;

	public Cell(Integer rowidx, Integer colidx, T content) {
		this.rowPosition = rowidx;
		this.colPosition = colidx;
		this.contentValue = content;
	}

	public T getContent() {
		return this.contentValue;
	}


}
