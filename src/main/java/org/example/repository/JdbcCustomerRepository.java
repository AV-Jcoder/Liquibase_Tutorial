package org.example.repository;

import org.example.model.Customer;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class JdbcCustomerRepository {

    private final static String CREATE = "INSERT INTO customers (name) VALUES (?) RETURNING customer_id;";
    private final static String READ_ALL = "SELECT * FROM customers;";
    private final static String GET_NEXT_ID = "SELECT nextval('customers_customer_id_seq');";
    private final static String GET_BY_ID = "SELECT * FROM customers WHERE customer_id = (?);";
    private final static String UPDATE_BY_ID = "UPDATE customers SET name = (?) WHERE customer_id = (?);";
    private final static String DELETE_BY_ID = "DELETE FROM customers WHERE customer_id = (?);";

    public boolean create(Customer customer) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE)) {
            statement.setString(1,customer.getName());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                customer.setId(result.getInt("customer_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Customer read(Integer id) {
        Customer customer = null;
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public boolean update(Customer customer) {
        int count = 0;
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID)) {
            statement.setString(1,customer.getName());
            statement.setInt(2, customer.getId());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count != 0;
    }

    public boolean delete(Integer id) {
        int count = 0;
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count != 0;
    }

    public List<Customer> readAll() {
        List<Customer> list = new LinkedList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(READ_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("name"));
                list.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private Connection getConnection() {
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        String url = bundle.getString("URL");
        String login = bundle.getString("LOGIN");
        String pass = bundle.getString("PASSWORD");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, login, pass);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

}
