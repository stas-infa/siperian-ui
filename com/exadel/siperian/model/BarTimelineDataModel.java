package com.exadel.siperian.model;

import java.io.Serializable;
import java.util.List;

public interface BarTimelineDataModel extends Serializable{
	
	public List<Item> getItems();
	
	public List<ItemType> getItemTypes();
	
	public List<SwimLine> getSwimLines();
	
	public List<Interval> getIntervals();

}
