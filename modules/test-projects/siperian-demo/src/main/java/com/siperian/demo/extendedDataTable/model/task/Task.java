/*
 * DemoTask.java		Date created: Oct 28, 2008
 * Last modified by: $Author: srusak $
 * $Revision: 279 $	$Date: 2008-11-06 15:46:16 +0300 (╫Є, 06 эю  2008) $
 */

package com.siperian.demo.extendedDataTable.model.task;

import java.util.Date;

import com.siperian.demo.common.Entity;


/**
 * This class represents task object
 * @author Sergey Rusak
 *
 */
public class Task implements Entity {
    
    private Integer id;
    
    private String priority;
    
    private String subject;
    
    private String status;
    
    private Date dueDate;
    

    /**
     * TODO Description goes here.
     * @param id
     * @param priority
     * @param subject
     * @param status
     * @param dueDate
     */
    public Task(Integer id, String priority, String subject, String status, Date dueDate) {
        super();
        this.id = id;
        this.priority = priority;
        this.subject = subject;
        this.status = status;
        this.dueDate = dueDate;
    }

    /* (non-Javadoc)
     * @see org.richfaces.samples.extdt.model.impl.Entity#getId()
     */
    public Integer getId() {
        return id;
    }

    /**
     * TODO Description goes here.
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * TODO Description goes here.
     * @return
     */
    public String getPriority() {
        return priority;
    }

    /**
     * TODO Description goes here.
     * @param priority
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * TODO Description goes here.
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     * TODO Description goes here.
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * TODO Description goes here.
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * TODO Description goes here.
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * TODO Description goes here.
     * @return
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * TODO Description goes here.
     * @param dueDate
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

}
