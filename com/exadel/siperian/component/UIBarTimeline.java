package com.exadel.siperian.component;

import java.util.Locale;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.exadel.siperian.model.BarTimelineState;

public abstract class UIBarTimeline extends UIComponentBase {
	public static final String MONTH_LABELS_SHORT = "monthLabelsShort";
	public static final String MONTH_LABELS = "monthLabels";
	public static final String WEEK_DAY_LABELS_SHORT = "weekDayLabelsShort";
	public static final String WEEK_DAY_LABELS = "weekDayLabels";
	public static final String COMPONENT_TYPE = "com.siperian.richfaces.BarTimeline";
	public static final String COMPONENT_FAMILY = "com.siperian.richfaces.Timeline";
	   

	public abstract String getHeader();
    
	public abstract void setHeader(String header);
		
	public abstract Object getMonthLabels();

	public abstract void setMonthLabels(Object labels);

	public abstract Object getMonthLabelsShort();

	public abstract void setMonthLabelsShort(Object labels);

	public abstract Object getWeekDayLabels();

	public abstract void setWeekDayLabels(Object labels);

	public abstract Object getWeekDayLabelsShort();

	public abstract void setWeekDayLabelsShort(Object labels);
    
	public abstract void setBarTimelineState(BarTimelineState barTimelineState);
	
	public abstract BarTimelineState getBarTimelineState();
	
    /**
	  *Parse Locale from String.
	  *String must be represented as Locale.toString(); xx_XX_XXXX
    */
	public Locale parseLocale(String localeStr){
		int length = localeStr.length();
		if(null==localeStr||length<2){
			return Locale.getDefault();
		}
		
		//Lookup index of first '_' in string locale representation.
		int index1 = localeStr.indexOf("_");
		//Get first charters (if exist) from string
		String language = null; 
		if(index1!=-1){
			language = localeStr.substring(0, index1);
		}else{
			return new Locale(localeStr);
		}
		//Lookup index of second '_' in string locale representation.
		int index2 = localeStr.indexOf("_", index1+1);
		String country = null;
		if(index2!=-1){
			country = localeStr.substring(index1+1, index2);
			String variant = localeStr.substring(index2+1);
			return new Locale(language, country, variant);
		}else{
			country = localeStr.substring(index1+1);
			return new Locale(language, country);
		}		
	}
	
	public Locale getAsLocale(Object locale) {

		if (locale instanceof Locale) {

			return (Locale) locale;

		} else if (locale instanceof String) {

			return parseLocale((String) locale);

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			Converter converter = application
					.createConverter(locale.getClass());
			if (null != converter) {
				return parseLocale(converter.getAsString(context, this, locale));
			} else {
				throw new FacesException(
						"Wrong locale attibute type or there is no converter for custom attibute type");
			}
		}
	}
	
	public boolean getRendersChildren() {
        return true;
    }
	
	@Override
	public void processUpdates(FacesContext context){
		super.processUpdates(context);
		updateTableState(context);
	}
	
	protected void updateTableState(FacesContext context){
		ValueExpression ve = getValueExpression("barTimelineState");
		if ((null != ve) && (!ve.isReadOnly(context.getELContext()))) {
			ve.setValue(context.getELContext(), getBarTimelineState());
		}
	}
}
