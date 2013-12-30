package org.richfaces.datatable;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

/**
 * @author Ilya Shaikovsky
 * 
 */
public class FilteringBean {

	private String filterZoneValue = "";
	private String filterValue="";
	private ArrayList<SelectItem> filterZones = new ArrayList<SelectItem>();

	public boolean filterStates(Object current) {
		Capital currentCapital = (Capital)current;
		if (filterValue.length()==0) {
			return true;
		}
		if (currentCapital.getState().toLowerCase().startsWith(filterValue.toLowerCase())) {
			return true;
		}else {
			return false; 
		}
	}

	public boolean filterZone(Object current) {
		Capital currentCapital = (Capital)current;
		if (filterZoneValue.length()==0) {
			return true;
		}
		if (currentCapital.getTimeZone().toLowerCase().contains(filterZoneValue.toLowerCase())) {
			return true;
		}else {
			return false; 
		}
	}

	public FilteringBean() {
		SelectItem allSelect = new SelectItem();
		allSelect.setLabel("ALL");
		allSelect.setValue("");
		filterZones.add(allSelect);
		for (int i = 5; i < 11; i++) {
			SelectItem select = new SelectItem();
			select.setLabel("-" + i);
			select.setValue(i);
			filterZones.add(select);
		}
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public String getFilterZoneValue() {
		return filterZoneValue;
	}

	public void setFilterZoneValue(String filterZone) {
		this.filterZoneValue = filterZone;
	}

	public ArrayList<SelectItem> getFilterZones() {
		return filterZones;
	}
}
