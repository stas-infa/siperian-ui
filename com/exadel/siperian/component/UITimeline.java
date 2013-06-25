/**
 *
 */

package com.exadel.siperian.component;

import javax.faces.component.UIComponentBase;

/**
 * JSF component class
 *
 */
public abstract class UITimeline extends UIComponentBase {
	
	public static final String COMPONENT_TYPE = "com.siperian.richfaces.Timeline";
	
	public static final String COMPONENT_FAMILY = "com.siperian.richfaces.Timeline";
    public boolean getRendersChildren() {
        return true;
    }
  
  public abstract String getHeader();
  public abstract void setHeader(String header);
}
