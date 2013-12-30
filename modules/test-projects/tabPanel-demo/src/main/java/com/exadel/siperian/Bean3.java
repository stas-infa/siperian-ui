package com.exadel.siperian;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

public class Bean3 {
	
	int counter = 0;
	
	int [] exter = {5, 2 ,5 ,8, 4, 5, 6, 3, 1, 8, 5, 3, 7};
	int [] inter = {4, 1 ,7 ,3, 9, 3, 4, 3, 2, 6, 7, 2, 9};
	
	public static class Item {
		String code;
		boolean breakLine;
		int n;
		List<String> list = null;
		
		public Item(String code, boolean breakLine, int n) {
			this.breakLine = breakLine;
			this.code = code;
			this.n = n;
		}
		
		public boolean isBreakLine() {
			return breakLine;
		}
		
		public String getCode() {
			return code;
		}
		
		public List<String> getList() {
			if (list == null) {
				list = new ArrayList<String>();
				for (int i=0;i<n;i++) {
					list.add("Break");
				}
			}
			return list;
		}
		
		public void setCode(String code) {
			System.out.println("setCode --> "  + code);
			this.code = code;
		}
	}
	
	public Bean3() {
		init();
	}
	
	public void init(ActionEvent event) {
		init();
	}
	
	public void test(ActionEvent event) {
		list.set(2, null);
	}
	
	public void init() {
		list.clear();
		for (int i=0; i<exter[counter];i++) {
			list.add(new Item("Item" + i, i == 5 || i ==2, inter[counter]));
		}
	}
	
	public void listener(ActionEvent event) {
		counter++;
		if (counter == exter.length) {
			counter=0;
		}
		init();
	}

	List<Item> list = new ArrayList<Item>();
	
	public List<Item>  getList() {
		return list;
	}
}
