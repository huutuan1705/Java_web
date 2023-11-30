/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bai2_4;

/**
 *
 * @author huutuan
 */
public class Bai4 {
    public static void main(String[] args) {
        new Thread() {
            public void run(){
                String line = "HOC VIEN CONG NGHE BUU CHINH VIEN THONG";
                while(true){
                    line = line.charAt(line.length()-1) + line.substring(0, line.length()-1);
                    System.out.println(line);
                    try{
                        Thread.sleep(300);
                    }catch(InterruptedException e){
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }
}
