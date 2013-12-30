/**
 * 
 */
package com.exadel.siperian.renderkit.html.images;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import org.ajax4jsf.resource.ResourceContext;

/**
 * @author Alexandr Levkovsky
 *
 */
public class DoubleRightIcon extends IconBase {
	
	/* (non-Javadoc)
	 * @see org.ajax4jsf.resource.Java2Dresource#paint(org.ajax4jsf.resource.ResourceContext, java.awt.Graphics2D)
	 */
	@Override
	protected void paint(ResourceContext context, Graphics2D graphics) {
		Object[] stored = (Object[]) restoreData(context);
		if (stored != null) {
			BufferedImage arrow = paintDoubleArrow(stored, false);

			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			
			graphics.rotate(Math.toRadians(180), getDimensions(context).getWidth()/2, getDimensions(context).getHeight()/2);
			graphics.drawImage(arrow, 0, 1, null);
		}
	}
}
