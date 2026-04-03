package project;

public class Main {
    
    public static void main (String[] args){

        accountFiles files = new accountFiles();
        
        menuFrame hp = new menuFrame(files);
        hp.setVisible(true);
    }

}
