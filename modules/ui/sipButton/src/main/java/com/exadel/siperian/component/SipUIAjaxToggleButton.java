/**
 *
 */

package com.exadel.siperian.component;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;

import org.ajax4jsf.component.UIAjaxCommandLink;
import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.event.AjaxEvent;

/**
 * This component represents button with two states: Pressed and Unpressed
 * Each click to this component toggles state to the opposed.
 * State of a control is stored and special boolean property pressedState is exposed and can be bound to a backing
 * bean.
 *
 * @author Alexander Chigrinets
 */
public abstract class SipUIAjaxToggleButton extends UIAjaxCommandLink {

    public static final String PRESSED_STATE = "pressedState";

    private boolean pressedState;

    @Override
    public void broadcast(FacesEvent event) throws AbortProcessingException {
        super.broadcast(event);
        if (event instanceof AjaxEvent) {
            AjaxContext.getCurrentInstance().addComponentToAjaxRender(this);
        }
    }

    public abstract boolean isPressedState();

    public abstract void setPressedState(boolean pressedState);

    @Override
    public Object saveState(FacesContext context) {
        Object state [] = new Object[2];
        state[0] = super.saveState(context);
        state[1] = pressedState;
        return state;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object states [] = (Object []) state;
        super.restoreState(context, states[0]);
        pressedState = (Boolean)states[1];
    }

    private void doToggle(FacesContext context) {
        // Here we detect that request has been initiated by this component and toggle pressState
        String id = getClientId(context);
        if (id.equals(context.getExternalContext().getRequestParameterMap().get(id))) {
            pressedState = !pressedState;
            ValueExpression ve = getValueExpression(PRESSED_STATE);
            if (ve != null) {
                boolean userValue = (Boolean)ve.getValue(context.getELContext());
                pressedState = !userValue;
                ve.setValue(context.getELContext(), pressedState);
            }
        }
    }

    @Override
    public void processDecodes(FacesContext context) {
        super.processDecodes(context);
        doToggle(context);
    }

    public boolean isInternalPressedState() {
        return getValueExpression(PRESSED_STATE) == null? pressedState : isPressedState();
    }
}
