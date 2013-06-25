/**
 *
 */

package com.exadel.siperian.component;

import javax.faces.component.UIComponentBase;

/**
 * Icon component class
 * @author Alexandr Levkovsky
 *
 */
public abstract class UIIcon extends UIComponentBase {
	
	//TODO nick - so is it com.siperian.richfaces or com.exadel.siperian?
	public static final String COMPONENT_TYPE = "com.exadel.siperian.Icon";	
	public static final String COMPONENT_FAMILY = "com.exadel.siperian.Icon";

	public abstract String getDirection();

    public abstract void setDirection(String direction);
    
	public abstract String getType();

    public abstract void setType(String type);
    
    public abstract boolean isDisabled();

    public abstract void setDisabled(boolean disabled);
}
