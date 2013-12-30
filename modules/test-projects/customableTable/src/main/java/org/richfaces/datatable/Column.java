/**
 * 
 */
package org.richfaces.datatable;

/**
 * @author asmirnov
 *
 */
public class Column {
	
	/**
	 * @param order
	 */
	public Column(int order) {
		this.order = order;
		switch (order) {
		case 0:
			name="Capital";
			break;
		case 1:
			name="State Name";
			break;
		case 2:
			name="Time Zone";
			break;
		case 3:
			name="Population";
			break;
		case 4:
			name="Budget";
			break;
		case 5:
			name="Description";
			break;

		default:
			throw new IllegalArgumentException();
		}
	}
	private final int order;
	private final String name;
	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.valueOf(getOrder());
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + order;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Column))
			return false;
		Column other = (Column) obj;
		if (order != other.order)
			return false;
		return true;
	}

}
