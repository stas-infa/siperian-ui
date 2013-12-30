package com.exadel.sample;

import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.ajax4jsf.context.AjaxContext;

public class ValidationListener implements PhaseListener {
	
	private static final long serialVersionUID = 3535952855461578601L;

	public void afterPhase(PhaseEvent event) {
		
	}
	
	public void beforePhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		Iterator<String> i = context.getClientIdsWithMessages();
		while (i.hasNext()) {
			String id = i.next();
			UIComponent c = context.getViewRoot().findComponent(id);
			if (c != null) {
				AjaxContext.getCurrentInstance(context).addComponentToAjaxRender(c.getParent());
			}
		}
		
	}
	
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
