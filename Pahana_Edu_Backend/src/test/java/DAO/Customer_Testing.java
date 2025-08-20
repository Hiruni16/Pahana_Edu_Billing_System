/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Utils.customer_DAO;
import classes.customer;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;


/**
 *
 * @author Hiruni
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Ensures tests run in alphabetical order
public class Customer_Testing {
    
    private static customer_DAO dao;
    private static Integer testCustomerId; // Use Integer for null safety

    @BeforeClass
    public static void setup() {
        dao = new customer_DAO();
    }

    @Test
    public void test1_AddCustomer() {
        customer c = new customer();
        c.setAccount_no("TEST-001");
        c.setName("John Doe");
        c.setAddress("Colombo");
        c.setTele_no(771234567);

        boolean result = dao.addCustomer(c);
        assertTrue("Customer should be added successfully", result);

        // Retrieve the ID of the last inserted customer safely
        List<customer> customers = dao.getAllCustomers();
        assertFalse("Customer list should not be empty after adding", customers.isEmpty());
        testCustomerId = customers.get(customers.size() - 1).getCustomer_id();
        assertNotNull("Test customer ID should be set", testCustomerId);
    }

    @Test
    public void test2_GetCustomerById() {
        assertNotNull("Test customer ID should not be null", testCustomerId);
        customer c = dao.getCustomerById(testCustomerId);
        assertNotNull("Customer should exist", c);
        assertEquals("Customer name should match", "John Doe", c.getName());
    }

    @Test
    public void test3_UpdateCustomer() {
        assertNotNull("Test customer ID should not be null", testCustomerId);
        customer c = dao.getCustomerById(testCustomerId);
        c.setAddress("Kandy");

        boolean updated = dao.updateCustomer(c);
        assertTrue("Customer should be updated", updated);

        customer updatedCustomer = dao.getCustomerById(testCustomerId);
        assertEquals("Customer address should be updated", "Kandy", updatedCustomer.getAddress());
    }

    @Test
    public void test4_GetAllCustomers() {
        List<customer> customers = dao.getAllCustomers();
        assertFalse("Customer list should not be empty", customers.isEmpty());
    }

    @Test
    public void test5_DeleteCustomer() {
        assertNotNull("Test customer ID should not be null", testCustomerId);
        boolean deleted = dao.deleteCustomer(testCustomerId);
        assertTrue("Customer should be deleted", deleted);

        customer c = dao.getCustomerById(testCustomerId);
        assertNull("Deleted customer should not exist", c);
    }
}
