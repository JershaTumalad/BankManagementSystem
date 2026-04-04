/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bams;

/**
 *
 * @author princ
 */
public class BamsMainApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       User user = new User();
        new DashboardFrame(user);
    }
}
