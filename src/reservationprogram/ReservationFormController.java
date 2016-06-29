/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationprogram;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gaming
 */
public class ReservationFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField nameField;
    @FXML
    private TextField roomTypeField;
    @FXML
    private DatePicker checkinField;
    @FXML
    private DatePicker checkoutField;
    
    @FXML 
    private void handleSubmitButtonEvent(ActionEvent event){
        ReservInfo info = new ReservInfo(nameField.getText(), Integer.parseInt(roomTypeField.getText()), Date.valueOf(checkinField.getValue()), Date.valueOf(checkoutField.getValue()));
        SQLHelper helper = new SQLHelper();
        helper.insert(info);
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
