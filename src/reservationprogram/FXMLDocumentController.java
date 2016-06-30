/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationprogram;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

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
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {

}
 
    
    
}
    

