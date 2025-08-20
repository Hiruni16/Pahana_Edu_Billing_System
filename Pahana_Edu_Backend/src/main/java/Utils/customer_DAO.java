/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DB.DB_Operation1;
import classes.customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class customer_DAO {
    private static final String URL = DB_Operation1.URL;
    private static final String USER = DB_Operation1.USER;
    private static final String PASS = DB_Operation1.PASS;

    private static final String QUERY_ALL = "SELECT * FROM customer";
    private static final String QUERY_BY_ID = "SELECT * FROM customer WHERE customer_id = ?";
    
    
//    // Retrieve all customers (not implemented yet)
//    public List<customer> getAllCustomers() {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    // Retrieve customer by ID (not implemented yet)
//    public customer getCustomerById(int id) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    // Add a new customer (not implemented yet)
//    public boolean addCustomer(customer customer) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    // Update an existing customer (not implemented yet)
//    public boolean updateCustomer(customer customer) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    // Delete a customer by ID (not implemented yet)
//    public boolean deleteCustomer(int customerId) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
    

    public List<customer> getAllCustomers() {
        List<customer> customers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY_ALL)) {

            while (rs.next()) {
                customer c = new customer();
                c.setCustomer_id(rs.getInt("customer_id"));
                c.setAccount_no(rs.getString("account_no"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setTele_no(rs.getInt("tele_no"));
                customers.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving customers: " + e.getMessage());
            e.printStackTrace();
        }

        return customers;
    }

    public customer getCustomerById(int id) {
        customer customer = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(QUERY_BY_ID)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = new customer();
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setAccount_no(rs.getString("account_no"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setTele_no(rs.getInt("tele_no"));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving customer by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return customer;
    }
    
    public boolean addCustomer(customer customer) {
        String sql = "INSERT INTO customer (account_no, name, address, tele_no) VALUES (?, ?, ?, ?)";

        try (Connection conn = DB_Operation1.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getAccount_no());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getAddress());
            pstmt.setInt(4, customer.getTele_no());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error adding customer: " + e.getMessage());
            return false;
        }
    }

    public boolean updateCustomer(customer customer) {
        String sql = "UPDATE customer SET account_no = ?, name = ?, address = ?, tele_no = ? WHERE customer_id = ?";

        try (Connection conn = DB_Operation1.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getAccount_no());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getAddress());
            pstmt.setInt(4, customer.getTele_no());
            pstmt.setInt(5, customer.getCustomer_id());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error updating customer: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";

        try (Connection conn = DB_Operation1.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
            return false;
        }
    }
}
