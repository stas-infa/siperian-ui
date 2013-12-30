//=====================================================================
// project:   Siperian Hub
//---------------------------------------------------------------------
// copyright: Siperian Inc. (c) 2003-2009.  All rights reserved.
//=====================================================================

package com.exadel.siperian.example;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Valery Tcherepanov
 */
public class TabContainer {

    private List<TabConfig> tabs;
    private TabConfig selectedTab;

    public TabContainer() {
        tabs = new ArrayList<TabConfig>();
    }
    
    public String action() {
        tabs.add(new TabConfig("/include/dataview.xhtml", new UIBean("John", "Washington")));
        tabs.add(new TabConfig("/include/datatable.xhtml", new DataUIBean("John", "Kennedy")));
        tabs.add(new TabConfig("/include/dataview.xhtml", new UIBean("Ronald", "Regan")));
        tabs.add(new TabConfig("/include/datatable.xhtml", new DataUIBean("George", "Bush")));
    	tabs.add(new TabConfig("/include/datatable.xhtml", new DataUIBean("George2", "Bush2")));
    	return null;
    }

    public List<TabConfig> getTabs() {
        return tabs;
    }

    public TabConfig getSelectedTab() {
        return selectedTab;
    }
}
