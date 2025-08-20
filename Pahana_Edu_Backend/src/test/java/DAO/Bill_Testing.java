package DAO;

import Utils.bills_DAO;
import classes.bills;
import classes.customer;
import classes.item;
import Utils.customer_DAO;
import Utils.item_DAO;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Bill_Testing {

    private static bills_DAO billDao;
    private static customer_DAO custDao;
    private static item_DAO itemDao;

    private static Integer testBillId;
    private static Integer testCustomerId;
    private static Integer testItemId;

    @BeforeClass
    public static void setup() {
        billDao = new bills_DAO();
        custDao = new customer_DAO();
        itemDao = new item_DAO();

        // 1. Create test customer
        customer testCust = new customer();
        testCust.setAccount_no("T-001");
        testCust.setName("Test Customer");
        testCust.setAddress("123, Testing");
        testCust.setTele_no(790281077);

        boolean custAdded = custDao.addCustomer(testCust);
        assertTrue("Test customer should be added", custAdded);
        testCustomerId = custDao.getAllCustomers().get(custDao.getAllCustomers().size() - 1).getCustomer_id();

        // 2. Create test item
        item testIt = new item();
        testIt.setItem_name("Test Item");
        testIt.setItem_category("Book");
        testIt.setItem_price(500.0);

        boolean itemAdded = itemDao.addItem(testIt);
        assertTrue("Test item should be added", itemAdded);
        testItemId = itemDao.getAllItems().get(itemDao.getAllItems().size() - 1).getItem_id();
    }

    // 1. Test Adding a Bill
    @Test
    public void test1_AddBill() {
        bills b = new bills();
        b.setCustom_id(testCustomerId);
        b.setItem_id(testItemId);
        b.setUnits_consumed(5);
        b.setTotal_price(2500.0);

        boolean added = billDao.addBill(b);
        assertTrue("Bill should be added successfully", added);

        List<bills> billsList = billDao.getAllBills();
        assertFalse("Bill list should not be empty after insert", billsList.isEmpty());

        testBillId = billsList.get(billsList.size() - 1).getBill_id();
        assertNotNull("Test bill ID should not be null", testBillId);
    }

    // 2. Test Retrieving Bill by ID
    @Test
    public void test2_GetBillById() {
        assertNotNull("Test bill ID should not be null", testBillId);

        bills b = billDao.getBillById(testBillId);
        assertNotNull("Bill should exist after insertion", b);

        assertEquals("Units consumed should match", 5, b.getUnits_consumed());
        assertEquals("Total price should match", 2500.0, b.getTotal_price(), 0.01);
    }

    // 3. Test Updating a Bill
    @Test
    public void test3_UpdateBill() {
        assertNotNull("Test bill ID should not be null", testBillId);

        bills b = billDao.getBillById(testBillId);
        assertNotNull("Bill should exist before update", b);

        b.setUnits_consumed(10);
        b.setTotal_price(5000.0);

        boolean updated = billDao.updateBill(b);
        assertTrue("Bill should be updated successfully", updated);

        bills updatedBill = billDao.getBillById(testBillId);
        assertNotNull("Updated bill should exist", updatedBill);

        assertEquals("Updated units consumed should match", 10, updatedBill.getUnits_consumed());
        assertEquals("Updated total price should match", 5000.0, updatedBill.getTotal_price(), 0.01);
    }

    // 4. Test Getting All Bills
    @Test
    public void test4_GetAllBills() {
        List<bills> billsList = billDao.getAllBills();
        assertNotNull("Bill list should not be null", billsList);
        assertFalse("Bill list should not be empty", billsList.isEmpty());
    }

    // 5. Test Deleting a Bill
    @Test
    public void test5_DeleteBill() {
        assertNotNull("Test bill ID should not be null", testBillId);

        boolean deleted = billDao.deleteBill(testBillId);
        assertTrue("Bill should be deleted successfully", deleted);

        bills b = billDao.getBillById(testBillId);
        assertNull("Deleted bill should not exist anymore", b);
    }
}
