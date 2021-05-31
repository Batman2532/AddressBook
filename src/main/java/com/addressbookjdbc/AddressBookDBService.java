package com.addressbookjdbc;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    AddressBookDBService() {
    }

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/addressbook_service?useSSL=false";
        String username = "root";
        String password = "";
        Connection con;
        System.out.println("Connecting to database:" + jdbcURL);
        con = DriverManager.getConnection(jdbcURL, username, password);
        System.out.println("Connection is successful:" + con);
        return con;

    }

    public List<AddressBookData> readData() throws AddressBookException {
        String query;
        query = "select * from addressbook";
        return getAddressBookDataUsingDB(query);
    }

    private List<AddressBookData> getAddressBookDataUsingDB(String sql) throws AddressBookException {
        List<AddressBookData> addressBookData;
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            addressBookData = this.getAddressBookDetails(resultSet);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookData;
    }

    private List<AddressBookData> getAddressBookDetails(ResultSet resultSet) throws AddressBookException {
        List<AddressBookData> addressBookData = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                BigDecimal zip = resultSet.getBigDecimal("zip");
                BigDecimal phoneNo = resultSet.getBigDecimal("phone_number");
                String email = resultSet.getString("email");
                addressBookData.add(new AddressBookData(firstName, lastName, address, city, state, zip, phoneNo, email));
            }
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookData;
    }
}
