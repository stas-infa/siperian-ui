/*
 * ExtendedDataTableTaskBB.java		Date created: Nov 6, 2008
 * Last modified by: $Author: alevkovsky $
 * $Revision: 543 $	$Date: 2008-11-23 17:25:04 +0300 (┬ё, 23 эю  2008) $
 */

package com.siperian.demo.extendedDataTable.beans;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ajax4jsf.model.ExtendedDataModel;

import com.exadel.siperian.component.UIExtendedDataTable;
import com.siperian.demo.extendedDataTable.model.task.Task;

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
    
    private List<Field> fieldDescriptors = Arrays.asList(Task.class.getDeclaredFields());
    
    
    public ExtendedDataTableTaskBB(){
        //initialize highlight colors
        highlightColors.put("Overdue", "redHighlight");
        highlightColors.put("On Time", "greenHighlight");
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

    public List<Field> getFieldDescriptors() {
        return fieldDescriptors;
    }

    public void setFieldDescriptors(List<Field> fieldDescriptors) {
        this.fieldDescriptors = fieldDescriptors;
    }

}
