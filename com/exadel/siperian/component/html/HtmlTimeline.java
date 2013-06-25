package com.exadel.siperian.component.html;

import com.exadel.siperian.component.UITimeline;
import com.exadel.siperian.model.ITimeLineState;
import com.exadel.siperian.model.TimeLineDataModel;
import java.util.Date;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

public class HtmlTimeline extends UITimeline{

public final static  String COMPONENT_FAMILY = "com.exadel.siperian.Timeline";

public final static  String COMPONENT_TYPE = "com.exadel.siperian.UITimeline";

/*
* null
*/
private  TimeLineDataModel _dataModel = null;

/*
* Set of colors should be defined with list of Color objects or list of String color values
*/
private  String _eventBoxColors = null;

/*
* The attribute defines allowed percentage of superposition the Event boxes. On exceeding the value Event boxes need to be displayed as Event group
*/
private  String _eventBoxSuperposition = null;

/*
* The attribute defines width of the Event box as well as width of Group box in pixels
*/
private  Integer _eventBoxWidth = null;

/*
* Interval between two events used for groups union.
*/
private  String _eventInterval = null;

/*
* The menu identifier this menu will be show when we click by event
*/
private  String _eventMenuId = null;

/*
* Menu items for events .
*/
private  String _eventMenuItems = null;

/*
* common css style class for events on scroller
*/
private  String _eventStyleClass = null;

/*
* null
*/
private  String _flashWmode = null;

/*
* null
*/
private  Date _fromDate = null;

/*
* Global menu items for Safari browser flash menu.
*/
private  String _globalMenuItems = null;

/*
* common css style class for groups when group is hovered
*/
private  String _groupHoverStyleClass = null;

/*
* common css style class for groups when group is selected
*/
private  String _groupSelectedStyleClass = null;

/*
* common css style class for groups
*/
private  String _groupStyleClass = null;

/*
* The attribute defines content of the tooltip
*/
private  String _groupTooltipTemplate = null;

/*
* header
*/
private  String _header = null;

/*
* null
*/
private  String _height = null;

/*
* maximum count of events that will displayed in tooltip if 0(by default) then unlimited
*/
private  Integer _maxDisplaedEventsInToolTip = null;

/*
* A configurable parameter to define the smallest visible interval supported
*/
private  Integer _maximumZoomResolution = null;

/*
* defined an javascript handler that will invoke when timeline inited.
*/
private  String _ontimelineinit = null;

/*
* defined an javascript handler that will invoke when timeline zoomer change.
*/
private  String _onzoomerchange = null;

/*
* Global menu identifier
*/
private  String _popupMenuId = null;

/*
* if true then if user not have a flash player will display link to the flash player. By default false
*/
private  boolean _showFlashPlayerInstall = true;

private  boolean _showFlashPlayerInstallSet = false;

/*
* null
*/
private  String _styleClass = null;

/*
* swinlane css class
*/
private  String _swimLaneClass = null;

/*
* store state for the timeline component.
*/
private  ITimeLineState _timelineState = null;

/*
* path to the file where template for the group tooltip located.
*/
private  String _timlineStylesheet = null;

/*
* null
*/
private  Date _toDate = null;

/*
* null
*/
private  String _width = null;

/*
* zoomer indent from first event in timeline. By default 10
*/
private  Integer _zoomerIndent = null;


public HtmlTimeline(){
setRendererType("com.exadel.siperian.TimelineRenderer");
}

public TimeLineDataModel getDataModel(){
	if (this._dataModel != null) {
		return this._dataModel;
	}
	ValueExpression ve = getValueExpression("dataModel");
	if (ve != null) {
	    TimeLineDataModel value = null;
	    
	    try {
			value = (TimeLineDataModel) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setDataModel(TimeLineDataModel _dataModel){
this._dataModel = _dataModel;
}

public String getEventBoxColors(){
	if (this._eventBoxColors != null) {
		return this._eventBoxColors;
	}
	ValueExpression ve = getValueExpression("eventBoxColors");
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

public void setEventBoxColors(String _eventBoxColors){
this._eventBoxColors = _eventBoxColors;
}

public String getEventBoxSuperposition(){
	if (this._eventBoxSuperposition != null) {
		return this._eventBoxSuperposition;
	}
	ValueExpression ve = getValueExpression("eventBoxSuperposition");
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

public void setEventBoxSuperposition(String _eventBoxSuperposition){
this._eventBoxSuperposition = _eventBoxSuperposition;
}

public Integer getEventBoxWidth(){
	if (this._eventBoxWidth != null) {
		return this._eventBoxWidth;
	}
	ValueExpression ve = getValueExpression("eventBoxWidth");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setEventBoxWidth(Integer _eventBoxWidth){
this._eventBoxWidth = _eventBoxWidth;
}

public String getEventInterval(){
	if (this._eventInterval != null) {
		return this._eventInterval;
	}
	ValueExpression ve = getValueExpression("eventInterval");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "0";
	

}

public void setEventInterval(String _eventInterval){
this._eventInterval = _eventInterval;
}

public String getEventMenuId(){
	if (this._eventMenuId != null) {
		return this._eventMenuId;
	}
	ValueExpression ve = getValueExpression("eventMenuId");
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

public void setEventMenuId(String _eventMenuId){
this._eventMenuId = _eventMenuId;
}

public String getEventMenuItems(){
	if (this._eventMenuItems != null) {
		return this._eventMenuItems;
	}
	ValueExpression ve = getValueExpression("eventMenuItems");
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

public void setEventMenuItems(String _eventMenuItems){
this._eventMenuItems = _eventMenuItems;
}

public String getEventStyleClass(){
	if (this._eventStyleClass != null) {
		return this._eventStyleClass;
	}
	ValueExpression ve = getValueExpression("eventStyleClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "sip-timeline-event";
	

}

public void setEventStyleClass(String _eventStyleClass){
this._eventStyleClass = _eventStyleClass;
}

public String getFlashWmode(){
	if (this._flashWmode != null) {
		return this._flashWmode;
	}
	ValueExpression ve = getValueExpression("flashWmode");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "transparent";
	

}

public void setFlashWmode(String _flashWmode){
this._flashWmode = _flashWmode;
}

public Date getFromDate(){
	if (this._fromDate != null) {
		return this._fromDate;
	}
	ValueExpression ve = getValueExpression("fromDate");
	if (ve != null) {
	    Date value = null;
	    
	    try {
			value = (Date) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setFromDate(Date _fromDate){
this._fromDate = _fromDate;
}

public String getGlobalMenuItems(){
	if (this._globalMenuItems != null) {
		return this._globalMenuItems;
	}
	ValueExpression ve = getValueExpression("globalMenuItems");
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

public void setGlobalMenuItems(String _globalMenuItems){
this._globalMenuItems = _globalMenuItems;
}

public String getGroupHoverStyleClass(){
	if (this._groupHoverStyleClass != null) {
		return this._groupHoverStyleClass;
	}
	ValueExpression ve = getValueExpression("groupHoverStyleClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "sip-timeline-grouphover";
	

}

public void setGroupHoverStyleClass(String _groupHoverStyleClass){
this._groupHoverStyleClass = _groupHoverStyleClass;
}

public String getGroupSelectedStyleClass(){
	if (this._groupSelectedStyleClass != null) {
		return this._groupSelectedStyleClass;
	}
	ValueExpression ve = getValueExpression("groupSelectedStyleClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "sip-timeline-groupselect";
	

}

public void setGroupSelectedStyleClass(String _groupSelectedStyleClass){
this._groupSelectedStyleClass = _groupSelectedStyleClass;
}

public String getGroupStyleClass(){
	if (this._groupStyleClass != null) {
		return this._groupStyleClass;
	}
	ValueExpression ve = getValueExpression("groupStyleClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "sip-timeline-group";
	

}

public void setGroupStyleClass(String _groupStyleClass){
this._groupStyleClass = _groupStyleClass;
}

public String getGroupTooltipTemplate(){
	if (this._groupTooltipTemplate != null) {
		return this._groupTooltipTemplate;
	}
	ValueExpression ve = getValueExpression("groupTooltipTemplate");
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

public void setGroupTooltipTemplate(String _groupTooltipTemplate){
this._groupTooltipTemplate = _groupTooltipTemplate;
}

public String getHeader(){
	if (this._header != null) {
		return this._header;
	}
	ValueExpression ve = getValueExpression("header");
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

public void setHeader(String _header){
this._header = _header;
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

    return "250";
	

}

public void setHeight(String _height){
this._height = _height;
}

public Integer getMaxDisplaedEventsInToolTip(){
	if (this._maxDisplaedEventsInToolTip != null) {
		return this._maxDisplaedEventsInToolTip;
	}
	ValueExpression ve = getValueExpression("maxDisplaedEventsInToolTip");
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

public void setMaxDisplaedEventsInToolTip(Integer _maxDisplaedEventsInToolTip){
this._maxDisplaedEventsInToolTip = _maxDisplaedEventsInToolTip;
}

public Integer getMaximumZoomResolution(){
	if (this._maximumZoomResolution != null) {
		return this._maximumZoomResolution;
	}
	ValueExpression ve = getValueExpression("maximumZoomResolution");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setMaximumZoomResolution(Integer _maximumZoomResolution){
this._maximumZoomResolution = _maximumZoomResolution;
}

public String getOntimelineinit(){
	if (this._ontimelineinit != null) {
		return this._ontimelineinit;
	}
	ValueExpression ve = getValueExpression("ontimelineinit");
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

public void setOntimelineinit(String _ontimelineinit){
this._ontimelineinit = _ontimelineinit;
}

public String getOnzoomerchange(){
	if (this._onzoomerchange != null) {
		return this._onzoomerchange;
	}
	ValueExpression ve = getValueExpression("onzoomerchange");
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

public void setOnzoomerchange(String _onzoomerchange){
this._onzoomerchange = _onzoomerchange;
}

public String getPopupMenuId(){
	if (this._popupMenuId != null) {
		return this._popupMenuId;
	}
	ValueExpression ve = getValueExpression("popupMenuId");
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

public void setPopupMenuId(String _popupMenuId){
this._popupMenuId = _popupMenuId;
}

public boolean isShowFlashPlayerInstall(){
	if (this._showFlashPlayerInstallSet) {
	    return (this._showFlashPlayerInstall);
	}
	ValueExpression ve = getValueExpression("showFlashPlayerInstall");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    if (null == value) {
			return (this._showFlashPlayerInstall);
	    }
	    
	    return value;
	} else {
	    return (this._showFlashPlayerInstall);
	}

}

public void setShowFlashPlayerInstall(boolean _showFlashPlayerInstall){
this._showFlashPlayerInstall = _showFlashPlayerInstall;
this._showFlashPlayerInstallSet = true;
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

public String getSwimLaneClass(){
	if (this._swimLaneClass != null) {
		return this._swimLaneClass;
	}
	ValueExpression ve = getValueExpression("swimLaneClass");
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

public void setSwimLaneClass(String _swimLaneClass){
this._swimLaneClass = _swimLaneClass;
}

public ITimeLineState getTimelineState(){
	if (this._timelineState != null) {
		return this._timelineState;
	}
	ValueExpression ve = getValueExpression("timelineState");
	if (ve != null) {
	    ITimeLineState value = null;
	    
	    try {
			value = (ITimeLineState) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setTimelineState(ITimeLineState _timelineState){
this._timelineState = _timelineState;
}

public String getTimlineStylesheet(){
	if (this._timlineStylesheet != null) {
		return this._timlineStylesheet;
	}
	ValueExpression ve = getValueExpression("timlineStylesheet");
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

public void setTimlineStylesheet(String _timlineStylesheet){
this._timlineStylesheet = _timlineStylesheet;
}

public Date getToDate(){
	if (this._toDate != null) {
		return this._toDate;
	}
	ValueExpression ve = getValueExpression("toDate");
	if (ve != null) {
	    Date value = null;
	    
	    try {
			value = (Date) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setToDate(Date _toDate){
this._toDate = _toDate;
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

public Integer getZoomerIndent(){
	if (this._zoomerIndent != null) {
		return this._zoomerIndent;
	}
	ValueExpression ve = getValueExpression("zoomerIndent");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return 10;
	

}

public void setZoomerIndent(Integer _zoomerIndent){
this._zoomerIndent = _zoomerIndent;
}

public String getFamily(){
return COMPONENT_FAMILY;
}

@Override
public Object saveState(FacesContext context){
Object [] state = new Object[32];
state[0] = super.saveState(context);
state[1] = saveAttachedState(context, _dataModel);
state[2] = _eventBoxColors;
state[3] = _eventBoxSuperposition;
state[4] = _eventBoxWidth;
state[5] = _eventInterval;
state[6] = _eventMenuId;
state[7] = _eventMenuItems;
state[8] = _eventStyleClass;
state[9] = _flashWmode;
state[10] = saveAttachedState(context, _fromDate);
state[11] = _globalMenuItems;
state[12] = _groupHoverStyleClass;
state[13] = _groupSelectedStyleClass;
state[14] = _groupStyleClass;
state[15] = _groupTooltipTemplate;
state[16] = _header;
state[17] = _height;
state[18] = _maxDisplaedEventsInToolTip;
state[19] = _maximumZoomResolution;
state[20] = _ontimelineinit;
state[21] = _onzoomerchange;
state[22] = _popupMenuId;
state[23] = Boolean.valueOf(_showFlashPlayerInstall);
state[24] = Boolean.valueOf(_showFlashPlayerInstallSet);
state[25] = _styleClass;
state[26] = _swimLaneClass;
state[27] = saveAttachedState(context, _timelineState);
state[28] = _timlineStylesheet;
state[29] = saveAttachedState(context, _toDate);
state[30] = _width;
state[31] = _zoomerIndent;
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_dataModel = (TimeLineDataModel)restoreAttachedState(context, states[1]);
		_eventBoxColors = (String)states[2];;
		_eventBoxSuperposition = (String)states[3];;
		_eventBoxWidth = (Integer)states[4];;
		_eventInterval = (String)states[5];;
		_eventMenuId = (String)states[6];;
		_eventMenuItems = (String)states[7];;
		_eventStyleClass = (String)states[8];;
		_flashWmode = (String)states[9];;
		_fromDate = (Date)restoreAttachedState(context, states[10]);
		_globalMenuItems = (String)states[11];;
		_groupHoverStyleClass = (String)states[12];;
		_groupSelectedStyleClass = (String)states[13];;
		_groupStyleClass = (String)states[14];;
		_groupTooltipTemplate = (String)states[15];;
		_header = (String)states[16];;
		_height = (String)states[17];;
		_maxDisplaedEventsInToolTip = (Integer)states[18];;
		_maximumZoomResolution = (Integer)states[19];;
		_ontimelineinit = (String)states[20];;
		_onzoomerchange = (String)states[21];;
		_popupMenuId = (String)states[22];;
		_showFlashPlayerInstall = ((Boolean)states[23]).booleanValue();
		_showFlashPlayerInstallSet = ((Boolean)states[24]).booleanValue();
		_styleClass = (String)states[25];;
		_swimLaneClass = (String)states[26];;
		_timelineState = (ITimeLineState)restoreAttachedState(context, states[27]);
		_timlineStylesheet = (String)states[28];;
		_toDate = (Date)restoreAttachedState(context, states[29]);
		_width = (String)states[30];;
		_zoomerIndent = (Integer)states[31];;
	
}

}
