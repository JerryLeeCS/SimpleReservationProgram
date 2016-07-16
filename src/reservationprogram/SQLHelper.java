/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationprogram;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaming
 */
public class SQLHelper {

    private static Connection connection = null;
    private static final String dbURL = "jdbc:derby:myDB;create=true;user=administrator;password=1234";
    private static final String dbName = "ADMINISTRATOR.RESERVATION_TB";
    private static final String user = "administrator";
    private static final String pass = "1234";

    public SQLHelper() {
        createTable();
    }

    public void insert(ReservInfo info) {
        PreparedStatement stmt = null;
        getConnection();
        try {
            String query = "INSERT INTO " + dbName + "(NAME, ROOM_TYPE, CHECKIN_DATE, CHECKOUT_DATE)"
                    + " VALUES (?,?,?,?)";
            stmt = connection.prepareStatement(query);

            stmt.setString(1, info.getName());
            stmt.setString(2, info.getRoomType());
            stmt.setString(3, info.getCheckinDate());
            stmt.setString(4, info.getCheckoutDate());

            System.err.println("Info inserted: " + info.getName() + " " + info.getRoomType() + " " + info.getCheckinDate() + " " + info.getCheckoutDate());

            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ReservInfo newInfo) {
        PreparedStatement stmt = null;
        getConnection();
        String query = "UPDATE " + dbName
                + " SET NAME = ?, ROOM_TYPE = ?, CHECKIN_DATE = ?, CHECKOUT_DATE = ? "
                + "WHERE ID = ?";
        try {
            stmt = connection.prepareStatement(query);
            
            stmt.setString(1, newInfo.getName());
            stmt.setString(2, newInfo.getRoomType());
            stmt.setString(3, newInfo.getCheckinDate());
            stmt.setString(4,newInfo.getCheckoutDate());
            stmt.setString(5, newInfo.getID());
            
            System.out.println("Update newInfo: " + newInfo.getName());
            
            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRow(ReservInfo info) {
        if (info != null) {
            getConnection();
            Statement stmt = null;
            String deleteQuery = "DELETE FROM " + dbName
                    + " WHERE ID = " + info.getID();
            try {
                stmt = connection.createStatement();
                stmt.executeUpdate(deleteQuery);
                stmt.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("Selected info for delete is null");
        }
    }

    public List<ReservInfo> getListOfReservation() throws SQLException {
        getConnection();
        Statement stmt = null;
        String query = "SELECT * FROM " + dbName + " ORDER BY CHECKIN_DATE";
        List<ReservInfo> list = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ReservInfo info = new ReservInfo();
                info.setName(rs.getString("NAME"));
                info.setRoomType(rs.getString("ROOM_TYPE"));
                info.setCheckinDate(rs.getDate("CHECKIN_DATE").toString());
                info.setCheckoutDate(rs.getDate("CHECKOUT_DATE").toString());
                info.setID(rs.getInt("ID"));
                System.out.println("reservationprogram.SQLHelper.getListOfReservation() " + rs.getInt("ID"));
                list.add(info);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            connection.close();
            return list;
        }
    }

    private void createTable() {//MAKING
        getConnection();
        String createTable = "CREATE TABLE RESERVATION_TB (ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1),NAME VARCHAR(30),"
                + "ROOM_TYPE VARCHAR(15), CHECKIN_DATE DATE, CHECKOUT_DATE DATE, PRIMARY KEY (ID))";

        try {
            Statement statment = connection.createStatement();
            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet rs = dmd.getTables(null, "ADMINISTRATOR", "RESERVATION_TB", null);
            if (!rs.next()) {
                statment.executeUpdate(createTable);
            }
            System.out.println("Table created.");
            statment.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void deleteTable() {
        getConnection();
        String deleteQuery = "DROP TABLE RESERVATION_TB";

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(deleteQuery);
        } catch (SQLException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();//MAKING
            connection = DriverManager.getConnection(dbURL, user, pass);
            if (connection != null) {
                System.out.println("Successful connected to database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
