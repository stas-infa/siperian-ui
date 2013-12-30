/**
 * 
 */
package org.richfaces.sample;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author asmirnov
 *
 */
public class DataActionBean {
	
	private DataModelBean data;
	
	private Integer key;
	
	private Set<Integer> ajaxKeys;

	/**
	 * @return the ajaxKeys
	 */
	public Set<Integer> getAjaxKeys() {
		return ajaxKeys;
	}

	/**
	 * @return the data
	 */
	public DataModelBean getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(DataModelBean data) {
		this.data = data;
	}

	/**
	 * @return the key
	 */
	public Integer getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(Integer key) {
		this.key = key;
	}

	
	public void insert(){
		Integer insertItem = data.insertItem(key);
		if(null != insertItem){
			ajaxKeys = new HashSet<Integer>();
			ajaxKeys.add(insertItem);
		}
	}
}
