package org.example.repository.impl;

import org.example.model.Customer;
import org.example.repository.CustomerRepository;

import java.util.List;

public class JdbcCustomerRepositoryImpl implements CustomerRepository {
    @Override
    public boolean create(Customer customer) {
        return false;
    }

    @Override
    public Customer read(Integer integer) {
        return null;
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public List<Customer> readAll() {
        return null;
    }
}
