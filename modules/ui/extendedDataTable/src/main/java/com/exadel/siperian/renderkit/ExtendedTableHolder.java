/**
 * 
 */
package com.exadel.siperian.renderkit;

import org.richfaces.component.UIColumn;
import org.richfaces.renderkit.TableHolder;

/**
 * Extended table holder. It keeps additional information like last row key, last row data
 * and information about grouping.
 * @author pawelgo
 *
 */
public class ExtendedTableHolder extends TableHolder {
	
	private Object lastData = null;
	private Object lastKey = null;
	//private int curRowId = -1;
	private int groupRowCounter = -1;
	
	private String currentRootRowId;
	private boolean isRootRowExpanded;
	private boolean previousRootRowExpanded;
	private boolean notEditable;
	private int rowCounter = 0;
	
	/**
	 * @return the rowCounter
	 */
	public int getRowCounter() {
		return this.rowCounter;
	}
	
	public void setRowCounter(int rowCounter) {
		this.rowCounter = rowCounter;
	}
	
	/**
	 * Get current rendered row number, and increment to next value.
	 * @return the rowCounter
	 */
	public int nextRow() {
		super.nextRow();
		return ++rowCounter;
	}
	
	
	public String getCurrentRootRowId() {
		return currentRootRowId;
	}

	public void setCurrentRootRowId(String currentRootRowId) {
		this.currentRootRowId = currentRootRowId;
	}

	private boolean firstRow = true;
	
//	private String groupingColumn = null;
//	private String groupingColumnLabel = null;
	private Integer frozenColumns = null;
	
	/**
	 * 
	 * @param table
	 */
	public ExtendedTableHolder(com.exadel.siperian.component.UIExtendedDataTable table) {
		super(table);
		lastData = null;
		lastKey = null;
		groupRowCounter = 0;
		
	}
	
	/**
	 * 
	 * @param table
	 */
	public ExtendedTableHolder(com.exadel.siperian.component.UIExtendedDataTable table, Integer frozenColumns) {
		this(table);
		this.frozenColumns = frozenColumns;
	}
	
	public boolean isFirstRow() {
        return firstRow;
    }

    public void setFirstRow(boolean firstRow) {
        this.firstRow = firstRow;
    }



    public com.exadel.siperian.component.UIExtendedDataTable getTable() {
		return (com.exadel.siperian.component.UIExtendedDataTable)super.getTable();
	}

	public Object getLastData() {
		return lastData;
	}

	public void setLastData(Object lastData) {
		this.lastData = lastData;
	}

	public Object getLastKey() {
		return lastKey;
	}

	public void setLastKey(Object lastKey) {
		this.lastKey = lastKey;
	}
	
	public int getGroupRowCounter() {
		return groupRowCounter;
	}

	/**
	 * Get current rendered row number, and increment to next value.
	 * @return the rowCounter
	 */
	public int nextGroupRow() {
		return groupRowCounter++;
	}
	
	public Integer getFrozenColumns() {
		return frozenColumns;
	}
	
	public void setRootRowExpanded(boolean isRootRowExpanded) {
		this.previousRootRowExpanded = this.isRootRowExpanded;
		this.isRootRowExpanded = isRootRowExpanded;
	}
	
	public boolean isRootRowExpanded() {
		return isRootRowExpanded;
	}
	
	public boolean isPreviousRootRowExpanded() {
		return previousRootRowExpanded;
	}
	
	public void setNotEditable(boolean notEditable) {
		this.notEditable = notEditable;
	}
	
	public boolean isNotEditable() {
		return notEditable;
	}

}
