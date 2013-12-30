/**
 * calendar
 * Created 22.05.2009  13:23:04
 */
package com.exadel.siperian.renderkit.html;

import java.io.IOException;
import java.util.Arrays;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.ServletContext;

import org.ajax4jsf.resource.InternetResource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UICalendar;
import org.richfaces.renderkit.html.CalendarRenderer;

/**
 * Custom renderer for the rich:calendar component.
 * 
 * @author Eugene Stherbin
 */
public class SipCalendarRenderer extends CalendarRenderer {

    /** The Constant RICH_CALENDAR_RESOURCEBUNDLE1. */
    public static final String RICH_CALENDAR_RESOURCEBUNDLE1 = "rich.calendar.resourceBundleVar";
    
    private InternetResource[] scriptsAll = null;
    
    protected InternetResource[] getScripts() {
        synchronized (this) {
            if (scriptsAll == null) {
                InternetResource[] rsrcs = super.getScripts();
                scriptsAll = Arrays.copyOf(rsrcs, rsrcs.length + 1);
                scriptsAll[rsrcs.length] = getResource("/com/exadel/siperian/renderkit/html/scripts/calendar2.js");
            }
        }
        return scriptsAll;
    }
    
    /** The log. */
    private static Log log = LogFactory.getLog(SipCalendarRenderer.class);

    /**
     * Get resource bungle from session context-param message bundle.
     * 
     * @param calendar the calendar
     * @param context the context
     * 
     * @throws IOException the IO exception
     * 
     * @see org.richfaces.renderkit.CalendarRendererBase#writeLabels(javax.faces.context.FacesContext,
     * org.richfaces.component.UICalendar)
     */
    @Override
    public void writeLabels(FacesContext context, UICalendar calendar) throws IOException {

	ResourceBundle bundle1 = null;
	ResourceBundle bundle2 = null;
	ClassLoader loader = Thread.currentThread().getContextClassLoader();
	String messageBundle = context.getApplication().getMessageBundle();
	final ResourceBundle customResourceBundle = getCustomResourceBundle(context);

	if(customResourceBundle != null){
	  bundle1 = customResourceBundle;
	}
	Object locale = calendar.getLocale();
	if ((null != messageBundle) && (bundle1 == null)) {
	    log.debug("Used resource bundle is +" + messageBundle);
	    try {
		bundle1 = ResourceBundle.getBundle(messageBundle, calendar.getAsLocale(locale), loader);
	    } catch (MissingResourceException e) {
		log.error(e.getMessage(), e);
	    }
	}
	try {
	    bundle2 = ResourceBundle.getBundle(CALENDAR_BUNDLE, calendar.getAsLocale(locale), loader);

	} catch (MissingResourceException e) {
	    // No external bundle was found, ignore this exception.
	}

	ResponseWriter writer = context.getResponseWriter();
	writer.writeText(",\n labels:{", null);
	if (null != bundle1 || null != bundle2) {
	    writeStringsFromBundle(bundle1, bundle2, "Apply", writer);
	    writeStringsFromBundle(bundle1, bundle2, "Today", writer);
	    writeStringsFromBundle(bundle1, bundle2, "Clean", writer);
	    writeStringsFromBundle(bundle1, bundle2, "Cancel", writer);
	    writeStringsFromBundle(bundle1, bundle2, "OK", writer);
	    writeStringsFromBundle(bundle1, bundle2, "Close", writer);
	} else {
	    // No bundles were found, use default labels.
	    writer.writeText("apply:'Apply', today:'Today', clean:'Clean', ok:'OK', cancel:'Cancel', close:'x'", null);
	}
	writer.writeText("}", null);

    }

    /**
     * Gets the custom resource bundle.
     * 
     * @param context the context
     * 
     * @return the custom resource bundle
     */
    protected ResourceBundle getCustomResourceBundle(FacesContext context) {
	ResourceBundle result = null;

	if (context.getExternalContext().getContext() instanceof ServletContext) {
	    final ServletContext ctx = (ServletContext) context.getExternalContext().getContext();
	    final String name =  ctx.getInitParameter(RICH_CALENDAR_RESOURCEBUNDLE1);
	    
	    if(name!=null){
		result = context.getApplication().getResourceBundle(context, name);
	    }
	}
	return result;
    }

}
