/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Utils.bills_DAO;
import classes.bills;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Bill_Testing {
    private static bills_DAO dao;
    private static Integer testBillId;

    @BeforeClass
    public static void setup() {
        dao = new bills_DAO();
    }

    // 1. Test Adding a Bill
    @Test
    public void test1_AddBill() {
        bills b = new bills();
        b.setCustom_id(1);      // Ensure customer with ID=1 exists
        b.setItem_id(1);        // Ensure item with ID=1 exists
        b.setUnits_consumed(5);
        b.setTotal_price(2500.0);

        boolean added = dao.addBill(b);
        assertTrue("Bill should be added successfully", added);

        List<bills> billsList = dao.getAllBills();
        assertFalse("Bill list should not be empty after insert", billsList.isEmpty());

        // Save the last inserted bill's ID for next tests
        testBillId = billsList.get(billsList.size() - 1).getBill_id();
        assertNotNull("Test bill ID should not be null", testBillId);
    }

    // 2. Test Retrieving Bill by ID
    @Test
    public void test2_GetBillById() {
        assertNotNull("Test bill ID should not be null", testBillId);

        bills b = dao.getBillById(testBillId);
        assertNotNull("Bill should exist after insertion", b);

        assertEquals("Units consumed should match", 5, b.getUnits_consumed());
        assertEquals("Total price should match", 2500.0, b.getTotal_price(), 0.01);
    }

    // 3. Test Updating a Bill
    @Test
    public void test3_UpdateBill() {
        assertNotNull("Test bill ID should not be null", testBillId);

        bills b = dao.getBillById(testBillId);
        assertNotNull("Bill should exist before update", b);

        b.setUnits_consumed(10);
        b.setTotal_price(5000.0);

        boolean updated = dao.updateBill(b);
        assertTrue("Bill should be updated successfully", updated);

        bills updatedBill = dao.getBillById(testBillId);
        assertNotNull("Updated bill should exist", updatedBill);

        assertEquals("Updated units consumed should match", 10, updatedBill.getUnits_consumed());
        assertEquals("Updated total price should match", 5000.0, updatedBill.getTotal_price(), 0.01);
    }

    // 4. Test Getting All Bills
    @Test
    public void test4_GetAllBills() {
        List<bills> billsList = dao.getAllBills();
        assertNotNull("Bill list should not be null", billsList);
        assertFalse("Bill list should not be empty", billsList.isEmpty());
    }

    // 5. Test Deleting a Bill
    @Test
    public void test5_DeleteBill() {
        assertNotNull("Test bill ID should not be null", testBillId);

        boolean deleted = dao.deleteBill(testBillId);
        assertTrue("Bill should be deleted successfully", deleted);

        bills b = dao.getBillById(testBillId);
        assertNull("Deleted bill should not exist anymore", b);
    }
}
