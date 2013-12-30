/*
 * ModifiableDataModel.java		Date created: Nov 3, 2008
 * Last modified by: $Author: srusak $
 * $Revision: 410 $	$Date: 2008-11-14 18:31:50 +0300 (╧Є, 14 эю  2008) $
 */

package com.siperian.demo.extendedDataTable.model.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.model.ExtendedFilterField;
import org.richfaces.model.FilterField;
import org.richfaces.model.Modifiable;
import org.richfaces.model.SortField2;

import com.siperian.demo.common.Entity;

/**
 * TODO Class description goes here.
 * @author Administrator
 *
 */
public class ModifiableDataModel extends ExtendedDataModel implements Modifiable  {
    
    private Object rowKey;
    private Integer rowCount;
    private DataProviderImpl dataProvider;
    
    private List<Object> wrappedKeys = null;
    private Map<Object, Entity> wrappedData = new HashMap<Object, Entity>();
    private Integer rowIndex;
    private Integer firstRow = 0;
    private Integer endRow = 0;
    
/*    private boolean sortNeeded = true;
    private boolean filterNeeded = true;*/
    
    /* (non-Javadoc)
     * @see javax.faces.model.DataModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        if (rowCount == null) {
            rowCount = new Integer(dataProvider.getRowCount());
        } else {
            return rowCount.intValue();
        }
        return rowCount.intValue();
        //return dataProvider.getRowCount();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.faces.model.DataModel#getRowData()
     */
    @Override
    public Object getRowData() {
        if (rowKey == null) {
            return null;
        } else {
            return  getObjectByKey(rowKey);
        }
    }

    /* (non-Javadoc)
     * @see javax.faces.model.DataModel#getRowIndex()
     */
    @Override
    public int getRowIndex() {
        return rowIndex.intValue();
    }

    /* (non-Javadoc)
     * @see javax.faces.model.DataModel#getWrappedData()
     */
    @Override
    public Object getWrappedData() {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see javax.faces.model.DataModel#isRowAvailable()
     */
    @Override
    public boolean isRowAvailable() {
        return getRowData() != null;
    }

    /* (non-Javadoc)
     * @see javax.faces.model.DataModel#setRowIndex(int)
     */
    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
        
    }

    /* (non-Javadoc)
     * @see javax.faces.model.DataModel#setWrappedData(java.lang.Object)
     */
    @Override
    public void setWrappedData(Object data) {
        throw new UnsupportedOperationException();
        
    }

    /* (non-Javadoc)
     * @see org.richfaces.model.Modifiable#modify(java.util.List, java.util.List)
     */
    public void modify(List<FilterField> filterFields, List<SortField2> sortFields) {
        wrappedKeys = null;
        rowCount = null;
        //int endRow = firstRow + numberOfRows;
        Map<String, Object> filter = new HashMap<String, Object>();
        for (FilterField filterField : filterFields) {            
            filter.put(filterField.getExpression().getExpressionString().split("\\.")[1].replace("}", ""),                    
                    ((ExtendedFilterField)filterField).getFilterValue());
        }

        Map<String, Object> sorting = new HashMap<String, Object>();
        
        //ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        for (SortField2 sortField : sortFields) {
        	sorting.put(sortField.getExpression().getExpressionString().split("\\.")[1].replace("}", ""),                    
        			sortField.getOrdering());
            
/*        	sorting.put((String)((ValueExpression)sortField.getExpression()).getValue(elContext),                    
        			sortField.getOrdering());
*/        }

        dataProvider.setFilter(filter);
        dataProvider.setSorting(sorting);
        
       // for (Entity item : loadData(firstRow, endRow)) {
       //     Object key = item.getId();
       //     wrappedKeys.add(key);
       //     wrappedData.put(key, item);            
       // }
        
    }
    
    public Entity getObjectByKey(Object key) {
        Entity t = wrappedData.get(key);
        if (t == null){
            throw new NullPointerException();
/*            t = dataProvider.getItemByKey(key);
            wrappedData.put(key, t);*/
        }
/*        Map map = new HashMap<String, String>();
        for (Field field : Task.class.getDeclaredFields()) {
            try {
                map.put(field.getName(), BeanUtils.getProperty(t, field.getName()));
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }        
        return map;
*/
        return t;
    }

    /* (non-Javadoc)
     * @see org.ajax4jsf.model.ExtendedDataModel#getRowKey()
     */
    @Override
    public Object getRowKey() {
        return rowKey;
    }

    /* (non-Javadoc)
     * @see org.ajax4jsf.model.ExtendedDataModel#setRowKey(java.lang.Object)
     */
    @Override
    public void setRowKey(Object key) {
        rowKey = key;        
    }

    /* (non-Javadoc)
     * @see org.ajax4jsf.model.ExtendedDataModel#walk(javax.faces.context.FacesContext, org.ajax4jsf.model.DataVisitor, org.ajax4jsf.model.Range, java.lang.Object)
     */
    @Override
    public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) throws IOException {
        int rowC = getRowCount();
        firstRow = ((SequenceRange) range).getFirstRow();
        int numberOfRows = ((SequenceRange) range).getRows();
        if (numberOfRows <= 0) {
            numberOfRows = rowC;
        }
        if (wrappedKeys != null) { // Is this serialized model
            // Here we just ignore current Rage and use whatever data was saved in serialized model. 
            // Such approach uses much more getByPk() operations, instead of just one request by range.
            // Concrete case may be different from that, so you can just load data from data provider by range.
            // We are using wrappedKeys list only to preserve actual order of items.
            for (Object key : wrappedKeys) {
                setRowKey(key);
                visitor.process(context, key, argument);
            }
        } else { // if not serialized, than we request data from data provider
            wrappedKeys = new ArrayList<Object>();
            endRow = firstRow + numberOfRows;
            if (endRow > rowC){
                endRow = rowC; 
            }
            for (Entity item : loadData(firstRow, endRow)) {
                Object key = getKey(item);
                wrappedKeys.add(key);
                wrappedData.put(key, item);
                visitor.process(context, key, argument);
            }
        }
        
/*        /////////////////////////////////////
        int firstRow = ((SequenceRange)range).getFirstRow();
        int numberOfRows = ((SequenceRange)range).getRows();
        if (detached) { // Is this serialized model
// Here we just ignore current Rage and use whatever data was saved in serialized model. 
// Such approach uses much more getByPk() operations, instead of just one request by range.
// Concrete case may be different from that, so you can just load data from data provider by range.
// We are using wrappedKeys list only to preserve actual order of items.
            for (Integer key:wrappedKeys) {
                setRowKey(key);
                visitor.process(context, key, argument);
            }
        } else { // if not serialized, than we request data from data provider
            wrappedKeys = new ArrayList<Integer>();
            for (AuctionItem item:dataProvider.getItemsByrange(new Integer(firstRow), numberOfRows, null, true)) {
                wrappedKeys.add(item.getPk());
                wrappedData.put(item.getPk(), item);
                visitor.process(context, item.getPk(), argument);
            }
        }   */     
        
    }    
    
    protected List<Entity> loadData(int startRow, int endRow) {
        if (startRow < 0){
            startRow = 0;
            throw new IllegalArgumentException("Illegal start index value: " + startRow);
        }
        int rowCount = getRowCount();
        if (endRow > rowCount){
            endRow = rowCount;
            throw new IllegalArgumentException("Illegal end index value: " + endRow);
        }
        //load all from provider and get sublist        
        return dataProvider.getItemsByRange(startRow, endRow);
        //return dataProvider.getItemsByRange(0, rowCount).subList(startRow, endRow);
    }//loadData
    public Object getKey(Entity o) {
        return o.getId();
    }

    public DataProviderImpl getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(DataProviderImpl dataProvider) {
        this.dataProvider = dataProvider;
    }

}
