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
    private static final String nameCol = "NAME";
    private static final String contactInfoCol = "CONTACT_INFO";
    private static final String roomtypeCol = "ROOM_TYPE";
    private static final String numberOfPeopleCol = "NUMBER_OF_PEOPLE";
    private static final String checkinCol = "CHECKIN_DATE";
    private static final String checkoutCol = "CHECKOUT_DATE";
    private static final String roomPriceCol = "ROOM_PRICE";
    private static final String idCol = "ID";
    
    private Statement stmt;
    private PreparedStatement preStmt;
    
    public SQLHelper() {
        createTable();
        
    }
    
    public void insert(ReservInfo info) {
        String query = "INSERT INTO " + dbName + "( " + nameCol + ", " + contactInfoCol + ", " + roomtypeCol + ", " + numberOfPeopleCol + ", " + checkinCol + ", " + checkoutCol + ", " + roomPriceCol + ")"
                + " VALUES (?,?,?,?,?,?,?)";
        doPreparedStatement(query, info);
    }
    
    public void update(ReservInfo newInfo) {
        String query = "UPDATE " + dbName
                + " SET " + nameCol + " = ?, " + contactInfoCol + " = ?, " + roomtypeCol + " = ?, " + numberOfPeopleCol + " = ?, " + checkinCol + " = ?, " + checkoutCol + " = ?, " + roomPriceCol + " = ? "
                + "WHERE " + idCol + " = ?";
        doPreparedStatement(query, newInfo);
    }
    
    public void deleteRow(ReservInfo info) {
        if (info != null) {
            String deleteQuery = "DELETE FROM " + dbName
                    + " WHERE " + idCol + " = " + info.getID();
            doStatementUpdate(deleteQuery);
        }
    }
    
    public List<ReservInfo> getListOfReservation() throws SQLException {//NEED WORK ON
        deleteDataBeforeToday();
        getConnection();
        Statement stmt = null;
        String query = "SELECT * FROM " + dbName + " ORDER BY " + checkinCol + " , " + nameCol;
        
        List<ReservInfo> list = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ReservInfo info = new ReservInfo();
                info.setName(rs.getString(nameCol));
                info.setContactInfo(rs.getString(contactInfoCol));
                info.setRoomType(rs.getString(roomtypeCol));
                info.setNumberOfPeople(rs.getString(numberOfPeopleCol));
                info.setCheckinDate(rs.getDate(checkinCol).toString());
                info.setCheckoutDate(rs.getDate(checkoutCol).toString());
                info.setRoomPrice(rs.getString(roomPriceCol));
                info.setID(rs.getInt(idCol));
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
    
    private void deleteDataBeforeToday() {
        String query = "DELETE FROM " + dbName
                + " WHERE CURRENT_DATE > " + checkinCol;
        doStatementUpdate(query);
    }
    
    private void createTable() {//MAKING
        getConnection();
        String createTable = "CREATE TABLE RESERVATION_TB  "
                + "( " + idCol + " INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1), "
                + nameCol + " VARCHAR(30), "
                + contactInfoCol + " VARCHAR(50), "
                + roomtypeCol + " VARCHAR(15), "
                + numberOfPeopleCol + " VARCHAR(12), "
                + checkinCol + " DATE, "
                + checkoutCol + " DATE, "
                + roomPriceCol + " VARCHAR(12), "
                + "PRIMARY KEY (" + idCol + "))";
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
        String deleteQuery = "DROP TABLE RESERVATION_TB";
        doStatementUpdate(deleteQuery);
    }
    
    private void doStatementUpdate(String query) {
        getConnection();
        stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            if (stmt != null) {
                stmt.close();
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void doPreparedStatement(String query, ReservInfo info) {
        preStmt = null;
        getConnection();
        try {
            preStmt = connection.prepareStatement(query);
            
            preStmt.setString(1, info.getName());
            preStmt.setString(2, info.getContactInfo());
            preStmt.setString(3, info.getRoomType());
            preStmt.setString(4, info.getNumberOfPeople());
            preStmt.setString(5, info.getCheckinDate());
            preStmt.setString(6, info.getCheckoutDate());
            preStmt.setString(7, info.getRoomPrice());
            if (!info.getID().equals("")) {
                System.out.println("Got in with ID: x" + info.getID()+"x");
                preStmt.setString(8, info.getID());
            }
            
            preStmt.executeUpdate();
            preStmt.close();
            connection.close();
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
