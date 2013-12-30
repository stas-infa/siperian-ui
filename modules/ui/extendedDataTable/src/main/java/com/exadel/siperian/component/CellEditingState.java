package com.exadel.siperian.component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

class CellEditingState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4359222273019825992L;

	private static final String SEP = ";";
	
	boolean hasModifiedData = false;

	Map<Object, Boolean> editingState = new HashMap<Object, Boolean>();

	private CellEditingState() {
		super();
	}

	static com.exadel.siperian.component.CellEditingState getCellEditingState(
			UIExtendedDataTable extendedDataTable, String val) {
		com.exadel.siperian.component.CellEditingState cellEditingState = new com.exadel.siperian.component.CellEditingState();
		cellEditingState.init(extendedDataTable, val);
		return cellEditingState;
	}

	private void init(UIExtendedDataTable extendedDataTable, String val) {
		if (val != null) {
			String[] keys = val.split(SEP);
			for (String k : keys) {
				if (k != null && k.length() > 0) {
					editingState.put(k, true);
				}
			}
		}
	}

	public boolean isCellModified(Object rowKey, String colId) {
		Boolean o = (Boolean) editingState.get(rowKey.toString() + colId);
		hasModifiedData = hasModifiedData || o != null;
		return o != null;
	}

	public void setCellModified(Object rowKey, String colId) {
		editingState.put(rowKey.toString() + colId, true);
	}
	
	public boolean hasModifiedData() {
		return hasModifiedData;
	}

	/**
	 * Converts its state to String representation.
	 */
	public String toString() {
		StringBuilder val = new StringBuilder("");
		for (Object key : editingState.keySet()) {
			val.append(key).append(SEP);
		}
		return val.toString();
	}

}
