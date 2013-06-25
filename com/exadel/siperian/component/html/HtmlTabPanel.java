package com.exadel.siperian.component.html;

import com.exadel.siperian.component.UITabPanel;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;

public class HtmlTabPanel extends UITabPanel{

public final static  String COMPONENT_FAMILY = "com.exadel.siperian.TabPanel";

public final static  String COMPONENT_TYPE = "com.exadel.siperian.TabPanel";

/*
* A CSS class to be applied to an active tab
*/
private  String _activeTabClass = null;

/*
* A CSS class for content of a tab panel
*/
private  String _contentClass = null;

/*
* A CSS style is for the content of a tab panel
*/
private  String _contentStyle = null;

/*
* Direction indication for text that does not inherit
			directionality. Valid values are "LTR" (left-to-right)
			and "RTL" (right-to-left)
*/
private  String _dir = null;

/*
* A CSS class to be applied to a disabled tab
*/
private  String _disabledTabClass = null;

/*
* Sets tab headers alignment. It can be "left" or "right".
	    		 Default value is "left".
*/
private  String _headerAlignment = null;

/*
* A CSS style is for the header of a tab panel.
*/
private  String _headerClass = null;

/*
* Sets tab headers spacing. It should be a valid size unit expression. Default value is "1px".
*/
private  String _headerSpacing = null;

/*
* Height of a tab panel defined in pixels or in percents
*/
private  String _height = null;

/*
* CSS class to be applied to an inactive (but not disabled) tab
*/
private  String _inactiveTabClass = null;

/*
* A localized user presentable name for this component.
*/
private  String _label = null;

/*
* Code describing the language used in the generated markup for this component
*/
private  String _lang = null;

/*
* Defines tab panel type (Search, Static)
*/
private  String _markupTemplate = null;

/*
* Defines max count of tabs to load on client
*/
private  Integer _maxCountOfTabsLoadedOnClient = null;

/*
* 
*/
private  Integer _maxTabWidth = null;

/*
* The clientside script method to be called when the element is clicked
*/
private  String _onclick = null;

/*
* The client side script method to be called when the element is double-clicked
*/
private  String _ondblclick = null;

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
* HTML: a script expression; a tab has been changed
*/
private  String _ontabchange = null;

/*
* 
*/
private  String _ontabclosed = null;

/*
* 
*/
private  Object _renderedValue = null;

/*
* CSS style(s) is/are to be applied when this component is rendered
*/
private  String _style = null;

/*
* Corresponds to the HTML class attribute
*/
private  String _styleClass = null;

/*
* A CSS class to be applied to all tabs
*/
private  String _tabClass = null;

/*
* MethodExpression representing an action listener method
				that will be notified after tab closed.
*/
private  MethodBinding _tabCloseListener = null;

/*
* 
*/
private  Integer _tabListHeight = null;

/*
* Advisory title information about markup elements generated for this component
*/
private  String _title = null;

/*
* Width of a tab panel defined in pixels or in percents. 
	    	The default value is 100%
*/
private  String _width = null;


public HtmlTabPanel(){
setRendererType("com.exadel.siperian.TabPanelRenderer");
}

public String getActiveTabClass(){
	if (this._activeTabClass != null) {
		return this._activeTabClass;
	}
	ValueExpression ve = getValueExpression("activeTabClass");
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

public void setActiveTabClass(String _activeTabClass){
this._activeTabClass = _activeTabClass;
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

public String getDisabledTabClass(){
	if (this._disabledTabClass != null) {
		return this._disabledTabClass;
	}
	ValueExpression ve = getValueExpression("disabledTabClass");
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

public void setDisabledTabClass(String _disabledTabClass){
this._disabledTabClass = _disabledTabClass;
}

public String getHeaderAlignment(){
	if (this._headerAlignment != null) {
		return this._headerAlignment;
	}
	ValueExpression ve = getValueExpression("headerAlignment");
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

public void setHeaderAlignment(String _headerAlignment){
this._headerAlignment = _headerAlignment;
}

public String getHeaderClass(){
	if (this._headerClass != null) {
		return this._headerClass;
	}
	ValueExpression ve = getValueExpression("headerClass");
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

public void setHeaderClass(String _headerClass){
this._headerClass = _headerClass;
}

public String getHeaderSpacing(){
	if (this._headerSpacing != null) {
		return this._headerSpacing;
	}
	ValueExpression ve = getValueExpression("headerSpacing");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "1px";
	

}

public void setHeaderSpacing(String _headerSpacing){
this._headerSpacing = _headerSpacing;
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

    return "100%";
	

}

public void setHeight(String _height){
this._height = _height;
}

public String getInactiveTabClass(){
	if (this._inactiveTabClass != null) {
		return this._inactiveTabClass;
	}
	ValueExpression ve = getValueExpression("inactiveTabClass");
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

public void setInactiveTabClass(String _inactiveTabClass){
this._inactiveTabClass = _inactiveTabClass;
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

public String getMarkupTemplate(){
	if (this._markupTemplate != null) {
		return this._markupTemplate;
	}
	ValueExpression ve = getValueExpression("markupTemplate");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "dynamic";
	

}

public void setMarkupTemplate(String _markupTemplate){
this._markupTemplate = _markupTemplate;
}

public Integer getMaxCountOfTabsLoadedOnClient(){
	if (this._maxCountOfTabsLoadedOnClient != null) {
		return this._maxCountOfTabsLoadedOnClient;
	}
	ValueExpression ve = getValueExpression("maxCountOfTabsLoadedOnClient");
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

public void setMaxCountOfTabsLoadedOnClient(Integer _maxCountOfTabsLoadedOnClient){
this._maxCountOfTabsLoadedOnClient = _maxCountOfTabsLoadedOnClient;
}

public Integer getMaxTabWidth(){
	if (this._maxTabWidth != null) {
		return this._maxTabWidth;
	}
	ValueExpression ve = getValueExpression("maxTabWidth");
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

public void setMaxTabWidth(Integer _maxTabWidth){
this._maxTabWidth = _maxTabWidth;
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

public String getOntabchange(){
	if (this._ontabchange != null) {
		return this._ontabchange;
	}
	ValueExpression ve = getValueExpression("ontabchange");
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

public void setOntabchange(String _ontabchange){
this._ontabchange = _ontabchange;
}

public String getOntabclosed(){
	if (this._ontabclosed != null) {
		return this._ontabclosed;
	}
	ValueExpression ve = getValueExpression("ontabclosed");
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

public void setOntabclosed(String _ontabclosed){
this._ontabclosed = _ontabclosed;
}

public Object getRenderedValue(){
	if (this._renderedValue != null) {
		return this._renderedValue;
	}
	ValueExpression ve = getValueExpression("renderedValue");
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

public void setRenderedValue(Object _renderedValue){
this._renderedValue = _renderedValue;
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

public String getTabClass(){
	if (this._tabClass != null) {
		return this._tabClass;
	}
	ValueExpression ve = getValueExpression("tabClass");
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

public void setTabClass(String _tabClass){
this._tabClass = _tabClass;
}

public MethodBinding getTabCloseListener(){
return _tabCloseListener;
}

public void setTabCloseListener(MethodBinding _tabCloseListener){
this._tabCloseListener = _tabCloseListener;
}

public Integer getTabListHeight(){
	if (this._tabListHeight != null) {
		return this._tabListHeight;
	}
	ValueExpression ve = getValueExpression("tabListHeight");
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

public void setTabListHeight(Integer _tabListHeight){
this._tabListHeight = _tabListHeight;
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
Object [] state = new Object[36];
state[0] = super.saveState(context);
state[1] = _activeTabClass;
state[2] = _contentClass;
state[3] = _contentStyle;
state[4] = _dir;
state[5] = _disabledTabClass;
state[6] = _headerAlignment;
state[7] = _headerClass;
state[8] = _headerSpacing;
state[9] = _height;
state[10] = _inactiveTabClass;
state[11] = _label;
state[12] = _lang;
state[13] = _markupTemplate;
state[14] = _maxCountOfTabsLoadedOnClient;
state[15] = _maxTabWidth;
state[16] = _onclick;
state[17] = _ondblclick;
state[18] = _onkeydown;
state[19] = _onkeypress;
state[20] = _onkeyup;
state[21] = _onmousedown;
state[22] = _onmousemove;
state[23] = _onmouseout;
state[24] = _onmouseover;
state[25] = _onmouseup;
state[26] = _ontabchange;
state[27] = _ontabclosed;
state[28] = saveAttachedState(context, _renderedValue);
state[29] = _style;
state[30] = _styleClass;
state[31] = _tabClass;
state[32] = saveAttachedState(context, _tabCloseListener);
state[33] = _tabListHeight;
state[34] = _title;
state[35] = _width;
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_activeTabClass = (String)states[1];;
		_contentClass = (String)states[2];;
		_contentStyle = (String)states[3];;
		_dir = (String)states[4];;
		_disabledTabClass = (String)states[5];;
		_headerAlignment = (String)states[6];;
		_headerClass = (String)states[7];;
		_headerSpacing = (String)states[8];;
		_height = (String)states[9];;
		_inactiveTabClass = (String)states[10];;
		_label = (String)states[11];;
		_lang = (String)states[12];;
		_markupTemplate = (String)states[13];;
		_maxCountOfTabsLoadedOnClient = (Integer)states[14];;
		_maxTabWidth = (Integer)states[15];;
		_onclick = (String)states[16];;
		_ondblclick = (String)states[17];;
		_onkeydown = (String)states[18];;
		_onkeypress = (String)states[19];;
		_onkeyup = (String)states[20];;
		_onmousedown = (String)states[21];;
		_onmousemove = (String)states[22];;
		_onmouseout = (String)states[23];;
		_onmouseover = (String)states[24];;
		_onmouseup = (String)states[25];;
		_ontabchange = (String)states[26];;
		_ontabclosed = (String)states[27];;
		_renderedValue = (Object)restoreAttachedState(context, states[28]);
		_style = (String)states[29];;
		_styleClass = (String)states[30];;
		_tabClass = (String)states[31];;
		_tabCloseListener = (MethodBinding)restoreAttachedState(context, states[32]);
		_tabListHeight = (Integer)states[33];;
		_title = (String)states[34];;
		_width = (String)states[35];;
	
}

}
