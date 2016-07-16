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
    }

    private ObservableList<ReservInfo> getData() {
        List list;
        try {
            list = new ArrayList(databaseHelper.getListOfReservation());
            ObservableList<ReservInfo> data = FXCollections.observableArrayList();
            ListIterator li = list.listIterator();
            while (li.hasNext()) {
                ReservInfo info = (ReservInfo) li.next();

                System.out.println("Row added" + info.getName() + " " + info.getRoomType() + " " + info.getCheckinDate() + " " + info.getCheckoutDate());
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
        System.out.println("returned NameCol");
        return nameCol;
    }

    private TableColumn<ReservInfo, String> getRoomTypeCol() {
        TableColumn<ReservInfo, String> roomTypeCol = new TableColumn<>("Room Type");
        roomTypeCol.setCellValueFactory(new PropertyValueFactory("RoomType"));
        System.out.println("returned RoomTypeCol");
        return roomTypeCol;
    }

    private TableColumn<ReservInfo, String> getCheckinDateCol() {
        TableColumn<ReservInfo, String> checkinDateCol = new TableColumn<>("Checkin Date");
        checkinDateCol.setCellValueFactory(new PropertyValueFactory("checkinDate"));
        System.out.println("returned CheckinDateCol");
        return checkinDateCol;
    }

    private TableColumn<ReservInfo, String> getCheckoutDateCol() {
        TableColumn<ReservInfo, String> checkoutDateCol = new TableColumn<>("Checkout Date");
        checkoutDateCol.setCellValueFactory(new PropertyValueFactory("checkoutDate"));
        System.out.println("returned CheckoutDateCol");
        return checkoutDateCol;
    }

    public void setTable() {
        reservationTable.getColumns().setAll(getNameCol(), getRoomTypeCol(), getCheckinDateCol(), getCheckoutDateCol());
        reservationTable.setItems(getData());
    }

}
