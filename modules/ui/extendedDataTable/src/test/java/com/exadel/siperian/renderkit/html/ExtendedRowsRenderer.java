package com.exadel.siperian.renderkit.html;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * @author mpopiolek
 * 
 */
public class ExtendedRowsRenderer extends com.exadel.siperian.renderkit.AbstractExtendedRowsRenderer {

    public ExtendedRowsRenderer() {
    }

    @Override
    public void encodeOneRow(FacesContext context, com.exadel.siperian.renderkit.ExtendedTableHolder holder)
            throws IOException {
    }

    @Override
    protected Class<? extends UIComponent> getComponentClass() {
        return null;
    }
}
