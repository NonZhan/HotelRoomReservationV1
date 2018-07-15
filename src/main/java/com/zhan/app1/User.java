package com.zhan.app1;

import java.util.Locale;
import java.util.ResourceBundle;

public class User {
    
    Locale current = Locale.getDefault();    
    Locale lang = new Locale(current.getLanguage(), current.getCountry());
    ResourceBundle res = ResourceBundle.getBundle("text", lang);
    
    private String email = "";
    private String password = "";

    private String message = "";

    public User() 
    {

    }

    public String getMessage() 
    {
            return message;
    }

    public User(String email, String password) 
    {
            this.email = email;
            this.password = password;
    }

    public String getEmail() 
    {
            return email;
    }

    public void setEmail(String email) 
    {
            this.email = email;
    }

    public String getPassword() 
    {
            return password;
    }

    public void setPassword(String password) 
    {
            this.password = password;
    }

    public boolean validate() 
    {

            if(email.isEmpty()) 
            {
                    message = res.getString("Invalidemail");
                    return false;
            }

            if(password.isEmpty()) 
            {
                    message = res.getString("Invalidpassword");
                    return false;
            }

            if(!email.matches("\\w+@\\w+\\.\\w+")) 
            {
                    message = res.getString("Invalidemail");
                    return false;
            }

            if(password.length() < 8) 
            {
                    message = res.getString("Passwordmustleast");
                    return false;
            }
            else if(password.matches("\\w*\\s+\\w*")) 
            {
                    message = res.getString("Passwordcannotspace");
                    return false;
            }
            
            

            return true;
    }
}
