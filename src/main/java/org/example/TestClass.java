package org.example;

import org.example.model.Customer;
import org.example.repository.JdbcCustomerRepository;

import java.util.List;

public class TestClass {
    public static void main(String[] args) {

        JdbcCustomerRepository repo = new JdbcCustomerRepository();

        // read all ////////////////////////////////

        List<Customer> list = repo.readAll();
        for (Customer c :
                list) {
            System.out.println(c);
        }

        // create //////////////////////////////////

        Customer customer1 = new Customer();
        customer1.setName("Akinf");
        System.out.println(repo.create(customer1));
        System.out.println(customer1.getId());

        // read by id //////////////////////////////

        Customer customer2 = repo.read(26);
        System.out.println(customer2);

        // update //////////////////////////////////

        customer2.setName("Pro");
        System.out.println(repo.update(customer2));

        // delete //////////////////////////////////

        System.out.println(repo.delete(36));

        // read all ////////////////////////////////

        list = repo.readAll();
        for (Customer c :
                list) {
            System.out.println(c);
        }
    }
}
