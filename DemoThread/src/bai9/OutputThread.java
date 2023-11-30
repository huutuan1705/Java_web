/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bai9;

import java.util.Scanner;

/**
 *
 * @author huutuan
 */
public class OutputThread extends Thread{
    private Data d;

    public OutputThread(Data d) {
        this.d = d;
    }
    
    public void run(){
        while(d.isRunning()){
            synchronized (d) {
                try{
                    d.wait();
                }catch(InterruptedException e){
                    System.out.println(3);
                }
                
                int num1 = d.get();
                if(num1 == 0){
                    d.setRunning(false);
                    d.notify();
                    break;
                }
                
                int num2 = d.get();
                
                System.out.println("Tong: " + (num1 + num2));
                
                d.setIndex(1);
                d.notify();
            }
        }
    }
}
