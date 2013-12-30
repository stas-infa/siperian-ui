package com.siperian.demo.extendedDataTable.verticalScroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VerticalScrollerBean {
	
	List<String> list = new ArrayList<String>();
	
	List<String> list2 = new ArrayList<String>();
	
	int page;
	
	public VerticalScrollerBean() {
		list.add("Tab Content 1");
		list.add("Tab Content 2");
		list.add("Tab Content 3");
		list.add("Tab Content 4");
		list.add("Tab Content 5");
		list.add("Tab Content 6");
		list.add("Tab Content 7");
		list.add("Tab Content 8");
		list.add("Tab Content 9");
		list.add("Tab Content 10");
		list.add("Tab Content 11");
		list.add("Tab Content 12");
		list.add("Tab Content 13");
		list.add("Tab Content 14");
		list.add("Tab Content 15");
		list.add("Tab Content 16");
		list.add("Tab Content 17");
		list.add("Tab Content 18");
		list.add("Tab Content 19");
		list.add("Tab Content 20");
		list.add("Tab Content 21");
		list.add("Tab Content 22");
		list.add("Tab Content 23");
		list.add("Tab Content 24");
		list.add("Tab Content 25");
		list.add("Tab Content 26");
		list.add("Tab Content 27");
		list.add("Tab Content 28");
		list.add("Tab Content 29");
		list.add("Tab Content 30");
		
		list2.add("1");
		list2.add("2");
		list2.add("3");
		list2.add("4");
		list2.add("5");
		list2.add("6");
		list2.add("7");
		list2.add("8");
		list2.add("9");
		list2.add("10");
		list2.add("11");
		list2.add("12");
		list2.add("13");
		
	}

	/**
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<String> list) {
		this.list = list;
	}

	public List<String> getList2() {
		return list2;
	}

	public void setList2(List<String> list2) {
		this.list2 = list2;
	}
	
	public String getTime() {
		return new Date().toLocaleString();
	}
	
	public String action() {
		System.out.println("VerticalScrollerBean.action(). Page: " + page);
		return null;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		System.out.println("VerticalScrollerBean.setPage() --> " + page);
		this.page = page;
	}
	
	

}
