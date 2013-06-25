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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;

import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.javascript.JSReference;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.iterators.FilterIterator;
import org.apache.commons.collections.iterators.IteratorChain;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UISwitchablePanel;

import com.exadel.siperian.renderkit.TabPanelClassBuilder;
import com.exadel.siperian.renderkit.TabPanelRendererBase;

/**
 * JSF component class
 */
@SuppressWarnings("deprecation")
public abstract class UITabPanel extends UISwitchablePanel {
   
   private static final Log log = LogFactory.getLog(UITabPanel.class);
  
   public static final String COMPONENT_TYPE = "com.exadel.siperian.TabPanel";
   
   //---
   public final static String TABS_COLLECTOR_ATTR_NAME = TabPanelRendererBase.class.getName() +  "tabs";
   
   public final static String CONTAINERID_VAR_NAME = "containerId";
   
   public static final String CLASS_BUILDER_ATTRIBUTE = "classBuilder";
   
   public static final String TAB_NAME_PARAMETER = ":tabName";
   
   public static final String CLOSE_TAB_PARAMETER = ":close";
   
   public static final String NEW_TABS_IDS_ATTRIBUTE = "newTabsIds";
   
   public static final String NEW_TABS_INFO_ATTRIBUTE = "newTabsInfos";
   
   public static final String NEW_TAB_ID_PARAMETER = ":newTabId";
   
   private static final String NEW_TAB_ID_REFERENCE = "newTabId";
   
   
   //---

    private transient boolean processedTabImmediate;
   
    private Object selectedTab;
    
    transient boolean tabSwitched = false;
    
    transient String tabName2Delete = null;
    
    transient List<Object> newTabNames = new ArrayList<Object>();
    
    transient boolean newTabs; 
   
    @Override
    public void broadcast(FacesEvent facesEvent)
         throws AbortProcessingException {
      if (facesEvent instanceof ActionEvent && newTabs) {
//          FacesContext context = FacesContext.getCurrentInstance();
//          if (isNewTabs()) {
//             List<String> ids = (List<String>)getAttributes().get("newTabsList");
//             List<String> clientTabIds = new ArrayList<String>();
//             if (ids != null && ids.size() > 0) {
//                initVariables(context);
//                getAttributes().put(NEW_TABS_IDS_ATTRIBUTE, new ArrayList<String>());
//                getAttributes().put(NEW_TABS_INFO_ATTRIBUTE, new ArrayList<Object>());
//                for (String newTabId : ids) {
//                UITab newTab = findTabById(newTabId);
//                   if (newTab != null) {
//                      setSelectedTab(newTab.getName());
//                      getNewTabNames().add(newTab.getName());
//                      new SwitchablePanelSwitchEvent(newTab, null, newTab).queue();
//                      AjaxContext ajaxContext = AjaxContext.getCurrentInstance(context);
//                      if (ajaxContext != null) {
//                         ajaxContext.addRenderedArea(newTab.getClientId(context) + ":header");
//                      }
//                      setTabSwitched(true);
//                      clientTabIds.add(newTab.getClientId(context));
//                   }
//                }
//                AjaxContext.getCurrentInstance().setResponseData(ScriptUtils.toScript(clientTabIds));
//                //new ActionEvent(tabPanel).queue();
//             }
//          }
         AjaxContext.getCurrentInstance().addComponentToAjaxRender(this);
      }
      super.broadcast(facesEvent);
    }
    
    private void initVariables(FacesContext context) {
      // Set up rendered variables
         getAttributes().put(TABS_COLLECTOR_ATTR_NAME, new HashMap<String, Object>());
         getAttributes().put(CLASS_BUILDER_ATTRIBUTE, new TabPanelClassBuilder(this));
    } 
    
    
    private UITab findTabById(String newTabId) {
      UIComponent c = findComponent(newTabId);
      if (c != null && c instanceof UITab) {
         return (UITab)c;
      }
      return null;
    }
    
    public boolean getRendersChildren() {
        return true;
    }

     @SuppressWarnings("unchecked")
   protected Iterator<UIComponent> getSwitchedFacetsAndChildren() {
      return new IteratorChain(new FilterIterator(getRenderedTabs(),
            new Predicate() {

               public boolean evaluate(Object object) {
                  TabBase tab = (TabBase) object;

                  if (tab.isDisabled()) {
                     return false;
                  }
                  return selectedTab != null
                        && selectedTab.equals(tab.getName());
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
      
  
    public Object getValue() {
      if (selectedTab != null) {
         return selectedTab;
      }
      
      return super.getValue();
    }
    
    public void setValue(Object value) {
      super.setValue(value);
    }
    
    public void setSelectedTab(Object selectedTab) {
      this.selectedTab = selectedTab;
    }
    
    public Object getSelectedTab() {
      if (selectedTab != null) {
         return selectedTab;
      }
      
      return getValue();
    }
    
   
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

              Iterator<?> kids = getSwitchedFacetsAndChildren();
            while (kids.hasNext()) {
                UIComponent kid = (UIComponent) kids.next();
                if (PhaseId.APPLY_REQUEST_VALUES.equals(phaseId)) {
                  try {
                     kid.processDecodes(context);
            
                  }catch (ValidatorException e) {
                  System.out.println(e);
               }
                } else if (PhaseId.PROCESS_VALIDATIONS.equals(phaseId)) {
                    kid.processValidators(context);
                } else if (PhaseId.UPDATE_MODEL_VALUES.equals(phaseId)) {
                    kid.processUpdates(context);
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
            if (context.getRenderResponse()) {
               setNewTabNames(new ArrayList<Object>());
               setTabName2Delete(null);
               setTabSwitched(false);
               setNewTabs(false);
               
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
    
    
    public Object saveState(FacesContext context) {
      //TabPanelRendererBase.resetVariables(context, this);
      Object[] state = new Object[2];
      state[0] = super.saveState(context);
      state[1] = selectedTab;
         
      return state;
    }
    
    public void restoreState(FacesContext context, Object state) {
      Object[] states = (Object[]) state;
      super.restoreState(context, states[0]);
      selectedTab = (String)states[1];
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
    
    public Iterator<?> getRenderedTabs() {
   if (getChildCount() > 0) {
       return new FilterIterator(getChildren().iterator(), RENDERED_TAB_PREDICATE);
   } else {
       return CollectionUtils.EMPTY_COLLECTION.iterator();
   }
    }

    @SuppressWarnings("deprecation")
   public abstract MethodBinding getTabCloseListener();
    
    @SuppressWarnings("deprecation")
    public abstract void setTabCloseListener(MethodBinding binding);

    public UITab getTabWithName(Object tabName){
      for (Iterator<?> iterator = getRenderedTabs(); iterator.hasNext();) {
         UIComponent childComponent =  (UIComponent)iterator.next();
         if(childComponent instanceof UITab && ((UITab)childComponent).getName()!=null && ((UITab)childComponent).getName().equals(tabName)){
            return (UITab)childComponent; 
         }
      }
      return null;
    }
       
    public boolean isTabRendered(TabBase tab) {
        return (tab.getName() != null) ? tab.getName().equals(selectedTab) : false;
    }

  
    @Override
    @SuppressWarnings("deprecation")
    public void updateModel(FacesContext context) {
    
      setSelectedTab(this.selectedTab);
            
      if (getTabName2Delete() != null) {
         MethodBinding binding = getTabCloseListener();
         if (binding != null) {
            binding.invoke(context, new Object [] {getTabName2Delete()});
         }
         AjaxContext.getCurrentInstance().setResponseData(JSReference.TRUE);
      }
      
    }
    
    public void updateValue(FacesContext context, Object newValue) {
      Object oldValue = getValue();
      if ((oldValue == null && newValue != null) ||
            (oldValue != null && !oldValue.equals(newValue))) {

         queueEvent(new ValueChangeEvent(this, oldValue, newValue));
      }
      ValueExpression valueBinding = getValueExpression("value");
      if (valueBinding != null) {
         valueBinding.setValue(context.getELContext(), newValue);
         setValue(null);
      } else {
         setValue(newValue);
      }
      
      setSelectedTab(newValue);
      
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

   public List<Object> getNewTabNames() {
      return newTabNames;
   }

   public void setNewTabNames(List<Object> newTabNames) {
      this.newTabNames = newTabNames;
   }
   
   public boolean isTabSwitched() {
      return tabSwitched;
   }
   
   public void setTabSwitched(boolean tabSwitched) {
      this.tabSwitched = tabSwitched;
   }

   public boolean isNewTabs() {
      return newTabs;
   }

   public void setNewTabs(boolean newTabs) {
      this.newTabs = newTabs;
   }

   @Override
   public void queueEvent(FacesEvent event) {
      if (event instanceof ActionEvent) {
         event.setPhaseId(PhaseId.RENDER_RESPONSE);
      }
      super.queueEvent(event);
   }
}
