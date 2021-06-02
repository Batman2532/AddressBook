package com.addressbookjdbc;

import com.addressbook.Contacts;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AddressbookJSONTest {

    @BeforeAll
    static void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
    }

    public Contacts[] getContactsList(){
        Response response = RestAssured.get("/contacts");
        System.out.println("contacts in json"+response.asString());
        Contacts[] arrayOfContacts = new Gson().fromJson(response.asString(),Contacts[].class);
        return arrayOfContacts;
    }
    public Response addContactToJsonServer(Contacts contacts) {
        String contact = new Gson().toJson(contacts);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(contact);
        return request.post("/contacts");
    }

    // Test to retrieve contacts from json server
    @Test
    public void givenAddressbookDataInJSONServer_WhenRetrieved_shouldMatchTheCount()  {
        Contacts[] arrayOfContacts = getContactsList();
        AddressService addressService;
        addressService = new AddressService(Arrays.asList(arrayOfContacts));
        long entries = addressService.countEntries();
        Assertions.assertEquals(4,entries);
    }

    // adding multiple contacts to json server and then counting
    @Test
    public void givenMultipleContacts_WhenAdded_ShouldMatchCountand201ResponseAndCount()  {
        Contacts[] arrayOfContactEntries = {
                new Contacts(0,"sharwan", "iol", "abc", "ngp", "mh", 123455, 12324435,
                        "sharwan@abc.com","2020-09-30"),
                new Contacts(0,"ankur", "ghj", "bcd", "ngp", "mh", 123455, 12324435, "ankur@abc.com","2017-02-06"),
                new Contacts(0,"bat", "dfg", "cde", "ngp", "mh", 123455, 12324435, "bittu@abc.com","2019-12-19"),
        };
        for (Contacts contacts : arrayOfContactEntries) {
            Response response = addContactToJsonServer(contacts);
            int statusCode = response.getStatusCode();
            Assertions.assertEquals(201, statusCode);
        }
    }

    //updating contact details(city) to json server
    @Test
    public void givenNewCityToContact_WhenUpdated_ShouldMatch200Response()
    {
        Contacts[] arrayOfContacts = getContactsList();
        AddressService addressService= new AddressService(Arrays.asList(arrayOfContacts));

        addressService.updateAddressBookDataJSONServer("saurabh", "nagpur");
        Contacts contacts = addressService.getAddressBookData("saurabh");

        String empJson = new Gson().toJson(contacts);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
        request.body(empJson);
        System.out.println(contacts.getId());
        System.out.println(contacts.getCity());
        Response response = request.put("/contacts/"+contacts.getId());
        int statusCode = response.getStatusCode();
        Assertions.assertEquals(200,statusCode);
    }

    //deleting contact from  json server
    @Test
    public void givenContactToDelete_WhenDeleted_ShouldMatch200Response()
    {
        Contacts[] arrayOfContacts = getContactsList();
        AddressService addressService;
        addressService = new AddressService(Arrays.asList(arrayOfContacts));

        Contacts contacts = addressService.getAddressBookData("sharwan");

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
        Response response = request.delete("/contacts/"+contacts.getId());
        int statusCode = response.getStatusCode();
        Assertions.assertEquals(200,statusCode);
    }
}
