package com.exadel.siperian.model.impl;

import com.exadel.siperian.model.SwimLine;

public class SwimLineImpl implements SwimLine {
	
	private static final long serialVersionUID = -8677775570200028305L;

	private String id;
	private String name;
	private String styleClass;

	
	public SwimLineImpl() {
	}
	
	public SwimLineImpl(String id, String name) {
		this(id, name, null);
	}
	
	public SwimLineImpl(String id, String name, String styleClass) {
		this.id = id;
		this.name = name;
		this.styleClass = styleClass;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
