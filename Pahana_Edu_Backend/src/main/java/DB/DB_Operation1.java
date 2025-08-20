/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import static DB.DB_Operation1.PASS;
import static DB.DB_Operation1.URL;
import static DB.DB_Operation1.USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class DB_Operation1 {
    public static final String URL = "jdbc:mysql://localhost:3306/pahana_bookshop?connectTimeout=3000&useSSL=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASS = "@Hiruni1116*";
    
      public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {

            throw new SQLDataException("MySQL JDBC Driver not found.", e);
        }
      }  
}
