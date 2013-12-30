

package com.siperian.demo.user;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.event.ValueChangeEvent;


public class UserBean {
    private String name = "";

    private java.lang.Integer screenWidth;
    
    private static String[] TEST_DATA1 = new String[]{"Masha","Malaisia","Maldivi","Maldova","Maldovanka"};

    private java.lang.Integer screenHeight;

    private String job;

    private String address;

    private String city;

    private String zip;

    private Integer age;
    
    
   private List<UserBean> comboboxItems = new ArrayList<UserBean>();

    private boolean opened = false;

    private String comboboxValue = null;

    private List<Locale> locales = new ArrayList<Locale>();

    private String reRenderList = "panel";

    public List<UserBean> getComboboxItems() {
        if(comboboxItems.size() == 0){
            for(int i =0 ; i < 1 ; i ++ ){
                comboboxItems.add(new UserBean());
            }
        }
        return comboboxItems;
    }

    public void setComboboxItems(List<UserBean> comboboxItems) {
        this.comboboxItems = comboboxItems;
    }

    public String getReRenderList() {
        return reRenderList;
    }

    public void setReRenderList(String reRenderList) {
        this.reRenderList = reRenderList;
    }

    public UserBean() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.lang.Integer getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(java.lang.Integer screenWidth) {
        this.screenWidth = screenWidth;
    }

    public java.lang.Integer getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(java.lang.Integer screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String nameItJohn() {
        setName("Jonh");
        return null;
    }

    public String nameItMark() {
        setName("Mark");
        return null;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void valueChanged(ValueChangeEvent event) {
        System.out.println(event.getSource());
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public void switchPanel() {
        setOpened(!isOpened());
        System.out.println("UserBean.switchPanel()");
        System.out.println(isOpened());
    }

    private int counter = 1;

    public List<Integer> getTableCounter() {
        List<Integer> rst = new ArrayList<Integer>();

        for (int i = 0; i < counter; i++) {
            rst.add(new Integer(i));
        }
        return rst;
    }

    public String addTableToPanel() {
        counter++;
        return null;
    }

    public String removeTableFromPanel() {
        if (counter > 0) {
            counter--;
        }
        return null;
    }

    public String getLongDescription() {
        return "There are\n 3 line\n\n description \n in the end";
    }

    public List<Locale> getLocales() {
        locales.clear();
        Locale[] _locales = Locale.getAvailableLocales();
        for (int i = 0; i < _locales.length; i++) {
            locales.add(_locales[i]);
//            if (i == 10) {
//                break;
//            }

        }
        return locales;
    }

    public List<String> getComboboxSuggestionValues() {
        final List<String> sugValues = new ArrayList<String>();

        for (Locale s:getLocales()) {
            if(!sugValues.contains(s.getDisplayLanguage())){
        	sugValues.add(s.getDisplayLanguage());
            }
        }

        for (Locale s:getLocales()) {
                String e = s.getDisplayLanguage() + s.getDisplayLanguage() + s.getDisplayLanguage() +
                        s.getDisplayLanguage();
            if(!sugValues.contains(e)){
                sugValues.add(e);
            }

        }

        sugValues.add("asdasdasd");
        sugValues.add("asdasdasd");
        sugValues.add("1234");
        sugValues.add("1234");
        sugValues.add("12");
        return sugValues;
    }
    
    

    public void setLocales(List<Locale> locales) {
        this.locales = locales;
    }

    public String getComboboxValue() {
        return comboboxValue;
    }

    public void setComboboxValue(String comboboxValue) {
        this.comboboxValue = comboboxValue;
    }

}
