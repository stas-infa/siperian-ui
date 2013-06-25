/**
 * 
 */


package com.exadel.siperian.model;

import java.io.Serializable;


/**
 * Contains of intervall data.
 * 
 * @author Evgenij Stherbin
 */
public class DateIntervalModel implements Serializable {
  
    private static final long serialVersionUID = 7688624829527966628L;

    /** The interval. */
    private String interval;

    /** The scale. */
    private String scale;

    /** The date pattern. */
    private String datePattern;

    /**
     * Gets the interval.
     * 
     * @return the interval
     */
    public String getInterval() {
        return interval;
    }

    /**
     * The Constructor.
     * 
     * @param datePattern the date pattern
     * @param scale the scale
     * @param interval the interval
     */
    DateIntervalModel(String interval, String scale, String datePattern) {
        super();
        this.interval = interval;
        this.scale = scale;
        this.datePattern = datePattern;
    }

    /**
     * Sets the interval.
     * 
     * @param interval the interval
     */
    public void setInterval(String interval) {
        this.interval = interval;
    }

    /**
     * Gets the scale.
     * 
     * @return the scale
     */
    public String getScale() {
        return scale;
    }

    /**
     * Sets the scale.
     * 
     * @param scale the scale
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * Gets the date pattern.
     * 
     * @return the date pattern
     */
    public String getDatePattern() {
        return datePattern;
    }

    /**
     * Sets the date pattern.
     * 
     * @param datePattern the date pattern
     */
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
    
    /**
     * Create.
     * 
     * @param datePattern the date pattern
     * @param scale the scale
     * @param interval the interval
     * 
     * @return the date interval model
     */
    public static DateIntervalModel create(String interval, String scale, String datePattern){
        return new DateIntervalModel(interval,scale,datePattern);
    }

}
