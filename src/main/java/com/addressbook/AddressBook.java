package com.addressbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    public static ArrayList<Contacts> person = new ArrayList<Contacts>();//initializing array list
    public static Scanner sc = new Scanner(System.in);
    static void deleteContact() {
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

    static void editContact() {
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

    // Check Duplicate Entry
    public void checkDuplicate() {
        Set<String> ContactSet = new HashSet<>();
        Set<Contacts> filterSet = person.stream().filter(n -> !ContactSet.add(n.getFirstName())).collect(Collectors.toSet());

        for (Contacts contact : filterSet) {
            System.out.println("The Duplicate Contact is: " + contact.getFirstName() + " " + contact.getLastName());
        }
    }

    public void getPersonNameByState(String State) {
        List<Contacts> list  = person.stream().filter(p ->p.getCity().equals(State)).collect(Collectors.toList());
        for(Contacts contact: list){
            System.out.println("First Name: "+contact.getFirstName());
            System.out.println("Last Name: "+contact.getLastName());
        }

    }

    public void getPersonNameByCity(String cityName) {
        List<Contacts> list  = person.stream().filter(p ->p.getCity().equals(cityName)).collect(Collectors.toList());
        for(Contacts contact: list){
            System.out.println("First Name: "+contact.getFirstName());
            System.out.println("Last Name: "+contact.getLastName());
        }
    }
}
