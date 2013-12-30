//=====================================================================
// project:   Siperian Hub
//---------------------------------------------------------------------
// copyright: Siperian Inc. (c) 2003-2009.  All rights reserved.
//=====================================================================

package com.exadel.siperian.example;

import java.util.List;
import java.util.ArrayList;

/**
 * @author: Alexander Chigrinets
 * Date: 13.11.2008
 */
public class DataUIBean extends UIBean{

    private List<String> values = new ArrayList<String>();

    private int currentPage=1;


    public DataUIBean(String firstName, String lastName) {
        super(firstName, lastName);
        buildValues();
    }

    private void buildValues() {
        for (int i=0; i<1000; i++){
            if (i % 2 == 0){
                values.add(getFirstName()+"__"+i);
            }
            else{
                values.add(getLastName()+"__"+i);
            }
        }
    }

    public List<String> getValues(){
        return values; 
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
