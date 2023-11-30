/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankExample;

/**
 *
 * @author huutuan
 */
public class Main {
    public static void main(String[] args) {
        Account a = new Account();
        Thread1 t1 = new Thread1("husband", 3500, a);
        t1.start();
        Thread2 t2 = new Thread2("wife", 1000, a);
        t2.start();
    }
}
