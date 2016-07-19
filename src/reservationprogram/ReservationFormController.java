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
    private TextField contactInfoField;
    @FXML
    private TextField roomTypeField;
    @FXML
    private TextField numberOfPeopleField;
    @FXML
    private DatePicker checkinField;
    @FXML
    private DatePicker checkoutField;
    @FXML
    private TextField roomPriceField;

    private boolean edit = false;

    private ReservInfo info;

    @FXML
    private void handleSubmitButtonEvent(ActionEvent event) {
        SQLHelper helper = new SQLHelper();

        if (edit) {
            setInfo();
            helper.update(info);
        } else {
            info = new ReservInfo();
            setInfo();
            if (!info.getName().isEmpty()) {
                helper.insert(info);
            }

        }
        //eraseFields();
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    private void setInfo() {
        info.setName(nameField.getText());
        info.setContactInfo(contactInfoField.getText());
        info.setRoomType(roomTypeField.getText());
        info.setNumberOfPeople(numberOfPeopleField.getText());
        info.setCheckinDate(checkinField.getEditor().getText());
        info.setCheckoutDate(checkoutField.getEditor().getText());
        info.setRoomPrice(roomPriceField.getText());
    }

    private void eraseFields() {
        nameField.clear();
        roomTypeField.clear();
        checkinField.getEditor().clear();
        checkoutField.getEditor().clear();

    }

    public void setForm(ReservInfo info) {
        nameField.setText(info.getName());
        contactInfoField.setText(info.getContactInfo());
        roomTypeField.setText(info.getRoomType());
        numberOfPeopleField.setText(info.getNumberOfPeople());
        checkinField.getEditor().setText(info.getCheckinDate());
        checkoutField.getEditor().setText(info.getCheckoutDate());
        roomPriceField.setText(info.getRoomPrice());
        edit = true;
        this.info = info;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        checkinField.setPromptText("MM/dd/yyyy");
        checkoutField.setPromptText("MM/dd/yyyy");
    }

}
