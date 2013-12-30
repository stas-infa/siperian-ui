/**
 * 
 */


package com.siperian.demo.timeline;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.exadel.siperian.model.TimeLineDataModel;
import com.exadel.siperian.model.TimeLineEventDataModel;
import com.exadel.siperian.model.TimeLineEventTypeModel;
import com.exadel.siperian.model.TimeLineSwimLaneModel;
import com.exadel.siperian.model.impl.TimeLineEventDataModelImpl;
import com.exadel.siperian.model.impl.TimeLineEventTypeModelImpl;
import com.exadel.siperian.model.impl.TimeLineSwimLaneModelImpl;
import com.exadel.siperian.test.SimpleTimeLineDataModel;


/**
 * Generate fake data for testing.
 * @author Evgenij Stherbin
 * 
 */
public class FakeDataGenerator {
    
    public static final ArrayList<TimeLineSwimLaneModel> stubSwimLines = new ArrayList<TimeLineSwimLaneModel>();
    private static final String[] STYLES_DEFINITIONS=new String[]{
       
    };
    private static final Map<String, String> stylesMap = new HashMap<String, String>();

    
    static int uniqueIds = 0;
    static{
        stubSwimLines.add(new TimeLineSwimLaneModelImpl("1","Phone","swimLine"));
        stubSwimLines.add(new TimeLineSwimLaneModelImpl("2","Email",""));
        stubSwimLines.add(new TimeLineSwimLaneModelImpl("3","Address",""));
        stubSwimLines.add(new TimeLineSwimLaneModelImpl("4","Person",""));
        stubSwimLines.add(new TimeLineSwimLaneModelImpl("5","Party1",""));
        stubSwimLines.add(new TimeLineSwimLaneModelImpl("6","Party2",""));
        stubSwimLines.add(new TimeLineSwimLaneModelImpl("7","Party3",""));
        stubSwimLines.add(new TimeLineSwimLaneModelImpl("8","Party4",""));
        stubSwimLines.add(new TimeLineSwimLaneModelImpl("9","Party5",""));
        stubSwimLines.add(new TimeLineSwimLaneModelImpl("10","Party6",""));
        stubSwimLines.add(new TimeLineSwimLaneModelImpl("11","Party7",""));

       
    }

    
    static int hoursINc = 6;

    /**
     * Generate event for each second between from and to date.
     * 
     * @param from
     * @param to
     * @return
     */
    public static List<TimeLineEventDataModel> generateEvents(int count ,String swimLineId,String eventTypeId,Date from,Date to){
        final List<TimeLineEventDataModel> r = new ArrayList<TimeLineEventDataModel>();
        
        for(int i = 0 ; i < count ; i ++ ){
            Calendar c = Calendar.getInstance();
            c.setTime(from);
            
            c.add(Calendar.SECOND, hoursINc);
            hoursINc = hoursINc + 2;
            long timeInMilis =  c.getTimeInMillis();
            
            TimeLineEventDataModel dm = new TimeLineEventDataModelImpl(new Date(timeInMilis),String.valueOf(++uniqueIds),eventTypeId,String.valueOf(swimLineId),"Tool tip:"+i,true);
            r.add(dm);
        }
        return r;
    }
    
    public static TimeLineEventTypeModel createEventType(String name,String id,String css){
        return new TimeLineEventTypeModelImpl(id,name,css);
    }
    
    public static TimeLineDataModel getModelForTest1(Date from,Date to,int countOfSwimLines){
        final SimpleTimeLineDataModel dataModel = new  SimpleTimeLineDataModel();        

        
        for(String style:stylesMap.values()){
            dataModel.addStyle(style);
        }
        
        dataModel.addAllTimeLineSwimLaneModel(stubSwimLines.subList(0, countOfSwimLines));
        
        dataModel.addTimeLineEventTypeModel(FakeDataGenerator.createEventType("test_name", "type1", "event1"));
        dataModel.addTimeLineEventTypeModel(FakeDataGenerator.createEventType("test_name2", "type2", "event2"));
        dataModel.addTimeLineEventTypeModel(FakeDataGenerator.createEventType("test_name3", "type3", "event3"));
        Random r  = new Random(System.currentTimeMillis() * 100000);
        int i = 0 ;
  
        for(TimeLineSwimLaneModel sm:dataModel.getSwimLanes()){
      
        
            dataModel.addAllTimeLineEventDataModel(FakeDataGenerator.generateEvents(5, sm.getSwimLaneId(),dataModel.getEventTypes()[r.nextInt(dataModel.getEventTypes().length)].getEventTypeId(),from,to));
            i++;
        }
        
        
            
        return dataModel;
    }
}
