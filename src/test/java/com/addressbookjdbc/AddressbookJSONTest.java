package com.addressbookjdbc;

import com.addressbook.Contacts;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
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

    @Test
    public void givenAddressbookDataInJSONServer_WhenRetrieved_shouldMatchTheCount()  {
        Contacts[] arrayOfContacts = getContactsList();
        Assertions.assertEquals(1,arrayOfContacts.length);

    }
}
