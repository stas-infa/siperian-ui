/*
 * CachedDataProviderImpl.java		Date created: Nov 3, 2008
 * Last modified by: $Author: srusak $
 * $Revision: 264 $	$Date: 2008-11-05 20:31:25 +0300 (╤Ё, 05 эю  2008) $
 */

package org.richfaces.samples.extdt.model.task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.richfaces.samples.extdt.Entity;

/**
 * TODO Class description goes here.
 * @author Administrator
 *
 */
public class CachedDataProviderImpl extends DataProviderImpl {
    
    private static final int CACHE_SIZE = 4;//the default number of pages
    
    private Map<String,List<Entity>> cache = new HashMap<String, List<Entity>>();
    private Integer totalRecords = null;

    
    /* (non-Javadoc)
     * @see org.richfaces.model.DataProvider#getItemsByRange(int, int)
     */
    public List<Entity> getItemsByRange(int firstRow, int endRow) {
        
        String currentPageKey = new StringBuffer().append(firstRow).append(":").append(endRow).toString();
        //System.out.println("getItemsByRange(int firstRow, int endRow) current Page: "+currentPageKey);
        //printCacheState();

        List<Entity> cachedList = null;
        
        //cache lookup
        if(!cache.containsKey(currentPageKey)){            
            //reset cache if the number of the cached pages exceeds the maximum.
            if(cache.size()>CACHE_SIZE){
                resetCache();
            }
            Integer pageSize = endRow - firstRow;
            Integer previousPageFirstRow = (firstRow-pageSize)<0?firstRow:firstRow-pageSize;
            Integer nextPageEndRow = (endRow+pageSize)<getRowCount()?endRow+pageSize:getRowCount();
            
            String previousPageKey = new StringBuffer().append(previousPageFirstRow).append(":").append(firstRow).toString();
            String nextPageKey = new StringBuffer().append(endRow).append(":").append(nextPageEndRow).toString();
            
            //cachedList = getFinder().findByFilter(getFilter(), previousPageFirstRow, nextPageEndRow);
            cachedList = super.getItemsByRange(previousPageFirstRow,nextPageEndRow);
            
            cache.put(previousPageKey, cachedList.subList(0, firstRow-previousPageFirstRow));            
            cache.put(currentPageKey, cachedList.subList(firstRow-previousPageFirstRow, endRow-previousPageFirstRow));
            cache.put(nextPageKey, cachedList.subList(endRow-previousPageFirstRow, nextPageEndRow -previousPageFirstRow ));           
        }
        
        setCurrentPage(cache.get(currentPageKey));        
        //printCacheState();
        
        

        //return super.getItemsByRange(firstRow,endRow);
        return getCurrentPage();
    }
    
    
    /**
     * TODO Description goes here.
     */
    public void resetCache(){
        cache.clear();
        totalRecords = null;
    }
    
    public void printCacheState() {
        if (cache != null && CollectionUtils.isNotEmpty((cache.keySet()))) {
            for (Iterator itr = cache.keySet().iterator(); itr.hasNext();) {
                String pageKey = (String) itr.next();
                System.out.println(pageKey);
            }
        }
    }
    
    public void setFilter(Map<String, Object> filter) {
        if(MapUtils.isNotEmpty(getFilter()) && !getFilter().equals(filter))
            resetCache();
        super.setFilter(filter);
    }


    @Override
    public int getRowCount() {
        if(totalRecords==null){
            totalRecords = super.getRowCount();
        }         
        return totalRecords;
    }


    @Override
    public void setSorting(Map<String, Object> sorting) {
        if(MapUtils.isNotEmpty(getSorting()) && !getSorting().equals(sorting))
            resetCache();
        super.setSorting(sorting);
    }
    
    
}
