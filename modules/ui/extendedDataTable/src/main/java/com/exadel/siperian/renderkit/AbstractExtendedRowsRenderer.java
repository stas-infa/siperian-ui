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
import java.util.Iterator;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.component.SequenceDataAdaptor;
import org.ajax4jsf.component.UIDataAdaptor;
import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.renderkit.ComponentVariables;
import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;
import org.ajax4jsf.renderkit.RendererUtils;
import org.ajax4jsf.renderkit.RendererUtils.HTML;
import org.ajax4jsf.resource.InternetResource;
import org.richfaces.component.Column;
import org.richfaces.component.Row;
import org.richfaces.component.UIDataTable;
import org.richfaces.renderkit.AbstractRowsRenderer;
import org.richfaces.renderkit.CompositeRenderer;
import org.richfaces.renderkit.RendererContributor;
import org.richfaces.renderkit.ScriptOptions;
import org.richfaces.renderkit.TableHolder;

import com.exadel.siperian.component.UIExtendedDataTable;
import com.exadel.siperian.renderkit.html.TableSelectionRendererContributor;

/** @author shura */
public abstract class AbstractExtendedRowsRenderer extends
        HeaderResourcesRendererBase implements DataVisitor {

    protected static final String MENU_ID = "_TABLE_MENU_ID_";

    protected static final String ENCODED_IN_TABLE_KEY = "encodedInTable";

    protected static final String GROUPING_ROWS_MAP_KEY = "groupingMapKey";


    protected class CompositeRendererEnabler extends CompositeRenderer {
        public CompositeRendererEnabler() {
            addContributor(new TableSelectionRendererContributor());
        }

        protected Class<? extends UIComponent> getComponentClass() {
            return AbstractExtendedRowsRenderer.this.getComponentClass();
        }

        public void mergeScriptOptions(ScriptOptions scriptOptions,
                FacesContext context, UIComponent component) {
            super.mergeScriptOptions(scriptOptions, context, component);

        }

        public String getScriptContributions(String varString,
                FacesContext context, UIComponent component) {
            return super.getScriptContributions(varString, context, component);
        }

        public RendererContributor[] getContributors() {
            return super.getContributors();
        }

        public InternetResource[] getScripts() {
            return super.getScripts();
        }

        public InternetResource[] getStyles() {
            return super.getStyles();
        }
    }

    public static final String[][] TABLE_EVENT_ATTRS = {
            {"onclick", "onRowClick"}, {"ondblclick", "onRowDblClick"},
            {"onmousemove", "onRowMouseMove"},
            {"onmouseup", "onRowMouseUp"},
            {"onmousedown", "onRowMouseDown"},
            {"onmouseover", "onRowMouseOver"},
            {"onmouseout", "onRowMouseOut"},
            /* { "oncontextmenu", "onRowContextMenu" } */};

    public static final String ROW_CLASS_KEY = AbstractRowsRenderer.class
            .getName()
            + ".rowClass";

    public static final String SKIN_ROW_CLASS_KEY = AbstractRowsRenderer.class
            .getName()
            + ".skinRowClass";

    public static final String CELL_CLASS_KEY = AbstractRowsRenderer.class
            .getName()
            + ".cellClass";

    public static final String SKIN_CELL_CLASS_KEY = AbstractRowsRenderer.class
            .getName()
            + ".skinCellClass";

    public static final String SKIN_FIRST_ROW_CLASS_KEY = AbstractRowsRenderer.class
            .getName()
            + ".firstRowSkinClass";

    // protected TableMenuRenderer menuRenderer = new RichTableMenuRenderer();

    protected CompositeRendererEnabler composite = new CompositeRendererEnabler();

    /*
      * (non-Javadoc)
      *
      * @see
      * org.ajax4jsf.ajax.repeat.DataVisitor#process(javax.faces.context.FacesContext
      * , java.lang.Object, java.lang.Object)
      */
    public void process(FacesContext context, Object rowKey, Object argument)
            throws IOException {
        ExtendedTableHolder holder = (ExtendedTableHolder) argument;
        UIExtendedDataTable table = holder.getTable();
        if (holder.getLastKey() != null) {
            table.setRowKey(context, holder.getLastKey());
            holder.setLastData(table.getRowData());
        }
        holder.setLastKey(rowKey);
        table.setRowKey(context, rowKey);
        boolean notRendered = (!table.isRootRow() && table.isRoowRootAjaxExpanded() && !holder.isRootRowExpanded());
        if (!notRendered) {
            encodeOneRow(context, holder);
        }
        holder.nextRow();
    }

    /**
     * Calculate total number of columns in table.
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

    protected Integer getFrozenColumnsCount(FacesContext context, UIDataTable table) {
        Integer frozenColumns = (Integer) table.getAttributes().get("frozenColumns");
        Integer columnsCount = getColumnsCount(table);

        if (frozenColumns != null && frozenColumns >= columnsCount) {
            frozenColumns = columnsCount - 1;
        }

        return frozenColumns;
    }


    /**
     * Calculate max number of columns per row. For rows, recursive calculate
     * max length.
     * @param col -
     * Iterator other all columns in table.
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


    public void encodeRows(FacesContext context, UIComponent component, ComponentVariables variables)
            throws IOException {
        ExtendedTableHolder holder =
                new ExtendedTableHolder((com.exadel.siperian.component.UIExtendedDataTable) component);
        encodeRows(context, component, holder);
    }

    public void encodeFrozenRows(FacesContext context, UIComponent component, ComponentVariables variables)
            throws IOException {
        UIExtendedDataTable dataTable = (com.exadel.siperian.component.UIExtendedDataTable) component;
        ExtendedTableHolder holder = new ExtendedTableHolder(dataTable, getFrozenColumnsCount(context, dataTable));
        encodeRows(context, component, holder);
    }

    /**
     * Iterate over all rows for this table.
     * @param context
     * @param component
     * @throws IOException
     */
    protected void encodeRows(FacesContext context, UIComponent component,
            ExtendedTableHolder tableHolder) throws IOException {
        tableHolder.setFirstRow(true);
        UIDataAdaptor table = (UIDataAdaptor) component;
        Object key = table.getRowKey();
        table.captureOrigValue(context);
        table.walk(context, this, tableHolder);
        doCleanup(context, tableHolder);
        table.setRowKey(key);
        table.restoreOrigValue(context);
    }

    /**
     * @param context
     * @param tableHolder
     * @throws IOException
     */
    protected void doCleanup(FacesContext context, TableHolder tableHolder)
            throws IOException {
    }

    public abstract void encodeOneRow(FacesContext context,
            ExtendedTableHolder holder) throws IOException;

    /*
      * (non-Javadoc)
      *
      * @see javax.faces.render.Renderer#getRendersChildren()
      */
    public boolean getRendersChildren() {
        return true;
    }

    //	public void encodeChildren(FacesContext context, UIComponent component)
    //			throws IOException {
    //		encodeRows(context, component);
    //	}

    public void encodeCaption(FacesContext context, SequenceDataAdaptor table)
            throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String clientId = table.getClientId(context);
        UIComponent caption = table.getFacet("caption");
        if (caption != null) {
            String captionClass = (String) table.getAttributes().get(
                    "captionClass");
            String captionStyle = (String) table.getAttributes().get(
                    "captionStyle");
            writer.startElement("caption", table);
            if (captionClass != null) {
                captionClass = "extdt-caption rich-extdt-caption "
                        + captionClass;
            } else {
                captionClass = "extdt-caption rich-extdt-caption";
            }
            writer.writeAttribute("class", captionClass, "captionClass");
            if (captionStyle != null) {
                writer.writeAttribute("style", captionStyle, "captionStyle");
            }
            writer.writeAttribute(HTML.id_ATTRIBUTE, clientId + ":caption",
                    null);
            renderChild(context, caption);
            writer.endElement("caption");
        }

    }

    /**
     * @param context
     * @param table
     * @throws IOException
     */
    protected void encodeRowEvents(FacesContext context, UIDataAdaptor table)
            throws IOException {
        RendererUtils utils2 = getUtils();
        for (int i = 0; i < TABLE_EVENT_ATTRS.length; i++) {
            String[] attrs = TABLE_EVENT_ATTRS[i];
            utils2.encodeAttribute(context, table, attrs[1], attrs[0]);
        }
    }

    /**
     * Encode HTML "class" attribute, if is not empty. Classes combined from
     * pre-defined skin classes, class from parent component, and custom
     * attribute.
     * @param writer
     * @param parentPredefined
     * @param predefined predefined skin classes
     * @param parent class from parent component
     * @param custom custom classes.
     * @throws IOException
     */
    protected void encodeStyleClass(ResponseWriter writer,
            Object parentPredefined, Object predefined, Object parent,
            Object custom) throws IOException {
        StringBuilder styleClass = new StringBuilder();
        // Construct predefined classes
        if (null != parentPredefined) {
            styleClass.append(parentPredefined).append(" ");
        } else if (null != predefined) {
            styleClass.append(predefined).append(" ");
        }
        // Append class from parent component.
        if (null != parent) {
            styleClass.append(parent).append(" ");
        }
        if (null != custom) {
            styleClass.append(custom);
        }
        if (styleClass.length() > 0) {
            writer.writeAttribute(HTML.class_ATTRIBUTE, styleClass,
                    "styleClass");
        }
    }

    protected void encodeStyle(ResponseWriter writer, Object parentPredefined,
            Object predefined, Object parent, Object custom) throws IOException {
        StringBuffer style = new StringBuffer();
        // Construct predefined styles
        if (null != parentPredefined) {
            style.append(parentPredefined).append(" ");
        } else if (null != predefined) {
            style.append(predefined).append(" ");
        }
        // Append style from parent component.
        if (null != parent) {
            style.append(parent).append(" ");
        }
        if (null != custom) {
            style.append(custom);
        }
        if (style.length() > 0) {
            writer.writeAttribute("style", style, "style");
        }
    }

    /**
     * Render component and all its children with current row/cell style
     * classes.
     * @param context
     * @param cell
     * @param skinFirstRowClass
     * @param skinRowClass
     * @param rowClass
     * @param skinCellClass
     * @param cellClass
     * @throws IOException
     */
    protected void encodeCellChildren(FacesContext context, UIComponent cell,
            String skinFirstRowClass, String skinRowClass, String rowClass,
            String skinCellClass, String cellClass) throws IOException {
        Map<String, Object> requestMap = context.getExternalContext()
                .getRequestMap();
        // Save top level class parameters ( if any ), and put new for this
        // component
        Object savedRowClass = requestMap.get(ROW_CLASS_KEY);
        if (null != rowClass) {
            requestMap.put(ROW_CLASS_KEY, rowClass);

        }
        Object savedSkinFirstRowClass = requestMap
                .get(SKIN_FIRST_ROW_CLASS_KEY);
        if (null != skinRowClass) {
            requestMap.put(SKIN_FIRST_ROW_CLASS_KEY, skinFirstRowClass);

        }
        Object savedSkinRowClass = requestMap.get(SKIN_ROW_CLASS_KEY);
        if (null != skinRowClass) {
            requestMap.put(SKIN_ROW_CLASS_KEY, skinRowClass);

        }
        Object savedCellClass = requestMap.get(CELL_CLASS_KEY);
        if (null != cellClass) {
            requestMap.put(CELL_CLASS_KEY, cellClass);
        }
        Object savedSkinCellClass = requestMap.get(SKIN_CELL_CLASS_KEY);
        if (null != skinCellClass) {
            requestMap.put(SKIN_CELL_CLASS_KEY, skinCellClass);

        }
        renderChild(context, cell);
        // Restore original values.
        requestMap.put(ROW_CLASS_KEY, savedRowClass);
        requestMap.put(CELL_CLASS_KEY, savedCellClass);
        requestMap.put(SKIN_FIRST_ROW_CLASS_KEY, savedSkinFirstRowClass);
        requestMap.put(SKIN_ROW_CLASS_KEY, savedSkinRowClass);
        requestMap.put(SKIN_CELL_CLASS_KEY, savedSkinCellClass);

    }

    protected void encodeTableHeaderFacet(FacesContext context, int columns,
            ResponseWriter writer, UIComponent footer,
            String skinFirstRowClass, String skinRowClass,
            String skinCellClass, String footerClass, String element,
            String facetName) throws IOException {
        boolean isColgroup = footer instanceof Row;
        if (!isColgroup) {
            writer.startElement("tr", footer);
            encodeStyleClass(writer, null, skinFirstRowClass, footerClass, null);
            writer.startElement(element, footer);
            encodeStyleClass(writer, null, skinCellClass, footerClass, null);
            if (columns > 0) {
                writer.writeAttribute("colspan", String.valueOf(columns), null);
            }
            writer.writeAttribute("scope", "colgroup", null);
        }
        encodeCellChildren(context, footer, skinFirstRowClass, skinRowClass,
                footerClass, skinCellClass, null);
        if (!isColgroup) {
            writer.endElement(element);
            writer.endElement("tr");
        }

    }

    protected InternetResource[] getScripts() {
        return composite.getScripts();
    }

    protected InternetResource[] getStyles() {
        return composite.getStyles();
    }

}
