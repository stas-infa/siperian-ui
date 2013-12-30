package com.exadel.sample;

//=====================================================================
// project:   Siperian Hub
//---------------------------------------------------------------------
// copyright: Siperian Inc. (c) 2003-2010.  All rights reserved.
//=====================================================================

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.event.FacesEvent;
import javax.faces.event.ValueChangeEvent;

import org.ajax4jsf.context.AjaxContext;
import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.Ordering;

import com.exadel.siperian.component.UIExtendedDataTable;

/**
 * This class provides test data for ExtendedDataTable
 * 
 * @author: Alexander Chigrinets
 * @date: Feb 17, 2011
 */
public class TestTableData {

	Object selection;

	Ordering order;

	List<String> selects;
	
	Boolean allSelected;

	private ExtendedTableDataModel<TestTableData.TestTableRow> model;

	private int currentPage = 1;
	
	public Boolean getAllSelected() {
		return allSelected;
	}
	
	public void setAllSelected(Boolean allSelected) {
		this.allSelected = allSelected;
	}

	public TestTableData() {
		model = new ExtendedTableDataModel<TestTableData.TestTableRow>(
				new TestDataProvider());
	}
	
	public void valueChanged(ValueChangeEvent event) {
		
		UIExtendedDataTable table = getTable(event);
		
		Object rowKey = table.getActiveRowKey();
		
		if (rowKey != null && rowKey instanceof Integer) {
			table.getAjaxKeys().add(((Integer)rowKey) + 1);
		}
		
		AjaxContext.getCurrentInstance().addComponentToAjaxRender(table.findComponent("timespamp"));
		System.out.println("TestTableData.valueChanged()");
		
	}
	
	private UIExtendedDataTable getTable(FacesEvent event) {
		UIComponent c = event.getComponent();
		
		while (c != null) {
			if (c instanceof UIExtendedDataTable) {
				return (UIExtendedDataTable)c;
			}
			c = c.getParent();
		}
		
		return null;
	}

	public ExtendedTableDataModel<TestTableData.TestTableRow> getDataModel() {
		return model;
	}

	public int getRowCount() {
		return model.getDataProvider().getRowCount();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		
		this.currentPage = currentPage;
		model.reset();
	}

	public class TestTableRow {
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		private String email;

		private String date;

		private String select;

		private List<TestTableRow> rows;

		boolean expanded;

		boolean checked;
		
		boolean editable;
		
		boolean emailEdited;
		
		boolean dateEdited;
		
		boolean selectEdited;

		public TestTableRow(boolean editable) {
			this(true, editable);
		}

		public TestTableRow(boolean children, boolean editable) {
			this.email = "achigrinets@informatica.com";
			this.date = format.format(new Date());
			this.select = "Select item select time select 0";
			this.editable = editable;

			if (children) {
				rows = new ArrayList<TestTableData.TestTableRow>();
				rows.add(new TestTableRow(false, editable));
				rows.add(new TestTableRow(false, editable));
				rows.add(new TestTableRow(false, editable));
			}

		}
		
		public boolean isEditable() {
			return editable;
		}

		public boolean isExpanded() {
			return expanded;
		}

		public void setExpanded(boolean expanded) {
			this.expanded = expanded;
		}

		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}

		public String getDate() {
			return date;
		}
		
		public void setDate(String date) {
			this.date = date;
		}

		public String getSelect() {
			return select;
		}

		public void setSelect(String select) {
			this.select = select;
		}

		public List<TestTableRow> getRows() {
			return rows;
		}

		public boolean isChecked() {
			return (allSelected != null && allSelected) || checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		public boolean isEmailEdited() {
			return emailEdited;
		}

		public void setEmailEdited(boolean emailEdited) {
			this.emailEdited = emailEdited;
		}

		public boolean isDateEdited() {
			return dateEdited;
		}

		public void setDateEdited(boolean dateEdited) {
			this.dateEdited = dateEdited;
		}

		public void setEditable(boolean editable) {
			this.editable = editable;
		}
		
		
	}

	private Object state1;
	
	private Object state2;

	public Object getState1() {
		return state1;
	}

	public void setState1(Object value) {
		state1 = value;
	}
	
	public Object getState2() {
		return state2;
	}

	public void setState2(Object value) {
		state2 = value;
	}


	class TestDataProvider implements DataProvider<TestTableRow> {

		/**
			 * 
			 */
		private static final long serialVersionUID = -6720620269862981688L;

		static final private int ROW_COUNT = 95;

		private List<TestTableRow> items = new ArrayList<TestTableRow>(
				ROW_COUNT);

		TestDataProvider() {
			for (int i = 0; i < ROW_COUNT; i++) {
				items.add(new TestTableRow(i % 2 == 0));
			}
		}

		public int getRowCount() {
			return items.size();
		}

		public List<TestTableRow> getItemsByRange(int firstRow, int endRow) {
			List<TestTableRow> result = new ArrayList<TestTableRow>();
			for (int i = firstRow; i < endRow; i++) {
				if (i < items.size()) {
					result.add(items.get(i));
				}
			}
			return result;
		}

		public TestTableRow getItemByKey(Object key) {
			return items.get((Integer) key);
		}

		public Object getKey(TestTableRow item) {
			return items.indexOf(item);
		}
	}

	public Object getSelection() {
		return selection;
	}

	public void setSelection(Object selection) {
		this.selection = selection;
	}

	public Ordering getOrder() {
		return order;
	}

	public void setOrder(Ordering order) {
		this.order = order;
	}

	public String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
		return f.format(new Date());

	}

	public List<String> getSelects() {
		if (selects == null) {
			List<String> l = new ArrayList<String>();
			for (int i = 0; i < 10; i++) {
				l.add("Select item select time select " + i);
			}
			selects = l;
		}

		return selects;
	}

}
