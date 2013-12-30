/**
 * 
 */
package org.richfaces.datatable;

import java.util.AbstractList;

/**
 * @author asmirnov
 *
 */
public class CapitalWrapper extends AbstractList<Object> {
	
	private Capital capital;

	/**
	 * @param capital
	 */
	public CapitalWrapper(Capital capital) {
		this.capital = capital;
	}

	@Override
	public Object get(int index) {
		Object result = null;
		switch (index) {
		case 0:
			result = capital.getName();
			break;
		case 1:
			result = capital.getState();
			break;
		case 2:
			result = capital.getTimeZone();
			break;
		case 3:
			result = capital.getPopulation();
			break;
		case 4:
			result = capital.getBuget();
			break;
		case 5:
			result = capital.getDescription();
			break;

		default:
			break;
		}
		return result;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 6;
	}

}
