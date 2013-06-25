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
import org.ajax4jsf.resource.InternetResource;
import org.ajax4jsf.resource.InternetResource;
//
//
//


import com.exadel.siperian.renderkit.TabPanelRendererBase;



/**
 * Renderer for component class com.exadel.siperian.renderkit.html.TabPanelRenderer
 */
public class TabPanelRenderer extends TabPanelRendererBase {

	public TabPanelRenderer () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] styles = {
						getResource("/com/exadel/siperian/renderkit/html/css/sipTabPanel.xcss")
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
				new org.ajax4jsf.javascript.ImageCacheScript()
						,
				getResource("/org/richfaces/renderkit/html/scripts/browser_info.js")
						,
				getResource("/org/ajax4jsf/javascript/scripts/form.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/utils.js")
						,
				getResource("scripts/horizontalScroller.js")
						,
				getResource("scripts/sipTabPanel.js")
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
		return com.exadel.siperian.component.UITabPanel.class;
	}

	
	public void doEncodeBegin(ResponseWriter writer, FacesContext context, UIComponent component ) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeBegin(writer, context, (com.exadel.siperian.component.UITabPanel)component, variables );
	}		

	public void doEncodeBegin(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UITabPanel component, ComponentVariables variables ) throws IOException {
	    
		
		         if (component.isNewTabs()) {
		        	 initNewTabs(component, context);
		        	 return;
		         }
		     	
		
java.lang.String clientId = component.getClientId(context);
variables.setVariable("arrowContext", getResource( "/com/exadel/siperian/renderkit/html/images/arrow-context.png" ).getUri(context, component) );

variables.setVariable("sipArrowContext", getResource( "/com/exadel/siperian/renderkit/html/images/sip-static-pnl-arrow-context.png" ).getUri(context, component) );

variables.setVariable("clear", getResource( "/com/exadel/siperian/renderkit/html/images/clear.gif" ).getUri(context, component) );

initVariables(context, component);


		
			com.exadel.siperian.renderkit.TabPanelClassBuilder builder = (com.exadel.siperian.renderkit.TabPanelClassBuilder)component.getAttributes().get(TabPanelRendererBase.CLASS_BUILDER_ATTRIBUTE);
			//variables.setVariable("classBuilder", builder);
			variables.setVariable("panelClass", builder.getPanelClass());
			variables.setVariable("headersContainerClass", builder.getHeadersContainerClass());
			variables.setVariable("scrollContainerClass", builder.getScrollContainerClass());
			variables.setVariable("contextArrowClass", builder.getTabContextArrowClass());
		     	
		
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "id", clientId );
						getUtils().writeAttribute(writer, "style", component.getAttributes().get("style") );
			
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
						getUtils().writeAttribute(writer, "style", "table-layout: fixed; position: relative;" );
						getUtils().writeAttribute(writer, "width", "100%" );
			
writer.startElement("tr", component);

writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", variables.getVariable("panelClass") );
			
getUtils().encodePassThruWithExclusions(context, component, "width,height,styleClass,class,id");

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", variables.getVariable("headersContainerClass") );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":tabsContainer" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", variables.getVariable("scrollContainerClass") );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":tabs" );
						getUtils().writeAttribute(writer, "style", "visibility: hidden" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "style", "width: 10000px;" );
			
encodeTabs(context, component);

writer.endElement("div");
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", variables.getVariable("contextArrowClass") );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":tabListHeaderDiv" );
						getUtils().writeAttribute(writer, "style", "visibility: hidden" );
			
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "arrowLeftDis" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":left" );
						getUtils().writeAttribute(writer, "style", "visibility: hidden" );
			
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "arrowRight" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":right" );
						getUtils().writeAttribute(writer, "style", "visibility: hidden" );
			
writer.endElement("div");
writer.endElement("div");
 if (builder.isDynamic()) { 
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_panel_bottom_container" );
			
writer.endElement("div");
 } 
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "style", "position: relative; width: 100%" );
			

		
		         if (builder.isDynamic()) {
			
		
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_content" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":content" );
						getUtils().writeAttribute(writer, "style", "visibility: hidden;" );
			

				
		        	 }else {
		     		
		
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
						getUtils().writeAttribute(writer, "class", "sip-static-pnl-content-container" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":contentContainer" );
			
writer.startElement("tr", component);
			getUtils().writeAttribute(writer, "style", "height: 10px" );
			
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "align", "left" );
						getUtils().writeAttribute(writer, "class", "sip-static-pnl-bg-left-top" );
						getUtils().writeAttribute(writer, "style", "width: 10px" );
			
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "sip-static-pnl-bg-top" );
						getUtils().writeAttribute(writer, "style", "overflow: hidden" );
			
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "align", "right" );
						getUtils().writeAttribute(writer, "class", "sip-static-pnl-bg-right-top-noround" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":rightTop" );
						getUtils().writeAttribute(writer, "style", "width: 10px" );
			
writer.endElement("td");
writer.endElement("tr");
writer.startElement("tr", component);

writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "sip-static-pnl-bg-left" );
			
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "sip-static-pnl-content" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":content" );
						getUtils().writeAttribute(writer, "style", "visibility: hidden;" );
			

		
		         }
		     	
		

	}		
	
    public void doEncodeChildren(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeChildren(writer, context, (com.exadel.siperian.component.UITabPanel)component, variables );
	}		

    public void doEncodeChildren(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UITabPanel component, ComponentVariables variables) throws IOException {
	    
renderChildren(context, component);


	}		

	/* (non-Javadoc)
	 * @see javax.faces.render.Renderer#getRendersChildren()
	 */
	public boolean getRendersChildren() {
		return true;
	}

	public void doEncodeEnd(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UITabPanel component, ComponentVariables variables) throws IOException {
	  

		
		         if (component.isNewTabs()) {
		        	 return;
		         }
		     	
		
java.lang.String clientId = component.getClientId(context);

				 com.exadel.siperian.renderkit.TabPanelClassBuilder builder = (com.exadel.siperian.renderkit.TabPanelClassBuilder)component.getAttributes().get(TabPanelRendererBase.CLASS_BUILDER_ATTRIBUTE);
				 if (builder == null) {
					 builder = new com.exadel.siperian.renderkit.TabPanelClassBuilder(component);
				 }
		         if (!builder.isDynamic()) {
			
		
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "sip-static-pnl-bg-right" );
			
writer.endElement("td");
writer.endElement("tr");
writer.startElement("tr", component);
			getUtils().writeAttribute(writer, "style", "height: 10px" );
			
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "sip-static-pnl-bg-left-bottom" );
			
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "sip-static-pnl-bg-bottom" );
			
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "sip-static-pnl-bg-right-bottom" );
			
writer.endElement("td");
writer.endElement("tr");
writer.endElement("table");

		
		         }else {
		     	
		
writer.endElement("div");

		         }
		 
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
						getUtils().writeAttribute(writer, "class", "tabList" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":tabListDiv" );
						getUtils().writeAttribute(writer, "style", "visibility: hidden;" );
			
writer.startElement("tr", component);

writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "tabListLeft" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":tabListLeft" );
			
writer.endElement("div");
writer.endElement("td");
writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "tabListContent" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":tabListContentDiv" );
			
writer.endElement("div");
writer.endElement("td");
writer.endElement("tr");
writer.startElement("tr", component);

writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "tabListCorner" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":tabListCorner" );
			
writer.endElement("div");
writer.endElement("td");
writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "tabListBottom" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":tabListBottom" );
			
writer.endElement("div");
writer.endElement("td");
writer.endElement("tr");
writer.endElement("table");
writer.endElement("div");
writer.startElement("script", component);

writer.writeText(convertToString("new TabPanel(\n					'" + convertToString(clientId) + "', \n					'" + convertToString(getContainerId(context,component)) + "',\n					'" + convertToString(component.getAttributes().get("selectedTab")) + "',\n					" + convertToString(component.getAttributes().get("maxTabWidth")) + ",\n					" + convertToString(writeEventHandlerFunction(context,component,"ontabchange")) + ",\n					\"" + convertToString(component.getAttributes().get("ontabclosed")) + "\",\n					" + convertToString(getTabsInfo(context,component)) + ",\n					\"" + convertToString(component.getAttributes().get("markupTemplate")) + "\",\n					" + convertToString(getAddNewTabScript(context,component)) + ",\n					\"" + convertToString(component.getAttributes().get("highlightStyleClass")) + "\", \n					\"" + convertToString(component.getAttributes().get("tabListHeight")) + "\");"),null);

writer.endElement("script");
writer.startElement("br", component);
			getUtils().writeAttribute(writer, "clear", "all" );
			
writer.endElement("br");
resetVariables(context, component);

writer.endElement("div");
writer.endElement("td");
writer.endElement("tr");
writer.endElement("table");
writer.endElement("div");

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (com.exadel.siperian.component.UITabPanel)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
