package com.exadel.siperian.component;

import org.richfaces.component.UIColumn;
import org.ajax4jsf.util.HtmlDimensions;

import javax.faces.component.UIComponent;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;


class ColumnsSizeState implements Serializable {

	private static final long serialVersionUID = 8724163192351491340L;

	private static final String SEP = ";";

	private static final String DEFAULT_WIDTH = "20";

	private String value;

	private ColumnsSizeState() {
		super();
	}

	static com.exadel.siperian.component.ColumnsSizeState getColumnsSize(UIExtendedDataTable extendedDataTable, String val){
		com.exadel.siperian.component.ColumnsSizeState columnsSize = new com.exadel.siperian.component.ColumnsSizeState();
		columnsSize.init(extendedDataTable, val);
		return columnsSize;
	}

	/**
	 * Converts its state from String representation or create default state if it is not set.
	 */
	private void init(UIExtendedDataTable extendedDataTable, String val){
		value = val;
		if ((value == null) || (value.length() == 0))
			createDefaultColumnsSizeState(extendedDataTable);
	}

	/**
	 * Converts its state to String representation.
	 */
	public String toString(){
		return value;
	}

	/**
	 * Create default column order based on component children.
	 */
	private void createDefaultColumnsSizeState(UIExtendedDataTable extendedDataTable){
		StringBuilder builder = new StringBuilder();

		for (Iterator<UIColumn> iter = extendedDataTable.getChildColumns(); iter.hasNext();) {
			UIColumn col = iter.next();
			builder.append(col.getId().toUpperCase()).append("-").append(getDefaultColumnSize(col)).append(SEP);
		}
		value = builder.toString();
	}

	private String getDefaultColumnSize(UIComponent column){
		String widthStr = (String) column.getAttributes().get("width");
		return (widthStr == null) ? DEFAULT_WIDTH : widthStr;
	}

	public String getColumnSize(UIComponent column){
		if (value == null)
			return getDefaultColumnSize(column);
		String[] widths = value.split(SEP);
		if (widths != null){
			String colId = column.getId().toUpperCase();
			for (String val : widths){
				if (val.toUpperCase().startsWith(colId+"-")){
					return val.split("-")[1];
				}
			}//for
		}
		return getDefaultColumnSize(column);
	}

	private String formatWidth(String value){
		return String.valueOf(HtmlDimensions.decode(value).intValue());
	}

	public void changeColumnSize(UIExtendedDataTable extendedDataTable, String newValue){
		if (value == null)
			return;
		Set<String> widths = new HashSet<String>(Arrays.asList(value.toUpperCase().split(SEP)));
		String[] newWidths = newValue.split(";");
		int index = 0;
		for (Iterator<UIColumn> iter = extendedDataTable.getSortedColumns(); iter.hasNext();) {
			UIComponent col = (UIComponent) iter.next();
			if (col.isRendered()){
				String colId = col.getId().toUpperCase();
				//remove existing item
				Set<String> toDel = new HashSet<String>();
				for (String val : widths){
					if (val.toUpperCase().startsWith(colId+"-")){
						toDel.add(val);
					}
				}//for
				widths.removeAll(toDel);
				//create new item
				String newWidth = newWidths[index++];
				String item = colId + "-" + newWidth;;
				widths.add(item);
			}//if
		}//for
		//build new value
		StringBuilder builder = new StringBuilder();
		for (String val : widths){
			builder.append(val).append(SEP);
		}
		value = builder.toString();
	}//changeColumnSize

}//ColumnsSizeState
