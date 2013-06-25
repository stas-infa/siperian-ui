/**
 * 
 */


package com.exadel.siperian.renderkit;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.ajax4jsf.component.AjaxContainer;
import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;

import com.exadel.siperian.component.UITimeline;
import com.exadel.siperian.model.DateIntervalModel;
import com.exadel.siperian.model.ITimeLineState;
import com.exadel.siperian.model.TimeLineDataModel;
import com.exadel.siperian.xml.XMLDataModelGenerator;


/**
 * The Class TimelineRenderKit.
 * 
 * @author Andrey Markavtsov, Eugene Stherbin
 */
public class TimelineRenderKit extends HeaderResourcesRendererBase {

    /**
     * 
     */
    private static final String NULL = "null";

    private static final String TIMELINE_STATE_ATTR = "timelineState";

    private static final String STATE_DELIMITER = ",";

    /** The Constant EVENT_DATE. */
    private static final String EVENT_DATE = "eventDate";

    /** The Constant STRING_DELIMITER. */
    private static final String STRING_DELIMITER = STATE_DELIMITER;

    /** The Constant DATA_MODEL_ATTRIBUTE. */
    private static final String DATA_MODEL_ATTRIBUTE = "dataModel";

    /** The Constant LAST_EVENTS_ID. */
    public static final String LAST_EVENTS_ID = "lastEvents";

    /**
     * Do decode.
     * 
     * @param component
     *            the component
     * @param context
     *            the context
     */
    @Override
    protected void doDecode(FacesContext context, UIComponent component) {
        // System.err.println("Do decode invoked");
        super.doDecode(context, component);
        final String clientId = component.getClientId(context);
        final Map<String, String> requestParameterMap = context.getExternalContext().getRequestParameterMap();
        final Object model = component.getAttributes().get(DATA_MODEL_ATTRIBUTE);
        final Object timelineState = component.getAttributes().get(TIMELINE_STATE_ATTR);
        final String id = clientId + ":" + getEventsInputSuffixId();
        final String dateId = clientId + ":" + EVENT_DATE;
        final String positionStateId = clientId + ":timelinePositionState";
        final String rulerState = clientId + ":timelineRulerState";
        final String eventsState = clientId + ":timelineEventsState";
        setState(component.getId(), context, requestParameterMap, positionStateId, rulerState, eventsState, timelineState);
        setEventIdsAndDate(requestParameterMap, model, id, dateId);

    }
    
    public static class FlashMenuItem implements Serializable {
	
		private static final long serialVersionUID = 1L;
		private String label;
		private String handler;

		public String getLabel() {
			return label;
		}

		public String getHandler() {
			return handler;
		}

		public FlashMenuItem(String label, String handler) {
			super();
			this.label = label;
			this.handler = handler;
		}

		public FlashMenuItem(String src) {
			super();
			if(src == null){
				throw new IllegalArgumentException("Can't be null");
			}
			String[] items = src.split(":");
			if(items!=null && items.length == 2){
				this.label = items[0];
				this.handler = items[1];
			}else{
				throw new IllegalArgumentException("Illegal argument :"+src);
			}

		}

	}	

    /**
     * Gets the position state.
     * 
     * @param component
     *            the component
     * @param context
     *            the context
     * 
     * @return the position state
     */
    public String getPositionState(FacesContext context, UIComponent component) {
        String rst = null;
        
        final Object model = component.getAttributes().get(TIMELINE_STATE_ATTR);
        if (model instanceof ITimeLineState) {
            final ITimeLineState state = (ITimeLineState) model;

            rst = state.getVisibleIntervalFromPosition() + STATE_DELIMITER + state.getVisibleIntervalToPosition();
        }
//        System.err.println("getPositionState() return : " + rst);
        return rst;
    }
    
    public String getEventDate(FacesContext context, UIComponent component){
        String rst = null;
        if(component.getAttributes().get(DATA_MODEL_ATTRIBUTE) instanceof TimeLineDataModel){
            final TimeLineDataModel model = (TimeLineDataModel) component.getAttributes().get(DATA_MODEL_ATTRIBUTE);
            
            rst  = ((model.getClickedEventDate() == null) ? null : String.valueOf(model.getClickedEventDate().getTime()));
        }
        return rst;
    }
    public String getEventIds(FacesContext context, UIComponent component){
        String rst = null;
        if(component.getAttributes().get(DATA_MODEL_ATTRIBUTE) instanceof TimeLineDataModel){
            final TimeLineDataModel model = (TimeLineDataModel) component.getAttributes().get(DATA_MODEL_ATTRIBUTE);
            final StringBuffer sb  = new StringBuffer();
            
            if(model.getClickedEventIds()!=null){
                int i = 0;
                int size = model.getClickedEventIds().size();
                for(String s:model.getClickedEventIds()){
                    sb.append(s);
                    i++;
                    if(i != size){
                       sb.append(",");
                    }
                  
                }
            }
            rst = sb.toString();
        }
        return rst;
    }
    public String getEventsState(FacesContext context, UIComponent component) {
        String rst = "";

        final Object model = component.getAttributes().get(TIMELINE_STATE_ATTR);
        if (model instanceof ITimeLineState) {
            final ITimeLineState state = (ITimeLineState) model;

            if ((state.getHiddenEventTypes() != null) && (state.getHiddenEventTypes().size() > 0)) {
                StringBuffer sb = new StringBuffer();
                int i = 0;
                int size = state.getHiddenEventTypes().size();
                for(String he:state.getHiddenEventTypes()){
                    sb.append(he);
              
                    if(i < size){
                        sb.append(",");
                    }
                    i++;
                    
                }
                rst = sb.toString();
                System.err.println("Rst(hidden events) = "+rst);
            }
        }
        return rst;
    }
    /**
     * Gets the vertical ruler state.
     * 
     * @param component
     *            the component
     * @param context
     *            the context
     * 
     * @return the vertical ruler state
     */
    public String getVerticalRulerState(FacesContext context, UIComponent component) {
        String rst = null;
        final Object model = component.getAttributes().get(TIMELINE_STATE_ATTR);
        if (model instanceof ITimeLineState) {
            final ITimeLineState state = (ITimeLineState) model;

            if (state.getVerticalRulerPosition() != null) {
                rst = state.getVerticalRulerPosition().toString();
            }

        }
//        System.err.println("getVerticalRulerState() return : " + rst);
        return rst;
    }

    /**
     * Sets the state.
     * 
     * @param rulerState *
     * @param rulerStateId
     *            the ruler state id
     * @param requestParameterMap
     *            the request parameter map
     * @param context
     *            the context
     * @param clientId
     *            the client id
     * @param positionStateId
     *            the position state id
     */
    private void setState(String clientId, FacesContext context, final Map<String, String> requestParameterMap,
            final String positionStateId, final String rulerStateId,final String eventsStateId, Object model) {
        if ((requestParameterMap.get(positionStateId) != null) && (model instanceof ITimeLineState)) {
            final String positionState = requestParameterMap.get(positionStateId);
            final String rulerState = requestParameterMap.get(rulerStateId);
            final String hideEventsState = requestParameterMap.get(eventsStateId);
            final ITimeLineState state = (ITimeLineState) model;

            if (positionState != null) {
                String[] states = positionState.split(STATE_DELIMITER);
                if ((states != null) && (states.length == 2) && (states[0] != null) && (states[1] != null)
                        && ((!states[0].equals(NULL)) && !states[1].equals(NULL))) {
                    try {
                        state.setVisibleIntervalFromPosition(Long.parseLong(states[0]));
                        state.setVisibleIntervalToPosition(Long.parseLong(states[1]));
                    } catch (NumberFormatException e) {
                        //e.printStackTrace();
                    }

                }
            }
            if (rulerState != null && rulerState.trim().length() > 0) {
                try {
                    state.setVerticalRulerPosition(Long.parseLong(rulerState));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    state.setVerticalRulerPosition(null);
                }
            }
            if(hideEventsState!=null && hideEventsState.trim().length() > 0){
                 String hideEvents[] = null ;
                 
                 if(hideEventsState.contains(STATE_DELIMITER)){
                     hideEvents = hideEventsState.split(STATE_DELIMITER);
                 }else{
                     hideEvents = new String[]{hideEventsState};
                 }
                 state.setHiddenEventTypes(Arrays.asList(hideEvents));
                 
            }
//            System.err.println(" setState state = from: " + state.getVisibleIntervalFromPosition() + ", to:"
//                    + state.getVisibleIntervalToPosition() + ",ruler:" + state.getVerticalRulerPosition());
        } else {
            System.err.println("Couldn't set state");
        }
    }

    /**
     * Sets the event ids and date.
     * 
     * @param dateId
     *            the date id
     * @param requestParameterMap
     *            the request parameter map
     * @param model
     *            the model
     * @param id
     *            the id
     */
    private void setEventIdsAndDate(final Map<String, String> requestParameterMap, final Object model, final String id, final String dateId) {
        if ((requestParameterMap.get(id) != null) && (model instanceof TimeLineDataModel)) {
            final TimeLineDataModel modelCasted = (TimeLineDataModel) model;
            final String eventValues = requestParameterMap.get(id);

            if (eventValues.length() > 0) {
                String[] parts = eventValues.split(STRING_DELIMITER);
                if ((parts != null) && (parts.length > 0)) {
                    modelCasted.setClickedEventIds(Arrays.asList(parts));
                }
            }
            if (requestParameterMap.get(dateId) != null) {
                final String eventDateAsStr = requestParameterMap.get(dateId);
                if (eventDateAsStr != null && eventDateAsStr.length() > 5) {
                    try {
                        final Date eventAsDate = new Date(Long.valueOf(eventDateAsStr));

                        modelCasted.setClickedEventDate(eventAsDate);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Gets the container id.
     * 
     * @param context
     *            the context
     * @param panel
     *            the panel
     * 
     * @return the container id
     */
    public Object getContainerId(FacesContext context, UITimeline panel) {
        AjaxContainer container = AjaxRendererUtils.findAjaxContainer(context, panel);
        if (container != null) {
            return ((UIComponent) container).getClientId(context);
        }
        return "";
    }

    /**
     * Gets the events input suffix id.
     * 
     * @return the events input suffix id
     */
    public String getEventsInputSuffixId() {
        return LAST_EVENTS_ID;
    }

    /**
     * Gets the component class.
     * 
     * @return the component class
     * 
     * @see org.ajax4jsf.renderkit.RendererBase#getComponentClass()
     */
    @Override
    protected Class<? extends UIComponent> getComponentClass() {
        // TODO Auto-generated method stub
        return UITimeline.class;
    }

    /**
     * Gets the xml.
     * 
     * @param component
     *            the component
     * @param context
     *            the context
     * @param defaultTemplate
     *            the default template
     * 
     * @return the xml
     * 
     * @throws IOException
     *             the IO exception
     */
    public String getXml(FacesContext context, UIComponent component, Object defaultTemplate) throws IOException {
        Object o = component.getAttributes().get(DATA_MODEL_ATTRIBUTE);
        Object from = component.getAttributes().get("fromDate");
        Object to = component.getAttributes().get("toDate");
        Object tooltipInclude = component.getAttributes().get("groupTooltipTemplate");
        Object globalMenuItems = component.getAttributes().get("globalMenuItems");
        Object eventMenuItems  = component.getAttributes().get("eventMenuItems");
        java.util.List<FlashMenuItem> globalMenuItemsAsList =new ArrayList<FlashMenuItem>();
        java.util.List<FlashMenuItem> eventMenuItemsAsList = new ArrayList<FlashMenuItem>();
        if(globalMenuItems instanceof String && eventMenuItems instanceof String){
        	String[] globalItemsElements = globalMenuItems.toString().split(";");
        	String[] eventMenuItemsElemens = eventMenuItems.toString().split(";");
        	if(globalMenuItems!=null){
        		for(String item:globalItemsElements){
        			globalMenuItemsAsList.add(new FlashMenuItem(item));
        		}
        	}
        	if(eventMenuItems!=null){
        		for(String item:eventMenuItemsElemens){
        			eventMenuItemsAsList.add(new FlashMenuItem(item));
        		}
        	}
        }
        final String eventStyleClass = (String) component.getAttributes().get("eventStyleClass");
        final String groupStyleClass = (String) component.getAttributes().get("groupStyleClass");
        final String hoveredStyleClass = (String) component.getAttributes().get("groupHoverStyleClass");
        final String groupSelectedStyleClass = (String) component.getAttributes().get("groupSelectedStyleClass");
        final Integer maxCountOfToolTips     = (Integer) component.getAttributes().get("maxDisplaedEventsInToolTip");
        final Integer zoomerIndent  = (Integer) component.getAttributes().get("zoomerIndent");
        final String  groupInterval = (String) component.getAttributes().get("eventInterval");
        
        if ((o instanceof TimeLineDataModel) && (from instanceof Date) && (to instanceof Date)) {
            TimeLineDataModel model = (TimeLineDataModel) o;
            Date dateFrom = (Date) from;
            Date dateTo = (Date) to;
            final String groupTooltipTemplate = getTooltipTemplate(tooltipInclude == null ? defaultTemplate.toString() : tooltipInclude
                    .toString());
            String newGroupTooltipTemplate = groupTooltipTemplate;
            try {
                newGroupTooltipTemplate = (String) FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(
                        FacesContext.getCurrentInstance(), groupTooltipTemplate, String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            Object value = component.getAttributes().get("dateIntervals"); 
            List<DateIntervalModel> dateIntervals = value != null ? (List<DateIntervalModel>) value : null; 

            XMLDataModelGenerator generator = new XMLDataModelGenerator(model, dateIntervals, dateFrom, dateTo, newGroupTooltipTemplate, eventStyleClass, groupStyleClass,
                    hoveredStyleClass, groupSelectedStyleClass,maxCountOfToolTips,zoomerIndent,groupInterval,globalMenuItemsAsList,eventMenuItemsAsList);
            return generator.getXMLData(context);
        }
        return null;
    }

    /**
     * Gets the remote content.
     * 
     * @param path
     *            the path
     * @param req
     *            the req
     * 
     * @return the remote content
     */
    protected InputStream getRemoteContent(String path, HttpServletRequest req) {
        URL u = null;

        try {
            final String contentPath = req.getContextPath();
            String contentPathForUrl = contentPath;
            if (path.startsWith(contentPath)) {
                contentPathForUrl = "";
            }
            final String ulr = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + contentPathForUrl + path;
            u = new URL(ulr);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        InputStream is = null;
        try {
            is = u.openStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return is;

    }

    /**
     * Gets the tooltip template.
     * 
     * @param tooltipInclude
     *            the tooltip include
     * 
     * @return the tooltip template
     */
    private String getTooltipTemplate(String tooltipInclude) {
        String toolTipvalue = null;
        if (tooltipInclude != null) {
            InputStream is = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(tooltipInclude);
            if (is == null) {
                final HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                is = getRemoteContent(tooltipInclude, req);
            }
            if (is != null) {
                final InputStreamReader isr = new InputStreamReader(is);
                final BufferedReader bufReader = new BufferedReader(isr);
                String line = null;

                final BufferedInputStream bis = new BufferedInputStream(is);
                try {
                    final StringBuffer sb = new StringBuffer();

                    while ((line = bufReader.readLine()) != null) {
                        sb.append(line);
                    }
                    toolTipvalue = sb.toString();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else {
                throw new RuntimeException("Couldn't find tooltip template=[" + tooltipInclude + "] for timeline component");
            }
        }
        return toolTipvalue;
    }

}
