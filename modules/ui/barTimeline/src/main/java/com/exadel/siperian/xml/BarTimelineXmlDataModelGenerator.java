package com.exadel.siperian.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exadel.siperian.component.UIBarTimeline;
import com.exadel.siperian.model.BarTimelineDataModel;
import com.exadel.siperian.model.Interval;
import com.exadel.siperian.model.Item;
import com.exadel.siperian.model.ItemType;
import com.exadel.siperian.model.SwimLine;

public class BarTimelineXmlDataModelGenerator {
	
	private static Log LOG = LogFactory.getLog(BarTimelineXmlDataModelGenerator.class);
	
	private BarTimelineDataModel model;
	private Map<String, String[]> symbolsMap;
	
	public BarTimelineXmlDataModelGenerator(BarTimelineDataModel model,Map<String, String[]> symbolsMap) {
		this.model = model;
		this.symbolsMap = symbolsMap;
	}
	
	public String getXmlData(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        StringWriter stringWriter = new StringWriter();
        ResponseWriter responseWriter = writer.cloneWithWriter(stringWriter);
        StringBuffer result = null;
        buldXML(responseWriter);
        result = stringWriter.getBuffer();
        return result.toString();
	}

    void buldXML(ResponseWriter writer) throws IOException {
        writer.write(TimelineXML.XML_HEADER);
        writer.flush();
        writer.startElement(TimelineXML.ROOT, null);
        encodeProperties(writer);
        encodeData(writer);
        writer.endElement(TimelineXML.ROOT);
    }
    
    public void encodeData(ResponseWriter writer) throws IOException {
        writer.startElement(TimelineXML.DATA, null);
                
        List<SwimLine> swimLines = model.getSwimLines();
        
        if(!isEmpty(swimLines)) {
        	encodeSwimLines(writer,swimLines);
        }
        
        List<Interval> intervals = model.getIntervals();
        if(isEmpty(intervals)) {
        	try {
        		intervals = IntervalLoader.loadIntervals();
        	} catch (Exception e){
        		LOG.error("Can't load default intervals", e);
        	}
        }
        
        if(!isEmpty(intervals)) {
        	encodeIntervals(writer, intervals);
        }	
        
        List<ItemType> itemTypes = model.getItemTypes();
        if(!isEmpty(itemTypes)) {
        encodeItemTypes(writer, itemTypes);
        }
        
        List<Item> items = model.getItems();
        if(!isEmpty(items)) {
        	encodeItems(writer,items);
        }
        
        writer.endElement(TimelineXML.DATA);
    }
    
    public void encodeProperties(ResponseWriter writer) throws IOException{
    	if(symbolsMap != null) {
    		writer.startElement(TimelineXML.PROPERTIES, null);
    		encodeMonthNames(writer, join(symbolsMap.get(UIBarTimeline.MONTH_LABELS), symbolsMap.get(UIBarTimeline.MONTH_LABELS_SHORT)));
    		encodeDayNames(writer, join(symbolsMap.get(UIBarTimeline.WEEK_DAY_LABELS), symbolsMap.get(UIBarTimeline.WEEK_DAY_LABELS_SHORT)));
    		writer.endElement(TimelineXML.PROPERTIES);
    	}
    }
    
    public void encodeMonthNames(ResponseWriter writer, String monthNames) throws IOException {
    	if(monthNames != null && !monthNames.isEmpty()) {
    		writer.startElement(TimelineXML.MONTH_NAMES, null);
    		writer.writeText(convert(monthNames), null);
    		writer.endElement(TimelineXML.MONTH_NAMES);
    	}	
    }
    
    public void encodeDayNames(ResponseWriter writer, String dayNames) throws IOException {
    	if(dayNames != null && !dayNames.isEmpty()) {
    		writer.startElement(TimelineXML.DAY_NAMES, null);
    		writer.writeText(convert(dayNames), null);
    		writer.endElement(TimelineXML.DAY_NAMES);
    	}
    }
    
    public void encodeSwimLines(ResponseWriter writer, List<SwimLine> swimLines) throws IOException {
    	 writer.startElement(TimelineXML.SWINLINES, null);
         writer.writeAttribute("frozenWidth", "120", null);
         for (SwimLine swimLanel : swimLines) {
             encodeSwimLine(writer, swimLanel);
         }
         writer.endElement(TimelineXML.SWINLINES);
    }
    
    public void encodeSwimLine(ResponseWriter writer, SwimLine swimLine) throws IOException {
        writer.startElement(TimelineXML.SWIMLINE, null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.ID, swimLine.getId(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.STYLECLASS, swimLine.getStyleClass(), null);
        writer.writeAttribute(TimelineXML.ATTRIBUTES.NAME, convert(swimLine.getName()), null);
        writer.endElement(TimelineXML.SWIMLINE);
    }
    
    public void encodeIntervals(ResponseWriter writer, List<Interval> intervals) throws IOException {
    	writer.startElement(TimelineXML.INTERVALS, null);
    	for (Interval interval : intervals) {
    		encodeInterval(writer, interval);
    	}
    	writer.endElement(TimelineXML.INTERVALS);
    }
    
    public void encodeInterval(ResponseWriter writer, Interval interval) throws IOException {
    	 writer.startElement(TimelineXML.INTERVAL, null);
    	 writer.writeAttribute(TimelineXML.ATTRIBUTES.LENGTH, interval.getLength(), null);
    	 writer.writeAttribute(TimelineXML.ATTRIBUTES.MARKER, interval.getMarker(), null);
    	 writer.writeAttribute(TimelineXML.ATTRIBUTES.DATEPATTERN, interval.getDatePattern(), null);
         writer.endElement(TimelineXML.INTERVAL);
    }
    
    public void encodeItemTypes(ResponseWriter writer, List<ItemType> itemTypes) throws IOException {
    	writer.startElement(TimelineXML.ITEMTYPES, null);
    	for(ItemType itemType: itemTypes) {
    		encodeItemType(writer, itemType);
    	}
    	writer.endElement(TimelineXML.ITEMTYPES);
    }
    
    public void encodeItemType(ResponseWriter writer, ItemType itemType) throws IOException {
    	writer.startElement(TimelineXML.ITEMTYPE, null);
    	writer.writeAttribute(TimelineXML.ATTRIBUTES.ID, itemType.getId(), null);
    	writer.writeAttribute(TimelineXML.ATTRIBUTES.NAME, itemType.getName(), null);
    	writer.writeAttribute(TimelineXML.ATTRIBUTES.STYLECLASS, itemType.getStyleClass(), null);
    	writer.writeAttribute(TimelineXML.ATTRIBUTES.SELECTEDSTYLECLASS, itemType.getSelectedStyleClass(), null);
    	writer.writeAttribute(TimelineXML.ATTRIBUTES.HOVERSTYLECLASS, itemType.getHoverStyleClass(), null);
    	writer.writeAttribute(TimelineXML.ATTRIBUTES.HIGHLIGHTSTYLECLASS, itemType.getHighlightStyleClass(), null);
    	writer.writeAttribute(TimelineXML.ATTRIBUTES.TOOLTIPSTYLECLASS, itemType.getTooltipStyleClass(), null);
    	writer.endElement(TimelineXML.ITEMTYPE);
    }

    public void encodeItems(ResponseWriter writer, List<Item> items) throws IOException {
    	writer.startElement(TimelineXML.ITEMS, null);
    	for(Item item: items) {
    		encodeItem(writer, item);
    	}
    	writer.endElement(TimelineXML.ITEMS);
    }
    
    public void encodeItem(ResponseWriter writer, Item item) throws IOException {
    	writer.startElement(TimelineXML.ITEM, null);
    	writer.writeAttribute(TimelineXML.ATTRIBUTES.ID, item.getId(), null);
    	writer.writeAttribute(TimelineXML.ATTRIBUTES.TYPE, item.getType(), null);
    	
    	Date startDate = item.getStartDate();
    	if(startDate != null) { 
    		writer.writeAttribute(TimelineXML.ATTRIBUTES.START_DATE, startDate.getTime(), null);
    	}	
    	
    	Date endDate = item.getEndDate();
    	if(endDate != null) {
    		writer.writeAttribute(TimelineXML.ATTRIBUTES.END_DATE, endDate.getTime(), null);
    	}	
    	writer.writeAttribute(TimelineXML.ATTRIBUTES.SWIMLINE_ID, item.getSwimLineId(), null);
    	
    	writer.startElement(TimelineXML.TOOLTIP, null);
    	writer.writeText(convert(item.getTooltip()), null);
    	writer.endElement(TimelineXML.TOOLTIP);
    	
    	writer.endElement(TimelineXML.ITEM);
    }
    
    private boolean isEmpty(List<?> elements) {
    	return (elements == null || elements.isEmpty()); 
    }
    
    private String join(String[] longNames, String[] shortNames) {
    	StringBuffer buffer = new StringBuffer();
    	String sep = "";

    	if(longNames != null) {
    		for(String longName: longNames) {
    			if(longName.isEmpty()) continue;
    			buffer.append(sep).append(longName);
    			sep = " ";
    		}			
    	}
    	
    	if(shortNames != null) {
    		for(String shortName: shortNames) {
    			if(shortName.isEmpty()) continue;
    			buffer.append(sep).append(shortName);
    			sep = " ";
    		}	
    	}
    	
    	return buffer.toString();
    }
    
	/**
	 * The Class TimelineXML.
	 */
	class TimelineXML {

		public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";

		public static final String OPEN_TAG_CHAR = "<";

		public static final String CLOSE_TAG_CHAR = ">";

		public static final String OPEN_CLOSE_TAG_CHAR = "</";

		public static final String ROOT = "timeline-root";

		public static final String PROPERTIES = "properties";

		public static final String MENU = "menu";

		public static final String STYLES = "styles";

		public static final String DATA = "data";

		public static final String SWINLINES = "swimLines";
		
		public static final String SWIMLINE = "swimLine";

		public static final String INTERVALS = "intervals";
		
		public static final String INTERVAL = "interval";
		
		public static final String ITEMTYPES = "itemTypes";
		
		public static final String ITEMTYPE = "itemType";
		
		public static final String ITEMS = "items";
		
		public static final String ITEM = "item";
		
		public static final String HTML = "html";

		public static final String TOOLTIP = "tooltip";
		
		public static final String MONTH_NAMES = "monthNames";
		
		public static final String DAY_NAMES = "dayNames";
		
		/**
		 * The Class ATTRIBUTES.
		 */
		public class ATTRIBUTES {

			public static final String ID = "id";

			public static final String TYPE = "type";

			public static final String DATE = "date";

			public static final String SWIMLINE_ID = "swimLineId";
			
			public static final String TOOLTIP = "tooltip";

			public static final String NAME = "name";

			public static final String STYLECLASS = "styleClass";

			public static final String SELECTEDSTYLECLASS = "selectedStyleClass";

			public static final String HIGHLIGHTSTYLECLASS = "highlightStyleClass";
			
			public static final String HOVERSTYLECLASS = "hoverStyleClass";
			
			public static final String TOOLTIPSTYLECLASS = "tooltipStyleClass";

			public static final String LENGTH = "length";

			public static final String MARKER = "marker";

			public static final String DATEPATTERN = "datePattern";

			public static final String START_DATE = "startDate";

			public static final String END_DATE = "endDate";
			
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
