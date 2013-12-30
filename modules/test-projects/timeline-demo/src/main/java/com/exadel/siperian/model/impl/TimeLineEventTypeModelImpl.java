/**
 * 
 */
package com.exadel.siperian.model.impl;

import com.exadel.siperian.model.TimeLineEventTypeModel;

/**
 * @author Andrey
 *
 */
public class TimeLineEventTypeModelImpl implements TimeLineEventTypeModel {
	
	String eventID;
	
	String eventName;
	
	String styleClass;
	
	

	public TimeLineEventTypeModelImpl(String eventID, String eventName,
			String styleClass) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.styleClass = styleClass;
	}

	/* (non-Javadoc)
	 * @see com.exadel.siperian.model.TimeLineEventTypeModel#getEventTypeId()
	 */
	public String getEventTypeId() {
		// TODO Auto-generated method stub
		return eventID;
	}

	/* (non-Javadoc)
	 * @see com.exadel.siperian.model.TimeLineEventTypeModel#getEventTypeName()
	 */
	public String getEventTypeName() {
		// TODO Auto-generated method stub
		return eventName;
	}

	/* (non-Javadoc)
	 * @see com.exadel.siperian.model.TimeLineEventTypeModel#getEventTypeStyleClass()
	 */
	public String getEventTypeStyleClass() {
		// TODO Auto-generated method stub
		return styleClass;
	}

}
