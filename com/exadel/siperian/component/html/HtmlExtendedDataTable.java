package com.exadel.siperian.component.html;

import com.exadel.siperian.component.UIExtendedDataTable;
import java.util.List;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import org.richfaces.model.selection.Selection;

public class HtmlExtendedDataTable extends UIExtendedDataTable{

public final static  String COMPONENT_FAMILY = "com.exadel.siperian.ExtendedDataTable";

public final static  String COMPONENT_TYPE = "com.exadel.siperian.ExtendedDataTable";

/*
* CSS class for active row
*/
private  String _activeClass = null;

/*
* Request scope attribute under which the activeRowKey will
                                        be accessible
*/
private  Object _activeRowKey = null;

/*
* Deprecated. This attribute specifies the position of the table with respect to the document. 
            The possible values are "left", "center" and "right". The default value is "left".
*/
private  String _align = null;

/*
* Allow or not cell selection by default false.
*/
private  Boolean _allowCellSelection = null;

/*
* false to disable columns resize functionality
*/
private  Boolean _allowColumnResize = null;

/*
* false to disable hide columns functionality
*/
private  Boolean _allowColumnsHide = null;

/*
* Deprecated. This attribute sets the background color for the document body or table cells.
            
            This attribute sets the background color of the canvas for the document body (the BODY element) or for tables (the TABLE, TR, TH, and TD elements). Additional attributes for specifying text color can be used with the BODY element.
            
            This attribute has been deprecated in favor of style sheets for specifying background color information
*/
private  String _bgcolor = null;

/*
* This attributes specifies the width of the frame around a
                                        component
*/
private  String _border = null;

/*
* Space-separated list of CSS style class(es) that are be
                                        applied to caption for this component
*/
private  String _captionClass = null;

/*
* CSS style(s) is/are to be applied to caption when this
                                        component is rendered
*/
private  String _captionStyle = null;

/*
* This attribute specifies the amount of space between the
                                        border of the cell and its contents
*/
private  String _cellpadding = null;

/*
* This attribute specifies the amount of space between the
                                        border of the cell and its contents. The attribute also
                                        specifies the amount of space to leave between cells
*/
private  String _cellspacing = null;

/*
* Comma-delimited list of CSS style classes that are be applied to the columns of this table. A space separated list of classes may also be specified for any individual column. If the number of elements in this list is less than the number of columns specified in the "columns" attribute, no "class" attribute is output for each column greater than the number of elements in the list. If the number of elements in the list is greater than the number of columns specified in the "columns" attribute, the elements at the position in the list after the value of the "columns" attribute are ignored
*/
private  String _columnClasses = null;

/*
* Direction indication for text that does not inherit
			directionality. Valid values are "LTR" (left-to-right)
			and "RTL" (right-to-left)
*/
private  String _dir = null;

/*
* false to hide the scroll
*/
private  Boolean _dndSupported = null;

/*
* filterFields
*/
private  List _filterFields = null;

/*
* Space-separated list of CSS style class(es) that are be
                                        applied to footer for this component
*/
private  String _footerClass = null;

/*
* Default true rerender after sorting
*/
private  Boolean _forseSorting = null;

/*
* This attribute specifies which sides of the frame surrounding a table will be visible. Possible values:  "void", "above", "below", "hsides", "lhs", "rhs", "vsides", "box" and "border".
        The default value is "void".
*/
private  String _frame = null;

/*
* Frozen columns number
*/
private  Integer _frozenColumns = null;

/*
* 
*/
private  String _groupColumnWidth = null;

/*
* The information in the table wiil be grouped by the
                                        information in the column, which id is provided with this
                                        attribute.
*/
private  String _groupingColumn = null;

/*
* Space-separated list of CSS style class(es) that are be
                                        applied to header for this component
*/
private  String _headerClass = null;

/*
* Defines a height of the component. Default value is 500px
*/
private  String _height = null;

/*
* Default true rerender after sorting
*/
private  Boolean _horizontalScrolling = null;

/*
* CSS class for row with mouse over
*/
private  String _hoverRowClass = null;

/*
* null
*/
private  Boolean _inlineEditing = null;

/*
* Code describing the language used in the generated markup for this component
*/
private  String _lang = null;

/*
* HTML: a script expression; a pointer button is clicked
                                        on row
*/
private  String _onRowClick = null;

/*
* HTML: a script expression; a pointer button is clicked
                                        on row
*/
private  String _onRowContextMenu = null;

/*
* HTML: a script expression; a pointer button is
                                        double-clicked on row
*/
private  String _onRowDblClick = null;

/*
* HTML: script expression; a pointer button is pressed down
                                        on row
*/
private  String _onRowMouseDown = null;

/*
* HTML: a script expression; a pointer is moved within of
                                        row
*/
private  String _onRowMouseMove = null;

/*
* HTML: a script expression; a pointer is moved away of row
*/
private  String _onRowMouseOut = null;

/*
* HTML: a script expression; a pointer is moved onto of row
*/
private  String _onRowMouseOver = null;

/*
* HTML: script expression; a pointer button is released on
                                        row
*/
private  String _onRowMouseUp = null;

/*
* The clientside script method to be called when the element is clicked
*/
private  String _onclick = null;

/*
* The client side script method to be called when the element is double-clicked
*/
private  String _ondblclick = null;

/*
* The client side script method to be called when a key is pressed down over the element
*/
private  String _onkeydown = null;

/*
* The client side script method to be called when a key is pressed over the element and released
*/
private  String _onkeypress = null;

/*
* The client side script method to be called when a key is released
*/
private  String _onkeyup = null;

/*
* Handler for update layout event
*/
private  String _onlayoutupdate = null;

/*
* The client side script method to be called when a mouse button is pressed down over the element
*/
private  String _onmousedown = null;

/*
* The client side script method to be called when a pointer is moved within the element
*/
private  String _onmousemove = null;

/*
* The client side script method to be called when a pointer is moved away from the element
*/
private  String _onmouseout = null;

/*
* The client side script method to be called when a pointer is moved onto the element
*/
private  String _onmouseover = null;

/*
* The client side script method to be called when a mouse button is released
*/
private  String _onmouseup = null;

/*
* HTML: a script expression; table content is scrolled
*/
private  String _onscroll = null;

/*
* HTML: script expression to invoke on changing of rows
                                        selection
*/
private  String _onselectionchange = null;

/*
* A comma-delimited list of CSS style classes that is applied to popup table rows. A space separated list of classes may also be specified for any individual row. The styles are applied, in turn, to each row in the table. For example, if the list has two elements, the first style class in the list is applied to the first row, the second to the second row, the first to the third row, the second to the fourth row, etc. In other words, we keep iterating through the list until we reach the end, and then we start at the beginning again
*/
private  String _rowClasses = null;

/*
* This attribute specifies which rules will appear between cells within a table. The rendering of rules is user agent dependent. Possible values:
            
            * none: No rules. This is the default value.
            * groups: Rules will appear between row groups (see THEAD, TFOOT, and TBODY) and column groups (see COLGROUP and COL) only.
            * rows: Rules will appear between rows only.
            * cols: Rules will appear between columns only.
            * all: Rules will appear between all rows and columns
*/
private  String _rules = null;

/*
* false to hide the scroll
*/
private  String _scrollable = null;

/*
* Css style class that define styles for highlighted cell
*/
private  String _selectedCellClass = null;

/*
* CSS class for selected rows
*/
private  String _selectedClass = null;

/*
* Value binding representing selected rows
*/
private  Selection _selection = null;

/*
* single [default]: Single row can be selected. multi:
                                        Multiple rows can be selected. none: no rows can be
                                        selected.
*/
private  String _selectionMode = null;

/*
* sortFields
*/
private  List _sortFields = null;

/*
* Defines mode of sorting. Possible values are 'single'
                                        for sorting of one column and 'multi' for some.
*/
private  String _sortMode = null;

/*
* CSS style(s) is/are to be applied when this component is rendered
*/
private  String _style = null;

/*
* Corresponds to the HTML class attribute
*/
private  String _styleClass = null;

/*
* null
*/
private  List _subRows = null;

/*
* null
*/
private  Object _summary = null;

/*
* ValueBinding pointing at a property of a String to hold
                                        table state
*/
private  String _tableState = null;

/*
* Advisory title information about markup elements generated for this component
*/
private  String _title = null;

/*
* This attribute specifies the desired width of the entire table and is intended for visual user agents. When the value is percentage value, the value is relative to the user agent's available horizontal space. In the absence of any width specification, table width is determined by the user agent
*/
private  String _width = null;


public HtmlExtendedDataTable(){
setRendererType("com.exadel.siperian.ExtendedDataTableRenderer");
}

public String getActiveClass(){
	if (this._activeClass != null) {
		return this._activeClass;
	}
	ValueExpression ve = getValueExpression("activeClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setActiveClass(String _activeClass){
this._activeClass = _activeClass;
}

public Object getActiveRowKey(){
	if (this._activeRowKey != null) {
		return this._activeRowKey;
	}
	ValueExpression ve = getValueExpression("activeRowKey");
	if (ve != null) {
	    Object value = null;
	    
	    try {
			value = (Object) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setActiveRowKey(Object _activeRowKey){
this._activeRowKey = _activeRowKey;
}

public String getAlign(){
	if (this._align != null) {
		return this._align;
	}
	ValueExpression ve = getValueExpression("align");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setAlign(String _align){
this._align = _align;
}

public Boolean getAllowCellSelection(){
	if (this._allowCellSelection != null) {
		return this._allowCellSelection;
	}
	ValueExpression ve = getValueExpression("allowCellSelection");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return false;
	

}

public void setAllowCellSelection(Boolean _allowCellSelection){
this._allowCellSelection = _allowCellSelection;
}

public Boolean getAllowColumnResize(){
	if (this._allowColumnResize != null) {
		return this._allowColumnResize;
	}
	ValueExpression ve = getValueExpression("allowColumnResize");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return true;
	

}

public void setAllowColumnResize(Boolean _allowColumnResize){
this._allowColumnResize = _allowColumnResize;
}

public Boolean getAllowColumnsHide(){
	if (this._allowColumnsHide != null) {
		return this._allowColumnsHide;
	}
	ValueExpression ve = getValueExpression("allowColumnsHide");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return true;
	

}

public void setAllowColumnsHide(Boolean _allowColumnsHide){
this._allowColumnsHide = _allowColumnsHide;
}

public String getBgcolor(){
	if (this._bgcolor != null) {
		return this._bgcolor;
	}
	ValueExpression ve = getValueExpression("bgcolor");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setBgcolor(String _bgcolor){
this._bgcolor = _bgcolor;
}

public String getBorder(){
	if (this._border != null) {
		return this._border;
	}
	ValueExpression ve = getValueExpression("border");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "0";
	

}

public void setBorder(String _border){
this._border = _border;
}

public String getCaptionClass(){
	if (this._captionClass != null) {
		return this._captionClass;
	}
	ValueExpression ve = getValueExpression("captionClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "";
	

}

public void setCaptionClass(String _captionClass){
this._captionClass = _captionClass;
}

public String getCaptionStyle(){
	if (this._captionStyle != null) {
		return this._captionStyle;
	}
	ValueExpression ve = getValueExpression("captionStyle");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setCaptionStyle(String _captionStyle){
this._captionStyle = _captionStyle;
}

public String getCellpadding(){
	if (this._cellpadding != null) {
		return this._cellpadding;
	}
	ValueExpression ve = getValueExpression("cellpadding");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "0";
	

}

public void setCellpadding(String _cellpadding){
this._cellpadding = _cellpadding;
}

public String getCellspacing(){
	if (this._cellspacing != null) {
		return this._cellspacing;
	}
	ValueExpression ve = getValueExpression("cellspacing");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "0";
	

}

public void setCellspacing(String _cellspacing){
this._cellspacing = _cellspacing;
}

public String getColumnClasses(){
	if (this._columnClasses != null) {
		return this._columnClasses;
	}
	ValueExpression ve = getValueExpression("columnClasses");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setColumnClasses(String _columnClasses){
this._columnClasses = _columnClasses;
}

public String getDir(){
	if (this._dir != null) {
		return this._dir;
	}
	ValueExpression ve = getValueExpression("dir");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setDir(String _dir){
this._dir = _dir;
}

public Boolean getDndSupported(){
	if (this._dndSupported != null) {
		return this._dndSupported;
	}
	ValueExpression ve = getValueExpression("dndSupported");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return true;
	

}

public void setDndSupported(Boolean _dndSupported){
this._dndSupported = _dndSupported;
}

public List getFilterFields(){
	if (this._filterFields != null) {
		return this._filterFields;
	}
	ValueExpression ve = getValueExpression("filterFields");
	if (ve != null) {
	    List value = null;
	    
	    try {
			value = (List) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setFilterFields(List _filterFields){
this._filterFields = _filterFields;
}

public String getFooterClass(){
	if (this._footerClass != null) {
		return this._footerClass;
	}
	ValueExpression ve = getValueExpression("footerClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "";
	

}

public void setFooterClass(String _footerClass){
this._footerClass = _footerClass;
}

public Boolean getForseSorting(){
	if (this._forseSorting != null) {
		return this._forseSorting;
	}
	ValueExpression ve = getValueExpression("forseSorting");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return false;
	

}

public void setForseSorting(Boolean _forseSorting){
this._forseSorting = _forseSorting;
}

public String getFrame(){
	if (this._frame != null) {
		return this._frame;
	}
	ValueExpression ve = getValueExpression("frame");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setFrame(String _frame){
this._frame = _frame;
}

public Integer getFrozenColumns(){
	if (this._frozenColumns != null) {
		return this._frozenColumns;
	}
	ValueExpression ve = getValueExpression("frozenColumns");
	if (ve != null) {
	    Integer value = null;
	    
	    try {
			value = (Integer) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return 0;
	

}

public void setFrozenColumns(Integer _frozenColumns){
this._frozenColumns = _frozenColumns;
}

public String getGroupColumnWidth(){
	if (this._groupColumnWidth != null) {
		return this._groupColumnWidth;
	}
	ValueExpression ve = getValueExpression("groupColumnWidth");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "40px";
	

}

public void setGroupColumnWidth(String _groupColumnWidth){
this._groupColumnWidth = _groupColumnWidth;
}

public String getGroupingColumn(){
	if (this._groupingColumn != null) {
		return this._groupingColumn;
	}
	ValueExpression ve = getValueExpression("groupingColumn");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setGroupingColumn(String _groupingColumn){
this._groupingColumn = _groupingColumn;
}

public String getHeaderClass(){
	if (this._headerClass != null) {
		return this._headerClass;
	}
	ValueExpression ve = getValueExpression("headerClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "";
	

}

public void setHeaderClass(String _headerClass){
this._headerClass = _headerClass;
}

public String getHeight(){
	if (this._height != null) {
		return this._height;
	}
	ValueExpression ve = getValueExpression("height");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "auto";
	

}

public void setHeight(String _height){
this._height = _height;
}

public Boolean getHorizontalScrolling(){
	if (this._horizontalScrolling != null) {
		return this._horizontalScrolling;
	}
	ValueExpression ve = getValueExpression("horizontalScrolling");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return false;
	

}

public void setHorizontalScrolling(Boolean _horizontalScrolling){
this._horizontalScrolling = _horizontalScrolling;
}

public String getHoverRowClass(){
	if (this._hoverRowClass != null) {
		return this._hoverRowClass;
	}
	ValueExpression ve = getValueExpression("hoverRowClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setHoverRowClass(String _hoverRowClass){
this._hoverRowClass = _hoverRowClass;
}

public Boolean getInlineEditing(){
	if (this._inlineEditing != null) {
		return this._inlineEditing;
	}
	ValueExpression ve = getValueExpression("inlineEditing");
	if (ve != null) {
	    Boolean value = null;
	    
	    try {
			value = (Boolean) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return false;
	

}

public void setInlineEditing(Boolean _inlineEditing){
this._inlineEditing = _inlineEditing;
}

public String getLang(){
	if (this._lang != null) {
		return this._lang;
	}
	ValueExpression ve = getValueExpression("lang");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setLang(String _lang){
this._lang = _lang;
}

public String getOnRowClick(){
	if (this._onRowClick != null) {
		return this._onRowClick;
	}
	ValueExpression ve = getValueExpression("onRowClick");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnRowClick(String _onRowClick){
this._onRowClick = _onRowClick;
}

public String getOnRowContextMenu(){
	if (this._onRowContextMenu != null) {
		return this._onRowContextMenu;
	}
	ValueExpression ve = getValueExpression("onRowContextMenu");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnRowContextMenu(String _onRowContextMenu){
this._onRowContextMenu = _onRowContextMenu;
}

public String getOnRowDblClick(){
	if (this._onRowDblClick != null) {
		return this._onRowDblClick;
	}
	ValueExpression ve = getValueExpression("onRowDblClick");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnRowDblClick(String _onRowDblClick){
this._onRowDblClick = _onRowDblClick;
}

public String getOnRowMouseDown(){
	if (this._onRowMouseDown != null) {
		return this._onRowMouseDown;
	}
	ValueExpression ve = getValueExpression("onRowMouseDown");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnRowMouseDown(String _onRowMouseDown){
this._onRowMouseDown = _onRowMouseDown;
}

public String getOnRowMouseMove(){
	if (this._onRowMouseMove != null) {
		return this._onRowMouseMove;
	}
	ValueExpression ve = getValueExpression("onRowMouseMove");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnRowMouseMove(String _onRowMouseMove){
this._onRowMouseMove = _onRowMouseMove;
}

public String getOnRowMouseOut(){
	if (this._onRowMouseOut != null) {
		return this._onRowMouseOut;
	}
	ValueExpression ve = getValueExpression("onRowMouseOut");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnRowMouseOut(String _onRowMouseOut){
this._onRowMouseOut = _onRowMouseOut;
}

public String getOnRowMouseOver(){
	if (this._onRowMouseOver != null) {
		return this._onRowMouseOver;
	}
	ValueExpression ve = getValueExpression("onRowMouseOver");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnRowMouseOver(String _onRowMouseOver){
this._onRowMouseOver = _onRowMouseOver;
}

public String getOnRowMouseUp(){
	if (this._onRowMouseUp != null) {
		return this._onRowMouseUp;
	}
	ValueExpression ve = getValueExpression("onRowMouseUp");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnRowMouseUp(String _onRowMouseUp){
this._onRowMouseUp = _onRowMouseUp;
}

public String getOnclick(){
	if (this._onclick != null) {
		return this._onclick;
	}
	ValueExpression ve = getValueExpression("onclick");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnclick(String _onclick){
this._onclick = _onclick;
}

public String getOndblclick(){
	if (this._ondblclick != null) {
		return this._ondblclick;
	}
	ValueExpression ve = getValueExpression("ondblclick");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOndblclick(String _ondblclick){
this._ondblclick = _ondblclick;
}

public String getOnkeydown(){
	if (this._onkeydown != null) {
		return this._onkeydown;
	}
	ValueExpression ve = getValueExpression("onkeydown");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnkeydown(String _onkeydown){
this._onkeydown = _onkeydown;
}

public String getOnkeypress(){
	if (this._onkeypress != null) {
		return this._onkeypress;
	}
	ValueExpression ve = getValueExpression("onkeypress");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnkeypress(String _onkeypress){
this._onkeypress = _onkeypress;
}

public String getOnkeyup(){
	if (this._onkeyup != null) {
		return this._onkeyup;
	}
	ValueExpression ve = getValueExpression("onkeyup");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnkeyup(String _onkeyup){
this._onkeyup = _onkeyup;
}

public String getOnlayoutupdate(){
	if (this._onlayoutupdate != null) {
		return this._onlayoutupdate;
	}
	ValueExpression ve = getValueExpression("onlayoutupdate");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnlayoutupdate(String _onlayoutupdate){
this._onlayoutupdate = _onlayoutupdate;
}

public String getOnmousedown(){
	if (this._onmousedown != null) {
		return this._onmousedown;
	}
	ValueExpression ve = getValueExpression("onmousedown");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnmousedown(String _onmousedown){
this._onmousedown = _onmousedown;
}

public String getOnmousemove(){
	if (this._onmousemove != null) {
		return this._onmousemove;
	}
	ValueExpression ve = getValueExpression("onmousemove");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnmousemove(String _onmousemove){
this._onmousemove = _onmousemove;
}

public String getOnmouseout(){
	if (this._onmouseout != null) {
		return this._onmouseout;
	}
	ValueExpression ve = getValueExpression("onmouseout");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnmouseout(String _onmouseout){
this._onmouseout = _onmouseout;
}

public String getOnmouseover(){
	if (this._onmouseover != null) {
		return this._onmouseover;
	}
	ValueExpression ve = getValueExpression("onmouseover");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnmouseover(String _onmouseover){
this._onmouseover = _onmouseover;
}

public String getOnmouseup(){
	if (this._onmouseup != null) {
		return this._onmouseup;
	}
	ValueExpression ve = getValueExpression("onmouseup");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnmouseup(String _onmouseup){
this._onmouseup = _onmouseup;
}

public String getOnscroll(){
	if (this._onscroll != null) {
		return this._onscroll;
	}
	ValueExpression ve = getValueExpression("onscroll");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnscroll(String _onscroll){
this._onscroll = _onscroll;
}

public String getOnselectionchange(){
	if (this._onselectionchange != null) {
		return this._onselectionchange;
	}
	ValueExpression ve = getValueExpression("onselectionchange");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setOnselectionchange(String _onselectionchange){
this._onselectionchange = _onselectionchange;
}

public String getRowClasses(){
	if (this._rowClasses != null) {
		return this._rowClasses;
	}
	ValueExpression ve = getValueExpression("rowClasses");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setRowClasses(String _rowClasses){
this._rowClasses = _rowClasses;
}

public String getRules(){
	if (this._rules != null) {
		return this._rules;
	}
	ValueExpression ve = getValueExpression("rules");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setRules(String _rules){
this._rules = _rules;
}

public String getScrollable(){
	if (this._scrollable != null) {
		return this._scrollable;
	}
	ValueExpression ve = getValueExpression("scrollable");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "true";
	

}

public void setScrollable(String _scrollable){
this._scrollable = _scrollable;
}

public String getSelectedCellClass(){
	if (this._selectedCellClass != null) {
		return this._selectedCellClass;
	}
	ValueExpression ve = getValueExpression("selectedCellClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "extdt-default-selected-cell";
	

}

public void setSelectedCellClass(String _selectedCellClass){
this._selectedCellClass = _selectedCellClass;
}

public String getSelectedClass(){
	if (this._selectedClass != null) {
		return this._selectedClass;
	}
	ValueExpression ve = getValueExpression("selectedClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setSelectedClass(String _selectedClass){
this._selectedClass = _selectedClass;
}

public Selection getSelection(){
	if (this._selection != null) {
		return this._selection;
	}
	ValueExpression ve = getValueExpression("selection");
	if (ve != null) {
	    Selection value = null;
	    
	    try {
			value = (Selection) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setSelection(Selection _selection){
this._selection = _selection;
}

public String getSelectionMode(){
	if (this._selectionMode != null) {
		return this._selectionMode;
	}
	ValueExpression ve = getValueExpression("selectionMode");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return "single";
	

}

public void setSelectionMode(String _selectionMode){
this._selectionMode = _selectionMode;
}

public List getSortFields(){
	if (this._sortFields != null) {
		return this._sortFields;
	}
	ValueExpression ve = getValueExpression("sortFields");
	if (ve != null) {
	    List value = null;
	    
	    try {
			value = (List) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setSortFields(List _sortFields){
this._sortFields = _sortFields;
}

public String getSortMode(){
	if (this._sortMode != null) {
		return this._sortMode;
	}
	ValueExpression ve = getValueExpression("sortMode");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setSortMode(String _sortMode){
this._sortMode = _sortMode;
}

public String getStyle(){
	if (this._style != null) {
		return this._style;
	}
	ValueExpression ve = getValueExpression("style");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setStyle(String _style){
this._style = _style;
}

public String getStyleClass(){
	if (this._styleClass != null) {
		return this._styleClass;
	}
	ValueExpression ve = getValueExpression("styleClass");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setStyleClass(String _styleClass){
this._styleClass = _styleClass;
}

public List getSubRows(){
	if (this._subRows != null) {
		return this._subRows;
	}
	ValueExpression ve = getValueExpression("subRows");
	if (ve != null) {
	    List value = null;
	    
	    try {
			value = (List) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setSubRows(List _subRows){
this._subRows = _subRows;
}

public Object getSummary(){
	if (this._summary != null) {
		return this._summary;
	}
	ValueExpression ve = getValueExpression("summary");
	if (ve != null) {
	    Object value = null;
	    
	    try {
			value = (Object) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setSummary(Object _summary){
this._summary = _summary;
}

public String getTableState(){
	if (this._tableState != null) {
		return this._tableState;
	}
	ValueExpression ve = getValueExpression("tableState");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setTableState(String _tableState){
this._tableState = _tableState;
}

public String getTitle(){
	if (this._title != null) {
		return this._title;
	}
	ValueExpression ve = getValueExpression("title");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setTitle(String _title){
this._title = _title;
}

public String getWidth(){
	if (this._width != null) {
		return this._width;
	}
	ValueExpression ve = getValueExpression("width");
	if (ve != null) {
	    String value = null;
	    
	    try {
			value = (String) ve.getValue(getFacesContext().getELContext());
	    } catch (ELException e) {
			throw new FacesException(e);
	    }
	    
	    return value;
	} 

    return null;
	

}

public void setWidth(String _width){
this._width = _width;
}

public String getFamily(){
return COMPONENT_FAMILY;
}

@Override
public Object saveState(FacesContext context){
Object [] state = new Object[66];
state[0] = super.saveState(context);
state[1] = _activeClass;
state[2] = saveAttachedState(context, _activeRowKey);
state[3] = _align;
state[4] = _allowCellSelection;
state[5] = _allowColumnResize;
state[6] = _allowColumnsHide;
state[7] = _bgcolor;
state[8] = _border;
state[9] = _captionClass;
state[10] = _captionStyle;
state[11] = _cellpadding;
state[12] = _cellspacing;
state[13] = _columnClasses;
state[14] = _dir;
state[15] = _dndSupported;
state[16] = saveAttachedState(context, _filterFields);
state[17] = _footerClass;
state[18] = _forseSorting;
state[19] = _frame;
state[20] = _frozenColumns;
state[21] = _groupColumnWidth;
state[22] = _groupingColumn;
state[23] = _headerClass;
state[24] = _height;
state[25] = _horizontalScrolling;
state[26] = _hoverRowClass;
state[27] = _inlineEditing;
state[28] = _lang;
state[29] = _onRowClick;
state[30] = _onRowContextMenu;
state[31] = _onRowDblClick;
state[32] = _onRowMouseDown;
state[33] = _onRowMouseMove;
state[34] = _onRowMouseOut;
state[35] = _onRowMouseOver;
state[36] = _onRowMouseUp;
state[37] = _onclick;
state[38] = _ondblclick;
state[39] = _onkeydown;
state[40] = _onkeypress;
state[41] = _onkeyup;
state[42] = _onlayoutupdate;
state[43] = _onmousedown;
state[44] = _onmousemove;
state[45] = _onmouseout;
state[46] = _onmouseover;
state[47] = _onmouseup;
state[48] = _onscroll;
state[49] = _onselectionchange;
state[50] = _rowClasses;
state[51] = _rules;
state[52] = _scrollable;
state[53] = _selectedCellClass;
state[54] = _selectedClass;
state[55] = saveAttachedState(context, _selection);
state[56] = _selectionMode;
state[57] = saveAttachedState(context, _sortFields);
state[58] = _sortMode;
state[59] = _style;
state[60] = _styleClass;
state[61] = saveAttachedState(context, _subRows);
state[62] = saveAttachedState(context, _summary);
state[63] = _tableState;
state[64] = _title;
state[65] = _width;
return state;
}

@Override
public void restoreState(FacesContext context, Object state){
Object[] states = (Object[]) state;
super.restoreState(context, states[0]);
	_activeClass = (String)states[1];;
		_activeRowKey = (Object)restoreAttachedState(context, states[2]);
		_align = (String)states[3];;
		_allowCellSelection = (Boolean)states[4];;
		_allowColumnResize = (Boolean)states[5];;
		_allowColumnsHide = (Boolean)states[6];;
		_bgcolor = (String)states[7];;
		_border = (String)states[8];;
		_captionClass = (String)states[9];;
		_captionStyle = (String)states[10];;
		_cellpadding = (String)states[11];;
		_cellspacing = (String)states[12];;
		_columnClasses = (String)states[13];;
		_dir = (String)states[14];;
		_dndSupported = (Boolean)states[15];;
		_filterFields = (List)restoreAttachedState(context, states[16]);
		_footerClass = (String)states[17];;
		_forseSorting = (Boolean)states[18];;
		_frame = (String)states[19];;
		_frozenColumns = (Integer)states[20];;
		_groupColumnWidth = (String)states[21];;
		_groupingColumn = (String)states[22];;
		_headerClass = (String)states[23];;
		_height = (String)states[24];;
		_horizontalScrolling = (Boolean)states[25];;
		_hoverRowClass = (String)states[26];;
		_inlineEditing = (Boolean)states[27];;
		_lang = (String)states[28];;
		_onRowClick = (String)states[29];;
		_onRowContextMenu = (String)states[30];;
		_onRowDblClick = (String)states[31];;
		_onRowMouseDown = (String)states[32];;
		_onRowMouseMove = (String)states[33];;
		_onRowMouseOut = (String)states[34];;
		_onRowMouseOver = (String)states[35];;
		_onRowMouseUp = (String)states[36];;
		_onclick = (String)states[37];;
		_ondblclick = (String)states[38];;
		_onkeydown = (String)states[39];;
		_onkeypress = (String)states[40];;
		_onkeyup = (String)states[41];;
		_onlayoutupdate = (String)states[42];;
		_onmousedown = (String)states[43];;
		_onmousemove = (String)states[44];;
		_onmouseout = (String)states[45];;
		_onmouseover = (String)states[46];;
		_onmouseup = (String)states[47];;
		_onscroll = (String)states[48];;
		_onselectionchange = (String)states[49];;
		_rowClasses = (String)states[50];;
		_rules = (String)states[51];;
		_scrollable = (String)states[52];;
		_selectedCellClass = (String)states[53];;
		_selectedClass = (String)states[54];;
		_selection = (Selection)restoreAttachedState(context, states[55]);
		_selectionMode = (String)states[56];;
		_sortFields = (List)restoreAttachedState(context, states[57]);
		_sortMode = (String)states[58];;
		_style = (String)states[59];;
		_styleClass = (String)states[60];;
		_subRows = (List)restoreAttachedState(context, states[61]);
		_summary = (Object)restoreAttachedState(context, states[62]);
		_tableState = (String)states[63];;
		_title = (String)states[64];;
		_width = (String)states[65];;
	
}

}
