/**
 *
 */

package com.exadel.siperian.component;

import javax.faces.component.UIComponentBase;

/**
 * JSF component class
 *
 */
public abstract class UIPanel extends UIComponentBase {
	
	//TODO nick - so is it com.siperian.richfaces or com.exadel.siperian?
	public static final String COMPONENT_TYPE = "com.siperian.richfaces.Panel";
	
	public static final String COMPONENT_FAMILY = "com.siperian.richfaces.Panel";
    public boolean getRendersChildren() {
        return true;
    }
  
  public abstract String getHeader();
  public abstract void setHeader(String header);
}
