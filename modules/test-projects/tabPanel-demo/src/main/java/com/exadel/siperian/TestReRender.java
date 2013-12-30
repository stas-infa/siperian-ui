//=====================================================================
// project:   Siperian Hub
//---------------------------------------------------------------------
// copyright: Siperian Inc. (c) 2003-2009.  All rights reserved.
//=====================================================================

package com.exadel.siperian;

import javax.faces.context.FacesContext;

/**
 * @author Alexander Chigrinets
 * @since Jun 24, 2009
 */
public class TestReRender {

    private boolean rendered;
    String value="initial";

    private boolean rendered2;

    public boolean isRendered() {
        return rendered;
    }

    public boolean isRendered2() {
        return rendered2;
    }

    public Object openDlg() {
        rendered = true;
        return null;
    }

    public Object closeDlg() {
        rendered = false;
        return null;
    }

    public Object openDlg2() {
        rendered2 = true;
        return null;
    }

    public Object closeDlg2() {
        rendered2 = false;
        return null;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean isHaveErrors(){
        return FacesContext.getCurrentInstance().getMessages().hasNext();
    }

    public String getErrorMessage(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (isHaveErrors())
            return ctx.getMessages().next().getSummary();
        return "No errors";
    }

    public void clear(){
        setValue("123");
    }
}
