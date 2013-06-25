/**
 * 
 */
package com.exadel.siperian.model;

import java.io.Serializable;

/**
 * Base interface for event type definition.
 * 
 * @author Andrey Markavtsov
 */
public interface TimeLineEventTypeModel extends Serializable {
	
	/**
	 * Gets the event type id.
	 * 
	 * @return event type id
	 */
	public String getEventTypeId();
	
	/**
	 * Gets the event type name.
	 * 
	 * @return event type name
	 */
	public String getEventTypeName();
	
	/**
	 * Gets the event type style class.
	 * 
	 * @return event type css style class
	 */
	public String getEventTypeStyleClass();
	
	
	/**
	 * Gets the selected style class.
	 * 
	 * @return the selected style class
	 */
	public String getSelectedStyleClass();
	
	
	/**
	 * Gets the hover style class.
	 * 
	 * @return the hover style class
	 */
	public String getHoverStyleClass();
	
	

}
