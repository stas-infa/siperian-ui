/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import javax.faces.convert.Converter ;
import java.lang.Object ;
import java.util.List ;
import java.lang.Integer ;
import java.lang.String ;
import com.exadel.siperian.taglib.TabPanelTagBase ;
import javax.faces.el.MethodBinding ;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.el.MethodExpression;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.el.ValueExpression;
import org.richfaces.webapp.taglib.MethodBindingMethodExpressionAdaptor;
import org.richfaces.webapp.taglib.ValueBindingValueExpressionAdaptor;
import com.exadel.siperian.component.html.HtmlTabPanel;

public class TabPanelTag extends com.exadel.siperian.taglib.TabPanelTagBase {

		// Fields
		 	  			  		  	  
		/*
		 * activeTabClass
		 * A CSS class to be applied to an active tab
		 */
		private ValueExpression _activeTabClass;
		/**
		 * A CSS class to be applied to an active tab
		 * Setter for activeTabClass
		 * @param activeTabClass - new value
		 */
		 public void setActiveTabClass( ValueExpression  __activeTabClass ){
			this._activeTabClass = __activeTabClass;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * contentClass
		 * A CSS class for content of a tab panel
		 */
		private ValueExpression _contentClass;
		/**
		 * A CSS class for content of a tab panel
		 * Setter for contentClass
		 * @param contentClass - new value
		 */
		 public void setContentClass( ValueExpression  __contentClass ){
			this._contentClass = __contentClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * contentStyle
		 * A CSS style is for the content of a tab panel
		 */
		private ValueExpression _contentStyle;
		/**
		 * A CSS style is for the content of a tab panel
		 * Setter for contentStyle
		 * @param contentStyle - new value
		 */
		 public void setContentStyle( ValueExpression  __contentStyle ){
			this._contentStyle = __contentStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * converter
		 * Id of Converter to be used or reference to a Converter
		 */
		private ValueExpression _converter;
		/**
		 * Id of Converter to be used or reference to a Converter
		 * Setter for converter
		 * @param converter - new value
		 */
		 public void setConverter( ValueExpression  __converter ){
			this._converter = __converter;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * converterMessage
		 * A ValueExpression enabled attribute that, if present, will be used as the text of the converter message, replacing any message that comes from the converter
		 */
		private ValueExpression _converterMessage;
		/**
		 * A ValueExpression enabled attribute that, if present, will be used as the text of the converter message, replacing any message that comes from the converter
		 * Setter for converterMessage
		 * @param converterMessage - new value
		 */
		 public void setConverterMessage( ValueExpression  __converterMessage ){
			this._converterMessage = __converterMessage;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * disabledTabClass
		 * A CSS class to be applied to a disabled tab
		 */
		private ValueExpression _disabledTabClass;
		/**
		 * A CSS class to be applied to a disabled tab
		 * Setter for disabledTabClass
		 * @param disabledTabClass - new value
		 */
		 public void setDisabledTabClass( ValueExpression  __disabledTabClass ){
			this._disabledTabClass = __disabledTabClass;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * headerAlignment
		 * Sets tab headers alignment. It can be "left" or "right".
	    		 Default value is "left".
		 */
		private ValueExpression _headerAlignment;
		/**
		 * Sets tab headers alignment. It can be "left" or "right".
	    		 Default value is "left".
		 * Setter for headerAlignment
		 * @param headerAlignment - new value
		 */
		 public void setHeaderAlignment( ValueExpression  __headerAlignment ){
			this._headerAlignment = __headerAlignment;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * headerClass
		 * A CSS style is for the header of a tab panel.
		 */
		private ValueExpression _headerClass;
		/**
		 * A CSS style is for the header of a tab panel.
		 * Setter for headerClass
		 * @param headerClass - new value
		 */
		 public void setHeaderClass( ValueExpression  __headerClass ){
			this._headerClass = __headerClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * headerSpacing
		 * Sets tab headers spacing. It should be a valid size unit expression. Default value is "1px".
		 */
		private ValueExpression _headerSpacing;
		/**
		 * Sets tab headers spacing. It should be a valid size unit expression. Default value is "1px".
		 * Setter for headerSpacing
		 * @param headerSpacing - new value
		 */
		 public void setHeaderSpacing( ValueExpression  __headerSpacing ){
			this._headerSpacing = __headerSpacing;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * height
		 * Height of a tab panel defined in pixels or in percents
		 */
		private ValueExpression _height;
		/**
		 * Height of a tab panel defined in pixels or in percents
		 * Setter for height
		 * @param height - new value
		 */
		 public void setHeight( ValueExpression  __height ){
			this._height = __height;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * immediate
		 * A flag indicating that this component value must be converted
            and validated immediately (that is, during Apply Request Values
            phase), rather than waiting until a Process Validations phase
		 */
		private ValueExpression _immediate;
		/**
		 * A flag indicating that this component value must be converted
            and validated immediately (that is, during Apply Request Values
            phase), rather than waiting until a Process Validations phase
		 * Setter for immediate
		 * @param immediate - new value
		 */
		 public void setImmediate( ValueExpression  __immediate ){
			this._immediate = __immediate;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * inactiveTabClass
		 * CSS class to be applied to an inactive (but not disabled) tab
		 */
		private ValueExpression _inactiveTabClass;
		/**
		 * CSS class to be applied to an inactive (but not disabled) tab
		 * Setter for inactiveTabClass
		 * @param inactiveTabClass - new value
		 */
		 public void setInactiveTabClass( ValueExpression  __inactiveTabClass ){
			this._inactiveTabClass = __inactiveTabClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * label
		 * A localized user presentable name for this component.
		 */
		private ValueExpression _label;
		/**
		 * A localized user presentable name for this component.
		 * Setter for label
		 * @param label - new value
		 */
		 public void setLabel( ValueExpression  __label ){
			this._label = __label;
	     }
	  
	 	 		 		 		 		 	  			  		  	  
		/*
		 * markupTemplate
		 * Defines tab panel type (Search, Static)
		 */
		private ValueExpression _markupTemplate;
		/**
		 * Defines tab panel type (Search, Static)
		 * Setter for markupTemplate
		 * @param markupTemplate - new value
		 */
		 public void setMarkupTemplate( ValueExpression  __markupTemplate ){
			this._markupTemplate = __markupTemplate;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * maxCountOfTabsLoadedOnClient
		 * Defines max count of tabs to load on client
		 */
		private ValueExpression _maxCountOfTabsLoadedOnClient;
		/**
		 * Defines max count of tabs to load on client
		 * Setter for maxCountOfTabsLoadedOnClient
		 * @param maxCountOfTabsLoadedOnClient - new value
		 */
		 public void setMaxCountOfTabsLoadedOnClient( ValueExpression  __maxCountOfTabsLoadedOnClient ){
			this._maxCountOfTabsLoadedOnClient = __maxCountOfTabsLoadedOnClient;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * maxTabWidth
		 * 
		 */
		private ValueExpression _maxTabWidth;
		/**
		 * 
		 * Setter for maxTabWidth
		 * @param maxTabWidth - new value
		 */
		 public void setMaxTabWidth( ValueExpression  __maxTabWidth ){
			this._maxTabWidth = __maxTabWidth;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * newTabNames
		 * newTabNames
		 */
		private ValueExpression _newTabNames;
		/**
		 * newTabNames
		 * Setter for newTabNames
		 * @param newTabNames - new value
		 */
		 public void setNewTabNames( ValueExpression  __newTabNames ){
			this._newTabNames = __newTabNames;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * newTabs
		 * newTabs
		 */
		private ValueExpression _newTabs;
		/**
		 * newTabs
		 * Setter for newTabs
		 * @param newTabs - new value
		 */
		 public void setNewTabs( ValueExpression  __newTabs ){
			this._newTabs = __newTabs;
	     }
	  
	 	 		 		 		 		 		 		 		 		 		 		 		 	  			  		  	  
		/*
		 * ontabchange
		 * HTML: a script expression; a tab has been changed
		 */
		private ValueExpression _ontabchange;
		/**
		 * HTML: a script expression; a tab has been changed
		 * Setter for ontabchange
		 * @param ontabchange - new value
		 */
		 public void setOntabchange( ValueExpression  __ontabchange ){
			this._ontabchange = __ontabchange;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * ontabclosed
		 * 
		 */
		private ValueExpression _ontabclosed;
		/**
		 * 
		 * Setter for ontabclosed
		 * @param ontabclosed - new value
		 */
		 public void setOntabclosed( ValueExpression  __ontabclosed ){
			this._ontabclosed = __ontabclosed;
	     }
	  
	 	 		 		 		 		 	  			  		  	  
		/*
		 * required
		 * If "true", this component is checked for non-empty input
		 */
		private ValueExpression _required;
		/**
		 * If "true", this component is checked for non-empty input
		 * Setter for required
		 * @param required - new value
		 */
		 public void setRequired( ValueExpression  __required ){
			this._required = __required;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * requiredMessage
		 * A ValueExpression enabled attribute that, if present, will be used as the text of the validation message for the "required" facility, if the "required" facility is used
		 */
		private ValueExpression _requiredMessage;
		/**
		 * A ValueExpression enabled attribute that, if present, will be used as the text of the validation message for the "required" facility, if the "required" facility is used
		 * Setter for requiredMessage
		 * @param requiredMessage - new value
		 */
		 public void setRequiredMessage( ValueExpression  __requiredMessage ){
			this._requiredMessage = __requiredMessage;
	     }
	  
	 	 		 		 		 		 	  			  		  	  
		/*
		 * switchType
		 * Tab switch algorithm: "client", "server"(default), "ajax"
		 */
		private ValueExpression _switchType;
		/**
		 * Tab switch algorithm: "client", "server"(default), "ajax"
		 * Setter for switchType
		 * @param switchType - new value
		 */
		 public void setSwitchType( ValueExpression  __switchType ){
			this._switchType = __switchType;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * tabClass
		 * A CSS class to be applied to all tabs
		 */
		private ValueExpression _tabClass;
		/**
		 * A CSS class to be applied to all tabs
		 * Setter for tabClass
		 * @param tabClass - new value
		 */
		 public void setTabClass( ValueExpression  __tabClass ){
			this._tabClass = __tabClass;
	     }
	  
	 	 		 	  	  	  
		/*
		 * tabCloseListener
		 * MethodExpression representing an action listener method
				that will be notified after tab closed.
		 */
		private MethodExpression _tabCloseListener;
		/**
		 * MethodExpression representing an action listener method
				that will be notified after tab closed.
		 * Setter for tabCloseListener
		 * @param tabCloseListener - new value
		 */
		 public void setTabCloseListener( MethodExpression  __tabCloseListener ){
			this._tabCloseListener = __tabCloseListener;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * tabListHeight
		 * 
		 */
		private ValueExpression _tabListHeight;
		/**
		 * 
		 * Setter for tabListHeight
		 * @param tabListHeight - new value
		 */
		 public void setTabListHeight( ValueExpression  __tabListHeight ){
			this._tabListHeight = __tabListHeight;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * tabName2Delete
		 * tabName2Delete
		 */
		private ValueExpression _tabName2Delete;
		/**
		 * tabName2Delete
		 * Setter for tabName2Delete
		 * @param tabName2Delete - new value
		 */
		 public void setTabName2Delete( ValueExpression  __tabName2Delete ){
			this._tabName2Delete = __tabName2Delete;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * tabSwitched
		 * tabSwitched
		 */
		private ValueExpression _tabSwitched;
		/**
		 * tabSwitched
		 * Setter for tabSwitched
		 * @param tabSwitched - new value
		 */
		 public void setTabSwitched( ValueExpression  __tabSwitched ){
			this._tabSwitched = __tabSwitched;
	     }
	  
	 	 		 		 		 	  	  	  
		/*
		 * validator
		 * MethodBinding pointing at a method that is called during
            Process Validations phase of the request processing lifecycle,
            to validate the current value of this component
		 */
		private MethodExpression _validator;
		/**
		 * MethodBinding pointing at a method that is called during
            Process Validations phase of the request processing lifecycle,
            to validate the current value of this component
		 * Setter for validator
		 * @param validator - new value
		 */
		 public void setValidator( MethodExpression  __validator ){
			this._validator = __validator;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * validatorMessage
		 * A ValueExpression enabled attribute that, if present, will be used as the text of the validator message, replacing any message that comes from the validator
		 */
		private ValueExpression _validatorMessage;
		/**
		 * A ValueExpression enabled attribute that, if present, will be used as the text of the validator message, replacing any message that comes from the validator
		 * Setter for validatorMessage
		 * @param validatorMessage - new value
		 */
		 public void setValidatorMessage( ValueExpression  __validatorMessage ){
			this._validatorMessage = __validatorMessage;
	     }
	  
	 	 		 		 		 	  	  	  
		/*
		 * valueChangeListener
		 * Listener for value changes
		 */
		private MethodExpression _valueChangeListener;
		/**
		 * Listener for value changes
		 * Setter for valueChangeListener
		 * @param valueChangeListener - new value
		 */
		 public void setValueChangeListener( MethodExpression  __valueChangeListener ){
			this._valueChangeListener = __valueChangeListener;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * width
		 * Width of a tab panel defined in pixels or in percents. 
	    	The default value is 100%
		 */
		private ValueExpression _width;
		/**
		 * Width of a tab panel defined in pixels or in percents. 
	    	The default value is 100%
		 * Setter for width
		 * @param width - new value
		 */
		 public void setWidth( ValueExpression  __width ){
			this._width = __width;
	     }
	  
	 	 	
	
    public void release()
    {
        // TODO Auto-generated method stub
        super.release();
        		 		    this._activeTabClass = null;
	 		 		 		    this._contentClass = null;
	 		 		    this._contentStyle = null;
	 		 		    this._converter = null;
	 		 		    this._converterMessage = null;
	 		 		 		    this._disabledTabClass = null;
	 		 		 		    this._headerAlignment = null;
	 		 		    this._headerClass = null;
	 		 		    this._headerSpacing = null;
	 		 		    this._height = null;
	 		 		 		    this._immediate = null;
	 		 		    this._inactiveTabClass = null;
	 		 		    this._label = null;
	 		 		 		 		 		    this._markupTemplate = null;
	 		 		    this._maxCountOfTabsLoadedOnClient = null;
	 		 		    this._maxTabWidth = null;
	 		 		    this._newTabNames = null;
	 		 		    this._newTabs = null;
	 		 		 		 		 		 		 		 		 		 		 		 		    this._ontabchange = null;
	 		 		    this._ontabclosed = null;
	 		 		 		 		 		    this._required = null;
	 		 		    this._requiredMessage = null;
	 		 		 		 		 		    this._switchType = null;
	 		 		    this._tabClass = null;
	 		 		    this._tabCloseListener = null;
	 		 		    this._tabListHeight = null;
	 		 		    this._tabName2Delete = null;
	 		 		    this._tabSwitched = null;
	 		 		 		 		    this._validator = null;
	 		 		    this._validatorMessage = null;
	 		 		 		 		    this._valueChangeListener = null;
	 		 		 		    this._width = null;
	 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlTabPanel comp = (HtmlTabPanel) component;
 		 			 
						if (this._activeTabClass != null) {
				if (this._activeTabClass.isLiteralText()) {
					try {
												
						java.lang.String __activeTabClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._activeTabClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setActiveTabClass(__activeTabClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("activeTabClass", this._activeTabClass);
				}
			}
					    		 			 
						if (this._contentClass != null) {
				if (this._contentClass.isLiteralText()) {
					try {
												
						java.lang.String __contentClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._contentClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setContentClass(__contentClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("contentClass", this._contentClass);
				}
			}
					   		 			 
						if (this._contentStyle != null) {
				if (this._contentStyle.isLiteralText()) {
					try {
												
						java.lang.String __contentStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._contentStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setContentStyle(__contentStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("contentStyle", this._contentStyle);
				}
			}
					   		 			setConverterProperty(comp, this._converter);
		   		 			 
						if (this._converterMessage != null) {
				if (this._converterMessage.isLiteralText()) {
					try {
												
						java.lang.String __converterMessage = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._converterMessage.getExpressionString(), 
											java.lang.String.class);
					
												comp.setConverterMessage(__converterMessage);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("converterMessage", this._converterMessage);
				}
			}
					    		 			 
						if (this._disabledTabClass != null) {
				if (this._disabledTabClass.isLiteralText()) {
					try {
												
						java.lang.String __disabledTabClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._disabledTabClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setDisabledTabClass(__disabledTabClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("disabledTabClass", this._disabledTabClass);
				}
			}
					    		 			 
						if (this._headerAlignment != null) {
				if (this._headerAlignment.isLiteralText()) {
					try {
												
						java.lang.String __headerAlignment = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._headerAlignment.getExpressionString(), 
											java.lang.String.class);
					
												comp.setHeaderAlignment(__headerAlignment);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("headerAlignment", this._headerAlignment);
				}
			}
					   		 			 
						if (this._headerClass != null) {
				if (this._headerClass.isLiteralText()) {
					try {
												
						java.lang.String __headerClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._headerClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setHeaderClass(__headerClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("headerClass", this._headerClass);
				}
			}
					   		 			 
						if (this._headerSpacing != null) {
				if (this._headerSpacing.isLiteralText()) {
					try {
												
						java.lang.String __headerSpacing = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._headerSpacing.getExpressionString(), 
											java.lang.String.class);
					
												comp.setHeaderSpacing(__headerSpacing);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("headerSpacing", this._headerSpacing);
				}
			}
					   		 			 
						if (this._height != null) {
				if (this._height.isLiteralText()) {
					try {
												
						java.lang.String __height = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._height.getExpressionString(), 
											java.lang.String.class);
					
												comp.setHeight(__height);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("height", this._height);
				}
			}
					    		 			 
						if (this._immediate != null) {
				if (this._immediate.isLiteralText()) {
					try {
												
						Boolean __immediate = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._immediate.getExpressionString(), 
											Boolean.class);
					
												comp.setImmediate(__immediate.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("immediate", this._immediate);
				}
			}
					   		 			 
						if (this._inactiveTabClass != null) {
				if (this._inactiveTabClass.isLiteralText()) {
					try {
												
						java.lang.String __inactiveTabClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._inactiveTabClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setInactiveTabClass(__inactiveTabClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("inactiveTabClass", this._inactiveTabClass);
				}
			}
					   		 			 
						if (this._label != null) {
				if (this._label.isLiteralText()) {
					try {
												
						java.lang.String __label = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._label.getExpressionString(), 
											java.lang.String.class);
					
												comp.setLabel(__label);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("label", this._label);
				}
			}
					      		 			 
						if (this._markupTemplate != null) {
				if (this._markupTemplate.isLiteralText()) {
					try {
												
						java.lang.String __markupTemplate = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._markupTemplate.getExpressionString(), 
											java.lang.String.class);
					
												comp.setMarkupTemplate(__markupTemplate);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("markupTemplate", this._markupTemplate);
				}
			}
					   		 			 
						if (this._maxCountOfTabsLoadedOnClient != null) {
				if (this._maxCountOfTabsLoadedOnClient.isLiteralText()) {
					try {
												
						java.lang.Integer __maxCountOfTabsLoadedOnClient = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._maxCountOfTabsLoadedOnClient.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setMaxCountOfTabsLoadedOnClient(__maxCountOfTabsLoadedOnClient);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("maxCountOfTabsLoadedOnClient", this._maxCountOfTabsLoadedOnClient);
				}
			}
					   		 			 
						if (this._maxTabWidth != null) {
				if (this._maxTabWidth.isLiteralText()) {
					try {
												
						java.lang.Integer __maxTabWidth = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._maxTabWidth.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setMaxTabWidth(__maxTabWidth);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("maxTabWidth", this._maxTabWidth);
				}
			}
					   		 			 
						if (this._newTabNames != null) {
				if (this._newTabNames.isLiteralText()) {
					try {
												
						java.util.List __newTabNames = (java.util.List) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._newTabNames.getExpressionString(), 
											java.util.List.class);
					
												comp.setNewTabNames(__newTabNames);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("newTabNames", this._newTabNames);
				}
			}
					   		 			 
						if (this._newTabs != null) {
				if (this._newTabs.isLiteralText()) {
					try {
												
						Boolean __newTabs = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._newTabs.getExpressionString(), 
											Boolean.class);
					
												comp.setNewTabs(__newTabs.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("newTabs", this._newTabs);
				}
			}
					             		 			 
						if (this._ontabchange != null) {
				if (this._ontabchange.isLiteralText()) {
					try {
												
						java.lang.String __ontabchange = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._ontabchange.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOntabchange(__ontabchange);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("ontabchange", this._ontabchange);
				}
			}
					   		 			 
						if (this._ontabclosed != null) {
				if (this._ontabclosed.isLiteralText()) {
					try {
												
						java.lang.String __ontabclosed = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._ontabclosed.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOntabclosed(__ontabclosed);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("ontabclosed", this._ontabclosed);
				}
			}
					      		 			 
						if (this._required != null) {
				if (this._required.isLiteralText()) {
					try {
												
						Boolean __required = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._required.getExpressionString(), 
											Boolean.class);
					
												comp.setRequired(__required.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("required", this._required);
				}
			}
					   		 			 
						if (this._requiredMessage != null) {
				if (this._requiredMessage.isLiteralText()) {
					try {
												
						java.lang.String __requiredMessage = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._requiredMessage.getExpressionString(), 
											java.lang.String.class);
					
												comp.setRequiredMessage(__requiredMessage);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("requiredMessage", this._requiredMessage);
				}
			}
					      		 			 
						if (this._switchType != null) {
				if (this._switchType.isLiteralText()) {
					try {
												
						java.lang.String __switchType = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._switchType.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSwitchType(__switchType);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("switchType", this._switchType);
				}
			}
					   		 			 
						if (this._tabClass != null) {
				if (this._tabClass.isLiteralText()) {
					try {
												
						java.lang.String __tabClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._tabClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setTabClass(__tabClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("tabClass", this._tabClass);
				}
			}
					   		 			if(null != this._tabCloseListener){
             if (!this._tabCloseListener.isLiteralText())
             {
                MethodBinding mb = new MethodBindingMethodExpressionAdaptor(this._tabCloseListener);
                ((HtmlTabPanel)component).setTabCloseListener(mb);
             }
             else
             {
                getFacesContext().getExternalContext().log("Component " + component.getClientId(getFacesContext()) + " has invalid tabCloseListener value: " + this._tabCloseListener);
             }
			}
		   		 			 
						if (this._tabListHeight != null) {
				if (this._tabListHeight.isLiteralText()) {
					try {
												
						java.lang.Integer __tabListHeight = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._tabListHeight.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setTabListHeight(__tabListHeight);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("tabListHeight", this._tabListHeight);
				}
			}
					   		 			 
						if (this._tabName2Delete != null) {
				if (this._tabName2Delete.isLiteralText()) {
					try {
												
						java.lang.String __tabName2Delete = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._tabName2Delete.getExpressionString(), 
											java.lang.String.class);
					
												comp.setTabName2Delete(__tabName2Delete);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("tabName2Delete", this._tabName2Delete);
				}
			}
					   		 			 
						if (this._tabSwitched != null) {
				if (this._tabSwitched.isLiteralText()) {
					try {
												
						Boolean __tabSwitched = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._tabSwitched.getExpressionString(), 
											Boolean.class);
					
												comp.setTabSwitched(__tabSwitched.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("tabSwitched", this._tabSwitched);
				}
			}
					     		 			setValidatorProperty(comp, this._validator);
		   		 			 
						if (this._validatorMessage != null) {
				if (this._validatorMessage.isLiteralText()) {
					try {
												
						java.lang.String __validatorMessage = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._validatorMessage.getExpressionString(), 
											java.lang.String.class);
					
												comp.setValidatorMessage(__validatorMessage);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("validatorMessage", this._validatorMessage);
				}
			}
					     		 			setValueChangeListenerProperty(comp, this._valueChangeListener);
		    		 			 
						if (this._width != null) {
				if (this._width.isLiteralText()) {
					try {
												
						java.lang.String __width = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._width.getExpressionString(), 
											java.lang.String.class);
					
												comp.setWidth(__width);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("width", this._width);
				}
			}
					     }
	
	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		// TODO Auto-generated method stub
		return "com.exadel.siperian.TabPanel";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.TabPanelRenderer";
			}

}
