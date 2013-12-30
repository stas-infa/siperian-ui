/**
 * 
 */
package com.exadel.siperian.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.exadel.siperian.model.TimeLineDataModel;
import com.exadel.siperian.model.TimeLineEventDataModel;
import com.exadel.siperian.model.TimeLineEventTypeModel;
import com.exadel.siperian.model.TimeLineSwimLaneModel;

/**
 * @author Andrey
 *
 */
public class TimeLineDataModelImpl implements TimeLineDataModel {
	
	List<TimeLineEventTypeModel> eventTypes; 
	
	TimeLineEventDataModel [] data;
	
	TimeLineSwimLaneModel[] swimLanes;
	
	
	public TimeLineDataModelImpl() {
		eventTypes = new ArrayList<TimeLineEventTypeModel>();
		eventTypes.add(new TimeLineEventTypeModelImpl("type1", "name1", "class1"));
		eventTypes.add(new TimeLineEventTypeModelImpl("type2", "name2", "class2"));
		
		
		
		data = new TimeLineEventDataModelImpl[5];
		for (int i = 0 ;i < 5; i++) {
			data[i] = new TimeLineEventDataModelImpl(new Date(), "1", "type1", "lane1", "tooltip1", i == 0);
		}
		
		swimLanes = new TimeLineSwimLaneModelImpl[2];
		swimLanes[0] = new TimeLineSwimLaneModelImpl("lane1", "laneName1", "laneClass1");
		swimLanes[1] = new TimeLineSwimLaneModelImpl("lane2", "laneName2", "laneClass2");
		
	}

	/* (non-Javadoc)
	 * @see com.exadel.siperian.model.TimeLineDataModel#getData(java.util.Date, java.util.Date)
	 */
	public TimeLineEventDataModel[] getData(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return data;
	}

	/* (non-Javadoc)
	 * @see com.exadel.siperian.model.TimeLineDataModel#getSwimLanes()
	 */
	public TimeLineSwimLaneModel[] getSwimLanes() {
		// TODO Auto-generated method stub
		return swimLanes;
	}

}
