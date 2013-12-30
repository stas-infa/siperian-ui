package com.siperian.demo.extendedDataTable.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

public class ExtendedDataTableControlBean {

    String width = "100%";
    String height = "500px";
    List<SelectItem> sortModeSelectItems = new ArrayList<SelectItem>();
    List<SelectItem> selectionModeSelectItems = new ArrayList<SelectItem>();
    String sortMode;
    String selectionMode;
    boolean scrollable=true;
    Integer rowsNumber = 5;
    boolean paginated = false;
    
    private boolean showRegion = Boolean.TRUE; 
    
    
    public void changeRegionVisibility(ActionEvent anEvent) {
    	this.showRegion = (this.showRegion == Boolean.TRUE) ? Boolean.FALSE : Boolean.TRUE;     	
    }
    
    
    public ExtendedDataTableControlBean() {
        sortModeSelectItems.add(new SelectItem("single", "single"));
        sortModeSelectItems.add(new SelectItem("multi", "multi"));
        selectionModeSelectItems.add(new SelectItem("single", "single"));
        selectionModeSelectItems.add(new SelectItem("multi", "multi"));
        selectionModeSelectItems.add(new SelectItem("none", "none"));
    }
    
    public boolean isPaginated() {
        return paginated;
    }

    public void setPaginated(boolean paginated) {
        this.paginated = paginated;
    }

    public Integer getRowsNumber() {
        return rowsNumber;
    }

    public void setRowsNumber(Integer rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    public List<SelectItem> getSelectionModeSelectItems() {
        return selectionModeSelectItems;
    }

    public void setSelectionModeSelectItems(
            List<SelectItem> selectionModeSelectItems) {
        this.selectionModeSelectItems = selectionModeSelectItems;
    }



    public List<SelectItem> getSortModeSelectItems() {
        return sortModeSelectItems;
    }

    public void setSortModeSelectItems(List<SelectItem> sortModeSelectItems) {
        this.sortModeSelectItems = sortModeSelectItems;
    }
    
    public String getSortMode() {
        return sortMode;
    }

    public void setSortMode(String sortMode) {
        this.sortMode = sortMode;
    }

    public String getSelectionMode() {
        return selectionMode;
    }

    public void setSelectionMode(String selectionMode) {
        this.selectionMode = selectionMode;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

	public void setScrollable(boolean scrollable) {
		this.scrollable = scrollable;
	}

	public boolean isScrollable() {
		return scrollable;
	}

	public boolean isShowRegion() {
		return showRegion;
	}

	public void setShowRegion(boolean showRegion) {
		this.showRegion = showRegion;
	}

}
