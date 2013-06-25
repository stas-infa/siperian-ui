/**
 * 
 */


package com.exadel.siperian.servlet;


import com.exadel.siperian.model.TimeLineDataModel;


/**
 * Provide time line data model.
 * 
 * @author Evgenij Stherbin
 */
public interface ITimeLineDataModelProvider {

    /**
     * Gets the time line data model.
     * 
     * @param params the params
     * 
     * @return the time line data model
     */
    TimeLineDataModel getTimeLineDataModel(Object... params);
}
