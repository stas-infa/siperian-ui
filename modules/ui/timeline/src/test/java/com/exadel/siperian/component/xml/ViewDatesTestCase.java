/**
 * 
 */
package com.exadel.siperian.component.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.exadel.siperian.xml.XMLDataModelGenerator;

/**
 * @author Evgenij Stherbin
 *
 */
public class ViewDatesTestCase {
    
//    @Test
    public void testViewDate(){
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
      SimpleDateFormat resultFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
      try {
        Date from = sdf.parse("01/01/2008 12:00");
        Date to = sdf.parse("01/01/2008 18:00");
        System.err.println("Before from ="+resultFormat.format(from));
        System.err.println("Before to   ="+resultFormat.format(to));
        Date[] dates = new Date[]{new Date(),new Date()};
        //XMLDataModelGenerator.calculateViewDates();
      
        
        System.err.println("Result from ="+resultFormat.format(dates[0]));
        System.err.println("Result to   ="+resultFormat.format(dates[1]));
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
 
      
    }
}
