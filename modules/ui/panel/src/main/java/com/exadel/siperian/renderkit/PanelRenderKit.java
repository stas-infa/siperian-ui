/**
 * 
 */
package com.exadel.siperian.renderkit;

import javax.faces.component.UIComponent;

import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;

import com.exadel.siperian.component.UIPanel;

/**
 * @author Evgenij Stherbin
 *
 */

//TODO nick - that is not a RenderKit - rename
public class PanelRenderKit extends HeaderResourcesRendererBase {

    /**
     * @see org.ajax4jsf.renderkit.RendererBase#getComponentClass()
     */
    @Override
    protected Class<? extends UIComponent> getComponentClass() {
        // TODO Auto-generated method stub
        return UIPanel.class;
    }

}
