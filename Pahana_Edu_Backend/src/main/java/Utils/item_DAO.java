/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DB.DB_Operation1;
import classes.item;
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
public class item_DAO {
    private static final String URL = DB_Operation1.URL;
    private static final String USER = DB_Operation1.USER;
    private static final String PASS = DB_Operation1.PASS;

    private static final String QUERY_ALL = "SELECT * FROM items";
    private static final String QUERY_BY_ID = "SELECT * FROM items WHERE item_id = ?";
    
//     // Retrieve all items (not implemented yet)
//    public List<item> getAllItems() {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    // Retrieve item by ID (not implemented yet)
//    public item getItemById(int id) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    // Add a new item (not implemented yet)
//    public boolean addItem(item item) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    // Update an existing item (not implemented yet)
//    public boolean updateItem(item item) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    // Delete an item by ID (not implemented yet)
//    public boolean deleteItem(int itemId) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//}

    public List<item> getAllItems() {
        List<item> items = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY_ALL)) {

            while (rs.next()) {
                item i = new item();
                i.setItem_id(rs.getInt("item_id"));
                i.setItem_name(rs.getString("item_name"));
                i.setItem_category(rs.getString("item_category"));
                i.setItem_price(rs.getDouble("item_price"));
                items.add(i);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving items: " + e.getMessage());
            e.printStackTrace();
        }

        return items;
    }

    public item getItemById(int id) {
        item item = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(QUERY_BY_ID)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                item = new item();
                item.setItem_id(rs.getInt("item_id"));
                item.setItem_name(rs.getString("item_name"));
                item.setItem_category(rs.getString("item_category"));
                item.setItem_price(rs.getDouble("item_price"));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving item by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return item;
    }

    public boolean addItem(item item) {
        String sql = "INSERT INTO items (item_name, item_category, item_price) VALUES (?, ?, ?)";

        try (Connection conn = DB_Operation1.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getItem_name());
            pstmt.setString(2, item.getItem_category());
            pstmt.setDouble(3, item.getItem_price());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error adding item: " + e.getMessage());
            return false;
        }
    }

    public boolean updateItem(item item) {
        String sql = "UPDATE items SET item_name = ?, item_category = ?, item_price = ? WHERE item_id = ?";

        try (Connection conn = DB_Operation1.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getItem_name());
            pstmt.setString(2, item.getItem_category());
            pstmt.setDouble(3, item.getItem_price());
            pstmt.setInt(4, item.getItem_id());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error updating item: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteItem(int itemId) {
        String sql = "DELETE FROM items WHERE item_id = ?";

        try (Connection conn = DB_Operation1.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, itemId);
            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting item: " + e.getMessage());
            return false;
        }
    }   
}
