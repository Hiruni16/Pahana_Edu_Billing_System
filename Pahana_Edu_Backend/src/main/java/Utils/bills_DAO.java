/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DB.DB_Operation1;
import classes.bills;
import java.sql.Connection;
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
public class bills_DAO {

    private static final String QUERY_ALL = "SELECT * FROM bills";
    private static final String QUERY_BY_ID = "SELECT * FROM bills WHERE bill_id = ?";
    private static final String INSERT = "INSERT INTO bills (custom_id, item_id, units_consumed, total_price) VALUES (?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE bills SET custom_id = ?, item_id = ?, units_consumed = ?, total_price = ? WHERE bill_id = ?";
    private static final String DELETE = "DELETE FROM bills WHERE bill_id = ?";
    
//    // Retrieve all bills (not implemented yet)
//    public List<bills> getAllBills() {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    // Retrieve bill by ID (not implemented yet)
//    public bills getBillById(int id) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    // Add a new bill (not implemented yet)
//    public boolean addBill(bills bill) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    // Update an existing bill (not implemented yet)
//    public boolean updateBill(bills bill) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    // Delete a bill by ID (not implemented yet)
//    public boolean deleteBill(int billId) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//}
    
    public List<bills> getAllBills() {
        List<bills> billsList = new ArrayList<>();
        try (Connection conn = DB_Operation1.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY_ALL)) {

            while (rs.next()) {
                bills b = new bills();
                b.setBill_id(rs.getInt("bill_id"));
                b.setCustom_id(rs.getInt("custom_id"));
                b.setItem_id(rs.getInt("item_id"));
                b.setUnits_consumed(rs.getInt("units_consumed"));
                b.setTotal_price(rs.getDouble("total_price"));
                billsList.add(b);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving bills: " + e.getMessage());
        }

        return billsList;
    }

    public bills getBillById(int id) {
        bills b = null;

        try (Connection conn = DB_Operation1.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(QUERY_BY_ID)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                b = new bills();
                b.setBill_id(rs.getInt("bill_id"));
                b.setCustom_id(rs.getInt("custom_id"));
                b.setItem_id(rs.getInt("item_id"));
                b.setUnits_consumed(rs.getInt("units_consumed"));
                b.setTotal_price(rs.getDouble("total_price"));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving bill by ID: " + e.getMessage());
        }

        return b;
    }

    public boolean addBill(bills bill) {
    try (Connection conn = DB_Operation1.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(INSERT)) {

        // 🔍 DEBUG: Check what columns the 'bills' table has
        System.out.println("=== Columns in 'bills' table (from backend DB) ===");
        ResultSet rs = conn.getMetaData().getColumns(null, null, "bills", null);
        while (rs.next()) {
            System.out.println("Column: " + rs.getString("COLUMN_NAME"));
        }
        System.out.println("===============================================");

        //  Proceed with insert (you can comment this if just testing)
        pstmt.setInt(1, bill.getCustom_id());
        pstmt.setInt(2, bill.getItem_id());
        pstmt.setInt(3, bill.getUnits_consumed());
        pstmt.setDouble(4, bill.getTotal_price());

        int result = pstmt.executeUpdate();
        return result > 0;

    } catch (SQLException e) {
        System.err.println("Error adding bill: " + e.getMessage());
        return false;
    }
}

    
    // Existing methods (getAllBills, getBillById, addBill)...

    public boolean updateBill(bills bill) {
        try (Connection conn = DB_Operation1.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {

            pstmt.setInt(1, bill.getCustom_id());
            pstmt.setInt(2, bill.getItem_id());
            pstmt.setInt(3, bill.getUnits_consumed());
            pstmt.setDouble(4, bill.getTotal_price());
            pstmt.setInt(5, bill.getBill_id());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating bill: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteBill(int billId) {
        try (Connection conn = DB_Operation1.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE)) {

            pstmt.setInt(1, billId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting bill: " + e.getMessage());
            return false;
        }
    }
}
