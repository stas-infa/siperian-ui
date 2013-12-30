/*
 * CachedDataProvider.java		Date created: Oct 28, 2008
 * Last modified by: $Author: vsinelnikov $
 * $Revision: 262 $	$Date: 2008-11-05 20:05:29 +0300 (╤Ё, 05 эю  2008) $
 */

package org.richfaces.samples.extdt.model.task;

import java.util.List;
import java.util.Map;

import org.richfaces.model.DataProvider;
import org.richfaces.samples.extdt.Entity;

/**
 *This class provides task test data.
 * @author Sergey Rusak
 *
 */
public class DataProviderImpl implements DataProvider<Entity>{
    
    private static final long serialVersionUID = 1L;

    private Map<String,Object> filter;

    private Map<String,Object> sorting;
    
    private List<Entity> currentPage = null;
    
    private TaskFinder finder = new TaskFinder();

    public Entity getItemByKey(Object key) {
        if (key == null)
            return null;
        for (Entity entity : getCurrentPage()){
            if (entity.getId().equals(key))
                return entity;
        }
        return null;        
    }

    /* (non-Javadoc)
     * @see org.richfaces.model.DataProvider#getItemsByRange(int, int)
     */
    public List<Entity> getItemsByRange(int firstRow, int endRow) {  
        System.out.println("getItemsByRange(int firstRow, int endRow)"+firstRow+":"+endRow);
        return finder.findByFilter(filter, sorting, firstRow, endRow);
    }

    /* (non-Javadoc)
     * @see org.richfaces.model.DataProvider#getKey(java.lang.Object)
     */
    public Object getKey(Entity item) {
        return item.getId();
    }

    /* (non-Javadoc)
     * @see org.richfaces.model.DataProvider#getRowCount()
     */
    public int getRowCount() {         
        System.out.println("getRowCount()");
        return finder.countByFilter(filter);
    }

    public List<Entity> getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(List<Entity> currentPage) {
        this.currentPage = currentPage;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {       
        this.filter = filter;
    }
    
    public Map<String, Object> getSorting() {
		return sorting;
	}

	public void setSorting(Map<String, Object> sorting) {
		this.sorting = sorting;
	}

	public TaskFinder getFinder() {
        return finder;
    }

    public void setFinder(TaskFinder finder) {
        this.finder = finder;
    }
}
