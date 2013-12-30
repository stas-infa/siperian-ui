package com.exadel.siperian.model;

import java.io.Serializable;

public class BarTimelineState implements Serializable{
	
	private static final long serialVersionUID = 4492533164222926416L;

	private Long visibleIntervalFromPosition;
	
	private Long visibleIntervalToPosition;
	
	private Long verticalRulerPosition;

	public Long getVisibleIntervalFromPosition() {
		return visibleIntervalFromPosition;
	}

	public void setVisibleIntervalFromPosition(Long visibleIntervalFromPosition) {
		this.visibleIntervalFromPosition = visibleIntervalFromPosition;
	}

	public Long getVisibleIntervalToPosition() {
		return visibleIntervalToPosition;
	}

	public void setVisibleIntervalToPosition(Long visibleIntervalToPosition) {
		this.visibleIntervalToPosition = visibleIntervalToPosition;
	}

	public Long getVerticalRulerPosition() {
		return verticalRulerPosition;
	}

	public void setVerticalRulerPosition(Long verticalRulerPosition) {
		this.verticalRulerPosition = verticalRulerPosition;
	}
}
