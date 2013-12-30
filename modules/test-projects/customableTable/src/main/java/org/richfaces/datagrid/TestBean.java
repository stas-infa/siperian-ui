package org.richfaces.datagrid;

import java.util.ArrayList;
import java.util.List;

public class TestBean {

	private List<Table> tables = new ArrayList<Table>();

	public TestBean() {
		Table t1 = new Table();
		tables.add(t1);
		t1.getCells().add(new Cell());
		t1.getCells().add(new Cell());

		Table t2 = new Table();
		tables.add(t2);
		t2.getCells().add(new Cell());
		t2.getCells().add(new Cell());
	}

	public String submit() {
		return null;
	}

	public void submitAjax() {
		
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
}
