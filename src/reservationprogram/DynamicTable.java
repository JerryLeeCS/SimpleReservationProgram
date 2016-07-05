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
import javafx.scene.control.TableView;

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

    private TableView<ObservableList<String>> table;

    public DynamicTable() {

    }

    public TableView showTable() {
        SQLHelper helper = new SQLHelper();
        List list;
        try {
            list = new ArrayList(helper.getListOfReservation());
            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            ListIterator li = list.listIterator();
            while (li.hasNext()) {
                ReservInfo info = (ReservInfo) li.next();
                ObservableList<String> l = FXCollections.observableArrayList();
                l.add(info.getName());
                l.add(info.getRoomType());
                l.add(info.getCheckinDate());
                l.add(info.getCheckoutDate());

                System.out.println("Row added" + l);
                data.add(l);
            }

            //table.setItems(data);
            return table;
        } catch (SQLException ex) {
            Logger.getLogger(DynamicTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    public void setTable() {
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        ObservableList<String> l = FXCollections.observableArrayList();
        l.add("Jerry");
        l.add("Lee");
        ObservableList<String> l1 = FXCollections.observableArrayList();
        l1.add("Jessy");
        l1.add("Lee");
        
        data.add(l);
        data.add(l1);
        
        table.setItems(data);
    }
}
