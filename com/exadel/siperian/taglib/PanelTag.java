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
import com.exadel.siperian.component.html.HtmlPanel;

public class PanelTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

		// Fields
		 		 		 	  			  		  	  
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
		 * minWidth
		 * A class that defines a style for a panel header
		 */
		private ValueExpression _minWidth;
		/**
		 * A class that defines a style for a panel header
		 * Setter for minWidth
		 * @param minWidth - new value
		 */
		 public void setMinWidth( ValueExpression  __minWidth ){
			this._minWidth = __minWidth;
	     }
	  
	 	 		 		 		 		 		 		 		 		 		 		 		 		 		 	
	
    public void release()
    {
        // TODO Auto-generated method stub
        super.release();
        		 		 		 		    this._header = null;
	 		 		 		    this._minWidth = null;
	 		 		 		 		 		 		 		 		 		 		 		 		 		 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlPanel comp = (HtmlPanel) component;
   		 			 
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
					    		 			 
						if (this._minWidth != null) {
				if (this._minWidth.isLiteralText()) {
					try {
												
						java.lang.String __minWidth = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._minWidth.getExpressionString(), 
											java.lang.String.class);
					
												comp.setMinWidth(__minWidth);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("minWidth", this._minWidth);
				}
			}
					                  }
	
	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		// TODO Auto-generated method stub
		return "com.exadel.siperian.Panel";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.PanelRenderer";
			}

}
