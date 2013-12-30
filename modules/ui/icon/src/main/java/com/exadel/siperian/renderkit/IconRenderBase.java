/**
 * 
 */
package com.exadel.siperian.renderkit;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;

import com.exadel.siperian.component.UIIcon;


/**
 * Icon component renderer base
 * 
 * @author Alexandr Levkovsky
 *
 */
public class IconRenderBase extends HeaderResourcesRendererBase {
	
	//TODO nick - can be rewritten in more effecient way using enums
	
	private final static String SINGLE_LEFT_ICON = "com.exadel.siperian.renderkit.html.images.SingleLeftIcon.png"; 
	private final static String SINGLE_RIGHT_ICON = "com.exadel.siperian.renderkit.html.images.SingleRightIcon.png";
	private final static String SINGLE_UP_ICON = "com.exadel.siperian.renderkit.html.images.SingleUpIcon.png";
	private final static String SINGLE_DOWN_ICON = "com.exadel.siperian.renderkit.html.images.SingleDownIcon.png";
	
	private final static String SINGLE_LEFT_DISABLED_ICON = "com.exadel.siperian.renderkit.html.images.SingleLeftDisabledIcon.png"; 
	private final static String SINGLE_RIGHT_DISABLED_ICON = "com.exadel.siperian.renderkit.html.images.SingleRightDisabledIcon.png";
	private final static String SINGLE_UP_DISABLED_ICON = "com.exadel.siperian.renderkit.html.images.SingleUpDisabledIcon.png";
	private final static String SINGLE_DOWN_DISABLED_ICON = "com.exadel.siperian.renderkit.html.images.SingleDownDisabledIcon.png";
	
	private final static String DOUBLE_LEFT_ICON = "com.exadel.siperian.renderkit.html.images.DoubleLeftIcon.png"; 
	private final static String DOUBLE_RIGHT_ICON = "com.exadel.siperian.renderkit.html.images.DoubleRightIcon.png";
	private final static String DOUBLE_UP_ICON = "com.exadel.siperian.renderkit.html.images.DoubleUpIcon.png";
	private final static String DOUBLE_DOWN_ICON = "com.exadel.siperian.renderkit.html.images.DoubleDownIcon.png";
	
	private final static String DOUBLE_LEFT_DISABLED_ICON = "com.exadel.siperian.renderkit.html.images.DoubleLeftDisabledIcon.png"; 
	private final static String DOUBLE_RIGHT_DISABLED_ICON = "com.exadel.siperian.renderkit.html.images.DoubleRightDisabledIcon.png";
	private final static String DOUBLE_UP_DISABLED_ICON = "com.exadel.siperian.renderkit.html.images.DoubleUpDisabledIcon.png";
	private final static String DOUBLE_DOWN_DISABLED_ICON = "com.exadel.siperian.renderkit.html.images.DoubleDownDisabledIcon.png";
	
	
	private final static String LEFT_DIRECTION = "left";
	private final static String RIGHT_DIRECTION = "right";
	private final static String UP_DIRECTION = "up";
	private final static String DOWN_DIRECTION = "down";
	private final static String SINGLE_TYPE = "single";
	private final static String DOUBLE_TYPE = "double";

	
    /**
     * @see org.ajax4jsf.renderkit.RendererBase#getComponentClass()
     */
    @Override
    protected Class<? extends UIComponent> getComponentClass() {
        return UIIcon.class;
    }
    
    public String getIconURI(FacesContext context, UIIcon component) {
		String result = null;
		
		Boolean disabled = component.isDisabled();
		String direction = component.getDirection();
		String type = component.getType();
		
		if(!disabled){
			if(direction != null && direction.equalsIgnoreCase(LEFT_DIRECTION)){
				if(type != null && type.equalsIgnoreCase(SINGLE_TYPE)){
					result = getResource(SINGLE_LEFT_ICON).getUri(context, component);
				}else if(type != null && type.equalsIgnoreCase(DOUBLE_TYPE)){
					result = getResource(DOUBLE_LEFT_ICON).getUri(context, component);
				}
			} else if(direction != null && direction.equalsIgnoreCase(RIGHT_DIRECTION)){
				if(type != null && type.equalsIgnoreCase(SINGLE_TYPE)){
					result = getResource(SINGLE_RIGHT_ICON).getUri(context, component);
				}else if(type != null && type.equalsIgnoreCase(DOUBLE_TYPE)){
					result = getResource(DOUBLE_RIGHT_ICON).getUri(context, component);
				}
			} else if(direction != null && direction.equalsIgnoreCase(UP_DIRECTION)){
				if(type != null && type.equalsIgnoreCase(SINGLE_TYPE)){
					result = getResource(SINGLE_UP_ICON).getUri(context, component);
				}else if(type != null && type.equalsIgnoreCase(DOUBLE_TYPE)){
					result = getResource(DOUBLE_UP_ICON).getUri(context, component);
				}
			} else if(direction != null && direction.equalsIgnoreCase(DOWN_DIRECTION)){
				if(type != null && type.equalsIgnoreCase(SINGLE_TYPE)){
					result = getResource(SINGLE_DOWN_ICON).getUri(context, component);
				}else if(type != null && type.equalsIgnoreCase(DOUBLE_TYPE)){
					result = getResource(DOUBLE_DOWN_ICON).getUri(context, component);
				}
			} 
		} else {
			if(direction != null && direction.equalsIgnoreCase(LEFT_DIRECTION)){
				if(type != null && type.equalsIgnoreCase(SINGLE_TYPE)){
					result = getResource(SINGLE_LEFT_DISABLED_ICON).getUri(context, component);
				}else if(type != null && type.equalsIgnoreCase(DOUBLE_TYPE)){
					result = getResource(DOUBLE_LEFT_DISABLED_ICON).getUri(context, component);
				}
			} else if(direction != null && direction.equalsIgnoreCase(RIGHT_DIRECTION)){
				if(type != null && type.equalsIgnoreCase(SINGLE_TYPE)){
					result = getResource(SINGLE_RIGHT_DISABLED_ICON).getUri(context, component);
				}else if(type != null && type.equalsIgnoreCase(DOUBLE_TYPE)){
					result = getResource(DOUBLE_RIGHT_DISABLED_ICON).getUri(context, component);
				}
			} else if(direction != null && direction.equalsIgnoreCase(UP_DIRECTION)){
				if(type != null && type.equalsIgnoreCase(SINGLE_TYPE)){
					result = getResource(SINGLE_UP_DISABLED_ICON).getUri(context, component);
				}else if(type != null && type.equalsIgnoreCase(DOUBLE_TYPE)){
					result = getResource(DOUBLE_UP_DISABLED_ICON).getUri(context, component);
				}
			} else if(direction != null && direction.equalsIgnoreCase(DOWN_DIRECTION)){
				if(type != null && type.equalsIgnoreCase(SINGLE_TYPE)){
					result = getResource(SINGLE_DOWN_DISABLED_ICON).getUri(context, component);
				}else if(type != null && type.equalsIgnoreCase(DOUBLE_TYPE)){
					result = getResource(DOUBLE_DOWN_DISABLED_ICON).getUri(context, component);
				}
			} 
		}
		
		if(result == null){
			throw new FacesException("Please specify 'direction' and 'type' attributes for Icon component.");
		}
		
		return result;
	}

}
