package com.addressbookjdbc;

public class AddressBookException extends Exception {
    enum ExceptionType {
        DATABASE_EXCEPTION,CONNECTION_FAILED
    }

    public ExceptionType type;

    public AddressBookException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
