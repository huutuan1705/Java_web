/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankExample;

/**
 *
 * @author huutuan
 */
public class Account {
    private long balance = 3000;
    public synchronized boolean check(long value){
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
        return balance >= value;
    }
    
    public synchronized void withdraw(String name, long value){
        System.out.println(name + " withdraw: " + value);
//        synchronized(this){
//            if(check(value)){
//                try{
//                    Thread.sleep(1000);
//                }catch(InterruptedException e){
//                    System.out.println(e);
//                }
//                balance -= value;
//                System.out.println(name + " successfull!!");
//            } else{
//                System.out.println("ERROR");
//            }
//        }
//        
//        System.out.println(name + " balance: " + balance);

        if(!check(value)){
            try{
                wait();
                System.out.println("Wating.....");
            }catch(InterruptedException e){
                System.out.println(e);
            }
            
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
        balance -= value;
        System.out.println(name + " successfull!!");
        System.out.println("Balance: " + balance);
    }
    
    public synchronized void deposit(String name, long value){
        System.out.println(name + " deposit: " + value);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
        balance += value;
        notify();
    }
}
