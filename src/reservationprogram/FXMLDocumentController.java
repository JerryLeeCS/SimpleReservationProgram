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

    @FXML
    private Button insertButton;

    @FXML
    private TableView<ReservInfo> reservationTable;

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        ReservationProgram.showPopupWindow();
        showTable();
    }

    @FXML
    private void handleRefreshButtonAction(ActionEvent event) throws SQLException {
        showTable();
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        showTable();
    }

    public void showTable() {
        DynamicTable tableHelper = new DynamicTable();
        reservationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        reservationTable.getColumns().setAll(tableHelper.getNameCol(), tableHelper.getRoomTypeCol(), tableHelper.getCheckinDateCol(), tableHelper.getCheckoutDateCol());
        reservationTable.setItems(tableHelper.getData());
    }

}
