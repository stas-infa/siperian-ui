/**
 * 
 */
package com.exadel.siperian.model;

import java.io.Serializable;

/**
 * base interface for time line swin lane implementation 
 * @author Andrey Markavtsov
 *
 */
public interface TimeLineSwimLaneModel extends Serializable{
	
	/**
	 * @return swim lane id
	 */
	public String getSwimLaneId();
	
	/**
	 * @return swim lane css style class
	 */
	public String getSwimLaneStyleClass();
	
	/**
	 * @return swim lane name
	 */
	public String getSwimLaneName();

}
