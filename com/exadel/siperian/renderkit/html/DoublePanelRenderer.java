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


import com.exadel.siperian.renderkit.DoublePanelRendererBase;



/**
 * Renderer for component class com.exadel.siperian.renderkit.html.DoublePanelRenderer
 */
public class DoublePanelRenderer extends DoublePanelRendererBase {

	public DoublePanelRenderer () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] scripts = {
						new org.ajax4jsf.javascript.PrototypeScript()
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
	private final InternetResource[] styles = {
						getResource("css/sipDoublePanel.xcss")
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
		return com.exadel.siperian.component.UIDoublePanel.class;
	}


	public void doEncodeEnd(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIDoublePanel component, ComponentVariables variables) throws IOException {
	  java.lang.String clientId = component.getClientId(context);
variables.setVariable("emptyGif", getResource( "images/sip_dbl_panel_empty.gif" ).getUri(context, component) );

writer.startElement("table", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
						getUtils().writeAttribute(writer, "height", "100%" );
						getUtils().writeAttribute(writer, "id", clientId );
						getUtils().writeAttribute(writer, "width", "100%" );
			
writer.startElement("tr", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":leftImages" );
			
writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgtl" );
			
writer.endElement("div");
writer.endElement("td");
writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgtleft" );
						getUtils().writeAttribute(writer, "style", "width: " + convertToString(component.getAttributes().get("leftPanelWidth")) + "px" );
			
writer.endElement("div");
writer.endElement("td");
writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgct" );
			
writer.endElement("div");
writer.endElement("td");
writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgtright" );
			
writer.endElement("div");
writer.endElement("td");
writer.startElement("td", component);

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgtr" );
			
writer.endElement("div");
writer.endElement("td");
writer.endElement("tr");
writer.startElement("tr", component);

writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgl" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":bgl" );
			
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_contentleft" );
						getUtils().writeAttribute(writer, "style", "width: " + convertToString(component.getAttributes().get("leftPanelWidth")) + "px" );
			
encodeLeftContent(context, component);

writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "1" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("emptyGif") );
						getUtils().writeAttribute(writer, "width", component.getAttributes().get("leftPanelWidth") );
			
writer.endElement("img");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgcc" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":bgcc" );
						getUtils().writeAttribute(writer, "style", "height: " + convertToString(component.getAttributes().get("defaultHeight")) + "px" );
			
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_contentright" );
			
encodeRightContent(context, component);

writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "1" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("emptyGif") );
						getUtils().writeAttribute(writer, "width", component.getAttributes().get("rightPanelMinWidth") );
			
writer.endElement("img");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgr" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":bgr" );
			
writer.endElement("td");
writer.endElement("tr");
writer.startElement("tr", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":rightImages" );
			
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgbl" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "15" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("emptyGif") );
						getUtils().writeAttribute(writer, "width", "15" );
			
writer.endElement("img");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgbleft" );
						getUtils().writeAttribute(writer, "style", "width: " + convertToString(component.getAttributes().get("leftPanelWidth")) + "px" );
			
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgcb" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "15" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("emptyGif") );
						getUtils().writeAttribute(writer, "width", "30" );
			
writer.endElement("img");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgbright" );
			
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn_dbl_panel_bgbr" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "15" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("emptyGif") );
						getUtils().writeAttribute(writer, "width", "15" );
			
writer.endElement("img");
writer.endElement("td");
writer.endElement("tr");
writer.startElement("script", component);

writer.writeText(convertToString("if (isIE()) {\n        		PNGFIX.pngfixElements($('" + convertToString(clientId) + ":leftImages'), $('" + convertToString(clientId) + ":bgl'), $('" + convertToString(clientId) + ":bgcc'), $('" + convertToString(clientId) + ":bgr'), $('" + convertToString(clientId) + ":rightImages'));\n        	}"),null);

writer.endElement("script");
writer.endElement("table");

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (com.exadel.siperian.component.UIDoublePanel)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
