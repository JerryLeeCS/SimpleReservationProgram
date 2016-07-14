/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationprogram;

import java.sql.Date;
import javafx.beans.property.*;
import javafx.beans.property.adapter.JavaBeanObjectProperty;

/**
 *
 * @author gaming
 */
public class ReservInfo {

    private StringProperty Name;
    private StringProperty RoomType;
    private StringProperty checkinDate;
    private StringProperty checkoutDate;
    private int ID;
    public void setName(String value) {
        nameProperty().set(value);
    }

    public void setRoomType(String value) {
        roomTypeProperty().set(value);
    }

    public void setCheckinDate(String value) {
        checkinDateProperty().set(value);
    }

    public void setCheckoutDate(String value) {
        checkoutDateProperty().set(value);
    }
    
    public void setID(int ID){
        this.ID = ID;
    }

    public String getName() {
        return nameProperty().get();
    }

    public String getRoomType() {
        return roomTypeProperty().get();
    }

    public String getCheckinDate() {
        return checkinDateProperty().get();
    }

    public String getCheckoutDate() {
        return checkoutDateProperty().get();
    }
    
    
    public String getID(){
        return Integer.toString(ID);
    }

    public StringProperty nameProperty() {
        if (Name == null) {
            Name = new SimpleStringProperty(this, "Name");
        }
        return Name;
    }

    public StringProperty roomTypeProperty() {
        if (RoomType == null) {
            RoomType = new SimpleStringProperty(this, "RoomType");
        }
        return RoomType;
    }

    public StringProperty checkinDateProperty() {
        if (checkinDate == null) {
            checkinDate = new SimpleStringProperty(this, "checkinDate");
        }
        return checkinDate;
    }

    public StringProperty checkoutDateProperty() {
        if (checkoutDate == null) {
            checkoutDate = new SimpleStringProperty(this, "checkoutDate");
        }
        return checkoutDate;
    }
}
