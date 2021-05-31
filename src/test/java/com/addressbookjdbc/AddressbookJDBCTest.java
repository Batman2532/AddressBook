package com.addressbookjdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class AddressbookJDBCTest {

    @Test
    public void givenAddressBookContactsInDB_WhenRetrieved_ShouldMatchContactsCount() throws AddressBookException {
        AddressBookService addressBookService = new AddressBookService();
        List<AddressBookData> addressBookData = addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        Assertions.assertEquals(5, addressBookData.size());
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
        Assertions.assertEquals(5, addressBookData.size());
    }

    @Test
    public void givenAddressBook_WhenRetrieved_ShouldReturnCountOfCity() throws AddressBookException {
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        int count =addressBookService.readContactByCity("nagpur");
        Assertions.assertEquals(1, count);
    }
}


