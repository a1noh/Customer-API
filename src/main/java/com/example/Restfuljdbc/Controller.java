package com.example.Restfuljdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/hello")
    public String getHelloMessage() {
        return "Hello spring boot world !";
    } // Hello World

    @PostMapping("/customer")
    public void newCustomer(@RequestBody Customer newPerson) // POST request to add customer body to jdbc database (h2)
            throws badNameException {

        //System.out.println("success");
        if (!(isStringOnlyAlphabet(newPerson.getFirstName()) && isStringOnlyAlphabet(newPerson.getLastName()))) {
            throw new badNameException("BAD NAME!");
        }
        CustomerImplementation customerImplementation = new CustomerImplementation(newPerson, jdbcTemplate);
        customerImplementation.insertData();
    }

    public static boolean isStringOnlyAlphabet(String str) //helper function using regex to validate names on customer body
    {
        return ((!str.equals(""))
                && (str != null)
                && (str.matches("^[a-zA-Z]*$")));
    }


    @GetMapping("customer/{firstName}")
    public List<Customer> getCustomer(@PathVariable("firstName") String firstName) // gets all the names containg requested first name
            throws ResourceNotFoundException {
        CustomerImplementation customerImplementation = new CustomerImplementation(jdbcTemplate);
               // System.out.println(firstName);
        List<Customer> customers = customerImplementation.getCustomerData(firstName);
        if (customers.size() == 0) {
            throw new ResourceNotFoundException(String.format("No information that consists of %s", firstName));
        }
        return customers;
    }

    @DeleteMapping("customer/{id}")
    public void deleteCustomer(@PathVariable("id") long customerid) // deletes requested customer of the id from the database
            throws ResourceNotFoundException {
        CustomerImplementation customerImplementation = new CustomerImplementation(jdbcTemplate);
        int result = customerImplementation.delete((int) customerid);
        if (result == 0) {
            throw new ResourceNotFoundException("no such id");
        }
    }

    @PutMapping("customer/{id}")
    public void putCustomer(@RequestBody @Validated Customer newCustomer, @PathVariable("id") int customerid) //update
            throws ResourceNotFoundException {
        CustomerImplementation customerImplementation = new CustomerImplementation(newCustomer, jdbcTemplate);
        int result = customerImplementation.updateCustomer(customerid);
        if (result == 0) {
            throw new ResourceNotFoundException("no such id");
        }

    }


}

