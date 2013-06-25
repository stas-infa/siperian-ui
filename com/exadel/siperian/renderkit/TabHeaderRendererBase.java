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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.javascript.JSReference;
import org.ajax4jsf.javascript.ScriptUtils;
import org.ajax4jsf.renderkit.AjaxRendererUtils;

import com.exadel.siperian.component.TabBase;
import com.exadel.siperian.component.UITab;
import com.exadel.siperian.component.UITabPanel;


/**
 * @author Nick Belaevski - nbelaevski@exadel.com
 *         created 12.01.2007
 *  Modified by Andrey Markavtsov
 */
public class TabHeaderRendererBase extends org.ajax4jsf.renderkit.HeaderResourcesRendererBase {


    protected Class<? extends UIComponent> getComponentClass() {
        return UIComponent.class;
    }
    
    private static final TabInfoCollector collector = new TabInfoCollector() {

    	        private final JSReference JSR_ID = new JSReference("0");
    	        private final JSReference JSR_NAME = new JSReference("1");
    	        private final JSReference JSR_LABEL = new JSReference("2");
    	        private final JSReference JSR_LONG_RUNNING = new JSReference("3");
    	        private final JSReference JSR_ONTABLEAVE = new JSReference("4");
    	        private final JSReference JSR_ONTABENTER = new JSReference("5");
    	        
    	        public Object collectTabInfo(FacesContext context, UIComponent tab) {
    	            Map<Object, Object> info = new HashMap<Object, Object>();
    	            TabBase base = (TabBase)tab;
    	            info.put(JSR_ID, tab.getClientId(context));
    	            info.put(JSR_NAME, base.getName());
    	            info.put(JSR_LABEL, base.getLabel());
    	            info.put(JSR_LONG_RUNNING, ((UITab)tab).getLongRunning());
    	            info.put(JSR_ONTABLEAVE, tab.getAttributes().get("ontableave"));
    	            info.put(JSR_ONTABENTER, tab.getAttributes().get("ontabenter"));
    	            return info;
    	        }
    };
    
    private boolean isNewTab(TabBase base, UITabPanel panel) {
    	return panel.getNewTabNames().contains(base.getName());
    }
    
   
	public String getSwitchScript(FacesContext context, UIComponent tab) throws IOException {
        boolean disabled = (Boolean) tab.getAttributes().get("disabled");
        
        StringBuffer script = new StringBuffer();
        if (!disabled) {
        	
        	Map<String, Object> parameters = new HashMap<String, Object>();
        	
        	TabBase tabBase = (TabBase)tab;
			String panelId = tabBase.getPane().getClientId(context);
			if (tabBase.getName() != null) {
				parameters.put(panelId + TabPanelRendererBase.TAB_NAME_PARAMETER, tabBase.getName());
			}
			

        	script.append("return $('").append(tabBase.getPane().getClientId(context))
        		.append("').component.onTabClick(event, this,")
        		.append(ScriptUtils.toScript(AjaxRendererUtils.buildEventOptions(context, tab, parameters)))
        		.append(");");

			return script.toString();

		}
        return null;
    }
    
    public TabPanelClassBuilder getBuilder(UIComponent tab) {
    	UITabPanel panel = ((TabBase)tab).getPane();
    	TabPanelClassBuilder  builder = (TabPanelClassBuilder)panel.getAttributes().get(TabPanelRendererBase.CLASS_BUILDER_ATTRIBUTE);;
    	if (builder != null) {
    		return builder;
    	}else {
    		return new TabPanelClassBuilder(panel);
    	}
    }
    
    @SuppressWarnings("unchecked")
	public void collectTabInfo(FacesContext context, UIComponent component) {
    	TabBase base = (TabBase)component;
    	UITabPanel tabPanel = base.getPane();
    	
    	if (!isNewTab(base, tabPanel)) { 
	   		Map<String, Object> map = (Map<String, Object>)tabPanel.getAttributes().get(TabPanelRendererBase.TABS_COLLECTOR_ATTR_NAME);
	   		if (map == null) {
	   			map = new HashMap<String, Object>();
	   			tabPanel.getAttributes().put(TabPanelRendererBase.TABS_COLLECTOR_ATTR_NAME, map);
	   		}
	   		map.put(component.getClientId(context) + ":header", collector.collectTabInfo(context, component));
    	}else {
    		initOnComplete(context, tabPanel, component, collector.collectTabInfo(context, component));
    	}

    }
    
    @SuppressWarnings("unchecked")
    private void initOnComplete(FacesContext context, UITabPanel panel, UIComponent tab, Object info) {
    	AjaxContext ajaxContext = AjaxContext.getCurrentInstance(context);
    	List<String> newTabsIds = (List<String>)panel.getAttributes().get(TabPanelRendererBase.NEW_TABS_IDS_ATTRIBUTE);
    	List<Object> newTabsInfos = (List<Object>)panel.getAttributes().get(TabPanelRendererBase.NEW_TABS_INFO_ATTRIBUTE);
    	if (ajaxContext != null && newTabsIds != null && newTabsInfos != null) {
    		newTabsIds.add(tab.getClientId(context));
    		newTabsInfos.add(info);
 		
    		StringBuffer b = new StringBuffer("$('");
    		b.append(panel.getClientId(context));
    		b.append("').component.onNewTabLoaded(");
    		b.append(ScriptUtils.toScript(newTabsIds));
    		b.append(",");
    		b.append(ScriptUtils.toScript(newTabsInfos));
    		b.append(");");
    		ajaxContext.setOncomplete(b.toString());
		}
    }
       
    public String getCloseScript(FacesContext context, UIComponent component) {
    	TabBase tab = (TabBase)component;
    	String clientId = component.getClientId(context);
    	UITabPanel panel = tab.getPane();
    	
    	String panelId = panel.getClientId(context);
    	
    	StringBuffer b = new StringBuffer("return $('"+panelId+"').component.closeTab('");
    	
    	Map<String, Object> ajaxOptions = AjaxRendererUtils.buildEventOptions(context, component);
 	   	
    	b.append(clientId).append(":header', event, ");
    	b.append(ScriptUtils.toScript(ajaxOptions));
    	b.append(");");
    	return b.toString();
    }

    public boolean getRendersChildren() {
        return true;
    }

    public void encodeLabel(FacesContext context, UIComponent tab)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String label = (String) tab.getAttributes().get("label");

		UIComponent labelFacet = ((UIComponent) tab).getFacet("label");
		if (labelFacet != null && labelFacet.isRendered()) {
			renderChild(context, labelFacet);
		} else if (label == null || label.length() == 0) {
			label = "&#160;";
			writer.write(label);
		} else {
			writer.writeText(label, null);
		}

	}
        
  
    public boolean isTabRendered(FacesContext context, UIComponent component) {
		TabBase tab = (TabBase) component;
		UITabPanel panel = tab.getPane();
		return panel.isTabRendered(tab);
	}
    
   
    public boolean isFirstTab(UIComponent component) {
    	TabBase base = (TabBase)component;
    	UITabPanel tabPanel = base.getPane();
    	
    	@SuppressWarnings("unchecked")
		Map<String, Object> list = (Map<String, Object>)tabPanel.getAttributes().get(TabPanelRendererBase.TABS_COLLECTOR_ATTR_NAME);
    	return (list == null || list.size() == 0) && !isNewTab(base, tabPanel);
    
    }
      
}
