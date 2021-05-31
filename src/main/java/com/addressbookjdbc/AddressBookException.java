package com.addressbookjdbc;

public class AddressBookException extends Exception {
    enum ExceptionType {
        DATABASE_EXCEPTION
    }

    public ExceptionType type;

    public AddressBookException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
