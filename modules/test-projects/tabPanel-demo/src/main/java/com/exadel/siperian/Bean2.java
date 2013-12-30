package com.exadel.siperian;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

public class Bean2 {
	
	List<String> list = new ArrayList<String>();
	
	public void listener(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		System.out.println(params.get("param"));
	}
	
	public Bean2() {
		for (int i=0;i<10;i++) {
			list.add(Integer.toString(i));
		}
	}

	public List<String> getList() {
		return list;
	}
}
