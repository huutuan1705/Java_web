/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example1;

/**
 *
 * @author huutuan
 */
public class ThreadDemo extends Thread{
    public ThreadDemo(String name){
        super(name);
    }
    
    public void run(){
        System.out.println(this.getName() + " START");
        for(int i=0;i<5;i++){
            System.out.println(this.getName() + " " + i);
            try{
                sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println(e);
            }
        }
        System.out.println(this.getName() + " STOP");
    }
}
