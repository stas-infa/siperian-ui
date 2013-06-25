/**
 *
 */

package com.exadel.siperian.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;
import javax.faces.model.DataModel;

import org.ajax4jsf.component.UIDataAdaptor;
import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.context.ContextInitParameters;
import org.ajax4jsf.event.AjaxEvent;
import org.ajax4jsf.javascript.ScriptUtils;
import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.SequenceRange;
import org.ajax4jsf.renderkit.AjaxChildrenRenderer;
import org.ajax4jsf.renderkit.RendererUtils.HTML;
import org.apache.commons.collections.iterators.IteratorChain;
import org.richfaces.component.Column;
import org.richfaces.component.Filterable;
import org.richfaces.component.Selectable;
import org.richfaces.component.Sortable2;
import org.richfaces.component.UIColumn;
import org.richfaces.component.UIDataTable;
import org.richfaces.event.extdt.ChangeColumnVisibilityEvent;
import org.richfaces.event.extdt.ColumnResizeEvent;
import org.richfaces.event.extdt.DragDropEvent;
import org.richfaces.event.extdt.ExtTableFilterEvent;
import org.richfaces.event.extdt.ExtTableSortEvent;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.ExtendedTableDataModifiableModel;
import org.richfaces.model.FilterField;
import org.richfaces.model.LocaleAware;
import org.richfaces.model.Modifiable;
import org.richfaces.model.ModifiableModel;
import org.richfaces.model.SortField2;

import com.exadel.siperian.model.TreeModifiableModel;
import com.exadel.siperian.renderkit.AbstractExtendedTableRenderer;
import com.exadel.siperian.renderkit.ExtendedTableHolder;

/**
 * JSF component class
 * 
 */
public abstract class UIExtendedDataTable extends UIDataTable implements Selectable, Filterable, Sortable2 {
	
    /**
     * COMPONENT_TYPE
     */
    public static final String COMPONENT_TYPE = "com.exadel.siperian.ExtendedDataTable";

    /**
     * COMPONENT_FAMILY
     */
    public static final String COMPONENT_FAMILY = "com.exadel.siperian.ExtendedDataTable";

    protected com.exadel.siperian.component.ExtendedDataTableState state;

    public abstract String getGroupColumnWidth(); 
    
    public abstract void setGroupColumnWidth(String width); 
    
    public static final String EDITING_CELL_VALIDATION_FAILED_ID_ATTR_NAME = "_EDITING_CELL_VALIDATION_FAILED_ID_ATTR_NAME";
    
    
    public String getCountOfForceSort() {
        return countOfForceSort;
    }

    public void setCountOfForceSort(String countOfForceSort) {
        this.countOfForceSort = countOfForceSort;
    }

    public abstract Object getActiveRowKey();
    
    private String countOfForceSort = String.valueOf(0);

    public abstract void setActiveRowKey(Object activeRowKey);

    public void broadcast(FacesEvent event) throws AbortProcessingException {
	super.broadcast(event);
	if (event instanceof AjaxEvent) {
	    AjaxContext.getCurrentInstance().addComponentToAjaxRender(this);
	} else if (event instanceof DragDropEvent) {
	    processDradDrop((DragDropEvent) event);
	} else if (event instanceof ChangeColumnVisibilityEvent) {
	    processChangeColumnVisibility((ChangeColumnVisibilityEvent) event);
	} else if (event instanceof ColumnResizeEvent) {
	    processColumnResize((ColumnResizeEvent) event);
	} else if (event instanceof ExtTableSortEvent) {
	    processSortingChange((ExtTableSortEvent) event);
	} else if (event instanceof ExtTableFilterEvent) {
	    processFilteringChange((ExtTableFilterEvent) event);
	}

    }



    public void queueEvent(FacesEvent event) {
	if (event.getSource() instanceof UIExtendedDataTable) {
	    if (event instanceof AjaxEvent) {
		event.setPhaseId(PhaseId.APPLY_REQUEST_VALUES);
	    } else if (event instanceof DragDropEvent) {
		new AjaxEvent(this).queue();
		event.setPhaseId(PhaseId.APPLY_REQUEST_VALUES);
	    } else if (event instanceof ChangeColumnVisibilityEvent) {
		new AjaxEvent(this).queue();
		event.setPhaseId(PhaseId.APPLY_REQUEST_VALUES);
	    } else if (event instanceof ColumnResizeEvent) {
		event.setPhaseId(PhaseId.RENDER_RESPONSE);
	    } else if (event instanceof ExtTableSortEvent) {
		new AjaxEvent(this).queue();
		event.setPhaseId(PhaseId.PROCESS_VALIDATIONS);
	    } else if (event instanceof ExtTableFilterEvent) {
		new AjaxEvent(this).queue();
		event.setPhaseId(PhaseId.PROCESS_VALIDATIONS);
	    }
	}
	
	super.queueEvent(event);

    }
    
    @Override
    protected void processUpdates(FacesContext faces, Object argument) {
    	// TODO Auto-generated method stub
    	super.processUpdates(faces, argument);
    }  
    
    private Object isGroupAjaxExpantion (FacesContext context) {
    	return context.getExternalContext().getRequestMap().get(getId() + AbstractExtendedTableRenderer.ROOT_ROW_AJAX_EXPANDED);
    }
    
    @Override
    public void encodeChildren(FacesContext context) throws IOException {
    	if (isGroupAjaxExpantion(context) != null) {
    		return;
    	}
    	
    	super.encodeChildren(context);
    }
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
    	
    	if (isGroupAjaxExpantion(context) != null) {
    		return;
    	}
   		super.encodeEnd(context);
    }
    
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
    	//groupExpandAjax
    	final Object rowKey = isGroupAjaxExpantion(context);
    	//addGroupColumn();
    	
    	Integer rowCounter = 0;
    	String rowCounterStr = context.getExternalContext().getRequestParameterMap().get("rowCounter");
    	if (rowCounterStr != null) {
    		rowCounter = Integer.parseInt(rowCounterStr);
    	}
    	
    	if (rowKey != null) {
    	
   		try {
    			
    			ExtendedTableHolder holder = new ExtendedTableHolder(this, getChildCount());
    			holder.setRowCounter(rowCounter);
    			
    			ResponseWriter writer = context.getResponseWriter();
    			writer.startElement("div", this);
    			writer.writeAttribute("style", "display: none", null);
    			writer.writeAttribute("id", getClientId(context) + "subRowsDiv", null);
    			writer.startElement("table", this);
    			
     			getExtendedDataModel().walk(context, (DataVisitor)getRenderer(context),
    					new SequenceRange() {
    						@Override
    						public int getFirstRow() {
    							return (Integer)rowKey;
    						}
    						
    						@Override
    						public int getRows() {
    							return 1;
    						}
    						
						}, holder);
    			writer.endElement("table");
    			writer.endElement("div");
    			return;
    		}finally {
    			setRowKey(null);
    		}
    	}
    	
    	super.encodeBegin(context);
    }
    

    public Iterator<UIColumn> getSortedColumns() {
	return new com.exadel.siperian.component.SortedColumnsIterator(this);
    }

    public Iterator<UIColumn> getChildColumns() {
	return new com.exadel.siperian.component.ExtendedTableColumnsIterator(this);
    }

    public void processDradDrop(DragDropEvent event) {
	String dragValue = event.getDragValue().toString();// dnd_drag_script
	String dropValue = event.getDropValue().toString();// dnd_drop_script

	ensureTableStateInitialized();
	state.changeColumnsOrder(dragValue, dropValue, event.isDropBefore());
	state.publishChanges(getFacesContext(), this);

	getFacesContext().renderResponse();
    }

    public List<UIComponent> getSortedChildren() {
	ensureTableStateInitialized();
	return state.sortColumns(getFacesContext(), super.getChildren());
    }

    public void ensureTableStateInitialized() {
	if (state == null) {
	    state = ExtendedDataTableState.getExtendedDataTableState(this);
	}
    }
    

    public void updateTableState() {
	state = ExtendedDataTableState.getExtendedDataTableState(this);
    }

    public void processChangeColumnVisibility(ChangeColumnVisibilityEvent event) {
	ensureTableStateInitialized();
	state.toggleColumnVisibility(this, event.getColumnId());
	state.publishChanges(getFacesContext(), this);

	getFacesContext().renderResponse();
    }// processChangeColumnVisibility

    public void processSortingChange(ExtTableSortEvent event) {
	DataModel dataModel = getDataModel();
	if (dataModel instanceof ExtendedTableDataModifiableModel<?>) {
	    ((ExtendedTableDataModifiableModel<?>) dataModel).resetSort();
	} else {
	    super.resetDataModel();
	}
//	getFacesContext().renderResponse();
	this.countOfForceSort = String.valueOf(1); 
	this.state.columnGroupingState.reset();
    }

    public void processFilteringChange(ExtTableFilterEvent event) {
	DataModel dataModel = getDataModel();
	if (dataModel instanceof ExtendedTableDataModifiableModel<?>) {
	    ((ExtendedTableDataModifiableModel<?>) dataModel).resetFilter();
	} else {
	    super.resetDataModel();
	}
//	resetGroupVisibilityState();
//	getFacesContext().renderResponse();
	this.state.columnGroupingState.reset();
    }
    
    public void processToggleRootRow(Integer key, boolean expanded) {
    	ValueExpression ve = getValueExpression("rootRowExpanded");
    	if (ve != null) {
    		Object orig = getRowKey();
    		try {
    			setRowKey(key);
    			
    			ve.setValue(getFacesContext().getELContext(), expanded);
    			
    		} finally {
				setRowKey(orig);
			}
    	}else {
    		state.setRootRowExpanded(key, expanded);
    		state.publishChanges(getFacesContext(), this);
    	}
    }
    
    
    public void processCellEditing(String key, UIColumn  column) {
    	//Object orig = getRowKey();
    	FacesContext context = getFacesContext();
    	ExtendedDataModel model = (ExtendedDataModel) getExtendedDataModel();
		try {
			//setRowKey(key);
			
			UIComponent editor = column.getFacet("editor");
			if (editor != null) {
				
				Object rowKey = getRowKeyConverter().getAsObject(context, this, key);
				
				model.setRowKey(rowKey);
				setupVariable(context, model, true);
				
				setActiveRowKey(rowKey);
				
				toggleEditors(context, true);
				
				editor.processDecodes(context);
				editor.processValidators(context);
				editor.processUpdates(context);
				
				Iterator<FacesMessage> it = context.getMessages();
				
				boolean falidationFailed = it.hasNext();
				
				if (falidationFailed) {
					Map<String, List<String>> m = new HashMap<String, List<String>>();
					List<String> errors = new ArrayList<String>();
					while (it.hasNext()) {
						FacesMessage message = it.next();
						errors.add(message.getSummary());
					}
					m.put("errors", errors);
					AjaxContext.getCurrentInstance(context).setResponseData(m);
					context.getExternalContext().getRequestMap().put(EDITING_CELL_VALIDATION_FAILED_ID_ATTR_NAME, column.getClientId(context));
				}else {
					
					//AjaxContext.getCurrentInstance(context).addComponentToAjaxRender(this);
					//context.getExternalContext().getRequestMap().put(getId() + AbstractExtendedTableRenderer.EDIT_ACTIONE_NAME, true);
					if (getCellEdited(column) == null) {
						state.processCellEditing(this, key, column.getId());
					}else {
						getCellEdited(column).setValue(context.getELContext(), true);
					}
					
					addRequestKey(rowKey);
					AjaxContext.getCurrentInstance(context).addComponentToAjaxRender(column);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			toggleEditors(context, false);
			setupVariable(context, getDataModel(), false);
		}
    }
    
    public boolean isColumnVisible(UIComponent column) {
	ensureTableStateInitialized();
	return state.isColumnVisible(column.getId());
    }// isColumnVisible
    
    public boolean isCellModified(UIColumn column) {
    	ensureTableStateInitialized();
    	String rowKey = getRowKeyConverter().getAsString(getFacesContext(), this, getRowKey());
    	if (getCellEdited(column) != null) {
    		boolean result = (Boolean)getCellEdited(column).getValue(getFacesContext().getELContext());
    		if (result) {
    			state.cellEditingState.hasModifiedData = state.cellEditingState.hasModifiedData || result; 
    		}
    		return result;
    	}
    	return state.isCellModified(rowKey, column.getId());
    }
    
    public ValueExpression getCellEdited(UIColumn column) {
    	return column.getValueExpression("edited");
    }
    
    public boolean hasModifiedData() {
    	ensureTableStateInitialized();
    	return state.cellEditingState.hasModifiedData();
    }

    public void processColumnResize(ColumnResizeEvent event) {
	ensureTableStateInitialized();
	state.changeColumnSize(this, event.getColumnWidths());
	state.publishChanges(getFacesContext(), this);

	getFacesContext().renderResponse();
    }// processChangeColumnVisibility

    public void processScrollPosition(String scrollPosition) {
	ensureTableStateInitialized();
	state.saveScrollPosition(scrollPosition);
    }

    public void processCellState(String cellState) {
	ensureTableStateInitialized();
	state.setCellPosition(cellState);
	state.publishChanges(getFacesContext(), this);
    }

    public Integer getScrollPosition() {
	ensureTableStateInitialized();
	return state.scrollPosition;
    }

    public String getCellPosition() {
	ensureTableStateInitialized();
	return state.getCellPosition();
    }

    public String getColumnSize(UIComponent column) {
	ensureTableStateInitialized();
	return state.getColumnSize(column);
    }
    
    private Boolean _isRootRowExpanded() {
    	ValueExpression ve = getValueExpression("rootRowExpanded");
    	if (ve != null) {
    		Object o = ve.getValue(getFacesContext().getELContext());
    		if (o != null && o instanceof Boolean) {
    			return (Boolean)o;
    		}
    	}
    	
    	return null;
    }
    
    public boolean isRootRowExpanded() {
    	Boolean b = _isRootRowExpanded();
    	if (b != null) {
    		return b;
    	}
    	ensureTableStateInitialized();
    	return state.isRootRowExpanded(getRowKey());
    }
    
   
    public boolean isGroupingOn() {
	//ensureTableStateInitialized();
	return getValueExpression("subRows") != null;
    }
        
    public UIColumn getColumn(String id) {
	for (Iterator<UIColumn> columns = getChildColumns(); columns.hasNext();) {
	    UIColumn child = columns.next();
	    if (id != null) {
		if (id.equalsIgnoreCase(child.getId())) {
		    return child;
		}
	    }
	}
	return null;
    }

    public Object saveState(FacesContext context) {
	Object values[] = new Object[2];
	values[0] = super.saveState(context);
	values[1] = state;
	return values;
    }

    public void restoreState(FacesContext context, Object state) {
	Object values[] = (Object[]) state;
	super.restoreState(context, values[0]);
	this.state = (ExtendedDataTableState) values[1];
	if (this.state != null) {
		this.state.restoreState();
	}
    }

    public int getVisibleColumnsCount() {
	int count = 0;
	for (Iterator<UIColumn> iter = getChildColumns(); iter.hasNext();) {
	    UIColumn column = iter.next();
	    if (column.isRendered())
		count++;
	}// for
	return count;
    }// getVisibleColumnnCount

	  @Override
	protected ExtendedDataModel createDataModel() {
		  List<FilterField> filterFields = new LinkedList<FilterField>();
			Map<String, SortField2> sortFieldsMap = new LinkedHashMap<String, SortField2>();
			List<UIComponent> list = getChildren();
			for (Iterator<UIComponent> iterator = list.iterator(); iterator.hasNext();) {
				UIComponent component = iterator.next();
				if (component instanceof org.richfaces.component.UIColumn) {
					org.richfaces.component.UIColumn column = (org.richfaces.component.UIColumn) component;
					FilterField filterField = column.getFilterField();
					Object filterableAttr = column.getAttributes().get("filterable");
					boolean filterable = true;
					if (filterableAttr != null) {
						if (filterableAttr instanceof Boolean) {
							filterable = (Boolean)filterableAttr;
						}else if (filterableAttr instanceof String) {
							filterable = Boolean.parseBoolean((String)filterableAttr);
						}
					}
					if (filterField != null && filterable) {
						filterFields.add(filterField);
					}
					SortField2 sortField = column.getSortField();
					if (sortField != null) {
						sortFieldsMap.put(component.getId(), sortField);
					}
				}
				
			}
			List<SortField2> sortFields = new LinkedList<SortField2>();
			Collection<Object> sortPriority = getSortPriority();
			if (sortPriority != null) {
				for (Object object : sortPriority) {
					if (object instanceof String) {
						String id = (String) object;
						SortField2 sortField = sortFieldsMap.get(id);
						if (sortField != null) {
							sortFields.add(sortField);
							sortFieldsMap.remove(id);
						}
					}
				}
			}
			sortFields.addAll(sortFieldsMap.values());
			ExtendedDataModel dataModel = (ExtendedDataModel)getDataModel();
			ValueExpression subRows = getValueExpression("subRows");
			if (subRows != null || (filterFields != null && !filterFields.isEmpty())
					|| (sortFields != null && !sortFields.isEmpty())) {
				Modifiable modifiable = null;
				if (subRows != null && !(dataModel instanceof TreeModifiableModel)) {
					TreeModifiableModel treeModifiableModel = new TreeModifiableModel(dataModel, getVar(), subRows, (String)getAttributes().get("rootVar"));
					dataModel = treeModifiableModel;
					modifiable = treeModifiableModel;					
				} else if (dataModel instanceof Modifiable) {
					modifiable = (Modifiable) dataModel;
				} else {
					ModifiableModel modifiableModel = new ModifiableModel(dataModel, getVar());
					dataModel = modifiableModel;
					modifiable = modifiableModel;
				}
				
				if (dataModel instanceof LocaleAware) {
					FacesContext facesContext = getFacesContext();
					if (ContextInitParameters.isDatatableUsesViewLocale(facesContext)) {
						UIViewRoot viewRoot = facesContext.getViewRoot();
						((LocaleAware) dataModel).setLocale(viewRoot.getLocale());
					}
				}
				
				modifiable.modify(filterFields, sortFields);
			}
			return dataModel;
	}

    /**
     * Original version of this method is defined in
     * {@link org.ajax4jsf.component.UIDataAdaptor} and is called before
     * RENDER_RESPONSE phase. In that version data model is reseted which causes
     * need to sort and filter every time component is rendered.
     */
     protected void resetDataModel() {
    	 if (!(getDataModel() instanceof ExtendedTableDataModel<?>)) { 
    		 super.resetDataModel(); 
    	 } 
     }

    @SuppressWarnings("unchecked")
    public Iterator<UIComponent> fixedChildren() {
	IteratorChain chain = new IteratorChain(getFacets().values().iterator());
	// RF-1248 Adding facets to both dataChildren and fixed children
	// To make both supports and header/footer work
	for (Iterator<UIComponent> i = getChildren().iterator(); i.hasNext();) {
	    UIComponent kid = (UIComponent) i.next();
	    if (kid instanceof Column || kid instanceof UIColumn) {
		chain.addIterator(kid.getFacets().values().iterator());
	    }
	}
	
	return chain;
    }
    
    
	public boolean isRootRow() {
		DataModel dataModel = getExtendedDataModel();
		return dataModel instanceof TreeModifiableModel && ((TreeModifiableModel) dataModel).isRootRow();
	}

    @Override
    public Converter getRowKeyConverter() {
    	Converter rowKeyConverter = super.getRowKeyConverter();
    	if (rowKeyConverter == null) {
    		rowKeyConverter = new Converter() {
				
				public String getAsString(FacesContext context, UIComponent component,
						Object value) {
					if (value instanceof Integer[]) {
						Integer[] integers = (Integer[]) value;
				        StringBuilder b = new StringBuilder();
				        for (Integer integer : integers) {
				        	 b.append(integer).append("_");
						}
				        return b.deleteCharAt(b.length() - 1).toString();
					} else {
						return value.toString();
					}
				}
				
				public Object getAsObject(FacesContext context, UIComponent component,
						String value) {
					try {
						String[] strings = value.split("_");
						if (strings.length > 1) {
							return new Integer[]{Integer.valueOf(strings[0]), Integer.valueOf(strings[1])};
						} else {
							return Integer.valueOf(value);
						}
					}catch (Exception e) {
						return null;
					}
				}
			};
    	}
    	return rowKeyConverter;
    }
    
    public boolean isRoowRootAjaxExpanded() {
    	Object ajaxMode = getAttributes().get(AbstractExtendedTableRenderer.ROOT_ROW_AJAX_EXPANDED);
    	if (ajaxMode == null) {
    		return false;
    	}
    	return Boolean.TRUE.toString().equalsIgnoreCase(ajaxMode.toString());
    }

    @Override
    public boolean invokeOnComponent(FacesContext context, String clientId,
    		ContextCallback callback) throws FacesException {
    	
    	
    	return super.invokeOnComponent(context, clientId, callback);
    }
    
    
    @Override
    protected AjaxChildrenRenderer getChildrenRenderer() {
    	
    	return new AjaxChildrenRenderer() {
			
			@Override
			protected Class<? extends UIComponent> getComponentClass() {
				return UIDataAdaptor.class;
			}
			
			@Override
			public void encodeAjaxComponent(FacesContext context,
					UIComponent component, String currentPath, Set<String> ids,
					Set<String> renderedAreas) throws IOException {
				
				if (component instanceof UIColumn) {
					
					String editingCellValidationFailedId = (String) context
							.getExternalContext().getRequestMap()
							.get(EDITING_CELL_VALIDATION_FAILED_ID_ATTR_NAME);
					
						if (ids.contains(currentPath + component.getId())) {
							
							if ((editingCellValidationFailedId != null && (currentPath + component.getId()).contains(editingCellValidationFailedId))) {
								ids.remove(currentPath + component.getId());
							}else {
								// Wrap into SPAN to pass TIDY filter
								ResponseWriter writer = context.getResponseWriter();
									writer.startElement(HTML.SPAN_ELEM, component);
									encodeCell(context, (UIColumn)component);
									writer.endElement(HTML.SPAN_ELEM);
								
								renderedAreas.add(component.getClientId(context));
								return;
							}
						}
					
				}
				
				super.encodeAjaxComponent(context, component, currentPath, ids, renderedAreas);

			}
		};
    	
    }
    
    private void encodeCell(FacesContext context, UIColumn c) throws IOException {
    	ExtendedTableHolder holder = new ExtendedTableHolder(this);
    	holder.setRootRowExpanded(isRootRowExpanded());
    	
    	Iterator<UIComponent> cols = columns();
    	int currentColumn = 0;
    	int i = 0;
    	while (cols.hasNext()) {
    		if (cols.next() == c) {
    			currentColumn = i;
    			break;
    		}
    		i++;
    	}
     	
    	((AbstractExtendedTableRenderer)getRenderer(context)).encodeCell(context, holder, c, false, currentColumn);
    	
    }
    
    public boolean isNotEditable(UIColumn column) {
    	if (column.getAttributes().get("editable") != null) {
    		Object editable = column.getAttributes().get("editable");
    		if (editable instanceof Boolean) {
    			return !(Boolean)editable;
    		}else if (editable instanceof String) {
    			return !Boolean.valueOf((String)editable);
    		}
    	}
    	
    	return false;
    }
    
    public void toggleEditors(FacesContext context, boolean enable) {
    	Iterator<UIComponent> kids = columns(); 
    	
    	while (kids.hasNext()) {
    		UIComponent c = kids.next();
    		if (c instanceof UIColumn) {
    			if (c.getFacet("editor") != null) {
    				c.getFacet("editor").setRendered(enable);
    			}
    		}
    	}
    }
}
