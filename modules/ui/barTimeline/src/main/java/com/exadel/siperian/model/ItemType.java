package com.exadel.siperian.model;

import java.io.Serializable;

public interface ItemType extends Serializable {
		
	public String getId();

	public String getName();

	public String getStyleClass();

	public void setStyleClass(String styleClass);

	public String getSelectedStyleClass();

	public void setSelectedStyleClass(String selectedStyleClass);

	public String getHoverStyleClass();

	public void setHoverStyleClass(String hoverStyleClass);

	public String getHighlightStyleClass();

	public void setHighlightStyleClass(String highlightStyleClass);

	public String getTooltipStyleClass();

	public void setTooltipStyleClass(String tooltipStyleClass);
	
}
