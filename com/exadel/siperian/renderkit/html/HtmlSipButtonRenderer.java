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


import com.exadel.siperian.renderkit.SipButtonRendererBase;



/**
 * Renderer for component class com.exadel.siperian.renderkit.html.HtmlSipButtonRenderer
 */
public class HtmlSipButtonRenderer extends SipButtonRendererBase {

	public HtmlSipButtonRenderer () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] styles = {
						getResource("/com/exadel/siperian/renderkit/html/css/button.xcss")
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
				getResource("/org/ajax4jsf/javascript/scripts/form.js")
						,
				getResource("/com/exadel/siperian/renderkit/html/scripts/sipButton.js")
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
	
   public void encodeIcon(FacesContext context, UIComponent component) throws IOException {
      ResponseWriter writer = context.getResponseWriter();
      String iconAlign =   (String) component.getAttributes().get("iconAlign");
      String icon =  (String) component.getAttributes().get("icon");
      if("right".equals(iconAlign)){
         writer.writeText(convertToString(getValue(component)),null);
      }
      if(icon != null){
         icon = context.getApplication().getViewHandler().getResourceURL(context, icon);
         icon = context.getExternalContext().encodeResourceURL(icon);
         writer.startElement("img", component);
         writer.writeURIAttribute("src", icon, null);
         writer.writeAttribute("width", "9", null);
         writer.writeAttribute("height", "9", null);
         writer.writeAttribute("class", "icon-"+iconAlign, null);        
         writer.endElement("img");
      }     
      if("left".equals(iconAlign)){
         writer.writeText(convertToString(getValue(component)),null);
      }
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
		return javax.faces.component.UICommand.class;
	}


	public void doEncodeEnd(ResponseWriter writer, FacesContext context, javax.faces.component.UICommand component, ComponentVariables variables) throws IOException {
	  java.lang.String clientId = component.getClientId(context);
variables.setVariable("icon", getResource( "/com/exadel/siperian/renderkit/html/images/st.png" ).getUri(context, component) );

variables.setVariable("clear", getResource( "/com/exadel/siperian/renderkit/html/images/clear.gif" ).getUri(context, component) );


   
         String onclickhandler = "if (!(this.getAttribute('sipdisabled')=='true')) {" + getOnClick(context,component) + "; } else {return false;}";
         variables.setVariable("onclickhandler", onclickhandler);
      
   
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
						getUtils().writeAttribute(writer, "id", clientId );
			
writer.startElement("tbody", component);

writer.startElement("tr", component);

writer.startElement("td", component);

writer.startElement("a", component);
			getUtils().writeAttribute(writer, "href", "javascript:void(0);" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "butLink" );
						getUtils().writeAttribute(writer, "onblur", "if (!isIE7()) {this.className='normalButtonAnchor';}" );
						getUtils().writeAttribute(writer, "onfocus", "if (!isIE7()) {this.className='dottedButtonAnchor';}" );
						getUtils().writeAttribute(writer, "onkeydown", "if(event.keyCode == 32 || event.keyCode == 13){this.onmousedown();return false;}" );
						getUtils().writeAttribute(writer, "onmousedown", variables.getVariable("onclickhandler") );
						getUtils().writeAttribute(writer, "sipdisabled", component.getAttributes().get("disabled") );
						getUtils().writeAttribute(writer, "style", "color:transparent !important" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "sipbutton" );
			

      
         if (!isNestedWithinForm(context, component)) {
                                                           
   
writer.startElement("span", component);

writer.writeText(convertToString(convertToString(getValue(component)) + ": This link is disabled as it is not nested within a JSF form."),null);

writer.endElement("span");

      
         } else if (isDisabled(component)) {
      
   
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "disabled" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "container" );
			//
// pass thru attributes
//
getUtils().encodeAttributesFromArray(context,component,new String[] {
    "align" ,
	    "dir" ,
	    "lang" ,
	    "ondblclick" ,
	    "onkeydown" ,
	    "onkeypress" ,
	    "onkeyup" ,
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

writer.startElement("table", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
			
writer.startElement("tr", component);

writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "left" );
			
writer.write("&#160;&#160;&#160;");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "middle" );
			
encodeIcon(context, component);
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "btnstub" );
			
writer.endElement("div");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "right" );
			
writer.write("&#160;&#160;&#160;");
writer.endElement("td");
writer.endElement("tr");
writer.endElement("table");
writer.endElement("div");

      
         } else {
      
   
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "sipButCommon" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "container" );
			//
// pass thru attributes
//
getUtils().encodeAttributesFromArray(context,component,new String[] {
    "align" ,
	    "dir" ,
	    "lang" ,
	    "ondblclick" ,
	    "onkeydown" ,
	    "onkeypress" ,
	    "onkeyup" ,
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

writer.startElement("table", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
			
writer.startElement("tr", component);

writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "left" );
			
writer.write("&#160;&#160;&#160;");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "middle" );
			
encodeIcon(context, component);
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "btnstub" );
			
writer.endElement("div");
writer.endElement("td");
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "right" );
			
writer.write("&#160;&#160;&#160;");
writer.endElement("td");
writer.endElement("tr");
writer.endElement("table");
writer.endElement("div");

      
         }
      
   
writer.endElement("div");
writer.endElement("a");
writer.endElement("td");
writer.endElement("tr");
writer.endElement("tbody");
writer.endElement("table");

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (javax.faces.component.UICommand)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
