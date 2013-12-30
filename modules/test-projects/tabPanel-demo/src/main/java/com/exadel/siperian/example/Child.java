//=====================================================================
// project:   Siperian Hub
//---------------------------------------------------------------------
// copyright: Siperian Inc. (c) 2003-2009.  All rights reserved.
//=====================================================================

package com.exadel.siperian.example;

import java.util.List;
import javax.faces.event.ActionEvent;

import org.ajax4jsf.component.html.HtmlAjaxSupport;
import org.richfaces.component.html.HtmlDataTable;

/**
 * @author Valery Tcherepanov
 */
public class Child {

    private List<Element> data;

    private List selection;

    private String[] columns = {"Name", "Symbol", "Host"};

    private String name;

    public Child(String name, List<Element> data) {
        this.name = name;
        this.data = data;
    }

    public List<Element> getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public String getViewFileName() {
        return "/include/dataview_child.xhtml";
    }

    public String[] getColumns() {
        return columns;
    }

    public void onRowSelection(ActionEvent e) {
        int selectionIndex = ((HtmlDataTable) ((HtmlAjaxSupport) e.getSource()).getParent()).getRowIndex();
        selection = data.get(selectionIndex);
    }

    public List getSelection() {
        return selection;
    }
}
