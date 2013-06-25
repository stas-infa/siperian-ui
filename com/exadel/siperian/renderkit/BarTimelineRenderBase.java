package com.exadel.siperian.renderkit;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;
import org.ajax4jsf.resource.InternetResource;
import org.richfaces.component.util.ComponentUtil;
import org.richfaces.renderkit.ScriptOptions;

import com.exadel.siperian.component.UIBarTimeline;
import com.exadel.siperian.model.BarTimelineDataModel;
import com.exadel.siperian.model.BarTimelineState;
import com.exadel.siperian.xml.BarTimelineXmlDataModelGenerator;

public class BarTimelineRenderBase extends HeaderResourcesRendererBase{
	
	public static final String DATA_MODEL_ATTRIBUTE = "dataModel";
	
	public static final String LOCALE_ATTRIBUTE = "locale";
    
    private static final String NULL = "null";
    
    private static final String STATE_DELIMITER = ",";


    public String getPositionState(FacesContext context, UIComponent component) {
    	BarTimelineState state = getState(component);
        return state != null ? (state.getVisibleIntervalFromPosition() + STATE_DELIMITER + state.getVisibleIntervalToPosition()) : null;
    }
    
    public String getVerticalRulerState(FacesContext context, UIComponent component) {
    	BarTimelineState state = getState(component);
    	
    	return (state != null && state.getVerticalRulerPosition() != null) ? state.getVerticalRulerPosition().toString() : null; 
    }

	public String getEventHandlers(FacesContext context, UIComponent component) {
		ScriptOptions options = new ScriptOptions(component);
		options.addEventHandler("ontimelineinit");
		options.addEventHandler("oncontextmenu");
		options.addEventHandler("onzoomerchange");
		return options.toString();
	}
  
    public String getXml(FacesContext context, UIComponent component) throws IOException {
		String xml = null;
		Object dataModel = component.getAttributes().get(DATA_MODEL_ATTRIBUTE);
        
        if(dataModel instanceof BarTimelineDataModel) {
        	Map<String, String[]> symbolsMap = getSymbolsMap(context, (UIBarTimeline)component);
			BarTimelineXmlDataModelGenerator xmlGenerator = new BarTimelineXmlDataModelGenerator((BarTimelineDataModel)dataModel, symbolsMap);
			xml = xmlGenerator.getXmlData(context);
		}
		
		return xml;
	}
    
    public String getTimelineStyleSheet(FacesContext context, UIComponent component) {
    	String path = (String)component.getAttributes().get("timelineStylesheet");
    	String uri = "";
    	
    	if(path != null && !path.isEmpty()){
    		InternetResource resource =  getResource(path);

    		if(resource != null) {
	    		uri = resource.getUri(context, component);
	    	}
    	}
    	
    	return  uri;    	
    }

    
	@Override
	protected void doDecode(FacesContext context, UIComponent component) {
		if(!(component instanceof UIBarTimeline)) {
			return;
		}
		
		ExternalContext externalContext = context.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();

		if(requestMap != null) {
			
			UIBarTimeline barTimeline = (UIBarTimeline)component;
			
			if(barTimeline.getBarTimelineState() == null) {
				barTimeline.setBarTimelineState(new BarTimelineState());
			}
			
			decodePositionState(context, barTimeline, requestMap);
			decodeVerticalRulerState(context, barTimeline, requestMap);
		}	
	}
		    
    protected Map<String, String[]> getSymbolsMap(FacesContext facesContext, UIBarTimeline barTimeline) {
		Map<String, String[]> map = new HashMap<String, String[]>();

		Locale locale = barTimeline.getAsLocale(barTimeline.getAttributes().get(LOCALE_ATTRIBUTE));

		DateFormatSymbols symbols = new DateFormatSymbols(locale);
		String[] weekDayLabels = ComponentUtil.asArray(barTimeline.getWeekDayLabels());
		if (weekDayLabels == null) {
			weekDayLabels = symbols.getWeekdays();
		}

		String[] weekDayLabelsShort = ComponentUtil.asArray(barTimeline.getWeekDayLabelsShort());
		if (weekDayLabelsShort == null) {
			weekDayLabelsShort = symbols.getShortWeekdays();
		}

		String[] monthLabels = ComponentUtil.asArray(barTimeline.getMonthLabels());
		if (monthLabels == null) {
			monthLabels = symbols.getMonths();
		}

		String[] monthLabelsShort = ComponentUtil.asArray(barTimeline.getMonthLabelsShort());
		if (monthLabelsShort == null) {
			monthLabelsShort = symbols.getShortMonths();
		}

		map.put(UIBarTimeline.WEEK_DAY_LABELS, weekDayLabels);
		map.put(UIBarTimeline.WEEK_DAY_LABELS_SHORT, weekDayLabelsShort);
		map.put(UIBarTimeline.MONTH_LABELS, monthLabels);
		map.put(UIBarTimeline.MONTH_LABELS_SHORT, monthLabelsShort);

		return map;
	}
    
    
	@Override
	protected Class<? extends UIComponent> getComponentClass() {
		return UIBarTimeline.class;
	}
	
	private BarTimelineState getState(UIComponent component) {
		BarTimelineState state = null;
		
		if(component instanceof UIBarTimeline) {
			UIBarTimeline timeline = (UIBarTimeline)component;
			state = timeline.getBarTimelineState();
		}
		
		return state;
	}
	
	private void decodePositionState(FacesContext context, UIBarTimeline timeline, Map<String, String> requestMap) {
        final String clientId = timeline.getClientId(context);
        final String positionStateId = clientId + ":timelinePositionState"; 
		
        if(requestMap.containsKey(positionStateId)) {
			String positionClientState = requestMap.get(positionStateId);
			
			if(positionClientState != null) {
				String[] states = positionClientState.split(STATE_DELIMITER);
                
				if ((states != null) && (states.length == 2) && (states[0] != null) && (states[1] != null)
                        && ((!states[0].equals(NULL)) && !states[1].equals(NULL))) {
			        BarTimelineState state = timeline.getBarTimelineState();

			        try {
                        state.setVisibleIntervalFromPosition(Long.parseLong(states[0]));
                        state.setVisibleIntervalToPosition(Long.parseLong(states[1]));
                    } catch (NumberFormatException e) {
                    	state.setVisibleIntervalFromPosition(null);
                    	state.setVisibleIntervalToPosition(null);
                    }
			        
                }
				
			}
			
		}
	}
	
	private void decodeVerticalRulerState(FacesContext context, UIBarTimeline timeline, Map<String, String> requestMap) {
		final String clientId = timeline.getClientId(context);
        final String rulerStateId = clientId + ":timelineRulerState";

        if (requestMap.containsKey(rulerStateId)) {
        	String rulerState = requestMap.get(rulerStateId);
            
        	if(rulerState != null) {
                BarTimelineState state = timeline.getBarTimelineState();

                try {
                	state.setVerticalRulerPosition(Long.parseLong(rulerState));
                } catch (NumberFormatException e) {
                	state.setVerticalRulerPosition(null);
                }
            } 
        	
        }
	}

}
