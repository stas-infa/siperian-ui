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


import com.exadel.siperian.renderkit.html.DropDownMenuRendererBase;



/**
 * Renderer for component class com.exadel.siperian.renderkit.html.DropDownMenuRenderer
 */
public class DropDownMenuRenderer extends DropDownMenuRendererBase {

	public DropDownMenuRenderer () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] styles = {
						getResource("css/sipdropdownmenu.xcss")
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
		return com.exadel.siperian.component.UIDropDownMenu.class;
	}

	
	public void doEncodeBegin(ResponseWriter writer, FacesContext context, UIComponent component ) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeBegin(writer, context, (com.exadel.siperian.component.UIDropDownMenu)component, variables );
	}		

	public void doEncodeBegin(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIDropDownMenu component, ComponentVariables variables ) throws IOException {
	    java.lang.String clientId = component.getClientId(context);

		com.exadel.siperian.component.UIDropDownMenu menu = (com.exadel.siperian.component.UIDropDownMenu) component;
		        if (!menu.getSubmitMode().equalsIgnoreCase("none"))
		        	org.richfaces.component.util.FormUtil.throwEnclFormReqExceptionIfNeed(context,component);
		        
    			if (!menu.isDisabled()) {
    
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "dr-menu-label dr-menu-label-unselect rich-ddmenu-label rich-ddmenu-label-unselect " + convertToString(component.getAttributes().get("styleClass")) + " " + convertToString(component.getAttributes().get("labelClass")) );
						getUtils().writeAttribute(writer, "id", clientId );
						getUtils().writeAttribute(writer, "onmousemove", component.getAttributes().get("onmousemove") );
						getUtils().writeAttribute(writer, "onmouseout", component.getAttributes().get("onmouseout") );
						getUtils().writeAttribute(writer, "onmouseover", component.getAttributes().get("onmouseover") );
						getUtils().writeAttribute(writer, "style", "outline: none; " + convertToString(component.getAttributes().get("style")) );
						getUtils().writeAttribute(writer, "tabindex", "-1" );
			
	
		 } else {  
	
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "dr-menu-label dr-menu-label-unselect dr-ddmenu-label-disabled rich-ddmenu-label-disabled rich-ddmenu-label-unselect " + convertToString(component.getAttributes().get("styleClass")) + " " + convertToString(component.getAttributes().get("disabledLabelClass")) );
						getUtils().writeAttribute(writer, "id", clientId );
						getUtils().writeAttribute(writer, "onmousemove", component.getAttributes().get("onmousemove") );
						getUtils().writeAttribute(writer, "onmouseout", component.getAttributes().get("onmouseout") );
						getUtils().writeAttribute(writer, "onmouseover", component.getAttributes().get("onmouseover") );
						getUtils().writeAttribute(writer, "style", "outline: none; " + convertToString(component.getAttributes().get("style")) );
						getUtils().writeAttribute(writer, "tabindex", "-1" );
			

		 } if (menu.isDisabled() &&
			(component.getFacet("labelDisabled")!=null && component.getFacet("labelDisabled").isRendered())) {
	
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "dr-label-text-decor rich-label-text-decor" );
			
UIComponent indexChildren_1 = component.getFacet("labelDisabled");
if (null != indexChildren_1 && indexChildren_1 .isRendered()) {
	renderChild(context, indexChildren_1);
}

writer.endElement("div");
			
			} else if(component.getFacet("label")!=null && component.getFacet("label").isRendered()) {
		 
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "dr-label-text-decor rich-label-text-decor" );
			
UIComponent indexChildren_2 = component.getFacet("label");
if (null != indexChildren_2 && indexChildren_2 .isRendered()) {
	renderChild(context, indexChildren_2);
}

writer.endElement("div");
	
					} else { 
		
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "dr-label-text-decor rich-label-text-decor" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "_span" );
			
writer.writeText(convertToString(component.getAttributes().get("value")),null);

writer.endElement("div");
	
					} 
		
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "style", "margin: 0px; padding: 0px; border: 0px; position: absolute; z-index: 100;" );
			

	}		
	
    public void doEncodeChildren(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeChildren(writer, context, (com.exadel.siperian.component.UIDropDownMenu)component, variables );
	}		

    public void doEncodeChildren(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIDropDownMenu component, ComponentVariables variables) throws IOException {
	    
renderChildren(context, component);


	}		

	/* (non-Javadoc)
	 * @see javax.faces.render.Renderer#getRendersChildren()
	 */
	public boolean getRendersChildren() {
		return true;
	}

	public void doEncodeEnd(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIDropDownMenu component, ComponentVariables variables) throws IOException {
	  
writer.endElement("div");

		if (!((com.exadel.siperian.component.UIDropDownMenu) component).isDisabled()) {
    
writer.endElement("div");
	
		} else { 
	
writer.endElement("div");
	
		}  
	

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (com.exadel.siperian.component.UIDropDownMenu)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
