/**
 * 
 */
package com.exadel.siperian.renderkit.html;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.richfaces.renderkit.CompositeRenderer;
import org.richfaces.renderkit.DraggableRendererContributor;
import org.richfaces.renderkit.RendererContributor;
import org.richfaces.renderkit.ScriptOptions;

/**
 * Renderer contributor for drag support based on
 * {@link org.richfaces.renderkit.DraggableRendererContributor}. Used only with
 * {@link com.exadel.siperian.component.UIExtendedDataTable} component. All
 * functionality from wrapped
 * {@link org.richfaces.renderkit.DraggableRendererContributor} are available
 * and special method
 * {@link ExtDraggableRendererContributor#buildOptions(FacesContext, UIComponent, String, String)}
 * is added.
 * 
 * @author pawelgo
 * 
 */
public class ExtDraggableRendererContributor implements RendererContributor {

    private static ExtDraggableRendererContributor instance;

    private static RendererContributor wrappedContributor;

    private ExtDraggableRendererContributor() {
        super();
    }

    public static synchronized ExtDraggableRendererContributor getInstance() {
        if (instance == null) {
            instance = new ExtDraggableRendererContributor();
            wrappedContributor = DraggableRendererContributor.getInstance();
        }
        return instance;
    }

    public void decode(FacesContext context, UIComponent component,
            CompositeRenderer compositeRenderer) {
        wrappedContributor.decode(context, component, compositeRenderer);
    }

    public Class<?> getAcceptableClass() {
        return wrappedContributor.getAcceptableClass();
    }

    public String getScriptContribution(FacesContext context,
            UIComponent component) {
        return wrappedContributor.getScriptContribution(context, component);
    }

    public String[] getScriptDependencies() {
        return wrappedContributor.getScriptDependencies();
    }

    public String[] getStyleDependencies() {
        return wrappedContributor.getStyleDependencies();
    }

    public ScriptOptions buildOptions(FacesContext context,
            UIComponent component) {
        return wrappedContributor.buildOptions(context, component);
    }

    /**
     * Builds options for DnD.ExtSimpleDraggable JavaScript object. These options
     * are specialized for drag source used to start changing table columns
     * order event.
     * 
     * @param context
     *            faces context
     * @param column
     *            table column
     * @param dragSourceScriptId
     *            drag source HTML element id
     * @param indicatorId
     *            drag indicator id
     * @return all options needed for drag JavaScript object to work
     */
    public ScriptOptions buildOptions(FacesContext context, UIComponent column,
            String dragSourceScriptId, String indicatorId) {

        ScriptOptions options = new ScriptOptions(column);
        options.addOption("dragType", "COLUMN_ORDER_"
                + column.getParent().getClientId(context));

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters
                .put(
                        DraggableRendererContributor.DRAG_SOURCE_ID,
                        dragSourceScriptId);
        parameters.put(dragSourceScriptId, dragSourceScriptId);
        options.addOption("parameters", parameters);

        if (indicatorId != null) {
            options.addOption("dragIndicator", indicatorId);
        }

        return options;
    }

}
