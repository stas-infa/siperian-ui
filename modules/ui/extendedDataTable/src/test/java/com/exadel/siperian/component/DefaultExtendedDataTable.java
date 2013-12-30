package com.exadel.siperian.component;

import java.util.ArrayList;
import java.util.List;

import org.richfaces.model.FilterField;
import org.richfaces.model.SortField2;
import org.richfaces.model.selection.Selection;

/**
 * @author mpopiolek
 * 
 */
public class DefaultExtendedDataTable extends com.exadel.siperian.component.UIExtendedDataTable {

    @Override
    public Object getActiveRowKey() {
        return "activeRowKey";
    }

    @Override
    public void setActiveRowKey(Object activeRowKey) {
    }

    @Override
    public String getSortMode() {
        return null;
    }

    @Override
    public void setSortMode(String sortMode) {
    }

    public Selection getSelection() {
        return null;
    }

    public void setSelection(Selection selection) {
    }

    public List<SortField2> getSortFields() {
        List<SortField2> list = new ArrayList<SortField2>();
        return list;
    }

    public void setSortFields(List<SortField2> sortFields) {
    }

    public List<FilterField> getFilterFields() {
        List<FilterField> list = new ArrayList<FilterField>();
        return list;
    }

    public void setFilterFields(List<FilterField> filterFields) {
    }

	@Override
	public String getGroupColumnWidth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGroupColumnWidth(String width) {
		// TODO Auto-generated method stub
		
	}

}
