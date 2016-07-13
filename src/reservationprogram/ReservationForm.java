/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationprogram;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author gaming
 */
public class ReservationForm {

    @FXML
    private TextField nameField;

    @FXML
    private TextField roomTypeField;

    @FXML
    private DatePicker checkinField;

    @FXML
    private DatePicker checkoutField;

    private Stage newStage;

    private FXMLLoader loader;

    public ReservationForm() {
        try {
            setNewStage();
        } catch (IOException ex) {
            Logger.getLogger(ReservationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setNewStage() throws IOException {
        loader = new FXMLLoader(getClass().getResource("ReservationForm.fxml"));
        Parent popRoot = (Parent) loader.load();
        Scene popScene = new Scene(popRoot);
        newStage = new Stage();
        newStage.setScene(popScene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("RES FORM");
        newStage.setResizable(false);
    }

    @FXML
    public void showPopupWindow(ReservInfo info) {
        if (info != null) {
            ReservationFormController controller = loader.getController();
            controller.setForm(info);
        }

        newStage.showAndWait();
    }
}
