/**
 *
 *
 */

package com.exadel.siperian.renderkit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.ajax4jsf.javascript.JSFunctionDefinition;
import org.ajax4jsf.javascript.ScriptUtils;
import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;
import org.ajax4jsf.util.InputUtils;
import org.ajax4jsf.util.SelectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UIComboBox;
import org.richfaces.renderkit.ComboBoxBaseRenderer;

/**
 * @author Eugene Stherbin
 * @since 3.2.0 ComboBox Base renderer implementation
 * 
 */
public class SipComboBoxBaseRenderer extends HeaderResourcesRendererBase {

    private static final String RICH_COMBOBOX_ITEM_CLASSES = "sip-combobox-item sip-combobox-item-normal";
    private static Log logger = LogFactory.getLog(ComboBoxBaseRenderer.class);

    protected Class<? extends UIComponent> getComponentClass() {
	return UIComboBox.class;
    }

    protected void doDecode(FacesContext context, UIComponent component) {
	UIComboBox comboBox = null;

	if (component instanceof UIComboBox) {
	    comboBox = (UIComboBox) component;
	} else {
	    if (logger.isDebugEnabled()) {
		logger.debug("No decoding necessary since the component " + component.getId()
			+ " is not an instance or a sub class of UIComboBox");
	    }
	    return;
	}

	String clientId = comboBox.getClientId(context);
	if (clientId == null) {
	    throw new NullPointerException("component client id is NULL");
	}

	if (InputUtils.isDisabled(comboBox) || InputUtils.isReadOnly(comboBox)) {
	    if (logger.isDebugEnabled()) {
		logger.debug(("No decoding necessary since the component " + component.getId() + " is disabled"));
	    }
	}

	Map<String, String> request = context.getExternalContext().getRequestParameterMap();
	String newValue = (String) request.get(clientId);
	if (newValue != null) {
	    comboBox.setSubmittedValue(newValue);
	}
    }

    public List<Object> encodeItems(FacesContext context, UIComponent component) throws IOException, IllegalArgumentException {
	List<Object> values = new ArrayList<Object>();
	if (isAcceptableComponent(component)) {
	    UIComboBox comboBox = (UIComboBox) component;
	    values.addAll(encodeSuggestionValues(context, comboBox));
	    List<SelectItem> selectItems = SelectUtils.getSelectItems(context, component);
	    for (SelectItem selectItem : selectItems) {
		String convertedValue = getConvertedStringValue(context, component, selectItem.getValue());
		encodeSuggestion(context, comboBox, convertedValue, RICH_COMBOBOX_ITEM_CLASSES);
		values.add(convertedValue);
	    }
	}
	return values;
    }

    public List<Object> encodeSuggestionValues(FacesContext context, UIComboBox combobox) throws IOException, IllegalArgumentException {

	List<Object> values = new ArrayList<Object>();
	Object suggestionValues = combobox.getSuggestionValues();
	if (suggestionValues != null) {
	    if (suggestionValues instanceof Collection) {
		Collection collection = (Collection) suggestionValues;
		for (Object suggestionValue : collection) {
		    String convertedValue = getConvertedStringValue(context, combobox, suggestionValue);
		    encodeSuggestion(context, combobox, convertedValue, RICH_COMBOBOX_ITEM_CLASSES);
		    values.add(convertedValue);
		}
	    } else if (suggestionValues.getClass().isArray()) {
		Object[] suggestions = (Object[]) suggestionValues;
		for (Object suggestionValue : suggestions) {
		    String convertedValue = getConvertedStringValue(context, combobox, suggestionValue);
		    encodeSuggestion(context, combobox, convertedValue, RICH_COMBOBOX_ITEM_CLASSES);
		    values.add(convertedValue);
		}
	    } else {
		throw new IllegalArgumentException("suggestionValues should be Collection or Array");
	    }
	}
	return values;
    }

    @Override
    public Object getConvertedValue(FacesContext context, UIComponent component, Object submittedValue) throws ConverterException {
	return InputUtils.getConvertedValue(context, component, submittedValue);
    }

    protected String getConvertedStringValue(FacesContext context, UIComponent component, Object value) {
	return InputUtils.getConvertedStringValue(context, component, value);
    }

    protected void encodeSuggestion(FacesContext context, UIComponent component, String value, String classes) throws IOException {

    }

    protected boolean isAcceptableComponent(UIComponent component) {
	return component != null && this.getComponentClass().isAssignableFrom(component.getClass());
    }

    public String getItemsTextAsJSArray(FacesContext context, UIComponent component, List items) {
	return ScriptUtils.toScript(items);
    }

    public String isNeedCustomClassForIE7(UIComponent comp, FacesContext cont) {
	
	final String agent = ((HttpServletRequest)cont.getExternalContext().getRequest()).getHeader("user-agent");
	String customIe7ListStyle = " ";
	
	if ((agent != null) && (agent.indexOf("MSIE 7.0") != -1 || agent.indexOf("MSIE 6") != -1 || agent.indexOf("MSIE 8.0") != -1)) {
	    customIe7ListStyle += "sip-combobox-ie7-list";

	}
	return customIe7ListStyle;
    }
    
    public Integer getCountOfItems(List<?> comp){
        Integer rst = 0 ;
        if(comp!=null){
            rst = comp.size();
        }
        return rst;
    }

    public String getAsEventHandler(FacesContext context, UIComponent component, String attributeName) {
	JSFunctionDefinition script = getUtils().getAsEventHandler(context, component, attributeName, null);
	return ScriptUtils.toScript(script);
    }

    public String encodeValue(String value) {
	return ScriptUtils.toScript(value);
    }
}
