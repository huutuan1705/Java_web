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
public class Main {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println(t.getName() + " START");
        
//        for(int i=0;i<12;i++){
//            System.out.println(t.getName() + " " + i);
//            try{
//                sleep(500);
//            }
//            catch(InterruptedException e){
//                System.out.println(e);
//            }
//        }
        DeamonThread dT = new DeamonThread();
        dT.setDaemon(true);
        dT.start();
        dT.finalize();
//        System.out.println(t.getPriority());
//        System.out.println(t.getState()); 
        ThreadDemo t1 = new ThreadDemo("GIO MOI");
        t1.start();
        
//        RunnableDemo r = new RunnableDemo("Huu Tuan");
//        Thread t2 = new Thread(r);
//        t2.start();

        System.out.println(t.getName() + " STOP");
    }
}
