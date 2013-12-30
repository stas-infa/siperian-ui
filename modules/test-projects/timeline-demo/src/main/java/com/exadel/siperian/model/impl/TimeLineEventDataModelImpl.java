/**
 * 
 */
package com.exadel.siperian.model.impl;

import java.util.Date;

import com.exadel.siperian.model.TimeLineEventDataModel;

/**
 * @author Andrey
 *
 */
public class TimeLineEventDataModelImpl implements TimeLineEventDataModel {
	
	Date date;
	
	String eventId;
	
	String eventType;
	
	String swimLaneId;
	
	String toolTipId;
	
	boolean selected;
	
	

	public TimeLineEventDataModelImpl(Date date, String eventId,
			String eventType, String swimLaneId, String toolTipId,
			boolean selected) {
		super();
		this.date = date;
		this.eventId = eventId;
		this.eventType = eventType;
		this.swimLaneId = swimLaneId;
		this.toolTipId = toolTipId;
		this.selected = selected;
	}

	/* (non-Javadoc)
	 * @see com.exadel.siperian.model.TimeLineEventDataModel#getEventDate()
	 */
	public Date getEventDate() {
		// TODO Auto-generated method stub
		return date;
	}

	/* (non-Javadoc)
	 * @see com.exadel.siperian.model.TimeLineEventDataModel#getEventId()
	 */
	public String getEventId() {
		// TODO Auto-generated method stub
		return eventId;
	}

	/* (non-Javadoc)
	 * @see com.exadel.siperian.model.TimeLineEventDataModel#getEventType()
	 */
	public String getEventType() {
		// TODO Auto-generated method stub
		return eventType;
	}

	/* (non-Javadoc)
	 * @see com.exadel.siperian.model.TimeLineEventDataModel#getSwimLaneId()
	 */
	public String getSwimLaneId() {
		// TODO Auto-generated method stub
		return swimLaneId;
	}

	/* (non-Javadoc)
	 * @see com.exadel.siperian.model.TimeLineEventDataModel#getToolTip()
	 */
	public String getToolTip() {
		// TODO Auto-generated method stub
		return toolTipId;
	}

	/* (non-Javadoc)
	 * @see com.exadel.siperian.model.TimeLineEventDataModel#isSelected()
	 */
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return selected;
	}

}
