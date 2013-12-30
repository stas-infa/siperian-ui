package com.exadel.siperian.model.impl;

import java.util.Date;

import com.exadel.siperian.model.Item;

public class ItemImpl implements Item{

	private static final long serialVersionUID = -494175839684494640L;

	private String id;
	private String type;
	private Date startDate;	
	private Date endDate;
	private String swimLineId;
	private String tooltip;
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getSwimLineId() {
		return swimLineId;
	}
	
	public void setSwimLineId(String swimLineId) {
		this.swimLineId = swimLineId;
	}
	
	public String getTooltip() {
		return tooltip;
	}
	
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	
}
