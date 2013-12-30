/**
 * 
 */


package com.exadel.siperian.renderkit;


import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.ajax4jsf.renderkit.AjaxCommandRendererBase;

import com.exadel.siperian.component.SipUIAjaxCommandButton;


/**
 * @author Evgenij Stherbin
 * 
 */
public class SipAjaxButtonRendererBase extends AjaxCommandRendererBase {

    /*
     * (non-Javadoc)
     * 
     * @see org.ajax4jsf.renderkit.RendererBase#getComponentClass()
     */
    @Override
    protected Class<? extends UIComponent> getComponentClass() {

        return SipUIAjaxCommandButton.class;
    }

    
    public String getClick1(FacesContext context, UIComponent component){
        StringBuffer sb = new StringBuffer();
        final String clientId = component.getClientId(context);
        sb.append("$('");
        sb.append(clientId);
        sb.append("')");
        sb.append(".click()");
        sb.append(";");
        return sb.toString();
    }
    public boolean isIE7(FacesContext context,UIComponent component){
        boolean isAddId = false;
        if (context != null && context.getExternalContext().getRequest() instanceof HttpServletRequest) {
            final String agent = ((HttpServletRequest) context.getExternalContext().getRequest()).getHeader("user-agent");
            
            if ((agent != null) && (agent.indexOf("MSIE 7.0") != -1 || agent.indexOf("MSIE 6") != -1) || agent.indexOf("MSIE 8.0") != -1) {
                isAddId = true;
            }
        }
        return isAddId;
    }
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        // TODO Auto-generated method stub
//        boolean isAddId = true;
//  
//        if (isAddId && (component instanceof SipUIAjaxCommandButton)) {
//            final SipUIAjaxCommandButton c2 = (SipUIAjaxCommandButton) component;
//            final Object reRender = c2.getReRender();
//            
//            if (reRender == null || reRender.toString().length() == 0) {
//                c2.setReRender(c2.getId());
//            } else if(reRender.toString().indexOf(c2.getId())==-1){
//                c2.setReRender(reRender + "," + c2.getId());
//            }
//        }
        super.encodeBegin(context, component);
    }

}
