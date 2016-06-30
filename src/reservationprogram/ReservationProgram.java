/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationprogram;

import java.io.IOException;
import java.sql.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author gaming
 */
public class ReservationProgram extends Application {

    private Stage primaryStage;
    private static Stage newStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        setNewStage();
        setPrimaryStage();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void setPrimaryStage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RESERVATION PROGRAM");
    }

    private void setNewStage() throws IOException {
        Parent popRoot = FXMLLoader.load(getClass().getResource("ReservationForm.fxml"));
        Scene popScene = new Scene(popRoot);
        newStage = new Stage();
        newStage.setScene(popScene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("RES FORM");
    }

    public static void showPopupWindow() {
        newStage.showAndWait();
    }

}
