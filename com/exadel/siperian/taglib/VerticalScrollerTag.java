/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import java.lang.Object ;
import java.lang.String ;
import javax.el.MethodExpression ;
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
import com.exadel.siperian.component.html.HtmlVerticalScroller;

public class VerticalScrollerTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

		// Fields
		 	  	  	  
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
		 * ajaxSingle
		 * Boolean attribute which provides possibility to limit JSF tree processing(decoding, conversion/validation, value applying) to the component which send the request only.
		 */
		private ValueExpression _ajaxSingle;
		/**
		 * Boolean attribute which provides possibility to limit JSF tree processing(decoding, conversion/validation, value applying) to the component which send the request only.
		 * Setter for ajaxSingle
		 * @param ajaxSingle - new value
		 */
		 public void setAjaxSingle( ValueExpression  __ajaxSingle ){
			this._ajaxSingle = __ajaxSingle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * align
		 * This attribute specifies the position of the table with relatively to  the document.
			Possible values are "left","center","right ". Default value is "center".
		 */
		private ValueExpression _align;
		/**
		 * This attribute specifies the position of the table with relatively to  the document.
			Possible values are "left","center","right ". Default value is "center".
		 * Setter for align
		 * @param align - new value
		 */
		 public void setAlign( ValueExpression  __align ){
			this._align = __align;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * boundaryControls
		 * The attribute specifies the visibility of boundaryControls. 
				Possible values are: "show"  (controls are always visible ). "hide" (controls are hidden. 
				"auto" (unnecessary controls are hidden). Default value is "show".
		 */
		private ValueExpression _boundaryControls;
		/**
		 * The attribute specifies the visibility of boundaryControls. 
				Possible values are: "show"  (controls are always visible ). "hide" (controls are hidden. 
				"auto" (unnecessary controls are hidden). Default value is "show".
		 * Setter for boundaryControls
		 * @param boundaryControls - new value
		 */
		 public void setBoundaryControls( ValueExpression  __boundaryControls ){
			this._boundaryControls = __boundaryControls;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * bypassUpdates
		 * If "true", after process validations phase it skips updates of model beans on a force render response. It can be used for validating components input
		 */
		private ValueExpression _bypassUpdates;
		/**
		 * If "true", after process validations phase it skips updates of model beans on a force render response. It can be used for validating components input
		 * Setter for bypassUpdates
		 * @param bypassUpdates - new value
		 */
		 public void setBypassUpdates( ValueExpression  __bypassUpdates ){
			this._bypassUpdates = __bypassUpdates;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * data
		 * Serialized (on default with JSON) data passed on the client by a developer on AJAX request. It's accessible via "data.foo" syntax
		 */
		private ValueExpression _data;
		/**
		 * Serialized (on default with JSON) data passed on the client by a developer on AJAX request. It's accessible via "data.foo" syntax
		 * Setter for data
		 * @param data - new value
		 */
		 public void setData( ValueExpression  __data ){
			this._data = __data;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * eventsQueue
		 * Name of requests queue to avoid send next request before complete other from same event. Can be used to reduce number of requests of frequently events (key press, mouse move etc.)
		 */
		private ValueExpression _eventsQueue;
		/**
		 * Name of requests queue to avoid send next request before complete other from same event. Can be used to reduce number of requests of frequently events (key press, mouse move etc.)
		 * Setter for eventsQueue
		 * @param eventsQueue - new value
		 */
		 public void setEventsQueue( ValueExpression  __eventsQueue ){
			this._eventsQueue = __eventsQueue;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * fastControls
		 * The attribute specifies the visibility of fastControls.
				Possible values are: "show"  (controls are always visible ). "hide" (controls are hidden. 
				"auto" (unnecessary controls are hidden). Default value is "show".
		 */
		private ValueExpression _fastControls;
		/**
		 * The attribute specifies the visibility of fastControls.
				Possible values are: "show"  (controls are always visible ). "hide" (controls are hidden. 
				"auto" (unnecessary controls are hidden). Default value is "show".
		 * Setter for fastControls
		 * @param fastControls - new value
		 */
		 public void setFastControls( ValueExpression  __fastControls ){
			this._fastControls = __fastControls;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * fastStep
		 * The attribute indicates pages quantity to switch onto when fast scrolling is used. Default value is "0".
		 */
		private ValueExpression _fastStep;
		/**
		 * The attribute indicates pages quantity to switch onto when fast scrolling is used. Default value is "0".
		 * Setter for fastStep
		 * @param fastStep - new value
		 */
		 public void setFastStep( ValueExpression  __fastStep ){
			this._fastStep = __fastStep;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * focus
		 * id of element to set focus after request completed on client side
		 */
		private ValueExpression _focus;
		/**
		 * id of element to set focus after request completed on client side
		 * Setter for focus
		 * @param focus - new value
		 */
		 public void setFocus( ValueExpression  __focus ){
			this._focus = __focus;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * for
		 * ID of the table component whose data is scrollled
		 */
		private ValueExpression _for;
		/**
		 * ID of the table component whose data is scrollled
		 * Setter for for
		 * @param for - new value
		 */
		 public void setFor( ValueExpression  __for ){
			this._for = __for;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * handleValue
		 * Current handle value
		 */
		private ValueExpression _handleValue;
		/**
		 * Current handle value
		 * Setter for handleValue
		 * @param handleValue - new value
		 */
		 public void setHandleValue( ValueExpression  __handleValue ){
			this._handleValue = __handleValue;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * height
		 * Component height
		 */
		private ValueExpression _height;
		/**
		 * Component height
		 * Setter for height
		 * @param height - new value
		 */
		 public void setHeight( ValueExpression  __height ){
			this._height = __height;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * ignoreDupResponses
		 * Attribute allows to ignore an Ajax Response produced by a request if the newest 'similar' request is
in a queue already. ignoreDupResponses="true" does not cancel the request while it is processed on the server,
but just allows to avoid unnecessary updates on the client side if the response isn't actual now. Default value is "true".
		 */
		private ValueExpression _ignoreDupResponses;
		/**
		 * Attribute allows to ignore an Ajax Response produced by a request if the newest 'similar' request is
in a queue already. ignoreDupResponses="true" does not cancel the request while it is processed on the server,
but just allows to avoid unnecessary updates on the client side if the response isn't actual now. Default value is "true".
		 * Setter for ignoreDupResponses
		 * @param ignoreDupResponses - new value
		 */
		 public void setIgnoreDupResponses( ValueExpression  __ignoreDupResponses ){
			this._ignoreDupResponses = __ignoreDupResponses;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * immediate
		 * A flag indicating that this component value must be converted and validated immediately (that is, during Apply Request Values phase), rather than waiting until a Process Validations phase
		 */
		private ValueExpression _immediate;
		/**
		 * A flag indicating that this component value must be converted and validated immediately (that is, during Apply Request Values phase), rather than waiting until a Process Validations phase
		 * Setter for immediate
		 * @param immediate - new value
		 */
		 public void setImmediate( ValueExpression  __immediate ){
			this._immediate = __immediate;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * inactiveStyle
		 * Corresponds to the HTML style attribute for the inactive cell on scroller
		 */
		private ValueExpression _inactiveStyle;
		/**
		 * Corresponds to the HTML style attribute for the inactive cell on scroller
		 * Setter for inactiveStyle
		 * @param inactiveStyle - new value
		 */
		 public void setInactiveStyle( ValueExpression  __inactiveStyle ){
			this._inactiveStyle = __inactiveStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * inactiveStyleClass
		 * Corresponds to the HTML class attribute for the inactive cell on scroller
		 */
		private ValueExpression _inactiveStyleClass;
		/**
		 * Corresponds to the HTML class attribute for the inactive cell on scroller
		 * Setter for inactiveStyleClass
		 * @param inactiveStyleClass - new value
		 */
		 public void setInactiveStyleClass( ValueExpression  __inactiveStyleClass ){
			this._inactiveStyleClass = __inactiveStyleClass;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * limitToList
		 * If "true", then of all AJAX-rendered on the page components only those will be updated, 
		which ID's are passed to the "reRender" attribute of the describable component. 
		"false"-the default value-means that all components with ajaxRendered="true" will be updated.
		 */
		private ValueExpression _limitToList;
		/**
		 * If "true", then of all AJAX-rendered on the page components only those will be updated, 
		which ID's are passed to the "reRender" attribute of the describable component. 
		"false"-the default value-means that all components with ajaxRendered="true" will be updated.
		 * Setter for limitToList
		 * @param limitToList - new value
		 */
		 public void setLimitToList( ValueExpression  __limitToList ){
			this._limitToList = __limitToList;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * onbeforedomupdate
		 * The client side script method to be called before DOM is updated
		 */
		private ValueExpression _onbeforedomupdate;
		/**
		 * The client side script method to be called before DOM is updated
		 * Setter for onbeforedomupdate
		 * @param onbeforedomupdate - new value
		 */
		 public void setOnbeforedomupdate( ValueExpression  __onbeforedomupdate ){
			this._onbeforedomupdate = __onbeforedomupdate;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * oncomplete
		 * The client side script method to be called after the request is completed
		 */
		private ValueExpression _oncomplete;
		/**
		 * The client side script method to be called after the request is completed
		 * Setter for oncomplete
		 * @param oncomplete - new value
		 */
		 public void setOncomplete( ValueExpression  __oncomplete ){
			this._oncomplete = __oncomplete;
	     }
	  
	 	 		 		 		 		 		 		 		 		 		 		 	  			  		  	  
		/*
		 * onpagechange
		 * JavaScript handler for call after the page is changed
		 */
		private ValueExpression _onpagechange;
		/**
		 * JavaScript handler for call after the page is changed
		 * Setter for onpagechange
		 * @param onpagechange - new value
		 */
		 public void setOnpagechange( ValueExpression  __onpagechange ){
			this._onpagechange = __onpagechange;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * page
		 * If page &amp;gt;= 1 then it's a page number to show
		 */
		private ValueExpression _page;
		/**
		 * If page &amp;gt;= 1 then it's a page number to show
		 * Setter for page
		 * @param page - new value
		 */
		 public void setPage( ValueExpression  __page ){
			this._page = __page;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * pageIndexVar
		 * Name of variable in request scope containing index of active page
		 */
		private ValueExpression _pageIndexVar;
		/**
		 * Name of variable in request scope containing index of active page
		 * Setter for pageIndexVar
		 * @param pageIndexVar - new value
		 */
		 public void setPageIndexVar( ValueExpression  __pageIndexVar ){
			this._pageIndexVar = __pageIndexVar;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * pagesVar
		 * Name of variable in request scope containing number of pages
		 */
		private ValueExpression _pagesVar;
		/**
		 * Name of variable in request scope containing number of pages
		 * Setter for pagesVar
		 * @param pagesVar - new value
		 */
		 public void setPagesVar( ValueExpression  __pagesVar ){
			this._pagesVar = __pagesVar;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * process
		 * Id['s] (in format of call  UIComponent.findComponent()) of components, processed at the phases 2-5 in case of AjaxRequest  caused by this component. Can be single id, comma-separated list of Id's, or EL Expression  with array or Collection
		 */
		private ValueExpression _process;
		/**
		 * Id['s] (in format of call  UIComponent.findComponent()) of components, processed at the phases 2-5 in case of AjaxRequest  caused by this component. Can be single id, comma-separated list of Id's, or EL Expression  with array or Collection
		 * Setter for process
		 * @param process - new value
		 */
		 public void setProcess( ValueExpression  __process ){
			this._process = __process;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * reRender
		 * Id['s] (in format of call  UIComponent.findComponent()) of components, rendered in case of AjaxRequest  caused by this component. Can be single id, comma-separated list of Id's, or EL Expression  with array or Collection
		 */
		private ValueExpression _reRender;
		/**
		 * Id['s] (in format of call  UIComponent.findComponent()) of components, rendered in case of AjaxRequest  caused by this component. Can be single id, comma-separated list of Id's, or EL Expression  with array or Collection
		 * Setter for reRender
		 * @param reRender - new value
		 */
		 public void setReRender( ValueExpression  __reRender ){
			this._reRender = __reRender;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * renderIfSinglePage
		 * If renderIfSinglePage is "true" then datascroller is displayed on condition that the data hold on one page. Default value is "true".
		 */
		private ValueExpression _renderIfSinglePage;
		/**
		 * If renderIfSinglePage is "true" then datascroller is displayed on condition that the data hold on one page. Default value is "true".
		 * Setter for renderIfSinglePage
		 * @param renderIfSinglePage - new value
		 */
		 public void setRenderIfSinglePage( ValueExpression  __renderIfSinglePage ){
			this._renderIfSinglePage = __renderIfSinglePage;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * requestDelay
		 * Attribute defines the time (in ms.) that the request will be wait in the queue before it is ready to send.
When the delay time is over, the request will be sent to the server or removed if the newest 'similar' request is in a queue already
		 */
		private ValueExpression _requestDelay;
		/**
		 * Attribute defines the time (in ms.) that the request will be wait in the queue before it is ready to send.
When the delay time is over, the request will be sent to the server or removed if the newest 'similar' request is in a queue already
		 * Setter for requestDelay
		 * @param requestDelay - new value
		 */
		 public void setRequestDelay( ValueExpression  __requestDelay ){
			this._requestDelay = __requestDelay;
	     }
	  
	 	 		 		 	  	  	  
		/*
		 * scrollerListener
		 * MethodBinding representing an action listener method that will be notified after scrolling
		 */
		private MethodExpression _scrollerListener;
		/**
		 * MethodBinding representing an action listener method that will be notified after scrolling
		 * Setter for scrollerListener
		 * @param scrollerListener - new value
		 */
		 public void setScrollerListener( MethodExpression  __scrollerListener ){
			this._scrollerListener = __scrollerListener;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * selectedStyle
		 * Corresponds to the HTML style attribute for the selected cell on scroller
		 */
		private ValueExpression _selectedStyle;
		/**
		 * Corresponds to the HTML style attribute for the selected cell on scroller
		 * Setter for selectedStyle
		 * @param selectedStyle - new value
		 */
		 public void setSelectedStyle( ValueExpression  __selectedStyle ){
			this._selectedStyle = __selectedStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * selectedStyleClass
		 * Corresponds to the HTML class attribute for the selected cell on scroller
		 */
		private ValueExpression _selectedStyleClass;
		/**
		 * Corresponds to the HTML class attribute for the selected cell on scroller
		 * Setter for selectedStyleClass
		 * @param selectedStyleClass - new value
		 */
		 public void setSelectedStyleClass( ValueExpression  __selectedStyleClass ){
			this._selectedStyleClass = __selectedStyleClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * similarityGroupingId
		 * If there are any component requests with identical IDs then these requests will be grouped.
		 */
		private ValueExpression _similarityGroupingId;
		/**
		 * If there are any component requests with identical IDs then these requests will be grouped.
		 * Setter for similarityGroupingId
		 * @param similarityGroupingId - new value
		 */
		 public void setSimilarityGroupingId( ValueExpression  __similarityGroupingId ){
			this._similarityGroupingId = __similarityGroupingId;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * status
		 * ID (in format of call UIComponent.findComponent()) of Request status component
		 */
		private ValueExpression _status;
		/**
		 * ID (in format of call UIComponent.findComponent()) of Request status component
		 * Setter for status
		 * @param status - new value
		 */
		 public void setStatus( ValueExpression  __status ){
			this._status = __status;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * stepControls
		 * The attribute specifies the visibility of stepControls.
				Possible values are: "show"  (controls are always visible ). "hide" (controls are hidden. 
				"auto" (unnecessary controls are hidden). Default value is "show".
		 */
		private ValueExpression _stepControls;
		/**
		 * The attribute specifies the visibility of stepControls.
				Possible values are: "show"  (controls are always visible ). "hide" (controls are hidden. 
				"auto" (unnecessary controls are hidden). Default value is "show".
		 * Setter for stepControls
		 * @param stepControls - new value
		 */
		 public void setStepControls( ValueExpression  __stepControls ){
			this._stepControls = __stepControls;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * tableStyle
		 * CSS style(s) is/are to be applied to outside table when this component is rendered
		 */
		private ValueExpression _tableStyle;
		/**
		 * CSS style(s) is/are to be applied to outside table when this component is rendered
		 * Setter for tableStyle
		 * @param tableStyle - new value
		 */
		 public void setTableStyle( ValueExpression  __tableStyle ){
			this._tableStyle = __tableStyle;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * tableStyleClass
		 * Space-separated list of CSS style class(es) that are be applied to outside table of this component
		 */
		private ValueExpression _tableStyleClass;
		/**
		 * Space-separated list of CSS style class(es) that are be applied to outside table of this component
		 * Setter for tableStyleClass
		 * @param tableStyleClass - new value
		 */
		 public void setTableStyleClass( ValueExpression  __tableStyleClass ){
			this._tableStyleClass = __tableStyleClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * timeout
		 * Response waiting time on a particular request. If a response is not received during this time, the request is aborted
		 */
		private ValueExpression _timeout;
		/**
		 * Response waiting time on a particular request. If a response is not received during this time, the request is aborted
		 * Setter for timeout
		 * @param timeout - new value
		 */
		 public void setTimeout( ValueExpression  __timeout ){
			this._timeout = __timeout;
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
        		 		    this._action = null;
	 		 		 		    this._actionListener = null;
	 		 		 		 		    this._ajaxSingle = null;
	 		 		    this._align = null;
	 		 		 		    this._boundaryControls = null;
	 		 		    this._bypassUpdates = null;
	 		 		    this._data = null;
	 		 		 		    this._eventsQueue = null;
	 		 		 		    this._fastControls = null;
	 		 		 		 		    this._fastStep = null;
	 		 		 		 		    this._focus = null;
	 		 		    this._for = null;
	 		 		    this._handleValue = null;
	 		 		    this._height = null;
	 		 		 		    this._ignoreDupResponses = null;
	 		 		    this._immediate = null;
	 		 		    this._inactiveStyle = null;
	 		 		    this._inactiveStyleClass = null;
	 		 		 		    this._limitToList = null;
	 		 		 		 		    this._onbeforedomupdate = null;
	 		 		 		    this._oncomplete = null;
	 		 		 		 		 		 		 		 		 		 		 		    this._onpagechange = null;
	 		 		    this._page = null;
	 		 		 		 		    this._pageIndexVar = null;
	 		 		    this._pagesVar = null;
	 		 		 		    this._process = null;
	 		 		    this._reRender = null;
	 		 		    this._renderIfSinglePage = null;
	 		 		 		    this._requestDelay = null;
	 		 		 		    this._scrollerListener = null;
	 		 		 		    this._selectedStyle = null;
	 		 		    this._selectedStyleClass = null;
	 		 		    this._similarityGroupingId = null;
	 		 		    this._status = null;
	 		 		    this._stepControls = null;
	 		 		 		 		    this._tableStyle = null;
	 		 		    this._tableStyleClass = null;
	 		 		    this._timeout = null;
	 		 		    this._value = null;
	 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlVerticalScroller comp = (HtmlVerticalScroller) component;
 		 			setActionProperty(comp, this._action);
		    		 			setActionListenerProperty(comp, this._actionListener);
		     		 			 
						if (this._ajaxSingle != null) {
				if (this._ajaxSingle.isLiteralText()) {
					try {
												
						Boolean __ajaxSingle = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._ajaxSingle.getExpressionString(), 
											Boolean.class);
					
												comp.setAjaxSingle(__ajaxSingle.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("ajaxSingle", this._ajaxSingle);
				}
			}
					   		 			 
						if (this._align != null) {
				if (this._align.isLiteralText()) {
					try {
												
						java.lang.String __align = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._align.getExpressionString(), 
											java.lang.String.class);
					
												comp.setAlign(__align);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("align", this._align);
				}
			}
					    		 			 
						if (this._boundaryControls != null) {
				if (this._boundaryControls.isLiteralText()) {
					try {
												
						java.lang.String __boundaryControls = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._boundaryControls.getExpressionString(), 
											java.lang.String.class);
					
												comp.setBoundaryControls(__boundaryControls);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("boundaryControls", this._boundaryControls);
				}
			}
					   		 			 
						if (this._bypassUpdates != null) {
				if (this._bypassUpdates.isLiteralText()) {
					try {
												
						Boolean __bypassUpdates = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._bypassUpdates.getExpressionString(), 
											Boolean.class);
					
												comp.setBypassUpdates(__bypassUpdates.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("bypassUpdates", this._bypassUpdates);
				}
			}
					   		 			 
						if (this._data != null) {
				if (this._data.isLiteralText()) {
					try {
												
						java.lang.Object __data = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._data.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setData(__data);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("data", this._data);
				}
			}
					    		 			 
						if (this._eventsQueue != null) {
				if (this._eventsQueue.isLiteralText()) {
					try {
												
						java.lang.String __eventsQueue = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._eventsQueue.getExpressionString(), 
											java.lang.String.class);
					
												comp.setEventsQueue(__eventsQueue);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("eventsQueue", this._eventsQueue);
				}
			}
					    		 			 
						if (this._fastControls != null) {
				if (this._fastControls.isLiteralText()) {
					try {
												
						java.lang.String __fastControls = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._fastControls.getExpressionString(), 
											java.lang.String.class);
					
												comp.setFastControls(__fastControls);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("fastControls", this._fastControls);
				}
			}
					     		 			 
						if (this._fastStep != null) {
				if (this._fastStep.isLiteralText()) {
					try {
												
						Integer __fastStep = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._fastStep.getExpressionString(), 
											Integer.class);
					
												comp.setFastStep(__fastStep.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("fastStep", this._fastStep);
				}
			}
					     		 			 
						if (this._focus != null) {
				if (this._focus.isLiteralText()) {
					try {
												
						java.lang.String __focus = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._focus.getExpressionString(), 
											java.lang.String.class);
					
												comp.setFocus(__focus);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("focus", this._focus);
				}
			}
					   		 			 
						if (this._for != null) {
				if (this._for.isLiteralText()) {
					try {
												
						java.lang.String __for = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._for.getExpressionString(), 
											java.lang.String.class);
					
												comp.setFor(__for);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("for", this._for);
				}
			}
					   		 			 
						if (this._handleValue != null) {
				if (this._handleValue.isLiteralText()) {
					try {
												
						java.lang.String __handleValue = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._handleValue.getExpressionString(), 
											java.lang.String.class);
					
												comp.setHandleValue(__handleValue);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("handleValue", this._handleValue);
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
					    		 			 
						if (this._ignoreDupResponses != null) {
				if (this._ignoreDupResponses.isLiteralText()) {
					try {
												
						Boolean __ignoreDupResponses = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._ignoreDupResponses.getExpressionString(), 
											Boolean.class);
					
												comp.setIgnoreDupResponses(__ignoreDupResponses.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("ignoreDupResponses", this._ignoreDupResponses);
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
					   		 			 
						if (this._inactiveStyle != null) {
				if (this._inactiveStyle.isLiteralText()) {
					try {
												
						java.lang.String __inactiveStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._inactiveStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setInactiveStyle(__inactiveStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("inactiveStyle", this._inactiveStyle);
				}
			}
					   		 			 
						if (this._inactiveStyleClass != null) {
				if (this._inactiveStyleClass.isLiteralText()) {
					try {
												
						java.lang.String __inactiveStyleClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._inactiveStyleClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setInactiveStyleClass(__inactiveStyleClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("inactiveStyleClass", this._inactiveStyleClass);
				}
			}
					    		 			 
						if (this._limitToList != null) {
				if (this._limitToList.isLiteralText()) {
					try {
												
						Boolean __limitToList = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._limitToList.getExpressionString(), 
											Boolean.class);
					
												comp.setLimitToList(__limitToList.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("limitToList", this._limitToList);
				}
			}
					     		 			 
						if (this._onbeforedomupdate != null) {
				if (this._onbeforedomupdate.isLiteralText()) {
					try {
												
						java.lang.String __onbeforedomupdate = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onbeforedomupdate.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnbeforedomupdate(__onbeforedomupdate);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onbeforedomupdate", this._onbeforedomupdate);
				}
			}
					    		 			 
						if (this._oncomplete != null) {
				if (this._oncomplete.isLiteralText()) {
					try {
												
						java.lang.String __oncomplete = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._oncomplete.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOncomplete(__oncomplete);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("oncomplete", this._oncomplete);
				}
			}
					            		 			 
						if (this._onpagechange != null) {
				if (this._onpagechange.isLiteralText()) {
					try {
												
						java.lang.String __onpagechange = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onpagechange.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnpagechange(__onpagechange);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onpagechange", this._onpagechange);
				}
			}
					   		 			 
						if (this._page != null) {
				if (this._page.isLiteralText()) {
					try {
												
						Integer __page = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._page.getExpressionString(), 
											Integer.class);
					
												comp.setPage(__page.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("page", this._page);
				}
			}
					     		 			 
						if (this._pageIndexVar != null) {
				if (this._pageIndexVar.isLiteralText()) {
					try {
												
						java.lang.String __pageIndexVar = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._pageIndexVar.getExpressionString(), 
											java.lang.String.class);
					
												comp.setPageIndexVar(__pageIndexVar);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("pageIndexVar", this._pageIndexVar);
				}
			}
					   		 			 
						if (this._pagesVar != null) {
				if (this._pagesVar.isLiteralText()) {
					try {
												
						java.lang.String __pagesVar = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._pagesVar.getExpressionString(), 
											java.lang.String.class);
					
												comp.setPagesVar(__pagesVar);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("pagesVar", this._pagesVar);
				}
			}
					    		 			 
						if (this._process != null) {
				if (this._process.isLiteralText()) {
					try {
												
						java.lang.Object __process = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._process.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setProcess(__process);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("process", this._process);
				}
			}
					   		 			 
						if (this._reRender != null) {
				if (this._reRender.isLiteralText()) {
					try {
												
						java.lang.Object __reRender = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._reRender.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setReRender(__reRender);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("reRender", this._reRender);
				}
			}
					   		 			 
						if (this._renderIfSinglePage != null) {
				if (this._renderIfSinglePage.isLiteralText()) {
					try {
												
						Boolean __renderIfSinglePage = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._renderIfSinglePage.getExpressionString(), 
											Boolean.class);
					
												comp.setRenderIfSinglePage(__renderIfSinglePage.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("renderIfSinglePage", this._renderIfSinglePage);
				}
			}
					    		 			 
						if (this._requestDelay != null) {
				if (this._requestDelay.isLiteralText()) {
					try {
												
						Integer __requestDelay = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._requestDelay.getExpressionString(), 
											Integer.class);
					
												comp.setRequestDelay(__requestDelay.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("requestDelay", this._requestDelay);
				}
			}
					    		 			if(null != this._scrollerListener){
				((HtmlVerticalScroller)component).setScrollerListener(this._scrollerListener);
			}		
		    		 			 
						if (this._selectedStyle != null) {
				if (this._selectedStyle.isLiteralText()) {
					try {
												
						java.lang.String __selectedStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._selectedStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSelectedStyle(__selectedStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("selectedStyle", this._selectedStyle);
				}
			}
					   		 			 
						if (this._selectedStyleClass != null) {
				if (this._selectedStyleClass.isLiteralText()) {
					try {
												
						java.lang.String __selectedStyleClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._selectedStyleClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSelectedStyleClass(__selectedStyleClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("selectedStyleClass", this._selectedStyleClass);
				}
			}
					   		 			 
						if (this._similarityGroupingId != null) {
				if (this._similarityGroupingId.isLiteralText()) {
					try {
												
						java.lang.String __similarityGroupingId = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._similarityGroupingId.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSimilarityGroupingId(__similarityGroupingId);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("similarityGroupingId", this._similarityGroupingId);
				}
			}
					   		 			 
						if (this._status != null) {
				if (this._status.isLiteralText()) {
					try {
												
						java.lang.String __status = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._status.getExpressionString(), 
											java.lang.String.class);
					
												comp.setStatus(__status);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("status", this._status);
				}
			}
					   		 			 
						if (this._stepControls != null) {
				if (this._stepControls.isLiteralText()) {
					try {
												
						java.lang.String __stepControls = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._stepControls.getExpressionString(), 
											java.lang.String.class);
					
												comp.setStepControls(__stepControls);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("stepControls", this._stepControls);
				}
			}
					     		 			 
						if (this._tableStyle != null) {
				if (this._tableStyle.isLiteralText()) {
					try {
												
						java.lang.String __tableStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._tableStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setTableStyle(__tableStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("tableStyle", this._tableStyle);
				}
			}
					   		 			 
						if (this._tableStyleClass != null) {
				if (this._tableStyleClass.isLiteralText()) {
					try {
												
						java.lang.String __tableStyleClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._tableStyleClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setTableStyleClass(__tableStyleClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("tableStyleClass", this._tableStyleClass);
				}
			}
					   		 			 
						if (this._timeout != null) {
				if (this._timeout.isLiteralText()) {
					try {
												
						Integer __timeout = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._timeout.getExpressionString(), 
											Integer.class);
					
												comp.setTimeout(__timeout.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("timeout", this._timeout);
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
		return "com.exadel.siperian.VerticalScroller";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.VerticalScrollerRenderer";
			}

}
