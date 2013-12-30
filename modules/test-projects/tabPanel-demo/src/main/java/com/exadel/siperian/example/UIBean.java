//=====================================================================
// project:   Siperian Hub
//---------------------------------------------------------------------
// copyright: Siperian Inc. (c) 2003-2009.  All rights reserved.
//=====================================================================

package com.exadel.siperian.example;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Valery Tcherepanov
 */
public class UIBean {

    private String firstName;

    private String lastName;

    private String fullName;

    private List<Child> children;

    public UIBean(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;

        Element email1 = new Element();
        email1.add(firstName + "." + lastName);
        email1.add("@");
        email1.add("yahoo.com");

        Element email2 = new Element();
        email2.add(firstName + lastName);
        email2.add("@");
        email2.add("gmail.com");

        Element email3 = new Element();
        email3.add(lastName);
        email3.add("@");
        email3.add("mail.ru");

        List<Element> emailList = new ArrayList<Element>();
        emailList.add(email1);
        emailList.add(email2);
        emailList.add(email3);


        Child emails = new Child("Emails", emailList);


        Element address1 = new Element();
        address1.add(firstName + "." + lastName);
        address1.add("@");
        address1.add("yahoo.com");

        Element address2 = new Element();
        address2.add(firstName + lastName);
        address2.add("@");
        address2.add("gmail.com");

        Element address3 = new Element();
        address3.add(lastName);
        address3.add("@");
        address3.add("mail.ru");

        List<Element> addressList = new ArrayList<Element>();
        addressList.add(address1);
        addressList.add(address2);
        addressList.add(address3);

        Child addresses = new Child("Addresses", addressList);
        children = new ArrayList<Child>();
        children.add(emails);
        children.add(addresses);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return fullName;
    }

    public List<Child> getChildren() {
        return children;
    }
}
