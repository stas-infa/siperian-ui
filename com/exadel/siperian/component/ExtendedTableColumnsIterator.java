/**
 * 
 */
package com.exadel.siperian.component;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.faces.component.UIComponent;

import org.richfaces.component.UIColumn;

/**
 * Columns iterator that work exactly like org.richfaces.component.ColumnsIterator
 * but raw type is {@link org.richfaces.component.UIColumn} instead of {@link javax.faces.component.UIComponent}
 * @see org.richfaces.component.ColumnsIterator
 * @author pawelgo
 *
 */
public class ExtendedTableColumnsIterator implements Iterator<UIColumn> {
	
	protected Iterator<UIComponent> childrenIterator;
	protected UIColumn next;
	protected boolean initialized = false;
	
	/**
	 * Creates iterator for table.
	 * @param dataTable table for which iterator is created
	 */
	public ExtendedTableColumnsIterator(com.exadel.siperian.component.UIExtendedDataTable dataTable) {
		super();
		childrenIterator = dataTable.getChildren().iterator();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		if(!initialized){
			next = nextColumn();
			initialized = true;
		}
		return null != next;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	public UIColumn next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		UIColumn result = next;
		next = nextColumn();
		return result;
	}
	
	protected UIColumn nextColumn(){
		UIColumn nextColumn = null;
		while (childrenIterator.hasNext()) {
			UIComponent child = childrenIterator.next();
			if(child instanceof UIColumn){
				nextColumn = (UIColumn)child;
				break;
			}
		}
		return nextColumn;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		throw new UnsupportedOperationException("Iterator is read-only");
	}

}
