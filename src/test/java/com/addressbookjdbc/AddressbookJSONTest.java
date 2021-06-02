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
}
