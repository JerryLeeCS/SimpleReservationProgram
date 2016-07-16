/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationprogram;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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

    private DynamicTable tableHelper;

    @FXML
    private Button insertButton;

    @FXML
    private TableView<ReservInfo> reservationTable;

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        showSubmitWindow(null);
        tableHelper.setTable();
    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        tableHelper.deleteSelectedRow();
        tableHelper.setTable();
    }

    @FXML
    private void handleEditButtonAction(ActionEvent event) {
        if (tableHelper.rowSelected()) {
            showSubmitWindow(tableHelper.getSelectedItem());
            tableHelper.setTable();
        }
    }

    @FXML
    private void handleRefreshButtonAction(ActionEvent event) throws SQLException {
        tableHelper.setTable();
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        tableHelper = new DynamicTable(reservationTable);
        tableHelper.setTable();
    }

    private void showSubmitWindow(ReservInfo info) {
        ReservationForm form = new ReservationForm();
        form.showPopupWindow(info);
    }

}
