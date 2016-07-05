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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        stmt.setString(2, info.getRoomType());
        stmt.setString(3, info.getCheckinDate());
        stmt.setString(4, info.getCheckoutDate());
        
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
    public List<ReservInfo> getListOfReservation()throws SQLException{
        getConnection();
        Statement stmt = null;
        String query = "SELECT * FROM " + dbName;
        List<ReservInfo> list = new ArrayList<>();
        try{
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                ReservInfo info = new ReservInfo();
                info.setName(rs.getString("NAME"));
                info.setRoomType("ROOM_TYPE");
                info.setCheckinDate("CHECKIN_DATE");
                info.setCheckoutDate("CHECKOUT_DATE");
                list.add(info);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(stmt!=null) stmt.close();
            connection.close();
            return list;
        }
    }

    private void getConnection() {
        try {
            connection = DriverManager.getConnection(dbURL,user,pass);
            if (connection != null) {
                System.out.println("Successful connected to database");
            }
            //connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
