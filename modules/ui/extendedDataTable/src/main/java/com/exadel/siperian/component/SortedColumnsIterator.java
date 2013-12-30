/**
 * 
 */
package com.exadel.siperian.component;


/**
 * Columns iterator that returns column in sort order.
 * 
 * @see UIExtendedDataTable#getSortedChildren()
 * @author pawelgo
 * 
 */
public class SortedColumnsIterator extends com.exadel.siperian.component.ExtendedTableColumnsIterator {

    /**
     * Creates iterator for table.
     * 
     * @param dataTable
     *            table for which iterator is created
     */
    public SortedColumnsIterator(com.exadel.siperian.component.UIExtendedDataTable dataTable) {
        super(dataTable);
        childrenIterator = dataTable.getSortedChildren().iterator();
    }

}
