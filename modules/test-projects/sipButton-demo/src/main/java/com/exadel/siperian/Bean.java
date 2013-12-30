/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package com.exadel.siperian;

import javax.faces.event.ActionEvent;

/**
 * Test bean
 */
public class Bean {

    private String value = "5";
    private String action = "";
    private String actionListener = "";

    /**
     * Gets value of value field.
     * @return value of value field
     */
    public String getValue() {
        return value;
    }

    /**
     * Set a new value for value field.
     * @param value a new value for value field
     */
    public void setValue(String value) {
        this.value = value;
    }

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

    public void actionListener(ActionEvent event) {
        setActionListener("actionListener");
    }

    public void action() {
        setAction("action");
    }
}