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

package com.exadel.siperian;


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


import com.exadel.siperian.renderkit.IconRenderBase;



/**
 * Renderer for component class com.exadel.siperian.IconRenderer
 */
public class IconRenderer extends IconRenderBase {

	public IconRenderer () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] scripts = {
						new org.ajax4jsf.javascript.PrototypeScript()
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
						getResource("/com/exadel/siperian/renderkit/html/css/icon.xcss")
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
		return com.exadel.siperian.component.UIIcon.class;
	}


	public void doEncodeEnd(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIIcon component, ComponentVariables variables) throws IOException {
	  java.lang.String clientId = component.getClientId(context);
writer.startElement("a", component);
			getUtils().writeAttribute(writer, "href", "javascript:void(0);" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "iconLink" );
						getUtils().writeAttribute(writer, "style", "color:transparent !important" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "icon" );
						getUtils().writeAttribute(writer, "id", clientId );
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
	    "style" ,
	    "title" ,
	    "xml:lang" });
//
//
//


		
			boolean disabled = getUtils().isBooleanAttribute(component, "disabled");
			if(!disabled){
		
	
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "iconCommon" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "container" );
			
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
			
writer.startElement("tr", component);

writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "left" );
			
writer.write("&#160;&#160;");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "middle" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "class", "iconImage" );
						getUtils().writeAttribute(writer, "height", "16" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "IconImage" );
						getUtils().writeAttribute(writer, "width", "16" );
			

									
										String src = getIconURI(context, component);
										getUtils().writeAttribute(writer, "src", src);	    
									
								
writer.endElement("img");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "right" );
			
writer.write("&#160;&#160;");
writer.endElement("td");
writer.endElement("tr");
writer.endElement("table");
writer.endElement("div");

		
			}else{
		
	
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
						getUtils().writeAttribute(writer, "class", "disabled" );
			
writer.startElement("tr", component);

writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "left" );
			
writer.write("&#160;&#160;");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "middle" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "class", "iconImage" );
						getUtils().writeAttribute(writer, "height", "16" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "IconImage" );
						getUtils().writeAttribute(writer, "width", "16" );
			

									
										String src = getIconURI(context, component);
										getUtils().writeAttribute(writer, "src", src);	    
									
								
writer.endElement("img");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "right" );
			
writer.write("&#160;&#160;");
writer.endElement("td");
writer.endElement("tr");
writer.endElement("table");

		
			}
		
	
writer.endElement("div");
writer.endElement("a");

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (com.exadel.siperian.component.UIIcon)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
