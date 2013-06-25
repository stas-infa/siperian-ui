package com.exadel.siperian.model;

import java.io.Serializable;
import java.util.Date;

public interface Item extends Serializable{
	
	public String getId();
	
	public String getType();
	
	public Date getStartDate();
	
	public Date getEndDate();
	
	public String getSwimLineId();
	
	public String getTooltip();
	
}
