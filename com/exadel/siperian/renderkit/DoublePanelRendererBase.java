/**
 * 
 */
package com.exadel.siperian.renderkit;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;
import org.ajax4jsf.renderkit.RendererUtils;

import com.exadel.siperian.component.UIDoublePanel;

/**
 * @author Evgenij Stherbin
 *
 */

//TODO nick - that is not a RenderKit - rename
public class DoublePanelRendererBase extends HeaderResourcesRendererBase {
	
	static final String LEFT_CONTENT_FACET = "leftPanelContent";
	
	static final String RIGHT_CONTENT_FACET = "rightPanelContent";

    /**
     * @see org.ajax4jsf.renderkit.RendererBase#getComponentClass()
     */
    @Override
    protected Class<? extends UIComponent> getComponentClass() {
        // TODO Auto-generated method stub
        return UIDoublePanel.class;
    }
    
    private void encodeFacet (FacesContext context, UIComponent component, String facetName)throws IOException  {
    	UIComponent facet = component.getFacet(facetName);
    	if (facet != null) {
    		renderChild(context, facet);
    	}
    }
    
    public void encodeLeftContent(FacesContext context, UIComponent component) throws IOException {
    	encodeFacet(context, component, LEFT_CONTENT_FACET);
    }
    
    public void encodeRightContent(FacesContext context, UIComponent component) throws IOException {
    	encodeFacet(context, component, RIGHT_CONTENT_FACET);
    }
    
    

}
