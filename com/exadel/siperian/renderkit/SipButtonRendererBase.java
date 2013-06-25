package com.exadel.siperian.renderkit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.ajax4jsf.Messages;
import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;
import org.ajax4jsf.renderkit.html.AjaxFormRenderer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exadel.siperian.component.SipUIAjaxCommandButton;

//TODO nick - consider extending org.ajax4jsf.renderkit.html.HtmlCommandLinkRenderer instead of code copying
//TODO nick - why link code conatins "button" word instead of "link"
public class SipButtonRendererBase extends HeaderResourcesRendererBase {

    private static final Log _log = LogFactory.getLog(SipButtonRendererBase.class);

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
  
        super.encodeBegin(context, component);
    }

    @Override
    protected Class<? extends UIComponent> getComponentClass() {
        return SipUIAjaxCommandButton.class;
    }

    protected void doDecode(FacesContext context, UIComponent component) {
        UIForm form = getUtils().getNestingForm(context, component);
        if (null != form) {
            String hiddenFieldId = getHiddenFieldId(context, form, component);
            Object hiddenFieldValue;
            if (null != (hiddenFieldValue = context.getExternalContext().getRequestParameterMap().get(hiddenFieldId))) {
                if (component.getClientId(context).equals(hiddenFieldValue)) {
                    // Link submitted !
                    if (_log.isDebugEnabled()) {
                        _log.debug(Messages.getMessage(Messages.COMMAND_LINK_SUBMIT_INFO, component
                                .getClientId(context)));
                    }
                    ActionEvent actionEvent = new ActionEvent(component);
                    component.queueEvent(actionEvent);
                }
            }
        }
    }

    public String getOnClick(FacesContext context, UIComponent component) {
        String clientId = component.getClientId(context);
        UIForm form = getUtils().getNestingForm(context, component);
        Object click = component.getAttributes().get("onclick");
        StringBuffer onclick = new StringBuffer(256);
        if (null != click) {
            onclick.append(click).append(';');
        }
        if (null == form) {
            return onclick.toString();
        }
        JSFunction submit = new JSFunction(AjaxFormRenderer.FORM_SUBMIT_FUNCTION_NAME);
        submit.addParameter(clientId);
        submit.addParameter(form.getClientId(context));
        submit.addParameter(component.getAttributes().get("target"));
        Map<String, Object> parameters = new HashMap<String, Object>();
        for (Iterator<UIComponent> iter = component.getChildren().iterator(); iter.hasNext();) {
            Object child = iter.next();
            if (child instanceof UIParameter) {
                UIParameter param = (UIParameter) child;
                parameters.put(param.getName(), param.getValue());
            }
        }

        parameters.put(getHiddenFieldId(context, form, component), component.getClientId(context));

        submit.addParameter(parameters);
        onclick.append("return ");
        submit.appendScript(onclick);
        return onclick.toString();
    }

    public String getValue(UIComponent command) {
        String valueString = null;
        Object value = ((UICommand) command).getValue();
        if (null != value) {
            valueString = value.toString();
        }
        return valueString;
    }

    public boolean isDisabled(UIComponent command) {
    	//TODO nick - this must be rewritten in more efficient way
        if (command.getAttributes().get("disabled") != null) {
            if ((command.getAttributes().get("disabled")).equals(Boolean.TRUE)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNestedWithinForm(FacesContext context, UIComponent component) {
        boolean isNested = (null != getUtils().getNestingForm(context, component));
        if(!isNested) {
            if (_log.isWarnEnabled()) {
                _log.warn(Messages
                        .getMessage(Messages.COMMAND_LINK_NOT_IN_FORM_WARNING, component.getClientId(context)));
            }
        }
        return isNested;
    }

    private String getHiddenFieldId(FacesContext context, UIForm form, UIComponent component) {
        return form.getClientId(context) + AjaxFormRenderer.HIDDEN_FIELD_SUFFIX;
    }
}
