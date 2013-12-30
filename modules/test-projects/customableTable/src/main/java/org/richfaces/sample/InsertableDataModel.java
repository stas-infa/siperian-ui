/**
 * 
 */
package org.richfaces.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;

public class InsertableDataModel extends ExtendedDataModel {

	private List<DataItem> data = new ArrayList<DataItem>();

	private Integer key;

	private int row = -1;

	
	
	/**
	 * @param data
	 */
	public InsertableDataModel(List<DataItem> data) {
		super();
		this.data = data;
	}

	public InsertableDataModel() {
		data = new ArrayList<DataItem>();
	}

	@Override
	public Object getRowKey() {
		return key;
	}

	@Override
	public void setRowKey(Object key) {
		if (key instanceof Integer) {
			Integer intKey = (Integer) key;
			DataItem keyItem = new DataItem(intKey.intValue());
			row = data.indexOf(keyItem);
		}

	}

	@Override
	public void walk(FacesContext context, DataVisitor visitor,
			Range range, Object argument) throws IOException {
		int firstRow = ((SequenceRange) range).getFirstRow();
		int numberOfRows = ((SequenceRange) range).getRows();
		if(numberOfRows < 0){numberOfRows = data.size()-firstRow;}
		for(int i = firstRow; i < numberOfRows+firstRow && i < data.size(); i++){
			visitor.process(context, data.get(i).getKey(), argument);
		}
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getRowData() {
		if (row >= 0 && row < data.size()) {
			return data.get(row);
		}
		return null;
	}

	@Override
	public int getRowIndex() {
		return row;
	}

	@Override
	public Object getWrappedData() {
		return data;
	}

	@Override
	public boolean isRowAvailable() {
		return row >= 0 && row < data.size();
	}

	@Override
	public void setRowIndex(int rowIndex) {
		row = rowIndex;
		if (row >= 0 && row < data.size()) {
			key = data.get(row).getKey();
		} else {
			key = null;
		}
	}

	@Override
	public void setWrappedData(Object data) {
		if (data instanceof List) {
			this.data = (List) data;
			key = null;
			row = -1;
		}

	}

}