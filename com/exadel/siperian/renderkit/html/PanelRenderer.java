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
//
//
//


import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;



/**
 * Renderer for component class com.exadel.siperian.renderkit.html.PanelRenderer
 */
public class PanelRenderer extends HeaderResourcesRendererBase {

	public PanelRenderer () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] styles = {
						getResource("css/sippanel.xcss")
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
		return com.exadel.siperian.component.UIPanel.class;
	}

	
	public void doEncodeBegin(ResponseWriter writer, FacesContext context, UIComponent component ) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeBegin(writer, context, (com.exadel.siperian.component.UIPanel)component, variables );
	}		

	public void doEncodeBegin(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIPanel component, ComponentVariables variables ) throws IOException {
	    java.lang.String clientId = component.getClientId(context);
variables.setVariable("emptyPng", getResource( "images/empty.gif" ).getUri(context, component) );

writer.startElement("table", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
						getUtils().writeAttribute(writer, "height", "100%" );
						getUtils().writeAttribute(writer, "id", clientId );
						getUtils().writeAttribute(writer, "style", component.getAttributes().get("style") );
						getUtils().writeAttribute(writer, "width", "100%" );
			//
// pass thru attributes
//
getUtils().encodeAttributesFromArray(context,component,new String[] {
    "align" ,
	    "bgcolor" ,
	    "border" ,
	    "cellpadding" ,
	    "cellspacing" ,
	    "dir" ,
	    "frame" ,
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
	    "rules" ,
	    "summary" ,
	    "title" ,
	    "width" ,
	    "xml:lang" });
//
//
//

writer.startElement("tr", component);

writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_panel_bgtl" );
			
writer.endElement("div");
writer.endElement("td");
writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_panel_bgt" );
			
writer.endElement("div");
writer.endElement("td");
writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_panel_bgtr" );
			
writer.endElement("div");
writer.endElement("td");
writer.endElement("tr");
writer.startElement("tr", component);

writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_panel_bgl" );
			
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_panel_content" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "1" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("emptyPng") );
						getUtils().writeAttribute(writer, "width", component.getAttributes().get("minWidth") );
			
writer.endElement("img");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":sipbody" );
			

	}		
	
    public void doEncodeChildren(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeChildren(writer, context, (com.exadel.siperian.component.UIPanel)component, variables );
	}		

    public void doEncodeChildren(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIPanel component, ComponentVariables variables) throws IOException {
	    
renderChildren(context, component);


	}		

	/* (non-Javadoc)
	 * @see javax.faces.render.Renderer#getRendersChildren()
	 */
	public boolean getRendersChildren() {
		return true;
	}

	public void doEncodeEnd(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIPanel component, ComponentVariables variables) throws IOException {
	  
writer.endElement("div");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_panel_bgr" );
			
writer.endElement("td");
writer.endElement("tr");
writer.startElement("tr", component);

writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_panel_bgbl" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "15" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("emptyPng") );
						getUtils().writeAttribute(writer, "width", "15" );
			
writer.endElement("img");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_panel_bgb" );
			
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_panel_bgbr" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "15" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("emptyPng") );
						getUtils().writeAttribute(writer, "width", "15" );
			
writer.endElement("img");
writer.endElement("td");
writer.endElement("tr");
writer.endElement("table");
java.lang.String clientId = component.getClientId(context);

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (com.exadel.siperian.component.UIPanel)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
