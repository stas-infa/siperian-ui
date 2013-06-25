package com.exadel.siperian.component.html;

import com.exadel.siperian.component.UIDropDownMenu;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

public class HtmlDropDownMenu extends UIDropDownMenu{

public final static  String COMPONENT_FAMILY = "com.exadel.siperian.DropDownMenu";

public final static  String COMPONENT_TYPE = "com.exadel.siperian.DropDownMenu";

/*
* Defines direction of the popup list to appear. 
                Possible values are "top-right", "top-right", "top-left", "bottom-right", "bottom-left", "auto".
                Default value is "auto".
*/
private  String _direction = null;

/*
* Attribute 'disabled' provides possibility to make the whole menu disabled if its value equals to "true". Default value is "false"
*/
private  boolean _disabled = false;

private  boolean _disabledSet = false;

/*
* Space-separated list of CSS style class(es) that are be applied to disabled item of this component
*/
private  String _disabledItemClass = null;

/*
* CSS style(s) is/are to be applied to disabled item when this component is rendered.
*/
private  String _disabledItemStyle = null;

/*
* Space-separated list of CSS style class(es) applied to dropdown menu label when it is disabled.
*/
private  String _disabledLabelClass = null;

/*
* Defines the event on the representation element that triggers
                the menu's appearance.
*/
private  String _event = null;

/*
* Delay between losing focus and menu closing. Default value is "800".
*/
private  Integer _hideDelay = null;

/*
* Sets the horizontal offset between popup list and label element. Default value is "0".
                conjunction point
*/
private  int _horizontalOffset = 0;

private  boolean _horizontalOffsetSet = false;

/*
* Space-separated list of CSS style class(es) that are be applied to item of this component
*/
private  String _itemClass = null;

/*
* CSS style(s) is/are to be applied to item when this component is rendered.
*/
private  String _itemStyle = null;

/*
* Sets the corner of the label for the pop-up to be connected with.
                Possible values are "tr", "tl", "bl", "br", "bottom-left", "auto".
                Default value is "auto".
                "tr" stands for top-right.
*/
private  String _jointPoint = null;

/*
* Space-separated list of CSS style class(es) applied to dropdown menu label in it normal (neither selected nor disabled) sate.
*/
private  String _labelClass = null;

/*
* HTML: script expression; a menu is collapsed.
*/
private  String _oncollapse = null;

/*
* HTML: script expression; a menu is expanded.
*/
private  String _onexpand = null;

/*
* HTML: script expression; some group was activated.
*/
private  String _ongroupactivate = null;

/*
* HTML: script expression; some item was selected.
*/
private  String _onitemselect = null;

/*
* HTML: script expression; a pointer was moved within.
*/
private  String _onmousemove = null;

/*
* HTML: script expression; a pointer was moved away.
*/
private  String _onmouseout = null;

/*
* HTML: script expression; a pointer was moved onto.
*/
private  String _onmouseover = null;

/*
* Sets minimal width for  all  lists that will appear.
*/
private  String _popupWidth = null;

/*
* Space-separated list of CSS style class(es) that are be applied to selected item of this component.
*/
private  String _selectItemClass = null;

/*
* CSS style(s) is/are to be applied to selected item when this component is rendered.
*/
private  String _selectItemStyle = null;

/*
* Space-separated list of CSS style class(es) applied to dropdown menu label when it is selected.
*/
private  String _selectedLabelClass = null;

/*
* Delay between event and menu showing. Default value is "50".
*/
private  Integer _showDelay = null;

/*
* CSS style(s) is/are to be applied when this component is rendered
*/
private  String _style = null;

/*
* Corresponds to the HTML class attribute
*/
private  String _styleClass = null;

/*
* Sets the submission mode for all menu items of the menu except
                ones where this attribute redefined.
                Possible values are "ajax","server","none". Default value is "sever".
*/
private  String _submitMode = null;

/*
* Defines representation text for Label used for menu calls.
*/
private  Object _value = null;

/*
* Sets the vertical offset between popup list and label element. Default value is "0".
                conjunction point
*/
private  int _verticalOffset = 0;

private  boolean _verticalOffsetSet = false;


public HtmlDropDownMenu(){
setRendererType("com.exadel.siperian.DropDownMenuRenderer");
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

    return "auto";
	

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

public String getDisabledItemClass(){
	if (this._disabledItemClass != null) {
		return this._disabledItemClass;
	}
	ValueExpression ve = getValueExpression("disabledItemClass");
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

public void setDisabledItemClass(String _disabledItemClass){
this._disabledItemClass = _disabledItemClass;
}

public String getDisabledItemStyle(){
	if (this._disabledItemStyle != null) {
		return this._disabledItemStyle;
	}
	ValueExpression ve = getValueExpression("disabledItemStyle");
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

public void setDisabledItemStyle(String _disabledItemStyle){
this._disabledItemStyle = _disabledItemStyle;
}

public String getDisabledLabelClass(){
	if (this._disabledLabelClass != null) {
		return this._disabledLabelClass;
	}
	ValueExpression ve = getValueExpression("disabledLabelClass");
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

public void setDisabledLabelClass(String _disabledLabelClass){
this._disabledLabelClass = _disabledLabelClass;
}

public String getEvent(){
	if (this._event != null) {
		return this._event;
	}
	ValueExpression ve = getValueExpression("event");
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

public void setEvent(String _event){
this._event = _event;
}

public Integer getHideDelay(){
	if (this._hideDelay != null) {
		return this._hideDelay;
	}
	ValueExpression ve = getValueExpression("hideDelay");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return new Integer(800);
	

}

public void setHideDelay(Integer _hideDelay){
this._hideDelay = _hideDelay;
}

public int getHorizontalOffset(){
	if (this._horizontalOffsetSet) {
	    return (this._horizontalOffset);
	}
	ValueExpression ve = getValueExpression("horizontalOffset");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._horizontalOffset);
	    }
	    
	    return value;
	} else {
	    return (this._horizontalOffset);
	}

}

public void setHorizontalOffset(int _horizontalOffset){
this._horizontalOffset = _horizontalOffset;
this._horizontalOffsetSet = true;
}

public String getItemClass(){
	if (this._itemClass != null) {
		return this._itemClass;
	}
	ValueExpression ve = getValueExpression("itemClass");
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

public void setItemClass(String _itemClass){
this._itemClass = _itemClass;
}

public String getItemStyle(){
	if (this._itemStyle != null) {
		return this._itemStyle;
	}
	ValueExpression ve = getValueExpression("itemStyle");
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

public void setItemStyle(String _itemStyle){
this._itemStyle = _itemStyle;
}

public String getJointPoint(){
	if (this._jointPoint != null) {
		return this._jointPoint;
	}
	ValueExpression ve = getValueExpression("jointPoint");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "auto";
	

}

public void setJointPoint(String _jointPoint){
this._jointPoint = _jointPoint;
}

public String getLabelClass(){
	if (this._labelClass != null) {
		return this._labelClass;
	}
	ValueExpression ve = getValueExpression("labelClass");
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

public void setLabelClass(String _labelClass){
this._labelClass = _labelClass;
}

public String getOncollapse(){
	if (this._oncollapse != null) {
		return this._oncollapse;
	}
	ValueExpression ve = getValueExpression("oncollapse");
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

public void setOncollapse(String _oncollapse){
this._oncollapse = _oncollapse;
}

public String getOnexpand(){
	if (this._onexpand != null) {
		return this._onexpand;
	}
	ValueExpression ve = getValueExpression("onexpand");
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

public void setOnexpand(String _onexpand){
this._onexpand = _onexpand;
}

public String getOngroupactivate(){
	if (this._ongroupactivate != null) {
		return this._ongroupactivate;
	}
	ValueExpression ve = getValueExpression("ongroupactivate");
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

public void setOngroupactivate(String _ongroupactivate){
this._ongroupactivate = _ongroupactivate;
}

public String getOnitemselect(){
	if (this._onitemselect != null) {
		return this._onitemselect;
	}
	ValueExpression ve = getValueExpression("onitemselect");
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

public void setOnitemselect(String _onitemselect){
this._onitemselect = _onitemselect;
}

public String getOnmousemove(){
	if (this._onmousemove != null) {
		return this._onmousemove;
	}
	ValueExpression ve = getValueExpression("onmousemove");
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

public void setOnmousemove(String _onmousemove){
this._onmousemove = _onmousemove;
}

public String getOnmouseout(){
	if (this._onmouseout != null) {
		return this._onmouseout;
	}
	ValueExpression ve = getValueExpression("onmouseout");
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

public void setOnmouseout(String _onmouseout){
this._onmouseout = _onmouseout;
}

public String getOnmouseover(){
	if (this._onmouseover != null) {
		return this._onmouseover;
	}
	ValueExpression ve = getValueExpression("onmouseover");
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

public void setOnmouseover(String _onmouseover){
this._onmouseover = _onmouseover;
}

public String getPopupWidth(){
	if (this._popupWidth != null) {
		return this._popupWidth;
	}
	ValueExpression ve = getValueExpression("popupWidth");
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

public void setPopupWidth(String _popupWidth){
this._popupWidth = _popupWidth;
}

public String getSelectItemClass(){
	if (this._selectItemClass != null) {
		return this._selectItemClass;
	}
	ValueExpression ve = getValueExpression("selectItemClass");
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

public void setSelectItemClass(String _selectItemClass){
this._selectItemClass = _selectItemClass;
}

public String getSelectItemStyle(){
	if (this._selectItemStyle != null) {
		return this._selectItemStyle;
	}
	ValueExpression ve = getValueExpression("selectItemStyle");
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

public void setSelectItemStyle(String _selectItemStyle){
this._selectItemStyle = _selectItemStyle;
}

public String getSelectedLabelClass(){
	if (this._selectedLabelClass != null) {
		return this._selectedLabelClass;
	}
	ValueExpression ve = getValueExpression("selectedLabelClass");
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

public void setSelectedLabelClass(String _selectedLabelClass){
this._selectedLabelClass = _selectedLabelClass;
}

public Integer getShowDelay(){
	if (this._showDelay != null) {
		return this._showDelay;
	}
	ValueExpression ve = getValueExpression("showDelay");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return new Integer(50);
	

}

public void setShowDelay(Integer _showDelay){
this._showDelay = _showDelay;
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

    return null;
	

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

    return null;
	

}

public void setStyleClass(String _styleClass){
this._styleClass = _styleClass;
}

public String getSubmitMode(){
	if (this._submitMode != null) {
		return this._submitMode;
	}
	ValueExpression ve = getValueExpression("submitMode");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "server";
	

}

public void setSubmitMode(String _submitMode){
this._submitMode = _submitMode;
}

public Object getValue(){
	if (this._value != null) {
		return this._value;
	}
	ValueExpression ve = getValueExpression("value");
	if (ve != null) {
	    Object value = null;
	    
	    try {
			value = (Object) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setValue(Object _value){
this._value = _value;
}

public int getVerticalOffset(){
	if (this._verticalOffsetSet) {
	    return (this._verticalOffset);
	}
	ValueExpression ve = getValueExpression("verticalOffset");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._verticalOffset);
	    }
	    
	    return value;
	} else {
	    return (this._verticalOffset);
	}

}

public void setVerticalOffset(int _verticalOffset){
this._verticalOffset = _verticalOffset;
this._verticalOffsetSet = true;
}

public String getFamily(){
return COMPONENT_FAMILY;
}

@Override
public Object saveState(FacesContext context){
Object [] state = new Object[33];
state[0] = super.saveState(context);
state[1] = _direction;
state[2] = Boolean.valueOf(_disabled);
state[3] = Boolean.valueOf(_disabledSet);
state[4] = _disabledItemClass;
state[5] = _disabledItemStyle;
state[6] = _disabledLabelClass;
state[7] = _event;
state[8] = _hideDelay;
state[9] = Integer.valueOf(_horizontalOffset);
state[10] = Boolean.valueOf(_horizontalOffsetSet);
state[11] = _itemClass;
state[12] = _itemStyle;
state[13] = _jointPoint;
state[14] = _labelClass;
state[15] = _oncollapse;
state[16] = _onexpand;
state[17] = _ongroupactivate;
state[18] = _onitemselect;
state[19] = _onmousemove;
state[20] = _onmouseout;
state[21] = _onmouseover;
state[22] = _popupWidth;
state[23] = _selectItemClass;
state[24] = _selectItemStyle;
state[25] = _selectedLabelClass;
state[26] = _showDelay;
state[27] = _style;
state[28] = _styleClass;
state[29] = _submitMode;
state[30] = saveAttachedState(context, _value);
state[31] = Integer.valueOf(_verticalOffset);
state[32] = Boolean.valueOf(_verticalOffsetSet);
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_direction = (String)states[1];;
		_disabled = ((Boolean)states[2]).booleanValue();
		_disabledSet = ((Boolean)states[3]).booleanValue();
		_disabledItemClass = (String)states[4];;
		_disabledItemStyle = (String)states[5];;
		_disabledLabelClass = (String)states[6];;
		_event = (String)states[7];;
		_hideDelay = (Integer)states[8];;
		_horizontalOffset = ((Integer)states[9]).intValue();
		_horizontalOffsetSet = ((Boolean)states[10]).booleanValue();
		_itemClass = (String)states[11];;
		_itemStyle = (String)states[12];;
		_jointPoint = (String)states[13];;
		_labelClass = (String)states[14];;
		_oncollapse = (String)states[15];;
		_onexpand = (String)states[16];;
		_ongroupactivate = (String)states[17];;
		_onitemselect = (String)states[18];;
		_onmousemove = (String)states[19];;
		_onmouseout = (String)states[20];;
		_onmouseover = (String)states[21];;
		_popupWidth = (String)states[22];;
		_selectItemClass = (String)states[23];;
		_selectItemStyle = (String)states[24];;
		_selectedLabelClass = (String)states[25];;
		_showDelay = (Integer)states[26];;
		_style = (String)states[27];;
		_styleClass = (String)states[28];;
		_submitMode = (String)states[29];;
		_value = (Object)restoreAttachedState(context, states[30]);
		_verticalOffset = ((Integer)states[31]).intValue();
		_verticalOffsetSet = ((Boolean)states[32]).booleanValue();
	
}

}
