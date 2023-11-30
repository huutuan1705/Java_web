/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bai2_4;

/**
 *
 * @author huutuan
 */
public class Thread1 extends Thread{
    private int num;

    public Thread1(int num) {
        this.num = num;
    }
    
    public  void run(){
        while(num>=0){
            System.out.println("\33[31m count:" + num--);
            try{
                sleep(1000);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
}
