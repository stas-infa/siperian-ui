package com.exadel.siperian.component.html;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import org.richfaces.component.UIToolBarGroup;

public class HtmlToolBarGroup extends UIToolBarGroup{

public final static  String COMPONENT_FAMILY = "org.richfaces.ToolBar";

public final static  String COMPONENT_TYPE = "com.exadel.siperian.ToolBarGroup";

/*
* Defines if group should be disabled
*/
private  Boolean _disabled = null;

/*
* Defines if group should be highlighted
*/
private  Boolean _highlight = null;

/*
* A separator for the items in a group. Possible
	    	values are "none", "line", "square", "disc" and "grid" Default value is "none".
*/
private  String _itemSeparator = null;

/*
* A location of a group on a tool bar. Possible values are "left" and "right". Default value is "left".
*/
private  String _location = null;

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
* A CSS class to be applied to tool bar group separators.
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


public HtmlToolBarGroup(){
setRendererType("com.exadel.siperian.ToolBarGroupRenderer");
}

public Boolean getDisabled(){
	if (this._disabled != null) {
		return this._disabled;
	}
	ValueExpression ve = getValueExpression("disabled");
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

public void setDisabled(Boolean _disabled){
this._disabled = _disabled;
}

public Boolean getHighlight(){
	if (this._highlight != null) {
		return this._highlight;
	}
	ValueExpression ve = getValueExpression("highlight");
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

public void setHighlight(Boolean _highlight){
this._highlight = _highlight;
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

public String getLocation(){
	if (this._location != null) {
		return this._location;
	}
	ValueExpression ve = getValueExpression("location");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "left";
	

}

public void setLocation(String _location){
this._location = _location;
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

public String getFamily(){
return COMPONENT_FAMILY;
}

@Override
public Object saveState(FacesContext context){
Object [] state = new Object[28];
state[0] = super.saveState(context);
state[1] = _disabled;
state[2] = _highlight;
state[3] = _itemSeparator;
state[4] = _location;
state[5] = _onclick;
state[6] = _ondblclick;
state[7] = _onitemclick;
state[8] = _onitemdblclick;
state[9] = _onitemkeydown;
state[10] = _onitemkeypress;
state[11] = _onitemkeyup;
state[12] = _onitemmousedown;
state[13] = _onitemmousemove;
state[14] = _onitemmouseout;
state[15] = _onitemmouseover;
state[16] = _onitemmouseup;
state[17] = _onkeydown;
state[18] = _onkeypress;
state[19] = _onkeyup;
state[20] = _onmousedown;
state[21] = _onmousemove;
state[22] = _onmouseout;
state[23] = _onmouseover;
state[24] = _onmouseup;
state[25] = _separatorClass;
state[26] = _style;
state[27] = _styleClass;
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_disabled = (Boolean)states[1];;
		_highlight = (Boolean)states[2];;
		_itemSeparator = (String)states[3];;
		_location = (String)states[4];;
		_onclick = (String)states[5];;
		_ondblclick = (String)states[6];;
		_onitemclick = (String)states[7];;
		_onitemdblclick = (String)states[8];;
		_onitemkeydown = (String)states[9];;
		_onitemkeypress = (String)states[10];;
		_onitemkeyup = (String)states[11];;
		_onitemmousedown = (String)states[12];;
		_onitemmousemove = (String)states[13];;
		_onitemmouseout = (String)states[14];;
		_onitemmouseover = (String)states[15];;
		_onitemmouseup = (String)states[16];;
		_onkeydown = (String)states[17];;
		_onkeypress = (String)states[18];;
		_onkeyup = (String)states[19];;
		_onmousedown = (String)states[20];;
		_onmousemove = (String)states[21];;
		_onmouseout = (String)states[22];;
		_onmouseover = (String)states[23];;
		_onmouseup = (String)states[24];;
		_separatorClass = (String)states[25];;
		_style = (String)states[26];;
		_styleClass = (String)states[27];;
	
}

}
