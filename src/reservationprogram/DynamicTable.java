package reservationprogram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gaming
 */
public class DynamicTable {

    @FXML
    private TableView<ReservInfo> reservationTable;

    private SQLHelper databaseHelper = new SQLHelper();
    
    public DynamicTable(TableView<ReservInfo> reservationTable) {
        this.reservationTable = reservationTable;
        this.reservationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.reservationTable.setEditable(false);
    }

    private ObservableList<ReservInfo> getData() {
        List list;
        try {
            list = new ArrayList(databaseHelper.getListOfReservation());
            ObservableList<ReservInfo> data = FXCollections.observableArrayList();
            ListIterator li = list.listIterator();
            while (li.hasNext()) {
                ReservInfo info = (ReservInfo) li.next();
                data.add(info);
            }

            return data;
        } catch (SQLException ex) {
            Logger.getLogger(DynamicTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteSelectedRow() {
        databaseHelper.deleteRow(getSelectedItem());
    }

    public boolean rowSelected(){
        return !reservationTable.getSelectionModel().isEmpty();
    }

    public ReservInfo getSelectedItem() {
        ReservInfo info = reservationTable.getSelectionModel().getSelectedItem();
        return info;
    }

    private TableColumn<ReservInfo, String> getNameCol() {
        TableColumn<ReservInfo, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("Name"));
        return nameCol;
    }
    
    private TableColumn<ReservInfo, String> getContactInfo(){
        TableColumn<ReservInfo, String> contactInfoCol = new TableColumn<>("Contact Info");
        contactInfoCol.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        return contactInfoCol;
    }

    private TableColumn<ReservInfo, String> getRoomTypeCol() {
        TableColumn<ReservInfo, String> roomTypeCol = new TableColumn<>("Room Type");
        roomTypeCol.setCellValueFactory(new PropertyValueFactory("roomType"));
        return roomTypeCol;
    }
    
    private TableColumn<ReservInfo, String> getNumberOfPeopleCol(){
        TableColumn<ReservInfo, String> numberOfPeopleCol = new TableColumn<>("Number of People");
        numberOfPeopleCol.setCellValueFactory(new PropertyValueFactory<>("numberOfPeople"));
        return numberOfPeopleCol;
    }

    private TableColumn<ReservInfo, String> getCheckinDateCol() {
        TableColumn<ReservInfo, String> checkinDateCol = new TableColumn<>("Checkin Date");
        checkinDateCol.setCellValueFactory(new PropertyValueFactory("checkinDate"));
        return checkinDateCol;
    }

    private TableColumn<ReservInfo, String> getCheckoutDateCol() {
        TableColumn<ReservInfo, String> checkoutDateCol = new TableColumn<>("Checkout Date");
        checkoutDateCol.setCellValueFactory(new PropertyValueFactory("checkoutDate"));
        return checkoutDateCol;
    }
    
    private TableColumn<ReservInfo, String> getRoomPriceCol(){
        TableColumn<ReservInfo, String> roomPriceCol = new TableColumn<>("Room Price");
        roomPriceCol.setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
        return roomPriceCol;
    }

    public void setTable() {
        reservationTable.getColumns().setAll(getNameCol(),getContactInfo() , getRoomTypeCol(),getNumberOfPeopleCol() ,getCheckinDateCol(), getCheckoutDateCol(), getRoomPriceCol());
        reservationTable.setItems(getData());
    }

}
