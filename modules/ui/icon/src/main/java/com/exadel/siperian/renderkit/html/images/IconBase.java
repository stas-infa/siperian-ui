/**
 * 
 */
package com.exadel.siperian.renderkit.html.images;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Date;

import javax.faces.context.FacesContext;

import org.ajax4jsf.resource.InternetResourceBuilder;
import org.ajax4jsf.resource.Java2Dresource;
import org.ajax4jsf.resource.PngRenderer;
import org.ajax4jsf.resource.ResourceContext;
import org.ajax4jsf.util.HtmlColor;
import org.ajax4jsf.util.Zipper2;
import org.richfaces.skin.Skin;
import org.richfaces.skin.SkinFactory;

/**
 * @author Alexandr Levkovsky
 * 
 */
public class IconBase extends Java2Dresource {

	private Dimension dimension = new Dimension(16, 16);

	public IconBase() {
		super();

		setRenderer(new PngRenderer());
		setLastModified(new Date(InternetResourceBuilder.getInstance()
				.getStartTime()));
	}

	public Dimension getDimensions(FacesContext facesContext, Object data) {
		return dimension;
	}

	protected Dimension getDimensions(ResourceContext resourceContext) {
		return dimension;
	}

	private final String NORMAL_FONT_COLOR = "Btn-NormalFontColor";
	private final String SELECTED_FONT_COLOR = "Btn-SelectedFontColor";
	private final String DISABLED_FONT_COLOR = "Btn-DisabledFontColor";

	protected Object getDataToStore(FacesContext context, Object data) {
		String normalFontColor = getSkinParameter(context, NORMAL_FONT_COLOR);
		String selectedFontColor = getSkinParameter(context,
				SELECTED_FONT_COLOR);
		String disabledFontColor = getSkinParameter(context,
				DISABLED_FONT_COLOR);

		byte[] ret = new byte[9];
		Zipper2 zipper2 = new Zipper2(ret);
		zipper2.addColor(HtmlColor.decode(normalFontColor).getRGB());
		zipper2.addColor(HtmlColor.decode(selectedFontColor).getRGB());
		zipper2.addColor(HtmlColor.decode(disabledFontColor).getRGB());

		return ret;
	}

	protected Object deserializeData(byte[] objectArray) {
		if (objectArray == null) {
			return null;
		}

		Object[] colors = new Object[3];
		Zipper2 z = new Zipper2(objectArray);
		colors[0] = z.nextColor();
		colors[1] = z.nextColor();
		colors[2] = z.nextColor();

		return colors;
	}

	private String getSkinParameter(FacesContext context, String parameterName) {
		Skin skin = SkinFactory.getInstance().getSkin(context);
		Skin defaultSkin = SkinFactory.getInstance().getDefaultSkin(context);
		String value = (String) skin.getParameter(context, parameterName);
		if (null == value || "".equals(value)) {
			value = (String) defaultSkin.getParameter(context, parameterName);
		}
		return value;

	}

	//TODO nick - do not create extra image, paint on graphics2d you have
	//TODO nick - why not Color[] ?
	protected BufferedImage paintArrow(Object[] colors, boolean disabled) {

		int w = 16;
		int h = 16;

		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = prepareImage(image);

		Color normalFontColor = (Color) colors[0];
		Color selectedFontColor = (Color) colors[1];
		Color disabledFontColor = (Color) colors[2];
		
//		Color normalFontColor = Color.BLUE;
//		Color selectedFontColor = Color.RED;
//		Color disabledFontColor = Color.GRAY;
		
		// Draw body
		Color bodyColor;
		if (disabled) {
			bodyColor = disabledFontColor;
		} else {
			bodyColor = normalFontColor;
		}		
		paintOneArrow(g2d, 3, 8, bodyColor, selectedFontColor);
		g2d.dispose();
		return image;
	}
	
	//TODO nick - do not create extra image, paint on graphics2d you have
	protected BufferedImage paintDoubleArrow(Object[] colors, boolean disabled) {

		int w = 16;
		int h = 16;

		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = prepareImage(image);

		Color normalFontColor = (Color) colors[0];
		Color selectedFontColor = (Color) colors[1];
		Color disabledFontColor = (Color) colors[2];
		
//		Color normalFontColor = Color.BLUE;
//		Color selectedFontColor = Color.RED;
//		Color disabledFontColor = Color.GRAY;
		
		// Draw body
		Color bodyColor;
		if (disabled) {
			bodyColor = disabledFontColor;
		} else {
			bodyColor = normalFontColor;
		}		
		paintOneArrow(g2d, 1, 8, bodyColor, selectedFontColor);
		paintOneArrow(g2d, 6, 8, bodyColor, selectedFontColor);
		g2d.dispose();
		return image;
	}
	
	private void paintOneArrow(Graphics2D g2d, int x, int y, Color main,
			Color second) {

		int w = 4;
		int h = 4;

//		g2d.setColor(second);
//		g2d.drawLine(x, y, x + w, y + h);
//		g2d.drawLine(x + 1, y - 1, x + 1 + w, y - 1 + h);
//		g2d.drawLine(x + 2, y - 2, x + 2 + w, y - 2 + h);
//		g2d.drawLine(x, y, x + w, y - h);
//		g2d.drawLine(x + 1, y + 1, x + 1 + w, y + 1 - h);
//		g2d.drawLine(x + 2, y + 2, x + 2 + w, y + 2 - h);

		g2d.setColor(main);
		g2d.drawLine(x + 1, y, x + 1 + w, y - 1 + h);
		g2d.drawLine(x + 1, y, x + 1 + w, y + 1 - h);

	}

	private Graphics2D prepareImage(BufferedImage image) {

		Graphics2D g2d = image.createGraphics();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);

		g2d.setStroke(new BasicStroke(1));

		return g2d;
	}
}
