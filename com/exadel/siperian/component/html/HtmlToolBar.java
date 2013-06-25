package com.exadel.siperian.component.html;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import org.richfaces.component.UIToolBar;

public class HtmlToolBar extends UIToolBar{

public final static  String COMPONENT_FAMILY = "org.richfaces.ToolBar";

public final static  String COMPONENT_TYPE = "com.exadel.siperian.ToolBar";

/*
* A CSS style is to be applied to each element of tool bar content. 
    		Use this style, for example, to setup parameters of the font.
*/
private  String _contentClass = null;

/*
* A CSS style is to be applied to each element of tool bar content.
*/
private  String _contentStyle = null;

/*
* 
*/
private  boolean _disabled = false;

private  boolean _disabledSet = false;

/*
* A height of a bar in pixels. If a height is not defined,
    		a bar height depends of the "headerFontSize" skin parameter.
*/
private  String _height = null;

/*
* A separator between items on a bar. Possible values
    		are "none", "line", "square", "disc" and "grid". Default value is "none".
*/
private  String _itemSeparator = null;

/*
* The clientside script method to be called when the element is clicked
*/
private  String _onclick = null;

/*
* The client side script method to be called when the element is double-clicked
*/
private  String _ondblclick = null;

/*
* HTML: a script expression; a pointer button is clicked on an item
*/
private  String _onitemclick = null;

/*
* HTML: a script expression; a pointer button is double-clicked on an item
*/
private  String _onitemdblclick = null;

/*
* HTML: a script expression; a key is pressed down on an item
*/
private  String _onitemkeydown = null;

/*
* HTML: a script expression; a key is pressed and released on an item
*/
private  String _onitemkeypress = null;

/*
* HTML: a script expression; a key is released on an item
*/
private  String _onitemkeyup = null;

/*
* HTML: script expression; a pointer button is pressed down on an item
*/
private  String _onitemmousedown = null;

/*
* HTML: a script expression; a pointer is moved on an item
*/
private  String _onitemmousemove = null;

/*
* HTML: a script expression; a pointer is moved away from an item
*/
private  String _onitemmouseout = null;

/*
* HTML: a script expression; a pointer is moved onto an item
*/
private  String _onitemmouseover = null;

/*
* HTML: script expression; a pointer button is released on an item
*/
private  String _onitemmouseup = null;

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
* A CSS class to be applied to tool bar separators.
*/
private  String _separatorClass = null;

/*
* CSS style(s) is/are to be applied when this component is rendered
*/
private  String _style = null;

/*
* Corresponds to the HTML class attribute
*/
private  String _styleClass = null;

/*
* A width of a bar that can be defined in pixels or as percentage. Default value is &amp;quot;100%&amp;quot;.
*/
private  String _width = null;


public HtmlToolBar(){
setRendererType("com.exadel.siperian.ToolBarRenderer");
}

public String getContentClass(){
	if (this._contentClass != null) {
		return this._contentClass;
	}
	ValueExpression ve = getValueExpression("contentClass");
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

public void setContentClass(String _contentClass){
this._contentClass = _contentClass;
}

public String getContentStyle(){
	if (this._contentStyle != null) {
		return this._contentStyle;
	}
	ValueExpression ve = getValueExpression("contentStyle");
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

public void setContentStyle(String _contentStyle){
this._contentStyle = _contentStyle;
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

public String getHeight(){
	if (this._height != null) {
		return this._height;
	}
	ValueExpression ve = getValueExpression("height");
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

public void setHeight(String _height){
this._height = _height;
}

public String getItemSeparator(){
	if (this._itemSeparator != null) {
		return this._itemSeparator;
	}
	ValueExpression ve = getValueExpression("itemSeparator");
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

public void setItemSeparator(String _itemSeparator){
this._itemSeparator = _itemSeparator;
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

public String getOnitemclick(){
	if (this._onitemclick != null) {
		return this._onitemclick;
	}
	ValueExpression ve = getValueExpression("onitemclick");
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

public void setOnitemclick(String _onitemclick){
this._onitemclick = _onitemclick;
}

public String getOnitemdblclick(){
	if (this._onitemdblclick != null) {
		return this._onitemdblclick;
	}
	ValueExpression ve = getValueExpression("onitemdblclick");
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

public void setOnitemdblclick(String _onitemdblclick){
this._onitemdblclick = _onitemdblclick;
}

public String getOnitemkeydown(){
	if (this._onitemkeydown != null) {
		return this._onitemkeydown;
	}
	ValueExpression ve = getValueExpression("onitemkeydown");
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

public void setOnitemkeydown(String _onitemkeydown){
this._onitemkeydown = _onitemkeydown;
}

public String getOnitemkeypress(){
	if (this._onitemkeypress != null) {
		return this._onitemkeypress;
	}
	ValueExpression ve = getValueExpression("onitemkeypress");
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

public void setOnitemkeypress(String _onitemkeypress){
this._onitemkeypress = _onitemkeypress;
}

public String getOnitemkeyup(){
	if (this._onitemkeyup != null) {
		return this._onitemkeyup;
	}
	ValueExpression ve = getValueExpression("onitemkeyup");
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

public void setOnitemkeyup(String _onitemkeyup){
this._onitemkeyup = _onitemkeyup;
}

public String getOnitemmousedown(){
	if (this._onitemmousedown != null) {
		return this._onitemmousedown;
	}
	ValueExpression ve = getValueExpression("onitemmousedown");
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

public void setOnitemmousedown(String _onitemmousedown){
this._onitemmousedown = _onitemmousedown;
}

public String getOnitemmousemove(){
	if (this._onitemmousemove != null) {
		return this._onitemmousemove;
	}
	ValueExpression ve = getValueExpression("onitemmousemove");
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

public void setOnitemmousemove(String _onitemmousemove){
this._onitemmousemove = _onitemmousemove;
}

public String getOnitemmouseout(){
	if (this._onitemmouseout != null) {
		return this._onitemmouseout;
	}
	ValueExpression ve = getValueExpression("onitemmouseout");
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

public void setOnitemmouseout(String _onitemmouseout){
this._onitemmouseout = _onitemmouseout;
}

public String getOnitemmouseover(){
	if (this._onitemmouseover != null) {
		return this._onitemmouseover;
	}
	ValueExpression ve = getValueExpression("onitemmouseover");
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

public void setOnitemmouseover(String _onitemmouseover){
this._onitemmouseover = _onitemmouseover;
}

public String getOnitemmouseup(){
	if (this._onitemmouseup != null) {
		return this._onitemmouseup;
	}
	ValueExpression ve = getValueExpression("onitemmouseup");
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

public void setOnitemmouseup(String _onitemmouseup){
this._onitemmouseup = _onitemmouseup;
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

public String getSeparatorClass(){
	if (this._separatorClass != null) {
		return this._separatorClass;
	}
	ValueExpression ve = getValueExpression("separatorClass");
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

public void setSeparatorClass(String _separatorClass){
this._separatorClass = _separatorClass;
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

    return "100%";
	

}

public void setWidth(String _width){
this._width = _width;
}

public String getFamily(){
return COMPONENT_FAMILY;
}

@Override
public Object saveState(FacesContext context){
Object [] state = new Object[31];
state[0] = super.saveState(context);
state[1] = _contentClass;
state[2] = _contentStyle;
state[3] = Boolean.valueOf(_disabled);
state[4] = Boolean.valueOf(_disabledSet);
state[5] = _height;
state[6] = _itemSeparator;
state[7] = _onclick;
state[8] = _ondblclick;
state[9] = _onitemclick;
state[10] = _onitemdblclick;
state[11] = _onitemkeydown;
state[12] = _onitemkeypress;
state[13] = _onitemkeyup;
state[14] = _onitemmousedown;
state[15] = _onitemmousemove;
state[16] = _onitemmouseout;
state[17] = _onitemmouseover;
state[18] = _onitemmouseup;
state[19] = _onkeydown;
state[20] = _onkeypress;
state[21] = _onkeyup;
state[22] = _onmousedown;
state[23] = _onmousemove;
state[24] = _onmouseout;
state[25] = _onmouseover;
state[26] = _onmouseup;
state[27] = _separatorClass;
state[28] = _style;
state[29] = _styleClass;
state[30] = _width;
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_contentClass = (String)states[1];;
		_contentStyle = (String)states[2];;
		_disabled = ((Boolean)states[3]).booleanValue();
		_disabledSet = ((Boolean)states[4]).booleanValue();
		_height = (String)states[5];;
		_itemSeparator = (String)states[6];;
		_onclick = (String)states[7];;
		_ondblclick = (String)states[8];;
		_onitemclick = (String)states[9];;
		_onitemdblclick = (String)states[10];;
		_onitemkeydown = (String)states[11];;
		_onitemkeypress = (String)states[12];;
		_onitemkeyup = (String)states[13];;
		_onitemmousedown = (String)states[14];;
		_onitemmousemove = (String)states[15];;
		_onitemmouseout = (String)states[16];;
		_onitemmouseover = (String)states[17];;
		_onitemmouseup = (String)states[18];;
		_onkeydown = (String)states[19];;
		_onkeypress = (String)states[20];;
		_onkeyup = (String)states[21];;
		_onmousedown = (String)states[22];;
		_onmousemove = (String)states[23];;
		_onmouseout = (String)states[24];;
		_onmouseover = (String)states[25];;
		_onmouseup = (String)states[26];;
		_separatorClass = (String)states[27];;
		_style = (String)states[28];;
		_styleClass = (String)states[29];;
		_width = (String)states[30];;
	
}

}
