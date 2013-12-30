package com.siperian.demo.capitals;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.ajax4jsf.event.PushEventListener;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.richfaces.component.Dropzone;
import org.richfaces.event.DropEvent;
import org.xml.sax.SAXException;

public class CapitalsBean implements Runnable {
	
	static final String LONG_RUNNING_TAB_NAME = "_long_running_tab_name";
	static final String LONG_RUNNING_RERENDER_ID = "_long_running_rerender_id";
	
	Thread thread;
	
	private ArrayList<Capital> capitals = new ArrayList<Capital>();
	private ArrayList<Capital> droppedCapitals = new ArrayList<Capital>();
	private ArrayList<String> capitalsNames = new ArrayList<String>();
	private List<SelectItem> capitalsOptions = new ArrayList<SelectItem>();
	private String capital = "";
	private Capital current;
	private String selectedTab;
	
	// Long Running Context
	private PushEventListener listener;
	private String pushOncomplete;
	private String pushReRender;
	private List<LongRunningItem> longRunning = new ArrayList<LongRunningItem>();
	
	public class LongRunningItem {
		String reRenderId;
		String name;
		Long startDate;
		boolean completed;
		boolean processed;
		Long runTime = 10000L;
		
		public LongRunningItem(String id, String name) {
			startDate = new Date().getTime();
			completed = false;
			processed = false;
			this.reRenderId = id;
			this.name = name;
			setLongRunnig(name , true);
		}
		

		public boolean isFinished() {
			if (completed) {
				return true;
			}
			return new Date().getTime() - startDate > runTime;
		}
		
		public void complete() {
			setLongRunnig(name , false);
			completed = true;
		}
		
		public void process() {
			processed = true;
		}
		
		public String getReRenderId() {
			return reRenderId;
		}
		
		public String getName() {
			return name;
		}
		
		public boolean isProcessed() {
			return processed;
		}
		
		public boolean isCompleted() {
			return completed;
		}
	}
	
	public void setLongRunnig(String name, boolean value) {
		Capital capital = findTabByName(name);
		if (capital != null) {
			capital.setLongRunning(value);
		}
	}

	public List<Capital> autocomplete(Object suggest) {
		String pref = (String) suggest;
		ArrayList<Capital> result = new ArrayList<Capital>();

		Iterator<Capital> iterator = getCapitals().iterator();
		while (iterator.hasNext()) {
			Capital elem = ((Capital) iterator.next());
			if ((elem.getName() != null && elem.getName().toLowerCase()
					.indexOf(pref.toLowerCase()) == 0)
					|| "".equals(pref)) {
				result.add(elem);
			}
		}
		return result;
	}
	

	public void remove(String name) {
			if(name == null) 
				return;
			
			Capital currentCapital = findTabByName(name);
			
			if(currentCapital != null){
				int index = capitals.indexOf(currentCapital);
				capitals.set(index, null);
			}
	}
	
	private Capital findTabByName(String name) {
		Capital currentCapital=null;
		for (Capital capital : capitals) {
			if (capital != null && capital.getName().equals(name)) {
				currentCapital = capital;
				
			}
		}
		return currentCapital;

	}
	
	public void addEventProducer(EventListener listenet) {
		if (listener == null) {
			listener = (PushEventListener)listenet;
		}
	}
	
	public void addLongRunning(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String tabName = params.get(LONG_RUNNING_TAB_NAME);
		String id = params.get(LONG_RUNNING_RERENDER_ID);
			
		LongRunningItem item = new LongRunningItem(id, tabName);
		longRunning.add(item);
		
		if (thread == null) {
			thread = new Thread(this);
			thread.setDaemon(true);
			thread.start();
		}
	}
	
	synchronized void clearProcessdItems() {
		Iterator<LongRunningItem> it = longRunning.iterator();
		while (it.hasNext()) {
		LongRunningItem item = it.next();
			if (item.isProcessed()) {
				it.remove();
			}
		}
		

	}
	
	
	public void run() {
		while(thread != null) {
			try {
				
				clearProcessdItems();
			
				if (longRunning.size() == 0) {
					listener.onEvent(new EventObject(this));
					thread = null;
					return;
				}
					
				for (LongRunningItem item : longRunning) {
					if (item.isFinished()) {
						if (!item.isCompleted()) {
							listener.onEvent(new EventObject(this));
						}
						item.complete();
					}
				}
				
				Thread.sleep(5000);
				System.out.println("step++");
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void pushListener(ActionEvent event) {
		String reRender = "pushId, ";
		String oncompleteDef = "$('form:tapPanel').component.releseLongRunning('%1$s'); ";
		String oncomplete = "";
		Iterator<LongRunningItem> it = longRunning.iterator();
		boolean f = false;
		for (; it.hasNext();) {
			LongRunningItem item = it.next();
			if (item.isCompleted() && !item.isProcessed()) {
				reRender += item.getReRenderId() +  ",";
				oncomplete += String.format(oncompleteDef, item.getName()) + "; ";
				item.process();
				f = true;
			}
			this.pushOncomplete = oncomplete;
			this.pushReRender = reRender;
		}
		
		if (!f) {
			this.pushOncomplete = null;
		}
		clearProcessdItems();
		
		System.out.println("CapitalsBean.pushListener(). pushOncomplete=" + pushOncomplete + ", pushReRender=" + pushReRender);
	}
	
	public boolean isPushEnabled() {
		return longRunning.size() > 0;
	}
	
	
		
	public CapitalsBean() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL rulesUrl = loader.getResource("com/siperian/demo/capitals/capitals-rules.xml");
		Digester digester = DigesterLoader.createDigester(rulesUrl);
		digester.push(this);
		try {
			digester.parse(loader.getResourceAsStream("com/siperian/demo/capitals/capitals.xml"));
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
			capitalsOptions.add(new SelectItem(cap.getName(), cap.getState()));
		}
	}
	
	public void processDrop(DropEvent dropEvent) {
		Dropzone dropzone = (Dropzone) dropEvent.getComponent();
		if("capital".equals(dropzone.getDropValue())){
			Capital cap = (Capital) dropEvent.getDragValue();
			int index = capitals.indexOf(cap);
			if(index > -1){
				droppedCapitals.add(capitals.remove(index));
			}
		}
	}
	

	public String addCapital(Capital capital) {
		capitals.add(capital);
		return null;
	}

	public ArrayList<Capital> getCapitals() {
		return capitals;
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

	public Capital getCurrent() {
		return current;
	}

	public void setCurrent(Capital current) {
		this.current = current;
	}

	/**
	 * @return the droppedCapitals
	 */
	public ArrayList<Capital> getDroppedCapitals() {
		return droppedCapitals;
	}

	/**
	 * @param droppedCapitals the droppedCapitals to set
	 */
	public void setDroppedCapitals(ArrayList<Capital> droppedCapitals) {
		this.droppedCapitals = droppedCapitals;
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


	public String getPushOncomplete() {
		return pushOncomplete;
	}


	public void setPushOncomplete(String pushOncomplete) {
		this.pushOncomplete = pushOncomplete;
	}


	public String getPushReRender() {
		return pushReRender;
	}


	public void setPushReRender(String pushReRender) {
		this.pushReRender = pushReRender;
	}

	
}

