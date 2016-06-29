/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationprogram;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.util.Callback;

/**
 *
 * @author gaming
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button insertButton;
    
    @FXML
    private TableView<ObservableList<ObservableList>> reservationTable;
    
    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        ReservationProgram.showPopupWindow();
    }
    
    @FXML
    private void handleRefreshButtonAction(ActionEvent event) throws SQLException{
        showTable();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {

}
 
    
    public  void showTable() throws SQLException{
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        SQLHelper helper = new SQLHelper();
        ResultSet rs = helper.getSelectAllResultSet();
        
        for(int i = 0; i < rs.getMetaData().getColumnCount(); i++){
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                    
                    return new SimpleStringProperty(param.getValue().toString());//Watch this out
                }
            });
            
            reservationTable.getColumns().addAll(col);
            System.out.println("Column [" + i + "]");
        }
        
        while(rs.next()){
             ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);
        }
        //reservationTable.setItems(data);
    }
}
    

