
package com.exadel.siperian.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Interface for time line event data implementation  
 * @author Andrey Markavtsov
 */
public interface TimeLineEventDataModel extends Serializable {
	
	
	/**
	 * @return event id
	 */
	public String getEventId();
	
	/**
	 * @return event type
	 */
	public String getEventType();
	
	
	/**
	 * @return event date
	 */
	public Date getEventDate();
	
	/**
	 * @return swim lane id
	 */
	public String getSwimLaneId();
	
	/**
	 * @return true if selected
	 */
	public boolean isSelected();
	
	/**
	 * @return toolTip text
	 */
	public String getToolTip();

}
