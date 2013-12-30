/**
 * 
 */
package com.exadel.siperian.listener;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Evgenij Stherbin
 *
 */
public class ModalPanelPhaseListener implements PhaseListener {
    
    private static Log log = LogFactory.getLog(ModalPanelPhaseListener.class);

    /**
     * 
     */
    private static final long serialVersionUID = -5552216285069651051L;

    /**
     * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
     */
    public void afterPhase(PhaseEvent arg0) {
       // No implemented
        log.debug("After phase");
        
    }

    /**
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     */
    public void beforePhase(PhaseEvent arg0) {
        FacesContext ctx = arg0.getFacesContext();
        UIViewRoot root = ctx.getViewRoot();
        
        log.debug("Before phase");
        
    }

    /* (non-Javadoc)
     * @see javax.faces.event.PhaseListener#getPhaseId()
     */
    public PhaseId getPhaseId() {
     
        return PhaseId.RENDER_RESPONSE;
    }
    

}
