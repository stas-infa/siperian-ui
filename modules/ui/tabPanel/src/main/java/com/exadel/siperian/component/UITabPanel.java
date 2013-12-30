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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.iterators.FilterIterator;
import org.apache.commons.collections.iterators.IteratorChain;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UISwitchablePanel;
import org.richfaces.event.SwitchablePanelSwitchEvent;

import com.exadel.siperian.renderkit.TabPanelRendererBase;

/**
 * JSF component class
 */
public abstract class UITabPanel extends UISwitchablePanel {
	
	private static final Log log = LogFactory.getLog(UITabPanel.class);
  
	public static final String COMPONENT_TYPE = "com.exadel.siperian.TabPanel";

    private transient boolean processedTabImmediate;
    
    private String switchType = UISwitchablePanel.AJAX_METHOD;
    
    private Object activeTabName;

    transient Object activatedTabName;
    
    transient Object switchedTabName;
    
    transient String tabName2Delete = null;
    
    transient List<Object> newTabNames = new ArrayList<Object>();
    
    @Override
    public void broadcast(FacesEvent facesEvent)
    		throws AbortProcessingException {
    	FacesContext context = getFacesContext();
    	Map<String, String>  params = context.getExternalContext().getRequestParameterMap();
//    	if (params.get(getClientId(context) + TabPanelRendererBase.CLOSE_TAB_PARAMETER) != null) {
//    		MethodBinding binding = getTabCloseListener();
//    		if (binding != null) {
//    			binding.invoke(context, new Object[] { facesEvent });
//    		}
//    	}
       	super.broadcast(facesEvent);
    }
    
    public boolean getRendersChildren() {
        return true;
    }

	  @SuppressWarnings("unchecked")
	protected Iterator<UIComponent> getSwitchedFacetsAndChildren() {

		final Object activeTabName = this.activeTabName;

		return new IteratorChain(new FilterIterator(getRenderedTabs(),
				new Predicate() {

					public boolean evaluate(Object object) {
						TabBase tab = (TabBase) object;

						if (tab.isDisabled()) {
							return false;
						}
						
						// Process only active tab
						/*if (CLIENT_METHOD.equals(tab.getSwitchTypeOrDefault())) {
							return true;
						}*/

						return activeTabName != null
								&& activeTabName.equals(tab.getName());
					}

				}), getFacets().values().iterator());
	}


    public boolean isImmediate() {
    	if(!super.isImmediate()){
    		if(processedTabImmediate){
    			return true;
    		}
    		return false;
      	}else{
    		return super.isImmediate();
    	}
	
    }
       
    public Object convertSwitchValue(UIComponent component, Object object) {
        if (object != null) {
            return object;
        }
        
        return ((TabBase) component).getName();
    }

    public Object getValue() {
    	if (renderedValue != null) {
    		return renderedValue;
    	}
    	
    	return super.getValue();
    }
    
    public void setValue(Object value) {
    	super.setValue(value);
    }
    
    /**
     * Get value for current selected tab. Possible classes - prefered {@link Integer} for tab number, or other Object for tab model value.
     *
     * @return selectedTab value from local variable or value bindings
     */
    public Object getSelectedTab() {
    	return getValue();
    }

    /**
     * Set currently selected tab.
     *
     * @param tab
     */
    public void setSelectedTab(Object tab) {
    	setValue(tab);
    }

    public Object getRenderedValue() {
    	return getValue();
    }
    
    public void setRenderedValue(Object renderedValue) {
		this.renderedValue = renderedValue;
	}
    
    private Object renderedValue;
    
   
    public void processDecodes(FacesContext context) {
		processPhase(context, PhaseId.APPLY_REQUEST_VALUES);
	}
    
    private void processPhase(FacesContext context, PhaseId phaseId) {
    	if (context == null) {
			throw new NullPointerException("FacesContext is null!");
		}

		if (!isRendered()) {
            return;
        }

        for (String renderedTabName : getTabsLoaded()) {
            if ((renderedTabName != null && renderedTabName.equals(tabName2Delete)) || renderedTabName == null
                    || renderedTabName.length() == 0) {
                continue;
            }
            setActiveTabName(renderedTabName);
            Iterator<?> kids = getSwitchedFacetsAndChildren();
            while (kids.hasNext()) {
                UIComponent kid = (UIComponent) kids.next();
                if (PhaseId.APPLY_REQUEST_VALUES.equals(phaseId)) {
                    kid.processDecodes(context);
                } else if (PhaseId.PROCESS_VALIDATIONS.equals(phaseId)) {
                    kid.processValidators(context);
                } else if (PhaseId.UPDATE_MODEL_VALUES.equals(phaseId)) {
                    kid.processUpdates(context);
                }
            }

        }

		try {

			if (PhaseId.APPLY_REQUEST_VALUES.equals(phaseId)) {
				decode(context);
				if (isImmediate()) {
					validate(context);
				}
			} else if (PhaseId.PROCESS_VALIDATIONS.equals(phaseId)) {
				if (!isImmediate()) {
	                validate(context);

	                if (!isValid()) {
	                    context.renderResponse();
	                }
	            }
			} else if (PhaseId.UPDATE_MODEL_VALUES.equals(phaseId)) {
				updateModel(context);
				if (!isValid()) {
				    context.renderResponse();
				}
			}

			if (isImmediate()) {
				validate(context);
			}
		} catch (RuntimeException e) {
			log.error(e);
			context.renderResponse();
			throw e;
		}
    }
    
    @Override
    public void processValidators(FacesContext context) {
    	processPhase(context, PhaseId.PROCESS_VALIDATIONS);
    }
    
    
    @Override
    public void processUpdates(FacesContext context) {
    	processPhase(context, PhaseId.UPDATE_MODEL_VALUES);
    }
    
    
    //TODO nick - there are fields that have their state not saved/restored
    public Object saveState(FacesContext context) {
    	TabPanelRendererBase.resetVariables(context, this);
    	Object[] state = new Object[2];
    	state[0] = super.saveState(context);
    	state[1] = activeTabName;
      	
    	return state;
    }
    
    public void restoreState(FacesContext context, Object state) {
    	Object[] states = (Object[]) state;
    	super.restoreState(context, states[0]);
    	activeTabName = states[1];
    	
    	//TODO nick - shouldn't be done on restore state phase, Seam conversations are not ready on this phase
    	if (activeTabName == null) {
    		activeTabName = getValue();
    		
    	}
    	//this.restoredRenderedValue = getRenderedValue();
    }
    
    private static final Predicate RENDERED_TAB_PREDICATE = new Predicate() {

	public boolean evaluate(Object object) {
	    if (object instanceof UITab) {
		UITab tab = (UITab) object;

		return tab.isRendered();
	    }
	    return false;
	}

    };
    
    /**
     * Create iterator for all rendered tabs in this component
     * {@link Iterator#next()} method will return tab model - {@link UITab}
     *
     * @return Iterator
     */
    
    //TODO nick - this is iterator of UIComponent but not of ?
    public Iterator<?> getRenderedTabs() {
	if (getChildCount() > 0) {
	    return new FilterIterator(getChildren().iterator(), RENDERED_TAB_PREDICATE);
	} else {
	    return CollectionUtils.EMPTY_COLLECTION.iterator();
	}
    }

	public String getSwitchType() {
		//TODO nick - what if user wishes to set switch type by EL?
		return switchType;
	}

	public void setSwitchType(String switchType) {
		this.switchType = switchType;
	}
	public abstract String getHeaderAlignment();

    public abstract void setHeaderAlignment(String newAlignment);

    public abstract String getHeaderSpacing();

    public abstract void setHeaderSpacing(String value);
    
    public abstract String getHeaderClass();

    public abstract void setHeaderClass(String value);
    
    public abstract MethodBinding getTabCloseListener();
//    
    public abstract void setTabCloseListener(MethodBinding binding);
//    
//    public void addTabCloseListener(ActionListener listener) {
//        addFacesListener(listener);
//    }
//
//    public ActionListener[] getTabCloseListeners() {
//        return (ActionListener[]) getFacesListeners(ActionListener.class);
//    }
//
//    public void removeTabCloseListener(ActionListener listener) {
//        removeFacesListener(listener);
//    }
    
//    public abstract MethodBinding getTabCloseListener();
//
//    public abstract void setTabCloseListener(MethodBinding scrollerListener);
//
//    public void addTabCloseListener(ActionListener listener) {
//        addFacesListener(listener);
//    }
//
//    public ActionListener[] getTabCloseListeners() {
//        return (ActionListener[]) getFacesListeners(ActionListener.class);
//    }
//
//    public void removeTabCloseListener(ActionListener listener) {
//        removeFacesListener(listener);
//    }
    

	public Object getActiveTabName() {
		if (activeTabName != null) {
			return activeTabName;
		}else {
			return getSelectedTab();
		}
	}

	public void setActiveTabName(Object activeTabName) {
		this.activeTabName = activeTabName;
	}

	private UITab processedTab(UIComponent component, Object object){
    	if (object != null) {
             return getTabWithName(object);
        }
        return (UITab)component;
    }


    private UITab getTabWithName(Object tabName){
    	for (Iterator<?> iterator = getRenderedTabs(); iterator.hasNext();) {
			UIComponent childComponent =  (UIComponent)iterator.next();
			if(childComponent instanceof UITab && ((UITab)childComponent).getName().equals(tabName)){
				return (UITab)childComponent; 
			}
		}
    	return null;
    }
        
    public void queueEvent(FacesEvent event) {
    	
    	if(event instanceof SwitchablePanelSwitchEvent && this.equals(event.getComponent())){
    		SwitchablePanelSwitchEvent switchEvent = (SwitchablePanelSwitchEvent)event; 
    		if (switchEvent.getEventSource() instanceof UITab) {
    			UITab tab = processedTab(switchEvent.getEventSource(),switchEvent.getValue());     		
    		//Check if target Tab is immediate
    			processedTabImmediate = tab.isImmediate();
    		}else {
    			processedTabImmediate = false;
    		}
    	}
    	
    	if(event instanceof ActionEvent && event.getComponent()instanceof UITab){
    		
    		if(isImmediate()){
    			event.setPhaseId(PhaseId.APPLY_REQUEST_VALUES);
    		}else{
    			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
    		}
    		
    	}
    	
    	super.queueEvent(event);
    }
    
    public boolean isTabActive(TabBase tab) {
		if (tab.getName() != null && tab.getName().equals(getSelectedTab())) {
			return true;
		} else {
			return false;
		}
	}
    public boolean isTabLoaded(TabBase tab) {
//        for (String tabName : getTabsLoaded()) {
//            if (tabName.equals(tab.getName())) {
//                return true;
//            }
//        }
        return (tab.getName() != null) ? tab.getName().equals(getRenderedTabName()) : false;
        // return ArrayUtils.contains(renderedTabsNames, tab.getName());
    }

    private String[] getTabsLoaded() {
        String[] result = new String[0];
        Map<String, String> params = this.getFacesContext().getExternalContext().getRequestParameterMap();

        String renderedTabsName = params.get(this.getClientId(this.getFacesContext())
                + TabPanelRendererBase.RENDERED_TAB_NAME_PARAMETER);

        if (renderedTabsName != null && renderedTabsName.length() != 0) {
            String[] renderedTabsNames = renderedTabsName.split(",");
            if (renderedTabsNames != null && renderedTabsNames.length != 0) {
                result = renderedTabsNames;
            }
        }
        return result;
    }
    
    private String getRenderedTabName() {
    	String [] loadedtabs = getTabsLoaded();
    	return (loadedtabs != null && loadedtabs.length > 0) ? getTabsLoaded()[loadedtabs.length-1] : null;
    }
    
    @Override
    public void updateModel(FacesContext context) {
    	if (activatedTabName != null) {
    		this.activeTabName = this.activatedTabName;
    	}
    	
    	setSelectedTab(this.activeTabName);
    	    	
		if (getTabName2Delete() != null) {
			MethodBinding binding = getTabCloseListener();
			if (binding != null) {
				binding.invoke(context, new Object [] {getTabName2Delete()});
			}
		}
		
		updateValue(context);
    }
    
    private void updateValue(FacesContext context) {
    	Object activeTabName = getActiveTabName();
    	Object oldValue = getValue();
		if ((oldValue == null && activeTabName != null) ||
				(oldValue != null && !oldValue.equals(activeTabName))) {

			queueEvent(new ValueChangeEvent(this, oldValue, activeTabName));
		}
		ValueExpression valueBinding = getValueExpression("value");
		if (valueBinding != null) {
			valueBinding.setValue(context.getELContext(), activeTabName);
			setValue(null);
		} else {
			setValue(activeTabName);
		}
		
    	
    }

	/**
	 * @param activeTabNameSet the activeTabNameSet to set
	 */
	public void setActivatedTabName(Object activatedTabName) {
		this.activatedTabName = activatedTabName;
	}

	/**
	 * @return the tabName2Delete
	 */
	public String getTabName2Delete() {
		return tabName2Delete;
	}

	/**
	 * @param tabName2Delete the tabName2Delete to set
	 */
	public void setTabName2Delete(String tabName2Delete) {
		this.tabName2Delete = tabName2Delete;
	}

	/**
	 * @return the newTabNames
	 */
	public List<Object> getNewTabNames() {
		return newTabNames;
	}

	/**
	 * @param newTabNames the newTabNames to set
	 */
	public void setNewTabNames(List<Object> newTabNames) {
		this.newTabNames = newTabNames;
	}
	
}
