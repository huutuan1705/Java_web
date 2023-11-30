/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example1;

/**
 *
 * @author huutuan
 */
public class DeamonThread extends Thread{
    public void run(){
        int i = 0;
        while(true){
            System.out.println("Num: " + i++);
            try{
                sleep(500);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
    
    public void finalize(){
        System.out.println("STOPPPPPPP");
    }
}
