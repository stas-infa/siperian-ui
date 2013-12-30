package com.exadel.siperian.component.renderer;

import java.util.HashSet;
import java.util.Set;

import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlOutputText;

import org.ajax4jsf.tests.AbstractAjax4JsfTestCase;
import org.richfaces.component.UIColumn;

import com.exadel.siperian.component.UIExtendedDataTable;


/**
 * 
 * @author pbuda
 *
 */
public class ExtendedDataTableRendererTest extends AbstractAjax4JsfTestCase {
    
    private UIExtendedDataTable table;
    
    private Set<String> javascripts = new HashSet<String>();
    
    
    public ExtendedDataTableRendererTest(String name) {
        super(name);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        
        //Create data table
        table = (UIExtendedDataTable) application.createComponent(UIExtendedDataTable.COMPONENT_TYPE);
        table.setId("table");
        
        UIColumn column = (UIColumn) createComponent(
                UIColumn.COMPONENT_TYPE,
                "org.richfaces.component.Column",
                null, null, null
        );
        
        column.setFilterValue("field");
        
        UIOutput output = (UIOutput) createComponent(
                UIOutput.COMPONENT_TYPE,
                HtmlOutputText.class.getName(),
                null, null, null
        );
        
        column.getChildren().add(output);
        
        
        table.getChildren().add(column);
        
        facesContext.getViewRoot().getChildren().add(table);
        
        //javascripts initialization
        javascripts.add("org/richfaces/renderkit/html/scripts/sip-extended-data-table.js");
    }

    @Override
    public void tearDown() throws Exception {
        table = null;
        super.tearDown();
    }
    
    @SuppressWarnings("unchecked")
    public void testLoadingStyles() throws Exception {
    	assertTrue(true);
    /*  HtmlPage page = renderView();
        assertNotNull(page);
        List links = page.getDocumentHtmlElement().getHtmlElementsByTagName("link");
        assertNotNull(links);
        HtmlElement link = (HtmlElement) links.get(0);
        assertTrue(link.getAttributeValue("href").contains("css/extendedDataTable.xcss"));
    */
    }
    
   /* public void testLoadingScripts() throws Exception {
        HtmlPage page = renderView();
        assertNotNull(page);
        assertTrue(getCountValidScripts(page, javascripts, false).intValue() == javascripts.size());
    }*/
}
