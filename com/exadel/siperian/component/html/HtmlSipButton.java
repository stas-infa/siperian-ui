package com.exadel.siperian.component.html;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;

public class HtmlSipButton extends UICommand{

public final static  String COMPONENT_FAMILY = "javax.faces.Command";

public final static  String COMPONENT_TYPE = "com.exadel.siperian.HtmlSipButton";

/*
* This attribute assigns an access key to an element. An access key is a single character from the document character set. Note: Authors should consider the input method of the expected reader when specifying an accesskey
*/
private  String _accesskey = null;

/*
* The character encoding of a resource designated by this hyperlink
*/
private  String _charset = null;

/*
* This attribute specifies the position and shape on the screen. The number and order of values depends on the shape being defined. Possible combinations:
            
            * rect: left-x, top-y, right-x, bottom-y.
            * circle: center-x, center-y, radius. Note. When the radius value is percentage value, user agents should calculate the final radius value based on the associated object's width and height. The radius should be the smaller value of the two.
            * poly: x1, y1, x2, y2, ..., xN, yN. The first x and y coordinate pair and the last should be the same to close the polygon. When these coordinate values are not the same, user agents should infer an additional coordinate pair to close the polygon.
            
            Coordinates are relative to the top, left corner of the object. All values are lengths. All values are separated by commas
*/
private  String _coords = null;

/*
* Direction indication for text that does not inherit
			directionality. Valid values are "LTR" (left-to-right)
			and "RTL" (right-to-left)
*/
private  String _dir = null;

/*
* 
*/
private  boolean _disabled = false;

private  boolean _disabledSet = false;

/*
* Base language of a resource specified with the href attribute; hreflang may only be used with href
*/
private  String _hreflang = null;

/*
* Absolute or relative URL of the image to be displayed for this button.
*/
private  String _icon = null;

/*
* the align on the button icon right/left
*/
private  String _iconAlign = null;

/*
* Code describing the language used in the generated markup for this component
*/
private  String _lang = null;

/*
* JavaScript code. The onblur event occurs when an element loses focus either by the pointing device or by tabbing navigation. It may be used with the same elements as onfocus
*/
private  String _onblur = null;

/*
* The clientside script method to be called when the element is clicked
*/
private  String _onclick = null;

/*
* The client side script method to be called when the element is double-clicked
*/
private  String _ondblclick = null;

/*
* JavaScript code. The onfocus event occurs when an element gets focus
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
* The relationship from the current document to the anchor specified by this hyperlink. The value of this attribute is a space-separated list of link types
*/
private  String _rel = null;

/*
* A reverse link from the anchor specified by this hyperlink to the current document. The value of this attribute is a space-separated list of link types
*/
private  String _rev = null;

/*
* This attribute specifies the shape of a region. The possible values are "default", "rect", "circle" and "poly".
*/
private  String _shape = null;

/*
* CSS style(s) is/are to be applied when this component is rendered
*/
private  String _style = null;

/*
* Corresponds to the HTML class attribute
*/
private  String _styleClass = null;

/*
* This attribute specifies the position of the current element in the tabbing order for the current document. This value must be a number between 0 and 32767. User agents should ignore leading zeros
*/
private  String _tabindex = null;

/*
* This attribute specifies the name of a frame where a document is to be opened.
            
            By assigning a name to a frame via the name attribute, authors can refer to it as the "target" of links defined by other elements
*/
private  String _target = null;

/*
* Advisory title information about markup elements generated for this component
*/
private  String _title = null;

/*
* The content type of the resource designated by this hyperlink
*/
private  String _type = null;


public HtmlSipButton(){
setRendererType("com.exadel.siperian.HtmlSipButtonRenderer");
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

public String getCharset(){
	if (this._charset != null) {
		return this._charset;
	}
	ValueExpression ve = getValueExpression("charset");
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

public void setCharset(String _charset){
this._charset = _charset;
}

public String getCoords(){
	if (this._coords != null) {
		return this._coords;
	}
	ValueExpression ve = getValueExpression("coords");
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

public void setCoords(String _coords){
this._coords = _coords;
}

public String getDir(){
	if (this._dir != null) {
		return this._dir;
	}
	ValueExpression ve = getValueExpression("dir");
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

public void setDir(String _dir){
this._dir = _dir;
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

public String getHreflang(){
	if (this._hreflang != null) {
		return this._hreflang;
	}
	ValueExpression ve = getValueExpression("hreflang");
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

public void setHreflang(String _hreflang){
this._hreflang = _hreflang;
}

public String getIcon(){
	if (this._icon != null) {
		return this._icon;
	}
	ValueExpression ve = getValueExpression("icon");
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

public void setIcon(String _icon){
this._icon = _icon;
}

public String getIconAlign(){
	if (this._iconAlign != null) {
		return this._iconAlign;
	}
	ValueExpression ve = getValueExpression("iconAlign");
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

public void setIconAlign(String _iconAlign){
this._iconAlign = _iconAlign;
}

public String getLang(){
	if (this._lang != null) {
		return this._lang;
	}
	ValueExpression ve = getValueExpression("lang");
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

public void setLang(String _lang){
this._lang = _lang;
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

public String getRel(){
	if (this._rel != null) {
		return this._rel;
	}
	ValueExpression ve = getValueExpression("rel");
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

public void setRel(String _rel){
this._rel = _rel;
}

public String getRev(){
	if (this._rev != null) {
		return this._rev;
	}
	ValueExpression ve = getValueExpression("rev");
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

public void setRev(String _rev){
this._rev = _rev;
}

public String getShape(){
	if (this._shape != null) {
		return this._shape;
	}
	ValueExpression ve = getValueExpression("shape");
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

public void setShape(String _shape){
this._shape = _shape;
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

public String getTarget(){
	if (this._target != null) {
		return this._target;
	}
	ValueExpression ve = getValueExpression("target");
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

public void setTarget(String _target){
this._target = _target;
}

public String getTitle(){
	if (this._title != null) {
		return this._title;
	}
	ValueExpression ve = getValueExpression("title");
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

public void setTitle(String _title){
this._title = _title;
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
Object [] state = new Object[32];
state[0] = super.saveState(context);
state[1] = _accesskey;
state[2] = _charset;
state[3] = _coords;
state[4] = _dir;
state[5] = Boolean.valueOf(_disabled);
state[6] = Boolean.valueOf(_disabledSet);
state[7] = _hreflang;
state[8] = _icon;
state[9] = _iconAlign;
state[10] = _lang;
state[11] = _onblur;
state[12] = _onclick;
state[13] = _ondblclick;
state[14] = _onfocus;
state[15] = _onkeydown;
state[16] = _onkeypress;
state[17] = _onkeyup;
state[18] = _onmousedown;
state[19] = _onmousemove;
state[20] = _onmouseout;
state[21] = _onmouseover;
state[22] = _onmouseup;
state[23] = _rel;
state[24] = _rev;
state[25] = _shape;
state[26] = _style;
state[27] = _styleClass;
state[28] = _tabindex;
state[29] = _target;
state[30] = _title;
state[31] = _type;
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_accesskey = (String)states[1];;
		_charset = (String)states[2];;
		_coords = (String)states[3];;
		_dir = (String)states[4];;
		_disabled = ((Boolean)states[5]).booleanValue();
		_disabledSet = ((Boolean)states[6]).booleanValue();
		_hreflang = (String)states[7];;
		_icon = (String)states[8];;
		_iconAlign = (String)states[9];;
		_lang = (String)states[10];;
		_onblur = (String)states[11];;
		_onclick = (String)states[12];;
		_ondblclick = (String)states[13];;
		_onfocus = (String)states[14];;
		_onkeydown = (String)states[15];;
		_onkeypress = (String)states[16];;
		_onkeyup = (String)states[17];;
		_onmousedown = (String)states[18];;
		_onmousemove = (String)states[19];;
		_onmouseout = (String)states[20];;
		_onmouseover = (String)states[21];;
		_onmouseup = (String)states[22];;
		_rel = (String)states[23];;
		_rev = (String)states[24];;
		_shape = (String)states[25];;
		_style = (String)states[26];;
		_styleClass = (String)states[27];;
		_tabindex = (String)states[28];;
		_target = (String)states[29];;
		_title = (String)states[30];;
		_type = (String)states[31];;
	
}

}
