package com.exadel.siperian.model.impl;

import com.exadel.siperian.model.TimeLineSwimLaneModel;

public class TimeLineSwimLaneModelImpl implements TimeLineSwimLaneModel {
	
	String swimLineId;
	
	String swimLimeName;
	
	String swimLaneStyleClass;
	
	

	public TimeLineSwimLaneModelImpl(String swimLineId, String swimLimeName,
			String swimLaneStyleClass) {
		super();
		this.swimLineId = swimLineId;
		this.swimLimeName = swimLimeName;
		this.swimLaneStyleClass = swimLaneStyleClass;
	}

	public String getSwimLaneId() {
		// TODO Auto-generated method stub
		return swimLineId;
	}

	public String getSwimLaneName() {
		// TODO Auto-generated method stub
		return swimLimeName;
	}

	public String getSwimLaneStyleClass() {
		// TODO Auto-generated method stub
		return swimLaneStyleClass;
	}

}
