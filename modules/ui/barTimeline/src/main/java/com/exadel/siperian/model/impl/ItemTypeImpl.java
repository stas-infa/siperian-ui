package com.exadel.siperian.model.impl;

import com.exadel.siperian.model.ItemType;

public class ItemTypeImpl implements ItemType {

	private static final long serialVersionUID = 202792404885828157L;

	private String id;
	private String name;
	private String styleClass;
	private String selectedStyleClass;
	private String hoverStyleClass;
	private String highlightStyleClass;
	private String tooltipStyleClass;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStyleClass() {
		return styleClass;
	}
	
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	
	public String getSelectedStyleClass() {
		return selectedStyleClass;
	}
	
	public void setSelectedStyleClass(String selectedStyleClass) {
		this.selectedStyleClass = selectedStyleClass;
	}
	
	public String getHoverStyleClass() {
		return hoverStyleClass;
	}
	
	public void setHoverStyleClass(String hoverStyleClass) {
		this.hoverStyleClass = hoverStyleClass;
	}
	
	public String getHighlightStyleClass() {
		return highlightStyleClass;
	}
	
	public void setHighlightStyleClass(String highlightStyleClass) {
		this.highlightStyleClass = highlightStyleClass;
	}
	
	public String getTooltipStyleClass() {
		return tooltipStyleClass;
	}
	
	public void setTooltipStyleClass(String tooltipStyleClass) {
		this.tooltipStyleClass = tooltipStyleClass;
	}
	
}
