package com.addressbook;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
    public static String firstName;
    public static String lastName;
    public static String address;
    public static String city;
    public static String state;
    public static int zipCode;
    public static int phoneNumber;
    public static String email;

    public AddressBook(String firstName, String lastName, String address,String city,String state,int zipCode,int phoneNumber,String email){
        this.firstName = firstName;
        this.lastName =lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void add(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Name");
        this.firstName = sc.nextLine();
        System.out.println("Enter Last Name");
        this.lastName = sc.nextLine();
        System.out.println("Enter Address");
        this.address = sc.nextLine();
        System.out.println("Enter City");
        this.city = sc.nextLine();
        System.out.println("Enter State");
        this.state = sc.nextLine();
        System.out.println("Enter Zipcode");
        this.zipCode = sc.nextInt();
        System.out.println("Enter Phone Number");
        this.phoneNumber = sc.nextInt();
        System.out.println("Enter Email");
        this.email = sc.next();
    }
    public String toString(){
        return ("The entered details are: \nfirst name " +firstName+"\nlast name "+ lastName+ "\nAddress "+address+"\ncity "+city+"\nstate " +state+"\nzip code "+zipCode+"\nphone number "+phoneNumber+"\nemail "+email);
    }

    public static void main(String[] args){
        System.out.println("Welcome to Address Book System");
        ArrayList<AddressBook> contacts = new ArrayList<>();
        AddressBook ab = new AddressBook(firstName, lastName, address, city, state, zipCode,phoneNumber,email);
        System.out.println("Enter 1 to add contact: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice) {
            case 1:
                ab.add();
                ab.toString();
                contacts.add(ab);
                System.out.println(contacts.get(0));
                break;
            default:
                System.out.println("Enter valid number");
                break;
        }
    }
}
