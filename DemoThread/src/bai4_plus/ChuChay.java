/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bai4_plus;

/**
 *
 * @author huutuan
 */
public class ChuChay implements Runnable{
    public boolean running;
    
    public ChuChay(){
        running = true;
    }
    
    public void stop(){
        running = false;
    }
    
    @Override
    public void run() {
        String line = "HOC VIEN CONG NGHE BUU CHINH VIEN THONG - NHOM 7";
        while(running){
            line = line.charAt(line.length()-1) + line.substring(0, line.length()-1);
            System.out.println(line);
            try{
                Thread.sleep(300);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
    
}
