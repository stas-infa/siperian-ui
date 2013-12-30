/**
 * 
 */
package com.siperian.demo.tabPanel;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 * @author Andrey Markavtsov
 * 
 */
public class TabPanelBean {
	
	List<SearchResult> list = new ArrayList<SearchResult>();
	
	String markup = "dynamic";
	
	Integer maxTabWidth = 150;
	
	Integer maxTabsToLoad = 0;
	
	String selectedTab;
	
	List<String> newTabsId = new ArrayList<String>();
	
	public void clearNewTabs(ActionEvent event) {
		newTabsId.clear();
	}
	
	public String search() {
		int i = list.size();
		list.add(new SearchResult("Search" + (i + 1), "/richfaces/searchResultExample/examples/include.xhtml"));
		selectedTab = "Search" + (i + 1);
		newTabsId.add("searchTab" + i);
		return null;
	}

	public SelectItem[] getMarkups() {
		return new SelectItem[] { new SelectItem("dynamic", "Dynamic"),
				new SelectItem("static", "Static") };
	}

	public String getMarkup() {
		return markup;
	}

	public void setMarkup(String markup) {
		this.markup = markup;
	}

	public Integer getMaxTabWidth() {
		return maxTabWidth;
	}

	public void setMaxTabWidth(Integer maxTabWidth) {
		this.maxTabWidth = maxTabWidth;
	}

	/**
	 * @return the maxTabsToLoad
	 */
	public Integer getMaxTabsToLoad() {
		return maxTabsToLoad;
	}

	/**
	 * @param maxTabsToLoad the maxTabsToLoad to set
	 */
	public void setMaxTabsToLoad(Integer maxTabsToLoad) {
		this.maxTabsToLoad = maxTabsToLoad;
	}
	
	public void closeTab(String tabName) {
		SearchResult result = findTabByName(tabName);
		if (result != null) {
			list.set(list.indexOf(result), null);
		}
	}
	
	private SearchResult findTabByName(String tabName) {
		for (SearchResult result : list) {
			if (result != null && result.getName().equals(tabName)) {
				return result;
			}
		}
		return null;
	}
	
	public class SearchResult {
		String name;
		String include;
		String input;
		
		
		
		public SearchResult(String name, String include) {
			super();
			this.name = name;
			this.include = include;
		}
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the include
		 */
		public String getInclude() {
			return include;
		}
		/**
		 * @param include the include to set
		 */
		public void setInclude(String include) {
			this.include = include;
		}
		/**
		 * @return the input
		 */
		public String getInput() {
			return input;
		}
		/**
		 * @param input the input to set
		 */
		public void setInput(String input) {
			this.input = input;
		}
		
		
	}



	/**
	 * @return the list
	 */
	public List<SearchResult> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<SearchResult> list) {
		this.list = list;
	}

	/**
	 * @return the selectedTab
	 */
	public String getSelectedTab() {
		return selectedTab;
	}

	/**
	 * @param selectedTab the selectedTab to set
	 */
	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
		System.out.println("TabPanelBean.setSelectedTab() :" + selectedTab);
	}

	/**
	 * @return the newTabId
	 */
	public List<String> getNewTabsId() {
		return newTabsId;
	}

	/**
	 * @param newTabId the newTabId to set
	 */
	public void setNewTabsId(List<String> newTabsId) {
		this.newTabsId = newTabsId;
	}

}
