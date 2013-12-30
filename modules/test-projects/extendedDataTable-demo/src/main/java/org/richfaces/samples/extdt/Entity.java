/*
 * Entity.java		Date created: Oct 28, 2008
 * Last modified by: $Author: srusak $
 * $Revision: 257 $	$Date: 2008-11-05 18:06:37 +0300 (╤Ё, 05 эю  2008) $
 */

package org.richfaces.samples.extdt;

import java.io.Serializable;

/**
 * Every entity should implement this interface.
 * @author Sergei Rusak
 *
 */
public interface Entity {
    
    public Serializable getId();

}
