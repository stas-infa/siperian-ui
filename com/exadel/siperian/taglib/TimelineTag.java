/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import com.exadel.siperian.model.ITimeLineState ;
import javax.el.ValueExpression ;
import java.lang.Integer ;
import java.lang.String ;
import java.util.Date ;
import org.ajax4jsf.webapp.taglib.HtmlComponentTagBase ;
import com.exadel.siperian.model.TimeLineDataModel ;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.el.MethodExpression;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.el.ValueExpression;
import org.richfaces.webapp.taglib.MethodBindingMethodExpressionAdaptor;
import org.richfaces.webapp.taglib.ValueBindingValueExpressionAdaptor;
import com.exadel.siperian.component.html.HtmlTimeline;

public class TimelineTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

		// Fields
		 		 	  			  		  	  
		/*
		 * dataModel
		 * ${prop.xmlEncodedDescription}
		 */
		private ValueExpression _dataModel;
		/**
		 * ${prop.xmlEncodedDescription}
		 * Setter for dataModel
		 * @param dataModel - new value
		 */
		 public void setDataModel( ValueExpression  __dataModel ){
			this._dataModel = __dataModel;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * eventBoxColors
		 * Set of colors should be defined with list of Color objects or list of String color values
		 */
		private ValueExpression _eventBoxColors;
		/**
		 * Set of colors should be defined with list of Color objects or list of String color values
		 * Setter for eventBoxColors
		 * @param eventBoxColors - new value
		 */
		 public void setEventBoxColors( ValueExpression  __eventBoxColors ){
			this._eventBoxColors = __eventBoxColors;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * eventBoxSuperposition
		 * The attribute defines allowed percentage of superposition the Event boxes. On exceeding the value Event boxes need to be displayed as Event group
		 */
		private ValueExpression _eventBoxSuperposition;
		/**
		 * The attribute defines allowed percentage of superposition the Event boxes. On exceeding the value Event boxes need to be displayed as Event group
		 * Setter for eventBoxSuperposition
		 * @param eventBoxSuperposition - new value
		 */
		 public void setEventBoxSuperposition( ValueExpression  __eventBoxSuperposition ){
			this._eventBoxSuperposition = __eventBoxSuperposition;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * eventBoxWidth
		 * The attribute defines width of the Event box as well as width of Group box in pixels
		 */
		private ValueExpression _eventBoxWidth;
		/**
		 * The attribute defines width of the Event box as well as width of Group box in pixels
		 * Setter for eventBoxWidth
		 * @param eventBoxWidth - new value
		 */
		 public void setEventBoxWidth( ValueExpression  __eventBoxWidth ){
			this._eventBoxWidth = __eventBoxWidth;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * eventInterval
		 * Interval between two events used for groups union.
		 */
		private ValueExpression _eventInterval;
		/**
		 * Interval between two events used for groups union.
		 * Setter for eventInterval
		 * @param eventInterval - new value
		 */
		 public void setEventInterval( ValueExpression  __eventInterval ){
			this._eventInterval = __eventInterval;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * eventMenuId
		 * The menu identifier this menu will be show when we click by event
		 */
		private ValueExpression _eventMenuId;
		/**
		 * The menu identifier this menu will be show when we click by event
		 * Setter for eventMenuId
		 * @param eventMenuId - new value
		 */
		 public void setEventMenuId( ValueExpression  __eventMenuId ){
			this._eventMenuId = __eventMenuId;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * eventMenuItems
		 * Menu items for events .
		 */
		private ValueExpression _eventMenuItems;
		/**
		 * Menu items for events .
		 * Setter for eventMenuItems
		 * @param eventMenuItems - new value
		 */
		 public void setEventMenuItems( ValueExpression  __eventMenuItems ){
			this._eventMenuItems = __eventMenuItems;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * eventStyleClass
		 * common css style class for events on scroller
		 */
		private ValueExpression _eventStyleClass;
		/**
		 * common css style class for events on scroller
		 * Setter for eventStyleClass
		 * @param eventStyleClass - new value
		 */
		 public void setEventStyleClass( ValueExpression  __eventStyleClass ){
			this._eventStyleClass = __eventStyleClass;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * flashWmode
		 * ${prop.xmlEncodedDescription}
		 */
		private ValueExpression _flashWmode;
		/**
		 * ${prop.xmlEncodedDescription}
		 * Setter for flashWmode
		 * @param flashWmode - new value
		 */
		 public void setFlashWmode( ValueExpression  __flashWmode ){
			this._flashWmode = __flashWmode;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * fromDate
		 * ${prop.xmlEncodedDescription}
		 */
		private ValueExpression _fromDate;
		/**
		 * ${prop.xmlEncodedDescription}
		 * Setter for fromDate
		 * @param fromDate - new value
		 */
		 public void setFromDate( ValueExpression  __fromDate ){
			this._fromDate = __fromDate;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * globalMenuItems
		 * Global menu items for Safari browser flash menu.
		 */
		private ValueExpression _globalMenuItems;
		/**
		 * Global menu items for Safari browser flash menu.
		 * Setter for globalMenuItems
		 * @param globalMenuItems - new value
		 */
		 public void setGlobalMenuItems( ValueExpression  __globalMenuItems ){
			this._globalMenuItems = __globalMenuItems;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * groupHoverStyleClass
		 * common css style class for groups when group is hovered
		 */
		private ValueExpression _groupHoverStyleClass;
		/**
		 * common css style class for groups when group is hovered
		 * Setter for groupHoverStyleClass
		 * @param groupHoverStyleClass - new value
		 */
		 public void setGroupHoverStyleClass( ValueExpression  __groupHoverStyleClass ){
			this._groupHoverStyleClass = __groupHoverStyleClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * groupSelectedStyleClass
		 * common css style class for groups when group is selected
		 */
		private ValueExpression _groupSelectedStyleClass;
		/**
		 * common css style class for groups when group is selected
		 * Setter for groupSelectedStyleClass
		 * @param groupSelectedStyleClass - new value
		 */
		 public void setGroupSelectedStyleClass( ValueExpression  __groupSelectedStyleClass ){
			this._groupSelectedStyleClass = __groupSelectedStyleClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * groupStyleClass
		 * common css style class for groups
		 */
		private ValueExpression _groupStyleClass;
		/**
		 * common css style class for groups
		 * Setter for groupStyleClass
		 * @param groupStyleClass - new value
		 */
		 public void setGroupStyleClass( ValueExpression  __groupStyleClass ){
			this._groupStyleClass = __groupStyleClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * groupTooltipTemplate
		 * The attribute defines content of the tooltip
		 */
		private ValueExpression _groupTooltipTemplate;
		/**
		 * The attribute defines content of the tooltip
		 * Setter for groupTooltipTemplate
		 * @param groupTooltipTemplate - new value
		 */
		 public void setGroupTooltipTemplate( ValueExpression  __groupTooltipTemplate ){
			this._groupTooltipTemplate = __groupTooltipTemplate;
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
		 * height
		 * ${prop.xmlEncodedDescription}
		 */
		private ValueExpression _height;
		/**
		 * ${prop.xmlEncodedDescription}
		 * Setter for height
		 * @param height - new value
		 */
		 public void setHeight( ValueExpression  __height ){
			this._height = __height;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * maxDisplaedEventsInToolTip
		 * maximum count of events that will displayed in tooltip if 0(by default) then unlimited
		 */
		private ValueExpression _maxDisplaedEventsInToolTip;
		/**
		 * maximum count of events that will displayed in tooltip if 0(by default) then unlimited
		 * Setter for maxDisplaedEventsInToolTip
		 * @param maxDisplaedEventsInToolTip - new value
		 */
		 public void setMaxDisplaedEventsInToolTip( ValueExpression  __maxDisplaedEventsInToolTip ){
			this._maxDisplaedEventsInToolTip = __maxDisplaedEventsInToolTip;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * maximumZoomResolution
		 * A configurable parameter to define the smallest visible interval supported
		 */
		private ValueExpression _maximumZoomResolution;
		/**
		 * A configurable parameter to define the smallest visible interval supported
		 * Setter for maximumZoomResolution
		 * @param maximumZoomResolution - new value
		 */
		 public void setMaximumZoomResolution( ValueExpression  __maximumZoomResolution ){
			this._maximumZoomResolution = __maximumZoomResolution;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * ontimelineinit
		 * defined an javascript handler that will invoke when timeline inited.
		 */
		private ValueExpression _ontimelineinit;
		/**
		 * defined an javascript handler that will invoke when timeline inited.
		 * Setter for ontimelineinit
		 * @param ontimelineinit - new value
		 */
		 public void setOntimelineinit( ValueExpression  __ontimelineinit ){
			this._ontimelineinit = __ontimelineinit;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onzoomerchange
		 * defined an javascript handler that will invoke when timeline zoomer change.
		 */
		private ValueExpression _onzoomerchange;
		/**
		 * defined an javascript handler that will invoke when timeline zoomer change.
		 * Setter for onzoomerchange
		 * @param onzoomerchange - new value
		 */
		 public void setOnzoomerchange( ValueExpression  __onzoomerchange ){
			this._onzoomerchange = __onzoomerchange;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * popupMenuId
		 * Global menu identifier
		 */
		private ValueExpression _popupMenuId;
		/**
		 * Global menu identifier
		 * Setter for popupMenuId
		 * @param popupMenuId - new value
		 */
		 public void setPopupMenuId( ValueExpression  __popupMenuId ){
			this._popupMenuId = __popupMenuId;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * showFlashPlayerInstall
		 * if true then if user not have a flash player will display link to the flash player. By default false
		 */
		private ValueExpression _showFlashPlayerInstall;
		/**
		 * if true then if user not have a flash player will display link to the flash player. By default false
		 * Setter for showFlashPlayerInstall
		 * @param showFlashPlayerInstall - new value
		 */
		 public void setShowFlashPlayerInstall( ValueExpression  __showFlashPlayerInstall ){
			this._showFlashPlayerInstall = __showFlashPlayerInstall;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * swimLaneClass
		 * swinlane css class
		 */
		private ValueExpression _swimLaneClass;
		/**
		 * swinlane css class
		 * Setter for swimLaneClass
		 * @param swimLaneClass - new value
		 */
		 public void setSwimLaneClass( ValueExpression  __swimLaneClass ){
			this._swimLaneClass = __swimLaneClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * timelineState
		 * store state for the timeline component.
		 */
		private ValueExpression _timelineState;
		/**
		 * store state for the timeline component.
		 * Setter for timelineState
		 * @param timelineState - new value
		 */
		 public void setTimelineState( ValueExpression  __timelineState ){
			this._timelineState = __timelineState;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * timlineStylesheet
		 * path to the file where template for the group tooltip located.
		 */
		private ValueExpression _timlineStylesheet;
		/**
		 * path to the file where template for the group tooltip located.
		 * Setter for timlineStylesheet
		 * @param timlineStylesheet - new value
		 */
		 public void setTimlineStylesheet( ValueExpression  __timlineStylesheet ){
			this._timlineStylesheet = __timlineStylesheet;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * toDate
		 * ${prop.xmlEncodedDescription}
		 */
		private ValueExpression _toDate;
		/**
		 * ${prop.xmlEncodedDescription}
		 * Setter for toDate
		 * @param toDate - new value
		 */
		 public void setToDate( ValueExpression  __toDate ){
			this._toDate = __toDate;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * width
		 * ${prop.xmlEncodedDescription}
		 */
		private ValueExpression _width;
		/**
		 * ${prop.xmlEncodedDescription}
		 * Setter for width
		 * @param width - new value
		 */
		 public void setWidth( ValueExpression  __width ){
			this._width = __width;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * zoomerIndent
		 * zoomer indent from first event in timeline. By default 10
		 */
		private ValueExpression _zoomerIndent;
		/**
		 * zoomer indent from first event in timeline. By default 10
		 * Setter for zoomerIndent
		 * @param zoomerIndent - new value
		 */
		 public void setZoomerIndent( ValueExpression  __zoomerIndent ){
			this._zoomerIndent = __zoomerIndent;
	     }
	  
	 	 	
	
    public void release()
    {
        // TODO Auto-generated method stub
        super.release();
        		 		 		    this._dataModel = null;
	 		 		    this._eventBoxColors = null;
	 		 		    this._eventBoxSuperposition = null;
	 		 		    this._eventBoxWidth = null;
	 		 		    this._eventInterval = null;
	 		 		    this._eventMenuId = null;
	 		 		    this._eventMenuItems = null;
	 		 		    this._eventStyleClass = null;
	 		 		 		    this._flashWmode = null;
	 		 		    this._fromDate = null;
	 		 		    this._globalMenuItems = null;
	 		 		    this._groupHoverStyleClass = null;
	 		 		    this._groupSelectedStyleClass = null;
	 		 		    this._groupStyleClass = null;
	 		 		    this._groupTooltipTemplate = null;
	 		 		    this._header = null;
	 		 		    this._height = null;
	 		 		 		    this._maxDisplaedEventsInToolTip = null;
	 		 		    this._maximumZoomResolution = null;
	 		 		    this._ontimelineinit = null;
	 		 		    this._onzoomerchange = null;
	 		 		    this._popupMenuId = null;
	 		 		 		    this._showFlashPlayerInstall = null;
	 		 		 		    this._swimLaneClass = null;
	 		 		    this._timelineState = null;
	 		 		    this._timlineStylesheet = null;
	 		 		    this._toDate = null;
	 		 		    this._width = null;
	 		 		    this._zoomerIndent = null;
	 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlTimeline comp = (HtmlTimeline) component;
  		 			 
						if (this._dataModel != null) {
				if (this._dataModel.isLiteralText()) {
					try {
												
						com.exadel.siperian.model.TimeLineDataModel __dataModel = (com.exadel.siperian.model.TimeLineDataModel) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._dataModel.getExpressionString(), 
											com.exadel.siperian.model.TimeLineDataModel.class);
					
												comp.setDataModel(__dataModel);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("dataModel", this._dataModel);
				}
			}
					   		 			 
						if (this._eventBoxColors != null) {
				if (this._eventBoxColors.isLiteralText()) {
					try {
												
						java.lang.String __eventBoxColors = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._eventBoxColors.getExpressionString(), 
											java.lang.String.class);
					
												comp.setEventBoxColors(__eventBoxColors);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("eventBoxColors", this._eventBoxColors);
				}
			}
					   		 			 
						if (this._eventBoxSuperposition != null) {
				if (this._eventBoxSuperposition.isLiteralText()) {
					try {
												
						java.lang.String __eventBoxSuperposition = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._eventBoxSuperposition.getExpressionString(), 
											java.lang.String.class);
					
												comp.setEventBoxSuperposition(__eventBoxSuperposition);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("eventBoxSuperposition", this._eventBoxSuperposition);
				}
			}
					   		 			 
						if (this._eventBoxWidth != null) {
				if (this._eventBoxWidth.isLiteralText()) {
					try {
												
						java.lang.Integer __eventBoxWidth = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._eventBoxWidth.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setEventBoxWidth(__eventBoxWidth);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("eventBoxWidth", this._eventBoxWidth);
				}
			}
					   		 			 
						if (this._eventInterval != null) {
				if (this._eventInterval.isLiteralText()) {
					try {
												
						java.lang.String __eventInterval = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._eventInterval.getExpressionString(), 
											java.lang.String.class);
					
												comp.setEventInterval(__eventInterval);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("eventInterval", this._eventInterval);
				}
			}
					   		 			 
						if (this._eventMenuId != null) {
				if (this._eventMenuId.isLiteralText()) {
					try {
												
						java.lang.String __eventMenuId = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._eventMenuId.getExpressionString(), 
											java.lang.String.class);
					
												comp.setEventMenuId(__eventMenuId);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("eventMenuId", this._eventMenuId);
				}
			}
					   		 			 
						if (this._eventMenuItems != null) {
				if (this._eventMenuItems.isLiteralText()) {
					try {
												
						java.lang.String __eventMenuItems = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._eventMenuItems.getExpressionString(), 
											java.lang.String.class);
					
												comp.setEventMenuItems(__eventMenuItems);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("eventMenuItems", this._eventMenuItems);
				}
			}
					   		 			 
						if (this._eventStyleClass != null) {
				if (this._eventStyleClass.isLiteralText()) {
					try {
												
						java.lang.String __eventStyleClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._eventStyleClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setEventStyleClass(__eventStyleClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("eventStyleClass", this._eventStyleClass);
				}
			}
					    		 			 
						if (this._flashWmode != null) {
				if (this._flashWmode.isLiteralText()) {
					try {
												
						java.lang.String __flashWmode = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._flashWmode.getExpressionString(), 
											java.lang.String.class);
					
												comp.setFlashWmode(__flashWmode);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("flashWmode", this._flashWmode);
				}
			}
					   		 			 
						if (this._fromDate != null) {
				if (this._fromDate.isLiteralText()) {
					try {
												
						java.util.Date __fromDate = (java.util.Date) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._fromDate.getExpressionString(), 
											java.util.Date.class);
					
												comp.setFromDate(__fromDate);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("fromDate", this._fromDate);
				}
			}
					   		 			 
						if (this._globalMenuItems != null) {
				if (this._globalMenuItems.isLiteralText()) {
					try {
												
						java.lang.String __globalMenuItems = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._globalMenuItems.getExpressionString(), 
											java.lang.String.class);
					
												comp.setGlobalMenuItems(__globalMenuItems);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("globalMenuItems", this._globalMenuItems);
				}
			}
					   		 			 
						if (this._groupHoverStyleClass != null) {
				if (this._groupHoverStyleClass.isLiteralText()) {
					try {
												
						java.lang.String __groupHoverStyleClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._groupHoverStyleClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setGroupHoverStyleClass(__groupHoverStyleClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("groupHoverStyleClass", this._groupHoverStyleClass);
				}
			}
					   		 			 
						if (this._groupSelectedStyleClass != null) {
				if (this._groupSelectedStyleClass.isLiteralText()) {
					try {
												
						java.lang.String __groupSelectedStyleClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._groupSelectedStyleClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setGroupSelectedStyleClass(__groupSelectedStyleClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("groupSelectedStyleClass", this._groupSelectedStyleClass);
				}
			}
					   		 			 
						if (this._groupStyleClass != null) {
				if (this._groupStyleClass.isLiteralText()) {
					try {
												
						java.lang.String __groupStyleClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._groupStyleClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setGroupStyleClass(__groupStyleClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("groupStyleClass", this._groupStyleClass);
				}
			}
					   		 			 
						if (this._groupTooltipTemplate != null) {
				if (this._groupTooltipTemplate.isLiteralText()) {
					try {
												
						java.lang.String __groupTooltipTemplate = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._groupTooltipTemplate.getExpressionString(), 
											java.lang.String.class);
					
												comp.setGroupTooltipTemplate(__groupTooltipTemplate);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("groupTooltipTemplate", this._groupTooltipTemplate);
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
					    		 			 
						if (this._maxDisplaedEventsInToolTip != null) {
				if (this._maxDisplaedEventsInToolTip.isLiteralText()) {
					try {
												
						java.lang.Integer __maxDisplaedEventsInToolTip = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._maxDisplaedEventsInToolTip.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setMaxDisplaedEventsInToolTip(__maxDisplaedEventsInToolTip);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("maxDisplaedEventsInToolTip", this._maxDisplaedEventsInToolTip);
				}
			}
					   		 			 
						if (this._maximumZoomResolution != null) {
				if (this._maximumZoomResolution.isLiteralText()) {
					try {
												
						java.lang.Integer __maximumZoomResolution = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._maximumZoomResolution.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setMaximumZoomResolution(__maximumZoomResolution);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("maximumZoomResolution", this._maximumZoomResolution);
				}
			}
					   		 			 
						if (this._ontimelineinit != null) {
				if (this._ontimelineinit.isLiteralText()) {
					try {
												
						java.lang.String __ontimelineinit = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._ontimelineinit.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOntimelineinit(__ontimelineinit);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("ontimelineinit", this._ontimelineinit);
				}
			}
					   		 			 
						if (this._onzoomerchange != null) {
				if (this._onzoomerchange.isLiteralText()) {
					try {
												
						java.lang.String __onzoomerchange = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onzoomerchange.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnzoomerchange(__onzoomerchange);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onzoomerchange", this._onzoomerchange);
				}
			}
					   		 			 
						if (this._popupMenuId != null) {
				if (this._popupMenuId.isLiteralText()) {
					try {
												
						java.lang.String __popupMenuId = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._popupMenuId.getExpressionString(), 
											java.lang.String.class);
					
												comp.setPopupMenuId(__popupMenuId);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("popupMenuId", this._popupMenuId);
				}
			}
					    		 			 
						if (this._showFlashPlayerInstall != null) {
				if (this._showFlashPlayerInstall.isLiteralText()) {
					try {
												
						Boolean __showFlashPlayerInstall = (Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._showFlashPlayerInstall.getExpressionString(), 
											Boolean.class);
					
												comp.setShowFlashPlayerInstall(__showFlashPlayerInstall.booleanValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("showFlashPlayerInstall", this._showFlashPlayerInstall);
				}
			}
					    		 			 
						if (this._swimLaneClass != null) {
				if (this._swimLaneClass.isLiteralText()) {
					try {
												
						java.lang.String __swimLaneClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._swimLaneClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSwimLaneClass(__swimLaneClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("swimLaneClass", this._swimLaneClass);
				}
			}
					   		 			 
						if (this._timelineState != null) {
				if (this._timelineState.isLiteralText()) {
					try {
												
						com.exadel.siperian.model.ITimeLineState __timelineState = (com.exadel.siperian.model.ITimeLineState) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._timelineState.getExpressionString(), 
											com.exadel.siperian.model.ITimeLineState.class);
					
												comp.setTimelineState(__timelineState);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("timelineState", this._timelineState);
				}
			}
					   		 			 
						if (this._timlineStylesheet != null) {
				if (this._timlineStylesheet.isLiteralText()) {
					try {
												
						java.lang.String __timlineStylesheet = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._timlineStylesheet.getExpressionString(), 
											java.lang.String.class);
					
												comp.setTimlineStylesheet(__timlineStylesheet);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("timlineStylesheet", this._timlineStylesheet);
				}
			}
					   		 			 
						if (this._toDate != null) {
				if (this._toDate.isLiteralText()) {
					try {
												
						java.util.Date __toDate = (java.util.Date) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._toDate.getExpressionString(), 
											java.util.Date.class);
					
												comp.setToDate(__toDate);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("toDate", this._toDate);
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
					   		 			 
						if (this._zoomerIndent != null) {
				if (this._zoomerIndent.isLiteralText()) {
					try {
												
						java.lang.Integer __zoomerIndent = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._zoomerIndent.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setZoomerIndent(__zoomerIndent);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("zoomerIndent", this._zoomerIndent);
				}
			}
					     }
	
	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		// TODO Auto-generated method stub
		return "com.exadel.siperian.UITimeline";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.TimelineRenderer";
			}

}
