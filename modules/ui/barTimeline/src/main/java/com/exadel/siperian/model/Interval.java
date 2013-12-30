package com.exadel.siperian.model;

import java.io.Serializable;

public interface Interval extends Serializable {
		
	public String getLength();

	public String getMarker();

	public String getDatePattern();
}
