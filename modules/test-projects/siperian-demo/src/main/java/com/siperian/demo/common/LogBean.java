/**
 * 
 */
package com.siperian.demo.common;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

/**
 * @author Evgenij Stherbin
 *
 */
public class LogBean implements Serializable {

    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    boolean isShowLog = false;
    
    
    public boolean getShowLog() {
        return isShowLog;
    }


    public void setShowLog(boolean isShowLog) {
        this.isShowLog = isShowLog;
    }


    public void actionShow(ActionEvent event){
        this.isShowLog = (isShowLog ? false : true);
    }
    
    

}
