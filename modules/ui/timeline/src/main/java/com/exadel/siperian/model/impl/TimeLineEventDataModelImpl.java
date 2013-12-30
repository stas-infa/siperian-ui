/**
 * 
 */


package com.exadel.siperian.model.impl;


import java.io.Serializable;
import java.util.Date;

import com.exadel.siperian.model.TimeLineEventDataModel;


/**
 * The Class TimeLineEventDataModelImpl.
 * 
 * @author Evgenij Stherbin
 */
public class TimeLineEventDataModelImpl implements TimeLineEventDataModel, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4595986354297859199L;

    /** The event date. */
    Date eventDate;

    /** The event id. */
    String eventId;

    /** The event type. */
    String eventType;

    /** The is selected. */
    Boolean isSelected;

    /** The swim lane id. */
    String swimLaneId;

    /** The tool tip. */
    String toolTip;

    /**
     * The Constructor.
     * 
     * @param toolTip the tool tip
     * @param eventId the event id
     * @param isSelected the is selected
     * @param eventDate the event date
     * @param eventType the event type
     * @param swimLaneId the swim lane id
     */
    public TimeLineEventDataModelImpl(Date eventDate, String eventId, String eventType, String swimLaneId, String toolTip,
            Boolean isSelected) {
        super();
        this.eventDate = eventDate;
        this.eventId = eventId;
        this.eventType = eventType;
        this.swimLaneId = swimLaneId;
        this.toolTip = toolTip;
        this.isSelected = isSelected;
    }

    /**
     * Gets the event date.
     * 
     * @return the event date
     * 
     * @see com.exadel.siperian.model.TimeLineEventDataModel#getEventDate()
     */
    public Date getEventDate() {
        // TODO Auto-generated method stub
        return eventDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.exadel.siperian.model.TimeLineEventDataModel#getEventId()
     */
    /**
     * Gets the event id.
     * 
     * @return the event id
     */
    public String getEventId() {
        // TODO Auto-generated method stub
        return eventId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.exadel.siperian.model.TimeLineEventDataModel#getEventType()
     */
    /**
     * Gets the event type.
     * 
     * @return the event type
     */
    public String getEventType() {
        // TODO Auto-generated method stub
        return eventType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.exadel.siperian.model.TimeLineEventDataModel#getSwimLaneId()
     */
    /**
     * Gets the swim lane id.
     * 
     * @return the swim lane id
     */
    public String getSwimLaneId() {
        // TODO Auto-generated method stub
        return swimLaneId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.exadel.siperian.model.TimeLineEventDataModel#getToolTip()
     */
    /**
     * Gets the tool tip.
     * 
     * @return the tool tip
     */
    public String getToolTip() {
        // TODO Auto-generated method stub
        return toolTip;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.exadel.siperian.model.TimeLineEventDataModel#isSelected()
     */
    /**
     * Checks if is selected.
     * 
     * @return true, if is selected
     */
    public boolean isSelected() {
        // TODO Auto-generated method stub
        return isSelected;
    }

    /**
     * Sets the event date.
     * 
     * @param eventDate the event date
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Sets the event id.
     * 
     * @param eventId the event id
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * Sets the event type.
     * 
     * @param eventType the event type
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * Sets the is selected.
     * 
     * @param isSelected the is selected
     */
    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * Sets the swim lane id.
     * 
     * @param swimLineId the swim line id
     */
    public void setSwimLaneId(String swimLineId) {
        this.swimLaneId = swimLineId;
    }

    /**
     * Sets the tool tip.
     * 
     * @param toolTip the tool tip
     */
    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

}
