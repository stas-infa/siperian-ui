package com.exadel.siperian.component.html;

import com.exadel.siperian.component.UIBarTimeline;
import com.exadel.siperian.model.BarTimelineDataModel;
import com.exadel.siperian.model.BarTimelineState;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

public class HtmlBarTimeline extends UIBarTimeline{

public final static  String COMPONENT_FAMILY = "com.exadel.siperian.Timeline";

public final static  String COMPONENT_TYPE = "com.exadel.siperian.BarTimeline";

/*
* barTimelineState
*/
private  BarTimelineState _barTimelineState = null;

/*
* null
*/
private  BarTimelineDataModel _dataModel = null;

/*
* null
*/
private  String _flashWmode = null;

/*
* header
*/
private  String _header = null;

/*
* null
*/
private  String _height = null;

/*
* null
*/
private  Object _locale = null;

/*
* monthLabels
*/
private  Object _monthLabels = null;

/*
* monthLabelsShort
*/
private  Object _monthLabelsShort = null;

/*
* js handler
*/
private  String _oncontextmenu = null;

/*
* js handler
*/
private  String _ontimelineinit = null;

/*
* js handler
*/
private  String _onzoomerchange = null;

/*
* null
*/
private  String _styleClass = null;

/*
* path to the file where template for the group tooltip located.
*/
private  String _timelineStylesheet = null;

/*
* weekDayLabels
*/
private  Object _weekDayLabels = null;

/*
* weekDayLabelsShort
*/
private  Object _weekDayLabelsShort = null;

/*
* null
*/
private  String _width = null;


public HtmlBarTimeline(){
setRendererType("com.exadel.siperian.BarTimelineRenderer");
}

public BarTimelineState getBarTimelineState(){
	if (this._barTimelineState != null) {
		return this._barTimelineState;
	}
	ValueExpression ve = getValueExpression("barTimelineState");
	if (ve != null) {
	    BarTimelineState value = null;
	    
	    try {
			value = (BarTimelineState) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setBarTimelineState(BarTimelineState _barTimelineState){
this._barTimelineState = _barTimelineState;
}

public BarTimelineDataModel getDataModel(){
	if (this._dataModel != null) {
		return this._dataModel;
	}
	ValueExpression ve = getValueExpression("dataModel");
	if (ve != null) {
	    BarTimelineDataModel value = null;
	    
	    try {
			value = (BarTimelineDataModel) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setDataModel(BarTimelineDataModel _dataModel){
this._dataModel = _dataModel;
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

public Object getLocale(){
	if (this._locale != null) {
		return this._locale;
	}
	ValueExpression ve = getValueExpression("locale");
	if (ve != null) {
	    Object value = null;
	    
	    try {
			value = (Object) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "en_EN";
	

}

public void setLocale(Object _locale){
this._locale = _locale;
}

public Object getMonthLabels(){
	if (this._monthLabels != null) {
		return this._monthLabels;
	}
	ValueExpression ve = getValueExpression("monthLabels");
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

public void setMonthLabels(Object _monthLabels){
this._monthLabels = _monthLabels;
}

public Object getMonthLabelsShort(){
	if (this._monthLabelsShort != null) {
		return this._monthLabelsShort;
	}
	ValueExpression ve = getValueExpression("monthLabelsShort");
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

public void setMonthLabelsShort(Object _monthLabelsShort){
this._monthLabelsShort = _monthLabelsShort;
}

public String getOncontextmenu(){
	if (this._oncontextmenu != null) {
		return this._oncontextmenu;
	}
	ValueExpression ve = getValueExpression("oncontextmenu");
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

public void setOncontextmenu(String _oncontextmenu){
this._oncontextmenu = _oncontextmenu;
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

public String getTimelineStylesheet(){
	if (this._timelineStylesheet != null) {
		return this._timelineStylesheet;
	}
	ValueExpression ve = getValueExpression("timelineStylesheet");
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

public void setTimelineStylesheet(String _timelineStylesheet){
this._timelineStylesheet = _timelineStylesheet;
}

public Object getWeekDayLabels(){
	if (this._weekDayLabels != null) {
		return this._weekDayLabels;
	}
	ValueExpression ve = getValueExpression("weekDayLabels");
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

public void setWeekDayLabels(Object _weekDayLabels){
this._weekDayLabels = _weekDayLabels;
}

public Object getWeekDayLabelsShort(){
	if (this._weekDayLabelsShort != null) {
		return this._weekDayLabelsShort;
	}
	ValueExpression ve = getValueExpression("weekDayLabelsShort");
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

public void setWeekDayLabelsShort(Object _weekDayLabelsShort){
this._weekDayLabelsShort = _weekDayLabelsShort;
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
Object [] state = new Object[17];
state[0] = super.saveState(context);
state[1] = saveAttachedState(context, _barTimelineState);
state[2] = saveAttachedState(context, _dataModel);
state[3] = _flashWmode;
state[4] = _header;
state[5] = _height;
state[6] = saveAttachedState(context, _locale);
state[7] = saveAttachedState(context, _monthLabels);
state[8] = saveAttachedState(context, _monthLabelsShort);
state[9] = _oncontextmenu;
state[10] = _ontimelineinit;
state[11] = _onzoomerchange;
state[12] = _styleClass;
state[13] = _timelineStylesheet;
state[14] = saveAttachedState(context, _weekDayLabels);
state[15] = saveAttachedState(context, _weekDayLabelsShort);
state[16] = _width;
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_barTimelineState = (BarTimelineState)restoreAttachedState(context, states[1]);
		_dataModel = (BarTimelineDataModel)restoreAttachedState(context, states[2]);
		_flashWmode = (String)states[3];;
		_header = (String)states[4];;
		_height = (String)states[5];;
		_locale = (Object)restoreAttachedState(context, states[6]);
		_monthLabels = (Object)restoreAttachedState(context, states[7]);
		_monthLabelsShort = (Object)restoreAttachedState(context, states[8]);
		_oncontextmenu = (String)states[9];;
		_ontimelineinit = (String)states[10];;
		_onzoomerchange = (String)states[11];;
		_styleClass = (String)states[12];;
		_timelineStylesheet = (String)states[13];;
		_weekDayLabels = (Object)restoreAttachedState(context, states[14]);
		_weekDayLabelsShort = (Object)restoreAttachedState(context, states[15]);
		_width = (String)states[16];;
	
}

}
