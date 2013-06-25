/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import java.lang.Object ;
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
import com.exadel.siperian.component.html.HtmlModalPanel;

public class ModalPanelTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

		// Fields
		 	  			  		  	  
		/*
		 * attachmentFormId
		 * If panelDOMAttachment equals 'form' then check this attribute that contains of form for attachment.
		 */
		private ValueExpression _attachmentFormId;
		/**
		 * If panelDOMAttachment equals 'form' then check this attribute that contains of form for attachment.
		 * Setter for attachmentFormId
		 * @param attachmentFormId - new value
		 */
		 public void setAttachmentFormId( ValueExpression  __attachmentFormId ){
			this._attachmentFormId = __attachmentFormId;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * autosized
		 * If "true" modalPanel should be autosizeable. Default value is "false".
		 */
		private ValueExpression _autosized;
		/**
		 * If "true" modalPanel should be autosizeable. Default value is "false".
		 * Setter for autosized
		 * @param autosized - new value
		 */
		 public void setAutosized( ValueExpression  __autosized ){
			this._autosized = __autosized;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * controlsClass
		 * CSS style(s) is/are to be applied to component controls when this component
				is rendered
		 */
		private ValueExpression _controlsClass;
		/**
		 * CSS style(s) is/are to be applied to component controls when this component
				is rendered
		 * Setter for controlsClass
		 * @param controlsClass - new value
		 */
		 public void setControlsClass( ValueExpression  __controlsClass ){
			this._controlsClass = __controlsClass;
	     }
	  
	 	 		 		 		 		 	  			  		  	  
		/*
		 * headerClass
		 * CSS style(s) is/are to be applied to component header when this component
				is rendered
		 */
		private ValueExpression _headerClass;
		/**
		 * CSS style(s) is/are to be applied to component header when this component
				is rendered
		 * Setter for headerClass
		 * @param headerClass - new value
		 */
		 public void setHeaderClass( ValueExpression  __headerClass ){
			this._headerClass = __headerClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * height
		 * Attribute defines height of component. Default value is "200".
		 */
		private ValueExpression _height;
		/**
		 * Attribute defines height of component. Default value is "200".
		 * Setter for height
		 * @param height - new value
		 */
		 public void setHeight( ValueExpression  __height ){
			this._height = __height;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * isModal
		 * Defines whether to show page blocking div under modalPanel
		 */
		private ValueExpression _isModal;
		/**
		 * Defines whether to show page blocking div under modalPanel
		 * Setter for isModal
		 * @param isModal - new value
		 */
		 public void setIsModal( ValueExpression  __isModal ){
			this._isModal = __isModal;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * keepVisualState
		 * If "true" modalPanel should save state after submission. Default value is "false".
		 */
		private ValueExpression _keepVisualState;
		/**
		 * If "true" modalPanel should save state after submission. Default value is "false".
		 * Setter for keepVisualState
		 * @param keepVisualState - new value
		 */
		 public void setKeepVisualState( ValueExpression  __keepVisualState ){
			this._keepVisualState = __keepVisualState;
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
		 * left
		 * Attribute defines X position of component left-top corner. Default value is "auto".
		 */
		private ValueExpression _left;
		/**
		 * Attribute defines X position of component left-top corner. Default value is "auto".
		 * Setter for left
		 * @param left - new value
		 */
		 public void setLeft( ValueExpression  __left ){
			this._left = __left;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * minHeight
		 * Attribute defines min height of component. Default value is "10". If the value is less then 10, a "IllegalArgumentException" exception is thrown.
		 */
		private ValueExpression _minHeight;
		/**
		 * Attribute defines min height of component. Default value is "10". If the value is less then 10, a "IllegalArgumentException" exception is thrown.
		 * Setter for minHeight
		 * @param minHeight - new value
		 */
		 public void setMinHeight( ValueExpression  __minHeight ){
			this._minHeight = __minHeight;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * minWidth
		 * Attribute defines min width of component. Default value is "10".  If the value is less then 10, a "IllegalArgumentException" exception is thrown.
		 */
		private ValueExpression _minWidth;
		/**
		 * Attribute defines min width of component. Default value is "10".  If the value is less then 10, a "IllegalArgumentException" exception is thrown.
		 * Setter for minWidth
		 * @param minWidth - new value
		 */
		 public void setMinWidth( ValueExpression  __minWidth ){
			this._minWidth = __minWidth;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * moveable
		 * if "true" there is possibility to move component. Default value is "true".
		 */
		private ValueExpression _moveable;
		/**
		 * if "true" there is possibility to move component. Default value is "true".
		 * Setter for moveable
		 * @param moveable - new value
		 */
		 public void setMoveable( ValueExpression  __moveable ){
			this._moveable = __moveable;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onbeforehide
		 * Event must occurs before panel is hiding
		 */
		private ValueExpression _onbeforehide;
		/**
		 * Event must occurs before panel is hiding
		 * Setter for onbeforehide
		 * @param onbeforehide - new value
		 */
		 public void setOnbeforehide( ValueExpression  __onbeforehide ){
			this._onbeforehide = __onbeforehide;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onbeforeshow
		 * Event must occurs before panel is opening
		 */
		private ValueExpression _onbeforeshow;
		/**
		 * Event must occurs before panel is opening
		 * Setter for onbeforeshow
		 * @param onbeforeshow - new value
		 */
		 public void setOnbeforeshow( ValueExpression  __onbeforeshow ){
			this._onbeforeshow = __onbeforeshow;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onhide
		 * Event must occurs after panel closed
		 */
		private ValueExpression _onhide;
		/**
		 * Event must occurs after panel closed
		 * Setter for onhide
		 * @param onhide - new value
		 */
		 public void setOnhide( ValueExpression  __onhide ){
			this._onhide = __onhide;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmaskclick
		 * HTML: a script expression; a pointer button is clicked outside modalPanel
		 */
		private ValueExpression _onmaskclick;
		/**
		 * HTML: a script expression; a pointer button is clicked outside modalPanel
		 * Setter for onmaskclick
		 * @param onmaskclick - new value
		 */
		 public void setOnmaskclick( ValueExpression  __onmaskclick ){
			this._onmaskclick = __onmaskclick;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmaskcontextmenu
		 * JavaScript handler to be called on right click outside modalPanel
		 */
		private ValueExpression _onmaskcontextmenu;
		/**
		 * JavaScript handler to be called on right click outside modalPanel
		 * Setter for onmaskcontextmenu
		 * @param onmaskcontextmenu - new value
		 */
		 public void setOnmaskcontextmenu( ValueExpression  __onmaskcontextmenu ){
			this._onmaskcontextmenu = __onmaskcontextmenu;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmaskdblclick
		 * HTML: a script expression; a pointer button is double-clicked outside modalPanel
		 */
		private ValueExpression _onmaskdblclick;
		/**
		 * HTML: a script expression; a pointer button is double-clicked outside modalPanel
		 * Setter for onmaskdblclick
		 * @param onmaskdblclick - new value
		 */
		 public void setOnmaskdblclick( ValueExpression  __onmaskdblclick ){
			this._onmaskdblclick = __onmaskdblclick;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmaskmousedown
		 * HTML: a script expression; a pointer button is pressed down outside modalPanel
		 */
		private ValueExpression _onmaskmousedown;
		/**
		 * HTML: a script expression; a pointer button is pressed down outside modalPanel
		 * Setter for onmaskmousedown
		 * @param onmaskmousedown - new value
		 */
		 public void setOnmaskmousedown( ValueExpression  __onmaskmousedown ){
			this._onmaskmousedown = __onmaskmousedown;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmaskmousemove
		 * HTML: a script expression; a pointer button is moved outside modalPanel
		 */
		private ValueExpression _onmaskmousemove;
		/**
		 * HTML: a script expression; a pointer button is moved outside modalPanel
		 * Setter for onmaskmousemove
		 * @param onmaskmousemove - new value
		 */
		 public void setOnmaskmousemove( ValueExpression  __onmaskmousemove ){
			this._onmaskmousemove = __onmaskmousemove;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmaskmouseout
		 * HTML: a script expression; a pointer button is moved away modalPanel
		 */
		private ValueExpression _onmaskmouseout;
		/**
		 * HTML: a script expression; a pointer button is moved away modalPanel
		 * Setter for onmaskmouseout
		 * @param onmaskmouseout - new value
		 */
		 public void setOnmaskmouseout( ValueExpression  __onmaskmouseout ){
			this._onmaskmouseout = __onmaskmouseout;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmaskmouseover
		 * HTML: a script expression; a pointer button is moved onto modalPanel
		 */
		private ValueExpression _onmaskmouseover;
		/**
		 * HTML: a script expression; a pointer button is moved onto modalPanel
		 * Setter for onmaskmouseover
		 * @param onmaskmouseover - new value
		 */
		 public void setOnmaskmouseover( ValueExpression  __onmaskmouseover ){
			this._onmaskmouseover = __onmaskmouseover;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmaskmouseup
		 * HTML: a script expression; a pointer button is released outside modalPanel
		 */
		private ValueExpression _onmaskmouseup;
		/**
		 * HTML: a script expression; a pointer button is released outside modalPanel
		 * Setter for onmaskmouseup
		 * @param onmaskmouseup - new value
		 */
		 public void setOnmaskmouseup( ValueExpression  __onmaskmouseup ){
			this._onmaskmouseup = __onmaskmouseup;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onmove
		 * Event must occurs before panel is moving
		 */
		private ValueExpression _onmove;
		/**
		 * Event must occurs before panel is moving
		 * Setter for onmove
		 * @param onmove - new value
		 */
		 public void setOnmove( ValueExpression  __onmove ){
			this._onmove = __onmove;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onresize
		 * Event must occurs than panel is resizing
		 */
		private ValueExpression _onresize;
		/**
		 * Event must occurs than panel is resizing
		 * Setter for onresize
		 * @param onresize - new value
		 */
		 public void setOnresize( ValueExpression  __onresize ){
			this._onresize = __onresize;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onshow
		 * Event must occurs after panel opened
		 */
		private ValueExpression _onshow;
		/**
		 * Event must occurs after panel opened
		 * Setter for onshow
		 * @param onshow - new value
		 */
		 public void setOnshow( ValueExpression  __onshow ){
			this._onshow = __onshow;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * panelDOMAttachment
		 * Attribute defines panel DOM attachment. Possible values 'body', 'form', 'unchanged'
		 */
		private ValueExpression _panelDOMAttachment;
		/**
		 * Attribute defines panel DOM attachment. Possible values 'body', 'form', 'unchanged'
		 * Setter for panelDOMAttachment
		 * @param panelDOMAttachment - new value
		 */
		 public void setPanelDOMAttachment( ValueExpression  __panelDOMAttachment ){
			this._panelDOMAttachment = __panelDOMAttachment;
	     }
	  
	 	 		 		 		 		 	  			  		  	  
		/*
		 * resizeable
		 * if "true" there is possibility to change component size. Default value is "true".
		 */
		private ValueExpression _resizeable;
		/**
		 * if "true" there is possibility to change component size. Default value is "true".
		 * Setter for resizeable
		 * @param resizeable - new value
		 */
		 public void setResizeable( ValueExpression  __resizeable ){
			this._resizeable = __resizeable;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * shadowDepth
		 * Pop-up shadow depth for suggestion content
		 */
		private ValueExpression _shadowDepth;
		/**
		 * Pop-up shadow depth for suggestion content
		 * Setter for shadowDepth
		 * @param shadowDepth - new value
		 */
		 public void setShadowDepth( ValueExpression  __shadowDepth ){
			this._shadowDepth = __shadowDepth;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * shadowOpacity
		 * HTML CSS class attribute of element for pop-up suggestion content
		 */
		private ValueExpression _shadowOpacity;
		/**
		 * HTML CSS class attribute of element for pop-up suggestion content
		 * Setter for shadowOpacity
		 * @param shadowOpacity - new value
		 */
		 public void setShadowOpacity( ValueExpression  __shadowOpacity ){
			this._shadowOpacity = __shadowOpacity;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * showCloseButton
		 * Defines whether to show close button in modalPanel header
		 */
		private ValueExpression _showCloseButton;
		/**
		 * Defines whether to show close button in modalPanel header
		 * Setter for showCloseButton
		 * @param showCloseButton - new value
		 */
		 public void setShowCloseButton( ValueExpression  __showCloseButton ){
			this._showCloseButton = __showCloseButton;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * showWhenRendered
		 * If "true" value for this attribute makes a modal panel opened as default.
		 */
		private ValueExpression _showWhenRendered;
		/**
		 * If "true" value for this attribute makes a modal panel opened as default.
		 * Setter for showWhenRendered
		 * @param showWhenRendered - new value
		 */
		 public void setShowWhenRendered( ValueExpression  __showWhenRendered ){
			this._showWhenRendered = __showWhenRendered;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * top
		 * Attribute defines Y position of component left-top corner. Default value is "auto".
		 */
		private ValueExpression _top;
		/**
		 * Attribute defines Y position of component left-top corner. Default value is "auto".
		 * Setter for top
		 * @param top - new value
		 */
		 public void setTop( ValueExpression  __top ){
			this._top = __top;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * tridentIVEngineSelectBehavior
		 * How to handle HTML SELECT-based controls in IE 6? - "disable" - default,
				handle as usual, use disabled="true" to hide SELECT controls - "hide" - use
				visibility="hidden" to hide SELECT controls
		 */
		private ValueExpression _tridentIVEngineSelectBehavior;
		/**
		 * How to handle HTML SELECT-based controls in IE 6? - "disable" - default,
				handle as usual, use disabled="true" to hide SELECT controls - "hide" - use
				visibility="hidden" to hide SELECT controls
		 * Setter for tridentIVEngineSelectBehavior
		 * @param tridentIVEngineSelectBehavior - new value
		 */
		 public void setTridentIVEngineSelectBehavior( ValueExpression  __tridentIVEngineSelectBehavior ){
			this._tridentIVEngineSelectBehavior = __tridentIVEngineSelectBehavior;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * trimOverlayedElements
		 * Defines whether to trim or not elements inside modalPanel
		 */
		private ValueExpression _trimOverlayedElements;
		/**
		 * Defines whether to trim or not elements inside modalPanel
		 * Setter for trimOverlayedElements
		 * @param trimOverlayedElements - new value
		 */
		 public void setTrimOverlayedElements( ValueExpression  __trimOverlayedElements ){
			this._trimOverlayedElements = __trimOverlayedElements;
	     }
	  
	 	 		 		 		 		 		 		 		 		 	  			  		  	  
		/*
		 * visualOptions
		 * Defines options that were specified on the client side
		 */
		private ValueExpression _visualOptions;
		/**
		 * Defines options that were specified on the client side
		 * Setter for visualOptions
		 * @param visualOptions - new value
		 */
		 public void setVisualOptions( ValueExpression  __visualOptions ){
			this._visualOptions = __visualOptions;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * width
		 * Attribute defines width of component. Default value is "300".
		 */
		private ValueExpression _width;
		/**
		 * Attribute defines width of component. Default value is "300".
		 * Setter for width
		 * @param width - new value
		 */
		 public void setWidth( ValueExpression  __width ){
			this._width = __width;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * zindex
		 * Attribute is similar to the standard HTML attribute and can specify window. Default value is "100".
				placement relative to the content
		 */
		private ValueExpression _zindex;
		/**
		 * Attribute is similar to the standard HTML attribute and can specify window. Default value is "100".
				placement relative to the content
		 * Setter for zindex
		 * @param zindex - new value
		 */
		 public void setZindex( ValueExpression  __zindex ){
			this._zindex = __zindex;
	     }
	  
	 	 	
	
    public void release()
    {
        // TODO Auto-generated method stub
        super.release();
        		 		    this._attachmentFormId = null;
	 		 		    this._autosized = null;
	 		 		 		    this._controlsClass = null;
	 		 		 		 		 		    this._headerClass = null;
	 		 		    this._height = null;
	 		 		 		 		    this._isModal = null;
	 		 		    this._keepVisualState = null;
	 		 		    this._label = null;
	 		 		    this._left = null;
	 		 		 		 		    this._minHeight = null;
	 		 		    this._minWidth = null;
	 		 		    this._moveable = null;
	 		 		    this._onbeforehide = null;
	 		 		    this._onbeforeshow = null;
	 		 		    this._onhide = null;
	 		 		    this._onmaskclick = null;
	 		 		    this._onmaskcontextmenu = null;
	 		 		    this._onmaskdblclick = null;
	 		 		    this._onmaskmousedown = null;
	 		 		    this._onmaskmousemove = null;
	 		 		    this._onmaskmouseout = null;
	 		 		    this._onmaskmouseover = null;
	 		 		    this._onmaskmouseup = null;
	 		 		    this._onmove = null;
	 		 		    this._onresize = null;
	 		 		    this._onshow = null;
	 		 		    this._panelDOMAttachment = null;
	 		 		 		 		 		    this._resizeable = null;
	 		 		    this._shadowDepth = null;
	 		 		    this._shadowOpacity = null;
	 		 		 		    this._showCloseButton = null;
	 		 		    this._showWhenRendered = null;
	 		 		 		 		    this._top = null;
	 		 		    this._tridentIVEngineSelectBehavior = null;
	 		 		    this._trimOverlayedElements = null;
	 		 		 		 		 		 		 		 		 		    this._visualOptions = null;
	 		 		    this._width = null;
	 		 		    this._zindex = null;
	 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlModalPanel comp = (HtmlModalPanel) component;
 		 			 
						if (this._attachmentFormId != null) {
				if (this._attachmentFormId.isLiteralText()) {
					try {
												
						java.lang.String __attachmentFormId = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._attachmentFormId.getExpressionString(), 
											java.lang.String.class);
					
												comp.setAttachmentFormId(__attachmentFormId);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("attachmentFormId", this._attachmentFormId);
				}
			}
					   		 			 
						if (this._autosized != null) {
				if (this._autosized.isLiteralText()) {
					try {
												
						Boolean __autosized = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._autosized.getExpressionString(), 
											Boolean.class);
					
												comp.setAutosized(__autosized.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("autosized", this._autosized);
				}
			}
					    		 			 
						if (this._controlsClass != null) {
				if (this._controlsClass.isLiteralText()) {
					try {
												
						java.lang.String __controlsClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._controlsClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setControlsClass(__controlsClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("controlsClass", this._controlsClass);
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
					   		 			 
						if (this._height != null) {
				if (this._height.isLiteralText()) {
					try {
												
						Integer __height = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._height.getExpressionString(), 
											Integer.class);
					
												comp.setHeight(__height.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("height", this._height);
				}
			}
					     		 			 
						if (this._isModal != null) {
				if (this._isModal.isLiteralText()) {
					try {
												
						Boolean __isModal = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._isModal.getExpressionString(), 
											Boolean.class);
					
												comp.setIsModal(__isModal.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("isModal", this._isModal);
				}
			}
					   		 			 
						if (this._keepVisualState != null) {
				if (this._keepVisualState.isLiteralText()) {
					try {
												
						Boolean __keepVisualState = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._keepVisualState.getExpressionString(), 
											Boolean.class);
					
												comp.setKeepVisualState(__keepVisualState.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("keepVisualState", this._keepVisualState);
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
					   		 			 
						if (this._left != null) {
				if (this._left.isLiteralText()) {
					try {
												
						java.lang.String __left = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._left.getExpressionString(), 
											java.lang.String.class);
					
												comp.setLeft(__left);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("left", this._left);
				}
			}
					     		 			 
						if (this._minHeight != null) {
				if (this._minHeight.isLiteralText()) {
					try {
												
						Integer __minHeight = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._minHeight.getExpressionString(), 
											Integer.class);
					
												comp.setMinHeight(__minHeight.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("minHeight", this._minHeight);
				}
			}
					   		 			 
						if (this._minWidth != null) {
				if (this._minWidth.isLiteralText()) {
					try {
												
						Integer __minWidth = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._minWidth.getExpressionString(), 
											Integer.class);
					
												comp.setMinWidth(__minWidth.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("minWidth", this._minWidth);
				}
			}
					   		 			 
						if (this._moveable != null) {
				if (this._moveable.isLiteralText()) {
					try {
												
						Boolean __moveable = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._moveable.getExpressionString(), 
											Boolean.class);
					
												comp.setMoveable(__moveable.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("moveable", this._moveable);
				}
			}
					   		 			 
						if (this._onbeforehide != null) {
				if (this._onbeforehide.isLiteralText()) {
					try {
												
						java.lang.String __onbeforehide = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onbeforehide.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnbeforehide(__onbeforehide);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onbeforehide", this._onbeforehide);
				}
			}
					   		 			 
						if (this._onbeforeshow != null) {
				if (this._onbeforeshow.isLiteralText()) {
					try {
												
						java.lang.String __onbeforeshow = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onbeforeshow.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnbeforeshow(__onbeforeshow);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onbeforeshow", this._onbeforeshow);
				}
			}
					   		 			 
						if (this._onhide != null) {
				if (this._onhide.isLiteralText()) {
					try {
												
						java.lang.String __onhide = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onhide.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnhide(__onhide);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onhide", this._onhide);
				}
			}
					   		 			 
						if (this._onmaskclick != null) {
				if (this._onmaskclick.isLiteralText()) {
					try {
												
						java.lang.String __onmaskclick = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmaskclick.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmaskclick(__onmaskclick);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmaskclick", this._onmaskclick);
				}
			}
					   		 			 
						if (this._onmaskcontextmenu != null) {
				if (this._onmaskcontextmenu.isLiteralText()) {
					try {
												
						java.lang.String __onmaskcontextmenu = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmaskcontextmenu.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmaskcontextmenu(__onmaskcontextmenu);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmaskcontextmenu", this._onmaskcontextmenu);
				}
			}
					   		 			 
						if (this._onmaskdblclick != null) {
				if (this._onmaskdblclick.isLiteralText()) {
					try {
												
						java.lang.String __onmaskdblclick = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmaskdblclick.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmaskdblclick(__onmaskdblclick);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmaskdblclick", this._onmaskdblclick);
				}
			}
					   		 			 
						if (this._onmaskmousedown != null) {
				if (this._onmaskmousedown.isLiteralText()) {
					try {
												
						java.lang.String __onmaskmousedown = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmaskmousedown.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmaskmousedown(__onmaskmousedown);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmaskmousedown", this._onmaskmousedown);
				}
			}
					   		 			 
						if (this._onmaskmousemove != null) {
				if (this._onmaskmousemove.isLiteralText()) {
					try {
												
						java.lang.String __onmaskmousemove = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmaskmousemove.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmaskmousemove(__onmaskmousemove);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmaskmousemove", this._onmaskmousemove);
				}
			}
					   		 			 
						if (this._onmaskmouseout != null) {
				if (this._onmaskmouseout.isLiteralText()) {
					try {
												
						java.lang.String __onmaskmouseout = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmaskmouseout.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmaskmouseout(__onmaskmouseout);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmaskmouseout", this._onmaskmouseout);
				}
			}
					   		 			 
						if (this._onmaskmouseover != null) {
				if (this._onmaskmouseover.isLiteralText()) {
					try {
												
						java.lang.String __onmaskmouseover = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmaskmouseover.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmaskmouseover(__onmaskmouseover);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmaskmouseover", this._onmaskmouseover);
				}
			}
					   		 			 
						if (this._onmaskmouseup != null) {
				if (this._onmaskmouseup.isLiteralText()) {
					try {
												
						java.lang.String __onmaskmouseup = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmaskmouseup.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmaskmouseup(__onmaskmouseup);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmaskmouseup", this._onmaskmouseup);
				}
			}
					   		 			 
						if (this._onmove != null) {
				if (this._onmove.isLiteralText()) {
					try {
												
						java.lang.String __onmove = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onmove.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnmove(__onmove);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onmove", this._onmove);
				}
			}
					   		 			 
						if (this._onresize != null) {
				if (this._onresize.isLiteralText()) {
					try {
												
						java.lang.String __onresize = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onresize.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnresize(__onresize);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onresize", this._onresize);
				}
			}
					   		 			 
						if (this._onshow != null) {
				if (this._onshow.isLiteralText()) {
					try {
												
						java.lang.String __onshow = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onshow.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnshow(__onshow);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onshow", this._onshow);
				}
			}
					   		 			 
						if (this._panelDOMAttachment != null) {
				if (this._panelDOMAttachment.isLiteralText()) {
					try {
												
						java.lang.String __panelDOMAttachment = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._panelDOMAttachment.getExpressionString(), 
											java.lang.String.class);
					
												comp.setPanelDOMAttachment(__panelDOMAttachment);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("panelDOMAttachment", this._panelDOMAttachment);
				}
			}
					      		 			 
						if (this._resizeable != null) {
				if (this._resizeable.isLiteralText()) {
					try {
												
						Boolean __resizeable = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._resizeable.getExpressionString(), 
											Boolean.class);
					
												comp.setResizeable(__resizeable.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("resizeable", this._resizeable);
				}
			}
					   		 			 
						if (this._shadowDepth != null) {
				if (this._shadowDepth.isLiteralText()) {
					try {
												
						java.lang.String __shadowDepth = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._shadowDepth.getExpressionString(), 
											java.lang.String.class);
					
												comp.setShadowDepth(__shadowDepth);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("shadowDepth", this._shadowDepth);
				}
			}
					   		 			 
						if (this._shadowOpacity != null) {
				if (this._shadowOpacity.isLiteralText()) {
					try {
												
						java.lang.String __shadowOpacity = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._shadowOpacity.getExpressionString(), 
											java.lang.String.class);
					
												comp.setShadowOpacity(__shadowOpacity);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("shadowOpacity", this._shadowOpacity);
				}
			}
					    		 			 
						if (this._showCloseButton != null) {
				if (this._showCloseButton.isLiteralText()) {
					try {
												
						Boolean __showCloseButton = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._showCloseButton.getExpressionString(), 
											Boolean.class);
					
												comp.setShowCloseButton(__showCloseButton.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("showCloseButton", this._showCloseButton);
				}
			}
					   		 			 
						if (this._showWhenRendered != null) {
				if (this._showWhenRendered.isLiteralText()) {
					try {
												
						Boolean __showWhenRendered = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._showWhenRendered.getExpressionString(), 
											Boolean.class);
					
												comp.setShowWhenRendered(__showWhenRendered.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("showWhenRendered", this._showWhenRendered);
				}
			}
					     		 			 
						if (this._top != null) {
				if (this._top.isLiteralText()) {
					try {
												
						java.lang.String __top = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._top.getExpressionString(), 
											java.lang.String.class);
					
												comp.setTop(__top);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("top", this._top);
				}
			}
					   		 			 
						if (this._tridentIVEngineSelectBehavior != null) {
				if (this._tridentIVEngineSelectBehavior.isLiteralText()) {
					try {
												
						java.lang.String __tridentIVEngineSelectBehavior = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._tridentIVEngineSelectBehavior.getExpressionString(), 
											java.lang.String.class);
					
												comp.setTridentIVEngineSelectBehavior(__tridentIVEngineSelectBehavior);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("tridentIVEngineSelectBehavior", this._tridentIVEngineSelectBehavior);
				}
			}
					   		 			 
						if (this._trimOverlayedElements != null) {
				if (this._trimOverlayedElements.isLiteralText()) {
					try {
												
						Boolean __trimOverlayedElements = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._trimOverlayedElements.getExpressionString(), 
											Boolean.class);
					
												comp.setTrimOverlayedElements(__trimOverlayedElements.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("trimOverlayedElements", this._trimOverlayedElements);
				}
			}
					          		 			 
						if (this._visualOptions != null) {
				if (this._visualOptions.isLiteralText()) {
					try {
												
						java.lang.Object __visualOptions = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._visualOptions.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setVisualOptions(__visualOptions);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("visualOptions", this._visualOptions);
				}
			}
					   		 			 
						if (this._width != null) {
				if (this._width.isLiteralText()) {
					try {
												
						Integer __width = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._width.getExpressionString(), 
											Integer.class);
					
												comp.setWidth(__width.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("width", this._width);
				}
			}
					   		 			 
						if (this._zindex != null) {
				if (this._zindex.isLiteralText()) {
					try {
												
						Integer __zindex = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._zindex.getExpressionString(), 
											Integer.class);
					
												comp.setZindex(__zindex.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("zindex", this._zindex);
				}
			}
					     }
	
	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		// TODO Auto-generated method stub
		return "com.exadel.siperian.ModalPanel";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.ModalPanelRenderer";
			}

}
