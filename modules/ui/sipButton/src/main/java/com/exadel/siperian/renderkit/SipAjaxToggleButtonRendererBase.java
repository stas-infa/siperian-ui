//=====================================================================
// project:   Siperian Hub
//---------------------------------------------------------------------
// copyright: Siperian Inc. (c) 2003-2013.  All rights reserved.
//=====================================================================

package com.exadel.siperian.renderkit;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.exadel.siperian.component.SipUIAjaxToggleButton;

/**
 * @author Alexander Chigrinets
 * @since 09.10.13
 */
public class SipAjaxToggleButtonRendererBase extends SipAjaxButtonRendererBase {

    @Override
    protected Class<? extends UIComponent> getComponentClass() {

        return SipUIAjaxToggleButton.class;
    }

    public boolean isPressedState(FacesContext context, UIComponent component) {
        return getUtils().isBooleanAttribute(component, SipUIAjaxToggleButton.PRESSED_STATE);
    }

    @Override
    public String getClick1(FacesContext context, UIComponent component) {
        StringBuilder buf = new StringBuilder( super.getClick1(context, component));
        buf.append(" SipToggleButton.toggle('");
        buf.append(component.getClientId(context));
        buf.append("');");
        return buf.toString();
    }
}
