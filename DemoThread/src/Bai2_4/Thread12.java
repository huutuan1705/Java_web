/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bai2_4;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author huutuan
 */
public class Thread12 extends Thread{
    private int num;

    public Thread12(int num) {
        this.num = num;
    }
    
    public  void run(){
        SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss");
        while(num>=0){
            System.out.println("\33[34m" + f.format(new Date()));
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println(e);
            }
            num--;
        }
    }
}
