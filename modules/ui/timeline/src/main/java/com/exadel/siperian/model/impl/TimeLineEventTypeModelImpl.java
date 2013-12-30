/**
 * 
 */


package com.exadel.siperian.model.impl;


import com.exadel.siperian.model.TimeLineEventTypeModel;


/**
 * The Class TimeLineEventTypeModelImpl.
 * 
 * @author Evgenij Stherbin
 */
public class TimeLineEventTypeModelImpl implements TimeLineEventTypeModel {


    /** The event type id. */
    String eventTypeId;

    /** The event type name. */
    String eventTypeName;

    /** The event type style class. */
    String eventTypeStyleClass;

    /** The hover style class. */
    private String hoverStyleClass;

    /** The selected style class. */
    private String selectedStyleClass;

    /**
     * The Constructor.
     * 
     * @param eventTypeId the event type id
     * @param eventTypeStyleClass the event type style class
     * @param eventTypeName the event type name
     */
    public TimeLineEventTypeModelImpl(String eventTypeId, String eventTypeName, String eventTypeStyleClass) {
        super();
        this.eventTypeId = eventTypeId;
        this.eventTypeName = eventTypeName;
        this.eventTypeStyleClass = eventTypeStyleClass;
        this.hoverStyleClass = this.eventTypeStyleClass+"hover";
        this.selectedStyleClass = this.eventTypeStyleClass+"selected";
    }

    /**
     * Sets the event type id.
     * 
     * @param eventTypeId the event type id
     */
    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    /**
     * Sets the event type name.
     * 
     * @param eventTypeName the event type name
     */
    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    /**
     * Sets the event type style class.
     * 
     * @param eventTypeStyleClass the event type style class
     */
    public void setEventTypeStyleClass(String eventTypeStyleClass) {
        this.eventTypeStyleClass = eventTypeStyleClass;
    }

    /**
     * Gets the event type id.
     * 
     * @return the event type id
     * 
     * @see com.exadel.siperian.model.TimeLineEventTypeModel#getEventTypeId()
     */
    public String getEventTypeId() {
        return eventTypeId;
    }

    /**
     * Gets the event type name.
     * 
     * @return the event type name
     * 
     * @see com.exadel.siperian.model.TimeLineEventTypeModel#getEventTypeName()
     */
    public String getEventTypeName() {
        return eventTypeName;
    }

    /**
     * Gets the event type style class.
     * 
     * @return the event type style class
     * 
     * @see com.exadel.siperian.model.TimeLineEventTypeModel#getEventTypeStyleClass()
     */
    public String getEventTypeStyleClass() {
        return eventTypeStyleClass;
    }

    /**
     * Gets the hover style class.
     * 
     * @return the hover style class
     */
    public String getHoverStyleClass() {
        return this.hoverStyleClass;
    }

    /**
     * Gets the selected style class.
     * 
     * @return the selected style class
     */
    public String getSelectedStyleClass() {

        return this.selectedStyleClass;
    }

    /**
     * Sets the hover style class.
     * 
     * @param hoverStyleClass the hover style class
     */
    public void setHoverStyleClass(String hoverStyleClass) {
        this.hoverStyleClass = hoverStyleClass;
    }

    /**
     * Sets the selected style class.
     * 
     * @param selectedStyleClass the selected style class
     */
    public void setSelectedStyleClass(String selectedStyleClass) {
        this.selectedStyleClass = selectedStyleClass;
    }

}
