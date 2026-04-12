
package bankmanagementapp;


import java.util.*;

public class accCreationPage {
   

    //separated lang for storing acc
    
    private String email,userid,pass;
    
   
    public accCreationPage(String userid,String email, String pass){
        
        
    this.userid= userid;
    this.pass= pass;
    this.email= email;
        
        
    }
    
    //setting up a getter for extra na protection of the attributes
    
    public String getUserId(){
        return userid;
    }

    public String getEmail(){
        return email;
    }public String getPassword(){
        return pass;
    }
    
}
