/**
 * 
 */


package com.exadel.siperian.component;


import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;

import org.ajax4jsf.component.UIAjaxCommandLink;
import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.event.AjaxEvent;


/**
 * @author Evgenij Stherbin
 * 
 */
public abstract class SipUIAjaxCommandButton extends UIAjaxCommandLink {
	
	@Override
	public void broadcast(FacesEvent event) throws AbortProcessingException {
		super.broadcast(event);
		if (event instanceof AjaxEvent) {
			AjaxContext.getCurrentInstance().addComponentToAjaxRender(this);
		}
	}
	
//    Object  reRender  = null;
//
//   
//
//    public Object getReRender() {
//        this.reRender = super.getAttributes().get("reRender");
//        if ( reRender!= null) {
//            String rR = getId() + "," + reRender;
//            System.err.println("ReRender :=" + rR);
//            return rR;
//        }
//        ValueExpression ve = getValueExpression("reRender");
//        if (ve != null) {
//            Object value = null;
//
//            try {
//                value = (Object) ve.getValue(getFacesContext().getELContext());
//            } catch (ELException e) {
//                throw new FacesException(e);
//            }
//            String rR = getId() + "," + value;
//            System.err.println("ReRender :=" + rR);
//            return rR;
//        } else {
//
//        }
//        String id = getId();
//        System.err.println("ReRender :=" + id);
//        return id;
//
//    }
//    
//    public void setReRender(Object o){
//
//       this.reRender = o;
//    }



}
