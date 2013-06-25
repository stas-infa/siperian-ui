package com.exadel.siperian.component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mabramchik
 * Date: Oct 30, 2008
 * Time: 4:18:38 PM
 * To change this template use File | Settings | File Templates.
 */
class ColumnGroupingState implements Serializable {

	private static final long serialVersionUID = -3923409650272094713L;
	
	private static final String SEP = ";";
	
	Map<Object, Boolean> groupingState = new HashMap<Object, Boolean>();
	
	private ColumnGroupingState() {
		super();
	}

	static com.exadel.siperian.component.ColumnGroupingState getGroupingState(UIExtendedDataTable extendedDataTable, String val){
		com.exadel.siperian.component.ColumnGroupingState groupingState = new com.exadel.siperian.component.ColumnGroupingState();
		groupingState.init(extendedDataTable, val);
		return groupingState;
	}
	
	/**
	 * Converts its state from String representation or create default state if it is not set.
	 */
	private void init(UIExtendedDataTable extendedDataTable, String val){
		if (val != null) {
			String [] keys = val.split(SEP);
			for (String k : keys) {
				if (k != null && k.length() > 0) {
					groupingState.put(k, true);
				}
			}
		}
	}
	
	public boolean isExpanded(Object key) {
		Boolean o = (Boolean)groupingState.get(key.toString());
		return o != null;
	}
	
	public void setExpanded(Object key, boolean expanded) {
		if (expanded) {
			groupingState.put(key.toString(), true);
		}else {
			groupingState.remove(key.toString());
		}
	}

	/**
	 * Converts its state to String representation.
	 */
	public String toString(){
		StringBuilder val = new StringBuilder("");
		for (Object key : groupingState.keySet()) {
			val.append(key).append(SEP);
		}
		return val.toString();
	}
	
	public void reset() {
		groupingState.clear();
	}
	
	
}
