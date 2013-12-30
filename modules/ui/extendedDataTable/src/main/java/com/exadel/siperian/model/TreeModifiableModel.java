package com.exadel.siperian.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.model.ModifiableModel;


public class TreeModifiableModel extends ModifiableModel {

	private Object rowKey;
	private ValueExpression subRows;
	private String rootRowVar;

	public TreeModifiableModel(ExtendedDataModel originalModel, String var, ValueExpression subRows, String rootRowVar) {
		super(originalModel, var);
		this.subRows = subRows;
		this.rootRowVar = rootRowVar;
	}
	
	@Override
	public Object getRowKey() {
		return rowKey;
	}
	
	@Override
	public void setRowKey(Object key) {
		rowKey = key;
		if (key instanceof Integer[]) {
			key = ((Integer[]) key)[0];
		}
		super.setRowKey(key);
		exposeRootRowVar(isRootRow());
	}

	@Override
	public void walk(FacesContext context, DataVisitor visitor, Range range,
			Object argument) throws IOException {
		final SequenceRange seqRange = (SequenceRange) range;
		int rows = seqRange.getRows();
		int rowCount = getRowCount();
		int currentRow = seqRange.getFirstRow();
		if(rows > 0){
			rows += currentRow;
			rows = Math.min(rows, rowCount);
		} else {
			rows = rowCount;
		}
		for (; currentRow < rows; currentRow++) {
			int subRowsCount = 0;
			Object originalRowKey = super.getRowKey();
			super.setRowKey(currentRow);
			if (super.isRowAvailable()) {
				Object value = getSubRows(context);
				if (value instanceof List) {
					subRowsCount = ((List<?>) value).size();
				}
			}
			super.setRowKey(originalRowKey);
			
			visitor.process(context, currentRow, argument);
			
			for (int i = 0; i < subRowsCount; i++) {
				visitor.process(context, new Integer[]{currentRow, i}, argument);
			}
		}
	}

	@Override
	public Object getRowData() {
		Object rowData = originalModel.getRowData();
		if (rowKey instanceof Integer[]) {
			Object value = getSubRows(FacesContext.getCurrentInstance());
			if (value instanceof List) {
				rowData = ((List<?>) value).get(((Integer[]) rowKey)[1]);
			}
		}
		return rowData;
	}

	@Override
	public boolean isRowAvailable() {
		boolean result = originalModel.isRowAvailable();
		if (result && rowKey instanceof Integer[]) {
			Object value = getSubRows(FacesContext.getCurrentInstance());
			if (value instanceof List) {
				int subRowKey = ((Integer[]) rowKey)[1];
				result = subRowKey >= 0 && subRowKey < ((List<?>) value).size();
			}
		}
		return result;
	}
	
	public boolean isRootRow() {
		boolean r = rowKey instanceof Integer;
		exposeRootRowVar(r);
		return r;
	}
	
	private void exposeRootRowVar(boolean isRoot) {
		if (rootRowVar != null) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(rootRowVar, isRoot);
		}
	}
	
	private Object getSubRows(FacesContext context) {
		Map<String, Object> map = context.getExternalContext().getRequestMap();
		Object originalValue = map.put(var, originalModel.getRowData());
		Object value = subRows.getValue(context.getELContext());
		map.put(var, originalValue);
		return value;
	}
}
