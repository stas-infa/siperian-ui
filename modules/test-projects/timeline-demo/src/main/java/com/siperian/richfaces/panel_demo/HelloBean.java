package com.siperian.richfaces.panel_demo;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Data
 * 
 */
public class HelloBean {
    private String name;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getToolTipContent() {
    	FacesContext context = FacesContext.getCurrentInstance();
    	ExternalContext externalContext = context.getExternalContext();
    	Map<String, String> params = externalContext.getRequestParameterMap();
    	return params.get("toolTipContent");
    }
}