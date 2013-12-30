package org.richfaces.datatable;

import java.io.IOException;
import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.faces.FacesException;
import javax.faces.model.SelectItem;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.richfaces.event.DropEvent;
import org.xml.sax.SAXException;

public class CapitalsBean {
	private ArrayList<Capital> capitals = new ArrayList<Capital>();
	private ArrayList<String> capitalsNames = new ArrayList<String>();
	private List<SelectItem> capitalsOptions = new ArrayList<SelectItem>();
	private List<Column> availableColumns = new ArrayList<Column>(6);
	private List<Column> visibleColumns = new ArrayList<Column>(6);
	private int[] columnWidths = {100,100,100,100,100,100};

    private String capital = ""; 
	
	public List<Capital> autocomplete(Object suggest) {
        String pref = (String)suggest;
        ArrayList<Capital> result = new ArrayList<Capital>();

        Iterator<Capital> iterator = getCapitals().iterator();
        while (iterator.hasNext()) {
            Capital elem = ((Capital) iterator.next());
            if ((elem.getName() != null && elem.getName().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref))
            {
                result.add(elem);
            }
        }
        return result;
    }
	
	
	public void reorder(DropEvent event){
		Integer dragValue = (Integer) event.getDragValue();
		Integer dropValue = (Integer) event.getDropValue();
		if(!dragValue.equals(dropValue)){
			List<Column> visibleColumns = getVisibleColumns();
			// Get and remove moved column
			int dragColumn = visibleColumns.indexOf(new Column(dragValue));
			Column oldColumn = visibleColumns.remove(dragColumn);
			// Insert moved column before target.
			int targetColumn = visibleColumns.indexOf(new Column(dropValue));
			visibleColumns.add(targetColumn, oldColumn);
		}
	}
    
	public CapitalsBean() {
		URL rulesUrl = getClass().getResource("capitals-rules.xml");
		Digester digester =	DigesterLoader.createDigester(rulesUrl);
		digester.push(this);
		try {
			digester.parse(getClass().getResourceAsStream("capitals.xml"));
		} catch (IOException e) {
			throw new FacesException(e);
		} catch (SAXException e) {
			throw new FacesException(e);
		}
		capitalsNames.clear();
		for (Capital cap : capitals) {
			capitalsNames.add(cap.getName());
		}
		capitalsOptions.clear();
		for (Capital cap : capitals) {
			capitalsOptions.add(new SelectItem(cap.getName(),cap.getState()));
		}
		
		for(int i = 0;i<4;i++){
			availableColumns.add(new Column(i));
		}
		for(int i = 4;i<6;i++){
			visibleColumns.add(new Column(i));
		}
	}
	
	public String addCapital(Capital capital) {
		capitals.add(capital);
		return null;
	}
	
	public ArrayList<Capital> getCapitals() {
		return capitals;
	}
	
	public List<CapitalWrapper> getCapitalWrappers() {
		return new AbstractList<CapitalWrapper>(){

			@Override
			public CapitalWrapper get(int index) {
				return new CapitalWrapper(getCapitals().get(index));
			}

			@Override
			public int size() {
				return getCapitals().size();
			}

			
		};
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public List<SelectItem> getCapitalsOptions() {
		return capitalsOptions;
	}

	public ArrayList<String> getCapitalsNames() {
		return capitalsNames;
	}

	/**
	 * @return the availableColumns
	 */
	public List<Column> getAvailableColumns() {
		return availableColumns;
	}

	/**
	 * @param availableColumns the availableColumns to set
	 */
	public void setAvailableColumns(List<Column> availableColumns) {
		this.availableColumns = availableColumns;
	}

	/**
	 * @return the visibleColumns
	 */
	public List<Column> getVisibleColumns() {
		return visibleColumns;
	}

	/**
	 * @param visibleColumns the visibleColumns to set
	 */
	public void setVisibleColumns(List<Column> visibleColumns) {
		this.visibleColumns = visibleColumns;
	}


	/**
	 * @return the columnWidths
	 */
	public int[] getColumnWidths() {
		return columnWidths;
	}

}
