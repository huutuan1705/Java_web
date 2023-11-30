/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankExample;

/**
 *
 * @author huutuan
 */
public class Thread1 extends Thread{
    String name;
    long value;
    Account account;

    public Thread1(String name, long value, Account account) {
        this.name = name;
        this.value = value;
        this.account = account;
    }
    
    public void run(){
        account.withdraw(name, value);
    }
}
