

package com.exadel.siperian.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Base interface for time line data implementation.
 * 
 * @author Andrey Markavtsov,Eugene Stherbin
 */
public interface TimeLineDataModel extends Serializable {

    /**
     * Returns array of time line events according to time interval.
     * 
     * @param toDate -
     * end date interval
     * @param fromDate -
     * start date interval
     * 
     * @return array of TimeLineEventDataModel
     */
    public TimeLineEventDataModel[] getData(Date fromDate, Date toDate);

    /**
     * Returns array of swim lanes.
     * 
     * @return array of TimeLineSwimLaneModel
     */
    public TimeLineSwimLaneModel[] getSwimLanes();

    /**
     * Returns array of event type.
     * 
     * @return an array of event type.
     */
    public TimeLineEventTypeModel[] getEventTypes();

    /**
     * Gets the styles defintion.
     * 
     * @return the styles defintion
     * 
     * @deprecated Pass style via sip:timeline component attribute instread.
     */
    public String[] getStylesDefintion();

    /**
     * Sets the clicked event ids.
     * 
     * @param eventIds the event ids
     */
    public void setClickedEventIds(List<String> eventIds);

    /**
     * Gets the clicked event ids.
     * 
     * @return the clicked event ids
     */
    public List<String> getClickedEventIds();

    /**
     * Sets the clicked event date.
     * 
     * @param date the date
     */
    public void setClickedEventDate(Date date);

    /**
     * Gets the clicked event date.
     * 
     * @return the clicked event date
     */
    public Date getClickedEventDate();
    
    
    /**
     * Sets the from date with indent.
     * 
     * @return the date
     */
    public void setFromDateWithIndent(Date x);
    
    /**
     * Sets the to date with indent.
     * 
     * @return the date
     */
    public void  setToDateWithIndent(Date x);
    
    
}
