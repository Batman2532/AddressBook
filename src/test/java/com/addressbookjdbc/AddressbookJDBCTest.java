package com.addressbookjdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressbookJDBCTest {

    @Test
    public void givenAddressBookContactsInDB_WhenRetrieved_ShouldMatchContactsCount() throws AddressBookException {
        AddressBookService addressBookService = new AddressBookService();
        int addressBookSize = addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        Assertions.assertEquals(1, addressBookSize);
    }

    @Test
    public void givenAddressBook_WhenUpdate_ShouldSyncWithDB() throws AddressBookException {
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.updateRecord("john", "mb town 2");
        boolean result = addressBookService.checkUpdatedRecordSyncWithDatabase("john");
        Assertions.assertTrue(result);
    }

    @Test
    public void givenAddressBook_WhenRetrieved_ShouldMatchCountInGivenRange() throws AddressBookException {
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        LocalDate startDate = LocalDate.of(2020,01,02);
        LocalDate endDate = LocalDate.now();
        List<AddressBookData> addressBookData = addressBookService.readAddressbookDataForDataRange(startDate,endDate);
        Assertions.assertEquals(2, addressBookData.size());
    }

    @Test
    public void givenAddressBook_WhenRetrieved_ShouldReturnCountOfCity() throws AddressBookException {
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        int count =addressBookService.readContactByCity("nagpur");
        Assertions.assertEquals(1, count);
    }

    @Test
    public void givenAddressBookDetails_WhenAdded_ShouldSyncWithDB() throws AddressBookException {
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        addressBookService.addNewContact("honda", "aviator", "baner", "pune", "maharashtra", 5537, 5785412, "aviator@gmail.com", LocalDate.now());
        boolean result = addressBookService.checkUpdatedRecordSyncWithDatabase("honda");
        Assertions.assertTrue(result);
    }

    @Test
    public void given3Contacts_WhenAddedToDB_ShouldMatchAddressbookEntries() throws AddressBookException {
        AddressBookData[] arrayOfContacts={
                    new AddressBookData("bruce","wayne","home","gotham","msa",440030,465789,"batman@yahoo.com",LocalDate.now()),
                    new AddressBookData("tony","stark","home","new york","usa",456123,852369,"ironman@jarvis.con",LocalDate.now()),
                    new AddressBookData("abc","bcd","cde","def","efg",123654,852147,"fgh@ghi.hij",LocalDate.now())
        };
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        addressBookService.addNewContactThread(Arrays.asList(arrayOfContacts));
        int addressBookSize = addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        Assertions.assertEquals(5, addressBookSize);

    }

}


