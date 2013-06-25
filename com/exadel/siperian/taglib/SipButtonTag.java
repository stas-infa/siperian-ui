/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import java.lang.Object ;
import java.lang.String ;
import org.ajax4jsf.webapp.taglib.HtmlComponentTagBase ;
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
import com.exadel.siperian.component.html.HtmlSipButton;

public class SipButtonTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

		// Fields
		 	  			  		  	  
		/*
		 * accesskey
		 * This attribute assigns an access key to an element. An access key is a single character from the document character set. Note: Authors should consider the input method of the expected reader when specifying an accesskey
		 */
		private ValueExpression _accesskey;
		/**
		 * This attribute assigns an access key to an element. An access key is a single character from the document character set. Note: Authors should consider the input method of the expected reader when specifying an accesskey
		 * Setter for accesskey
		 * @param accesskey - new value
		 */
		 public void setAccesskey( ValueExpression  __accesskey ){
			this._accesskey = __accesskey;
	     }
	  
	 	 		 	  	  	  
		/*
		 * action
		 * MethodBinding pointing at the application action to be invoked,
            if this UIComponent is activated by you, during the Apply
            Request Values or Invoke Application phase of the request
            processing lifecycle, depending on the value of the immediate
            property
		 */
		private MethodExpression _action;
		/**
		 * MethodBinding pointing at the application action to be invoked,
            if this UIComponent is activated by you, during the Apply
            Request Values or Invoke Application phase of the request
            processing lifecycle, depending on the value of the immediate
            property
		 * Setter for action
		 * @param action - new value
		 */
		 public void setAction( MethodExpression  __action ){
			this._action = __action;
	     }
	  
	 	 		 		 	  	  	  
		/*
		 * actionListener
		 * MethodBinding pointing at method accepting  an ActionEvent with return type void
		 */
		private MethodExpression _actionListener;
		/**
		 * MethodBinding pointing at method accepting  an ActionEvent with return type void
		 * Setter for actionListener
		 * @param actionListener - new value
		 */
		 public void setActionListener( MethodExpression  __actionListener ){
			this._actionListener = __actionListener;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * charset
		 * The character encoding of a resource designated by this hyperlink
		 */
		private ValueExpression _charset;
		/**
		 * The character encoding of a resource designated by this hyperlink
		 * Setter for charset
		 * @param charset - new value
		 */
		 public void setCharset( ValueExpression  __charset ){
			this._charset = __charset;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * coords
		 * This attribute specifies the position and shape on the screen. The number and order of values depends on the shape being defined. Possible combinations:
            
            * rect: left-x, top-y, right-x, bottom-y.
            * circle: center-x, center-y, radius. Note. When the radius value is percentage value, user agents should calculate the final radius value based on the associated object's width and height. The radius should be the smaller value of the two.
            * poly: x1, y1, x2, y2, ..., xN, yN. The first x and y coordinate pair and the last should be the same to close the polygon. When these coordinate values are not the same, user agents should infer an additional coordinate pair to close the polygon.
            
            Coordinates are relative to the top, left corner of the object. All values are lengths. All values are separated by commas
		 */
		private ValueExpression _coords;
		/**
		 * This attribute specifies the position and shape on the screen. The number and order of values depends on the shape being defined. Possible combinations:
            
            * rect: left-x, top-y, right-x, bottom-y.
            * circle: center-x, center-y, radius. Note. When the radius value is percentage value, user agents should calculate the final radius value based on the associated object's width and height. The radius should be the smaller value of the two.
            * poly: x1, y1, x2, y2, ..., xN, yN. The first x and y coordinate pair and the last should be the same to close the polygon. When these coordinate values are not the same, user agents should infer an additional coordinate pair to close the polygon.
            
            Coordinates are relative to the top, left corner of the object. All values are lengths. All values are separated by commas
		 * Setter for coords
		 * @param coords - new value
		 */
		 public void setCoords( ValueExpression  __coords ){
			this._coords = __coords;
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
		 * hreflang
		 * Base language of a resource specified with the href attribute; hreflang may only be used with href
		 */
		private ValueExpression _hreflang;
		/**
		 * Base language of a resource specified with the href attribute; hreflang may only be used with href
		 * Setter for hreflang
		 * @param hreflang - new value
		 */
		 public void setHreflang( ValueExpression  __hreflang ){
			this._hreflang = __hreflang;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * icon
		 * Absolute or relative URL of the image to be displayed for this button.
		 */
		private ValueExpression _icon;
		/**
		 * Absolute or relative URL of the image to be displayed for this button.
		 * Setter for icon
		 * @param icon - new value
		 */
		 public void setIcon( ValueExpression  __icon ){
			this._icon = __icon;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * iconAlign
		 * the align on the button icon right/left
		 */
		private ValueExpression _iconAlign;
		/**
		 * the align on the button icon right/left
		 * Setter for iconAlign
		 * @param iconAlign - new value
		 */
		 public void setIconAlign( ValueExpression  __iconAlign ){
			this._iconAlign = __iconAlign;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * immediate
		 * True means, that the default ActionListener should be executed
            immediately (i.e. during Apply Request Values phase of the
            request processing lifecycle), rather than waiting until the
            Invoke Application phase
		 */
		private ValueExpression _immediate;
		/**
		 * True means, that the default ActionListener should be executed
            immediately (i.e. during Apply Request Values phase of the
            request processing lifecycle), rather than waiting until the
            Invoke Application phase
		 * Setter for immediate
		 * @param immediate - new value
		 */
		 public void setImmediate( ValueExpression  __immediate ){
			this._immediate = __immediate;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * onblur
		 * JavaScript code. The onblur event occurs when an element loses focus either by the pointing device or by tabbing navigation. It may be used with the same elements as onfocus
		 */
		private ValueExpression _onblur;
		/**
		 * JavaScript code. The onblur event occurs when an element loses focus either by the pointing device or by tabbing navigation. It may be used with the same elements as onfocus
		 * Setter for onblur
		 * @param onblur - new value
		 */
		 public void setOnblur( ValueExpression  __onblur ){
			this._onblur = __onblur;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * onfocus
		 * JavaScript code. The onfocus event occurs when an element gets focus
		 */
		private ValueExpression _onfocus;
		/**
		 * JavaScript code. The onfocus event occurs when an element gets focus
		 * Setter for onfocus
		 * @param onfocus - new value
		 */
		 public void setOnfocus( ValueExpression  __onfocus ){
			this._onfocus = __onfocus;
	     }
	  
	 	 		 		 		 		 		 		 		 		 		 	  			  		  	  
		/*
		 * rel
		 * The relationship from the current document to the anchor specified by this hyperlink. The value of this attribute is a space-separated list of link types
		 */
		private ValueExpression _rel;
		/**
		 * The relationship from the current document to the anchor specified by this hyperlink. The value of this attribute is a space-separated list of link types
		 * Setter for rel
		 * @param rel - new value
		 */
		 public void setRel( ValueExpression  __rel ){
			this._rel = __rel;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * rev
		 * A reverse link from the anchor specified by this hyperlink to the current document. The value of this attribute is a space-separated list of link types
		 */
		private ValueExpression _rev;
		/**
		 * A reverse link from the anchor specified by this hyperlink to the current document. The value of this attribute is a space-separated list of link types
		 * Setter for rev
		 * @param rev - new value
		 */
		 public void setRev( ValueExpression  __rev ){
			this._rev = __rev;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * shape
		 * This attribute specifies the shape of a region. The possible values are "default", "rect", "circle" and "poly".
		 */
		private ValueExpression _shape;
		/**
		 * This attribute specifies the shape of a region. The possible values are "default", "rect", "circle" and "poly".
		 * Setter for shape
		 * @param shape - new value
		 */
		 public void setShape( ValueExpression  __shape ){
			this._shape = __shape;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * tabindex
		 * This attribute specifies the position of the current element in the tabbing order for the current document. This value must be a number between 0 and 32767. User agents should ignore leading zeros
		 */
		private ValueExpression _tabindex;
		/**
		 * This attribute specifies the position of the current element in the tabbing order for the current document. This value must be a number between 0 and 32767. User agents should ignore leading zeros
		 * Setter for tabindex
		 * @param tabindex - new value
		 */
		 public void setTabindex( ValueExpression  __tabindex ){
			this._tabindex = __tabindex;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * target
		 * This attribute specifies the name of a frame where a document is to be opened.
            
            By assigning a name to a frame via the name attribute, authors can refer to it as the "target" of links defined by other elements
		 */
		private ValueExpression _target;
		/**
		 * This attribute specifies the name of a frame where a document is to be opened.
            
            By assigning a name to a frame via the name attribute, authors can refer to it as the "target" of links defined by other elements
		 * Setter for target
		 * @param target - new value
		 */
		 public void setTarget( ValueExpression  __target ){
			this._target = __target;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * type
		 * The content type of the resource designated by this hyperlink
		 */
		private ValueExpression _type;
		/**
		 * The content type of the resource designated by this hyperlink
		 * Setter for type
		 * @param type - new value
		 */
		 public void setType( ValueExpression  __type ){
			this._type = __type;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * value
		 * The current value for this component
		 */
		private ValueExpression _value;
		/**
		 * The current value for this component
		 * Setter for value
		 * @param value - new value
		 */
		 public void setValue( ValueExpression  __value ){
			this._value = __value;
	     }
	  
	 	 	
	
    public void release()
    {
        // TODO Auto-generated method stub
        super.release();
        		 		    this._accesskey = null;
	 		 		    this._action = null;
	 		 		 		    this._actionListener = null;
	 		 		 		 		    this._charset = null;
	 		 		    this._coords = null;
	 		 		 		    this._disabled = null;
	 		 		 		    this._hreflang = null;
	 		 		    this._icon = null;
	 		 		    this._iconAlign = null;
	 		 		 		    this._immediate = null;
	 		 		 		    this._onblur = null;
	 		 		 		 		    this._onfocus = null;
	 		 		 		 		 		 		 		 		 		 		    this._rel = null;
	 		 		 		    this._rev = null;
	 		 		    this._shape = null;
	 		 		 		 		    this._tabindex = null;
	 		 		    this._target = null;
	 		 		 		    this._type = null;
	 		 		    this._value = null;
	 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlSipButton comp = (HtmlSipButton) component;
 		 			 
						if (this._accesskey != null) {
				if (this._accesskey.isLiteralText()) {
					try {
												
						java.lang.String __accesskey = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._accesskey.getExpressionString(), 
											java.lang.String.class);
					
												comp.setAccesskey(__accesskey);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("accesskey", this._accesskey);
				}
			}
					   		 			setActionProperty(comp, this._action);
		    		 			setActionListenerProperty(comp, this._actionListener);
		     		 			 
						if (this._charset != null) {
				if (this._charset.isLiteralText()) {
					try {
												
						java.lang.String __charset = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._charset.getExpressionString(), 
											java.lang.String.class);
					
												comp.setCharset(__charset);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("charset", this._charset);
				}
			}
					   		 			 
						if (this._coords != null) {
				if (this._coords.isLiteralText()) {
					try {
												
						java.lang.String __coords = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._coords.getExpressionString(), 
											java.lang.String.class);
					
												comp.setCoords(__coords);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("coords", this._coords);
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
					    		 			 
						if (this._hreflang != null) {
				if (this._hreflang.isLiteralText()) {
					try {
												
						java.lang.String __hreflang = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._hreflang.getExpressionString(), 
											java.lang.String.class);
					
												comp.setHreflang(__hreflang);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("hreflang", this._hreflang);
				}
			}
					   		 			 
						if (this._icon != null) {
				if (this._icon.isLiteralText()) {
					try {
												
						java.lang.String __icon = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._icon.getExpressionString(), 
											java.lang.String.class);
					
												comp.setIcon(__icon);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("icon", this._icon);
				}
			}
					   		 			 
						if (this._iconAlign != null) {
				if (this._iconAlign.isLiteralText()) {
					try {
												
						java.lang.String __iconAlign = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._iconAlign.getExpressionString(), 
											java.lang.String.class);
					
												comp.setIconAlign(__iconAlign);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("iconAlign", this._iconAlign);
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
					    		 			 
						if (this._onblur != null) {
				if (this._onblur.isLiteralText()) {
					try {
												
						java.lang.String __onblur = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onblur.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnblur(__onblur);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onblur", this._onblur);
				}
			}
					     		 			 
						if (this._onfocus != null) {
				if (this._onfocus.isLiteralText()) {
					try {
												
						java.lang.String __onfocus = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onfocus.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnfocus(__onfocus);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onfocus", this._onfocus);
				}
			}
					           		 			 
						if (this._rel != null) {
				if (this._rel.isLiteralText()) {
					try {
												
						java.lang.String __rel = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._rel.getExpressionString(), 
											java.lang.String.class);
					
												comp.setRel(__rel);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("rel", this._rel);
				}
			}
					    		 			 
						if (this._rev != null) {
				if (this._rev.isLiteralText()) {
					try {
												
						java.lang.String __rev = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._rev.getExpressionString(), 
											java.lang.String.class);
					
												comp.setRev(__rev);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("rev", this._rev);
				}
			}
					   		 			 
						if (this._shape != null) {
				if (this._shape.isLiteralText()) {
					try {
												
						java.lang.String __shape = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._shape.getExpressionString(), 
											java.lang.String.class);
					
												comp.setShape(__shape);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("shape", this._shape);
				}
			}
					     		 			 
						if (this._tabindex != null) {
				if (this._tabindex.isLiteralText()) {
					try {
												
						java.lang.String __tabindex = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._tabindex.getExpressionString(), 
											java.lang.String.class);
					
												comp.setTabindex(__tabindex);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("tabindex", this._tabindex);
				}
			}
					   		 			 
						if (this._target != null) {
				if (this._target.isLiteralText()) {
					try {
												
						java.lang.String __target = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._target.getExpressionString(), 
											java.lang.String.class);
					
												comp.setTarget(__target);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("target", this._target);
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
					     }
	
	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		// TODO Auto-generated method stub
		return "com.exadel.siperian.HtmlSipButton";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.HtmlSipButtonRenderer";
			}

}
