/**
 * 
 */
package com.exadel.siperian.renderkit.html;

import java.io.IOException;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;

import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.renderkit.RendererUtils.HTML;
import org.richfaces.model.selection.ClientSelection;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;
import org.richfaces.renderkit.CompositeRenderer;
import org.richfaces.renderkit.RendererContributor;
import org.richfaces.renderkit.ScriptOptions;
import org.richfaces.renderkit.TableHolder;
import org.richfaces.renderkit.html.HTMLEncodingContributor;

import com.exadel.siperian.component.UIExtendedDataTable;

/**
 * Renderer contributor based on
 * {@link org.richfaces.renderkit.html.SelectionRendererContributor}} adapted
 * for {@link com.exadel.siperian.component.UIExtendedDataTable} component.
 * 
 * @author pawelgo
 * 
 */
public class TableSelectionRendererContributor implements RendererContributor,
        HTMLEncodingContributor {

    public static final String CLIENT_SELECTION = "clientSelection";

    public static final String getSelectionInputName(FacesContext context,
            com.exadel.siperian.component.UIExtendedDataTable table) {
        String id = table.getBaseClientId(context) + ":s";
        return id;
    }

    public static final String getGridId(FacesContext context,
            com.exadel.siperian.component.UIExtendedDataTable table) {
        return table.getBaseClientId(context);
    }

    public void decode(FacesContext context, UIComponent component,
            CompositeRenderer compositeRenderer) {

        final com.exadel.siperian.component.UIExtendedDataTable table = (com.exadel.siperian.component.UIExtendedDataTable) component;

        ExternalContext externalContext = context.getExternalContext();
        Map<String, String> requestParamMap = externalContext
                .getRequestParameterMap();
        Application application = context.getApplication();

        String id = getSelectionInputName(context, table);

        String value = (String) requestParamMap.get(id);
        if (value != null) {

            Converter converter = application
                    .createConverter(ClientSelection.class);

            ClientSelection _oldClientSelection = (ClientSelection) table
                    .getAttributes().get(CLIENT_SELECTION);

            final ClientSelection oldClientSelection = _oldClientSelection == null ? new ClientSelection()
                    : _oldClientSelection;

            final ClientSelection clientSelection = (ClientSelection) converter
                    .getAsObject(context, table, value);

            // final ScrollableDataTableRendererState state =
            // ScrollableDataTableRendererState.createState(context, grid);
            // state.setRowIndex(ScrollableDataTableUtils.getClientRowIndex(grid));
            Object savedKey = table.getRowKey();

            final TableHolder holder = new TableHolder(table);

            final SimpleSelection simpleSelection = table.getSelection() == null ? new SimpleSelection()
                    : (SimpleSelection) table.getSelection();

            if (clientSelection.isReset() || clientSelection.isSelectAll()) {
                simpleSelection.clear();
            }

            try {
                table.walk(context, new DataVisitor() {
                    public void process(FacesContext context, Object rowKey,
                            Object argument) throws IOException {

                        // TableHolder holder = (TableHolder) argument;
                        // int i = state.getRowIndex();
                        int i = holder.getRowCounter();

                        if (shouldAddToSelection(i, oldClientSelection,
                                clientSelection)) {

                            simpleSelection.addKey(rowKey);

                        } else if (shouldRemoveFromSelection(i,
                                oldClientSelection, clientSelection)) {

                            simpleSelection.removeKey(rowKey);

                        }

                        if (i == clientSelection.getActiveRowIndex()) {
                            table.setActiveRowKey(rowKey);
                        }
                        holder.nextRow();

                    }
                }, holder);
            } catch (IOException e) {
                throw new FacesException(e);
            }


            ValueExpression selectionBinding = table
                    .getValueExpression("selection");
            if (selectionBinding != null) {
                selectionBinding.setValue(context.getELContext(),
                        simpleSelection);
            }else{
                table.setSelection(simpleSelection);
            }
            table.setRowKey(savedKey);
            // ScrollableDataTableRendererState.restoreState(context);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.richfaces.renderkit.RendererContributor#getAcceptableClass()
     */
    public Class<?> getAcceptableClass() {
        return com.exadel.siperian.component.UIExtendedDataTable.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.richfaces.renderkit.RendererContributor#getScriptContribution(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent)
     */
    public String getScriptContribution(FacesContext context,
            UIComponent component) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.richfaces.renderkit.RendererContributor#getScriptDependencies()
     */
    public String[] getScriptDependencies() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.richfaces.renderkit.RendererContributor#getStyleDependencies()
     */
    public String[] getStyleDependencies() {
        return null;
    }

    public ScriptOptions buildOptions(FacesContext context,
            UIComponent component) {
        ScriptOptions scriptOptions = new ScriptOptions(component);
        scriptOptions.addOption("selectionInput", getSelectionInputName(
                context, (com.exadel.siperian.component.UIExtendedDataTable) component));
        scriptOptions.addOption("gridId", getGridId(context,
                (UIExtendedDataTable) component));
        Map<String, Object> attributes = component.getAttributes();
        Object attribut = attributes.get("selectedClass");
        if (attribut == null) {
            attribut = "";
        }
        scriptOptions.addOption("selectedClass", attribut);
        attribut = attributes.get("activeClass");
        if (attribut == null) {
            attribut = "";
        }
        scriptOptions.addOption("activeClass", attribut);

        attribut = attributes.get("selectionMode");
        if (attribut == null) {
            attribut = "";
        }
        scriptOptions.addOption("selectionMode", attribut);
        
        attribut = attributes.get("hoverRowClass");
        if (attribut == null) {
            attribut = "";
        }
        scriptOptions.addOption("hoverRowClass", attribut);
        scriptOptions.addEventHandler("onselectionchange");
        return scriptOptions;
    }

    public void encode(FacesContext context, UIComponent component)
            throws IOException {
        com.exadel.siperian.component.UIExtendedDataTable grid = (com.exadel.siperian.component.UIExtendedDataTable) component;
        encodeSelection(context, grid);
        writeSelection(context, grid);
    }

    // Decide whether to add new row to selection based on comparison with old
    // one
    public boolean shouldAddToSelection(int i, ClientSelection oldSelection,
            ClientSelection newSelection) {

        return newSelection.isSelectAll()
                || (newSelection.isSelected(i) && (!oldSelection.isSelected(i) || newSelection
                        .isReset()));
    }

    // Decide whether to remove new row to selection based on comparison with
    // old one
    public boolean shouldRemoveFromSelection(int i,
            ClientSelection oldSelection, ClientSelection newSelection) {
        return !newSelection.isReset()
                && (!newSelection.isSelectAll() && (!newSelection.isSelected(i) && oldSelection
                        .isSelected(i)));
    }

    private void encodeSelection(FacesContext context,
            final UIExtendedDataTable table) throws IOException {

        // final ScrollableDataTableRendererState state =
        // ScrollableDataTableRendererState.createState(context, grid);
        // state.setRowIndex(ScrollableDataTableUtils.getClientRowIndex(grid));
    	Object savedKey = table.getRowKey();

        final TableHolder holder = new TableHolder(table);

        final Selection gridSelection = table.getSelection() == null ? new SimpleSelection()
                : table.getSelection();
        final ClientSelection clientSelection = new ClientSelection();

        table.walk(context, new DataVisitor() {
            public void process(FacesContext context, Object rowKey,
                    Object argument) throws IOException {

                // TableHolder holder = (TableHolder) argument;

                if (gridSelection.isSelected(rowKey)) {

                    int i = holder.getRowCounter();

                    clientSelection.addIndex(i);
                }

                if (rowKey.equals(table.getActiveRowKey())) {
                    clientSelection.setActiveRowIndex(holder.getRowCounter());
                }

                holder.nextRow();

            }
        }, holder);

        // ScrollableDataTableRendererState.restoreState(context);
        table.setRowKey(savedKey);
        table.getAttributes().put(CLIENT_SELECTION, clientSelection);
    }

    /**
     * Get client selection from the component, transform it into string form,
     * and write it as hidden input
     * 
     * @param context
     *            faces context
     * @param table
     *            table component
     * @throws IOException
     */
    public void writeSelection(FacesContext context, com.exadel.siperian.component.UIExtendedDataTable table)
            throws IOException {

        Application application = context.getApplication();

        Converter converter = application
                .createConverter(ClientSelection.class);

        ClientSelection selection = (ClientSelection) table.getAttributes()
                .get(CLIENT_SELECTION);
        String string = converter.getAsString(context, table, selection);

        if (string == null) {
            string = "";
        }

        string += selection.getActiveRowIndex();

        String id = getSelectionInputName(context, table);

        ResponseWriter writer = context.getResponseWriter();
        writer.startElement(HTML.INPUT_ELEM, table);
        writer.writeAttribute(HTML.TYPE_ATTR, "hidden", null);
        writer.writeAttribute(HTML.id_ATTRIBUTE, id, null);
        writer.writeAttribute(HTML.NAME_ATTRIBUTE, id, null);
        writer.writeAttribute(HTML.value_ATTRIBUTE, string, null);
        writer.endElement(HTML.INPUT_ELEM);

        AjaxContext ajaxContext = AjaxContext.getCurrentInstance(context);

        if (ajaxContext.isAjaxRequest()) {
            ajaxContext.addRenderedArea(id);
        }

    }
}
