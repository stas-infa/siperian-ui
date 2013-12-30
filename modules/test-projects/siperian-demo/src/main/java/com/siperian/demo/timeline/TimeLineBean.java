/**
 * 
 */


package com.siperian.demo.timeline;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.exadel.siperian.model.ITimeLineState;
import com.exadel.siperian.model.TimeLineDataModel;
import com.exadel.siperian.model.impl.TimeLineStateImpl;
import com.exadel.siperian.test.SimpleTimeLineDataModel;


/**
 * @author Evgenij Stherbin
 * 
 */
public class TimeLineBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5043772836220190839L;

    public static Date INIT_FROM_DATE;

    public static Date INIT_TO_DATE;
    static {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            INIT_FROM_DATE = sdf.parse("2009/01/01 20:20");
            INIT_TO_DATE = sdf.parse("2009/01/01 20:25");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Date fromDate = INIT_FROM_DATE;

    private Date toDate = INIT_TO_DATE;
    
    private Date zoomerFromDate =INIT_FROM_DATE;
    
    private Date zoomerToDate  = INIT_TO_DATE;

    private Date visibleDateFrom = null;

    private Date visibleDateTo = null;

    private Integer countOfSwimLines = new Integer(4);

    private String timelineState = null;

    private ITimeLineState state = new TimeLineStateImpl();

    public ITimeLineState getState() {
        return state;
    }

    public void setState(ITimeLineState state) {
        this.state = state;
    }

    public Integer getCountOfSwimLines() {
        return countOfSwimLines;
    }

    public void setCountOfSwimLines(Integer countOfSwimLines) {
        this.countOfSwimLines = countOfSwimLines;
    }

    public List<SelectItem> getSwimLineValues() {
        final List<SelectItem> values = new ArrayList<SelectItem>();

        for (int i = 1; i <= 8; i++) {
            values.add(new SelectItem(new Integer(i), String.valueOf(i)));
        }
        return values;
    }

    private String[] values = null;

    private String value = null;

    private TimeLineDataModel swfData = null;

    public Date getVisibleDateFrom() {
        return visibleDateFrom;
    }

    public void setVisibleDateFrom(Date visibleDateFrom) {
        this.visibleDateFrom = visibleDateFrom;
    }

    public Date getVisibleDateTo() {
        return visibleDateTo;
    }

    public void setVisibleDateTo(Date visibleDateTo) {
        this.visibleDateTo = visibleDateTo;
    }

    public TimeLineDataModel getSwfData() {
        if (swfData == null) {
            this.swfData = FakeDataGenerator.getModelForTest1(getFromDate(), getToDate(), getCountOfSwimLines());
        }
        return swfData;
    }

    public void setSwfData(TimeLineDataModel swfData) {
        this.swfData = swfData;
    }

    public Date getFromDate() {

        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    protected String getRemoteContent(HttpServletRequest req) {

        return null;

    }

    public void actionListener(ActionEvent e) {
        if (fromDate != null && toDate != null) {
            this.swfData = FakeDataGenerator.getModelForTest1(getFromDate(), getToDate(), getCountOfSwimLines());
            System.err.println("swfData = " + swfData);

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Select from and to dates", "Select from and to dates"));
        }
    }

    public String[] getValues() {
        if (values == null) {
            values = new String[20];
            for (int i = 0; i < 20; i++) {
                values[i] = "Value - " + i;
            }
        }
        return values;
    }

    public long getDate() {
        if (getSwfData() instanceof SimpleTimeLineDataModel) {
            final Date d = ((SimpleTimeLineDataModel) getSwfData()).getClickedEventDate();
            
            if (d != null) {
                return d.getTime();
            }
        }
        return 0;
    }
    
    public void setValues(String[] values) {
        this.values = values;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String getTestValue(){
        String text="There are the text:#{msg.test1}";
        String rst = (String) FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(),text,String.class);
        return rst;
    }

    public String getTimelineState() {
        return timelineState;
    }

    public void setTimelineState(String timelineState) {
        this.timelineState = timelineState;
    }

    public Date getZoomerFromDate() {
        return zoomerFromDate;
    }

    public void setZoomerFromDate(Date zoomerFromDate) {
        this.zoomerFromDate = zoomerFromDate;
    }

    public Date getZoomerToDate() {
        return zoomerToDate;
    }

    public void setZoomerToDate(Date zoomerToDate) {
        this.zoomerToDate = zoomerToDate;
    }

}
