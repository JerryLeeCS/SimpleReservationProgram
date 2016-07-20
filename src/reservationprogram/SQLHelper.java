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

    public SQLHelper() {
        createTable();

    }

    public void insert(ReservInfo info) {
        PreparedStatement stmt = null;
        getConnection();
        try {
            String query = "INSERT INTO " + dbName + "( " + nameCol + ", " + contactInfoCol + ", " + roomtypeCol + ", " + numberOfPeopleCol + ", " + checkinCol + ", " + checkoutCol + ", " + roomPriceCol + ")"
                    + " VALUES (?,?,?,?,?,?,?)";
            stmt = connection.prepareStatement(query);

            stmt.setString(1, info.getName());
            stmt.setString(2, info.getContactInfo());
            stmt.setString(3, info.getRoomType());
            stmt.setString(4, info.getNumberOfPeople());
            stmt.setString(5, info.getCheckinDate());
            stmt.setString(6, info.getCheckoutDate());
            stmt.setString(7, info.getRoomPrice());

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
                + " SET " + nameCol + " = ?, " + contactInfoCol + " = ?, " + roomtypeCol + " = ?, " + numberOfPeopleCol + " = ?, " + checkinCol + " = ?, " + checkoutCol + " = ?, " + roomPriceCol + " = ? "
                + "WHERE " + idCol + " = ?";
        try {
            stmt = connection.prepareStatement(query);

            stmt.setString(1, newInfo.getName());
            stmt.setString(2, newInfo.getContactInfo());
            stmt.setString(3, newInfo.getRoomType());
            stmt.setString(4, newInfo.getNumberOfPeople());
            stmt.setString(5, newInfo.getCheckinDate());
            stmt.setString(6, newInfo.getCheckoutDate());
            stmt.setString(7, newInfo.getRoomPrice());
            stmt.setString(8, newInfo.getID());

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
                    + " WHERE " + idCol + " = " + info.getID();
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

    public List<ReservInfo> getListOfReservation() throws SQLException {//NEED WORK ON
        getConnection();
        Statement stmt = null;
        String query = "SELECT * FROM " + dbName + " ORDER BY " + checkinCol;
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
                + "PRIMARY KEY (" + idCol +"))";
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
