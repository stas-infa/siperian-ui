package com.exadel.siperian.model.impl;

import com.exadel.siperian.model.Interval;

public class IntervalImpl implements Interval {

	private static final long serialVersionUID = -5540585006931642475L;

	private String length;
	private String marker;
	private String datePattern;


	public IntervalImpl() {
	}
	
	public IntervalImpl(String length, String marker, String datePattern) {
		this.length = length;
		this.marker = marker;
		this.datePattern = datePattern;
	}

	public String getLength() {
		return length;
	}

	public String getMarker() {
		return marker;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
}
