/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import org.ajax4jsf.webapp.taglib.UIComponentTagBase ;
import java.lang.Boolean ;
import java.lang.String ;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.el.MethodExpression;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.el.ValueExpression;
import org.richfaces.webapp.taglib.MethodBindingMethodExpressionAdaptor;
import org.richfaces.webapp.taglib.ValueBindingValueExpressionAdaptor;
import com.exadel.siperian.component.html.HtmlToolBarGroup;

public class ToolBarGroupTag extends org.ajax4jsf.webapp.taglib.UIComponentTagBase {

		// Fields
		 		 	  			  		  	  
		/*
		 * disabled
		 * Defines if group should be disabled
		 */
		private ValueExpression _disabled;
		/**
		 * Defines if group should be disabled
		 * Setter for disabled
		 * @param disabled - new value
		 */
		 public void setDisabled( ValueExpression  __disabled ){
			this._disabled = __disabled;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * highlight
		 * Defines if group should be highlighted
		 */
		private ValueExpression _highlight;
		/**
		 * Defines if group should be highlighted
		 * Setter for highlight
		 * @param highlight - new value
		 */
		 public void setHighlight( ValueExpression  __highlight ){
			this._highlight = __highlight;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * itemSeparator
		 * A separator for the items in a group. Possible
	    	values are "none", "line", "square", "disc" and "grid" Default value is "none".
		 */
		private ValueExpression _itemSeparator;
		/**
		 * A separator for the items in a group. Possible
	    	values are "none", "line", "square", "disc" and "grid" Default value is "none".
		 * Setter for itemSeparator
		 * @param itemSeparator - new value
		 */
		 public void setItemSeparator( ValueExpression  __itemSeparator ){
			this._itemSeparator = __itemSeparator;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * location
		 * A location of a group on a tool bar. Possible values are "left" and "right". Default value is "left".
		 */
		private ValueExpression _location;
		/**
		 * A location of a group on a tool bar. Possible values are "left" and "right". Default value is "left".
		 * Setter for location
		 * @param location - new value
		 */
		 public void setLocation( ValueExpression  __location ){
			this._location = __location;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onclick
		 * The clientside script method to be called when the element is clicked
		 */
		private ValueExpression _onclick;
		/**
		 * The clientside script method to be called when the element is clicked
		 * Setter for onclick
		 * @param onclick - new value
		 */
		 public void setOnclick( ValueExpression  __onclick ){
			this._onclick = __onclick;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * ondblclick
		 * The client side script method to be called when the element is double-clicked
		 */
		private ValueExpression _ondblclick;
		/**
		 * The client side script method to be called when the element is double-clicked
		 * Setter for ondblclick
		 * @param ondblclick - new value
		 */
		 public void setOndblclick( ValueExpression  __ondblclick ){
			this._ondblclick = __ondblclick;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onitemclick
		 * HTML: a script expression; a pointer button is clicked on an item
		 */
		private ValueExpression _onitemclick;
		/**
		 * HTML: a script expression; a pointer button is clicked on an item
		 * Setter for onitemclick
		 * @param onitemclick - new value
		 */
		 public void setOnitemclick( ValueExpression  __onitemclick ){
			this._onitemclick = __onitemclick;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onitemdblclick
		 * HTML: a script expression; a pointer button is double-clicked on an item
		 */
		private ValueExpression _onitemdblclick;
		/**
		 * HTML: a script expression; a pointer button is double-clicked on an item
		 * Setter for onitemdblclick
		 * @param onitemdblclick - new value
		 */
		 public void setOnitemdblclick( ValueExpression  __onitemdblclick ){
			this._onitemdblclick = __onitemdblclick;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onitemkeydown
		 * HTML: a script expression; a key is pressed down on an item
		 */
		private ValueExpression _onitemkeydown;
		/**
		 * HTML: a script expression; a key is pressed down on an item
		 * Setter for onitemkeydown
		 * @param onitemkeydown - new value
		 */
		 public void setOnitemkeydown( ValueExpression  __onitemkeydown ){
			this._onitemkeydown = __onitemkeydown;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onitemkeypress
		 * HTML: a script expression; a key is pressed and released on an item
		 */
		private ValueExpression _onitemkeypress;
		/**
		 * HTML: a script expression; a key is pressed and released on an item
		 * Setter for onitemkeypress
		 * @param onitemkeypress - new value
		 */
		 public void setOnitemkeypress( ValueExpression  __onitemkeypress ){
			this._onitemkeypress = __onitemkeypress;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onitemkeyup
		 * HTML: a script expression; a key is released on an item
		 */
		private ValueExpression _onitemkeyup;
		/**
		 * HTML: a script expression; a key is released on an item
		 * Setter for onitemkeyup
		 * @param onitemkeyup - new value
		 */
		 public void setOnitemkeyup( ValueExpression  __onitemkeyup ){
			this._onitemkeyup = __onitemkeyup;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onitemmousedown
		 * HTML: script expression; a pointer button is pressed down on an item
		 */
		private ValueExpression _onitemmousedown;
		/**
		 * HTML: script expression; a pointer button is pressed down on an item
		 * Setter for onitemmousedown
		 * @param onitemmousedown - new value
		 */
		 public void setOnitemmousedown( ValueExpression  __onitemmousedown ){
			this._onitemmousedown = __onitemmousedown;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onitemmousemove
		 * HTML: a script expression; a pointer is moved on an item
		 */
		private ValueExpression _onitemmousemove;
		/**
		 * HTML: a script expression; a pointer is moved on an item
		 * Setter for onitemmousemove
		 * @param onitemmousemove - new value
		 */
		 public void setOnitemmousemove( ValueExpression  __onitemmousemove ){
			this._onitemmousemove = __onitemmousemove;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onitemmouseout
		 * HTML: a script expression; a pointer is moved away from an item
		 */
		private ValueExpression _onitemmouseout;
		/**
		 * HTML: a script expression; a pointer is moved away from an item
		 * Setter for onitemmouseout
		 * @param onitemmouseout - new value
		 */
		 public void setOnitemmouseout( ValueExpression  __onitemmouseout ){
			this._onitemmouseout = __onitemmouseout;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onitemmouseover
		 * HTML: a script expression; a pointer is moved onto an item
		 */
		private ValueExpression _onitemmouseover;
		/**
		 * HTML: a script expression; a pointer is moved onto an item
		 * Setter for onitemmouseover
		 * @param onitemmouseover - new value
		 */
		 public void setOnitemmouseover( ValueExpression  __onitemmouseover ){
			this._onitemmouseover = __onitemmouseover;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onitemmouseup
		 * HTML: script expression; a pointer button is released on an item
		 */
		private ValueExpression _onitemmouseup;
		/**
		 * HTML: script expression; a pointer button is released on an item
		 * Setter for onitemmouseup
		 * @param onitemmouseup - new value
		 */
		 public void setOnitemmouseup( ValueExpression  __onitemmouseup ){
			this._onitemmouseup = __onitemmouseup;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onkeydown
		 * The client side script method to be called when a key is pressed down over the element
		 */
		private ValueExpression _onkeydown;
		/**
		 * The client side script method to be called when a key is pressed down over the element
		 * Setter for onkeydown
		 * @param onkeydown - new value
		 */
		 public void setOnkeydown( ValueExpression  __onkeydown ){
			this._onkeydown = __onkeydown;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onkeypress
		 * The client side script method to be called when a key is pressed over the element and released
		 */
		private ValueExpression _onkeypress;
		/**
		 * The client side script method to be called when a key is pressed over the element and released
		 * Setter for onkeypress
		 * @param onkeypress - new value
		 */
		 public void setOnkeypress( ValueExpression  __onkeypress ){
			this._onkeypress = __onkeypress;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onkeyup
		 * The client side script method to be called when a key is released
		 */
		private ValueExpression _onkeyup;
		/**
		 * The client side script method to be called when a key is released
		 * Setter for onkeyup
		 * @param onkeyup - new value
		 */
		 public void setOnkeyup( ValueExpression  __onkeyup ){
			this._onkeyup = __onkeyup;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmousedown
		 * The client side script method to be called when a mouse button is pressed down over the element
		 */
		private ValueExpression _onmousedown;
		/**
		 * The client side script method to be called when a mouse button is pressed down over the element
		 * Setter for onmousedown
		 * @param onmousedown - new value
		 */
		 public void setOnmousedown( ValueExpression  __onmousedown ){
			this._onmousedown = __onmousedown;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmousemove
		 * The client side script method to be called when a pointer is moved within the element
		 */
		private ValueExpression _onmousemove;
		/**
		 * The client side script method to be called when a pointer is moved within the element
		 * Setter for onmousemove
		 * @param onmousemove - new value
		 */
		 public void setOnmousemove( ValueExpression  __onmousemove ){
			this._onmousemove = __onmousemove;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmouseout
		 * The client side script method to be called when a pointer is moved away from the element
		 */
		private ValueExpression _onmouseout;
		/**
		 * The client side script method to be called when a pointer is moved away from the element
		 * Setter for onmouseout
		 * @param onmouseout - new value
		 */
		 public void setOnmouseout( ValueExpression  __onmouseout ){
			this._onmouseout = __onmouseout;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmouseover
		 * The client side script method to be called when a pointer is moved onto the element
		 */
		private ValueExpression _onmouseover;
		/**
		 * The client side script method to be called when a pointer is moved onto the element
		 * Setter for onmouseover
		 * @param onmouseover - new value
		 */
		 public void setOnmouseover( ValueExpression  __onmouseover ){
			this._onmouseover = __onmouseover;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmouseup
		 * The client side script method to be called when a mouse button is released
		 */
		private ValueExpression _onmouseup;
		/**
		 * The client side script method to be called when a mouse button is released
		 * Setter for onmouseup
		 * @param onmouseup - new value
		 */
		 public void setOnmouseup( ValueExpression  __onmouseup ){
			this._onmouseup = __onmouseup;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * separatorClass
		 * A CSS class to be applied to tool bar group separators.
		 */
		private ValueExpression _separatorClass;
		/**
		 * A CSS class to be applied to tool bar group separators.
		 * Setter for separatorClass
		 * @param separatorClass - new value
		 */
		 public void setSeparatorClass( ValueExpression  __separatorClass ){
			this._separatorClass = __separatorClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * style
		 * CSS style(s) is/are to be applied when this component is rendered
		 */
		private ValueExpression _style;
		/**
		 * CSS style(s) is/are to be applied when this component is rendered
		 * Setter for style
		 * @param style - new value
		 */
		 public void setStyle( ValueExpression  __style ){
			this._style = __style;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * styleClass
		 * Corresponds to the HTML class attribute
		 */
		private ValueExpression _styleClass;
		/**
		 * Corresponds to the HTML class attribute
		 * Setter for styleClass
		 * @param styleClass - new value
		 */
		 public void setStyleClass( ValueExpression  __styleClass ){
			this._styleClass = __styleClass;
	     }
	  
	 	 		 	
	
    public void release()
    {
        // TODO Auto-generated method stub
        super.release();
        		 		 		    this._disabled = null;
	 		 		 		    this._highlight = null;
	 		 		 		    this._itemSeparator = null;
	 		 		    this._location = null;
	 		 		    this._onclick = null;
	 		 		    this._ondblclick = null;
	 		 		    this._onitemclick = null;
	 		 		    this._onitemdblclick = null;
	 		 		    this._onitemkeydown = null;
	 		 		    this._onitemkeypress = null;
	 		 		    this._onitemkeyup = null;
	 		 		    this._onitemmousedown = null;
	 		 		    this._onitemmousemove = null;
	 		 		    this._onitemmouseout = null;
	 		 		    this._onitemmouseover = null;
	 		 		    this._onitemmouseup = null;
	 		 		    this._onkeydown = null;
	 		 		    this._onkeypress = null;
	 		 		    this._onkeyup = null;
	 		 		    this._onmousedown = null;
	 		 		    this._onmousemove = null;
	 		 		    this._onmouseout = null;
	 		 		    this._onmouseover = null;
	 		 		    this._onmouseup = null;
	 		 		 		 		    this._separatorClass = null;
	 		 		    this._style = null;
	 		 		    this._styleClass = null;
	 		 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlToolBarGroup comp = (HtmlToolBarGroup) component;
  		 			 
						if (this._disabled != null) {
				if (this._disabled.isLiteralText()) {
					try {
												
						java.lang.Boolean __disabled = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._disabled.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setDisabled(__disabled);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("disabled", this._disabled);
				}
			}
					    		 			 
						if (this._highlight != null) {
				if (this._highlight.isLiteralText()) {
					try {
												
						java.lang.Boolean __highlight = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._highlight.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setHighlight(__highlight);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("highlight", this._highlight);
				}
			}
					    		 			 
						if (this._itemSeparator != null) {
				if (this._itemSeparator.isLiteralText()) {
					try {
												
						java.lang.String __itemSeparator = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._itemSeparator.getExpressionString(), 
											java.lang.String.class);
					
												comp.setItemSeparator(__itemSeparator);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("itemSeparator", this._itemSeparator);
				}
			}
					   		 			 
						if (this._location != null) {
				if (this._location.isLiteralText()) {
					try {
												
						java.lang.String __location = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._location.getExpressionString(), 
											java.lang.String.class);
					
												comp.setLocation(__location);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("location", this._location);
				}
			}
					   		 			 
						if (this._onclick != null) {
				if (this._onclick.isLiteralText()) {
					try {
												
						java.lang.String __onclick = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onclick.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnclick(__onclick);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onclick", this._onclick);
				}
			}
					   		 			 
						if (this._ondblclick != null) {
				if (this._ondblclick.isLiteralText()) {
					try {
												
						java.lang.String __ondblclick = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._ondblclick.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOndblclick(__ondblclick);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("ondblclick", this._ondblclick);
				}
			}
					   		 			 
						if (this._onitemclick != null) {
				if (this._onitemclick.isLiteralText()) {
					try {
												
						java.lang.String __onitemclick = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onitemclick.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnitemclick(__onitemclick);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onitemclick", this._onitemclick);
				}
			}
					   		 			 
						if (this._onitemdblclick != null) {
				if (this._onitemdblclick.isLiteralText()) {
					try {
												
						java.lang.String __onitemdblclick = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onitemdblclick.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnitemdblclick(__onitemdblclick);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onitemdblclick", this._onitemdblclick);
				}
			}
					   		 			 
						if (this._onitemkeydown != null) {
				if (this._onitemkeydown.isLiteralText()) {
					try {
												
						java.lang.String __onitemkeydown = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onitemkeydown.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnitemkeydown(__onitemkeydown);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onitemkeydown", this._onitemkeydown);
				}
			}
					   		 			 
						if (this._onitemkeypress != null) {
				if (this._onitemkeypress.isLiteralText()) {
					try {
												
						java.lang.String __onitemkeypress = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onitemkeypress.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnitemkeypress(__onitemkeypress);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onitemkeypress", this._onitemkeypress);
				}
			}
					   		 			 
						if (this._onitemkeyup != null) {
				if (this._onitemkeyup.isLiteralText()) {
					try {
												
						java.lang.String __onitemkeyup = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onitemkeyup.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnitemkeyup(__onitemkeyup);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onitemkeyup", this._onitemkeyup);
				}
			}
					   		 			 
						if (this._onitemmousedown != null) {
				if (this._onitemmousedown.isLiteralText()) {
					try {
												
						java.lang.String __onitemmousedown = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onitemmousedown.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnitemmousedown(__onitemmousedown);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onitemmousedown", this._onitemmousedown);
				}
			}
					   		 			 
						if (this._onitemmousemove != null) {
				if (this._onitemmousemove.isLiteralText()) {
					try {
												
						java.lang.String __onitemmousemove = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onitemmousemove.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnitemmousemove(__onitemmousemove);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onitemmousemove", this._onitemmousemove);
				}
			}
					   		 			 
						if (this._onitemmouseout != null) {
				if (this._onitemmouseout.isLiteralText()) {
					try {
												
						java.lang.String __onitemmouseout = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onitemmouseout.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnitemmouseout(__onitemmouseout);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onitemmouseout", this._onitemmouseout);
				}
			}
					   		 			 
						if (this._onitemmouseover != null) {
				if (this._onitemmouseover.isLiteralText()) {
					try {
												
						java.lang.String __onitemmouseover = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onitemmouseover.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnitemmouseover(__onitemmouseover);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onitemmouseover", this._onitemmouseover);
				}
			}
					   		 			 
						if (this._onitemmouseup != null) {
				if (this._onitemmouseup.isLiteralText()) {
					try {
												
						java.lang.String __onitemmouseup = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onitemmouseup.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnitemmouseup(__onitemmouseup);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onitemmouseup", this._onitemmouseup);
				}
			}
					   		 			 
						if (this._onkeydown != null) {
				if (this._onkeydown.isLiteralText()) {
					try {
												
						java.lang.String __onkeydown = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onkeydown.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnkeydown(__onkeydown);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onkeydown", this._onkeydown);
				}
			}
					   		 			 
						if (this._onkeypress != null) {
				if (this._onkeypress.isLiteralText()) {
					try {
												
						java.lang.String __onkeypress = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onkeypress.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnkeypress(__onkeypress);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onkeypress", this._onkeypress);
				}
			}
					   		 			 
						if (this._onkeyup != null) {
				if (this._onkeyup.isLiteralText()) {
					try {
												
						java.lang.String __onkeyup = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onkeyup.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnkeyup(__onkeyup);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onkeyup", this._onkeyup);
				}
			}
					   		 			 
						if (this._onmousedown != null) {
				if (this._onmousedown.isLiteralText()) {
					try {
												
						java.lang.String __onmousedown = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmousedown.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmousedown(__onmousedown);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmousedown", this._onmousedown);
				}
			}
					   		 			 
						if (this._onmousemove != null) {
				if (this._onmousemove.isLiteralText()) {
					try {
												
						java.lang.String __onmousemove = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmousemove.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmousemove(__onmousemove);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmousemove", this._onmousemove);
				}
			}
					   		 			 
						if (this._onmouseout != null) {
				if (this._onmouseout.isLiteralText()) {
					try {
												
						java.lang.String __onmouseout = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmouseout.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmouseout(__onmouseout);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmouseout", this._onmouseout);
				}
			}
					   		 			 
						if (this._onmouseover != null) {
				if (this._onmouseover.isLiteralText()) {
					try {
												
						java.lang.String __onmouseover = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmouseover.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmouseover(__onmouseover);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmouseover", this._onmouseover);
				}
			}
					   		 			 
						if (this._onmouseup != null) {
				if (this._onmouseup.isLiteralText()) {
					try {
												
						java.lang.String __onmouseup = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmouseup.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmouseup(__onmouseup);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmouseup", this._onmouseup);
				}
			}
					     		 			 
						if (this._separatorClass != null) {
				if (this._separatorClass.isLiteralText()) {
					try {
												
						java.lang.String __separatorClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._separatorClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSeparatorClass(__separatorClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("separatorClass", this._separatorClass);
				}
			}
					   		 			 
						if (this._style != null) {
				if (this._style.isLiteralText()) {
					try {
												
						java.lang.String __style = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._style.getExpressionString(), 
											java.lang.String.class);
					
												comp.setStyle(__style);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("style", this._style);
				}
			}
					   		 			 
						if (this._styleClass != null) {
				if (this._styleClass.isLiteralText()) {
					try {
												
						java.lang.String __styleClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._styleClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setStyleClass(__styleClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("styleClass", this._styleClass);
				}
			}
					      }
	
	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		// TODO Auto-generated method stub
		return "com.exadel.siperian.ToolBarGroup";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.ToolBarGroupRenderer";
			}

}
