package com.addressbookjdbc;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    private PreparedStatement addressBookPreparedStatement;
    private static AddressBookDBService addressBookDBService;
    private List<AddressBookData> addressBookData = new ArrayList<>();

    private AddressBookDBService() {
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

    public static AddressBookDBService getInstance() {
        if (addressBookDBService == null)
            addressBookDBService = new AddressBookDBService();
        return addressBookDBService;
    }

    public List<AddressBookData> readData() throws AddressBookException {
        String query;
        query = "select * from addressbook";
        return getAddressBookDataUsingDB(query);
    }

    private List<AddressBookData> getAddressBookDataUsingDB(String sql) throws AddressBookException {
//        List<AddressBookData> addressBookData ;
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            addressBookData = this.getAddressBookDetails(resultSet);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookData;
    }

    private void prepareAddressBookStatement() throws AddressBookException {
        try {
            Connection connection = this.getConnection();
            String query = "select * from addressbook where first_name = ?";
            addressBookPreparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
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
                int zip = resultSet.getInt("zip");
                int phoneNo = resultSet.getInt("phone_number");
                String email = resultSet.getString("email");
                LocalDate date = resultSet.getDate("date_added").toLocalDate();
                addressBookData.add(new AddressBookData(firstName, lastName, address, city, state, zip, phoneNo, email,date));
            }
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookData;
    }

    public int updateAddressBookData(String firstname, String address) throws AddressBookException {
        String query = String.format("update addressbook set address = '%s' where first_name = '%s';", address, firstname);
        try (Connection connection = this.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeUpdate(query);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.CONNECTION_FAILED);
        }
    }

    public List<AddressBookData> getAddressBookData(String firstname) throws AddressBookException {
        if (this.addressBookPreparedStatement == null)
            this.prepareAddressBookStatement();
        try {
            addressBookPreparedStatement.setString(1, firstname);
            ResultSet resultSet = addressBookPreparedStatement.executeQuery();
            addressBookData = this.getAddressBookDetails(resultSet);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.CONNECTION_FAILED);
        }
        return addressBookData;
    }

    public List<AddressBookData> getEmployeePayrollDataForDateRange(LocalDate startDate, LocalDate endDate) throws AddressBookException {
        String sql = String.format("SELECT * FROM  addressbook WHERE date_added BETWEEN '%s' AND '%s';", Date.valueOf(startDate), Date.valueOf(endDate));
        return this.getAddressBookDataUsingDB(sql);
    }

    public int getCountByCity(String city) throws AddressBookException {
        int count = 0;
        String sql = String.format("select * from addressbook where city = '%s' ;", city);
        addressBookData=this.getAddressBookDataUsingDB(sql);
        count = (int) addressBookData.stream().count();

        return count;
    }

    public AddressBookData addToAddressbook(String firstname, String lastname, String address, String city, String state, int zip, int phonenumber, String email, LocalDate date) throws AddressBookException {
        int id = -1;
        AddressBookData addressBookData = null;
        String query = String.format(
                "insert into addressbook(first_name,last_name,address,city,state,zip,phone_number,email,date_added) values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s','%s')",
                firstname, lastname, address, city, state, zip, phonenumber, email,Date.valueOf(date) );
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            int rowChanged = statement.executeUpdate(query, statement.RETURN_GENERATED_KEYS);
            if (rowChanged == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next())
                    id = resultSet.getInt(1);
            }
            addressBookData = new AddressBookData(firstname, lastname, address, city, state, zip, phonenumber, email, date);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookData;
    }
}
