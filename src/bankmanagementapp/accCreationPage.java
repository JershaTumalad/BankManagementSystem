
package bankmanagementapp;

public class accCreationPage {
   


    
    private String email,userid,pass;
    
   
    public accCreationPage(String userid,String email, String pass){
        
        
    this.userid= userid;
    this.pass= pass;
    this.email= email;
        
        
    }
    
   
    
    public String getUserId(){
        return userid;
    }

    public String getEmail(){
        return email;
    }public String getPassword(){
        return pass;
    }
    
}
