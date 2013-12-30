/**
 * 
 */


package com.exadel.siperian.model.impl;


import com.exadel.siperian.model.TimeLineSwimLaneModel;


/**
 * Defautl implementation for TimeLineSwimLaneModel.
 * 
 * @author Evgenij Stherbin
 */
public class TimeLineSwimLaneModelImpl implements TimeLineSwimLaneModel {

    /**
     * 
     */
    private static final long serialVersionUID = 0L;

    /** The swim lane id. */
    String swimLaneId;

    /** The swim lane name. */
    String swimLaneName;

    /** The swim lane style class. */
    String swimLaneStyleClass;

    /**
     * The Constructor.
     * 
     * @param swimLaneName the swim lane name
     * @param swimLaneId the swim lane id
     * @param swimLaneStyleClass the swim lane style class
     */
    public TimeLineSwimLaneModelImpl(String swimLaneId, String swimLaneName, String swimLaneStyleClass) {
        super();
        this.swimLaneId = swimLaneId;
        this.swimLaneName = swimLaneName;
        this.swimLaneStyleClass = swimLaneStyleClass;
    }

    /**
     * Sets the swim lane id.
     * 
     * @param swimLaneId the swim lane id
     */
    public void setSwimLaneId(String swimLaneId) {
        this.swimLaneId = swimLaneId;
    }

    /**
     * Sets the swim lane name.
     * 
     * @param swimLaneName the swim lane name
     */
    public void setSwimLaneName(String swimLaneName) {
        this.swimLaneName = swimLaneName;
    }

    /**
     * Sets the swim lane style class.
     * 
     * @param swimLaneStyleClass the swim lane style class
     */
    public void setSwimLaneStyleClass(String swimLaneStyleClass) {
        this.swimLaneStyleClass = swimLaneStyleClass;
    }

    /**
     * Gets the swim lane id.
     * 
     * @return the swim lane id
     * 
     * @see com.exadel.siperian.model.TimeLineSwimLaneModel#getSwimLaneId()
     */
    public String getSwimLaneId() {
        return swimLaneId;
    }

    /**
     * Gets the swim lane name.
     * 
     * @return the swim lane name
     * 
     * @see com.exadel.siperian.model.TimeLineSwimLaneModel#getSwimLaneName()
     */
    public String getSwimLaneName() {

        return swimLaneName;
    }

    /**
     * Gets the swim lane style class.
     * 
     * @return the swim lane style class
     * 
     * @see com.exadel.siperian.model.TimeLineSwimLaneModel#getSwimLaneStyleClass()
     */
    public String getSwimLaneStyleClass() {

        return swimLaneStyleClass;
    }

}
