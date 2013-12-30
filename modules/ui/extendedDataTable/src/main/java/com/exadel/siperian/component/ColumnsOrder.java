package com.exadel.siperian.component;

import org.richfaces.component.UIColumn;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: mabramchik
 * Date: Oct 30, 2008
 * Time: 4:18:44 PM
 * To change this template use File | Settings | File Templates.
 */
class ColumnsOrder implements Serializable {

	private static final long serialVersionUID = 907700564445889954L;

	private static final String SEP = ";";

	private String value;

	private ColumnsOrder() {
		super();
	}

	static com.exadel.siperian.component.ColumnsOrder getColumnsOrder(UIExtendedDataTable extendedDataTable, String val){
		com.exadel.siperian.component.ColumnsOrder columnsOrder = new com.exadel.siperian.component.ColumnsOrder();
		columnsOrder.init(extendedDataTable, val);
		return columnsOrder;
	}

	/**
	 * Converts its state from String representation or create default state if it is not set.
	 */
	private void init(UIExtendedDataTable extendedDataTable, String val){
		value = val;
		if ((value == null) || (value.length() == 0))
			createDefaultColumnsOrder(extendedDataTable);
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
	private void createDefaultColumnsOrder(com.exadel.siperian.component.UIExtendedDataTable extendedDataTable){
		StringBuilder builder = new StringBuilder();
		for (Iterator<UIColumn> iter = extendedDataTable.getChildColumns(); iter.hasNext();) {
			UIColumn child = iter.next();
			builder.append(child.getId().toUpperCase()).append(SEP);
		}
		value = builder.toString();
	}

	/**
	 * Get column index in order.
	 * @param columnId column id to be found
	 * @return column index or null if not found
	 */
	private Integer getColumnIndex(String columnId){
		if (value == null)
			return null;
		List<String> list = Arrays.asList(value.toUpperCase().split(SEP));
		if (list.contains(columnId.toUpperCase()))
			return list.indexOf(columnId.toUpperCase());
		return null;
	}//getColumnIndex

	/**
	 * Changes column order. Moves source column to be next to target column.
	 * @param sourceColumnId source column id to be moved
	 * @param targetColumnId target column id
	 * @param dropBefore
	 */
	void changeOrder(String sourceColumnId, String targetColumnId, boolean dropBefore){
		if (value == null)
			return;
		if (sourceColumnId.equals(targetColumnId))
			return;
		List<String> list = new ArrayList<String>(Arrays.asList(value.toUpperCase().split(SEP)));
		//get index of source column
		int sourceIndex = list.indexOf(sourceColumnId.toUpperCase());
		//remove from order if exist
		if (sourceIndex != -1)
			list.remove(sourceIndex);
		//get index of target column
		int targetIndex = list.indexOf(targetColumnId.toUpperCase());
		//add source column after or before target column
		if (targetIndex == -1)//add to end
			list.add(sourceColumnId.toUpperCase());
		else{
			//add at proper position
			list.add((targetIndex + (dropBefore ? 0 : 1)), sourceColumnId.toUpperCase());
		}
		//convert from List to String
		StringBuilder builder = new StringBuilder();
		for (String str : list)
			builder.append(str).append(SEP);
		value = builder.toString();
	}

	/**
	 * Sort column by given order.
	 * @param context faces context
	 * @param children list of unsorted columns
	 * @return list of sorted columns
	 */
	List<UIComponent> sortColumns(final FacesContext context, List<UIComponent> children){
		List<UIComponent> childs = new ArrayList<UIComponent>(children);
		Collections.sort(childs, new Comparator<UIComponent>() {
			public int compare(UIComponent o1, UIComponent o2) {
				Integer index1 = getColumnIndex(o1.getId());
				Integer index2 = getColumnIndex(o2.getId());
				if (index1 == null) {
					return ((index2 == null) ? 0 : 1);
				}
				return ((index2 == null) ? -1 : index1.compareTo(index2));
			}
		});
		return childs;
	}

}//ColumnsOrder
