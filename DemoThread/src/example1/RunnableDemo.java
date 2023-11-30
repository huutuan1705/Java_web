/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example1;

import static java.lang.Thread.sleep;

/**
 *
 * @author huutuan
 */
public class RunnableDemo implements Runnable{
    private String name;

    public RunnableDemo(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " START");
        for(int i=0;i<5;i++){
            System.out.println(name + " " + i);
            try{
                sleep(1500);
            }
            catch(InterruptedException e){
                System.out.println(e);
            }
        }
        System.out.println(name + " STOP");
    }
    
}
