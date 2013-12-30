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

import org.ajax4jsf.javascript.ScriptUtils;
import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.ajax4jsf.renderkit.ComponentsVariableResolver;
import org.ajax4jsf.renderkit.ComponentVariables;
import org.ajax4jsf.resource.InternetResource;
import org.ajax4jsf.resource.InternetResource;
//
//
//


import org.richfaces.component.MenuComponent;
import org.richfaces.component.UIMenuItem;
import org.richfaces.component.util.ViewUtil;
import org.richfaces.renderkit.html.MenuItemRendererBase;



/**
 * Renderer for component class org.richfaces.renderkit.html.MenuItemRenderer
 */
public class SiperianMenuItemRenderer extends MenuItemRendererBase {

	public SiperianMenuItemRenderer () {
		super();
	}

	// 
	// Declarations
	//
	private final InternetResource[] styles = {
						getResource("css/menucomponents.xcss")
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
				getResource("/org/ajax4jsf/javascript/scripts/form.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/form.js")
						,
				getResource("/org/richfaces/renderkit/html/scripts/menu.js")
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
		return org.richfaces.component.UIMenuItem.class;
	}
	
	@Override
	public void initializeResources(FacesContext context, UIMenuItem menuItem)
			throws IOException {
		ComponentVariables variables =
                ComponentsVariableResolver.getVariables(this, menuItem);

        String resource = menuItem.isDisabled()
                ? ViewUtil.getResourceURL(menuItem.getIconDisabled())
                : ViewUtil.getResourceURL(menuItem.getIcon());
        if (resource == null || (resource.length() == 0)) {
            resource = getResource("images/spacer.gif").getUri(
                    context, menuItem);
        }
        variables.setVariable("icon", resource);
        
        // create attributes string for item without parent
        StringBuffer attr = new StringBuffer();
        String attrStr = "";
        if (!(menuItem.getParent() instanceof MenuComponent))
        {
        	String styleClass = (String) menuItem.getAttributes().get("styleClass");
        	String str = "";
        	attr.append(",{");
        	if (null!=styleClass && styleClass.length()>0) {
        		attr.append("styleClass:");
        	    attr.append(ScriptUtils.toScript(styleClass));
        	    str = ",";
        	}
        	String onselect = (String) menuItem.getAttributes().get("onselect");
        	if (null!=onselect && onselect.length()>0) {
        		attr.append(str);
        		attr.append("onselect:function(event){");
        		attr.append(onselect);
        		attr.append("}");
        	}
        	attr.append("}");
        	if (attr.length()>3) attrStr = attr.toString();
        }
        
        if (menuItem.isDisabled()) {
            variables.setVariable("iconDisabledClasses",
                    "dr-menu-icon-disabled rich-menu-item-icon-disabled");
        } else {

            variables.setVariable("onmouseoutInlineStyles",
                    collectInlineStyles(context, menuItem, false));
            variables.setVariable("onmouseoverInlineStyles",
            		collectInlineStyles(context, menuItem, true));

            //-----------------------------------
            StringBuffer scriptValue = new StringBuffer();
            String mode = resolveSubmitMode(menuItem);
            
            if (MenuComponent.MODE_AJAX.equalsIgnoreCase(mode)) {
            	scriptValue.append("RichFaces.SipMenu.updateItem(event,this");
            	scriptValue.append(attrStr);
            	scriptValue.append(");");
                scriptValue.append(AjaxRendererUtils.buildOnClick(
                        menuItem, context).toString());
            } else if (MenuComponent.MODE_SERVER.equalsIgnoreCase(mode)) {
            	scriptValue.append("RichFaces.SipMenu.submitForm(event,this");
            	String params = encodeParamsAsObject(context, menuItem);
            	if (null!=params) {
            		scriptValue.append(",");
            		scriptValue.append(params);
            	}
    	        String target = (String) menuItem.getAttributes().get("target");
    	        if (null != target && target.length()>0) {
    	        	scriptValue.append(",");
    	 			scriptValue.append(ScriptUtils.toScript(target));
    	        }
    	        
            	scriptValue.append(attrStr);
            	scriptValue.append(")");
            	
            } else {
            	scriptValue.append("RichFaces.SipMenu.updateItem(event,this");
            	scriptValue.append(attrStr);
            	scriptValue.append(");");
                scriptValue.append(getStringAttributeOrEmptyString(menuItem, "onclick"));
            }
            if (resource.length() > 0) {
                variables.setVariable("onclick", scriptValue.toString());
            }
            //-------------------------------
          }

	}


	public void doEncodeEnd(ResponseWriter writer, FacesContext context, org.richfaces.component.UIMenuItem component, ComponentVariables variables) throws IOException {
	  
		
		         String mode = resolveSubmitMode(component);
		         if (mode != null && !mode.equalsIgnoreCase("none")) org.richfaces.component.util.FormUtil.throwEnclFormReqExceptionIfNeed(context,component);
				                                               
    
initializeResources(context, component);

initializeStyles(context, component);

java.lang.String clientId = component.getClientId(context);

			if (((org.richfaces.component.UIMenuItem) component).isDisabled()) {
				
	
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", variables.getVariable("menuItemClass") );
						getUtils().writeAttribute(writer, "id", clientId );
						getUtils().writeAttribute(writer, "onclick", "Event.stop(event);" );
						getUtils().writeAttribute(writer, "style", variables.getVariable("menuItemStyle") );
			

		} else {
			/*String onselect = (String) component.getAttributes().get("onselect");
	    	if(null!=onselect&&onselect.length()>0){
	    		onselect = onselect+";";	    		  		
	    	}else{
	    		onselect="";
	    	}
	    	variables.setVariable("onselect",onselect);
	    	*/
	
writer.startElement("div", component);
			getUtils().writeAttribute(writer, "class", variables.getVariable("menuItemClass") );
						getUtils().writeAttribute(writer, "id", clientId );
						getUtils().writeAttribute(writer, "onclick", variables.getVariable("onclick") );
						getUtils().writeAttribute(writer, "onmousedown", "Event.stop(event); " + convertToString(component.getAttributes().get("onmousedown")) );
						getUtils().writeAttribute(writer, "onmouseout", "RichFaces.SipMenu.itemMouseOut(event, this, '" + convertToString(variables.getVariable("menuItemCustomClass")) + "', '" + convertToString(variables.getVariable("onmouseoutInlineStyles")) + "', '" + convertToString(component.getAttributes().get("iconClass")) + "');" );
						getUtils().writeAttribute(writer, "onmouseover", "RichFaces.SipMenu.itemMouseOver(event, this, '" + convertToString(variables.getVariable("menuItemHoverClass")) + "', '" + convertToString(variables.getVariable("onmouseoverInlineStyles")) + "', '" + convertToString(component.getAttributes().get("iconClass")) + "');" );
						getUtils().writeAttribute(writer, "onmouseup", "Event.stop(event); " + convertToString(component.getAttributes().get("onmouseup")) );
						getUtils().writeAttribute(writer, "style", variables.getVariable("menuItemStyle") );
			
getUtils().encodeAttributes(context, component, "onmousemove");


		}
	
writer.startElement("span", component);
			getUtils().writeAttribute(writer, "class", "dr-menu-icon rich-menu-item-icon " + convertToString(variables.getVariable("iconDisabledClasses")) + " " + convertToString(component.getAttributes().get("iconClass")) );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":icon" );
						getUtils().writeAttribute(writer, "style", component.getAttributes().get("iconStyle") );
			

				UIComponent iconFacet = getIconFacet((org.richfaces.component.UIMenuItem) component);			
				if (null != iconFacet) {
					renderChild(context, iconFacet);	
				} else {
			
writer.startElement("img", component);
			getUtils().writeAttribute(writer, "alt", "" );
						getUtils().writeAttribute(writer, "height", "16" );
						getUtils().writeAttribute(writer, "src", variables.getVariable("icon") );
						getUtils().writeAttribute(writer, "width", "16" );
			
writer.endElement("img");

				}
			
writer.endElement("span");
writer.startElement("span", component);
			getUtils().writeAttribute(writer, "class", variables.getVariable("menuItemLabelClass") );
						getUtils().writeAttribute(writer, "id", convertToString(clientId) + ":anchor" );
			
writer.writeText(convertToString(component.getAttributes().get("value")),null);

renderChildren(context, component);

writer.endElement("span");

		if (((org.richfaces.component.UIMenuItem) component).isDisabled()) {
	
writer.endElement("div");

		} else {
	
writer.endElement("div");

		}
	

	}		
	
	public void doEncodeEnd(ResponseWriter writer, FacesContext context, UIComponent component) throws IOException {
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		doEncodeEnd(writer, context, (org.richfaces.component.UIMenuItem)component, variables );

		ComponentsVariableResolver.removeVariables(this, component);
	}		
	

}
