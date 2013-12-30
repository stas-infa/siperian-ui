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

package com.exadel.siperian.renderkit;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.renderkit.AjaxChildrenRenderer;
import org.ajax4jsf.renderkit.RendererBase;

import com.exadel.siperian.component.TabEncoder;
import com.exadel.siperian.component.TabBase;
import com.exadel.siperian.component.UITab;
import com.exadel.siperian.component.UITabPanel;


/**
 * @author Nick Belaevski - nbelaevski@exadel.com
 *         created 12.01.2007
 */
public class TabRendererBase extends AjaxChildrenRenderer implements TabEncoder { 
    private RendererBase tabHeaderRenderer;

    private synchronized RendererBase getHeaderRenderer() {
    	if (tabHeaderRenderer == null) {
            Package pkg = this.getClass().getPackage();
            try {
                tabHeaderRenderer = (RendererBase) Class.forName(pkg.getName() + ".TabHeaderRenderer").newInstance();
            } catch (InstantiationException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            }
    	}

    	return tabHeaderRenderer;
    }
    
    public TabRendererBase() {
        super();
    }

    /**
     * Encode this tab header in Panel switch pane.
     *
     * @param context
     * @param tab
     * @param active
     * @throws IOException
     */
    public void encodeTab(FacesContext context, UIComponent tab) throws IOException {
    	RendererBase rendererBase = getHeaderRenderer();
    	rendererBase.encodeBegin(context, tab);
    	rendererBase.encodeEnd(context, tab);
    }

    public String getTabDisplay(FacesContext context, TabBase tab) {
        if (!tab.getPane().isTabActive(tab)) {
            return "display: none;";
        }

        return "";
    }

    protected boolean shouldRenderTab(TabBase tab) {
        return tab.getPane().isTabActive(tab) || UITabPanel.CLIENT_METHOD.equals(tab.getSwitchType()) || ((UITab)tab).getLongRunning();
    }
    
    protected boolean shouldRenderTabWithHeader(TabBase tab) {
        return tab.getPane().getNewTabNames().contains(tab.getName());
    }

    public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException {
    	
    	TabBase tab = (TabBase) component;
    	
    	if (shouldRenderTabWithHeader(tab)) {
    		encodeTab(context, component);
    	}

        if (shouldRenderTab(tab)) {
            super.encodeBegin(context, component);
        }
    }

    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {

        TabBase tab = (TabBase) component;
        if (shouldRenderTab(tab)) {
            super.encodeEnd(context, component);
        }
                
    }

    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {

        TabBase tab = (TabBase) component;
        if (shouldRenderTab(tab)) {
            if ((component.getChildren() != null) && (component.getChildren().size() > 0)) {
                renderChildren(context, component);
            } else {
                ResponseWriter out = context.getResponseWriter();
                out.write("&#160;");
            }
        }
    }

    protected Class<? extends UIComponent> getComponentClass() {
        return UITab.class;
    }

    public boolean getRendersChildren() {
        return true;
    }
}
