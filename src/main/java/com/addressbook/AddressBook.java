package com.addressbook;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
    private static ArrayList<Contacts> person = new ArrayList<Contacts>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Welcome to Address Book System");
        boolean flag = true;
        while(flag) {
            System.out.println("Enter 1 to add contact: ");
            System.out.println("Enter 2 to edit contact: ");
            System.out.println("Enter 3 to add contact: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    editContact();
                    break;
                case 3:
                    flag = !flag;
                    break;
                default:
                    System.out.println("Enter valid number");
                    break;
            }
        }
    }

    private static void editContact() {
        System.out.println("enter first name to edit contacts");
        String firstName = sc.next();
        int flag = 0;
        for (Contacts contacts : person) {
            if (contacts.getFirstName().equals(firstName)) {
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
                switch (choice) {
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
        String firstName,lastName,address,city,state,email;
        int zipCode;
        long phoneNumber;
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
        Contacts contacts = new Contacts(firstName, lastName, address, city, state, zipCode, phoneNumber, email);
        person.add(contacts);
    }
}
