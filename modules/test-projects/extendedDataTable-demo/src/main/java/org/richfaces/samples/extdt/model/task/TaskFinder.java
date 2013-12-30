/*
 * TaskFinder.java		Date created: Oct 28, 2008
 * Last modified by: $Author: vsinelnikov $
 * $Revision: 329 $	$Date: 2008-11-11 14:12:06 +0300 (┬Є, 11 эю  2008) $
 */

package org.richfaces.samples.extdt.model.task;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.richfaces.model.Ordering;
import org.richfaces.samples.extdt.Entity;

import sun.security.action.GetBooleanAction;

/**
 * TODO Class description goes here.
 * 
 * @author Administrator
 * 
 */
public class TaskFinder {

	private String[] priorities = { "Critical", "Major", "Minor" };
	private String[] statuses = { "Overdue", "Not Started", "On Time" };
	private String[] subjects = { "Review and Update Customer Record",
			"Review and Duplicate Matches", "Compose Daily Reports" };

	private List<Entity> items;

	private Integer itemsNumber = 100;

	private Random random = new Random();
	private long now = new Date().getTime();

	private final static class EntityComparator implements Comparator<Entity> {

		Map<String, Object> sortingRules;

		public EntityComparator(Map<String, Object> sortingRules) {
			this.sortingRules = sortingRules;
		}

		public int compare(Entity o1, Entity o2) {

			for (Map.Entry<String, Object> rule: sortingRules.entrySet()) {
				
				Ordering ordering = (Ordering) rule.getValue();
				
				if (ordering.equals(Ordering.UNSORTED)) {
					continue;
				}
				String field1 = getBeanPropertyValue(o1, rule.getKey());
				String field2 = getBeanPropertyValue(o2, rule.getKey());
				
				if (field1.equals(field2)) {
					continue;
				}
				
				int result = field1.compareTo(field2);
				
				if (result != 0) {
					if (ordering.equals(Ordering.ASCENDING)) {
						return result;
					} else {
						return result * (-1);
					}
				}
			}
			
			return 0;
		}
	}

	public Integer countByFilter(Map<String, Object> filter) {
		return (filter != null) ? findAllByFilter(filter, null).size()
				: getItems().size();
	}

	public List<Entity> findByFilter(Map<String, Object> filter,
			Map<String, Object> sorting, int start, int end) {
		List<Entity> result = (filter != null) ? findAllByFilter(filter,
				sorting) : getItems();

		if (CollectionUtils.isNotEmpty(result)) {
			if (start <= result.size()) {
				return result.subList(start, Math.min(result.size(), end));
			} else {
				return result.subList(0, Math.min(result.size(), end - start));
			}
		}
		return result;
	}

	private static String getBeanPropertyValue(Entity entity, String property) {
		try {
			return (String) BeanUtils.getProperty((Object) entity, property);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return null;
	}

	private List<Entity> findAllByFilter(Map<String, Object> filter,
			Map<String, Object> sorting) {
		List<Entity> result = new LinkedList<Entity>();

		// Forming list basing on criteria
		for (Entity entity : getItems()) {
			// List<String> fieldValues = new ArrayList<String>();
			boolean matchFilter = true;
			for (String filterKey : filter.keySet()) {
				if (filter.get(filterKey) instanceof String) {
					String filterValue = (String) filter.get(filterKey);
					matchFilter = StringUtils.containsIgnoreCase(
							getBeanPropertyValue(entity, filterValue),
							filterValue);
					if (!matchFilter) {
						break;
					}
				}
			}
			if (matchFilter) {
				result.add(entity);
			}
		}

		// Sorting here
		if (sorting != null) {
			System.out.println(sorting.size());
			Collections.sort(result, new EntityComparator(sorting));
		}

		return result;
	}

	protected List<Entity> getItems() {
		if (items == null) {
			int l = itemsNumber;
			items = new ArrayList<Entity>(l);
			for (int i = 0; i < l; i++) {
				items.add(new Task(i, priorities[Math.abs(random.nextInt())
						% (priorities.length)], subjects[Math.abs(random
						.nextInt())
						% (subjects.length)], statuses[Math.abs(random
						.nextInt())
						% (statuses.length)], new Date(Math.abs(random
						.nextLong()
						% now))));
			}

		}
		return items;
	}

}
