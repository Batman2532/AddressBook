package com.addressbookjdbc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class AddressBookData {
    public String date;
    public   int id;
    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    int zip;
    int phoneNo;
    String email;
    LocalDate dateAdded;
    public AddressBookData(int id, String firstname, String lastname, String address, String city, String state, int zip,
                           int phonenumber, String email) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNo = phonenumber;
        this.email = email;

    }

    public AddressBookData(String firstName, String lastName, String address, String city, String state, int zip, int phoneNo,
                           String email, LocalDate dateAdded) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNo = phoneNo;
        this.email = email;
        this.dateAdded = dateAdded;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "AddressBook [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city="
                + city + ", state=" + state + ", zip=" + zip + ", phoneNo=" + phoneNo + ", email=" + email + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressBookData)) return false;
        AddressBookData that = (AddressBookData) o;
        return getZip() == that.getZip() && getPhoneNo() == that.getPhoneNo() && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getState(), that.getState()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getDateAdded(), that.getDateAdded());
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
