package com.addressbookjdbc;

import java.time.LocalDate;
import java.util.*;

public class AddressBookService {

    public enum IOService {
        DB_IO,REST_IO
    }

    public List<AddressBookData> addressBookList =  new ArrayList<>();
    private static AddressBookDBService addressBookDBService;

    public AddressBookService(List<AddressBookData> contactsList) {
        this();
        this.addressBookList = new ArrayList<>(contactsList);
    }
    public AddressBookService() {
        addressBookDBService = AddressBookDBService.getInstance();
    }

    public int readAddressBookData(IOService ioservice) throws AddressBookException {
        if (ioservice.equals(IOService.DB_IO))
            return (this.addressBookList = addressBookDBService.readData()).size();
        return this.addressBookList.size();
    }

    public void updateRecord(String firstname, String address) throws AddressBookException {
        int result = addressBookDBService.updateAddressBookData(firstname, address);
        if (result == 0)
            return;
        AddressBookData addressBookData = this.getAddressBookData(firstname);
        if (addressBookData != null)
            addressBookData.address = address;
    }

    public boolean checkUpdatedRecordSyncWithDatabase(String firstname) throws AddressBookException {
        List<AddressBookData> addressBookData = addressBookDBService.getAddressBookData(firstname);
        return addressBookData.equals(addressBookDBService.getAddressBookData(firstname));
    }

    private AddressBookData getAddressBookData(String firstname) {
        return this.addressBookList.stream().filter(addressBookItem -> addressBookItem.firstName.equals(firstname))
                .findFirst().orElse(null);
    }

    public List<AddressBookData> readAddressbookDataForDataRange(LocalDate startDate, LocalDate endDate) throws AddressBookException {
        return addressBookDBService.getEmployeePayrollDataForDateRange(startDate,endDate);
    }

    public int readContactByCity(String city) throws AddressBookException {
            return addressBookDBService.getCountByCity(city);
    }

    public void addNewContact(String firstname, String lastname, String address, String city, String state, int zip, int phonenumber , String email, LocalDate date) throws AddressBookException {
        addressBookList.add(addressBookDBService.addToAddressbook(firstname,lastname,address,city,state,zip,phonenumber,email,date));
    }


    public void addNewContactThread(List<AddressBookData> addressBookDataList) {
        Map<Integer, Boolean> contactAdditionStatus = new HashMap<>();
        addressBookDataList.forEach(contactsData ->{
            Runnable task = () -> {
                contactAdditionStatus.put(contactsData.hashCode(),false);
                try {
                    this.addNewContact(contactsData.firstName,contactsData.lastName,contactsData.address,contactsData.city,contactsData.state,contactsData.zip,contactsData.phoneNo,contactsData.email,contactsData.dateAdded);
                    contactAdditionStatus.put(contactsData.hashCode(),true);
                } catch (AddressBookException e) {
                    e.printStackTrace();
                }
            };
            Thread thread = new Thread(task,contactsData.firstName);
            thread.start();
        });
        while (contactAdditionStatus.containsValue(false)){
            try{Thread.sleep(10);
            }catch (InterruptedException e){}
        }
    }

}
