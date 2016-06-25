/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationprogram;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gaming
 */
public class SQLHelper {

    private static Connection connection = null;

    public SQLHelper() {

    }

    public void insert() {
        PreparedStatement stmt = null;
        getConnection();
        try{
        String query = "INSERT INTO ADMINISTRATOR.RESERVATION_TABLE " +
                "VALUES (?)";
        stmt = connection.prepareStatement(query);
        
        stmt.setString(1, "Jerry");
        
        stmt.executeUpdate();
        connection.commit();
        stmt.close();
        connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void getConnection() {
        try {
            String dbURL = "jdbc:derby://localhost:1527/RESERVATION_DB";
            String user = "administrator";
            String pass = "1234";
            connection = DriverManager.getConnection(dbURL,user,pass);
            if (connection != null) {
                System.out.println("Successful connected to database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
