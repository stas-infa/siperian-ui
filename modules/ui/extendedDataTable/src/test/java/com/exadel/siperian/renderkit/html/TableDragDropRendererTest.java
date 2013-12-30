package com.exadel.siperian.renderkit.html;

import java.util.Iterator;

import org.ajax4jsf.tests.AbstractAjax4JsfTestCase;
import org.richfaces.component.UIColumn;

import com.exadel.siperian.renderkit.html.TableDragDropRenderer;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author mpopiolek
 * 
 */
public class TableDragDropRendererTest extends AbstractAjax4JsfTestCase {

    private TableDragDropRenderer renderer;

    private UIColumn column1;

    public TableDragDropRendererTest(String name) {
        super(name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ajax4jsf.tests.AbstractAjax4JsfTestCase#setUp()
     */
    public void setUp() throws Exception {
        super.setUp();
        column1 = (UIColumn) application
                .createComponent(UIColumn.COMPONENT_TYPE);
        column1.setId("columnId1");
        facesContext.getViewRoot().getChildren().add(column1);

        renderer = com.exadel.siperian.renderkit.html.TableDragDropRenderer.getInstance(facesContext);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.ajax4jsf.tests.AbstractAjax4JsfTestCase#tearDown()
     */
    public void tearDown() throws Exception {
        super.tearDown();
        renderer = null;
        column1 = null;
    }

    public void testEncodeChildScripts() {

    }

    public void testRenderDragSupport() {
        try {
            setupResponseWriter();
            renderer.renderDragSupport(column1, "dragSourceId", "indicatorId",
                    "dragLabel");
            HtmlPage page = processResponseWriter();

            Iterator elementIterator = page.getAllHtmlChildElements();

            HtmlElement script = null;

            while (elementIterator.hasNext()) {
                HtmlElement node = (HtmlElement) elementIterator.next();
                if (node.getNodeName().equalsIgnoreCase("script")) {
                    script = node;
                }
            }

            assertNotNull(script);

            String className = script.getAttributeValue("id");

            assertNotNull(className);

            assertEquals("columnId1:dnd_drag_script", className);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testRenderDropSupport() {

        try {
            setupResponseWriter();
            renderer.renderDropSupport(column1, "dropTargetId", true);
            HtmlPage page = processResponseWriter();

            Iterator elementIterator = page.getAllHtmlChildElements();

            HtmlElement script = null;

            while (elementIterator.hasNext()) {
                HtmlElement node = (HtmlElement) elementIterator.next();
                if (node.getNodeName().equalsIgnoreCase("script")) {
                    script = node;
                }
            }

            assertNotNull(script);

            String className = script.getAttributeValue("id");

            assertNotNull(className);

            assertEquals("columnId1:dnd_drop_script_left", className);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
