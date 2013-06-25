package com.exadel.siperian.model;

import java.io.Serializable;

public interface SwimLine extends Serializable{
	
	public String getId();
	
	public String getName();
	
	public String getStyleClass();
	
	public void setStyleClass(String styleClass);

}
