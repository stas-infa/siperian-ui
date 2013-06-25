/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import java.lang.Object ;
import java.lang.Boolean ;
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
import com.exadel.siperian.component.html.HtmlTab;

public class TabTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

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
		 * boolean attribute which provides possibility to limit JSF tree processing(decoding, conversion/validation, value applying) 
to the component which send the request only
		 */
		private ValueExpression _ajaxSingle;
		/**
		 * boolean attribute which provides possibility to limit JSF tree processing(decoding, conversion/validation, value applying) 
to the component which send the request only
		 * Setter for ajaxSingle
		 * @param ajaxSingle - new value
		 */
		 public void setAjaxSingle( ValueExpression  __ajaxSingle ){
			this._ajaxSingle = __ajaxSingle;
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
		 * disabled
		 * Disables a tab in a tab panel
		 */
		private ValueExpression _disabled;
		/**
		 * Disables a tab in a tab panel
		 * Setter for disabled
		 * @param disabled - new value
		 */
		 public void setDisabled( ValueExpression  __disabled ){
			this._disabled = __disabled;
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
		 * ignoreDupResponses
		 * Attribute allows to ignore an Ajax Response produced by a request if the newest 'similar' request is
in a queue already. ignoreDupResponses="true" does not cancel the request while it is processed on the server,
but just allows to avoid unnecessary updates on the client side if the response isn't actual now
		 */
		private ValueExpression _ignoreDupResponses;
		/**
		 * Attribute allows to ignore an Ajax Response produced by a request if the newest 'similar' request is
in a queue already. ignoreDupResponses="true" does not cancel the request while it is processed on the server,
but just allows to avoid unnecessary updates on the client side if the response isn't actual now
		 * Setter for ignoreDupResponses
		 * @param ignoreDupResponses - new value
		 */
		 public void setIgnoreDupResponses( ValueExpression  __ignoreDupResponses ){
			this._ignoreDupResponses = __ignoreDupResponses;
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
		 * label
		 * Text for the actual "tab" in a tab section
		 */
		private ValueExpression _label;
		/**
		 * Text for the actual "tab" in a tab section
		 * Setter for label
		 * @param label - new value
		 */
		 public void setLabel( ValueExpression  __label ){
			this._label = __label;
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
		 * longRunning
		 * longRunning
		 */
		private ValueExpression _longRunning;
		/**
		 * longRunning
		 * Setter for longRunning
		 * @param longRunning - new value
		 */
		 public void setLongRunning( ValueExpression  __longRunning ){
			this._longRunning = __longRunning;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * name
		 * Attribute defines tab name. Default value is "getId()".
		 */
		private ValueExpression _name;
		/**
		 * Attribute defines tab name. Default value is "getId()".
		 * Setter for name
		 * @param name - new value
		 */
		 public void setName( ValueExpression  __name ){
			this._name = __name;
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
		 * onlabelclick
		 * A JavaScript event handler; a label of the tab is clicked
		 */
		private ValueExpression _onlabelclick;
		/**
		 * A JavaScript event handler; a label of the tab is clicked
		 * Setter for onlabelclick
		 * @param onlabelclick - new value
		 */
		 public void setOnlabelclick( ValueExpression  __onlabelclick ){
			this._onlabelclick = __onlabelclick;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onlabeldblclick
		 * A JavaScript event handler; a pointer within label is double-clicked
		 */
		private ValueExpression _onlabeldblclick;
		/**
		 * A JavaScript event handler; a pointer within label is double-clicked
		 * Setter for onlabeldblclick
		 * @param onlabeldblclick - new value
		 */
		 public void setOnlabeldblclick( ValueExpression  __onlabeldblclick ){
			this._onlabeldblclick = __onlabeldblclick;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onlabelkeydown
		 * A JavaScript event handler; a key within label is pressed down
		 */
		private ValueExpression _onlabelkeydown;
		/**
		 * A JavaScript event handler; a key within label is pressed down
		 * Setter for onlabelkeydown
		 * @param onlabelkeydown - new value
		 */
		 public void setOnlabelkeydown( ValueExpression  __onlabelkeydown ){
			this._onlabelkeydown = __onlabelkeydown;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onlabelkeypress
		 * A JavaScript event handler; a key within label is pressed and released
		 */
		private ValueExpression _onlabelkeypress;
		/**
		 * A JavaScript event handler; a key within label is pressed and released
		 * Setter for onlabelkeypress
		 * @param onlabelkeypress - new value
		 */
		 public void setOnlabelkeypress( ValueExpression  __onlabelkeypress ){
			this._onlabelkeypress = __onlabelkeypress;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onlabelkeyup
		 * A JavaScript event handler; a key within label is released
		 */
		private ValueExpression _onlabelkeyup;
		/**
		 * A JavaScript event handler; a key within label is released
		 * Setter for onlabelkeyup
		 * @param onlabelkeyup - new value
		 */
		 public void setOnlabelkeyup( ValueExpression  __onlabelkeyup ){
			this._onlabelkeyup = __onlabelkeyup;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onlabelmousedown
		 * A JavaScript event handler; a pointer within label is pressed down
		 */
		private ValueExpression _onlabelmousedown;
		/**
		 * A JavaScript event handler; a pointer within label is pressed down
		 * Setter for onlabelmousedown
		 * @param onlabelmousedown - new value
		 */
		 public void setOnlabelmousedown( ValueExpression  __onlabelmousedown ){
			this._onlabelmousedown = __onlabelmousedown;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onlabelmousemove
		 * A JavaScript event handler; a pointer is moved within label
		 */
		private ValueExpression _onlabelmousemove;
		/**
		 * A JavaScript event handler; a pointer is moved within label
		 * Setter for onlabelmousemove
		 * @param onlabelmousemove - new value
		 */
		 public void setOnlabelmousemove( ValueExpression  __onlabelmousemove ){
			this._onlabelmousemove = __onlabelmousemove;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onlabelmouseup
		 * A JavaScript event handler; a pointer within label is released
		 */
		private ValueExpression _onlabelmouseup;
		/**
		 * A JavaScript event handler; a pointer within label is released
		 * Setter for onlabelmouseup
		 * @param onlabelmouseup - new value
		 */
		 public void setOnlabelmouseup( ValueExpression  __onlabelmouseup ){
			this._onlabelmouseup = __onlabelmouseup;
	     }
	  
	 	 		 		 		 		 		 		 	  			  		  	  
		/*
		 * ontabenter
		 * Event must occur on the tab which has been entered
		 */
		private ValueExpression _ontabenter;
		/**
		 * Event must occur on the tab which has been entered
		 * Setter for ontabenter
		 * @param ontabenter - new value
		 */
		 public void setOntabenter( ValueExpression  __ontabenter ){
			this._ontabenter = __ontabenter;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * ontableave
		 * Event must occurs on the tab which has been left
		 */
		private ValueExpression _ontableave;
		/**
		 * Event must occurs on the tab which has been left
		 * Setter for ontableave
		 * @param ontableave - new value
		 */
		 public void setOntableave( ValueExpression  __ontableave ){
			this._ontableave = __ontableave;
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
		 * switchType
		 * Tab switch algorithm. Possible values are  "client", "server", "ajax", "page".
		 */
		private ValueExpression _switchType;
		/**
		 * Tab switch algorithm. Possible values are  "client", "server", "ajax", "page".
		 * Setter for switchType
		 * @param switchType - new value
		 */
		 public void setSwitchType( ValueExpression  __switchType ){
			this._switchType = __switchType;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * tabCloseable
		 * tabCloseable
		 */
		private ValueExpression _tabCloseable;
		/**
		 * tabCloseable
		 * Setter for tabCloseable
		 * @param tabCloseable - new value
		 */
		 public void setTabCloseable( ValueExpression  __tabCloseable ){
			this._tabCloseable = __tabCloseable;
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
	  
	 	 		 		 	
	
    public void release()
    {
        // TODO Auto-generated method stub
        super.release();
        		 		    this._action = null;
	 		 		 		    this._actionListener = null;
	 		 		 		 		 		    this._ajaxSingle = null;
	 		 		 		    this._bypassUpdates = null;
	 		 		    this._data = null;
	 		 		    this._disabled = null;
	 		 		    this._eventsQueue = null;
	 		 		 		    this._focus = null;
	 		 		 		    this._ignoreDupResponses = null;
	 		 		    this._immediate = null;
	 		 		    this._label = null;
	 		 		 		    this._limitToList = null;
	 		 		    this._longRunning = null;
	 		 		    this._name = null;
	 		 		    this._onbeforedomupdate = null;
	 		 		 		    this._oncomplete = null;
	 		 		 		 		 		 		    this._onlabelclick = null;
	 		 		    this._onlabeldblclick = null;
	 		 		    this._onlabelkeydown = null;
	 		 		    this._onlabelkeypress = null;
	 		 		    this._onlabelkeyup = null;
	 		 		    this._onlabelmousedown = null;
	 		 		    this._onlabelmousemove = null;
	 		 		    this._onlabelmouseup = null;
	 		 		 		 		 		 		 		    this._ontabenter = null;
	 		 		    this._ontableave = null;
	 		 		 		    this._process = null;
	 		 		    this._reRender = null;
	 		 		 		    this._requestDelay = null;
	 		 		    this._similarityGroupingId = null;
	 		 		    this._status = null;
	 		 		 		 		    this._switchType = null;
	 		 		 		    this._tabCloseable = null;
	 		 		    this._timeout = null;
	 		 		 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlTab comp = (HtmlTab) component;
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
					   		 			 
						if (this._longRunning != null) {
				if (this._longRunning.isLiteralText()) {
					try {
												
						java.lang.Boolean __longRunning = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._longRunning.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setLongRunning(__longRunning);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("longRunning", this._longRunning);
				}
			}
					   		 			 
						if (this._name != null) {
				if (this._name.isLiteralText()) {
					try {
												
						java.lang.Object __name = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._name.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setName(__name);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("name", this._name);
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
					       		 			 
						if (this._onlabelclick != null) {
				if (this._onlabelclick.isLiteralText()) {
					try {
												
						java.lang.String __onlabelclick = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onlabelclick.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnlabelclick(__onlabelclick);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onlabelclick", this._onlabelclick);
				}
			}
					   		 			 
						if (this._onlabeldblclick != null) {
				if (this._onlabeldblclick.isLiteralText()) {
					try {
												
						java.lang.String __onlabeldblclick = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onlabeldblclick.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnlabeldblclick(__onlabeldblclick);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onlabeldblclick", this._onlabeldblclick);
				}
			}
					   		 			 
						if (this._onlabelkeydown != null) {
				if (this._onlabelkeydown.isLiteralText()) {
					try {
												
						java.lang.String __onlabelkeydown = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onlabelkeydown.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnlabelkeydown(__onlabelkeydown);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onlabelkeydown", this._onlabelkeydown);
				}
			}
					   		 			 
						if (this._onlabelkeypress != null) {
				if (this._onlabelkeypress.isLiteralText()) {
					try {
												
						java.lang.String __onlabelkeypress = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onlabelkeypress.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnlabelkeypress(__onlabelkeypress);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onlabelkeypress", this._onlabelkeypress);
				}
			}
					   		 			 
						if (this._onlabelkeyup != null) {
				if (this._onlabelkeyup.isLiteralText()) {
					try {
												
						java.lang.String __onlabelkeyup = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onlabelkeyup.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnlabelkeyup(__onlabelkeyup);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onlabelkeyup", this._onlabelkeyup);
				}
			}
					   		 			 
						if (this._onlabelmousedown != null) {
				if (this._onlabelmousedown.isLiteralText()) {
					try {
												
						java.lang.String __onlabelmousedown = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onlabelmousedown.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnlabelmousedown(__onlabelmousedown);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onlabelmousedown", this._onlabelmousedown);
				}
			}
					   		 			 
						if (this._onlabelmousemove != null) {
				if (this._onlabelmousemove.isLiteralText()) {
					try {
												
						java.lang.String __onlabelmousemove = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onlabelmousemove.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnlabelmousemove(__onlabelmousemove);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onlabelmousemove", this._onlabelmousemove);
				}
			}
					   		 			 
						if (this._onlabelmouseup != null) {
				if (this._onlabelmouseup.isLiteralText()) {
					try {
												
						java.lang.String __onlabelmouseup = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onlabelmouseup.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnlabelmouseup(__onlabelmouseup);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onlabelmouseup", this._onlabelmouseup);
				}
			}
					        		 			 
						if (this._ontabenter != null) {
				if (this._ontabenter.isLiteralText()) {
					try {
												
						java.lang.String __ontabenter = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._ontabenter.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOntabenter(__ontabenter);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("ontabenter", this._ontabenter);
				}
			}
					   		 			 
						if (this._ontableave != null) {
				if (this._ontableave.isLiteralText()) {
					try {
												
						java.lang.String __ontableave = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._ontableave.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOntableave(__ontableave);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("ontableave", this._ontableave);
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
					    		 			 
						if (this._tabCloseable != null) {
				if (this._tabCloseable.isLiteralText()) {
					try {
												
						java.lang.Boolean __tabCloseable = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._tabCloseable.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setTabCloseable(__tabCloseable);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("tabCloseable", this._tabCloseable);
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
					       }
	
	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		// TODO Auto-generated method stub
		return "com.exadel.siperian.Tab";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.TabRenderer";
			}

}
