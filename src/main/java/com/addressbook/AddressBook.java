package com.addressbook;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
    private static ArrayList<Contacts> person = new ArrayList<Contacts>();
    static Scanner sc = new Scanner(System.in);

    public static void addPerson(){
        String firstName,lastName,address,city,state,email;
        int zipCode,phoneNumber;
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
        phoneNumber = sc.nextInt();
        System.out.println("Enter Email");
        email = sc.next();
        Contacts ab = new Contacts(firstName, lastName, address, city, state, zipCode, phoneNumber, email);
        System.out.println(ab);
        person.add(ab);
    }

    public static void main(String[] args){
        System.out.println("Welcome to Address Book System");
        System.out.println("Enter 1 to add contact: ");
        int choice = sc.nextInt();
        switch(choice) {
            case 1:
                addPerson();
                break;
            default:
                System.out.println("Enter valid number");
                break;
        }
    }
}
