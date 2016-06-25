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
           private static final String dbURL = "jdbc:derby://localhost:1527/RESERVATION_DB";
           private static final String user = "administrator";
           private static final String pass = "1234";
    public SQLHelper() {

    }

    public void insert(ReservInfo info) {
        PreparedStatement stmt = null;
        getConnection();
        try{
        String query = "INSERT INTO ADMINISTRATOR.RESERVATION_TABLE " +
                "VALUES (?,?,?,?)";
        stmt = connection.prepareStatement(query);
        
        stmt.setString(1, info.getName());
        stmt.setInt(2, info.getRoomType());
        stmt.setDate(3, info.getCheckinDate());
        stmt.setDate(4, info.getCheckoutDate());
        
        stmt.executeUpdate();
        connection.commit();
        stmt.close();
        connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //delete method
    //edit method
    //refresh list 
    

    private void getConnection() {
        try {

            connection = DriverManager.getConnection(dbURL,user,pass);
            if (connection != null) {
                System.out.println("Successful connected to database");
            }
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
