/**
 * 
 */
package org.ajax4jsf;

import java.util.AbstractList;

/**
 * @author asmirnov
 *
 */
public class TreeBeansCollection extends AbstractList<SimpleTreeBean> {
	
	private final SimpleTreeBean[] beans;

	public TreeBeansCollection() {
		beans = new SimpleTreeBean[3];
		for (int i = 0; i < beans.length; i++) {
			beans[i]=new SimpleTreeBean();
		}
	}
	/* (non-Javadoc)
	 * @see java.util.AbstractList#get(int)
	 */
	@Override
	public SimpleTreeBean get(int index) {
		return beans[index];
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#size()
	 */
	@Override
	public int size() {
		return beans.length;
	}

}
