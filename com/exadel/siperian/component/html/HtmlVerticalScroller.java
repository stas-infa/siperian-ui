package com.exadel.siperian.component.html;

import com.exadel.siperian.component.UIVerticalScroller;
import javax.el.ELException;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

public class HtmlVerticalScroller extends UIVerticalScroller{

public final static  String COMPONENT_FAMILY = "com.exadel.siperian.VerticalScroller";

public final static  String COMPONENT_TYPE = "com.exadel.siperian.VerticalScroller";

/*
* Boolean attribute which provides possibility to limit JSF tree processing(decoding, conversion/validation, value applying) to the component which send the request only.
*/
private  boolean _ajaxSingle = true;

private  boolean _ajaxSingleSet = false;

/*
* This attribute specifies the position of the table with relatively to  the document.
			Possible values are "left","center","right ". Default value is "center".
*/
private  String _align = null;

/*
* The attribute specifies the visibility of boundaryControls. 
				Possible values are: "show"  (controls are always visible ). "hide" (controls are hidden. 
				"auto" (unnecessary controls are hidden). Default value is "show".
*/
private  String _boundaryControls = null;

/*
* If "true", after process validations phase it skips updates of model beans on a force render response. It can be used for validating components input
*/
private  boolean _bypassUpdates = false;

private  boolean _bypassUpdatesSet = false;

/*
* Serialized (on default with JSON) data passed on the client by a developer on AJAX request. It's accessible via "data.foo" syntax
*/
private  Object _data = null;

/*
* Name of requests queue to avoid send next request before complete other from same event. Can be used to reduce number of requests of frequently events (key press, mouse move etc.)
*/
private  String _eventsQueue = null;

/*
* The attribute specifies the visibility of fastControls.
				Possible values are: "show"  (controls are always visible ). "hide" (controls are hidden. 
				"auto" (unnecessary controls are hidden). Default value is "show".
*/
private  String _fastControls = null;

/*
* The attribute indicates pages quantity to switch onto when fast scrolling is used. Default value is "0".
*/
private  int _fastStep = 0;

private  boolean _fastStepSet = false;

/*
* id of element to set focus after request completed on client side
*/
private  String _focus = null;

/*
* ID of the table component whose data is scrollled
*/
private  String _for = null;

/*
* Current handle value
*/
private  String _handleValue = null;

/*
* Component height
*/
private  String _height = null;

/*
* Attribute allows to ignore an Ajax Response produced by a request if the newest 'similar' request is
in a queue already. ignoreDupResponses="true" does not cancel the request while it is processed on the server,
but just allows to avoid unnecessary updates on the client side if the response isn't actual now. Default value is "true".
*/
private  boolean _ignoreDupResponses = true;

private  boolean _ignoreDupResponsesSet = false;

/*
* Corresponds to the HTML style attribute for the inactive cell on scroller
*/
private  String _inactiveStyle = null;

/*
* Corresponds to the HTML class attribute for the inactive cell on scroller
*/
private  String _inactiveStyleClass = null;

/*
* If "true", then of all AJAX-rendered on the page components only those will be updated, 
		which ID's are passed to the "reRender" attribute of the describable component. 
		"false"-the default value-means that all components with ajaxRendered="true" will be updated.
*/
private  boolean _limitToList = false;

private  boolean _limitToListSet = false;

/*
* The client side script method to be called before DOM is updated
*/
private  String _onbeforedomupdate = null;

/*
* The clientside script method to be called when the element is clicked
*/
private  String _onclick = null;

/*
* The client side script method to be called after the request is completed
*/
private  String _oncomplete = null;

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
* JavaScript handler for call after the page is changed
*/
private  String _onpagechange = null;

/*
* Name of variable in request scope containing index of active page
*/
private  String _pageIndexVar = null;

/*
* Name of variable in request scope containing number of pages
*/
private  String _pagesVar = null;

/*
* Id['s] (in format of call  UIComponent.findComponent()) of components, processed at the phases 2-5 in case of AjaxRequest  caused by this component. Can be single id, comma-separated list of Id's, or EL Expression  with array or Collection
*/
private  Object _process = null;

/*
* Id['s] (in format of call  UIComponent.findComponent()) of components, rendered in case of AjaxRequest  caused by this component. Can be single id, comma-separated list of Id's, or EL Expression  with array or Collection
*/
private  Object _reRender = null;

/*
* If renderIfSinglePage is "true" then datascroller is displayed on condition that the data hold on one page. Default value is "true".
*/
private  boolean _renderIfSinglePage = true;

private  boolean _renderIfSinglePageSet = false;

/*
* Attribute defines the time (in ms.) that the request will be wait in the queue before it is ready to send.
When the delay time is over, the request will be sent to the server or removed if the newest 'similar' request is in a queue already
*/
private  int _requestDelay = Integer.MIN_VALUE;

private  boolean _requestDelaySet = false;

/*
* MethodBinding representing an action listener method that will be notified after scrolling
*/
private  MethodExpression _scrollerListener = null;

/*
* Corresponds to the HTML style attribute for the selected cell on scroller
*/
private  String _selectedStyle = null;

/*
* Corresponds to the HTML class attribute for the selected cell on scroller
*/
private  String _selectedStyleClass = null;

/*
* If there are any component requests with identical IDs then these requests will be grouped.
*/
private  String _similarityGroupingId = null;

/*
* ID (in format of call UIComponent.findComponent()) of Request status component
*/
private  String _status = null;

/*
* The attribute specifies the visibility of stepControls.
				Possible values are: "show"  (controls are always visible ). "hide" (controls are hidden. 
				"auto" (unnecessary controls are hidden). Default value is "show".
*/
private  String _stepControls = null;

/*
* CSS style(s) is/are to be applied when this component is rendered
*/
private  String _style = null;

/*
* Corresponds to the HTML class attribute
*/
private  String _styleClass = null;

/*
* CSS style(s) is/are to be applied to outside table when this component is rendered
*/
private  String _tableStyle = null;

/*
* Space-separated list of CSS style class(es) that are be applied to outside table of this component
*/
private  String _tableStyleClass = null;

/*
* Response waiting time on a particular request. If a response is not received during this time, the request is aborted
*/
private  int _timeout = Integer.MIN_VALUE;

private  boolean _timeoutSet = false;


public HtmlVerticalScroller(){
setRendererType("com.exadel.siperian.VerticalScrollerRenderer");
}

public boolean isAjaxSingle(){
	if (this._ajaxSingleSet) {
	    return (this._ajaxSingle);
	}
	ValueExpression ve = getValueExpression("ajaxSingle");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._ajaxSingle);
	    }
	    
	    return value;
	} else {
	    return (this._ajaxSingle);
	}

}

public void setAjaxSingle(boolean _ajaxSingle){
this._ajaxSingle = _ajaxSingle;
this._ajaxSingleSet = true;
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

    return "center";
	

}

public void setAlign(String _align){
this._align = _align;
}

public String getBoundaryControls(){
	if (this._boundaryControls != null) {
		return this._boundaryControls;
	}
	ValueExpression ve = getValueExpression("boundaryControls");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "show";
	

}

public void setBoundaryControls(String _boundaryControls){
this._boundaryControls = _boundaryControls;
}

public boolean isBypassUpdates(){
	if (this._bypassUpdatesSet) {
	    return (this._bypassUpdates);
	}
	ValueExpression ve = getValueExpression("bypassUpdates");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._bypassUpdates);
	    }
	    
	    return value;
	} else {
	    return (this._bypassUpdates);
	}

}

public void setBypassUpdates(boolean _bypassUpdates){
this._bypassUpdates = _bypassUpdates;
this._bypassUpdatesSet = true;
}

public Object getData(){
	if (this._data != null) {
		return this._data;
	}
	ValueExpression ve = getValueExpression("data");
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

public void setData(Object _data){
this._data = _data;
}

public String getEventsQueue(){
	if (this._eventsQueue != null) {
		return this._eventsQueue;
	}
	ValueExpression ve = getValueExpression("eventsQueue");
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

public void setEventsQueue(String _eventsQueue){
this._eventsQueue = _eventsQueue;
}

public String getFastControls(){
	if (this._fastControls != null) {
		return this._fastControls;
	}
	ValueExpression ve = getValueExpression("fastControls");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "show";
	

}

public void setFastControls(String _fastControls){
this._fastControls = _fastControls;
}

public int getFastStep(){
	if (this._fastStepSet) {
	    return (this._fastStep);
	}
	ValueExpression ve = getValueExpression("fastStep");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._fastStep);
	    }
	    
	    return value;
	} else {
	    return (this._fastStep);
	}

}

public void setFastStep(int _fastStep){
this._fastStep = _fastStep;
this._fastStepSet = true;
}

public String getFocus(){
	if (this._focus != null) {
		return this._focus;
	}
	ValueExpression ve = getValueExpression("focus");
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

public void setFocus(String _focus){
this._focus = _focus;
}

public String getFor(){
	if (this._for != null) {
		return this._for;
	}
	ValueExpression ve = getValueExpression("for");
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

public void setFor(String _for){
this._for = _for;
}

public String getHandleValue(){
	if (this._handleValue != null) {
		return this._handleValue;
	}
	ValueExpression ve = getValueExpression("handleValue");
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

public void setHandleValue(String _handleValue){
this._handleValue = _handleValue;
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

public boolean isIgnoreDupResponses(){
	if (this._ignoreDupResponsesSet) {
	    return (this._ignoreDupResponses);
	}
	ValueExpression ve = getValueExpression("ignoreDupResponses");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._ignoreDupResponses);
	    }
	    
	    return value;
	} else {
	    return (this._ignoreDupResponses);
	}

}

public void setIgnoreDupResponses(boolean _ignoreDupResponses){
this._ignoreDupResponses = _ignoreDupResponses;
this._ignoreDupResponsesSet = true;
}

public String getInactiveStyle(){
	if (this._inactiveStyle != null) {
		return this._inactiveStyle;
	}
	ValueExpression ve = getValueExpression("inactiveStyle");
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

public void setInactiveStyle(String _inactiveStyle){
this._inactiveStyle = _inactiveStyle;
}

public String getInactiveStyleClass(){
	if (this._inactiveStyleClass != null) {
		return this._inactiveStyleClass;
	}
	ValueExpression ve = getValueExpression("inactiveStyleClass");
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

public void setInactiveStyleClass(String _inactiveStyleClass){
this._inactiveStyleClass = _inactiveStyleClass;
}

public boolean isLimitToList(){
	if (this._limitToListSet) {
	    return (this._limitToList);
	}
	ValueExpression ve = getValueExpression("limitToList");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._limitToList);
	    }
	    
	    return value;
	} else {
	    return (this._limitToList);
	}

}

public void setLimitToList(boolean _limitToList){
this._limitToList = _limitToList;
this._limitToListSet = true;
}

public String getOnbeforedomupdate(){
	if (this._onbeforedomupdate != null) {
		return this._onbeforedomupdate;
	}
	ValueExpression ve = getValueExpression("onbeforedomupdate");
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

public void setOnbeforedomupdate(String _onbeforedomupdate){
this._onbeforedomupdate = _onbeforedomupdate;
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

public String getOncomplete(){
	if (this._oncomplete != null) {
		return this._oncomplete;
	}
	ValueExpression ve = getValueExpression("oncomplete");
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

public void setOncomplete(String _oncomplete){
this._oncomplete = _oncomplete;
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

public String getOnpagechange(){
	if (this._onpagechange != null) {
		return this._onpagechange;
	}
	ValueExpression ve = getValueExpression("onpagechange");
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

public void setOnpagechange(String _onpagechange){
this._onpagechange = _onpagechange;
}

public String getPageIndexVar(){
	if (this._pageIndexVar != null) {
		return this._pageIndexVar;
	}
	ValueExpression ve = getValueExpression("pageIndexVar");
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

public void setPageIndexVar(String _pageIndexVar){
this._pageIndexVar = _pageIndexVar;
}

public String getPagesVar(){
	if (this._pagesVar != null) {
		return this._pagesVar;
	}
	ValueExpression ve = getValueExpression("pagesVar");
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

public void setPagesVar(String _pagesVar){
this._pagesVar = _pagesVar;
}

public Object getProcess(){
	if (this._process != null) {
		return this._process;
	}
	ValueExpression ve = getValueExpression("process");
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

public void setProcess(Object _process){
this._process = _process;
}

public Object getReRender(){
	if (this._reRender != null) {
		return this._reRender;
	}
	ValueExpression ve = getValueExpression("reRender");
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

public void setReRender(Object _reRender){
this._reRender = _reRender;
}

public boolean isRenderIfSinglePage(){
	if (this._renderIfSinglePageSet) {
	    return (this._renderIfSinglePage);
	}
	ValueExpression ve = getValueExpression("renderIfSinglePage");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._renderIfSinglePage);
	    }
	    
	    return value;
	} else {
	    return (this._renderIfSinglePage);
	}

}

public void setRenderIfSinglePage(boolean _renderIfSinglePage){
this._renderIfSinglePage = _renderIfSinglePage;
this._renderIfSinglePageSet = true;
}

public int getRequestDelay(){
	if (this._requestDelaySet) {
	    return (this._requestDelay);
	}
	ValueExpression ve = getValueExpression("requestDelay");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._requestDelay);
	    }
	    
	    return value;
	} else {
	    return (this._requestDelay);
	}

}

public void setRequestDelay(int _requestDelay){
this._requestDelay = _requestDelay;
this._requestDelaySet = true;
}

public MethodExpression getScrollerListener(){
return _scrollerListener;
}

public void setScrollerListener(MethodExpression _scrollerListener){
this._scrollerListener = _scrollerListener;
}

public String getSelectedStyle(){
	if (this._selectedStyle != null) {
		return this._selectedStyle;
	}
	ValueExpression ve = getValueExpression("selectedStyle");
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

public void setSelectedStyle(String _selectedStyle){
this._selectedStyle = _selectedStyle;
}

public String getSelectedStyleClass(){
	if (this._selectedStyleClass != null) {
		return this._selectedStyleClass;
	}
	ValueExpression ve = getValueExpression("selectedStyleClass");
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

public void setSelectedStyleClass(String _selectedStyleClass){
this._selectedStyleClass = _selectedStyleClass;
}

public String getSimilarityGroupingId(){
	if (this._similarityGroupingId != null) {
		return this._similarityGroupingId;
	}
	ValueExpression ve = getValueExpression("similarityGroupingId");
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

public void setSimilarityGroupingId(String _similarityGroupingId){
this._similarityGroupingId = _similarityGroupingId;
}

public String getStatus(){
	if (this._status != null) {
		return this._status;
	}
	ValueExpression ve = getValueExpression("status");
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

public void setStatus(String _status){
this._status = _status;
}

public String getStepControls(){
	if (this._stepControls != null) {
		return this._stepControls;
	}
	ValueExpression ve = getValueExpression("stepControls");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "show";
	

}

public void setStepControls(String _stepControls){
this._stepControls = _stepControls;
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

public String getTableStyle(){
	if (this._tableStyle != null) {
		return this._tableStyle;
	}
	ValueExpression ve = getValueExpression("tableStyle");
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

public void setTableStyle(String _tableStyle){
this._tableStyle = _tableStyle;
}

public String getTableStyleClass(){
	if (this._tableStyleClass != null) {
		return this._tableStyleClass;
	}
	ValueExpression ve = getValueExpression("tableStyleClass");
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

public void setTableStyleClass(String _tableStyleClass){
this._tableStyleClass = _tableStyleClass;
}

public int getTimeout(){
	if (this._timeoutSet) {
	    return (this._timeout);
	}
	ValueExpression ve = getValueExpression("timeout");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._timeout);
	    }
	    
	    return value;
	} else {
	    return (this._timeout);
	}

}

public void setTimeout(int _timeout){
this._timeout = _timeout;
this._timeoutSet = true;
}

public String getFamily(){
return COMPONENT_FAMILY;
}

@Override
public Object saveState(FacesContext context){
Object [] state = new Object[55];
state[0] = super.saveState(context);
state[1] = Boolean.valueOf(_ajaxSingle);
state[2] = Boolean.valueOf(_ajaxSingleSet);
state[3] = _align;
state[4] = _boundaryControls;
state[5] = Boolean.valueOf(_bypassUpdates);
state[6] = Boolean.valueOf(_bypassUpdatesSet);
state[7] = saveAttachedState(context, _data);
state[8] = _eventsQueue;
state[9] = _fastControls;
state[10] = Integer.valueOf(_fastStep);
state[11] = Boolean.valueOf(_fastStepSet);
state[12] = _focus;
state[13] = _for;
state[14] = _handleValue;
state[15] = _height;
state[16] = Boolean.valueOf(_ignoreDupResponses);
state[17] = Boolean.valueOf(_ignoreDupResponsesSet);
state[18] = _inactiveStyle;
state[19] = _inactiveStyleClass;
state[20] = Boolean.valueOf(_limitToList);
state[21] = Boolean.valueOf(_limitToListSet);
state[22] = _onbeforedomupdate;
state[23] = _onclick;
state[24] = _oncomplete;
state[25] = _ondblclick;
state[26] = _onkeydown;
state[27] = _onkeypress;
state[28] = _onkeyup;
state[29] = _onmousedown;
state[30] = _onmousemove;
state[31] = _onmouseout;
state[32] = _onmouseover;
state[33] = _onmouseup;
state[34] = _onpagechange;
state[35] = _pageIndexVar;
state[36] = _pagesVar;
state[37] = saveAttachedState(context, _process);
state[38] = saveAttachedState(context, _reRender);
state[39] = Boolean.valueOf(_renderIfSinglePage);
state[40] = Boolean.valueOf(_renderIfSinglePageSet);
state[41] = Integer.valueOf(_requestDelay);
state[42] = Boolean.valueOf(_requestDelaySet);
state[43] = saveAttachedState(context, _scrollerListener);
state[44] = _selectedStyle;
state[45] = _selectedStyleClass;
state[46] = _similarityGroupingId;
state[47] = _status;
state[48] = _stepControls;
state[49] = _style;
state[50] = _styleClass;
state[51] = _tableStyle;
state[52] = _tableStyleClass;
state[53] = Integer.valueOf(_timeout);
state[54] = Boolean.valueOf(_timeoutSet);
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_ajaxSingle = ((Boolean)states[1]).booleanValue();
		_ajaxSingleSet = ((Boolean)states[2]).booleanValue();
		_align = (String)states[3];;
		_boundaryControls = (String)states[4];;
		_bypassUpdates = ((Boolean)states[5]).booleanValue();
		_bypassUpdatesSet = ((Boolean)states[6]).booleanValue();
		_data = (Object)restoreAttachedState(context, states[7]);
		_eventsQueue = (String)states[8];;
		_fastControls = (String)states[9];;
		_fastStep = ((Integer)states[10]).intValue();
		_fastStepSet = ((Boolean)states[11]).booleanValue();
		_focus = (String)states[12];;
		_for = (String)states[13];;
		_handleValue = (String)states[14];;
		_height = (String)states[15];;
		_ignoreDupResponses = ((Boolean)states[16]).booleanValue();
		_ignoreDupResponsesSet = ((Boolean)states[17]).booleanValue();
		_inactiveStyle = (String)states[18];;
		_inactiveStyleClass = (String)states[19];;
		_limitToList = ((Boolean)states[20]).booleanValue();
		_limitToListSet = ((Boolean)states[21]).booleanValue();
		_onbeforedomupdate = (String)states[22];;
		_onclick = (String)states[23];;
		_oncomplete = (String)states[24];;
		_ondblclick = (String)states[25];;
		_onkeydown = (String)states[26];;
		_onkeypress = (String)states[27];;
		_onkeyup = (String)states[28];;
		_onmousedown = (String)states[29];;
		_onmousemove = (String)states[30];;
		_onmouseout = (String)states[31];;
		_onmouseover = (String)states[32];;
		_onmouseup = (String)states[33];;
		_onpagechange = (String)states[34];;
		_pageIndexVar = (String)states[35];;
		_pagesVar = (String)states[36];;
		_process = (Object)restoreAttachedState(context, states[37]);
		_reRender = (Object)restoreAttachedState(context, states[38]);
		_renderIfSinglePage = ((Boolean)states[39]).booleanValue();
		_renderIfSinglePageSet = ((Boolean)states[40]).booleanValue();
		_requestDelay = ((Integer)states[41]).intValue();
		_requestDelaySet = ((Boolean)states[42]).booleanValue();
		_scrollerListener = (MethodExpression)restoreAttachedState(context, states[43]);
		_selectedStyle = (String)states[44];;
		_selectedStyleClass = (String)states[45];;
		_similarityGroupingId = (String)states[46];;
		_status = (String)states[47];;
		_stepControls = (String)states[48];;
		_style = (String)states[49];;
		_styleClass = (String)states[50];;
		_tableStyle = (String)states[51];;
		_tableStyleClass = (String)states[52];;
		_timeout = ((Integer)states[53]).intValue();
		_timeoutSet = ((Boolean)states[54]).booleanValue();
	
}

}
