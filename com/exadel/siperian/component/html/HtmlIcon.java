package com.exadel.siperian.component.html;

import com.exadel.siperian.component.UIIcon;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

public class HtmlIcon extends UIIcon{

public final static  String COMPONENT_FAMILY = "com.siperian.Icon";

public final static  String COMPONENT_TYPE = "com.siperian.Icon";

/*
* direction
*/
private  String _direction = null;

/*
* disabled
*/
private  boolean _disabled = false;

private  boolean _disabledSet = false;

/*
* CSS style(s) is/are to be applied when this component is rendered
*/
private  String _style = null;

/*
* Corresponds to the HTML class attribute
*/
private  String _styleClass = null;

/*
* type
*/
private  String _type = null;


public HtmlIcon(){
setRendererType("com.exadel.siperian.IconRenderer");
}

public String getDirection(){
	if (this._direction != null) {
		return this._direction;
	}
	ValueExpression ve = getValueExpression("direction");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setDirection(String _direction){
this._direction = _direction;
}

public boolean isDisabled(){
	if (this._disabledSet) {
	    return (this._disabled);
	}
	ValueExpression ve = getValueExpression("disabled");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._disabled);
	    }
	    
	    return value;
	} else {
	    return (this._disabled);
	}

}

public void setDisabled(boolean _disabled){
this._disabled = _disabled;
this._disabledSet = true;
}

public String getStyle(){
	if (this._style != null) {
		return this._style;
	}
	ValueExpression ve = getValueExpression("style");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "";
	

}

public void setStyle(String _style){
this._style = _style;
}

public String getStyleClass(){
	if (this._styleClass != null) {
		return this._styleClass;
	}
	ValueExpression ve = getValueExpression("styleClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "";
	

}

public void setStyleClass(String _styleClass){
this._styleClass = _styleClass;
}

public String getType(){
	if (this._type != null) {
		return this._type;
	}
	ValueExpression ve = getValueExpression("type");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setType(String _type){
this._type = _type;
}

public String getFamily(){
return COMPONENT_FAMILY;
}

@Override
public Object saveState(FacesContext context){
Object [] state = new Object[7];
state[0] = super.saveState(context);
state[1] = _direction;
state[2] = Boolean.valueOf(_disabled);
state[3] = Boolean.valueOf(_disabledSet);
state[4] = _style;
state[5] = _styleClass;
state[6] = _type;
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_direction = (String)states[1];;
		_disabled = ((Boolean)states[2]).booleanValue();
		_disabledSet = ((Boolean)states[3]).booleanValue();
		_style = (String)states[4];;
		_styleClass = (String)states[5];;
		_type = (String)states[6];;
	
}

}
