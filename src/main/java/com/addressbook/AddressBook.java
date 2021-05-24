package com.addressbook;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    public static ArrayList<Contacts> person = new ArrayList<Contacts>();//initializing array list
    public static Scanner sc = new Scanner(System.in);
    public HashMap<String, ArrayList<Contacts>> personByState;
    public HashMap<String, ArrayList<Contacts>> personByCity;
    public static String ADDRESSBOOK_File = "addressBook.txt";

    public AddressBook() {
        personByCity = new HashMap<String, ArrayList<Contacts>>();
        personByState = new HashMap<String, ArrayList<Contacts>>();
    }
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

    public ArrayList<Contacts> addContact(){
        //initializing variables
        String firstName,lastName,address,city,state,email;
        int zipCode, numberOfContacts;
        long phoneNumber;
        System.out.println("enter the number of contacts you want to enter");
        numberOfContacts= sc.nextInt();//getting number of contacts user wants to enter
        for (int i = 0; i <numberOfContacts ; i++) {//running for loop to enter the details
            System.out.println("Enter First Name");
            firstName = sc.next();
            checkDuplicate();
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
            if(!personByState.containsKey(state)){
                personByState.put(state,new ArrayList<Contacts>());
            }
            personByState.get(state).add(contacts);

            if(!personByCity.containsKey(city)){
                personByCity.put(city,new ArrayList<Contacts>());
            }
            personByCity.get(city).add(contacts);
        }
        try {
            writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writeDataToCSV();
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
        try {
            writeDataInJSon();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }
    public void writeData() throws IOException {
        StringBuffer contactBuffer = new StringBuffer();
        person.forEach(contact -> {
            String contactDataString = contact.toString().concat("\n");
            contactBuffer.append(contactDataString);
        });
        try {
            Files.write(Paths.get(ADDRESSBOOK_File), contactBuffer.toString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // write data to CSV file
    public void writeDataToCSV() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (Writer writer = Files.newBufferedWriter(Paths.get("/home/saurabh/IdeaProjects/AddressBook/src/main/resources/contacts.csv"));) {
            StatefulBeanToCsvBuilder<Contacts> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<Contacts> beanWriter = builder.build();
            beanWriter.write(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read data from CSV file
    public void readDataFromCSV() throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get("/home/saurabh/IdeaProjects/AddressBook/src/main/resources/contacts.csv"));
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println("First Name - " + nextRecord[3]);
                System.out.println("Last Name - " + nextRecord[4]);
                System.out.println("Address - " + nextRecord[0]);
                System.out.println("City - " + nextRecord[1]);
                System.out.println("State - " + nextRecord[6]);
                System.out.println("Email - " + nextRecord[2]);
                System.out.println("Phone - " + nextRecord[5]);
                System.out.println("Zip - " + nextRecord[7]);
            }
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public void writeDataInJSon() throws IOException {
        {
            Path filePath = Paths.get(
                    "/home/saurabh/IdeaProjects/AddressBook/src/main/resources/contacts.json");
            Gson gson = new Gson();
            String json = gson.toJson(person);
            FileWriter writer = new FileWriter(String.valueOf(filePath));
            writer.write(json);
            writer.close();
        }
    }

    // Read from JSON
    public void readDataFromJson() throws IOException {
        ArrayList<Contacts> contactList = null;
        Path filePath = Paths.get(
                "/home/saurabh/IdeaProjects/AddressBook/src/main/resources/contacts.json");
        try (Reader reader = Files.newBufferedReader(filePath);) {
            Gson gson = new Gson();
            contactList = new ArrayList<Contacts>(Arrays.asList(gson.fromJson(reader, Contacts[].class)));
            for (Contacts contact : contactList) {
                System.out.println("Firstname : " + contact.getFirstName());
                System.out.println("Lastname : " + contact.getLastName());
                System.out.println("Address : " + contact.getAddress());
                System.out.println("City : " + contact.getCity());
                System.out.println("State : " + contact.getState());
                System.out.println("Zip : " + contact.getZipCode());
                System.out.println("Phone number : " + contact.getPhoneNumber());
                System.out.println("Email : " + contact.getEmail());
            }
        }
    }

    // Check Duplicate Entry
    public static void checkDuplicate() {
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
