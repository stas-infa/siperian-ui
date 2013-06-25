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


import com.exadel.siperian.renderkit.ModalPanelRendererBase;



/**
 * Renderer for component class com.exadel.siperian.renderkit.html.ModalPanelRenderer
 */
public class ModalPanelRenderer extends ModalPanelRendererBase {

	public ModalPanelRenderer () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] styles = {
						getResource("/com/exadel/siperian/renderkit/html/css/modalPanel.xcss")
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
				getResource("/org/richfaces/renderkit/html/scripts/utils.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/browser_info.js")
						,
				getResource("/com/exadel/siperian/renderkit/html/scripts/modalPanel.js")
						,
				getResource("/com/exadel/siperian/renderkit/html/scripts/modalPanelBorders.js")
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
		return com.exadel.siperian.component.UIModalPanel.class;
	}

	
	public void doEncodeBegin(ResponseWriter writer, FacesContext context, UIComponent component ) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeBegin(writer, context, (com.exadel.siperian.component.UIModalPanel)component, variables );
	}		

	public void doEncodeBegin(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIModalPanel component, ComponentVariables variables ) throws IOException {
	    java.lang.String clientId = component.getClientId(context);
variables.setVariable("background", getResource( "/com/exadel/siperian/renderkit/html/images/spn_modpanel_bg.png" ).getUri(context, component) );

variables.setVariable("close", getResource( "/com/exadel/siperian/renderkit/html/images/spn_close_btn.png" ).getUri(context, component) );

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "id", clientId );
			
checkOptions(context, component);

initializeResources(context, component);

writer.startElement("input", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + "OpenedState" );
						getUtils().writeAttribute(writer, "name", convertToString(clientId) + "OpenedState" );
						getUtils().writeAttribute(writer, "type", "hidden" );
			
writer.endElement("input");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn-modalpanel " + convertToString(component.getAttributes().get("styleClass")) );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "Container" );
						getUtils().writeAttribute(writer, "style", "position: absolute; display: none; z-index: " + convertToString(component.getZindex()) + "; background-color: inherit;" );
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

writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn-mpnl-mask-div spn-mpnl-mask-div-opaque spn-mpnl-mask-div" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "Div" );
						getUtils().writeAttribute(writer, "onclick", component.getAttributes().get("onmaskclick") );
						getUtils().writeAttribute(writer, "oncontextmenu", component.getAttributes().get("onmaskcontextmenu") );
						getUtils().writeAttribute(writer, "ondblclick", component.getAttributes().get("onmaskdblclick") );
						getUtils().writeAttribute(writer, "onmousedown", component.getAttributes().get("onmaskmousedown") );
						getUtils().writeAttribute(writer, "onmousemove", component.getAttributes().get("onmaskmousemove") );
						getUtils().writeAttribute(writer, "onmouseout", component.getAttributes().get("onmaskmouseout") );
						getUtils().writeAttribute(writer, "onmouseover", component.getAttributes().get("onmaskmouseover") );
						getUtils().writeAttribute(writer, "onmouseup", component.getAttributes().get("onmaskmouseup") );
						getUtils().writeAttribute(writer, "style", "z-index: 1;" );
			
writer.startElement("button", component);
			getUtils().writeAttribute(writer, "class", "spn-mpnl-pnl-button" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "FirstHref" );
			
writer.endElement("button");
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn-mpnl-panel spn-mpnl_panel" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "ContainerDiv" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + "CDiv" );
						getUtils().writeAttribute(writer, "style", "position: absolute; left: 0px; top: 0px; z-index: 9;" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_modpanel_bgtl" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "BgTL" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "alt", "" );
						getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "850" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "BgImgTL" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("background") );
						getUtils().writeAttribute(writer, "style", "position : absolute; top: 0px; left: 0px;" );
						getUtils().writeAttribute(writer, "width", "1100" );
			
writer.endElement("img");
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_modpanel_bgtr" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "BgTR" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "alt", "" );
						getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "850" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "BgImgTR" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("background") );
						getUtils().writeAttribute(writer, "style", "position : absolute; top: 0px; right: 0px;" );
						getUtils().writeAttribute(writer, "width", "1100" );
			
writer.endElement("img");
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_modpanel_bgbl" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "BgBL" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "alt", "" );
						getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "850" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "BgImgBL" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("background") );
						getUtils().writeAttribute(writer, "style", "position : absolute; bottom: 0px; left: 0px;" );
						getUtils().writeAttribute(writer, "width", "1100" );
			
writer.endElement("img");
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn_modpanel_bgbr" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "BgBR" );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "alt", "" );
						getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "height", "850" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "BgImgBR" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("background") );
						getUtils().writeAttribute(writer, "style", "position : absolute; bottom: 0px; right: 0px;" );
						getUtils().writeAttribute(writer, "width", "1100" );
			
writer.endElement("img");
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn-mpnl-shadow spn-mpnl-shadow" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "ShadowDiv" );
						getUtils().writeAttribute(writer, "style", "display: none" );
			
writer.endElement("div");
java.lang.String divClass  = "" ;
java.lang.String tableStyle  = "" ;

					
						if (component.isAutosized()) {
							int minWidth = component.getMinWidth();
							int minHeight = component.getMinHeight();

							int width = component.getWidth();
							int height = component.getHeight();

							if (width < 0 || width < minWidth) {
								width = minWidth;
							}

							if (height < 0 || height < minHeight) {
								height = minHeight;
							}

							tableStyle += "width: " + (width > 0 ? width : 1) + "px;";
							tableStyle += "height: " + (height > 0 ? height : 1) + "px;";

							divClass = "";
							
						} else {
							tableStyle = "height: 100%; width: 100%;";
							/*
							overflow: hidden;
							*/
							divClass = "spn-mpnl-ovf-hd";
							if (component.isTrimOverlayedElements()) {
								/*
								position: relative; 
								z-index: 0;
								*/
								divClass += " spn-mpnl-trim";
							}
						}
					
				
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", convertToString(divClass) + " spn-mpnl-pnl spn-mp-content" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "ContentDiv" );
						getUtils().writeAttribute(writer, "style", component.getAttributes().get("style") );
			

						if(component.getFacet("controls")!=null && component.getFacet("controls").isRendered()) {
					
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn-mpnl-pnl-text spn-mpnl-text spn-mpnl-controls " + convertToString(component.getAttributes().get("controlsClass")) );
			
UIComponent indexChildren_3 = component.getFacet("controls");
if (null != indexChildren_3 && indexChildren_3 .isRendered()) {
	renderChild(context, indexChildren_3);
}

writer.endElement("div");

						}else if(component.isShowCloseButton()) {
					
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn-mpnl-pnl-text spn-mpnl-text spn-mpnl-controls " + convertToString(component.getAttributes().get("controlsClass")) );
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "alt", "" );
						getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "class", "spn-mpnl-controls-close-button" );
						getUtils().writeAttribute(writer, "height", "13" );
						getUtils().writeAttribute(writer, "onclick", "Siperian.hideModalPanel('" + convertToString(clientId) + "');" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("close") );
						getUtils().writeAttribute(writer, "width", "13" );
			
writer.endElement("img");
writer.endElement("div");

						}
					
writer.startElement("table", component);
			getUtils().writeAttribute(writer, "border", "0" );
						getUtils().writeAttribute(writer, "cellpadding", "0" );
						getUtils().writeAttribute(writer, "cellspacing", "0" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "ContentTable" );
						getUtils().writeAttribute(writer, "style", tableStyle );
			

							if(component.getFacet("header")!=null && component.getFacet("header").isRendered()) {
						
writer.startElement("tr", component);
			getUtils().writeAttribute(writer, "style", "height: 1%;" );
			
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn-mpnl-header spn-mpnl-header-cell" );
			
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn-mpnl-pnl-text spn-mpnl-pnl-h spn-mpnl-text spn-mpnl-header " + convertToString(component.getAttributes().get("headerClass")) );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "Header" );
						getUtils().writeAttribute(writer, "style", "white-space: nowrap;" );
			
UIComponent indexChildren_4 = component.getFacet("header");
if (null != indexChildren_4 && indexChildren_4 .isRendered()) {
	renderChild(context, indexChildren_4);
}

writer.endElement("div");
writer.endElement("td");
writer.endElement("tr");

							}
						
writer.startElement("tr", component);
			getUtils().writeAttribute(writer, "style", "height: 99%" );
			
writer.startElement("td", component);
			getUtils().writeAttribute(writer, "class", "spn-mpnl-pnl-b spn-mpnl-body" );
						getUtils().writeAttribute(writer, "valign", "top" );
			

	}		
	
    public void doEncodeChildren(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeChildren(writer, context, (com.exadel.siperian.component.UIModalPanel)component, variables );
	}		

    public void doEncodeChildren(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIModalPanel component, ComponentVariables variables) throws IOException {
	    
renderChildren(context, component);


	}		

	/* (non-Javadoc)
	 * @see javax.faces.render.Renderer#getRendersChildren()
	 */
	public boolean getRendersChildren() {
		return true;
	}

	public void doEncodeEnd(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIModalPanel component, ComponentVariables variables) throws IOException {
	  
java.lang.String clientId = component.getClientId(context);
writer.endElement("td");
writer.endElement("tr");
writer.endElement("table");
writer.endElement("div");

					
						if (component.isResizeable()) {
							for (int i = 0; i < RESIZERS.length; i++) {
								variables.setVariable("resizer", RESIZERS[i]);

								boolean isHor = i % 2 == 0;
								String style = isHor ? "width: 40px; height: 4px;" : "height: 40px; width: 4px;";

								//higher z-index for corner elements
								//style += "z-index: " + (i % 3 == 1 ? 0 : 1) + 3 + ";";
								variables.setVariable("resizerStyle", style);
					
				
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn-mpnl-resizer spn-mpnl-resizer" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "Resizer" + convertToString(variables.getVariable("resizer")) );
						getUtils().writeAttribute(writer, "style", variables.getVariable("resizerStyle") );
			
writer.endElement("div");

					
							}
						}
					
				
writer.endElement("div");
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "spn-mpnl-mask-div spn-mpnl-mask-div-transparent spn-mpnl-mask-div" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "CursorDiv" );
						getUtils().writeAttribute(writer, "style", "z-index: -200;" );
			
writer.startElement("button", component);
			getUtils().writeAttribute(writer, "class", "spn-mpnl-pnl-button" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + "LastHref" );
			
writer.endElement("button");
writer.endElement("div");
writer.startElement("script", component);
			getUtils().writeAttribute(writer, "type", "text/javascript" );
			
writer.writeText(convertToString("new SipModalPanel('" + convertToString(clientId) + "',\n				{\n					width: " + convertToString(component.getWidth()) + ",\n					height: " + convertToString(component.getHeight()) + ",\n\n					minWidth: " + convertToString(component.getMinWidth()) + ",\n					minHeight: " + convertToString(component.getMinHeight()) + ",\n					attachmentFormId:\"" + convertToString(component.getAttributes().get("attachmentFormId")) + "\",\n					resizeable: " + convertToString(component.isResizeable()) + ",\n					moveable: " + convertToString(component.isMoveable()) + ",\n\n					left: \"" + convertToString(component.getLeft()) + "\",\n					top: \"" + convertToString(component.getTop()) + "\",\n\n					zindex: " + convertToString(component.getZindex()) + ",\n					\n					isModal: " + convertToString(component.isIsModal()) + ","),null);

writePanelDOMAttachmentParameter(context, component);

writer.writeText(convertToString(","),null);

writeEventHandlerFunction(context, component, "onresize");

writer.writeText(convertToString(","),null);

writeEventHandlerFunction(context, component, "onmove");

writer.writeText(convertToString(","),null);

writeEventHandlerFunction(context, component, "onshow");

writer.writeText(convertToString(","),null);

writeEventHandlerFunction(context, component, "onhide");

writer.writeText(convertToString(","),null);

writeEventHandlerFunction(context, component, "onbeforeshow");

writer.writeText(convertToString(","),null);

writeEventHandlerFunction(context, component, "onbeforehide");

writer.writeText(convertToString(",\n									\n					keepVisualState: " + convertToString(component.isKeepVisualState()) + ",\n					showWhenRendered: " + convertToString(component.isShowWhenRendered()) + ",\n					selectBehavior: \"" + convertToString(component.getTridentIVEngineSelectBehavior()) + "\",\n\n					autosized: " + convertToString(component.isAutosized())),null);

writeVisualOptions(context, component);

writer.writeText(convertToString("});"),null);

writer.endElement("script");
writer.endElement("div");
writer.startElement("script", component);
			getUtils().writeAttribute(writer, "type", "text/javascript" );
			
writer.writeText(convertToString(getShowScript(context,component)),null);

writer.endElement("script");
writer.endElement("div");

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (com.exadel.siperian.component.UIModalPanel)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
