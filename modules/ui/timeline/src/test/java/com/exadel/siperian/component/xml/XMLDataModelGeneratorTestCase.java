/**
 * 
 */
package com.exadel.siperian.component.xml;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Evgenij Stherbin
 *
 */
public class XMLDataModelGeneratorTestCase {



    
    //@Test
    public void testGenerateXml1(){
        
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date from = sdf.parse("2009/01/01 12:00");
            Date to = sdf.parse("2009/01/01   18:00");
            long l1= 1232056800000L;
            long l2 = 1233316800000L;
            Date fromView = new Date(l1);
            Date toView  =  new Date(l2);
            
            System.err.println("viewFromDate = "+sdf.format(fromView) + " toViewDate = "+sdf.format(toView));
//            DataSource ds = new XMLDataModelGenerator(FakeDataGenerator.getModelForTest1(from,to),from,to).getXMLData();
//            System.err.println("output = ");
//            System.err.println(ds.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
//           fail("Test Fail :"+e.getMessage());
        }
        
    }
}
