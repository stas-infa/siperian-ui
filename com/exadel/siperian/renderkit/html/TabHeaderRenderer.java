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
//
//
//


import com.exadel.siperian.renderkit.TabHeaderRendererBase;



/**
 * Renderer for component class com.exadel.siperian.renderkit.html.TabHeaderRenderer
 */
public class TabHeaderRenderer extends TabHeaderRendererBase {

	public TabHeaderRenderer () {
		super();
	}

	// 
	// Declarations
	//
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
		return javax.faces.component.UIComponent.class;
	}


	public void doEncodeEnd(ResponseWriter writer, FacesContext context, javax.faces.component.UIComponent component, ComponentVariables variables) throws IOException {
	  java.lang.String clientId = component.getClientId(context);
variables.setVariable("closeImage", getResource( "/com/exadel/siperian/renderkit/html/images/spn_close_btn.png" ).getUri(context, component) );

variables.setVariable("processImage", getResource( "/com/exadel/siperian/renderkit/html/images/spn_ico_tab_process.gif" ).getUri(context, component) );


		
		       
		    Boolean isActive = isTabRendered(context, component);
		    com.exadel.siperian.renderkit.TabPanelClassBuilder builder = getBuilder(component);
		   
			String tabClassName = isActive ? builder.getActiveTabClass()  : builder.getInactiveTabClass();
			String tabLinkClass = isActive ? "activeTabLink"  : "inactiveTabLink";
			String tabStyle = isFirstTab(component) ? "padding-left: 0px; " : "";
			
			
		
			variables.setVariable("tabClassName", tabClassName);
			variables.setVariable("tabStyle", tabStyle);
			variables.setVariable("tabLinkClass", tabLinkClass);
			variables.setVariable("tabLinkClass", tabLinkClass);
	
		
	
collectTabInfo(context, component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", variables.getVariable("tabClassName") );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":header" );
						getUtils().writeAttribute(writer, "onclick", getSwitchScript(context,component) );
						getUtils().writeAttribute(writer, "style", variables.getVariable("tabStyle") );
			

					
			         	if (builder.isDynamic()) {
		     		
			
writer.startElement("a", component);
			getUtils().writeAttribute(writer, "class", variables.getVariable("tabLinkClass") );
						getUtils().writeAttribute(writer, "href", "#" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":headerLink" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "left" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":headerLeft" );
			

					
					    UIComponent facet = component.getFacet("infoImage");     
						if(facet != null && facet.isRendered()) {
					
				
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":faceContainer" );
						getUtils().writeAttribute(writer, "style", "float: left" );
			

												     	
							renderChild(context, facet);
							
						
writer.endElement("div");

					
						}     
					
					
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_sr_tabpanel_contentActive" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":headerLabel" );
						getUtils().writeAttribute(writer, "onclick", component.getAttributes().get("onlabelclick") );
						getUtils().writeAttribute(writer, "ondblclick", component.getAttributes().get("onlabeldblclick") );
						getUtils().writeAttribute(writer, "onkeydown", component.getAttributes().get("onlabelkeydown") );
						getUtils().writeAttribute(writer, "onkeypress", component.getAttributes().get("onlabelkeypress") );
						getUtils().writeAttribute(writer, "onkeyup", component.getAttributes().get("onlabelkeyup") );
						getUtils().writeAttribute(writer, "onmousedown", component.getAttributes().get("onlabelmousedown") );
						getUtils().writeAttribute(writer, "onmousemove", component.getAttributes().get("onlabelmousemove") );
						getUtils().writeAttribute(writer, "onmouseup", component.getAttributes().get("onlabelmouseup") );
						getUtils().writeAttribute(writer, "style", "float: left" );
			
encodeLabel(context, component);

writer.endElement("div");

					
					     Boolean isCloseable = ((com.exadel.siperian.component.UITab)component).getTabCloseable();
			        	 if (isCloseable) {
		     		
				
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "tabPanelIcon" );
						getUtils().writeAttribute(writer, "onclick", getCloseScript(context,component) );
			

					
					     Boolean isLongRunning = ((com.exadel.siperian.component.UITab)component).getLongRunning();
			        	 if (!isLongRunning) {
		     		
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "13" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":headerClose" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("closeImage") );
						getUtils().writeAttribute(writer, "width", "13" );
			
writer.endElement("img");

					
			         	}else {
		     		
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "13" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":headerClose" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("processImage") );
						getUtils().writeAttribute(writer, "width", "13" );
			
writer.endElement("img");

					
			         	}
		     		
writer.endElement("div");

					
			         	}
		     		
				
writer.endElement("div");
writer.writeComment(convertToString(" TODO nick - some AJAX parsers can remove empty DIV elements "));

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "right" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":headerRight" );
			
writer.endElement("div");
writer.endElement("a");

					
			         	}else {
		     		
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "align", "center" );
						getUtils().writeAttribute(writer, "class", "sip-static-pnl-left sip-static-pnl-leftActive" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":headerLeft" );
			

					
					    UIComponent facet = component.getFacet("infoImage");     
						if(facet != null && facet.isRendered()) {
					
				
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":faceContainer" );
						getUtils().writeAttribute(writer, "style", "float: left" );
			

												     	
							renderChild(context, facet);
							
						
writer.endElement("div");

					
						}     
					
					
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":headerLabel" );
						getUtils().writeAttribute(writer, "style", "float: left" );
			
encodeLabel(context, component);

writer.endElement("div");
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "sip-static-pnl-right" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":headerRight" );
			
writer.writeComment(convertToString(" TODO nick - some AJAX parsers can remove empty DIV elements "));

writer.startElement("div", component);

writer.endElement("div");
writer.endElement("div");

					
			         	}
		     		
			
writer.endElement("div");

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (javax.faces.component.UIComponent)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
