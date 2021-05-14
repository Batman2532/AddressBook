package com.addressbook;

import java.util.*;

public class AddressBookMain {
    private static AddressBook addressBook = new AddressBook();
    static Scanner sc = new Scanner(System.in); //initializing scanner class
    public static Map<String, AddressBook> addressBookListMap = new HashMap<String, AddressBook>();//initializing hashmap
/*
main class to store new address book or check if the address book already there
 */
    public static void main(String[] args) {
        AddressBookMain addressBookMain = new AddressBookMain();
        System.out.println("Welcome to Address Book System");
        boolean flag = true;//declaring flag
        while (flag) {
            System.out.println("1: Add new address book");
            System.out.println("2:Find Duplicate Entry in Address Book");
            System.out.println("3: exit");
            int option = sc.nextInt();
            switch (option) {//getting option from user
                case 1:
                    System.out.println("Enter the name of the address book");
                    String addressBookName = sc.next();//getting address boo name from user
                    if (addressBookListMap.containsKey(addressBookName)) {//checking if the same name address book is already there
                        System.out.println("this address book already exists ");
                        break;
                    } else {
                        addAddressBook(addressBookName);//storing address book in map
                        break;
                    }
                case 2:
                    for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
                        AddressBook value = entry.getValue();
                        System.out.println("Address Book Name: " + entry.getKey());
                        value.checkDuplicate();
                    }
                case 3: {
                    flag = false;
                    break;
                }
            }
        }
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
                    addressBook.editContact();// calling editContact method
                    break;
                case 3:
                    addressBook.deleteContact();// calling delete contact method
                    break;
                case 4:
                    flag = !flag;
                    break;
                default:
                    System.out.println("Enter valid number");
                    break;
            }
            addressBookListMap.put(addressBookName, addressBook);
            System.out.println("Address Book Added Successfully");
        }
    }


}
