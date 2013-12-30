package org.richfaces.datatable;

import java.io.Serializable;

public class Capital implements Serializable {
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}
	/**
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}
	/**
	 * @return the buget
	 */
	public double getBuget() {
		return buget;
	}
	/**
	 * @param buget the buget to set
	 */
	public void setBuget(double buget) {
		this.buget = buget;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -1042449580199397136L;
	private boolean checked=false;
	private String name;
	private String state;
	private String timeZone;
	private int population;
	private double buget;
	private String description;
	
	private final static String FILE_EXT = ".gif";
	public Capital() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	private String stateNameToFileName() {
		return state.replaceAll("\\s", "").toLowerCase();
	}
	
	public String getStateFlag() {
		return "/images/capitals/" + stateNameToFileName() + FILE_EXT;
	}
	
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
