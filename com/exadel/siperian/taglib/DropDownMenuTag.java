/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import java.lang.Object ;
import java.lang.Integer ;
import java.lang.String ;
import org.ajax4jsf.webapp.taglib.HtmlComponentTagBase ;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.el.MethodExpression;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.el.ValueExpression;
import org.richfaces.webapp.taglib.MethodBindingMethodExpressionAdaptor;
import org.richfaces.webapp.taglib.ValueBindingValueExpressionAdaptor;
import com.exadel.siperian.component.html.HtmlDropDownMenu;

public class DropDownMenuTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

		// Fields
		 		 	  			  		  	  
		/*
		 * direction
		 * Defines direction of the popup list to appear. 
                Possible values are "top-right", "top-right", "top-left", "bottom-right", "bottom-left", "auto".
                Default value is "auto".
		 */
		private ValueExpression _direction;
		/**
		 * Defines direction of the popup list to appear. 
                Possible values are "top-right", "top-right", "top-left", "bottom-right", "bottom-left", "auto".
                Default value is "auto".
		 * Setter for direction
		 * @param direction - new value
		 */
		 public void setDirection( ValueExpression  __direction ){
			this._direction = __direction;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * disabled
		 * Attribute 'disabled' provides possibility to make the whole menu disabled if its value equals to "true". Default value is "false"
		 */
		private ValueExpression _disabled;
		/**
		 * Attribute 'disabled' provides possibility to make the whole menu disabled if its value equals to "true". Default value is "false"
		 * Setter for disabled
		 * @param disabled - new value
		 */
		 public void setDisabled( ValueExpression  __disabled ){
			this._disabled = __disabled;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * disabledItemClass
		 * Space-separated list of CSS style class(es) that are be applied to disabled item of this component
		 */
		private ValueExpression _disabledItemClass;
		/**
		 * Space-separated list of CSS style class(es) that are be applied to disabled item of this component
		 * Setter for disabledItemClass
		 * @param disabledItemClass - new value
		 */
		 public void setDisabledItemClass( ValueExpression  __disabledItemClass ){
			this._disabledItemClass = __disabledItemClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * disabledItemStyle
		 * CSS style(s) is/are to be applied to disabled item when this component is rendered.
		 */
		private ValueExpression _disabledItemStyle;
		/**
		 * CSS style(s) is/are to be applied to disabled item when this component is rendered.
		 * Setter for disabledItemStyle
		 * @param disabledItemStyle - new value
		 */
		 public void setDisabledItemStyle( ValueExpression  __disabledItemStyle ){
			this._disabledItemStyle = __disabledItemStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * disabledLabelClass
		 * Space-separated list of CSS style class(es) applied to dropdown menu label when it is disabled.
		 */
		private ValueExpression _disabledLabelClass;
		/**
		 * Space-separated list of CSS style class(es) applied to dropdown menu label when it is disabled.
		 * Setter for disabledLabelClass
		 * @param disabledLabelClass - new value
		 */
		 public void setDisabledLabelClass( ValueExpression  __disabledLabelClass ){
			this._disabledLabelClass = __disabledLabelClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * event
		 * Defines the event on the representation element that triggers
                the menu's appearance.
		 */
		private ValueExpression _event;
		/**
		 * Defines the event on the representation element that triggers
                the menu's appearance.
		 * Setter for event
		 * @param event - new value
		 */
		 public void setEvent( ValueExpression  __event ){
			this._event = __event;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * hideDelay
		 * Delay between losing focus and menu closing. Default value is "800".
		 */
		private ValueExpression _hideDelay;
		/**
		 * Delay between losing focus and menu closing. Default value is "800".
		 * Setter for hideDelay
		 * @param hideDelay - new value
		 */
		 public void setHideDelay( ValueExpression  __hideDelay ){
			this._hideDelay = __hideDelay;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * horizontalOffset
		 * Sets the horizontal offset between popup list and label element. Default value is "0".
                conjunction point
		 */
		private ValueExpression _horizontalOffset;
		/**
		 * Sets the horizontal offset between popup list and label element. Default value is "0".
                conjunction point
		 * Setter for horizontalOffset
		 * @param horizontalOffset - new value
		 */
		 public void setHorizontalOffset( ValueExpression  __horizontalOffset ){
			this._horizontalOffset = __horizontalOffset;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * itemClass
		 * Space-separated list of CSS style class(es) that are be applied to item of this component
		 */
		private ValueExpression _itemClass;
		/**
		 * Space-separated list of CSS style class(es) that are be applied to item of this component
		 * Setter for itemClass
		 * @param itemClass - new value
		 */
		 public void setItemClass( ValueExpression  __itemClass ){
			this._itemClass = __itemClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * itemStyle
		 * CSS style(s) is/are to be applied to item when this component is rendered.
		 */
		private ValueExpression _itemStyle;
		/**
		 * CSS style(s) is/are to be applied to item when this component is rendered.
		 * Setter for itemStyle
		 * @param itemStyle - new value
		 */
		 public void setItemStyle( ValueExpression  __itemStyle ){
			this._itemStyle = __itemStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * jointPoint
		 * Sets the corner of the label for the pop-up to be connected with.
                Possible values are "tr", "tl", "bl", "br", "bottom-left", "auto".
                Default value is "auto".
                "tr" stands for top-right.
		 */
		private ValueExpression _jointPoint;
		/**
		 * Sets the corner of the label for the pop-up to be connected with.
                Possible values are "tr", "tl", "bl", "br", "bottom-left", "auto".
                Default value is "auto".
                "tr" stands for top-right.
		 * Setter for jointPoint
		 * @param jointPoint - new value
		 */
		 public void setJointPoint( ValueExpression  __jointPoint ){
			this._jointPoint = __jointPoint;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * labelClass
		 * Space-separated list of CSS style class(es) applied to dropdown menu label in it normal (neither selected nor disabled) sate.
		 */
		private ValueExpression _labelClass;
		/**
		 * Space-separated list of CSS style class(es) applied to dropdown menu label in it normal (neither selected nor disabled) sate.
		 * Setter for labelClass
		 * @param labelClass - new value
		 */
		 public void setLabelClass( ValueExpression  __labelClass ){
			this._labelClass = __labelClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * oncollapse
		 * HTML: script expression; a menu is collapsed.
		 */
		private ValueExpression _oncollapse;
		/**
		 * HTML: script expression; a menu is collapsed.
		 * Setter for oncollapse
		 * @param oncollapse - new value
		 */
		 public void setOncollapse( ValueExpression  __oncollapse ){
			this._oncollapse = __oncollapse;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onexpand
		 * HTML: script expression; a menu is expanded.
		 */
		private ValueExpression _onexpand;
		/**
		 * HTML: script expression; a menu is expanded.
		 * Setter for onexpand
		 * @param onexpand - new value
		 */
		 public void setOnexpand( ValueExpression  __onexpand ){
			this._onexpand = __onexpand;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * ongroupactivate
		 * HTML: script expression; some group was activated.
		 */
		private ValueExpression _ongroupactivate;
		/**
		 * HTML: script expression; some group was activated.
		 * Setter for ongroupactivate
		 * @param ongroupactivate - new value
		 */
		 public void setOngroupactivate( ValueExpression  __ongroupactivate ){
			this._ongroupactivate = __ongroupactivate;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onitemselect
		 * HTML: script expression; some item was selected.
		 */
		private ValueExpression _onitemselect;
		/**
		 * HTML: script expression; some item was selected.
		 * Setter for onitemselect
		 * @param onitemselect - new value
		 */
		 public void setOnitemselect( ValueExpression  __onitemselect ){
			this._onitemselect = __onitemselect;
	     }
	  
	 	 		 		 		 		 	  			  		  	  
		/*
		 * popupWidth
		 * Sets minimal width for  all  lists that will appear.
		 */
		private ValueExpression _popupWidth;
		/**
		 * Sets minimal width for  all  lists that will appear.
		 * Setter for popupWidth
		 * @param popupWidth - new value
		 */
		 public void setPopupWidth( ValueExpression  __popupWidth ){
			this._popupWidth = __popupWidth;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * selectItemClass
		 * Space-separated list of CSS style class(es) that are be applied to selected item of this component.
		 */
		private ValueExpression _selectItemClass;
		/**
		 * Space-separated list of CSS style class(es) that are be applied to selected item of this component.
		 * Setter for selectItemClass
		 * @param selectItemClass - new value
		 */
		 public void setSelectItemClass( ValueExpression  __selectItemClass ){
			this._selectItemClass = __selectItemClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * selectItemStyle
		 * CSS style(s) is/are to be applied to selected item when this component is rendered.
		 */
		private ValueExpression _selectItemStyle;
		/**
		 * CSS style(s) is/are to be applied to selected item when this component is rendered.
		 * Setter for selectItemStyle
		 * @param selectItemStyle - new value
		 */
		 public void setSelectItemStyle( ValueExpression  __selectItemStyle ){
			this._selectItemStyle = __selectItemStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * selectedLabelClass
		 * Space-separated list of CSS style class(es) applied to dropdown menu label when it is selected.
		 */
		private ValueExpression _selectedLabelClass;
		/**
		 * Space-separated list of CSS style class(es) applied to dropdown menu label when it is selected.
		 * Setter for selectedLabelClass
		 * @param selectedLabelClass - new value
		 */
		 public void setSelectedLabelClass( ValueExpression  __selectedLabelClass ){
			this._selectedLabelClass = __selectedLabelClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * showDelay
		 * Delay between event and menu showing. Default value is "50".
		 */
		private ValueExpression _showDelay;
		/**
		 * Delay between event and menu showing. Default value is "50".
		 * Setter for showDelay
		 * @param showDelay - new value
		 */
		 public void setShowDelay( ValueExpression  __showDelay ){
			this._showDelay = __showDelay;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * submitMode
		 * Sets the submission mode for all menu items of the menu except
                ones where this attribute redefined.
                Possible values are "ajax","server","none". Default value is "sever".
		 */
		private ValueExpression _submitMode;
		/**
		 * Sets the submission mode for all menu items of the menu except
                ones where this attribute redefined.
                Possible values are "ajax","server","none". Default value is "sever".
		 * Setter for submitMode
		 * @param submitMode - new value
		 */
		 public void setSubmitMode( ValueExpression  __submitMode ){
			this._submitMode = __submitMode;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * value
		 * Defines representation text for Label used for menu calls.
		 */
		private ValueExpression _value;
		/**
		 * Defines representation text for Label used for menu calls.
		 * Setter for value
		 * @param value - new value
		 */
		 public void setValue( ValueExpression  __value ){
			this._value = __value;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * verticalOffset
		 * Sets the vertical offset between popup list and label element. Default value is "0".
                conjunction point
		 */
		private ValueExpression _verticalOffset;
		/**
		 * Sets the vertical offset between popup list and label element. Default value is "0".
                conjunction point
		 * Setter for verticalOffset
		 * @param verticalOffset - new value
		 */
		 public void setVerticalOffset( ValueExpression  __verticalOffset ){
			this._verticalOffset = __verticalOffset;
	     }
	  
	 	 	
	
    public void release()
    {
        // TODO Auto-generated method stub
        super.release();
        		 		 		    this._direction = null;
	 		 		    this._disabled = null;
	 		 		    this._disabledItemClass = null;
	 		 		    this._disabledItemStyle = null;
	 		 		    this._disabledLabelClass = null;
	 		 		    this._event = null;
	 		 		 		    this._hideDelay = null;
	 		 		    this._horizontalOffset = null;
	 		 		 		    this._itemClass = null;
	 		 		    this._itemStyle = null;
	 		 		    this._jointPoint = null;
	 		 		    this._labelClass = null;
	 		 		    this._oncollapse = null;
	 		 		    this._onexpand = null;
	 		 		    this._ongroupactivate = null;
	 		 		    this._onitemselect = null;
	 		 		 		 		 		    this._popupWidth = null;
	 		 		 		    this._selectItemClass = null;
	 		 		    this._selectItemStyle = null;
	 		 		    this._selectedLabelClass = null;
	 		 		    this._showDelay = null;
	 		 		 		 		    this._submitMode = null;
	 		 		    this._value = null;
	 		 		    this._verticalOffset = null;
	 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlDropDownMenu comp = (HtmlDropDownMenu) component;
  		 			 
						if (this._direction != null) {
				if (this._direction.isLiteralText()) {
					try {
												
						java.lang.String __direction = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._direction.getExpressionString(), 
											java.lang.String.class);
					
												comp.setDirection(__direction);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("direction", this._direction);
				}
			}
					   		 			 
						if (this._disabled != null) {
				if (this._disabled.isLiteralText()) {
					try {
												
						Boolean __disabled = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._disabled.getExpressionString(), 
											Boolean.class);
					
												comp.setDisabled(__disabled.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("disabled", this._disabled);
				}
			}
					   		 			 
						if (this._disabledItemClass != null) {
				if (this._disabledItemClass.isLiteralText()) {
					try {
												
						java.lang.String __disabledItemClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._disabledItemClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setDisabledItemClass(__disabledItemClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("disabledItemClass", this._disabledItemClass);
				}
			}
					   		 			 
						if (this._disabledItemStyle != null) {
				if (this._disabledItemStyle.isLiteralText()) {
					try {
												
						java.lang.String __disabledItemStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._disabledItemStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setDisabledItemStyle(__disabledItemStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("disabledItemStyle", this._disabledItemStyle);
				}
			}
					   		 			 
						if (this._disabledLabelClass != null) {
				if (this._disabledLabelClass.isLiteralText()) {
					try {
												
						java.lang.String __disabledLabelClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._disabledLabelClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setDisabledLabelClass(__disabledLabelClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("disabledLabelClass", this._disabledLabelClass);
				}
			}
					   		 			 
						if (this._event != null) {
				if (this._event.isLiteralText()) {
					try {
												
						java.lang.String __event = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._event.getExpressionString(), 
											java.lang.String.class);
					
												comp.setEvent(__event);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("event", this._event);
				}
			}
					    		 			 
						if (this._hideDelay != null) {
				if (this._hideDelay.isLiteralText()) {
					try {
												
						java.lang.Integer __hideDelay = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._hideDelay.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setHideDelay(__hideDelay);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("hideDelay", this._hideDelay);
				}
			}
					   		 			 
						if (this._horizontalOffset != null) {
				if (this._horizontalOffset.isLiteralText()) {
					try {
												
						Integer __horizontalOffset = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._horizontalOffset.getExpressionString(), 
											Integer.class);
					
												comp.setHorizontalOffset(__horizontalOffset.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("horizontalOffset", this._horizontalOffset);
				}
			}
					    		 			 
						if (this._itemClass != null) {
				if (this._itemClass.isLiteralText()) {
					try {
												
						java.lang.String __itemClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._itemClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setItemClass(__itemClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("itemClass", this._itemClass);
				}
			}
					   		 			 
						if (this._itemStyle != null) {
				if (this._itemStyle.isLiteralText()) {
					try {
												
						java.lang.String __itemStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._itemStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setItemStyle(__itemStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("itemStyle", this._itemStyle);
				}
			}
					   		 			 
						if (this._jointPoint != null) {
				if (this._jointPoint.isLiteralText()) {
					try {
												
						java.lang.String __jointPoint = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._jointPoint.getExpressionString(), 
											java.lang.String.class);
					
												comp.setJointPoint(__jointPoint);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("jointPoint", this._jointPoint);
				}
			}
					   		 			 
						if (this._labelClass != null) {
				if (this._labelClass.isLiteralText()) {
					try {
												
						java.lang.String __labelClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._labelClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setLabelClass(__labelClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("labelClass", this._labelClass);
				}
			}
					   		 			 
						if (this._oncollapse != null) {
				if (this._oncollapse.isLiteralText()) {
					try {
												
						java.lang.String __oncollapse = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._oncollapse.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOncollapse(__oncollapse);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("oncollapse", this._oncollapse);
				}
			}
					   		 			 
						if (this._onexpand != null) {
				if (this._onexpand.isLiteralText()) {
					try {
												
						java.lang.String __onexpand = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onexpand.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnexpand(__onexpand);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onexpand", this._onexpand);
				}
			}
					   		 			 
						if (this._ongroupactivate != null) {
				if (this._ongroupactivate.isLiteralText()) {
					try {
												
						java.lang.String __ongroupactivate = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._ongroupactivate.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOngroupactivate(__ongroupactivate);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("ongroupactivate", this._ongroupactivate);
				}
			}
					   		 			 
						if (this._onitemselect != null) {
				if (this._onitemselect.isLiteralText()) {
					try {
												
						java.lang.String __onitemselect = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onitemselect.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnitemselect(__onitemselect);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onitemselect", this._onitemselect);
				}
			}
					      		 			 
						if (this._popupWidth != null) {
				if (this._popupWidth.isLiteralText()) {
					try {
												
						java.lang.String __popupWidth = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._popupWidth.getExpressionString(), 
											java.lang.String.class);
					
												comp.setPopupWidth(__popupWidth);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("popupWidth", this._popupWidth);
				}
			}
					    		 			 
						if (this._selectItemClass != null) {
				if (this._selectItemClass.isLiteralText()) {
					try {
												
						java.lang.String __selectItemClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._selectItemClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSelectItemClass(__selectItemClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("selectItemClass", this._selectItemClass);
				}
			}
					   		 			 
						if (this._selectItemStyle != null) {
				if (this._selectItemStyle.isLiteralText()) {
					try {
												
						java.lang.String __selectItemStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._selectItemStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSelectItemStyle(__selectItemStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("selectItemStyle", this._selectItemStyle);
				}
			}
					   		 			 
						if (this._selectedLabelClass != null) {
				if (this._selectedLabelClass.isLiteralText()) {
					try {
												
						java.lang.String __selectedLabelClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._selectedLabelClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSelectedLabelClass(__selectedLabelClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("selectedLabelClass", this._selectedLabelClass);
				}
			}
					   		 			 
						if (this._showDelay != null) {
				if (this._showDelay.isLiteralText()) {
					try {
												
						java.lang.Integer __showDelay = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._showDelay.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setShowDelay(__showDelay);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("showDelay", this._showDelay);
				}
			}
					     		 			 
						if (this._submitMode != null) {
				if (this._submitMode.isLiteralText()) {
					try {
												
						java.lang.String __submitMode = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._submitMode.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSubmitMode(__submitMode);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("submitMode", this._submitMode);
				}
			}
					   		 			 
						if (this._value != null) {
				if (this._value.isLiteralText()) {
					try {
												
						java.lang.Object __value = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._value.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setValue(__value);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("value", this._value);
				}
			}
					   		 			 
						if (this._verticalOffset != null) {
				if (this._verticalOffset.isLiteralText()) {
					try {
												
						Integer __verticalOffset = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._verticalOffset.getExpressionString(), 
											Integer.class);
					
												comp.setVerticalOffset(__verticalOffset.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("verticalOffset", this._verticalOffset);
				}
			}
					     }
	
	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		// TODO Auto-generated method stub
		return "com.exadel.siperian.DropDownMenu";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.DropDownMenuRenderer";
			}

}
