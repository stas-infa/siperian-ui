/**
 * 
 */


package com.exadel.siperian.model.impl;


import java.util.ArrayList;
import java.util.List;

import com.exadel.siperian.model.ITimeLineState;


/**
 * The Class TimeLineStateImpl.
 * 
 * @author Evgenij Stherbin
 */
public class TimeLineStateImpl implements ITimeLineState {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2747788152077538917L;

    /** The visible interval from position. */
    private Long visibleIntervalFromPosition;

    /** The visible interval to position. */
    private Long visibleIntervalToPosition;

    /** The vertical ruler position. */
    private Long verticalRulerPosition;
    
    /** The hidden event types. */
    private List<String> hiddenEventTypes = new ArrayList<String>();

    /**
     * Gets the visible interval from position.
     * 
     * @return the visible interval from position
     */
    public Long getVisibleIntervalFromPosition() {
        return visibleIntervalFromPosition;
    }

    /**
     * Sets the visible interval from position.
     * 
     * @param visibleIntervalFromPosition the visible interval from position
     */
    public void setVisibleIntervalFromPosition(Long visibleIntervalFromPosition) {
        this.visibleIntervalFromPosition = visibleIntervalFromPosition;
    }

    /**
     * Gets the visible interval to position.
     * 
     * @return the visible interval to position
     */
    public Long getVisibleIntervalToPosition() {
        return visibleIntervalToPosition;
    }

    /**
     * Sets the visible interval to position.
     * 
     * @param visibleIntervalToPosition the visible interval to position
     */
    public void setVisibleIntervalToPosition(Long visibleIntervalToPosition) {
        this.visibleIntervalToPosition = visibleIntervalToPosition;
    }

    /**
     * Gets the vertical ruler position.
     * 
     * @return the vertical ruler position
     */
    public Long getVerticalRulerPosition() {
        return verticalRulerPosition;
    }

    /**
     * Sets the vertical ruler position.
     * 
     * @param verticalRulerPosition the vertical ruler position
     */
    public void setVerticalRulerPosition(Long verticalRulerPosition) {
        this.verticalRulerPosition = verticalRulerPosition;
    }

  
    /**
     * Gets the hidden event types.
     * 
     * @return the hidden event types
     */
    public List<String> getHiddenEventTypes() {
      
        return this.hiddenEventTypes;
    }

    /**
     * Sets the hidden event types.
     * 
     * @param eventTypes the event types
     */
    public void setHiddenEventTypes(List<String> eventTypes) {
        this.hiddenEventTypes = eventTypes;
        
    }

}
