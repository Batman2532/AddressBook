package com.addressbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookMain {
    private static final AddressBook addressBook = new AddressBook();
    static Scanner sc = new Scanner(System.in); //initializing scanner class
    public static Map<String, AddressBook> addressBookListMap = new HashMap<String, AddressBook>();//initializing hashmap
/*
main class to store new address book or check if the address book already there
 */
    public static void main(String[] args) {
        addressBook.addContact();
//        AddressBookMain addressBookMain = new AddressBookMain();
//        System.out.println("Welcome to Address Book System");
//        boolean flag = true;//declaring flag
//        while (flag) {
//            System.out.println("1: Add new address book");
//            System.out.println("2:Find Duplicate Entry in Address Book");
//            System.out.println("3.Search Contact from a city");
//            System.out.println("4.Search Contact from a State");
//            System.out.println("5.View contact By State Using State and Person HashMap");
//            System.out.println("6.View Contact by city Using City and Person HashMap");
//            System.out.println("7.Count Contact By State");
//            System.out.println("8.Count Contact By City");
//            System.out.println("9.Sort and Print in Alphabetically Order");
//            System.out.println("10.Sort Contact By City");
//            System.out.println("11.Sort Contact By State");
//            System.out.println("12.Sort Contact By Zip Code");
//            System.out.println("13.Write Data into the file");
//            System.out.println("14.Read data from the console");
//            System.out.println("15.Exit");
//            String addressBookName = null;
//            int option = sc.nextInt();
//            switch (option) {//getting option from user
//                case 1:
//                    System.out.println("Enter the name of the address book");
//                    addressBookName = sc.next();//getting address boo name from user
//                    if (addressBookListMap.containsKey(addressBookName)) {//checking if the same name address book is already there
//                        System.out.println("this address book already exists ");
//                    } else {
//                        addAddressBook(addressBookName);//storing address book in map
//                    }
//                    break;
//                case 2:
//                    for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
//                        AddressBook value = entry.getValue();
//                        System.out.println("Address Book Name: " + entry.getKey());
//                        value.checkDuplicate();
//                    }
//                case 3:
//                    System.out.println("Enter Name of City: ");
//                    String cityName = sc.next();
//                    addressBookMain.searchPersonByCity(cityName);
//                    break;
//
//                case 4:{
//                    System.out.println("Enter Name of State: ");
//                    String stateName = sc.next();
//                    addressBookMain.searchPersonByState(stateName);
//                    break;
//                }
//                case 5:
//                    System.out.println("Enter Name of State: ");
//                    String stateName1 = sc.next();
//                    addressBookMain.viewPersonByStateUsingHashmap(stateName1);
//                    break;
//
//                case 6:
//                    System.out.println("Enter Name of City: ");
//                    String cityName1 = sc.next();
//                    addressBookMain.viewPersonByCityUsingHashMap(cityName1);
//                    break;
//
//                case 7:
//                    System.out.println("Enter Name of State: ");
//                    String stateName2 = sc.next();
//                    addressBookMain.CountByState(stateName2);
//                    break;
//
//                case 8:
//                    System.out.println("Enter Name of City: ");
//                    String cityName2 = sc.next();
//                    addressBookMain.CountByCity(cityName2);
//                    break;
//
//                case 9:
//                    System.out.println("Sort Contact");
//                    addressBookMain.sortContactByName();
//
//                case 10:
//                    addressBookMain.sortContactByCity();
//                    break;
//
//                case 11:
//                    addressBookMain.sortContactByState();
//                    break;
//
//                case 12:
//                    addressBookMain.sortContactByZipCode();
//                    break;
//                case 13:
//                    System.out.println("11");
//                    break;
//
//                case 14:
//                    addressBook.readData();
//                    break;
//
//                case 15:
//
//                    flag = false;
//                    break;
//
//            }
//            }
        }
/*
addAddressBook method to add,edit and delete in address book
 */
    private static void addAddressBook(String addressBookName) {
        boolean flag = true;
        while(flag) {
            System.out.println("Enter 1 to add contact: ");
            System.out.println("Enter 2 to edit contact: ");
            System.out.println("Enter 3 to delete contact: ");
            System.out.println("Enter 4 to exit");
            int choice = sc.nextInt();//getting choice from user to add,edit or delete
            switch (choice) {
                case 1:
                    addressBook.addContact();// calling addContact method
                    break;
                case 2:
                    AddressBook.editContact();// calling editContact method
                    break;
                case 3:
                    AddressBook.deleteContact();// calling delete contact method
                    break;
                case 4:
                    flag = !flag;
                    break;
                default:
                    System.out.println("Enter valid number");
                    break;
            }
        }
        addressBookListMap.put(addressBookName, addressBook);
        System.out.println("Address Book Added Successfully");
    }
    private void searchPersonByState(String stateName) {
        for(Map.Entry<String,AddressBook> entry: addressBookListMap.entrySet()){
            AddressBook value = entry.getValue();
            System.out.println("The Address Book: "+entry.getKey());
            value.getPersonNameByState(stateName);
        }
    }

    private void searchPersonByCity(String cityName) {
        for(Map.Entry<String,AddressBook> entry: addressBookListMap.entrySet()){
            AddressBook value = entry.getValue();
            System.out.println("The Address Book: "+entry.getKey());
            value.getPersonNameByCity(cityName);
        }
    }


    private void viewPersonByStateUsingHashmap(String stateName) {
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            AddressBook value = entry.getValue();
            ArrayList<Contacts> contacts = value.personByState.entrySet().stream().filter(findState -> findState.getKey().equals(stateName)).map(Map.Entry::getValue).findFirst().orElse(null);
            for(Contacts contact: contacts){
                System.out.println("First Name: "+contact.getFirstName()+" Last Name: "+ contact.getLastName());
            }
        }
    }

    private void viewPersonByCityUsingHashMap(String cityName) {
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            AddressBook value = entry.getValue();
            ArrayList<Contacts> contacts = value.personByCity.entrySet().stream().filter(findCity -> findCity.getKey().equals(cityName)).map(Map.Entry::getValue).findFirst().orElse(null);
            for(Contacts contact: contacts){
                System.out.println("First Name: "+contact.getFirstName()+" Last Name: "+ contact.getLastName());
            }
        }
    }

    public void CountByState(String state) {
        int count = 0;
        for(Map.Entry<String, AddressBook> entry: addressBookListMap.entrySet()){
            for(int i=0;i<(entry.getValue()).person.size();i++)
            {
                Contacts contact= entry.getValue().person.get(i);

                if(state.equals(contact.getState()))
                {
                    count++;
                }

            }
        }
        System.out.println("Total Person Count in state "+state+": "+count);
    }
    public void CountByCity(String city) {
        int countPersonInCity=0;
        for(Map.Entry<String, AddressBook> entry: addressBookListMap.entrySet())
        {
            for(int i=0;i<(entry.getValue()).person.size();i++)
            {
                Contacts d= (Contacts) entry.getValue().person.get(i);

                if(city.equals(d.getCity()))
                {
                    countPersonInCity++;
                }

            }
        }
        System.out.println("Total number of people in this city "+city+": "+countPersonInCity);
    }
    private void sortContactByName() {
        for (Map.Entry<String,AddressBook>entry:addressBookListMap.entrySet()){
            AddressBook value = entry.getValue();
            List<Contacts> sortedList = value.person.stream().sorted(Comparator.comparing(Contacts::getFirstName)).collect(Collectors.toList());

            for(Contacts contact:sortedList){
                System.out.println("First Name: "+contact.getFirstName());
                System.out.println("Last Name: "+contact.getLastName());
            }
        }
    }

    private void sortContactByZipCode() {
        for (Map.Entry<String,AddressBook>entry:addressBookListMap.entrySet()){
            AddressBook value = entry.getValue();
            List<Contacts> sortedList = value.person.stream().sorted(Comparator.comparing(Contacts::getZipCode)).collect(Collectors.toList());

            for(Contacts contact:sortedList){
                System.out.println("First Name: "+contact.getFirstName());
                System.out.println("Last Name: "+contact.getLastName());
            }
        }
    }

    private void sortContactByState() {
        for (Map.Entry<String,AddressBook>entry:addressBookListMap.entrySet()){
            AddressBook value = entry.getValue();
            List<Contacts> sortedList = value.person.stream().sorted(Comparator.comparing(Contacts::getState)).collect(Collectors.toList());

            for(Contacts contact:sortedList){
                System.out.println("First Name: "+contact.getFirstName());
                System.out.println("Last Name: "+contact.getLastName());
            }
        }
    }

    private void sortContactByCity() {
        for (Map.Entry<String,AddressBook>entry:addressBookListMap.entrySet()){
            AddressBook value = entry.getValue();
            List<Contacts> sortedList = value.person.stream().sorted(Comparator.comparing(Contacts::getCity)).collect(Collectors.toList());

            for(Contacts contact:sortedList){
                System.out.println("First Name: "+contact.getFirstName());
                System.out.println("Last Name: "+contact.getLastName());
            }
        }
    }
}
