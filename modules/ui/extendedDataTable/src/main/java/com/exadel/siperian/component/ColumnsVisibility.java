package com.exadel.siperian.component;

import org.richfaces.component.UIColumn;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: mabramchik
 * Date: Oct 30, 2008
 * Time: 4:18:53 PM
 * To change this template use File | Settings | File Templates.
 */
class ColumnsVisibility implements Serializable {

	private static final long serialVersionUID = -3923409650272094713L;

	private static final String SEP = ";";

	private String value;

	private ColumnsVisibility() {
		super();
	}

	static com.exadel.siperian.component.ColumnsVisibility getColumnsVisibility(com.exadel.siperian.component.UIExtendedDataTable extendedDataTable, String val){
		com.exadel.siperian.component.ColumnsVisibility columnsVisibility = new com.exadel.siperian.component.ColumnsVisibility();
		columnsVisibility.init(extendedDataTable, val);
		return columnsVisibility;
	}

	/**
	 * Converts its state from String representation or create default state if it is not set.
	 */
	private void init(com.exadel.siperian.component.UIExtendedDataTable extendedDataTable, String val){
		value = val;
		if ((value == null) || (value.length() == 0))
			createDefaultColumnsVisibility(extendedDataTable);
		//set visibility flag for all columns
		for (Iterator<UIColumn> iter = extendedDataTable.getChildColumns(); iter.hasNext();) {
			UIColumn child = iter.next();
			if (child instanceof UIColumn) {
				UIColumn dataColumn = (UIColumn) child;
				dataColumn.setVisible(isVisible(dataColumn.getId()));
			}//if
		}//for
	}//init

	/**
	 * Converts its state to String representation.
	 */
	public String toString(){
		return value;
	}

	/**
	 * Create default column visibility based on component children.
	 */
	private void createDefaultColumnsVisibility(UIExtendedDataTable extendedDataTable){
		StringBuilder builder = new StringBuilder();
		for (Iterator<UIColumn> iter = extendedDataTable.getChildColumns(); iter.hasNext();) {
			UIColumn kid = iter.next();
			builder.append(kid.getId().toUpperCase()).append(SEP);
		}
		value = builder.toString();
	}//createDefaultColumnsVisibility

	/**
	 * Get column visibility.
	 * @param columnId column id to be found
	 * @return true if column is visible, otherwise false
	 */
	boolean isVisible(String columnId){
		if (value == null)
			return true;
		Set<String> visibleIds = new HashSet<String>(Arrays.asList(value.toUpperCase().split(SEP)));
		return visibleIds.contains(columnId.toUpperCase());
	}//isVisible

	/**
	 * Toggle column visibility.
	 * @param extendedDataTable table component
	 * @param columnId column id
	 */
	void toggleVisibility(com.exadel.siperian.component.UIExtendedDataTable extendedDataTable, String columnId){
		if (value == null)
			return;
		UIColumn column = null;
		//find column by id
		for (Iterator<UIColumn> iter = extendedDataTable.getChildColumns(); iter.hasNext();) {
			UIColumn col = iter.next();
			if (col.getId().equalsIgnoreCase(columnId)){
				if (col instanceof UIColumn){
					column = (UIColumn) col;
				}
				break;
			}//if
		}//for
		if (column == null)
			return;
		boolean visible = column.isVisible();
		//toggle visibility
		visible = !visible;
		//set visibility flag for column
		column.setVisible(visible);
		Set<String> visibleIds = new HashSet<String>(Arrays.asList(value.toUpperCase().split(SEP)));
		if (visible){
			//add id to set
			visibleIds.add(columnId.toUpperCase());
		}
		else{
			//remove id from list
			visibleIds.remove(columnId.toUpperCase());
		}
		//convert from Set to String
		StringBuilder builder = new StringBuilder();
		for (String str : visibleIds)
			builder.append(str).append(SEP);
		value = builder.toString();
	}//changeVisibility

}//ColumnsVisibility
