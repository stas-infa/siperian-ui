/**
 * 
 */


package com.exadel.siperian.test;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.exadel.siperian.model.ITimeLineState;
import com.exadel.siperian.model.TimeLineDataModel;
import com.exadel.siperian.model.TimeLineEventDataModel;
import com.exadel.siperian.model.TimeLineEventTypeModel;
import com.exadel.siperian.model.TimeLineSwimLaneModel;
import com.exadel.siperian.model.impl.TimeLineStateImpl;


/**
 * The Class TestSimpleTimeLineDataModel.
 * 
 * @author Evgenij Stherbin
 */
public class SimpleTimeLineDataModel implements TimeLineDataModel, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2376686616962251395L;

    /** The event data model. */
    protected List<TimeLineEventDataModel> eventDataModel = new ArrayList<TimeLineEventDataModel>();

    /** The event type model. */
    protected List<TimeLineEventTypeModel> eventTypeModel = new ArrayList<TimeLineEventTypeModel>();

    /** The swim lines. */
    protected List<TimeLineSwimLaneModel> swimLines = new ArrayList<TimeLineSwimLaneModel>();

    /** The styles list. */
    protected List<String> stylesList = new ArrayList<String>();

    /** The last clicked. */
    protected List<String> lastClicked = new ArrayList<String>();

    /** The clicked event date. */
    protected Date clickedEventDate = null;
    
    /** The from date with indent. */
    protected Date fromDateWithIndent = null;
    
    /** The to date with indent. */
    protected Date toDateWithIndent = null;




    /**
     * Gets the from date with indent.
     * 
     * @return the from date with indent
     */
    public Date getFromDateWithIndent() {
        return fromDateWithIndent;
    }

    /**
     * Sets the from date with indent.
     * 
     * @param fromDateWithIndent the from date with indent
     */
    public void setFromDateWithIndent(Date fromDateWithIndent) {
        this.fromDateWithIndent = fromDateWithIndent;
    }

    /**
     * Gets the to date with indent.
     * 
     * @return the to date with indent
     */
    public Date getToDateWithIndent() {
        return toDateWithIndent;
    }

    /**
     * Sets the to date with indent.
     * 
     * @param toDateWithIndent the to date with indent
     */
    public void setToDateWithIndent(Date toDateWithIndent) {
        this.toDateWithIndent = toDateWithIndent;
    }

    /**
     * Gets the clicked event date.
     * 
     * @return the clicked event date
     */
    public Date getClickedEventDate() {
        return clickedEventDate;
    }

    /**
     * Adds the all time line swim lane model.
     * 
     * @param c the c
     * 
     * @return true, if add all time line swim lane model
     */
    public boolean addAllTimeLineSwimLaneModel(Collection<? extends TimeLineSwimLaneModel> c) {
        return swimLines.addAll(c);
    }

    /**
     * Adds the all time line event data model.
     * 
     * @param c the c
     * 
     * @return true, if add all time line event data model
     */
    public boolean addAllTimeLineEventDataModel(Collection<? extends TimeLineEventDataModel> c) {
        return eventDataModel.addAll(c);
    }

    /**
     * Adds the time line event data model.
     * 
     * @param element the element
     */
    public void addTimeLineEventDataModel(TimeLineEventDataModel element) {
        eventDataModel.add(element);
    }

    /**
     * Adds the time line event type model.
     * 
     * @param o the o
     * 
     * @return true, if add time line event type model
     */
    public boolean addTimeLineEventTypeModel(TimeLineEventTypeModel o) {
        return eventTypeModel.add(o);
    }

    /**
     * Adds the time line swim lane model.
     * 
     * @param o the o
     * 
     * @return true, if add time line swim lane model
     */
    public boolean addTimeLineSwimLaneModel(TimeLineSwimLaneModel o) {
        return swimLines.add(o);
    }

    /**
     * Adds the style.
     * 
     * @param style the style
     * 
     * @return true, if add style
     */
    public boolean addStyle(String style) {
        return this.stylesList.add(style);
    }

    /**
     * Gets the data.
     * 
     * @param toDate the to date
     * @param fromDate the from date
     * 
     * @return the data
     * 
     * @see com.exadel.siperian.model.TimeLineDataModel#getData(java.util.Date,
     * java.util.Date)
     */
    public TimeLineEventDataModel[] getData(Date fromDate, Date toDate) {

        return eventDataModel.toArray(new TimeLineEventDataModel[eventDataModel.size()]);
    }

    /**
     * Gets the event types.
     * 
     * @return the event types
     * 
     * @see com.exadel.siperian.model.TimeLineDataModel#getEventTypes()
     */
    public TimeLineEventTypeModel[] getEventTypes() {

        return eventTypeModel.toArray(new TimeLineEventTypeModel[eventTypeModel.size()]);
    }

    /**
     * Gets the swim lanes.
     * 
     * @return the swim lanes
     * 
     * @see com.exadel.siperian.model.TimeLineDataModel#getSwimLanes()
     */
    public TimeLineSwimLaneModel[] getSwimLanes() {

        return swimLines.toArray(new TimeLineSwimLaneModel[swimLines.size()]);
    }

    /**
     * Gets the styles defintion.
     * 
     * @return the styles defintion
     * 
     * @see com.exadel.siperian.model.TimeLineDataModel#getStylesDefintion()
     */
    public String[] getStylesDefintion() {
        // TODO Auto-generated method stub
        return this.stylesList.toArray(new String[this.stylesList.size()]);
    }

    /**
     * Sets the clicked event ids.
     * 
     * @param eventIds the event ids
     * 
     * @see com.exadel.siperian.model.TimeLineDataModel#setClickedEventIds(java.util.List)
     */
    public void setClickedEventIds(List<String> eventIds) {
        this.lastClicked = eventIds;
    }

    /**
     * Gets the clicked event ids.
     * 
     * @return the clicked event ids
     */
    public List<String> getClickedEventIds() {
        return this.lastClicked;
    }

    /**
     * Sets the clicked event date.
     * 
     * @param date the date
     * 
     * @see com.exadel.siperian.model.TimeLineDataModel#setClickedEventDate(java.util.Date)
     */
    public void setClickedEventDate(Date date) {
        this.clickedEventDate = date;
    }

    /* (non-Javadoc)
     * @see com.exadel.siperian.model.TimeLineDataModel#setFromDateWithIndent()
     */
    /**
     * Sets the from date with indent.
     * 
     * @return the date
     */
    public Date setFromDateWithIndent() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.exadel.siperian.model.TimeLineDataModel#setToDateWithIndent()
     */
    /**
     * Sets the to date with indent.
     * 
     * @return the date
     */
    public Date setToDateWithIndent() {
        // TODO Auto-generated method stub
        return null;
    }

}
