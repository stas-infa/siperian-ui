package com.exadel.siperian.taglib;

import java.io.IOException;
import java.util.Iterator;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

import org.richfaces.component.UIColumn;
import org.richfaces.component.html.HtmlColumn;

import com.exadel.siperian.component.UIExtendedDataTable;
import com.sun.facelets.FaceletContext;
import com.sun.facelets.tag.jsf.ComponentConfig;
import com.sun.facelets.tag.jsf.ComponentHandler;

public class ExtendedDataTableTagHandlerBase extends ComponentHandler {

	public ExtendedDataTableTagHandlerBase(ComponentConfig config) {
		super(config);
	}
	
	
	 private void addGroupColumn(FaceletContext ctx, UIExtendedDataTable table) {
	    	
	    	if (!table.isGroupingOn()) {
	    		return;
	    	}
	    	
	    	Iterator<UIColumn> columns = table.getChildColumns();
	    	UIComponent groupColumn = null;
	    	while (columns.hasNext()) {
	    		UIColumn c = columns.next();
	    		if (c.getAttributes().get("GroupColumn") != null) {
	    			groupColumn = c;
	    			break;
	    		}
	    	}
	    	
	    	if (groupColumn == null) {
	    		String width = table.getGroupColumnWidth();
	    		
	    		HtmlColumn column = new HtmlColumn();
	    		//column.setTransient(true);
	    		column.setId("groupCol");
	    		column.setLabel("+/-");
	    		column.getAttributes().put("GroupColumn", true);
	    		column.setWidth(width);
	    		table.getChildren().add(0, column);
	    		
	    		groupColumn = column;
	    		
	    		if (table.getFacet("beforeGroupControl") != null) {
	    			groupColumn.getFacets().put("beforeGroupControl", table.getFacet("beforeGroupControl"));
				}
	    		if (table.getFacet("afterGroupControl") != null) {
	    			groupColumn.getFacets().put("afterGroupControl", table.getFacet("afterGroupControl"));
				}
	    	}
	    	
	    	if (groupColumn != null) {
	    		((HtmlColumn)groupColumn).setStyleClass("extdt-group-cell");
	    		((HtmlColumn)groupColumn).setHeaderClass("group-col");
	    	}
			
	    	table.getFacets().remove("beforeGroupControl");
	    	table.getFacets().remove("afterGroupControl");
	    	
	    	((UIExtendedDataTable)table).toggleEditors(ctx.getFacesContext(), false);
	    }
	
	@Override
	protected void applyNextHandler(FaceletContext ctx, UIComponent c)
			throws IOException, FacesException, ELException {
		super.applyNextHandler(ctx, c);
		
		if (c instanceof UIExtendedDataTable) {
			addGroupColumn(ctx, (UIExtendedDataTable)c);
		}
	}

}
