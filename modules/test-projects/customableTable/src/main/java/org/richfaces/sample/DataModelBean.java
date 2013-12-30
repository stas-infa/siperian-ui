/**
 * 
 */
package org.richfaces.sample;

import java.util.ArrayList;
import java.util.List;


import org.ajax4jsf.model.ExtendedDataModel;

/**
 * @author asmirnov
 * 
 */
public class DataModelBean {

	private ExtendedDataModel model;
	private List<DataItem> data;
	private int itemNumber;
	 
	 
	 public DataModelBean() {
		data = new ArrayList<DataItem>(10);
		for(itemNumber = 0;itemNumber<5;itemNumber++){
			DataItem item = new DataItem(itemNumber);
			item.setName("Item number "+itemNumber);
			item.setPrice((int)(Math.random()*100.0D));
			data.add(item);
		}
		model = new InsertableDataModel(data);
	}
	

	/**
	 * @return the model
	 */
	public ExtendedDataModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(ExtendedDataModel model) {
		this.model = model;
	}
	
	public Integer removeItem(Integer key){
		DataItem keyItem = new DataItem(key);
		boolean removed = data.remove(keyItem);
		return removed?key:null;
	}
	
	
	public Integer insertItem(Integer key){
		DataItem keyItem = new DataItem(key.intValue());
		Integer newKey = null;
		int row = data.indexOf(keyItem);
		if(row >=0){
			newKey = itemNumber;
			data.add(row, new DataItem(itemNumber++));
		}
		return newKey;
	}
}
