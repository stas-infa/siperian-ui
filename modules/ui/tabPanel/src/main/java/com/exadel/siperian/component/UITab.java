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

package com.exadel.siperian.component;

import java.io.IOException;
import java.util.Set;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.ActionSource;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.render.Renderer;

import org.ajax4jsf.component.AjaxActionComponent;
import org.ajax4jsf.component.AjaxChildrenEncoder;
import org.ajax4jsf.component.AjaxComponent;
import org.ajax4jsf.event.AjaxSource;
import org.ajax4jsf.renderkit.AjaxChildrenRenderer;
import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UISwitchablePanel;
import org.richfaces.event.SwitchablePanelSwitchEvent;

/**
 * Base class for panel tab.
 *
 * @author asmirnov@exadel.com (latest modification by $Author: ishabalov $)
 * @version $Revision: 1.12 $ $Date: 2007/02/21 20:35:05 $
 */
public abstract class UITab extends AjaxActionComponent implements AjaxComponent, AjaxSource, ActionSource, TabBase, AjaxChildrenEncoder, NamingContainer{
	
	private static final Log log = LogFactory.getLog(UITab.class);
	
	private Boolean longRunning;
	
    public static final String COMPONENT_TYPE = "com.exadel.siperian.Tab";
    
    @Override
    public void broadcast(FacesEvent event) throws AbortProcessingException {
       	if (event instanceof SwitchablePanelSwitchEvent) {
       		SwitchablePanelSwitchEvent switchEvent = (SwitchablePanelSwitchEvent)event;
       		UIComponent component = switchEvent.getEventSource();
       		FacesContext context = getFacesContext();
       		if (AjaxRendererUtils.isAjaxRequest(context)) {
				AjaxRendererUtils.addRegionByName(context, component, component
						.getId());
			}
       	}
       	super.broadcast(event);
    }
    
   
    /**
     * @param context
     * @param active
     * @throws IOException
     */
    public void encodeTab(FacesContext context) throws IOException {
        if (context == null) {
            throw new NullPointerException("No FacesContext");
        }
        
        if (!isRendered()) {
        	return;
        }

        if (getName() == null) {
        	log.info("Tab won't be rendered because getName() == null");
            return;
        }

        Renderer renderer = getRenderer(context);
        if (null != renderer && renderer instanceof TabEncoder) {
            ((TabEncoder) renderer).encodeTab(context, this);
        }
    }

    /**
     * Get enclosed {@link UITabPanel} tab panel for this component.
     *
     * @return {@link UITabPanel}
     */
    public UITabPanel getPane() throws FacesException {
        UIComponent component = this.getParent();
    	while (component != null && !(component instanceof UITabPanel)) {
    		component = component.getParent();
    	}

    	if (component == null) {
    		throw new FacesException("The component: " + this.getClientId(getFacesContext()) + " is not nested within " + UITabPanel.class.getSimpleName());
    	} else {
            return (UITabPanel) component;
    	}
    }


    public abstract Object getValue();

    public abstract void setValue(Object newvalue);

    public abstract Boolean getTabCloseable();

    public abstract void setTabCloseable(Boolean tabCloseable);
    
    public abstract Boolean getLongRunning();
    
    public abstract void setLongRunning(Boolean longRunning);

    public String getSwitchTypeOrDefault() {
        return UISwitchablePanel.AJAX_METHOD;
    }
    
    @Override
    public Object saveState(FacesContext context) {
    	Object state [] = new Object[2];
    	state[0] = super.saveState(context);
    	state[1] = longRunning;
       	return state;
    }
    
    @Override
    public void restoreState(FacesContext context, Object state) {
    	Object states [] = (Object []) state;
    	super.restoreState(context, states[0]);
    	this.longRunning = (Boolean)states[1];
    }

    public void encodeAjaxChild(FacesContext context, String path, Set<String> ids, Set<String> renderedAreas)
            throws IOException {
    	AjaxChildrenRenderer childrenRenderer = (AjaxChildrenRenderer)getRenderer(context);
        if(getPane().isTabLoaded(this)){
            childrenRenderer = (AjaxChildrenRenderer)getRenderer(context);
            childrenRenderer.encodeAjaxChildren(context, this, path, ids, renderedAreas);
        }
        
       	UIComponent facet = getFacet("label");
        	if (facet != null) {
        		path += getId() + ":";
        		childrenRenderer.encodeAjaxComponent(context, facet, path, ids, renderedAreas);
        	}
        
    }
    
    @Override
    public void queueEvent(FacesEvent event) {
    	if (event instanceof SwitchablePanelSwitchEvent) {
    		event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
    	}
    	super.queueEvent(event);
    }
}
