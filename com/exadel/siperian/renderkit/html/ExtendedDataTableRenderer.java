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


import com.exadel.siperian.renderkit.AbstractExtendedTableRenderer;



/**
 * Renderer for component class com.exadel.siperian.renderkit.html.ExtendedDataTableRenderer
 */
public class ExtendedDataTableRenderer extends AbstractExtendedTableRenderer {

	public ExtendedDataTableRenderer () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] styles = {
						getResource("/com/exadel/siperian/renderkit/html/css/extendedDataTable.xcss")
						,
				getResource("/org/richfaces/renderkit/html/css/dragIndicator.xcss")
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
						new org.ajax4jsf.javascript.AjaxScript()
						,
				new org.ajax4jsf.javascript.PrototypeScript()
						,
				getResource("/org/richfaces/renderkit/html/scripts/utils.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/browser_info.js")
						,
				getResource("/org/ajax4jsf/javascript/scripts/form.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/form.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/jquery/jquery.js")
						,
				getResource("/org/richfaces/renderkit/html/script/controlUtils.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/common-scrollable-data-table.js")
						,
				getResource("/com/exadel/siperian/renderkit/html/scripts/sip-extended-data-table.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/drag-indicator.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/ext-dt-drag-indicator.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/ext-dt-simple-draggable.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/ext-dt-simple-dropzone.js")
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
		return com.exadel.siperian.component.UIExtendedDataTable.class;
	}

	
	public void doEncodeBegin(ResponseWriter writer, FacesContext context, UIComponent component ) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeBegin(writer, context, (com.exadel.siperian.component.UIExtendedDataTable)component, variables );
	}		

	public void doEncodeBegin(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIExtendedDataTable component, ComponentVariables variables ) throws IOException {
	    variables.setVariable("emptyGif", getResource( "images/s.gif" ).getUri(context, component) );

java.lang.String clientId = component.getClientId(context);

     	
     	    
			variables.setVariable("columnsCount", getColumnsCount(component)+1);
			Boolean scrollable = Boolean.valueOf((String)component.getAttributes().get("scrollable"));
			String mainDivHeight = scrollable ? (String)component.getAttributes().get("height") : "auto";
			variables.setVariable("mainDivHeight",mainDivHeight);
			String innnerDivPosition = "relative";
			variables.setVariable("innnerDivPosition",innnerDivPosition);
			String scrollerDivHeight = "auto";// scrollable ? "50px" : "auto";
			variables.setVariable("scrollerDivHeight",scrollerDivHeight);
			
			String onclickhandler = getOnClick(context,component);
			variables.setVariable("onclickhandler", onclickhandler);
			
			String forceCount = getForceCount(context,component);
			variables.setVariable("forceCount",forceCount);
		
    
writer.writeComment(convertToString(" \n		TODO nick - enclose all HTML elements into one container element or add them \n		to rendered areas manually in order to be handled correctly by AJAX updates\n	"));

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "extdt-maindiv rich-extdt-maindiv" );
						getUtils().writeAttribute(writer, "id", clientId );
						getUtils().writeAttribute(writer, "style", convertToString(component.getAttributes().get("style")) + ";width:" + convertToString(component.getAttributes().get("width")) + ";height:" + convertToString(variables.getVariable("mainDivHeight")) );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "style", "height:0px" );
			
writer.startElement("a", component);
			getUtils().writeAttribute(writer, "href", "#" );
						getUtils().writeAttribute(writer, "onfocus", "if(this.blur)this.blur(); document.getElementById('" + convertToString(clientId) + ":n:0').click();" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "1" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("emptyGif") );
						getUtils().writeAttribute(writer, "width", "1" );
			
writer.endElement("img");
writer.endElement("a");
writer.endElement("div");
writer.startElement("input", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + "scrollPos" );
						getUtils().writeAttribute(writer, "name", convertToString(clientId) + "scrollPos" );
						getUtils().writeAttribute(writer, "type", "hidden" );
			
writer.endElement("input");
writer.startElement("button", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + "forseSorting" );
						getUtils().writeAttribute(writer, "name", convertToString(clientId) + "forseSorting" );
						getUtils().writeAttribute(writer, "onclick", variables.getVariable("onclickhandler") );
						getUtils().writeAttribute(writer, "style", "display:none" );
						getUtils().writeAttribute(writer, "value", variables.getVariable("forceCount") );
			
writer.endElement("button");

		     
			if (Boolean.valueOf(component.getAttributes().get("allowCellSelection").toString())) {
				                                               
	
writer.startElement("input", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + "cellState" );
						getUtils().writeAttribute(writer, "name", convertToString(clientId) + "cellState" );
						getUtils().writeAttribute(writer, "type", "hidden" );
						getUtils().writeAttribute(writer, "value", component.getAttributes().get("cellPosition") );
			
writer.endElement("input");

		
			} 
		
	
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "extdt-outerdiv" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":od" );
						getUtils().writeAttribute(writer, "style", "width:100%; height:100%;overflow-x:hidden;overflow-y:hidden; position: relative;" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "drgind_fly drgind_default drag_indicator" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":dataTable_indicator" );
						getUtils().writeAttribute(writer, "style", "display: none;" );
			
encodeNamespace(context, component);

writer.startElement("script", component);
			getUtils().writeAttribute(writer, "type", "text/javascript" );
			
writer.writeText(convertToString("//"),null);

     writer.write("<![CDATA[");
     writer.write(convertToString("\n	            " + convertToString(encodeDragDropChildScripts(context,component)) + ";\n	            //"));
     writer.write("]]>");

writer.endElement("script");
writer.endElement("div");
writer.startElement("span", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":dataTable_indicator_span" );
			
writer.endElement("span");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "extdt-innerdiv" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":innerd" );
						getUtils().writeAttribute(writer, "style", "height:100%;width:100%;position:" + convertToString(variables.getVariable("innnerDivPosition")) + ";" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "extdt-hsplit" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":cs" );
						getUtils().writeAttribute(writer, "style", "display: none;" );
			
writer.endElement("div");
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
						getUtils().writeAttribute(writer, "class", "extdt-table-layout rich-table " + convertToString(component.getAttributes().get("styleClass")) );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":tu" );
						getUtils().writeAttribute(writer, "width", "100%" );
			
encodeCaption(context, component);

writer.startElement("thead", component);

writer.startElement("tr", component);

writer.startElement("th", component);
			getUtils().writeAttribute(writer, "colspan", variables.getVariable("columnsCount") );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "style", "overflow: hidden; position: relative; width:100%; white-space: nowrap;" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "style", "width: 100%; position: relative; width: 100%; height: 100%; left: 0" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "style", "position: absolute; height: 100%; float: left; left: 0; z-index: 1; background: white; display: block;  text-align: left" );
			
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":fh" );
						getUtils().writeAttribute(writer, "style", "table-layout: fixed;" );
			
writer.startElement("colgroup", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":colgroup:header" );
			
encodeFrozenColumns(context, component);

writer.endElement("colgroup");
encodeFrozenHeader(context, component);

writer.endElement("table");
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "wrapper" );
						getUtils().writeAttribute(writer, "style", "position: relative; height: 100%; z-index: 0; background: white; display: block; text-align: left" );
			
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":h" );
						getUtils().writeAttribute(writer, "style", "width: 100%; table-layout: fixed;" );
			
writer.startElement("colgroup", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":colgroup:header" );
			
encodeColumns(context, component);

writer.endElement("colgroup");
encodeHeader(context, component);

writer.endElement("table");
writer.endElement("div");
writer.endElement("div");
writer.endElement("div");
writer.endElement("th");
writer.endElement("tr");
writer.endElement("thead");
writer.startElement("tbody", component);

writer.startElement("tr", component);

writer.startElement("td", component);
			getUtils().writeAttribute(writer, "colspan", variables.getVariable("columnsCount") );
						getUtils().writeAttribute(writer, "style", "padding: 0px;" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "extdt-content" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":sd" );
						getUtils().writeAttribute(writer, "style", "height:" + convertToString(variables.getVariable("scrollerDivHeight")) + ";width:100%; left: 0; position: relative; white-space: nowrap" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "style", "position: relative; height: 100%" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "wrapper" );
						getUtils().writeAttribute(writer, "style", "position: absolute; height: 100%; left: 0; width: 100%; float: left; z-index: 1; background: white;  display: block;  text-align: left" );
			
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "class", "extdt-table-layout rich-table " + convertToString(component.getAttributes().get("styleClass")) );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":fn" );
			
getUtils().encodePassThruWithExclusions(context, component, "height,value,name,type,id,class,rows,style,width");

writer.startElement("colgroup", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":colgroup:body" );
			
encodeFrozenColumns(context, component);

writer.endElement("colgroup");
writer.startElement("tbody", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":ftb" );
			

	}		
	
    public void doEncodeChildren(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeChildren(writer, context, (com.exadel.siperian.component.UIExtendedDataTable)component, variables );
	}		

    public void doEncodeChildren(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIExtendedDataTable component, ComponentVariables variables) throws IOException {
	    

     										
												encodeFrozenRows(context, component, variables);			
											
    									

	}		

	/* (non-Javadoc)
	 * @see javax.faces.render.Renderer#getRendersChildren()
	 */
	public boolean getRendersChildren() {
		return true;
	}

	public void doEncodeEnd(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIExtendedDataTable component, ComponentVariables variables) throws IOException {
	  
writer.endElement("tbody");
writer.startElement("tfoot", component);

encodeFrozenFooter(context, component);

writer.endElement("tfoot");
writer.endElement("table");
writer.endElement("div");
java.lang.String clientId2 = component.getClientId(context);
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "wrapper" );
						getUtils().writeAttribute(writer, "style", "position: relative; width: 100%; height: 100%; z-index: 0; background: white; display: block;  text-align: left" );
			
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "class", "extdt-table-layout rich-table " + convertToString(component.getAttributes().get("styleClass")) );
						getUtils().writeAttribute(writer, "id", convertToString(clientId2) + ":n" );
						getUtils().writeAttribute(writer, "style", "width: 100%" );
			
getUtils().encodePassThruWithExclusions(context, component, "height,value,name,type,id,class,rows,style,width");

writer.startElement("colgroup", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId2) + ":colgroup:body" );
			
encodeColumns(context, component);

writer.endElement("colgroup");
writer.startElement("tbody", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId2) + ":tb" );
			

     										
												encodeRows(context, component, variables);			
											
    									
writer.endElement("tbody");
writer.startElement("tfoot", component);

encodeFooter(context, component);

writer.endElement("tfoot");
writer.endElement("table");
writer.endElement("div");
writer.endElement("div");
writer.endElement("div");
writer.startElement("span", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId2) + ":scroll" );
						getUtils().writeAttribute(writer, "style", "overflow-x: scroll; overflow-y: hidden; display: none" );
			
writer.startElement("span", component);
			getUtils().writeAttribute(writer, "style", "display: block" );
			
writer.startElement("br", component);

writer.endElement("br");
writer.endElement("span");
writer.endElement("span");
writer.endElement("td");
writer.endElement("tr");
writer.endElement("tbody");
encodeTableFooter(context, component);

writer.endElement("table");
contributorsEncodeHere(context, component);

writer.endElement("div");
java.lang.String clientId = component.getClientId(context);
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "extdt-ss-vsbl" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":splashscreen" );
			
writer.endElement("div");
writer.startElement("script", component);
			getUtils().writeAttribute(writer, "type", "text/javascript" );
			
writer.writeText(convertToString("//"),null);

     writer.write("<![CDATA[");
     writer.write(convertToString("\n			delete " + convertToString(getJavaScriptVarName(context,component)) + ";\n			" + convertToString(getJavaScriptVarName(context,component)) + " = " + convertToString(createClientDataTable(context,component)) + ";\n			" + convertToString(getScriptContributions(context,component)) + ";\n		//"));
     writer.write("]]>");

writer.endElement("script");
writer.endElement("div");
encodeTableMenu(context, component);

writer.endElement("div");

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (com.exadel.siperian.component.UIExtendedDataTable)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
