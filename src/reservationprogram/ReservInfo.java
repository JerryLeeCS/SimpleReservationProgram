/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationprogram;

import javafx.beans.property.*;

/**
 *
 * @author gaming
 */
public class ReservInfo {

    private StringProperty Name;
    private StringProperty contactInfo;
    private StringProperty roomType;
    private StringProperty numberOfPeople;
    private StringProperty checkinDate;
    private StringProperty checkoutDate;
    private StringProperty roomPrice;
    private int ID;
    public void setName(String value) {
        nameProperty().set(value);
    }
    
    public void setContactInfo(String value){
        contactInfoProperty().set(value);
    }

    public void setRoomType(String value) {
        roomTypeProperty().set(value);
    }
    
    public void setNumberOfPeople(String value){
        numberOfPeopleProperty().set(value);
    }

    public void setCheckinDate(String value) {
        checkinDateProperty().set(value);
    }

    public void setCheckoutDate(String value) {
        checkoutDateProperty().set(value);
    }
    
    public void setRoomPrice(String value){
        roomPriceProperty().set(value);
    }
    
    public void setID(int ID){
        this.ID = ID;
    }

    public String getName() {
        return nameProperty().get();
    }
    
    public String getContactInfo(){
        return contactInfoProperty().get();
    }

    public String getRoomType() {
        return roomTypeProperty().get();
    }
    
    public String getNumberOfPeople(){
        return numberOfPeopleProperty().get();
    }

    public String getCheckinDate() {
        return checkinDateProperty().get();
    }

    public String getCheckoutDate() {
        return checkoutDateProperty().get();
    }
    
    public String getRoomPrice(){
        return roomPriceProperty().get();
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
    
    public StringProperty contactInfoProperty(){
        if(contactInfo == null){
            contactInfo = new SimpleStringProperty(this, "contactInfo");
        }
        return contactInfo;
    }

    public StringProperty roomTypeProperty() {
        if (roomType == null) {
            roomType = new SimpleStringProperty(this, "roomType");
        }
        return roomType;
    }
    
    public StringProperty numberOfPeopleProperty(){
        if(numberOfPeople == null){
            numberOfPeople = new SimpleStringProperty(this, "numOfPeople");
        }
        return numberOfPeople;
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
    
    public StringProperty roomPriceProperty(){
        if(roomPrice == null){
            roomPrice = new SimpleStringProperty(this, "roomPrice");
        }
        return roomPrice;
    }
}
