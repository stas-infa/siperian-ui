/**
 * GENERATED FILE - DO NOT EDIT
 *
 */

package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent;
import org.richfaces.event.DataScrollerSource;

import java.io.IOException;

import javax.faces.*;
import javax.faces.el.*;

import javax.el.*;

import com.sun.facelets.*;
import com.sun.facelets.el.*;
import com.sun.facelets.tag.*;
import com.sun.facelets.tag.jsf.*;

public class VerticalScrollerListenerTagHandler extends TagHandler {

	private Class listenerType;

    private final TagAttribute type;

    private final TagAttribute binding;

	public VerticalScrollerListenerTagHandler(TagConfig config) {
	    super(config);
		this.binding = this.getAttribute("binding");
        this.type = this.getAttribute("type");
        if (type != null) {
            if (!type.isLiteral()) {
                throw new TagAttributeException(this.tag, this.type, "Must be literal");
            }
            try {
                this.listenerType = Class.forName(type.getValue());
            } catch (Exception e) {
                throw new TagAttributeException(this.tag, this.type, e);
            }
        }
  	}

  	 public void apply(FaceletContext ctx, UIComponent parent)
            throws IOException, FacesException, FaceletException, ELException {
        if (parent instanceof org.richfaces.event.DataScrollerSource) {
            // only process if parent was just created
            if (parent.getParent() == null) {
                org.richfaces.event.DataScrollerSource src = (org.richfaces.event.DataScrollerSource) parent;
                org.richfaces.event.DataScrollerListener listener = null;
                ValueExpression ve = null;
                if (this.binding != null) {
                    ve = this.binding.getValueExpression(ctx,
                            org.richfaces.event.DataScrollerListener.class);
                    listener = (org.richfaces.event.DataScrollerListener) ve.getValue(ctx);
                }
                if (listener == null) {
                    try {
                        listener = (org.richfaces.event.DataScrollerListener) listenerType.newInstance();
                    } catch (Exception e) {
                        throw new TagAttributeException(this.tag, this.type, e.getCause());
                    }
                    if (ve != null) {
                        ve.setValue(ctx, ve);
                    }
                }
				
                src.addScrollerListener(listener);
            }
        } else {
            throw new TagException(this.tag,
                    "Parent is not of type org.richfaces.event.DataScrollerSource, type is: " + parent);
        }
    }
  	
}
