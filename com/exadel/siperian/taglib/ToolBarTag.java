/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
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
import com.exadel.siperian.component.html.HtmlToolBar;

public class ToolBarTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

		// Fields
		 		 	  			  		  	  
		/*
		 * contentClass
		 * A CSS style is to be applied to each element of tool bar content. 
    		Use this style, for example, to setup parameters of the font.
		 */
		private ValueExpression _contentClass;
		/**
		 * A CSS style is to be applied to each element of tool bar content. 
    		Use this style, for example, to setup parameters of the font.
		 * Setter for contentClass
		 * @param contentClass - new value
		 */
		 public void setContentClass( ValueExpression  __contentClass ){
			this._contentClass = __contentClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * contentStyle
		 * A CSS style is to be applied to each element of tool bar content.
		 */
		private ValueExpression _contentStyle;
		/**
		 * A CSS style is to be applied to each element of tool bar content.
		 * Setter for contentStyle
		 * @param contentStyle - new value
		 */
		 public void setContentStyle( ValueExpression  __contentStyle ){
			this._contentStyle = __contentStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * disabled
		 * 
		 */
		private ValueExpression _disabled;
		/**
		 * 
		 * Setter for disabled
		 * @param disabled - new value
		 */
		 public void setDisabled( ValueExpression  __disabled ){
			this._disabled = __disabled;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * height
		 * A height of a bar in pixels. If a height is not defined,
    		a bar height depends of the "headerFontSize" skin parameter.
		 */
		private ValueExpression _height;
		/**
		 * A height of a bar in pixels. If a height is not defined,
    		a bar height depends of the "headerFontSize" skin parameter.
		 * Setter for height
		 * @param height - new value
		 */
		 public void setHeight( ValueExpression  __height ){
			this._height = __height;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * itemSeparator
		 * A separator between items on a bar. Possible values
    		are "none", "line", "square", "disc" and "grid". Default value is "none".
		 */
		private ValueExpression _itemSeparator;
		/**
		 * A separator between items on a bar. Possible values
    		are "none", "line", "square", "disc" and "grid". Default value is "none".
		 * Setter for itemSeparator
		 * @param itemSeparator - new value
		 */
		 public void setItemSeparator( ValueExpression  __itemSeparator ){
			this._itemSeparator = __itemSeparator;
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
		 * separatorClass
		 * A CSS class to be applied to tool bar separators.
		 */
		private ValueExpression _separatorClass;
		/**
		 * A CSS class to be applied to tool bar separators.
		 * Setter for separatorClass
		 * @param separatorClass - new value
		 */
		 public void setSeparatorClass( ValueExpression  __separatorClass ){
			this._separatorClass = __separatorClass;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * width
		 * A width of a bar that can be defined in pixels or as percentage. Default value is &amp;quot;100%&amp;quot;.
		 */
		private ValueExpression _width;
		/**
		 * A width of a bar that can be defined in pixels or as percentage. Default value is &amp;quot;100%&amp;quot;.
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
        		 		 		    this._contentClass = null;
	 		 		    this._contentStyle = null;
	 		 		    this._disabled = null;
	 		 		 		    this._height = null;
	 		 		 		    this._itemSeparator = null;
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
	 		 		 		 		 		 		 		 		 		 		 		    this._separatorClass = null;
	 		 		 		 		    this._width = null;
	 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlToolBar comp = (HtmlToolBar) component;
  		 			 
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
		return "com.exadel.siperian.ToolBar";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.ToolBarRenderer";
			}

}
