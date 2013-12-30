/**
 * 
 */
package com.exadel.siperian.renderkit.html;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.javascript.JSFunctionDefinition;
import org.ajax4jsf.javascript.JSReference;
import org.ajax4jsf.javascript.ScriptUtils;
import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.richfaces.renderkit.CompositeRenderer;
import org.richfaces.renderkit.DraggableRendererContributor;
import org.richfaces.renderkit.DropzoneRendererContributor;
import org.richfaces.renderkit.RendererContributor;
import org.richfaces.renderkit.ScriptOptions;

/**
 * Renderer contributor for drop support based on {@link org.richfaces.renderkit.DropzoneRendererContributor}.
 * Used only with {@link com.exadel.siperian.component.UIExtendedDataTable} component.
 * All functionality from wrapped {@link org.richfaces.renderkit.DropzoneRendererContributor} are available.
 * Also special methods {@link ExtDropzoneRendererContributor#buildOptions(FacesContext, UIComponent)}
 * and {@link ExtDropzoneRendererContributor#getScriptContribution(FacesContext, UIComponent, String, JSFunctionDefinition)}
 * are added.
 * 
 * @author pawelgo
 *
 */
public class ExtDropzoneRendererContributor implements RendererContributor {
	
	private ExtDropzoneRendererContributor() {
		super();
	}

	private static ExtDropzoneRendererContributor instance;
	
	private static RendererContributor wrappedContributor;

	public static synchronized ExtDropzoneRendererContributor getInstance() {
		if (instance == null) {
			instance = new ExtDropzoneRendererContributor();
			wrappedContributor = DropzoneRendererContributor.getInstance();
		}

		return instance;
	}

	public void decode(FacesContext context, UIComponent component, CompositeRenderer compositeRenderer) {
		wrappedContributor.decode(context, component, compositeRenderer);
	}

	public Class<?> getAcceptableClass() {
		return wrappedContributor.getAcceptableClass();
	}

	public String getScriptContribution(FacesContext context, UIComponent component) {
		return wrappedContributor.getScriptContribution(context, component);
	}
	
	/**
	 * Build JavaScript code for drag'n drop support specialized for change table column order event.
	 * @param context faces context
	 * @param column table column
	 * @param dropTargetScriptId id of HTML script element that contains defined DnD.ExtSimpleDropZone object
	 * @param onAjaxCompleteFunction JavaScript function to be called on AJAX request complete 
	 * @return JavaScript code
	 */
	public String getScriptContribution(FacesContext context, UIComponent column, String dropTargetScriptId, JSFunction preSendAjaxRequestFunction, JSFunctionDefinition onAjaxCompleteFunction) {
		StringBuffer result = new StringBuffer();

		result.append(".drop = ");

		JSFunctionDefinition definition = new JSFunctionDefinition();
		definition.addParameter("event");
		definition.addParameter("drag");

		Map<String, Object> requestOpts = AjaxRendererUtils.buildEventOptions(context, column);
		//replace parameters
		String clientId = column.getClientId(context);
		@SuppressWarnings("unchecked")
		Map<String, Object> parameters = (Map<String, Object>) requestOpts.get("parameters");
		if (parameters != null){
			if (parameters.containsKey(clientId)){
				parameters.remove(clientId);
				parameters.put(dropTargetScriptId, dropTargetScriptId);
			}
		}
		
		definition.addToBody("var dragParams = drag.getParameters();");
		
		String dragSourceScriptId = column.getClientId(context) + ":"+ com.exadel.siperian.renderkit.html.TableDragDropRenderer.DRAG_SOURCE_SCRIPT_ID;
		definition.addToBody(
			"var source = dragParams['"+ DraggableRendererContributor.DRAG_SOURCE_ID +"'];" +
			"if (source != \"" + dragSourceScriptId + "\"){"//send request only if drag column in not equals to drop column
		);
		definition.addToBody("var options = ").addToBody(ScriptUtils.toScript(requestOpts)).addToBody(";");
		definition.addToBody("options.parameters['" + DropzoneRendererContributor.DROP_TARGET_ID + "'] = '" + dropTargetScriptId + "';");
		if (onAjaxCompleteFunction != null)		
			definition.addToBody("options['" + AjaxRendererUtils.ONCOMPLETE_ATTR_NAME + "'] = " + onAjaxCompleteFunction.toScript() + ";");
		//TODO remove as legacy
		definition.addToBody("Object.extend(options.parameters, dragParams);");
		if (preSendAjaxRequestFunction != null){
			definition.addToBody(preSendAjaxRequestFunction.toScript()).addToBody(";");
		}
		definition.addToBody("var dzOptions = this.getDropzoneOptions(); if (dzOptions.ondrop) { if (!dzOptions.ondrop.call(this, event)) return; };");
		
		JSFunction dropFunction = AjaxRendererUtils.buildAjaxFunction(column, context);
		dropFunction.addParameter(new JSReference("options"));
		
		definition.addToBody(dropFunction.toScript()).addToBody(";");
		
		definition.addToBody("};");
		
		definition.appendScript(result);
		result.append(";");

		return result.toString();
	}
	
	/**
	 * Build JavaScript code for drag'n drop support specialized for change table column order event.
	 * @param context faces context
	 * @param column table column
	 * @param dropTargetScriptId id of HTML script element that contains defined DnD.ExtSimpleDropZone object
	 * @return JavaScript code
	 */
	public String getScriptContribution(FacesContext context, UIComponent column, String dropTargetScriptId) {
		return getScriptContribution(context, column, dropTargetScriptId, null, null);
	}

	public String[] getScriptDependencies() {
		return wrappedContributor.getScriptDependencies();
	}

	public String[] getStyleDependencies() {
		return wrappedContributor.getStyleDependencies();
	}

	/**
	 * Builds options for DnD.ExtSimpleDropZone JavaScript object.
	 * These options are specialized for drop target used to end changing table columns order event.
	 * @param context faces context
	 * @param column table column
	 * @return all options needed for drop JavaScript object to work
	 */
	public ScriptOptions buildOptions(FacesContext context, UIComponent column) {
		ScriptOptions options = new ScriptOptions(column);
		options.addOption("acceptedTypes", "COLUMN_ORDER_"+column.getParent().getClientId(context));
		return options;
	}

}
