/**
 * GENERATED FILE - DO NOT EDIT
 *
 */
package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import org.richfaces.model.selection.Selection ;
import javax.faces.convert.Converter ;
import java.lang.Object ;
import java.lang.Boolean ;
import java.util.List ;
import org.ajax4jsf.model.DataComponentState ;
import java.lang.Integer ;
import java.lang.String ;
import java.util.Set ;
import org.ajax4jsf.webapp.taglib.HtmlComponentTagBase ;
import java.util.Collection ;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.el.MethodExpression;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.el.ValueExpression;
import org.richfaces.webapp.taglib.MethodBindingMethodExpressionAdaptor;
import org.richfaces.webapp.taglib.ValueBindingValueExpressionAdaptor;
import com.exadel.siperian.component.html.HtmlExtendedDataTable;

public class ExtendedDataTableTag extends org.ajax4jsf.webapp.taglib.HtmlComponentTagBase {

		// Fields
		 	  			  		  	  
		/*
		 * activeClass
		 * CSS class for active row
		 */
		private ValueExpression _activeClass;
		/**
		 * CSS class for active row
		 * Setter for activeClass
		 * @param activeClass - new value
		 */
		 public void setActiveClass( ValueExpression  __activeClass ){
			this._activeClass = __activeClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * activeRowKey
		 * Request scope attribute under which the activeRowKey will
                                        be accessible
		 */
		private ValueExpression _activeRowKey;
		/**
		 * Request scope attribute under which the activeRowKey will
                                        be accessible
		 * Setter for activeRowKey
		 * @param activeRowKey - new value
		 */
		 public void setActiveRowKey( ValueExpression  __activeRowKey ){
			this._activeRowKey = __activeRowKey;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * ajaxKeys
		 * This attribute defines row keys that are updated after an
                                        AJAX request
		 */
		private ValueExpression _ajaxKeys;
		/**
		 * This attribute defines row keys that are updated after an
                                        AJAX request
		 * Setter for ajaxKeys
		 * @param ajaxKeys - new value
		 */
		 public void setAjaxKeys( ValueExpression  __ajaxKeys ){
			this._ajaxKeys = __ajaxKeys;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * align
		 * Deprecated. This attribute specifies the position of the table with respect to the document. 
            The possible values are "left", "center" and "right". The default value is "left".
		 */
		private ValueExpression _align;
		/**
		 * Deprecated. This attribute specifies the position of the table with respect to the document. 
            The possible values are "left", "center" and "right". The default value is "left".
		 * Setter for align
		 * @param align - new value
		 */
		 public void setAlign( ValueExpression  __align ){
			this._align = __align;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * allowCellSelection
		 * Allow or not cell selection by default false.
		 */
		private ValueExpression _allowCellSelection;
		/**
		 * Allow or not cell selection by default false.
		 * Setter for allowCellSelection
		 * @param allowCellSelection - new value
		 */
		 public void setAllowCellSelection( ValueExpression  __allowCellSelection ){
			this._allowCellSelection = __allowCellSelection;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * allowColumnResize
		 * false to disable columns resize functionality
		 */
		private ValueExpression _allowColumnResize;
		/**
		 * false to disable columns resize functionality
		 * Setter for allowColumnResize
		 * @param allowColumnResize - new value
		 */
		 public void setAllowColumnResize( ValueExpression  __allowColumnResize ){
			this._allowColumnResize = __allowColumnResize;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * allowColumnsHide
		 * false to disable hide columns functionality
		 */
		private ValueExpression _allowColumnsHide;
		/**
		 * false to disable hide columns functionality
		 * Setter for allowColumnsHide
		 * @param allowColumnsHide - new value
		 */
		 public void setAllowColumnsHide( ValueExpression  __allowColumnsHide ){
			this._allowColumnsHide = __allowColumnsHide;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * bgcolor
		 * Deprecated. This attribute sets the background color for the document body or table cells.
            
            This attribute sets the background color of the canvas for the document body (the BODY element) or for tables (the TABLE, TR, TH, and TD elements). Additional attributes for specifying text color can be used with the BODY element.
            
            This attribute has been deprecated in favor of style sheets for specifying background color information
		 */
		private ValueExpression _bgcolor;
		/**
		 * Deprecated. This attribute sets the background color for the document body or table cells.
            
            This attribute sets the background color of the canvas for the document body (the BODY element) or for tables (the TABLE, TR, TH, and TD elements). Additional attributes for specifying text color can be used with the BODY element.
            
            This attribute has been deprecated in favor of style sheets for specifying background color information
		 * Setter for bgcolor
		 * @param bgcolor - new value
		 */
		 public void setBgcolor( ValueExpression  __bgcolor ){
			this._bgcolor = __bgcolor;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * border
		 * This attributes specifies the width of the frame around a
                                        component
		 */
		private ValueExpression _border;
		/**
		 * This attributes specifies the width of the frame around a
                                        component
		 * Setter for border
		 * @param border - new value
		 */
		 public void setBorder( ValueExpression  __border ){
			this._border = __border;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * captionClass
		 * Space-separated list of CSS style class(es) that are be
                                        applied to caption for this component
		 */
		private ValueExpression _captionClass;
		/**
		 * Space-separated list of CSS style class(es) that are be
                                        applied to caption for this component
		 * Setter for captionClass
		 * @param captionClass - new value
		 */
		 public void setCaptionClass( ValueExpression  __captionClass ){
			this._captionClass = __captionClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * captionStyle
		 * CSS style(s) is/are to be applied to caption when this
                                        component is rendered
		 */
		private ValueExpression _captionStyle;
		/**
		 * CSS style(s) is/are to be applied to caption when this
                                        component is rendered
		 * Setter for captionStyle
		 * @param captionStyle - new value
		 */
		 public void setCaptionStyle( ValueExpression  __captionStyle ){
			this._captionStyle = __captionStyle;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * cellpadding
		 * This attribute specifies the amount of space between the
                                        border of the cell and its contents
		 */
		private ValueExpression _cellpadding;
		/**
		 * This attribute specifies the amount of space between the
                                        border of the cell and its contents
		 * Setter for cellpadding
		 * @param cellpadding - new value
		 */
		 public void setCellpadding( ValueExpression  __cellpadding ){
			this._cellpadding = __cellpadding;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * cellspacing
		 * This attribute specifies the amount of space between the
                                        border of the cell and its contents. The attribute also
                                        specifies the amount of space to leave between cells
		 */
		private ValueExpression _cellspacing;
		/**
		 * This attribute specifies the amount of space between the
                                        border of the cell and its contents. The attribute also
                                        specifies the amount of space to leave between cells
		 * Setter for cellspacing
		 * @param cellspacing - new value
		 */
		 public void setCellspacing( ValueExpression  __cellspacing ){
			this._cellspacing = __cellspacing;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * columnClasses
		 * Comma-delimited list of CSS style classes that are be applied to the columns of this table. A space separated list of classes may also be specified for any individual column. If the number of elements in this list is less than the number of columns specified in the "columns" attribute, no "class" attribute is output for each column greater than the number of elements in the list. If the number of elements in the list is greater than the number of columns specified in the "columns" attribute, the elements at the position in the list after the value of the "columns" attribute are ignored
		 */
		private ValueExpression _columnClasses;
		/**
		 * Comma-delimited list of CSS style classes that are be applied to the columns of this table. A space separated list of classes may also be specified for any individual column. If the number of elements in this list is less than the number of columns specified in the "columns" attribute, no "class" attribute is output for each column greater than the number of elements in the list. If the number of elements in the list is greater than the number of columns specified in the "columns" attribute, the elements at the position in the list after the value of the "columns" attribute are ignored
		 * Setter for columnClasses
		 * @param columnClasses - new value
		 */
		 public void setColumnClasses( ValueExpression  __columnClasses ){
			this._columnClasses = __columnClasses;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * componentState
		 * It defines EL-binding for a component state for saving or
                                        redefinition
		 */
		private ValueExpression _componentState;
		/**
		 * It defines EL-binding for a component state for saving or
                                        redefinition
		 * Setter for componentState
		 * @param componentState - new value
		 */
		 public void setComponentState( ValueExpression  __componentState ){
			this._componentState = __componentState;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * countOfForceSort
		 * countOfForceSort
		 */
		private ValueExpression _countOfForceSort;
		/**
		 * countOfForceSort
		 * Setter for countOfForceSort
		 * @param countOfForceSort - new value
		 */
		 public void setCountOfForceSort( ValueExpression  __countOfForceSort ){
			this._countOfForceSort = __countOfForceSort;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * dndSupported
		 * false to hide the scroll
		 */
		private ValueExpression _dndSupported;
		/**
		 * false to hide the scroll
		 * Setter for dndSupported
		 * @param dndSupported - new value
		 */
		 public void setDndSupported( ValueExpression  __dndSupported ){
			this._dndSupported = __dndSupported;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * first
		 * A zero-relative row number of the first row to display
		 */
		private ValueExpression _first;
		/**
		 * A zero-relative row number of the first row to display
		 * Setter for first
		 * @param first - new value
		 */
		 public void setFirst( ValueExpression  __first ){
			this._first = __first;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * footerClass
		 * Space-separated list of CSS style class(es) that are be
                                        applied to footer for this component
		 */
		private ValueExpression _footerClass;
		/**
		 * Space-separated list of CSS style class(es) that are be
                                        applied to footer for this component
		 * Setter for footerClass
		 * @param footerClass - new value
		 */
		 public void setFooterClass( ValueExpression  __footerClass ){
			this._footerClass = __footerClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * forseSorting
		 * Default true rerender after sorting
		 */
		private ValueExpression _forseSorting;
		/**
		 * Default true rerender after sorting
		 * Setter for forseSorting
		 * @param forseSorting - new value
		 */
		 public void setForseSorting( ValueExpression  __forseSorting ){
			this._forseSorting = __forseSorting;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * frame
		 * This attribute specifies which sides of the frame surrounding a table will be visible. Possible values:  "void", "above", "below", "hsides", "lhs", "rhs", "vsides", "box" and "border".
        The default value is "void".
		 */
		private ValueExpression _frame;
		/**
		 * This attribute specifies which sides of the frame surrounding a table will be visible. Possible values:  "void", "above", "below", "hsides", "lhs", "rhs", "vsides", "box" and "border".
        The default value is "void".
		 * Setter for frame
		 * @param frame - new value
		 */
		 public void setFrame( ValueExpression  __frame ){
			this._frame = __frame;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * frozenColumns
		 * Frozen columns number
		 */
		private ValueExpression _frozenColumns;
		/**
		 * Frozen columns number
		 * Setter for frozenColumns
		 * @param frozenColumns - new value
		 */
		 public void setFrozenColumns( ValueExpression  __frozenColumns ){
			this._frozenColumns = __frozenColumns;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * groupColumnWidth
		 * 
		 */
		private ValueExpression _groupColumnWidth;
		/**
		 * 
		 * Setter for groupColumnWidth
		 * @param groupColumnWidth - new value
		 */
		 public void setGroupColumnWidth( ValueExpression  __groupColumnWidth ){
			this._groupColumnWidth = __groupColumnWidth;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * groupingColumn
		 * The information in the table wiil be grouped by the
                                        information in the column, which id is provided with this
                                        attribute.
		 */
		private ValueExpression _groupingColumn;
		/**
		 * The information in the table wiil be grouped by the
                                        information in the column, which id is provided with this
                                        attribute.
		 * Setter for groupingColumn
		 * @param groupingColumn - new value
		 */
		 public void setGroupingColumn( ValueExpression  __groupingColumn ){
			this._groupingColumn = __groupingColumn;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * headerClass
		 * Space-separated list of CSS style class(es) that are be
                                        applied to header for this component
		 */
		private ValueExpression _headerClass;
		/**
		 * Space-separated list of CSS style class(es) that are be
                                        applied to header for this component
		 * Setter for headerClass
		 * @param headerClass - new value
		 */
		 public void setHeaderClass( ValueExpression  __headerClass ){
			this._headerClass = __headerClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * height
		 * Defines a height of the component. Default value is 500px
		 */
		private ValueExpression _height;
		/**
		 * Defines a height of the component. Default value is 500px
		 * Setter for height
		 * @param height - new value
		 */
		 public void setHeight( ValueExpression  __height ){
			this._height = __height;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * horizontalScrolling
		 * Default true rerender after sorting
		 */
		private ValueExpression _horizontalScrolling;
		/**
		 * Default true rerender after sorting
		 * Setter for horizontalScrolling
		 * @param horizontalScrolling - new value
		 */
		 public void setHorizontalScrolling( ValueExpression  __horizontalScrolling ){
			this._horizontalScrolling = __horizontalScrolling;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * hoverRowClass
		 * CSS class for row with mouse over
		 */
		private ValueExpression _hoverRowClass;
		/**
		 * CSS class for row with mouse over
		 * Setter for hoverRowClass
		 * @param hoverRowClass - new value
		 */
		 public void setHoverRowClass( ValueExpression  __hoverRowClass ){
			this._hoverRowClass = __hoverRowClass;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * inlineEditing
		 * ${prop.xmlEncodedDescription}
		 */
		private ValueExpression _inlineEditing;
		/**
		 * ${prop.xmlEncodedDescription}
		 * Setter for inlineEditing
		 * @param inlineEditing - new value
		 */
		 public void setInlineEditing( ValueExpression  __inlineEditing ){
			this._inlineEditing = __inlineEditing;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * onRowClick
		 * HTML: a script expression; a pointer button is clicked
                                        on row
		 */
		private ValueExpression _onRowClick;
		/**
		 * HTML: a script expression; a pointer button is clicked
                                        on row
		 * Setter for onRowClick
		 * @param onRowClick - new value
		 */
		 public void setOnRowClick( ValueExpression  __onRowClick ){
			this._onRowClick = __onRowClick;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onRowContextMenu
		 * HTML: a script expression; a pointer button is clicked
                                        on row
		 */
		private ValueExpression _onRowContextMenu;
		/**
		 * HTML: a script expression; a pointer button is clicked
                                        on row
		 * Setter for onRowContextMenu
		 * @param onRowContextMenu - new value
		 */
		 public void setOnRowContextMenu( ValueExpression  __onRowContextMenu ){
			this._onRowContextMenu = __onRowContextMenu;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onRowDblClick
		 * HTML: a script expression; a pointer button is
                                        double-clicked on row
		 */
		private ValueExpression _onRowDblClick;
		/**
		 * HTML: a script expression; a pointer button is
                                        double-clicked on row
		 * Setter for onRowDblClick
		 * @param onRowDblClick - new value
		 */
		 public void setOnRowDblClick( ValueExpression  __onRowDblClick ){
			this._onRowDblClick = __onRowDblClick;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onRowMouseDown
		 * HTML: script expression; a pointer button is pressed down
                                        on row
		 */
		private ValueExpression _onRowMouseDown;
		/**
		 * HTML: script expression; a pointer button is pressed down
                                        on row
		 * Setter for onRowMouseDown
		 * @param onRowMouseDown - new value
		 */
		 public void setOnRowMouseDown( ValueExpression  __onRowMouseDown ){
			this._onRowMouseDown = __onRowMouseDown;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onRowMouseMove
		 * HTML: a script expression; a pointer is moved within of
                                        row
		 */
		private ValueExpression _onRowMouseMove;
		/**
		 * HTML: a script expression; a pointer is moved within of
                                        row
		 * Setter for onRowMouseMove
		 * @param onRowMouseMove - new value
		 */
		 public void setOnRowMouseMove( ValueExpression  __onRowMouseMove ){
			this._onRowMouseMove = __onRowMouseMove;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onRowMouseOut
		 * HTML: a script expression; a pointer is moved away of row
		 */
		private ValueExpression _onRowMouseOut;
		/**
		 * HTML: a script expression; a pointer is moved away of row
		 * Setter for onRowMouseOut
		 * @param onRowMouseOut - new value
		 */
		 public void setOnRowMouseOut( ValueExpression  __onRowMouseOut ){
			this._onRowMouseOut = __onRowMouseOut;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onRowMouseOver
		 * HTML: a script expression; a pointer is moved onto of row
		 */
		private ValueExpression _onRowMouseOver;
		/**
		 * HTML: a script expression; a pointer is moved onto of row
		 * Setter for onRowMouseOver
		 * @param onRowMouseOver - new value
		 */
		 public void setOnRowMouseOver( ValueExpression  __onRowMouseOver ){
			this._onRowMouseOver = __onRowMouseOver;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onRowMouseUp
		 * HTML: script expression; a pointer button is released on
                                        row
		 */
		private ValueExpression _onRowMouseUp;
		/**
		 * HTML: script expression; a pointer button is released on
                                        row
		 * Setter for onRowMouseUp
		 * @param onRowMouseUp - new value
		 */
		 public void setOnRowMouseUp( ValueExpression  __onRowMouseUp ){
			this._onRowMouseUp = __onRowMouseUp;
	     }
	  
	 	 		 		 		 		 		 		 	  			  		  	  
		/*
		 * onlayoutupdate
		 * Handler for update layout event
		 */
		private ValueExpression _onlayoutupdate;
		/**
		 * Handler for update layout event
		 * Setter for onlayoutupdate
		 * @param onlayoutupdate - new value
		 */
		 public void setOnlayoutupdate( ValueExpression  __onlayoutupdate ){
			this._onlayoutupdate = __onlayoutupdate;
	     }
	  
	 	 		 		 		 		 		 		 	  			  		  	  
		/*
		 * onscroll
		 * HTML: a script expression; table content is scrolled
		 */
		private ValueExpression _onscroll;
		/**
		 * HTML: a script expression; table content is scrolled
		 * Setter for onscroll
		 * @param onscroll - new value
		 */
		 public void setOnscroll( ValueExpression  __onscroll ){
			this._onscroll = __onscroll;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * onselectionchange
		 * HTML: script expression to invoke on changing of rows
                                        selection
		 */
		private ValueExpression _onselectionchange;
		/**
		 * HTML: script expression to invoke on changing of rows
                                        selection
		 * Setter for onselectionchange
		 * @param onselectionchange - new value
		 */
		 public void setOnselectionchange( ValueExpression  __onselectionchange ){
			this._onselectionchange = __onselectionchange;
	     }
	  
	 	 		 		 		 		 		 		 	  			  		  	  
		/*
		 * rowClasses
		 * A comma-delimited list of CSS style classes that is applied to popup table rows. A space separated list of classes may also be specified for any individual row. The styles are applied, in turn, to each row in the table. For example, if the list has two elements, the first style class in the list is applied to the first row, the second to the second row, the first to the third row, the second to the fourth row, etc. In other words, we keep iterating through the list until we reach the end, and then we start at the beginning again
		 */
		private ValueExpression _rowClasses;
		/**
		 * A comma-delimited list of CSS style classes that is applied to popup table rows. A space separated list of classes may also be specified for any individual row. The styles are applied, in turn, to each row in the table. For example, if the list has two elements, the first style class in the list is applied to the first row, the second to the second row, the first to the third row, the second to the fourth row, etc. In other words, we keep iterating through the list until we reach the end, and then we start at the beginning again
		 * Setter for rowClasses
		 * @param rowClasses - new value
		 */
		 public void setRowClasses( ValueExpression  __rowClasses ){
			this._rowClasses = __rowClasses;
	     }
	  
	 	 		 		 		 		 		 	  			  		  	  
		/*
		 * rowKeyConverter
		 * Converter for a row key object
		 */
		private ValueExpression _rowKeyConverter;
		/**
		 * Converter for a row key object
		 * Setter for rowKeyConverter
		 * @param rowKeyConverter - new value
		 */
		 public void setRowKeyConverter( ValueExpression  __rowKeyConverter ){
			this._rowKeyConverter = __rowKeyConverter;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * rowKeyVar
		 * The attribute provides access to a row key in a Request
                                        scope
		 */
		private ValueExpression _rowKeyVar;
		/**
		 * The attribute provides access to a row key in a Request
                                        scope
		 * Setter for rowKeyVar
		 * @param rowKeyVar - new value
		 */
		 public void setRowKeyVar( ValueExpression  __rowKeyVar ){
			this._rowKeyVar = __rowKeyVar;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * rows
		 * A number of rows to display, or zero for all remaining
            rows in the table
		 */
		private ValueExpression _rows;
		/**
		 * A number of rows to display, or zero for all remaining
            rows in the table
		 * Setter for rows
		 * @param rows - new value
		 */
		 public void setRows( ValueExpression  __rows ){
			this._rows = __rows;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * rules
		 * This attribute specifies which rules will appear between cells within a table. The rendering of rules is user agent dependent. Possible values:
            
            * none: No rules. This is the default value.
            * groups: Rules will appear between row groups (see THEAD, TFOOT, and TBODY) and column groups (see COLGROUP and COL) only.
            * rows: Rules will appear between rows only.
            * cols: Rules will appear between columns only.
            * all: Rules will appear between all rows and columns
		 */
		private ValueExpression _rules;
		/**
		 * This attribute specifies which rules will appear between cells within a table. The rendering of rules is user agent dependent. Possible values:
            
            * none: No rules. This is the default value.
            * groups: Rules will appear between row groups (see THEAD, TFOOT, and TBODY) and column groups (see COLGROUP and COL) only.
            * rows: Rules will appear between rows only.
            * cols: Rules will appear between columns only.
            * all: Rules will appear between all rows and columns
		 * Setter for rules
		 * @param rules - new value
		 */
		 public void setRules( ValueExpression  __rules ){
			this._rules = __rules;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * scrollable
		 * false to hide the scroll
		 */
		private ValueExpression _scrollable;
		/**
		 * false to hide the scroll
		 * Setter for scrollable
		 * @param scrollable - new value
		 */
		 public void setScrollable( ValueExpression  __scrollable ){
			this._scrollable = __scrollable;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * selectedCellClass
		 * Css style class that define styles for highlighted cell
		 */
		private ValueExpression _selectedCellClass;
		/**
		 * Css style class that define styles for highlighted cell
		 * Setter for selectedCellClass
		 * @param selectedCellClass - new value
		 */
		 public void setSelectedCellClass( ValueExpression  __selectedCellClass ){
			this._selectedCellClass = __selectedCellClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * selectedClass
		 * CSS class for selected rows
		 */
		private ValueExpression _selectedClass;
		/**
		 * CSS class for selected rows
		 * Setter for selectedClass
		 * @param selectedClass - new value
		 */
		 public void setSelectedClass( ValueExpression  __selectedClass ){
			this._selectedClass = __selectedClass;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * selection
		 * Value binding representing selected rows
		 */
		private ValueExpression _selection;
		/**
		 * Value binding representing selected rows
		 * Setter for selection
		 * @param selection - new value
		 */
		 public void setSelection( ValueExpression  __selection ){
			this._selection = __selection;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * selectionMode
		 * single [default]: Single row can be selected. multi:
                                        Multiple rows can be selected. none: no rows can be
                                        selected.
		 */
		private ValueExpression _selectionMode;
		/**
		 * single [default]: Single row can be selected. multi:
                                        Multiple rows can be selected. none: no rows can be
                                        selected.
		 * Setter for selectionMode
		 * @param selectionMode - new value
		 */
		 public void setSelectionMode( ValueExpression  __selectionMode ){
			this._selectionMode = __selectionMode;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * sortMode
		 * Defines mode of sorting. Possible values are 'single'
                                        for sorting of one column and 'multi' for some.
		 */
		private ValueExpression _sortMode;
		/**
		 * Defines mode of sorting. Possible values are 'single'
                                        for sorting of one column and 'multi' for some.
		 * Setter for sortMode
		 * @param sortMode - new value
		 */
		 public void setSortMode( ValueExpression  __sortMode ){
			this._sortMode = __sortMode;
	     }
	  
	 	 		 	  			  		  	  
		/*
		 * sortPriority
		 * Defines a set of column ids in the order the columns
                                        could be set
		 */
		private ValueExpression _sortPriority;
		/**
		 * Defines a set of column ids in the order the columns
                                        could be set
		 * Setter for sortPriority
		 * @param sortPriority - new value
		 */
		 public void setSortPriority( ValueExpression  __sortPriority ){
			this._sortPriority = __sortPriority;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * stateVar
		 * The attribute provides access to a component state on
                                        the client side
		 */
		private ValueExpression _stateVar;
		/**
		 * The attribute provides access to a component state on
                                        the client side
		 * Setter for stateVar
		 * @param stateVar - new value
		 */
		 public void setStateVar( ValueExpression  __stateVar ){
			this._stateVar = __stateVar;
	     }
	  
	 	 		 		 		 	  			  		  	  
		/*
		 * subRows
		 * ${prop.xmlEncodedDescription}
		 */
		private ValueExpression _subRows;
		/**
		 * ${prop.xmlEncodedDescription}
		 * Setter for subRows
		 * @param subRows - new value
		 */
		 public void setSubRows( ValueExpression  __subRows ){
			this._subRows = __subRows;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * tableState
		 * ValueBinding pointing at a property of a String to hold
                                        table state
		 */
		private ValueExpression _tableState;
		/**
		 * ValueBinding pointing at a property of a String to hold
                                        table state
		 * Setter for tableState
		 * @param tableState - new value
		 */
		 public void setTableState( ValueExpression  __tableState ){
			this._tableState = __tableState;
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
	  
	 	 		 	  			  		  	  
		/*
		 * var
		 * A request-scope attribute via which the data object for the
            current row will be used when iterating
		 */
		private String _var;
		/**
		 * A request-scope attribute via which the data object for the
            current row will be used when iterating
		 * Setter for var
		 * @param var - new value
		 */
		 public void setVar( String  __var ){
			this._var = __var;
	     }
	  
	 	 		 		 	  			  		  	  
		/*
		 * width
		 * This attribute specifies the desired width of the entire table and is intended for visual user agents. When the value is percentage value, the value is relative to the user agent's available horizontal space. In the absence of any width specification, table width is determined by the user agent
		 */
		private ValueExpression _width;
		/**
		 * This attribute specifies the desired width of the entire table and is intended for visual user agents. When the value is percentage value, the value is relative to the user agent's available horizontal space. In the absence of any width specification, table width is determined by the user agent
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
        		 		    this._activeClass = null;
	 		 		    this._activeRowKey = null;
	 		 		    this._ajaxKeys = null;
	 		 		    this._align = null;
	 		 		 		    this._allowCellSelection = null;
	 		 		    this._allowColumnResize = null;
	 		 		    this._allowColumnsHide = null;
	 		 		    this._bgcolor = null;
	 		 		 		    this._border = null;
	 		 		    this._captionClass = null;
	 		 		    this._captionStyle = null;
	 		 		 		    this._cellpadding = null;
	 		 		    this._cellspacing = null;
	 		 		 		    this._columnClasses = null;
	 		 		    this._componentState = null;
	 		 		    this._countOfForceSort = null;
	 		 		 		    this._dndSupported = null;
	 		 		 		 		    this._first = null;
	 		 		 		    this._footerClass = null;
	 		 		    this._forseSorting = null;
	 		 		    this._frame = null;
	 		 		    this._frozenColumns = null;
	 		 		    this._groupColumnWidth = null;
	 		 		    this._groupingColumn = null;
	 		 		 		 		    this._headerClass = null;
	 		 		    this._height = null;
	 		 		    this._horizontalScrolling = null;
	 		 		    this._hoverRowClass = null;
	 		 		 		    this._inlineEditing = null;
	 		 		 		    this._onRowClick = null;
	 		 		    this._onRowContextMenu = null;
	 		 		    this._onRowDblClick = null;
	 		 		    this._onRowMouseDown = null;
	 		 		    this._onRowMouseMove = null;
	 		 		    this._onRowMouseOut = null;
	 		 		    this._onRowMouseOver = null;
	 		 		    this._onRowMouseUp = null;
	 		 		 		 		 		 		 		    this._onlayoutupdate = null;
	 		 		 		 		 		 		 		    this._onscroll = null;
	 		 		    this._onselectionchange = null;
	 		 		 		 		 		 		 		    this._rowClasses = null;
	 		 		 		 		 		 		    this._rowKeyConverter = null;
	 		 		    this._rowKeyVar = null;
	 		 		    this._rows = null;
	 		 		    this._rules = null;
	 		 		 		    this._scrollable = null;
	 		 		    this._selectedCellClass = null;
	 		 		    this._selectedClass = null;
	 		 		    this._selection = null;
	 		 		    this._selectionMode = null;
	 		 		 		    this._sortMode = null;
	 		 		    this._sortPriority = null;
	 		 		 		 		    this._stateVar = null;
	 		 		 		 		    this._subRows = null;
	 		 		 		    this._tableState = null;
	 		 		 		    this._value = null;
	 		 		    this._var = null;
	 		 		 		    this._width = null;
	 		}
	
    /* (non-Javadoc)
     * @see org.ajax4jsf.components.taglib.html.HtmlCommandButtonTagBase#setProperties(javax.faces.component.UIComponent)
     */
    protected void setProperties(UIComponent component)
    {
        // TODO Auto-generated method stub
        super.setProperties(component);
		HtmlExtendedDataTable comp = (HtmlExtendedDataTable) component;
 		 			 
						if (this._activeClass != null) {
				if (this._activeClass.isLiteralText()) {
					try {
												
						java.lang.String __activeClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._activeClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setActiveClass(__activeClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("activeClass", this._activeClass);
				}
			}
					   		 			 
						if (this._activeRowKey != null) {
				if (this._activeRowKey.isLiteralText()) {
					try {
												
						java.lang.Object __activeRowKey = (java.lang.Object) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._activeRowKey.getExpressionString(), 
											java.lang.Object.class);
					
												comp.setActiveRowKey(__activeRowKey);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("activeRowKey", this._activeRowKey);
				}
			}
					   		 			 
						if (this._ajaxKeys != null) {
				if (this._ajaxKeys.isLiteralText()) {
					try {
												
						java.util.Set __ajaxKeys = (java.util.Set) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._ajaxKeys.getExpressionString(), 
											java.util.Set.class);
					
												comp.setAjaxKeys(__ajaxKeys);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("ajaxKeys", this._ajaxKeys);
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
					    		 			 
						if (this._allowCellSelection != null) {
				if (this._allowCellSelection.isLiteralText()) {
					try {
												
						java.lang.Boolean __allowCellSelection = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._allowCellSelection.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setAllowCellSelection(__allowCellSelection);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("allowCellSelection", this._allowCellSelection);
				}
			}
					   		 			 
						if (this._allowColumnResize != null) {
				if (this._allowColumnResize.isLiteralText()) {
					try {
												
						java.lang.Boolean __allowColumnResize = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._allowColumnResize.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setAllowColumnResize(__allowColumnResize);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("allowColumnResize", this._allowColumnResize);
				}
			}
					   		 			 
						if (this._allowColumnsHide != null) {
				if (this._allowColumnsHide.isLiteralText()) {
					try {
												
						java.lang.Boolean __allowColumnsHide = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._allowColumnsHide.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setAllowColumnsHide(__allowColumnsHide);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("allowColumnsHide", this._allowColumnsHide);
				}
			}
					   		 			 
						if (this._bgcolor != null) {
				if (this._bgcolor.isLiteralText()) {
					try {
												
						java.lang.String __bgcolor = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._bgcolor.getExpressionString(), 
											java.lang.String.class);
					
												comp.setBgcolor(__bgcolor);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("bgcolor", this._bgcolor);
				}
			}
					    		 			 
						if (this._border != null) {
				if (this._border.isLiteralText()) {
					try {
												
						java.lang.String __border = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._border.getExpressionString(), 
											java.lang.String.class);
					
												comp.setBorder(__border);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("border", this._border);
				}
			}
					   		 			 
						if (this._captionClass != null) {
				if (this._captionClass.isLiteralText()) {
					try {
												
						java.lang.String __captionClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._captionClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setCaptionClass(__captionClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("captionClass", this._captionClass);
				}
			}
					   		 			 
						if (this._captionStyle != null) {
				if (this._captionStyle.isLiteralText()) {
					try {
												
						java.lang.String __captionStyle = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._captionStyle.getExpressionString(), 
											java.lang.String.class);
					
												comp.setCaptionStyle(__captionStyle);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("captionStyle", this._captionStyle);
				}
			}
					    		 			 
						if (this._cellpadding != null) {
				if (this._cellpadding.isLiteralText()) {
					try {
												
						java.lang.String __cellpadding = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._cellpadding.getExpressionString(), 
											java.lang.String.class);
					
												comp.setCellpadding(__cellpadding);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("cellpadding", this._cellpadding);
				}
			}
					   		 			 
						if (this._cellspacing != null) {
				if (this._cellspacing.isLiteralText()) {
					try {
												
						java.lang.String __cellspacing = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._cellspacing.getExpressionString(), 
											java.lang.String.class);
					
												comp.setCellspacing(__cellspacing);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("cellspacing", this._cellspacing);
				}
			}
					    		 			 
						if (this._columnClasses != null) {
				if (this._columnClasses.isLiteralText()) {
					try {
												
						java.lang.String __columnClasses = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._columnClasses.getExpressionString(), 
											java.lang.String.class);
					
												comp.setColumnClasses(__columnClasses);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("columnClasses", this._columnClasses);
				}
			}
					   		 			 
						if (this._componentState != null) {
				if (this._componentState.isLiteralText()) {
					try {
												
						org.ajax4jsf.model.DataComponentState __componentState = (org.ajax4jsf.model.DataComponentState) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._componentState.getExpressionString(), 
											org.ajax4jsf.model.DataComponentState.class);
					
												comp.setComponentState(__componentState);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("componentState", this._componentState);
				}
			}
					   		 			 
						if (this._countOfForceSort != null) {
				if (this._countOfForceSort.isLiteralText()) {
					try {
												
						java.lang.String __countOfForceSort = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._countOfForceSort.getExpressionString(), 
											java.lang.String.class);
					
												comp.setCountOfForceSort(__countOfForceSort);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("countOfForceSort", this._countOfForceSort);
				}
			}
					    		 			 
						if (this._dndSupported != null) {
				if (this._dndSupported.isLiteralText()) {
					try {
												
						java.lang.Boolean __dndSupported = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._dndSupported.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setDndSupported(__dndSupported);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("dndSupported", this._dndSupported);
				}
			}
					     		 			 
						if (this._first != null) {
				if (this._first.isLiteralText()) {
					try {
												
						Integer __first = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._first.getExpressionString(), 
											Integer.class);
					
												comp.setFirst(__first.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("first", this._first);
				}
			}
					    		 			 
						if (this._footerClass != null) {
				if (this._footerClass.isLiteralText()) {
					try {
												
						java.lang.String __footerClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._footerClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setFooterClass(__footerClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("footerClass", this._footerClass);
				}
			}
					   		 			 
						if (this._forseSorting != null) {
				if (this._forseSorting.isLiteralText()) {
					try {
												
						java.lang.Boolean __forseSorting = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._forseSorting.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setForseSorting(__forseSorting);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("forseSorting", this._forseSorting);
				}
			}
					   		 			 
						if (this._frame != null) {
				if (this._frame.isLiteralText()) {
					try {
												
						java.lang.String __frame = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._frame.getExpressionString(), 
											java.lang.String.class);
					
												comp.setFrame(__frame);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("frame", this._frame);
				}
			}
					   		 			 
						if (this._frozenColumns != null) {
				if (this._frozenColumns.isLiteralText()) {
					try {
												
						java.lang.Integer __frozenColumns = (java.lang.Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._frozenColumns.getExpressionString(), 
											java.lang.Integer.class);
					
												comp.setFrozenColumns(__frozenColumns);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("frozenColumns", this._frozenColumns);
				}
			}
					   		 			 
						if (this._groupColumnWidth != null) {
				if (this._groupColumnWidth.isLiteralText()) {
					try {
												
						java.lang.String __groupColumnWidth = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._groupColumnWidth.getExpressionString(), 
											java.lang.String.class);
					
												comp.setGroupColumnWidth(__groupColumnWidth);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("groupColumnWidth", this._groupColumnWidth);
				}
			}
					   		 			 
						if (this._groupingColumn != null) {
				if (this._groupingColumn.isLiteralText()) {
					try {
												
						java.lang.String __groupingColumn = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._groupingColumn.getExpressionString(), 
											java.lang.String.class);
					
												comp.setGroupingColumn(__groupingColumn);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("groupingColumn", this._groupingColumn);
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
					   		 			 
						if (this._horizontalScrolling != null) {
				if (this._horizontalScrolling.isLiteralText()) {
					try {
												
						java.lang.Boolean __horizontalScrolling = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._horizontalScrolling.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setHorizontalScrolling(__horizontalScrolling);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("horizontalScrolling", this._horizontalScrolling);
				}
			}
					   		 			 
						if (this._hoverRowClass != null) {
				if (this._hoverRowClass.isLiteralText()) {
					try {
												
						java.lang.String __hoverRowClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._hoverRowClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setHoverRowClass(__hoverRowClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("hoverRowClass", this._hoverRowClass);
				}
			}
					    		 			 
						if (this._inlineEditing != null) {
				if (this._inlineEditing.isLiteralText()) {
					try {
												
						java.lang.Boolean __inlineEditing = (java.lang.Boolean) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._inlineEditing.getExpressionString(), 
											java.lang.Boolean.class);
					
												comp.setInlineEditing(__inlineEditing);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("inlineEditing", this._inlineEditing);
				}
			}
					    		 			 
						if (this._onRowClick != null) {
				if (this._onRowClick.isLiteralText()) {
					try {
												
						java.lang.String __onRowClick = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onRowClick.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnRowClick(__onRowClick);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onRowClick", this._onRowClick);
				}
			}
					   		 			 
						if (this._onRowContextMenu != null) {
				if (this._onRowContextMenu.isLiteralText()) {
					try {
												
						java.lang.String __onRowContextMenu = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onRowContextMenu.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnRowContextMenu(__onRowContextMenu);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onRowContextMenu", this._onRowContextMenu);
				}
			}
					   		 			 
						if (this._onRowDblClick != null) {
				if (this._onRowDblClick.isLiteralText()) {
					try {
												
						java.lang.String __onRowDblClick = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onRowDblClick.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnRowDblClick(__onRowDblClick);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onRowDblClick", this._onRowDblClick);
				}
			}
					   		 			 
						if (this._onRowMouseDown != null) {
				if (this._onRowMouseDown.isLiteralText()) {
					try {
												
						java.lang.String __onRowMouseDown = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onRowMouseDown.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnRowMouseDown(__onRowMouseDown);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onRowMouseDown", this._onRowMouseDown);
				}
			}
					   		 			 
						if (this._onRowMouseMove != null) {
				if (this._onRowMouseMove.isLiteralText()) {
					try {
												
						java.lang.String __onRowMouseMove = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onRowMouseMove.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnRowMouseMove(__onRowMouseMove);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onRowMouseMove", this._onRowMouseMove);
				}
			}
					   		 			 
						if (this._onRowMouseOut != null) {
				if (this._onRowMouseOut.isLiteralText()) {
					try {
												
						java.lang.String __onRowMouseOut = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onRowMouseOut.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnRowMouseOut(__onRowMouseOut);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onRowMouseOut", this._onRowMouseOut);
				}
			}
					   		 			 
						if (this._onRowMouseOver != null) {
				if (this._onRowMouseOver.isLiteralText()) {
					try {
												
						java.lang.String __onRowMouseOver = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onRowMouseOver.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnRowMouseOver(__onRowMouseOver);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onRowMouseOver", this._onRowMouseOver);
				}
			}
					   		 			 
						if (this._onRowMouseUp != null) {
				if (this._onRowMouseUp.isLiteralText()) {
					try {
												
						java.lang.String __onRowMouseUp = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onRowMouseUp.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnRowMouseUp(__onRowMouseUp);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onRowMouseUp", this._onRowMouseUp);
				}
			}
					        		 			 
						if (this._onlayoutupdate != null) {
				if (this._onlayoutupdate.isLiteralText()) {
					try {
												
						java.lang.String __onlayoutupdate = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onlayoutupdate.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnlayoutupdate(__onlayoutupdate);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onlayoutupdate", this._onlayoutupdate);
				}
			}
					        		 			 
						if (this._onscroll != null) {
				if (this._onscroll.isLiteralText()) {
					try {
												
						java.lang.String __onscroll = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onscroll.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnscroll(__onscroll);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onscroll", this._onscroll);
				}
			}
					   		 			 
						if (this._onselectionchange != null) {
				if (this._onselectionchange.isLiteralText()) {
					try {
												
						java.lang.String __onselectionchange = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._onselectionchange.getExpressionString(), 
											java.lang.String.class);
					
												comp.setOnselectionchange(__onselectionchange);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("onselectionchange", this._onselectionchange);
				}
			}
					        		 			 
						if (this._rowClasses != null) {
				if (this._rowClasses.isLiteralText()) {
					try {
												
						java.lang.String __rowClasses = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._rowClasses.getExpressionString(), 
											java.lang.String.class);
					
												comp.setRowClasses(__rowClasses);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("rowClasses", this._rowClasses);
				}
			}
					       		 			setRowKeyConverterProperty(comp, this._rowKeyConverter);
		   		 			 
						if (this._rowKeyVar != null) {
				if (this._rowKeyVar.isLiteralText()) {
					try {
												
						java.lang.String __rowKeyVar = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._rowKeyVar.getExpressionString(), 
											java.lang.String.class);
					
												comp.setRowKeyVar(__rowKeyVar);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("rowKeyVar", this._rowKeyVar);
				}
			}
					   		 			 
						if (this._rows != null) {
				if (this._rows.isLiteralText()) {
					try {
												
						Integer __rows = (Integer) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._rows.getExpressionString(), 
											Integer.class);
					
												comp.setRows(__rows.intValue());
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("rows", this._rows);
				}
			}
					   		 			 
						if (this._rules != null) {
				if (this._rules.isLiteralText()) {
					try {
												
						java.lang.String __rules = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._rules.getExpressionString(), 
											java.lang.String.class);
					
												comp.setRules(__rules);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("rules", this._rules);
				}
			}
					    		 			 
						if (this._scrollable != null) {
				if (this._scrollable.isLiteralText()) {
					try {
												
						java.lang.String __scrollable = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._scrollable.getExpressionString(), 
											java.lang.String.class);
					
												comp.setScrollable(__scrollable);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("scrollable", this._scrollable);
				}
			}
					   		 			 
						if (this._selectedCellClass != null) {
				if (this._selectedCellClass.isLiteralText()) {
					try {
												
						java.lang.String __selectedCellClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._selectedCellClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSelectedCellClass(__selectedCellClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("selectedCellClass", this._selectedCellClass);
				}
			}
					   		 			 
						if (this._selectedClass != null) {
				if (this._selectedClass.isLiteralText()) {
					try {
												
						java.lang.String __selectedClass = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._selectedClass.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSelectedClass(__selectedClass);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("selectedClass", this._selectedClass);
				}
			}
					   		 			 				if(null != this._selection && this._selection.isLiteralText()){
					throw new IllegalArgumentException("Component com.exadel.siperian.ExtendedDataTable with Id " + component.getClientId(getFacesContext()) +" allows only EL expressions for property selection");
				}
			 
						if (this._selection != null) {
				if (this._selection.isLiteralText()) {
					try {
												
						org.richfaces.model.selection.Selection __selection = (org.richfaces.model.selection.Selection) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._selection.getExpressionString(), 
											org.richfaces.model.selection.Selection.class);
					
												comp.setSelection(__selection);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("selection", this._selection);
				}
			}
					   		 			 
						if (this._selectionMode != null) {
				if (this._selectionMode.isLiteralText()) {
					try {
												
						java.lang.String __selectionMode = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._selectionMode.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSelectionMode(__selectionMode);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("selectionMode", this._selectionMode);
				}
			}
					    		 			 
						if (this._sortMode != null) {
				if (this._sortMode.isLiteralText()) {
					try {
												
						java.lang.String __sortMode = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._sortMode.getExpressionString(), 
											java.lang.String.class);
					
												comp.setSortMode(__sortMode);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("sortMode", this._sortMode);
				}
			}
					   		 			 
						if (this._sortPriority != null) {
				if (this._sortPriority.isLiteralText()) {
					try {
												
						java.util.Collection __sortPriority = (java.util.Collection) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._sortPriority.getExpressionString(), 
											java.util.Collection.class);
					
												comp.setSortPriority(__sortPriority);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("sortPriority", this._sortPriority);
				}
			}
					     		 			 
						if (this._stateVar != null) {
				if (this._stateVar.isLiteralText()) {
					try {
												
						java.lang.String __stateVar = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._stateVar.getExpressionString(), 
											java.lang.String.class);
					
												comp.setStateVar(__stateVar);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("stateVar", this._stateVar);
				}
			}
					     		 			 
						if (this._subRows != null) {
				if (this._subRows.isLiteralText()) {
					try {
												
						java.util.List __subRows = (java.util.List) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._subRows.getExpressionString(), 
											java.util.List.class);
					
												comp.setSubRows(__subRows);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("subRows", this._subRows);
				}
			}
					    		 			 				if(null != this._tableState && this._tableState.isLiteralText()){
					throw new IllegalArgumentException("Component com.exadel.siperian.ExtendedDataTable with Id " + component.getClientId(getFacesContext()) +" allows only EL expressions for property tableState");
				}
			 
						if (this._tableState != null) {
				if (this._tableState.isLiteralText()) {
					try {
												
						java.lang.String __tableState = (java.lang.String) getFacesContext().
							getApplication().
								getExpressionFactory().
									coerceToType(this._tableState.getExpressionString(), 
											java.lang.String.class);
					
												comp.setTableState(__tableState);
											} catch (ELException e) {
						throw new FacesException(e);
					}
				} else {
					component.setValueExpression("tableState", this._tableState);
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
					   		 			 
											if (this._var != null) {
					comp.setVar(this._var);
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
		return "com.exadel.siperian.ExtendedDataTable";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
				return "com.exadel.siperian.ExtendedDataTableRenderer";
			}

}
