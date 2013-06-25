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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;

import org.ajax4jsf.component.AjaxContainer;
import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.javascript.JSFunctionDefinition;
import org.ajax4jsf.javascript.JSReference;
import org.ajax4jsf.javascript.ScriptUtils;
import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.richfaces.event.SwitchablePanelSwitchEvent;

import com.exadel.siperian.component.TabBase;
import com.exadel.siperian.component.UITab;
import com.exadel.siperian.component.UITabPanel;


/**
 * @author Nick Belaevski - nbelaevski@exadel.com
 *         created 12.01.2007
 */
@SuppressWarnings("deprecation")
public class TabPanelRendererBase extends org.ajax4jsf.renderkit.HeaderResourcesRendererBase {

	public final static String TABS_COLLECTOR_ATTR_NAME = TabPanelRendererBase.class.getName() +  "tabs";
	
	public final static String CONTAINERID_VAR_NAME = "containerId";
	
	public static final String CLASS_BUILDER_ATTRIBUTE = "classBuilder";
	
	public static final String TAB_NAME_PARAMETER = ":tabName";
	
	public static final String CLOSE_TAB_PARAMETER = ":close";
	
	public static final String NEW_TABS_IDS_ATTRIBUTE = "newTabsIds";
	
	public static final String NEW_TABS_INFO_ATTRIBUTE = "newTabsInfos";
	
	public static final String NEW_TAB_ID_PARAMETER = ":newTabId";
	
	private static final String NEW_TAB_ID_REFERENCE = "newTabId";
	
	//private static final String ADD_NEW_TABS_PARAMETER = "_addNewTabs";
	
	
    protected Class<UITabPanel> getComponentClass() {
        return UITabPanel.class;
    }

    protected void doDecode(FacesContext context, UIComponent component) {
        super.doDecode(context, component);

        UITabPanel panel = (UITabPanel) component;

        String clientId = component.getClientId(context);
        Map<String, String> requestParameterMap = context.getExternalContext().getRequestParameterMap();
        
        UIComponent eventTab = null;
        
       
        if (requestParameterMap.get(clientId + CLOSE_TAB_PARAMETER) != null) {
        	MethodBinding binding = panel.getTabCloseListener();
        	if (binding != null) {
        		String tabName = requestParameterMap.get(clientId + CLOSE_TAB_PARAMETER);
        		panel.setTabName2Delete(tabName);
        	}
        }
        
        

        for (Iterator<?> tabsIterator = panel.getRenderedTabs();
             tabsIterator.hasNext() && eventTab == null;) {

            UIComponent tab = (UIComponent) tabsIterator.next();
            if (((TabBase)tab).isDisabled()) {
                continue;
            }
            String tabClientId = tab.getClientId(context);
            if ((null != requestParameterMap.get(tabClientId) && tabClientId.equals(requestParameterMap.get(tabClientId) ))) {

                eventTab = tab;
            }
        }

        if (eventTab != null) {
        	String newValue = requestParameterMap.get(clientId + TAB_NAME_PARAMETER);
            new SwitchablePanelSwitchEvent(eventTab, newValue, eventTab).queue();
            new ActionEvent(eventTab).queue();
            panel.setTabSwitched(true);

        } else {
            String newTabsId= (String) requestParameterMap.get(clientId + NEW_TAB_ID_PARAMETER);
                        
            if (null != newTabsId) {
            	/*
            	String [] tabsId = newTabsId.split(",");
    			initVariables(context, panel);
    			panel.getAttributes().put(NEW_TABS_IDS_ATTRIBUTE, new ArrayList<String>());
    			panel.getAttributes().put(NEW_TABS_INFO_ATTRIBUTE, new ArrayList<Object>());
            	for (String newTabId : tabsId) {
            	UITab newTab = findTabById(newTabId, panel);
            		if (newTab != null) {
            			panel.setSelectedTab(newTab.getName());
            			panel.getNewTabNames().add(newTab.getName());
            			new SwitchablePanelSwitchEvent(newTab, null, newTab).queue();
            			AjaxContext ajaxContext = AjaxContext.getCurrentInstance(context);
            			if (ajaxContext != null) {
            				ajaxContext.addRenderedArea(newTab.getClientId(context) + ":header");
            			}
            			panel.setTabSwitched(true);
            		}
            	}*/
            	panel.setNewTabs(true);
            	new ActionEvent(panel).queue();
            }
        }
    
    }
    
    private UITab findTabById(String newTabId, UITabPanel tabPanel) {
    	UIComponent c = tabPanel.findComponent(newTabId);
    	if (c != null && c instanceof UITab) {
    		return (UITab)c;
    	}
    	return null;
    }
  
     
    public void encodeTabs(FacesContext context, UITabPanel tabPanel) throws IOException {
     		for (Iterator<?> iter = tabPanel.getRenderedTabs(); iter.hasNext();) {
				Object child = iter.next();

				if (child instanceof UITab) {
					UITab tab = (UITab) child;
					tab.encodeTab(context);
				}
			}
    }
    
    public void initNewTabs(UITabPanel tabPanel, FacesContext context) {
    	if (tabPanel.isNewTabs()) {
			List<String> ids = (List<String>)tabPanel.getAttributes().get("newTabsList");
			List<String> clientTabIds = new ArrayList<String>();
			if (ids != null && ids.size() > 0 && tabPanel.getNewTabNames() != null) {
				initVariables(context, tabPanel);
				tabPanel.getAttributes().put(NEW_TABS_IDS_ATTRIBUTE, new ArrayList<String>());
				tabPanel.getAttributes().put(NEW_TABS_INFO_ATTRIBUTE, new ArrayList<Object>());
	        	for (String newTabId : ids) {
	        	UITab newTab = findTabById(newTabId, tabPanel);
        		if (newTab != null) {
	        			tabPanel.setSelectedTab(newTab.getName());
	        			tabPanel.getNewTabNames().add(newTab.getName());
	        			AjaxContext ajaxContext = AjaxContext.getCurrentInstance(context);
	        			ajaxContext.removeRenderedArea(tabPanel.getClientId(context));
	        			//new SwitchablePanelSwitchEvent(newTab, null, newTab).queue();
	        			//ajaxContext.addComponentToAjaxRender(newTab);
	    				//AjaxRendererUtils.addRegionByName(context, newTab, newTab
	    					//	.getId());
	        			if (ajaxContext != null) {
	        				ajaxContext.addRenderedArea(newTab.getClientId(context) + ":header");
	        				ajaxContext.addRenderedArea(newTab.getClientId(context));
	        			}
	    				
	    				Object newValue = newTab.getName();
	    	    		
	    				tabPanel.updateValue(context, newValue);

	        			tabPanel.setTabSwitched(true);
	        			clientTabIds.add(newTab.getClientId(context));
	        		}
	        	}
	        	AjaxContext.getCurrentInstance().setResponseData(clientTabIds);
	        	//new ActionEvent(tabPanel).queue();
			}
	}
    }
    
    @Override
    protected void preEncodeBegin(FacesContext context, UIComponent component)
    		throws IOException {
    	UITabPanel tabPanel = (UITabPanel) component;
    	
    	Object selectedTab = updateSelectedTab(context, tabPanel);
		if (selectedTab != null) {
			tabPanel.setSelectedTab(selectedTab);
		}
    	
    	if (tabPanel.getSelectedTab() == null || tabPanel.getSelectedTab().toString().length() == 0
    			|| tabPanel.getTabWithName(tabPanel.getSelectedTab()) == null) {
    		findFirstTab(tabPanel, context);
    	}
    }
    public Object getAddNewTabScript(FacesContext context, UIComponent component) {
    	JSFunctionDefinition definition = new JSFunctionDefinition();
    	definition.addParameter(NEW_TAB_ID_REFERENCE);
    	JSFunction function = AjaxRendererUtils.buildAjaxFunction(component, context, AjaxRendererUtils.AJAX_FUNCTION_NAME);
    	function.addParameter(JSReference.NULL);
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put(component.getClientId(context) + NEW_TAB_ID_PARAMETER, new JSReference(NEW_TAB_ID_REFERENCE));
    	Map<String, Object> options = AjaxRendererUtils.buildEventOptions(context, component, params);
    	
    	JSFunctionDefinition onbeforedomupdate = new JSFunctionDefinition("request", "event", "data");
    	onbeforedomupdate.addToBody("$('"+component.getClientId(context)+"').component.creatNewTabs(data)");
    	options.put("onbeforedomupdate", ScriptUtils.toScript(onbeforedomupdate));
    	
    	function.addParameter(AjaxRendererUtils.buildEventOptions(context, component, params));
    	definition.addToBody(function.toScript());
    	return definition.toScript();
    }
    
    Object updateSelectedTab(FacesContext context, UITabPanel tabPanel) {
    	ValueExpression ve = tabPanel.getValueExpression("value");
    	if (ve != null) {
    	    try {
    		return (ve.getValue(context.getELContext()));
    	    }
    	    catch (ELException e) {
    		throw new FacesException(e);
    	    }
    	} else {
    	    return (null);
    	}
    }
    
    public void initVariables(FacesContext context, UITabPanel tabPanel) {
    	// Set up rendered variables
       	tabPanel.getAttributes().put(TABS_COLLECTOR_ATTR_NAME, new HashMap<String, Object>());
       	tabPanel.getAttributes().put(CLASS_BUILDER_ATTRIBUTE, new TabPanelClassBuilder(tabPanel));
    } 
    
    public static void resetVariables(FacesContext context, UIComponent tabPanel) {
    	tabPanel.getAttributes().remove(TABS_COLLECTOR_ATTR_NAME);
    	tabPanel.getAttributes().remove(CLASS_BUILDER_ATTRIBUTE);
    } 
    
    private void findFirstTab(UITabPanel tabPanel, FacesContext context) {
    	Iterator<?> iterator = tabPanel.getRenderedTabs();
    	UITab tab = null;
    	while (iterator.hasNext()) {
    		UIComponent c = (UIComponent)iterator.next();
    		if (c instanceof UITab) {
    			tab = (UITab)c;
    			if (tab.getName() != null) {
    				break;
    			}
    		}
    	}
    	
    	if (tab != null) {
    		tabPanel.updateValue(context, tab.getName());
    	}
    }

    /**
     * Encode JavaScript function for switch tabs.
     *
     * @param context
     * @throws IOException
     */
   
    
    @SuppressWarnings("unchecked")
	public String getTabsInfo(FacesContext context, UITabPanel panel) {
    	Map<String, Object> info = (Map<String, Object>)panel.getAttributes().get(TABS_COLLECTOR_ATTR_NAME);
    	return ScriptUtils.toScript(info);    	
    }
    
   
	public Object getContainerId(FacesContext context, UITabPanel panel) {
    	AjaxContainer container = AjaxRendererUtils.findAjaxContainer(context, panel);
    	if (container != null) {
    		return ((UIComponent)container).getClientId(context);
    	}
    	return "";    	
    }
	
	public Object getSelectedTab(FacesContext context, UITabPanel tabPanel) {
		return tabPanel.getSelectedTab();
	}
    
     
    public Object writeEventHandlerFunction(FacesContext context, UIComponent component, String eventName) throws IOException {
    	String script = (String)component.getAttributes().get(eventName);
    	if (script != null && !"".equals(script)) {
    		JSFunctionDefinition onEventDefinition = new JSFunctionDefinition();
    		onEventDefinition.addParameter("event");
    		onEventDefinition.addToBody(script);
    		return onEventDefinition.toScript();
    	}
    	return JSReference.NULL;
    }
  

}
