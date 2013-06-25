package com.exadel.siperian.component.html;

import com.exadel.siperian.component.SipUIComboBox;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

public class HtmlComboBox extends SipUIComboBox{

public final static  String COMPONENT_FAMILY = "org.richfaces.SipComboBox";

public final static  String COMPONENT_TYPE = "org.richfaces.SipComboBox";

/*
* 
*/
private  String _accesskey = null;

/*
* null
*/
private  String _align = null;

/*
* 
*/
private  String _alt = null;

/*
* Determine Sip combox behaviour
*/
private  String _behaviourStrategy = null;

/*
* Style Class attribute for the button
*/
private  String _buttonClass = null;

/*
* Style Class attribute for the disabled button
*/
private  String _buttonDisabledClass = null;

/*
* CSS style rules to be applied to disabled button
*/
private  String _buttonDisabledStyle = null;

/*
* Defines icon for the button element
*/
private  String _buttonIcon = null;

/*
* Defines disabled icon for the button element
*/
private  String _buttonIconDisabled = null;

/*
* Defines inactive icon for the button element
*/
private  String _buttonIconInactive = null;

/*
* Style Class attribute for the inactive button
*/
private  String _buttonInactiveClass = null;

/*
* CSS style rules to be applied to inactive button
*/
private  String _buttonInactiveStyle = null;

/*
* CSS style rules to be applied to button
*/
private  String _buttonStyle = null;

/*
* Defines default label for the input field element
*/
private  String _defaultLabel = null;

/*
* Defines the first value from the suggested in input field. Default value is "false".
*/
private  Boolean _directInputSuggestions = null;

/*
* When set for a form control, this boolean attribute disables the control for your input
*/
private  boolean _disabled = false;

private  boolean _disabledSet = false;

/*
* Enables  keyboard input, if "false" keyboard input will be locked. Default value is "true"
*/
private  boolean _enableManualInput = true;

private  boolean _enableManualInputSet = false;

/*
* Defines the appearance of values in the list. Default value is "true".
*/
private  Boolean _filterNewValues = null;

/*
* Delay between losing focus and pop-up list closing. Default value is "0".
*/
private  Integer _hideDelay = null;

/*
* Style Class attribute for the input field
*/
private  String _inputClass = null;

/*
* Style Class attribute for the disabled input
*/
private  String _inputDisabledClass = null;

/*
* CSS style rules to be applied to disabled input
*/
private  String _inputDisabledStyle = null;

/*
* Style Class attribute for the inactive input
*/
private  String _inputInactiveClass = null;

/*
* CSS style rules to be applied to inactive input
*/
private  String _inputInactiveStyle = null;

/*
* CSS style rules to be applied to input field
*/
private  String _inputStyle = null;

/*
* Style Class attribute for the items
*/
private  String _itemClass = null;

/*
* Style Class attribute for the selected item
*/
private  String _itemSelectedClass = null;

/*
* A localized user presentable name for this component.
*/
private  String _label = null;

/*
* Style Class attribute for the popup list
*/
private  String _listClass = null;

/*
* Defines height of file pop-up list. Default value is "200px".
*/
private  String _listHeight = null;

/*
* CSS style rules to be applied to popup list
*/
private  String _listStyle = null;

/*
* Defines width of file popup list
*/
private  String _listWidth = null;

/*
* null
*/
private  int _maxlength = Integer.MIN_VALUE;

private  boolean _maxlengthSet = false;

/*
* The client side script method to be called when the element loses the focus
*/
private  String _onblur = null;

/*
* The client side script method to be called when the element value is changed
*/
private  String _onchange = null;

/*
* The clientside script method to be called when the element is clicked
*/
private  String _onclick = null;

/*
* The client side script method to be called when the element is double-clicked
*/
private  String _ondblclick = null;

/*
* The client side script method to be called when the element gets the focus
*/
private  String _onfocus = null;

/*
* The client side script method to be called when a key is pressed down over the element
*/
private  String _onkeydown = null;

/*
* The client side script method to be called when a key is pressed over the element and released
*/
private  String _onkeypress = null;

/*
* The client side script method to be called when a key is released
*/
private  String _onkeyup = null;

/*
* HTML: script expression; a list is called
*/
private  String _onlistcall = null;

/*
* HTML: script expression; a list is closed
*/
private  String _onlistclose = null;

/*
* The client side script method to be called when a mouse button is pressed down over the element
*/
private  String _onmousedown = null;

/*
* The client side script method to be called when a pointer is moved within the element
*/
private  String _onmousemove = null;

/*
* The client side script method to be called when a pointer is moved away from the element
*/
private  String _onmouseout = null;

/*
* The client side script method to be called when a pointer is moved onto the element
*/
private  String _onmouseover = null;

/*
* The client side script method to be called when a mouse button is released
*/
private  String _onmouseup = null;

/*
* The client side script method to be called when some text is selected in the text field. This attribute can be used with the INPUT and TEXTAREA elements.
*/
private  String _onselect = null;

/*
* Defines if the first value from suggested is selected in pop-up list. Default value is "true".
*/
private  Boolean _selectFirstOnUpdate = null;

/*
* Delay between event and pop-up list showing. Default value is "0".
*/
private  Integer _showDelay = null;

/*
* 
*/
private  int _size = Integer.MIN_VALUE;

private  boolean _sizeSet = false;

/*
* CSS style(s) is/are to be applied when this component is rendered
*/
private  String _style = null;

/*
* Corresponds to the HTML class attribute
*/
private  String _styleClass = null;

/*
* Defines the suggestion collection
*/
private  Object _suggestionValues = null;

/*
* This attribute specifies the position of the current element in the tabbing order for the current document. This value must be a number between 0 and 32767. User agents should ignore leading zeros
*/
private  String _tabindex = null;

/*
* Width of the component. Default value is "150".
*/
private  String _width = null;


public HtmlComboBox(){
setRendererType("com.exadel.siperian.renderkit.SipComboBoxRenderer");
}

public String getAccesskey(){
	if (this._accesskey != null) {
		return this._accesskey;
	}
	ValueExpression ve = getValueExpression("accesskey");
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

public void setAccesskey(String _accesskey){
this._accesskey = _accesskey;
}

public String getAlign(){
	if (this._align != null) {
		return this._align;
	}
	ValueExpression ve = getValueExpression("align");
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

public void setAlign(String _align){
this._align = _align;
}

public String getAlt(){
	if (this._alt != null) {
		return this._alt;
	}
	ValueExpression ve = getValueExpression("alt");
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

public void setAlt(String _alt){
this._alt = _alt;
}

public String getBehaviourStrategy(){
	if (this._behaviourStrategy != null) {
		return this._behaviourStrategy;
	}
	ValueExpression ve = getValueExpression("behaviourStrategy");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "none";
	

}

public void setBehaviourStrategy(String _behaviourStrategy){
this._behaviourStrategy = _behaviourStrategy;
}

public String getButtonClass(){
	if (this._buttonClass != null) {
		return this._buttonClass;
	}
	ValueExpression ve = getValueExpression("buttonClass");
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

public void setButtonClass(String _buttonClass){
this._buttonClass = _buttonClass;
}

public String getButtonDisabledClass(){
	if (this._buttonDisabledClass != null) {
		return this._buttonDisabledClass;
	}
	ValueExpression ve = getValueExpression("buttonDisabledClass");
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

public void setButtonDisabledClass(String _buttonDisabledClass){
this._buttonDisabledClass = _buttonDisabledClass;
}

public String getButtonDisabledStyle(){
	if (this._buttonDisabledStyle != null) {
		return this._buttonDisabledStyle;
	}
	ValueExpression ve = getValueExpression("buttonDisabledStyle");
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

public void setButtonDisabledStyle(String _buttonDisabledStyle){
this._buttonDisabledStyle = _buttonDisabledStyle;
}

public String getButtonIcon(){
	if (this._buttonIcon != null) {
		return this._buttonIcon;
	}
	ValueExpression ve = getValueExpression("buttonIcon");
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

public void setButtonIcon(String _buttonIcon){
this._buttonIcon = _buttonIcon;
}

public String getButtonIconDisabled(){
	if (this._buttonIconDisabled != null) {
		return this._buttonIconDisabled;
	}
	ValueExpression ve = getValueExpression("buttonIconDisabled");
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

public void setButtonIconDisabled(String _buttonIconDisabled){
this._buttonIconDisabled = _buttonIconDisabled;
}

public String getButtonIconInactive(){
	if (this._buttonIconInactive != null) {
		return this._buttonIconInactive;
	}
	ValueExpression ve = getValueExpression("buttonIconInactive");
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

public void setButtonIconInactive(String _buttonIconInactive){
this._buttonIconInactive = _buttonIconInactive;
}

public String getButtonInactiveClass(){
	if (this._buttonInactiveClass != null) {
		return this._buttonInactiveClass;
	}
	ValueExpression ve = getValueExpression("buttonInactiveClass");
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

public void setButtonInactiveClass(String _buttonInactiveClass){
this._buttonInactiveClass = _buttonInactiveClass;
}

public String getButtonInactiveStyle(){
	if (this._buttonInactiveStyle != null) {
		return this._buttonInactiveStyle;
	}
	ValueExpression ve = getValueExpression("buttonInactiveStyle");
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

public void setButtonInactiveStyle(String _buttonInactiveStyle){
this._buttonInactiveStyle = _buttonInactiveStyle;
}

public String getButtonStyle(){
	if (this._buttonStyle != null) {
		return this._buttonStyle;
	}
	ValueExpression ve = getValueExpression("buttonStyle");
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

public void setButtonStyle(String _buttonStyle){
this._buttonStyle = _buttonStyle;
}

public String getDefaultLabel(){
	if (this._defaultLabel != null) {
		return this._defaultLabel;
	}
	ValueExpression ve = getValueExpression("defaultLabel");
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

public void setDefaultLabel(String _defaultLabel){
this._defaultLabel = _defaultLabel;
}

public Boolean getDirectInputSuggestions(){
	if (this._directInputSuggestions != null) {
		return this._directInputSuggestions;
	}
	ValueExpression ve = getValueExpression("directInputSuggestions");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return false;
	

}

public void setDirectInputSuggestions(Boolean _directInputSuggestions){
this._directInputSuggestions = _directInputSuggestions;
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

public boolean isEnableManualInput(){
	if (this._enableManualInputSet) {
	    return (this._enableManualInput);
	}
	ValueExpression ve = getValueExpression("enableManualInput");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._enableManualInput);
	    }
	    
	    return value;
	} else {
	    return (this._enableManualInput);
	}

}

public void setEnableManualInput(boolean _enableManualInput){
this._enableManualInput = _enableManualInput;
this._enableManualInputSet = true;
}

public Boolean getFilterNewValues(){
	if (this._filterNewValues != null) {
		return this._filterNewValues;
	}
	ValueExpression ve = getValueExpression("filterNewValues");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return true;
	

}

public void setFilterNewValues(Boolean _filterNewValues){
this._filterNewValues = _filterNewValues;
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

    return 0;
	

}

public void setHideDelay(Integer _hideDelay){
this._hideDelay = _hideDelay;
}

public String getInputClass(){
	if (this._inputClass != null) {
		return this._inputClass;
	}
	ValueExpression ve = getValueExpression("inputClass");
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

public void setInputClass(String _inputClass){
this._inputClass = _inputClass;
}

public String getInputDisabledClass(){
	if (this._inputDisabledClass != null) {
		return this._inputDisabledClass;
	}
	ValueExpression ve = getValueExpression("inputDisabledClass");
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

public void setInputDisabledClass(String _inputDisabledClass){
this._inputDisabledClass = _inputDisabledClass;
}

public String getInputDisabledStyle(){
	if (this._inputDisabledStyle != null) {
		return this._inputDisabledStyle;
	}
	ValueExpression ve = getValueExpression("inputDisabledStyle");
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

public void setInputDisabledStyle(String _inputDisabledStyle){
this._inputDisabledStyle = _inputDisabledStyle;
}

public String getInputInactiveClass(){
	if (this._inputInactiveClass != null) {
		return this._inputInactiveClass;
	}
	ValueExpression ve = getValueExpression("inputInactiveClass");
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

public void setInputInactiveClass(String _inputInactiveClass){
this._inputInactiveClass = _inputInactiveClass;
}

public String getInputInactiveStyle(){
	if (this._inputInactiveStyle != null) {
		return this._inputInactiveStyle;
	}
	ValueExpression ve = getValueExpression("inputInactiveStyle");
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

public void setInputInactiveStyle(String _inputInactiveStyle){
this._inputInactiveStyle = _inputInactiveStyle;
}

public String getInputStyle(){
	if (this._inputStyle != null) {
		return this._inputStyle;
	}
	ValueExpression ve = getValueExpression("inputStyle");
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

public void setInputStyle(String _inputStyle){
this._inputStyle = _inputStyle;
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

    return null;
	

}

public void setItemClass(String _itemClass){
this._itemClass = _itemClass;
}

public String getItemSelectedClass(){
	if (this._itemSelectedClass != null) {
		return this._itemSelectedClass;
	}
	ValueExpression ve = getValueExpression("itemSelectedClass");
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

public void setItemSelectedClass(String _itemSelectedClass){
this._itemSelectedClass = _itemSelectedClass;
}

public String getLabel(){
	if (this._label != null) {
		return this._label;
	}
	ValueExpression ve = getValueExpression("label");
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

public void setLabel(String _label){
this._label = _label;
}

public String getListClass(){
	if (this._listClass != null) {
		return this._listClass;
	}
	ValueExpression ve = getValueExpression("listClass");
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

public void setListClass(String _listClass){
this._listClass = _listClass;
}

public String getListHeight(){
	if (this._listHeight != null) {
		return this._listHeight;
	}
	ValueExpression ve = getValueExpression("listHeight");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "200px";
	

}

public void setListHeight(String _listHeight){
this._listHeight = _listHeight;
}

public String getListStyle(){
	if (this._listStyle != null) {
		return this._listStyle;
	}
	ValueExpression ve = getValueExpression("listStyle");
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

public void setListStyle(String _listStyle){
this._listStyle = _listStyle;
}

public String getListWidth(){
	if (this._listWidth != null) {
		return this._listWidth;
	}
	ValueExpression ve = getValueExpression("listWidth");
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

public void setListWidth(String _listWidth){
this._listWidth = _listWidth;
}

public int getMaxlength(){
	if (this._maxlengthSet) {
	    return (this._maxlength);
	}
	ValueExpression ve = getValueExpression("maxlength");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._maxlength);
	    }
	    
	    return value;
	} else {
	    return (this._maxlength);
	}

}

public void setMaxlength(int _maxlength){
this._maxlength = _maxlength;
this._maxlengthSet = true;
}

public String getOnblur(){
	if (this._onblur != null) {
		return this._onblur;
	}
	ValueExpression ve = getValueExpression("onblur");
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

public void setOnblur(String _onblur){
this._onblur = _onblur;
}

public String getOnchange(){
	if (this._onchange != null) {
		return this._onchange;
	}
	ValueExpression ve = getValueExpression("onchange");
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

public void setOnchange(String _onchange){
this._onchange = _onchange;
}

public String getOnclick(){
	if (this._onclick != null) {
		return this._onclick;
	}
	ValueExpression ve = getValueExpression("onclick");
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

public void setOnclick(String _onclick){
this._onclick = _onclick;
}

public String getOndblclick(){
	if (this._ondblclick != null) {
		return this._ondblclick;
	}
	ValueExpression ve = getValueExpression("ondblclick");
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

public void setOndblclick(String _ondblclick){
this._ondblclick = _ondblclick;
}

public String getOnfocus(){
	if (this._onfocus != null) {
		return this._onfocus;
	}
	ValueExpression ve = getValueExpression("onfocus");
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

public void setOnfocus(String _onfocus){
this._onfocus = _onfocus;
}

public String getOnkeydown(){
	if (this._onkeydown != null) {
		return this._onkeydown;
	}
	ValueExpression ve = getValueExpression("onkeydown");
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

public void setOnkeydown(String _onkeydown){
this._onkeydown = _onkeydown;
}

public String getOnkeypress(){
	if (this._onkeypress != null) {
		return this._onkeypress;
	}
	ValueExpression ve = getValueExpression("onkeypress");
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

public void setOnkeypress(String _onkeypress){
this._onkeypress = _onkeypress;
}

public String getOnkeyup(){
	if (this._onkeyup != null) {
		return this._onkeyup;
	}
	ValueExpression ve = getValueExpression("onkeyup");
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

public void setOnkeyup(String _onkeyup){
this._onkeyup = _onkeyup;
}

public String getOnlistcall(){
	if (this._onlistcall != null) {
		return this._onlistcall;
	}
	ValueExpression ve = getValueExpression("onlistcall");
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

public void setOnlistcall(String _onlistcall){
this._onlistcall = _onlistcall;
}

public String getOnlistclose(){
	if (this._onlistclose != null) {
		return this._onlistclose;
	}
	ValueExpression ve = getValueExpression("onlistclose");
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

public void setOnlistclose(String _onlistclose){
this._onlistclose = _onlistclose;
}

public String getOnmousedown(){
	if (this._onmousedown != null) {
		return this._onmousedown;
	}
	ValueExpression ve = getValueExpression("onmousedown");
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

public void setOnmousedown(String _onmousedown){
this._onmousedown = _onmousedown;
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

    return null;
	

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

    return null;
	

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

    return null;
	

}

public void setOnmouseover(String _onmouseover){
this._onmouseover = _onmouseover;
}

public String getOnmouseup(){
	if (this._onmouseup != null) {
		return this._onmouseup;
	}
	ValueExpression ve = getValueExpression("onmouseup");
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

public void setOnmouseup(String _onmouseup){
this._onmouseup = _onmouseup;
}

public String getOnselect(){
	if (this._onselect != null) {
		return this._onselect;
	}
	ValueExpression ve = getValueExpression("onselect");
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

public void setOnselect(String _onselect){
this._onselect = _onselect;
}

public Boolean getSelectFirstOnUpdate(){
	if (this._selectFirstOnUpdate != null) {
		return this._selectFirstOnUpdate;
	}
	ValueExpression ve = getValueExpression("selectFirstOnUpdate");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return true;
	

}

public void setSelectFirstOnUpdate(Boolean _selectFirstOnUpdate){
this._selectFirstOnUpdate = _selectFirstOnUpdate;
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

    return 0;
	

}

public void setShowDelay(Integer _showDelay){
this._showDelay = _showDelay;
}

public int getSize(){
	if (this._sizeSet) {
	    return (this._size);
	}
	ValueExpression ve = getValueExpression("size");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._size);
	    }
	    
	    return value;
	} else {
	    return (this._size);
	}

}

public void setSize(int _size){
this._size = _size;
this._sizeSet = true;
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

public Object getSuggestionValues(){
	if (this._suggestionValues != null) {
		return this._suggestionValues;
	}
	ValueExpression ve = getValueExpression("suggestionValues");
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

public void setSuggestionValues(Object _suggestionValues){
this._suggestionValues = _suggestionValues;
}

public String getTabindex(){
	if (this._tabindex != null) {
		return this._tabindex;
	}
	ValueExpression ve = getValueExpression("tabindex");
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

public void setTabindex(String _tabindex){
this._tabindex = _tabindex;
}

public String getWidth(){
	if (this._width != null) {
		return this._width;
	}
	ValueExpression ve = getValueExpression("width");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "150";
	

}

public void setWidth(String _width){
this._width = _width;
}

public String getFamily(){
return COMPONENT_FAMILY;
}

@Override
public Object saveState(FacesContext context){
Object [] state = new Object[62];
state[0] = super.saveState(context);
state[1] = _accesskey;
state[2] = _align;
state[3] = _alt;
state[4] = _behaviourStrategy;
state[5] = _buttonClass;
state[6] = _buttonDisabledClass;
state[7] = _buttonDisabledStyle;
state[8] = _buttonIcon;
state[9] = _buttonIconDisabled;
state[10] = _buttonIconInactive;
state[11] = _buttonInactiveClass;
state[12] = _buttonInactiveStyle;
state[13] = _buttonStyle;
state[14] = _defaultLabel;
state[15] = _directInputSuggestions;
state[16] = Boolean.valueOf(_disabled);
state[17] = Boolean.valueOf(_disabledSet);
state[18] = Boolean.valueOf(_enableManualInput);
state[19] = Boolean.valueOf(_enableManualInputSet);
state[20] = _filterNewValues;
state[21] = _hideDelay;
state[22] = _inputClass;
state[23] = _inputDisabledClass;
state[24] = _inputDisabledStyle;
state[25] = _inputInactiveClass;
state[26] = _inputInactiveStyle;
state[27] = _inputStyle;
state[28] = _itemClass;
state[29] = _itemSelectedClass;
state[30] = _label;
state[31] = _listClass;
state[32] = _listHeight;
state[33] = _listStyle;
state[34] = _listWidth;
state[35] = Integer.valueOf(_maxlength);
state[36] = Boolean.valueOf(_maxlengthSet);
state[37] = _onblur;
state[38] = _onchange;
state[39] = _onclick;
state[40] = _ondblclick;
state[41] = _onfocus;
state[42] = _onkeydown;
state[43] = _onkeypress;
state[44] = _onkeyup;
state[45] = _onlistcall;
state[46] = _onlistclose;
state[47] = _onmousedown;
state[48] = _onmousemove;
state[49] = _onmouseout;
state[50] = _onmouseover;
state[51] = _onmouseup;
state[52] = _onselect;
state[53] = _selectFirstOnUpdate;
state[54] = _showDelay;
state[55] = Integer.valueOf(_size);
state[56] = Boolean.valueOf(_sizeSet);
state[57] = _style;
state[58] = _styleClass;
state[59] = saveAttachedState(context, _suggestionValues);
state[60] = _tabindex;
state[61] = _width;
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_accesskey = (String)states[1];;
		_align = (String)states[2];;
		_alt = (String)states[3];;
		_behaviourStrategy = (String)states[4];;
		_buttonClass = (String)states[5];;
		_buttonDisabledClass = (String)states[6];;
		_buttonDisabledStyle = (String)states[7];;
		_buttonIcon = (String)states[8];;
		_buttonIconDisabled = (String)states[9];;
		_buttonIconInactive = (String)states[10];;
		_buttonInactiveClass = (String)states[11];;
		_buttonInactiveStyle = (String)states[12];;
		_buttonStyle = (String)states[13];;
		_defaultLabel = (String)states[14];;
		_directInputSuggestions = (Boolean)states[15];;
		_disabled = ((Boolean)states[16]).booleanValue();
		_disabledSet = ((Boolean)states[17]).booleanValue();
		_enableManualInput = ((Boolean)states[18]).booleanValue();
		_enableManualInputSet = ((Boolean)states[19]).booleanValue();
		_filterNewValues = (Boolean)states[20];;
		_hideDelay = (Integer)states[21];;
		_inputClass = (String)states[22];;
		_inputDisabledClass = (String)states[23];;
		_inputDisabledStyle = (String)states[24];;
		_inputInactiveClass = (String)states[25];;
		_inputInactiveStyle = (String)states[26];;
		_inputStyle = (String)states[27];;
		_itemClass = (String)states[28];;
		_itemSelectedClass = (String)states[29];;
		_label = (String)states[30];;
		_listClass = (String)states[31];;
		_listHeight = (String)states[32];;
		_listStyle = (String)states[33];;
		_listWidth = (String)states[34];;
		_maxlength = ((Integer)states[35]).intValue();
		_maxlengthSet = ((Boolean)states[36]).booleanValue();
		_onblur = (String)states[37];;
		_onchange = (String)states[38];;
		_onclick = (String)states[39];;
		_ondblclick = (String)states[40];;
		_onfocus = (String)states[41];;
		_onkeydown = (String)states[42];;
		_onkeypress = (String)states[43];;
		_onkeyup = (String)states[44];;
		_onlistcall = (String)states[45];;
		_onlistclose = (String)states[46];;
		_onmousedown = (String)states[47];;
		_onmousemove = (String)states[48];;
		_onmouseout = (String)states[49];;
		_onmouseover = (String)states[50];;
		_onmouseup = (String)states[51];;
		_onselect = (String)states[52];;
		_selectFirstOnUpdate = (Boolean)states[53];;
		_showDelay = (Integer)states[54];;
		_size = ((Integer)states[55]).intValue();
		_sizeSet = ((Boolean)states[56]).booleanValue();
		_style = (String)states[57];;
		_styleClass = (String)states[58];;
		_suggestionValues = (Object)restoreAttachedState(context, states[59]);
		_tabindex = (String)states[60];;
		_width = (String)states[61];;
	
}

}
