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


import com.exadel.siperian.renderkit.BarTimelineRenderBase;



/**
 * Renderer for component class com.exadel.siperian.renderkit.html.BarTimelineRenderer
 */
public class BarTimelineRenderer extends BarTimelineRenderBase {

	public BarTimelineRenderer () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] styles = {
						getResource("/com/exadel/siperian/renderkit/html/css/barTimeline.xcss")
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
				getResource("/org/richfaces/renderkit/html/scripts/common-scrollable-data-table.js")
						,
				getResource("/com/exadel/siperian/renderkit/html/js/swfobject.js")
						,
				getResource("/com/exadel/siperian/renderkit/html/js/bartimeline.js")
						,
				getResource("/com/exadel/siperian/renderkit/html/js/rightClick.js")
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
		return com.exadel.siperian.component.UIBarTimeline.class;
	}


	public void doEncodeEnd(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIBarTimeline component, ComponentVariables variables) throws IOException {
	  variables.setVariable("barTimelineUrl", getResource( "/com/exadel/siperian/renderkit/html/css/barTimeline.xcss" ).getUri(context, component) );

variables.setVariable("flashUrl", getResource( "/com/exadel/siperian/renderkit/html/swf/barTimeline.swf" ).getUri(context, component) );

variables.setVariable("expressInstallUrl", getResource( "/com/exadel/siperian/renderkit/html/swf/expressInstall.swf" ).getUri(context, component) );

java.lang.String clientId = component.getClientId(context);
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", component.getAttributes().get("styleClass") );
						getUtils().writeAttribute(writer, "id", clientId );
						getUtils().writeAttribute(writer, "style", "width: " + convertToString(component.getAttributes().get("width")) + "; height: " + convertToString(component.getAttributes().get("height")) );
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

writer.startElement("input", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":timelinePositionState" );
						getUtils().writeAttribute(writer, "name", convertToString(clientId) + ":timelinePositionState" );
						getUtils().writeAttribute(writer, "type", "hidden" );
						getUtils().writeAttribute(writer, "value", getPositionState(context,component) );
			
writer.endElement("input");
writer.startElement("input", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":timelineRulerState" );
						getUtils().writeAttribute(writer, "name", convertToString(clientId) + ":timelineRulerState" );
						getUtils().writeAttribute(writer, "type", "hidden" );
						getUtils().writeAttribute(writer, "value", getVerticalRulerState(context,component) );
			
writer.endElement("input");
writer.startElement("input", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":xml" );
						getUtils().writeAttribute(writer, "type", "hidden" );
						getUtils().writeAttribute(writer, "value", getXml(context,component) );
			
writer.endElement("input");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":flashContainer" );
						getUtils().writeAttribute(writer, "oncontextmenu", "return false;" );
						getUtils().writeAttribute(writer, "style", "position:relative" );
			

			
				if (Boolean.TRUE.equals(component.getAttributes().get("showFlashPlayerInstall"))) {
					                                               
		
writer.startElement("h1", component);

writer.writeText(convertToString("Timeline"),null);

writer.endElement("h1");
writer.startElement("p", component);

writer.writeText(convertToString("Alternative content"),null);

writer.endElement("p");
writer.startElement("p", component);

writer.startElement("a", component);
			getUtils().writeAttribute(writer, "href", "http://www.adobe.com/go/getflashplayer" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "alt", "Get Adobe Flash player" );
						getUtils().writeAttribute(writer, "src", "http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" );
			
writer.endElement("img");
writer.endElement("a");
writer.endElement("p");

		
			} 
		
		
writer.endElement("div");
writer.startElement("script", component);

writer.writeText(convertToString("new BarTimeline('" + convertToString(clientId) + "', '" + convertToString(variables.getVariable("flashUrl")) + "','" + convertToString(variables.getVariable("expressInstallUrl")) + "',\"" + convertToString(component.getAttributes().get("width")) + "\",\"" + convertToString(component.getAttributes().get("height")) + "\",\"" + convertToString(component.getAttributes().get("flashWmode")) + "\", '', \"" + convertToString(getTimelineStyleSheet(context,component)) + "\",null, " + convertToString(getEventHandlers(context,component)) + ");"),null);

writer.endElement("script");
writer.endElement("div");

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (com.exadel.siperian.component.UIBarTimeline)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
