package com.siperian.demo.extendedDataTable.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.richfaces.model.DataProvider;

/**
 * @author pkawiak
 *
 */

public class DemoPatientProvider implements DataProvider<DemoPatient>{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7416469705086293847L;

	private static String allFirstNames[] = {
            "Aaron",        "Abbott",   "Abel",     "Abner",
            "Abraham",      "Adam",     "Addison",  "Adler",
            "Adley",        "Adrian",   "Aedan",    "Aiken",
            "Alan",         "Alastair", "Albern",   "Albert",
            "Albion",       "Alden",    "Aldis",    "Aldrich",
            "Alexander",    "Alfie",    "Alfred",   "Algernon",
            "Alston",       "Alton",    "Alvin",    "Ambrose",
            "Amery",        "Amos",     "Andrew",   "Angus",
            "Ansel",        "Anthony",  "Archer",   "Archibald",
            "Arlen",        "Arnold",   "Arthur",   "Arvel",
            "Atwater",      "Atwood",   "Aubrey",   "Austin",
            "Magorzata",   "Pawe‚",    "Piotr"
    };
    
    private static String allLastNames[] = {
            "Brown",        "Smith",        "Patel",
            "Jones",        "Williams",     "Johnson",
            "Taylor",       "Thomas",       "Roberts",
            "Khan",         "Lewis",        "Jackson",
            "Clarke",       "James",        "Phillips",
            "Wilson",       "Ali",          "Mason",
            "Davies",       "Rodriguez",    "Cox",
            "Alexander",    "Popioek",     "Buda",
            "Goawski",     "Kawiak",       "Mitchel"

    };
    
    private List<DemoPatient> items;
    private Integer itemsNumber;
    
    private static Random random = new Random();

    public DemoPatientProvider(Integer itemsNumber) {
        super();
        this.itemsNumber = itemsNumber;
    }
    
    private static Date generateRandomDate(){
        return new Date(Math.abs(random.nextLong()%(getCurrentDate().getTime())));
    }
    
    private static Date getCurrentDate() {
    	return new Date();
    }
    
    private static String generateRandomFirstName() {
        int rand = Math.abs(random.nextInt());
        return allFirstNames[rand%(allFirstNames.length)];
    }

    private static String generateRandomLastName() {
        int rand = Math.abs(random.nextInt());
        return allLastNames[rand%(allLastNames.length)];
    }
    
    /* (non-Javadoc)
     * @see org.richfaces.model.DataProvider#getItemByKey(java.lang.Object)
     */
    public DemoPatient getItemByKey(Object key) {
        if (key == null)
            return null;
        for (DemoPatient entity : getItems()){
            if (entity.getId().equals(key))
                return entity;
        }
        return null;
    }

    protected List<DemoPatient> getItems() {
        if (items == null){
            int l = itemsNumber;
            items = new ArrayList<DemoPatient>(l);
            for (int i=0;i<l;i++) {
                items.add(new DemoPatient(i,
                        generateRandomFirstName(),
                        generateRandomLastName(),
                        generateRandomDate(),
                        getCurrentDate()));
            }
                
        }
        return items;
    }
    
    /* (non-Javadoc)
     * @see org.richfaces.model.DataProvider#getItemsByRange(int, int)
     */
    public List<DemoPatient> getItemsByRange(int firstRow, int lastRow) {
        return getItems().subList(firstRow, lastRow);
    }

    /* (non-Javadoc)
     * @see org.richfaces.model.DataProvider#getKey(java.lang.Object)
     */
    public Object getKey(DemoPatient item) {
        return item.getId();
    }

    /* (non-Javadoc)
     * @see org.richfaces.model.DataProvider#getRowCount()
     */
    public int getRowCount() {
        return getItems().size();
    }

}