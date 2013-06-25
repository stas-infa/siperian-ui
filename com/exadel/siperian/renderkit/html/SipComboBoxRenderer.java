/**
 * License Agreement.
 *
 * Ajax4jsf 1.1 - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package com.exadel.siperian.renderkit.html;


// 
// Imports
//
import java.util.Iterator;
import java.util.Collection;
import java.util.Map;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.ajax4jsf.renderkit.ComponentsVariableResolver;
import org.ajax4jsf.renderkit.ComponentVariables;
import org.richfaces.component.util.HtmlUtil;
import java.util.List;
import org.ajax4jsf.renderkit.compiler.TemplateContext;
import org.ajax4jsf.resource.InternetResource;
import org.ajax4jsf.resource.InternetResource;
//
//
//


import com.exadel.siperian.renderkit.SipComboBoxBaseRenderer;



/**
 * Renderer for component class com.exadel.siperian.renderkit.html.SipComboBoxRenderer
 */
public class SipComboBoxRenderer extends SipComboBoxBaseRenderer {

	public SipComboBoxRenderer () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] styles = {
						getResource("/com/exadel/siperian/renderkit/html/css/sipcombobox.xcss")
	};

private InternetResource[] stylesAll = null;

protected InternetResource[] getStyles() {
	synchronized (this) {
		if (stylesAll == null) {
			InternetResource[] rsrcs = super.getStyles();
			boolean ignoreSuper = rsrcs == null || rsrcs.length == 0;
			boolean ignoreThis = styles == null || styles.length == 0;
			
			if (ignoreSuper) {
				if (ignoreThis) {
					stylesAll = new InternetResource[0];	
				} else {
					stylesAll = styles;
				}
			} else {
				if (ignoreThis) {
					stylesAll = rsrcs;
				} else {
					java.util.Set rsrcsSet = new java.util.LinkedHashSet();

					for (int i = 0; i < rsrcs.length; i++ ) {
						rsrcsSet.add(rsrcs[i]);
					}

					for (int i = 0; i < styles.length; i++ ) {
						rsrcsSet.add(styles[i]);
					}

					stylesAll = (InternetResource[]) rsrcsSet.toArray(new InternetResource[rsrcsSet.size()]);
				}
			}
		}
	}
	
	return stylesAll;
}
	private final InternetResource[] scripts = {
						new org.ajax4jsf.javascript.PrototypeScript()
						,
				new org.ajax4jsf.javascript.AjaxScript()
						,
				getResource("/org/richfaces/renderkit/html/scripts/jquery/jquery.js")
						,
				getResource("scripts/sipcomboboxstyles.js")
						,
				getResource("scripts/sipcomboboxUtils.js")
						,
				getResource("scripts/sipcombolist.js")
						,
				getResource("scripts/sipcombobox.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/utils.js")
	};

private InternetResource[] scriptsAll = null;

protected InternetResource[] getScripts() {
	synchronized (this) {
		if (scriptsAll == null) {
			InternetResource[] rsrcs = super.getScripts();
			boolean ignoreSuper = rsrcs == null || rsrcs.length == 0;
			boolean ignoreThis = scripts == null || scripts.length == 0;
			
			if (ignoreSuper) {
				if (ignoreThis) {
					scriptsAll = new InternetResource[0];	
				} else {
					scriptsAll = scripts;
				}
			} else {
				if (ignoreThis) {
					scriptsAll = rsrcs;
				} else {
					java.util.Set rsrcsSet = new java.util.LinkedHashSet();

					for (int i = 0; i < rsrcs.length; i++ ) {
						rsrcsSet.add(rsrcs[i]);
					}

					for (int i = 0; i < scripts.length; i++ ) {
						rsrcsSet.add(scripts[i]);
					}

					scriptsAll = (InternetResource[]) rsrcsSet.toArray(new InternetResource[rsrcsSet.size()]);
				}
			}
		}
	}
	
	return scriptsAll;
}
	// 
	// 
	//


	private String convertToString(Object obj ) {
		return ( obj == null ? "" : obj.toString() );
	}
	private String convertToString(boolean b ) {
		return String.valueOf(b);
	}
	private String convertToString(int b ) {
		return b!=Integer.MIN_VALUE?String.valueOf(b):"";
	}
	private String convertToString(long b ) {
		return b!=Long.MIN_VALUE?String.valueOf(b):"";
	}
	
	private boolean isEmpty(Object o) {
		if (null == o) {
			return true;
		}
		if (o instanceof String ) {
			return (0 == ((String)o).length());
		}
		if (o instanceof Collection) {
			return (0 == ((Collection)o).size());
		}
		if (o instanceof Map) {
			return (0 == ((Map)o).size());
		}
		if (o.getClass().isArray()) {
			return (0 == ((Object [])o).length);
		}
		return false;
	}
	
	/**
	 * Get base component class, targetted for this renderer. Used for check arguments in decode/encode.
	 * @return
	 */
	protected Class getComponentClass() {
		return com.exadel.siperian.component.SipUIComboBox.class;
	}


	public void doEncodeEnd(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.SipUIComboBox component, ComponentVariables variables) throws IOException {
	  variables.setVariable("sp", getResource( "images/spacer.gif" ).getUri(context, component) );

java.lang.String clientId = component.getClientId(context);

		
            Boolean directInputSuggestions = (Boolean) component.getAttributes().get("directInputSuggestions");
            variables.setVariable("directInputSuggestions", directInputSuggestions);
            
            Boolean filterNewValues = (Boolean) component.getAttributes().get("filterNewValues");
            variables.setVariable("filterNewValues", filterNewValues);
            
            Boolean disabled = (Boolean) component.getAttributes().get("disabled");
            variables.setVariable("disabled", disabled);
            
            String listHeight = (String) component.getAttributes().get("listHeight");
			if (listHeight == null || listHeight.length() == 0 || listHeight.trim().startsWith("0")) {
				listHeight = "200px";
			} else {
				listHeight = HtmlUtil.qualifySize(listHeight);
			}
			variables.setVariable("listHeight", listHeight);
		
			    	
	    	String width = (String) component.getAttributes().get("width");
	    	String listWidth = (String) component.getAttributes().get("listWidth");
	    	String correction = null;
	    	if (width == null || (width.length() == 0) || (width.trim().startsWith("0")) ) {
	    	    width = "150px";
	    	     
	    	} else {
	    		width = HtmlUtil.qualifySize(width);
	    	}
	    	
	    	if (width.indexOf("%") == -1) {
	    		correction =  width.substring(0,width.indexOf("px"));  
    	    	correction = (Integer.parseInt(correction) - 10) + "px";
	    	}else {
	    		correction = width;
	    		//listWidth = width;
	    	}
	    	
	    	variables.setVariable("width", width);
	    	variables.setVariable("correction", correction);
	    	
	    	if (listWidth != null) {
	    		listWidth = HtmlUtil.qualifySize(listWidth);
	    	}
    	
	//    	if (listWidth == null || listWidth.length() == 0 || listWidth.trim().startsWith("0")) {
	//    		String listCorrection =  width.substring(0,width.indexOf("px"));  
	//    	    listCorrection = (Integer.parseInt(listCorrection) - 2) + "px";
	//    		listWidth = width;	    	   		
	//    	} else {
	//    		listWidth = HtmlUtil.qualifySize(listWidth);
	//    	}
	    	variables.setVariable("listWidth", listWidth);
	    	 
	    	
	    	String inputSize = (String) component.getAttributes().get("inputSize");
	    	variables.setVariable("inputSize", inputSize);
	    	
	    	String defaultLabel = (String) component.getAttributes().get("defaultLabel");
	    	variables.setVariable("defaultLabel", defaultLabel);
	    	
	    	Boolean selectFirstOnUpdate = (Boolean) component.getAttributes().get("selectFirstOnUpdate");
	        variables.setVariable("selectFirstOnUpdate", selectFirstOnUpdate);
	        
			Object value = component.getSubmittedValue();
			if (value == null) {
			    value = component.getAttributes().get("value");    
			}

       		String valueStyle = "sip-combobox-font-inactive";	
	    	value = getConvertedStringValue(context, component,value);
	    	if ("".equals(value)) {
				valueStyle = "sip-combobox-font-disabled";
				//value = defaultLabel;
	    	} 
	    	variables.setVariable("value", value);
	    	String convertedValue = encodeValue((String)value);
	    	variables.setVariable("convertedValue", convertedValue);
	    	
			variables.setVariable("valueStyle", valueStyle);

			Object inputStyle = component.getAttributes().get("inputStyle");
	    	variables.setVariable("inputStyle", inputStyle);
	    	
	    	Object inputClass = component.getAttributes().get("inputClass");
	    	if("".equals(inputClass)) {
	    		inputClass = null;
	    	}
	    	variables.setVariable("inputClass", inputClass);
	    	
	    	Object inputDisabledStyle = component.getAttributes().get("inputDisabledStyle");
	    	variables.setVariable("inputDisabledStyle", inputDisabledStyle);
	    	
	    	Object inputDisabledClass = component.getAttributes().get("inputDisabledClass");
	    	if("".equals(inputDisabledClass)) {
	    		inputDisabledClass = null;
	    	}
	    	variables.setVariable("inputDisabledClass", inputDisabledClass);
	    	
	    	Object inputInactiveStyle = component.getAttributes().get("inputInactiveStyle");
	    	variables.setVariable("inputInactiveStyle", inputInactiveStyle);
	    	
	    	Object inputInactiveClass = component.getAttributes().get("inputInactiveClass");
	    	if("".equals(inputInactiveClass)) {
	    		inputInactiveClass = null;
	    	}
	    	variables.setVariable("inputInactiveClass", inputInactiveClass);
	    	
	    	
	    	Object buttonInactiveClass = component.getAttributes().get("buttonInactiveClass");
	    	if("".equals(buttonInactiveClass)) {
	    		buttonInactiveClass = null;
	    	}
	    	variables.setVariable("buttonInactiveClass", buttonInactiveClass);
	    	
	    	Object buttonInactiveStyle = component.getAttributes().get("buttonInactiveStyle");
	    	variables.setVariable("buttonInactiveStyle", buttonInactiveStyle);
	    	
	    	Object buttonDisabledClass = component.getAttributes().get("buttonDisabledClass");
	    	if("".equals(buttonDisabledClass)) {
	    		buttonDisabledClass = null;
	    	}
	    	variables.setVariable("buttonDisabledClass", buttonDisabledClass);
	    	
	    	Object buttonDisabledStyle = component.getAttributes().get("buttonDisabledStyle");
	    	variables.setVariable("buttonDisabledStyle", buttonDisabledStyle);
	    	
	    	Object buttonClass = component.getAttributes().get("buttonClass");
	    	if("".equals(buttonClass)) {
	    		buttonClass = null;
	    	}
	    	variables.setVariable("buttonClass", buttonClass);
	    	
	    	Object buttonStyle = component.getAttributes().get("buttonStyle");
	    	variables.setVariable("buttonStyle", buttonStyle);
	    	
	    	Object listStyle = component.getAttributes().get("listStyle");
	    	variables.setVariable("listStyle", listStyle);
	    	
	    	Object listClass = component.getAttributes().get("listClass");
	    	if("".equals(listClass)) {
	    		listClass = null;
	    	}
	    	final String customIe7ListStyle = isNeedCustomClassForIE7(component,context);
			variables.setVariable("customIe7ListStyle",customIe7ListStyle);
	    	variables.setVariable("listClass", listClass);
	    	
	      	Object styleClass = component.getAttributes().get("styleClass");
	    	variables.setVariable("styleClass", styleClass);
	    	
	    	Object style = component.getAttributes().get("style");
	    	variables.setVariable("style", style);
	    	
	    	Object itemClass = component.getAttributes().get("itemClass");
	    	if("".equals(itemClass)) {
	    		itemClass = null;
	    	}
	    	variables.setVariable("itemClass", itemClass);
	    	
	      	Object itemSelectedClass = component.getAttributes().get("itemSelectedClass");
	    	if("".equals(itemSelectedClass)) {
	    		itemSelectedClass = null;
	    	}
	    	variables.setVariable("itemSelectedClass", itemSelectedClass);
	      	
	    	String buttonIcon = (String)component.getAttributes().get("buttonIcon");
    		if (!"".equals(buttonIcon)) {
    			buttonIcon = "url('" + getResource(buttonIcon).getUri(context, component) + "')";
    		}
    		variables.setVariable("buttonIcon", buttonIcon);
    		
    		String buttonIconDisabled = (String)component.getAttributes().get("buttonIconDisabled");
    		if (!"".equals(buttonIconDisabled)) {
    			buttonIconDisabled = "url('" +  getResource(buttonIconDisabled).getUri(context, component) + "')";
    		}
    		variables.setVariable("buttonIconDisabled", buttonIconDisabled);
    		
    		String buttonIconInactive = (String)component.getAttributes().get("buttonIconInactive");
    		if (!"".equals(buttonIconInactive)) {
    			buttonIconInactive = "url('" + getResource(buttonIconInactive).getUri(context, component) + "')";
    		}
    		String behaviourStrategy = (String)component.getAttributes().get("behaviourStrategy");
    		variables.setVariable("behaviourStrategy", behaviourStrategy);
       		variables.setVariable("buttonIconInactive", buttonIconInactive);
       		variables.setVariable("enableManualInput", !component.isEnableManualInput());
       		
		
    
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "id", clientId );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "sip-combobox-font sip-combobox " + convertToString(variables.getVariable("styleClass")) );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "combobox" );
						getUtils().writeAttribute(writer, "style", "width:" + convertToString(variables.getVariable("width")) + ";" + convertToString(variables.getVariable("style")) );
			//
// pass thru attributes
//
getUtils().encodeAttributesFromArray(context,component,new String[] {
    "align" ,
	    "dir" ,
	    "lang" ,
	    "onclick" ,
	    "ondblclick" ,
	    "onkeydown" ,
	    "onkeypress" ,
	    "onkeyup" ,
	    "onmousedown" ,
	    "onmousemove" ,
	    "onmouseout" ,
	    "onmouseover" ,
	    "onmouseup" ,
	    "title" ,
	    "xml:lang" });
//
//
//

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "sip-combobox-list-cord" );
			
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "sip-combobox-font sip-combobox-shell" );
						getUtils().writeAttribute(writer, "style", "width:" + convertToString(variables.getVariable("width")) + ";" );
			
writer.startElement("input", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + "comboboxFieldVirtual" );
						getUtils().writeAttribute(writer, "name", convertToString(clientId) + "comboboxFieldVirtual" );
						getUtils().writeAttribute(writer, "style", "display: none" );
			
writer.endElement("input");
writer.startElement("input", component);
			getUtils().writeAttribute(writer, "autocomplete", "off" );
						getUtils().writeAttribute(writer, "class", convertToString(variables.getVariable("valueStyle")) + " sip-combobox-input-inactive  " + convertToString(variables.getVariable("inputInactiveClass")) );
						getUtils().writeAttribute(writer, "disabled", variables.getVariable("disabled") );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "comboboxField" );
						getUtils().writeAttribute(writer, "name", convertToString(clientId) + "comboboxField" );
						getUtils().writeAttribute(writer, "onchange", component.getAttributes().get("onchange") );
						getUtils().writeAttribute(writer, "onfocus", component.getAttributes().get("onfocus") );
						getUtils().writeAttribute(writer, "readonly", variables.getVariable("enableManualInput") );
						getUtils().writeAttribute(writer, "style", "width:" + convertToString(variables.getVariable("correction")) + "; " + convertToString(variables.getVariable("inputInactiveStyle")) );
						getUtils().writeAttribute(writer, "tabindex", component.getAttributes().get("tabindex") );
						getUtils().writeAttribute(writer, "type", "text" );
						getUtils().writeAttribute(writer, "value", variables.getVariable("value") );
						getUtils().writeAttribute(writer, "onblur", component.getAttributes().get("onblur") );
			
writer.endElement("input");
writer.startElement("input", component);
			getUtils().writeAttribute(writer, "class", "sip-combobox-font-inactive sip-combobox-button-background sip-combobox-button-inactive" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "comboBoxButtonBG" );
						getUtils().writeAttribute(writer, "readonly", "true" );
						getUtils().writeAttribute(writer, "tabindex", "-1" );
						getUtils().writeAttribute(writer, "type", "text" );
						getUtils().writeAttribute(writer, "value", "" );
			
writer.endElement("input");
writer.startElement("input", component);
			getUtils().writeAttribute(writer, "class", "sip-combobox-font-inactive sip-combobox-button-icon-inactive sip-combobox-button-inactive " + convertToString(variables.getVariable("buttonInactiveClass")) );
						getUtils().writeAttribute(writer, "disabled", variables.getVariable("disabled") );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "comboboxButton" );
						getUtils().writeAttribute(writer, "readonly", "true" );
						getUtils().writeAttribute(writer, "style", convertToString(variables.getVariable("buttonStyle")) + "; background-image: " + convertToString(variables.getVariable("buttonIconInactive")) + ";" );
						getUtils().writeAttribute(writer, "tabindex", "-1" );
						getUtils().writeAttribute(writer, "type", "text" );
						getUtils().writeAttribute(writer, "value", "" );
			
writer.endElement("input");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "sip-combobox-strut sip-combobox-font" );
						getUtils().writeAttribute(writer, "style", "width:" + convertToString(variables.getVariable("correction")) );
			
writer.writeText(convertToString("Strut"),null);

writer.endElement("div");
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "sip-combobox-list-cord " + convertToString(variables.getVariable("listClass")) + " " + convertToString(variables.getVariable("customIe7ListStyle")) );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "listParent" );
						getUtils().writeAttribute(writer, "style", "display:none; " + convertToString(variables.getVariable("listStyle")) + "; position:absolute;z-index:1000;" );
			
java.util.List items  = encodeItems(context,component) ;
writer.endElement("div");

	
	    variables.setVariable("hiddenValue", value);
	 
	
writer.startElement("input", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + "comboboxValue" );
						getUtils().writeAttribute(writer, "name", clientId );
						getUtils().writeAttribute(writer, "type", "hidden" );
			
writer.endElement("input");
writer.endElement("div");
writer.startElement("script", component);
			getUtils().writeAttribute(writer, "type", "text/javascript" );
			
writer.writeText(convertToString("var clientId = '" + convertToString(clientId) + "';\n		var spacer = '" + convertToString(variables.getVariable("sp")) + "';\n 		\n				\n		var comboboxUserStyles = {\n			button : { \n				classes : \n					{\n						normal:  \"" + convertToString(variables.getVariable("buttonInactiveClass")) + "\",\n						active: \"" + convertToString(variables.getVariable("buttonClass")) + "\",\n						disabled: \"" + convertToString(variables.getVariable("buttonDisabledClass")) + "\" \n					},\n				style :\n					{\n						normal: \"" + convertToString(variables.getVariable("buttonInactiveStyle")) + "\",\n						active: \"" + convertToString(variables.getVariable("buttonStyle")) + "\",\n						disabled:	\"" + convertToString(variables.getVariable("buttonDisabledStyle")) + "\"\n					}	 \n			},\n			buttonicon : {\n			 	style :\n			 		{\n			 			normal: \"" + convertToString(variables.getVariable("buttonIconInactive")) + "\",\n						active: \"" + convertToString(variables.getVariable("buttonIcon")) + "\",\n						disabled: \"" + convertToString(variables.getVariable("buttonIconDisabled")) + "\"\n			 		}		   			   \n			},\n			field : {\n				classes :\n					{\n						normal:  \"" + convertToString(variables.getVariable("inputInactiveClass")) + "\",\n						active: \"" + convertToString(variables.getVariable("inputClass")) + "\",\n						disabled: \"" + convertToString(variables.getVariable("inputDisabledClass")) + "\" \n					},\n				style : \n					{\n						normal : \"" + convertToString(variables.getVariable("inputInactiveStyle")) + "\",\n					  	active : \"" + convertToString(variables.getVariable("inputStyle")) + "\",\n					  	disabled : \"" + convertToString(variables.getVariable("inputDisabledStyle")) + "\"\n					}	\n			},\n			combolist : {\n						list: {\n							classes: \n							 	{\n							 		active: \"" + convertToString(variables.getVariable("listClass")) + "\"\n							 	},\n							style: \n								{\n									active: \"'" + convertToString(variables.getVariable("listStyle")) + "\"\n								} 						\n						},\n						item: {\n							normal : \"" + convertToString(variables.getVariable("itemClass")) + "\",\n							selected: \"" + convertToString(variables.getVariable("itemSelectedClass")) + "\"\n						}\n			}\n		};\n		 \n		\n		var combobox = new RichFaces.SipComboBox( \"" + convertToString(clientId) + "\", \n							   				   \"" + convertToString(clientId) + "list\", \n							   				   \"" + convertToString(clientId) + "listParent\",\n							   				   \"" + convertToString(clientId) + "comboboxValue\",\n											   \"" + convertToString(clientId) + "comboboxField\", \n											   \"" + convertToString(clientId) + "comboboxButton\", \n											   \"" + convertToString(clientId) + "comboBoxButtonBG\",\n											   \"" + convertToString(clientId) + "shadow\",\n											   new Richfaces.SipComboBoxStyles(), \n											   comboboxUserStyles, \n											   \"" + convertToString(variables.getVariable("listWidth")) + "\", \"" + convertToString(variables.getVariable("listHeight")) + "\",\n											   " + convertToString(getItemsTextAsJSArray(context,component,items)) + ", \n											   " + convertToString(variables.getVariable("directInputSuggestions")) + ", \n											   " + convertToString(variables.getVariable("filterNewValues")) + ", \n											   " + convertToString(variables.getVariable("selectFirstOnUpdate")) + ",\n											   " + convertToString(getAsEventHandler(context,component,"onlistcall")) + ", \n											   " + convertToString(getAsEventHandler(context,component,"onlistclose")) + ", \n											   " + convertToString(getAsEventHandler(context,component,"onselect")) + ",\n											   \"" + convertToString(variables.getVariable("defaultLabel")) + "\",\n											   " + convertToString(variables.getVariable("disabled")) + ", " + convertToString(variables.getVariable("convertedValue")) + ", \n											   " + convertToString(component.getAttributes().get("showDelay")) + ", \n											   " + convertToString(component.getAttributes().get("hideDelay")) + ",\n											   spacer,\n											   " + convertToString(getCountOfItems(items)) + ",\n											   \"" + convertToString(variables.getVariable("behaviourStrategy")) + "\");"),null);

writer.endElement("script");
writer.endElement("div");

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (com.exadel.siperian.component.SipUIComboBox)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
