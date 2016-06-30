package reservationprogram;


import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import reservationprogram.SQLHelper;

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
    private TableView<ObservableList> reservationTable;

    public DynamicTable() throws SQLException{
    }
    
   /* public  void showTable() throws SQLException{
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        SQLHelper helper = new SQLHelper();
        ResultSet rs = helper.getSelectAllResultSet();
        
        while(rs.next()){
             ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);
        }
        reservationTable.setItems(data);
    }*/
}
