/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import com.exadel.siperian.model.BarTimelineState ;
import com.exadel.siperian.model.BarTimelineDataModel ;
import java.lang.Object ;
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
import com.exadel.siperian.component.html.HtmlBarTimeline;

public class BarTimelineTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

		// Fields
		 	  			  		  	  
		/*
		 * barTimelineState
		 * barTimelineState
		 */
		private ValueExpression _barTimelineState;
		/**
		 * barTimelineState
		 * Setter for barTimelineState
		 * @param barTimelineState - new value
		 */
		 public void setBarTimelineState( ValueExpression  __barTimelineState ){
			this._barTimelineState = __barTimelineState;
	     }
	  
	 	 		 		 	  			  		  	  
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
		 * locale
		 * ${prop.xmlEncodedDescription}
		 */
		private ValueExpression _locale;
		/**
		 * ${prop.xmlEncodedDescription}
		 * Setter for locale
		 * @param locale - new value
		 */
		 public void setLocale( ValueExpression  __locale ){
			this._locale = __locale;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * monthLabels
		 * monthLabels
		 */
		private ValueExpression _monthLabels;
		/**
		 * monthLabels
		 * Setter for monthLabels
		 * @param monthLabels - new value
		 */
		 public void setMonthLabels( ValueExpression  __monthLabels ){
			this._monthLabels = __monthLabels;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * monthLabelsShort
		 * monthLabelsShort
		 */
		private ValueExpression _monthLabelsShort;
		/**
		 * monthLabelsShort
		 * Setter for monthLabelsShort
		 * @param monthLabelsShort - new value
		 */
		 public void setMonthLabelsShort( ValueExpression  __monthLabelsShort ){
			this._monthLabelsShort = __monthLabelsShort;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * oncontextmenu
		 * js handler
		 */
		private ValueExpression _oncontextmenu;
		/**
		 * js handler
		 * Setter for oncontextmenu
		 * @param oncontextmenu - new value
		 */
		 public void setOncontextmenu( ValueExpression  __oncontextmenu ){
			this._oncontextmenu = __oncontextmenu;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * ontimelineinit
		 * js handler
		 */
		private ValueExpression _ontimelineinit;
		/**
		 * js handler
		 * Setter for ontimelineinit
		 * @param ontimelineinit - new value
		 */
		 public void setOntimelineinit( ValueExpression  __ontimelineinit ){
			this._ontimelineinit = __ontimelineinit;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onzoomerchange
		 * js handler
		 */
		private ValueExpression _onzoomerchange;
		/**
		 * js handler
		 * Setter for onzoomerchange
		 * @param onzoomerchange - new value
		 */
		 public void setOnzoomerchange( ValueExpression  __onzoomerchange ){
			this._onzoomerchange = __onzoomerchange;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * timelineStylesheet
		 * path to the file where template for the group tooltip located.
		 */
		private ValueExpression _timelineStylesheet;
		/**
		 * path to the file where template for the group tooltip located.
		 * Setter for timelineStylesheet
		 * @param timelineStylesheet - new value
		 */
		 public void setTimelineStylesheet( ValueExpression  __timelineStylesheet ){
			this._timelineStylesheet = __timelineStylesheet;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * weekDayLabels
		 * weekDayLabels
		 */
		private ValueExpression _weekDayLabels;
		/**
		 * weekDayLabels
		 * Setter for weekDayLabels
		 * @param weekDayLabels - new value
		 */
		 public void setWeekDayLabels( ValueExpression  __weekDayLabels ){
			this._weekDayLabels = __weekDayLabels;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * weekDayLabelsShort
		 * weekDayLabelsShort
		 */
		private ValueExpression _weekDayLabelsShort;
		/**
		 * weekDayLabelsShort
		 * Setter for weekDayLabelsShort
		 * @param weekDayLabelsShort - new value
		 */
		 public void setWeekDayLabelsShort( ValueExpression  __weekDayLabelsShort ){
			this._weekDayLabelsShort = __weekDayLabelsShort;
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
	  
	 	 	
	
    public void release()
    {
        // TODO Auto-generated method stub
        super.release();
        		 		    this._barTimelineState = null;
	 		 		 		    this._dataModel = null;
	 		 		 		    this._flashWmode = null;
	 		 		    this._header = null;
	 		 		    this._height = null;
	 		 		 		    this._locale = null;
	 		 		    this._monthLabels = null;
	 		 		    this._monthLabelsShort = null;
	 		 		    this._oncontextmenu = null;
	 		 		    this._ontimelineinit = null;
	 		 		    this._onzoomerchange = null;
	 		 		 		 		    this._timelineStylesheet = null;
	 		 		    this._weekDayLabels = null;
	 		 		    this._weekDayLabelsShort = null;
	 		 		    this._width = null;
	 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlBarTimeline comp = (HtmlBarTimeline) component;
 		 			 
						if (this._barTimelineState != null) {
				if (this._barTimelineState.isLiteralText()) {
					try {
												
						com.exadel.siperian.model.BarTimelineState __barTimelineState = (com.exadel.siperian.model.BarTimelineState) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._barTimelineState.getExpressionString(), 
											com.exadel.siperian.model.BarTimelineState.class);
					
												comp.setBarTimelineState(__barTimelineState);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("barTimelineState", this._barTimelineState);
				}
			}
					    		 			 
						if (this._dataModel != null) {
				if (this._dataModel.isLiteralText()) {
					try {
												
						com.exadel.siperian.model.BarTimelineDataModel __dataModel = (com.exadel.siperian.model.BarTimelineDataModel) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._dataModel.getExpressionString(), 
											com.exadel.siperian.model.BarTimelineDataModel.class);
					
												comp.setDataModel(__dataModel);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("dataModel", this._dataModel);
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
					    		 			 
						if (this._locale != null) {
				if (this._locale.isLiteralText()) {
					try {
												
						java.lang.Object __locale = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._locale.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setLocale(__locale);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("locale", this._locale);
				}
			}
					   		 			 
						if (this._monthLabels != null) {
				if (this._monthLabels.isLiteralText()) {
					try {
												
						java.lang.Object __monthLabels = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._monthLabels.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setMonthLabels(__monthLabels);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("monthLabels", this._monthLabels);
				}
			}
					   		 			 
						if (this._monthLabelsShort != null) {
				if (this._monthLabelsShort.isLiteralText()) {
					try {
												
						java.lang.Object __monthLabelsShort = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._monthLabelsShort.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setMonthLabelsShort(__monthLabelsShort);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("monthLabelsShort", this._monthLabelsShort);
				}
			}
					   		 			 
						if (this._oncontextmenu != null) {
				if (this._oncontextmenu.isLiteralText()) {
					try {
												
						java.lang.String __oncontextmenu = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._oncontextmenu.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOncontextmenu(__oncontextmenu);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("oncontextmenu", this._oncontextmenu);
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
					     		 			 
						if (this._timelineStylesheet != null) {
				if (this._timelineStylesheet.isLiteralText()) {
					try {
												
						java.lang.String __timelineStylesheet = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._timelineStylesheet.getExpressionString(), 
											java.lang.String.class);
					
												comp.setTimelineStylesheet(__timelineStylesheet);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("timelineStylesheet", this._timelineStylesheet);
				}
			}
					   		 			 
						if (this._weekDayLabels != null) {
				if (this._weekDayLabels.isLiteralText()) {
					try {
												
						java.lang.Object __weekDayLabels = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._weekDayLabels.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setWeekDayLabels(__weekDayLabels);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("weekDayLabels", this._weekDayLabels);
				}
			}
					   		 			 
						if (this._weekDayLabelsShort != null) {
				if (this._weekDayLabelsShort.isLiteralText()) {
					try {
												
						java.lang.Object __weekDayLabelsShort = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._weekDayLabelsShort.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setWeekDayLabelsShort(__weekDayLabelsShort);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("weekDayLabelsShort", this._weekDayLabelsShort);
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
		return "com.exadel.siperian.BarTimeline";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.BarTimelineRenderer";
			}

}
