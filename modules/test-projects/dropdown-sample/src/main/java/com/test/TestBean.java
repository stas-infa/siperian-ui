package com.test;

import javax.faces.event.ActionEvent;

public class TestBean {
	
	String value = "TestValue";
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void action() {
		System.out.println("action");
	}
}
