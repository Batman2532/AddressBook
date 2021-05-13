package com.addressbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook {
    public static ArrayList<Contacts> person = new ArrayList<Contacts>();//initializing array list
    static Scanner sc = new Scanner(System.in); //initializing scanner class
    public static Map<String, ArrayList<Contacts>> addressBookListMap = new HashMap<String, ArrayList<Contacts>>();//initializing hashmap
/*
main class to store new address book or check if the address book already there
 */
    public static void main(String[] args){
        System.out.println("Welcome to Address Book System");
        boolean flag = true;//declaring flag
        while(flag) {
            System.out.println("1: Add new address book");
            System.out.println("2: exit");
            int option = sc.nextInt();
            switch (option) {//getting option from user
                case 1:
                    System.out.println("Enter the name of the address book");
                    String addressBookName = sc.next();//getting address boo name from user
                    if (addressBookListMap.containsKey(addressBookName)) {//checking if the same name address book is already there
                        System.out.println("this address book already exists ");
                    } else {
                        addressBookListMap.put(addressBookName, person);//storing address book in map
                        addAddressBook();
                    }
            }
        }
    }
/*
addAddressBook method to add,edit and delete in address book
 */
    private static void addAddressBook() {
        boolean flag = true;
        while(flag) {
            System.out.println("Enter 1 to add contact: ");
            System.out.println("Enter 2 to edit contact: ");
            System.out.println("Enter 3 to delete contact: ");
            System.out.println("Enter 4 to exit");
            int choice = sc.nextInt();//getting choice from user to add,edit or delete
            switch (choice) {
                case 1:
                    addContact();// calling addContact method
                    break;
                case 2:
                    editContact();// calling editContact method
                    break;
                case 3:
                    deleteContact();// calling delete contact method
                    break;
                case 4:
                    flag = !flag;
                    break;
                default:
                    System.out.println("Enter valid number");
                    break;
            }
        }
    }

    private static void deleteContact() {
        System.out.println("enter first name to delete contacts");
        String firstName = sc.next(); // getting details from user to delete contact
        int flag = 0;
        for (Contacts contacts : person) {
            if (contacts.getFirstName().equals(firstName)) { //checking if the arraylist contains that details
                person.remove(contacts);// deleting that details
                flag = 1;
                break;
            }
        }
        if(flag==1){
            System.out.println("Contacts deleted");
        }else{
            System.out.println("contacts not found");
        }
    }

    private static void editContact() {
        System.out.println("enter first name to edit contacts");
        String firstName = sc.next();
        int flag = 0;
        for (Contacts contacts : person) {
            if (contacts.getFirstName().equals(firstName)) {//checking if the arraylist contains that details
                System.out.println("1 : First Name of the contact to be edited");
                System.out.println("2 : Last Name of the contact to be edited");
                System.out.println("3 : Address of the contact to be edited");
                System.out.println("4 : City of the contact to be edited");
                System.out.println("5 : State of the contact to be edited");
                System.out.println("6 : Phone Number of the contact to be edited");
                System.out.println("7 : Zip of the contact to be edited");
                System.out.println("8 : Email of the contact to be edited");
                System.out.println("Select the index for the contact detail you want to edit ");

                int choice = sc.nextInt();
                switch (choice) {//initializing switch case to edit contact
                    case 1: {
                        System.out.println("Enter First Name: ");
                        firstName = sc.next();
                        contacts.setFirstName(firstName);
                        break;
                    }
                    case 2: {
                        System.out.println("Enter last name: ");
                        String lastName = sc.next();
                        contacts.setLastName(lastName);
                        break;
                    }
                    case 3: {
                        System.out.println("Enter Address: ");
                        String address = sc.next();
                        contacts.setAddress(address);
                        break;
                    }
                    case 4: {
                        System.out.println("Enter City: ");
                        String city = sc.next();
                        contacts.setCity(city);
                        break;
                    }
                    case 5: {
                        System.out.println("Enter State: ");
                        String state = sc.next();
                        contacts.setState(state);
                        break;
                    }
                    case 6: {
                        System.out.println("Enter Phone Number:");
                        int phoneNumber = sc.nextInt();
                        contacts.setPhoneNumber(phoneNumber);
                        break;
                    }
                    case 7: {
                        System.out.println("Enter Zip Code: ");
                        int zip = sc.nextInt();
                        contacts.setZipCode(zip);
                        break;
                    }
                    case 8: {
                        System.out.println("Enter Email: ");
                        String email = sc.next();
                        contacts.setEmail(email);
                        break;

                    }
                }
                flag=1;
                break;
            }
        }
        if(flag==1){
            System.out.println("Contacts updated");
        }else{
            System.out.println("contacts not found");
        }
    }

    public static void addContact(){
        //initializing variables
        String firstName,lastName,address,city,state,email;
        int zipCode, numberOfContacts;
        long phoneNumber;
        System.out.println("enter the number of contacts you want to enter");
        numberOfContacts= sc.nextInt();//getting number of contacts user wants to enter
        for (int i = 0; i <numberOfContacts ; i++) {//running for loop to enter the details
            System.out.println("Enter First Name");
            firstName = sc.next();
            System.out.println("Enter Last Name");
            lastName = sc.next();
            System.out.println("Enter Address");
            address = sc.next();
            System.out.println("Enter City");
            city = sc.next();
            System.out.println("Enter State");
            state = sc.next();
            System.out.println("Enter Zipcode");
            zipCode = sc.nextInt();
            System.out.println("Enter Phone Number");
            phoneNumber = sc.nextLong();
            System.out.println("Enter Email");
            email = sc.next();
            Contacts contacts = new Contacts(firstName, lastName, address, city, state, zipCode, phoneNumber, email);//creating object of contacts
            person.add(contacts);//storing  contacts to array list
        }
    }
}

}