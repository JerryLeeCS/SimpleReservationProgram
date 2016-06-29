/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationprogram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.TableColumn;

/**
 *
 * @author gaming
 */
public class SQLHelper {

    private static Connection connection = null;
           private static final String dbURL = "jdbc:derby://localhost:1527/RESERVATION_DB";
           private static final String dbName ="ADMINISTRATOR.RESERVATION_TABLE";
           private static final String user = "administrator";
           private static final String pass = "1234";
    public SQLHelper() {

    }

    public void insert(ReservInfo info) {
        PreparedStatement stmt = null;
        getConnection();
        try{
        String query = "INSERT INTO " + dbName +
                " VALUES (?,?,?,?)";
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
    public void showDatabaseTable()throws SQLException{
        getConnection();
        Statement stmt = null;
        String query = "SELECT * FROM " + dbName;
        
        try{
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                System.out.println("Name: " + rs.getString("NAME") + " ROOM TYPE: " + rs.getInt("ROOM_TYPE") + " CHECKIN: "+ rs.getDate("CHECKIN_DATE") + "CHECKOUT: "+ rs.getDate("CHECKOUT_DATE"));
                
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(stmt!=null) stmt.close();
            connection.close();
        }
    }

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

    public ResultSet getSelectAllResultSet()throws SQLException{
        getConnection();
        String query = "SELECT * FROM " + dbName;
        ResultSet rs = connection.createStatement().executeQuery(query);
        connection.close();
        return rs;
    }
}
