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


import com.exadel.siperian.renderkit.html.VerticalScrollerRenderer;



/**
 * Renderer for component class com.exadel.siperian.renderkit.html.VerticalScrollerTemplate
 */
public class VerticalScrollerTemplate extends VerticalScrollerRenderer {

	public VerticalScrollerTemplate () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] styles = {
						getResource("css/verticalScroller.xcss")
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
				getResource("/org/richfaces/renderkit/html/scripts/utils.js")
						,
				getResource("scripts/vScroller.js")
						,
				getResource("scripts/verticalScroller.js")
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
		return com.exadel.siperian.component.UIVerticalScroller.class;
	}


	public void doEncodeEnd(ResponseWriter writer, FacesContext context, com.exadel.siperian.component.UIVerticalScroller component, ComponentVariables variables) throws IOException {
	  java.lang.String clientId = component.getClientId(context);
variables.setVariable("clear", getResource( "/com/exadel/siperian/renderkit/html/images/clear.gif" ).getUri(context, component) );


		org.richfaces.component.util.FormUtil.throwEnclFormReqExceptionIfNeed(context,component);		                                               
    
java.lang.String singlePageRenderStyle  = "" ;

		
        int pageCount = component.getPageCount();
        int pageIndex = component.getPage();

        com.exadel.siperian.renderkit.html.ControlsState controlsState = getControlsState(context, component, pageIndex, pageCount);
        boolean singlePageRender = true;
		
		if (pageCount == 1 && !component.isRenderIfSinglePage()) {
        	singlePageRenderStyle = "; display: none";
        	singlePageRender = false;
        } else if (!controlsState.isFirstRendered() && !controlsState.isFastRewindRendered() &&
        	!controlsState.isPreviousRendered() && !controlsState.isNextRendered() && 
        	!controlsState.isFastForwardRendered() && !controlsState.isLastRendered() && 
        	pageCount <= 1 ) {
            singlePageRenderStyle = "; display: none";
    		singlePageRender = false;
        }
		variables.setVariable("pageIndex", pageIndex);
				                                               
    
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "mainContainer " + convertToString(component.getAttributes().get("styleClass")) );
						getUtils().writeAttribute(writer, "id", clientId );
						getUtils().writeAttribute(writer, "style", convertToString(component.getAttributes().get("style")) + " " + convertToString(singlePageRenderStyle) );
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


			
			if (singlePageRender) {
					                                               
	    
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", "verticalTabsNoScroll" );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":inner" );
			

					
					String height = "300";
					
					if (component.getHeight() != null) {
						height = component.getHeight();
					}
					
			        ResponseWriter out = context.getResponseWriter();
		            out.startElement("div", component);
					out.writeAttribute("id", clientId + ":tabs", null);
					out.writeAttribute("class", "scrollTabsContainer", null);
					out.writeAttribute("style", "height: " + height + "px;", null);
							                                               
				

	                String facet;                         
	                
	                if (controlsState.isFirstRendered()){                       
	                if (controlsState.isFirstEnabled()){
	                  variables.setVariable("buttonClass", "");                  
	                  variables.setVariable("onclick", getOnClick(component.FIRST_FACET_NAME));                        
	                  variables.setVariable("facet", component.FIRST_FACET_NAME);                  
	                  facet=component.FIRST_FACET_NAME;
	                }else{
	                  variables.setVariable("buttonClass", "dr-dscr-button-dsbld rich-datascr-button-dsbld");  
	                  variables.setVariable("onclick", "");                                            
	                  variables.setVariable("facet", component.FIRST_DISABLED_FACET_NAME);                                    
	                  facet=component.FIRST_DISABLED_FACET_NAME;
	                };                
	               
	               
	                }
	                

						renderPages(context, component, pageIndex, pageCount);
	   			        UIComponent pagesFacet = component.getFacet("pages");
	   			        if (pagesFacet !=null && pagesFacet.isRendered()) {
							renderChild(context, pagesFacet);
	                

	   			        } else {
	   			        	renderPager(context, component, pageIndex, pageCount);
	   			        }
	                

					               
					out.endElement("div");
							                                               
				
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":down" );
			
writer.endElement("div");
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":up" );
			
writer.endElement("div");
writer.endElement("div");

			
			}
					                                               
	    
writer.startElement("script", component);
			getUtils().writeAttribute(writer, "type", "text/javascript" );
			
writer.writeText(convertToString("new VerticalScroller('" + convertToString(clientId) + "', " + convertToString(getSubmitFunction(context,component)) + ", " + convertToString(variables.getVariable("pageIndex")) + ", '" + convertToString(variables.getVariable("clear")) + "');\n		Event.observe('" + convertToString(clientId) + "', 'rich:datascroller:onscroll', $('" + convertToString(clientId) + "').component.onscroll.bind($('" + convertToString(clientId) + "').component));\n		document.observe(\"dom:loaded\", $('" + convertToString(clientId) + "').component.initScrolling.bind($('" + convertToString(clientId) + "').component));"),null);

writer.endElement("script");
writer.startElement("br", component);
			getUtils().writeAttribute(writer, "clear", "all" );
			
writer.endElement("br");
writer.endElement("div");

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (com.exadel.siperian.component.UIVerticalScroller)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
