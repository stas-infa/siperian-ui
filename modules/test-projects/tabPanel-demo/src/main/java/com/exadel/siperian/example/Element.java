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
public class Element extends ArrayList {

    public Object get(int index) {
        System.out.println("TRACE get(int index) " + index);
        return super.get(index);
    }

    public Object set(int index, Object element) {
        System.out.println("TRACE set(int index, Object element) " + index + " " + element);
        return super.set(index, element);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
