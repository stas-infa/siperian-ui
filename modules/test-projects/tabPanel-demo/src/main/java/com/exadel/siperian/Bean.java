/**
 * License Agreement.
 *
 *  JBoss RichFaces - Ajax4jsf Component Library
 *
 * Copyright (C) 2007  Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package com.exadel.siperian;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;





/**
 * @author $Autor$
 *
 */
public class Bean {
	
	Object selectedTab;
	
	String markup = "dynamic";
	
	List<String> newTabsId = new ArrayList<String>();
	
	public class Tab {
		String name;
		String input;
		Boolean longRunning;
		public Tab(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public String getInput() {
			return input;
		}
		
		public void setInput(String input) {
			System.out.println("Tab.setInput() " + input);
			this.input = input;
		}
		
		public String getUrl() {
			return "sipTest2.xhtml";
		}
		
	}
	
	List<Tab> tabs = new ArrayList<Tab>();
	
	public void removeTab(String name) {
		Integer i = getTabIndexByname(name);
		if (i != null) {
			tabs.set(i, null);
		}
		System.out.println("Bean.removeTab() " + name);
	}
	
	public String search() {
		newTabsId.clear();
		addNew();
		//addNew();
		return null;
	}

	private void addNew() {
		int i = tabs.size() + 1;
		tabs.add(new Tab("Tab" + i));
		selectedTab = "Tab" + (i);
		newTabsId.add("tabIDTab" + i);
	}
	
	public void clearNewTabs(ActionEvent event) {
		newTabsId.clear();
	}
	
	
	public void actionListener(ActionEvent event) {
		System.out.println("Bean.actionListener()");
	}
	
	Integer getTabIndexByname(String name) {
		int i=0;
		for (Tab t : tabs) {
			
			if (t != null && t.name.equals(name)) {
				return i;
			}
			i++;
		}
		return null;
	}
	
	public Bean() {
		for (int i=1; i<2;i++) {
			tabs.add(new Tab("Tab" + i));
		}
		
		selectedTab = "Tab1";
	}
	
	public void clear(ActionEvent event) {
		selectedTab = null;
	}
	
	
	public List<Tab> getTabs() {
		return tabs;
	}
	
	public Object getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(Object selectedTab) {
		this.selectedTab = selectedTab;
		System.out.println("Bean.setSelectedTab() " + selectedTab);
	}
	
	public List<String> getNewTabsId() {
		return newTabsId;
	}
	
	public void switchMarkup(ActionEvent event) {
		markup = ("dynamic".equals(markup)) ? "static" : "dynamic";
	
	}
	
	public String getMarkup() {
		return markup;
	}
	
}