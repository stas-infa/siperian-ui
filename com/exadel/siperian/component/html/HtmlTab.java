package com.exadel.siperian.component.html;

import com.exadel.siperian.component.UITab;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

public class HtmlTab extends UITab{

public final static  String COMPONENT_FAMILY = "com.exadel.siperian.Tab";

public final static  String COMPONENT_TYPE = "com.exadel.siperian.Tab";

/*
* null
*/
private  boolean _active = false;

private  boolean _activeSet = false;

/*
* boolean attribute which provides possibility to limit JSF tree processing(decoding, conversion/validation, value applying) 
to the component which send the request only
*/
private  boolean _ajaxSingle = false;

private  boolean _ajaxSingleSet = false;

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
* Disables a tab in a tab panel
*/
private  boolean _disabled = false;

private  boolean _disabledSet = false;

/*
* Name of requests queue to avoid send next request before complete other from same event. Can be used to reduce number of requests of frequently events (key press, mouse move etc.)
*/
private  String _eventsQueue = null;

/*
* id of element to set focus after request completed on client side
*/
private  String _focus = null;

/*
* Attribute allows to ignore an Ajax Response produced by a request if the newest 'similar' request is
in a queue already. ignoreDupResponses="true" does not cancel the request while it is processed on the server,
but just allows to avoid unnecessary updates on the client side if the response isn't actual now
*/
private  boolean _ignoreDupResponses = false;

private  boolean _ignoreDupResponsesSet = false;

/*
* Text for the actual "tab" in a tab section
*/
private  String _label = null;

/*
* Length for the actual "tab" in a tab section defined in pixels. If it is not defined, the
	    	length is calculated basing on a tab label text length
*/
private  String _labelWidth = null;

/*
* If "true", then of all AJAX-rendered on the page components only those will be updated, 
		which ID's are passed to the "reRender" attribute of the describable component. 
		"false"-the default value-means that all components with ajaxRendered="true" will be updated.
*/
private  boolean _limitToList = false;

private  boolean _limitToListSet = false;

/*
* longRunning
*/
private  Boolean _longRunning = null;

/*
* Attribute defines tab name. Default value is "getId()".
*/
private  Object _name = null;

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
* A JavaScript event handler; a label of the tab is clicked
*/
private  String _onlabelclick = null;

/*
* A JavaScript event handler; a pointer within label is double-clicked
*/
private  String _onlabeldblclick = null;

/*
* A JavaScript event handler; a key within label is pressed down
*/
private  String _onlabelkeydown = null;

/*
* A JavaScript event handler; a key within label is pressed and released
*/
private  String _onlabelkeypress = null;

/*
* A JavaScript event handler; a key within label is released
*/
private  String _onlabelkeyup = null;

/*
* A JavaScript event handler; a pointer within label is pressed down
*/
private  String _onlabelmousedown = null;

/*
* A JavaScript event handler; a pointer is moved within label
*/
private  String _onlabelmousemove = null;

/*
* A JavaScript event handler; a pointer within label is released
*/
private  String _onlabelmouseup = null;

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
* Event must occur on the tab which has been entered
*/
private  String _ontabenter = null;

/*
* Event must occurs on the tab which has been left
*/
private  String _ontableave = null;

/*
* Id['s] (in format of call  UIComponent.findComponent()) of components, processed at the phases 2-5 in case of AjaxRequest  caused by this component. Can be single id, comma-separated list of Id's, or EL Expression  with array or Collection
*/
private  Object _process = null;

/*
* Id['s] (in format of call  UIComponent.findComponent()) of components, rendered in case of AjaxRequest  caused by this component. Can be single id, comma-separated list of Id's, or EL Expression  with array or Collection
*/
private  Object _reRender = null;

/*
* Attribute defines the time (in ms.) that the request will be wait in the queue before it is ready to send.
When the delay time is over, the request will be sent to the server or removed if the newest 'similar' request is in a queue already
*/
private  int _requestDelay = Integer.MIN_VALUE;

private  boolean _requestDelaySet = false;

/*
* If there are any component requests with identical IDs then these requests will be grouped.
*/
private  String _similarityGroupingId = null;

/*
* ID (in format of call UIComponent.findComponent()) of Request status component
*/
private  String _status = null;

/*
* CSS style(s) is/are to be applied when this component is rendered
*/
private  String _style = null;

/*
* Corresponds to the HTML class attribute
*/
private  String _styleClass = null;

/*
* Tab switch algorithm. Possible values are  "client", "server", "ajax", "page".
*/
private  String _switchType = null;

/*
* tabCloseable
*/
private  Boolean _tabCloseable = null;

/*
* Response waiting time on a particular request. If a response is not received during this time, the request is aborted
*/
private  int _timeout = Integer.MIN_VALUE;

private  boolean _timeoutSet = false;

/*
* HTML: An advisory title for this element. Often displayed as a tooltip
*/
private  String _title = null;

/*
* value
*/
private  Object _value = null;


public HtmlTab(){
setRendererType("com.exadel.siperian.TabRenderer");
}

public boolean isActive(){
	if (this._activeSet) {
	    return (this._active);
	}
	ValueExpression ve = getValueExpression("active");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._active);
	    }
	    
	    return value;
	} else {
	    return (this._active);
	}

}

public void setActive(boolean _active){
this._active = _active;
this._activeSet = true;
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

public String getLabelWidth(){
	if (this._labelWidth != null) {
		return this._labelWidth;
	}
	ValueExpression ve = getValueExpression("labelWidth");
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

public void setLabelWidth(String _labelWidth){
this._labelWidth = _labelWidth;
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

public Boolean getLongRunning(){
	if (this._longRunning != null) {
		return this._longRunning;
	}
	ValueExpression ve = getValueExpression("longRunning");
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

public void setLongRunning(Boolean _longRunning){
this._longRunning = _longRunning;
}

public Object getName(){
	if (this._name != null) {
		return this._name;
	}
	ValueExpression ve = getValueExpression("name");
	if (ve != null) {
	    Object value = null;
	    
	    try {
			value = (Object) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return getId();
	

}

public void setName(Object _name){
this._name = _name;
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

public String getOnlabelclick(){
	if (this._onlabelclick != null) {
		return this._onlabelclick;
	}
	ValueExpression ve = getValueExpression("onlabelclick");
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

public void setOnlabelclick(String _onlabelclick){
this._onlabelclick = _onlabelclick;
}

public String getOnlabeldblclick(){
	if (this._onlabeldblclick != null) {
		return this._onlabeldblclick;
	}
	ValueExpression ve = getValueExpression("onlabeldblclick");
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

public void setOnlabeldblclick(String _onlabeldblclick){
this._onlabeldblclick = _onlabeldblclick;
}

public String getOnlabelkeydown(){
	if (this._onlabelkeydown != null) {
		return this._onlabelkeydown;
	}
	ValueExpression ve = getValueExpression("onlabelkeydown");
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

public void setOnlabelkeydown(String _onlabelkeydown){
this._onlabelkeydown = _onlabelkeydown;
}

public String getOnlabelkeypress(){
	if (this._onlabelkeypress != null) {
		return this._onlabelkeypress;
	}
	ValueExpression ve = getValueExpression("onlabelkeypress");
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

public void setOnlabelkeypress(String _onlabelkeypress){
this._onlabelkeypress = _onlabelkeypress;
}

public String getOnlabelkeyup(){
	if (this._onlabelkeyup != null) {
		return this._onlabelkeyup;
	}
	ValueExpression ve = getValueExpression("onlabelkeyup");
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

public void setOnlabelkeyup(String _onlabelkeyup){
this._onlabelkeyup = _onlabelkeyup;
}

public String getOnlabelmousedown(){
	if (this._onlabelmousedown != null) {
		return this._onlabelmousedown;
	}
	ValueExpression ve = getValueExpression("onlabelmousedown");
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

public void setOnlabelmousedown(String _onlabelmousedown){
this._onlabelmousedown = _onlabelmousedown;
}

public String getOnlabelmousemove(){
	if (this._onlabelmousemove != null) {
		return this._onlabelmousemove;
	}
	ValueExpression ve = getValueExpression("onlabelmousemove");
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

public void setOnlabelmousemove(String _onlabelmousemove){
this._onlabelmousemove = _onlabelmousemove;
}

public String getOnlabelmouseup(){
	if (this._onlabelmouseup != null) {
		return this._onlabelmouseup;
	}
	ValueExpression ve = getValueExpression("onlabelmouseup");
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

public void setOnlabelmouseup(String _onlabelmouseup){
this._onlabelmouseup = _onlabelmouseup;
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

public String getOntabenter(){
	if (this._ontabenter != null) {
		return this._ontabenter;
	}
	ValueExpression ve = getValueExpression("ontabenter");
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

public void setOntabenter(String _ontabenter){
this._ontabenter = _ontabenter;
}

public String getOntableave(){
	if (this._ontableave != null) {
		return this._ontableave;
	}
	ValueExpression ve = getValueExpression("ontableave");
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

public void setOntableave(String _ontableave){
this._ontableave = _ontableave;
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

public String getSwitchType(){
	if (this._switchType != null) {
		return this._switchType;
	}
	ValueExpression ve = getValueExpression("switchType");
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

public void setSwitchType(String _switchType){
this._switchType = _switchType;
}

public Boolean getTabCloseable(){
	if (this._tabCloseable != null) {
		return this._tabCloseable;
	}
	ValueExpression ve = getValueExpression("tabCloseable");
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

public void setTabCloseable(Boolean _tabCloseable){
this._tabCloseable = _tabCloseable;
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

public String getFamily(){
return COMPONENT_FAMILY;
}

@Override
public Object saveState(FacesContext context){
Object [] state = new Object[56];
state[0] = super.saveState(context);
state[1] = Boolean.valueOf(_active);
state[2] = Boolean.valueOf(_activeSet);
state[3] = Boolean.valueOf(_ajaxSingle);
state[4] = Boolean.valueOf(_ajaxSingleSet);
state[5] = Boolean.valueOf(_bypassUpdates);
state[6] = Boolean.valueOf(_bypassUpdatesSet);
state[7] = saveAttachedState(context, _data);
state[8] = Boolean.valueOf(_disabled);
state[9] = Boolean.valueOf(_disabledSet);
state[10] = _eventsQueue;
state[11] = _focus;
state[12] = Boolean.valueOf(_ignoreDupResponses);
state[13] = Boolean.valueOf(_ignoreDupResponsesSet);
state[14] = _label;
state[15] = _labelWidth;
state[16] = Boolean.valueOf(_limitToList);
state[17] = Boolean.valueOf(_limitToListSet);
state[18] = _longRunning;
state[19] = saveAttachedState(context, _name);
state[20] = _onbeforedomupdate;
state[21] = _onclick;
state[22] = _oncomplete;
state[23] = _ondblclick;
state[24] = _onkeydown;
state[25] = _onkeypress;
state[26] = _onkeyup;
state[27] = _onlabelclick;
state[28] = _onlabeldblclick;
state[29] = _onlabelkeydown;
state[30] = _onlabelkeypress;
state[31] = _onlabelkeyup;
state[32] = _onlabelmousedown;
state[33] = _onlabelmousemove;
state[34] = _onlabelmouseup;
state[35] = _onmousedown;
state[36] = _onmousemove;
state[37] = _onmouseout;
state[38] = _onmouseover;
state[39] = _onmouseup;
state[40] = _ontabenter;
state[41] = _ontableave;
state[42] = saveAttachedState(context, _process);
state[43] = saveAttachedState(context, _reRender);
state[44] = Integer.valueOf(_requestDelay);
state[45] = Boolean.valueOf(_requestDelaySet);
state[46] = _similarityGroupingId;
state[47] = _status;
state[48] = _style;
state[49] = _styleClass;
state[50] = _switchType;
state[51] = _tabCloseable;
state[52] = Integer.valueOf(_timeout);
state[53] = Boolean.valueOf(_timeoutSet);
state[54] = _title;
state[55] = saveAttachedState(context, _value);
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_active = ((Boolean)states[1]).booleanValue();
		_activeSet = ((Boolean)states[2]).booleanValue();
		_ajaxSingle = ((Boolean)states[3]).booleanValue();
		_ajaxSingleSet = ((Boolean)states[4]).booleanValue();
		_bypassUpdates = ((Boolean)states[5]).booleanValue();
		_bypassUpdatesSet = ((Boolean)states[6]).booleanValue();
		_data = (Object)restoreAttachedState(context, states[7]);
		_disabled = ((Boolean)states[8]).booleanValue();
		_disabledSet = ((Boolean)states[9]).booleanValue();
		_eventsQueue = (String)states[10];;
		_focus = (String)states[11];;
		_ignoreDupResponses = ((Boolean)states[12]).booleanValue();
		_ignoreDupResponsesSet = ((Boolean)states[13]).booleanValue();
		_label = (String)states[14];;
		_labelWidth = (String)states[15];;
		_limitToList = ((Boolean)states[16]).booleanValue();
		_limitToListSet = ((Boolean)states[17]).booleanValue();
		_longRunning = (Boolean)states[18];;
		_name = (Object)restoreAttachedState(context, states[19]);
		_onbeforedomupdate = (String)states[20];;
		_onclick = (String)states[21];;
		_oncomplete = (String)states[22];;
		_ondblclick = (String)states[23];;
		_onkeydown = (String)states[24];;
		_onkeypress = (String)states[25];;
		_onkeyup = (String)states[26];;
		_onlabelclick = (String)states[27];;
		_onlabeldblclick = (String)states[28];;
		_onlabelkeydown = (String)states[29];;
		_onlabelkeypress = (String)states[30];;
		_onlabelkeyup = (String)states[31];;
		_onlabelmousedown = (String)states[32];;
		_onlabelmousemove = (String)states[33];;
		_onlabelmouseup = (String)states[34];;
		_onmousedown = (String)states[35];;
		_onmousemove = (String)states[36];;
		_onmouseout = (String)states[37];;
		_onmouseover = (String)states[38];;
		_onmouseup = (String)states[39];;
		_ontabenter = (String)states[40];;
		_ontableave = (String)states[41];;
		_process = (Object)restoreAttachedState(context, states[42]);
		_reRender = (Object)restoreAttachedState(context, states[43]);
		_requestDelay = ((Integer)states[44]).intValue();
		_requestDelaySet = ((Boolean)states[45]).booleanValue();
		_similarityGroupingId = (String)states[46];;
		_status = (String)states[47];;
		_style = (String)states[48];;
		_styleClass = (String)states[49];;
		_switchType = (String)states[50];;
		_tabCloseable = (Boolean)states[51];;
		_timeout = ((Integer)states[52]).intValue();
		_timeoutSet = ((Boolean)states[53]).booleanValue();
		_title = (String)states[54];;
		_value = (Object)restoreAttachedState(context, states[55]);
	
}

}
