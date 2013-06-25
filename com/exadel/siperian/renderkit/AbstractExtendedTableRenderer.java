/**
 * License Agreement.
 *
 *  JBoss RichFaces - Ajax4jsf Component Library
 *
 * Copyright (C) 2007  Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package com.exadel.siperian.renderkit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;

import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.javascript.JSFunctionDefinition;
import org.ajax4jsf.javascript.JSReference;
import org.ajax4jsf.javascript.ScriptUtils;
import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.ajax4jsf.renderkit.RendererUtils.HTML;
import org.richfaces.component.Column;
import org.richfaces.component.Row;
import org.richfaces.component.UIColumn;
import org.richfaces.component.UIDataTable;
import org.richfaces.component.nsutils.NSUtils;
import org.richfaces.event.extdt.ChangeColumnVisibilityEvent;
import org.richfaces.event.extdt.ColumnResizeEvent;
import org.richfaces.event.extdt.DragDropEvent;
import org.richfaces.event.extdt.ExtTableFilterEvent;
import org.richfaces.event.extdt.ExtTableSortEvent;
import org.richfaces.model.Ordering;
import org.richfaces.model.SortField2;
import org.richfaces.model.impl.expressive.JavaBeanWrapper;
import org.richfaces.model.impl.expressive.ObjectWrapperFactory;
import org.richfaces.model.impl.expressive.WrappedBeanComparator2;
import org.richfaces.renderkit.HeaderEncodeStrategy;
import org.richfaces.renderkit.RendererContributor;
import org.richfaces.renderkit.ScriptOptions;
import org.richfaces.renderkit.html.HTMLEncodingContributor;
import org.richfaces.renderkit.html.iconimages.DataTableIconSortAsc;
import org.richfaces.renderkit.html.iconimages.DataTableIconSortDesc;
import org.richfaces.renderkit.html.iconimages.DataTableIconSortNone;
import org.richfaces.renderkit.html.images.TriangleIconDown;
import org.richfaces.renderkit.html.images.TriangleIconUp;

import com.exadel.siperian.component.UIExtendedDataTable;
import com.exadel.siperian.renderkit.html.RichTableMenuRenderer;
import com.exadel.siperian.renderkit.html.TableMenuRenderer;

/**
 * @author shura
 * 
 */
public abstract class AbstractExtendedTableRenderer extends
        com.exadel.siperian.renderkit.AbstractExtendedRowsRenderer {
	
    private static final String ALLOW_COLUMNS_HIDE = "allowColumnsHide";

    private static final String CELL_STATE = "cellState";

    private static final String SORT_FILTER_PARAMETER = "fsp";

    private static final String SORT_DIR_PARAMETER = "sortDir";
    
    private static final String ATTRIBUTE_CAN_HIDE = "canHide";

    private static final String SORT_DIR_PARAMETER_ASC = "asc";

    private static final String SORT_DIR_PARAMETER_DESC = "desc";
    
    private static final String SCROLL_POSITION_PARAMETER = "scrollPos"; 
    
    private static final String FILTER_INPUT_FACET_NAME = "filterValueInput";

    private static final String COL_RESIZE_ACTION_NAME = "columnResizeAction";

    private final static String CHANGE_COL_VISIBILITY = "change_col_v";

    private static final String SORT_FUNCTION = "sortFunction";

    private static final String SHOW_MENU_FUNCTION = "showMenuFunction";

    private static final String CAN_HIDE = "isSingleCanHideTrueAvailable";
    
    private static final String ON_RESIZE_FUNCTION = "onColumnResize";
    
    private static final String ON_SCROLL_FUNCTION = "onScrollFunction";
    
    private static final String ON_ROW_CONTEXT_MENU = "onRowContextMenu";  

    private static final String GROUP_FILTER_PARAMETER = "groupParam";

    private static final String ON_GROUP_TOGGLE_FUNCTION = "onGroupToggleFunction";

    private static final String GROUP_TOGGLE_ACTION_NAME = "groupToggleAction";
    
    public static final String EDIT_ACTIONE_NAME = "editAction";
    
    private static final String MIN_COLUMN_WIDTH = "20";
    
    private static final String SCROLLABLE = "scrollable";

    private static final String RESIZIABLE = "allowColumnResize";
    
    private static final String HORIZONTALSCROLLING = "horizontalScrolling";
    
    private static final String SELECTEDCELLCLASS = "selectedCellClass";
    
    private static final String ALLOWCELLSELECTION = "allowCellSelection";
    
    public static final String ROOT_ROW_AJAX_EXPANDED = "groupExpandAjax";

    /**
     * Encode all table structure - colgroups definitions, caption, header,
     * footer
     * 
     * @param context
     * @param table
     * @throws IOException
     */
    public String getOnClick(FacesContext context, UIComponent component) {
        StringBuffer onClick = AjaxRendererUtils.buildOnClick(component, context);
          
       
        return onClick.toString();
    }
    
@Override
public void encodeChildren(FacesContext context, UIComponent component)
		throws IOException {
	ResponseWriter writer = context.getResponseWriter();
	doEncodeChildren(writer, context, component);
}
    
    public String getForceCount(FacesContext context, UIComponent component){
        String rst = String.valueOf(1);
            if(component instanceof UIExtendedDataTable){
                rst = String.valueOf(((UIExtendedDataTable)component).getCountOfForceSort());
            }
        return rst;
    }
    public void encodeColumns(FacesContext context,
            com.exadel.siperian.component.UIExtendedDataTable table) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        Iterator<UIColumn> cols = table.getSortedColumns();
        Integer frozenColumns = getFrozenColumnsCount(context, table);
        int c = 0;
        
       
        while (cols.hasNext()) {
        	
        	if (frozenColumns != null && c++ < frozenColumns) {
        		cols.next();
        		continue;
        	}

        	
        	UIColumn col = cols.next();
            if (col.isRendered()) {
                writer.startElement("col", table);
                String colWidth = table.getColumnSize(col);
                writer.writeAttribute("width", colWidth, null);
                writer.endElement("col");
            }
         }
        writer.startElement("col", table);
        writer.endElement("col");
    }
    
    public void encodeFrozenColumns(FacesContext context,
            com.exadel.siperian.component.UIExtendedDataTable table) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        Iterator<UIColumn> cols = table.getSortedColumns();
        Integer frozenColumns = getFrozenColumnsCount(context, table);
        
               
        int counter = 0;
        
        while (cols.hasNext()) {
        	
        	if (frozenColumns != null && counter++ >= frozenColumns) {
        		break;
        	}
        	
            UIColumn col = cols.next();
            if (col.isRendered()) {
                writer.startElement("col", table);
                String colWidth = table.getColumnSize(col);
                writer.writeAttribute("width", colWidth, null);
                writer.endElement("col");
            }
        }

    }
    
    public void encodeTableHeader(FacesContext context, UIExtendedDataTable table)
    throws IOException {
    	 ResponseWriter writer = context.getResponseWriter();
         UIComponent header = table.getHeader();
         int numberOfColumns = table.getVisibleColumnsCount() + 1;
         
         if (header != null) {
             writer.endElement("tr");

             String headerClass = (String) table.getAttributes().get(
             "headerClass");
             encodeTableHeaderFacet(context, numberOfColumns, writer,
                     header, "extdt-header rich-extdt-header",
                     "extdt-header-continue rich-extdt-header-continue",
                     "extdt-headercell rich-extdt-headercell",
                     headerClass, "th", "header");
         }
    }
    
    public void encodeFrozenHeader(FacesContext context, UIExtendedDataTable table)
    throws IOException {
    	
    	Integer frozenColumns = getFrozenColumnsCount(context, table);
    	if (frozenColumns != null) {
    		encodeHeader(context, table, 0, frozenColumns, false);
    	}
    }
    
    
    public void encodeHeader(FacesContext context, UIExtendedDataTable table)
    throws IOException {
    	Integer frozenColumns = getFrozenColumnsCount(context, table);
    	if (frozenColumns != null) {
    		encodeHeader(context, table, frozenColumns, Integer.MAX_VALUE, true);
    	}
    }

    public void encodeHeader(FacesContext context, UIExtendedDataTable table, int startColumns, int endColumn, boolean emptyCell)
            throws IOException {

        ResponseWriter writer = context.getResponseWriter();
        UIComponent header = table.getHeader();
        boolean columnFacetPresent = isColumnFacetPresent(table, "header");
        Iterator<UIColumn> colums = table.getSortedColumns();
        // int numberOfColumns = getColumnsCount(table);
        int numberOfColumns = table.getVisibleColumnsCount() + 1;
        if (header != null || columnFacetPresent) {
            writer.startElement("thead", table);
            writer.writeAttribute(HTML.id_ATTRIBUTE, table
                    .getBaseClientId(context)
                    + ":header", null);
            writer.writeAttribute(HTML.class_ATTRIBUTE, "extdt-thead", null);
            String headerClass = (String) table.getAttributes().get(
                    "headerClass");
            // String menuId = (String) table.getAttributes().get(MENU_ID);
            
            writer.startElement("tr", header);
            encodeStyleClass(writer, null,
                    "extdt-header rich-extdt-header", headerClass, null);
            writer.writeAttribute(HTML.id_ATTRIBUTE, table
                    .getBaseClientId(context)
                    + ":fakeIeRow", null);
            for (int i = 0; i < numberOfColumns; i++) {
            	
            	if (i<startColumns || i>=endColumn) {
            		continue;
            	}
            	
                writer.startElement("th", header);
                encodeStyleClass(writer, null,
                        "extdt-headercell extdt-fakeierow rich-extdt-headercell",
                        headerClass, null);
                writer.endElement("th");
            }
            writer.endElement("tr");
            

            if (columnFacetPresent) {
                writer.startElement("tr", table);
                writer.writeAttribute(HTML.id_ATTRIBUTE, table
                        .getBaseClientId(context)
                        + ":headerRow", null);
                encodeStyleClass(writer, null,
                        "extdt-subheader rich-extdt-subheader", null,
                        headerClass);
                encodeHeaderFacets(
                        context,
                        writer,
                        table,
                        colums,
                        "extdt-menucell extdt-subheadercell rich-extdt-subheadercell",
                        headerClass, "header", "th", numberOfColumns, startColumns, endColumn);
                /* encoding additional empty column used in resizing columns */
                
                if (emptyCell) {
	                writer.startElement("th", table);
	                encodeStyleClass(writer, null,
	                        "extdt-empty-cell rich-extdt-subheadercell", null,
	                        null);
	
	                writer.endElement("th");
                }
                
                writer.endElement("tr");

                encodeFilterRow(context, writer, table, table
                        .getSortedColumns(),
                        "extdt-subheadercell rich-extdt-subheadercell",
                        headerClass, "header", HTML.th_ELEM, startColumns, endColumn, emptyCell);

            }
            writer.endElement("thead");
        }
    }

    private void encodeFilterRow(FacesContext context, ResponseWriter writer,
            UIExtendedDataTable table, Iterator<UIColumn> headers,
            String skinCellClass, String headerClass, String facetName,
            String element, int startColumns, int endColumn, boolean emptyCell) throws IOException {
        
        if (filteringEnabled(table)) {
            writer.startElement(HTML.TR_ELEMENT, table);
            writer.writeAttribute(HTML.id_ATTRIBUTE, table
                    .getBaseClientId(context)
                    + ":filterRow", null);
            encodeStyleClass(writer, null,
                    "extdt-table-filterrow rich-extdt-subheader", null,
                    headerClass);
            encodeFilterInputs(context, writer, table, headers, skinCellClass,
                    headerClass, facetName, element, startColumns, endColumn, emptyCell);
            
            if (emptyCell) {
	            writer.startElement(HTML.th_ELEM, table);
	            encodeStyleClass(writer, null,
	                    "extdt-empty-cell rich-extdt-subheadercell", null, null);
	            writer.endElement(HTML.th_ELEM);
            }
            writer.endElement(HTML.TR_ELEMENT);
        }
    }

    /**
     * Checks if column has filtering enabled
     * 
     * @param dataColumn column to inspect
     * @return true if filtering is enabled, false otherwise
     * @author pbuda
     */
    private boolean filterEnabledColumn(UIColumn dataColumn) {
    	Object filterableAttr = dataColumn.getAttributes().get("filterable");
		boolean filterable = true;
		if (filterableAttr != null) {
			if (filterableAttr instanceof Boolean) {
				filterable = (Boolean)filterableAttr;
			}else if (filterableAttr instanceof String) {
				filterable = Boolean.parseBoolean((String)filterableAttr);
			}
		}
        return filterable && (dataColumn.getFilterMethod() == null
                && dataColumn.getValueExpression("filterExpression") == null
                && dataColumn.getValueExpression("filterBy") != null);
    }

    /**
     * Checks whether filtering is enabled in this table
     * 
     * @param table table to inspect
     * @return true if filtering is enabled, false otherwise
     * @author pbuda
     */
    private boolean filteringEnabled(com.exadel.siperian.component.UIExtendedDataTable table) {
        Iterator<UIColumn> columns = table.getSortedColumns();
        boolean enabled = false;
        while (columns.hasNext()) {
            UIColumn col = columns.next();
            if (col instanceof UIColumn) {
                UIColumn dataColumn = (UIColumn) col;
                if (filterEnabledColumn(dataColumn) && dataColumn.isRendered()) {
                    enabled = true;
                    break;
                }
            }
        }
        return enabled;
    }

    /**
     * Encodes a new row of the table and places filter inputs for corresponding
     * columns in that new row
     * 
     * @param context
     *            current FacesContext instance
     * @param writer
     *            ResponseWriter for this context
     * @param table
     *            table instance
     * @param headers
     *            iterator over headers in table
     * @param skinCellClass
     *            css class of skin
     * @param headerClass
     *            css class of header
     * @param facetName
     *            facet to encode
     * @param element
     *            element to encode
     * @throws IOException
     *             if ResponseWriter fails it's operation
     * @author pbuda
     */
    private void encodeFilterInputs(FacesContext context,
            ResponseWriter writer, UIDataTable table,
            Iterator<UIColumn> headers, String skinCellClass,
            String headerClass, String facetName, String element, int startColumns, int endColumn, boolean emptyCell)
            throws IOException {
    	
    	int i=0;
        while (headers.hasNext()) {
        	
        	if (i<startColumns || i>=endColumn) {
        		headers.next();
        		i++;
        		continue;
        	}
        	
            UIColumn column = headers.next();
            if (column instanceof UIColumn) {
                if (column.isRendered()) {
                    writer.startElement(element, table);
                    String classAttribute = facetName + "Class";
                    String columnHeaderClass = (String) column.getAttributes()
                            .get(classAttribute);
                    encodeStyleClass(writer, null, skinCellClass, headerClass,
                            columnHeaderClass);
                    UIColumn dataColumn = (UIColumn) column;
                    if (filterEnabledColumn(dataColumn)) {
                        writer.startElement(HTML.DIV_ELEM, column);
                        addInplaceInput(context, column, buildAjaxFunction(
                                context, column, false,
                                getOnAjaxCompleteFunction(context,
                                        (UIDataTable) column.getParent())));
                        writer.endElement(HTML.DIV_ELEM);
                    }else {
                    	writer.startElement(HTML.SPAN_ELEM, column);
                    	writer.write("<br/>");
                    	writer.endElement(HTML.SPAN_ELEM);
                    }
                    writer.endElement(element);
                }
            }
            i++;
        }
    }

    public boolean isColumnFacetPresent(UIDataTable table, String facetName) {
        Iterator<UIComponent> columns = table.columns();
        boolean result = false;
        while (columns.hasNext() && !result) {
            UIComponent component = columns.next();
            if (isColumnRendered(component)) {
                if (null != component.getFacet(facetName)) {
                    result = true;
                } /*
                 * else if(component instanceof Column) { Column column =
                 * (Column)component; result = column.isSelfSorted(); }
                 */
            }
        }
        return result;
    }

    /**
     * @param component
     * @return
     */
    protected boolean isColumnRendered(UIComponent component) {
        boolean rendered = true;
        try {
            rendered = component.isRendered();
        } catch (Exception e) {
            // DO nothing, rendered binded to row variable;
        }
        return rendered;
    }
    
    protected Integer getFrozenColumnsCount(FacesContext context, UIDataTable table) {
    	 Integer frozenColumns = (Integer)table.getAttributes().get("frozenColumns");
    	 Integer columnsCount = getColumnsCount(table);
    	 
    	 if (frozenColumns != null && frozenColumns >= columnsCount) {
    		 frozenColumns = columnsCount - 1;
    	 }
    	 
    	 return frozenColumns;
    }

    protected void encodeHeaderFacets(FacesContext context,
            ResponseWriter writer, UIExtendedDataTable table,
            Iterator<UIColumn> headers, String skinCellClass,
            String headerClass, String facetName, String element, int colCount, int startCol, int endCol)
            throws IOException {
        int t_colCount = 0;

        // HeaderEncodeStrategy richEncodeStrategy = new
        // RichHeaderEncodeStrategy();
        // HeaderEncodeStrategy simpleEncodeStrategy = new
        // SimpleHeaderEncodeStrategy();

        int i=0;
        table.toggleEditors(context, true);
        while (headers.hasNext()) {
        	
        	if (i < startCol || i>=endCol) {
        		headers.next();
        		i++;
        		continue;
        	}
        	
            UIColumn column = headers.next();
            if (isColumnRendered(column)) {
                if ((Integer) column.getAttributes().get("colspan") != null) {
                    t_colCount = t_colCount
                            + ((Integer) column.getAttributes().get("colspan"))
                                    .intValue();
                } else {
                    t_colCount++;
                }
                if (t_colCount > colCount) {
                    break;
                }

                String classAttribute = facetName + "Class";
                String columnHeaderClass = (String) column.getAttributes().get(
                        classAttribute);

                writer.startElement(element, column);
                encodeStyleClass(writer, null, skinCellClass, headerClass,
                        columnHeaderClass);
                writer.writeAttribute("scope", "col", null);
                getUtils().encodeAttribute(context, column, "colspan");

                boolean sortableColumn = column
                        .getValueExpression("comparator") != null
                        || column.getValueExpression("sortBy") != null;
                column.getAttributes().put("sortable",
                        Boolean.valueOf(sortableColumn));

                
                
                HeaderEncodeStrategy strategy = (column instanceof UIColumn && "header"
                        .equals(facetName)) ? new G3HeaderEncodeStrategy()
                        : new SimpleHeaderEncodeStrategy();

                strategy.encodeBegin(context, writer, column, facetName,
                        sortableColumn);

                UIComponent facet = column.getFacet(facetName);
                if (facet != null && isColumnRendered(facet)) {
                    renderChild(context, facet);
                }

                strategy.encodeEnd(context, writer, column, facetName,
                        sortableColumn);

                if (column.getFacet("editor") != null) {
                	 writer.startElement(HTML.DIV_ELEM, column);
                     writer.writeAttribute(HTML.class_ATTRIBUTE, "rich-extdt-ie", null);
                     writer.startElement(HTML.DIV_ELEM, column);
                     writer.writeAttribute(HTML.class_ATTRIBUTE, "rich-extdt-ie-editor", null);
                     renderChild(context, column.getFacet("editor"));
                     writer.endElement(HTML.DIV_ELEM);
                     writer.startElement(HTML.DIV_ELEM, column);
                     writer.writeAttribute(HTML.class_ATTRIBUTE, "rich-extdt-ie-content", null);
                     writer.endElement(HTML.DIV_ELEM);
                     writer.endElement(HTML.DIV_ELEM);
                }

                writer.endElement(element);

            }
            i++;
         }
         table.toggleEditors(context, false);
    }
    
    public void encodeFrozenFooter(FacesContext context, UIExtendedDataTable table)
    throws IOException {
    	Integer frozenColumns = getFrozenColumnsCount(context, table);
    	if (frozenColumns != null) {
    		encodeFooter(context, table, 0, frozenColumns);
    	}
    }
    
    public void encodeFooter(FacesContext context, UIExtendedDataTable table)
    throws IOException {
    	Integer frozenColumns = getFrozenColumnsCount(context, table);
    	int numberOfColumns = table.getVisibleColumnsCount() + 1;
    	if (frozenColumns != null) {
    		encodeFooter(context, table, frozenColumns, numberOfColumns);
    	}
    }

    public void encodeFooter(FacesContext context, UIExtendedDataTable table, int startColumns, int endColumn)
            throws IOException {

        ResponseWriter writer = context.getResponseWriter();
        UIComponent footer = table.getFooter();
        boolean columnFacetPresent = isColumnFacetPresent(table, "footer");
        Iterator<UIColumn> tableColumns = table.getSortedColumns();
        // int columns = getColumnsCount(table);
        int columns = table.getVisibleColumnsCount() + 1;
        if (footer != null || columnFacetPresent) {
           // writer.startElement("tbody", table);
            //writer.writeAttribute(HTML.id_ATTRIBUTE, table.getBaseClientId(context)
            //        + ":footer", null);
            String footerClass = (String) table.getAttributes().get(
                    "footerClass");

            if (columnFacetPresent) {
                writer.startElement("tr", table);
                encodeStyleClass(writer, null,
                        "extdt-subfooter rich-extdt-subfooter", null,
                        footerClass);

                encodeHeaderFacets(context, writer, table, tableColumns,
                        "extdt-subfootercell rich-extdt-subfootercell",
                        footerClass, "footer", "td", columns, startColumns, endColumn);
                writer.startElement("td", table);
                encodeStyleClass(writer, null,
                        "extdt-subfootercell rich-extdt-subfootercell", null,
                        null);
                writer.endElement("td");
                writer.endElement("tr");
            }
            /*
            if (footer != null) {
                encodeTableHeaderFacet(context, columns, writer, footer,
                        "extdt-footer rich-extdt-footer",
                        "extdt-footer-continue rich-extdt-footer-continue",
                        "extdt-footercell rich-extdt-footercell",
                        footerClass, "td", "footer");
            }*/
           // writer.endElement("tbody");
        }

    }
    
    public void encodeTableFooter(FacesContext context, UIExtendedDataTable table)
    throws IOException {
    	 ResponseWriter writer = context.getResponseWriter();
    	 UIComponent footer = table.getFooter();
         String footerClass = (String) table.getAttributes().get(
         "footerClass");
         int columns = table.getVisibleColumnsCount() + 1;
    	 if (footer != null) {
    		 writer.startElement("tfoot", table);
    		 writer.writeAttribute(HTML.id_ATTRIBUTE, table.getBaseClientId(context)
    		                    + ":footer", null);
             encodeTableHeaderFacet(context, columns, writer, footer,
                     "extdt-footer rich-extdt-footer",
                     "extdt-footer-continue rich-extdt-footer-continue",
                     "extdt-footercell rich-extdt-footercell",
                     footerClass, "td", "footer");
             writer.endElement("tfoot");
         }
        
    }

    /**
     * Encodes the false row to enable proper rendering in IE when rows grouping
     * is on (IE won't render the table properly if it has first row with
     * colspan).
     * 
     * @param table
     *            to render false row to,
     * @param context
     *            of the table.
     * @throws IOException
     */

    private void encodeFakeIeRow(FacesContext context,
            com.exadel.siperian.component.UIExtendedDataTable table, ExtendedTableHolder holder)
            throws IOException {

        ResponseWriter writer = context.getResponseWriter();
        int numberOfColumns = getColumnsCount(table) + 1;

        writer.startElement("tr", table);
        encodeStyleClass(writer, null, "extdt-fakeierow ", null, null);
        writer.writeAttribute(HTML.id_ATTRIBUTE, table.getBaseClientId(context)
                + ":body:fakeIeRow", null);
        for (int i = 0; i < numberOfColumns; i++) {
            writer.startElement("td", table);
            encodeStyleClass(writer, null, "extdt-fakeierow " + getCellSkinClass(), null, null);
            writer.endElement("td");
        }
        writer.endElement("tr");
    }


    private void encodeGroupAsset(FacesContext context, UIExtendedDataTable table, ExtendedTableHolder holder) throws IOException {
    	
    	if (!table.isRootRow()) {
    		return;
    	}
    	
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement(HTML.SPAN_ELEM, table);
              
        
        writer.startElement(HTML.IMG_ELEMENT, table);
        writer.writeAttribute("class", "groupImg", null);
        writer.writeAttribute("id", table.getRowKey(), null);  
        String imagePlusUri = getResource(
                "/org/richfaces/renderkit/html/images/plusIcon.gif").getUri(
                context, null);
        String imageMinusUri = getResource(
                "/org/richfaces/renderkit/html/images/minusIcon.gif").getUri(
                context, null);
        
        if (holder.isRootRowExpanded()) {
            writer.writeAttribute(HTML.src_ATTRIBUTE, imageMinusUri, null);
        } else {
            writer.writeAttribute(HTML.src_ATTRIBUTE, imagePlusUri, null);
        }
        writer.endElement(HTML.IMG_ELEMENT);
        writer.endElement(HTML.SPAN_ELEM);
  
        
    }
    
    protected boolean rowGroupChanged(FacesContext context,
            ExtendedTableHolder holder) {
        com.exadel.siperian.component.UIExtendedDataTable table = holder.getTable();
        if (holder.getLastData() == null)
            return true;

        // get sort fields
        List<SortField2> sortFields = table.getSortFields();
        // get group field which is actually the first from sort fields
        List<SortField2> groupFields = new ArrayList<SortField2>();
        if (sortFields != null && !sortFields.isEmpty())
            groupFields.add(sortFields.get(0));
        // create wrapper factory
        ObjectWrapperFactory wrapperFactory = new ObjectWrapperFactory(context,
                table.getVar(), groupFields);
        // create wrapper for last data
        JavaBeanWrapper wrappedLstD = wrapperFactory.wrapObject(holder
                .getLastData());
        // create wrapper for current data
        JavaBeanWrapper wrappedActD = wrapperFactory.wrapObject(table
                .getRowData());
        // create comparator
        WrappedBeanComparator2 wrappedBeanComparator = new WrappedBeanComparator2(
                (groupFields));
        // compare last and current data
        return (wrappedBeanComparator.compare(wrappedLstD, wrappedActD) != 0);
    }// rowGroupChanged

    public void encodeOneRow(FacesContext context, ExtendedTableHolder holder)
            throws IOException {
        com.exadel.siperian.component.UIExtendedDataTable table = holder.getTable();
        ResponseWriter writer = context.getResponseWriter();
        Iterator<UIColumn> iter = table.getSortedColumns();
        boolean first = true;
        int currentColumn = 0;
        UIColumn column = null;
        
        if (holder.isFirstRow()) {
            encodeFakeIeRow(context, table, holder);
        }
        
        holder.setFirstRow(false);
        
        Integer frozenColumns = holder.getFrozenColumns();
        Integer frozenColumnsAttr = getFrozenColumnsCount(context, table);

        // Go to non-frozen columns
        if (frozenColumnsAttr != null && frozenColumns == null) {
        	int i = frozenColumnsAttr;
        	while (i-- > 0 && iter.hasNext()) {
        		iter.next();
        	}
        }
        
        int counter = 0;
        while (iter.hasNext()) {
        	
        	if (frozenColumns != null && counter >= frozenColumns) {
        		break;
        	}
        	
            column = iter.next();
            // Start new row for first column - expect a case of the detail
            // table, which will be insert own row.
            boolean isRow = (column instanceof Row);
            
            if (table.isGroupingOn() && table.isRootRow()) {
            	holder.setRootRowExpanded(table.isRootRowExpanded());
            }else if (!table.isGroupingOn()) {
            	holder.setRootRowExpanded(true);
            }

        	//holder.setNotEditable(isNotEditable(column));
                       
            if (first && !isRow) {
                encodeRowStart(context, first ? getFirstRowSkinClass() : "", table, holder, writer);

            }

            if (column.isRendered()) {
            	encodeCell(context, holder, column, first, currentColumn);
            }
            
            currentColumn++;
            first = false;
            counter++;
        }
        
        // encode additional empty row for resizing
        if (frozenColumns == null) {
	        writer.startElement(HTML.td_ELEM, table);
	        String columnClass = holder.getColumnClass(currentColumn);
	        encodeStyleClass(writer, null, "extdt-empty-cell rich-extdt-cell", null, columnClass);
	        writer.endElement(HTML.td_ELEM);
        }
        // Close row if then is open.
        if (!first && !(column instanceof Row)) {
            writer.endElement(HTML.TR_ELEMENT);
        }
    }
    
    
    public void encodeGroupingCell(FacesContext context, ExtendedTableHolder holder) throws IOException {
    	ResponseWriter writer = context.getResponseWriter();
    	UIExtendedDataTable table = holder.getTable();
    	
    	// UIColumn don't have own renderer
        writer.startElement(HTML.td_ELEM, table);
        
        // TODO - encode column attributes.
        writer.startElement(HTML.DIV_ELEM, table);
        writer.writeAttribute("class", "extdt-cell-div extdt-cell-grouping", null);
        
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.td_ELEM);
    	
    }
    
    public void encodeCell(FacesContext context, ExtendedTableHolder holder, UIColumn column, boolean first, int currentColumn) throws IOException {
    	ResponseWriter writer = context.getResponseWriter();
    	UIExtendedDataTable table = holder.getTable();
    	
    	// UIColumn don't have own renderer
        writer.startElement(HTML.td_ELEM, table);
        getUtils().encodeId(context, column);
        
        String columnClass = holder.getColumnClass(currentColumn);
        if (table.isNotEditable(column)) {
        	columnClass += " extdt-cell-not-editable";
        }
        
        encodeStyleClass(writer, null, getCellSkinClass(), columnClass,
                column.getAttributes().get("styleClass"));
        encodeStyle(writer, null, null, null, column.getAttributes().get("style"));
      
        // TODO - encode column attributes.
        writer.startElement(HTML.DIV_ELEM, table);
        
        writer.writeAttribute("class", "extdt-cell-div", null);
        
        boolean isColGroup = column.getAttributes().get("GroupColumn") != null;
        
        // IE
        if (isColGroup) {
        	
        	renderChildren(context, column);
        	
        	if (column.getFacet("beforeGroupControl") != null) {
        		renderChild(context, column.getFacet("beforeGroupControl"));
        	}
    		encodeGroupAsset(context, table, holder);
        	if (column.getFacet("afterGroupControl") != null) {
        		renderChild(context, column.getFacet("afterGroupControl"));
        	}
    	}

        boolean isCellModified = table.isCellModified(column);
        
        if (isCellModified) {
        	writer.startElement(HTML.DIV_ELEM, column);
        	writer.writeAttribute("class", "extdt-cell-edit", null);
        	writer.startElement(HTML.DIV_ELEM, column);
        	renderChildren(context, column);
        	writer.endElement(HTML.DIV_ELEM);
        	encodeModifiedState(context, column, writer);
        	writer.endElement(HTML.DIV_ELEM);
        }else if (!isColGroup) {
            renderChildren(context, column);        	
        }
        
		
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.td_ELEM);
    	
    }
    
    protected void encodeModifiedState(FacesContext context, UIColumn column, ResponseWriter writer) throws IOException {
    	
    	if (column.getFacet("modifiedIcon") != null) {
    		writer.startElement(HTML.SPAN_ELEM, column);
    		writer.writeAttribute("class", "extdt-cell-modified-img", null);
    		renderChild(context, column.getFacet("modifiedIcon"));
    		writer.endElement(HTML.SPAN_ELEM);
    	}else {
        	writer.startElement(HTML.IMG_ELEMENT, column);
        	writer.writeAttribute("class", "extdt-cell-modified-img", null);
        	writer.writeAttribute("src", getResource(
			"/com/exadel/siperian/renderkit/html/images/modified.gif")
			.getUri(context, null), null);
        	
        	writer.endElement(HTML.IMG_ELEMENT);
    	}
    }

    protected void encodeRowStart(FacesContext context, String rowClass,
    		UIExtendedDataTable table, ExtendedTableHolder holder, ResponseWriter writer)
            throws IOException {
        encodeRowStart(context, getRowSkinClass(), rowClass, table, holder,
                writer);
    }

    /**
     * @return
     */
    protected String getRowSkinClass() {
        return "extdt-row rich-extdt-row";
    }

    /**
     * @return
     */
    protected String getFirstRowSkinClass() {
        return "extdt-firstrow rich-extdt-firstrow";
    }

    /**
     * @return
     */
    protected String getCellSkinClass() {
        return "extdt-cell rich-extdt-cell";

    }

    protected void encodeRowStart(FacesContext context, String skinClass,
            String rowClass, UIExtendedDataTable table, ExtendedTableHolder holder,
            ResponseWriter writer) throws IOException {
        writer.startElement(HTML.TR_ELEMENT, table);
 
        String id = table.getBaseClientId(context) + ":n:" + holder.getRowCounter();
        getUtils().writeAttribute(writer, "id", id);
        if (!table.isRootRow() && (!holder.isRootRowExpanded() && !table.isRoowRootAjaxExpanded())) {
        	getUtils().writeAttribute(writer, "style", "display: none");
        }
        
        boolean isRootRow = table.isRootRow(); 
 
        String s = holder.getRowClass();
        s += isRootRow ? " extdt-root-row" : " exdt-child-row";
        if (isRootRow && (holder.isRootRowExpanded() || holder.isPreviousRootRowExpanded())) {
        	s += " extdt-root-row-border";
        	if (holder.isRootRowExpanded()) {
        		s += " extdt-root-row-expanded";	
        	}
        }
        
        encodeStyleClass(writer, s, skinClass, null, rowClass);
        encodeRowEvents(context, table);
    }
    
   

    /**
     * Row ID generator
     * 
     * @param context
     *            current FacesContext
     * @param writer
     *            ResponseWriter for FacesContext
     * @param table
     *            table for which this row is being encoded
     * @param rowId
     *            new rowId to encode
     * @throws IOException
     *             if ResponseWriter fails
     */
//    protected String encodeRowId(FacesContext context, ResponseWriter writer,
//            UIDataTable table, int rowId) throws IOException {
//        String ownerId = table.getBaseClientId(context);
//        getUtils().writeAttribute(writer, "id", ownerId + ":n:" + rowId);
//    }

    /**
     * Calculate total number of columns in table.
     * 
     * @param context
     * @param table
     * @return
     */
    protected int getColumnsCount(UIDataTable table) {
        int count = 0;
        // check for exact value in component
        Integer span = (Integer) table.getAttributes().get("columns");
        if (null != span && span.intValue() != Integer.MIN_VALUE) {
            count = span.intValue();
        } else {
            // calculate max html columns count for all columns/rows children.
            Iterator<UIComponent> col = table.columns();
            count = calculateRowColumns(col);
        }
//        if (((UIExtendedDataTable)table).isGroupingOn()) {
//        	count++;
//        }
        return count;
    }

    /**
     * Calculate max number of columns per row. For rows, recursive calculate
     * max length.
     * 
     * @param col -
     *            Iterator other all columns in table.
     * @return
     */
    protected int calculateRowColumns(Iterator<UIComponent> col) {
        int count = 0;
        int currentLength = 0;
        while (col.hasNext()) {
            UIComponent column = (UIComponent) col.next();
            if (column.isRendered()) {
                if (column instanceof Row) {
                    // Store max calculated value of previsous rows.
                    if (currentLength > count) {
                        count = currentLength;
                    }
                    // Calculate number of columns in row.
                    currentLength = calculateRowColumns(((Row) column)
                            .columns());
                    // Store max calculated value
                    if (currentLength > count) {
                        count = currentLength;
                    }
                    currentLength = 0;
                } else if (column instanceof Column) {
                    Column tableColumn = (Column) column;
                    // For new row, save length of previsous.
                    if (tableColumn.isBreakBefore()) {
                        if (currentLength > count) {
                            count = currentLength;
                        }
                        currentLength = 0;
                    }
                    Integer colspan = (Integer) column.getAttributes().get(
                            "colspan");
                    // Append colspan of this column
                    if (null != colspan
                            && colspan.intValue() != Integer.MIN_VALUE) {
                        currentLength += colspan.intValue();
                    } else {
                        currentLength++;
                    }
                } else if (column instanceof javax.faces.component.UIColumn) {
                    // UIColumn always have colspan == 1.
                    currentLength++;
                }

            }
        }
        if (currentLength > count) {
            count = currentLength;
        }
        return count;
    }

    public void encodeScriptIfNecessary(FacesContext context,
            UIExtendedDataTable table) throws IOException {
        boolean shouldRender = false;
        Iterator<UIColumn> columns = table.getSortedColumns();
        while (columns.hasNext() && !shouldRender) {
            UIColumn next = columns.next();
            shouldRender = next.isSortable();
            shouldRender = true;// shouldRender || (next instanceof
            // HtmlDataColumn);
        }
        shouldRender = true;
        if (shouldRender) {
            getUtils().writeScript(context, table,
                    createClientDataTable(context, table));
        }
    }

    public String createClientDataTable(FacesContext context,
            com.exadel.siperian.component.UIExtendedDataTable table) {
        JSFunction function = new JSFunction("new ExtendedDataTable.DataTable");
        function.addParameter(table.getBaseClientId(context));
        ScriptOptions scriptOptions = new ScriptOptions(table);
        // add on resize column AJAX function
        scriptOptions.addOption(SCROLLABLE, table.getAttributes().get(SCROLLABLE));
        scriptOptions.addOption(ALLOWCELLSELECTION,table.getAttributes().get(ALLOWCELLSELECTION));
        scriptOptions.addOption(SELECTEDCELLCLASS,table.getAttributes().get(SELECTEDCELLCLASS));
        
        scriptOptions.addOption(RESIZIABLE, table.getAttributes().get(RESIZIABLE));
        scriptOptions.addOption(HORIZONTALSCROLLING, table.getAttributes().get(HORIZONTALSCROLLING));
        scriptOptions.addOption(ON_RESIZE_FUNCTION, getOnResizeFunctionDef(
                context, table));
        scriptOptions.addOption("forseSorting",table.getAttributes().get("forseSorting"));
        scriptOptions.addOption(SORT_FUNCTION, getSortFunctionDef(context,
                table));
        //scriptOptions.addOption(SHOW_MENU_FUNCTION, getShowMenuFunction(
        //        context, table));
        scriptOptions.addOption(CAN_HIDE, isSingleCanHideTrueAvailable(table));
        scriptOptions.addOption(SCROLL_POSITION_PARAMETER, table.getScrollPosition());
        scriptOptions.addOption(ROOT_ROW_AJAX_EXPANDED, table.getAttributes().get(ROOT_ROW_AJAX_EXPANDED));
        
        if (table.getAttributes().get(ON_ROW_CONTEXT_MENU) != null) {
        	scriptOptions.addOption(ON_ROW_CONTEXT_MENU, new JSFunctionDefinition("event").addToBody(table.getAttributes().get(ON_ROW_CONTEXT_MENU)));
        }
        if (table.getAttributes().get("onscroll") != null) {
        	scriptOptions.addOption(ON_SCROLL_FUNCTION, new JSFunctionDefinition("event").addToBody(table.getAttributes().get("onscroll")));
        }
        
        /* Not needed if we do not save open/close state */
        scriptOptions.addOption(ON_GROUP_TOGGLE_FUNCTION,
                getOnGroupToggleFunctionDef(context, table));
        scriptOptions.addOption(EDIT_ACTIONE_NAME, getOnEditComplete(context, table));
        scriptOptions.addOption("minColumnWidth", MIN_COLUMN_WIDTH);
        scriptOptions.addOption("modifiedData", table.hasModifiedData());
        scriptOptions.addOption("synchronizeHorizonalScrolling", table.getAttributes().get("synchronizeHorizonalScrolling"));
        
        Map<String, Object> editing = new HashMap<String, Object>();
        
        Iterator<UIComponent> iterator = table.columns();
        
        while (iterator.hasNext()) {
        	UIComponent component = iterator.next();
        	
        	if (component instanceof UIColumn) {
        		UIColumn column = (UIColumn)component;
        		
        		if (column.getFacet("editor") != null) {
        			Map<String, Object> map = new HashMap<String, Object>();
        			if (column.getAttributes().get("onEdit") != null) {
        				JSFunctionDefinition definition = new JSFunctionDefinition("event", "data");
        				definition.addToBody(column.getAttributes().get("onEdit"));
        				map.put("onEdit", definition);
        			}	
        			if (column.getAttributes().get("onEdited") != null) {
        				JSFunctionDefinition definition = new JSFunctionDefinition("event", "data");
        				definition.addToBody(column.getAttributes().get("onEdited"));
        				map.put("onEdited", definition);
        			}
        			if (column.getAttributes().get("onError") != null) {
        				JSFunctionDefinition definition = new JSFunctionDefinition("errors");
        				definition.addToBody(column.getAttributes().get("onError"));
        				map.put("onError", definition);
        			}

            		editing.put(column.getClientId(context), map);
        		}
        	}
        }
        
        scriptOptions.addOption("editing", editing);
        
        Iterator<UIColumn> cols = table.getSortedColumns();
        int counter = 0;
        Map<Integer, Object> onColumnResizeFunctions = new HashMap<Integer, Object>();
        while (cols.hasNext()) {
        	UIComponent col = cols.next();
        	if (col.isRendered() && col.getAttributes().get(ON_RESIZE_FUNCTION) != null) {
        		JSFunctionDefinition definition = new JSFunctionDefinition("width");
        		definition.addToBody(col.getAttributes().get(ON_RESIZE_FUNCTION));
        		onColumnResizeFunctions.put(counter, definition);
        	}
        	counter++;
        }
        if (onColumnResizeFunctions.size() > 0) {
        	 scriptOptions.addOption("onColumnResizeFunctions", onColumnResizeFunctions);
        }
        
        composite.mergeScriptOptions(scriptOptions, context, table);
        function.addParameter(scriptOptions);
        
        Object onupdatelayout = table.getAttributes().get("onlayoutupdate");
        if (onupdatelayout != null) {
        	JSFunctionDefinition onupdateLayouHandler = new JSFunctionDefinition("event");
        	onupdateLayouHandler.addToBody(onupdatelayout);
        	function.addParameter(onupdateLayouHandler);
        }else {
        	function.addParameter(JSReference.NULL);
        }
        
        if ((Boolean)table.getAttributes().get("dndSupported")) {
        	function.addParameter(getDragContext(context, table));
        }else {
        	function.addParameter(JSReference.NULL);
        }
        
        JSFunctionDefinition onkeydown = null;
        if (table.getAttributes().get("onkeydown") != null) {
        	onkeydown = new JSFunctionDefinition("event");
        	onkeydown.addToBody(table.getAttributes().get("onkeydown"));
        }
        if (onkeydown != null) {
        	function.addParameter(onkeydown);
        }else {
        	function.addParameter(JSReference.NULL);
        }
        
        return function.toScript();
    }
    
    private Map<String, Object> getDragContext(FacesContext context, UIDataTable table) {
    	Map<String, Object> dragContext = new HashMap<String, Object>();
    	UIComponent form = AjaxRendererUtils.getNestingForm(table);
    	if (form != null) {
    		dragContext.put("formId", AjaxRendererUtils.getNestingForm(table).getClientId(context));
    	}else {
    		dragContext.put("formId", JSReference.NULL);
    	}
    	
    	String containerId = ((UIComponent)AjaxRendererUtils.findAjaxContainer(context, table)).getClientId(context);
    	if (containerId != null) {
    		dragContext.put("containerId", containerId);
    	}else {
    		dragContext.put("containerId", JSReference.NULL);
    	}
    	
    	dragContext.put("actionUrl", AjaxContext.getCurrentInstance().getAjaxActionURL(context));
    	
    	return dragContext;
    }

    protected JSFunctionDefinition getShowMenuFunction(FacesContext context,
            UIDataTable table) {
        return new RichTableMenuRenderer().createShowMenuEventFunction();
    }

    protected JSFunctionDefinition getSortFunctionDef(FacesContext context,
            UIDataTable table) {
        return getSortFunctionDef(context, table, null);
    }

    protected JSFunctionDefinition getSortFunctionDef(FacesContext context,
            UIDataTable table, Boolean asc) {
        JSFunctionDefinition definition = new JSFunctionDefinition();
        definition.addParameter("event");
        definition.addParameter("columnId");
        String id = table.getClientId(context);
        Map<String, Object> eventOptions = AjaxRendererUtils.buildEventOptions(
                context, table);
        @SuppressWarnings("unchecked")
        Map<String, Object> parameters = (Map<String, Object>) eventOptions
                .get("parameters");
        parameters.put(id, SORT_FILTER_PARAMETER);
        if (asc != null) {
            parameters.put(SORT_DIR_PARAMETER, asc ? SORT_DIR_PARAMETER_ASC
                    : SORT_DIR_PARAMETER_DESC);
        }
        // parameters.put(SORT_FILTER_PARAMETER, column.getClientId(context));
        JSFunctionDefinition onAjaxCompleteFunction = getOnAjaxCompleteFunction(
                context, table);
        if (onAjaxCompleteFunction != null) {
            eventOptions.put(AjaxRendererUtils.ONCOMPLETE_ATTR_NAME,
                    onAjaxCompleteFunction);
        }
        definition.addToBody("var options = ").addToBody(
                ScriptUtils.toScript(eventOptions)).addToBody(";\n");
        definition.addToBody("options.parameters['" + SORT_FILTER_PARAMETER
                + "'] = columnId;\n");
        JSFunction ajaxFunction = AjaxRendererUtils.buildAjaxFunction(table,
                context);
        ajaxFunction.addParameter(new JSReference("options"));
        definition.addToBody(ajaxFunction.toScript()).addToBody(";\n");
        return definition;
    }// getSortFunction

    protected JSFunction getSortFunction(FacesContext context, UIDataTable table) {
        String id = table.getClientId(context);
        Map<String, Object> requestOpts = AjaxRendererUtils.buildEventOptions(
                context, table);
        @SuppressWarnings("unchecked")
        Map<String, Object> parameters = (Map<String, Object>) requestOpts
                .get("parameters");
        parameters.put(id, SORT_FILTER_PARAMETER);
        parameters.put(SORT_DIR_PARAMETER, "{sortDirection}");
        parameters.put(SORT_FILTER_PARAMETER, "{columnId}");
        JSFunctionDefinition onAjaxCompleteFunction = getOnAjaxCompleteFunction(
                context, table);
        if (onAjaxCompleteFunction != null) {
            requestOpts.put(AjaxRendererUtils.ONCOMPLETE_ATTR_NAME,
                    onAjaxCompleteFunction);
        }
        JSFunction ajaxFunction = AjaxRendererUtils.buildAjaxFunction(table,
                context);
        ajaxFunction.addParameter(requestOpts);
        return ajaxFunction;
    }// getSortFunction
    
    protected JSFunctionDefinition getOnEditComplete(FacesContext context, UIDataTable table) {
    	  JSFunctionDefinition definition = new JSFunctionDefinition();
          definition.addParameter("event");
          definition.addParameter("rowIndex");
          definition.addParameter("colId");
          definition.addParameter("onbeforedomupdate");
          definition.addParameter("oncomplete");
          
          Map<String, Object> eventOptions = AjaxRendererUtils.buildEventOptions(
                  context, table);
          @SuppressWarnings("unchecked")
          Map<String, Object> parameters = (Map<String, Object>) eventOptions
                  .get("parameters");
          parameters.put(EDIT_ACTIONE_NAME, EDIT_ACTIONE_NAME);
          
          definition.addToBody("var options = ").addToBody(
                  ScriptUtils.toScript(eventOptions)).addToBody(";\n");
          definition
                  .addToBody("options.parameters['rowIndex'] = rowIndex;\n options.parameters['colId'] = colId;\n options['onbeforedomupdate'] = onbeforedomupdate; \n options['oncomplete'] = oncomplete; \n options.parameters['ajaxSingle'] = '" +table.getClientId(context)+ "';");
          JSFunction ajaxFunction = AjaxRendererUtils.buildAjaxFunction(table,
                  context);
          ajaxFunction.addParameter(new JSReference("options"));
          definition.addToBody(ajaxFunction.toScript()).addToBody(";\n");
          return definition;
    }

    protected JSFunctionDefinition getOnGroupToggleFunctionDef(
            FacesContext context, UIDataTable table) {
        JSFunctionDefinition definition = new JSFunctionDefinition();
        definition.addParameter("event");
        definition.addParameter("groupIndex");
        definition.addParameter("rowCounter");
        definition.addParameter("expanded");
        definition.addParameter("ajaxExpanded");
        definition.addParameter("oncomplete");
        Map<String, Object> eventOptions = AjaxRendererUtils.buildEventOptions(
                context, table);
        @SuppressWarnings("unchecked")
        Map<String, Object> parameters = (Map<String, Object>) eventOptions
                .get("parameters");
        parameters.put(GROUP_TOGGLE_ACTION_NAME, GROUP_TOGGLE_ACTION_NAME);
        definition.addToBody("var options = ").addToBody(
                ScriptUtils.toScript(eventOptions)).addToBody(";\n");
        definition
                .addToBody("options.parameters['groupIndex'] = groupIndex;\n options.parameters['rowCounter'] = rowCounter;\n options.parameters['expanded'] = expanded; \n options.parameters['ajaxExpanded'] = ajaxExpanded; \n options['oncomplete'] = oncomplete; \n");
        JSFunction ajaxFunction = AjaxRendererUtils.buildAjaxFunction(table,
                context);
        ajaxFunction.addParameter(new JSReference("options"));
        definition.addToBody(ajaxFunction.toScript()).addToBody(";\n");
        return definition;
    }// getSortFunction

    protected JSFunction getGroupFunction(FacesContext context,
            UIDataTable table) {
        String id = table.getClientId(context);
        Map<String, Object> requestOpts = AjaxRendererUtils.buildEventOptions(
                context, table);
        @SuppressWarnings("unchecked")
        Map<String, Object> parameters = (Map<String, Object>) requestOpts
                .get("parameters");
        parameters.put(id, GROUP_FILTER_PARAMETER);
        parameters.put(GROUP_FILTER_PARAMETER, "{columnId}");
        JSFunctionDefinition onAjaxCompleteFunction = getOnAjaxCompleteFunction(
                context, table);
        if (onAjaxCompleteFunction != null) {
            requestOpts.put(AjaxRendererUtils.ONCOMPLETE_ATTR_NAME,
                    onAjaxCompleteFunction);
        }
        JSFunction ajaxFunction = AjaxRendererUtils.buildAjaxFunction(table,
                context);
        ajaxFunction.addParameter(requestOpts);
        return ajaxFunction;
    }// getSortFunction

    protected JSFunctionDefinition getOnResizeFunctionDef(FacesContext context,
            UIDataTable table) {
        JSFunctionDefinition definition = new JSFunctionDefinition();
        definition.addParameter("event");
        definition.addParameter("columnWidths");

        Map<String, Object> eventOptions = AjaxRendererUtils.buildEventOptions(
                context, table);
        @SuppressWarnings("unchecked")
        Map<String, Object> parameters = (Map<String, Object>) eventOptions
                .get("parameters");
        parameters.put(COL_RESIZE_ACTION_NAME, COL_RESIZE_ACTION_NAME);
        definition.addToBody("var options = ").addToBody(
                ScriptUtils.toScript(eventOptions)).addToBody(";\n");
        definition
                .addToBody("options.parameters['"+table.getClientId(context)+":columnWidths'] = columnWidths;\n");

        JSFunction ajaxFunction = AjaxRendererUtils.buildAjaxFunction(table,
                context);
        ajaxFunction.addParameter(new JSReference("options"));
        definition.addToBody(ajaxFunction.toScript()).addToBody(";\n");
        return definition;
    }

    protected JSFunction getChangeColumnVisibilityFunction(
            FacesContext context, UIDataTable table) {
        boolean ajaxSingle = true;
        Map<String, Object> requestOpts = AjaxRendererUtils.buildEventOptions(
                context, table);

        JSFunctionDefinition onAjaxCompleteFunction = getOnAjaxCompleteFunction(
                context, table);
        if (onAjaxCompleteFunction != null)
            requestOpts.put(AjaxRendererUtils.ONCOMPLETE_ATTR_NAME,
                    onAjaxCompleteFunction);

        @SuppressWarnings("unchecked")
        Map<String, Object> parameters = (Map<String, Object>) requestOpts
                .get("parameters");
        if (parameters == null) {
            parameters = new HashMap<String, Object>();
            requestOpts.put("parameters", parameters);
        }
        if (ajaxSingle) {
            if (!parameters
                    .containsKey(AjaxRendererUtils.AJAX_SINGLE_PARAMETER_NAME))
                parameters.put(AjaxRendererUtils.AJAX_SINGLE_PARAMETER_NAME,
                        table.getClientId(context));
            if (!requestOpts.containsKey("control"))
                requestOpts.put("control", JSReference.THIS);
        }
        parameters.put(
                table.getClientId(context) + ":" + CHANGE_COL_VISIBILITY,
                "{columnId}");

        JSFunction ajaxFunction = AjaxRendererUtils.buildAjaxFunction(table,
                context);
        ajaxFunction.addParameter(requestOpts);
        return ajaxFunction;
    }

    protected JSFunction getPreSendAjaxRequestFunction(FacesContext context,
            UIDataTable table) {
        return new JSFunction(getJavaScriptVarName(context, table)
                + ".preSendAjaxRequest");
    }

    protected void preDecode(FacesContext context, UIComponent component) {
        if (component instanceof com.exadel.siperian.component.UIExtendedDataTable) {
            com.exadel.siperian.component.UIExtendedDataTable table = (com.exadel.siperian.component.UIExtendedDataTable) component;
            table.ensureTableStateInitialized();
        }
        super.preDecode(context, component);
    }

    protected void preEncodeBegin(FacesContext context, UIComponent component)
            throws IOException {
        if (component instanceof com.exadel.siperian.component.UIExtendedDataTable) {
            UIExtendedDataTable table = (com.exadel.siperian.component.UIExtendedDataTable) component;
            
            for (Iterator<UIColumn> columns = table.getChildColumns(); columns
                    .hasNext();) {
                UIColumn column = columns.next();
                column.setId(column.getId());
            }

            table.ensureTableStateInitialized();
        }
        super.preEncodeBegin(context, component);
    }

    protected void doDecode(FacesContext context, UIComponent component) {
        super.doDecode(context, component);
        composite.decode(context, component);
        
        if (component instanceof com.exadel.siperian.component.UIExtendedDataTable) {
            com.exadel.siperian.component.UIExtendedDataTable table = (com.exadel.siperian.component.UIExtendedDataTable) component;
            //addGroupColumn(context, table);
            Map<String, String> map = context.getExternalContext()
                    .getRequestParameterMap();
            String clientId = component.getClientId(context);

            if (SORT_FILTER_PARAMETER.equals(map.get(clientId))) {
                String sortColumnId = map.get(SORT_FILTER_PARAMETER);
                boolean isSingleSortMode = !"multi".equals(table.getSortMode());
                
                boolean sorting = false;
                boolean filtering = false;

                boolean isGroupingOn = table.isGroupingOn();
                boolean sortByGroupingColumn = false;

                UIColumn groupingColumn = null;

                for (Iterator<UIColumn> columns = table.getChildColumns(); columns
                        .hasNext();) {
                    UIColumn column = columns.next();
                    String id = column.getId();
                    column.setId(id);

                    if (sortColumnId != null) {
                    	sorting = true;
                        boolean isGroupingColumn = (isGroupingOn && column
                                .equals(groupingColumn));
                        if (sortColumnId.equals(column.getClientId(context))) {
                            // set sort order
                            if (map.containsKey(SORT_DIR_PARAMETER)) {
                                String sortDir = (String) map
                                        .get(SORT_DIR_PARAMETER);
                                column
                                        .setSortOrder((sortDir
                                                .equals(SORT_DIR_PARAMETER_ASC) ? Ordering.ASCENDING
                                                : (sortDir
                                                        .equals(SORT_DIR_PARAMETER_DESC) ? Ordering.DESCENDING
                                                        : Ordering.UNSORTED)));
                            } else {
                                column.toggleSortOrder();
                            }

                            Collection<Object> sortPriority = table
                                    .getSortPriority();
                            // clear sort priority in case of single sort mode
                            if (isSingleSortMode) {
                                sortPriority.clear();
                            }
                            // add column to sort priority if is not added yet
                            if (!sortPriority.contains(id)) {
                                sortPriority.add(id);
                            }
                            if (isGroupingColumn) {
                                // set as grouping column to mark that grouping
                                // order has changed
//                                table.setGroupingColumn(column);
                            }
                        } else if (isSingleSortMode) { // in case of single
                            // sort mode
                            if (!isGroupingColumn) { // grouping is not by
                                // this column
                                if (!sortByGroupingColumn) {// sort not by
                                    // grouping column
                                    // disable sort by this column
                                    column.setSortOrder(Ordering.UNSORTED);
                                }
                            }
                        }
                    }

                    UIInput filterValueInput = (UIInput) column
                            .getFacet(FILTER_INPUT_FACET_NAME);
                    if (null != filterValueInput) {
                        filterValueInput.decode(context);
                        String oldFilterValue = column.getFilterValue();
                        Object submittedValue = filterValueInput
                                .getSubmittedValue();
                        String newFilterValue = null;
                        if (null != submittedValue) {
                            newFilterValue = filterValueInput
                                    .getSubmittedValue().toString();
                            if ((newFilterValue != null)
                                    && (newFilterValue.length() == 0)) {
                                newFilterValue = null;
                            }
                            column.setFilterValue(newFilterValue);
                        }
                        boolean filterChanged = (newFilterValue == null ? (oldFilterValue != null)
                                : !newFilterValue.equals(oldFilterValue));
                        //if (filterChanged) {
                        //    table.resetGroupVisibilityState();
                        //}
                        filtering = (filtering || filterChanged);
                    }
                }

                // AjaxContext.getCurrentInstance().addComponentToAjaxRender(component);
                if (sorting){
                	new ExtTableSortEvent(component).queue();
                }
                if (filtering){
                	new ExtTableFilterEvent(component).queue();
                }
                
            }

            String dragSourceId = (String) map
                    .get(org.richfaces.renderkit.DraggableRendererContributor.DRAG_SOURCE_ID);
            String dropTargetId = (String) map
                    .get(org.richfaces.renderkit.DropzoneRendererContributor.DROP_TARGET_ID);
            if ((dragSourceId != null) && (dropTargetId != null)) {
                Pattern sourcePattern = Pattern.compile(clientId + ":([\\w\\-]*):"
                        + com.exadel.siperian.renderkit.html.TableDragDropRenderer.DRAG_SOURCE_SCRIPT_ID);
                Pattern targetPattern = Pattern.compile(clientId + ":([\\w\\-]*):"
                        + com.exadel.siperian.renderkit.html.TableDragDropRenderer.DROP_TARGET_SCRIPT_ID + "("
                        + com.exadel.siperian.renderkit.html.TableDragDropRenderer.DROP_TARGET_BEFORE + "|"
                        + com.exadel.siperian.renderkit.html.TableDragDropRenderer.DROP_TARGET_AFTER + ")");
                Matcher sourceMatcher = sourcePattern.matcher(dragSourceId);
                Matcher targetMatcher = targetPattern.matcher(dropTargetId);
                if (sourceMatcher.find() && targetMatcher.find()) {
                    String sourceColumnId = sourceMatcher.group(1);
                    String targetColumnId = targetMatcher.group(1);
                    String kind = targetMatcher.group(2);

                    DragDropEvent dragDropEvent = new DragDropEvent(component);
                    dragDropEvent.setDragValue(sourceColumnId);
                    dragDropEvent.setDropValue(targetColumnId);
                    dragDropEvent.setDropBefore(kind
                            .equals(com.exadel.siperian.renderkit.html.TableDragDropRenderer.DROP_TARGET_BEFORE));

                    dragDropEvent.queue();
                }
            }// change column order

            // CHANGE COLUMN VISIBILITY
            String columnToChange = (String) map.get(clientId + ":"
                    + TableMenuRenderer.CHANGE_COL_VISIBILITY);
            if (columnToChange != null) {
                ChangeColumnVisibilityEvent event = new ChangeColumnVisibilityEvent(
                        component, columnToChange);

                event.queue();
            }// change column visibility

            // COLUMN RESIZE
            if (COL_RESIZE_ACTION_NAME.equals(map.get(COL_RESIZE_ACTION_NAME)) && map.get(component.getClientId(context) + ":columnWidths")!=null) {
                String colWidths = (String) map.get(component.getClientId(context) + ":columnWidths");
                ColumnResizeEvent event = new ColumnResizeEvent(component,
                        colWidths);
                event.queue();
            }
            
            if (GROUP_TOGGLE_ACTION_NAME.equals(map.get(GROUP_TOGGLE_ACTION_NAME)) && map.get(component.getClientId(context)) != null) {
            	Integer rowKey = Integer.parseInt(map.get("groupIndex"));
            	Boolean expanded = Boolean.TRUE.toString().equalsIgnoreCase(map.get("expanded"));
            	Boolean isAjax = Boolean.TRUE.toString().equalsIgnoreCase(map.get("ajaxExpanded"));
            	if (isAjax) {
            		AjaxContext ajaxContext = AjaxContext.getCurrentInstance(context);
            		ajaxContext.addComponentToAjaxRender(table);
            		ajaxContext.addRenderedArea(table.getBaseClientId(context) + "subRowsDiv");
            		context.getExternalContext().getRequestMap().put(component.getId() + ROOT_ROW_AJAX_EXPANDED, rowKey);
            	}
            	table.processToggleRootRow(rowKey, expanded);
            	context.renderResponse();
            	return;
            }
            
            if (EDIT_ACTIONE_NAME.equals(map.get(EDIT_ACTIONE_NAME)) && map.get(component.getClientId(context)) != null) {
            	String colId = map.get("colId");
            	UIColumn column = table.getColumn(colId);
            	if (column != null) {
            		table.processCellEditing(map.get("rowIndex"), column);
            	}
            	
            	
            	
            	context.renderResponse();
            	return;
            }
            
            if (map.get(clientId + SCROLL_POSITION_PARAMETER) != null) {
            	String scrollPosition = map.get(clientId + SCROLL_POSITION_PARAMETER);
            	table.processScrollPosition(scrollPosition);
            }
            
            if (map.get(clientId + CELL_STATE) != null) {
                String cellState = map.get(clientId + CELL_STATE);
                table.processCellState(cellState);
            }
      
        }
    }
    
    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {
        super.encodeEnd(context, component);
        String clientId = component.getClientId(context);
        Set<String> ajaxRenderedAreas = AjaxContext.getCurrentInstance()
                .getAjaxRenderedAreas();

        if (ajaxRenderedAreas.contains(clientId)) {
            // remove all child elements
            for (Iterator<String> iter = ajaxRenderedAreas.iterator(); iter
                    .hasNext();) {
                String area = iter.next();
                if (area.startsWith(clientId) && (!area.equals(clientId))) {
                    iter.remove();
                }
            }
        }
    }

    protected void addInplaceInput(FacesContext context, UIComponent column,
            String buffer) throws IOException {
        UIInput filterValueInput = (UIInput) column
                .getFacet(FILTER_INPUT_FACET_NAME);
        if (null == filterValueInput) {
            filterValueInput = (UIInput) context.getApplication()
                    .createComponent(UIInput.COMPONENT_TYPE);
            filterValueInput.setId(column.getId() + SORT_FILTER_PARAMETER);
            filterValueInput.setImmediate(true);
            filterValueInput.getAttributes().put(HTML.STYLE_CLASS_ATTR, "rich-filter-input");
            column.getFacets().put(FILTER_INPUT_FACET_NAME, filterValueInput);
            filterValueInput.getAttributes().put(HTML.onclick_ATTRIBUTE,
                    "Event.stop(event);");
        }
        String filterEvent = (String) column.getAttributes().get("filterEvent");
        if (null == filterEvent || "".equals(filterEvent)) {
            filterEvent = "onchange";
        }

        filterValueInput.getAttributes().put(filterEvent, buffer);
        filterValueInput.setValue(column.getAttributes().get("filterValue"));

        getUtils().encodeBeginFormIfNessesary(context, column);
        renderChild(context, filterValueInput);
        getUtils().encodeEndFormIfNessesary(context, column);
    }

    protected String buildAjaxFunction(FacesContext context,
            UIComponent column, boolean sortable,
            JSFunctionDefinition onAjaxCompleteFunction) {
        UIComponent table = column.getParent();
        String id = table.getClientId(context);
        final String ignoreDupResponses = (String) column.getAttributes().get("_ignoreDupResponses");
        final String requestDelay = (String) column.getAttributes().get("_requestDelay");
        final String onCompleteFunction = (String) column.getAttributes().get("_oncomplete");
        
 
        JSFunction ajaxFunction = AjaxRendererUtils.buildAjaxFunction(table,
                context);
        Map<String, Object> eventOptions = AjaxRendererUtils.buildEventOptions(
                context, table);
        @SuppressWarnings("unchecked")
        Map<String, Object> parameters = (Map<String, Object>) eventOptions
                .get("parameters");

        parameters.put(id, SORT_FILTER_PARAMETER);
        
        if (sortable) {
            parameters.put(SORT_FILTER_PARAMETER, column.getClientId(context));
        }
  
        
        if(ignoreDupResponses!=null){
            eventOptions.put("ignoreDupResponses", Boolean.TRUE);
        }
        if(requestDelay!=null){
            eventOptions.put("requestDelay", Integer.valueOf(500));
            String clientId = column.getClientId(context);
            eventOptions.put(AjaxRendererUtils.AJAX_QUEUE_ATTR, clientId);
        }
       
        if(onCompleteFunction!=null){
            JSFunctionDefinition jsF = new JSFunctionDefinition("request","event","data");
            
            jsF.addToBody(onCompleteFunction);
            eventOptions.put(AjaxRendererUtils.ONCOMPLETE_ATTR_NAME, jsF);
        }
       
        if (onAjaxCompleteFunction != null) {
            eventOptions.put(AjaxRendererUtils.ONCOMPLETE_ATTR_NAME,
                    onAjaxCompleteFunction);
        }    
        
        ajaxFunction.addParameter(eventOptions);
        StringBuffer buffer = new StringBuffer();
        ajaxFunction.appendScript(buffer);

        return buffer.toString();
    }

    protected class SimpleHeaderEncodeStrategy implements HeaderEncodeStrategy {

        public void encodeBegin(FacesContext context, ResponseWriter writer,
                UIComponent column, String facetName, boolean sortableColumn)
                throws IOException {

        }

        public void encodeEnd(FacesContext context, ResponseWriter writer,
                UIComponent column, String facetName, boolean sortableColumn)
                throws IOException {

        }

    }

    protected class RichHeaderEncodeStrategy implements HeaderEncodeStrategy {

        public void encodeBegin(FacesContext context, ResponseWriter writer,
                UIComponent column, String facetName, boolean sortableColumn)
                throws IOException {
            UIColumn col = (UIColumn) column;
            String clientId = col.getClientId(context) + facetName;
            writer.writeAttribute("id", clientId, null);

            if (sortableColumn && col.isSelfSorted()) {
                writer.writeAttribute(HTML.onclick_ATTRIBUTE,
                        buildAjaxFunction(context, column, true, null)
                                .toString(), null);
                writer.writeAttribute(HTML.style_ATTRIBUTE, "cursor: pointer;",
                        null);
            }

            if (sortableColumn) {
                writer.startElement(HTML.SPAN_ELEM, column);
                writer.writeAttribute(HTML.class_ATTRIBUTE,
                        "extdt-sortable-header", null);
            }
        }

        public void encodeEnd(FacesContext context, ResponseWriter writer,
                UIComponent column, String facetName, boolean sortableColumn)
                throws IOException {
            UIColumn col = (UIColumn) column;
            if (sortableColumn) {
                String imageUrl = null;
                if (Ordering.ASCENDING.equals(col.getSortOrder())) {
                    if (null != col.getSortIconAscending()) {
                        imageUrl = col.getSortIconAscending();
                    } else {
                        imageUrl = getResource(TriangleIconUp.class.getName())
                                .getUri(context, null);
                    }
                } else if (Ordering.DESCENDING.equals(col.getSortOrder())) {
                    if (null != col.getSortIconDescending()) {
                        imageUrl = col.getSortIconDescending();
                    } else {
                        imageUrl = getResource(TriangleIconDown.class.getName())
                                .getUri(context, null);
                    }
                } else if (col.isSelfSorted()) {
                    if (null != col.getSortIcon()) {
                        imageUrl = col.getSortIcon();
                    } else {
                        imageUrl = getResource(
                                DataTableIconSortNone.class.getName()).getUri(
                                context, null);
                    }
                }

                if (imageUrl != null) {
                    writer.startElement(HTML.IMG_ELEMENT, column);
                    writer.writeAttribute(HTML.src_ATTRIBUTE, imageUrl, null);
                    writer.writeAttribute(HTML.width_ATTRIBUTE, "15", null);
                    writer.writeAttribute(HTML.height_ATTRIBUTE, "15", null);
                    writer.writeAttribute(HTML.class_ATTRIBUTE,
                            "extdt-header-sort-img", null);
                    writer.endElement(HTML.IMG_ELEMENT);
                }
                writer.endElement(HTML.SPAN_ELEM);
            }

            if (col.getFilterMethod() == null
                    && col.getValueExpression("filterExpression") == null
                    && col.getValueExpression("filterBy") != null) {

                writer.startElement(HTML.DIV_ELEM, column);
                addInplaceInput(context, column, buildAjaxFunction(context,
                        column, false, null));
                writer.endElement(HTML.DIV_ELEM);
            }
        }
    }

    protected class G3HeaderEncodeStrategy implements HeaderEncodeStrategy {

        public void encodeBegin(FacesContext context, ResponseWriter writer,
                UIComponent column, String facetName, boolean sortableColumn) throws IOException {
            if (column instanceof UIColumn) {
                UIColumn dataColumn = (UIColumn) column;
                String clientId = dataColumn.getClientId(context);// +
                // facetName;
                writer.writeAttribute("id", clientId, null);
                column.getAttributes().put("columnClientId", clientId);
                boolean sortable = sortableColumn && dataColumn.isSelfSorted();
                
                if (sortable) {
                    writer.writeAttribute(HTML.style_ATTRIBUTE, "cursor: pointer;", null);
                }
                
                writer.writeAttribute("sortable", String.valueOf(sortable), null);

                // drag source area
                writer.startElement(HTML.DIV_ELEM, dataColumn);
                writer.writeAttribute(HTML.id_ATTRIBUTE, dataColumn.getParent().getClientId(context)
                		+ "_hdrag_" + dataColumn.getId(), null);
                writer.writeAttribute("label", (String)dataColumn.getAttributes().get("label"), null);
                if (sortableColumn) {
                    writer.writeAttribute(HTML.onclick_ATTRIBUTE, "$('" + dataColumn.getParent().getClientId(context) + "').component.OnCellMouseClicked(event)", null);
                    writer.startElement(HTML.SPAN_ELEM, column);
                    writer.writeAttribute(HTML.class_ATTRIBUTE, "extdt-sortable-header", null);
                }
            }
        }

        public void encodeEnd(FacesContext context, ResponseWriter writer, UIComponent column, 
        		String facetName, boolean sortableColumn) throws IOException {
            
        	if (column instanceof UIColumn) {
                UIColumn dataColumn = (UIColumn) column;
                String clientId = dataColumn.getClientId(context) + facetName;
                String tableId = dataColumn.getParent().getClientId(context);

                if (sortableColumn) {
                    String imageUrl = null;
                    if (Ordering.ASCENDING.equals(dataColumn.getSortOrder())) {
                        if (null != dataColumn.getSortIconAscending()) {
                            imageUrl = dataColumn.getSortIconAscending();
                        } else {
                            imageUrl = getResource(
                                    DataTableIconSortAsc.class.getName()).getUri(
                                    context, null);
                        }
                    } else if (Ordering.DESCENDING.equals(dataColumn
                            .getSortOrder())) {
                        if (null != dataColumn.getSortIconDescending()) {
                            imageUrl = dataColumn.getSortIconDescending();
                        } else {
                            imageUrl = getResource(
                            		DataTableIconSortDesc.class.getName()).getUri(
                                    context, null);
                        }
                    } else if (dataColumn.isSelfSorted()) {
                        if (null != dataColumn.getSortIcon()) {
                            imageUrl = dataColumn.getSortIcon();
                        } else {
                            imageUrl = getResource(
                                    DataTableIconSortNone.class.getName())
                                    .getUri(context, null);
                        }
                    }

                    if (imageUrl != null) {
                        writer.startElement(HTML.IMG_ELEMENT, column);
                        writer.writeAttribute(HTML.src_ATTRIBUTE, imageUrl,
                                null);
                        writer.writeAttribute(HTML.width_ATTRIBUTE, "15", null);
                        writer
                                .writeAttribute(HTML.height_ATTRIBUTE, "15",
                                        null);
                        writer.writeAttribute(HTML.class_ATTRIBUTE,
                                "extdt-header-sort-img", null);
                        writer.endElement(HTML.IMG_ELEMENT);
                    }
                    writer.endElement(HTML.SPAN_ELEM);
                }

                // drag source area
                writer.endElement(HTML.DIV_ELEM);

                // separator area
                writer.startElement(HTML.SPAN_ELEM, column);
                writer.writeAttribute(HTML.id_ATTRIBUTE, clientId + ":sepSpan", null);

                writer.writeAttribute(HTML.onclick_ATTRIBUTE, "$('" + tableId + "').component.header.OnSepClick(event)", null);
                writer.writeAttribute(HTML.onmousedown_ATTRIBUTE, "$('" + tableId + "').component.header.OnSepMouseDown(event)", null);
                writer.writeAttribute(HTML.onmousemove_ATTRIBUTE, "$('" + tableId + "').component.header.OnSepMouseMove(event)", null);
                writer.writeAttribute(HTML.onmouseup_ATTRIBUTE, "$('" + tableId + "').component.header.OnSepMouseUp(event)", null);

                if(((Boolean)dataColumn.getParent().getAttributes().get(RESIZIABLE)).booleanValue()) {
                    writer.writeAttribute(HTML.class_ATTRIBUTE, "extdt-hsep", null);
                }

                writer.endElement(HTML.SPAN_ELEM);
                // drop target area LEFT
                String spanId = tableId + "_hdrop_" + dataColumn.getId() + "left";
                writer.startElement(HTML.SPAN_ELEM, column);
                writer.writeAttribute(HTML.id_ATTRIBUTE, spanId, null);
                writer.writeAttribute(HTML.class_ATTRIBUTE, "extdt-hdrop", null);
                writer.writeAttribute(HTML.style_ATTRIBUTE, "visibility: hidden;", null);
                writer.startElement(HTML.SPAN_ELEM, column);
                writer.writeAttribute(HTML.class_ATTRIBUTE, "extdt-hdrop-top extdt-hdrop-top-left", null);
                writer.writeAttribute(HTML.style_ATTRIBUTE, "visibility: hidden;", null);
                writer.endElement(HTML.SPAN_ELEM);
                writer.startElement(HTML.SPAN_ELEM, column);
                writer.writeAttribute(HTML.class_ATTRIBUTE,
                        "extdt-hdrop-bottom extdt-hdrop-bottom-left", null);
                writer.writeAttribute(HTML.style_ATTRIBUTE,
                        "visibility: hidden;", null);
                writer.endElement(HTML.SPAN_ELEM);
                writer.endElement(HTML.SPAN_ELEM);

                // drop target area RIGHT
                spanId = tableId + "_hdrop_" + dataColumn.getId() + "right";
                writer.startElement(HTML.SPAN_ELEM, column);
                writer.writeAttribute(HTML.id_ATTRIBUTE, spanId, null);
                writer.writeAttribute(HTML.class_ATTRIBUTE, "extdt-hdrop",
                        null);
                writer.writeAttribute(HTML.style_ATTRIBUTE,
                        "visibility: hidden;", null);
                writer.startElement(HTML.SPAN_ELEM, column);
                writer.writeAttribute(HTML.class_ATTRIBUTE,
                        "extdt-hdrop-top extdt-hdrop-top-right", null);
                writer.writeAttribute(HTML.style_ATTRIBUTE,
                        "visibility: hidden;", null);
                writer.endElement(HTML.SPAN_ELEM);
                writer.startElement(HTML.SPAN_ELEM, column);
                writer.writeAttribute(HTML.class_ATTRIBUTE,
                        "extdt-hdrop-bottom extdt-hdrop-bottom-right", null);
                writer.writeAttribute(HTML.style_ATTRIBUTE,
                        "visibility: hidden;", null);
                writer.endElement(HTML.SPAN_ELEM);
                writer.endElement(HTML.SPAN_ELEM);

                // menu
                
                Object allowColmnsHide = column.getParent().getAttributes().get("allowColumnsHide"); 
                if ("header".equals(facetName) && (allowColmnsHide != null && (Boolean)allowColmnsHide)) {
                    writer.startElement(HTML.DIV_ELEM, column);
                    String menuDivId = clientId + ":menuDiv";
                    writer.writeAttribute(HTML.id_ATTRIBUTE, menuDivId, null);
                    writer.writeAttribute(HTML.class_ATTRIBUTE,
                            "extdt-menu-div-out", null);
                    writer.writeAttribute(HTML.onmousedown_ATTRIBUTE, "$('" + tableId + "').component.header.OnMenuImageMouseDown(event)", null);

                    writer.endElement(HTML.DIV_ELEM);
                }

            }
        }
    }

    public String encodeDragDropChildScripts(FacesContext context,
            UIDataTable component) throws IOException {
        com.exadel.siperian.renderkit.html.TableDragDropRenderer.getInstance(context).encodeChildScripts(context,
                component);
        return "";
    }

    public void encodeNamespace(FacesContext context, UIComponent component)
            throws IOException {
        NSUtils.writeNameSpace(context, component);
    }

    public void renderDragSupport(FacesContext context, UIColumn column,
            String dragSourceId, String indicatorId, String dragLabel)
            throws IOException {
        com.exadel.siperian.renderkit.html.TableDragDropRenderer.getInstance(context).renderDragSupport(column,
                dragSourceId, indicatorId, dragLabel);
    }// renderDragSupport

    public void renderDropSupport(FacesContext context, UIColumn column,
            String dropTargetId, boolean before) throws IOException {
        com.exadel.siperian.renderkit.html.TableDragDropRenderer renderer = com.exadel.siperian.renderkit.html.TableDragDropRenderer
                .getInstance(context);
        renderer.setOnAjaxCompleteFunctionDef(getOnAjaxCompleteFunction(
                context, (UIDataTable) column.getParent()));
        renderer.setPreSendAjaxRequestFunction(getPreSendAjaxRequestFunction(
                context, (UIDataTable) column.getParent()));
        renderer.renderDropSupport(column, dropTargetId, before);
    }// renderDropSupport

    public static String getJavaScriptVarName(FacesContext context,
            UIDataTable grid) {
        String id = grid.getBaseClientId(context);
        String name = "ExtendedDataTable.DataTable_"
                + id.replaceAll("[^A-Za-z0-9_]", "_");
        // String name = "Richfaces_ScrollableGrid";
        return "window." + name;
    }

    protected String getScriptContributions(FacesContext context,
            UIDataTable grid) {
        return composite.getScriptContributions(getJavaScriptVarName(context,
                grid), context, grid);
    }

    protected JSFunctionDefinition getOnAjaxCompleteFunction(
            FacesContext context, UIDataTable table) {
         JSFunctionDefinition function = new JSFunctionDefinition("request", "event", "data");
         String varName = getJavaScriptVarName(context, table);
         function.addToBody(varName + ".updateAfterSorting();");
         return function;
    }

    public void encodeTableMenu(FacesContext context, com.exadel.siperian.component.UIExtendedDataTable table)
            throws IOException {
        AjaxContext ajaxContext = AjaxContext.getCurrentInstance();
        Object key = table.getRowKey();
        table.setRowKey(null);
        com.exadel.siperian.renderkit.html.TableMenuRenderer menuRenderer = new com.exadel.siperian.renderkit.html.RichTableMenuRenderer();
        //comment sort group
        //menuRenderer.setSortFunction(getSortFunction(context, table));
        //menuRenderer.setGroupFunction(getGroupFunction(context, table));
        menuRenderer
                .setChangeColumnVisibilityFunction(getChangeColumnVisibilityFunction(
                        context, table));
        menuRenderer.setPrepareFunction(getPreSendAjaxRequestFunction(context,
                table));
        //for (Iterator<UIColumn> colums = table.getSortedColumns(); colums
          //      .hasNext();) {
            //UIColumn col = colums.next();
            //if (col instanceof UIColumn) {
                //UIColumn column = (UIColumn) col;
                // if (column.isRendered()){
                String menuId = menuRenderer.renderMenu(context, table);
                ajaxContext.addRenderedArea(menuId);
                // }
            //}
        //}// for
      
        //String menuId = menuRenderer.renderMenu(context, table);
        //ajaxContext.addRenderedArea(menuId);
        table.setRowKey(key);
    }

    public void contributorsEncodeHere(FacesContext context, UIDataTable table)
            throws IOException {
        RendererContributor[] contribs = composite.getContributors();

        if (contribs != null) {
            for (int i = 0; i < contribs.length; i++) {
                RendererContributor rendererContributor = contribs[i];

                if (rendererContributor instanceof HTMLEncodingContributor) {
                    ((HTMLEncodingContributor) rendererContributor).encode(
                            context, table);
                }
            }
        }
    }
    
	public boolean isSingleCanHideTrueAvailable(UIComponent component) {
	boolean isSingleCanHideTrueAvailable = Boolean.TRUE;
	if (component instanceof com.exadel.siperian.component.UIExtendedDataTable) {
	    UIExtendedDataTable table = (com.exadel.siperian.component.UIExtendedDataTable) component;

	    isSingleCanHideTrueAvailable = isAllColumnsCanHide(isSingleCanHideTrueAvailable, table);
	    if(isSingleCanHideTrueAvailable){
		return checkIsAllowColumnsHideAttribute(table);
	    }else{
		return false;
	    }
	}

	return isSingleCanHideTrueAvailable;
    }

	/**
	 * @param isSingleCanHideTrueAvailable
	 * @param table
	 * @return
	 */
	private boolean isAllColumnsCanHide(boolean isSingleCanHideTrueAvailable, UIExtendedDataTable table) {
	    for (Iterator<UIColumn> iter = table.getChildColumns(); iter.hasNext();) {
		UIColumn column = iter.next();
		Object onhide = column.getAttributes().get(ATTRIBUTE_CAN_HIDE);
		if (onhide == null) {
		    return Boolean.TRUE;
		}
		if (onhide instanceof Boolean) {
		    isSingleCanHideTrueAvailable = (Boolean) onhide;
		} else if (onhide instanceof String) {

		    isSingleCanHideTrueAvailable = Boolean.valueOf(((String) onhide).toLowerCase());
		}
		if(isSingleCanHideTrueAvailable){
		    return true;
		}
		
	    }
	    return isSingleCanHideTrueAvailable;
	}

	/**
	 * @param table
	 */
	private Boolean checkIsAllowColumnsHideAttribute(UIExtendedDataTable table) {
	    final Object allowColumnsHide = table.getAttributes().get(ALLOW_COLUMNS_HIDE);
	    if (allowColumnsHide != null) {
		if (allowColumnsHide instanceof Boolean) {
		    return (Boolean) allowColumnsHide;
		} else if (allowColumnsHide instanceof String) {
		    return Boolean.valueOf(((String) allowColumnsHide).toLowerCase());
		}
	    }
	    return true;
	}	

	protected String getCurrentInputValue(FacesContext context, UIInput input) {
		String submittedValue = (String) input.getSubmittedValue();
		if (submittedValue == null) {
			Object value = input.getValue();
			Converter converter = input.getConverter();
			if (converter == null) {
				if (value == null) {
					return "";
				}
				if (value instanceof String) {
					return (String) value;
				}
		        try {            
		            converter = context.getApplication().createConverter(value.getClass());
		        } catch (Exception e) {}
				if (converter == null) {
					return value.toString();
				}
			}
			return converter.getAsString(context, input, value);
		}
		return submittedValue;
	}
}