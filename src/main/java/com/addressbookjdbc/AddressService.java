package com.addressbookjdbc;

import com.addressbook.Contacts;

import java.util.ArrayList;
import java.util.List;

public class AddressService {

    List<Contacts> addressBookList;

    public AddressService(List<Contacts> contacts) {
        this.addressBookList = contacts;
    }

    public long countEntries() {
        return addressBookList.size();
    }

    public void updateAddressBookDataJSONServer(String firstname, String city) {
        Contacts addressBookData = this.getAddressBookData(firstname);
        if (addressBookData != null) addressBookData.setCity(city);
    }

    public Contacts getAddressBookData(String firstname) {
        Contacts addressBookData;
        addressBookData = this.addressBookList.stream()
                .filter(addressBookEntry -> (addressBookEntry.getFirstName()).equals(firstname))
                .findFirst()
                .orElse(null);
        return addressBookData;
    }

}

