

package com.exadel.siperian.xml;


import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.exadel.siperian.model.DateIntervalModel;
import com.exadel.siperian.model.TimeLineDataModel;
import com.exadel.siperian.model.TimeLineEventDataModel;
import com.exadel.siperian.model.TimeLineEventTypeModel;
import com.exadel.siperian.model.TimeLineSwimLaneModel;
import com.exadel.siperian.renderkit.TimelineRenderKit.FlashMenuItem;


/**
 * The Class XMLDataModelGenerator.
 * 
 * @author Andrey Markavtsov , Eugene Stherbin
 */
public class XMLDataModelGenerator {

    /** The Constant COEFICIENT_IN_MINUTES. */
    private static final int COEFICIENT_IN_MINUTES = 1000 * 60;

    /** The Constant COEFICIENT_IN_HOURS. */
    private static final int COEFICIENT_IN_HOURS = COEFICIENT_IN_MINUTES * 60;

    /** The Constant COEFICIENT_IN_DAYS. */
    private static final int COEFICIENT_IN_DAYS = COEFICIENT_IN_HOURS * 24;

    /** The model. */
    private TimeLineDataModel model;

    /** The from date. */
    private Date fromDate;

    /** The to date. */
    private Date toDate;

    /** The group tooltip. */
    private String groupTooltip = null;

    /** The date intervals. */
    public DateIntervalModel[] dateIntervals;

    /** The Constant INTERVALS_FORMATTER. */
    private static final SimpleDateFormat INTERVALS_FORMATTER = new SimpleDateFormat("yyyy/MM/dd");

    /** The Constant f. */
    private static final SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    /** The Constant INTEND_INTERVAL. */
    private static final long INTEND_INTERVAL = 3;

    /** The Constant ZOOMER_INTEND_INTERVAL. */
    private static final long ZOOMER_INTEND_INTERVAL = 50;

    /** The event style class on scroller. */
    private String eventStyleClass = null;

    /** The group style class. */
    private String groupStyleClass = null;

    /** The first zoomer from. */
    private Date firstZoomerFrom = null;

    /** The first zoomer to. */
    private Date firstZoomerTo = null;

    /** The group hover style class. */
    private String groupHoverStyleClass = null;

    /** The group select style class. */
    private String groupSelectStyleClass = null;

    /** The max count of tool tips. */
    private int maxCountOfToolTips = 0;

    /** The zoomer indent. */
    private int zoomerIndent = (int) ZOOMER_INTEND_INTERVAL;

    /** The group interval. */
    private String groupInterval;
    
    private List<FlashMenuItem> globalMenuItems = null;
    
    
    private List<FlashMenuItem> eventMenuItems = null;
    
    private Comparator<TimeLineEventDataModel> eventsComparator  = new Comparator<TimeLineEventDataModel>(){

        public int compare(TimeLineEventDataModel o1, TimeLineEventDataModel o2) {
            return o1.getEventDate().compareTo(o2.getEventDate());
        }
        
    };

   
    /**
     * The Constructor.
     * 
     * @param hoveredStyleClass the hovered style class
     * @param groupSelectedStyleClass the group selected style class
     * @param groupTooltipTemplate the group tooltip template
     * @param eventStyleClass the event style class on scroller 
     * @param groupStyleClass the group style class
     * @param dataModel the data model
     * @param groupInterval the group interval
     * @param toDate the to date
     * @param zoomerIndent the zoomer indent
     * @param fromDate the from date
     * @param maxCountOfToolTips the max count of tool tips
     * @param eventMenuItems 
     * @param globalMenuItems 
     */
    public XMLDataModelGenerator(TimeLineDataModel dataModel, List<DateIntervalModel> dateIntervals, Date fromDate, Date toDate, String groupTooltipTemplate,
            String eventStyleClass, String groupStyleClass, String hoveredStyleClass, String groupSelectedStyleClass, Integer maxCountOfToolTips,
            Integer zoomerIndent, String groupInterval, List<FlashMenuItem> globalMenuItems, List<FlashMenuItem> eventMenuItems) {
    	
        this.model = dataModel;
        this.dateIntervals = dateIntervals != null ? dateIntervals.toArray(new DateIntervalModel[dateIntervals.size()]) : createDefaultDateIntervals();
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.groupTooltip = groupTooltipTemplate;
        this.eventStyleClass = eventStyleClass;
        this.groupStyleClass = groupStyleClass;
        this.groupSelectStyleClass = groupSelectedStyleClass;
        this.groupHoverStyleClass = hoveredStyleClass;
        this.maxCountOfToolTips = maxCountOfToolTips;

        if (zoomerIndent != null) {
            this.zoomerIndent = zoomerIndent;
        }

        changeFromAndToDates();
        this.firstZoomerFrom = (this.firstZoomerFrom == null ? this.fromDate : this.firstZoomerFrom);
        this.firstZoomerTo = (this.firstZoomerTo == null ? this.toDate : this.firstZoomerTo);
        this.groupInterval = (groupInterval == null ? "0px" : groupInterval);
        this.globalMenuItems = globalMenuItems;
        this.eventMenuItems = eventMenuItems;
    }

    /**
     * Change from and to dates.
     */
    private void changeFromAndToDates() {

        long diffDate = (((toDate.getTime() - fromDate.getTime()) / 100) * INTEND_INTERVAL);
        TimeLineEventDataModel[] dataModel = this.model.getData(this.fromDate, this.toDate);
        if ((dataModel == null) || (dataModel.length == 0)) {
            return;
        }
        Date maximumEventDate = getMaximumDateFromEvents();

        Date minimumEventDate = getMinimumDateFromEvents();

        Calendar maximumCalendar = Calendar.getInstance();
        Calendar minimumCalendar = Calendar.getInstance();

        maximumCalendar.setTime(maximumEventDate);
        minimumCalendar.setTime(minimumEventDate);

//        long offset = getOffsetInDays(fromDate, toDate);
//        int calendarOffcetType = 0;
//        int calendarOffcetValue = 9;

        maximumCalendar.add(Calendar.HOUR, 9);
        minimumCalendar.add(Calendar.HOUR, -9);
        maximumEventDate = maximumCalendar.getTime();
        minimumEventDate = minimumCalendar.getTime();

        firstZoomerTo = toDate;
        toDate = new Date(toDate.getTime() + diffDate);

        firstZoomerFrom = fromDate;
        fromDate = new Date(fromDate.getTime() - diffDate);

        System.err.println("After changed: From event date = " + f.format(fromDate));
        System.err.println("After changed: To event date = " + f.format(toDate));
        this.model.setFromDateWithIndent(fromDate);
        this.model.setToDateWithIndent(toDate);

    }

    /**
     * Inits the date intervals.
     */
    private DateIntervalModel[] createDefaultDateIntervals() {
    	List<DateIntervalModel> defaultDateIntervals = new ArrayList<DateIntervalModel>();
        defaultDateIntervals.add(DateIntervalModel.create("2h", "15m", "HH:mm"));
        defaultDateIntervals.add(DateIntervalModel.create("8h", "1h", "HH:mm"));
        defaultDateIntervals.add(DateIntervalModel.create("16h", "2h", "HH:mm"));
        defaultDateIntervals.add(DateIntervalModel.create("24h", "3h", "HH:mm"));
        defaultDateIntervals.add(DateIntervalModel.create("7d", "1d", "MM/dd"));
        defaultDateIntervals.add(DateIntervalModel.create("1M", "7d", "MM/dd"));
        defaultDateIntervals.add(DateIntervalModel.create("6M", "1M", "MM/dd"));
        defaultDateIntervals.add(DateIntervalModel.create("24M", "3M", "MM/dd/yyyy"));
        defaultDateIntervals.add(DateIntervalModel.create("+", "1y", "MM/dd/yyyy"));
        
        return defaultDateIntervals.toArray(new DateIntervalModel[defaultDateIntervals.size()]);
    }

    /**
     * Buld XML.
     * 
     * @param writer the writer
     * 
     * @throws IOException the IO exception
     */
    void buldXML(ResponseWriter writer) throws IOException {
        writer.write(TimelineXML.XML_HEADER);
        writer.flush();
        writer.startElement(TimelineXML.ROOT, null);
        encodeProperties(writer);
        encodeStyles(writer);
        encodeData(writer);
        writer.endElement(TimelineXML.ROOT);
    }

    /**
     * Encode properties.
     * 
     * @param writer the writer
     * 
     * @throws IOException the IO exception
     */
    void encodeProperties(ResponseWriter writer) throws IOException {
        writer.startElement(TimelineXML.PROPERTIES, null);
        if(eventMenuItems.size() > 0 || globalMenuItems.size() > 0){
        	writer.startElement(TimelineXML.MENU,null);
        	 if(eventMenuItems.size() > 0){
        	     writer.startElement(TimelineXML.EVENT_MENU,null);
        	     for(FlashMenuItem item:eventMenuItems){
        	    	 writeMenuItem(writer,item);
        	     }
        	     writer.endElement(TimelineXML.EVENT_MENU);
        	 }
        	 if(globalMenuItems.size() >0){
        		 writer.startElement(TimelineXML.GLOBAL_MENU, null);
        		   for(FlashMenuItem item:globalMenuItems){
          	    	 writeMenuItem(writer,item);
          	     }
        		 writer.endElement(TimelineXML.GLOBAL_MENU);
        	 }
        	writer.endElement(TimelineXML.MENU);
        }
        writer.endElement(TimelineXML.PROPERTIES);
        writer.flush();

    }

    private void writeMenuItem(ResponseWriter writer, FlashMenuItem item) throws IOException {
		writer.startElement("item", null);
		writer.writeAttribute(TimelineXML.ATTRIBUTES.LABEL, convert(item.getLabel()), null);
		writer.writeAttribute(TimelineXML.ATTRIBUTES.JS_CODE, item.getHandler(), null);
		writer.endElement("item");
		
	}

	/**
     * Encode styles.
     * 
     * @param writer the writer
     * 
     * @throws IOException the IO exception
     */
    void encodeStyles(ResponseWriter writer) throws IOException {
        writer.startElement(TimelineXML.STYLES, null);

        for (String style : this.model.getStylesDefintion()) {
            writer.writeText(style, null);
            writer.writeText("\n", null);
        }

        writer.endElement(TimelineXML.STYLES);

    }

    /**
     * Encode data.
     * 
     * @param writer the writer
     * 
     * @throws IOException the IO exception
     */
    void encodeData(ResponseWriter writer) throws IOException {
        writer.startElement(TimelineXML.DATA, null);
        encodeSwimLines(writer, model.getSwimLanes());
        encodeGroup(writer);
        encodeIntervals(writer, this.dateIntervals);
        encodeEventTypes(writer, model.getEventTypes());
        encodeEvents(writer, model.getData(fromDate, toDate));
        writer.endElement(TimelineXML.DATA);
    }

    /**
     * Encode group.
     * 
     * @param eventGroupModel the event group model
     * @param writer the writer
     * 
     * @throws IOException the IO exception
     */
    private void encodeGroup(ResponseWriter writer) throws IOException {
        writer.startElement(TimelineXML.EVENT_GROUP, null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.STYLECLASS, this.groupStyleClass, null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.HOVERSTYLECLASS, this.groupHoverStyleClass, null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.SELECTEDSTYLECLASS, this.groupSelectStyleClass, null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.GROUP_INTERVAL, this.groupInterval, null);
        writer.startElement(TimelineXML.TOOLTIPTEMPLATE, null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.MAXDISPLAYED, String.valueOf(this.maxCountOfToolTips), null);
        encodeTemplate(writer, this.groupTooltip);

        writer.endElement(TimelineXML.TOOLTIPTEMPLATE);

        writer.endElement(TimelineXML.EVENT_GROUP);

    }

    /**
     * Encode template.
     * 
     * @param groupModel the group model
     * @param templateContent the template content
     * @param eventGroupModel *
     * @param writer the writer
     * 
     * @throws IOException the IO exception
     */
    private void encodeTemplate(ResponseWriter writer, String templateContent) throws IOException {
        writer.append(templateContent);

    }

    /**
     * Encode intervals.
     * 
     * @param intervals the intervals
     * @param writer the writer
     * 
     * @throws IOException the IO exception
     */
    private void encodeIntervals(ResponseWriter writer, DateIntervalModel[] intervals) throws IOException {
        writer.startElement(TimelineXML.INTERVALS, null);

        Date from = fromDate;
        writer.writeAttribute(TimelineXML.ATTRIBUTES.FROM_DATE, from.getTime(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.TO_DATE, toDate.getTime(), null);

        // TODO Discuss about viewFromDate/viewToDate.

        Date[] fromAndToDates = calculateViewDates(this.firstZoomerFrom);

        if ((fromAndToDates.length == 2)) {
            this.firstZoomerTo = fromAndToDates[1];

            long diffTime = this.firstZoomerTo.getTime() - this.firstZoomerFrom.getTime();
            diffTime = ((diffTime / 100) * this.zoomerIndent);
            this.firstZoomerFrom = new Date(this.firstZoomerFrom.getTime() - diffTime);
            if (firstZoomerFrom.before(this.fromDate)) {
                firstZoomerFrom = this.fromDate;
            }
            if (firstZoomerTo.after(this.toDate)) {
                this.firstZoomerTo = this.toDate;
            }
            writer.writeAttribute(TimelineXML.ATTRIBUTES.VIEW_FROM_DATE, this.firstZoomerFrom.getTime(), null);
            writer.writeAttribute(TimelineXML.ATTRIBUTES.VIEW_TO_DATE, this.firstZoomerTo.getTime(), null);

        }
        for (DateIntervalModel dim : intervals) {
            encodeInterval(writer, dim);
        }
        writer.endElement(TimelineXML.INTERVALS);

    }

    /**
     * Gets the maximum date from events.
     * 
     * @return the maximum date from events
     */
    private Date getMaximumDateFromEvents() {
        // System.err.println("Begin find maximum date");
        TimeLineEventDataModel[] events = this.model.getData(this.fromDate, this.toDate);
        Date maxDate = events[0].getEventDate();
        int i = 0;

        for (i = 1; i < events.length; ++i) {
            // System.err.println("date["+i+"]="+f.format(events[i].getEventDate()));
            if (events[i].getEventDate().after(maxDate)) {
                maxDate = events[i].getEventDate();
            }
        }
        // System.err.println("Maximum date is :"+f.format(maxDate));
        return maxDate;
    }

    /**
     * Gets the minimum date from events.
     * 
     * @return the minimum date from events
     */
    private Date getMinimumDateFromEvents() {
        // System.err.println("Begin find min date");
        TimeLineEventDataModel[] events = this.model.getData(this.fromDate, this.toDate);
        int i = 0;
        Date minDate = events[0].getEventDate();
        for (i = 1; i < events.length; ++i) {
            // System.err.println("date["+i+"]="+f.format(events[i].getEventDate()));
            if (events[i].getEventDate().before(minDate)) {
                minDate = events[i].getEventDate();
            }
        }
        // System.err.println("Minimum date is :"+f.format(minDate));
        return minDate;
    }

    /**
     * Calculate view dates.
     * 
     * @param toDate2 the to date2
     * @param fromDate2 the from date2
     * @param fromDate the from date
     * 
     * @return the date[]
     */
    public Date[] calculateViewDates(Date fromDate) {
        Date[] dates = new Date[2];
        Calendar fromDateCalendar = Calendar.getInstance();
        Calendar toDateCalendar = Calendar.getInstance();

        fromDateCalendar.setTime(fromDate);
        toDateCalendar.setTime(fromDate);

        long days = getOffsetInDays(fromDate, toDate);

        if (days >= 1 && days <= 32) {
            toDateCalendar.add(Calendar.HOUR, (int) days);
        } else if (days > 31 && days <= 365) {
            int dyas = (int) (days / 30);
            toDateCalendar.add(Calendar.DAY_OF_MONTH, dyas);

        } else if (days > 365) {
            int month = (int) (days / 365);
            toDateCalendar.add(Calendar.MONTH, month);
        } else if (days == 0 && !toDate.equals(fromDate)) {
            long offsetInHours = getOffsetInHours(fromDate, toDate);
            int field = -1;
            int value = -1;
            if (offsetInHours > 12) {
                field = Calendar.HOUR;
                value = 1;
            } else if (offsetInHours > 0 && offsetInHours < 12) {
                field = Calendar.MINUTE;
                value = 30;
            } else if (offsetInHours == 0) {
                field = Calendar.MINUTE;
                long offsetInMinutes = getOffsetInMinuts(fromDate, toDate);
                if(offsetInMinutes < 10){
                    value = 1;
                }else if ( offsetInMinutes > 10 && offsetInMinutes < 30){
                    value = 3;
                }else if ( offsetInHours > 30){
                    value = 5;
                }else if ( offsetInMinutes == 0 || offsetInMinutes == 1){
                    field = Calendar.SECOND;
                    value = 5;
                }
            }
            toDateCalendar.add(field, value);
        }
        // INTERVALS_FORMATTER.format(toDateCalendar.getTime());
        dates[0] = fromDateCalendar.getTime();
        dates[1] = toDateCalendar.getTime();
        return dates;
    }

    /**
     * Gets the offset in minuts.
     * 
     * @param toDate2 the to date2
     * @param fromDate2 the from date2
     * 
     * @return the offset in minuts
     */
    private long getOffsetInMinuts(Date fromDate2, Date toDate2) {
        return getOffset(fromDate, toDate, COEFICIENT_IN_MINUTES);
        
    }

    /**
     * Gets the offset in hours.
     * 
     * @param toDate2 the to date2
     * @param fromDate2 the from date2
     * 
     * @return the offset in hours
     */
    private long getOffsetInHours(Date fromDate2, Date toDate2) {
        return getOffset(fromDate, toDate, COEFICIENT_IN_HOURS);
    }

    /**
     * Gets the offset in days.
     * 
     * @param toDate the to date
     * @param fromDate the from date
     * 
     * @return the offset in days
     */
    public long getOffsetInDays(Date fromDate, Date toDate) {
        return getOffset(fromDate, toDate, COEFICIENT_IN_DAYS);
    }

    /**
     * Gets the offset.
     * 
     * @param toDate the to date
     * @param fromDate the from date
     * @param coeficient the coeficient
     * 
     * @return the offset
     */
    protected long getOffset(Date fromDate, Date toDate, int coeficient) {
        long rst = 0;
        long fromTime = fromDate.getTime();

        long toTime = toDate.getTime();

        long diffTime = toTime - fromTime;
        rst = (diffTime / (coeficient));

        return rst;
    }

    /**
     * Encode interval.
     * 
     * @param dim the dim
     * @param writer the writer
     * 
     * @throws IOException the IO exception
     */
    private void encodeInterval(ResponseWriter writer, DateIntervalModel dim) throws IOException {
        writer.startElement(TimelineXML.INTERVAL, null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.LENGTH, dim.getInterval(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.MARKER, dim.getScale(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.DATEPATTERN, dim.getDatePattern(), null);
        writer.endElement(TimelineXML.INTERVAL);

    }

//    /**
//     * Gets the intervals.
//     * 
//     * @return the intervals
//     */
//    protected synchronized DateIntervalModel[] getIntervals() {
//        DateIntervalModel[] cIntervals = dateIntervals.clone();
//        return cIntervals;
//    }

    /**
     * Encode swim lines.
     * 
     * @param swimLaneModel the swim lane model
     * @param writer the writer
     * 
     * @throws IOException the IO exception
     */
    void encodeSwimLines(ResponseWriter writer, TimeLineSwimLaneModel[] swimLaneModel) throws IOException {
        writer.startElement(TimelineXML.SWINLINES, null);
        writer.writeAttribute("frozenWidth", "120", null);
        for (TimeLineSwimLaneModel model : swimLaneModel) {
            encodeSwimLine(writer, model);
        }
        writer.endElement(TimelineXML.SWINLINES);
    }

    /**
     * Encode swim line.
     * 
     * @param swimLaneModel the swim lane model
     * @param writer the writer
     * 
     * @throws IOException the IO exception
     */
    void encodeSwimLine(ResponseWriter writer, TimeLineSwimLaneModel swimLaneModel) throws IOException {
        writer.startElement(TimelineXML.SWINLINE, null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.ID, swimLaneModel.getSwimLaneId(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.NAME, convert(swimLaneModel.getSwimLaneName()), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.STYLECLASS, swimLaneModel.getSwimLaneStyleClass(), null);
        writer.endElement(TimelineXML.SWINLINE);
    }

    /**
     * Encode event types.
     * 
     * @param eventTypeModel the event type model
     * @param writer the writer
     * 
     * @throws IOException the IO exception
     */
    void encodeEventTypes(ResponseWriter writer, TimeLineEventTypeModel[] eventTypeModel) throws IOException {
        writer.startElement(TimelineXML.EVENTTYPES, null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.STYLECLASS, this.eventStyleClass, null);
        for (TimeLineEventTypeModel typeModel : eventTypeModel) {
            encodeEventType(writer, typeModel);
        }
        writer.endElement(TimelineXML.EVENTTYPES);
    }

    /**
     * Encode event type.
     * 
     * @param eventTypeModel the event type model
     * @param writer the writer
     * 
     * @throws IOException the IO exception
     */
    void encodeEventType(ResponseWriter writer, TimeLineEventTypeModel eventTypeModel) throws IOException {
        writer.startElement(TimelineXML.EVENTTYPE, null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.ID, eventTypeModel.getEventTypeId(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.NAME, eventTypeModel.getEventTypeName(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.STYLECLASS, eventTypeModel.getEventTypeStyleClass(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.SELECTEDSTYLECLASS, eventTypeModel.getSelectedStyleClass(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.HOVERSTYLECLASS, eventTypeModel.getHoverStyleClass(), null);
        writer.endElement(TimelineXML.EVENTTYPE);
    }

    /**
     * Encode events.
     * 
     * @param writer the writer
     * @param timeLineEventDataModel the time line event data model
     * 
     * @throws IOException the IO exception
     */
    void encodeEvents(ResponseWriter writer, TimeLineEventDataModel[] timeLineEventDataModel) throws IOException {
        final List<TimeLineEventDataModel> events = Arrays.asList(timeLineEventDataModel);
        Collections.sort(events,eventsComparator);
//        final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        writer.startElement(TimelineXML.EVENTS, null);
        for (TimeLineEventDataModel eventDataModel : events) {
//            System.err.println("After sorting:"+sdf.format(eventDataModel.getEventDate()));
            encodeEvent(writer, eventDataModel);
        }
        writer.endElement(TimelineXML.EVENTS);
    }

    /**
     * Encode event.
     * 
     * @param writer the writer
     * @param timeLineEventDataModel the time line event data model
     * 
     * @throws IOException the IO exception
     */
    void encodeEvent(ResponseWriter writer, TimeLineEventDataModel timeLineEventDataModel) throws IOException {
        writer.startElement(TimelineXML.EVENT, null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.ID, timeLineEventDataModel.getEventId(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.TYPE, timeLineEventDataModel.getEventType(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.DATE, timeLineEventDataModel.getEventDate().getTime(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.SWIMLINE_ID, timeLineEventDataModel.getSwimLaneId(), null);
        encodeToolTip(writer, timeLineEventDataModel);
        writer.endElement(TimelineXML.EVENT);
        writer.flush();
    }

    /**
     * Encode tool tip.
     * 
     * @param writer the writer
     * @param timeLineEventDataModel the time line event data model
     * 
     * @throws IOException the IO exception
     */
    void encodeToolTip(ResponseWriter writer, TimeLineEventDataModel timeLineEventDataModel) throws IOException {
        writer.startElement(TimelineXML.TOOLTIP, null);
        writer.writeText(convert(timeLineEventDataModel.getToolTip()), null);
        writer.endElement(TimelineXML.TOOLTIP);
    }

    /**
     * Gets the XML data.
     * 
     * @param context the context
     * 
     * @return the XML data
     * 
     * @throws IOException the IO exception
     */
    public String getXMLData(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        return _writeXml(writer);
    }

    /**
     * _write xml.
     * 
     * @param writer the writer
     * 
     * @return the string
     * 
     * @throws IOException the IO exception
     */
    private String _writeXml(ResponseWriter writer) throws IOException {
        StringWriter stringWriter = new StringWriter();
        ResponseWriter responseWriter = writer.cloneWithWriter(stringWriter);

        StringBuffer result = null;
        buldXML(responseWriter);
        result = stringWriter.getBuffer();
        return result.toString();

    }

    /**
     * The Class TimelineXML.
     */
    class TimelineXML {

        /** The Constant XML_HEADER. */
        public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";

        /** The Constant OPEN_TAG_CHAR. */
        public static final String OPEN_TAG_CHAR = "<";

        /** The Constant CLOSE_TAG_CHAR. */
        public static final String CLOSE_TAG_CHAR = ">";

        /** The Constant OPEN_CLOSE_TAG_CHAR. */
        public static final String OPEN_CLOSE_TAG_CHAR = "</";

        /** The Constant ROOT. */
        public static final String ROOT = "timeline-root";

        /** The Constant EVENT_GROUP. */
        public static final String EVENT_GROUP = "eventGroup";

        /** The Constant TOOLTIPTEMPLATE. */
        public static final String TOOLTIPTEMPLATE = "tooltipTemplate";

        /** The Constant PROPERTIES. */
        public static final String PROPERTIES = "properties";
        
        public static final String MENU = "menu";
      
        public static final String EVENT_MENU = "eventMenu";
        
        public static final String GLOBAL_MENU = "globalMenu";

        /** The Constant STYLES. */
        public static final String STYLES = "styles";

        /** The Constant DATA. */
        public static final String DATA = "data";

        /** The Constant SWINLINES. */
        public static final String SWINLINES = "swimLines";

        /** The Constant INTERVALS. */
        public static final String INTERVALS = "intervals";

        /** The Constant SWINLINE. */
        public static final String SWINLINE = "swimLine";

        /** The Constant EVENTTYPES. */
        public static final String EVENTTYPES = "eventTypes";

        /** The Constant EVENTTYPE. */
        public static final String EVENTTYPE = "eventType";

        /** The Constant EVENTS. */
        public static final String EVENTS = "events";

        /** The Constant EVENT. */
        public static final String EVENT = "event";

        /** The Constant HEADER. */
        public static final String HEADER = "header";

        /** The Constant FOOTER. */
        public static final String FOOTER = "footer";

        /** The Constant CONTENT. */
        public static final String CONTENT = "content";

        /** The Constant HTML. */
        public static final String HTML = "html";

        /** The Constant TOOLTIP. */
        public static final String TOOLTIP = "tooltip";

        /** The Constant INTERVAL. */
        public static final String INTERVAL = "interval";

        /**
         * The Class ATTRIBUTES.
         */
        public class ATTRIBUTES {

            /** The Constant ID. */
            public static final String ID = "id";

            /** The Constant TYPE. */
            public static final String TYPE = "type";

            /** The Constant DATE. */
            public static final String DATE = "date";

            /** The Constant SWIMLINE_ID. */
            public static final String SWIMLINE_ID = "swimLineId";

            /** The Constant NAME. */
            public static final String NAME = "name";

            /** The Constant STYLECLASS. */
            public static final String STYLECLASS = "styleClass";

            /** The Constant SELECTEDSTYLECLASS. */
            public static final String SELECTEDSTYLECLASS = "selectedStyleClass";

            /** The Constant HOVERSTYLECLASS. */
            public static final String HOVERSTYLECLASS = "hoverStyleClass";

            /** The Constant LENGTH. */
            public static final String LENGTH = "length";

            /** The Constant MARKER. */
            public static final String MARKER = "marker";

            /** The Constant DATEPATTERN. */
            public static final String DATEPATTERN = "datePattern";

            /** The Constant FROM_DATE. */
            public static final String FROM_DATE = "fromDate";

            /** The Constant TO_DATE. */
            public static final String TO_DATE = "toDate";

            /** The Constant VIEW_FROM_DATE. */
            public static final String VIEW_FROM_DATE = "viewFromDate";

            /** The Constant VIEW_TO_DATE. */
            public static final String VIEW_TO_DATE = "viewToDate";

            /** The Constant MAXDISPLAYED. */
            public static final String MAXDISPLAYED = "maxdisplayed";

            /** The Constant GROUP_INTERVAL. */
            public static final String GROUP_INTERVAL = "eventInterval";
            
            public static final String JS_CODE = "jsCode";
            
            public static final String LABEL = "label";
        }
    }
    
    public String convert(String original) {
		StringBuffer unicode = new StringBuffer();

		if (original != null) {
			for (int i = 0; i < original.length(); i++) {
				char ch = original.charAt(i);

				if ((ch >= 0x0020) && (ch <= 0x007e)) {
					unicode.append(ch);
				} else {
					unicode.append("%");
					
					String hexString = Integer.toHexString(ch);
					
					if(hexString.length() > 2) {
						unicode.append("u");
					}
					unicode.append(hexString);
				}
			}
		}
		
		return unicode.toString();
	}
}
