package com.exadel.siperian.component.html;

import com.exadel.siperian.component.UIModalPanel;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

public class HtmlModalPanel extends UIModalPanel{

public final static  String COMPONENT_FAMILY = "com.exadel.siperian.ModalPanel";

public final static  String COMPONENT_TYPE = "com.exadel.siperian.ModalPanel";

/*
* If panelDOMAttachment equals 'form' then check this attribute that contains of form for attachment.
*/
private  String _attachmentFormId = null;

/*
* If "true" modalPanel should be autosizeable. Default value is "false".
*/
private  boolean _autosized = false;

private  boolean _autosizedSet = false;

/*
* CSS style(s) is/are to be applied to component controls when this component
				is rendered
*/
private  String _controlsClass = null;

/*
* CSS style(s) is/are to be applied to component header when this component
				is rendered
*/
private  String _headerClass = null;

/*
* Attribute defines height of component. Default value is "200".
*/
private  int _height = -1;

private  boolean _heightSet = false;

/*
* Defines whether to show page blocking div under modalPanel
*/
private  boolean _isModal = true;

private  boolean _isModalSet = false;

/*
* If "true" modalPanel should save state after submission. Default value is "false".
*/
private  boolean _keepVisualState = false;

private  boolean _keepVisualStateSet = false;

/*
* A localized user presentable name for this component.
*/
private  String _label = null;

/*
* Attribute defines X position of component left-top corner. Default value is "auto".
*/
private  String _left = null;

/*
* Attribute defines min height of component. Default value is "10". If the value is less then 10, a "IllegalArgumentException" exception is thrown.
*/
private  int _minHeight = -1;

private  boolean _minHeightSet = false;

/*
* Attribute defines min width of component. Default value is "10".  If the value is less then 10, a "IllegalArgumentException" exception is thrown.
*/
private  int _minWidth = -1;

private  boolean _minWidthSet = false;

/*
* if "true" there is possibility to move component. Default value is "true".
*/
private  boolean _moveable = true;

private  boolean _moveableSet = false;

/*
* Event must occurs before panel is hiding
*/
private  String _onbeforehide = null;

/*
* Event must occurs before panel is opening
*/
private  String _onbeforeshow = null;

/*
* Event must occurs after panel closed
*/
private  String _onhide = null;

/*
* HTML: a script expression; a pointer button is clicked outside modalPanel
*/
private  String _onmaskclick = null;

/*
* JavaScript handler to be called on right click outside modalPanel
*/
private  String _onmaskcontextmenu = null;

/*
* HTML: a script expression; a pointer button is double-clicked outside modalPanel
*/
private  String _onmaskdblclick = null;

/*
* HTML: a script expression; a pointer button is pressed down outside modalPanel
*/
private  String _onmaskmousedown = null;

/*
* HTML: a script expression; a pointer button is moved outside modalPanel
*/
private  String _onmaskmousemove = null;

/*
* HTML: a script expression; a pointer button is moved away modalPanel
*/
private  String _onmaskmouseout = null;

/*
* HTML: a script expression; a pointer button is moved onto modalPanel
*/
private  String _onmaskmouseover = null;

/*
* HTML: a script expression; a pointer button is released outside modalPanel
*/
private  String _onmaskmouseup = null;

/*
* Event must occurs before panel is moving
*/
private  String _onmove = null;

/*
* Event must occurs than panel is resizing
*/
private  String _onresize = null;

/*
* Event must occurs after panel opened
*/
private  String _onshow = null;

/*
* Attribute defines panel DOM attachment. Possible values 'body', 'form', 'unchanged'
*/
private  String _panelDOMAttachment = null;

/*
* Pop-up shadow depth for suggestion content
*/
private  String _shadowDepth = null;

/*
* HTML CSS class attribute of element for pop-up suggestion content
*/
private  String _shadowOpacity = null;

/*
* Defines whether to show close button in modalPanel header
*/
private  boolean _showCloseButton = true;

private  boolean _showCloseButtonSet = false;

/*
* If "true" value for this attribute makes a modal panel opened as default.
*/
private  boolean _showWhenRendered = false;

private  boolean _showWhenRenderedSet = false;

/*
* CSS style(s) is/are to be applied when this component is rendered
*/
private  String _style = null;

/*
* Corresponds to the HTML class attribute
*/
private  String _styleClass = null;

/*
* Attribute defines Y position of component left-top corner. Default value is "auto".
*/
private  String _top = null;

/*
* How to handle HTML SELECT-based controls in IE 6? - "disable" - default,
				handle as usual, use disabled="true" to hide SELECT controls - "hide" - use
				visibility="hidden" to hide SELECT controls
*/
private  String _tridentIVEngineSelectBehavior = null;

/*
* Defines whether to trim or not elements inside modalPanel
*/
private  boolean _trimOverlayedElements = true;

private  boolean _trimOverlayedElementsSet = false;

/*
* Attribute defines width of component. Default value is "300".
*/
private  int _width = -1;

private  boolean _widthSet = false;

/*
* Attribute is similar to the standard HTML attribute and can specify window. Default value is "100".
				placement relative to the content
*/
private  int _zindex = 100;

private  boolean _zindexSet = false;


public HtmlModalPanel(){
setRendererType("com.exadel.siperian.ModalPanelRenderer");
}

public String getAttachmentFormId(){
	if (this._attachmentFormId != null) {
		return this._attachmentFormId;
	}
	ValueExpression ve = getValueExpression("attachmentFormId");
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

public void setAttachmentFormId(String _attachmentFormId){
this._attachmentFormId = _attachmentFormId;
}

public boolean isAutosized(){
	if (this._autosizedSet) {
	    return (this._autosized);
	}
	ValueExpression ve = getValueExpression("autosized");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._autosized);
	    }
	    
	    return value;
	} else {
	    return (this._autosized);
	}

}

public void setAutosized(boolean _autosized){
this._autosized = _autosized;
this._autosizedSet = true;
}

public String getControlsClass(){
	if (this._controlsClass != null) {
		return this._controlsClass;
	}
	ValueExpression ve = getValueExpression("controlsClass");
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

public void setControlsClass(String _controlsClass){
this._controlsClass = _controlsClass;
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

    return "";
	

}

public void setHeaderClass(String _headerClass){
this._headerClass = _headerClass;
}

public int getHeight(){
	if (this._heightSet) {
	    return (this._height);
	}
	ValueExpression ve = getValueExpression("height");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._height);
	    }
	    
	    return value;
	} else {
	    return (this._height);
	}

}

public void setHeight(int _height){
this._height = _height;
this._heightSet = true;
}

public boolean isIsModal(){
	if (this._isModalSet) {
	    return (this._isModal);
	}
	ValueExpression ve = getValueExpression("isModal");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._isModal);
	    }
	    
	    return value;
	} else {
	    return (this._isModal);
	}

}

public void setIsModal(boolean _isModal){
this._isModal = _isModal;
this._isModalSet = true;
}

public boolean isKeepVisualState(){
	if (this._keepVisualStateSet) {
	    return (this._keepVisualState);
	}
	ValueExpression ve = getValueExpression("keepVisualState");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._keepVisualState);
	    }
	    
	    return value;
	} else {
	    return (this._keepVisualState);
	}

}

public void setKeepVisualState(boolean _keepVisualState){
this._keepVisualState = _keepVisualState;
this._keepVisualStateSet = true;
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

public String getLeft(){
	if (this._left != null) {
		return this._left;
	}
	ValueExpression ve = getValueExpression("left");
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

public void setLeft(String _left){
this._left = _left;
}

public int getMinHeight(){
	if (this._minHeightSet) {
	    return (this._minHeight);
	}
	ValueExpression ve = getValueExpression("minHeight");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._minHeight);
	    }
	    
	    return value;
	} else {
	    return (this._minHeight);
	}

}

public void setMinHeight(int _minHeight){
this._minHeight = _minHeight;
this._minHeightSet = true;
}

public int getMinWidth(){
	if (this._minWidthSet) {
	    return (this._minWidth);
	}
	ValueExpression ve = getValueExpression("minWidth");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._minWidth);
	    }
	    
	    return value;
	} else {
	    return (this._minWidth);
	}

}

public void setMinWidth(int _minWidth){
this._minWidth = _minWidth;
this._minWidthSet = true;
}

public boolean isMoveable(){
	if (this._moveableSet) {
	    return (this._moveable);
	}
	ValueExpression ve = getValueExpression("moveable");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._moveable);
	    }
	    
	    return value;
	} else {
	    return (this._moveable);
	}

}

public void setMoveable(boolean _moveable){
this._moveable = _moveable;
this._moveableSet = true;
}

public String getOnbeforehide(){
	if (this._onbeforehide != null) {
		return this._onbeforehide;
	}
	ValueExpression ve = getValueExpression("onbeforehide");
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

public void setOnbeforehide(String _onbeforehide){
this._onbeforehide = _onbeforehide;
}

public String getOnbeforeshow(){
	if (this._onbeforeshow != null) {
		return this._onbeforeshow;
	}
	ValueExpression ve = getValueExpression("onbeforeshow");
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

public void setOnbeforeshow(String _onbeforeshow){
this._onbeforeshow = _onbeforeshow;
}

public String getOnhide(){
	if (this._onhide != null) {
		return this._onhide;
	}
	ValueExpression ve = getValueExpression("onhide");
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

public void setOnhide(String _onhide){
this._onhide = _onhide;
}

public String getOnmaskclick(){
	if (this._onmaskclick != null) {
		return this._onmaskclick;
	}
	ValueExpression ve = getValueExpression("onmaskclick");
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

public void setOnmaskclick(String _onmaskclick){
this._onmaskclick = _onmaskclick;
}

public String getOnmaskcontextmenu(){
	if (this._onmaskcontextmenu != null) {
		return this._onmaskcontextmenu;
	}
	ValueExpression ve = getValueExpression("onmaskcontextmenu");
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

public void setOnmaskcontextmenu(String _onmaskcontextmenu){
this._onmaskcontextmenu = _onmaskcontextmenu;
}

public String getOnmaskdblclick(){
	if (this._onmaskdblclick != null) {
		return this._onmaskdblclick;
	}
	ValueExpression ve = getValueExpression("onmaskdblclick");
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

public void setOnmaskdblclick(String _onmaskdblclick){
this._onmaskdblclick = _onmaskdblclick;
}

public String getOnmaskmousedown(){
	if (this._onmaskmousedown != null) {
		return this._onmaskmousedown;
	}
	ValueExpression ve = getValueExpression("onmaskmousedown");
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

public void setOnmaskmousedown(String _onmaskmousedown){
this._onmaskmousedown = _onmaskmousedown;
}

public String getOnmaskmousemove(){
	if (this._onmaskmousemove != null) {
		return this._onmaskmousemove;
	}
	ValueExpression ve = getValueExpression("onmaskmousemove");
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

public void setOnmaskmousemove(String _onmaskmousemove){
this._onmaskmousemove = _onmaskmousemove;
}

public String getOnmaskmouseout(){
	if (this._onmaskmouseout != null) {
		return this._onmaskmouseout;
	}
	ValueExpression ve = getValueExpression("onmaskmouseout");
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

public void setOnmaskmouseout(String _onmaskmouseout){
this._onmaskmouseout = _onmaskmouseout;
}

public String getOnmaskmouseover(){
	if (this._onmaskmouseover != null) {
		return this._onmaskmouseover;
	}
	ValueExpression ve = getValueExpression("onmaskmouseover");
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

public void setOnmaskmouseover(String _onmaskmouseover){
this._onmaskmouseover = _onmaskmouseover;
}

public String getOnmaskmouseup(){
	if (this._onmaskmouseup != null) {
		return this._onmaskmouseup;
	}
	ValueExpression ve = getValueExpression("onmaskmouseup");
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

public void setOnmaskmouseup(String _onmaskmouseup){
this._onmaskmouseup = _onmaskmouseup;
}

public String getOnmove(){
	if (this._onmove != null) {
		return this._onmove;
	}
	ValueExpression ve = getValueExpression("onmove");
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

public void setOnmove(String _onmove){
this._onmove = _onmove;
}

public String getOnresize(){
	if (this._onresize != null) {
		return this._onresize;
	}
	ValueExpression ve = getValueExpression("onresize");
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

public void setOnresize(String _onresize){
this._onresize = _onresize;
}

public String getOnshow(){
	if (this._onshow != null) {
		return this._onshow;
	}
	ValueExpression ve = getValueExpression("onshow");
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

public void setOnshow(String _onshow){
this._onshow = _onshow;
}

public String getPanelDOMAttachment(){
	if (this._panelDOMAttachment != null) {
		return this._panelDOMAttachment;
	}
	ValueExpression ve = getValueExpression("panelDOMAttachment");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "body";
	

}

public void setPanelDOMAttachment(String _panelDOMAttachment){
this._panelDOMAttachment = _panelDOMAttachment;
}

public String getShadowDepth(){
	if (this._shadowDepth != null) {
		return this._shadowDepth;
	}
	ValueExpression ve = getValueExpression("shadowDepth");
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

public void setShadowDepth(String _shadowDepth){
this._shadowDepth = _shadowDepth;
}

public String getShadowOpacity(){
	if (this._shadowOpacity != null) {
		return this._shadowOpacity;
	}
	ValueExpression ve = getValueExpression("shadowOpacity");
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

public void setShadowOpacity(String _shadowOpacity){
this._shadowOpacity = _shadowOpacity;
}

public boolean isShowCloseButton(){
	if (this._showCloseButtonSet) {
	    return (this._showCloseButton);
	}
	ValueExpression ve = getValueExpression("showCloseButton");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._showCloseButton);
	    }
	    
	    return value;
	} else {
	    return (this._showCloseButton);
	}

}

public void setShowCloseButton(boolean _showCloseButton){
this._showCloseButton = _showCloseButton;
this._showCloseButtonSet = true;
}

public boolean isShowWhenRendered(){
	if (this._showWhenRenderedSet) {
	    return (this._showWhenRendered);
	}
	ValueExpression ve = getValueExpression("showWhenRendered");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._showWhenRendered);
	    }
	    
	    return value;
	} else {
	    return (this._showWhenRendered);
	}

}

public void setShowWhenRendered(boolean _showWhenRendered){
this._showWhenRendered = _showWhenRendered;
this._showWhenRenderedSet = true;
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

public String getTop(){
	if (this._top != null) {
		return this._top;
	}
	ValueExpression ve = getValueExpression("top");
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

public void setTop(String _top){
this._top = _top;
}

public String getTridentIVEngineSelectBehavior(){
	if (this._tridentIVEngineSelectBehavior != null) {
		return this._tridentIVEngineSelectBehavior;
	}
	ValueExpression ve = getValueExpression("tridentIVEngineSelectBehavior");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "disable";
	

}

public void setTridentIVEngineSelectBehavior(String _tridentIVEngineSelectBehavior){
this._tridentIVEngineSelectBehavior = _tridentIVEngineSelectBehavior;
}

public boolean isTrimOverlayedElements(){
	if (this._trimOverlayedElementsSet) {
	    return (this._trimOverlayedElements);
	}
	ValueExpression ve = getValueExpression("trimOverlayedElements");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._trimOverlayedElements);
	    }
	    
	    return value;
	} else {
	    return (this._trimOverlayedElements);
	}

}

public void setTrimOverlayedElements(boolean _trimOverlayedElements){
this._trimOverlayedElements = _trimOverlayedElements;
this._trimOverlayedElementsSet = true;
}

public int getWidth(){
	if (this._widthSet) {
	    return (this._width);
	}
	ValueExpression ve = getValueExpression("width");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._width);
	    }
	    
	    return value;
	} else {
	    return (this._width);
	}

}

public void setWidth(int _width){
this._width = _width;
this._widthSet = true;
}

public int getZindex(){
	if (this._zindexSet) {
	    return (this._zindex);
	}
	ValueExpression ve = getValueExpression("zindex");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._zindex);
	    }
	    
	    return value;
	} else {
	    return (this._zindex);
	}

}

public void setZindex(int _zindex){
this._zindex = _zindex;
this._zindexSet = true;
}

public String getFamily(){
return COMPONENT_FAMILY;
}

@Override
public Object saveState(FacesContext context){
Object [] state = new Object[51];
state[0] = super.saveState(context);
state[1] = _attachmentFormId;
state[2] = Boolean.valueOf(_autosized);
state[3] = Boolean.valueOf(_autosizedSet);
state[4] = _controlsClass;
state[5] = _headerClass;
state[6] = Integer.valueOf(_height);
state[7] = Boolean.valueOf(_heightSet);
state[8] = Boolean.valueOf(_isModal);
state[9] = Boolean.valueOf(_isModalSet);
state[10] = Boolean.valueOf(_keepVisualState);
state[11] = Boolean.valueOf(_keepVisualStateSet);
state[12] = _label;
state[13] = _left;
state[14] = Integer.valueOf(_minHeight);
state[15] = Boolean.valueOf(_minHeightSet);
state[16] = Integer.valueOf(_minWidth);
state[17] = Boolean.valueOf(_minWidthSet);
state[18] = Boolean.valueOf(_moveable);
state[19] = Boolean.valueOf(_moveableSet);
state[20] = _onbeforehide;
state[21] = _onbeforeshow;
state[22] = _onhide;
state[23] = _onmaskclick;
state[24] = _onmaskcontextmenu;
state[25] = _onmaskdblclick;
state[26] = _onmaskmousedown;
state[27] = _onmaskmousemove;
state[28] = _onmaskmouseout;
state[29] = _onmaskmouseover;
state[30] = _onmaskmouseup;
state[31] = _onmove;
state[32] = _onresize;
state[33] = _onshow;
state[34] = _panelDOMAttachment;
state[35] = _shadowDepth;
state[36] = _shadowOpacity;
state[37] = Boolean.valueOf(_showCloseButton);
state[38] = Boolean.valueOf(_showCloseButtonSet);
state[39] = Boolean.valueOf(_showWhenRendered);
state[40] = Boolean.valueOf(_showWhenRenderedSet);
state[41] = _style;
state[42] = _styleClass;
state[43] = _top;
state[44] = _tridentIVEngineSelectBehavior;
state[45] = Boolean.valueOf(_trimOverlayedElements);
state[46] = Boolean.valueOf(_trimOverlayedElementsSet);
state[47] = Integer.valueOf(_width);
state[48] = Boolean.valueOf(_widthSet);
state[49] = Integer.valueOf(_zindex);
state[50] = Boolean.valueOf(_zindexSet);
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_attachmentFormId = (String)states[1];;
		_autosized = ((Boolean)states[2]).booleanValue();
		_autosizedSet = ((Boolean)states[3]).booleanValue();
		_controlsClass = (String)states[4];;
		_headerClass = (String)states[5];;
		_height = ((Integer)states[6]).intValue();
		_heightSet = ((Boolean)states[7]).booleanValue();
		_isModal = ((Boolean)states[8]).booleanValue();
		_isModalSet = ((Boolean)states[9]).booleanValue();
		_keepVisualState = ((Boolean)states[10]).booleanValue();
		_keepVisualStateSet = ((Boolean)states[11]).booleanValue();
		_label = (String)states[12];;
		_left = (String)states[13];;
		_minHeight = ((Integer)states[14]).intValue();
		_minHeightSet = ((Boolean)states[15]).booleanValue();
		_minWidth = ((Integer)states[16]).intValue();
		_minWidthSet = ((Boolean)states[17]).booleanValue();
		_moveable = ((Boolean)states[18]).booleanValue();
		_moveableSet = ((Boolean)states[19]).booleanValue();
		_onbeforehide = (String)states[20];;
		_onbeforeshow = (String)states[21];;
		_onhide = (String)states[22];;
		_onmaskclick = (String)states[23];;
		_onmaskcontextmenu = (String)states[24];;
		_onmaskdblclick = (String)states[25];;
		_onmaskmousedown = (String)states[26];;
		_onmaskmousemove = (String)states[27];;
		_onmaskmouseout = (String)states[28];;
		_onmaskmouseover = (String)states[29];;
		_onmaskmouseup = (String)states[30];;
		_onmove = (String)states[31];;
		_onresize = (String)states[32];;
		_onshow = (String)states[33];;
		_panelDOMAttachment = (String)states[34];;
		_shadowDepth = (String)states[35];;
		_shadowOpacity = (String)states[36];;
		_showCloseButton = ((Boolean)states[37]).booleanValue();
		_showCloseButtonSet = ((Boolean)states[38]).booleanValue();
		_showWhenRendered = ((Boolean)states[39]).booleanValue();
		_showWhenRenderedSet = ((Boolean)states[40]).booleanValue();
		_style = (String)states[41];;
		_styleClass = (String)states[42];;
		_top = (String)states[43];;
		_tridentIVEngineSelectBehavior = (String)states[44];;
		_trimOverlayedElements = ((Boolean)states[45]).booleanValue();
		_trimOverlayedElementsSet = ((Boolean)states[46]).booleanValue();
		_width = ((Integer)states[47]).intValue();
		_widthSet = ((Boolean)states[48]).booleanValue();
		_zindex = ((Integer)states[49]).intValue();
		_zindexSet = ((Boolean)states[50]).booleanValue();
	
}

}
