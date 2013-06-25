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


import com.exadel.siperian.renderkit.html.ToolBarRendererBase;



/**
 * Renderer for component class com.exadel.siperian.renderkit.html.ToolBarRenderer
 */
public class ToolBarRenderer extends ToolBarRendererBase {

	public ToolBarRenderer () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] styles = {
						getResource("css/sipToolBar.xcss")
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
				getResource("scripts/toolBar.js")
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
		return org.richfaces.component.UIToolBar.class;
	}

	
	public void doEncodeBegin(ResponseWriter writer, FacesContext context, UIComponent component ) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeBegin(writer, context, (org.richfaces.component.UIToolBar)component, variables );
	}		

	public void doEncodeBegin(ResponseWriter writer, FacesContext context, org.richfaces.component.UIToolBar component, ComponentVariables variables ) throws IOException {
	    variables.setVariable("left", getResource( "/com/exadel/siperian/renderkit/html/images/toolbar_left.png" ).getUri(context, component) );

variables.setVariable("right", getResource( "/com/exadel/siperian/renderkit/html/images/toolbar_right.png" ).getUri(context, component) );

variables.setVariable("clear", getResource( "/com/exadel/siperian/renderkit/html/images/clear.gif" ).getUri(context, component) );

java.lang.String clientId = component.getClientId(context);


	variables.setVariable("isDisabled", isDisabled(component));
	variables.setVariable("isHighlighted", isHighlighted(component));


writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "siptoolbar" );
						getUtils().writeAttribute(writer, "id", clientId );
			
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
						getUtils().writeAttribute(writer, "class", component.getAttributes().get("styleClass") );
						getUtils().writeAttribute(writer, "height", "30" );
						getUtils().writeAttribute(writer, "onclick", component.getAttributes().get("onclick") );
						getUtils().writeAttribute(writer, "ondblclick", component.getAttributes().get("ondblclick") );
						getUtils().writeAttribute(writer, "onkeydown", component.getAttributes().get("onkeydown") );
						getUtils().writeAttribute(writer, "onkeypress", component.getAttributes().get("onkeypress") );
						getUtils().writeAttribute(writer, "onkeyup", component.getAttributes().get("onkeyup") );
						getUtils().writeAttribute(writer, "onmousedown", component.getAttributes().get("onmousedown") );
						getUtils().writeAttribute(writer, "onmousemove", component.getAttributes().get("onmousemove") );
						getUtils().writeAttribute(writer, "onmouseout", component.getAttributes().get("onmouseout") );
						getUtils().writeAttribute(writer, "onmouseover", component.getAttributes().get("onmouseover") );
						getUtils().writeAttribute(writer, "onmouseup", component.getAttributes().get("onmouseup") );
						getUtils().writeAttribute(writer, "style", component.getAttributes().get("style") );
						getUtils().writeAttribute(writer, "width", component.getAttributes().get("width") );
			
writer.startElement("tr", component);
			getUtils().writeAttribute(writer, "valign", "middle" );
			
writer.startElement("td", component);

writer.startElement("img", component);
			getUtils().writeAttribute(writer, "height", "30" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("left") );
						getUtils().writeAttribute(writer, "width", "10" );
			
writer.endElement("img");
writer.endElement("td");

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, org.richfaces.component.UIToolBar component, ComponentVariables variables) throws IOException {
	  
writer.startElement("td", component);

writer.startElement("img", component);
			getUtils().writeAttribute(writer, "height", "30" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("right") );
						getUtils().writeAttribute(writer, "width", "10" );
			
writer.endElement("img");
writer.endElement("td");
writer.endElement("tr");
writer.endElement("table");
java.lang.String clientId = component.getClientId(context);
writer.startElement("script", component);

writer.writeText(convertToString("new ToolBar(\"" + convertToString(clientId) + "\", " + convertToString(variables.getVariable("isDisabled")) + ", '" + convertToString(variables.getVariable("left")) + "', " + convertToString(getDisabledGroupsNumbers(component)) + ", " + convertToString(variables.getVariable("isHighlighted")) + ");"),null);

writer.endElement("script");
writer.endElement("div");

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (org.richfaces.component.UIToolBar)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
