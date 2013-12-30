package org.richfaces.samples.extdt.beans;

import org.richfaces.model.selection.SimpleSelection;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.samples.extdt.model.impl.DemoPatient;
import org.richfaces.samples.extdt.model.impl.DemoPatientProvider;
import org.richfaces.component.UIColumn;
import org.ajax4jsf.util.base64.Base64;

import com.exadel.siperian.component.ExtendedDataTableState;
import com.exadel.siperian.component.UIExtendedDataTable;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author pkawiak
 *
 */
public class ExtendedDataTableBB {
    
    private SimpleSelection selection = new SimpleSelection();
    private List<DemoPatient> selectedItems;
    private Comparator<DemoPatient> dateComparator;
    private String tableState = null;
    private ExtendedTableDataModel<DemoPatient> dataModel;
    private Integer patientsNumber = 100;
    
    private UIExtendedDataTable extendedDataTable;
    
    private String history;
    
    private boolean firtstNameColumnVisible;
    
    private boolean lastNameColumnVisible;
    
    private boolean dateColumnVisible;
    
    private static String FIRST_NAME = "firstName";
    
    private static String LAST_NAME = "lastName";
    
    private static String ADMISSION_DATE = "admissionDate";
    
    private String columnsOrder;
    
    public ExtendedDataTableBB() {
    }
    
    public ExtendedTableDataModel<DemoPatient> getDataModel(){
        if (dataModel == null){
            dataModel = new ExtendedTableDataModel<DemoPatient>(new DemoPatientProvider(patientsNumber));
        }
        return dataModel;
    }
    
    public SimpleSelection getSelection() {
        return selection;
    }

    public void setSelection(SimpleSelection selection) {
        this.selection = selection;
    }
    
    public String takeSelection() {
        getSelectedItems().clear();
        Iterator<Object> iterator = getSelection().getKeys();
        while (iterator.hasNext()){
            Object key = iterator.next();
            selectedItems.add(getDataModel().getObjectByKey(key));
        }
        return null;
    }
    
    public Integer getPatientsNumber() {
        return patientsNumber;
    }

    public void setPatientsNumber(Integer patientsNumber) {
        if (patientsNumber != this.patientsNumber) {
            dataModel = new ExtendedTableDataModel<DemoPatient>(new DemoPatientProvider(patientsNumber));
        }
        this.patientsNumber = patientsNumber;
    }

    public List<DemoPatient> getSelectedItems() {
        if (selectedItems == null){
            selectedItems = new ArrayList<DemoPatient>();
        }
        return selectedItems;
    }

    public void setSelectedItems(List<DemoPatient> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public String getTableState() {
        if (tableState == null){
            //try to get state from cookies
            Cookie[] cookies = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getCookies();
            if (cookies != null){
                for (Cookie c : cookies){
                    if (c.getName().equals("extdtSampleTabelState")){
                        try {
							tableState = new String(Base64.decodeBase64(c.getValue().getBytes("UTF-8")),"UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
                        break;
                    }
                }
            }
        }
        System.out.println("getTableState" + tableState);
        return tableState;
    }

    public void setTableState(String tableState) {
        try {
        	this.tableState = tableState;
             //save state in cookies
			Cookie stateCookie = new Cookie("extdtSampleTabelState", new String(Base64.encodeBase64(this.tableState.getBytes("UTF-8")),"UTF-8"));
			stateCookie.setMaxAge(30 * 24 * 60 * 60);
	        ((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).addCookie(stateCookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("setTableState" + tableState);
    }
    
    public Comparator<DemoPatient> getDateComparator(){
        if (dateComparator == null){
            dateComparator = new Comparator<DemoPatient>(){

                public int compare(DemoPatient o1, DemoPatient o2) {
                    return o1.getAdmissionDate().compareTo(o2.getAdmissionDate());
                }
                
            };
        }
        return dateComparator;
    }
    
    public void applyColumnsVisibility() {
    	ExtendedDataTableState extendedDataTableState = ExtendedDataTableState.getExtendedDataTableState(extendedDataTable);
    	for(Iterator<UIComponent> colIterator = extendedDataTable.columns(); colIterator.hasNext(); ) {
    		UIColumn column = (UIColumn) colIterator.next();
    		boolean togleColumn = false;
    		if(FIRST_NAME.equalsIgnoreCase(column.getId())) {
    			togleColumn = column.isVisible() != isFirtstNameColumnVisible();
    		} else if(LAST_NAME.equalsIgnoreCase(column.getId())) {
    			togleColumn = column.isVisible() != isLastNameColumnVisible();
    		} else if(ADMISSION_DATE.equalsIgnoreCase(column.getId())) {
    			togleColumn = column.isVisible() != isDateColumnVisible();
    		}
    		
    		if(togleColumn) {
    			extendedDataTableState.toggleColumnVisibility(extendedDataTable, column.getId());
    		}
    		
    	}
    	setTableState(extendedDataTableState.toString());
    }
    
    public SelectItem[] getPossibleOrders() {
    	SelectItem[] selectItems = new SelectItem[6];
    	String[] columnNames = {FIRST_NAME, LAST_NAME, ADMISSION_DATE};
    	int index = 0;
    	for(int i = 0; i < 3; i++) {
    		for(int j = 0; j < 3; j++) {
    			for(int k = 0; k < 3; k ++) {
    				if(i != j && j != k && i != k) {
    					selectItems[index++] = new SelectItem(columnNames[i] + ";" + columnNames[j] + ";" + columnNames[k]+";");
    				}
    			}
    		}
    	}
    	return selectItems;
    }
    
    public void applyColumnsOrder() {
        ExtendedDataTableState extendedDataTableState = ExtendedDataTableState.getExtendedDataTableState(extendedDataTable);
        String[] stateProperties = extendedDataTableState.toString().split(":");
    	stateProperties[0] = columnsOrder;
    	String tableState = "";
    	for(String property : stateProperties) {
    		tableState += property.toUpperCase() + ":";
    	}
    	setTableState(tableState);
    	
    	//publish changes
    	extendedDataTable.getValueExpression("tableState")
    		.setValue(FacesContext.getCurrentInstance().getELContext(), this.tableState);
		extendedDataTable.updateTableState();
    }

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public UIExtendedDataTable getExtendedDataTable() {
		return extendedDataTable;
	}

	public void setExtendedDataTable(UIExtendedDataTable extendedDataTable) {
		this.extendedDataTable = extendedDataTable;
	}

	public boolean isFirtstNameColumnVisible() {
		return firtstNameColumnVisible;
	}

	public void setFirtstNameColumnVisible(boolean firtstNameColumnVisible) {
		this.firtstNameColumnVisible = firtstNameColumnVisible;
	}

	public boolean isLastNameColumnVisible() {
		return lastNameColumnVisible;
	}

	public void setLastNameColumnVisible(boolean lastNameColumnVisible) {
		this.lastNameColumnVisible = lastNameColumnVisible;
	}

	public boolean isDateColumnVisible() {
		return dateColumnVisible;
	}

	public void setDateColumnVisible(boolean dateColumnVisible) {
		this.dateColumnVisible = dateColumnVisible;
	}

	public String getColumnsOrder() {
		return columnsOrder;
	}

	public void setColumnsOrder(String columnsOrder) {
		this.columnsOrder = columnsOrder;
	}
	
	public void clearState() {
	       this.tableState = "";
	       setTableState("");
	}

}
