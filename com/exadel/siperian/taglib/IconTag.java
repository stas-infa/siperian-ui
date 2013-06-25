/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import javax.el.ValueExpression ;
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
import com.exadel.siperian.component.html.HtmlIcon;

public class IconTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

		// Fields
		 		 	  			  		  	  
		/*
		 * direction
		 * direction
		 */
		private ValueExpression _direction;
		/**
		 * direction
		 * Setter for direction
		 * @param direction - new value
		 */
		 public void setDirection( ValueExpression  __direction ){
			this._direction = __direction;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * disabled
		 * disabled
		 */
		private ValueExpression _disabled;
		/**
		 * disabled
		 * Setter for disabled
		 * @param disabled - new value
		 */
		 public void setDisabled( ValueExpression  __disabled ){
			this._disabled = __disabled;
	     }
	  
	 	 		 		 		 		 		 		 	  			  		  	  
		/*
		 * type
		 * type
		 */
		private ValueExpression _type;
		/**
		 * type
		 * Setter for type
		 * @param type - new value
		 */
		 public void setType( ValueExpression  __type ){
			this._type = __type;
	     }
	  
	 	 	
	
    public void release()
    {
        // TODO Auto-generated method stub
        super.release();
        		 		 		    this._direction = null;
	 		 		    this._disabled = null;
	 		 		 		 		 		 		 		    this._type = null;
	 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlIcon comp = (HtmlIcon) component;
  		 			 
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
					        		 			 
						if (this._type != null) {
				if (this._type.isLiteralText()) {
					try {
												
						java.lang.String __type = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._type.getExpressionString(), 
											java.lang.String.class);
					
												comp.setType(__type);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("type", this._type);
				}
			}
					     }
	
	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		// TODO Auto-generated method stub
		return "com.siperian.Icon";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.IconRenderer";
			}

}
