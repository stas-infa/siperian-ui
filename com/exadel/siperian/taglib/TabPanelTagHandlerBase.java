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

package com.exadel.siperian.taglib;

import java.io.IOException;
import java.util.Iterator;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.ajax4jsf.webapp.taglib.AjaxComponentHandler;

import com.exadel.siperian.component.TabBase;
import com.exadel.siperian.component.UITab;
import com.exadel.siperian.component.UITabPanel;
import com.sun.facelets.FaceletContext;
import com.sun.facelets.tag.MetaRuleset;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagAttributes;
import com.sun.facelets.tag.jsf.ComponentConfig;

/**
 * 
 * <br /><br />
 * 
 * Created 23.08.2007
 * @author Nick Belaevski
 * @since 3.1
 */

//TODO nick - support the same functionality as in com.exadel.siperian.taglib.TabSetTagHandlerBase
public abstract class TabPanelTagHandlerBase extends AjaxComponentHandler {
	
	static final String CLEAR_CHILDREN_ATTRIBUTE = "clearChildrenOnRender";

	public TabPanelTagHandlerBase(ComponentConfig config) {
		super(config);
	}
	
	protected MetaRuleset createMetaRuleset(Class type) {
		TagAttributes attributes = this.tag.getAttributes();
		TagAttribute attribute = attributes.get("value");
		if (attribute != null && attributes.get("selectedTab") != null) {
			TagAttribute idAttribute = attributes.get("id");
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().log("selectedTab attribute has been already set for component with id: " + 
					idAttribute != null ? idAttribute.getValue() : null + 
					"[" + attribute.getValue() + "]. value attribute is deprecated and thus has been dropped!");
		}
		return super.createMetaRuleset(type).alias("selectedTab", "value");
	}
	
	@Override
	protected void applyNextHandler(FaceletContext ctx, UIComponent c)
			throws IOException, FacesException, ELException {
		if (shouldClearChildren(ctx, c)) {
			//c.getChildren().clear();
		}
		super.applyNextHandler(ctx, c);
	}
	
	private boolean shouldClearChildren (FaceletContext ctx, UIComponent component) {
		UITabPanel panel = (UITabPanel)component;
		if (panel.getTabName2Delete() != null) {
			//return true;
			deleteTab(panel, panel.getTabName2Delete());
			//component.getChildren().clear();
		}
//		TagAttribute attribute = getAttribute(CLEAR_CHILDREN_ATTRIBUTE);
//		Boolean b = Boolean.FALSE;
//		if (attribute != null) {
//			b = attribute.getBoolean(ctx);
//		}
//		return b;
		return false;
	}
	
	private void deleteTab(UITabPanel panel, String name) {
		Iterator<UIComponent> children = panel.getChildren().iterator();
		while (children.hasNext()) {
			UIComponent c = children.next();
			if (c instanceof UITab) {
				UITab tab = (UITab)c;
				TabBase base = (TabBase)tab;
				if (name.equals(base.getName())) {
					panel.getChildren().remove(c);
				}
			}
		}
	}
}
