package com.exadel.siperian.renderkit;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.renderkit.TabClassBuilder;

import com.exadel.siperian.component.UITabPanel;

public class TabPanelClassBuilder implements Serializable{
	
	private static final long serialVersionUID = -3813625397263121729L;

	private static final Log log = LogFactory.getLog(TabClassBuilder.class); 
	
	static final String MARKUP_TYPE_ATTRIBUTE_NAME = "markupTemplate";
	
	TabPanelType panelType;
	
	public TabPanelClassBuilder(UITabPanel panel) {
		String markup = (String)panel.getAttributes().get(MARKUP_TYPE_ATTRIBUTE_NAME);
		panelType = getPanelType(markup);
	}
	
	private TabPanelType getPanelType(String markup) {
		TabPanelType type = null;
		try {
			type = TabPanelType.valueOf(markup.toUpperCase());			
		}catch (Exception e) {
			log.error("Type of TabPanel markup has not been detected");
		}
		return type;
	}
	
	public boolean isDynamic() {
		return panelType == null || panelType == TabPanelType.DYNAMIC;
	}
	
	public String getPanelClass() {
		//TODO nick - what in "spn_panel_container" shows it is dynamic and in "sip-static-pnl-searchContainer"
		//	   that it is not
		return isDynamic() ? "spn_panel_container" : "sip-static-pnl-searchContainer";
	}
	
	public String getHeadersContainerClass() {
		//TODO nick - -//-
		return isDynamic() ? "tabsContainer" : "sip-static-pnl-searchTabsContainer";
	}
	
	public String getScrollContainerClass() {
		//TODO nick - -//-
		return isDynamic() ? "tabs" : "sip-static-pnl-tabsContainerScroll";
	}
	
	public String getActiveTabClass() {
		//TODO nick - -//-
		return isDynamic() ? "containerActive" : "sip-static-pnl-tabContainerActive";
	}
	
	public String getInactiveTabClass() {
		//TODO nick - -//-
		return isDynamic() ? "containerInActive" : "sip-static-pnl-tabContainerInactive";
	}

	public String getTabContentClass() {
		//TODO nick - -//-
		return isDynamic() ? "spn_content" : "sip-static-pnl-contentContainer";
	}

	public String getTabContentInnerClass() {
		//TODO nick - -//-
		return isDynamic() ? "" : "sip-static-pnl-contentBackgroundPosition";
	}
	
	public String getTabContextArrowClass() {
		//TODO nick - -//-
		return isDynamic() ? "contextArrowRight" : "sip-static-pnl-contextArrowRight";
	}

	
}
