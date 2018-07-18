package com.zhan.app1;

import java.util.Locale;
import java.util.ResourceBundle;

public class RequestApartment {
    
    private String leaseTime;
    private String numberRooms;
    private String desiredPrice;
    private String recommendation;
    private String phone;
    
    private String message;
    
    private final Locale current = Locale.getDefault();    
    private final Locale lang = new Locale(current.getLanguage(), current.getCountry());
    private final ResourceBundle res = ResourceBundle.getBundle("text", lang);
        
    public RequestApartment(String leaseTime, String numberRooms, String desiredPrice, String recommendation, String phone){
        this.leaseTime = leaseTime;
        this.numberRooms = numberRooms;
        this.desiredPrice = desiredPrice;
        this.recommendation = recommendation;
        this.phone = phone;  
    }
    
     public boolean validate(){
         
        if(leaseTime.isEmpty())
        {
             message = res.getString("emptyleasetime");
             return false;
        }

        if(desiredPrice.isEmpty())
        {
            message = res.getString("emptydesiredprice");
            return false;
        }

        if(recommendation.isEmpty())
        {
            message = res.getString("emptydesiredrecommendation");
            return false;
        }
        
        if(phone.isEmpty())
        {
            message = res.getString("emptydesiredphone");
            return false;
        }
        else if(phone.matches("^\\+?([0-9]{2})?\\(?[0-9]{3}\\)?[0-9]{3}\\-?[0-9]{2}\\-?[0-9]{2}$"))
        {
            message = res.getString("invalidphone");
            return false;
        }
         
        
        message = res.getString("yourapplicationisaccepted");
         
         return true;
     }
    
    public String getMessage() 
    {
            return message;
    }

    public String getDesiredPrice() {
        return desiredPrice;
    }

    public String getLeaseTime() {
        return leaseTime;
    }

    public String getNumberRooms() {
        return numberRooms;
    }

    public String getPhone() {
        return phone;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setDesiredPrice(String desiredPrice) {
        this.desiredPrice = desiredPrice;
    }

    public void setLeaseTime(String leaseTime) {
        this.leaseTime = leaseTime;
    }

    public void setNumberRooms(String numberRooms) {
        this.numberRooms = numberRooms;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

}
