/**
 * 
 */
package com.exadel.siperian.component;



/**
 * @author Alexandr Levkovsky
 *
 */
public interface TabBase {

    public Object getName();
    
    public void setName(Object name);
    
    public boolean isDisabled();

    public void setDisabled(boolean disabled);

    public String getLabel();

    public void setLabel(String newvalue);

    public String getLabelWidth();

    public void setLabelWidth(String newvalue);

    public String getTitle();

    public void setTitle(String newvalue);

    public String getSwitchTypeOrDefault();
    
    public String getSwitchType();
    
    public void setSwitchType(String newvalue);
    
    public UITabPanel getPane();

}
