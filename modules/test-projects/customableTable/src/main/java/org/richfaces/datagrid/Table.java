package org.richfaces.datagrid;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private List<Cell> cells = new ArrayList<Cell>();

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}
}
