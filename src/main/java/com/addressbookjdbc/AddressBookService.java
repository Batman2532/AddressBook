package com.addressbookjdbc;

import java.util.List;

public class AddressBookService {
    public enum IOService {
        DB_IO
    }

    private List<AddressBookData> addressBookList;
    public AddressBookService(){}

    public List<AddressBookData> readAddressBookData(IOService ioservice) throws AddressBookException {
        if (ioservice.equals(IOService.DB_IO))
            this.addressBookList = new AddressBookDBService().readData();
        return this.addressBookList;
    }
}
