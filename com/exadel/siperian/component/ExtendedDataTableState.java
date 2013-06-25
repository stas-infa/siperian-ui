/**
 * 
 */
package com.exadel.siperian.component;

import java.io.Serializable;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * @author pawelgo
 *
 */
public class ExtendedDataTableState implements Serializable {

	private static final long serialVersionUID = -3103664821855261335L;

	public static final String TABLE_STATE_ATTR_NAME = "tableState";
	
	protected static final String SEP = ":";
	
	protected com.exadel.siperian.component.ColumnsOrder columnsOrder;
	protected com.exadel.siperian.component.ColumnsVisibility columnsVisibility;
	protected com.exadel.siperian.component.ColumnsSizeState columnsSizeState;
	protected com.exadel.siperian.component.ColumnGroupingState columnGroupingState;
	protected com.exadel.siperian.component.CellEditingState cellEditingState;
	//protected com.exadel.siperian.component.ColumnGroupingState columnGroupingState;
	protected Integer scrollPosition;
	protected String  cellPosition  = null;

	private boolean expanded;
	
	public boolean isRootRowExpanded(Object rowKey) {
		return columnGroupingState.isExpanded(rowKey);
	}
	
	public boolean isCellModified(Object rowKey, String colId ) {
		return cellEditingState.isCellModified(rowKey, colId);
	}
	
	public void setRootRowExpanded(Object rowKey, boolean expanded) {
		columnGroupingState.setExpanded(rowKey, expanded);
	}
	
	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getCellPosition() {
        return cellPosition;
    }

    public void setCellPosition(String cellPosition) {
        this.cellPosition = cellPosition;
    }

    public static ExtendedDataTableState getExtendedDataTableState(UIExtendedDataTable extendedDataTable){
		ExtendedDataTableState state = new ExtendedDataTableState();
		state.init(extendedDataTable);
		return state;
	}//init
	
	/**
	 * Converts its state based on table attribute value or create default state if it is not set.
	 */
	protected void init(UIExtendedDataTable extendedDataTable){
		//get state value from components attributes
		String value = (String)extendedDataTable.getAttributes().get(TABLE_STATE_ATTR_NAME);
		//split state value into parts
		String[] values = fromString(value);
		//initialize columns order part
		String val = (values != null && values.length>0) ? values[0] : null;
		columnsOrder = com.exadel.siperian.component.ColumnsOrder.getColumnsOrder(extendedDataTable, val);
		//initialize columns visibility part
		val = (values != null && values.length>1) ? values[1] : null;
		columnsVisibility = com.exadel.siperian.component.ColumnsVisibility.getColumnsVisibility(extendedDataTable, val);
		//initialize columns size part
		val = (values != null && values.length>2) ? values[2] : null;
		columnsSizeState = com.exadel.siperian.component.ColumnsSizeState.getColumnsSize(extendedDataTable, val);
		//initialize column grouping part
//		val = (values != null && values.length>3) ? values[3] : null;
//		columnGroupingState = com.exadel.siperian.component.ColumnGroupingState.getColumnGropingState(extendedDataTable, val);
		val = (values != null && values.length>3) ? values[3] : null;
		
		this.setCellPosition(val);
		
		val = (values != null && values.length>4) ? values[4] : null;
		columnGroupingState = com.exadel.siperian.component.ColumnGroupingState.getGroupingState(extendedDataTable, val);
		
		val = (values != null && values.length>5) ? values[5] : null;
		cellEditingState = com.exadel.siperian.component.CellEditingState.getCellEditingState(extendedDataTable, val);
		
		
	}//init
	
	/**
	 * Puts own state into component state. 
	 */
	public void publishChanges(FacesContext context, com.exadel.siperian.component.UIExtendedDataTable extendedDataTable){
		ValueExpression ve = extendedDataTable.getValueExpression(TABLE_STATE_ATTR_NAME);
		if ((null != ve) && (!ve.isReadOnly(context.getELContext()))) {
			ve.setValue(context.getELContext(), toString());
		}
	}//publishChanges
	
	/**
	 * Converts its state to String representation.
	 */
	public String toString(){
		String[] values = new String[6];
		values[0] = columnsOrder.toString();
		values[1] = columnsVisibility.toString();
		values[2] = columnsSizeState.toString();
		values[3] = getCellPosition();
		values[4] = columnGroupingState.toString();
		values[5] = cellEditingState.toString();
		
		StringBuilder builder = new StringBuilder();
		for (String str : values){
			builder.append(str).append(SEP);
		}//for
		return builder.toString();
	}//toString
	
	public String[] fromString(String value){
		return (value == null) ? null : value.split(SEP);
	}//fromString
	
	public void restoreState() {
		if (cellEditingState != null) {
			cellEditingState.hasModifiedData = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ColumnsOrder#changeOrder(String, String)
	 */
	public void changeColumnsOrder(String sourceColumnId, String targetColumnId, boolean dropBefore) {
		columnsOrder.changeOrder(sourceColumnId, targetColumnId, dropBefore);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ColumnsOrder#sortColumns(FacesContext, List)
	 */
	public List<UIComponent> sortColumns(FacesContext context,
			List<UIComponent> children) {
		return columnsOrder.sortColumns(context, children);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ColumnsVisibility#isVisible(String)
	 */
	public boolean isColumnVisible(String columnId) {
		return columnsVisibility.isVisible(columnId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ColumnsVisibility#toggleVisibility(UIExtendedDataTable, String)
	 */
	public void toggleColumnVisibility(UIExtendedDataTable extendedDataTable,
			String columnId) {
		columnsVisibility.toggleVisibility(extendedDataTable, columnId);
	}
	
	
	public void processCellEditing(UIExtendedDataTable extendedDataTable,
			Object rowKey, String columnId) {
		cellEditingState.setCellModified(rowKey, columnId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ColumnsSizeState#changeColumnSize(UIExtendedDataTable, String)
	 */
	public void changeColumnSize(UIExtendedDataTable extendedDataTable,
			String newValue) {
		columnsSizeState.changeColumnSize(extendedDataTable, newValue);
	}
	
	public void saveScrollPosition(String scrollPosition) {
		this.scrollPosition = 0;
		try {
			this.scrollPosition = Integer.parseInt(scrollPosition);
		}catch (Exception e) {
			;// Do nothing
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ColumnsSizeState#getColumnSize(UIComponent)
	 */
	public String getColumnSize(UIComponent column) {
		return columnsSizeState.getColumnSize(column);
	}
	

	public Integer getScrollPosition() {
		return scrollPosition;
	}
}

