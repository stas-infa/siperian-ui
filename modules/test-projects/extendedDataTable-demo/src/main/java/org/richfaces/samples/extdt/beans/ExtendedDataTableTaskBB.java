/*
 * ExtendedDataTableTaskBB.java		Date created: Oct 28, 2008
 * Last modified by: $Author: alevkovsky $
 * $Revision: 544 $	$Date: 2008-11-23 17:48:25 +0300 (┬ё, 23 эю  2008) $
 */

package org.richfaces.samples.extdt.beans;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.component.UIComponent;

import org.ajax4jsf.model.ExtendedDataModel;
import org.richfaces.component.UIColumn;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.samples.extdt.model.task.DataProviderImpl;
import org.richfaces.samples.extdt.model.task.Task;

import com.exadel.siperian.component.UIExtendedDataTable;


/**
 * Backing Bean for DemoTask entities to show the ability to highlight the cells
 * depending on the cell content.
 * 
 * @author Sergey Rusak
 * 
 */
public class ExtendedDataTableTaskBB {
    
    private Map<String, String> highlightColors = new HashMap<String, String>();
    
    private UIExtendedDataTable extendedDataTable;
    
    private ExtendedDataModel dataModel;
    
    public ExtendedDataTableTaskBB(){
        //initialize highlight colors
        highlightColors.put("Overdue", "redHighlight");
        highlightColors.put("On Time", "greenHighlight");        
    }
    
    /**
     * TODO Description goes here.
     */
/*    @PostConstruct
    public void init(){        
        //dataModel = new ExtendedTableDataModel<Task>(new DemoTaskProvider(1000));
        dataModel = new ExtendedTableDataModel<Task>(new CachedDataProvider());
    }*/
    /**
     * TODO Description goes here.
     */
    @PreDestroy
    public void cleanup(){
        extendedDataTable=null;
        dataModel=null;
       
    }
    /**
     * TODO Description goes here.
     * @return
     */
    public UIExtendedDataTable getExtendedDataTable() {
        return extendedDataTable;
    }
    /**
     * TODO Description goes here.
     * @param extendedDataTable
     */
    public void setExtendedDataTable(UIExtendedDataTable extendedDataTable) {
        this.extendedDataTable = extendedDataTable;
    }
    /**
     * TODO Description goes here.
     * @return
     */
    public ExtendedDataModel getDataModel() {
        return dataModel;
    }
    /**
     * TODO Description goes here.
     * @param dataModel
     */
    public void setDataModel(ExtendedDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public Map<String, String> getHighlightColors() {
        return highlightColors;
    }

    public void setHighlightColors(Map<String, String> highlightColors) {
        this.highlightColors = highlightColors;
    }

}
