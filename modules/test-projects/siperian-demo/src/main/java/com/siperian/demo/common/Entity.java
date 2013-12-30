/*
 * Entity.java		Date created: Oct 28, 2008
 * Last modified by: $Author: srusak $
 * $Revision: 279 $	$Date: 2008-11-06 15:46:16 +0300 (╫Є, 06 эю  2008) $
 */

package com.siperian.demo.common;

import java.io.Serializable;

/**
 * Every entity should implement this interface.
 * @author Sergei Rusak
 *
 */
public interface Entity {
    
    public Serializable getId();

}
