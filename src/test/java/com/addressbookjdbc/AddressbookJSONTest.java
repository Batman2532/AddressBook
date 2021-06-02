package com.addressbookjdbc;

import com.addressbook.Contacts;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(1,arrayOfContacts.length);
    }

    //adding new contact to json server and than counting
    @Test
    public void givenNewEmployee_WhenAdded_ShouldMatchCountand201ResponseAndCount()
    {
        Contacts contacts = null;
        contacts = new Contacts(2,"sharwan", "kumar", "mankapur", "pune", "MH", 123455, 12324435,
                "sharwan@abc.com","2020-03-15");
        Response response = addContactToJsonServer(contacts);
        int statusCode = response.getStatusCode();
        Assertions.assertEquals(201,statusCode);
        Contacts[] arrayOfEmployee = getContactsList();
        Assertions.assertEquals(2,arrayOfEmployee.length);
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
        Contacts[] arrayOfEmployee = getContactsList();
        Assertions.assertEquals(5,arrayOfEmployee.length);
    }
}
