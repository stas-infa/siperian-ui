//=====================================================================
// project:   Siperian Hub
//---------------------------------------------------------------------
// copyright: Siperian Inc. (c) 2003-2009.  All rights reserved.
//=====================================================================

package com.exadel.siperian.example;

/**
 * @author Valery Tcherepanov
 */
public class TabConfig {

    private String viewFileName;
    private UIBean uiBean;
    private String name;
    private String label;

    public TabConfig(String viewFileName, UIBean uiBean) {
        this.viewFileName = viewFileName;
        this.uiBean = uiBean;
        this.label = uiBean.getFullName();
        this.name = normalize(this.label);
    }

    public String getViewFileName() {
        return viewFileName;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public UIBean getUiBean() {
        return uiBean;
    }

    /**
     * ID's are not allowed to have whitespaces
     */
    public static String normalize(String str) {
        int length = str.length();
        char[] resArr = new char[length];

        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c == ' ' || c == '.') {
                resArr[i] = '_';
            } else {
                resArr[i] = c;
            }
        }
        return new String(resArr);
    }
}
