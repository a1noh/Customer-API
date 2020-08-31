package com.example.Restfuljdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


public class CustomerImplementation {

    protected JdbcTemplate jdbcTemplate;

    Customer customer;

    public CustomerImplementation(Customer customer, JdbcTemplate jdbcTemplate) { // constructor
        this.customer=customer;
        this.jdbcTemplate = jdbcTemplate;
    }

    public CustomerImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    } // constructor

    public List<Customer> getCustomerData(String firstName) {
        List<Customer> customers = new ArrayList<>();
        try {
            jdbcTemplate.query(
                    "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[]{firstName},
                    (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
            ).forEach(customer -> customers.add(customer));
        } catch (Exception exception){
            System.out.println(String.format("Exception %s", exception.getMessage()));
        }
        return customers;
    }

    public void insertData() {
        //createAndDeleteTable();
        jdbcTemplate.update("INSERT INTO customers(first_name, last_name) VALUES (?,?)", customer.getFirstName(), customer.getLastName());
    }

    public int delete(Integer id){
        String SQL = "delete from customers where id = ?";
        int result = jdbcTemplate.update(SQL, id);
        //System.out.println("Deleted Record with ID = " + id );
        return result;
    }


   public int updateCustomer(Integer id){
       String SQL = "update customers set first_name = ?, last_name = ? where id = ?";
       int result = jdbcTemplate.update(SQL, customer.getFirstName(),customer.getLastName(), id);
       System.out.println("Updated Record with ID = " + id );
       return result; // will return 0 if the the update function fails for the jdbctemplate
   }

}
