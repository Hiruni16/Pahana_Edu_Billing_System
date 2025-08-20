/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Utils.item_DAO;
import classes.item;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Ensures tests run in order
public class Item_Testing {
    private static item_DAO dao;
    private static Integer testItemId;

    @BeforeClass
    public static void setup() {
        dao = new item_DAO();
    }

    @Test
    public void test1_AddItem() {
        item i = new item();
        i.setItem_name("Test Book");
        i.setItem_category("Books");
        i.setItem_price(450.0);

        boolean added = dao.addItem(i);
        assertTrue("Item should be added successfully", added);

        // Retrieve the last inserted item ID
        List<item> items = dao.getAllItems();
        assertFalse("Item list should not be empty", items.isEmpty());
        testItemId = items.get(items.size() - 1).getItem_id();
        assertNotNull("Test item ID should be set", testItemId);
    }

    @Test
    public void test2_GetItemById() {
        assertNotNull("Test item ID should not be null", testItemId);
        item i = dao.getItemById(testItemId);
        assertNotNull("Item should exist", i);
        assertEquals("Item name should match", "Test Book", i.getItem_name());
    }

    @Test
    public void test3_UpdateItem() {
        assertNotNull("Test item ID should not be null", testItemId);
        item i = dao.getItemById(testItemId);
        i.setItem_name("Updated Book");
        i.setItem_price(500.0);

        boolean updated = dao.updateItem(i);
        assertTrue("Item should be updated", updated);

        item updatedItem = dao.getItemById(testItemId);
        assertEquals("Updated name should match", "Updated Book", updatedItem.getItem_name());
        assertEquals("Updated price should match", 500.0, updatedItem.getItem_price(), 0.01);
    }

    @Test
    public void test4_GetAllItems() {
        List<item> items = dao.getAllItems();
        assertFalse("Item list should not be empty", items.isEmpty());
    }

    @Test
    public void test5_DeleteItem() {
        assertNotNull("Test item ID should not be null", testItemId);
        boolean deleted = dao.deleteItem(testItemId);
        assertTrue("Item should be deleted", deleted);

        item i = dao.getItemById(testItemId);
        assertNull("Deleted item should not exist", i);
    }
}
