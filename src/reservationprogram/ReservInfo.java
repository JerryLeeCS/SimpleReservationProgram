/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationprogram;

import java.sql.Date;

/**
 *
 * @author gaming
 */
public class ReservInfo {
    private String Name;
    private int RoomType;
    private Date checkinDate;
    private Date checkoutDate;
    
    public ReservInfo(String Name, int RoomType, Date checkinDate, Date checkoutDate){
        this.Name = Name;
        this.RoomType = RoomType;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }
    
    public String getName(){
        return Name;
    }
    
    public int getRoomType(){
        return RoomType;
    }
    
    public Date getCheckinDate(){
        return checkinDate;
    }
    
    public Date getCheckoutDate(){
        return checkoutDate;
    }
}
