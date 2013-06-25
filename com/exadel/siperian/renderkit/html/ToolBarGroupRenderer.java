/**
 * License Agreement.
 *
 *  JBoss RichFaces - Ajax4jsf Component Library
 *
 * Copyright (C) 2007  Exadel, Inc.
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


import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.renderkit.RendererUtils.HTML;
import org.richfaces.component.UIToolBarGroup;

public class ToolBarGroupRenderer extends ToolBarRendererBase {
	
	protected Class getComponentClass() {
		return UIToolBarGroup.class;
	}

	public boolean getRendersChildren() {
		return true;
	}
	
	public void encodeChildren(FacesContext facesContext, UIComponent component) throws IOException {
		UIToolBarGroup toolBarGroup = (UIToolBarGroup) component;
		ResponseWriter writer = facesContext.getResponseWriter();
		String styleClass = (String) toolBarGroup.getAttributes().get(HTML.STYLE_CLASS_ATTR);
		String contentClass = (String) getParentToolBar(component).getAttributes().get("contentClass");
		String style = (String) toolBarGroup.getAttributes().get(HTML.style_ATTRIBUTE);
		String contentStyle = (String) getParentToolBar(component).getAttributes().get("contentStyle");
		Boolean isHighlighted = (Boolean) toolBarGroup.getAttributes().get("highlight");
		
		if (null == contentClass) {
			contentClass = "";
		}
		if (null == styleClass) {
			styleClass = "";
		}
		if (null == contentStyle) {
			contentStyle = "";
		}
		if (null == style) {
			style = "";
		}
		if (null == isHighlighted) {
			isHighlighted = false;
		}
		
		if (component.getChildCount() > 0) {
			List<UIComponent> children = component.getChildren();
			for (Iterator<UIComponent> iter = children.iterator(); iter.hasNext();) {
				UIComponent child = (UIComponent) iter.next();
				if(!child.isRendered()){
				
					//TODO nick - WHY you do this?
					iter.remove();
				}
			}

			writer.startElement(HTML.td_ELEM, component);
			
			//TODO nick - getUtils().writeAttribute
			writer.writeAttribute(HTML.valign_ATTRIBUTE, "middle", null);
            String groupDefaultStyleClass = (isHighlighted) ? "highlightedGroup " : "group ";             	
			writer.writeAttribute(HTML.class_ATTRIBUTE, groupDefaultStyleClass + contentClass + " " + styleClass, null);
            getUtils().writeAttribute(writer, HTML.style_ATTRIBUTE, contentStyle + ";" + style);
            encodeEventsAttributes(facesContext, toolBarGroup, writer);
			for (Iterator<UIComponent> it = children.iterator(); it.hasNext();) {
				UIComponent child = (UIComponent) it.next();
				
				//TODO nick - what is the reason of outputting spans for not rendered component?
				writer.startElement(HTML.SPAN_ELEM, component);
				writer.writeAttribute(HTML.class_ATTRIBUTE, "" + contentClass + " " + styleClass, null);
				getUtils().writeAttribute(writer, HTML.style_ATTRIBUTE, contentStyle + " " + style);
				encodeEventsAttributes(facesContext, toolBarGroup, writer);
				renderChild(facesContext, child);
				writer.endElement(HTML.SPAN_ELEM);
				if (it.hasNext()) {
					insertSeparatorIfNeed(facesContext, toolBarGroup, writer);
				}
			}
			writer.endElement(HTML.td_ELEM);
		}
	}
}
