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
    private TableView<ReservInfo> table;

    public DynamicTable() {
        table = new TableView<ReservInfo>();
    }

    public ObservableList<ReservInfo> getData() {
        SQLHelper helper = new SQLHelper();
        List list;
        try {
            list = new ArrayList(helper.getListOfReservation());
            ObservableList<ReservInfo> data = FXCollections.observableArrayList();
            ListIterator li = list.listIterator();
            while (li.hasNext()) {
                ReservInfo info = (ReservInfo) li.next();

                System.out.println("Row added" + info.getName()+ " " + info.getRoomType() + " " + info.getCheckinDate() + " " + info.getCheckoutDate());
                data.add(info);
            }

            //table.setItems(data);

            //table.getColumns().setAll(getNameCol(), getRoomTypeCol(), getCheckinDateCol(), getCheckoutDateCol());
           
            return data;
        } catch (SQLException ex) {
            Logger.getLogger(DynamicTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public TableColumn<ReservInfo, String> getNameCol() {
        TableColumn<ReservInfo, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("Name"));
        System.out.println("returned NameCol");
        return nameCol;
    }

    public TableColumn<ReservInfo, String> getRoomTypeCol() {
        TableColumn<ReservInfo, String> roomTypeCol = new TableColumn<>("Room Type");
        roomTypeCol.setCellValueFactory(new PropertyValueFactory("RoomType"));
        System.out.println("returned RoomTypeCol");
        return roomTypeCol;
    }

    public TableColumn<ReservInfo, String> getCheckinDateCol() {
        TableColumn<ReservInfo, String> checkinDateCol = new TableColumn<>("Checkin Date");
        checkinDateCol.setCellValueFactory(new PropertyValueFactory("checkinDate"));
        System.out.println("returned CheckinDateCol");
        return checkinDateCol;
    }

    public TableColumn<ReservInfo, String> getCheckoutDateCol() {
        TableColumn<ReservInfo, String> checkoutDateCol = new TableColumn<>("Checkout Date");
        checkoutDateCol.setCellValueFactory(new PropertyValueFactory("checkoutDate"));
        System.out.println("returned CheckoutDateCol");
        return checkoutDateCol;
    }

    public void knowTable() throws SQLException {
        SQLHelper helper = new SQLHelper();
        List list = new ArrayList(helper.getListOfReservation());
        ListIterator li = list.listIterator();
        while (li.hasNext()) {
            ReservInfo info = (ReservInfo) li.next();
            System.out.println("Name: " + info.getName() + " Room type: " + info.getRoomType());
        }
    }

}
