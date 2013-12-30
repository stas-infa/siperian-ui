/**
 * 
 */
package com.exadel.siperian;

import java.util.ArrayList;
import java.util.List;

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
	
	
	public String search() {
		int i = list.size();
		list.add(new SearchResult("Search" + (i + 1), "/pages/include.xhtml"));
		list.add(new SearchResult("Search" + (i + 2), "/pages/include.xhtml"));
		selectedTab = "Search" + (i + 1);
		selectedTab = "Search" + (i + 2);
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
	
	
	
	public class SearchResult {
		String name;
		String include;
		
		
		
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
	}

}
