/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import java.lang.String ;
import java.lang.Integer ;
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
import com.exadel.siperian.component.html.HtmlDoublePanel;

public class DoublePanelTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

		// Fields
		 		 	  			  		  	  
		/*
		 * defaultHeight
		 * Default panel height
		 */
		private ValueExpression _defaultHeight;
		/**
		 * Default panel height
		 * Setter for defaultHeight
		 * @param defaultHeight - new value
		 */
		 public void setDefaultHeight( ValueExpression  __defaultHeight ){
			this._defaultHeight = __defaultHeight;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * header
		 * header
		 */
		private ValueExpression _header;
		/**
		 * header
		 * Setter for header
		 * @param header - new value
		 */
		 public void setHeader( ValueExpression  __header ){
			this._header = __header;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * leftPanelWidth
		 * Css width of left panel
		 */
		private ValueExpression _leftPanelWidth;
		/**
		 * Css width of left panel
		 * Setter for leftPanelWidth
		 * @param leftPanelWidth - new value
		 */
		 public void setLeftPanelWidth( ValueExpression  __leftPanelWidth ){
			this._leftPanelWidth = __leftPanelWidth;
	     }
	  
	 	 		 		 		 		 		 		 		 		 		 		 		 		 	  			  		  	  
		/*
		 * rightPanelMinWidth
		 * Css min width of right panel
		 */
		private ValueExpression _rightPanelMinWidth;
		/**
		 * Css min width of right panel
		 * Setter for rightPanelMinWidth
		 * @param rightPanelMinWidth - new value
		 */
		 public void setRightPanelMinWidth( ValueExpression  __rightPanelMinWidth ){
			this._rightPanelMinWidth = __rightPanelMinWidth;
	     }
	  
	 	 		 		 	
	
    public void release()
    {
        // TODO Auto-generated method stub
        super.release();
        		 		 		    this._defaultHeight = null;
	 		 		 		    this._header = null;
	 		 		 		    this._leftPanelWidth = null;
	 		 		 		 		 		 		 		 		 		 		 		 		 		    this._rightPanelMinWidth = null;
	 		 		 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlDoublePanel comp = (HtmlDoublePanel) component;
  		 			 
						if (this._defaultHeight != null) {
				if (this._defaultHeight.isLiteralText()) {
					try {
												
						java.lang.Integer __defaultHeight = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._defaultHeight.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setDefaultHeight(__defaultHeight);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("defaultHeight", this._defaultHeight);
				}
			}
					    		 			 
						if (this._header != null) {
				if (this._header.isLiteralText()) {
					try {
												
						java.lang.String __header = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._header.getExpressionString(), 
											java.lang.String.class);
					
												comp.setHeader(__header);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("header", this._header);
				}
			}
					    		 			 
						if (this._leftPanelWidth != null) {
				if (this._leftPanelWidth.isLiteralText()) {
					try {
												
						java.lang.Integer __leftPanelWidth = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._leftPanelWidth.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setLeftPanelWidth(__leftPanelWidth);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("leftPanelWidth", this._leftPanelWidth);
				}
			}
					              		 			 
						if (this._rightPanelMinWidth != null) {
				if (this._rightPanelMinWidth.isLiteralText()) {
					try {
												
						java.lang.Integer __rightPanelMinWidth = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._rightPanelMinWidth.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setRightPanelMinWidth(__rightPanelMinWidth);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("rightPanelMinWidth", this._rightPanelMinWidth);
				}
			}
					       }
	
	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		// TODO Auto-generated method stub
		return "com.siperian.richfaces.DoublePanel";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.renderkit.DoublePanelRendererBase";
			}

}
