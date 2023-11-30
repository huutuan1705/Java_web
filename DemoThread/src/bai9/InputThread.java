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
public class InputThread extends Thread{
    private Data d;

    public InputThread(Data d) {
        this.d = d;
    }
    
    public void run(){
        Scanner sc = new Scanner(System.in);
        while(d.isRunning()){
            synchronized (d) {
                System.out.print("Nhap so 1: ");
                int num1 = sc.nextInt();
                d.add(num1);
                if(num1 == 0){
                    d.setRunning(false);
                    d.notify();
                    break;
                }
                System.out.print("Nhap so 2: ");
                int num2 = sc.nextInt();
                d.add(num2);
                d.setIndex(2);
                d.notify();
                
                try{
                    d.wait();
                }catch(InterruptedException e){
                    System.out.println(3);
                }
            }
        }
    }
}
