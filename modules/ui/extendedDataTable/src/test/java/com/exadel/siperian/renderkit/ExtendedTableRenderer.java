package com.exadel.siperian.renderkit;

import javax.faces.component.UIComponent;

/**
 * @author mpopiolek
 * 
 */
public class ExtendedTableRenderer extends com.exadel.siperian.renderkit.AbstractExtendedTableRenderer {

    @Override
    protected Class<? extends UIComponent> getComponentClass() {
        return com.exadel.siperian.component.UIExtendedDataTable.class;
    }

}
