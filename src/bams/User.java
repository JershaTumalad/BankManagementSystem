/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bams;

/**
 *
 * @author princ
 */
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private double balance = 00.00;  
    private ArrayList<String> history = new ArrayList<>();

    private String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        return sdf.format(new Date());
    } public double getBalance() {
        return Math.round(balance * 100.0) / 100.0;  
    } public boolean deposit(double amount) {
        if (amount <= 0 || amount > 999999) return false;  
        balance += amount;
        history.add(getDateTime() + " | 💰 Deposited: PHP " + formatAmount(amount));
        return true;
    } public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        history.add(getDateTime() + " | 💸 Withdraw: PHP " + formatAmount(amount));
        return true;
    } public boolean transfer(double amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        history.add(getDateTime() + " | 📤 Transfer: PHP " + formatAmount(amount));
        return true;
    } public void addHistory(String text) {
        history.add(getDateTime() + " | " + text);
    } public ArrayList<String> getHistory() {
        return new ArrayList<>(history);  
    } private String formatAmount(double amount) {
        return String.format("%.2f", amount);
    } public void reset() {
        balance = 10000.00;
        history.clear();
    }
}