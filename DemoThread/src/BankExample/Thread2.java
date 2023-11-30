/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankExample;

/**
 *
 * @author huutuan
 */
public class Thread2 extends Thread{
    String name;
    long value;
    Account account;

    public Thread2(String name, long value, Account account) {
        this.name = name;
        this.value = value;
        this.account = account;
    }
    
    public void run(){
        account.deposit(name, value);
    }
}
