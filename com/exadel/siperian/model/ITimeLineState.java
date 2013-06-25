/**
 * 
 */


package com.exadel.siperian.model;


import java.io.Serializable;
import java.util.List;


/**
 * The Interface ITimelineState.
 * 
 * @author Evgenij Stherbin
 */
public interface ITimeLineState extends Serializable {

    /**
     * Gets the visible interval from position.
     * 
     * @return the visible interval from position
     */
    Long getVisibleIntervalFromPosition();

    /**
     * Sets the visible interval from position.
     * 
     * @param interval
     *            the interval
     */
    void setVisibleIntervalFromPosition(Long interval);

    /**
     * Gets the visible interval to position.
     * 
     * @return the visible interval to position
     */
    Long getVisibleIntervalToPosition();

    /**
     * Sets the visible interval to postion.
     * 
     * @param interval
     *            the interval
     */
    void setVisibleIntervalToPosition(Long interval);

    /**
     * Gets the vertical ruler position.
     * 
     * @return the vertical ruler position
     */
    Long getVerticalRulerPosition();

    /**
     * Sets the vertical ruler position.
     * 
     * @param interval
     *            the interval
     */
    void setVerticalRulerPosition(Long interval);

    /**
     * Gets the hidden event types.
     * 
     * @return the hidden event types
     */
    List<String> getHiddenEventTypes();

    /**
     * Sets the hidden event types.
     * 
     * @param eventTypes
     *            the event types
     */
    void setHiddenEventTypes(List<String> eventTypes);
}
