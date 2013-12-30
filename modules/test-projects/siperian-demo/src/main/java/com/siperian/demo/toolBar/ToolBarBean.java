package com.siperian.demo.toolBar;

import javax.faces.component.UICommand;
import javax.faces.event.ActionEvent;

public class ToolBarBean {

    private String action;
    
    private boolean editGroupDisabled;
    
    private boolean deleteGroupHighlighted;

    /**
     * Gets value of action field.
     * @return value of action field
     */
    public String getAction() {
        return action;
    }

    /**
     * Set a new value for action field.
     * @param action a new value for action field
     */
    public void setAction(String action) {
        this.action = action;
    }

    private String actionListener;

    /**
     * Gets value of actionListener field.
     * @return value of actionListener field
     */
    public String getActionListener() {
        return actionListener;
    }

    /**
     * Set a new value for actionListener field.
     * @param actionListener a new value for actionListener field
     */
    public void setActionListener(String actionListener) {
        this.actionListener = actionListener;
    }

    public String create() {
        setAction("new");
        return null;
    }

    public String edit() {
        setAction("edit");
        return null;
    }

    public String delete() {
        setAction("delete");
        return null;
    }

    public void actionListener(ActionEvent event) {
        setActionListener(String.valueOf(((UICommand) event.getComponent()).getId()));
    }

	/**
	 * @return the editGroupDisabled
	 */
	public boolean isEditGroupDisabled() {
		return editGroupDisabled;
	}

	/**
	 * @param editGroupDisabled the editGroupDisabled to set
	 */
	public void setEditGroupDisabled(boolean editGroupDisabled) {
		this.editGroupDisabled = editGroupDisabled;
	}

	public boolean isDeleteGroupHighlighted() {
		return deleteGroupHighlighted;
	}

	public void setDeleteGroupHighlighted(boolean deleteGroupHighlighted) {
		this.deleteGroupHighlighted = deleteGroupHighlighted;
	}
    
    
}
